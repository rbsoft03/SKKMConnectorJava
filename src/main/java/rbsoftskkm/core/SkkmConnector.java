package rbsoftskkm.core;

import rbsoftskkm.data.KkmTransport;

/**
 * Хранит HTTP-транспорт и освобождает соединение.
 * В C# это поля и методы файла SkkmConnector.cs, в TS — класс ServerKkmBase
 * в SkkmConnector.ts. В Java базовый класс должен стоять в начале цепочки
 * наследования, поэтому лежит здесь же, рядом с публичным классом.
 */
abstract class SkkmConnectorBase {

    final KkmTransport http = new KkmTransport();

    final Object callLock = new Object();

    KkmTransport.Call currentCall;

    private boolean disposed;

    /** Отменяет текущий HTTP-запрос к серверу ККМ. */
    public void Cancel() {
        synchronized (callLock) {
            if (currentCall != null) {
                currentCall.cancel();
            }
        }
    }

    /**
     * Освобождает HTTP-соединение с сервером ККМ. После этого экземпляр использовать нельзя —
     * создайте новый, если снова нужен доступ к кассе.
     */
    public void Dispose() {
        if (disposed) {
            return;
        }
        disposed = true;
        Cancel();
        http.close();
    }
}

/**
 * Коннектор Сервера ККМ. Один экземпляр — одна сессия.
 * Порт SkkmConnector.cs: в C# класс собран из семи partial-файлов,
 * в Java те же семь файлов образуют цепочку наследования
 * SkkmConnectorBase → State → Connection → CheckInput → Internals → Requests → Api.
 */
public final class SkkmConnector extends SkkmConnectorApi implements AutoCloseable {

    /** Синоним {@link #Dispose()} для try-with-resources. */
    @Override
    public void close() {
        Dispose();
    }
}
