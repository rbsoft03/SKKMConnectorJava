package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Сохранённый документ с сервера */
public class CheckDocument {
    /** Позиции чека. */
    @JsonProperty("CheckItems")
    public CheckItem[] CheckItems;

    /** Подтверждён в ФН. */
    @JsonProperty("TrustedInFn")
    public boolean TrustedInFn;

    /** Фискальный документ. */
    @JsonProperty("IsFiscal")
    public boolean IsFiscal;

    /** Сдача. */
    @JsonProperty("Change")
    public BigDecimal Change;

    /** Сумма с учётом скидки. */
    @JsonProperty("Sum")
    public BigDecimal Sum;

    /** Признак применения ККТ при осуществлении расчёта в безналичном порядке в сети «Интернет». */
    @JsonProperty("OperationOnline")
    public boolean OperationOnline;

    /** Номер телефона или электронная почта клиента. */
    @JsonProperty("ClientContact")
    public String ClientContact;

    /** Сведения о покупателе (клиенте). */
    @JsonProperty("CustomerDetail")
    public CheckCustomer CustomerDetail;

    /** Данные для отображения QR-кода чека. */
    @JsonProperty("QrData")
    public QrCheckData QrData;

    /** Оплаты. */
    @JsonProperty("Payments")
    public CheckPayments Payments;

    /** Заголовок документа. */
    @JsonProperty("DocumentHeader")
    public DocumentHeader DocumentHeader;

    /** Регистрация чека без печати на ленте. */
    @JsonProperty("Electronically")
    public boolean Electronically;

    /** Код налогообложения (СНО): 0 — ОСН, 1 — УСН, 2 — УСНД_Р, 3 — ЕНВД, 4 — ЕСН, 5 — ПСН. */
    @JsonProperty("TaxType")
    public int TaxType;

    /** Часовая зона: 0 — авто; 1 — МСК-1 / UTC+2; … 11 — МСК+9 / UTC+12. */
    @JsonProperty("TimeZone")
    public int TimeZone;

    /** Данные коррекции (чеки коррекции 1.2 и 1.05). */
    @JsonProperty("CorrectionData")
    public CorrectionData CorrectionData;

    /** Дополнительный реквизит чека (тег 1192). */
    @JsonProperty("AdditionalAttribute")
    public String AdditionalAttribute;

    /** Номер сессии. Используется для GET check/list. */
    @JsonProperty("ShiftNumber")
    public int ShiftNumber;

    /** Номер фискального документа. */
    @JsonProperty("DocNumber")
    public int DocNumber;

    /** Номер фискального документа за смену. */
    @JsonProperty("DocNumberInShift")
    public int DocNumberInShift;

    /** Фискальный признак документа. */
    @JsonProperty("FiscalSign")
    public String FiscalSign;

    /** Серийный номер фискального накопителя. */
    @JsonProperty("Fn")
    public String Fn;

    /** Время регистрации операции по часам ККМ. */
    @JsonProperty("FiscalDate")
    public LocalDateTime FiscalDate;

    /** Имя кассира. */
    @JsonProperty("CashierName")
    public String CashierName;

    /** ИНН кассира. */
    @JsonProperty("CashierVatin")
    public String CashierVatin;

    /** Адрес проведения расчётов. */
    @JsonProperty("SaleAddress")
    public String SaleAddress;

    /** Место проведения расчётов. */
    @JsonProperty("SaleLocation")
    public String SaleLocation;

    /** Версия ФФД. */
    @JsonProperty("FfdVersion")
    public String FfdVersion;

    /** Структура значений тегов документа. */
    @JsonProperty("Tlv")
    public String Tlv;

    /** Тип чека */
    @JsonProperty("TaskType")
    public int TaskType;

    /** Идентификатор документа. */
    @JsonProperty("DocId")
    public String DocId;

    /** Дата создания документа. */
    @JsonProperty("Date")
    public LocalDateTime Date;

    /** Идентификатор терминала, с которого пришёл документ. */
    @JsonProperty("TerminalId")
    public String TerminalId;

    /** Имя устройства. */
    @JsonProperty("DeviceName")
    public String DeviceName;

    /** Пул, который назначен чеку. */
    @JsonProperty("PoolId")
    public String PoolId;

    /** Результат обработки. */
    @JsonProperty("ResultCode")
    public int ResultCode;

    /** Описание результата. */
    @JsonProperty("ResultDescription")
    public String ResultDescription;

    /** Признак удачного завершения обработки. */
    @JsonProperty("Processed")
    public boolean Processed;

    /** Версия сервера ККМ. */
    @JsonProperty("ServerVersion")
    public String ServerVersion;

    /** Сведения о ККТ на момент документа. */
    @JsonProperty("DeviceInfo")
    public Device DeviceInfo;

    /** Сменные итоги (X/Z-отчёт). */
    @JsonProperty("ShiftTotal")
    public ResShiftTotal ShiftTotal;

    /** Количество аннулирований (X/Z-отчёт). */
    @JsonProperty("AnullatesCount")
    public int AnullatesCount;

    /** Сумма НДС 0% (коррекция 1.05). */
    @JsonProperty("TaxSum0")
    public BigDecimal TaxSum0;

    /** Сумма НДС 5% (коррекция 1.05). */
    @JsonProperty("TaxSum5")
    public BigDecimal TaxSum5;

    /** Сумма НДС 7% (коррекция 1.05). */
    @JsonProperty("TaxSum7")
    public BigDecimal TaxSum7;

    /** Сумма НДС 10% (коррекция 1.05). */
    @JsonProperty("TaxSum10")
    public BigDecimal TaxSum10;

    /** Сумма НДС 18% (коррекция 1.05). */
    @JsonProperty("TaxSum18")
    public BigDecimal TaxSum18;

    /** Сумма НДС 20% (коррекция 1.05). */
    @JsonProperty("TaxSum20")
    public BigDecimal TaxSum20;

    /** Сумма НДС 22% (коррекция 1.05). */
    @JsonProperty("TaxSum22")
    public BigDecimal TaxSum22;

    /** Сумма без НДС (коррекция 1.05). */
    @JsonProperty("TaxSumNone")
    public BigDecimal TaxSumNone;

    /** Сумма НДС 5/105 (коррекция 1.05). */
    @JsonProperty("TaxSum105")
    public BigDecimal TaxSum105;

    /** Сумма НДС 7/107 (коррекция 1.05). */
    @JsonProperty("TaxSum107")
    public BigDecimal TaxSum107;

    /** Сумма НДС 10/110 (коррекция 1.05). */
    @JsonProperty("TaxSum110")
    public BigDecimal TaxSum110;

    /** Сумма НДС 18/118 (коррекция 1.05). */
    @JsonProperty("TaxSum118")
    public BigDecimal TaxSum118;

    /** Сумма НДС 20/120 (коррекция 1.05). */
    @JsonProperty("TaxSum120")
    public BigDecimal TaxSum120;

    /** Сумма НДС 22/122 (коррекция 1.05). */
    @JsonProperty("TaxSum122")
    public BigDecimal TaxSum122;
}
