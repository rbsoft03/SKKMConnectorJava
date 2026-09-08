package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Итог внесений или выемок за смену */
public class ShiftIncome {
    /** Количество операций */
    @JsonProperty("Count")
    public int Count;

    /** Сумма операций. */
    @JsonProperty("Sum")
    public BigDecimal Sum;
}
