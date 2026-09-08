package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.enums.*;
import rbsoftskkm.dto.positions.*;

/** Строка печатной формы. */
public class PrintFormLine {
    /** Тип строки. Если не указано — Text. */
    @JsonProperty("Type")
    public PrintLineType Type;

    /** Текст строки (левая часть). */
    @JsonProperty("Line")
    public String Line;

    /** Текст строки (правая часть). */
    @JsonProperty("LineRight")
    public String LineRight;

    /** Выравнивание. Если не указано — Left. */
    @JsonProperty("Alignment")
    public PrintAlignment Alignment;

    /** Шрифт. Если не указано — Normal. */
    @JsonProperty("Font")
    public PrintFont Font;

    /** Признак, что шрифт задан явно во входящих данных или при создании строки. */
    @JsonProperty("IsFontSpecified")
    public boolean IsFontSpecified;

    /** Ширина. Если не указано — 0 (по содержимому). */
    @JsonProperty("Width")
    public int Width;

    /** Масштаб. Если не указано — 100%. */
    @JsonProperty("Scale")
    public int Scale;

    /** Признак переноса строк: false — строка обрезается; true — переносится. Если не указано — true. */
    @JsonProperty("Wrap")
    public boolean Wrap;

    /** Разделительная линия. */
    @JsonProperty("SeparatorLine")
    public SeparatorLine SeparatorLine;

    /** Изображение. */
    @JsonProperty("Picture")
    public Picture Picture;

    /** Штрихкод. */
    @JsonProperty("Barcode")
    public PrintFormBarcode Barcode;

    /** Строки, выводимые справа или слева от штрихкода. */
    @JsonProperty("BarcodeLines")
    public String[] BarcodeLines;

    /** Признак создания строки из печатного шаблона. */
    @JsonProperty("IsCreateFromTemplate")
    public boolean IsCreateFromTemplate;
}
