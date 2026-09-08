package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.enums.*;

/** Элемент списка изображений. */
public class Picture {
    /** Название изображения. */
    @JsonProperty("PictureName")
    public String PictureName;

    /** Выравнивание изображения. */
    @JsonProperty("Alignment")
    public PictureAlignment Alignment;

    /** Изображение в Base64 (строка шаблона печати / печатной формы). */
    @JsonProperty("PictureBase64")
    public String PictureBase64;

    /** Ширина изображения при печати, в точках. */
    @JsonProperty("Width")
    public Integer Width;

    /** Высота изображения при печати, в точках. */
    @JsonProperty("Height")
    public Integer Height;
}
