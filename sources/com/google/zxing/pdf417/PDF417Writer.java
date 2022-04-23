package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.Compaction;
import com.google.zxing.pdf417.encoder.Dimensions;
import com.google.zxing.pdf417.encoder.PDF417;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

public final class PDF417Writer implements Writer {
    private static final int DEFAULT_ERROR_CORRECTION_LEVEL = 2;
    private static final int WHITE_SPACE = 30;

    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        int errorCorrectionLevel;
        int margin;
        if (format == BarcodeFormat.PDF_417) {
            PDF417 encoder = new PDF417();
            int margin2 = 30;
            int errorCorrectionLevel2 = 2;
            if (hints != null) {
                if (hints.containsKey(EncodeHintType.PDF417_COMPACT)) {
                    encoder.setCompact(Boolean.valueOf(hints.get(EncodeHintType.PDF417_COMPACT).toString()).booleanValue());
                }
                if (hints.containsKey(EncodeHintType.PDF417_COMPACTION)) {
                    encoder.setCompaction(Compaction.valueOf(hints.get(EncodeHintType.PDF417_COMPACTION).toString()));
                }
                if (hints.containsKey(EncodeHintType.PDF417_DIMENSIONS)) {
                    Dimensions dimensions = (Dimensions) hints.get(EncodeHintType.PDF417_DIMENSIONS);
                    encoder.setDimensions(dimensions.getMaxCols(), dimensions.getMinCols(), dimensions.getMaxRows(), dimensions.getMinRows());
                }
                if (hints.containsKey(EncodeHintType.MARGIN)) {
                    margin2 = Integer.parseInt(hints.get(EncodeHintType.MARGIN).toString());
                }
                if (hints.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                    errorCorrectionLevel2 = Integer.parseInt(hints.get(EncodeHintType.ERROR_CORRECTION).toString());
                }
                if (hints.containsKey(EncodeHintType.CHARACTER_SET)) {
                    encoder.setEncoding(Charset.forName(hints.get(EncodeHintType.CHARACTER_SET).toString()));
                }
                margin = margin2;
                errorCorrectionLevel = errorCorrectionLevel2;
            } else {
                margin = 30;
                errorCorrectionLevel = 2;
            }
            return bitMatrixFromEncoder(encoder, contents, errorCorrectionLevel, width, height, margin);
        }
        throw new IllegalArgumentException("Can only encode PDF_417, but got ".concat(String.valueOf(format)));
    }

    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height) throws WriterException {
        return encode(contents, format, width, height, (Map<EncodeHintType, ?>) null);
    }

    private static BitMatrix bitMatrixFromEncoder(PDF417 encoder, String contents, int errorCorrectionLevel, int width, int height, int margin) throws WriterException {
        int scale;
        encoder.generateBarcodeLogic(contents, errorCorrectionLevel);
        byte[][] originalScale = encoder.getBarcodeMatrix().getScaledMatrix(1, 4);
        boolean rotated = false;
        if ((height > width) != (originalScale[0].length < originalScale.length)) {
            originalScale = rotateArray(originalScale);
            rotated = true;
        }
        int scaleX = width / originalScale[0].length;
        int scaleY = height / originalScale.length;
        if (scaleX < scaleY) {
            scale = scaleX;
        } else {
            scale = scaleY;
        }
        if (scale <= 1) {
            return bitMatrixFromBitArray(originalScale, margin);
        }
        byte[][] scaledMatrix = encoder.getBarcodeMatrix().getScaledMatrix(scale, scale << 2);
        if (rotated) {
            scaledMatrix = rotateArray(scaledMatrix);
        }
        return bitMatrixFromBitArray(scaledMatrix, margin);
    }

    private static BitMatrix bitMatrixFromBitArray(byte[][] input, int margin) {
        BitMatrix bitMatrix = new BitMatrix(input[0].length + (margin * 2), input.length + (margin * 2));
        BitMatrix output = bitMatrix;
        bitMatrix.clear();
        int y = 0;
        int yOutput = (output.getHeight() - margin) - 1;
        while (y < input.length) {
            byte[] inputY = input[y];
            for (int x = 0; x < input[0].length; x++) {
                if (inputY[x] == 1) {
                    output.set(x + margin, yOutput);
                }
            }
            y++;
            yOutput--;
        }
        return output;
    }

    private static byte[][] rotateArray(byte[][] bitarray) {
        int length = bitarray[0].length;
        int[] iArr = new int[2];
        iArr[1] = bitarray.length;
        iArr[0] = length;
        byte[][] temp = (byte[][]) Array.newInstance(byte.class, iArr);
        for (int ii = 0; ii < bitarray.length; ii++) {
            int inverseii = (bitarray.length - ii) - 1;
            for (int jj = 0; jj < bitarray[0].length; jj++) {
                temp[jj][inverseii] = bitarray[ii][jj];
            }
        }
        return temp;
    }
}
