package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import com.ibm.icu.text.SCSU;
import kotlin.text.Typography;

/* loaded from: classes.dex */
final class GeneralAppIdDecoder {
    private final BitArray information;
    private final CurrentParsingState current = new CurrentParsingState();
    private final StringBuilder buffer = new StringBuilder();

    GeneralAppIdDecoder(BitArray information) {
        this.information = information;
    }

    String decodeAllCodes(StringBuilder buff, int initialPosition) throws NotFoundException, FormatException {
        int currentPosition = initialPosition;
        String remaining = null;
        while (true) {
            DecodedInformation info = decodeGeneralPurposeField(currentPosition, remaining);
            String parsedFields = FieldParser.parseFieldsInGeneralPurpose(info.getNewString());
            if (parsedFields != null) {
                buff.append(parsedFields);
            }
            if (info.isRemaining()) {
                remaining = String.valueOf(info.getRemainingValue());
            } else {
                remaining = null;
            }
            if (currentPosition != info.getNewPosition()) {
                currentPosition = info.getNewPosition();
            } else {
                return buff.toString();
            }
        }
    }

    private boolean isStillNumeric(int pos) {
        if (pos + 7 > this.information.getSize()) {
            return pos + 4 <= this.information.getSize();
        }
        for (int i = pos; i < pos + 3; i++) {
            if (this.information.get(i)) {
                return true;
            }
        }
        return this.information.get(pos + 3);
    }

    private DecodedNumeric decodeNumeric(int pos) throws FormatException {
        if (pos + 7 > this.information.getSize()) {
            int numeric = extractNumericValueFromBitArray(pos, 4);
            if (numeric == 0) {
                return new DecodedNumeric(this.information.getSize(), 10, 10);
            }
            return new DecodedNumeric(this.information.getSize(), numeric - 1, 10);
        }
        int numeric2 = extractNumericValueFromBitArray(pos, 7);
        int digit1 = (numeric2 - 8) / 11;
        int digit2 = (numeric2 - 8) % 11;
        return new DecodedNumeric(pos + 7, digit1, digit2);
    }

    int extractNumericValueFromBitArray(int pos, int bits) {
        return extractNumericValueFromBitArray(this.information, pos, bits);
    }

    static int extractNumericValueFromBitArray(BitArray information, int pos, int bits) {
        int value = 0;
        for (int i = 0; i < bits; i++) {
            if (information.get(pos + i)) {
                value |= 1 << ((bits - i) - 1);
            }
        }
        return value;
    }

    DecodedInformation decodeGeneralPurposeField(int pos, String remaining) throws FormatException {
        this.buffer.setLength(0);
        if (remaining != null) {
            this.buffer.append(remaining);
        }
        this.current.setPosition(pos);
        DecodedInformation lastDecoded = parseBlocks();
        if (lastDecoded != null && lastDecoded.isRemaining()) {
            return new DecodedInformation(this.current.getPosition(), this.buffer.toString(), lastDecoded.getRemainingValue());
        }
        return new DecodedInformation(this.current.getPosition(), this.buffer.toString());
    }

    private DecodedInformation parseBlocks() throws FormatException {
        BlockParsedResult result;
        boolean isFinished;
        do {
            int initialPosition = this.current.getPosition();
            if (this.current.isAlpha()) {
                BlockParsedResult parseAlphaBlock = parseAlphaBlock();
                result = parseAlphaBlock;
                isFinished = parseAlphaBlock.isFinished();
            } else if (this.current.isIsoIec646()) {
                BlockParsedResult parseIsoIec646Block = parseIsoIec646Block();
                result = parseIsoIec646Block;
                isFinished = parseIsoIec646Block.isFinished();
            } else {
                BlockParsedResult parseNumericBlock = parseNumericBlock();
                result = parseNumericBlock;
                isFinished = parseNumericBlock.isFinished();
            }
            if (!(initialPosition != this.current.getPosition()) && !isFinished) {
                break;
            }
        } while (!isFinished);
        return result.getDecodedInformation();
    }

