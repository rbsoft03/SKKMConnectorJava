package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Режимы работы ККТ */
public class FnModes {
    /** Принтер в автомате */
    @JsonProperty("PrinterAutomatic")
    public boolean PrinterAutomatic;

    /** Автономный режим (без передачи в ОФД) */
    @JsonProperty("OfflineMode")
    public boolean OfflineMode;

    /** Признак расчетов за услуги */
    @JsonProperty("ServiceSign")
    public boolean ServiceSign;

    /** Признак формирования БСО */
    @JsonProperty("BsoSign")
    public boolean BsoSign;

    /** ККТ для расчетов только в Интернет */
    @JsonProperty("CalcOnlineSign")
    public boolean CalcOnlineSign;

    /** Шифрование данных */
    @JsonProperty("DataEncryption")
    public boolean DataEncryption;

    /** Продажа подакцизного товара */
    @JsonProperty("SaleExcisableGoods")
    public boolean SaleExcisableGoods;

    /** Признак проведения азартных игр */
    @JsonProperty("SignOfGambling")
    public boolean SignOfGambling;

    /** Признак проведения лотереи */
    @JsonProperty("SignOfLottery")
    public boolean SignOfLottery;

    /** Ломбард */
    @JsonProperty("Pawnshop")
    public boolean Pawnshop;

    /** Страхование */
    @JsonProperty("Assurance")
    public boolean Assurance;

    /** Продажа маркированного товара */
    @JsonProperty("Marking")
    public boolean Marking;

    /** Вендинговый автомат */
    @JsonProperty("VendingMachine")
    public boolean VendingMachine;

    /** Общественное питание */
    @JsonProperty("CateringServices")
    public boolean CateringServices;

    /** Оптовая торговля */
    @JsonProperty("WholesaleTrade")
    public boolean WholesaleTrade;

    /** Автоматический режим */
    @JsonProperty("AutomaticMode")
    public boolean AutomaticMode;
}
