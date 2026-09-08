package rbsoftskkm.dto.positions;

/**
 * Дробное количество предмета расчёта:
 * Numerator - Числитель
 * Denominator - Знаменатель
 * Используется вместе с мерой количества при частичной реализации маркированного товара.
 */
public class FractionalQuantity {
    /** Числитель. */
    public int Numerator;

    /** Знаменатель. */
    public int Denominator;
}
