package rbsoftskkm.dto.templates;

/**
 * Параметры создания или изменения шаблона чека:
 * Name - Уникальное имя шаблона на сервере
 * Document - Документ шаблона ({@link CheckTemplateDocument})
 */
public class CheckTemplateParameters {
    /**
     * Имя шаблона чека. Уникальный идентификатор на сервере.
     * Разрешены символы a-z, A-Z, 0-9, _, -, (, ). Пробелы запрещены.
     */
    public String Name = "";

    /** Документ шаблона */
    public CheckTemplateDocument Document;
}
