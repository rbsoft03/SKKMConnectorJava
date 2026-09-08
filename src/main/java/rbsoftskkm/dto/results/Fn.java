package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/** Описание фискального накопителя */
public class Fn {
    /** Количество проведённых фискализаций */
    @JsonProperty("FiscalizationsCount")
    public int FiscalizationsCount;

    /** Дата и время последней фискализации. */
    @JsonProperty("FiscalizationDateTime")
    public LocalDateTime FiscalizationDateTime;

    /** Регистрационный номер ККТ (РНМ). */
    @JsonProperty("RnNumber")
    public String RnNumber;

    /** Адрес сайта ФНС, напечатанный на чеке. */
    @JsonProperty("FnsUrl")
    public String FnsUrl;

    /** Email отправителя электронных чеков. */
    @JsonProperty("SenderEmail")
    public String SenderEmail;

    /** Код систем налогообложения */
    @JsonProperty("TaxVariant")
    public int TaxVariant;

    /** Код причины перерегистрации / изменения параметров. */
    @JsonProperty("ReasonCode")
    public int ReasonCode;

    /** Версия ФФД */
    @JsonProperty("FfdVersion")
    public String FfdVersion;

    /** Заводской номер фискального накопителя. */
    @JsonProperty("SerialNumber")
    public String SerialNumber;

    /** Наименование организации */
    @JsonProperty("OrganizationName")
    public String OrganizationName;

    /** ИНН владельца ККТ. */
    @JsonProperty("Vatin")
    public String Vatin;

    /** Дата окончания срока действия ФН. */
    @JsonProperty("ValidityDate")
    public LocalDateTime ValidityDate;

    /** Адрес расчётов */
    @JsonProperty("SaleAddress")
    public String SaleAddress;

    /** Место расчётов */
    @JsonProperty("SaleLocation")
    public String SaleLocation;

    /** Признак агента (тег 1057). */
    @JsonProperty("SignOfAgent")
    public int SignOfAgent;

    /** Номер автомата */
    @JsonProperty("AutomaticNumber")
    public String AutomaticNumber;

    /** Оператор фискальных данных */
    @JsonProperty("Ofd")
    public Ofd Ofd;

    /** Предупреждения ФН */
    @JsonProperty("Warnings")
    public Warnings Warnings;

    /** Разрешённые режимы работы ККТ */
    @JsonProperty("Modes")
    public FnModes Modes;
}
