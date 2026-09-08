package rbsoftskkm.data;

import rbsoftskkm.dto.positions.BarcodeLine;
import rbsoftskkm.dto.enums.BarcodeType;
import rbsoftskkm.dto.enums.LineStyle;
import rbsoftskkm.dto.enums.PrintAlignment;
import rbsoftskkm.dto.enums.PrintFont;
import rbsoftskkm.dto.positions.SeparatorLine;

import rbsoftskkm.data.contracts.DocPosition;
import rbsoftskkm.data.contracts.TextString;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Разбор текста с разметкой в строки нефискального документа.
 * Префиксы: [big], [center], [QR], [line], [line,dotted], [dotted].
 */
public final class SlipTextParser {

    private SlipTextParser() {
    }

    public static DocPosition[] Parse(String text) {
        List<DocPosition> positions = new ArrayList<>();
        for (String rawLine : (text == null ? "" : text).replace("\r\n", "\n").split("\n", -1)) {
            positions.add(ParseLine(rawLine));
        }
        return positions.toArray(new DocPosition[0]);
    }

    /**
     * Одна строка: префиксы в тексте превращаются в SeparatorLine / Barcode / TextString.
     */
    public static DocPosition ParseLine(String line) {
        return ParseLine(line, null, null);
    }

    /**
     * Одна строка: префиксы в тексте превращаются в SeparatorLine / Barcode / TextString.
     */
    public static DocPosition ParseLine(String line, String font, String alignment) {
        PrintAlignment parsedAlignment = parseEnum(PrintAlignment.class, alignment);
        PrintFont parsedFont = parseEnum(PrintFont.class, font);
        BarcodeType barcodeType = null;
        LineStyle lineStyle = null;
        boolean hasLineTag = false;

        if (line.startsWith("[") && line.indexOf(']') >= 0) {
            int close = line.indexOf(']');
            String[] tags = line.substring(1, close).split(",");
            boolean recognized = false;

            for (String raw : tags) {
                if ("line".equalsIgnoreCase(raw.trim())) {
                    hasLineTag = true;
                    recognized = true;
                }
            }

            for (String raw : tags) {
                String tag = raw.trim();
                if (tag.isEmpty() || Character.isDigit(tag.charAt(0))) {
                    continue;
                }
                LineStyle parsedLineStyle = tryLineStyle(tag, hasLineTag);
                BarcodeType parsedBarcode;
                PrintAlignment parsedAlign;
                PrintFont parsedPrintFont;

                if (parsedLineStyle != null) {
                    lineStyle = parsedLineStyle;
                } else if ((parsedBarcode = parseEnum(BarcodeType.class, tag)) != null) {
                    barcodeType = parsedBarcode;
                } else if ((parsedAlign = parseEnum(PrintAlignment.class, tag)) != null) {
                    parsedAlignment = parsedAlign;
                } else if ((parsedPrintFont = parseEnum(PrintFont.class, tag)) != null) {
                    parsedFont = parsedPrintFont;
                } else {
                    continue;
                }

                recognized = true;
            }

            // Префикс в квадратных скобках срезаем только если внутри распознан тег
            // (center, dotted, QR, line...). Обычный текст вида "[Промо]" остается как есть.
            if (recognized) {
                line = line.substring(close + 1);
            }
        }

        DocPosition position = new DocPosition();

        if (hasLineTag || (lineStyle != null && line.isEmpty())) {
            SeparatorLine separator = new SeparatorLine();
            separator.LineStyle = lineStyle == null ? LineStyle.Solid : lineStyle;
            position.SeparatorLine = separator;
            return position;
        }

        if (barcodeType != null) {
            BarcodeLine barcode = new BarcodeLine();
            barcode.Type = barcodeType.name();
            barcode.Barcode = line.trim();
            barcode.Alignment = parsedAlignment == null ? null : parsedAlignment.name().toLowerCase(Locale.ROOT);
            position.Barcode = barcode;
            return position;
        }

        TextString textString = new TextString();
        textString.Text = line;
        textString.Font = parsedFont == null ? null : parsedFont.name();
        textString.Alignment = parsedAlignment == null ? null : parsedAlignment.name().toLowerCase(Locale.ROOT);
        position.TextString = textString;
        return position;
    }

    /**
     * [dotted] / [dashed] / [solid] / [double] - линия.
     * [bold] - шрифт, линия только вместе с [line].
     */
    private static LineStyle tryLineStyle(String tag, boolean hasLineTag) {
        LineStyle style = parseEnum(LineStyle.class, tag);
        if (style == null) {
            return null;
        }
        if (style == LineStyle.Bold && !hasLineTag) {
            return null;
        }
        return style;
    }

    private static <E extends Enum<E>> E parseEnum(Class<E> type, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        for (E item : type.getEnumConstants()) {
            if (item.name().equalsIgnoreCase(value.trim())) {
                return item;
            }
        }
        return null;
    }
}