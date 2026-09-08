package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.enums.*;
import java.math.BigDecimal;

/** Фискальный блок ответа сервера */
public class FiscalResult {
    /** Время операции на сервере. */
    @JsonProperty("datetime")
    public String DateTime;

    /** Название устройства. */
    @JsonProperty("deviceName")
    public String DeviceName;

    /** Идентификатор документа. */
    @JsonProperty("docId")
    public String DocId;

    /** Адрес сайта уполномоченного органа (ФНС) в сети «Интернет». */
    @JsonProperty("fnsUrl")
    public String FnsUrl;

    /** Номер фискального накопителя. */
    @JsonProperty("fnNumber")
    public String FnNumber;

    /** Регистрационный номер ККТ. */
    @JsonProperty("rnNumber")
    public String RnNumber;

    /** Дата и время документа по часам ФН. */
    @JsonProperty("fiscalDatetime")
    public String FiscalDateTime;

    /** Фискальный признак документа. */
    @JsonProperty("fiscalSign")
    public String FiscalSign;

    /** Номер смены. */
    @JsonProperty("shiftNumber")
    public int ShiftNumber;

    /** Номер фискального документа. */
    @JsonProperty("fiscalNumber")
    public int FiscalNumber;

    /** Состояние смены. Используйте enum {@link ShiftState}. */
    @JsonProperty("ShiftState")
    public ShiftState ShiftState;

    /** Сумма наличных в ящике */
    @JsonProperty("CashSum")
    public BigDecimal CashSum;

    /** Состояние денежного ящика. */
    @JsonProperty("CashDrawer")
    public CashDrawer CashDrawer;

    /** Очередь непереданных в ОФД документов. */
    @JsonProperty("Backlog")
    public Backlog Backlog;

    /** Дополнительные параметры вывода (статус ККТ вложенный в Result). */
    @JsonProperty("OutputParameters")
    public FiscalOutputParameters OutputParameters;
}
