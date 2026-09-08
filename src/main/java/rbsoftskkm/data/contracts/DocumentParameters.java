package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Тело запроса печати нефискального документа. */
public class DocumentParameters extends CheckbaseParameters {
    /** Строки документа */
    @JsonProperty("Positions")
    public DocPosition[] Positions;
}
