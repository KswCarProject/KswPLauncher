package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class Code39Writer extends OneDimensionalCodeWriter {
    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (format == BarcodeFormat.CODE_39) {
            return super.encode(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode CODE_39, but got ".concat(String.valueOf(format)));
    }

    public boolean[] encode(String contents) {
        int length = contents.length();
        int length2 = length;
        if (length <= 80) {
            int i = 0;
            while (true) {
                if (i >= length2) {
                    break;
                } else if ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(contents.charAt(i)) < 0) {
                    String tryToConvertToExtendedMode = tryToConvertToExtendedMode(contents);
                    contents = tryToConvertToExtendedMode;
                    int length3 = tryToConvertToExtendedMode.length();
                    length2 = length3;
                    if (length3 > 80) {
                        throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length2 + " (extended full ASCII mode)");
                    }
                } else {
                    i++;
                }
            }
            int[] widths = new int[9];
            int codeWidth = length2 + 25;
            for (int i2 = 0; i2 < length2; i2++) {
                toIntArray(Code39Reader.CHARACTER_ENCODINGS["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(contents.charAt(i2))], widths);
                for (int i3 = 0; i3 < 9; i3++) {
                    codeWidth += widths[i3];
                }
            }
            boolean[] result = new boolean[codeWidth];
            toIntArray(148, widths);
            int pos = appendPattern(result, 0, widths, true);
            int[] narrowWhite = {1};
            int pos2 = pos + appendPattern(result, pos, narrowWhite, false);
            for (int i4 = 0; i4 < length2; i4++) {
                toIntArray(Code39Reader.CHARACTER_ENCODINGS["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(contents.charAt(i4))], widths);
                int pos3 = appendPattern(result, pos2, widths, true) + pos2;
                pos2 = pos3 + appendPattern(result, pos3, narrowWhite, false);
            }
            toIntArray(148, widths);
            appendPattern(result, pos2, widths, true);
            return result;
        }
        throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length2)));
    }

    private static void toIntArray(int a, int[] toReturn) {
        for (int i = 0; i < 9; i++) {
            int i2 = 1;
            if (((1 << (8 - i)) & a) != 0) {
                i2 = 2;
            }
            toReturn[i] = i2;
        }
    }

    private static String tryToConvertToExtendedMode(String contents) {
        int length = contents.length();
        StringBuilder extendedContent = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char charAt = contents.charAt(i);
            char character = charAt;
            switch (charAt) {
                case 0:
                    extendedContent.append("%U");
                    break;
                case ' ':
                case '-':
                case '.':
                    extendedContent.append(character);
                    break;
                case '@':
                    extendedContent.append("%V");
                    break;
                case '`':
                    extendedContent.append("%W");
                    break;
                default:
                    if (character <= 26) {
                        extendedContent.append('$');
                        extendedContent.append((char) ((character - 1) + 65));
                        break;
                    } else if (character < ' ') {
                        extendedContent.append('%');
                        extendedContent.append((char) ((character - 27) + 65));
                        break;
                    } else if (character <= ',' || character == '/' || character == ':') {
                        extendedContent.append('/');
                        extendedContent.append((char) ((character - '!') + 65));
                        break;
                    } else if (character <= '9') {
                        extendedContent.append((char) ((character - '0') + 48));
                        break;
                    } else if (character <= '?') {
                        extendedContent.append('%');
                        extendedContent.append((char) ((character - ';') + 70));
                        break;
                    } else if (character <= 'Z') {
                        extendedContent.append((char) ((character - 'A') + 65));
                        break;
                    } else if (character <= '_') {
                        extendedContent.append('%');
                        extendedContent.append((char) ((character - '[') + 75));
                        break;
                    } else if (character <= 'z') {
                        extendedContent.append('+');
                        extendedContent.append((char) ((character - 'a') + 65));
                        break;
                    } else if (character <= 127) {
                        extendedContent.append('%');
                        extendedContent.append((char) ((character - '{') + 80));
                        break;
                    } else {
                        throw new IllegalArgumentException("Requested content contains a non-encodable character: '" + contents.charAt(i) + "'");
                    }
            }
        }
        return extendedContent.toString();
    }
}
