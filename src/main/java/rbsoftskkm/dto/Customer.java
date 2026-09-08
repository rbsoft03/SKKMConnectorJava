package rbsoftskkm.dto;

/**
 * Сведения о покупателе (клиенте):
 * Info - Наименование организации или ФИО
 * Vatin - ИНН покупателя
 * Email - Электронная почта
 * Phone - Телефон
 * DateOfBirth - Дата рождения
 * Citizenship - Код страны гражданства
 * DocumentTypeCode - Код вида документа (таблица 116 ФФД)
 * DocumentData - Данные документа, удостоверяющего личность
 * Address - Адрес покупателя
 * Заполните только нужные поля.
 */
public class Customer {
    /** Наименование организации или фамилия, имя, отчество (при наличии). */
    public String Info;

    /** ИНН покупателя. */
    public String Vatin;

    /** Электронная почта покупателя. */
    public String Email;

    /** Номер телефона. */
    public String Phone;

    /** Дата рождения покупателя (клиента). */
    public String DateOfBirth;

    /** Числовой код страны. */
    public String Citizenship;

    /** Числовой код вида документа, удостоверяющего личность (таблица 116). */
    public String DocumentTypeCode;

    /** Данные документа, удостоверяющего личность. */
    public String DocumentData;

    /** Адрес покупателя. */
    public String Address;
}
