package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.results.ShiftCounters;

/** Необнуляемые счётчики ККТ. */
public class OverallTotals {
    /** Счётчики фискальных операций. */
    @JsonProperty("Counters")
    public ShiftCounters Counters;
}
