package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Признак способа оплаты безналичными:
 * FullPrepayment - Предоплата 100%
 * PartialPrepayment - Предоплата
 * Advance - Аванс
 * FullPayment - Полный расчёт
 * PartialPaymentAndCredit - Частичный расчёт и кредит
 * CreditTransfer - Передача в кредит
 * CreditPayment - Оплата кредита
 */
public enum ElectronicPaymentMethod {

    /** Предоплата 100%. */
    FullPrepayment(0),

    /** Предоплата. */
    PartialPrepayment(1),

    /** Аванс. */
    Advance(2),

    /** Полный расчёт. */
    FullPayment(3),

    /** Частичный расчёт и кредит. */
    PartialPaymentAndCredit(4),

    /** Передача в кредит. */
    CreditTransfer(5),

    /** Оплата кредита. */
    CreditPayment(6);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    ElectronicPaymentMethod(int value) {
        this.value = value;
    }
}
