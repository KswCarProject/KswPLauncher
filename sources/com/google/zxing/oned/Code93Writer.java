package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes.dex */
public class Code93Writer extends OneDimensionalCodeWriter {
    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (format != BarcodeFormat.CODE_93) {
            throw new IllegalArgumentException("Can only encode CODE_93, but got ".concat(String.valueOf(format)));
        }
        return super.encode(contents, format, width, height, hints);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String contents) {
        int length = contents.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length)));
        }
        int[] widths = new int[9];
        int codeWidth = ((contents.length() + 2 + 2) * 9) + 1;
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[47], widths);
        boolean[] result = new boolean[codeWidth];
        int pos = appendPattern(result, 0, widths);
        for (int i = 0; i < length; i++) {
            int indexInString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(contents.charAt(i));
            toIntArray(Code93Reader.CHARACTER_ENCODINGS[indexInString], widths);
            pos += appendPattern(result, pos, widths);
        }
        int check1 = computeChecksumIndex(contents, 20);
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[check1], widths);
        int pos2 = pos + appendPattern(result, pos, widths);
        int check2 = computeChecksumIndex(contents + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(check1), 15);
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[check2], widths);
        int pos3 = pos2 + appendPattern(result, pos2, widths);
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[47], widths);
        result[pos3 + appendPattern(result, pos3, widths)] = true;
        return result;
    }

    private static void toIntArray(int a, int[] toReturn) {
        for (int i = 0; i < 9; i++) {
            int i2 = 1;
            int temp = (1 << (8 - i)) & a;
            if (temp == 0) {
                i2 = 0;
            }
            toReturn[i] = i2;
        }
    }

    @Deprecated
    protected static int appendPattern(boolean[] target, int pos, int[] pattern, boolean startColor) {
        return appendPattern(target, pos, pattern);
    }

    private static int appendPattern(boolean[] target, int pos, int[] pattern) {
        int length = pattern.length;
        int i = 0;
        while (i < length) {
            int bit = pattern[i];
            int pos2 = pos + 1;
            target[pos] = bit != 0;
            i++;
            pos = pos2;
        }
        return 9;
    }

    private static int computeChecksumIndex(String contents, int maxWeight) {
        int weight = 1;
        int total = 0;
        for (int i = contents.length() - 1; i >= 0; i--) {
            int indexInString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(contents.charAt(i));
            total += indexInString * weight;
            weight++;
            if (weight > maxWeight) {
                weight = 1;
            }
        }
        int i2 = total % 47;
        return i2;
    }
}
