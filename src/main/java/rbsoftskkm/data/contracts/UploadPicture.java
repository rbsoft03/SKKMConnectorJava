package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Тело запроса загрузки картинки. */
public class UploadPicture {
    /** Имя кассы */
    @JsonProperty("DeviceName")
    public String DeviceName;

    /** Изображение в формате Base64 (BMP) */
    @JsonProperty("Base64")
    public String Base64;

    /** Имя картинки на сервере */
    @JsonProperty("PictureName")
    public String PictureName;

    /** Выравнивание: 1 - слева, 2 - по центру, 3 - справа */
    @JsonProperty("Alignment")
    public int Alignment = 2;
}
