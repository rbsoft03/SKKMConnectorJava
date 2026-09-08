package rbsoftskkm.dto.positions;

import rbsoftskkm.dto.enums.*;

/**
 * Изображение в чеке:
 * Value - Картинка в Base64
 * Alignment - Выравнивание. Используйте enum {@link PictureAlignment}
 * Width / Height - Размер (при необходимости)
 */
public class PictureLine extends Position {
    /** Изображение в Base64. */
    public String Value = "";

    /** Выравнивание изображения. Используйте enum {@link PictureAlignment}. */
    public PictureAlignment Alignment = PictureAlignment.Center;

    /** Ширина изображения. */
    public Integer Width;

    /** Высота изображения. */
    public Integer Height;
}
