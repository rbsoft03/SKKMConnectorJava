package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/** Элемент списка отчётов */
public class ShiftListItem {
    /** Результат обработки. */
    @JsonProperty("ResultCode")
    public int ResultCode;

    /** Описание результата. */
    @JsonProperty("ResultDescription")
    public String ResultDescription;

    /** Дата создания документа. */
    @JsonProperty("Date")
    public LocalDateTime Date;

    /** Идентификатор документа. */
    @JsonProperty("DocId")
    public String DocId;

    /** Номер сессии (смены). */
    @JsonProperty("ShiftNumber")
    public int ShiftNumber;

    /** Имя устройства. */
    @JsonProperty("DeviceName")
    public String DeviceName;

    /** Идентификатор терминала, с которого пришёл документ. */
    @JsonProperty("TerminalId")
    public String TerminalId;
}
