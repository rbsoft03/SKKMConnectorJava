package rbsoftskkm.dto.queue;

import java.time.LocalDateTime;

/** Состояние задания в очереди. */
public class QueueTaskState {
    /** Название устройства. */
    public String DeviceName = "";

    /** Идентификатор документа. */
    public String DocId = "";

    /** Код состояния документа. */
    public int DocState;

    /** Код состояния очереди. */
    public int QueueState;

    /** Код результата. */
    public int ResultCode;

    /** Описание результата. */
    public String ResultDescription = "";

    /** Позиция задания в очереди на момент запроса. */
    public int NumberInQueue;

    /** Дата и время последнего изменения статуса. */
    public LocalDateTime Date;

    /** Фискальный признак документа (для успешно обработанных фискальных заданий). */
    public String FiscalSign = "";

    /** Описание текущего этапа обработки задания. */
    public String PrintStatusDescription = "";

    /** История обработки задания. */
    public DocumentHistoryItem[] History = new DocumentHistoryItem[0];
}
