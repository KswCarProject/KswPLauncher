package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI013x0x1xDecoder extends AI01weightDecoder {
    private static final int DATE_SIZE = 16;
    private static final int HEADER_SIZE = 8;
    private static final int WEIGHT_SIZE = 20;
    private final String dateCode;
    private final String firstAIdigits;

    AI013x0x1xDecoder(BitArray information, String firstAIdigits2, String dateCode2) {
        super(information);
        this.dateCode = dateCode2;
        this.firstAIdigits = firstAIdigits2;
    }

    public String parseInformation() throws NotFoundException {
        if (getInformation().getSize() == 84) {
            StringBuilder buf = new StringBuilder();
            encodeCompressedGtin(buf, 8);
            encodeCompressedWeight(buf, 48, 20);
            encodeCompressedDate(buf, 68);
            return buf.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void encodeCompressedDate(StringBuilder buf, int currentPos) {
        int extractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray(currentPos, 16);
        int numericDate = extractNumericValueFromBitArray;
        if (extractNumericValueFromBitArray != 38400) {
            buf.append('(');
            buf.append(this.dateCode);
            buf.append(')');
            int day = numericDate % 32;
            int i = numericDate / 32;
            int numericDate2 = i;
            int month = (i % 12) + 1;
            int i2 = numericDate2 / 12;
            int numericDate3 = i2;
            if (i2 / 10 == 0) {
                buf.append('0');
            }
            buf.append(numericDate3);
            if (month / 10 == 0) {
                buf.append('0');
            }
            buf.append(month);
            if (day / 10 == 0) {
                buf.append('0');
            }
            buf.append(day);
        }
    }

    /* access modifiers changed from: protected */
    public void addWeightCode(StringBuilder buf, int weight) {
        buf.append('(');
        buf.append(this.firstAIdigits);
        buf.append(weight / 100000);
        buf.append(')');
    }

    /* access modifiers changed from: protected */
    public int checkWeight(int weight) {
        return weight % 100000;
    }
}
