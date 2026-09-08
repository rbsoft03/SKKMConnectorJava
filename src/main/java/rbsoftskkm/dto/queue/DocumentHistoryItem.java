package rbsoftskkm.dto.queue;

import java.time.LocalDateTime;

/** Запись истории обработки документа в очереди. */
public class DocumentHistoryItem {
    /** Время события. */
    public LocalDateTime Time;

    /** Код состояния. */
    public int State;

    /** Описание события. */
    public String Description = "";

    /** Дополнительная информация о событии. */
    public String Info = "";
}
