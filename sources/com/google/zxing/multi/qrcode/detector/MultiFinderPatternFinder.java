package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final float DIFF_MODSIZE_CUTOFF = 0.5f;
    private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05f;
    private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];
    private static final float MAX_MODULE_COUNT_PER_EDGE = 180.0f;
    private static final float MIN_MODULE_COUNT_PER_EDGE = 9.0f;

    /* loaded from: classes.dex */
    private static final class ModuleSizeComparator implements Serializable, Comparator<FinderPattern> {
        private ModuleSizeComparator() {
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern center1, FinderPattern center2) {
            float value = center2.getEstimatedModuleSize() - center1.getEstimatedModuleSize();
            if (value < 0.0d) {
                return -1;
            }
            return ((double) value) > 0.0d ? 1 : 0;
        }
    }

    MultiFinderPatternFinder(BitMatrix image) {
        super(image);
    }

    MultiFinderPatternFinder(BitMatrix image, ResultPointCallback resultPointCallback) {
        super(image, resultPointCallback);
    }

    private FinderPattern[][] selectMutipleBestPatterns() throws NotFoundException {
        int i;
        FinderPattern finderPattern;
        List<FinderPattern> possibleCenters;
        boolean z;
        boolean z2;
        int i2;
        FinderPattern p1;
        List<FinderPattern> possibleCenters2;
        boolean z3;
        boolean z4;
        FinderPattern p12;
        List<FinderPattern> possibleCenters3;
        boolean z5;
        List<FinderPattern> possibleCenters4 = getPossibleCenters();
        List<FinderPattern> possibleCenters5 = possibleCenters4;
        int size = possibleCenters4.size();
        boolean z6 = false;
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i3 = 2;
        boolean z7 = true;
        if (size == 3) {
            return new FinderPattern[][]{new FinderPattern[]{possibleCenters5.get(0), possibleCenters5.get(1), possibleCenters5.get(2)}};
        }
        Collections.sort(possibleCenters5, new ModuleSizeComparator());
        List<FinderPattern[]> results = new ArrayList<>();
        int i1 = 0;
        while (i1 < size - 2) {
            FinderPattern finderPattern2 = possibleCenters5.get(i1);
            FinderPattern p13 = finderPattern2;
            if (finderPattern2 == null) {
                i = i3;
                finderPattern = p13;
                possibleCenters = possibleCenters5;
                z = z6;
                z2 = z7;
            } else {
                int i22 = i1 + 1;
                while (true) {
                    if (i22 >= size - 1) {
                        i = i3;
                        finderPattern = p13;
                        possibleCenters = possibleCenters5;
                        z = z6;
                        z2 = z7;
                        break;
                    }
                    FinderPattern p2 = possibleCenters5.get(i22);
                    if (p2 != null) {
                        float vModSize12 = (p13.getEstimatedModuleSize() - p2.getEstimatedModuleSize()) / Math.min(p13.getEstimatedModuleSize(), p2.getEstimatedModuleSize());
                        float abs = Math.abs(p13.getEstimatedModuleSize() - p2.getEstimatedModuleSize());
                        float f = DIFF_MODSIZE_CUTOFF;
                        if (abs > DIFF_MODSIZE_CUTOFF && vModSize12 >= DIFF_MODSIZE_CUTOFF_PERCENT) {
                            finderPattern = p13;
                            possibleCenters = possibleCenters5;
                            z = z6;
                            i = 2;
                            z2 = true;
                            break;
                        }
                        int i32 = i22 + 1;
                        while (true) {
                            if (i32 >= size) {
                                p1 = p13;
                                possibleCenters2 = possibleCenters5;
                                z3 = z6;
                                i2 = 2;
                                z4 = true;
                                break;
                            }
                            FinderPattern p3 = possibleCenters5.get(i32);
                            if (p3 != null) {
                                float vModSize23 = (p2.getEstimatedModuleSize() - p3.getEstimatedModuleSize()) / Math.min(p2.getEstimatedModuleSize(), p3.getEstimatedModuleSize());
                                if (Math.abs(p2.getEstimatedModuleSize() - p3.getEstimatedModuleSize()) > f && vModSize23 >= DIFF_MODSIZE_CUTOFF_PERCENT) {
                                    p1 = p13;
                                    possibleCenters2 = possibleCenters5;
                                    i2 = 2;
                                    z4 = true;
                                    z3 = false;
                                    break;
                                }
                                z5 = false;
                                FinderPattern[] test = {p13, p2, p3};
                                ResultPoint.orderBestPatterns(test);
                                FinderPatternInfo info = new FinderPatternInfo(test);
                                float dA = ResultPoint.distance(info.getTopLeft(), info.getBottomLeft());
                                float dC = ResultPoint.distance(info.getTopRight(), info.getBottomLeft());
                                possibleCenters3 = possibleCenters5;
                                float dB = ResultPoint.distance(info.getTopLeft(), info.getTopRight());
                                float estimatedModuleCount = (dA + dB) / (p13.getEstimatedModuleSize() * 2.0f);
                                if (estimatedModuleCount > MAX_MODULE_COUNT_PER_EDGE || estimatedModuleCount < MIN_MODULE_COUNT_PER_EDGE) {
                                    p12 = p13;
                                } else if (Math.abs((dA - dB) / Math.min(dA, dB)) >= 0.1f) {
                                    p12 = p13;
                                } else {
                                    p12 = p13;
                                    float dCpy = (float) Math.sqrt((dA * dA) + (dB * dB));
                                    if (Math.abs((dC - dCpy) / Math.min(dC, dCpy)) < 0.1f) {
                                        results.add(test);
                                    }
                                }
                            } else {
                                p12 = p13;
                                possibleCenters3 = possibleCenters5;
                                z5 = z6;
                            }
                            i32++;
                            z6 = z5;
                            possibleCenters5 = possibleCenters3;
                            p13 = p12;
                            f = DIFF_MODSIZE_CUTOFF;
                        }
                    } else {
                        i2 = i3;
                        p1 = p13;
                        possibleCenters2 = possibleCenters5;
                        z3 = z6;
                        z4 = z7;
                    }
                    i22++;
                    i3 = i2;
                    z7 = z4;
                    z6 = z3;
                    possibleCenters5 = possibleCenters2;
                    p13 = p1;
                }
            }
            i1++;
            i3 = i;
            z7 = z2;
            z6 = z;
            possibleCenters5 = possibleCenters;
        }
        if (!results.isEmpty()) {
            return (FinderPattern[][]) results.toArray(new FinderPattern[results.size()]);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public FinderPatternInfo[] findMulti(Map<DecodeHintType, ?> hints) throws NotFoundException {
        boolean tryHarder = hints != null && hints.containsKey(DecodeHintType.TRY_HARDER);
        BitMatrix image = getImage();
        int maxI = image.getHeight();
        int maxJ = image.getWidth();
        int i = (maxI * 3) / 388;
        int iSkip = i;
        iSkip = (i < 3 || tryHarder) ? 3 : 3;
        int[] stateCount = new int[5];
        for (int i2 = iSkip - 1; i2 < maxI; i2 += iSkip) {
            clearCounts(stateCount);
            int currentState = 0;
            for (int j = 0; j < maxJ; j++) {
                if (image.get(j, i2)) {
                    if ((currentState & 1) == 1) {
                        currentState++;
                    }
                    stateCount[currentState] = stateCount[currentState] + 1;
                } else if ((currentState & 1) == 0) {
                    if (currentState == 4) {
                        if (foundPatternCross(stateCount) && handlePossibleCenter(stateCount, i2, j)) {
                            currentState = 0;
                            clearCounts(stateCount);
                        } else {
                            shiftCounts2(stateCount);
                            currentState = 3;
                        }
                    } else {
                        currentState++;
                        stateCount[currentState] = stateCount[currentState] + 1;
                    }
                } else {
                    stateCount[currentState] = stateCount[currentState] + 1;
                }
            }
            if (foundPatternCross(stateCount)) {
                handlePossibleCenter(stateCount, i2, maxJ);
            }
        }
        FinderPattern[][] patternInfo = selectMutipleBestPatterns();
        List<FinderPatternInfo> result = new ArrayList<>();
        for (FinderPattern[] pattern : patternInfo) {
            ResultPoint.orderBestPatterns(pattern);
            result.add(new FinderPatternInfo(pattern));
        }
        if (result.isEmpty()) {
            return EMPTY_RESULT_ARRAY;
        }
        return (FinderPatternInfo[]) result.toArray(new FinderPatternInfo[result.size()]);
    }
}
