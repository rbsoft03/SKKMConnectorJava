package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Состояние кассовой смены:
 * Closed - Смена закрыта
 * Opened - Смена открыта
 * Expired - Смена истекла (открыта более 24 часов)
 */
public enum ShiftState {

    /** Смена закрыта */
    Closed(1),

    /** Смена открыта */
    Opened(2),

    /** Смена истекла (открыта более 24 часов) */
    Expired(3);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    ShiftState(int value) {
        this.value = value;
    }
}
