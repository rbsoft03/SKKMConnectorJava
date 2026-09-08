package rbsoftskkm.core;

import com.fasterxml.jackson.databind.JsonNode;
import rbsoftskkm.data.*;
import rbsoftskkm.dto.operations.*;
import rbsoftskkm.dto.results.*;
import rbsoftskkm.dto.templates.*;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Транспортная инфраструктура: выбор соединения, вызов и разбор ответа.
 * Порт SkkmConnector.Internals.cs оригинальной библиотеки.
 */
public abstract class SkkmConnectorInternals extends SkkmConnectorCheckInput {

    private static final DateTimeFormatter DATE_QUERY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FISCAL_DATE = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        protected String deviceQuery() {
        return "device=" + escape(this.DeviceName);
    }

    protected String idQuery() {
        return "id=" + escape(this.DocumentId);
    }

    protected String docIdQuery() {
        return "docId=" + escape(this.DocumentId);
    }

    protected static String escape(String value) {
        return URLEncoder.encode(value == null ? "" : value, StandardCharsets.UTF_8);
    }

    private KkmTransport transport() {
        http.Host = this.Host;
        http.Port = this.Port;
        http.UseHttps = this.UseHttps;
        http.Token = this.Token;
        http.TerminalId = this.TerminalId;
        http.BasicAuthUser = this.AuthUserName;
        http.BasicAuthPassword = this.AuthPassword;
        http.Timeout = this.Timeout;
        return http;
    }

    protected void apply(ResponseResult<JsonNode> result) {
        this.Ok = result.Success;
        this.ErrorCode = result.Code;
        this.ErrorDescription = result.Description == null ? "" : result.Description;
        this.LastResult = result.Result;
        this.FiscalResult = null;

        this.FiscalSign = "";
        JsonNode last = this.LastResult;
        if (last != null && last.isTextual()) {
            this.DocumentId = "";
            String id = last.asText();
            if (this.Ok && !id.isEmpty()) {
                this.DocumentId = id;
            }
            return;
        }

        extractFiscalResult(last);
    }

    /**
     * Разбор Result: заполняет {@link #FiscalResult} и плоские свойства.
     */
    private void extractFiscalResult(JsonNode result) {
        if (result == null || !result.isObject()) {
            return;
        }

        FiscalResult fiscal = readResult(FiscalResult.class);
        if (fiscal == null) {
            return;
        }

        boolean hasFiscal = notEmpty(fiscal.FiscalSign)
                || fiscal.FiscalNumber > 0
                || fiscal.ShiftNumber > 0
                || notEmpty(fiscal.DocId)
                || notEmpty(fiscal.FnNumber)
                || notEmpty(fiscal.RnNumber)
                || fiscal.CashSum != null
                || fiscal.CashDrawer != null
                || fiscal.Backlog != null
                || fiscal.OutputParameters != null
                || fiscal.ShiftState != null
                || notEmpty(fiscal.DateTime)
                || notEmpty(fiscal.FiscalDateTime)
                || notEmpty(fiscal.FnsUrl);

        if (!hasFiscal) {
            return;
        }

        this.FiscalResult = fiscal;

        if (notEmpty(fiscal.DocId)) {
            this.DocumentId = fiscal.DocId;
        }
        if (fiscal.ShiftNumber > 0) {
            this.ShiftNumber = fiscal.ShiftNumber;
        }
        if (fiscal.FiscalNumber > 0) {
            this.CheckNumber = fiscal.FiscalNumber;
        }
        if (fiscal.ShiftState != null) {
            this.CurrentShiftState = fiscal.ShiftState;
        }
        if (notEmpty(fiscal.FnsUrl)) {
            this.FnsUrl = fiscal.FnsUrl;
        }
        if (notEmpty(fiscal.FnNumber)) {
            this.FnNumber = fiscal.FnNumber;
            this.IsFnPresent = true;
        } else if (fiscal.FnNumber != null) {
            this.IsFnPresent = false;
        }
        if (notEmpty(fiscal.RnNumber)) {
            this.RnNumber = fiscal.RnNumber;
            this.IsFiscal = true;
        } else if (fiscal.RnNumber != null) {
            this.IsFiscal = false;
        }
        if (notEmpty(fiscal.FiscalSign)) {
            this.FiscalSign = fiscal.FiscalSign;
        }
        if (notEmpty(fiscal.DateTime)) {
            this.ServerDateTime = fiscal.DateTime;
        }
        if (notEmpty(fiscal.FiscalDateTime)) {
            this.FiscalDateTime = fiscal.FiscalDateTime;
            this.DeviceDateTime = fiscal.FiscalDateTime;
        }

        if (fiscal.CashDrawer != null) {
            this.CashBalance = fiscal.CashDrawer.Sum;
        } else if (fiscal.CashSum != null) {
            this.CashBalance = fiscal.CashSum;
        }

        applyBacklog(fiscal.Backlog);
        applyOutputParameters(fiscal.OutputParameters);
    }

