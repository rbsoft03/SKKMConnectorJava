package rbsoftskkm.core;

import rbsoftskkm.dto.admin.*;
import rbsoftskkm.dto.fiscalization.*;
import rbsoftskkm.dto.marking.*;
import rbsoftskkm.dto.operations.*;
import rbsoftskkm.dto.queue.*;
import rbsoftskkm.dto.results.*;
import rbsoftskkm.dto.templates.*;

import com.fasterxml.jackson.databind.JsonNode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Порт SkkmConnector.State.cs.
 */
public abstract class SkkmConnectorState extends SkkmConnectorBase {

    /** Успех последнего вызова. */
    public boolean Ok;

    /** Код ошибки сервера. 0 — нет ошибки. */
    public int ErrorCode;

    /** Текст ошибки сервера. */
    public String ErrorDescription = "";

    /** Поле Result последнего ответа сервера. */
    public JsonNode LastResult;

    /** Фискальный блок ответа. */
    public FiscalResult FiscalResult;

    /** Список устройств. */
    public DeviceListResponse[] Devices = new DeviceListResponse[0];

    /** Данные кассы. */
    public DataKkt Kkt;

    /** Состояние ККМ. */
    public KktStatus Status;

    /** Статус смены. */
    public ResponseCurrentStatus ShiftStatus;

    /** Итоги смены. */
    public ResShiftTotal ShiftTotals;

    /** Остаток наличных. */
    public BigDecimal CashBalance = BigDecimal.ZERO;

    /** Список картинок. */
    public List<Picture> Pictures = new ArrayList<>();

    /** Ширина строки чека в символах. */
    public int LineLength;

    /** Ширина печатной области в пикселях. */
    public int LineLengthPixels;

    /** Необнуляемая сумма продаж. */
    public BigDecimal NonZeroSum = BigDecimal.ZERO;

    /** Результат локальной проверки КМ. */
    public RequestKmResult MarkingCheck;

    /** Результат проверки КМ в ОИСМ. */
    public ProcessingKmResult MarkingProcessing;

    /** Документ. */
    public CheckDocument Check;

    /** Список документов. */
    public CheckDocument[] Checks = new CheckDocument[0];

    /** Статус задания. */
    public ResponseTaskStatus TaskStatus;

    /** Печатная форма. */
    public PrintFormLine[] PrintForm = new PrintFormLine[0];

    /** Список отчётов. */
    public ShiftListItem[] Shifts = new ShiftListItem[0];

    /** Версия сервера. */
    public String ServerVersion = "";

    /** Токен пользователя. */
    public UserToken UserToken;

    /** Список пользователей. */
    public ServiceUser[] Users = new ServiceUser[0];

    /** Настройки службы. */
    public ServiceSettings ServiceSettingsResult;

    /** Список пулов. */
    public String[] Pools = new String[0];

    /** Очередь печати. */
    public QueueItem[] Queue = new QueueItem[0];

    /** Состояние задания очереди. */
    public QueueTaskState QueueTask;

    /** Операция. */
    public DeviceTaskInfo Operation;

    /** История операции. */
    public OperationHistoryItem[] OperationHistory = new OperationHistoryItem[0];

    /** TLV операции. */
    public String OperationTlv = "";

    /** Коды маркировки операции. */
    public OperationKmRow[] OperationKm = new OperationKmRow[0];

    /** Связанные операции. */
    public DeviceTaskInfo[] RelatedOperations = new DeviceTaskInfo[0];

    /** Список операций. */
    public OperationListItem[] Operations = new OperationListItem[0];

    /** Шаблон печати. */
    public PrintTemplate PrintTemplate;

    /** Список шаблонов печати. */
    public PrintTemplate[] Templates = new PrintTemplate[0];

    /** Шаблон чека. */
    public CheckTemplate CheckTemplate;

    /** Список шаблонов чека. */
    public CheckTemplateListItem[] CheckTemplates = new CheckTemplateListItem[0];

    /** Документ фискализации. */
    public FiscalizationDocument FiscalizationDocument;

    /** Список фискализаций. */
    public FiscalizationDocument[] Fiscalizations = new FiscalizationDocument[0];

    /** Результат проверки маркировки. */
    public MarkingVerifyResult MarkingVerify;

    /** Картинка в Base64. */
    public String PictureBase64Result = "";
}
