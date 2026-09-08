package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Результат проверки кода маркировки в ОИСМ */
public class ProcessingKmResult {
    /** Идентификатор запроса КМ */
    @JsonProperty("Guid")
    public String Guid;

    /** Итог проверки кода маркировки. */
    @JsonProperty("Result")
    public boolean Result;

    /** Код результата проверки (тег 2106 ФФД). */
    @JsonProperty("ResultCode")
    public int ResultCode;

    /** Статус информации о коде маркировки (тег 2109 ФФД). */
    @JsonProperty("StatusInfo")
    public Integer StatusInfo;

    /** Код обработки запроса (тег 2105 ФФД). */
    @JsonProperty("HandleCode")
    public int HandleCode;

    /** Статус получения результата от ОИСМ: 0 — получен; 1 — ещё не получен; 2 — не может быть получен. */
    @JsonProperty("RequestStatus")
    public int RequestStatus;
}
