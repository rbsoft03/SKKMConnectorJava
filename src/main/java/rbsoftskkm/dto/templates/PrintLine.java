package rbsoftskkm.dto.templates;

import rbsoftskkm.dto.enums.PrintAlignment;
import rbsoftskkm.dto.enums.PrintFont;
import rbsoftskkm.dto.enums.PrintLineType;
import rbsoftskkm.dto.positions.SeparatorLine;
import rbsoftskkm.dto.results.Picture;
import rbsoftskkm.dto.results.PrintFormBarcode;

/**
 * Строка печатного шаблона:
 * Type - Тип строки. Используйте enum {@link PrintLineType}
 * Line / LineRight - Текст (левая / правая часть)
 * Alignment - Выравнивание. Используйте enum {@link PrintAlignment}
 * Font - Шрифт. Используйте enum {@link PrintFont}
 * Width / Scale - Ширина и масштаб
 * Barcode / Picture - Штрихкод или картинка (по типу строки)
 */
public class PrintLine {
    /** Тип строки. Используйте enum {@link PrintLineType}. Если не указано — Text. */
    public PrintLineType Type = PrintLineType.Text;

    /** Ширина. Если не указано — 0 (по содержимому). */
    public int Width;

    /** Масштаб. Если не указано — 100%. */
    public int Scale;

    /** Текст строки (левая часть). */
    public String Line;

    /** Текст строки (правая часть). */
    public String LineRight;

    /** Выравнивание. Используйте enum {@link PrintAlignment}. Если не указано — Left. */
    public PrintAlignment Alignment;

    /** Шрифт. Используйте enum {@link PrintFont}. Если не указано — Normal. */
    public PrintFont Font;

    /** Перенос строк: false — строка обрезается; true — переносится. Если не указано — true. */
    public boolean Wrap = true;

    /** Штрихкод. */
    public PrintFormBarcode Barcode;

    /** Разделительная линия. */
    public SeparatorLine SeparatorLine;

    /** Изображение. */
    public Picture Picture;
}
