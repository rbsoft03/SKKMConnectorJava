package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.CorrectionData;
import rbsoftskkm.dto.Payments;
import java.math.BigDecimal;

/** Тело запроса печати чека коррекции ФФД 1.05. */
public class Correction105Parameters extends CheckbaseParameters {
    /** Тип чека */
    @JsonProperty("PaymentType")
    public int PaymentType;

    /** Код системы налогообложения. */
    @JsonProperty("TaxVariant")
    public int TaxVariant;

    /** Дополнительный реквизит чека (БСО), тег 1192 */
    @JsonProperty("AdditionalAttribute")
    public String AdditionalAttribute;

    /** Данные коррекции. */
    @JsonProperty("CorrectionData")
    public CorrectionData CorrectionData;

    /** Список оплаты */
    @JsonProperty("Payments")
    public Payments Payments;

    /** Сумма расчёта по ставке НДС 0%. */
    @JsonProperty("SumTax0")
    public BigDecimal SumTax0;

    /** Сумма НДС чека по ставке 5%. */
    @JsonProperty("SumTax5")
    public BigDecimal SumTax5;

    /** Сумма НДС чека по ставке 7%. */
    @JsonProperty("SumTax7")
    public BigDecimal SumTax7;

    /** Сумма НДС чека по ставке 10%. */
    @JsonProperty("SumTax10")
    public BigDecimal SumTax10;

    /** Сумма НДС чека по ставке 18%. */
    @JsonProperty("SumTax18")
    public BigDecimal SumTax18;

    /** Сумма НДС чека по ставке 20%. */
    @JsonProperty("SumTax20")
    public BigDecimal SumTax20;

    /** Сумма НДС чека по ставке 22%. */
    @JsonProperty("SumTax22")
    public BigDecimal SumTax22;

    /** Сумма расчёта без НДС. */
    @JsonProperty("SumTaxNone")
    public BigDecimal SumTaxNone;

    /** Сумма НДС чека по ставке 5/105. */
    @JsonProperty("SumTax105")
    public BigDecimal SumTax105;

    /** Сумма НДС чека по ставке 7/107. */
    @JsonProperty("SumTax107")
    public BigDecimal SumTax107;

    /** Сумма НДС чека по расч. ставке 10/110. */
    @JsonProperty("SumTax110")
    public BigDecimal SumTax110;

    /** Сумма НДС чека по расч. ставке 18/118. */
    @JsonProperty("SumTax118")
    public BigDecimal SumTax118;

    /** Сумма НДС чека по расч. ставке 20/120. */
    @JsonProperty("SumTax120")
    public BigDecimal SumTax120;

    /** Сумма НДС чека по расч. ставке 22/122. */
    @JsonProperty("SumTax122")
    public BigDecimal SumTax122;
}
