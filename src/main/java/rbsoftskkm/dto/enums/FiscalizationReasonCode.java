package rbsoftskkm.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Код причины перерегистрации ККТ:
 * FnReplacement - Замена ФН
 * OfdReplacement - Замена ОФД
 * RequisitesChange - Изменение реквизитов
 * SettingsChange - Изменение настроек ККТ
 */
public enum FiscalizationReasonCode {

    /** Замена ФН. */
    FnReplacement(1),

    /** Замена ОФД. */
    OfdReplacement(2),

    /** Изменение реквизитов. */
    RequisitesChange(3),

    /** Изменение настроек ККТ. */
    SettingsChange(4);

    /** Числовое значение реквизита, как в API сервера ККМ. */
    @JsonValue
    public final int value;

    FiscalizationReasonCode(int value) {
        this.value = value;
    }
}
