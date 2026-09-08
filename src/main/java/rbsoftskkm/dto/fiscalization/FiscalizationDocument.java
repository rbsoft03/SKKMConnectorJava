package rbsoftskkm.dto.fiscalization;

import rbsoftskkm.dto.enums.*;

/** Результат фискализации. */
public class FiscalizationDocument {
    /** Тип выполненной операции. */
    public FiscalizationOperationType OperationType;

    /** Регистрационный номер ККТ. */
    public String RnNumber = "";

    /** Коды систем налогообложения. */
    public String TaxationSystems = "";

    /** ИНН организации. */
    public String Vatin = "";

    /** Название организации. */
    public String CompanyName = "";

    /** Версия ФФД ККТ. */
    public String FfdVersionKkt = "";

    /** Версия ФФД ФН. */
    public String FfdVersionFn = "";

    /** Признак фискального режима. */
    public boolean IsFiscal;

    /** Идентификатор документа фискализации. */
    public String DocId = "";

    /** Название устройства. */
    public String DeviceName = "";

    /** Номер смены. */
    public int ShiftNumber;

    /** Номер фискального документа. */
    public int DocNumber;

    /** Фискальный признак документа. */
    public String FiscalSign = "";
}
