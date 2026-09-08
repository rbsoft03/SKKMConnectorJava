package rbsoftskkm.dto;

import java.math.BigDecimal;

/**
 * Суммы НДС по ставкам для чека коррекции ФФД 1.05:
 * SumTax0 / SumTax5 / SumTax7 / SumTax10 / SumTax18 / SumTax20 / SumTax22 - Суммы по ставкам
 * SumTaxNone - Без НДС
 * SumTax105 / SumTax107 / SumTax110 / SumTax118 / SumTax120 / SumTax122 - Расчётные ставки
 * Укажите только те ставки, которые относятся к корректируемому расчёту.
 */
public class Correction105Taxes {
    /** Сумма расчёта по ставке НДС 0%. */
    public BigDecimal SumTax0;

    /** Сумма НДС чека по ставке 5%. */
    public BigDecimal SumTax5;

    /** Сумма НДС чека по ставке 7%. */
    public BigDecimal SumTax7;

    /** Сумма НДС чека по ставке 10%. */
    public BigDecimal SumTax10;

    /** Сумма НДС чека по ставке 18%. */
    public BigDecimal SumTax18;

    /** Сумма НДС чека по ставке 20%. */
    public BigDecimal SumTax20;

    /** Сумма НДС чека по ставке 22%. */
    public BigDecimal SumTax22;

    /** Сумма расчёта без НДС. */
    public BigDecimal SumTaxNone;

    /** Сумма НДС чека по ставке 5/105. */
    public BigDecimal SumTax105;

    /** Сумма НДС чека по ставке 7/107. */
    public BigDecimal SumTax107;

    /** Сумма НДС чека по расч. ставке 10/110. */
    public BigDecimal SumTax110;

    /** Сумма НДС чека по расч. ставке 18/118. */
    public BigDecimal SumTax118;

    /** Сумма НДС чека по расч. ставке 20/120. */
    public BigDecimal SumTax120;

    /** Сумма НДС чека по расч. ставке 22/122. */
    public BigDecimal SumTax122;
}
