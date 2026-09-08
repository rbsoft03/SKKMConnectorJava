package rbsoftskkm.dto.fiscalization;

import rbsoftskkm.dto.*;
import rbsoftskkm.dto.enums.*;

/**
 * Параметры фискализации / перерегистрации ККТ.
 * Регистрационные данные:
 * DeviceName - Имя кассы на сервере ККМ
 * Cashier - Кассир (объект {@link Cashier}: Name, Vatin)
 * RnNumber - Регистрационный номер ККТ (РНМ)
 * TaxationSystems - Системы налогообложения через запятую (коды 0–5, например "0,1,2")
 * Vatin - ИНН организации
 * CompanyName - Наименование организации
 * Fn - Заводской номер фискального накопителя
 * ФФД и коды изменения сведений:
 * FfdVersionKkt - Версия ФФД ККТ (например "1.05", "1.2")
 * FfdVersionFn - Версия ФФД ФН
 * RegistrationLabelCodes - Коды причин изменения сведений о ККТ (например "3.1")
 * ОФД и отправитель:
 * OfdAddress / OfdPort - Адрес и порт сервера ОФД
 * OfdVatin / OfdName - ИНН и наименование ОФД
 * SenderEmail - Email отправителя чеков
 * ReasonCode - Причина перерегистрации. Используйте enum {@link FiscalizationReasonCode}
 * ИСМ, ФНС, автоматы, агенты:
 * IsmHost / IsmPort - Хост и порт ИСМ (для маркировки)
 * FnsUrl - Адрес сайта ФНС (например "nalog.ru")
 * AutomaticNumber - Номер автоматического устройства для расчётов
 * AgentTypes - Признаки агента через запятую (коды типов агента)
 * Признаки режимов применения ККТ (true / false):
 * IsBsoSign - АС БСО
 * IsMarking - Маркированные товары
 * IsPawnshop - Ломбард
 * IsAssurance - Страхование
 * IsAutomatic - Автоматический режим
 * IsVending - Торговый автомат
 * IsAutomaticPrinter - Принтер в автомате
 * IsOnline - Только интернет-расчёты
 * IsLottery - Лотереи
 * IsGambling - Азартные игры
 * IsExcisable - Подакцизные товары
 * IsService - Услуги
 * IsEncrypted - Шифрование данных
 * IsOffline - Автономный режим (без ОФД)
 * IsCateringServices - Общественное питание
 * IsWholesaleTrade - Оптовая торговля
 * Адрес расчётов:
 * SaleAddress - Адрес места расчётов
 * SaleLocation - Место расчётов (например "Офис", "Торговый зал")
 */
public class FiscalizationParameters {
    /** Имя кассы на сервере ККМ. Если пусто — берётся из {@code kkm.DeviceName}. */
    public String DeviceName = "";

    /**
     * Кассир, выполняющий регистрацию. Создайте объект {@link Cashier} (Name, Vatin).
     * Если не задан — берётся из {@code kkm.Cashier}.
     */
    public Cashier Cashier;

    /** Регистрационный номер ККТ (РНМ), выданный при регистрации в ФНС. */
    public String RnNumber = "";

    /**
     * Применяемые системы налогообложения — коды через запятую
     * (0 — ОСН, 1 — УСН доход, 2 — УСН доход−расход, 3 — ЕНВД, 4 — ЕСХН, 5 — ПСН).
     * Пример: {@code "0,1,2"}.
     */
    public String TaxationSystems = "";

    /** ИНН организации-пользователя ККТ. */
    public String Vatin = "";

    /** Наименование организации-пользователя ККТ. */
    public String CompanyName = "";

    /** Заводской номер фискального накопителя (ФН). */
    public String Fn = "";

    /** Версия формата фискальных документов ККТ. Пример: {@code "1.2"}, {@code "1.05"}. */
    public String FfdVersionKkt = "";

    /** Версия формата фискальных документов ФН. Пример: {@code "1.2"}. */
    public String FfdVersionFn = "";

    /**
     * Коды причин изменения сведений о ККТ (через запятую или точку, по формату сервера).
     * Пример: {@code "3.1"}.
     */
    public String RegistrationLabelCodes = "";

    /** DNS-имя или IP-адрес сервера ОФД. */
    public String OfdAddress = "";

    /** TCP-порт сервера ОФД. */
    public int OfdPort;

    /** Номер автоматического устройства для расчётов (для автоматов / АС). */
    public String AutomaticNumber = "";

    /** Адрес электронной почты отправителя чека (тег 1117). */
    public String SenderEmail = "";

    /**
     * Причина перерегистрации ККТ. Используйте enum {@link FiscalizationReasonCode}.
     * Для первичной регистрации может не требоваться.
     */
    public FiscalizationReasonCode ReasonCode;

    /** Хост ИСМ (информационная система маркировки), если используется маркировка. */
    public String IsmHost = "";

    /** Порт ИСМ. */
    public int IsmPort;

    /** Адрес сайта ФНС. Пример: {@code "nalog.ru"}. */
    public String FnsUrl = "";

    /** ИНН оператора фискальных данных (ОФД). */
    public String OfdVatin = "";

    /** Наименование оператора фискальных данных (ОФД). */
    public String OfdName = "";

    /** Признаки агента — числовые коды через запятую (см. {@link AgentType}). */
    public String AgentTypes = "";

    /** {@code true} — ККТ применяется для формирования АС БСО. */
    public boolean IsBsoSign;

    /** {@code true} — ККТ применяется при продаже маркированных товаров. */
    public boolean IsMarking;

    /** {@code true} — ККТ применяется при осуществлении ломбардной деятельности. */
    public boolean IsPawnshop;

    /** {@code true} — ККТ применяется при осуществлении страховой деятельности. */
    public boolean IsAssurance;

    /** {@code true} — ККТ применяется в автоматическом режиме. */
    public boolean IsAutomatic;

    /** {@code true} — ККТ применяется в составе торгового автомата (вендинг). */
    public boolean IsVending;

    /** {@code true} — в автоматическом устройстве установлен принтер чеков. */
    public boolean IsAutomaticPrinter;

    /** {@code true} — расчёты ведутся только в сети Интернет (без выдачи бумажного чека покупателю на месте). */
    public boolean IsOnline;

    /** {@code true} — ККТ применяется при проведении лотерей. */
    public boolean IsLottery;

    /** {@code true} — ККТ применяется при проведении азартных игр. */
    public boolean IsGambling;

    /** {@code true} — ККТ применяется при продаже подакцизных товаров. */
    public boolean IsExcisable;

    /** {@code true} — ККТ применяется при оказании услуг. */
    public boolean IsService;

    /** {@code true} — данные в ФН шифруются. */
    public boolean IsEncrypted;

    /** {@code true} — автономный режим (без передачи данных в ОФД). */
    public boolean IsOffline;

    /** {@code true} — ККТ применяется при оказании услуг общественного питания. */
    public boolean IsCateringServices;

    /** {@code true} — ККТ применяется при оптовой торговле. */
    public boolean IsWholesaleTrade;

    /** Адрес места осуществления расчётов (улица, дом и т.п.). */
    public String SaleAddress = "";

    /** Место расчётов (краткое наименование: офис, торговый зал, павильон и т.п.). */
    public String SaleLocation = "";
}
