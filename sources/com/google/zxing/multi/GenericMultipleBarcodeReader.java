package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    private static final int MAX_DEPTH = 4;
    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader delegate;

    public GenericMultipleBarcodeReader(Reader delegate) {
        this.delegate = delegate;
    }

    @Override // com.google.zxing.multi.MultipleBarcodeReader
    public Result[] decodeMultiple(BinaryBitmap image) throws NotFoundException {
        return decodeMultiple(image, null);
    }

    @Override // com.google.zxing.multi.MultipleBarcodeReader
    public Result[] decodeMultiple(BinaryBitmap image, Map<DecodeHintType, ?> hints) throws NotFoundException {
        List<Result> results = new ArrayList<>();
        doDecodeMultiple(image, hints, results, 0, 0, 0);
        if (results.isEmpty()) {
            throw NotFoundException.getNotFoundInstance();
        }
        return (Result[]) results.toArray(new Result[results.size()]);
    }

    private void doDecodeMultiple(BinaryBitmap image, Map<DecodeHintType, ?> hints, List<Result> results, int xOffset, int yOffset, int currentDepth) {
        boolean alreadyFound;
        float maxX;
        float minY;
        int height;
        int width;
        if (currentDepth <= 4) {
            try {
                Result result = this.delegate.decode(image, hints);
                Iterator<Result> it = results.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        alreadyFound = false;
                        break;
                    } else if (it.next().getText().equals(result.getText())) {
                        alreadyFound = true;
                        break;
                    }
                }
                if (!alreadyFound) {
                    results.add(translateResultPoints(result, xOffset, yOffset));
                }
                ResultPoint[] resultPoints = result.getResultPoints();
                if (resultPoints != null && resultPoints.length != 0) {
                    int width2 = image.getWidth();
                    int height2 = image.getHeight();
                    float minX = width2;
                    float minY2 = height2;
                    float maxY = 0.0f;
                    float minX2 = minX;
                    float maxX2 = 0.0f;
                    for (ResultPoint point : resultPoints) {
                        if (point != null) {
                            float x = point.getX();
                            float y = point.getY();
                            if (x < minX2) {
                                minX2 = x;
                            }
                            if (y < minY2) {
                                minY2 = y;
                            }
                            if (x > maxX2) {
                                maxX2 = x;
                            }
                            if (y > maxY) {
                                maxY = y;
                            }
                        }
                    }
                    if (minX2 > 100.0f) {
                        minY = minY2;
                        height = height2;
                        maxX = maxX2;
                        width = width2;
                        doDecodeMultiple(image.crop(0, 0, (int) minX2, height2), hints, results, xOffset, yOffset, currentDepth + 1);
                    } else {
                        maxX = maxX2;
                        minY = minY2;
                        height = height2;
                        width = width2;
                    }
                    if (minY > 100.0f) {
                        doDecodeMultiple(image.crop(0, 0, width, (int) minY), hints, results, xOffset, yOffset, currentDepth + 1);
                    }
                    if (maxX < width - 100) {
                        doDecodeMultiple(image.crop((int) maxX, 0, width - ((int) maxX), height), hints, results, xOffset + ((int) maxX), yOffset, currentDepth + 1);
                    }
                    if (maxY < height - 100) {
                        doDecodeMultiple(image.crop(0, (int) maxY, width, height - ((int) maxY)), hints, results, xOffset, yOffset + ((int) maxY), currentDepth + 1);
                    }
                }
            } catch (ReaderException e) {
            }
        }
    }

    private static Result translateResultPoints(Result result, int xOffset, int yOffset) {
        ResultPoint[] oldResultPoints = result.getResultPoints();
        if (oldResultPoints == null) {
            return result;
        }
        ResultPoint[] newResultPoints = new ResultPoint[oldResultPoints.length];
        for (int i = 0; i < oldResultPoints.length; i++) {
            ResultPoint oldPoint = oldResultPoints[i];
            if (oldPoint != null) {
                newResultPoints[i] = new ResultPoint(oldPoint.getX() + xOffset, oldPoint.getY() + yOffset);
            }
        }
        Result newResult = new Result(result.getText(), result.getRawBytes(), result.getNumBits(), newResultPoints, result.getBarcodeFormat(), result.getTimestamp());
        newResult.putAllMetadata(result.getResultMetadata());
        return newResult;
    }
}
