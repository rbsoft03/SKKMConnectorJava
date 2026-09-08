package rbsoftskkm.core;

import com.fasterxml.jackson.databind.JsonNode;
import rbsoftskkm.data.contracts.*;
import rbsoftskkm.dto.*;
import rbsoftskkm.dto.admin.*;
import rbsoftskkm.dto.enums.*;
import rbsoftskkm.dto.fiscalization.*;
import rbsoftskkm.dto.marking.*;
import rbsoftskkm.dto.operations.*;
import rbsoftskkm.dto.queue.*;
import rbsoftskkm.dto.results.*;
import rbsoftskkm.dto.templates.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Методы API Сервера ККМ. Порт SkkmConnector.Api.cs.
 */
public abstract class SkkmConnectorApi extends SkkmConnectorRequests {

    /** Очистка входных данных перед новым запросом и результаты прошлого вызова. */
    public void NewRequest() {
        this.PaymentType = CheckType.Sale;
        this.IsProcessed = false;
        this.TaxVariant = TaxSystem.ОСН;
        this.Electronically = false;
        this.OperationOnline = false;
        this.TimeZone = null;
        this.TextBefore = "";
        this.TextAfter = "";
        this.SaleLocation = "";
        this.SaleAddress = "";
        this.SenderEmail = "";
        this.AdditionalAttribute = "";
        this.IndustryAttribute = null;
        this.UserAttribute = null;
        this.OperationalAttribute = null;
        this.ElectronicPayments.clear();
        this.AgentSign = null;
        this.Agent = null;
        this.Vendor = null;
        this.Customer = null;
        this.Payments = new Payments();
        this.Positions.clear();
        this.CorrectionData = null;
        this.Correction105Taxes = null;
        this.CashAmount = BigDecimal.ZERO;
        this.TextForPrint = "";
        this.PictureName = "";
        this.PictureBase64 = "";
        this.PictureAlignment = PictureAlignment.Center;
        this.MarkingCode = "";
        this.PlannedStatus = MarkingPlannedStatus.Sold;
        this.MarkingQuantity = BigDecimal.ONE;
        this.MeasureOfQuantity = MeasureOfQuantity.Piece;
        this.FractionalQuantityNumerator = 0;
        this.FractionalQuantityDenominator = 0;
        this.NotSendToServer = false;
        this.WaitForResult = false;
        this.RequestKmGuid = "";
        this.ConfirmationType = KmConfirmationType.Included;
        this.ShiftsFrom = LocalDate.now().minusDays(7);
        this.ShiftsTo = LocalDate.now();
        this.DocumentId = "";
        this.FiscalSign = "";
        this.ShiftNumber = 0;
        this.CheckNumber = 0;
        this.CheckNumberInShift = 0;
        this.RnNumber = "";
        this.FnsUrl = "";
        this.ServerDateTime = "";
        this.FiscalDateTime = "";
        this.DeviceDateTime = "";
        this.CurrentShiftState = null;
        this.BacklogDocumentsCount = 0;
        this.BacklogFirstDocumentNumber = 0;
        this.BacklogFirstDocumentDateTime = null;
        this.FnValidityDate = "";
        this.FnDaysResources = 0;
        this.IsFnPresent = false;
        this.IsFiscal = false;
        this.FnWarnings = null;
        this.ShiftTotals = null;
        this.NonZeroSum = BigDecimal.ZERO;
        this.Ok = false;
        this.ErrorCode = 0;
        this.ErrorDescription = "";
        this.LastResult = null;
        this.FiscalResult = null;
        this.MarkingCheck = null;
        this.MarkingProcessing = null;
        this.Check = null;
        this.Checks = new CheckDocument[0];
        this.TaskStatus = null;
        this.PrintForm = new PrintFormLine[0];
        this.Shifts = new ShiftListItem[0];
    }

    /**
     * Проверка доступности сервера ККМ. Не требует передачи ключа доступа (api_key)
     */
    public void Ping() {
        get("ping");
    }