    private void applyBacklog(Backlog backlog) {
        if (backlog == null) {
            return;
        }
        this.BacklogDocumentsCount = backlog.DocumentsCounter;
        if (backlog.DocumentsCounter > 0) {
            this.BacklogFirstDocumentNumber = backlog.DocumentFirstNumber;
            if (backlog.DocumentFirstDateTime != null) {
                this.BacklogFirstDocumentDateTime = backlog.DocumentFirstDateTime;
            }
        } else {
            this.BacklogFirstDocumentNumber = 0;
            this.BacklogFirstDocumentDateTime = null;
        }
    }

    private void applyOutputParameters(FiscalOutputParameters output) {
        if (output == null) {
            return;
        }
        if (output.NumberOfChecks > 0) {
            this.CheckNumberInShift = output.NumberOfChecks;
        }
        if (notEmpty(output.DateTime)) {
            this.FiscalDateTime = output.DateTime;
            this.DeviceDateTime = output.DateTime;
        }
        if (output.ShiftNumber > 0) {
            this.ShiftNumber = output.ShiftNumber;
        }
        if (output.CheckNumber > 0) {
            this.CheckNumber = output.CheckNumber;
        }
        this.CashBalance = output.CashBalance == null ? BigDecimal.ZERO : output.CashBalance;
        if (notEmpty(output.FnValidityDate)) {
            this.FnValidityDate = output.FnValidityDate;
        }
        if (output.ResourcesFn > 0) {
            this.FnDaysResources = output.ResourcesFn;
        } else if (notEmpty(this.FnValidityDate)) {
            LocalDate validUntil = tryParseDate(this.FnValidityDate);
            if (validUntil != null) {
                long days = ChronoUnit.DAYS.between(LocalDate.now(), validUntil);
                this.FnDaysResources = days < 0 ? 0 : (int) days;
            }
        }

        applyBacklog(output.Backlog);

        if (output.FnWarnings != null) {
            this.FnWarnings = output.FnWarnings;
        }
    }

