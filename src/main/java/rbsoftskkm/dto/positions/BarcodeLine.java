package rbsoftskkm.dto.positions;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Строка штрихкода в чеке:
 * Type - Тип штрихкода
 * Value - Значение
 */
public class BarcodeLine extends Position {
    /** Тип штрихкода */
    public String Type = "";

    /** Значение штрихкода */
    @JsonProperty("Value")
    public String Barcode = "";

    /** Значение штрихкода в Base64 */
    public String ValueBase64;

    /** Выравнивание штрихкода */
    public String Alignment;
}