    /**
     * Получение списка зарегистрированных ККТ
     */
    public void GetDeviceList() {
        get("kkt/list");
        DeviceListResponse[] devices = readResult(DeviceListResponse[].class);
        this.Devices = devices == null ? new DeviceListResponse[0] : devices;
    }

    /**
     * Получение подробной информации об устройстве ККТ
     */
    public void Connect() {
        get("kkt?" + deviceQuery());
        this.Kkt = readResult(DataKkt.class);
        if (this.Kkt != null && this.Kkt.Status != null) {
            this.Status = this.Kkt.Status;
        }
        if (this.Kkt != null && this.Kkt.Device != null) {
            this.LineLength = this.Kkt.Device.LineLength;
        } else if (this.Kkt != null && this.Kkt.Status != null) {
            this.LineLength = this.Kkt.Status.LineLength;
        }
        if (this.Kkt != null && this.Kkt.Fn != null && !blank(this.Kkt.Fn.SaleLocation)) {
            this.SaleLocation = this.Kkt.Fn.SaleLocation;
        }
    }

    /**
     * Получение расширенного статуса ККТ
     */
    public void GetStatus() {
        get("kkt/status?" + deviceQuery());
        this.Status = readResult(KktStatus.class);
        if (this.Status == null) {
            return;
        }
        this.LineLength = this.Status.LineLength;
        this.ShiftNumber = this.Status.ShiftNumber;
        this.CheckNumber = this.Status.DocNumber;
    }

    /**
     * Получение краткого статуса смены и очереди ОФД
     */
    public void GetShiftStatus() {
        get("kkt/shift/status?" + deviceQuery());
        this.ShiftStatus = readResult(ResponseCurrentStatus.class);
        if (this.ShiftStatus == null) {
            return;
        }
        this.ShiftNumber = this.ShiftStatus.ShiftNumber;
        this.CheckNumber = this.ShiftStatus.CheckNumber;
    }

    /**
     * Открытие кассовой смены
     */
    public void OpenShift() {
        post("shift/open", checkBase());
    }

    /**
     * Закрытие кассовой смены (Z-отчет)
     */
    public void CloseShift() {
        post("shift/z", checkBase());
    }

    /**
     * Формирование X-отчета (без закрытия смены)
     */
    public void ReportX() {
        post("shift/x", checkBase());
    }

    /**
     * Формирование отчета о текущем состоянии расчетов
     */
    public void ReportSettlement() {
        post("report/settlement", checkBase());
    }

    /**
     * Возвращает X-отчет по идентификатору документа (docId)
     */
    public void GetReportX() {
        getDocumentById("shift/x");
    }

    /**
     * Возвращает Z-отчет по идентификатору документа (docId)
     */
    public void GetReportZ() {
        getDocumentById("shift/z");
    }

    /**
     * Возвращает результат открытия смены по идентификатору документа (docId)
     */
    public void GetOpenShift() {
        getDocumentById("shift/open");
    }

    /**
     * Возвращает отчет о состоянии расчетов по идентификатору документа (docId)
     */
    public void GetReportSettlement() {
        getDocumentById("report/settlement");
    }

    /**
     * Получение необнуляемых (накопительных) счетчиков ККТ
     */
    public void GetOverAll() {
        get("kkt/counters/overall?" + deviceQuery());
        OverallTotals totals = readResult(OverallTotals.class);
        BigDecimal sum = totals == null || totals.Counters == null || totals.Counters.Sales == null
                ? null : totals.Counters.Sales.Sum;
        this.NonZeroSum = sum == null ? BigDecimal.ZERO : sum;
    }

    /**
     * Получение максимальной ширины строки чека устройства
     */
    public void GetLineLength() {
        get("kkt/lineLength?" + deviceQuery());
        LineLengthV2 length = readResult(LineLengthV2.class);
        if (length == null) {
            return;
        }
        this.LineLength = length.LineLength;
        this.LineLengthPixels = length.LineLengthPixels;
    }

