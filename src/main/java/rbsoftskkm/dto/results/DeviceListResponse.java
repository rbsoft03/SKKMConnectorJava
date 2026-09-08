package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.enums.*;

/** Элемент списка ККТ */
public class DeviceListResponse {
    /** Имя устройства. */
    @JsonProperty("DeviceName")
    public String DeviceName;

    /** Тип драйвера */
    @JsonProperty("Driver")
    public DeviceType Driver;

    /** Имя пула, в который входит устройство. */
    @JsonProperty("Pool")
    public String Pool;

    /** Описание статуса устройства. */
    @JsonProperty("DeviceStatusDescription")
    public String DeviceStatusDescription;
}
