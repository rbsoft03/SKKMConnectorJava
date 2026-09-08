package rbsoftskkm.dto;

/**
 * Операционный реквизит чека:
 * DateTime - Дата и время операции
 * OperationId - Идентификатор операции
 * OperationData - Данные операции
 */
public class OperationalAttribute {
    /** Дата, время операции. */
    public String DateTime;

    /** Идентификатор операции. */
    public Integer OperationId;

    /** Данные операции. */
    public String OperationData;
}
