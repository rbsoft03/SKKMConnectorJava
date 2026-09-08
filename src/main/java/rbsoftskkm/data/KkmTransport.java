package rbsoftskkm.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.Base64;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * HTTP-транспорт к серверу ККМ.
 */
public final class KkmTransport implements AutoCloseable {

    public static final ObjectMapper JsonOptions = buildJsonOptions(false);
    public static final ObjectMapper BodyJsonOptions = buildJsonOptions(true);
    private final HttpClient _http = createHttp();
    private boolean _disposed;
    private static final String ApiPath = "/PrintService/api/v4/";
    private static final String JsonMediaType = "application/json";
    private static final Duration DefaultTimeout = Duration.ofSeconds(60);

    public String Host = "localhost";
    public int Port = 4398;
    public boolean UseHttps;
    public String Token;
    public String TerminalId;
    public String BasicAuthUser;
    public String BasicAuthPassword;
    public Duration Timeout = DefaultTimeout;

    public Call Get(String path) {
        return sendAsync("GET", path, null, false);
    }

    public Call Get(String path, boolean useBasicAuth) {
        return sendAsync("GET", path, null, useBasicAuth);
    }

    public Call Post(String path) {
        return sendAsync("POST", path, null, false);
    }

    public Call Post(String path, Object body) {
        return sendAsync("POST", path, body, false);
    }

    public Call Put(String path) {
        return sendAsync("PUT", path, null, false);
    }

    public Call Put(String path, Object body) {
        return sendAsync("PUT", path, body, false);
    }

    public Call Delete(String path) {
        return sendAsync("DELETE", path, null, false);
    }

    /** Аналог Dispose(): закрывает HttpClient. */
    @Override
    public void close() {
        if (_disposed) {
            return;
        }
        _disposed = true;
        _http.close();
    }

    /**
     * Незавершенный вызов. В C# метод возвращает Task и отменяется через CancellationToken,
     * здесь результат забирается через {@link Call#await()}, а отменяется вызов
     * через {@link Call#cancel()} из другого потока.
     */
    public static final class Call {

        private final CompletableFuture<ResponseResult<JsonNode>> future;
        private final Duration timeout;

        Call(CompletableFuture<ResponseResult<JsonNode>> future, Duration timeout) {
            this.future = future;
            this.timeout = timeout;
        }

        static Call completed(ResponseResult<JsonNode> value) {
            return new Call(CompletableFuture.completedFuture(value), DefaultTimeout);
        }

        public void cancel() {
            future.cancel(true);
        }

        public ResponseResult<JsonNode> await() {
            try {
                return future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
            } catch (CancellationException e) {
                return failResult(-3, "Запрос отменён");
            } catch (TimeoutException e) {
                future.cancel(true);
                return failResult(-2, "Превышено время ожидания ответа сервера");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return failResult(-3, "Запрос отменён");
            } catch (Exception e) {
                Throwable cause = e instanceof CompletionException && e.getCause() != null ? e.getCause() : e;
                return failResult(-1, "Ошибка соединения: " + cause.getMessage());
            }
        }
    }

    private Call sendAsync(String method, String relativeUrl, Object body, boolean useBasicAuth) {
        if (_disposed) {
            return Call.completed(failResult(-1, "Коннектор закрыт. Создайте новый SkkmConnector."));
        }
        if (Host == null || Host.isBlank() || Port < 1 || Port > 65535) {
            return Call.completed(failResult(-1, "Укажите Host и Port сервера ККМ."));
        }

        HttpRequest request;
        try {
            request = buildRequest(method, relativeUrl, body, useBasicAuth);
        } catch (URISyntaxException | IOException e) {
            return Call.completed(failResult(-1, "Ошибка соединения: " + e.getMessage()));
        }

        Duration timeout = Timeout == null || Timeout.isZero() || Timeout.isNegative()
                ? DefaultTimeout : Timeout;

        CompletableFuture<ResponseResult<JsonNode>> future = _http
                .sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenApply(KkmTransport::parse);

        return new Call(future, timeout);
    }

    private HttpRequest buildRequest(String method, String relativeUrl, Object body, boolean useBasicAuth)
            throws URISyntaxException, IOException {

        // Таймаута на самом запросе нет намеренно: в C# HttpClient.Timeout выключен,
        // время ограничивает только CancellationTokenSource — его роль здесь играет Call.await().
        HttpRequest.Builder request = HttpRequest.newBuilder(requestUri(relativeUrl))
                .header("Accept", JsonMediaType);

        if (useBasicAuth) {
            addBasicAuth(request);
        } else {
            addApiKey(request);
        }

        if (body != null && !"GET".equals(method) && !"DELETE".equals(method)) {
            String json = BodyJsonOptions.writeValueAsString(body);
            request.header("Content-Type", JsonMediaType + "; charset=utf-8");
            return request.method(method, HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8)).build();
        }

