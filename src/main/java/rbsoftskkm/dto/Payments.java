package rbsoftskkm.dto;

import java.math.BigDecimal;

/**
 * Суммы оплаты по способам расчёта:
 * Cash - Наличными
 * ElectronicPayment - Безналичными
 * AdvancePayment - Предоплатой (зачётом аванса)
 * Credit - Постоплатой (в кредит)
 * CashProvision - Встречным предоставлением
 * Заполните одну или несколько сумм; итог должен соответствовать сумме позиций чека.
 */
public class Payments {
    /** Сумма наличной оплаты. */
    public BigDecimal Cash;

    /** Сумма безналичными средствами. */
    public BigDecimal ElectronicPayment;

    /** Сумма предоплатой (зачётом аванса). */
    public BigDecimal AdvancePayment;

    /** Сумма постоплатой (в кредит). */
    public BigDecimal Credit;

    /** Сумма встречным предоставлением. */
    public BigDecimal CashProvision;
}
