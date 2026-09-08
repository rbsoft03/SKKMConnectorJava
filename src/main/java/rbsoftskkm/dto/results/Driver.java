package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Описание драйвера ККМ */
public class Driver {
    /** Тип драйвера. */
    @JsonProperty("Type")
    public String Type;

    /** Версия драйвера */
    @JsonProperty("Version")
    public String Version;

    /** Данные поставщика. */
    @JsonProperty("Vendor")
    public String Vendor;
}
