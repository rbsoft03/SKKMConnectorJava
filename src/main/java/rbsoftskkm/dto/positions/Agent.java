package rbsoftskkm.dto.positions;

/**
 * Данные агента в чеке:
 * PayingAgentOperation - Операция платёжного агента
 * PayingAgentPhone - Телефон(ы) платёжного агента
 * ReceivePaymentsOperatorPhone - Телефон(ы) оператора по приёму платежей
 * MoneyTransferOperatorPhone - Телефон(ы) оператора перевода
 * MoneyTransferOperatorName - Наименование оператора перевода
 * MoneyTransferOperatorAddress - Адрес оператора перевода
 * MoneyTransferOperatorVatin - ИНН оператора перевода
 */
public class Agent {
    /** Операция платёжного агента. */
    public String PayingAgentOperation;

    /** Телефон платёжного агента. */
    public String[] PayingAgentPhone;

    /** Телефон оператора по приёму платежей. */
    public String[] ReceivePaymentsOperatorPhone;

    /** Телефон оператора перевода. */
    public String[] MoneyTransferOperatorPhone;

    /** Наименование оператора перевода. */
    public String MoneyTransferOperatorName;

    /** Адрес оператора перевода. */
    public String MoneyTransferOperatorAddress;

    /** ИНН оператора перевода. */
    public String MoneyTransferOperatorVatin;
}
