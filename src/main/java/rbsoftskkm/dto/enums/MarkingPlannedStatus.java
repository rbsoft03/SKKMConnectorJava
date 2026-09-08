package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Планируемый статус товара при проверке кода маркировки (таблица 105 ФФД):
 * NotSpecified - Не задан (значение по умолчанию в запросе)
 * Sold - Реализован
 * InSale - Мерный товар в стадии реализации
 * Returned - Возвращён
 * PartiallyReturned - Часть товара возвращена
 * Unchanged - Статус не изменился
 */
public enum MarkingPlannedStatus {

    /** Не задан (значение по умолчанию в запросе). */
    NotSpecified(0),

    /** Реализован. */
    Sold(1),

    /** Мерный товар в стадии реализации. */
    InSale(2),

    /** Возвращён. */
    Returned(3),

    /** Часть товара возвращена. */
    PartiallyReturned(4),

    /** Статус не изменился. */
    Unchanged(255);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    MarkingPlannedStatus(int value) {
        this.value = value;
    }
}
