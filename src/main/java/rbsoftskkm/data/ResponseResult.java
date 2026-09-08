package rbsoftskkm.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Ответ Сервера ККМ.
 */
public class ResponseResult<T> extends ResponseResultBase {

    /** Полезная нагрузка ответа. */
    @JsonProperty("Result")
    public T Result;
}
