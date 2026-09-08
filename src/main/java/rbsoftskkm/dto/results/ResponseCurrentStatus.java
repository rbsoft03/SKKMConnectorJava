package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.enums.*;

/** Краткий статус смены и очереди ОФД */
public class ResponseCurrentStatus {
    /** Номер смены. */
    @JsonProperty("ShiftNumber")
    public int ShiftNumber;

    /** Номер последнего фискального документа. */
    @JsonProperty("CheckNumber")
    public int CheckNumber;

    /** Состояние смены: 1 — закрыта, 2 — открыта, 3 — истекла. */
    @JsonProperty("ShiftState")
    public ShiftState ShiftState;

    /** Статус обмена данными с ОФД. */
    @JsonProperty("Backlog")
    public Backlog Backlog;
}
