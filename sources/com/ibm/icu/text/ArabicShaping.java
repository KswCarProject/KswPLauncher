package com.ibm.icu.text;

import com.ibm.icu.impl.UBiDiProps;

/* loaded from: classes.dex */
public final class ArabicShaping {
    private static final int ALEFTYPE = 32;
    private static final int DESHAPE_MODE = 1;
    public static final int DIGITS_AN2EN = 64;
    public static final int DIGITS_EN2AN = 32;
    public static final int DIGITS_EN2AN_INIT_AL = 128;
    public static final int DIGITS_EN2AN_INIT_LR = 96;
    public static final int DIGITS_MASK = 224;
    public static final int DIGITS_NOOP = 0;
    public static final int DIGIT_TYPE_AN = 0;
    public static final int DIGIT_TYPE_AN_EXTENDED = 256;
    public static final int DIGIT_TYPE_MASK = 256;
    private static final char HAMZA06_CHAR = '\u0621';
    private static final char HAMZAFE_CHAR = '\ufe80';
    private static final int IRRELEVANT = 4;
    public static final int LAMALEF_AUTO = 65536;
    public static final int LAMALEF_BEGIN = 3;
    public static final int LAMALEF_END = 2;
    public static final int LAMALEF_MASK = 65539;
    public static final int LAMALEF_NEAR = 1;
    public static final int LAMALEF_RESIZE = 0;
    private static final char LAMALEF_SPACE_SUB = '\uffff';
    private static final int LAMTYPE = 16;
    private static final char LAM_CHAR = '\u0644';
    public static final int LENGTH_FIXED_SPACES_AT_BEGINNING = 3;
    public static final int LENGTH_FIXED_SPACES_AT_END = 2;
    public static final int LENGTH_FIXED_SPACES_NEAR = 1;
    public static final int LENGTH_GROW_SHRINK = 0;
    public static final int LENGTH_MASK = 65539;
    public static final int LETTERS_MASK = 24;
    public static final int LETTERS_NOOP = 0;
    public static final int LETTERS_SHAPE = 8;
    public static final int LETTERS_SHAPE_TASHKEEL_ISOLATED = 24;
    public static final int LETTERS_UNSHAPE = 16;
    private static final int LINKL = 2;
    private static final int LINKR = 1;
    private static final int LINK_MASK = 3;
    private static final char NEW_TAIL_CHAR = '\ufe73';
    private static final char OLD_TAIL_CHAR = '\u200b';
    public static final int SEEN_MASK = 7340032;
    public static final int SEEN_TWOCELL_NEAR = 2097152;
    private static final char SHADDA06_CHAR = '\u0651';
    private static final char SHADDA_CHAR = '\ufe7c';
    private static final char SHADDA_TATWEEL_CHAR = '\ufe7d';
    private static final int SHAPE_MODE = 0;
    public static final int SHAPE_TAIL_NEW_UNICODE = 134217728;
    public static final int SHAPE_TAIL_TYPE_MASK = 134217728;
    public static final int SPACES_RELATIVE_TO_TEXT_BEGIN_END = 67108864;
    public static final int SPACES_RELATIVE_TO_TEXT_MASK = 67108864;
    private static final char SPACE_CHAR = ' ';
    public static final int TASHKEEL_BEGIN = 262144;
    public static final int TASHKEEL_END = 393216;
    public static final int TASHKEEL_MASK = 917504;
    public static final int TASHKEEL_REPLACE_BY_TATWEEL = 786432;
    public static final int TASHKEEL_RESIZE = 524288;
    private static final char TASHKEEL_SPACE_SUB = '\ufffe';
    private static final char TATWEEL_CHAR = '\u0640';
    public static final int TEXT_DIRECTION_LOGICAL = 0;
    public static final int TEXT_DIRECTION_MASK = 4;
    public static final int TEXT_DIRECTION_VISUAL_LTR = 4;
    public static final int TEXT_DIRECTION_VISUAL_RTL = 0;
    public static final int YEHHAMZA_MASK = 58720256;
    public static final int YEHHAMZA_TWOCELL_NEAR = 16777216;
    private static final char YEH_HAMZAFE_CHAR = '\ufe89';
    private static final char YEH_HAMZA_CHAR = '\u0626';
    private boolean isLogical;
    private final int options;
    private boolean spacesRelativeToTextBeginEnd;
    private char tailChar;
    private static final int[] irrelevantPos = {0, 2, 4, 6, 8, 10, 12, 14};
    private static final int[] tailFamilyIsolatedFinal = {1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1};
    private static final int[] tashkeelMedial = {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
    private static final char[] yehHamzaToYeh = {'\ufeef', '\ufef0'};
    private static final char[] convertNormalizedLamAlef = {'\u0622', '\u0623', '\u0625', '\u0627'};
    private static final int[] araLink = {4385, 4897, 5377, 5921, 6403, 7457, 7939, 8961, 9475, 10499, 11523, 12547, 13571, 14593, 15105, 15617, 16129, 16643, 17667, 18691, 19715, 20739, 21763, 22787, 23811, 0, 0, 0, 0, 0, 3, 24835, 25859, 26883, 27923, 28931, 29955, 30979, 32001, 32513, 33027, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 34049, 34561, 35073, 35585, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33, 33, 0, 33, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 3, 3, 3, 1, 1};
    private static final int[] presLink = {3, 3, 3, 0, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 32, 33, 32, 33, 0, 1, 32, 33, 0, 2, 3, 1, 32, 33, 0, 2, 3, 1, 0, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 16, 18, 19, 17, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 1, 0, 1, 0, 2, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1};
    private static int[] convertFEto06 = {1611, 1611, 1612, 1612, 1613, 1613, 1614, 1614, 1615, 1615, 1616, 1616, 1617, 1617, 1618, 1618, 1569, 1570, 1570, 1571, 1571, 1572, 1572, 1573, 1573, 1574, 1574, 1574, 1574, 1575, 1575, 1576, 1576, 1576, 1576, 1577, 1577, 1578, 1578, 1578, 1578, 1579, 1579, 1579, 1579, 1580, 1580, 1580, 1580, 1581, 1581, 1581, 1581, 1582, 1582, 1582, 1582, 1583, 1583, 1584, 1584, 1585, 1585, 1586, 1586, 1587, 1587, 1587, 1587, 1588, 1588, 1588, 1588, 1589, 1589, 1589, 1589, 1590, 1590, 1590, 1590, 1591, 1591, 1591, 1591, 1592, 1592, 1592, 1592, 1593, 1593, 1593, 1593, 1594, 1594, 1594, 1594, 1601, 1601, 1601, 1601, 1602, 1602, 1602, 1602, 1603, 1603, 1603, 1603, 1604, 1604, 1604, 1604, 1605, 1605, 1605, 1605, 1606, 1606, 1606, 1606, 1607, 1607, 1607, 1607, 1608, 1608, 1609, 1609, 1610, 1610, 1610, 1610, 1628, 1628, 1629, 1629, 1630, 1630, 1631, 1631};
    private static final int[][][] shapeTable = {new int[][]{new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{0, 1, 0, 3}, new int[]{0, 1, 0, 1}}, new int[][]{new int[]{0, 0, 2, 2}, new int[]{0, 0, 1, 2}, new int[]{0, 1, 1, 2}, new int[]{0, 1, 1, 3}}, new int[][]{new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{0, 1, 0, 3}, new int[]{0, 1, 0, 3}}, new int[][]{new int[]{0, 0, 1, 2}, new int[]{0, 0, 1, 2}, new int[]{0, 1, 1, 2}, new int[]{0, 1, 1, 3}}};

    public int shape(char[] source, int sourceStart, int sourceLength, char[] dest, int destStart, int destSize) throws ArabicShapingException {
        if (source != null) {
            if (sourceStart < 0 || sourceLength < 0 || sourceStart + sourceLength > source.length) {
                throw new IllegalArgumentException("bad source start (" + sourceStart + ") or length (" + sourceLength + ") for buffer of length " + source.length);
            }
            if (dest == null && destSize != 0) {
                throw new IllegalArgumentException("null dest requires destSize == 0");
            }
            if (destSize != 0 && (destStart < 0 || destSize < 0 || destStart + destSize > dest.length)) {
                throw new IllegalArgumentException("bad dest start (" + destStart + ") or size (" + destSize + ") for buffer of length " + dest.length);
            }
            int i = this.options;
            if ((i & TASHKEEL_MASK) != 0 && (i & TASHKEEL_MASK) != 262144 && (i & TASHKEEL_MASK) != 393216 && (i & TASHKEEL_MASK) != 524288 && (i & TASHKEEL_MASK) != 786432) {
                throw new IllegalArgumentException("Wrong Tashkeel argument");
            }
            if ((i & 65539) != 0 && (i & 65539) != 3 && (i & 65539) != 2 && (i & 65539) != 0 && (i & 65539) != 65536 && (65539 & i) != 1) {
                throw new IllegalArgumentException("Wrong Lam Alef argument");
            }
            if ((917504 & i) != 0 && (i & 24) == 16) {
                throw new IllegalArgumentException("Tashkeel replacement should not be enabled in deshaping mode ");
            }
            return internalShape(source, sourceStart, sourceLength, dest, destStart, destSize);
        }
        throw new IllegalArgumentException("source can not be null");
    }

    public void shape(char[] source, int start, int length) throws ArabicShapingException {
        if ((this.options & 65539) == 0) {
            throw new ArabicShapingException("Cannot shape in place with length option resize.");
        }
        shape(source, start, length, source, start, length);
    }

    public String shape(String text) throws ArabicShapingException {
        char[] dest;
        char[] src = text.toCharArray();
        int i = this.options;
        if ((65539 & i) == 0 && (i & 24) == 16) {
            char[] dest2 = new char[src.length * 2];
            dest = dest2;
        } else {
            dest = src;
        }
        int len = shape(src, 0, src.length, dest, 0, dest.length);
        return new String(dest, 0, len);
    }

    public ArabicShaping(int options) {
        this.options = options;
        if ((options & 224) > 128) {
            throw new IllegalArgumentException("bad DIGITS options");
        }
        this.isLogical = (options & 4) == 0;
        this.spacesRelativeToTextBeginEnd = (options & 67108864) == 67108864;
        if ((options & 134217728) == 134217728) {
            this.tailChar = NEW_TAIL_CHAR;
        } else {
            this.tailChar = OLD_TAIL_CHAR;
        }
    }

    public boolean equals(Object rhs) {
        return rhs != null && rhs.getClass() == ArabicShaping.class && this.options == ((ArabicShaping) rhs).options;
    }

    public int hashCode() {
        return this.options;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder(super.toString());
        buf.append('[');
        switch (this.options & 65539) {
            case 0:
                buf.append("LamAlef resize");
                break;
            case 1:
                buf.append("LamAlef spaces at near");
                break;
            case 2:
                buf.append("LamAlef spaces at end");
                break;
            case 3:
                buf.append("LamAlef spaces at begin");
                break;
            case 65536:
                buf.append("lamAlef auto");
                break;
        }
        switch (this.options & 4) {
            case 0:
                buf.append(", logical");
                break;
            case 4:
                buf.append(", visual");
                break;
        }
        switch (this.options & 24) {
            case 0:
                buf.append(", no letter shaping");
                break;
            case 8:
                buf.append(", shape letters");
                break;
            case 16:
                buf.append(", unshape letters");
                break;
            case 24:
                buf.append(", shape letters tashkeel isolated");
                break;
        }
        switch (this.options & SEEN_MASK) {
            case 2097152:
                buf.append(", Seen at near");
                break;
        }
        switch (this.options & YEHHAMZA_MASK) {
            case 16777216:
                buf.append(", Yeh Hamza at near");
                break;
        }
        switch (this.options & TASHKEEL_MASK) {
            case 262144:
                buf.append(", Tashkeel at begin");
                break;
            case TASHKEEL_END /* 393216 */:
                buf.append(", Tashkeel at end");
                break;
            case 524288:
                buf.append(", Tashkeel resize");
                break;
            case TASHKEEL_REPLACE_BY_TATWEEL /* 786432 */:
                buf.append(", Tashkeel replace with tatweel");
                break;
        }
        switch (this.options & 224) {
            case 0:
                buf.append(", no digit shaping");
                break;
            case 32:
                buf.append(", shape digits to AN");
                break;
            case 64:
                buf.append(", shape digits to EN");
                break;
            case 96:
                buf.append(", shape digits to AN contextually: default EN");
                break;
            case 128:
                buf.append(", shape digits to AN contextually: default AL");
                break;
        }
        switch (this.options & 256) {
            case 0:
                buf.append(", standard Arabic-Indic digits");
                break;
            case 256:
                buf.append(", extended Arabic-Indic digits");
                break;
        }
        buf.append("]");
        return buf.toString();
    }

    private void shapeToArabicDigitsWithContext(char[] dest, int start, int length, char digitBase, boolean lastStrongWasAL) {
        UBiDiProps bdp = UBiDiProps.INSTANCE;
        char digitBase2 = (char) (digitBase - '0');
        int i = start + length;
        while (true) {
            i--;
            if (i >= start) {
                char ch = dest[i];
                switch (bdp.getClass(ch)) {
                    case 0:
                    case 1:
                        lastStrongWasAL = false;
                        break;
                    case 2:
                        if (lastStrongWasAL && ch <= '9') {
                            dest[i] = (char) (ch + digitBase2);
                            break;
                        }
                        break;
                    case 13:
                        lastStrongWasAL = true;
                        break;
                }
            } else {
                return;
            }
        }
    }

    private static void invertBuffer(char[] buffer, int start, int length) {
        int i = start;
        for (int j = (start + length) - 1; i < j; j--) {
            char temp = buffer[i];
            buffer[i] = buffer[j];
            buffer[j] = temp;
            i++;
        }
    }

    private static char changeLamAlef(char ch) {
        switch (ch) {
            case '\u0622':
                return '\u065c';
            case '\u0623':
                return '\u065d';
            case '\u0624':
            case '\u0626':
            default:
                return (char) 0;
            case '\u0625':
                return '\u065e';
            case '\u0627':
                return '\u065f';
        }
    }

    private static int specialChar(char ch) {
        if ((ch <= '\u0621' || ch >= '\u0626') && ch != '\u0627') {
            if (ch <= '\u062e' || ch >= '\u0633') {
                if ((ch > '\u0647' && ch < '\u064a') || ch == '\u0629') {
                    return 1;
                }
                if (ch >= '\u064b' && ch <= '\u0652') {
                    return 2;
                }
                if ((ch < '\u0653' || ch > '\u0655') && ch != '\u0670') {
                    if (ch >= '\ufe70' && ch <= '\ufe7f') {
                        return 3;
                    }
                    return 0;
                }
                return 3;
            }
            return 1;
        }
        return 1;
    }

    private static int getLink(char ch) {
        if (ch >= '\u0622' && ch <= '\u06d3') {
            return araLink[ch - '\u0622'];
        }
        if (ch == '\u200d') {
            return 3;
        }
        if (ch >= '\u206d' && ch <= '\u206f') {
            return 4;
        }
        if (ch >= '\ufe70' && ch <= '\ufefc') {
            return presLink[ch - '\ufe70'];
        }
        return 0;
    }

    private static int countSpacesLeft(char[] dest, int start, int count) {
        int e = start + count;
        for (int i = start; i < e; i++) {
            if (dest[i] != ' ') {
                return i - start;
            }
        }
        return count;
    }

    private static int countSpacesRight(char[] dest, int start, int count) {
        int i = start + count;
        do {
            i--;
            if (i < start) {
                return count;
            }
        } while (dest[i] == ' ');
        return ((start + count) - 1) - i;
    }

    private static boolean isTashkeelChar(char ch) {
        return ch >= '\u064b' && ch <= '\u0652';
    }

    private static int isSeenTailFamilyChar(char ch) {
        if (ch >= '\ufeb1' && ch < '\ufebf') {
            return tailFamilyIsolatedFinal[ch - '\ufeb1'];
        }
        return 0;
    }

    private static int isSeenFamilyChar(char ch) {
        if (ch >= '\u0633' && ch <= '\u0636') {
            return 1;
        }
        return 0;
    }

    private static boolean isTailChar(char ch) {
        if (ch == '\u200b' || ch == '\ufe73') {
            return true;
        }
        return false;
    }

    private static boolean isAlefMaksouraChar(char ch) {
        return ch == '\ufeef' || ch == '\ufef0' || ch == '\u0649';
    }

    private static boolean isYehHamzaChar(char ch) {
        if (ch == '\ufe89' || ch == '\ufe8a') {
            return true;
        }
        return false;
    }

    private static boolean isTashkeelCharFE(char ch) {
        return ch != '\ufe75' && ch >= '\ufe70' && ch <= '\ufe7f';
    }

    private static int isTashkeelOnTatweelChar(char ch) {
        if (ch >= '\ufe70' && ch <= '\ufe7f' && ch != '\ufe73' && ch != '\ufe75' && ch != '\ufe7d') {
            return tashkeelMedial[ch - '\ufe70'];
        }
        if ((ch >= '\ufcf2' && ch <= '\ufcf4') || ch == '\ufe7d') {
            return 2;
        }
        return 0;
    }

    private static int isIsolatedTashkeelChar(char ch) {
        if (ch >= '\ufe70' && ch <= '\ufe7f' && ch != '\ufe73' && ch != '\ufe75') {
            return 1 - tashkeelMedial[ch - '\ufe70'];
        }
        if (ch >= '\ufc5e' && ch <= '\ufc63') {
            return 1;
        }
        return 0;
    }

    private static boolean isAlefChar(char ch) {
        return ch == '\u0622' || ch == '\u0623' || ch == '\u0625' || ch == '\u0627';
    }

    private static boolean isLamAlefChar(char ch) {
        return ch >= '\ufef5' && ch <= '\ufefc';
    }

    private static boolean isNormalizedLamAlefChar(char ch) {
        return ch >= '\u065c' && ch <= '\u065f';
    }

    private int calculateSize(char[] source, int sourceStart, int sourceLength) {
        int destSize = sourceLength;
        switch (this.options & 24) {
            case 8:
            case 24:
                if (this.isLogical) {
                    int e = (sourceStart + sourceLength) - 1;
                    for (int i = sourceStart; i < e; i++) {
                        if ((source[i] == '\u0644' && isAlefChar(source[i + 1])) || isTashkeelCharFE(source[i])) {
                            destSize--;
                        }
                    }
                    break;
                } else {
                    int e2 = sourceStart + sourceLength;
                    for (int i2 = sourceStart + 1; i2 < e2; i2++) {
                        if ((source[i2] == '\u0644' && isAlefChar(source[i2 - 1])) || isTashkeelCharFE(source[i2])) {
                            destSize--;
                        }
                    }
                    break;
                }
            case 16:
                int e3 = sourceStart + sourceLength;
                for (int i3 = sourceStart; i3 < e3; i3++) {
                    if (isLamAlefChar(source[i3])) {
                        destSize++;
                    }
                }
                break;
        }
        return destSize;
    }

    private static int countSpaceSub(char[] dest, int length, char subChar) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (dest[i] == subChar) {
                count++;
            }
        }
        return count;
    }

    private static void shiftArray(char[] dest, int start, int e, char subChar) {
        int w = e;
        int r = e;
        while (true) {
            r--;
            if (r >= start) {
                char ch = dest[r];
                if (ch != subChar && w - 1 != r) {
                    dest[w] = ch;
                }
            } else {
                return;
            }
        }
    }

    private static int flipArray(char[] dest, int start, int e, int w) {
        if (w > start) {
            int w2 = start;
            for (int r = w; r < e; r++) {
                dest[w2] = dest[r];
                w2++;
            }
            return w2;
        }
        return e;
    }

    private static int handleTashkeelWithTatweel(char[] dest, int sourceLength) {
        for (int i = 0; i < sourceLength; i++) {
            if (isTashkeelOnTatweelChar(dest[i]) == 1) {
                dest[i] = TATWEEL_CHAR;
            } else if (isTashkeelOnTatweelChar(dest[i]) == 2) {
                dest[i] = SHADDA_TATWEEL_CHAR;
            } else if (isIsolatedTashkeelChar(dest[i]) == 1 && dest[i] != '\ufe7c') {
                dest[i] = SPACE_CHAR;
            }
        }
        return sourceLength;
    }

    private int handleGeneratedSpaces(char[] dest, int start, int length) {
        int length2 = length;
        int i = this.options;
        int lenOptionsLamAlef = 65539 & i;
        int lenOptionsTashkeel = i & TASHKEEL_MASK;
        boolean lamAlefOn = false;
        boolean tashkeelOn = false;
        if ((!this.isLogical) & (!this.spacesRelativeToTextBeginEnd)) {
            switch (lenOptionsLamAlef) {
                case 2:
                    lenOptionsLamAlef = 3;
                    break;
                case 3:
                    lenOptionsLamAlef = 2;
                    break;
            }
            switch (lenOptionsTashkeel) {
                case 262144:
                    lenOptionsTashkeel = TASHKEEL_END;
                    break;
                case TASHKEEL_END /* 393216 */:
                    lenOptionsTashkeel = 262144;
                    break;
            }
        }
        if (lenOptionsLamAlef == 1) {
            int i2 = start;
            int e = i2 + length2;
            while (i2 < e) {
                if (dest[i2] == '\uffff') {
                    dest[i2] = SPACE_CHAR;
                }
                i2++;
            }
        } else {
            int e2 = start + length2;
            int wL = countSpaceSub(dest, length2, '\uffff');
            int wT = countSpaceSub(dest, length2, TASHKEEL_SPACE_SUB);
            if (lenOptionsLamAlef == 2) {
                lamAlefOn = true;
            }
            if (lenOptionsTashkeel == 393216) {
                tashkeelOn = true;
            }
            if (lamAlefOn && lenOptionsLamAlef == 2) {
                shiftArray(dest, start, e2, '\uffff');
                while (wL > start) {
                    wL--;
                    dest[wL] = SPACE_CHAR;
                }
            }
            if (tashkeelOn && lenOptionsTashkeel == 393216) {
                shiftArray(dest, start, e2, TASHKEEL_SPACE_SUB);
                while (wT > start) {
                    wT--;
                    dest[wT] = SPACE_CHAR;
                }
            }
            boolean lamAlefOn2 = false;
            boolean tashkeelOn2 = false;
            if (lenOptionsLamAlef == 0) {
                lamAlefOn2 = true;
            }
            if (lenOptionsTashkeel == 524288) {
                tashkeelOn2 = true;
            }
            if (lamAlefOn2 && lenOptionsLamAlef == 0) {
                shiftArray(dest, start, e2, '\uffff');
                wL = flipArray(dest, start, e2, wL);
                length2 = wL - start;
            }
            if (tashkeelOn2 && lenOptionsTashkeel == 524288) {
                shiftArray(dest, start, e2, TASHKEEL_SPACE_SUB);
                wT = flipArray(dest, start, e2, wT);
                length2 = wT - start;
            }
            boolean lamAlefOn3 = false;
            boolean tashkeelOn3 = false;
            lamAlefOn3 = (lenOptionsLamAlef == 3 || lenOptionsLamAlef == 65536) ? true : true;
            if (lenOptionsTashkeel == 262144) {
                tashkeelOn3 = true;
            }
            if (lamAlefOn3 && (lenOptionsLamAlef == 3 || lenOptionsLamAlef == 65536)) {
                shiftArray(dest, start, e2, '\uffff');
                for (int wL2 = flipArray(dest, start, e2, wL); wL2 < e2; wL2++) {
                    dest[wL2] = SPACE_CHAR;
                }
            }
            if (tashkeelOn3 && lenOptionsTashkeel == 262144) {
                shiftArray(dest, start, e2, TASHKEEL_SPACE_SUB);
                for (int wT2 = flipArray(dest, start, e2, wT); wT2 < e2; wT2++) {
                    dest[wT2] = SPACE_CHAR;
                }
            }
        }
        return length2;
    }

    private boolean expandCompositCharAtBegin(char[] dest, int start, int length, int lacount) {
        if (lacount > countSpacesRight(dest, start, length)) {
            return true;
        }
        int r = (start + length) - lacount;
        int w = start + length;
        while (true) {
            r--;
            if (r < start) {
                return false;
            }
            char ch = dest[r];
            if (isNormalizedLamAlefChar(ch)) {
                int w2 = w - 1;
                dest[w2] = LAM_CHAR;
                w = w2 - 1;
                dest[w] = convertNormalizedLamAlef[ch - '\u065c'];
            } else {
                w--;
                dest[w] = ch;
            }
        }
    }

    private boolean expandCompositCharAtEnd(char[] dest, int start, int length, int lacount) {
        if (lacount > countSpacesLeft(dest, start, length)) {
            return true;
        }
        int w = start;
        int e = start + length;
        for (int r = start + lacount; r < e; r++) {
            char ch = dest[r];
            if (isNormalizedLamAlefChar(ch)) {
                int w2 = w + 1;
                dest[w] = convertNormalizedLamAlef[ch - '\u065c'];
                w = w2 + 1;
                dest[w2] = LAM_CHAR;
            } else {
                dest[w] = ch;
                w++;
            }
        }
        return false;
    }

    private boolean expandCompositCharAtNear(char[] dest, int start, int length, int yehHamzaOption, int seenTailOption, int lamAlefOption) {
        if (isNormalizedLamAlefChar(dest[start])) {
            return true;
        }
        int i = start + length;
        while (true) {
            i--;
            if (i >= start) {
                char ch = dest[i];
                if (lamAlefOption == 1 && isNormalizedLamAlefChar(ch)) {
                    if (i > start && dest[i - 1] == ' ') {
                        dest[i] = LAM_CHAR;
                        i--;
                        dest[i] = convertNormalizedLamAlef[ch - '\u065c'];
                    } else {
                        return true;
                    }
                } else if (seenTailOption == 1 && isSeenTailFamilyChar(ch) == 1) {
                    if (i > start && dest[i - 1] == ' ') {
                        dest[i - 1] = this.tailChar;
                    } else {
                        return true;
                    }
                } else if (yehHamzaOption == 1 && isYehHamzaChar(ch)) {
                    if (i > start && dest[i - 1] == ' ') {
                        dest[i] = yehHamzaToYeh[ch - YEH_HAMZAFE_CHAR];
                        dest[i - 1] = HAMZAFE_CHAR;
                    } else {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int expandCompositChar(char[] dest, int start, int length, int lacount, int shapingMode) throws ArabicShapingException {
        int lenOptionsLamAlef;
        boolean spaceNotFound;
        boolean spaceNotFound2;
        int i = this.options;
        int lenOptionsLamAlef2 = 65539 & i;
        int lenOptionsSeen = i & SEEN_MASK;
        int lenOptionsYehHamza = i & YEHHAMZA_MASK;
        boolean z = this.isLogical;
        if (!z && !this.spacesRelativeToTextBeginEnd) {
            switch (lenOptionsLamAlef2) {
                case 2:
                    lenOptionsLamAlef = 3;
                    break;
                case 3:
                    lenOptionsLamAlef = 2;
                    break;
            }
            if (shapingMode != 1) {
                if (lenOptionsLamAlef == 65536) {
                    if (z) {
                        boolean spaceNotFound3 = expandCompositCharAtEnd(dest, start, length, lacount);
                        if (!spaceNotFound3) {
                            spaceNotFound2 = spaceNotFound3;
                        } else {
                            boolean spaceNotFound4 = expandCompositCharAtBegin(dest, start, length, lacount);
                            spaceNotFound2 = spaceNotFound4;
                        }
                        if (spaceNotFound2) {
                            spaceNotFound2 = expandCompositCharAtNear(dest, start, length, 0, 0, 1);
                        }
                        if (spaceNotFound2) {
                            throw new ArabicShapingException("No spacefor lamalef");
                        }
                        return length;
                    }
                    boolean spaceNotFound5 = expandCompositCharAtBegin(dest, start, length, lacount);
                    if (!spaceNotFound5) {
                        spaceNotFound = spaceNotFound5;
                    } else {
                        boolean spaceNotFound6 = expandCompositCharAtEnd(dest, start, length, lacount);
                        spaceNotFound = spaceNotFound6;
                    }
                    if (spaceNotFound) {
                        spaceNotFound = expandCompositCharAtNear(dest, start, length, 0, 0, 1);
                    }
                    if (spaceNotFound) {
                        throw new ArabicShapingException("No spacefor lamalef");
                    }
                    return length;
                } else if (lenOptionsLamAlef == 2) {
                    boolean spaceNotFound7 = expandCompositCharAtEnd(dest, start, length, lacount);
                    if (spaceNotFound7) {
                        throw new ArabicShapingException("No spacefor lamalef");
                    }
                    return length;
                } else if (lenOptionsLamAlef == 3) {
                    boolean spaceNotFound8 = expandCompositCharAtBegin(dest, start, length, lacount);
                    if (spaceNotFound8) {
                        throw new ArabicShapingException("No spacefor lamalef");
                    }
                    return length;
                } else if (lenOptionsLamAlef == 1) {
                    boolean spaceNotFound9 = expandCompositCharAtNear(dest, start, length, 0, 0, 1);
                    if (spaceNotFound9) {
                        throw new ArabicShapingException("No spacefor lamalef");
                    }
                    return length;
                } else if (lenOptionsLamAlef != 0) {
                    return length;
                } else {
                    int r = start + length;
                    int w = r + lacount;
                    while (true) {
                        r--;
                        if (r >= start) {
                            char ch = dest[r];
                            if (isNormalizedLamAlefChar(ch)) {
                                int w2 = w - 1;
                                dest[w2] = LAM_CHAR;
                                w = w2 - 1;
                                dest[w] = convertNormalizedLamAlef[ch - '\u065c'];
                            } else {
                                w--;
                                dest[w] = ch;
                            }
                        } else {
                            return length + lacount;
                        }
                    }
                }
            } else {
                if (lenOptionsSeen == 2097152) {
                    boolean spaceNotFound10 = expandCompositCharAtNear(dest, start, length, 0, 1, 0);
                    if (spaceNotFound10) {
                        throw new ArabicShapingException("No space for Seen tail expansion");
                    }
                }
                if (lenOptionsYehHamza != 16777216) {
                    return length;
                }
                boolean spaceNotFound11 = expandCompositCharAtNear(dest, start, length, 1, 0, 0);
                if (spaceNotFound11) {
                    throw new ArabicShapingException("No space for YehHamza expansion");
                }
                return length;
            }
        }
        lenOptionsLamAlef = lenOptionsLamAlef2;
        if (shapingMode != 1) {
        }
    }

    private int normalize(char[] dest, int start, int length) {
        int lacount = 0;
        int i = start;
        int e = i + length;
        while (i < e) {
            char ch = dest[i];
            if (ch >= '\ufe70' && ch <= '\ufefc') {
                if (isLamAlefChar(ch)) {
                    lacount++;
                }
                dest[i] = (char) convertFEto06[ch - '\ufe70'];
            }
            i++;
        }
        return lacount;
    }

    private int deshapeNormalize(char[] dest, int start, int length) {
        int lacount = 0;
        int i = this.options;
        int yehHamzaComposeEnabled = (58720256 & i) == 16777216 ? 1 : 0;
        int seenComposeEnabled = (i & SEEN_MASK) == 2097152 ? 1 : 0;
        int i2 = start;
        int e = i2 + length;
        while (i2 < e) {
            char ch = dest[i2];
            if (yehHamzaComposeEnabled == 1 && ((ch == '\u0621' || ch == '\ufe80') && i2 < length - 1 && isAlefMaksouraChar(dest[i2 + 1]))) {
                dest[i2] = SPACE_CHAR;
                dest[i2 + 1] = YEH_HAMZA_CHAR;
            } else if (seenComposeEnabled == 1 && isTailChar(ch) && i2 < length - 1 && isSeenTailFamilyChar(dest[i2 + 1]) == 1) {
                dest[i2] = SPACE_CHAR;
            } else if (ch >= '\ufe70' && ch <= '\ufefc') {
                if (isLamAlefChar(ch)) {
                    lacount++;
                }
                dest[i2] = (char) convertFEto06[ch - '\ufe70'];
            }
            i2++;
        }
        return lacount;
    }

    private int shapeUnicode(char[] dest, int start, int length, int destSize, int tashkeelFlag) throws ArabicShapingException {
        int destSize2;
        int nx;
        int lamalef_count = normalize(dest, start, length);
        int i = 1;
        int i2 = (start + length) - 1;
        int currLink = getLink(dest[i2]);
        int i3 = i2;
        int prevLink = 0;
        int lastLink = 0;
        int lastPos = i3;
        int nx2 = -2;
        int prevLink2 = 0;
        int lastLink2 = 0;
        int currLink2 = currLink;
        int nextLink = 0;
        int currLink3 = 0;
        int nextLink2 = 0;
        while (i3 >= 0) {
            if ((65280 & currLink2) != 0 || isTashkeelChar(dest[i3])) {
                int nw = i3 - 1;
                nx = -2;
                while (nx < 0) {
                    if (nw == -1) {
                        nextLink = 0;
                        nx = Integer.MAX_VALUE;
                    } else {
                        nextLink = getLink(dest[nw]);
                        if ((nextLink & 4) == 0) {
                            nx = nw;
                        } else {
                            nw--;
                        }
                    }
                }
                if ((currLink2 & 32) > 0 && (lastLink & 16) > 0) {
                    currLink3 = 1;
                    char wLamalef = changeLamAlef(dest[i3]);
                    if (wLamalef != 0) {
                        dest[i3] = '\uffff';
                        dest[lastPos] = wLamalef;
                        i3 = lastPos;
                    }
                    lastLink = prevLink;
                    currLink2 = getLink(wLamalef);
                }
                if (i3 > 0 && dest[i3 - 1] == ' ') {
                    if (isSeenFamilyChar(dest[i3]) != i) {
                        if (dest[i3] == '\u0626') {
                            prevLink2 = 1;
                        }
                    } else {
                        nextLink2 = 1;
                    }
                } else if (i3 == 0) {
                    if (isSeenFamilyChar(dest[i3]) != i) {
                        if (dest[i3] == '\u0626') {
                            prevLink2 = 1;
                        }
                    } else {
                        nextLink2 = 1;
                    }
                }
                int flag = specialChar(dest[i3]);
                int shape = shapeTable[nextLink & 3][lastLink & 3][currLink2 & 3];
                if (flag == i) {
                    shape &= 1;
                } else if (flag == 2) {
                    if (tashkeelFlag != 0 || (lastLink & 2) == 0 || (nextLink & 1) == 0 || dest[i3] == '\u064c' || dest[i3] == '\u064d' || ((nextLink & 32) == 32 && (lastLink & 16) == 16)) {
                        if (tashkeelFlag == 2 && dest[i3] == '\u0651') {
                            shape = 1;
                        } else {
                            shape = 0;
                        }
                    } else {
                        shape = 1;
                    }
                }
                if (flag == 2) {
                    if (tashkeelFlag == 2 && dest[i3] != '\u0651') {
                        dest[i3] = TASHKEEL_SPACE_SUB;
                        lastLink2 = 1;
                    } else {
                        dest[i3] = (char) (irrelevantPos[dest[i3] - '\u064b'] + 65136 + shape);
                    }
                } else {
                    dest[i3] = (char) ((currLink2 >> 8) + 65136 + shape);
                }
            } else {
                nx = nx2;
            }
            int nw2 = currLink2 & 4;
            if (nw2 == 0) {
                int prevLink3 = lastLink;
                int lastLink3 = currLink2;
                prevLink = prevLink3;
                lastLink = lastLink3;
                lastPos = i3;
            }
            i3--;
            if (i3 == nx) {
                currLink2 = nextLink;
                nx2 = -2;
                i = 1;
            } else if (i3 == -1) {
                nx2 = nx;
                i = 1;
            } else {
                currLink2 = getLink(dest[i3]);
                nx2 = nx;
                i = 1;
            }
        }
        if (currLink3 == 0 && lastLink2 == 0) {
            destSize2 = length;
        } else {
            int destSize3 = handleGeneratedSpaces(dest, start, length);
            destSize2 = destSize3;
        }
        if (nextLink2 != 0 || prevLink2 != 0) {
            return expandCompositChar(dest, start, destSize2, lamalef_count, 0);
        }
        return destSize2;
    }

    private int deShapeUnicode(char[] dest, int start, int length, int destSize) throws ArabicShapingException {
        int lamalef_count = deshapeNormalize(dest, start, length);
        if (lamalef_count != 0) {
            int destSize2 = expandCompositChar(dest, start, length, lamalef_count, 1);
            return destSize2;
        }
        return length;
    }

    private int internalShape(char[] source, int sourceStart, int sourceLength, char[] dest, int destStart, int destSize) throws ArabicShapingException {
        char digitBase;
        if (sourceLength == 0) {
            return 0;
        }
        if (destSize == 0) {
            int i = this.options;
            return ((i & 24) == 0 || (i & 65539) != 0) ? sourceLength : calculateSize(source, sourceStart, sourceLength);
        }
        char[] temp = new char[sourceLength * 2];
        System.arraycopy(source, sourceStart, temp, 0, sourceLength);
        if (this.isLogical) {
            invertBuffer(temp, 0, sourceLength);
        }
        int outputSize = sourceLength;
        int i2 = this.options;
        switch (i2 & 24) {
            case 8:
                if ((i2 & TASHKEEL_MASK) != 0 && (i2 & TASHKEEL_MASK) != 786432) {
                    outputSize = shapeUnicode(temp, 0, sourceLength, destSize, 2);
                    break;
                } else {
                    outputSize = shapeUnicode(temp, 0, sourceLength, destSize, 0);
                    if ((this.options & TASHKEEL_MASK) == 786432) {
                        outputSize = handleTashkeelWithTatweel(temp, sourceLength);
                        break;
                    }
                }
                break;
            case 16:
                outputSize = deShapeUnicode(temp, 0, sourceLength, destSize);
                break;
            case 24:
                outputSize = shapeUnicode(temp, 0, sourceLength, destSize, 1);
                break;
        }
        if (outputSize <= destSize) {
            int i3 = this.options;
            if ((i3 & 224) != 0) {
                switch (i3 & 256) {
                    case 0:
                        digitBase = '\u0660';
                        break;
                    case 256:
                        digitBase = '\u06f0';
                        break;
                    default:
                        digitBase = '0';
                        break;
                }
                switch (i3 & 224) {
                    case 32:
                        int digitDelta = digitBase - '0';
                        for (int i4 = 0; i4 < outputSize; i4++) {
                            char ch = temp[i4];
                            if (ch <= '9' && ch >= '0') {
                                temp[i4] = (char) (temp[i4] + digitDelta);
                            }
                        }
                        break;
                    case 64:
                        char digitTop = (char) (digitBase + '\t');
                        int digitDelta2 = '0' - digitBase;
                        for (int i5 = 0; i5 < outputSize; i5++) {
                            char ch2 = temp[i5];
                            if (ch2 <= digitTop && ch2 >= digitBase) {
                                temp[i5] = (char) (temp[i5] + digitDelta2);
                            }
                        }
                        break;
                    case 96:
                        shapeToArabicDigitsWithContext(temp, 0, outputSize, digitBase, false);
                        break;
                    case 128:
                        shapeToArabicDigitsWithContext(temp, 0, outputSize, digitBase, true);
                        break;
                }
            }
            if (this.isLogical) {
                invertBuffer(temp, 0, outputSize);
            }
            System.arraycopy(temp, 0, dest, destStart, outputSize);
            return outputSize;
        }
        throw new ArabicShapingException("not enough room for result data");
    }
}
