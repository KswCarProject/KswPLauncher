package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class ITFWriter extends OneDimensionalCodeWriter {
    private static final int[] END_PATTERN = {3, 1, 1};
    private static final int N = 1;
    private static final int[][] PATTERNS = {new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};
    private static final int[] START_PATTERN = {1, 1, 1, 1};
    private static final int W = 3;

    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (format == BarcodeFormat.ITF) {
            return super.encode(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode ITF, but got ".concat(String.valueOf(format)));
    }

    public boolean[] encode(String contents) {
        int length = contents.length();
        int length2 = length;
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The length of the input should be even");
        } else if (length2 <= 80) {
            boolean[] zArr = new boolean[((length2 * 9) + 9)];
            boolean[] result = zArr;
            int pos = appendPattern(zArr, 0, START_PATTERN, true);
            for (int i = 0; i < length2; i += 2) {
                int one = Character.digit(contents.charAt(i), 10);
                int two = Character.digit(contents.charAt(i + 1), 10);
                int[] encoding = new int[10];
                for (int j = 0; j < 5; j++) {
                    int[][] iArr = PATTERNS;
                    encoding[j * 2] = iArr[one][j];
                    encoding[(j * 2) + 1] = iArr[two][j];
                }
                pos += appendPattern(result, pos, encoding, true);
            }
            appendPattern(result, pos, END_PATTERN, true);
            return result;
        } else {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length2)));
        }
    }
}
