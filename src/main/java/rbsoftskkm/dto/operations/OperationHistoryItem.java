package rbsoftskkm.dto.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.results.*;
import java.time.LocalDateTime;

/** Элемент истории обработки операции. */
public class OperationHistoryItem {
    /** Время события. */
    @JsonProperty("Time")
    public LocalDateTime Time;

    /** Код состояния. */
    @JsonProperty("State")
    public int State;

    /** Описание события. */
    @JsonProperty("Description")
    public String Description = "";

    /** Состояние документа на этом шаге. */
    @JsonProperty("Document")
    public CheckDocument Document;
}
