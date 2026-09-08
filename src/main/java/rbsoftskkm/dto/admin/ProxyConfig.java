package rbsoftskkm.dto.admin;

/**
 * Настройки прокси-сервера:
 * IsUseProxy - Включить прокси для общих запросов
 * IsUseProxyService - Использовать прокси для службы печати
 * IsUseProxyMarking - Использовать прокси для запросов маркировки
 * IpAddress - IP-адрес или DNS-имя прокси-сервера
 * Port - TCP-порт прокси-сервера
 * Name - Логин для авторизации на прокси
 * Password - Пароль для авторизации на прокси
 */
public class ProxyConfig {
    /** {@code true} — использовать прокси для общих запросов сервера ККМ. */
    public boolean IsUseProxy;

    /** {@code true} — использовать прокси для службы печати. */
    public boolean IsUseProxyService;

    /** {@code true} — использовать прокси для запросов маркировки (ИСМ и связанные). */
    public boolean IsUseProxyMarking;

    /** IP-адрес или DNS-имя прокси-сервера. */
    public String IpAddress = "";

    /** TCP-порт прокси-сервера. */
    public int Port;

    /** Логин для авторизации на прокси (если требуется). */
    public String Name = "";

    /** Пароль для авторизации на прокси (если требуется). */
    public String Password = "";
}
