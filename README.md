# SkkmConnector для Java

Порт библиотеки [SkkmConnector](https://github.com/rbsoft03/SKKMConnector) (C#) и
[SKKMConnectorTS](https://github.com/rbsoft03/SKKMConnectorTS) (TypeScript) на Java 17.

Структура папок, имена файлов, классов, полей и публичных методов совпадают с оригиналами
один в один. Всего 132 файла – ровно столько же, сколько в репозитории на C#.

## Соответствие файлов

```
C#                                        Java
SkkmConnector/Core/SkkmConnector.cs       rbsoftskkm/core/SkkmConnector.java
SkkmConnector/Core/SkkmConnector.Api.cs   rbsoftskkm/core/SkkmConnectorApi.java
SkkmConnector/Core/SkkmConnector.State.cs rbsoftskkm/core/SkkmConnectorState.java
...                                       ...
SkkmConnector/Data/KkmTransport.cs        rbsoftskkm/data/KkmTransport.java
SkkmConnector/Data/Contracts/*.cs         rbsoftskkm/data/contracts/*.java
SkkmConnector/Dto/Enums/*.cs              rbsoftskkm/dto/enums/*.java
SkkmConnector/Dto/Results/*.cs            rbsoftskkm/dto/results/*.java
```

Namespace `RBSoftSkkm` стал корневым пакетом `rbsoftskkm`, папки – подпакетами,
как в модулях TypeScript (`src/dto/enums` → `rbsoftskkm.dto.enums`).

## Поля и методы

Поля публичные, с теми же именами, что в C# и TS – без геттеров и сеттеров:

```java
try (SkkmConnector kkm = new SkkmConnector()) {
    kkm.Host = "192.168.1.121";
    kkm.Port = 4398;
    kkm.Token = token;
    kkm.DeviceName = "KASSA1";

    kkm.GetStatus();
    System.out.println(kkm.Status.ShiftNumber);

    kkm.NewRequest();
    kkm.PaymentType = CheckType.Sale;
    kkm.TaxVariant = TaxSystem.ОСН;

    FiscalLine item = new FiscalLine();
    item.Name = "Товар";
    item.Quantity = BigDecimal.ONE;
    item.Price = new BigDecimal("100.00");
    item.Sum = new BigDecimal("100.00");
    kkm.Positions.add(item);

    kkm.Payments.Cash = new BigDecimal("100.00");
    kkm.PrintCheck();

    if (kkm.Ok) {
        System.out.println(kkm.FiscalSign);
    }
}
```

Публичные методы – PascalCase, как `Ping()`, `GetDeviceList()`, `PrintCheck()` в обоих
оригиналах. Внутренние помощники (`get`, `post`, `readResult`, `checkBase`, `fillCheck`)
названы camelCase – так же, как в TS-версии.

## Пять мест, где Java не позволяет буквального совпадения

**1. Точки в именах файлов.** `SkkmConnector.Api.cs` в Java невозможен: имя файла обязано
совпадать с именем публичного класса, а точка в идентификаторе запрещена. Отсюда
`SkkmConnectorApi.java`, `SkkmConnectorState.java` и так далее – порядок слов сохранен.

**2. partial-классы.** В C# семь файлов – один класс. В Java это цепочка наследования,
ровно как миксины в TS-версии:

```
SkkmConnectorBase → State → Connection → CheckInput → Internals → Requests → Api → SkkmConnector
```

`SkkmConnectorBase` лежит в `SkkmConnector.java` рядом с публичным классом – так же, как
`ServerKkmBase` лежит в `SkkmConnector.ts`.

**3. Несколько классов в одном файле.** `AdminContracts.cs` содержит восемь классов.
Java допускает в файле только один публичный класс, поэтому они стали вложенными
статическими: `AdminContracts.DeviceSettingsRequest`, `AdminContracts.MarkingCodesRequest`.
Файл при этом один, как в оригинале.

**4. Числовые значения перечислений.** `BankPaymentAgent = 0` в Java записать нельзя –
константа перечисления не может нести число без поля и конструктора. Значения при этом
не сплошные: `DeviceType` идет 1..5 и 100, `MeasureOfQuantity` – 0, 10, 11, 12, 20…,
`ShiftState` – 1..3, поэтому обойтись порядковым номером тоже нельзя. Минимальная
возможная форма – публичное поле `value` и конструктор:

```java
public enum ShiftState {
    /** Смена закрыта */
    Closed(1),
    /** Смена открыта */
    Opened(2),
    /** Смена истекла (открыта более 24 часов) */
    Expired(3);

    @JsonValue
    public final int value;

    ShiftState(int value) {
        this.value = value;
    }
}
```

Метода `fromValue` больше нет: разбор числа из JSON Jackson делает по `@JsonValue`.

**5. async/await.** В Java его нет, методы синхронные. Отмена работает как в оригинале:
транспорт использует `HttpClient.sendAsync`, ссылка на вызов хранится, `kkm.Cancel()`
из другого потока прерывает запрос. `Dispose()` сохранен, `close()` вызывает его –
чтобы работал try-with-resources.

Отдельно: `JsonSerializerOptions` из `KkmTransport.cs` превратились в статические
`JsonOptions` и `BodyJsonOptions` внутри того же `KkmTransport.java`, плюс десериализатор
дат `LenientLocalDateTime` – сервер отдает и `2026-09-03T16:27:36`, и
`2026-09-07T11:30:38.03+08:00`, а `DateTime` в .NET разбирает оба варианта.

`decimal` → `BigDecimal`, `DateTime` → `LocalDateTime`, `TimeSpan` → `Duration`,
`JsonElement` → `JsonNode`, `ShiftsFrom` / `ShiftsTo` → `LocalDate` (в запрос уходит `yyyy-MM-dd`).

## Сборка

```bash
mvn clean package
```

Зависимости: `jackson-databind`, `jackson-datatype-jsr310`.

## Проверка

Проект компилируется целиком. Против живой кассы прогонялись только чтения
(`Ping`, `GetDeviceList`, `GetStatus`) – начинать стоит с них.
