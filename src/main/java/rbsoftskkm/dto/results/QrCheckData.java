package rbsoftskkm.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Данные для отображения QR-кода чека (QrCheckData). */
public class QrCheckData {
    /** Дата создания документа. */
    @JsonProperty("Date")
    public LocalDateTime Date;

    /** Сумма чека. */
    @JsonProperty("Amount")
    public BigDecimal Amount;

    /** Фискальный накопитель. */
    @JsonProperty("Fn")
    public String Fn;

    /** Фискальный документ. */
    @JsonProperty("Fd")
    public int Fd;

    /** Фискальный признак. */
    @JsonProperty("Fp")
    public String Fp;

    /** Тип операции: 1 — приход; 2 — возврат прихода; 4 — расход; 5 — возврат расхода; 7 — коррекция прихода; 9 — коррекция расхода. */
    @JsonProperty("N")
    public int N;
}
