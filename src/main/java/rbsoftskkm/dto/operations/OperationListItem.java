package rbsoftskkm.dto.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Краткая информация об операции в списке. */
public class OperationListItem {
    /** Идентификатор документа. */
    @JsonProperty("DocId")
    public String DocId = "";

    /** Идентификатор документа-основания. */
    @JsonProperty("BaseDocId")
    public String BaseDocId = "";

    /** Идентификатор запроса. */
    @JsonProperty("RequestId")
    public String RequestId = "";

    /** Идентификатор терминала. */
    @JsonProperty("TerminalId")
    public String TerminalId = "";

    /** Имя устройства. */
    @JsonProperty("DeviceName")
    public String DeviceName = "";

    /** Идентификатор пула. */
    @JsonProperty("PoolId")
    public String PoolId = "";

    /** Дата операции. */
    @JsonProperty("Date")
    public LocalDateTime Date;

    /** Дата создания записи. */
    @JsonProperty("CreatedAt")
    public LocalDateTime CreatedAt;

    /** Дата последнего обновления. */
    @JsonProperty("UpdateAt")
    public LocalDateTime UpdateAt;

    /** Тип задания. */
    @JsonProperty("TaskType")
    public int TaskType;

    /** Наименование типа задания. */
    @JsonProperty("TaskName")
    public String TaskName = "";

    /** Сумма операции. */
    @JsonProperty("Sum")
    public BigDecimal Sum;

    /** Номер смены. */
    @JsonProperty("SessionNumber")
    public int SessionNumber;

    /** Номер документа в смене. */
    @JsonProperty("DocNumberInShift")
    public int DocNumberInShift;

    /** Номер фискального документа. */
    @JsonProperty("DocNumber")
    public int DocNumber;

    /** Дата документа по ФН. */
    @JsonProperty("FnDate")
    public LocalDateTime FnDate;

    /** Фискальный признак документа. */
    @JsonProperty("FiscalSign")
    public String FiscalSign = "";

    /** Номер фискального накопителя. */
    @JsonProperty("Fn")
    public String Fn = "";

    /** Контакт покупателя. */
    @JsonProperty("ClientContact")
    public String ClientContact = "";

    /** Имя кассира. */
    @JsonProperty("CashierName")
    public String CashierName = "";

    /** Регистрационный номер ККТ. */
    @JsonProperty("RnKKT")
    public String RnKKT = "";

    /** Заводской номер ККТ. */
    @JsonProperty("ZnKKT")
    public String ZnKKT = "";

    /** Код результата (0 — успех). */
    @JsonProperty("ResultCode")
    public int ResultCode;

    /** Описание результата. */
    @JsonProperty("ResultDescription")
    public String ResultDescription = "";

    /** Признак успешной обработки операции. */
    @JsonProperty("Processed")
    public boolean Processed;
}
