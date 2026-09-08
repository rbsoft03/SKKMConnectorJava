package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Тело запроса проверки кода маркировки. */
public class RequestKmParameters {
    /** Имя кассы. */
    @JsonProperty("DeviceName")
    public String DeviceName;

    /** Параметры проверяемого кода маркировки. */
    @JsonProperty("RequestKM")
    public RequestKm RequestKM = new RequestKm();
}
