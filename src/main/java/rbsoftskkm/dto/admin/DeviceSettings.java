package rbsoftskkm.dto.admin;

import rbsoftskkm.dto.enums.ConnectionMethod;
import rbsoftskkm.dto.enums.DeviceType;

/**
 * Настройки кассы на сервере ККМ:
 * DeviceName - Имя кассы
 * DeviceType - Тип драйвера ККТ. Используйте enum {@link DeviceType}
 * Available - Доступность устройства для печати ({@code true} — доступно)
 * MethodConnection - Способ связи с ККТ. Используйте enum {@link ConnectionMethod}
 * PortNumber - Номер COM-порта (для MethodConnection = Com)
 * BaudRate - Скорость COM-порта (например 9600, 115200)
 * IpAddress - IP-адрес ККТ (для MethodConnection = TcpIp)
 * TcpPort - TCP-порт ККТ (для MethodConnection = TcpIp)
 * Password - Пароль пользователя ККТ
 * AccessPassword - Пароль администратора / доступ к настройкам ККТ
 * SerialNumber - Заводской номер ККТ
 * Vatin - ИНН организации-пользователя ККТ
 * OrganizationName - Наименование организации
 * SaleAddress - Адрес места расчётов
 * ClientSaleLocation - Место расчётов (офис, торговый зал и т.п.)
 * Cashier - Имя кассира по умолчанию
 * CashierVatin - ИНН кассира по умолчанию
 * SenderEmail - Email отправителя чека
 * TimeoutConnection - Таймаут соединения с ККТ, мс
 * TimeoutWaitForPrinting - Таймаут ожидания завершения печати, мс
 * OfdAddress - Адрес (хост) ОФД
 * OfdPort - Порт ОФД
 * Pool - Имя пула устройств (если касса входит в пул)
 * TemplateSettingH1…H5 - Параметры шаблонов печати H1–H5
 */
public class DeviceSettings {
    /** Имя кассы на сервере ККМ (уникальный идентификатор устройства). */
    public String DeviceName = "";

    /** Тип драйвера ККТ. Используйте enum {@link DeviceType}. */
    public DeviceType DeviceType;

    /** {@code true} — устройство доступно для печати; {@code false} — недоступно. */
    public boolean Available;

    /**
     * Способ связи с ККТ. Используйте enum {@link ConnectionMethod}
     * (Com — COM-порт, TcpIp — сеть).
     */
    public ConnectionMethod MethodConnection;

    /** Номер COM-порта. Используется при {@link ConnectionMethod#Com}. */
    public int PortNumber;

    /** Скорость COM-порта (бод). Пример: {@code 9600}, {@code 115200}. */
    public int BaudRate;

    /** IP-адрес ККТ. Используется при {@link ConnectionMethod#TcpIp}. */
    public String IpAddress = "";

    /** TCP-порт ККТ. Используется при {@link ConnectionMethod#TcpIp}. */
    public int TcpPort;

    /** Пароль пользователя ККТ. */
    public String Password = "";

    /** Пароль администратора / доступ к настройкам ККТ. */
    public String AccessPassword = "";

    /** Заводской номер ККТ. */
    public String SerialNumber = "";

    /** ИНН организации-пользователя ККТ. */
    public String Vatin = "";

    /** Наименование организации. */
    public String OrganizationName = "";

    /** Адрес места осуществления расчётов. */
    public String SaleAddress = "";

    /** Место расчётов (краткое наименование: офис, торговый зал и т.п.). */
    public String ClientSaleLocation = "";

    /** Имя кассира по умолчанию для этой кассы. */
    public String Cashier = "";

    /** ИНН кассира по умолчанию. */
    public String CashierVatin = "";

    /** Email отправителя чека (тег 1117). */
    public String SenderEmail = "";

    /** Таймаут соединения с ККТ, миллисекунды. */
    public int TimeoutConnection;

    /** Таймаут ожидания завершения печати, миллисекунды. */
    public int TimeoutWaitForPrinting;

    /** DNS-имя или IP-адрес сервера ОФД. */
    public String OfdAddress = "";

    /** TCP-порт сервера ОФД. */
    public int OfdPort;

    /** Имя пула устройств, в который входит касса (если используется пул). */
    public String Pool = "";

    /** Параметр шаблона печати H1. */
    public String TemplateSettingH1 = "";

    /** Параметр шаблона печати H2. */
    public String TemplateSettingH2 = "";

    /** Параметр шаблона печати H3. */
    public String TemplateSettingH3 = "";

    /** Параметр шаблона печати H4. */
    public String TemplateSettingH4 = "";

    /** Параметр шаблона печати H5. */
    public String TemplateSettingH5 = "";
}
