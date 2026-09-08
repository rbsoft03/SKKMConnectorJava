package rbsoftskkm.dto.positions;

/**
 * Данные поставщика:
 * Name - Наименование поставщика
 * Phones - Телефон(ы) поставщика
 * Vatin - ИНН поставщика
 */
public class Vendor {
    /** Наименование поставщика. */
    public String Name;

    /** Телефоны поставщика. */
    public String[] Phones;

    /** ИНН поставщика. */
    public String Vatin;
}
