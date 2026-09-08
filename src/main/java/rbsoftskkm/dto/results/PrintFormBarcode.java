package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.enums.*;

/** Штрихкод в печатной форме. */
public class PrintFormBarcode {
    /** Тип штрихкода. */
    @JsonProperty("Type")
    public BarcodeType Type;

    /** Значение штрихкода. */
    @JsonProperty("Value")
    public String Value;

    /** Изображение штрихкода, закодированное в Base64. */
    @JsonProperty("PictureBase64")
    public String PictureBase64;

    /** Способ печати текста штрихкода (только для одномерных). */
    @JsonProperty("PrintText")
    public BarcodePrintText PrintText;

    /** Высота штрихкода в точках. Допустимые значения: 0..1199. */
    @JsonProperty("Height")
    public int Height;

    /** Ширина штриха в точках. Допустимые значения: 0..1199. Рекомендуемое значение — 2. */
    @JsonProperty("BarWidth")
    public int BarWidth;
}
