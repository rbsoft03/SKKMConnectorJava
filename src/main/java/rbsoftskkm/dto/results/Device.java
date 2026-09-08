package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Описание ККМ */
public class Device {
    /** Часовая зона */
    @JsonProperty("TimeZone")
    public int TimeZone;

    /** Фискальный режим. */
    @JsonProperty("IsFiscal")
    public boolean IsFiscal;

    /** Ширина чековой ленты. */
    @JsonProperty("LineLength")
    public int LineLength;

    /** Ширина чековой ленты в пикселях. */
    @JsonProperty("LineLengthPixels")
    public int LineLengthPixels;

    /** Версия ФФД. */
    @JsonProperty("FfdVersion")
    public String FfdVersion;

    /** Версия ФФД ФН. */
    @JsonProperty("FnFfdVersion")
    public String FnFfdVersion;

    /** Тип устройства */
    @JsonProperty("DeviceClass")
    public int DeviceClass;

    /** Название модели. */
    @JsonProperty("Model")
    public String Model;

    /** Заводской номер ККТ. */
    @JsonProperty("SerialNumber")
    public String SerialNumber;

    /** Версия прошивки. */
    @JsonProperty("FirmwareVersion")
    public String FirmwareVersion;

    /** Версия конфигурации прошивки устройства. */
    @JsonProperty("ConfigurationVersion")
    public String ConfigurationVersion;

    /** Массив лицензий ККТ. */
    @JsonProperty("KktLicenses")
    public KktLicense[] KktLicenses;
}
