package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataKkt {
    /** Версия сервера ККМ. */
    @JsonProperty("ServerVersion")
    public String ServerVersion;

    /** Описание фискального накопителя */
    @JsonProperty("Fn")
    public Fn Fn;

    /** Описание ККМ */
    @JsonProperty("Device")
    public Device Device;

    /** Описание драйвера ККМ */
    @JsonProperty("Driver")
    public Driver Driver;

    /** Состояние ККТ */
    @JsonProperty("Status")
    public KktStatus Status;
}