    private BlockParsedResult parseNumericBlock() throws FormatException {
        DecodedInformation information;
        while (isStillNumeric(this.current.getPosition())) {
            DecodedNumeric numeric = decodeNumeric(this.current.getPosition());
            this.current.setPosition(numeric.getNewPosition());
            if (numeric.isFirstDigitFNC1()) {
                if (numeric.isSecondDigitFNC1()) {
                    information = new DecodedInformation(this.current.getPosition(), this.buffer.toString());
                } else {
                    information = new DecodedInformation(this.current.getPosition(), this.buffer.toString(), numeric.getSecondDigit());
                }
                return new BlockParsedResult(information, true);
            }
            this.buffer.append(numeric.getFirstDigit());
            if (numeric.isSecondDigitFNC1()) {
                DecodedInformation information2 = new DecodedInformation(this.current.getPosition(), this.buffer.toString());
                return new BlockParsedResult(information2, true);
            }
            this.buffer.append(numeric.getSecondDigit());
        }
        if (isNumericToAlphaNumericLatch(this.current.getPosition())) {
            this.current.setAlpha();
            this.current.incrementPosition(4);
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult parseIsoIec646Block() throws FormatException {
        while (isStillIsoIec646(this.current.getPosition())) {
            DecodedChar iso = decodeIsoIec646(this.current.getPosition());
            this.current.setPosition(iso.getNewPosition());
            if (iso.isFNC1()) {
                DecodedInformation information = new DecodedInformation(this.current.getPosition(), this.buffer.toString());
                return new BlockParsedResult(information, true);
            }
            this.buffer.append(iso.getValue());
        }
        if (isAlphaOr646ToNumericLatch(this.current.getPosition())) {
            this.current.incrementPosition(3);
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.getPosition())) {
            if (this.current.getPosition() + 5 < this.information.getSize()) {
                this.current.incrementPosition(5);
            } else {
                this.current.setPosition(this.information.getSize());
            }
            this.current.setAlpha();
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult parseAlphaBlock() {
        while (isStillAlpha(this.current.getPosition())) {
            DecodedChar alpha = decodeAlphanumeric(this.current.getPosition());
            this.current.setPosition(alpha.getNewPosition());
            if (alpha.isFNC1()) {
                DecodedInformation information = new DecodedInformation(this.current.getPosition(), this.buffer.toString());
                return new BlockParsedResult(information, true);
            }
            this.buffer.append(alpha.getValue());
        }
        if (isAlphaOr646ToNumericLatch(this.current.getPosition())) {
            this.current.incrementPosition(3);
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.getPosition())) {
            if (this.current.getPosition() + 5 < this.information.getSize()) {
                this.current.incrementPosition(5);
            } else {
                this.current.setPosition(this.information.getSize());
            }
            this.current.setIsoIec646();
        }
        return new BlockParsedResult(false);
    }

    private boolean isStillIsoIec646(int pos) {
        int eightBitValue;
        if (pos + 5 > this.information.getSize()) {
            return false;
        }
        int fiveBitValue = extractNumericValueFromBitArray(pos, 5);
        if (fiveBitValue < 5 || fiveBitValue >= 16) {
            if (pos + 7 > this.information.getSize()) {
                return false;
            }
            int sevenBitValue = extractNumericValueFromBitArray(pos, 7);
            if (sevenBitValue < 64 || sevenBitValue >= 116) {
                return pos + 8 <= this.information.getSize() && (eightBitValue = extractNumericValueFromBitArray(pos, 8)) >= 232 && eightBitValue < 253;
            }
            return true;
        }
        return true;
    }

    private DecodedChar decodeIsoIec646(int pos) throws FormatException {
        char c;
        int fiveBitValue = extractNumericValueFromBitArray(pos, 5);
        if (fiveBitValue == 15) {
            return new DecodedChar(pos + 5, '$');
        }
        if (fiveBitValue >= 5 && fiveBitValue < 15) {
            return new DecodedChar(pos + 5, (char) ((fiveBitValue + 48) - 5));
        }
        int sevenBitValue = extractNumericValueFromBitArray(pos, 7);
        if (sevenBitValue >= 64 && sevenBitValue < 90) {
            return new DecodedChar(pos + 7, (char) (sevenBitValue + 1));
        }
        if (sevenBitValue >= 90 && sevenBitValue < 116) {
            return new DecodedChar(pos + 7, (char) (sevenBitValue + 7));
        }
        switch (extractNumericValueFromBitArray(pos, 8)) {
            case SCSU.UDEFINE0 /* 232 */:
                c = '!';
                break;
            case SCSU.UDEFINE1 /* 233 */:
                c = Typography.quote;
                break;
            case SCSU.UDEFINE2 /* 234 */:
                c = '%';
                break;
            case SCSU.UDEFINE3 /* 235 */:
                c = Typography.amp;
                break;
            case SCSU.UDEFINE4 /* 236 */:
                c = '\'';
                break;
            case SCSU.UDEFINE5 /* 237 */:
                c = '(';
                break;
            case SCSU.UDEFINE6 /* 238 */:
                c = ')';
                break;
            case SCSU.UDEFINE7 /* 239 */:
                c = '*';
                break;
            case SCSU.UQUOTEU /* 240 */:
                c = '+';
                break;
            case SCSU.UDEFINEX /* 241 */:
                c = ',';
                break;
            case SCSU.URESERVED /* 242 */:
                c = '-';
                break;
            case 243:
                c = '.';
                break;
            case 244:
                c = '/';
                break;
            case 245:
                c = ':';
                break;
            case 246:
                c = ';';
                break;
            case 247:
                c = Typography.less;
                break;
            case 248:
                c = '=';
                break;
            case SCSU.LATININDEX /* 249 */:
                c = Typography.greater;
                break;
            case 250:
                c = '?';
                break;
            case SCSU.GREEKINDEX /* 251 */:
                c = '_';
                break;
            case SCSU.ARMENIANINDEX /* 252 */:
                c = ' ';
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        return new DecodedChar(pos + 8, c);
    }

    private boolean isStillAlpha(int pos) {
        int sixBitValue;
        if (pos + 5 > this.information.getSize()) {
            return false;
        }
        int fiveBitValue = extractNumericValueFromBitArray(pos, 5);
        if (fiveBitValue < 5 || fiveBitValue >= 16) {
            return pos + 6 <= this.information.getSize() && (sixBitValue = extractNumericValueFromBitArray(pos, 6)) >= 16 && sixBitValue < 63;
        }
        return true;
    }

    private DecodedChar decodeAlphanumeric(int pos) {
        char c;
        int fiveBitValue = extractNumericValueFromBitArray(pos, 5);
        if (fiveBitValue == 15) {
            return new DecodedChar(pos + 5, '$');
        }
        if (fiveBitValue >= 5 && fiveBitValue < 15) {
            return new DecodedChar(pos + 5, (char) ((fiveBitValue + 48) - 5));
        }
        int sixBitValue = extractNumericValueFromBitArray(pos, 6);
        if (sixBitValue >= 32 && sixBitValue < 58) {
            return new DecodedChar(pos + 6, (char) (sixBitValue + 33));
        }
        switch (sixBitValue) {
            case 58:
                c = '*';
                break;
            case 59:
                c = ',';
                break;
            case 60:
                c = '-';
                break;
            case 61:
                c = '.';
                break;
            case 62:
                c = '/';
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: ".concat(String.valueOf(sixBitValue)));
        }
        return new DecodedChar(pos + 6, c);
    }

    private boolean isAlphaTo646ToAlphaLatch(int pos) {
        if (pos + 1 > this.information.getSize()) {
            return false;
        }
        for (int i = 0; i < 5 && i + pos < this.information.getSize(); i++) {
            if (i == 2) {
                if (!this.information.get(pos + 2)) {
                    return false;
                }
            } else if (this.information.get(pos + i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlphaOr646ToNumericLatch(int pos) {
        if (pos + 3 > this.information.getSize()) {
            return false;
        }
        for (int i = pos; i < pos + 3; i++) {
            if (this.information.get(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumericToAlphaNumericLatch(int pos) {
        if (pos + 1 > this.information.getSize()) {
            return false;
        }
        for (int i = 0; i < 4 && i + pos < this.information.getSize(); i++) {
            if (this.information.get(pos + i)) {
                return false;
            }
        }
        return true;
    }
}
