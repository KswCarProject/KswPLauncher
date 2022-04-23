package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public abstract class OneDimensionalCodeWriter implements Writer {
    public abstract boolean[] encode(String str);

    public final BitMatrix encode(String contents, BarcodeFormat format, int width, int height) throws WriterException {
        return encode(contents, format, width, height, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (contents.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Negative size is not allowed. Input: " + width + 'x' + height);
        } else {
            int sidesMargin = getDefaultMargin();
            if (hints != null && hints.containsKey(EncodeHintType.MARGIN)) {
                sidesMargin = Integer.parseInt(hints.get(EncodeHintType.MARGIN).toString());
            }
            return renderResult(encode(contents), width, height, sidesMargin);
        }
    }

    private static BitMatrix renderResult(boolean[] code, int width, int height, int sidesMargin) {
        int length = code.length;
        int inputWidth = length;
        int fullWidth = length + sidesMargin;
        int outputWidth = Math.max(width, fullWidth);
        int outputHeight = Math.max(1, height);
        int multiple = outputWidth / fullWidth;
        BitMatrix output = new BitMatrix(outputWidth, outputHeight);
        int inputX = 0;
        int outputX = (outputWidth - (inputWidth * multiple)) / 2;
        while (inputX < inputWidth) {
            if (code[inputX]) {
                output.setRegion(outputX, 0, multiple, outputHeight);
            }
            inputX++;
            outputX += multiple;
        }
        return output;
    }

    protected static int appendPattern(boolean[] target, int pos, int[] pattern, boolean startColor) {
        boolean color = startColor;
        int numAdded = 0;
        for (int len : pattern) {
            int j = 0;
            while (j < len) {
                target[pos] = color;
                j++;
                pos++;
            }
            numAdded += len;
            color = !color;
        }
        return numAdded;
    }

    public int getDefaultMargin() {
        return 10;
    }
}
