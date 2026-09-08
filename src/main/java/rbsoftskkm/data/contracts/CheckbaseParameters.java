package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import rbsoftskkm.dto.Cashier;

/** Базовые параметры кассового документа */
@JsonPropertyOrder({"DeviceName", "DocId", "Cashier"})
public class CheckbaseParameters {
    /** Имя кассы */
    @JsonProperty("DeviceName")
    public String DeviceName;

    /** Идентификатор документа */
    @JsonProperty("DocId")
    public String DocId;

    /** Кассир */
    @JsonProperty("Cashier")
    public Cashier Cashier;
}
