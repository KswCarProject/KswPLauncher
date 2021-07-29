package com.google.zxing.pdf417.detector;

import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.List;

public final class PDF417DetectorResult {
    private final BitMatrix bits;
    private final List<ResultPoint[]> points;

    public PDF417DetectorResult(BitMatrix bits2, List<ResultPoint[]> points2) {
        this.bits = bits2;
        this.points = points2;
    }

    public BitMatrix getBits() {
        return this.bits;
    }

    public List<ResultPoint[]> getPoints() {
        return this.points;
    }
}
