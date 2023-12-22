package com.google.zxing.datamatrix.encoder;

/* loaded from: classes.dex */
final class ASCIIEncoder implements Encoder {
    ASCIIEncoder() {
    }

    @Override // com.google.zxing.datamatrix.encoder.Encoder
    public int getEncodingMode() {
        return 0;
    }

    @Override // com.google.zxing.datamatrix.encoder.Encoder
    public void encode(EncoderContext context) {
        if (HighLevelEncoder.determineConsecutiveDigitCount(context.getMessage(), context.pos) >= 2) {
            context.writeCodeword(encodeASCIIDigits(context.getMessage().charAt(context.pos), context.getMessage().charAt(context.pos + 1)));
            context.pos += 2;
            return;
        }
        char c = context.getCurrentChar();
        int newMode = HighLevelEncoder.lookAheadTest(context.getMessage(), context.pos, getEncodingMode());
        if (newMode != getEncodingMode()) {
            switch (newMode) {
                case 1:
                    context.writeCodeword('\u00e6');
                    context.signalEncoderChange(1);
                    return;
                case 2:
                    context.writeCodeword('\u00ef');
                    context.signalEncoderChange(2);
                    return;
                case 3:
                    context.writeCodeword('\u00ee');
                    context.signalEncoderChange(3);
                    return;
                case 4:
                    context.writeCodeword('\u00f0');
                    context.signalEncoderChange(4);
                    return;
                case 5:
                    context.writeCodeword('\u00e7');
                    context.signalEncoderChange(5);
                    return;
                default:
                    throw new IllegalStateException("Illegal mode: ".concat(String.valueOf(newMode)));
            }
        } else if (HighLevelEncoder.isExtendedASCII(c)) {
            context.writeCodeword('\u00eb');
            context.writeCodeword((char) ((c - '\u0080') + 1));
            context.pos++;
        } else {
            context.writeCodeword((char) (c + 1));
            context.pos++;
        }
    }

    private static char encodeASCIIDigits(char digit1, char digit2) {
        if (HighLevelEncoder.isDigit(digit1) && HighLevelEncoder.isDigit(digit2)) {
            return (char) (((digit1 - '0') * 10) + (digit2 - '0') + 130);
        }
        throw new IllegalArgumentException("not digits: " + digit1 + digit2);
    }
}
