package rbsoftskkm.dto.admin;

/**
 * Настройки службы печати:
 * WcfServicePort - TCP-порт WCF-службы сервера ККМ
 * WebServicePort - TCP-порт веб-службы (HTTP API)
 * ServiceTimeOut - Таймаут ожидания ответа службы
 * ProxyServerSettings - Настройки прокси. Создайте объект {@link ProxyConfig}
 * MaxQueueSize - Максимальное число заданий в очереди печати
 * RepeatPrintingOnError - Повторять печать при ошибке ({@code true} / {@code false})
 */
public class ServiceSettings {
    /** TCP-порт WCF-службы сервера ККМ. */
    public int WcfServicePort;

    /** TCP-порт веб-службы (HTTP API). */
    public int WebServicePort;

    /** Таймаут ожидания ответа службы (строка в формате, ожидаемом сервером). */
    public String ServiceTimeOut = "";

    /** Настройки прокси-сервера. Создайте объект {@link ProxyConfig} и заполните нужные поля. */
    public ProxyConfig ProxyServerSettings;

    /** Максимальное число заданий в очереди печати. */
    public int MaxQueueSize;

    /** {@code true} — повторять печать при ошибке; {@code false} — не повторять. */
    public boolean RepeatPrintingOnError;
}
