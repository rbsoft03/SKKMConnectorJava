package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/** Состояние ККТ */
public class KktStatus {
    /** Присутствует ли фискальный накопитель. */
    @JsonProperty("IsFnPresent")
    public boolean IsFnPresent;

    /** Находится ли фискальный накопитель в состоянии ошибки. */
    @JsonProperty("IsFnError")
    public boolean IsFnError;

    /** Доступна ли информационная система маркировки. */
    @JsonProperty("IsIsmDisconnected")
    public boolean IsIsmDisconnected;

    /** Доступен ли оператор фискальных данных. */
    @JsonProperty("IsOfdDisconnected")
    public boolean IsOfdDisconnected;

    /** Предупреждения ФН */
    @JsonProperty("Warnings")
    public Warnings Warnings;

    /** Номер смены. */
    @JsonProperty("ShiftNumber")
    public int ShiftNumber;

    /** Номер фискального документа. */
    @JsonProperty("DocNumber")
    public int DocNumber;

    /** Фискальный режим. */
    @JsonProperty("IsFiscal")
    public boolean IsFiscal;

    /** Смена открыта. */
    @JsonProperty("IsShiftOpened")
    public boolean IsShiftOpened;

    /** Смена истекла. */
    @JsonProperty("IsShiftExpired")
    public boolean IsShiftExpired;

    /** Время получения данных. */
    @JsonProperty("ComputerTime")
    public LocalDateTime ComputerTime;

    /** Время в часах устройства. */
    @JsonProperty("DeviceTime")
    public LocalDateTime DeviceTime;

    /** Открыт денежный ящик. */
    @JsonProperty("IsDrawerOpened")
    public boolean IsDrawerOpened;

    /** Наличие чековой ленты. */
    @JsonProperty("IsCheckPaperPresent")
    public boolean IsCheckPaperPresent;

    /** Открыта ли крышка. */
    @JsonProperty("IsCoverOpened")
    public boolean IsCoverOpened;

    /** Аккумулятор разряжен. */
    @JsonProperty("IsBatteryLow")
    public boolean IsBatteryLow;

    /** Открытый документ. */
    @JsonProperty("IsOpenDocument")
    public boolean IsOpenDocument;

    /** Ширина чековой ленты. */
    @JsonProperty("LineLength")
    public int LineLength;
}
