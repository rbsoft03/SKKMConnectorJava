package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Признак подтверждения кода маркировки при закрытии сессии регистрации:
 * Included - Код маркировки включён в документ реализации
 * NotIncluded - Код маркировки не включён в документ реализации
 */
public enum KmConfirmationType {

    /** Код маркировки включён в документ реализации. */
    Included(0),

    /** Код маркировки не включён в документ реализации. */
    NotIncluded(1);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    KmConfirmationType(int value) {
        this.value = value;
    }
}
