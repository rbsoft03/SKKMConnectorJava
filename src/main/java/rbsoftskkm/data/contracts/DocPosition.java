package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.positions.BarcodeLine;
import rbsoftskkm.dto.positions.PictureLine;
import rbsoftskkm.dto.positions.SeparatorLine;

/** Строка нефискального документа */
public class DocPosition {
    /** Печать текстовой строки */
    @JsonProperty("TextString")
    public TextString TextString;

    /** Печать штрихкода */
    @JsonProperty("Barcode")
    public BarcodeLine Barcode;

    /** Печать картинки (Base64) */
    @JsonProperty("Picture")
    public PictureLine Picture;

    /** Горизонтальная разделительная линия на всю ширину чека */
    @JsonProperty("SeparatorLine")
    public SeparatorLine SeparatorLine;
}
