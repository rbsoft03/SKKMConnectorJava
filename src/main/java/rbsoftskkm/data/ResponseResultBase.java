package rbsoftskkm.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Базовый результат операции: код, описание, успех.
 */
public class ResponseResultBase {

    /** Код результата (0 - успех). */
    @JsonProperty("Code")
    public int Code;

    /** Описание результата или ошибки. */
    @JsonProperty("Description")
    public String Description;

    /** Признак успешного выполнения. */
    @JsonProperty("Success")
    public boolean Success;
}
