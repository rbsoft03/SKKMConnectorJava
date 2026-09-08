package rbsoftskkm.dto.positions;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.enums.*;
import java.math.BigDecimal;

/**
 * Фискальная строка чека (товар / услуга). Основные поля: Name, Quantity, Price, Sum,
 * Tax, SignMethodCalculation, SignCalculationObject; при необходимости Marking, Agent, Vendor.
 */
public class FiscalLine extends Position {
    /** Наименование товара */
    public String Name = "";

    /** Код товара */
    public String ProductCode;

    /** Количество товара */
    public BigDecimal Quantity = BigDecimal.valueOf(1);

    /** Цена единицы товара с учетом скидок/наценок */
    @JsonProperty("PriceWithDiscount")
    public BigDecimal Price;

    /** Конечная сумма по позиции чека с учетом всех скидок/наценок */
    @JsonProperty("SumWithDiscount")
    public BigDecimal Sum;

    /** Сумма скидок и наценок */
    public BigDecimal DiscountSum;

    /** Ставка НДС. Обязательна: сервер отклоняет позицию без ставки */
    public String Tax = "";

    /** Сумма НДС за предмет расчета */
    public BigDecimal TaxSum;

    /** Отдел, по которому ведется продажа */
    public int Department;

    /** Признак способа расчёта. Используйте enum {@link SignMethodCalculation}. */
    public SignMethodCalculation SignMethodCalculation;

    /** Признак предмета расчёта. Используйте enum {@link SignCalculationObject}. */
    public SignCalculationObject SignCalculationObject;

    /** Единица измерения предмета расчета */
    public String MeasurementUnit;

    /** Мера количества предмета расчёта. Используйте enum {@link MeasureOfQuantity}. */
    public MeasureOfQuantity MeasureOfQuantity;

    /** Сумма акциза с учетом копеек */
    public BigDecimal ExciseAmount;

    /** Цифровой код страны происхождения товара */
    public String CountryOfOrigin;

    /** Регистрационный номер таможенной декларации */
    public String CustomsDeclaration;

    /** Признак агента по предмету расчёта. Используйте enum {@link AgentType}. */
    @JsonProperty("SignSubjectCalculationAgent")
    public AgentType AgentSign;

    /** Данные агента. Создайте объект {@link Agent} и заполните нужные поля. */
    @JsonProperty("AgentData")
    public Agent Agent;

    /**
     * Данные поставщика. Создайте объект {@link Vendor}
     * (Name, Phones, Vatin).
     */
    public Vendor Vendor;

    /** Данные кода товарной номенклатуры. Создайте объект {@link Marking}. */
    @JsonProperty("GoodCodeData")
    public Marking Marking;

    /** Код контрольной марки */
    public String MarkingCode;

    /**
     * Описание частичного выбытия. Создайте объект {@link FractionalQuantity}
     * (Numerator, Denominator).
     */
    @JsonProperty("FractionalQuantity")
    public FractionalQuantity Fractional;

    /**
     * Отраслевой реквизит. Создайте объект {@link Industry}
     * (IdentifierFoiv, DocumentDate, DocumentNumber, AttributeValue).
     */
    @JsonProperty("IndustryAttribute")
    public Industry Industry;

    /** Дополнительный реквизит предмета расчета */
    public String AdditionalAttribute;
}
