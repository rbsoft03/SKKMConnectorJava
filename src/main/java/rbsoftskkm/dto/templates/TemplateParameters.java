package rbsoftskkm.dto.templates;

import rbsoftskkm.dto.enums.PrintTemplateType;
import java.util.ArrayList;
import java.util.List;

/**
 * Параметры создания или изменения шаблона печати:
 * Name - Уникальное имя шаблона на сервере
 * Type - Тип шаблона. Используйте enum {@link PrintTemplateType}
 * TemplateItems - Строки шаблона ({@link TemplateItem} / {@link PrintLine})
 */
public class TemplateParameters {
    /**
     * Имя шаблона. Уникальный идентификатор на сервере.
     * Разрешены символы a-z, A-Z, 0-9, _, -, (, ). Пробелы запрещены.
     */
    public String Name = "";

    /** Тип шаблона. Используйте enum {@link PrintTemplateType}. */
    public PrintTemplateType Type;

    /** Строки шаблона (текст, штрихкод, картинка, разделительная линия). */
    public List<TemplateItem> TemplateItems = new ArrayList<>();
}