    /**
     * Получение счетчиков за смену
     */
    public void GetTotals() {
        get("kkt/counters/shift?" + deviceQuery());
        this.ShiftTotals = readResult(ResShiftTotal.class);
    }

    /**
     * Получение списка Z-отчетов за период
     */
    public void GetShiftList() {
        String extra = this.ReportType > 0 ? "reportType=" + this.ReportType : null;
        getReportList("shift/z/list", extra);
    }

    /**
     * Получение списка открытий смен за период
     */
    public void GetOpenShiftList() {
        getReportList("shift/open/list", null);
    }

    /**
     * Получение списка X-отчетов за период
     */
    public void GetReportXList() {
        getReportList("shift/x/list", null);
    }

    /**
     * Список отчетов о состоянии расчетов по устройству за период
     */
    public void GetReportSettlementList() {
        getReportList("report/settlement/list", null);
    }

    /**
     * Печать кассового чека
     */
    public void PrintCheck() {
        post("check", checkBody());
    }

    /**
     * Асинхронно поставить фискальный чек в очередь печати
     */
    public void PrintCheckAsync() {
        post("check/async", checkBody());
    }

    /**
     * Печать чека коррекции для ФФД 1.2
     */
    public void PrintCheckCorrection120() {
        post("correction120", correction120Body());
    }

    /**
     * Асинхронно печатает чек коррекции для ФФД 1.2
     */
    public void PrintCheckCorrection120Async() {
        post("correction120/async", correction120Body());
    }

    /**
     * Печать чека коррекции для ФФД 1.0.5
     */
    public void PrintCheckCorrection105() {
        post("correction105", correction105Body());
    }

    /**
     * Асинхронно ставит печать чека коррекции для ФФД 1.0.5.
     */
    public void PrintCheckCorrection105Async() {
        post("correction105/async", correction105Body());
    }

    /**
     * Возвращает чек коррекции ФФД 1.2 по идентификатору документа (docId)
     */
    public void GetCorrection120() {
        getDocumentById("correction120");
    }

    /**
     * Получение списка чеков коррекции ФФД 1.2
     */
    public void GetCorrection120List() {
        getCheckList("correction120/list");
    }

    /**
     * Возвращает чек коррекции ФФД 1.0.5 по идентификатору документа (docId)
     */
    public void GetCorrection105() {
        getDocumentById("correction105");
    }

    /**
     * Получение списка чеков коррекции ФФД 1.0.5
     */
    public void GetCorrection105List() {
        getCheckList("correction105/list");
    }

    /**
     * Получение списка чеков за смену
     */
    public void GetChecksByShift() {
        get("check/list?" + deviceQuery() + "&shift=" + this.ShiftNumber);
        CheckDocument[] checks = readResult(CheckDocument[].class);
        this.Checks = checks == null ? new CheckDocument[0] : checks;
    }

    /**
     * Возвращает статус выполнения задания по идентификатору документа (docId)
     */
    public void GetTaskStatus() {
        get("task/status?" + idQuery());
        this.TaskStatus = readResult(ResponseTaskStatus.class);
        if (this.TaskStatus == null) {
            return;
        }
        if (notEmpty(this.TaskStatus.FiscalSign)) {
            this.FiscalSign = this.TaskStatus.FiscalSign;
        }
        if (this.TaskStatus.DocNumber > 0) {
            this.CheckNumber = this.TaskStatus.DocNumber;
        }
        if (this.TaskStatus.ShiftNumber > 0) {
            this.ShiftNumber = this.TaskStatus.ShiftNumber;
        }
        if (notEmpty(this.TaskStatus.DocId)) {
            this.DocumentId = this.TaskStatus.DocId;
        }
    }

    /**
     * Возвращает результат операции по идентификатору документа (docId)
     */
    public void GetCheck() {
        getDocumentById("check");
    }

    /**
     * Получение фискального признака (ФП) по номеру фискального документа (ФД)
     */
    public void GetFiscalSignByDocNumber() {
        get("check/fiscalSign?docNumber=" + this.CheckNumber + "&" + deviceQuery());
        JsonNode last = this.LastResult;
        if (this.Ok && last != null && last.isTextual()) {
            this.FiscalSign = last.asText();
        }
    }