        return request.method(method, HttpRequest.BodyPublishers.noBody()).build();
    }

    private URI requestUri(String relativeUrl) throws URISyntaxException {
        int queryStart = relativeUrl.indexOf('?');
        String path = queryStart < 0 ? relativeUrl : relativeUrl.substring(0, queryStart);
        String query = queryStart < 0 ? "" : relativeUrl.substring(queryStart + 1);

        String scheme = UseHttps ? "https" : "http";
        String fullPath = ApiPath.replaceAll("/$", "") + "/" + path.replaceAll("^/", "");
        return new URI(scheme + "://" + Host + ":" + Port + fullPath + (query.isEmpty() ? "" : "?" + query));
    }

    /**
     * В C# здесь SocketsHttpHandler с PooledConnectionLifetime 2 минуты и
     * PooledConnectionIdleTimeout 1 минута. У java.net.http.HttpClient таких настроек нет,
     * время жизни пула задается только системными свойствами jdk.httpclient.*.
     */
    private static HttpClient createHttp() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    private static ResponseResult<JsonNode> parse(HttpResponse<String> response) {
        int statusCode = response.statusCode();
        String responseBody = response.body();

        if (responseBody == null || responseBody.isBlank()) {
            return failResult(statusCode, describeHttpError(statusCode, statusName(statusCode)));
        }

        try {
            ResponseResult<JsonNode> parsed = JsonOptions.readValue(responseBody,
                    JsonOptions.getTypeFactory().constructParametricType(ResponseResult.class, JsonNode.class));
            if (parsed != null) {
                return parsed;
            }
        } catch (IOException ignored) {
            // как в C#: JsonException глушится, ниже вернется описание ошибки
        }

        return failResult(statusCode, describeHttpError(statusCode, "некорректный ответ сервера"));
    }

    private static ResponseResult<JsonNode> failResult(int code, String description) {
        ResponseResult<JsonNode> result = new ResponseResult<>();
        result.Success = false;
        result.Code = code;
        result.Description = description;
        return result;
    }

    private static String describeHttpError(int statusCode, String fallback) {
        return switch (statusCode) {
            case 401 -> "Ошибка авторизации. Укажите токен или включите анонимный доступ на сервере ККМ.";
            case 403 -> "Доступ запрещён. Проверьте токен API.";
            default -> "Ошибка HTTP " + statusCode + ": " + fallback;
        };
    }

    /** Имя статуса, как его дает HttpStatusCode.ToString() в .NET. */
    private static String statusName(int statusCode) {
        return switch (statusCode) {
            case 400 -> "BadRequest";
            case 401 -> "Unauthorized";
            case 403 -> "Forbidden";
            case 404 -> "NotFound";
            case 405 -> "MethodNotAllowed";
            case 408 -> "RequestTimeout";
            case 409 -> "Conflict";
            case 415 -> "UnsupportedMediaType";
            case 500 -> "InternalServerError";
            case 501 -> "NotImplemented";
            case 502 -> "BadGateway";
            case 503 -> "ServiceUnavailable";
            case 504 -> "GatewayTimeout";
            default -> String.valueOf(statusCode);
        };
    }

    private void addApiKey(HttpRequest.Builder request) {
        if (Token != null && !Token.isEmpty()) {
            request.header("api_key", Token);
        }
        if (TerminalId != null && !TerminalId.isEmpty()) {
            request.header("TerminalId", TerminalId);
        }
    }

    private void addBasicAuth(HttpRequest.Builder request) {
        String user = BasicAuthUser == null ? "" : BasicAuthUser;
        String password = BasicAuthPassword == null ? "" : BasicAuthPassword;
        byte[] bytes = (user + ":" + password).getBytes(StandardCharsets.UTF_8);
        request.header("Authorization", "Basic " + Base64.getEncoder().encodeToString(bytes));
    }

    /**
     * Аналог JsonSerializerOptions из C#. Encoder = UnsafeRelaxedJsonEscaping там нужен,
     * чтобы кириллица не уходила в escape-последовательности; Jackson не экранирует не-ASCII по умолчанию.
     */
    private static ObjectMapper buildJsonOptions(boolean forWriting) {
        SimpleModule dates = new SimpleModule();
        dates.addDeserializer(LocalDateTime.class, new LenientLocalDateTime());

        ObjectMapper mapper = JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .addModule(new JavaTimeModule())
                .addModule(dates)
                .build();

        if (forWriting) {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return mapper;
    }

    /**
     * Сервер отдает даты и как "2026-09-03T16:27:36", и со смещением
     * "2026-09-07T11:30:38.03+08:00". DateTime в .NET разбирает оба варианта.
     */
    private static final class LenientLocalDateTime extends JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            String text = parser.getValueAsString();
            if (text == null || text.isBlank()) {
                return null;
            }
            try {
                return LocalDateTime.parse(text);
            } catch (DateTimeParseException ignored) {
                // ниже пробуем вариант со смещением
            }
            try {
                return OffsetDateTime.parse(text).toLocalDateTime();
            } catch (DateTimeParseException e) {
                return null;
            }
        }
    }
}
