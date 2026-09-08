package rbsoftskkm.dto.positions;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.enums.*;

/**
 * Разделительная линия в чеке:
 * LineStyle - Стиль. Используйте enum {@link LineStyle}
 */
public class SeparatorLine extends Position {
    /** Стиль разделительной линии. Используйте enum {@link LineStyle}. */
    @JsonProperty("lineStyle")
    public LineStyle LineStyle = rbsoftskkm.dto.enums.LineStyle.Solid;
}
