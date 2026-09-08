package rbsoftskkm.dto.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/** Строка журнала кодов маркировки операции. */
public class OperationKmRow {
    /** Код маркировки (КиЗ). */
    @JsonProperty("Cis")
    public String Cis = "";

    /** Время проверки кода. */
    @JsonProperty("CheckedAt")
    public LocalDateTime CheckedAt;

    /** Код маркировки без крипто-подписи. */
    @JsonProperty("PrintView")
    public String PrintView = "";

    /** Сообщение о результате проверки. */
    @JsonProperty("Message")
    public String Message = "";

    /** Статус проверки кода. */
    @JsonProperty("CheckStatus")
    public int CheckStatus;

    /** Наименование позиции чека. */
    @JsonProperty("PositionName")
    public String PositionName = "";

    /** Идентификаторы связанных документов. */
    @JsonProperty("DocIds")
    public String[] DocIds = new String[0];

    /** Цена продажи (в копейках). */
    @JsonProperty("SalePrice")
    public long SalePrice;

    /** Имя устройства. */
    @JsonProperty("DeviceName")
    public String DeviceName = "";

    /** Идентификатор марки. */
    @JsonProperty("MarkId")
    public String MarkId = "";

    /** Метод проверки кода маркировки. */
    @JsonProperty("KmVerificationMethod")
    public int KmVerificationMethod;

    /** Инициатор проверки кода маркировки. */
    @JsonProperty("KmCheckInitiator")
    public int KmCheckInitiator;
}
