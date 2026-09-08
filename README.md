# SKKM Connector Java

Библиотека `skkm-connector` — программная обертка над REST API **Сервера ККМ** для приложений на Java (JDK 21).

Позволяет добавить печать чеков и управление ККТ в любое Java-приложение без ручной сборки HTTP-запросов и JSON. Работа строится вокруг одного класса `SkkmConnector`: заполняете поля, вызываете метод, читаете результат из полей.

## Содержание

- [Возможности](#1-возможности)
- [Требования](#2-требования)
- [Структура библиотеки](#3-структура-библиотеки)
- [Варианты интеграции](#4-варианты-интеграции)
- [Пример работы с ККТ](#5-пример-работы-с-ккт)
- [Полный API](#полный-api)

# 1. Возможности

- **Полное покрытие REST API Сервера ККМ 4.0** — печать чеков, возвраты и коррекции, открытие и закрытие смены, X/Z-отчёты и отчёт о расчётах, внесение и выемка наличных, слипы, картинки, маркировка, фискализация, очередь печати, шаблоны, операции, а также администрирование (пользователи, настройки службы, пулы, добавление и изменение ККТ).
- **Простой доступ** — один объект `SkkmConnector`: заполняете поля, вызываете метод, читаете результат из полей — без ручной сборки HTTP-запросов.
- **Синхронный и асинхронный режим** — ждать ответ на кассе или поставить задание в очередь и опросить статус позже.
- **Состояние касс** — список устройств с сервера, сведения о ККТ, статус смены, счётчики, остаток наличных.

# 2. Требования

- **Платформа:** JDK 21;
- **Зависимости:** `jackson-databind`, `jackson-datatype-jsr310`;
- **Доступный Сервер ККМ 4.0** — доступная служба печати (по умолчанию TCP-порт `4398`); если на сервере выключен анонимный доступ — токен авторизации пользователя.

# 3. Структура библиотеки

```
src/main/java/rbsoftskkm/
├── core/   — класс SkkmConnector: подключение, методы API, состояние, разбор ответов
├── dto/    — публичные типы: позиции чека, оплаты, покупатель, перечисления, результаты
└── data/   — внутренний HTTP-транспорт и контракты обмена с сервером
```

## 3.1 Поля `SkkmConnector`

### 3.1.1 Подключение

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Host` | `String` | Хост сервера ККМ (IP или DNS). По умолчанию `localhost`. |
| `Port` | `int` | Порт службы печати. По умолчанию `4398`. |
| `UseHttps` | `boolean` | Обращаться по HTTPS вместо HTTP. |
| `Timeout` | `Duration` | Таймаут запроса. По умолчанию 60 с. |
| `Token` | `String` | Токен авторизации (заголовок `api_key`). Нужен, если анонимный доступ выключен. |
| `AuthUserName` | `String` | Логин для `GetUserToken()` (Basic Auth). По умолчанию `Admin`. |
| `AuthPassword` | `String` | Пароль для `GetUserToken()`. По умолчанию `Admin`. |
| `TerminalId` | `String` | Идентификатор рабочего места (заголовок `TerminalId`). |
| `DeviceName` | `String` | Имя кассы на сервере ККМ. |
| `Cashier` | `Cashier` | Кассир для документов. |
| `PoolName` | `String` | Имя пула устройств. |
| `QueueTaskId` | `String` | Id задания в очереди печати. |
| `PictureId` | `String` | Имя картинки / шаблона. |
| `TemplateName` | `String` | Имя шаблона печати или чека. |
| `UserId` | `String` | Идентификатор пользователя сервера ККМ. |
| `FnNumber` | `String` | Номер ФН (копия чека по данным ФН). |
| `ReportType` | `int` | Тип отчёта для списка Z-отчётов. |
| `MarkingCodes` | `List<String>` | Коды маркировки для проверки (`VerifyMarking` и аналоги). |
| `DeviceSettings` | `DeviceSettings` | Настройки кассы для добавления / изменения. |
| `ServiceSettings` | `ServiceSettings` | Настройки службы печати. |
| `ServiceUser` | `ServiceUser` | Пользователь сервера ККМ. |
| `TemplateParameters` | `TemplateParameters` | Параметры шаблона печати. |
| `CheckTemplateParameters` | `CheckTemplateParameters` | Параметры шаблона чека. |
| `FiscalizationParameters` | `FiscalizationParameters` | Параметры фискализации / перерегистрации. |
| `ShiftsFrom` / `ShiftsTo` | `LocalDate` | Период для списков отчётов, чеков и операций (по умолчанию последние 7 дней). |

Хост, порт, токен и имя кассы можно менять между вызовами — один экземпляр по очереди работает с разными ККТ.
Прервать текущий запрос: `kkm.Cancel()`.

### 3.1.2 Входные поля операций

Перед новым чеком вызывайте `NewRequest()` — очистятся позиции, оплаты, покупатель, коррекция и результаты прошлого вызова. Подключение и кассир останутся.

| Свойство | Тип | Описание |
| --- | --- | --- |
| `PaymentType` | `CheckType` | Тип чека / задания:<br>Text - Текст<br>Sale - Продажа (приход)<br>SaleReturn - Возврат (возврат прихода)<br>Purchase - Покупка (расход)<br>PurchaseReturn - Возврат покупки (возврат расхода)<br>CorrectionSale - Чек коррекции прихода<br>CorrectionSaleReturn - Чек коррекции возврата прихода<br>CorrectionPurchase - Чек коррекции расхода<br>CorrectionPurchaseReturn - Чек коррекции возврата расхода<br>Slip - Слип<br>Fiscalization - Фискализация<br>OpenShift - Открытие смены<br>CloseShift - Z-отчёт<br>ReportX - X-отчёт<br>ReportSettlement - Отчёт о состоянии расчётов<br>CashOut - Выемка<br>CashIn - Внесение<br>OpenCashDrawer - Открытие денежного ящика<br>CopyFromFn - Копия из ФН<br>DocumentCopy - Дубликат документа |
| `TaxVariant` | `TaxSystem` | Система налогообложения (СНО):<br>ОСН - Общий<br>УСН - Упрощенная Доход<br>УСНД_Р - Упрощенная Доход минус Расход<br>ЕНВД - Единый налог на вмененный доход<br>ЕСН - Единый сельскохозяйственный налог<br>ПСН - Патентная система налогообложения |
| `Electronically` | `boolean` | Электронный чек (без печати на бумаге). |
| `TextBefore` | `String` | Текст в шапке (до товарной части). |
| `TextAfter` | `String` | Текст в подвале (после товарной части). |
| `SaleLocation` | `String` | Место расчётов. |
| `SaleAddress` | `String` | Адрес расчётов. |
| `SenderEmail` | `String` | Email отправителя чека. |
| `OperationOnline` | `boolean` | Расчёт в безналичном порядке в сети «Интернет». |
| `AdditionalAttribute` | `String` | Дополнительный реквизит чека (тег 1192). Рекомендуется указывать ФП корректируемого чека при возврате/коррекции. |
| `TimeZone` | `CheckTimeZone` | Часовая зона:<br>Auto - Авто (из настроек ККТ)<br>MskMinus1 - 1-я часовая зона (МСК−1, UTC+2)<br>Msk - 2-я часовая зона (МСК, UTC+3)<br>MskPlus1 - 3-я часовая зона (МСК+1, UTC+4)<br>MskPlus2 - 4-я часовая зона (МСК+2, UTC+5)<br>MskPlus3 - 5-я часовая зона (МСК+3, UTC+6)<br>MskPlus4 - 6-я часовая зона (МСК+4, UTC+7)<br>MskPlus5 - 7-я часовая зона (МСК+5, UTC+8).<br>MskPlus6 - 8-я часовая зона (МСК+6, UTC+9)<br>MskPlus7 -  9-я часовая зона (МСК+7, UTC+10)<br>MskPlus8 - 10-я часовая зона (МСК+8, UTC+11)<br>MskPlus9 - 11-я часовая зона (МСК+9, UTC+12) |
| `IndustryAttribute` | `Industry` | Отраслевой реквизит чека. Поля: IdentifierFoiv, DocumentDate, DocumentNumber, AttributeValue |
| `UserAttribute` | `UserAttribute` | Дополнительный реквизит пользователя. Поля: Name, Value. |
| `OperationalAttribute` | `OperationalAttribute` | Операционный реквизит чека. Поля: `DateTime`, `OperationId`, `OperationData`. |
| `AgentSign` | `AgentType` | Признак агента:<br>BankPaymentAgent - Банковский платежный агент<br>BankPaymentSubagent - Банковский платежный субагент<br>PaymentAgent - Платежный агент<br>PaymentSubagent - Платёжный субагент<br>Attorney - Поверенный<br>Commissioner - Комиссионер<br>Agent - Агент (иной тип). |
| `Agent` | `Agent` | Данные агента. |
| `Vendor` | `Vendor` | Данные поставщика. |
| `Customer` | `Customer` | Сведения о покупателе. |
| `Payments` | `Payments` | Оплаты. |
| `Positions` | `List<Position>` | Позиции чека (`FiscalLine`, `TextLine`, `BarcodeLine`, `PictureLine`, `SeparatorLine`). |
| `ElectronicPayments` | `List<ElectronicPayment>` | Детализация безналичных оплат. |
| `CorrectionData` | `CorrectionData` | Данные коррекции. |
| `Correction105Taxes` | `Correction105Taxes` | Суммы НДС по ставкам для коррекции ФФД 1.0.5. |
| `CashAmount` | `BigDecimal` | Сумма внесения / выемки. |
| `TextForPrint` | `String` | Текст нефискального документа (слип). |
| `PictureName` | `String` | Имя картинки на сервере. |
| `PictureBase64` | `String` | Картинка в Base64. |
| `PictureAlignment` | `PictureAlignment` | Выравнивание: <br>1 - слева; <br>2 - центр; <br>3 - справа. |
| `DocumentId` | `String` | Идентификатор документа (`docId`). |
| `FiscalSign` | `String` | Фискальный признак документа. |
| `ShiftNumber` | `int` | Номер смены (вход/выход в зависимости от метода). |
| `CheckNumber` | `int` | Номер фискального документа. |
| `MarkingCode` | `String` | Код маркировки в Base64. |
| `PlannedStatus` | `MarkingPlannedStatus` | Планируемый статус (таблица 105 ФФД): NotSpecified - Не задан (значение по умолчанию в запросе)<br>Sold - Реализован<br>InSale - Мерный товар в стадии реализации<br>Returned - Возвращён<br>PartiallyReturned - Часть товара возвращена<br>Unchanged - Статус не изменился |
| `MarkingQuantity` | `BigDecimal` | Количество для проверки КМ. |
| `MeasureOfQuantity` | `MeasureOfQuantity` | Мера количества предмета расчёта:<br>Piece - Штука или единица (поштучная реализация)<br>Gram - Грамм<br>Kilogram - Килограмм<br>Tonne - Тонна<br>Centimeter - Сантиметр<br>Decimeter - Дециметр<br>Meter - Метр<br>SquareCentimeter - Квадратный сантиметр<br>SquareDecimeter - Квадратный дециметр<br>SquareMeter - Квадратный метр<br>Milliliter - Миллилитр<br>Liter - Литр<br>CubicMeter - Кубический метр<br>KilowattHour - Киловатт-час<br>Gigacalorie - Гигакалория<br>Day - Сутки<br>Hour - Час<br>Minute - Минута<br>Second - Секунда<br>Kilobyte - Килобайт<br>Megabyte - Мегабайт<br>Gigabyte - Гигабайт<br>Terabyte - Терабайт<br>Other - Иная единица измерения |
| `FractionalQuantityNumerator` | `int` | Числитель дробного количества. |
| `FractionalQuantityDenominator` | `int` | Знаменатель дробного количества (не должен быть 0, если дробь применяется). |
| `NotSendToServer` | `boolean` | Не отправлять результат проверки на сервер ОИСМ. |
| `WaitForResult` | `boolean` | Ждать ответ ОИСМ. |
| `RequestKmGuid` | `String` | Уникальный код запроса КМ. |
| `ConfirmationType` | `KmConfirmationType` | Признак подтверждения кода маркировки:<br>Included - Код маркировки включён в документ реализации<br>NotIncluded - Код маркировки не включён в документ реализации |
| `IsProcessed` | `boolean` | Только обработанные операции (параметр `isProcessed` в `GetOperationLast`). |

### 3.1.3 Поля-результаты

После каждого вызова коннектор сам разбирает ответ сервера: в `Ok` / `ErrorCode` / `ErrorDescription` попадает статус, а содержимое `Result` — в поля объекта (в том числе плоские поля ниже после фискальных операций).

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Ok` | `boolean` | Успех последнего вызова. |
| `ErrorCode` | `int` | Код ошибки сервера. `0` — нет ошибки. |
| `ErrorDescription` | `String` | Текст ошибки. |
| `LastResult` | `JsonNode` | Поле `Result` последнего ответа. |
| `FiscalResult` | `FiscalResult` | Фискальный блок ответа (ФП, номер смены/документа и т.д.). |
| `DocumentId` | `String` | Идентификатор документа (`docId`) из ответа. |
| `FiscalSign` | `String` | Фискальный признак документа. |
| `ShiftNumber` | `int` | Номер смены. |
| `CheckNumber` | `int` | Номер фискального документа (ФД). |
| `CheckNumberInShift` | `int` | Номер чека за смену. |
| `RnNumber` | `String` | Регистрационный номер ККТ (РНМ). |
| `FnNumber` | `String` | Номер ФН. |
| `FnsUrl` | `String` | Адрес сайта ФНС. |
| `ServerDateTime` | `String` | Время на сервере ККМ. |
| `FiscalDateTime` | `String` | Дата и время документа по часам ФН. |
| `DeviceDateTime` | `String` | Время ККТ. |
| `CurrentShiftState` | `ShiftState` | Состояние смены: Closed / Opened / Expired. |
| `CashBalance` | `BigDecimal` | Остаток наличных. |
| `BacklogDocumentsCount` | `long` | Количество непереданных в ОФД документов. |
| `BacklogFirstDocumentNumber` | `long` | Номер первого непереданного документа. |
| `BacklogFirstDocumentDateTime` | `LocalDateTime` | Дата и время первого непереданного документа. |
| `FnValidityDate` | `String` | Срок действия ФН. |
| `FnDaysResources` | `int` | Остаток ресурса ФН в днях. |
| `IsFnPresent` | `boolean` | ФН присутствует. |
| `IsFiscal` | `boolean` | Фискальный режим. |
| `FnWarnings` | `Warnings` | Предупреждения ФН из ответа. |
| `Devices` | `DeviceListResponse[]` | Список устройств. |
| `Kkt` | `DataKkt` | Подробные данные кассы после `Connect()`. |
| `Status` | `KktStatus` | Расширенный статус ККТ. |
| `ShiftStatus` | `ResponseCurrentStatus` | Краткий статус смены. |
| `ShiftTotals` | `ResShiftTotal` | Итоги смены. |
| `LineLength` | `int` | Ширина строки чека в символах. |
| `LineLengthPixels` | `int` | Ширина печатной области в пикселях. |
| `NonZeroSum` | `BigDecimal` | Необнуляемая сумма. |
| `ServerVersion` | `String` | Версия сервера. |
| `MarkingCheck` | `RequestKmResult` | Результат локальной проверки КМ. |
| `MarkingProcessing` | `ProcessingKmResult` | Результат проверки КМ в ОИСМ. |
| `Check` | `CheckDocument` | Документ (чек) по идентификатору. |
| `Checks` | `CheckDocument[]` | Список документов. |
| `TaskStatus` | `ResponseTaskStatus` | Статус асинхронного задания. |
| `PrintForm` | `PrintFormLine[]` | Печатная форма. |
| `Pictures` | `List<Picture>` | Список картинок. |
| `Shifts` | `ShiftListItem[]` | Список отчётов / смен. |
| `Queue` | `QueueItem[]` | Очередь печати. |
| `QueueTask` | `QueueTaskState` | Состояние задания очереди. |
| `Operation` | `DeviceTaskInfo` | Операция по идентификатору документа. |
| `Operations` | `OperationListItem[]` | Список операций за период. |
| `RelatedOperations` | `DeviceTaskInfo[]` | Связанные операции. |
| `OperationHistory` | `OperationHistoryItem[]` | История выполнения операции. |
| `OperationTlv` | `String` | TLV операции. |
| `OperationKm` | `OperationKmRow[]` | Коды маркировки операции. |
| `UserToken` | `UserToken` | Токен пользователя (`GetUserToken`). |
| `Users` | `ServiceUser[]` | Список пользователей. |
| `ServiceSettingsResult` | `ServiceSettings` | Настройки службы после чтения. |
| `Pools` | `String[]` | Список пулов. |
| `PrintTemplate` | `PrintTemplate` | Шаблон печати. |
| `Templates` | `PrintTemplate[]` | Список шаблонов печати. |
| `CheckTemplate` | `CheckTemplate` | Шаблон чека. |
| `CheckTemplates` | `CheckTemplateListItem[]` | Список шаблонов чека. |
| `FiscalizationDocument` | `FiscalizationDocument` | Документ фискализации. |
| `Fiscalizations` | `FiscalizationDocument[]` | Список фискализаций. |
| `MarkingVerify` | `MarkingVerifyResult` | Результат проверки маркировки. |
| `PictureBase64Result` | `String` | Картинка в Base64 (результат чтения). |

Ограничения:

- объект не потокобезопасен — на параллельную работу создавайте отдельные экземпляры;
- коннектор не валидирует состав чека — неверные данные отвергнет сервер;
- после `Dispose()` экземпляр использовать нельзя.

## 3.2 Типы данных

### 3.2.1 Позиции чека (`Positions`)

#### `FiscalLine` — фискальная строка

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Name` | `String` | Наименование позиции. |
| `ProductCode` | `String` | Код товара. |
| `Quantity` | `BigDecimal` | Количество. По умолчанию `1`. |
| `Price` | `BigDecimal` | Цена с учётом скидки |
| `Sum` | `BigDecimal` | Сумма с учётом скидки |
| `DiscountSum` | `BigDecimal` | Сумма скидки / надбавки. |
| `Tax` | `String` | Ставка НДС. Обязательна. |
| `TaxSum` | `BigDecimal` | Сумма НДС. |
| `Department` | `int` | Отдел / секция. |
| `SignMethodCalculation` | `SignMethodCalculation` | Признак способа расчёта: <br>NotApplicable - Не применяется;<br>FullPrepayment - Предоплата полная;<br>PartialPrepayment - Предоплата; частичная;<br>Advance - Аванс;<br>FullPayment - Полная оплата;<br>PartialPaymentAndCredit - Частичная оплата и кредит;<br>CreditTransfer - Передача в кредит;<br>CreditPayment - Оплата кредита |
| `SignCalculationObject` | `SignCalculationObject` | Признак предмета расчёта:<br>NotApplicable - Не применяется;<br>Goods - Товар;<br>ExcisableGoods - Подакцизный товар;<br>Work - Работа;<br>Service - Услуга;<br>GamblingStake - Ставка (азартные игры);<br>GamblingPrize - Выигрыш (азартные игры);<br>LotteryTicket - Лотерейный билет или ставка;<br>LotteryPrize - Выигрыш в лотерее;<br>IntellectualProperty - Право на использование РИД или средств индивидуализации;<br>Advance - Аванс, задаток, предоплата и аналогичные предметы расчёта;<br>AgentFee - Агентское вознаграждение;<br>Payout - Выплата;<br>Other - Иной предмет расчёта;<br>PropertyRight - Имущественное право;<br>NonOperatingIncome - Внереализационный доход;<br>OtherPayments - Иные платежи и взносы;<br>TradeFee - Торговый сбор;<br>TouristTax - Туристический налог;<br>Deposit - Залог;<br>Expense - Расход;<br>PensionContributionIp - Взносы на ОПС ИП;<br>PensionContribution - Взносы на ОПС;<br>MedicalContributionIp - Взносы на ОМС ИП;<br>MedicalContribution - Взносы на ОМС;<br>SocialContribution - Взносы на ОСС;<br>CasinoPayment - Платёж казино;<br>CashWithdrawalByAgent - Выдача денежных средств банковским платёжным агентом;<br>АТНМ - Подакцизный товар с маркировкой без кода (АТНМ);<br>АТМ - Подакцизный товар с маркировкой с кодом (АТМ);<br>ТНМ - Товар с маркировкой без кода, не подакцизный (ТНМ);<br>ТМ - Товар с маркировкой с кодом, не подакцизный (ТМ) |
| `MeasurementUnit` | `String` | Единица измерения. |
| `MeasureOfQuantity` | `MeasureOfQuantity` | Мера количества предмета расчёта:<br>Piece - Штука или единица;<br>Gram - Грамм;<br>Kilogram - Килограмм;<br>Tonne - Тонна;<br>Centimeter - Сантиметр;<br>Decimeter - Дециметр;<br>Meter - Метр;<br>SquareCentimeter - Квадратный сантиметр;<br>SquareDecimeter - Квадратный дециметр;<br>SquareMeter - Квадратный метр;<br>Milliliter - Миллилитр;<br>Liter - Литр;<br>CubicMeter - Кубический метр;<br>KilowattHour - Киловатт-час;<br>Gigacalorie - Гигакалория;<br>Day - Сутки;<br>Hour - Час;<br>Minute - Минута;<br>Second - Секунда;<br>Kilobyte - Килобайт;<br>Megabyte - Мегабайт;<br>Gigabyte - Гигабайт;<br>Terabyte - Терабайт;<br>Other - Иная единица измерения |
| `ExciseAmount` | `BigDecimal` | Сумма акциза. |
| `CountryOfOrigin` | `String` | Код страны происхождения. |
| `CustomsDeclaration` | `String` | Номер таможенной декларации. |
| `AgentSign` | `AgentType` | Признак агента по предмету расчёта.<br>BankPaymentAgent - Банковский платежный агент;<br>BankPaymentSubagent - Банковский платежный субагент;<br>PaymentAgent - Платежный агент;<br>PaymentSubagent - Платёжный субагент;<br>Attorney - Поверенный;<br>Commissioner - Комиссионер;<br>Agent - Агент (иной тип) |
| `Agent` | `Agent` | Данные агента. |
| `Vendor` | `Vendor` | Данные поставщика. |
| `Marking` | `Marking` | Код товарной номенклатуры. |
| `MarkingCode` | `String` | Код контрольной марки. |
| `Fractional` | `FractionalQuantity` | Дробное количество. |
| `Industry` | `Industry` | Отраслевой реквизит позиции. |
| `AdditionalAttribute` | `String` | Дополнительный реквизит предмета расчёта. |

#### `TextLine` — текстовая строка

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Text` | `String` | Текст строк |
| `Font` | `String` | Шрифт: <br>normal - обычный; <br>bold - жирный;<br>small - мелкий; <br>medium - средний; <br>big - крупный; <br>H1 - основной заголовок;<br>H2 - заголовок раздела<br>H3 - заголовок подраздела;<br>H4 - заголовок подподраздела;<br>H5 - заголовок вложенного подраздела |
| `Alignment` | `String` | Выравнивание: <br>left - по левому краю; <br>center - по центру; <br>right - по правому краю; <br>width - на всю шир |

#### `BarcodeLine` - штрихкод

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Type` | `String` | Тип штрихкода: <br>QR; <br>EAN13; <br>EAN8; <br>CODE39; <br>CODE93; <br>CODE128; <br>UPCA; <br>UPCE; <br>ITF; <br>CODABAR; <br>PDF417;<br>CODE32. |
| `Barcode` | `String` | Значение штрихкода |
| `ValueBase64` | `String` | Значение штрихкода в Base64 (если передаёте закодированную строку). |
| `Alignment` | `String` | Выравнивание: <br>left; <br>center;<br>right;<br>width |

#### `PictureLine` — картинка в чеке

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Value` | `String` | Изображение в Base64. |
| `Alignment` | `PictureAlignment` | Выравнивание: <br>Left - слева; <br>Center - по центру; <br>Right - справа. <br>По умолчанию Center. |
| `Width` | `Integer` | Ширина изображения (при необходимости). |
| `Height` | `Integer` | Высота изображения (при необходимости). |

#### `SeparatorLine` — разделительная линия

| Свойство | Тип | Описание |
| --- | --- | --- |
| `LineStyle` | `LineStyle` | Стиль линии:<br>Solid — сплошная (по умолчанию);<br>Bold — жирная;<br>Dashed — штриховая;<br>Dotted — пунктирная;<br>Double — двойная. |

### 3.2.2 `Payments` - Оплаты

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Cash` | `BigDecimal` | Наличная оплата. |
| `ElectronicPayment` | `BigDecimal` | Безналичная оплата. |
| `AdvancePayment` | `BigDecimal` | Предоплата (зачёт аванса). |
| `Credit` | `BigDecimal` | Постоплата (в кредит). |
| `CashProvision` | `BigDecimal` | Встречное предоставление (бартер). |

### 3.2.3 `Customer` - Покупатель

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Info` | `String` | Наименование организации или ФИО. |
| `Vatin` | `String` | ИНН покупателя. |
| `Email` | `String` | Email. |
| `Phone` | `String` | Телефон. |
| `DateOfBirth` | `String` | Дата рождения (`DD.MM.YYYY`). |
| `Citizenship` | `String` | Код страны (ОКСМ). |
| `DocumentTypeCode` | `String` | Код вида документа (таблица 116 ФФД). |
| `DocumentData` | `String` | Данные документа, удостоверяющего личность. |
| `Address` | `String` | Адрес покупателя. |

### 3.2.4 `Cashier` - Кассир

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Name` | `String` | ФИО кассира. |
| `Vatin` | `String` | ИНН кассира. |

### 3.2.5 `CorrectionData` - Коррекция

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Type` | `CorrectionTypes` | Самостоятельно - Самостоятельно<br>ПоПредписанию - По предписанию налогового органа |
| `Description` | `String` | Описание (основание) коррекции. |
| `Date` | `LocalDateTime` | Дата совершения корректируемого расчёта. |
| `Number` | `String` | Номер предписания налогового органа. При самостоятельной коррекции можно указать `"0"`. |

### 3.2.6 `Agent` - Агент

| Свойство | Тип | Описание |
| --- | --- | --- |
| `PayingAgentOperation` | `String` | Операция платёжного агента (тег 1044). |
| `PayingAgentPhone` | `String[]` | Телефон платёжного агента (тег 1073). |
| `ReceivePaymentsOperatorPhone` | `String[]` | Телефон оператора по приёму платежей (тег 1074). |
| `MoneyTransferOperatorPhone` | `String[]` | Телефон оператора перевода (тег 1075). |
| `MoneyTransferOperatorName` | `String` | Наименование оператора перевода. |
| `MoneyTransferOperatorAddress` | `String` | Адрес оператора перевода (тег 1005). |
| `MoneyTransferOperatorVatin` | `String` | ИНН оператора перевода. |

### 3.2.7 `Vendor` - Поставщик

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Name` | `String` | Наименование поставщика. |
| `Phones` | `String[]` | Телефоны поставщика. |
| `Vatin` | `String` | ИНН поставщика. |

### 3.2.8 `Industry` - Отраслевой реквизит

| Свойство | Тип | Описание |
| --- | --- | --- |
| `IdentifierFoiv` | `String` | Идентификатор ФОИВ (тег 1262). |
| `DocumentDate` | `String` | Дата документа-основания (`DD.MM.YYYY`). |
| `DocumentNumber` | `String` | Номер документа-основания. |
| `AttributeValue` | `String` | Значение отраслевого реквизита. |

### 3.2.9 `UserAttribute` - Пользовательский реквизит

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Name` | `String` | Имя реквизита. |
| `Value` | `String` | Значение реквизита. |

### 3.2.10 `OperationalAttribute` - Операционный реквизит

| Свойство | Тип | Описание |
| --- | --- | --- |
| `DateTime` | `String` | Дата и время операции. |
| `OperationId` | `Integer` | Идентификатор операции. |
| `OperationData` | `String` | Данные операции. |

### 3.2.11 `FractionalQuantity` - Дробное количество предмета расчёта

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Numerator` | `int` | Числитель. |
| `Denominator` | `int` | Знаменатель (не должен быть 0). |

### 3.2.12 `ElectronicPayment` - Безналичная оплата

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Amount` | `BigDecimal` | Сумма оплаты безналичными. |
| `PaymentMethod` | `ElectronicPaymentMethod` | Признак способа оплаты:<br>FullPrepayment - Предоплата 100%;<br>PartialPrepayment - Предоплата;<br>Advance - Аванс;<br>FullPayment - Полный расчёт;<br>PartialPaymentAndCredit - Частичный расчёт и кредит;<br>CreditTransfer - Передача в кредит;<br>CreditPayment - Оплата кредита |
| `Identifiers` | `String` | Идентификаторы безналичной оплаты. |
| `AdditionalInformation` | `String` | Дополнительные сведения. |

### 3.2.13 `Marking` - Позиции маркировки

| Свойство | Тип | Описание |
| --- | --- | --- |
| `Gtin` | `String` | GTIN |
| `StampType` | `String` | Тип маркировки |
| `Stamp` | `String` | Контрольный идентификационный знак (КИЗ). |
| `SerialNumber` | `String` | Серийный номер. |
| `Code` | `String` | Код контрольной марки в Base64 |
| `Barcode` | `String` | Штрихкод. |
| `CommodityGroup` | `String` | Тип (группа) товара. |
| `NotIdentified` | `String` | Код товара, формат которого не идентифицирован (Base64). |
| `Ean8` | `String` | Код товара в формате EAN-8 (Base64) |
| `Ean13` | `String` | Код товара в формате EAN-13 (Base64) |
| `Itf14` | `String` | Код товара в формате ITF-14 (Base64) |
| `Gs10` | `String` | Код GS1 на товаре, не подлежащем маркировке средствами идентификации (Base64) |
| `Gs1m` | `String` | Код GS1 на товаре, подлежащем маркировке средствами идентификации (Base64) |
| `Kmk` | `String` | Короткий код маркировки на товаре, подлежащем маркировке (Base64) |
| `Mi` | `String` | Контрольно-идентификационный знак мехового изделия |
| `Egais20` | `String` | Код товара в формате ЕГАИС-2.0 |
| `Egais30` | `String` | Код товара в формате ЕГАИС-3.0 |
| `F1-F6` | `String` | Код товара в формате Ф.1-Ф.6 (Base64) |

### 3.2.14 `Correction105Taxes` - Ставки НДС для коррекции ФФД 1.0.5

| Свойство | Тип | Описание |
| --- | --- | --- |
| `SumTax0` | `BigDecimal` | Сумма расчёта по ставке НДС 0%. |
| `SumTax5` | `BigDecimal` | Сумма НДС по ставке 5%. |
| `SumTax7` | `BigDecimal` | Сумма НДС по ставке 7%. |
| `SumTax10` | `BigDecimal` | Сумма НДС по ставке 10%. |
| `SumTax18` | `BigDecimal` | Сумма НДС по ставке 18%. |
| `SumTax20` | `BigDecimal` | Сумма НДС по ставке 20%. |
| `SumTax22` | `BigDecimal` | Сумма НДС по ставке 22%. |
| `SumTaxNone` | `BigDecimal` | Сумма расчёта без НДС. |
| `SumTax105` | `BigDecimal` | Сумма НДС по расчётной ставке 5/105. |
| `SumTax107` | `BigDecimal` | Сумма НДС по расчётной ставке 7/107. |
| `SumTax110` | `BigDecimal` | Сумма НДС по расчётной ставке 10/110. |
| `SumTax118` | `BigDecimal` | Сумма НДС по расчётной ставке 18/118. |
| `SumTax120` | `BigDecimal` | Сумма НДС по расчётной ставке 20/120. |
| `SumTax122` | `BigDecimal` | Сумма НДС по расчётной ставке 22/122. |

# 4. Варианты интеграции

Коннектор подключают двумя путями. Код использования везде один: `import rbsoftskkm.core.SkkmConnector;` и класс `SkkmConnector`.

## 4.1. Локальный Maven-артефакт

Обычный способ поставки клиенту. Библиотека ставится в локальный репозиторий Maven (`~/.m2/repository`), после чего доступна всем проектам на машине.

```bash
mvn clean install
```

В файле проекта появится:

```xml
<dependency>
    <groupId>ru.rbsoft</groupId>
    <artifactId>skkm-connector</artifactId>
    <version>1.0.0</version>
</dependency>
```

Если есть только собранный `.jar` без исходников:

```bash
mvn install:install-file -Dfile=skkm-connector-1.0.0.jar -DgroupId=ru.rbsoft -DartifactId=skkm-connector -Dversion=1.0.0 -Dpackaging=jar
```

Либо положите `.jar` в папку рядом с приложением и объявите её репозиторием:

```xml
<repositories>
    <repository>
        <id>local-skkm</id>
        <url>file://${project.basedir}/libs</url>
    </repository>
</repositories>
```

Собрать jar из исходников:

```bash
mvn clean package
```

Файл появится в `target/skkm-connector-1.0.0.jar`.

## 4.2. Ссылка на проект

Удобно, когда коннектор лежит рядом и правится вместе с приложением. Родительский `pom.xml` перечисляет оба модуля:

```xml
<packaging>pom</packaging>

<modules>
    <module>SKKMConnectorJava</module>
    <module>MyApp</module>
</modules>
```

В файле проекта — та же зависимость, что и в 4.1. Maven берёт модуль из текущей сборки, поэтому `mvn install` после каждой правки коннектора не нужен.

В IntelliJ IDEA то же самое делается через File → Project Structure → Modules → Dependencies → **+** → Module Dependency.

# 5. Пример работы с ККТ

```java
import rbsoftskkm.core.SkkmConnector;

// 1. Создаём объект коннектора
SkkmConnector kkm = new SkkmConnector();

// 2. Настраиваем параметры подключения
// 2.1. Хост и порт (порт по умолчанию — 4398)
        kkm.Host = "127.0.0.1";
        kkm.Port = 4398;

// 2.2. Имя ККМ, заданное на стороне сервера ККМ
        kkm.DeviceName = "Atol";

// 2.3. Токен авторизации из профиля пользователя сервера ККМ
        kkm.Token = "a6261fa7-6675-41c6-a9f5-cee920e1d71c";

        kkm.Cashier = new Cashier();
        kkm.Cashier.Name = "Иванов А.И.";

// 3. Подключение: при успехе параметры ККМ уже в полях
        kkm.Connect();

// 4. Анализируем ответ — обработчик уже разложил Success/Code/Description и Result
if (kkm.Ok) {
        System.out.println("Длина строки: " + kkm.LineLength
            + ", место: " + kkm.SaleLocation
                    + ", ФФД: " + kkm.Kkt.Device.FfdVersion);
} else {
        System.out.println(kkm.ErrorCode + ": " + kkm.ErrorDescription);
}
```

Если подключение прошло успешно — можно вызывать методы работы с ККТ:

```java
// 5. Открываем кассовую смену
kkm.OpenShift();

if (kkm.Ok) {
        System.out.println("Смена открыта: " + kkm.ShiftNumber);
} else {
        System.out.println(kkm.ErrorCode + ": " + kkm.ErrorDescription);
}
```

Пример печати чека прихода:

```java
// 6. Новый запрос: очищает позиции, оплаты и результат прошлого вызова
kkm.NewRequest();
kkm.PaymentType = CheckType.Sale;
kkm.TaxVariant = TaxSystem.ОСН;
kkm.Electronically = false;

kkm.Customer = new Customer();
kkm.Customer.Info = "ООО «Ромашка»";
kkm.Customer.Vatin = "500100732259";
kkm.Customer.Email = "client@example.com";

// Фискальные позиции
FiscalLine line = new FiscalLine();
line.Name = "Кофе американо";
line.ProductCode = "CF-AM-001";
line.Quantity = BigDecimal.ONE;
line.Price = new BigDecimal("150");
line.Sum = new BigDecimal("150");
line.Tax = "20";
line.TaxSum = new BigDecimal("25");
line.Department = 1;
line.SignMethodCalculation = SignMethodCalculation.FullPayment;
line.SignCalculationObject = SignCalculationObject.Service;
line.MeasureOfQuantity = MeasureOfQuantity.Piece;
line.MeasurementUnit = "шт";
        kkm.Positions.add(line);

kkm.Payments = new Payments();
kkm.Payments.Cash = new BigDecimal("150");

kkm.PrintCheck();

if (kkm.Ok) {
        System.out.println("ФП: " + kkm.FiscalSign
            + ", смена: " + kkm.ShiftNumber
                    + ", docId: " + kkm.DocumentId);
} else {
        System.out.println(kkm.ErrorCode + ": " + kkm.ErrorDescription);
}
```

Экземпляр держит HTTP-соединение, поэтому по окончании работы его закрывают — `kkm.Dispose()` или try-with-resources:

```java
try (SkkmConnector kkm = new SkkmConnector()) {
kkm.Host = "127.0.0.1";
kkm.DeviceName = "Atol";
        kkm.Ping();
}
```

Остальные операции устроены так же: смена (`OpenShift`, `CloseShift`, `ReportX`), возврат и коррекция (`PrintCheck` / `PrintCheckCorrection105` / `PrintCheckCorrection120`), наличные (`CashIn`, `CashOut`), слип, картинки, маркировка.

## Полный API

Поля, методы и перечисления коннектора повторяют REST API Сервера ККМ.

- **Справочник по коннектору:** [API.md](API.md) — методы, эндпоинты, входные параметры, перечисления и поля-результаты.
- **Встроенная документация сервера:** в интерфейсе Сервера ККМ — «Помощь» → «REST API».

Подсказки по каждому полю и методу доступны из javadoc, поставляемого вместе с библиотекой.