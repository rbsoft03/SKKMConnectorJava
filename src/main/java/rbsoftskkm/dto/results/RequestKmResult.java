package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Результат локальной проверки кода маркировки */
public class RequestKmResult {
    /** Признак наличия связи с ОИСМ на момент отправки запроса. */
    @JsonProperty("ISMConnected")
    public boolean IsmConnected;

    /** Признак того, что проверка формата кода маркировки прошла успешно. */
    @JsonProperty("FormatChecking")
    public boolean FormatChecking;

    /** Признак того, что проверка кода маркировки поставлена в обработку. */
    @JsonProperty("Checking")
    public boolean Checking;

    /** Результат проверки, если он уже доступен на момент ответа. */
    @JsonProperty("CheckingResult")
    public boolean CheckingResult;

    /** Штрихкод после приведения к виду со спецсимволами GS. */
    @JsonProperty("Barcode")
    public String Barcode;
}
