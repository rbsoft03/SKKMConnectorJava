package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Тип коррекции:
 * Самостоятельно - Самостоятельно
 * ПоПредписанию - По предписанию налогового органа
 */
public enum CorrectionTypes {

    /** Самостоятельно */
    Самостоятельно(0),

    /** По предписанию налогового органа. Рекомендуется заполнять номер предписания. */
    ПоПредписанию(1);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    CorrectionTypes(int value) {
        this.value = value;
    }
}
