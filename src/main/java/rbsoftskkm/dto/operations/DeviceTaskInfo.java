package rbsoftskkm.dto.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.results.*;
import java.time.LocalDateTime;

/** Информация о задаче устройства. */
public class DeviceTaskInfo {
    /** Тип задания. */
    @JsonProperty("TaskType")
    public int TaskType;

    /** Идентификатор документа. */
    @JsonProperty("DocId")
    public String DocId = "";

    /** Дата создания / выполнения операции. */
    @JsonProperty("Date")
    public LocalDateTime Date;

    /** Идентификатор документа-основания. */
    @JsonProperty("BaseDocId")
    public String BaseDocId = "";

    /** Идентификатор запроса. */
    @JsonProperty("RequestId")
    public String RequestId = "";

    /** Идентификатор терминала. */
    @JsonProperty("TerminalId")
    public String TerminalId = "";

    /** Имя устройства. */
    @JsonProperty("DeviceName")
    public String DeviceName = "";

    /** Идентификатор пула. */
    @JsonProperty("PoolId")
    public String PoolId = "";

    /** Код результата (0 — успех). */
    @JsonProperty("ResultCode")
    public int ResultCode;

    /** Описание результата. */
    @JsonProperty("ResultDescription")
    public String ResultDescription = "";

    /** Признак успешного завершения обработки. */
    @JsonProperty("Processed")
    public boolean Processed;

    /** Версия клиента. */
    @JsonProperty("ClientVersion")
    public String ClientVersion = "";

    /** Версия сервера. */
    @JsonProperty("ServerVersion")
    public String ServerVersion = "";

    /** Сведения об устройстве, обработавшем задание. */
    @JsonProperty("DeviceInfo")
    public Device DeviceInfo;

    /** XML-представление документа. */
    @JsonProperty("Xml")
    public String Xml = "";

    /** Сведения о приложении-источнике запроса. */
    @JsonProperty("SenderInfo")
    public SenderInfo SenderInfo;
}