    /**
     * Печать копии чека
     */
    public void PrintCheckCopy() {
        if (blank(this.DocumentId)) {
            post("check/copy/last?" + deviceQuery());
        } else {
            CheckbaseParameters body = new CheckbaseParameters();
            body.DeviceName = this.DeviceName;
            body.DocId = this.DocumentId;
            post("check/copy", body);
        }
    }

    /**
     * Возвращает печатную форму документа по его идентификатору (docId)
     */
    public void GetPrintForm() {
        get("task/form?" + idQuery());
        PrintFormLine[] form = readResult(PrintFormLine[].class);
        this.PrintForm = form == null ? new PrintFormLine[0] : form;
    }

    /**
     * Регистрация операции внесения наличных в денежный ящик
     */
    public void CashIn() {
        post("cashin", cashBody());
    }

    /**
     * Регистрация операции выемки наличных из денежного ящика
     */
    public void CashOut() {
        post("cashout", cashBody());
    }

    /**
     * Открытие денежного ящика
     */
    public void OpenCashdrawer() {
        post("cash/open", checkBase());
    }

    /**
     * Получение остатка наличных в денежном ящике
     */
    public void GetCash() {
        get("cash?" + deviceQuery());
        CashSum cash = readResult(CashSum.class);
        this.CashBalance = cash == null || cash.Sum == null ? BigDecimal.ZERO : cash.Sum;
    }

    /**
     * Возвращает результат операции внесения наличных по идентификатору операции (docId)
     */
    public void GetCashIn() {
        getDocumentById("cashin");
    }

    /**
     * Получение списка операций внесения наличных по имени устройства
     */
    public void GetCashInList() {
        getCheckList("cashin/list");
    }

    /**
     * Возвращает результат операции выемки наличных по идентификатору операции (docId)
     */
    public void GetCashOut() {
        getDocumentById("cashout");
    }

    /**
     * Загрузка изображения в выбранную ККТ
     */
    public void SendPicture() {
        UploadPicture body = new UploadPicture();
        body.DeviceName = this.DeviceName;
        body.PictureName = this.PictureName;
        body.Base64 = this.PictureBase64;
        body.Alignment = this.PictureAlignment.value;
        post("picture", body);
    }

    /**
     * Получение списка изображений
     */
    public void GetPictureList() {
        get("picture/list?" + deviceQuery());
        this.Pictures = new ArrayList<>(readResultList(Picture.class));
    }

    /**
     * Открытие сессии регистрации (проверки) кодов маркировки на ККТ
     */
    public void OpenSessionRegistrationKM() {
        CheckbaseParameters body = new CheckbaseParameters();
        body.DeviceName = this.DeviceName;
        post("marking/session/open", body);
    }

    /**
     * Закрытие сессии регистрации (проверки) кодов маркировки на ККТ
     */
    public void CloseSessionRegistrationKM() {
        CheckbaseParameters body = new CheckbaseParameters();
        body.DeviceName = this.DeviceName;
        post("marking/session/close", body);
    }

    /**
     * Локальная проверка кода маркировки на ККТ (ФФД 1.2)
     */
    public void RequestKM() {
        if (blank(this.RequestKmGuid)) {
            this.RequestKmGuid = UUID.randomUUID().toString();
        }
        RequestKm km = new RequestKm();
        km.Guid = this.RequestKmGuid;
        km.NotSendToServer = this.NotSendToServer;
        km.WaitForResult = this.WaitForResult;
        km.MarkingCode = this.MarkingCode;
        km.PlannedStatus = this.PlannedStatus.value;
        km.Quantity = this.MarkingQuantity;
        km.MeasureOfQuantity = this.MeasureOfQuantity.value;
        km.FractionalQuantityNumerator = this.FractionalQuantityNumerator > 0
                ? this.FractionalQuantityNumerator : null;
        km.FractionalQuantityDenominator = this.FractionalQuantityDenominator > 0
                ? this.FractionalQuantityDenominator : null;
        RequestKmParameters body = new RequestKmParameters();
        body.DeviceName = this.DeviceName;
        body.RequestKM = km;
        post("marking/km/request", body);
        this.MarkingCheck = readResult(RequestKmResult.class);
    }

