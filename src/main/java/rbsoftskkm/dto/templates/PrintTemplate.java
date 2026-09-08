package rbsoftskkm.dto.templates;

import rbsoftskkm.dto.enums.PrintTemplateType;
import java.util.ArrayList;
import java.util.List;

/** Шаблон печати, полученный с сервера. */
public class PrintTemplate {
    /** Имя шаблона. Уникальный идентификатор на сервере. */
    public String Name = "";

    /** Тип шаблона. */
    public PrintTemplateType Type;

    /** Строки шаблона (текст, штрихкод, картинка, разделитель). */
    public List<TemplateItem> TemplateItems = new ArrayList<>();
}
