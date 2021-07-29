package com.ibm.icu.text;

import com.ibm.icu.impl.UBiDiProps;

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
    private static final char HAMZA06_CHAR = 'ء';
    private static final char HAMZAFE_CHAR = 'ﺀ';
    private static final int IRRELEVANT = 4;
    public static final int LAMALEF_AUTO = 65536;
    public static final int LAMALEF_BEGIN = 3;
    public static final int LAMALEF_END = 2;
    public static final int LAMALEF_MASK = 65539;
    public static final int LAMALEF_NEAR = 1;
    public static final int LAMALEF_RESIZE = 0;
    private static final char LAMALEF_SPACE_SUB = '￿';
    private static final int LAMTYPE = 16;
    private static final char LAM_CHAR = 'ل';
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
    private static final char NEW_TAIL_CHAR = 'ﹳ';
    private static final char OLD_TAIL_CHAR = '​';
    public static final int SEEN_MASK = 7340032;
    public static final int SEEN_TWOCELL_NEAR = 2097152;
    private static final char SHADDA06_CHAR = 'ّ';
    private static final char SHADDA_CHAR = 'ﹼ';
    private static final char SHADDA_TATWEEL_CHAR = 'ﹽ';
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
    private static final char TASHKEEL_SPACE_SUB = '￾';
    private static final char TATWEEL_CHAR = 'ـ';
    public static final int TEXT_DIRECTION_LOGICAL = 0;
    public static final int TEXT_DIRECTION_MASK = 4;
    public static final int TEXT_DIRECTION_VISUAL_LTR = 4;
    public static final int TEXT_DIRECTION_VISUAL_RTL = 0;
    public static final int YEHHAMZA_MASK = 58720256;
    public static final int YEHHAMZA_TWOCELL_NEAR = 16777216;
    private static final char YEH_HAMZAFE_CHAR = 'ﺉ';
    private static final char YEH_HAMZA_CHAR = 'ئ';
    private static final int[] araLink = {4385, 4897, 5377, 5921, 6403, 7457, 7939, 8961, 9475, 10499, 11523, 12547, 13571, 14593, 15105, 15617, 16129, 16643, 17667, 18691, 19715, 20739, 21763, 22787, 23811, 0, 0, 0, 0, 0, 3, 24835, 25859, 26883, 27923, 28931, 29955, 30979, 32001, 32513, 33027, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 34049, 34561, 35073, 35585, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33, 33, 0, 33, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 3, 3, 3, 1, 1};
    private static int[] convertFEto06 = {1611, 1611, 1612, 1612, 1613, 1613, 1614, 1614, 1615, 1615, 1616, 1616, 1617, 1617, 1618, 1618, 1569, 1570, 1570, 1571, 1571, 1572, 1572, 1573, 1573, 1574, 1574, 1574, 1574, 1575, 1575, 1576, 1576, 1576, 1576, 1577, 1577, 1578, 1578, 1578, 1578, 1579, 1579, 1579, 1579, 1580, 1580, 1580, 1580, 1581, 1581, 1581, 1581, 1582, 1582, 1582, 1582, 1583, 1583, 1584, 1584, 1585, 1585, 1586, 1586, 1587, 1587, 1587, 1587, 1588, 1588, 1588, 1588, 1589, 1589, 1589, 1589, 1590, 1590, 1590, 1590, 1591, 1591, 1591, 1591, 1592, 1592, 1592, 1592, 1593, 1593, 1593, 1593, 1594, 1594, 1594, 1594, 1601, 1601, 1601, 1601, 1602, 1602, 1602, 1602, 1603, 1603, 1603, 1603, 1604, 1604, 1604, 1604, 1605, 1605, 1605, 1605, 1606, 1606, 1606, 1606, 1607, 1607, 1607, 1607, 1608, 1608, 1609, 1609, 1610, 1610, 1610, 1610, 1628, 1628, 1629, 1629, 1630, 1630, 1631, 1631};
    private static final char[] convertNormalizedLamAlef = {1570, 1571, 1573, 1575};
    private static final int[] irrelevantPos = {0, 2, 4, 6, 8, 10, 12, 14};
    private static final int[] presLink = {3, 3, 3, 0, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 32, 33, 32, 33, 0, 1, 32, 33, 0, 2, 3, 1, 32, 33, 0, 2, 3, 1, 0, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 16, 18, 19, 17, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 1, 0, 1, 0, 2, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1};
    private static final int[][][] shapeTable = {new int[][]{new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{0, 1, 0, 3}, new int[]{0, 1, 0, 1}}, new int[][]{new int[]{0, 0, 2, 2}, new int[]{0, 0, 1, 2}, new int[]{0, 1, 1, 2}, new int[]{0, 1, 1, 3}}, new int[][]{new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{0, 1, 0, 3}, new int[]{0, 1, 0, 3}}, new int[][]{new int[]{0, 0, 1, 2}, new int[]{0, 0, 1, 2}, new int[]{0, 1, 1, 2}, new int[]{0, 1, 1, 3}}};
    private static final int[] tailFamilyIsolatedFinal = {1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1};
    private static final int[] tashkeelMedial = {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
    private static final char[] yehHamzaToYeh = {65263, 65264};
    private boolean isLogical;
    private final int options;
    private boolean spacesRelativeToTextBeginEnd;
    private char tailChar;

    public int shape(char[] source, int sourceStart, int sourceLength, char[] dest, int destStart, int destSize) throws ArabicShapingException {
        if (source == null) {
            throw new IllegalArgumentException("source can not be null");
        } else if (sourceStart < 0 || sourceLength < 0 || sourceStart + sourceLength > source.length) {
            throw new IllegalArgumentException("bad source start (" + sourceStart + ") or length (" + sourceLength + ") for buffer of length " + source.length);
        } else if (dest == null && destSize != 0) {
            throw new IllegalArgumentException("null dest requires destSize == 0");
        } else if (destSize == 0 || (destStart >= 0 && destSize >= 0 && destStart + destSize <= dest.length)) {
            int i = this.options;
            if ((i & TASHKEEL_MASK) != 0 && (i & TASHKEEL_MASK) != 262144 && (i & TASHKEEL_MASK) != 393216 && (i & TASHKEEL_MASK) != 524288 && (i & TASHKEEL_MASK) != 786432) {
                throw new IllegalArgumentException("Wrong Tashkeel argument");
            } else if ((i & 65539) != 0 && (i & 65539) != 3 && (i & 65539) != 2 && (i & 65539) != 0 && (i & 65539) != 65536 && (65539 & i) != 1) {
                throw new IllegalArgumentException("Wrong Lam Alef argument");
            } else if ((917504 & i) == 0 || (i & 24) != 16) {
                return internalShape(source, sourceStart, sourceLength, dest, destStart, destSize);
            } else {
                throw new IllegalArgumentException("Tashkeel replacement should not be enabled in deshaping mode ");
            }
        } else {
            throw new IllegalArgumentException("bad dest start (" + destStart + ") or size (" + destSize + ") for buffer of length " + dest.length);
        }
    }

    public void shape(char[] source, int start, int length) throws ArabicShapingException {
        if ((this.options & 65539) != 0) {
            shape(source, start, length, source, start, length);
            return;
        }
        throw new ArabicShapingException("Cannot shape in place with length option resize.");
    }

    public String shape(String text) throws ArabicShapingException {
        char[] dest;
        char[] src = text.toCharArray();
        char[] dest2 = src;
        int i = this.options;
        if ((65539 & i) == 0 && (i & 24) == 16) {
            dest = new char[(src.length * 2)];
        } else {
            dest = dest2;
        }
        return new String(dest, 0, shape(src, 0, src.length, dest, 0, dest.length));
    }

    public ArabicShaping(int options2) {
        this.options = options2;
        if ((options2 & 224) <= 128) {
            boolean z = true;
            this.isLogical = (options2 & 4) == 0;
            this.spacesRelativeToTextBeginEnd = (options2 & 67108864) != 67108864 ? false : z;
            if ((options2 & 134217728) == 134217728) {
                this.tailChar = NEW_TAIL_CHAR;
            } else {
                this.tailChar = OLD_TAIL_CHAR;
            }
        } else {
            throw new IllegalArgumentException("bad DIGITS options");
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
            case TASHKEEL_END /*393216*/:
                buf.append(", Tashkeel at end");
                break;
            case 524288:
                buf.append(", Tashkeel resize");
                break;
            case TASHKEEL_REPLACE_BY_TATWEEL /*786432*/:
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
            case 1570:
                return 1628;
            case 1571:
                return 1629;
            case 1573:
                return 1630;
            case 1575:
                return 1631;
            default:
                return 0;
        }
    }

    private static int specialChar(char ch) {
        if ((ch > 1569 && ch < 1574) || ch == 1575) {
            return 1;
        }
        if (ch > 1582 && ch < 1587) {
            return 1;
        }
        if ((ch > 1607 && ch < 1610) || ch == 1577) {
            return 1;
        }
        if (ch >= 1611 && ch <= 1618) {
            return 2;
        }
        if ((ch >= 1619 && ch <= 1621) || ch == 1648) {
            return 3;
        }
        if (ch < 65136 || ch > 65151) {
            return 0;
        }
        return 3;
    }

    private static int getLink(char ch) {
        if (ch >= 1570 && ch <= 1747) {
            return araLink[ch - 1570];
        }
        if (ch == 8205) {
            return 3;
        }
        if (ch >= 8301 && ch <= 8303) {
            return 4;
        }
        if (ch < 65136 || ch > 65276) {
            return 0;
        }
        return presLink[ch - 65136];
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
        return ch >= 1611 && ch <= 1618;
    }

    private static int isSeenTailFamilyChar(char ch) {
        if (ch < 65201 || ch >= 65215) {
            return 0;
        }
        return tailFamilyIsolatedFinal[ch - 65201];
    }

    private static int isSeenFamilyChar(char ch) {
        if (ch < 1587 || ch > 1590) {
            return 0;
        }
        return 1;
    }

    private static boolean isTailChar(char ch) {
        if (ch == 8203 || ch == 65139) {
            return true;
        }
        return false;
    }

    private static boolean isAlefMaksouraChar(char ch) {
        return ch == 65263 || ch == 65264 || ch == 1609;
    }

    private static boolean isYehHamzaChar(char ch) {
        if (ch == 65161 || ch == 65162) {
            return true;
        }
        return false;
    }

    private static boolean isTashkeelCharFE(char ch) {
        return ch != 65141 && ch >= 65136 && ch <= 65151;
    }

    private static int isTashkeelOnTatweelChar(char ch) {
        if (ch >= 65136 && ch <= 65151 && ch != 65139 && ch != 65141 && ch != 65149) {
            return tashkeelMedial[ch - 65136];
        }
        if ((ch < 64754 || ch > 64756) && ch != 65149) {
            return 0;
        }
        return 2;
    }

    private static int isIsolatedTashkeelChar(char ch) {
        if (ch >= 65136 && ch <= 65151 && ch != 65139 && ch != 65141) {
            return 1 - tashkeelMedial[ch - 65136];
        }
        if (ch < 64606 || ch > 64611) {
            return 0;
        }
        return 1;
    }

    private static boolean isAlefChar(char ch) {
        return ch == 1570 || ch == 1571 || ch == 1573 || ch == 1575;
    }

    private static boolean isLamAlefChar(char ch) {
        return ch >= 65269 && ch <= 65276;
    }

    private static boolean isNormalizedLamAlefChar(char ch) {
        return ch >= 1628 && ch <= 1631;
    }

    private int calculateSize(char[] source, int sourceStart, int sourceLength) {
        int destSize = sourceLength;
        switch (this.options & 24) {
            case 8:
            case 24:
                if (!this.isLogical) {
                    int e = sourceStart + sourceLength;
                    for (int i = sourceStart + 1; i < e; i++) {
                        if ((source[i] == 1604 && isAlefChar(source[i - 1])) || isTashkeelCharFE(source[i])) {
                            destSize--;
                        }
                    }
                    break;
                } else {
                    int e2 = (sourceStart + sourceLength) - 1;
                    for (int i2 = sourceStart; i2 < e2; i2++) {
                        if ((source[i2] == 1604 && isAlefChar(source[i2 + 1])) || isTashkeelCharFE(source[i2])) {
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
                if (!(ch == subChar || w - 1 == r)) {
                    dest[w] = ch;
                }
            } else {
                return;
            }
        }
    }

    private static int flipArray(char[] dest, int start, int e, int w) {
        if (w <= start) {
            return e;
        }
        int w2 = start;
        for (int r = w; r < e; r++) {
            dest[w2] = dest[r];
            w2++;
        }
        return w2;
    }

    private static int handleTashkeelWithTatweel(char[] dest, int sourceLength) {
        for (int i = 0; i < sourceLength; i++) {
            if (isTashkeelOnTatweelChar(dest[i]) == 1) {
                dest[i] = TATWEEL_CHAR;
            } else if (isTashkeelOnTatweelChar(dest[i]) == 2) {
                dest[i] = SHADDA_TATWEEL_CHAR;
            } else if (isIsolatedTashkeelChar(dest[i]) == 1 && dest[i] != 65148) {
                dest[i] = SPACE_CHAR;
            }
        }
        return sourceLength;
    }

    private int handleGeneratedSpaces(char[] dest, int start, int length) {
        char[] cArr = dest;
        int i = start;
        int length2 = length;
        int i2 = this.options;
        int lenOptionsLamAlef = 65539 & i2;
        int lenOptionsTashkeel = i2 & TASHKEEL_MASK;
        boolean lamAlefOn = false;
        boolean tashkeelOn = false;
        if ((!this.isLogical) && (!this.spacesRelativeToTextBeginEnd)) {
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
                case TASHKEEL_END /*393216*/:
                    lenOptionsTashkeel = 262144;
                    break;
            }
        }
        if (lenOptionsLamAlef == 1) {
            int i3 = start;
            int e = i3 + length2;
            while (i3 < e) {
                if (cArr[i3] == 65535) {
                    cArr[i3] = SPACE_CHAR;
                }
                i3++;
            }
        } else {
            int e2 = i + length2;
            int wL = countSpaceSub(cArr, length2, 65535);
            int wT = countSpaceSub(cArr, length2, TASHKEEL_SPACE_SUB);
            if (lenOptionsLamAlef == 2) {
                lamAlefOn = true;
            }
            if (lenOptionsTashkeel == 393216) {
                tashkeelOn = true;
            }
            if (lamAlefOn && lenOptionsLamAlef == 2) {
                shiftArray(cArr, i, e2, 65535);
                while (wL > i) {
                    wL--;
                    cArr[wL] = SPACE_CHAR;
                }
            }
            if (tashkeelOn && lenOptionsTashkeel == 393216) {
                shiftArray(cArr, i, e2, TASHKEEL_SPACE_SUB);
                while (wT > i) {
                    wT--;
                    cArr[wT] = SPACE_CHAR;
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
                shiftArray(cArr, i, e2, 65535);
                wL = flipArray(cArr, i, e2, wL);
                length2 = wL - i;
            }
            if (tashkeelOn2 && lenOptionsTashkeel == 524288) {
                shiftArray(cArr, i, e2, TASHKEEL_SPACE_SUB);
                wT = flipArray(cArr, i, e2, wT);
                length2 = wT - i;
            }
            boolean lamAlefOn3 = false;
            boolean tashkeelOn3 = false;
            if (lenOptionsLamAlef == 3 || lenOptionsLamAlef == 65536) {
                lamAlefOn3 = true;
            }
            if (lenOptionsTashkeel == 262144) {
                tashkeelOn3 = true;
            }
            if (lamAlefOn3 && (lenOptionsLamAlef == 3 || lenOptionsLamAlef == 65536)) {
                shiftArray(cArr, i, e2, 65535);
                for (int wL2 = flipArray(cArr, i, e2, wL); wL2 < e2; wL2++) {
                    cArr[wL2] = SPACE_CHAR;
                }
            }
            if (tashkeelOn3 && lenOptionsTashkeel == 262144) {
                shiftArray(cArr, i, e2, TASHKEEL_SPACE_SUB);
                for (int wT2 = flipArray(cArr, i, e2, wT); wT2 < e2; wT2++) {
                    cArr[wT2] = SPACE_CHAR;
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
                dest[w] = convertNormalizedLamAlef[ch - 1628];
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
                dest[w] = convertNormalizedLamAlef[ch - 1628];
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
            if (i < start) {
                return false;
            }
            char ch = dest[i];
            if (lamAlefOption != 1 || !isNormalizedLamAlefChar(ch)) {
                if (seenTailOption == 1 && isSeenTailFamilyChar(ch) == 1) {
                    if (i <= start || dest[i - 1] != ' ') {
                        return true;
                    }
                    dest[i - 1] = this.tailChar;
                } else if (yehHamzaOption == 1 && isYehHamzaChar(ch)) {
                    if (i <= start || dest[i - 1] != ' ') {
                        return true;
                    }
                    dest[i] = yehHamzaToYeh[ch - YEH_HAMZAFE_CHAR];
                    dest[i - 1] = HAMZAFE_CHAR;
                }
            } else if (i <= start || dest[i - 1] != ' ') {
                return true;
            } else {
                dest[i] = LAM_CHAR;
                i--;
                dest[i] = convertNormalizedLamAlef[ch - 1628];
            }
        }
    }

    private int expandCompositChar(char[] dest, int start, int length, int lacount, int shapingMode) throws ArabicShapingException {
        int lenOptionsLamAlef;
        boolean spaceNotFound;
        boolean spaceNotFound2;
        int i = start;
        int i2 = this.options;
        int lenOptionsLamAlef2 = 65539 & i2;
        int lenOptionsSeen = i2 & SEEN_MASK;
        int lenOptionsYehHamza = i2 & YEHHAMZA_MASK;
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
        }
        lenOptionsLamAlef = lenOptionsLamAlef2;
        if (shapingMode == 1) {
            if (lenOptionsLamAlef == 65536) {
                if (z) {
                    boolean spaceNotFound3 = expandCompositCharAtEnd(dest, start, length, lacount);
                    if (spaceNotFound3) {
                        spaceNotFound2 = expandCompositCharAtBegin(dest, start, length, lacount);
                    } else {
                        spaceNotFound2 = spaceNotFound3;
                    }
                    if (spaceNotFound2) {
                        spaceNotFound2 = expandCompositCharAtNear(dest, start, length, 0, 0, 1);
                    }
                    if (!spaceNotFound2) {
                        return length;
                    }
                    throw new ArabicShapingException("No spacefor lamalef");
                }
                boolean spaceNotFound4 = expandCompositCharAtBegin(dest, start, length, lacount);
                if (spaceNotFound4) {
                    spaceNotFound = expandCompositCharAtEnd(dest, start, length, lacount);
                } else {
                    spaceNotFound = spaceNotFound4;
                }
                if (spaceNotFound) {
                    spaceNotFound = expandCompositCharAtNear(dest, start, length, 0, 0, 1);
                }
                if (!spaceNotFound) {
                    return length;
                }
                throw new ArabicShapingException("No spacefor lamalef");
            } else if (lenOptionsLamAlef == 2) {
                if (!expandCompositCharAtEnd(dest, start, length, lacount)) {
                    return length;
                }
                throw new ArabicShapingException("No spacefor lamalef");
            } else if (lenOptionsLamAlef == 3) {
                if (!expandCompositCharAtBegin(dest, start, length, lacount)) {
                    return length;
                }
                throw new ArabicShapingException("No spacefor lamalef");
            } else if (lenOptionsLamAlef == 1) {
                if (!expandCompositCharAtNear(dest, start, length, 0, 0, 1)) {
                    return length;
                }
                throw new ArabicShapingException("No spacefor lamalef");
            } else if (lenOptionsLamAlef != 0) {
                return length;
            } else {
                int r = i + length;
                int w = r + lacount;
                while (true) {
                    r--;
                    if (r < i) {
                        return length + lacount;
                    }
                    char ch = dest[r];
                    if (isNormalizedLamAlefChar(ch)) {
                        int w2 = w - 1;
                        dest[w2] = LAM_CHAR;
                        w = w2 - 1;
                        dest[w] = convertNormalizedLamAlef[ch - 1628];
                    } else {
                        w--;
                        dest[w] = ch;
                    }
                }
            }
        } else if (lenOptionsSeen == 2097152 && expandCompositCharAtNear(dest, start, length, 0, 1, 0)) {
            throw new ArabicShapingException("No space for Seen tail expansion");
        } else if (lenOptionsYehHamza != 16777216) {
            return length;
        } else {
            if (!expandCompositCharAtNear(dest, start, length, 1, 0, 0)) {
                return length;
            }
            throw new ArabicShapingException("No space for YehHamza expansion");
        }
    }

    private int normalize(char[] dest, int start, int length) {
        int lacount = 0;
        int i = start;
        int e = i + length;
        while (i < e) {
            char ch = dest[i];
            if (ch >= 65136 && ch <= 65276) {
                if (isLamAlefChar(ch)) {
                    lacount++;
                }
                dest[i] = (char) convertFEto06[ch - 65136];
            }
            i++;
        }
        return lacount;
    }

    private int deshapeNormalize(char[] dest, int start, int length) {
        int lacount = 0;
        int i = this.options;
        int i2 = 0;
        int yehHamzaComposeEnabled = (58720256 & i) == 16777216 ? 1 : 0;
        if ((i & SEEN_MASK) == 2097152) {
            i2 = 1;
        }
        int seenComposeEnabled = i2;
        int i3 = start;
        int e = i3 + length;
        while (i3 < e) {
            char ch = dest[i3];
            if (yehHamzaComposeEnabled == 1 && ((ch == 1569 || ch == 65152) && i3 < length - 1 && isAlefMaksouraChar(dest[i3 + 1]))) {
                dest[i3] = SPACE_CHAR;
                dest[i3 + 1] = YEH_HAMZA_CHAR;
            } else if (seenComposeEnabled == 1 && isTailChar(ch) && i3 < length - 1 && isSeenTailFamilyChar(dest[i3 + 1]) == 1) {
                dest[i3] = SPACE_CHAR;
            } else if (ch >= 65136 && ch <= 65276) {
                if (isLamAlefChar(ch)) {
                    lacount++;
                }
                dest[i3] = (char) convertFEto06[ch - 65136];
            }
            i3++;
        }
        return lacount;
    }

    private int shapeUnicode(char[] dest, int start, int length, int destSize, int tashkeelFlag) throws ArabicShapingException {
        int destSize2;
        int nx;
        int i = tashkeelFlag;
        int lamalef_count = normalize(dest, start, length);
        int i2 = 1;
        int i3 = (start + length) - 1;
        int i4 = i3;
        int prevLink = 0;
        int lastLink = 0;
        int lastPos = i4;
        int nx2 = -2;
        boolean yehhamza_found = false;
        boolean tashkeel_found = false;
        int currLink = getLink(dest[i3]);
        int nextLink = 0;
        boolean lamalef_found = false;
        boolean seenfam_found = false;
        while (i4 >= 0) {
            if ((65280 & currLink) != 0 || isTashkeelChar(dest[i4])) {
                int nw = i4 - 1;
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
                if ((currLink & 32) > 0 && (lastLink & 16) > 0) {
                    lamalef_found = true;
                    char wLamalef = changeLamAlef(dest[i4]);
                    if (wLamalef != 0) {
                        dest[i4] = 65535;
                        dest[lastPos] = wLamalef;
                        i4 = lastPos;
                    }
                    lastLink = prevLink;
                    currLink = getLink(wLamalef);
                }
                if (i4 <= 0 || dest[i4 - 1] != ' ') {
                    if (i4 == 0) {
                        if (isSeenFamilyChar(dest[i4]) == i2) {
                            seenfam_found = true;
                        } else if (dest[i4] == 1574) {
                            yehhamza_found = true;
                        }
                    }
                } else if (isSeenFamilyChar(dest[i4]) == i2) {
                    seenfam_found = true;
                } else if (dest[i4] == 1574) {
                    yehhamza_found = true;
                }
                int flag = specialChar(dest[i4]);
                int shape = shapeTable[nextLink & 3][lastLink & 3][currLink & 3];
                if (flag == i2) {
                    shape &= 1;
                } else if (flag == 2) {
                    if (i == 0 && (lastLink & 2) != 0 && (nextLink & 1) != 0 && dest[i4] != 1612 && dest[i4] != 1613 && ((nextLink & 32) != 32 || (lastLink & 16) != 16)) {
                        shape = 1;
                    } else if (i == 2 && dest[i4] == 1617) {
                        shape = 1;
                    } else {
                        shape = 0;
                    }
                }
                if (flag != 2) {
                    dest[i4] = (char) ((currLink >> 8) + 65136 + shape);
                } else if (i != 2 || dest[i4] == 1617) {
                    dest[i4] = (char) (irrelevantPos[dest[i4] - 1611] + 65136 + shape);
                } else {
                    dest[i4] = TASHKEEL_SPACE_SUB;
                    int i5 = nw;
                    tashkeel_found = true;
                }
                int i6 = nw;
            } else {
                nx = nx2;
            }
            if ((currLink & 4) == 0) {
                prevLink = lastLink;
                lastLink = currLink;
                lastPos = i4;
            }
            i4--;
            if (i4 == nx) {
                currLink = nextLink;
                nx2 = -2;
                i2 = 1;
            } else if (i4 != -1) {
                currLink = getLink(dest[i4]);
                nx2 = nx;
                i2 = 1;
            } else {
                nx2 = nx;
                i2 = 1;
            }
        }
        int destSize3 = length;
        if (lamalef_found || tashkeel_found) {
            destSize2 = handleGeneratedSpaces(dest, start, length);
        } else {
            destSize2 = destSize3;
        }
        if (seenfam_found || yehhamza_found) {
            return expandCompositChar(dest, start, destSize2, lamalef_count, 0);
        }
        return destSize2;
    }

    private int deShapeUnicode(char[] dest, int start, int length, int destSize) throws ArabicShapingException {
        int lamalef_count = deshapeNormalize(dest, start, length);
        if (lamalef_count != 0) {
            return expandCompositChar(dest, start, length, lamalef_count, 1);
        }
        return length;
    }

    private int internalShape(char[] source, int sourceStart, int sourceLength, char[] dest, int destStart, int destSize) throws ArabicShapingException {
        char digitBase;
        int i = sourceLength;
        int i2 = destSize;
        if (i == 0) {
            return 0;
        }
        if (i2 == 0) {
            int i3 = this.options;
            if ((i3 & 24) == 0 || (i3 & 65539) != 0) {
                return i;
            }
            return calculateSize(source, sourceStart, sourceLength);
        }
        char[] temp = new char[(i * 2)];
        System.arraycopy(source, sourceStart, temp, 0, i);
        if (this.isLogical) {
            invertBuffer(temp, 0, i);
        }
        int outputSize = sourceLength;
        int i4 = this.options;
        switch (i4 & 24) {
            case 8:
                if ((i4 & TASHKEEL_MASK) != 0 && (i4 & TASHKEEL_MASK) != 786432) {
                    outputSize = shapeUnicode(temp, 0, sourceLength, destSize, 2);
                    break;
                } else {
                    outputSize = shapeUnicode(temp, 0, sourceLength, destSize, 0);
                    if ((this.options & TASHKEEL_MASK) == 786432) {
                        outputSize = handleTashkeelWithTatweel(temp, i);
                        break;
                    }
                }
                break;
            case 16:
                outputSize = deShapeUnicode(temp, 0, i, i2);
                break;
            case 24:
                outputSize = shapeUnicode(temp, 0, sourceLength, destSize, 1);
                break;
        }
        if (outputSize <= i2) {
            int i5 = this.options;
            if ((i5 & 224) != 0) {
                switch (i5 & 256) {
                    case 0:
                        digitBase = 1632;
                        break;
                    case 256:
                        digitBase = 1776;
                        break;
                    default:
                        digitBase = '0';
                        break;
                }
                switch (i5 & 224) {
                    case 32:
                        int digitDelta = digitBase - '0';
                        for (int i6 = 0; i6 < outputSize; i6++) {
                            char ch = temp[i6];
                            if (ch <= '9' && ch >= '0') {
                                temp[i6] = (char) (temp[i6] + digitDelta);
                            }
                        }
                        break;
                    case 64:
                        char digitTop = (char) (digitBase + 9);
                        int digitDelta2 = '0' - digitBase;
                        for (int i7 = 0; i7 < outputSize; i7++) {
                            char ch2 = temp[i7];
                            if (ch2 <= digitTop && ch2 >= digitBase) {
                                temp[i7] = (char) (temp[i7] + digitDelta2);
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
            if (this.isLogical != 0) {
                invertBuffer(temp, 0, outputSize);
            }
            System.arraycopy(temp, 0, dest, destStart, outputSize);
            return outputSize;
        }
        char[] cArr = dest;
        int i8 = destStart;
        throw new ArabicShapingException("not enough room for result data");
    }
}
