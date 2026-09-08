package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Тип строки печатного шаблона / печатной формы:
 * Fiscal - Фискальная
 * Text - Текстовая
 * Barcode - Штрихкод
 * Picture - Изображение
 * Separator - Разделительная линия
 */
public enum PrintLineType {

    /** Фискальная строка. */
    Fiscal(0),

    /** Текстовая строка. */
    Text(1),

    /** Штрихкод. */
    Barcode(2),

    /** Изображение. */
    Picture(3),

    /** Разделительная линия. */
    Separator(4);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    PrintLineType(int value) {
        this.value = value;
    }
}
