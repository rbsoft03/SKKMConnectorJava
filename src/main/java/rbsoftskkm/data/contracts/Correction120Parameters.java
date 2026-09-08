package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.CorrectionData;

/** Тело запроса печати чека коррекции ФФД 1.2. */
public class Correction120Parameters extends CheckParameters {
    /** Данные корректировки */
    @JsonProperty("CorrectionData")
    public CorrectionData CorrectionData;
}
