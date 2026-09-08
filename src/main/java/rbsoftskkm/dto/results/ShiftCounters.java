package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Счетчики фискальных операций за кассовую смену */
public class ShiftCounters {
    /** Общая сумма коррекций за смену. */
    @JsonProperty("SumCorrection")
    public BigDecimal SumCorrection;

    /** Количество коррекций за смену. */
    @JsonProperty("NumberCorrections")
    public int NumberCorrections;

    /** Приход */
    @JsonProperty("Sales")
    public DocData Sales;

    /** Возврат прихода */
    @JsonProperty("SalesReturn")
    public DocData SalesReturn;

    /** Коррекция прихода */
    @JsonProperty("SalesCorrection")
    public DocData SalesCorrection;

    /** Коррекция возврата прихода */
    @JsonProperty("SalesReturnCorrection")
    public DocData SalesReturnCorrection;

    /** Расход */
    @JsonProperty("Purchases")
    public DocData Purchases;

    /** Возврат расхода */
    @JsonProperty("PurchasesReturn")
    public DocData PurchasesReturn;

    /** Коррекция расхода */
    @JsonProperty("PurchasesCorrection")
    public DocData PurchasesCorrection;

    /** Коррекция возврата расхода */
    @JsonProperty("PurchasesReturnCorrection")
    public DocData PurchasesReturnCorrection;
}
