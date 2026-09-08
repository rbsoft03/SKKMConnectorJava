package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Позиция сохранённого чека */
public class CheckItem {
    /** Название. */
    @JsonProperty("Name")
    public String Name;

    /** Количество товара. */
    @JsonProperty("Quantity")
    public BigDecimal Quantity;

    /** Цена позиции. */
    @JsonProperty("Price")
    public BigDecimal Price;

    /** Сумма с учётом скидки. */
    @JsonProperty("Sum")
    public BigDecimal Sum;

    /** Отдел. */
    @JsonProperty("Department")
    public Integer Department;

    /** Фискальный режим. */
    @JsonProperty("IsFiscal")
    public boolean IsFiscal;

    /** Ставка НДС. */
    @JsonProperty("TaxValue")
    public int TaxValue;

    /** Сумма НДС. */
    @JsonProperty("TaxSum")
    public BigDecimal TaxSum;

    /** Признак способа расчёта */
    @JsonProperty("PaymentMode")
    public int PaymentMode;

    /** Признак предмета расчёта (тег 1030 / 1212). */
    @JsonProperty("ItemType")
    public int ItemType;

    /** Сумма акциза с учётом копеек, включённая в стоимость предмета расчёта. */
    @JsonProperty("ExciseAmount")
    public BigDecimal ExciseAmount;

    /** Мера количества предмета расчёта. */
    @JsonProperty("MeasureOfQuantity")
    public Integer MeasureOfQuantity;

    /** Скидка (&amp;gt;0) или наценка (&amp;lt;0). */
    @JsonProperty("DiscountInfoValue")
    public BigDecimal DiscountInfoValue;

    /** Дополнительный реквизит предмета расчёта. */
    @JsonProperty("AdditionalAttribute")
    public String AdditionalAttribute;
}
