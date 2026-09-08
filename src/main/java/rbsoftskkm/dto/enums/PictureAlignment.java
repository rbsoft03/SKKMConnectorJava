package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Выравнивание изображения при печати или загрузке в ККТ:
 * Left - По левому краю
 * Center - По центру
 * Right - По правому краю
 */
public enum PictureAlignment {

    /** По левому краю. */
    Left(1),

    /** По центру. */
    Center(2),

    /** По правому краю. */
    Right(3);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    PictureAlignment(int value) {
        this.value = value;
    }
}
