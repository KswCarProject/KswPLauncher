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

public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    private static final int MAX_DEPTH = 4;
    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader delegate;

    public GenericMultipleBarcodeReader(Reader delegate2) {
        this.delegate = delegate2;
    }

    public Result[] decodeMultiple(BinaryBitmap image) throws NotFoundException {
        return decodeMultiple(image, (Map<DecodeHintType, ?>) null);
    }

    public Result[] decodeMultiple(BinaryBitmap image, Map<DecodeHintType, ?> hints) throws NotFoundException {
        ArrayList arrayList = new ArrayList();
        doDecodeMultiple(image, hints, arrayList, 0, 0, 0);
        if (!arrayList.isEmpty()) {
            return (Result[]) arrayList.toArray(new Result[arrayList.size()]);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void doDecodeMultiple(BinaryBitmap image, Map<DecodeHintType, ?> hints, List<Result> results, int xOffset, int yOffset, int currentDepth) {
        boolean alreadyFound;
        float maxX;
        int width;
        int height;
        float minY;
        BinaryBitmap binaryBitmap = image;
        int i = xOffset;
        int i2 = yOffset;
        int i3 = currentDepth;
        if (i3 <= 4) {
            try {
                Result result = this.delegate.decode(binaryBitmap, hints);
                Iterator<Result> it = results.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getText().equals(result.getText())) {
                            alreadyFound = true;
                            break;
                        }
                    } else {
                        alreadyFound = false;
                        break;
                    }
                }
                if (!alreadyFound) {
                    results.add(translateResultPoints(result, i, i2));
                } else {
                    List<Result> list = results;
                }
                ResultPoint[] resultPoints = result.getResultPoints();
                ResultPoint[] resultPoints2 = resultPoints;
                if (resultPoints == null) {
                    ResultPoint[] resultPointArr = resultPoints2;
                    boolean z = alreadyFound;
                } else if (resultPoints2.length == 0) {
                    Result result2 = result;
                    ResultPoint[] resultPointArr2 = resultPoints2;
                    boolean z2 = alreadyFound;
                } else {
                    int width2 = image.getWidth();
                    int height2 = image.getHeight();
                    float minX = (float) width2;
                    float minY2 = (float) height2;
                    Result result3 = result;
                    int width3 = width2;
                    float maxY = 0.0f;
                    float minX2 = minX;
                    float maxX2 = 0.0f;
                    for (ResultPoint resultPoint : resultPoints2) {
                        ResultPoint point = resultPoint;
                        if (resultPoint != null) {
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
                        float f = minX2;
                        height = height2;
                        maxX = maxX2;
                        boolean z3 = alreadyFound;
                        width = width3;
                        ResultPoint[] resultPointArr3 = resultPoints2;
                        doDecodeMultiple(binaryBitmap.crop(0, 0, (int) minX2, height2), hints, results, xOffset, yOffset, i3 + 1);
                    } else {
                        maxX = maxX2;
                        minY = minY2;
                        float f2 = minX2;
                        height = height2;
                        boolean z4 = alreadyFound;
                        width = width3;
                        ResultPoint[] resultPointArr4 = resultPoints2;
                    }
                    if (minY > 100.0f) {
                        doDecodeMultiple(binaryBitmap.crop(0, 0, width, (int) minY), hints, results, xOffset, yOffset, i3 + 1);
                    }
                    if (maxX < ((float) (width - 100))) {
                        doDecodeMultiple(binaryBitmap.crop((int) maxX, 0, width - ((int) maxX), height), hints, results, i + ((int) maxX), yOffset, i3 + 1);
                    }
                    if (maxY < ((float) (height - 100))) {
                        doDecodeMultiple(binaryBitmap.crop(0, (int) maxY, width, height - ((int) maxY)), hints, results, xOffset, i2 + ((int) maxY), i3 + 1);
                    }
                }
            } catch (ReaderException e) {
            }
        }
    }

    private static Result translateResultPoints(Result result, int xOffset, int yOffset) {
        ResultPoint[] resultPoints = result.getResultPoints();
        ResultPoint[] oldResultPoints = resultPoints;
        if (resultPoints == null) {
            return result;
        }
        ResultPoint[] newResultPoints = new ResultPoint[oldResultPoints.length];
        int i = 0;
        while (i < oldResultPoints.length) {
            ResultPoint resultPoint = oldResultPoints[i];
            ResultPoint oldPoint = resultPoint;
            if (resultPoint != null) {
                newResultPoints[i] = new ResultPoint(oldPoint.getX() + ((float) xOffset), oldPoint.getY() + ((float) yOffset));
            }
            i++;
        }
        Result newResult = new Result(result.getText(), result.getRawBytes(), result.getNumBits(), newResultPoints, result.getBarcodeFormat(), result.getTimestamp());
        int i2 = i;
        Result newResult2 = newResult;
        newResult.putAllMetadata(result.getResultMetadata());
        return newResult2;
    }
}
