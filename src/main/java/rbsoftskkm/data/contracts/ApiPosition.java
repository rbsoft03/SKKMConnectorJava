package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.positions.FiscalLine;

/** Позиция чека: фискальная строка, либо текст/штрихкод. */
public class ApiPosition extends DocPosition {
    /** Фискальная строка. */
    @JsonProperty("FiscalString")
    public FiscalLine FiscalString;
}
