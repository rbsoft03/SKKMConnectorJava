package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Признак агента (тег 1222 ФФД):
 * BankPaymentAgent - Банковский платежный агент
 * BankPaymentSubagent - Банковский платежный субагент
 * PaymentAgent - Платежный агент
 * PaymentSubagent - Платёжный субагент
 * Attorney - Поверенный
 * Commissioner - Комиссионер
 * Agent - Агент (иной тип).
 */
public enum AgentType {

    /** Банковский платёжный агент. */
    BankPaymentAgent(0),

    /** Банковский платёжный субагент. */
    BankPaymentSubagent(1),

    /** Платёжный агент. */
    PaymentAgent(2),

    /** Платёжный субагент. */
    PaymentSubagent(3),

    /** Поверенный. */
    Attorney(4),

    /** Комиссионер. */
    Commissioner(5),

    /** Агент (иной тип). */
    Agent(6);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    AgentType(int value) {
        this.value = value;
    }
}
