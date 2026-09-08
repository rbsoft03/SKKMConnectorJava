package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Внесения/выемки наличных. */
public class CashdrawParameters extends CheckbaseParameters {
    /** Сумма внесения или выемки */
    @JsonProperty("Sum")
    public BigDecimal Sum;
}
