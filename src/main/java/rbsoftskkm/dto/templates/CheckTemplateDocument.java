package rbsoftskkm.dto.templates;

import rbsoftskkm.dto.*;
import rbsoftskkm.dto.enums.CheckTimeZone;
import rbsoftskkm.dto.enums.CheckType;
import rbsoftskkm.dto.enums.TaxSystem;
import rbsoftskkm.dto.positions.Industry;
import rbsoftskkm.dto.positions.Position;
import rbsoftskkm.dto.results.CheckItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Документ шаблона чека: тип чека, СНО, оплаты ({@link Payments}), позиции,
 * покупатель, агент и прочие реквизиты — по аналогии с обычным чеком.
 */
public class CheckTemplateDocument {
    /** Тип чека */
    public CheckType PaymentType;

    /** Система налогообложения */
    public TaxSystem TaxVariant;

    /** Часовая зона. */
    public CheckTimeZone TimeZone;

    /** Признак расчёта в сети Интернет. */
    public boolean OperationOnline;

    /** Адрес электронной почты отправителя чека. */
    public String SenderEmail = "";

    /** Адрес проведения расчётов. */
    public String SaleAddress = "";

    /** Место проведения расчётов. */
    public String SaleLocation = "";

    /** Формирование чека только в электронном виде. */
    public boolean Electronically;

    /** Покупатель. */
    public Customer Customer;

    /** Позиции чека */
    public List<Position> Positions = new ArrayList<>();

    /** Строки шаблона */
    public List<CheckItem> CheckItems = new ArrayList<>();

    /** Оплаты чека. */
    public Payments Payments;

    /** Электронные платежи. */
    public List<ElectronicPayment> ElectronicPayments = new ArrayList<>();

    /** Данные коррекции */
    public CorrectionData CorrectionData;

    /** Отраслевой реквизит чека */
    public Industry IndustryAttribute;

    /** Дополнительный реквизит пользователя */
    public UserAttribute UserAttribute;

    /** Операционный реквизит чека */
    public OperationalAttribute OperationalAttribute;

    /** Дополнительный реквизит чека (БСО), тег 1192 */
    public String AdditionalAttribute = "";
}
