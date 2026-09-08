package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Роль пользователя сервера ККМ:
 * Administrator - Администратор
 * Employee - Сотрудник
 */
public enum ServiceUserRole {

    /** Администратор. */
    Administrator(0),

    /** Сотрудник. */
    Employee(1);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    ServiceUserRole(int value) {
        this.value = value;
    }
}
