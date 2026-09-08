package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Итоги текущей кассовой смены */
public class ResShiftTotal {
    /** Номер смены. */
    @JsonProperty("ShiftNumber")
    public double ShiftNumber;

    /** Денежный ящик: остаток наличных и число операций. */
    @JsonProperty("CashDrawer")
    public CashDrawer CashDrawer;

    /** Внесения за смену. */
    @JsonProperty("ShiftIncome")
    public ShiftIncome ShiftIncome;

    /** Выемки за смену. */
    @JsonProperty("ShiftOutcome")
    public ShiftIncome ShiftOutcome;

    /** Счетчики фискальных операций за смену */
    @JsonProperty("Counters")
    public ShiftCounters Counters;
}
