package com.google.zxing.oned;

/* loaded from: classes.dex */
public final class CodaBarWriter extends OneDimensionalCodeWriter {
    private static final char[] ALT_START_END_CHARS = {'T', 'N', '*', 'E'};
    private static final char[] CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED = {'/', ':', '+', '.'};
    private static final char DEFAULT_GUARD;
    private static final char[] START_END_CHARS;

    static {
        char[] cArr = {'A', 'B', 'C', 'D'};
        START_END_CHARS = cArr;
        DEFAULT_GUARD = cArr[0];
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String contents) {
        if (contents.length() < 2) {
            StringBuilder sb = new StringBuilder();
            char c = DEFAULT_GUARD;
            contents = sb.append(c).append(contents).append(c).toString();
        } else {
            char firstChar = Character.toUpperCase(contents.charAt(0));
            char lastChar = Character.toUpperCase(contents.charAt(contents.length() - 1));
            char[] cArr = START_END_CHARS;
            boolean startsNormal = CodaBarReader.arrayContains(cArr, firstChar);
            boolean endsNormal = CodaBarReader.arrayContains(cArr, lastChar);
            char[] cArr2 = ALT_START_END_CHARS;
            boolean startsAlt = CodaBarReader.arrayContains(cArr2, firstChar);
            boolean endsAlt = CodaBarReader.arrayContains(cArr2, lastChar);
            if (startsNormal) {
                if (!endsNormal) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(contents)));
                }
            } else if (startsAlt) {
                if (!endsAlt) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(contents)));
                }
            } else if (endsNormal || endsAlt) {
                throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(contents)));
            } else {
                StringBuilder sb2 = new StringBuilder();
                char c2 = DEFAULT_GUARD;
                contents = sb2.append(c2).append(contents).append(c2).toString();
            }
        }
        int resultLength = 20;
        for (int i = 1; i < contents.length() - 1; i++) {
            if (Character.isDigit(contents.charAt(i)) || contents.charAt(i) == '-' || contents.charAt(i) == '$') {
                resultLength += 9;
            } else if (CodaBarReader.arrayContains(CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED, contents.charAt(i))) {
                resultLength += 10;
            } else {
                throw new IllegalArgumentException("Cannot encode : '" + contents.charAt(i) + '\'');
            }
        }
        int i2 = contents.length();
        boolean[] result = new boolean[(i2 - 1) + resultLength];
        int position = 0;
        for (int index = 0; index < contents.length(); index++) {
            char c3 = Character.toUpperCase(contents.charAt(index));
            if (index == 0 || index == contents.length() - 1) {
                switch (c3) {
                    case '*':
                        c3 = 'C';
                        break;
                    case 'E':
                        c3 = 'D';
                        break;
                    case 'N':
                        c3 = 'B';
                        break;
                    case 'T':
                        c3 = 'A';
                        break;
                }
            }
            int code = 0;
            int i3 = 0;
            while (true) {
                if (i3 < CodaBarReader.ALPHABET.length) {
                    if (c3 != CodaBarReader.ALPHABET[i3]) {
                        i3++;
                    } else {
                        code = CodaBarReader.CHARACTER_ENCODINGS[i3];
                    }
                }
            }
            boolean color = true;
            int counter = 0;
            int bit = 0;
            while (bit < 7) {
                result[position] = color;
                position++;
                if (((code >> (6 - bit)) & 1) == 0 || counter == 1) {
                    color = !color;
                    bit++;
                    counter = 0;
                } else {
                    counter++;
                }
            }
            if (index < contents.length() - 1) {
                result[position] = false;
                position++;
            }
        }
        return result;
    }
}
