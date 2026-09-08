package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Сведения о покупателе из ответа */
public class CheckCustomer {
    /** Наименование организации или фамилия, имя, отчество (при наличии). */
    @JsonProperty("Info")
    public String Info;

    /** ИНН организации или покупателя (клиента). */
    @JsonProperty("Inn")
    public String Inn;

    /** Электронная почта. */
    @JsonProperty("Email")
    public String Email;

    /** Номер телефона. */
    @JsonProperty("Phone")
    public String Phone;

    /** Дата рождения покупателя */
    @JsonProperty("DateOfBirth")
    public String DateOfBirth;

    /** Код страны (ОКСМ). */
    @JsonProperty("Citizenship")
    public String Citizenship;

    /** Числовой код вида документа, удостоверяющего личность. */
    @JsonProperty("DocumentTypeCode")
    public Integer DocumentTypeCode;

    /** Данные документа, удостоверяющего личность. */
    @JsonProperty("DocumentData")
    public String DocumentData;

    /** Адрес покупателя. */
    @JsonProperty("Address")
    public String Address;
}
