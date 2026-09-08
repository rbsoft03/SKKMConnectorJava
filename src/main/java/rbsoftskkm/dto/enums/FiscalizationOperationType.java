package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Тип операции фискализации:
 * Registration - Регистрация
 * ChangeParameters - Изменение параметров
 * CloseFn - Закрытие ФН
 */
public enum FiscalizationOperationType {

    /** Регистрация. */
    Registration(1),

    /** Изменение параметров. */
    ChangeParameters(2),

    /** Закрытие ФН. */
    CloseFn(3);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    FiscalizationOperationType(int value) {
        this.value = value;
    }
}