    /**
     * Получение результата проверки кода маркировки в ОИСМ
     */
    public void GetProcessingKMResult() {
        get("marking/km/result?" + deviceQuery());
        this.MarkingProcessing = readResult(ProcessingKmResult.class);
        if (this.MarkingProcessing != null && !blank(this.MarkingProcessing.Guid)) {
            this.RequestKmGuid = this.MarkingProcessing.Guid;
        }
    }

    /**
     * Подтверждение, будет ли ранее проверенный код маркировки включен в документ реализации. Действительно только в рамках открытой сессии регистрации
     */
    public void ConfirmKM() {
        RequestConfirmKm body = new RequestConfirmKm();
        body.DeviceName = this.DeviceName;
        body.GUID = this.RequestKmGuid;
        body.ConfirmationType = this.ConfirmationType.value;
        post("marking/km/confirm", body);
    }

    /**
     * Печать нефискального документа
     */
    public void PrintSlip() {
        post("slip", slipBody());
    }

    /**
     * Асинхронно поставить нефискальный документ в очередь печати
     */
    public void PrintSlipAsync() {
        post("slip/async", slipBody());
    }

    /**
     * Версия сервера ККМ.
     */
    public void GetVersion() {
        get("version");
        JsonNode last = this.LastResult;
        if (last == null) {
            this.ServerVersion = "";
        } else if (last.isTextual()) {
            this.ServerVersion = last.asText();
        } else {
            this.ServerVersion = last.toString();
        }
    }

    /**
     * Получение токена авторизации по логину и паролю. Нужны {@link #AuthUserName} и {@link #AuthPassword} (по умолчанию Admin / Admin).
     */
    public void GetUserToken() {
        if (blank(this.AuthUserName) || blank(this.AuthPassword)) {
            this.Ok = false;
            this.ErrorCode = -1;
            this.ErrorDescription = "Укажите AuthUserName и AuthPassword для получения токена.";
            return;
        }
        get("user/token", true);
        this.UserToken = readResult(UserToken.class);
        if (this.UserToken != null && !blank(this.UserToken.TokenId)) {
            this.Token = this.UserToken.TokenId;
        }
    }

    /**
     * Список пользователей сервера ККМ.
     */
    public void GetUserList() {
        get("user/list");
        ServiceUser[] users = readResult(ServiceUser[].class);
        this.Users = users == null ? new ServiceUser[0] : users;
    }

    /**
     * Добавление пользователя.
     */
    public void AddUser() {
        AdminContracts.UserProfileRequest body = new AdminContracts.UserProfileRequest();
        body.User = this.ServiceUser;
        post("user", body);
    }

    /**
     * Изменение пользователя.
     */
    public void UpdateUser() {
        put("user?id=" + escape(this.UserId), this.ServiceUser);
    }

    /**
     * Удаление пользователя.
     */
    public void DeleteUser() {
        delete("user?id=" + escape(this.UserId));
    }

    /**
     * Получение настроек службы печати.
     */
    public void GetServiceSettings() {
        get("service/settings");
        this.ServiceSettingsResult = readResult(ServiceSettings.class);
    }

    /**
     * Сохранение настроек службы печати.
     */
    public void SaveServiceSettings() {
        AdminContracts.ServiceSettingsRequest body = new AdminContracts.ServiceSettingsRequest();
        body.ServiceSettings = this.ServiceSettings;
        post("service/settings", body);
    }

