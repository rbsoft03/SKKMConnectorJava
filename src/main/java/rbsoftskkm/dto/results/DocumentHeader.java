package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Заголовок фискального документа */
public class DocumentHeader {
    /** Название организации. */
    @JsonProperty("OrganizationInfo")
    public String OrganizationInfo;

    /** Заводской номер ККТ. */
    @JsonProperty("SerialNumber")
    public String SerialNumber;

    /** ИНН организации. */
    @JsonProperty("Vatin")
    public String Vatin;

    /** Кассир. */
    @JsonProperty("Cashier")
    public String Cashier;

    /** Регистрационный номер ККТ. */
    @JsonProperty("RnNumber")
    public String RnNumber;

    /** Фискальный накопитель. */
    @JsonProperty("Fn")
    public String Fn;

    /** Адрес сайта уполномоченного органа (ФНС) в сети «Интернет». */
    @JsonProperty("FnsUrl")
    public String FnsUrl;

    /** Номер смены. */
    @JsonProperty("ShiftNumber")
    public int ShiftNumber;

    /** Номер фискального документа. */
    @JsonProperty("DocNumber")
    public int DocNumber;

    /** Фискальный признак документа. */
    @JsonProperty("FiscalSign")
    public String FiscalSign;

    /** Наименование провайдера ОФД. */
    @JsonProperty("OfdOrganizationName")
    public String OfdOrganizationName;

    /** ИНН провайдера ОФД. */
    @JsonProperty("OfdVatin")
    public String OfdVatin;
}
