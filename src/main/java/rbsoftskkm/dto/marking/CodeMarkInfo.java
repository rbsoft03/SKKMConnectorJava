package rbsoftskkm.dto.marking;

/** Сведения о коде маркировки. */
public class CodeMarkInfo {
    /** Полный код маркировки (КиЗ). */
    public String Cis = "";

    /** Признак валидности структуры кода. */
    public boolean Valid;

    /** Код маркировки без крипто-подписи. */
    public String PrintView = "";

    /** Идентификаторы товарных групп. */
    public int[] GroupIds = new int[0];

    /** Результат криптографической проверки кода. */
    public boolean Verified;

    /** Признак статуса «В обороте». */
    public boolean Realizable;

    /** Признак нанесения кода на упаковку. */
    public boolean Utilised;

    /** Признак наличия кода в ГИС МТ. */
    public boolean Found;

    /** Код ошибки проверки. */
    public int ErrorCode;

    /** Сообщение об ошибке. */
    public String Message = "";

    /** Признак старта прослеживаемости в товарной группе. */
    public boolean IsTracking;

    /** Признак того, что товар с данным кодом уже продан. */
    public boolean Sold;

    /** Код товара (GTIN). */
    public String Gtin = "";

    /** Тип упаковки. */
    public String PackageType = "";

    /** ИНН производителя. */
    public String ProducerInn = "";

    /** Признак нахождения продукции в «серой зоне». */
    public boolean GrayZone;

    /** Признак блокировки кода по решению ОГВ. */
    public boolean IsBlocked;

    /** Признак некорректного (незарегистрированного) GTIN. */
    public boolean IsGreyGtin;

    /** Органы государственной власти, установившие блокировку. */
    public String[] Ogvs = new String[0];

    /** Ёмкость КИГУ (количество потенциальных вложений). */
    public int PackageQuantity;
}