    /**
     * Добавление кассы на сервер.
     */
    public void AddDevice() {
        DeviceSettings settings = this.DeviceSettings == null ? new DeviceSettings() : this.DeviceSettings;
        settings.DeviceName = blank(settings.DeviceName) ? this.DeviceName : settings.DeviceName;
        AdminContracts.DeviceSettingsRequest body = new AdminContracts.DeviceSettingsRequest();
        body.DeviceName = settings.DeviceName;
        body.Settings = settings;
        post("kkt", body);
    }

    /**
     * Изменение настроек кассы.
     */
    public void UpdateDevice() {
        DeviceSettings settings = this.DeviceSettings == null ? new DeviceSettings() : this.DeviceSettings;
        settings.DeviceName = blank(settings.DeviceName) ? this.DeviceName : settings.DeviceName;
        AdminContracts.DeviceSettingsRequest body = new AdminContracts.DeviceSettingsRequest();
        body.DeviceName = settings.DeviceName;
        body.Settings = settings;
        put("kkt", body);
    }

    /**
     * Удаление кассы с сервера.
     */
    public void DeleteDevice() {
        delete("kkt?device=" + escape(this.DeviceName));
    }

    /**
     * Перезагрузка кассы.
     */
    public void RebootDevice() {
        post("kkt/reboot", checkBase());
    }

    /**
     * Настройка шрифтов шаблона кассы.
     */
    public void SetDeviceFont() {
        DeviceSettings settings = this.DeviceSettings;
        AdminContracts.DeviceFontSettingsRequest body = new AdminContracts.DeviceFontSettingsRequest();
        body.DeviceName = this.DeviceName;
        if (settings != null) {
            body.TemplateSettingH1 = settings.TemplateSettingH1;
            body.TemplateSettingH2 = settings.TemplateSettingH2;
            body.TemplateSettingH3 = settings.TemplateSettingH3;
            body.TemplateSettingH4 = settings.TemplateSettingH4;
            body.TemplateSettingH5 = settings.TemplateSettingH5;
        }
        post("kkt/font/setting", body);
    }

    /**
     * Список пулов устройств.
     */
    public void GetPoolList() {
        get("pool/list");
        String[] pools = readResult(String[].class);
        this.Pools = pools == null ? new String[0] : pools;
    }

    /**
     * Список касс в пуле.
     */
    public void GetDeviceListByPool() {
        get("kkt/list/byPool?pool=" + escape(this.PoolName));
        DeviceListResponse[] devices = readResult(DeviceListResponse[].class);
        this.Devices = devices == null ? new DeviceListResponse[0] : devices;
    }

    /**
     * Асинхронное открытие смены.
     */
    public void OpenShiftAsync() {
        post("shift/open/async", checkBase());
    }

    /**
     * Асинхронное закрытие смены.
     */
    public void CloseShiftAsync() {
        post("shift/z/async", checkBase());
    }

    /**
     * Асинхронный X-отчет.
     */
    public void ReportXAsync() {
        post("shift/x/async", checkBase());
    }

    /**
     * Асинхронный отчет о состоянии расчетов.
     */
    public void ReportSettlementAsync() {
        post("report/settlement/async", checkBase());
    }

    /**
     * Асинхронное внесение наличных.
     */
    public void CashInAsync() {
        post("cashin/async", cashBody());
    }

    /**
     * Асинхронная выемка наличных.
     */
    public void CashOutAsync() {
        post("cashout/async", cashBody());
    }

    /**
     * Список чеков за период или смену.
     */
    public void GetCheckList() {
        String query = deviceQuery() + "&" + dateQuery(this.ShiftsFrom, this.ShiftsTo);
        if (this.ShiftNumber > 0) {
            query += "&shift=" + this.ShiftNumber;
        }
        get("check/list?" + query);
        CheckDocument[] checks = readResult(CheckDocument[].class);
        this.Checks = checks == null ? new CheckDocument[0] : checks;
    }

    /**
     * Печать копии чека по данным фискального накопителя.
     */
    public void PrintCheckCopyFn() {
        AdminContracts.CheckCopyFnParameters body = new AdminContracts.CheckCopyFnParameters();
        body.DeviceName = this.DeviceName;
        body.FnNumber = this.FnNumber;
        body.FiscalSign = this.FiscalSign;
        body.DocNumber = this.CheckNumber;
        post("check/copy/fn", body);
    }

