package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Dimension;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.DefaultPlacement;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.datamatrix.encoder.HighLevelEncoder;
import com.google.zxing.datamatrix.encoder.SymbolInfo;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.util.Map;

public final class DataMatrixWriter implements Writer {
    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height) {
        return encode(contents, format, width, height, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) {
        if (contents.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (format != BarcodeFormat.DATA_MATRIX) {
            throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got ".concat(String.valueOf(format)));
        } else if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Requested dimensions can't be negative: " + width + 'x' + height);
        } else {
            SymbolShapeHint shape = SymbolShapeHint.FORCE_NONE;
            Dimension minSize = null;
            Dimension maxSize = null;
            if (hints != null) {
                SymbolShapeHint symbolShapeHint = (SymbolShapeHint) hints.get(EncodeHintType.DATA_MATRIX_SHAPE);
                SymbolShapeHint requestedShape = symbolShapeHint;
                if (symbolShapeHint != null) {
                    shape = requestedShape;
                }
                Dimension dimension = (Dimension) hints.get(EncodeHintType.MIN_SIZE);
                Dimension requestedMinSize = dimension;
                if (dimension != null) {
                    minSize = requestedMinSize;
                }
                Dimension dimension2 = (Dimension) hints.get(EncodeHintType.MAX_SIZE);
                Dimension requestedMaxSize = dimension2;
                if (dimension2 != null) {
                    maxSize = requestedMaxSize;
                }
            }
            String encodeHighLevel = HighLevelEncoder.encodeHighLevel(contents, shape, minSize, maxSize);
            String encoded = encodeHighLevel;
            SymbolInfo symbolInfo = SymbolInfo.lookup(encodeHighLevel.length(), shape, minSize, maxSize, true);
            DefaultPlacement placement = new DefaultPlacement(ErrorCorrection.encodeECC200(encoded, symbolInfo), symbolInfo.getSymbolDataWidth(), symbolInfo.getSymbolDataHeight());
            placement.place();
            return encodeLowLevel(placement, symbolInfo, width, height);
        }
    }

    private static BitMatrix encodeLowLevel(DefaultPlacement placement, SymbolInfo symbolInfo, int width, int height) {
        int symbolWidth = symbolInfo.getSymbolDataWidth();
        int symbolHeight = symbolInfo.getSymbolDataHeight();
        ByteMatrix matrix = new ByteMatrix(symbolInfo.getSymbolWidth(), symbolInfo.getSymbolHeight());
        int matrixY = 0;
        for (int y = 0; y < symbolHeight; y++) {
            if (y % symbolInfo.matrixHeight == 0) {
                int matrixX = 0;
                for (int x = 0; x < symbolInfo.getSymbolWidth(); x++) {
                    matrix.set(matrixX, matrixY, x % 2 == 0);
                    matrixX++;
                }
                matrixY++;
            }
            int matrixX2 = 0;
            for (int x2 = 0; x2 < symbolWidth; x2++) {
                if (x2 % symbolInfo.matrixWidth == 0) {
                    matrix.set(matrixX2, matrixY, true);
                    matrixX2++;
                }
                matrix.set(matrixX2, matrixY, placement.getBit(x2, y));
                matrixX2++;
                if (x2 % symbolInfo.matrixWidth == symbolInfo.matrixWidth - 1) {
                    matrix.set(matrixX2, matrixY, y % 2 == 0);
                    matrixX2++;
                }
            }
            matrixY++;
            if (y % symbolInfo.matrixHeight == symbolInfo.matrixHeight - 1) {
                int matrixX3 = 0;
                for (int x3 = 0; x3 < symbolInfo.getSymbolWidth(); x3++) {
                    matrix.set(matrixX3, matrixY, true);
                    matrixX3++;
                }
                matrixY++;
            }
        }
        return convertByteMatrixToBitMatrix(matrix, width, height);
    }

    private static BitMatrix convertByteMatrixToBitMatrix(ByteMatrix matrix, int reqWidth, int reqHeight) {
        BitMatrix output;
        int i = reqWidth;
        int i2 = reqHeight;
        int matrixWidth = matrix.getWidth();
        int matrixHeight = matrix.getHeight();
        int outputWidth = Math.max(i, matrixWidth);
        int outputHeight = Math.max(i2, matrixHeight);
        int multiple = Math.min(outputWidth / matrixWidth, outputHeight / matrixHeight);
        int leftPadding = (outputWidth - (matrixWidth * multiple)) / 2;
        int topPadding = (outputHeight - (matrixHeight * multiple)) / 2;
        if (i2 < matrixHeight || i < matrixWidth) {
            leftPadding = 0;
            topPadding = 0;
            output = new BitMatrix(matrixWidth, matrixHeight);
        } else {
            output = new BitMatrix(i, i2);
        }
        output.clear();
        int inputY = 0;
        int outputY = topPadding;
        while (inputY < matrixHeight) {
            int inputX = 0;
            int outputX = leftPadding;
            while (inputX < matrixWidth) {
                if (matrix.get(inputX, inputY) == 1) {
                    output.setRegion(outputX, outputY, multiple, multiple);
                }
                inputX++;
                outputX += multiple;
                int i3 = reqWidth;
            }
            ByteMatrix byteMatrix = matrix;
            inputY++;
            outputY += multiple;
            int i4 = reqWidth;
        }
        ByteMatrix byteMatrix2 = matrix;
        return output;
    }
}
