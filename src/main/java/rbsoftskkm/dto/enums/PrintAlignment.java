package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Выравнивание строки или штрихкода при печати:
 * Left - По левому краю
 * Center - По центру
 * Right - По правому краю
 * Width - На всю ширину
 */
public enum PrintAlignment {

    /** По левому краю. */
    Left(0),

    /** По центру. */
    Center(1),

    /** По правому краю. */
    Right(2),

    /** На всю ширину. */
    Width(3);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    PrintAlignment(int value) {
        this.value = value;
    }
}
