package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Тип печатного шаблона:
 * Advertisement - Реклама
 * CheckLines - Строки чека
 * HeaderOrFooter - Шапка или подвал чека
 */
public enum PrintTemplateType {

    /** Реклама. */
    Advertisement(0),

    /** Строки чека. */
    CheckLines(1),

    /** Шапка или подвал чека. */
    HeaderOrFooter(2);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    PrintTemplateType(int value) {
        this.value = value;
    }
}
