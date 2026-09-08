package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Признак способа расчёта (тег 1214 ФФД):
 * NotApplicable - Не применяется
 * FullPrepayment - Предоплата полная
 * PartialPrepayment - Предоплата частичная
 * Advance - Аванс
 * FullPayment - Полная оплата
 * PartialPaymentAndCredit - Частичная оплата и кредит
 * CreditTransfer - Передача в кредит
 * CreditPayment - Оплата кредита
 */
public enum SignMethodCalculation {

    /** Не применяется. */
    NotApplicable(0),

    /** Предоплата полная. */
    FullPrepayment(1),

    /** Предоплата частичная. */
    PartialPrepayment(2),

    /** Аванс. */
    Advance(3),

    /** Полная оплата. */
    FullPayment(4),

    /** Частичная оплата и кредит. */
    PartialPaymentAndCredit(5),

    /** Передача в кредит. */
    CreditTransfer(6),

    /** Оплата кредита. */
    CreditPayment(7);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    SignMethodCalculation(int value) {
        this.value = value;
    }
}
