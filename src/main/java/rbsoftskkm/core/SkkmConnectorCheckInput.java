package rbsoftskkm.core;

import rbsoftskkm.dto.*;
import rbsoftskkm.dto.enums.*;
import rbsoftskkm.dto.positions.*;
import rbsoftskkm.dto.results.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Входные свойства запроса: чек, коррекция, наличные, слип, картинки, маркировка.
 * Порт SkkmConnector.CheckInput.cs.
 */
public abstract class SkkmConnectorCheckInput extends SkkmConnectorConnection {

    /** Идентификатор документа (docId) */
    public String DocumentId = "";

    /** Фискальный признак документа */
    public String FiscalSign = "";

    /** Номер смены */
    public int ShiftNumber;

    /** Номер фискального документа (ФД) */
    public int CheckNumber;

    /** Номер чека за смену */
    public int CheckNumberInShift;

    /** Регистрационный номер ККТ (РНМ) */
    public String RnNumber = "";

    /** Адрес сайта ФНС */
    public String FnsUrl = "";

    /** Время на сервере ККМ */
    public String ServerDateTime = "";

    /** Дата и время документа по часам ФН */
    public String FiscalDateTime = "";

    /** Время ККТ */
    public String DeviceDateTime = "";

    /** Состояние смены. Используйте enum {@link ShiftState}. */
    public ShiftState CurrentShiftState;

    /** Количество непереданных в ОФД документов. */
    public long BacklogDocumentsCount;

    /** Номер первого непереданного документа. */
    public long BacklogFirstDocumentNumber;

    /** Дата и время первого непереданного документа. */
    public LocalDateTime BacklogFirstDocumentDateTime;

    /** Срок действия ФН. */
    public String FnValidityDate = "";

    /** Остаток ресурса ФН в днях. */
    public int FnDaysResources;

    /** ФН присутствует */
    public boolean IsFnPresent;

    /** Фискальный режим */
    public boolean IsFiscal;

    /** Предупреждения ФН из ответа. */
    public Warnings FnWarnings;

    /** Начало периода отбора отчётов, чеков и операций */
    public LocalDate ShiftsFrom;

    /** Конец периода отбора отчётов, чеков и операций */
    public LocalDate ShiftsTo = LocalDate.now();

    /** Сумма внесения или выемки */
    public BigDecimal CashAmount = BigDecimal.ZERO;

    /** Название изображения */
    public String PictureName = "";

    /** Изображение, закодированное в Base64. */
    public String PictureBase64 = "";

    /** Выравнивание изображения при печати. Используйте enum {@link PictureAlignment}. */
    public PictureAlignment PictureAlignment = rbsoftskkm.dto.enums.PictureAlignment.Center;

    /** Текст нефискального документа. */
    public String TextForPrint = "";

    /** Тип чека / задания. Используйте enum {@link CheckType}. */
    public CheckType PaymentType = CheckType.Sale;

    /** Только обработанные операции. Параметр {@code isProcessed} в {@link GetOperationLast}. */
    public boolean IsProcessed;

    /** Система налогообложения. Используйте enum {@link TaxSystem}. */
    public TaxSystem TaxVariant = TaxSystem.ОСН;

    /** Часовая зона. Используйте enum {@link CheckTimeZone}. */
    public CheckTimeZone TimeZone;

    /**
     * Чек только в электронном виде (без печати на бумаге).
     * true — не печатать; для обычной печати оставляйте false.
     */
    public boolean Electronically;

    /** Текст для печати перед товарной частью */
    public String TextBefore = "";

    /** Текст для печати после товарной части чека */
    public String TextAfter = "";

    /** Место проведения расчётов */
    public String SaleLocation = "";

    /** Адрес проведения расчётов */
    public String SaleAddress = "";

    /** Адрес электронной почты отправителя чека */
    public String SenderEmail = "";

    /** Признак применения ККТ при осуществлении расчета в безналичном порядке в сети «Интернет» */
    public boolean OperationOnline;

    /** Дополнительный реквизит чека (БСО), тег 1192 */
    public String AdditionalAttribute = "";

    /**
     * Отраслевой реквизит чека. Создайте объект {@link Industry}
     * (IdentifierFoiv, DocumentDate, DocumentNumber, AttributeValue).
     */
    public Industry IndustryAttribute;

    /**
     * Дополнительный реквизит пользователя. Создайте объект {@link UserAttribute}
     * (Name, Value).
     */
    public UserAttribute UserAttribute;

    /**
     * Операционный реквизит чека. Создайте объект {@link OperationalAttribute}
     * (DateTime, OperationId, OperationData).
     */
    public OperationalAttribute OperationalAttribute;

    /**
     * Детализация безналичных оплат. Добавляйте объекты {@link ElectronicPayment}
     * (Amount, PaymentMethod, Identifiers, AdditionalInformation).
     */
    public List<ElectronicPayment> ElectronicPayments = new ArrayList<>();

    /** Признак агента. Используйте enum {@link AgentType}. */
    public AgentType AgentSign;

    /** Данные агента. Создайте объект {@link Agent} и заполните нужные поля. */
    public Agent Agent;

    /**
     * Данные поставщика. Создайте объект {@link Vendor}
     * (Name, Phones, Vatin).
     */
    public Vendor Vendor;

    /** Сведения о покупателе. Создайте объект {@link Customer} и заполните нужные поля. */
    public Customer Customer;

    /**
     * Суммы оплаты. Создайте объект {@link Payments}
     * (Cash, ElectronicPayment, AdvancePayment, Credit, CashProvision).
     */
    public Payments Payments = new Payments();

    /**
     * Позиции чека. Добавляйте наследники {@link Position}:
     * {@link FiscalLine}, {@link TextLine}, {@link BarcodeLine},
     * {@link PictureLine}, {@link SeparatorLine}.
     */
    public List<Position> Positions = new ArrayList<>();

    /**
     * Данные коррекции. Создайте объект {@link CorrectionData}
     * (Type, Description, Date, Number).
     */
    public CorrectionData CorrectionData;

    /**
     * Суммы НДС по ставкам для чека коррекции ФФД 1.05.
     * Создайте объект {@link Correction105Taxes} и заполните нужные ставки.
     */
    public Correction105Taxes Correction105Taxes;

    /** Код маркировки в кодировке Base64 */
    public String MarkingCode = "";

    /** Планируемый статус товара. Используйте enum {@link MarkingPlannedStatus}. */
    public MarkingPlannedStatus PlannedStatus = MarkingPlannedStatus.Sold;

    /** Количество товара */
    public BigDecimal MarkingQuantity = BigDecimal.ONE;

    /** Мера количества предмета расчёта. Используйте enum {@link MeasureOfQuantity}. */
    public MeasureOfQuantity MeasureOfQuantity;

    /** Числитель дробного количества товара */
    public int FractionalQuantityNumerator;

    /** Знаменатель дробного количества товара. */
    public int FractionalQuantityDenominator;

    /** Не отправлять результат проверки на сервер ОИСМ */
    public boolean NotSendToServer;

    /** Признак ожидания ответа ОИСМ */
    public boolean WaitForResult;

    /** Уникальный код запроса КМ */
    public String RequestKmGuid = "";

    /** Признак подтверждения кода маркировки. Используйте enum {@link KmConfirmationType}. */
    public KmConfirmationType ConfirmationType;
}
