package rbsoftskkm.dto;

import rbsoftskkm.dto.enums.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Данные коррекции:
 * Type - Тип коррекции. Используйте enum {@link CorrectionTypes}
 * Description - Описание коррекции
 * Date - Дата совершения корректируемого расчёта
 * Number - Номер предписания налогового органа (для Type = ПоПредписанию)
 */
public class CorrectionData {
    /** Тип коррекции. Используйте enum {@link CorrectionTypes}. */
    public CorrectionTypes Type = CorrectionTypes.Самостоятельно;

    /** Описание коррекции. */
    public String Description = "";

    /** Дата совершения корректируемого расчёта. */
    public LocalDateTime Date = LocalDate.now().atStartOfDay();

    /** Номер предписания налогового органа. */
    public String Number = "";
}
