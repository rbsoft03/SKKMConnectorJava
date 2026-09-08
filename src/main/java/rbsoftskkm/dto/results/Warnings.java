package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Предупреждения ФН */
public class Warnings {
    /** Критическая ошибка ФН. */
    @JsonProperty("CriticalError")
    public boolean CriticalError;

    /** Память ФН переполнена. */
    @JsonProperty("MemoryOverflow")
    public boolean MemoryOverflow;

    /** Требуется срочная замена ФН. */
    @JsonProperty("NeedReplacement")
    public boolean NeedReplacement;

    /** Превышено время ожидания ответа от ОФД. */
    @JsonProperty("OfdTimeout")
    public boolean OfdTimeout;

    /** Исчерпан ресурс ФН. */
    @JsonProperty("ResourceExhausted")
    public boolean ResourceExhausted;
}
