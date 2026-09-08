package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.*;
import rbsoftskkm.dto.positions.Agent;
import rbsoftskkm.dto.positions.Industry;
import rbsoftskkm.dto.positions.Vendor;
import java.util.List;

/** Параметры для печати чека или чека коррекции 1.2 */
public class CheckParameters extends CheckbaseParameters {
    /** Тип чека */
    @JsonProperty("PaymentType")
    public int PaymentType;

    /** Код системы налогообложения */
    @JsonProperty("TaxVariant")
    public int TaxVariant;

    /** Сведения о покупателе (клиенте) */
    @JsonProperty("Customer")
    public Customer Customer;

    /** Место проведения расчетов */
    @JsonProperty("SaleLocation")
    public String SaleLocation;

    /** Адрес проведения расчетов */
    @JsonProperty("SaleAddress")
    public String SaleAddress;

    /** Адрес электронной почты отправителя чека */
    @JsonProperty("SenderEmail")
    public String SenderEmail;

    /** Признак применения ККТ при осуществлении расчета в безналичном порядке в сети «Интернет» */
    @JsonProperty("OperationOnline")
    public Boolean OperationOnline;

    /** Отраслевой реквизит чека */
    @JsonProperty("IndustryAttribute")
    public Industry IndustryAttribute;

    /** Дополнительный реквизит пользователя */
    @JsonProperty("UserAttribute")
    public UserAttribute UserAttribute;

    /** Операционный реквизит чека */
    @JsonProperty("OperationalAttribute")
    public OperationalAttribute OperationalAttribute;

    /** Сведения об оплате безналичными */
    @JsonProperty("ElectronicPaymentInfo")
    public List<ElectronicPayment> ElectronicPaymentInfo;

    /** Формирование чека только в электронном виде */
    @JsonProperty("Electronically")
    public boolean Electronically;

    /**
     * Номер часовой зоны места расчётов.
     * Если поле не указано, используется значение из поля «Часовая зона» в настройках ККТ.
     */
    @JsonProperty("TimeZone")
    public Integer TimeZone;

    /** Текст для печати перед товарной частью */
    @JsonProperty("TextBefore")
    public String TextBefore;

    /** Текст для печати после товарной части чека */
    @JsonProperty("TextAfter")
    public String TextAfter;

    /** Дополнительный реквизит чека (БСО), тег 1192 */
    @JsonProperty("AdditionalAttribute")
    public String AdditionalAttribute;

    /** Признак агента */
    @JsonProperty("AgentSign")
    public Integer AgentSign;

    /** Данные агента */
    @JsonProperty("AgentData")
    public Agent AgentData;

    /** Данные поставщика */
    @JsonProperty("Vendor")
    public Vendor Vendor;

    /** Оплаты */
    @JsonProperty("Payments")
    public Payments Payments;

    /** Товары */
    @JsonProperty("Positions")
    public ApiPosition[] Positions;
}
