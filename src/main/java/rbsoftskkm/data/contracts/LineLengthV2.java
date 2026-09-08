package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Ширина строки чека. */
public class LineLengthV2 {
    /** Ширина строки чека в символах. */
    @JsonProperty("LineLength")
    public int LineLength;

    /** Ширина печатной области в пикселях. */
    @JsonProperty("LineLengthPixels")
    public int LineLengthPixels;
}
