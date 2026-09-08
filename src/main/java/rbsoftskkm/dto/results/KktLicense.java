package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/** Лицензия ККТ */
public class KktLicense {
    /** Номер лицензии. */
    @JsonProperty("Number")
    public int Number;

    /** Наименование лицензии. */
    @JsonProperty("Name")
    public String Name;

    /** Действует с. */
    @JsonProperty("ValidFrom")
    public LocalDateTime ValidFrom;

    /** Действует до. */
    @JsonProperty("ValidUntil")
    public LocalDateTime ValidUntil;

    /** Версия узла */
    @JsonProperty("UnitVersion")
    public String UnitVersion;

    /** Описание лицензии. */
    @JsonProperty("Description")
    public String Description;

    /** Признак активной лицензии. */
    @JsonProperty("IsActive")
    public boolean IsActive;
}
