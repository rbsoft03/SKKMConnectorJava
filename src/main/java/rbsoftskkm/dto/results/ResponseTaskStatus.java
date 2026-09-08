package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/** Статус задания */
public class ResponseTaskStatus {
    /** Имя устройства. */
    @JsonProperty("DeviceName")
    public String DeviceName;

    /** Идентификатор документа. */
    @JsonProperty("DocId")
    public String DocId;

    /** Дата и время постановки задания в обработку. */
    @JsonProperty("Date")
    public LocalDateTime Date;

    /** Статус отправки: 0 — задача новая, в очереди; 1 — отправлена на выполнение; 2 — удачно обработана; −1 — вернулась с ошибкой. */
    @JsonProperty("SentToPrint")
    public int SentToPrint;

    /** Позиция задания в очереди на момент запроса. −1 — задание уже покинуло очередь. */
    @JsonProperty("NumberInQueue")
    public int NumberInQueue;

    /** Размер очереди. */
    @JsonProperty("QueueSize")
    public int QueueSize;

    /** Идентификатор пула. Если устройство не входит в пул — не заполняется. */
    @JsonProperty("PoolId")
    public String PoolId;

    /** Номер смены. */
    @JsonProperty("ShiftNumber")
    public int ShiftNumber;

    /** Номер чека. */
    @JsonProperty("DocNumber")
    public int DocNumber;

    /** Тип чека */
    @JsonProperty("TaskType")
    public int TaskType;

    /** Фискальный признак документа. Заполняется только для фискальных документов. */
    @JsonProperty("FiscalSign")
    public String FiscalSign;

    /** Заголовок документа. */
    @JsonProperty("DocumentHeader")
    public DocumentHeader DocumentHeader;

    /** Код результата обработки задания. */
    @JsonProperty("ResultCode")
    public int ResultCode;

    /** Описание результата обработки задания. */
    @JsonProperty("ResultDescription")
    public String ResultDescription;
}
