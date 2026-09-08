package rbsoftskkm.dto.positions;

/**
 * Текстовая строка чека:
 * Text - Текст
 * Font - Шрифт
 * Alignment - Выравнивание
 */
public class TextLine extends Position {
    /** Текст строки */
    public String Text = "";

    /** Шрифт */
    public String Font;

    /** Выравнивание */
    public String Alignment;
}
