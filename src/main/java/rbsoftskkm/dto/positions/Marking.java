package rbsoftskkm.dto.positions;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Код товарной номенклатуры / маркировка позиции.
 * Создайте объект и заполните нужные поля (MarkingCode/Code, Gtin, StampType, Quantity и т.д.),
 * затем присвойте в {@link Marking}.
 */
public class Marking {
    /** Глобальный идентификатор торговой единицы (GTIN) */
    public String Gtin;

    /**
     * Тип маркировки. Список значений:
     * "02" – изделия из меха
     * "05" - табачная продукция
     * "1520" - обувные товары
     */
    public String StampType;

    /** Контрольный идентификационный знак (КиЗ) */
    public String Stamp;

    /** Серийный номер */
    public String SerialNumber;

    /** Код контрольной марки. Кодируется текстом в кодировке Base64 */
    @JsonProperty("MarkingCode")
    public String Code;

    /** Штрихкод */
    public String Barcode;

    /** Тип (группа) товара */
    public String CommodityGroup;

    /** Код товара, формат которого не идентифицирован в Base64 */
    public String NotIdentified;

    /** Код товара в формате EAN-8 в Base64 */
    @JsonProperty("EAN8")
    public String Ean8;

    /** Код товара в формате EAN-13 в Base64 */
    @JsonProperty("EAN13")
    public String Ean13;

    /** Код товара в формате ITF-14 в Base64 */
    @JsonProperty("ITF14")
    public String Itf14;

    /** Код товара в формате GS1, нанесенный на товар, не подлежащий маркировке средствами идентификации в Base64 */
    @JsonProperty("GS10")
    public String Gs10;

    /** Код товара в формате GS1, нанесенный на товар, подлежащий маркировке средствами идентификации в Base64 */
    @JsonProperty("GS1M")
    public String Gs1m;

    /** Код товара в формате короткого кода маркировки, нанесенный на товар, подлежащий маркировке средствами идентификации в Base64 */
    @JsonProperty("KMK")
    public String Kmk;

    /** Контрольно-идентификационный знак мехового изделия */
    @JsonProperty("MI")
    public String Mi;

    /** Код товара в формате ЕГАИС-2.0 в Base64 */
    @JsonProperty("EGAIS20")
    public String Egais20;

    /** Код товара в формате ЕГАИС-3.0 в Base64 */
    @JsonProperty("EGAIS30")
    public String Egais30;

    /** Код товара в формате Ф.1 в Base64 */
    public String F1;

    /** Код товара в формате Ф.2 в Base64 */
    public String F2;

    /** Код товара в формате Ф.3 в Base64 */
    public String F3;

    /** Код товара в формате Ф.4 в Base64 */
    public String F4;

    /** Код товара в формате Ф.5 в Base64 */
    public String F5;

    /** Код товара в формате Ф.6 в Base64 */
    public String F6;
}
