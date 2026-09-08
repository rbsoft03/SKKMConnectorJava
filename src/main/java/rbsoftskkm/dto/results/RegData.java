package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Счётчик скидок или надбавок */
public class RegData {
    /** Количество операций (скидок или надбавок). */
    @JsonProperty("Count")
    public int Count;

    /** Сумма операций. */
    @JsonProperty("Sum")
    public BigDecimal Sum;
}
