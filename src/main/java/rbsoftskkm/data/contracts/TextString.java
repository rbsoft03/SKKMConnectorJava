package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Печать текстовой строки в документе. */
public class TextString {
    /** Строка с произвольным текстом */
    @JsonProperty("Text")
    public String Text;

    /** Шрифт строки: Normal, Bold, Small, Medium, Big, H1, H2, H3, H4, H5 */
    @JsonProperty("Font")
    public String Font;

    /** Выравнивание: left, right, center, width */
    @JsonProperty("Alignment")
    public String Alignment;
}
