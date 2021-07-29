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

public final class RSS14Reader extends AbstractRSSReader {
    private static final int[][] FINDER_PATTERNS = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    private static final int[] INSIDE_GSUM = {0, 336, 1036, 1516};
    private static final int[] INSIDE_ODD_TOTAL_SUBSET = {4, 20, 48, 81};
    private static final int[] INSIDE_ODD_WIDEST = {2, 4, 6, 8};
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = {1, 10, 34, 70, 126};
    private static final int[] OUTSIDE_GSUM = {0, 161, 961, 2015, 2715};
    private static final int[] OUTSIDE_ODD_WIDEST = {8, 6, 4, 3, 1};
    private final List<Pair> possibleLeftPairs = new ArrayList();
    private final List<Pair> possibleRightPairs = new ArrayList();

    public Result decodeRow(int rowNumber, BitArray row, Map<DecodeHintType, ?> hints) throws NotFoundException {
        addOrTally(this.possibleLeftPairs, decodePair(row, false, rowNumber, hints));
        row.reverse();
        addOrTally(this.possibleRightPairs, decodePair(row, true, rowNumber, hints));
        row.reverse();
        for (Pair next : this.possibleLeftPairs) {
            Pair left = next;
            if (next.getCount() > 1) {
                for (Pair next2 : this.possibleRightPairs) {
                    Pair right = next2;
                    if (next2.getCount() > 1 && checkChecksum(left, right)) {
                        return constructResult(left, right);
                    }
                }
                continue;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void addOrTally(Collection<Pair> possiblePairs, Pair pair) {
        if (pair != null) {
            boolean found = false;
            Iterator<Pair> it = possiblePairs.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Pair next = it.next();
                Pair other = next;
                if (next.getValue() == pair.getValue()) {
                    other.incrementCount();
                    found = true;
                    break;
                }
            }
            if (!found) {
                possiblePairs.add(pair);
            }
        }
    }

    public void reset() {
        this.possibleLeftPairs.clear();
        this.possibleRightPairs.clear();
    }

    private static Result constructResult(Pair leftPair, Pair rightPair) {
        String text = String.valueOf((((long) leftPair.getValue()) * 4537077) + ((long) rightPair.getValue()));
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
        return new Result(buffer.toString(), (byte[]) null, new ResultPoint[]{leftPoints[0], leftPoints[1], rightPoints[0], rightPoints[1]}, BarcodeFormat.RSS_14);
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
        if (checkValue == targetCheckValue) {
            return true;
        }
        return false;
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
                    float center = ((float) (startEnd[0] + startEnd[1])) / 2.0f;
                    if (right) {
                        center = ((float) (row.getSize() - 1)) - center;
                    }
                    resultPointCallback2.foundPossibleResultPoint(new ResultPoint(center, (float) rowNumber));
                }
                DataCharacter outside = decodeDataCharacter(row, pattern, true);
                DataCharacter inside = decodeDataCharacter(row, pattern, false);
                return new Pair((outside.getValue() * 1597) + inside.getValue(), outside.getChecksumPortion() + (inside.getChecksumPortion() * 4), pattern);
            } catch (NotFoundException e) {
                return null;
            }
        } catch (NotFoundException e2) {
            return null;
        }
    }

    private DataCharacter decodeDataCharacter(BitArray row, FinderPattern pattern, boolean outsideChar) throws NotFoundException {
        BitArray bitArray = row;
        boolean z = outsideChar;
        int[] counters = getDataCharacterCounters();
        for (int x = 0; x < counters.length; x++) {
            counters[x] = 0;
        }
        if (z) {
            recordPatternInReverse(bitArray, pattern.getStartEnd()[0], counters);
        } else {
            recordPattern(bitArray, pattern.getStartEnd()[1] + 1, counters);
            int i = 0;
            for (int j = counters.length - 1; i < j; j--) {
                int temp = counters[i];
                counters[i] = counters[j];
                counters[j] = temp;
                i++;
            }
        }
        int numModules = z ? 16 : 15;
        float elementWidth = ((float) MathUtils.sum(counters)) / ((float) numModules);
        int[] oddCounts = getOddCounts();
        int[] evenCounts = getEvenCounts();
        float[] oddRoundingErrors = getOddRoundingErrors();
        float[] evenRoundingErrors = getEvenRoundingErrors();
        for (int i2 = 0; i2 < counters.length; i2++) {
            float f = ((float) counters[i2]) / elementWidth;
            float value = f;
            int i3 = (int) (f + 0.5f);
            int count = i3;
            if (i3 <= 0) {
                count = 1;
            } else if (count > 8) {
                count = 8;
            }
            int offset = i2 / 2;
            if ((i2 & 1) == 0) {
                oddCounts[offset] = count;
                oddRoundingErrors[offset] = value - ((float) count);
            } else {
                evenCounts[offset] = count;
                evenRoundingErrors[offset] = value - ((float) count);
            }
        }
        adjustOddEvenCounts(z, numModules);
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
        int checksumPortion = (evenChecksumPortion * 3) + oddChecksumPortion;
        if (!z) {
            if ((evenSum & 1) != 0 || evenSum > 10 || evenSum < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int group = (10 - evenSum) / 2;
            int oddWidest = INSIDE_ODD_WIDEST[group];
            int vOdd = RSSUtils.getRSSvalue(oddCounts, oddWidest, true);
            int i6 = oddWidest;
            int vEven = RSSUtils.getRSSvalue(evenCounts, 9 - oddWidest, false);
            int i7 = group;
            int i8 = vEven;
            return new DataCharacter((vEven * INSIDE_ODD_TOTAL_SUBSET[group]) + vOdd + INSIDE_GSUM[group], checksumPortion);
        } else if ((oddSum & 1) != 0 || oddSum > 12 || oddSum < 4) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            int group2 = (12 - oddSum) / 2;
            int oddWidest2 = OUTSIDE_ODD_WIDEST[group2];
            int evenWidest = 9 - oddWidest2;
            int[] iArr = counters;
            int vOdd2 = RSSUtils.getRSSvalue(oddCounts, oddWidest2, false);
            int i9 = oddWidest2;
            int vEven2 = RSSUtils.getRSSvalue(evenCounts, evenWidest, true);
            int i10 = evenWidest;
            int i11 = vEven2;
            return new DataCharacter((vOdd2 * OUTSIDE_EVEN_TOTAL_SUBSET[group2]) + vEven2 + OUTSIDE_GSUM[group2], checksumPortion);
        }
    }

    private int[] findFinderPattern(BitArray row, boolean rightFinderPattern) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        int[] counters = decodeFinderCounters;
        decodeFinderCounters[0] = 0;
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
                if (counterPosition != 3) {
                    counterPosition++;
                } else if (isFinderPattern(counters)) {
                    return new int[]{patternStart, x};
                } else {
                    patternStart += counters[0] + counters[1];
                    counters[0] = counters[2];
                    counters[1] = counters[3];
                    counters[2] = 0;
                    counters[3] = 0;
                    counterPosition--;
                }
                counters[counterPosition] = 1;
                isWhite = !isWhite;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern parseFoundFinderPattern(BitArray row, int rowNumber, boolean right, int[] startEnd) throws NotFoundException {
        int end;
        int start;
        BitArray bitArray = row;
        boolean firstIsBlack = bitArray.get(startEnd[0]);
        int firstElementStart = startEnd[0] - 1;
        while (firstElementStart >= 0 && firstIsBlack != bitArray.get(firstElementStart)) {
            firstElementStart--;
        }
        int firstElementStart2 = firstElementStart + 1;
        int[] decodeFinderCounters = getDecodeFinderCounters();
        int[] counters = decodeFinderCounters;
        System.arraycopy(decodeFinderCounters, 0, counters, 1, counters.length - 1);
        counters[0] = startEnd[0] - firstElementStart2;
        int value = parseFinderValue(counters, FINDER_PATTERNS);
        int start2 = firstElementStart2;
        int end2 = startEnd[1];
        if (right) {
            start = (row.getSize() - 1) - start2;
            end = (row.getSize() - 1) - end2;
        } else {
            start = start2;
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
        boolean evenParityBad = false;
        boolean oddParityBad = (oddSum & true) == outsideChar;
        if ((evenSum & 1) == 1) {
            evenParityBad = true;
        }
        if (mismatch == 1) {
            if (oddParityBad) {
                if (!evenParityBad) {
                    decrementOdd = true;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (evenParityBad) {
                decrementEven = true;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else if (mismatch == -1) {
            if (oddParityBad) {
                if (!evenParityBad) {
                    incrementOdd = true;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (evenParityBad) {
                incrementEven = true;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else if (mismatch != 0) {
            throw NotFoundException.getNotFoundInstance();
        } else if (oddParityBad) {
            if (!evenParityBad) {
                throw NotFoundException.getNotFoundInstance();
            } else if (oddSum < evenSum) {
                incrementOdd = true;
                decrementEven = true;
            } else {
                decrementOdd = true;
                incrementEven = true;
            }
        } else if (evenParityBad) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (incrementOdd) {
            if (!decrementOdd) {
                increment(getOddCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (decrementOdd) {
            decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (incrementEven) {
            if (!decrementEven) {
                increment(getEvenCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (decrementEven) {
            decrement(getEvenCounts(), getEvenRoundingErrors());
        }
    }
}
