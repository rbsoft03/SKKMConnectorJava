package rbsoftskkm.core;

import rbsoftskkm.dto.*;
import rbsoftskkm.dto.admin.*;
import rbsoftskkm.dto.fiscalization.*;
import rbsoftskkm.dto.templates.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Параметры подключения. Порт SkkmConnector.Connection.cs.
 */
public abstract class SkkmConnectorConnection extends SkkmConnectorState {

    /** Хост сервера ККМ (IP или DNS). Можно менять между запросами, пока программа запущена. */
    public String Host = "localhost";

    /** TCP-порт сервера ККМ. Можно менять между запросами, пока программа запущена. */
    public int Port = 4398;

    /** HTTPS вместо HTTP */
    public boolean UseHttps;

    /** Таймаут запроса к серверу ККМ. По умолчанию 60 секунд. */
    public Duration Timeout;

    /** Токен авторизации (заголовок api_key). Можно менять между запросами, пока программа запущена. */
    public String Token = "";

    /** Идентификатор терминала */
    public String TerminalId = "";

    /** Имя устройства. */
    public String DeviceName = "";

    /** Сведения о кассире (продавце). Создайте объект {@link Cashier} (Name, Vatin). */
    public Cashier Cashier;

    /** Логин для Basic Auth при получении токена. По умолчанию Admin. */
    public String AuthUserName = "Admin";

    /** Пароль для Basic Auth при получении токена. По умолчанию Admin. */
    public String AuthPassword = "Admin";

    /** Имя пула устройств. */
    public String PoolName = "";

    /** Тип отчёта для списка Z-отчётов. */
    public int ReportType;

    /** Идентификатор задания в очереди печати. */
    public String QueueTaskId = "";

    /** Имя картинки или шаблона. */
    public String PictureId = "";

    /** Имя шаблона печати или чека. */
    public String TemplateName = "";

    /** Идентификатор пользователя сервера ККМ. */
    public String UserId = "";

    /** Номер ФН */
    public String FnNumber = "";

    /** Коды маркировки для проверки. */
    public List<String> MarkingCodes = new ArrayList<>();

    /**
     * Настройки кассы для добавления или изменения.
     * Создайте объект {@link DeviceSettings} и заполните нужные поля.
     */
    public DeviceSettings DeviceSettings;

    /**
     * Настройки службы печати. Создайте объект {@link ServiceSettings}
     * (WcfServicePort, WebServicePort, ServiceTimeOut, ProxyServerSettings, MaxQueueSize, RepeatPrintingOnError).
     */
    public ServiceSettings ServiceSettings;

    /**
     * Пользователь сервера ККМ. Создайте объект {@link ServiceUser}
     * (Id, UserName, FullName, Vatin, Role, TokenId, Password).
     */
    public ServiceUser ServiceUser;

    /**
     * Параметры шаблона печати. Создайте объект {@link TemplateParameters}
     * (Name, Type, TemplateItems).
     */
    public TemplateParameters TemplateParameters;

    /**
     * Параметры шаблона чека. Создайте объект {@link CheckTemplateParameters}
     * (Name, Document).
     */
    public CheckTemplateParameters CheckTemplateParameters;

    /**
     * Параметры фискализации / перерегистрации.
     * Создайте объект {@link FiscalizationParameters} и заполните нужные поля.
     */
    public FiscalizationParameters FiscalizationParameters;
}
