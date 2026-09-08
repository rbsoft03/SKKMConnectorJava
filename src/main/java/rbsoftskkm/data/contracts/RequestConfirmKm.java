package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Тело запроса подтверждения кода маркировки. */
public class RequestConfirmKm {
    /** Имя кассы. */
    @JsonProperty("DeviceName")
    public String DeviceName;

    /** Идентификатор запроса проверки кода маркировки. */
    @JsonProperty("GUID")
    public String GUID;

    /** Тип подтверждения: 0 - включить в документ, 1 - не включать. */
    @JsonProperty("ConfirmationType")
    public int ConfirmationType;
}
