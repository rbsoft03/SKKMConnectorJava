package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Вложенный блок OutputParameters в ответе сервера. */
public class FiscalOutputParameters {
    /** Номер чека за смену. */
    @JsonProperty("NumberOfChecks")
    public int NumberOfChecks;

    /** Дата и время ККТ. */
    @JsonProperty("DateTime")
    public String DateTime;

    /** Номер смены. */
    @JsonProperty("ShiftNumber")
    public int ShiftNumber;

    /** Номер фискального документа / чека. */
    @JsonProperty("CheckNumber")
    public int CheckNumber;

    /** Остаток наличных в ящике. */
    @JsonProperty("CashBalance")
    public BigDecimal CashBalance;

    /** Срок действия ФН. */
    @JsonProperty("FnValidityDate")
    public String FnValidityDate;

    /** Очередь непереданных документов. */
    @JsonProperty("Backlog")
    public Backlog Backlog;

    /** Предупреждения ФН. */
    @JsonProperty("FnWarnings")
    public Warnings FnWarnings;

    /** Остаток ресурса ФН в днях. */
    @JsonProperty("ResourcesFn")
    public int ResourcesFn;
}
