package rbsoftskkm.dto;

import rbsoftskkm.dto.enums.*;
import java.math.BigDecimal;

/**
 * Сведения об одной безналичной оплате (тег 1234 и связанные):
 * Amount - Сумма оплаты безналичными
 * PaymentMethod - Признак способа оплаты. Используйте enum {@link ElectronicPaymentMethod}
 * Identifiers - Идентификаторы безналичной оплаты
 * AdditionalInformation - Дополнительные сведения
 */
public class ElectronicPayment {
    /** Сумма оплаты безналичными. */
    public BigDecimal Amount;

    /** Признак способа оплаты безналичными. Используйте enum {@link ElectronicPaymentMethod}. */
    public ElectronicPaymentMethod PaymentMethod;

    /** Идентификаторы безналичной оплаты. */
    public String Identifiers;

    /** Дополнительные сведения о безналичной оплате. */
    public String AdditionalInformation;
}
