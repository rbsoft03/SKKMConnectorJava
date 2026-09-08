package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Оператор фискальных данных (Ofd). */
public class Ofd {
    /** Имя ОФД. */
    @JsonProperty("Name")
    public String Name;

    /** ИНН ОФД. */
    @JsonProperty("Vatin")
    public String Vatin;
}
