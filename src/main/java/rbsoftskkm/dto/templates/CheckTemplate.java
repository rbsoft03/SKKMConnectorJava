package rbsoftskkm.dto.templates;

/** Шаблон чека, полученный с сервера. */
public class CheckTemplate {
    /** Идентификатор шаблона на сервере. */
    public String Id = "";

    /** Имя шаблона чека. */
    public String Name = "";

    /** Документ шаблона. */
    public CheckTemplateDocument Document;
}