    /**
     * Получение слипа по идентификатору документа.
     */
    public void GetSlip() {
        getDocumentById("slip");
    }

    /**
     * Список слипов по кассе.
     */
    public void GetSlipList() {
        getCheckList("slip/list");
    }

    /**
     * Получение картинки по имени.
     */
    public void GetPicture() {
        get("picture?" + deviceQuery() + "&id=" + escape(this.PictureId));
        JsonNode last = this.LastResult;
        if (this.Ok && last != null && last.isTextual()) {
            this.PictureBase64Result = last.asText();
        }
    }

    /**
     * Удаление картинки.
     */
    public void DeletePicture() {
        delete("picture?" + deviceQuery() + "&id=" + escape(this.PictureId));
    }

    /**
     * Создание шаблона печати.
     */
    public void AddTemplate() {
        post("template", this.TemplateParameters);
    }

    /**
     * Изменение шаблона печати.
     */
    public void UpdateTemplate() {
        put("template", this.TemplateParameters);
    }

    /**
     * Удаление шаблона печати.
     */
    public void DeleteTemplate() {
        delete("template?id=" + escape(this.TemplateName));
    }

    /**
     * Список шаблонов печати.
     */
    public void GetTemplateList() {
        get("template/list");
        this.Templates = readTemplateList();
    }

    /**
     * Получение шаблона печати по имени.
     */
    public void GetTemplate() {
        get("template?name=" + escape(this.TemplateName));
        this.PrintTemplate = readResult(PrintTemplate.class);
    }

    /**
     * Создание шаблона чека.
     */
    public void AddCheckTemplate() {
        post("checkTemplate", checkTemplateBody());
    }

    /**
     * Изменение шаблона чека.
     */
    public void UpdateCheckTemplate() {
        put("checkTemplate", checkTemplateBody());
    }

    /**
     * Удаление шаблона чека.
     */
    public void DeleteCheckTemplate() {
        delete("checkTemplate?id=" + escape(this.TemplateName));
    }

    /**
     * Список шаблонов чека.
     */
    public void GetCheckTemplateList() {
        get("checkTemplate/list");
        CheckTemplateListItem[] items = readResult(CheckTemplateListItem[].class);
        this.CheckTemplates = items == null ? new CheckTemplateListItem[0] : items;
    }

    /**
     * Получение шаблона чека по имени.
     */
    public void GetCheckTemplate() {
        get("checkTemplate?id=" + escape(this.TemplateName));
        this.CheckTemplate = readResult(CheckTemplate.class);
    }

    /**
     * Состояние очереди печати.
     */
    public void GetQueue() {
        get("queue");
        QueueItem[] queue = readResult(QueueItem[].class);
        this.Queue = queue == null ? new QueueItem[0] : queue;
    }

    /**
     * Состояние задания в очереди.
     */
    public void GetQueueTask() {
        get("queue/task?taskId=" + escape(this.QueueTaskId));
        this.QueueTask = readResult(QueueTaskState.class);
    }

    /**
     * История обработки задания в очереди.
     */
    public void GetQueueTaskHistory() {
        get("queue/task/history?taskId=" + escape(this.QueueTaskId));
        this.QueueTask = readResult(QueueTaskState.class);
        if (this.QueueTask == null || this.QueueTask.History == null) {
            return;
        }
        List<OperationHistoryItem> history = new ArrayList<>();
        for (DocumentHistoryItem item : this.QueueTask.History) {
            OperationHistoryItem row = new OperationHistoryItem();
            row.Time = item.Time;
            row.State = item.State;
            row.Description = item.Description;
            history.add(row);
        }
        this.OperationHistory = history.toArray(new OperationHistoryItem[0]);
    }

    /**
     * Отмена задания в очереди.
     */
    public void CancelQueueTask() {
        delete("queue/task?taskId=" + escape(this.QueueTaskId));
    }

