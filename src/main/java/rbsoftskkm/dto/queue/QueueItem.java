package rbsoftskkm.dto.queue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Элемент очереди печати. */
public class QueueItem {
    /** Идентификатор документа задания. */
    public String DocId = "";

    /** Название устройства, которому адресовано задание. */
    public String DeviceName = "";

    /** Идентификатор пула (если задание адресовано пулу, а не конкретному устройству). */
    public String PoolId = "";

    /** Признак отправки задания на устройство. */
    public boolean SentToPrint;

    /** Время постановки задания в очередь. */
    public LocalDateTime Time;

    /** Время успешной печати. */
    public LocalDateTime PrintedTime;

    /** Признак успешной печати задания. */
    public boolean Printed;

    /** Сумма документа. */
    public BigDecimal Sum;

    /** Описание текущего состояния или ошибки задания. */
    public String ErrorDescription = "";

    /** Номер кассовой смены. */
    public int Session;

    /** Номер документа (заполняется после успешной обработки). */
    public int DocNumber;
}
