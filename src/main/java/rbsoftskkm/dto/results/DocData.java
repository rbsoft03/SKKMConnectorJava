package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Счетчик документов */
public class DocData {
    /** Количество документов */
    @JsonProperty("Count")
    public int Count;

    /** Сумма по документам */
    @JsonProperty("Sum")
    public BigDecimal Sum;

    /** Разбивка суммы по видам оплаты */
    @JsonProperty("Payments")
    public DocDataPayments Payments;

    /** Скидки: количество и сумма. */
    @JsonProperty("Discount")
    public RegData Discount;

    /** Надбавки (наценки): количество и сумма. */
    @JsonProperty("Adding")
    public RegData Adding;
}
