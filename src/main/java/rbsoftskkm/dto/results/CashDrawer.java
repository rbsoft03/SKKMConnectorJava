package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Состояние денежного ящика: сумма наличных и число операций. */
public class CashDrawer {
    /** Сумма наличных в ящике. */
    @JsonProperty("Sum")
    public BigDecimal Sum;

    /** Количество операций с наличными. */
    @JsonProperty("Count")
    public int Count;
}