    private static LocalDate tryParseDate(String text) {
        try {
            return LocalDateTime.parse(text).toLocalDate();
        } catch (DateTimeParseException ignored) {
            // ниже пробуем формат даты
        }
        try {
            return LocalDate.parse(text);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    protected PrintTemplate[] readTemplateList() {
        JsonNode last = this.LastResult;
        if (last == null || !last.isArray()) {
            return new PrintTemplate[0];
        }
        List<PrintTemplate> list = new ArrayList<>();
        for (JsonNode item : last) {
            if (item.isTextual()) {
                PrintTemplate template = new PrintTemplate();
                template.Name = item.asText();
                list.add(template);
            } else {
                PrintTemplate parsed = KkmTransport.JsonOptions.convertValue(item, PrintTemplate.class);
                if (parsed != null) {
                    list.add(parsed);
                }
            }
        }
        return list.toArray(new PrintTemplate[0]);
    }

    /** Разбор поля Result последнего ответа в нужный тип. */
    protected <T> T readResult(Class<T> type) {
        JsonNode last = this.LastResult;
        if (last == null || last.isNull() || last.isMissingNode()) {
            return null;
        }
        try {
            return KkmTransport.JsonOptions.convertValue(last, type);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /** Разбор поля Result последнего ответа в список. */
    protected <T> List<T> readResultList(Class<T> type) {
        JsonNode last = this.LastResult;
        if (last == null || !last.isArray()) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        for (JsonNode item : last) {
            T parsed = KkmTransport.JsonOptions.convertValue(item, type);
            if (parsed != null) {
                list.add(parsed);
            }
        }
        return list;
    }

    protected void applyOperation(DeviceTaskInfo operation) {
        this.Operation = operation;
        if (operation != null && notEmpty(operation.DocId)) {
            this.DocumentId = operation.DocId;
        }
    }

    protected void applyDocument(CheckDocument document) {
        this.Check = document;
        if (document == null) {
            return;
        }

        if (notEmpty(document.FiscalSign)) {
            this.FiscalSign = document.FiscalSign;
        }
        if (document.DocNumber > 0) {
            this.CheckNumber = document.DocNumber;
        }
        if (document.ShiftNumber > 0) {
            this.ShiftNumber = document.ShiftNumber;
        }
        if (notEmpty(document.DocId)) {
            this.DocumentId = document.DocId;
        }
        if (document.DocNumberInShift > 0) {
            this.CheckNumberInShift = document.DocNumberInShift;
        }

        DocumentHeader header = document.DocumentHeader;
        if (header != null && notEmpty(header.Fn)) {
            this.FnNumber = header.Fn;
            this.IsFnPresent = true;
        }
        if (header != null && notEmpty(header.RnNumber)) {
            this.RnNumber = header.RnNumber;
            this.IsFiscal = true;
        }
        if (header != null && notEmpty(header.FnsUrl)) {
            this.FnsUrl = header.FnsUrl;
        }

        FiscalResult fiscal = new FiscalResult();
        fiscal.DateTime = document.Date == null ? null : document.Date.toString();
        fiscal.DeviceName = document.DeviceName;
        fiscal.DocId = document.DocId;
        fiscal.FnsUrl = header == null ? null : header.FnsUrl;
        fiscal.FnNumber = header == null ? null : header.Fn;
        fiscal.RnNumber = header == null ? null : header.RnNumber;
        fiscal.FiscalDateTime = document.FiscalDate == null ? null : document.FiscalDate.format(FISCAL_DATE);
        fiscal.FiscalSign = document.FiscalSign;
        fiscal.ShiftNumber = document.ShiftNumber;
        fiscal.FiscalNumber = document.DocNumber;
        this.FiscalResult = fiscal;

        if (notEmpty(fiscal.DateTime)) {
            this.ServerDateTime = fiscal.DateTime;
        }
        if (notEmpty(fiscal.FiscalDateTime)) {
            this.FiscalDateTime = fiscal.FiscalDateTime;
            this.DeviceDateTime = fiscal.FiscalDateTime;
        }
    }

    private KkmTransport.Call beginCall(KkmTransport.Call call) {
        synchronized (callLock) {
            currentCall = call;
        }
        return call;
    }

    private void endCall(KkmTransport.Call call) {
        synchronized (callLock) {
            if (currentCall == call) {
                currentCall = null;
            }
        }
    }

    protected void get(String path) {
        get(path, false);
    }

    protected void get(String path, boolean useBasicAuth) {
        KkmTransport.Call call = beginCall(transport().Get(path, useBasicAuth));
        try {
            apply(call.await());
        } finally {
            endCall(call);
        }
    }

    protected void post(String path) {
        post(path, null);
    }

    protected void post(String path, Object body) {
        KkmTransport.Call call = beginCall(transport().Post(path, body));
        try {
            apply(call.await());
        } finally {
            endCall(call);
        }
    }

    protected void put(String path, Object body) {
        KkmTransport.Call call = beginCall(transport().Put(path, body));
        try {
            apply(call.await());
        } finally {
            endCall(call);
        }
    }

    protected void delete(String path) {
        KkmTransport.Call call = beginCall(transport().Delete(path));
        try {
            apply(call.await());
        } finally {
            endCall(call);
        }
    }

    protected String dateQuery(LocalDate from, LocalDate to) {
        return "from=" + from.format(DATE_QUERY) + "&to=" + to.format(DATE_QUERY);
    }

    /** GET документа по {@link #DocumentId}. */
    protected void getDocumentById(String path) {
        get(path + "?" + idQuery());
        applyDocument(readResult(CheckDocument.class));
    }

    /** GET списка документов по кассе. */
    protected void getCheckList(String path) {
        get(path + "?" + deviceQuery());
        CheckDocument[] checks = readResult(CheckDocument[].class);
        this.Checks = checks == null ? new CheckDocument[0] : checks;
    }

    /** GET списка отчетов за период {@link #ShiftsFrom}..{@link #ShiftsTo}. */
    protected void getReportList(String path, String extraQuery) {
        String query = deviceQuery() + "&" + dateQuery(this.ShiftsFrom, this.ShiftsTo);
        if (extraQuery != null && !extraQuery.isBlank()) {
            query += "&" + extraQuery;
        }
        get(path + "?" + query);
        ShiftListItem[] shifts = readResult(ShiftListItem[].class);
        this.Shifts = shifts == null ? new ShiftListItem[0] : shifts;
    }

    protected static boolean notEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    protected static boolean blank(String value) {
        return value == null || value.isBlank();
    }
}
