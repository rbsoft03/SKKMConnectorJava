package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Остаток наличных в денежном ящике. */
public class CashSum {
    /** Сумма наличных в денежном ящике */
    @JsonProperty("Sum")
    public BigDecimal Sum;
}
