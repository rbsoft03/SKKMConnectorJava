package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Оплаты из ответа сервера (Payments) */
public class CheckPayments {
    /** Сумма наличной оплаты. */
    @JsonProperty("Cash")
    public BigDecimal Cash;

    /** Сумма безналичными средствами. */
    @JsonProperty("Electronic")
    public BigDecimal Electronic;

    /** Сумма предоплатой (зачётом аванса). */
    @JsonProperty("PrePaid")
    public BigDecimal PrePaid;

    /** Сумма постоплатой (в кредит). */
    @JsonProperty("Credit")
    public BigDecimal Credit;

    /** Сумма встречным предоставлением. */
    @JsonProperty("Barter")
    public BigDecimal Barter;
}
