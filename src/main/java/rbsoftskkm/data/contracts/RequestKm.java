package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** Параметры проверяемого кода маркировки. */
public class RequestKm {
    /** Идентификатор запроса проверки. */
    @JsonProperty("Guid")
    public String Guid;

    /** Не отправлять запрос на сервер ОИСМ (только локальная проверка). */
    @JsonProperty("NotSendToServer")
    public boolean NotSendToServer;

    /** Ждать ответ ОИСМ. */
    @JsonProperty("WaitForResult")
    public boolean WaitForResult;

    /** Код маркировки в Base64. */
    @JsonProperty("MarkingCode")
    public String MarkingCode;

    /** Планируемый статус товара (тег 2003). */
    @JsonProperty("PlannedStatus")
    public int PlannedStatus;

    /** Количество предмета расчёта. */
    @JsonProperty("Quantity")
    public BigDecimal Quantity;

    /** Мера количества предмета расчёта (таблица 114 ФФД). */
    @JsonProperty("MeasureOfQuantity")
    public int MeasureOfQuantity;

    /** Числитель дробного количества маркированного товара. */
    @JsonProperty("FractionalQuantityNumerator")
    public Integer FractionalQuantityNumerator;

    /** Знаменатель дробного количества маркированного товара. */
    @JsonProperty("FractionalQuantityDenominator")
    public Integer FractionalQuantityDenominator;
}