    /**
     * Проверка кода маркировки через внешний сервис.
     */
    public void VerifyMarking() {
        post("marking/km/verify", markingCodesBody());
        this.MarkingVerify = readResult(MarkingVerifyResult.class);
    }

    /**
     * Проверка кода маркировки через ТС ПИоТ.
     */
    public void VerifyMarkingTsPiot() {
        post("marking/km/tspiot/verify", markingCodesBody());
        this.MarkingVerify = readResult(MarkingVerifyResult.class);
    }

    /**
     * Проверка кода маркировки через ЛМ ЧЗ.
     */
    public void VerifyMarkingLmcz() {
        post("marking/km/lmcz/verify", markingCodesBody());
        this.MarkingVerify = readResult(MarkingVerifyResult.class);
    }

    /**
     * Фискализация кассы.
     */
    public void Fiscalization() {
        post("fiscalization", fiscalizationBody());
    }

    /**
     * Асинхронная фискализация кассы.
     */
    public void FiscalizationAsync() {
        post("fiscalization/async", fiscalizationBody());
    }

    /**
     * Результат фискализации по идентификатору документа.
     */
    public void GetFiscalization() {
        getDocumentById("fiscalization");
        this.FiscalizationDocument = readResult(FiscalizationDocument.class);
    }

    /**
     * Список операций фискализации по кассе.
     */
    public void GetFiscalizationList() {
        get("fiscalization/list?" + deviceQuery());
        FiscalizationDocument[] items = readResult(FiscalizationDocument[].class);
        this.Fiscalizations = items == null ? new FiscalizationDocument[0] : items;
    }

    /**
     * Последняя операция из базы. tasktype — {@link #PaymentType}, isProcessed — {@link #IsProcessed}.
     */
    public void GetOperationLast() {
        String processed = this.IsProcessed ? "true" : "false";
        get("operation/last?tasktype=" + this.PaymentType.value + "&isProcessed=" + processed);
        applyOperation(readResult(DeviceTaskInfo.class));
    }

    /**
     * Операция по идентификатору документа.
     */
    public void GetOperation() {
        get("operation?" + docIdQuery());
        applyOperation(readResult(DeviceTaskInfo.class));
    }

    /**
     * История операции по идентификатору документа.
     */
    public void GetOperationHistory() {
        get("operation/history?" + docIdQuery());
        OperationHistoryItem[] items = readResult(OperationHistoryItem[].class);
        this.OperationHistory = items == null ? new OperationHistoryItem[0] : items;
    }

    /**
     * TLV-данные операции.
     */
    public void GetOperationTlv() {
        get("operation/tlv?" + docIdQuery());
        JsonNode last = this.LastResult;
        if (this.Ok && last != null && last.isTextual()) {
            this.OperationTlv = last.asText();
        }
    }

    /**
     * Данные маркировки операции.
     */
    public void GetOperationKm() {
        get("operation/km?" + docIdQuery());
        OperationKmRow[] rows = readResult(OperationKmRow[].class);
        this.OperationKm = rows == null ? new OperationKmRow[0] : rows;
    }

    /**
     * Связанные операции.
     */
    public void GetOperationRelated() {
        get("operation/related?" + docIdQuery());
        DeviceTaskInfo[] items = readResult(DeviceTaskInfo[].class);
        this.RelatedOperations = items == null ? new DeviceTaskInfo[0] : items;
    }

    /**
     * Список операций за период.
     */
    public void GetOperationList() {
        get("operation/list?" + dateQuery(this.ShiftsFrom, this.ShiftsTo));
        OperationListItem[] items = readResult(OperationListItem[].class);
        this.Operations = items == null ? new OperationListItem[0] : items;
    }

    private AdminContracts.MarkingCodesRequest markingCodesBody() {
        AdminContracts.MarkingCodesRequest body = new AdminContracts.MarkingCodesRequest();
        body.DeviceName = this.DeviceName;
        body.Codes = new ArrayList<>(this.MarkingCodes);
        return body;
    }
}
