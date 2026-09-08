package rbsoftskkm.dto.positions;

/**
 * Отраслевой реквизит предмета расчёта:
 * IdentifierFoiv - Идентификатор ФОИВ
 * DocumentDate - Дата документа-основания
 * DocumentNumber - Номер документа-основания
 * AttributeValue - Значение отраслевого реквизита
 */
public class Industry {
    /** Идентификатор ФОИВ. */
    public String IdentifierFoiv;

    /** Дата документа основания. */
    public String DocumentDate;

    /** Номер документа основания. */
    public String DocumentNumber;

    /** Значение отраслевого реквизита. */
    public String AttributeValue;
}
