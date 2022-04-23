package com.google.zxing.qrcode;

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
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import com.google.zxing.qrcode.detector.Detector;
import java.util.List;
import java.util.Map;

public class QRCodeReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    /* access modifiers changed from: protected */
    public final Decoder getDecoder() {
        return this.decoder;
    }

    public Result decode(BinaryBitmap image) throws NotFoundException, ChecksumException, FormatException {
        return decode(image, (Map<DecodeHintType, ?>) null);
    }

    public final Result decode(BinaryBitmap image, Map<DecodeHintType, ?> hints) throws NotFoundException, ChecksumException, FormatException {
        DecoderResult decoderResult;
        ResultPoint[] points;
        if (hints == null || !hints.containsKey(DecodeHintType.PURE_BARCODE)) {
            DetectorResult detectorResult = new Detector(image.getBlackMatrix()).detect(hints);
            decoderResult = this.decoder.decode(detectorResult.getBits(), hints);
            points = detectorResult.getPoints();
        } else {
            decoderResult = this.decoder.decode(extractPureBits(image.getBlackMatrix()), hints);
            points = NO_POINTS;
        }
        if (decoderResult.getOther() instanceof QRCodeDecoderMetaData) {
            ((QRCodeDecoderMetaData) decoderResult.getOther()).applyMirroredCorrection(points);
        }
        Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), points, BarcodeFormat.QR_CODE);
        List<byte[]> byteSegments = decoderResult.getByteSegments();
        List<byte[]> byteSegments2 = byteSegments;
        if (byteSegments != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, byteSegments2);
        }
        String eCLevel = decoderResult.getECLevel();
        String ecLevel = eCLevel;
        if (eCLevel != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, ecLevel);
        }
        if (decoderResult.hasStructuredAppend()) {
            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(decoderResult.getStructuredAppendSequenceNumber()));
            result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(decoderResult.getStructuredAppendParity()));
        }
        return result;
    }

    public void reset() {
    }

    private static BitMatrix extractPureBits(BitMatrix image) throws NotFoundException {
        BitMatrix bitMatrix = image;
        int[] leftTopBlack = image.getTopLeftOnBit();
        int[] rightBottomBlack = image.getBottomRightOnBit();
        if (leftTopBlack == null || rightBottomBlack == null) {
            int[] iArr = rightBottomBlack;
            throw NotFoundException.getNotFoundInstance();
        }
        float moduleSize = moduleSize(leftTopBlack, bitMatrix);
        int top = leftTopBlack[1];
        int bottom = rightBottomBlack[1];
        int left = leftTopBlack[0];
        int right = rightBottomBlack[0];
        if (left >= right || top >= bottom) {
            int[] iArr2 = rightBottomBlack;
            throw NotFoundException.getNotFoundInstance();
        }
        if (bottom - top != right - left) {
            int i = (bottom - top) + left;
            right = i;
            if (i >= image.getWidth()) {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        int matrixWidth = Math.round(((float) ((right - left) + 1)) / moduleSize);
        int matrixHeight = Math.round(((float) ((bottom - top) + 1)) / moduleSize);
        if (matrixWidth <= 0 || matrixHeight <= 0) {
            int[] iArr3 = rightBottomBlack;
            throw NotFoundException.getNotFoundInstance();
        } else if (matrixHeight == matrixWidth) {
            int nudge = (int) (moduleSize / 2.0f);
            int top2 = top + nudge;
            int i2 = left + nudge;
            int left2 = i2;
            int i3 = (i2 + ((int) (((float) (matrixWidth - 1)) * moduleSize))) - right;
            int nudgedTooFarRight = i3;
            if (i3 > 0) {
                if (nudgedTooFarRight <= nudge) {
                    left2 -= nudgedTooFarRight;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
            int i4 = (((int) (((float) (matrixHeight - 1)) * moduleSize)) + top2) - bottom;
            int nudgedTooFarDown = i4;
            if (i4 > 0) {
                if (nudgedTooFarDown <= nudge) {
                    top2 -= nudgedTooFarDown;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
            BitMatrix bits = new BitMatrix(matrixWidth, matrixHeight);
            int y = 0;
            while (y < matrixHeight) {
                int iOffset = ((int) (((float) y) * moduleSize)) + top2;
                int[] leftTopBlack2 = leftTopBlack;
                int x = 0;
                while (x < matrixWidth) {
                    int[] rightBottomBlack2 = rightBottomBlack;
                    if (bitMatrix.get(((int) (((float) x) * moduleSize)) + left2, iOffset)) {
                        bits.set(x, y);
                    }
                    x++;
                    rightBottomBlack = rightBottomBlack2;
                }
                y++;
                leftTopBlack = leftTopBlack2;
            }
            return bits;
        } else {
            int[] iArr4 = leftTopBlack;
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static float moduleSize(int[] leftTopBlack, BitMatrix image) throws NotFoundException {
        int height = image.getHeight();
        int width = image.getWidth();
        int x = leftTopBlack[0];
        int y = leftTopBlack[1];
        boolean inBlack = true;
        int transitions = 0;
        while (x < width && y < height) {
            if (inBlack != image.get(x, y)) {
                transitions++;
                if (transitions == 5) {
                    break;
                }
                inBlack = !inBlack;
            }
            x++;
            y++;
        }
        if (x != width && y != height) {
            return ((float) (x - leftTopBlack[0])) / 7.0f;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
