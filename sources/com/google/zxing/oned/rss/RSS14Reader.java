package com.google.zxing.oned.rss;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class RSS14Reader extends AbstractRSSReader {
    private final List<Pair> possibleLeftPairs = new ArrayList();
    private final List<Pair> possibleRightPairs = new ArrayList();
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = {1, 10, 34, 70, 126};
    private static final int[] INSIDE_ODD_TOTAL_SUBSET = {4, 20, 48, 81};
    private static final int[] OUTSIDE_GSUM = {0, 161, 961, 2015, 2715};
    private static final int[] INSIDE_GSUM = {0, 336, 1036, 1516};
    private static final int[] OUTSIDE_ODD_WIDEST = {8, 6, 4, 3, 1};
    private static final int[] INSIDE_ODD_WIDEST = {2, 4, 6, 8};
    private static final int[][] FINDER_PATTERNS = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int rowNumber, BitArray row, Map<DecodeHintType, ?> hints) throws NotFoundException {
        Pair leftPair = decodePair(row, false, rowNumber, hints);
        addOrTally(this.possibleLeftPairs, leftPair);
        row.reverse();
        Pair rightPair = decodePair(row, true, rowNumber, hints);
        addOrTally(this.possibleRightPairs, rightPair);
        row.reverse();
        for (Pair left : this.possibleLeftPairs) {
            if (left.getCount() > 1) {
                for (Pair right : this.possibleRightPairs) {
                    if (right.getCount() > 1 && checkChecksum(left, right)) {
                        return constructResult(left, right);
                    }
                }
                continue;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void addOrTally(Collection<Pair> possiblePairs, Pair pair) {
        if (pair == null) {
            return;
        }
        boolean found = false;
        Iterator<Pair> it = possiblePairs.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Pair other = it.next();
            if (other.getValue() == pair.getValue()) {
                other.incrementCount();
                found = true;
                break;
            }
        }
        if (!found) {
            possiblePairs.add(pair);
        }
    }

    @Override // com.google.zxing.oned.OneDReader, com.google.zxing.Reader
    public void reset() {
        this.possibleLeftPairs.clear();
        this.possibleRightPairs.clear();
    }

    private static Result constructResult(Pair leftPair, Pair rightPair) {
        String text = String.valueOf((leftPair.getValue() * 4537077) + rightPair.getValue());
        StringBuilder buffer = new StringBuilder(14);
        for (int i = 13 - text.length(); i > 0; i--) {
            buffer.append('0');
        }
        buffer.append(text);
        int checkDigit = 0;
        for (int i2 = 0; i2 < 13; i2++) {
            int digit = buffer.charAt(i2) - '0';
            checkDigit += (i2 & 1) == 0 ? digit * 3 : digit;
        }
        int i3 = 10 - (checkDigit % 10);
        int checkDigit2 = i3;
        if (i3 == 10) {
            checkDigit2 = 0;
        }
        buffer.append(checkDigit2);
        ResultPoint[] leftPoints = leftPair.getFinderPattern().getResultPoints();
        ResultPoint[] rightPoints = rightPair.getFinderPattern().getResultPoints();
        return new Result(buffer.toString(), null, new ResultPoint[]{leftPoints[0], leftPoints[1], rightPoints[0], rightPoints[1]}, BarcodeFormat.RSS_14);
    }

    private static boolean checkChecksum(Pair leftPair, Pair rightPair) {
        int checkValue = (leftPair.getChecksumPortion() + (rightPair.getChecksumPortion() * 16)) % 79;
        int value = (leftPair.getFinderPattern().getValue() * 9) + rightPair.getFinderPattern().getValue();
        int targetCheckValue = value;
        if (value > 72) {
            targetCheckValue--;
        }
        if (targetCheckValue > 8) {
            targetCheckValue--;
        }
        return checkValue == targetCheckValue;
    }

    private Pair decodePair(BitArray row, boolean right, int rowNumber, Map<DecodeHintType, ?> hints) {
        ResultPointCallback resultPointCallback;
        try {
            int[] startEnd = findFinderPattern(row, right);
            try {
                FinderPattern pattern = parseFoundFinderPattern(row, rowNumber, right, startEnd);
                if (hints == null) {
                    resultPointCallback = null;
                } else {
                    resultPointCallback = (ResultPointCallback) hints.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                }
                ResultPointCallback resultPointCallback2 = resultPointCallback;
                if (resultPointCallback != null) {
                    float center = (startEnd[0] + startEnd[1]) / 2.0f;
                    if (right) {
                        center = (row.getSize() - 1) - center;
                    }
                    resultPointCallback2.foundPossibleResultPoint(new ResultPoint(center, rowNumber));
                }
                DataCharacter outside = decodeDataCharacter(row, pattern, true);
                DataCharacter inside = decodeDataCharacter(row, pattern, false);
                return new Pair((outside.getValue() * 1597) + inside.getValue(), outside.getChecksumPortion() + (inside.getChecksumPortion() * 4), pattern);
            } catch (NotFoundException e) {
                return null;
            }
        } catch (NotFoundException e2) {
        }
    }

    private DataCharacter decodeDataCharacter(BitArray row, FinderPattern pattern, boolean outsideChar) throws NotFoundException {
        int[] counters = getDataCharacterCounters();
        for (int x = 0; x < counters.length; x++) {
            counters[x] = 0;
        }
        if (outsideChar) {
            recordPatternInReverse(row, pattern.getStartEnd()[0], counters);
        } else {
            recordPattern(row, pattern.getStartEnd()[1] + 1, counters);
            int i = 0;
            for (int j = counters.length - 1; i < j; j--) {
                int temp = counters[i];
                counters[i] = counters[j];
                counters[j] = temp;
                i++;
            }
        }
        int numModules = outsideChar ? 16 : 15;
        float elementWidth = MathUtils.sum(counters) / numModules;
        int[] oddCounts = getOddCounts();
        int[] evenCounts = getEvenCounts();
        float[] oddRoundingErrors = getOddRoundingErrors();
        float[] evenRoundingErrors = getEvenRoundingErrors();
        for (int i2 = 0; i2 < counters.length; i2++) {
            float value = counters[i2] / elementWidth;
            int i3 = (int) (value + 0.5f);
            int count = i3;
            if (i3 <= 0) {
                count = 1;
            } else if (count > 8) {
                count = 8;
            }
            int offset = i2 / 2;
            if ((i2 & 1) == 0) {
                oddCounts[offset] = count;
                oddRoundingErrors[offset] = value - count;
            } else {
                evenCounts[offset] = count;
                evenRoundingErrors[offset] = value - count;
            }
        }
        adjustOddEvenCounts(outsideChar, numModules);
        int oddSum = 0;
        int oddChecksumPortion = 0;
        for (int i4 = oddCounts.length - 1; i4 >= 0; i4--) {
            oddChecksumPortion = (oddChecksumPortion * 9) + oddCounts[i4];
            oddSum += oddCounts[i4];
        }
        int evenChecksumPortion = 0;
        int evenSum = 0;
        for (int i5 = evenCounts.length - 1; i5 >= 0; i5--) {
            evenChecksumPortion = (evenChecksumPortion * 9) + evenCounts[i5];
            evenSum += evenCounts[i5];
        }
        int i6 = evenChecksumPortion * 3;
        int checksumPortion = i6 + oddChecksumPortion;
        if (outsideChar) {
            if ((oddSum & 1) != 0 || oddSum > 12 || oddSum < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int group = (12 - oddSum) / 2;
            int oddWidest = OUTSIDE_ODD_WIDEST[group];
            int evenWidest = 9 - oddWidest;
            int vOdd = RSSUtils.getRSSvalue(oddCounts, oddWidest, false);
            int vEven = RSSUtils.getRSSvalue(evenCounts, evenWidest, true);
            int tEven = OUTSIDE_EVEN_TOTAL_SUBSET[group];
            int gSum = OUTSIDE_GSUM[group];
            return new DataCharacter((vOdd * tEven) + vEven + gSum, checksumPortion);
        } else if ((evenSum & 1) != 0 || evenSum > 10 || evenSum < 4) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            int group2 = (10 - evenSum) / 2;
            int oddWidest2 = INSIDE_ODD_WIDEST[group2];
            int evenWidest2 = 9 - oddWidest2;
            int vOdd2 = RSSUtils.getRSSvalue(oddCounts, oddWidest2, true);
            int vEven2 = RSSUtils.getRSSvalue(evenCounts, evenWidest2, false);
            int tOdd = INSIDE_ODD_TOTAL_SUBSET[group2];
            int gSum2 = INSIDE_GSUM[group2];
            return new DataCharacter((vEven2 * tOdd) + vOdd2 + gSum2, checksumPortion);
        }
    }

    private int[] findFinderPattern(BitArray row, boolean rightFinderPattern) throws NotFoundException {
        int[] counters = getDecodeFinderCounters();
        counters[0] = 0;
        counters[1] = 0;
        counters[2] = 0;
        counters[3] = 0;
        int width = row.getSize();
        boolean isWhite = false;
        int rowOffset = 0;
        while (rowOffset < width) {
            isWhite = !row.get(rowOffset);
            if (rightFinderPattern == isWhite) {
                break;
            }
            rowOffset++;
        }
        int counterPosition = 0;
        int patternStart = rowOffset;
        for (int x = rowOffset; x < width; x++) {
            if (row.get(x) != isWhite) {
                counters[counterPosition] = counters[counterPosition] + 1;
            } else {
                if (counterPosition == 3) {
                    if (isFinderPattern(counters)) {
                        return new int[]{patternStart, x};
                    }
                    patternStart += counters[0] + counters[1];
                    counters[0] = counters[2];
                    counters[1] = counters[3];
                    counters[2] = 0;
                    counters[3] = 0;
                    counterPosition--;
                } else {
                    counterPosition++;
                }
                counters[counterPosition] = 1;
                isWhite = !isWhite;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern parseFoundFinderPattern(BitArray row, int rowNumber, boolean right, int[] startEnd) throws NotFoundException {
        int start;
        int end;
        boolean firstIsBlack = row.get(startEnd[0]);
        int firstElementStart = startEnd[0] - 1;
        while (firstElementStart >= 0 && firstIsBlack != row.get(firstElementStart)) {
            firstElementStart--;
        }
        int firstElementStart2 = firstElementStart + 1;
        int firstCounter = startEnd[0] - firstElementStart2;
        int[] counters = getDecodeFinderCounters();
        System.arraycopy(counters, 0, counters, 1, counters.length - 1);
        counters[0] = firstCounter;
        int value = parseFinderValue(counters, FINDER_PATTERNS);
        int end2 = startEnd[1];
        if (right) {
            int start2 = (row.getSize() - 1) - firstElementStart2;
            start = start2;
            end = (row.getSize() - 1) - end2;
        } else {
            start = firstElementStart2;
            end = end2;
        }
        return new FinderPattern(value, new int[]{firstElementStart2, startEnd[1]}, start, end, rowNumber);
    }

    private void adjustOddEvenCounts(boolean outsideChar, int numModules) throws NotFoundException {
        int oddSum = MathUtils.sum(getOddCounts());
        int evenSum = MathUtils.sum(getEvenCounts());
        boolean incrementOdd = false;
        boolean decrementOdd = false;
        boolean incrementEven = false;
        boolean decrementEven = false;
        if (outsideChar) {
            if (oddSum > 12) {
                decrementOdd = true;
            } else if (oddSum < 4) {
                incrementOdd = true;
            }
            if (evenSum > 12) {
                decrementEven = true;
            } else if (evenSum < 4) {
                incrementEven = true;
            }
        } else {
            if (oddSum > 11) {
                decrementOdd = true;
            } else if (oddSum < 5) {
                incrementOdd = true;
            }
            if (evenSum > 10) {
                decrementEven = true;
            } else if (evenSum < 4) {
                incrementEven = true;
            }
        }
        int mismatch = (oddSum + evenSum) - numModules;
        boolean oddParityBad = (oddSum & 1) == outsideChar;
        boolean evenParityBad = (evenSum & 1) == 1;
        if (mismatch == 1) {
            if (oddParityBad) {
                if (evenParityBad) {
                    throw NotFoundException.getNotFoundInstance();
                }
                decrementOdd = true;
            } else if (!evenParityBad) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                decrementEven = true;
            }
        } else if (mismatch == -1) {
            if (oddParityBad) {
                if (evenParityBad) {
                    throw NotFoundException.getNotFoundInstance();
                }
                incrementOdd = true;
            } else if (!evenParityBad) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                incrementEven = true;
            }
        } else if (mismatch == 0) {
            if (oddParityBad) {
                if (!evenParityBad) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (oddSum < evenSum) {
                    incrementOdd = true;
                    decrementEven = true;
                } else {
                    decrementOdd = true;
                    incrementEven = true;
                }
            } else if (evenParityBad) {
                throw NotFoundException.getNotFoundInstance();
            }
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
        if (incrementOdd) {
            if (decrementOdd) {
                throw NotFoundException.getNotFoundInstance();
            }
            increment(getOddCounts(), getOddRoundingErrors());
        }
        if (decrementOdd) {
            decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (incrementEven) {
            if (decrementEven) {
                throw NotFoundException.getNotFoundInstance();
            }
            increment(getEvenCounts(), getOddRoundingErrors());
        }
        if (decrementEven) {
            decrement(getEvenCounts(), getEvenRoundingErrors());
        }
    }
}
