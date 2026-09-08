package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/** Данные о непереданных документах */
public class Backlog {
    /** Количество непереданных документов. */
    @JsonProperty("DocumentsCounter")
    public long DocumentsCounter;

    /** Номер первого непереданного документа. */
    @JsonProperty("DocumentFirstNumber")
    public long DocumentFirstNumber;

    /** Дата и время первого из непереданных документов. */
    @JsonProperty("DocumentFirstDateTime")
    public LocalDateTime DocumentFirstDateTime;
}
