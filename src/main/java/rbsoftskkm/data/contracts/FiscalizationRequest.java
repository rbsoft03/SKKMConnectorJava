package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.enums.FiscalizationReasonCode;

/** Тело запроса фискализации ККТ. */
public class FiscalizationRequest extends CheckbaseParameters {
    /** Регистрационный номер ККТ. */
    @JsonProperty("RnNumber")
    public String RnNumber;

    /** Коды систем налогообложения через запятую. */
    @JsonProperty("TaxationSystems")
    public String TaxationSystems;

    /** ИНН организации. */
    @JsonProperty("Vatin")
    public String Vatin;

    /** Название организации. */
    @JsonProperty("CompanyName")
    public String CompanyName;

    /** Заводской номер ФН. */
    @JsonProperty("Fn")
    public String Fn;

    /** Версия ФФД ККТ. */
    @JsonProperty("FfdVersionKkt")
    public String FfdVersionKkt;

    /** Версия ФФД ФН. */
    @JsonProperty("FfdVersionFn")
    public String FfdVersionFn;

    /** Коды причин изменения сведений о ККТ. */
    @JsonProperty("RegistrationLabelCodes")
    public String RegistrationLabelCodes;

    /** Адрес ОФД. */
    @JsonProperty("OfdAddress")
    public String OfdAddress;

    /** Порт ОФД. */
    @JsonProperty("OfdPort")
    public Integer OfdPort;

    /** Номер автоматического устройства для расчётов. */
    @JsonProperty("AutomaticNumber")
    public String AutomaticNumber;

    /** Email отправителя чека. */
    @JsonProperty("SenderEmail")
    public String SenderEmail;

    /** Код причины перерегистрации. */
    @JsonProperty("ReasonCode")
    public FiscalizationReasonCode ReasonCode;

    /** Хост ИСМ. */
    @JsonProperty("IsmHost")
    public String IsmHost;

    /** Порт ИСМ. */
    @JsonProperty("IsmPort")
    public Integer IsmPort;

    /** Адрес сайта ФНС. */
    @JsonProperty("FnsUrl")
    public String FnsUrl;

    /** ИНН ОФД. */
    @JsonProperty("OfdVatin")
    public String OfdVatin;

    /** Название ОФД. */
    @JsonProperty("OfdName")
    public String OfdName;

    /** Коды признаков агента через запятую. */
    @JsonProperty("AgentTypes")
    public String AgentTypes;

    /** Признак формирования АС БСО. */
    @JsonProperty("IsBsoSign")
    public Boolean IsBsoSign;

    /** Признак торговли маркированными товарами. */
    @JsonProperty("IsMarking")
    public Boolean IsMarking;

    /** Признак ломбардной деятельности. */
    @JsonProperty("IsPawnshop")
    public Boolean IsPawnshop;

    /** Признак страховой деятельности. */
    @JsonProperty("IsAssurance")
    public Boolean IsAssurance;

    /** Признак автоматического режима. */
    @JsonProperty("IsAutomatic")
    public Boolean IsAutomatic;

    /** Признак применения в торговом автомате. */
    @JsonProperty("IsVending")
    public Boolean IsVending;

    /** Признак установки принтера в автомате. */
    @JsonProperty("IsAutomaticPrinter")
    public Boolean IsAutomaticPrinter;

    /** Признак расчётов только в интернете. */
    @JsonProperty("IsOnline")
    public Boolean IsOnline;

    /** Признак проведения лотерей. */
    @JsonProperty("IsLottery")
    public Boolean IsLottery;

    /** Признак проведения азартных игр. */
    @JsonProperty("IsGambling")
    public Boolean IsGambling;

    /** Признак продажи подакцизных товаров. */
    @JsonProperty("IsExcisable")
    public Boolean IsExcisable;

    /** Признак расчётов за услуги. */
    @JsonProperty("IsService")
    public Boolean IsService;

    /** Признак шифрования данных. */
    @JsonProperty("IsEncrypted")
    public Boolean IsEncrypted;

    /** Признак автономного режима. */
    @JsonProperty("IsOffline")
    public Boolean IsOffline;

    /** Признак общественного питания. */
    @JsonProperty("IsCateringServices")
    public Boolean IsCateringServices;

    /** Признак оптовой торговли. */
    @JsonProperty("IsWholesaleTrade")
    public Boolean IsWholesaleTrade;

    /** Адрес расчётов. */
    @JsonProperty("SaleAddress")
    public String SaleAddress;

    /** Место расчётов. */
    @JsonProperty("SaleLocation")
    public String SaleLocation;
}
