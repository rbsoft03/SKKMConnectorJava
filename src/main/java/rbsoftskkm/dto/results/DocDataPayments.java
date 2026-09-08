package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Разбивка суммы операций по видам оплаты */
public class DocDataPayments {
    /** Общая сумма оплат. */
    @JsonProperty("Sum")
    public BigDecimal Sum;

    /** Наличные */
    @JsonProperty("Cash")
    public BigDecimal Cash;

    /** Безналичные */
    @JsonProperty("Electronically")
    public BigDecimal Electronically;

    /** Аванс (предоплата). */
    @JsonProperty("Prepaid")
    public BigDecimal Prepaid;

    /** Кредит (постоплата). */
    @JsonProperty("Credit")
    public BigDecimal Credit;

    /** Встречные предоставления (бартер). */
    @JsonProperty("Barter")
    public BigDecimal Barter;
}
