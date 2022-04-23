package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI01AndOtherAIs extends AI01decoder {
    private static final int HEADER_SIZE = 4;

    AI01AndOtherAIs(BitArray information) {
        super(information);
    }

    public String parseInformation() throws NotFoundException, FormatException {
        StringBuilder sb = new StringBuilder();
        StringBuilder buff = sb;
        sb.append("(01)");
        int initialGtinPosition = buff.length();
        buff.append(getGeneralDecoder().extractNumericValueFromBitArray(4, 4));
        encodeCompressedGtinWithoutAI(buff, 8, initialGtinPosition);
        return getGeneralDecoder().decodeAllCodes(buff, 48);
    }
}
