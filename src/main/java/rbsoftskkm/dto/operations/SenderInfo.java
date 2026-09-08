package rbsoftskkm.dto.operations;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Сведения о приложении-источнике запроса. */
public class SenderInfo {
    /** Название приложения. */
    @JsonProperty("AppName")
    public String AppName = "";

    /** Версия приложения. */
    @JsonProperty("AppVersion")
    public String AppVersion = "";
}
