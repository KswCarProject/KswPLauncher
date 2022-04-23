package com.google.zxing.maxicode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.maxicode.decoder.Decoder;
import java.util.Map;

public final class MaxiCodeReader implements Reader {
    private static final int MATRIX_HEIGHT = 33;
    private static final int MATRIX_WIDTH = 30;
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    public Result decode(BinaryBitmap image) throws NotFoundException, ChecksumException, FormatException {
        return decode(image, (Map<DecodeHintType, ?>) null);
    }

    public Result decode(BinaryBitmap image, Map<DecodeHintType, ?> hints) throws NotFoundException, ChecksumException, FormatException {
        if (hints == null || !hints.containsKey(DecodeHintType.PURE_BARCODE)) {
            throw NotFoundException.getNotFoundInstance();
        }
        DecoderResult decoderResult = this.decoder.decode(extractPureBits(image.getBlackMatrix()), hints);
        Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), NO_POINTS, BarcodeFormat.MAXICODE);
        String eCLevel = decoderResult.getECLevel();
        String ecLevel = eCLevel;
        if (eCLevel != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, ecLevel);
        }
        return result;
    }

    public void reset() {
    }

    private static BitMatrix extractPureBits(BitMatrix image) throws NotFoundException {
        int[] enclosingRectangle = image.getEnclosingRectangle();
        int[] enclosingRectangle2 = enclosingRectangle;
        if (enclosingRectangle != null) {
            int left = enclosingRectangle2[0];
            int top = enclosingRectangle2[1];
            int width = enclosingRectangle2[2];
            int height = enclosingRectangle2[3];
            BitMatrix bits = new BitMatrix(30, 33);
            for (int y = 0; y < 33; y++) {
                int iy = (((y * height) + (height / 2)) / 33) + top;
                for (int x = 0; x < 30; x++) {
                    if (image.get(((((x * width) + (width / 2)) + (((y & 1) * width) / 2)) / 30) + left, iy)) {
                        bits.set(x, y);
                    }
                }
            }
            return bits;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
