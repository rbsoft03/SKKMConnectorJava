package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Метод подключения устройства:
 * Com - COM-порт
 * TcpIp - TCP/IP
 */
public enum ConnectionMethod {

    /** COM-порт. */
    Com(0),

    /** TCP/IP. */
    TcpIp(1);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    ConnectionMethod(int value) {
        this.value = value;
    }
}
