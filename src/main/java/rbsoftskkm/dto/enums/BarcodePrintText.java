package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Способ печати текста штрихкода (для одномерных):
 * None - Не печатать
 * Below - Снизу
 * Above - Сверху
 * AboveAndBelow - Сверху и снизу
 */
public enum BarcodePrintText {

    /** Не печатать. */
    None(0),

    /** Печатать снизу. */
    Below(1),

    /** Печатать сверху. */
    Above(2),

    /** Печатать сверху и снизу. */
    AboveAndBelow(3);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    BarcodePrintText(int value) {
        this.value = value;
    }
}
