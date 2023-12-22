package com.ibm.icu.text;

import com.ibm.icu.impl.UBiDiProps;
import com.ibm.icu.lang.UCharacter;
import java.awt.font.NumericShaper;
import java.awt.font.TextAttribute;
import java.lang.reflect.Array;
import java.text.AttributedCharacterIterator;
import java.util.Arrays;

/* loaded from: classes.dex */
public class Bidi {

    /* renamed from: B */
    static final byte f126B = 7;

    /* renamed from: BN */
    static final byte f127BN = 18;
    @Deprecated
    public static final int CLASS_DEFAULT = 23;

    /* renamed from: CR */
    private static final char f128CR = '\r';

    /* renamed from: CS */
    static final byte f129CS = 6;
    public static final int DIRECTION_DEFAULT_LEFT_TO_RIGHT = 126;
    public static final int DIRECTION_DEFAULT_RIGHT_TO_LEFT = 127;
    public static final int DIRECTION_LEFT_TO_RIGHT = 0;
    public static final int DIRECTION_RIGHT_TO_LEFT = 1;
    public static final short DO_MIRRORING = 2;

    /* renamed from: EN */
    static final byte f130EN = 2;

    /* renamed from: ES */
    static final byte f131ES = 3;

    /* renamed from: ET */
    static final byte f132ET = 4;
    static final int FIRSTALLOC = 10;
    static final byte FSI = 19;
    private static final int IMPTABLEVELS_COLUMNS = 8;
    private static final int IMPTABLEVELS_RES = 7;
    private static final int IMPTABPROPS_COLUMNS = 16;
    private static final int IMPTABPROPS_RES = 15;
    public static final short INSERT_LRM_FOR_NUMERIC = 4;
    static final int ISOLATE = 256;
    public static final short KEEP_BASE_COMBINING = 1;

    /* renamed from: L */
    static final byte f133L = 0;
    public static final byte LEVEL_DEFAULT_LTR = 126;
    public static final byte LEVEL_DEFAULT_RTL = Byte.MAX_VALUE;
    public static final byte LEVEL_OVERRIDE = Byte.MIN_VALUE;

    /* renamed from: LF */
    private static final char f134LF = '\n';
    static final int LOOKING_FOR_PDI = 3;
    static final int LRM_AFTER = 2;
    static final int LRM_BEFORE = 1;
    public static final byte LTR = 0;
    public static final int MAP_NOWHERE = -1;
    static final int MASK_BN_EXPLICIT;
    static final int MASK_B_S;
    static final int MASK_EMBEDDING;
    static final int MASK_EXPLICIT;
    static final int MASK_ISO;
    static final int MASK_POSSIBLE_N;
    static final int MASK_WS;
    public static final byte MAX_EXPLICIT_LEVEL = 125;
    public static final byte MIXED = 2;
    public static final byte NEUTRAL = 3;
    static final int NOT_SEEKING_STRONG = 0;
    static final byte NSM = 17;

    /* renamed from: ON */
    static final byte f135ON = 10;
    public static final int OPTION_DEFAULT = 0;
    public static final int OPTION_INSERT_MARKS = 1;
    public static final int OPTION_REMOVE_CONTROLS = 2;
    public static final int OPTION_STREAMING = 4;
    public static final short OUTPUT_REVERSE = 16;
    static final byte PDF = 16;
    static final byte PDI = 22;

    /* renamed from: R */
    static final byte f136R = 1;
    public static final short REMOVE_BIDI_CONTROLS = 8;
    static final short REORDER_COUNT = 7;
    public static final short REORDER_DEFAULT = 0;
    public static final short REORDER_GROUP_NUMBERS_WITH_R = 2;
    public static final short REORDER_INVERSE_FOR_NUMBERS_SPECIAL = 6;
    public static final short REORDER_INVERSE_LIKE_DIRECT = 5;
    public static final short REORDER_INVERSE_NUMBERS_AS_L = 4;
    static final short REORDER_LAST_LOGICAL_TO_VISUAL = 1;
    public static final short REORDER_NUMBERS_SPECIAL = 1;
    public static final short REORDER_RUNS_ONLY = 3;
    static final int RLM_AFTER = 8;
    static final int RLM_BEFORE = 4;
    public static final byte RTL = 1;

    /* renamed from: S */
    static final byte f137S = 8;
    static final int SEEKING_STRONG_FOR_FSI = 2;
    static final int SEEKING_STRONG_FOR_PARA = 1;
    static final int SIMPLE_OPENINGS_COUNT = 20;
    static final int SIMPLE_PARAS_COUNT = 10;

    /* renamed from: WS */
    static final byte f138WS = 9;
    private static final short _AN = 3;

    /* renamed from: _B */
    private static final short f139_B = 6;
    private static final short _EN = 2;

    /* renamed from: _L */
    private static final short f140_L = 0;
    private static final short _ON = 4;

    /* renamed from: _R */
    private static final short f141_R = 1;

    /* renamed from: _S */
    private static final short f142_S = 5;
    private static final short[] groupProp;
    private static final short[] impAct0;
    private static final short[] impAct1;
    private static final short[] impAct2;
    private static final short[] impAct3;
    private static final byte[][] impTabL_DEFAULT;
    private static final byte[][] impTabL_GROUP_NUMBERS_WITH_R;
    private static final byte[][] impTabL_INVERSE_FOR_NUMBERS_SPECIAL_WITH_MARKS;
    private static final byte[][] impTabL_INVERSE_LIKE_DIRECT_WITH_MARKS;
    private static final byte[][] impTabL_INVERSE_NUMBERS_AS_L;
    private static final byte[][] impTabL_NUMBERS_SPECIAL;
    private static final short[][] impTabProps;
    private static final byte[][] impTabR_DEFAULT;
    private static final byte[][] impTabR_GROUP_NUMBERS_WITH_R;
    private static final byte[][] impTabR_INVERSE_LIKE_DIRECT;
    private static final byte[][] impTabR_INVERSE_LIKE_DIRECT_WITH_MARKS;
    private static final byte[][] impTabR_INVERSE_NUMBERS_AS_L;
    private static final ImpTabPair impTab_DEFAULT;
    private static final ImpTabPair impTab_GROUP_NUMBERS_WITH_R;
    private static final ImpTabPair impTab_INVERSE_FOR_NUMBERS_SPECIAL;
    private static final ImpTabPair impTab_INVERSE_FOR_NUMBERS_SPECIAL_WITH_MARKS;
    private static final ImpTabPair impTab_INVERSE_LIKE_DIRECT;
    private static final ImpTabPair impTab_INVERSE_LIKE_DIRECT_WITH_MARKS;
    private static final ImpTabPair impTab_INVERSE_NUMBERS_AS_L;
    private static final ImpTabPair impTab_NUMBERS_SPECIAL;
    final UBiDiProps bdp;
    int controlCount;
    BidiClassifier customClassifier;
    byte defaultParaLevel;
    byte[] dirProps;
    byte[] dirPropsMemory;
    byte direction;
    String epilogue;
    int flags;
    ImpTabPair impTabPair;
    InsertPoints insertPoints;
    boolean isGoodLogicalToVisualRunsMap;
    boolean isInverse;
    int isolateCount;
    Isolate[] isolates;
    int lastArabicPos;
    int length;
    byte[] levels;
    byte[] levelsMemory;
    int[] logicalToVisualRunsMap;
    boolean mayAllocateRuns;
    boolean mayAllocateText;
    boolean orderParagraphsLTR;
    int originalLength;
    Bidi paraBidi;
    int paraCount;
    byte paraLevel;
    byte[] paras_level;
    int[] paras_limit;
    String prologue;
    int reorderingMode;
    int reorderingOptions;
    int resultLength;
    int runCount;
    BidiRun[] runs;
    BidiRun[] runsMemory;
    BidiRun[] simpleRuns;
    char[] text;
    int trailingWSStart;
    static final byte FOUND_L = (byte) DirPropFlag((byte) 0);
    static final byte FOUND_R = (byte) DirPropFlag((byte) 1);
    static final int DirPropFlagMultiRuns = DirPropFlag((byte) 31);
    static final int[] DirPropFlagLR = {DirPropFlag((byte) 0), DirPropFlag((byte) 1)};
    static final byte LRE = 11;
    static final byte RLE = 14;
    static final int[] DirPropFlagE = {DirPropFlag(LRE), DirPropFlag(RLE)};
    static final byte LRO = 12;
    static final byte RLO = 15;
    static final int[] DirPropFlagO = {DirPropFlag(LRO), DirPropFlag(RLO)};
    static final byte ENL = 23;
    static final byte ENR = 24;

    /* renamed from: AN */
    static final byte f125AN = 5;
    static final byte LRI = 20;
    static final int MASK_LTR = ((((((DirPropFlag((byte) 0) | DirPropFlag((byte) 2)) | DirPropFlag(ENL)) | DirPropFlag(ENR)) | DirPropFlag(f125AN)) | DirPropFlag(LRE)) | DirPropFlag(LRO)) | DirPropFlag(LRI);

    /* renamed from: AL */
    static final byte f124AL = 13;
    static final byte RLI = 21;
    static final int MASK_RTL = (((DirPropFlag((byte) 1) | DirPropFlag(f124AL)) | DirPropFlag(RLE)) | DirPropFlag(RLO)) | DirPropFlag(RLI);
    static final int MASK_R_AL = DirPropFlag((byte) 1) | DirPropFlag(f124AL);
    static final int MASK_STRONG_EN_AN = (((DirPropFlag((byte) 0) | DirPropFlag((byte) 1)) | DirPropFlag(f124AL)) | DirPropFlag((byte) 2)) | DirPropFlag(f125AN);

    /* loaded from: classes.dex */
    static class Point {
        int flag;
        int pos;

        Point() {
        }
    }

    /* loaded from: classes.dex */
    static class InsertPoints {
        int confirmed;
        Point[] points = new Point[0];
        int size;

        InsertPoints() {
        }
    }

    /* loaded from: classes.dex */
    static class Opening {
        byte contextDir;
        int contextPos;
        short flags;
        int match;
        int position;

        Opening() {
        }
    }

    /* loaded from: classes.dex */
    static class IsoRun {
        byte contextDir;
        int contextPos;
        byte lastBase;
        byte lastStrong;
        byte level;
        short limit;
        short start;

        IsoRun() {
        }
    }

    /* loaded from: classes.dex */
    static class BracketData {
        boolean isNumbersSpecial;
        int isoRunLast;
        Opening[] openings = new Opening[20];
        IsoRun[] isoRuns = new IsoRun[127];

        BracketData() {
        }
    }

    /* loaded from: classes.dex */
    static class Isolate {
        int start1;
        int startON;
        short state;
        short stateImp;

        Isolate() {
        }
    }

    static {
        int DirPropFlag = DirPropFlag(LRE) | DirPropFlag(LRO) | DirPropFlag(RLE) | DirPropFlag(RLO) | DirPropFlag(PDF);
        MASK_EXPLICIT = DirPropFlag;
        int DirPropFlag2 = DirPropFlag(f127BN) | DirPropFlag;
        MASK_BN_EXPLICIT = DirPropFlag2;
        int DirPropFlag3 = DirPropFlag(LRI) | DirPropFlag(RLI) | DirPropFlag(FSI) | DirPropFlag(PDI);
        MASK_ISO = DirPropFlag3;
        int DirPropFlag4 = DirPropFlag(f126B) | DirPropFlag(f137S);
        MASK_B_S = DirPropFlag4;
        int DirPropFlag5 = DirPropFlag4 | DirPropFlag(f138WS) | DirPropFlag2 | DirPropFlag3;
        MASK_WS = DirPropFlag5;
        int DirPropFlag6 = DirPropFlag(f135ON) | DirPropFlag(f129CS) | DirPropFlag((byte) 3) | DirPropFlag(f132ET) | DirPropFlag5;
        MASK_POSSIBLE_N = DirPropFlag6;
        MASK_EMBEDDING = DirPropFlag(NSM) | DirPropFlag6;
        groupProp = new short[]{0, 1, 2, REORDER_COUNT, 8, 3, 9, 6, 5, 4, 4, 10, 10, 12, 10, 10, 10, 11, 10, 4, 4, 4, 4, 13, 14};
        impTabProps = new short[][]{new short[]{1, 2, 4, 5, REORDER_COUNT, 15, 17, REORDER_COUNT, 9, REORDER_COUNT, 0, REORDER_COUNT, 3, 18, 21, 4}, new short[]{1, 34, 36, 37, 39, 47, 49, 39, 41, 39, 1, 1, 35, 50, 53, 0}, new short[]{33, 2, 36, 37, 39, 47, 49, 39, 41, 39, 2, 2, 35, 50, 53, 1}, new short[]{33, 34, 38, 38, 40, 48, 49, 40, 40, 40, 3, 3, 3, 50, 53, 1}, new short[]{33, 34, 4, 37, 39, 47, 49, 74, 11, 74, 4, 4, 35, 18, 21, 2}, new short[]{33, 34, 36, 5, 39, 47, 49, 39, 41, 76, 5, 5, 35, 50, 53, 3}, new short[]{33, 34, 6, 6, 40, 48, 49, 40, 40, 77, 6, 6, 35, 18, 21, 3}, new short[]{33, 34, 36, 37, REORDER_COUNT, 47, 49, REORDER_COUNT, 78, REORDER_COUNT, REORDER_COUNT, REORDER_COUNT, 35, 50, 53, 4}, new short[]{33, 34, 38, 38, 8, 48, 49, 8, 8, 8, 8, 8, 35, 50, 53, 4}, new short[]{33, 34, 4, 37, REORDER_COUNT, 47, 49, REORDER_COUNT, 9, REORDER_COUNT, 9, 9, 35, 18, 21, 4}, new short[]{97, 98, 4, 101, 135, 111, 113, 135, 142, 135, 10, 135, 99, 18, 21, 2}, new short[]{33, 34, 4, 37, 39, 47, 49, 39, 11, 39, 11, 11, 35, 18, 21, 2}, new short[]{97, 98, 100, 5, 135, 111, 113, 135, 142, 135, 12, 135, 99, 114, 117, 3}, new short[]{97, 98, 6, 6, 136, 112, 113, 136, 136, 136, 13, 136, 99, 18, 21, 3}, new short[]{33, 34, 132, 37, REORDER_COUNT, 47, 49, REORDER_COUNT, 14, REORDER_COUNT, 14, 14, 35, 146, 149, 4}, new short[]{33, 34, 36, 37, 39, 15, 49, 39, 41, 39, 15, 39, 35, 50, 53, 5}, new short[]{33, 34, 38, 38, 40, 16, 49, 40, 40, 40, 16, 40, 35, 50, 53, 5}, new short[]{33, 34, 36, 37, 39, 47, 17, 39, 41, 39, 17, 39, 35, 50, 53, 6}, new short[]{33, 34, 18, 37, 39, 47, 49, 83, 20, 83, 18, 18, 35, 18, 21, 0}, new short[]{97, 98, 18, 101, 135, 111, 113, 135, 142, 135, 19, 135, 99, 18, 21, 0}, new short[]{33, 34, 18, 37, 39, 47, 49, 39, 20, 39, 20, 20, 35, 18, 21, 0}, new short[]{33, 34, 21, 37, 39, 47, 49, 86, 23, 86, 21, 21, 35, 18, 21, 3}, new short[]{97, 98, 21, 101, 135, 111, 113, 135, 142, 135, 22, 135, 99, 18, 21, 3}, new short[]{33, 34, 21, 37, 39, 47, 49, 39, 23, 39, 23, 23, 35, 18, 21, 3}};
        byte[][] bArr = {new byte[]{0, 1, 0, 2, 0, 0, 0, 0}, new byte[]{0, 1, 3, 3, LRI, LRI, 0, 1}, new byte[]{0, 1, 0, 2, RLI, RLI, 0, 2}, new byte[]{0, 1, 3, 3, LRI, LRI, 0, 2}, new byte[]{0, 33, 51, 51, f132ET, f132ET, 0, 0}, new byte[]{0, 33, 0, 50, f125AN, f125AN, 0, 0}};
        impTabL_DEFAULT = bArr;
        byte[][] bArr2 = {new byte[]{1, 0, 2, 2, 0, 0, 0, 0}, new byte[]{1, 0, 1, 3, LRI, LRI, 0, 1}, new byte[]{1, 0, 2, 2, 0, 0, 0, 1}, new byte[]{1, 0, 1, 3, f125AN, f125AN, 0, 1}, new byte[]{33, 0, 33, 3, f132ET, f132ET, 0, 0}, new byte[]{1, 0, 1, 3, f125AN, f125AN, 0, 0}};
        impTabR_DEFAULT = bArr2;
        short[] sArr = {0, 1, 2, 3, 4};
        impAct0 = sArr;
        impTab_DEFAULT = new ImpTabPair(bArr, bArr2, sArr, sArr);
        byte[][] bArr3 = {new byte[]{0, 2, NSM, NSM, 0, 0, 0, 0}, new byte[]{0, 66, 1, 1, 0, 0, 0, 0}, new byte[]{0, 2, f132ET, f132ET, FSI, FSI, 0, 1}, new byte[]{0, 34, 52, 52, 3, 3, 0, 0}, new byte[]{0, 2, f132ET, f132ET, FSI, FSI, 0, 2}};
        impTabL_NUMBERS_SPECIAL = bArr3;
        impTab_NUMBERS_SPECIAL = new ImpTabPair(bArr3, bArr2, sArr, sArr);
        byte[][] bArr4 = {new byte[]{0, 3, NSM, NSM, 0, 0, 0, 0}, new byte[]{32, 3, 1, 1, 2, 32, 32, 2}, new byte[]{32, 3, 1, 1, 2, 32, 32, 1}, new byte[]{0, 3, f125AN, f125AN, LRI, 0, 0, 1}, new byte[]{32, 3, f125AN, f125AN, f132ET, 32, 32, 1}, new byte[]{0, 3, f125AN, f125AN, LRI, 0, 0, 2}};
        impTabL_GROUP_NUMBERS_WITH_R = bArr4;
        byte[][] bArr5 = {new byte[]{2, 0, 1, 1, 0, 0, 0, 0}, new byte[]{2, 0, 1, 1, 0, 0, 0, 1}, new byte[]{2, 0, LRI, LRI, FSI, 0, 0, 1}, new byte[]{34, 0, f132ET, f132ET, 3, 0, 0, 0}, new byte[]{34, 0, f132ET, f132ET, 3, 0, 0, 1}};
        impTabR_GROUP_NUMBERS_WITH_R = bArr5;
        impTab_GROUP_NUMBERS_WITH_R = new ImpTabPair(bArr4, bArr5, sArr, sArr);
        byte[][] bArr6 = {new byte[]{0, 1, 0, 0, 0, 0, 0, 0}, new byte[]{0, 1, 0, 0, LRI, LRI, 0, 1}, new byte[]{0, 1, 0, 0, RLI, RLI, 0, 2}, new byte[]{0, 1, 0, 0, LRI, LRI, 0, 2}, new byte[]{32, 1, 32, 32, f132ET, f132ET, 32, 1}, new byte[]{32, 1, 32, 32, f125AN, f125AN, 32, 1}};
        impTabL_INVERSE_NUMBERS_AS_L = bArr6;
        byte[][] bArr7 = {new byte[]{1, 0, 1, 1, 0, 0, 0, 0}, new byte[]{1, 0, 1, 1, LRI, LRI, 0, 1}, new byte[]{1, 0, 1, 1, 0, 0, 0, 1}, new byte[]{1, 0, 1, 1, f125AN, f125AN, 0, 1}, new byte[]{33, 0, 33, 33, f132ET, f132ET, 0, 0}, new byte[]{1, 0, 1, 1, f125AN, f125AN, 0, 0}};
        impTabR_INVERSE_NUMBERS_AS_L = bArr7;
        impTab_INVERSE_NUMBERS_AS_L = new ImpTabPair(bArr6, bArr7, sArr, sArr);
        byte[][] bArr8 = {new byte[]{1, 0, 2, 2, 0, 0, 0, 0}, new byte[]{1, 0, 1, 2, FSI, FSI, 0, 1}, new byte[]{1, 0, 2, 2, 0, 0, 0, 1}, new byte[]{33, 48, f129CS, f132ET, 3, 3, 48, 0}, new byte[]{33, 48, f129CS, f132ET, f125AN, f125AN, 48, 3}, new byte[]{33, 48, f129CS, f132ET, f125AN, f125AN, 48, 2}, new byte[]{33, 48, f129CS, f132ET, 3, 3, 48, 1}};
        impTabR_INVERSE_LIKE_DIRECT = bArr8;
        short[] sArr2 = {0, 1, 13, 14};
        impAct1 = sArr2;
        impTab_INVERSE_LIKE_DIRECT = new ImpTabPair(bArr, bArr8, sArr, sArr2);
        byte[][] bArr9 = {new byte[]{0, 99, 0, 1, 0, 0, 0, 0}, new byte[]{0, 99, 0, 1, f127BN, 48, 0, f132ET}, new byte[]{32, 99, 32, 1, 2, 48, 32, 3}, new byte[]{0, 99, 85, 86, LRI, 48, 0, 3}, new byte[]{48, 67, 85, 86, f132ET, 48, 48, 3}, new byte[]{48, 67, f125AN, 86, LRI, 48, 48, f132ET}, new byte[]{48, 67, 85, f129CS, LRI, 48, 48, f132ET}};
        impTabL_INVERSE_LIKE_DIRECT_WITH_MARKS = bArr9;
        byte[][] bArr10 = {new byte[]{FSI, 0, 1, 1, 0, 0, 0, 0}, new byte[]{35, 0, 1, 1, 2, 64, 0, 1}, new byte[]{35, 0, 1, 1, 2, 64, 0, 0}, new byte[]{3, 0, 3, 54, LRI, 64, 0, 1}, new byte[]{83, 64, f125AN, 54, f132ET, 64, 64, 0}, new byte[]{83, 64, f125AN, 54, f132ET, 64, 64, 1}, new byte[]{83, 64, f129CS, f129CS, f132ET, 64, 64, 3}};
        impTabR_INVERSE_LIKE_DIRECT_WITH_MARKS = bArr10;
        short[] sArr3 = {0, 1, 2, 5, 6, REORDER_COUNT, 8};
        impAct2 = sArr3;
        short[] sArr4 = {0, 1, 9, 10, 11, 12};
        impAct3 = sArr4;
        impTab_INVERSE_LIKE_DIRECT_WITH_MARKS = new ImpTabPair(bArr9, bArr10, sArr3, sArr4);
        impTab_INVERSE_FOR_NUMBERS_SPECIAL = new ImpTabPair(bArr3, bArr8, sArr, sArr2);
        byte[][] bArr11 = {new byte[]{0, 98, 1, 1, 0, 0, 0, 0}, new byte[]{0, 98, 1, 1, 0, 48, 0, f132ET}, new byte[]{0, 98, 84, 84, FSI, 48, 0, 3}, new byte[]{48, 66, 84, 84, 3, 48, 48, 3}, new byte[]{48, 66, f132ET, f132ET, FSI, 48, 48, f132ET}};
        impTabL_INVERSE_FOR_NUMBERS_SPECIAL_WITH_MARKS = bArr11;
        impTab_INVERSE_FOR_NUMBERS_SPECIAL_WITH_MARKS = new ImpTabPair(bArr11, bArr10, sArr3, sArr4);
    }

    static int DirPropFlag(byte dir) {
        return 1 << dir;
    }

    boolean testDirPropFlagAt(int flag, int index) {
        return (DirPropFlag(this.dirProps[index]) & flag) != 0;
    }

    static final int DirPropFlagLR(byte level) {
        return DirPropFlagLR[level & 1];
    }

    static final int DirPropFlagE(byte level) {
        return DirPropFlagE[level & 1];
    }

    static final int DirPropFlagO(byte level) {
        return DirPropFlagO[level & 1];
    }

    static final byte DirFromStrong(byte strong) {
        return strong == 0 ? (byte) 0 : (byte) 1;
    }

    static final byte NoOverride(byte level) {
        return (byte) (level & Byte.MAX_VALUE);
    }

    static byte GetLRFromLevel(byte level) {
        return (byte) (level & 1);
    }

    static boolean IsDefaultLevel(byte level) {
        return (level & LEVEL_DEFAULT_LTR) == 126;
    }

    static boolean IsBidiControlChar(int c) {
        return (c & (-4)) == 8204 || (c >= 8234 && c <= 8238) || (c >= 8294 && c <= 8297);
    }

    void verifyValidPara() {
        if (this != this.paraBidi) {
            throw new IllegalStateException();
        }
    }

    void verifyValidParaOrLine() {
        Bidi para = this.paraBidi;
        if (this == para) {
            return;
        }
        if (para == null || para != para.paraBidi) {
            throw new IllegalStateException();
        }
    }

    void verifyRange(int index, int start, int limit) {
        if (index < start || index >= limit) {
            throw new IllegalArgumentException("Value " + index + " is out of range " + start + " to " + limit);
        }
    }

    public Bidi() {
        this(0, 0);
    }

    public Bidi(int maxLength, int maxRunCount) {
        this.dirPropsMemory = new byte[1];
        this.levelsMemory = new byte[1];
        this.paras_limit = new int[10];
        this.paras_level = new byte[10];
        this.runsMemory = new BidiRun[0];
        this.simpleRuns = new BidiRun[]{new BidiRun()};
        this.customClassifier = null;
        this.insertPoints = new InsertPoints();
        if (maxLength < 0 || maxRunCount < 0) {
            throw new IllegalArgumentException();
        }
        this.bdp = UBiDiProps.INSTANCE;
        if (maxLength > 0) {
            getInitialDirPropsMemory(maxLength);
            getInitialLevelsMemory(maxLength);
        } else {
            this.mayAllocateText = true;
        }
        if (maxRunCount > 0) {
            if (maxRunCount > 1) {
                getInitialRunsMemory(maxRunCount);
                return;
            }
            return;
        }
        this.mayAllocateRuns = true;
    }

    private Object getMemory(String label, Object array, Class<?> arrayClass, boolean mayAllocate, int sizeNeeded) {
        int len = Array.getLength(array);
        if (sizeNeeded == len) {
            return array;
        }
        if (!mayAllocate) {
            if (sizeNeeded <= len) {
                return array;
            }
            throw new OutOfMemoryError("Failed to allocate memory for " + label);
        }
        try {
            return Array.newInstance(arrayClass, sizeNeeded);
        } catch (Exception e) {
            throw new OutOfMemoryError("Failed to allocate memory for " + label);
        }
    }

    private void getDirPropsMemory(boolean mayAllocate, int len) {
        Object array = getMemory("DirProps", this.dirPropsMemory, Byte.TYPE, mayAllocate, len);
        this.dirPropsMemory = (byte[]) array;
    }

    void getDirPropsMemory(int len) {
        getDirPropsMemory(this.mayAllocateText, len);
    }

    private void getLevelsMemory(boolean mayAllocate, int len) {
        Object array = getMemory("Levels", this.levelsMemory, Byte.TYPE, mayAllocate, len);
        this.levelsMemory = (byte[]) array;
    }

    void getLevelsMemory(int len) {
        getLevelsMemory(this.mayAllocateText, len);
    }

    private void getRunsMemory(boolean mayAllocate, int len) {
        Object array = getMemory("Runs", this.runsMemory, BidiRun.class, mayAllocate, len);
        this.runsMemory = (BidiRun[]) array;
    }

    void getRunsMemory(int len) {
        getRunsMemory(this.mayAllocateRuns, len);
    }

    private void getInitialDirPropsMemory(int len) {
        getDirPropsMemory(true, len);
    }

    private void getInitialLevelsMemory(int len) {
        getLevelsMemory(true, len);
    }

    private void getInitialRunsMemory(int len) {
        getRunsMemory(true, len);
    }

    public void setInverse(boolean isInverse) {
        this.isInverse = isInverse;
        this.reorderingMode = isInverse ? 4 : 0;
    }

    public boolean isInverse() {
        return this.isInverse;
    }

    public void setReorderingMode(int reorderingMode) {
        if (reorderingMode < 0 || reorderingMode >= 7) {
            return;
        }
        this.reorderingMode = reorderingMode;
        this.isInverse = reorderingMode == 4;
    }

    public int getReorderingMode() {
        return this.reorderingMode;
    }

    public void setReorderingOptions(int options) {
        if ((options & 2) != 0) {
            this.reorderingOptions = options & (-2);
        } else {
            this.reorderingOptions = options;
        }
    }

    public int getReorderingOptions() {
        return this.reorderingOptions;
    }

    public static byte getBaseDirection(CharSequence paragraph) {
        if (paragraph == null || paragraph.length() == 0) {
            return (byte) 3;
        }
        int length = paragraph.length();
        int i = 0;
        while (i < length) {
            int c = UCharacter.codePointAt(paragraph, i);
            byte direction = UCharacter.getDirectionality(c);
            if (direction == 0) {
                return (byte) 0;
            }
            if (direction == 1 || direction == 13) {
                return (byte) 1;
            }
            i = UCharacter.offsetByCodePoints(paragraph, i, 1);
        }
        return (byte) 3;
    }

    private byte firstL_R_AL() {
        byte result = f135ON;
        int i = 0;
        while (i < this.prologue.length()) {
            int uchar = this.prologue.codePointAt(i);
            i += Character.charCount(uchar);
            byte dirProp = (byte) getCustomizedClass(uchar);
            if (result == 10) {
                if (dirProp == 0 || dirProp == 1 || dirProp == 13) {
                    result = dirProp;
                }
            } else if (dirProp == 7) {
                result = f135ON;
            }
        }
        return result;
    }

    private void checkParaCount() {
        int count = this.paraCount;
        byte[] bArr = this.paras_level;
        if (count <= bArr.length) {
            return;
        }
        int oldLength = bArr.length;
        int[] saveLimits = this.paras_limit;
        byte[] saveLevels = this.paras_level;
        try {
            int[] iArr = new int[count * 2];
            this.paras_limit = iArr;
            this.paras_level = new byte[count * 2];
            System.arraycopy(saveLimits, 0, iArr, 0, oldLength);
            System.arraycopy(saveLevels, 0, this.paras_level, 0, oldLength);
        } catch (Exception e) {
            throw new OutOfMemoryError("Failed to allocate memory for paras");
        }
    }

    private void getDirProps() {
        boolean isDefaultLevelInverse;
        boolean removeBidiControls;
        byte state;
        byte b;
        char c;
        boolean removeBidiControls2;
        byte dirProp;
        int i;
        byte dirProp2 = 0;
        this.flags = 0;
        boolean isDefaultLevel = IsDefaultLevel(this.paraLevel);
        if (!isDefaultLevel || ((i = this.reorderingMode) != 5 && i != 6)) {
            isDefaultLevelInverse = false;
        } else {
            isDefaultLevelInverse = true;
        }
        this.lastArabicPos = -1;
        int controlCount = 0;
        int i2 = this.reorderingOptions;
        if ((i2 & 2) == 0) {
            removeBidiControls = false;
        } else {
            removeBidiControls = true;
        }
        byte lastStrong = f135ON;
        int[] isolateStartStack = new int[126];
        byte[] previousStateStack = new byte[126];
        int stackLast = -1;
        if ((i2 & 4) != 0) {
            this.length = 0;
        }
        byte b2 = this.paraLevel;
        byte defaultParaLevel = (byte) (b2 & 1);
        if (isDefaultLevel) {
            this.paras_level[0] = defaultParaLevel;
            lastStrong = defaultParaLevel;
            if (this.prologue != null && (dirProp = firstL_R_AL()) != 10) {
                if (dirProp == 0) {
                    this.paras_level[0] = 0;
                } else {
                    this.paras_level[0] = 1;
                }
                state = 0;
            } else {
                state = 1;
            }
        } else {
            this.paras_level[0] = b2;
            state = 0;
        }
        int i3 = 0;
        while (true) {
            int i4 = this.originalLength;
            if (i3 >= i4) {
                break;
            }
            int i0 = i3;
            int uchar = UTF16.charAt(this.text, dirProp2, i4, i3);
            i3 += UTF16.getCharCount(uchar);
            int i1 = i3 - 1;
            byte dirProp3 = (byte) getCustomizedClass(uchar);
            int DirPropFlag = this.flags | DirPropFlag(dirProp3);
            this.flags = DirPropFlag;
            byte defaultParaLevel2 = defaultParaLevel;
            this.dirProps[i1] = dirProp3;
            if (i1 > i0) {
                this.flags = DirPropFlag | DirPropFlag(f127BN);
                do {
                    c = '\uffff';
                    i1--;
                    this.dirProps[i1] = f127BN;
                } while (i1 > i0);
            } else {
                c = '\uffff';
            }
            if (removeBidiControls && IsBidiControlChar(uchar)) {
                controlCount++;
            }
            if (dirProp3 == 0) {
                if (state == 1) {
                    removeBidiControls2 = removeBidiControls;
                    this.paras_level[this.paraCount - 1] = 0;
                    state = 0;
                } else {
                    removeBidiControls2 = removeBidiControls;
                    if (state == 2) {
                        if (stackLast <= 125) {
                            this.flags |= DirPropFlag(LRI);
                        }
                        state = 3;
                    }
                }
                lastStrong = 0;
                defaultParaLevel = defaultParaLevel2;
                removeBidiControls = removeBidiControls2;
                dirProp2 = 0;
            } else {
                boolean removeBidiControls3 = removeBidiControls;
                if (dirProp3 == 1 || dirProp3 == 13) {
                    if (state == 1) {
                        this.paras_level[this.paraCount - 1] = 1;
                        state = 0;
                    } else if (state == 2) {
                        if (stackLast <= 125) {
                            this.dirProps[isolateStartStack[stackLast]] = RLI;
                            this.flags = DirPropFlag(RLI) | this.flags;
                        }
                        state = 3;
                    }
                    lastStrong = 1;
                    if (dirProp3 == 13) {
                        this.lastArabicPos = i3 - 1;
                    }
                } else if (dirProp3 >= 19 && dirProp3 <= 21) {
                    stackLast++;
                    if (stackLast <= 125) {
                        isolateStartStack[stackLast] = i3 - 1;
                        previousStateStack[stackLast] = state;
                    }
                    if (dirProp3 == 19) {
                        this.dirProps[i3 - 1] = LRI;
                        state = 2;
                        defaultParaLevel = defaultParaLevel2;
                        removeBidiControls = removeBidiControls3;
                        dirProp2 = 0;
                    } else {
                        state = 3;
                        defaultParaLevel = defaultParaLevel2;
                        removeBidiControls = removeBidiControls3;
                        dirProp2 = 0;
                    }
                } else if (dirProp3 == 22) {
                    if (state == 2 && stackLast <= 125) {
                        this.flags |= DirPropFlag(LRI);
                    }
                    if (stackLast >= 0) {
                        if (stackLast <= 125) {
                            state = previousStateStack[stackLast];
                        }
                        stackLast--;
                        defaultParaLevel = defaultParaLevel2;
                        removeBidiControls = removeBidiControls3;
                        dirProp2 = 0;
                    }
                } else if (dirProp3 == 7) {
                    int i5 = this.originalLength;
                    if (i3 < i5 && uchar == 13) {
                        if (this.text[i3] == '\n') {
                        }
                    }
                    int[] iArr = this.paras_limit;
                    int i6 = this.paraCount;
                    iArr[i6 - 1] = i3;
                    if (isDefaultLevelInverse && lastStrong == 1) {
                        this.paras_level[i6 - 1] = 1;
                    }
                    if ((this.reorderingOptions & 4) != 0) {
                        this.length = i3;
                        this.controlCount = controlCount;
                    }
                    if (i3 < i5) {
                        this.paraCount = i6 + 1;
                        checkParaCount();
                        if (isDefaultLevel) {
                            this.paras_level[this.paraCount - 1] = defaultParaLevel2;
                            state = 1;
                            lastStrong = defaultParaLevel2;
                        } else {
                            this.paras_level[this.paraCount - 1] = this.paraLevel;
                            state = 0;
                        }
                        stackLast = -1;
                        defaultParaLevel = defaultParaLevel2;
                        removeBidiControls = removeBidiControls3;
                        dirProp2 = 0;
                    }
                }
                defaultParaLevel = defaultParaLevel2;
                removeBidiControls = removeBidiControls3;
                dirProp2 = 0;
            }
        }
        if (stackLast > 125) {
            stackLast = 125;
            state = 2;
        }
        while (true) {
            if (stackLast < 0) {
                break;
            } else if (state == 2) {
                this.flags |= DirPropFlag(LRI);
                break;
            } else {
                state = previousStateStack[stackLast];
                stackLast--;
            }
        }
        if ((this.reorderingOptions & 4) != 0) {
            if (this.length < this.originalLength) {
                b = 1;
                this.paraCount--;
            } else {
                b = 1;
            }
        } else {
            b = 1;
            this.paras_limit[this.paraCount - 1] = this.originalLength;
            this.controlCount = controlCount;
        }
        if (isDefaultLevelInverse && lastStrong == b) {
            this.paras_level[this.paraCount - b] = b;
        }
        if (isDefaultLevel) {
            this.paraLevel = this.paras_level[0];
        }
        for (int i7 = 0; i7 < this.paraCount; i7++) {
            this.flags |= DirPropFlagLR(this.paras_level[i7]);
        }
        if (this.orderParagraphsLTR && (this.flags & DirPropFlag(f126B)) != 0) {
            this.flags |= DirPropFlag((byte) 0);
        }
    }

    byte GetParaLevelAt(int pindex) {
        int i;
        if (this.defaultParaLevel == 0 || pindex < this.paras_limit[0]) {
            return this.paraLevel;
        }
        int i2 = 1;
        while (true) {
            i = this.paraCount;
            if (i2 >= i || pindex < this.paras_limit[i2]) {
                break;
            }
            i2++;
        }
        if (i2 >= i) {
            i2 = i - 1;
        }
        return this.paras_level[i2];
    }

    private void bracketInit(BracketData bd) {
        boolean z = false;
        bd.isoRunLast = 0;
        bd.isoRuns[0] = new IsoRun();
        bd.isoRuns[0].start = (short) 0;
        bd.isoRuns[0].limit = (short) 0;
        bd.isoRuns[0].level = GetParaLevelAt(0);
        IsoRun isoRun = bd.isoRuns[0];
        IsoRun isoRun2 = bd.isoRuns[0];
        IsoRun isoRun3 = bd.isoRuns[0];
        byte GetParaLevelAt = (byte) (GetParaLevelAt(0) & 1);
        isoRun3.contextDir = GetParaLevelAt;
        isoRun2.lastBase = GetParaLevelAt;
        isoRun.lastStrong = GetParaLevelAt;
        bd.isoRuns[0].contextPos = 0;
        bd.openings = new Opening[20];
        int i = this.reorderingMode;
        if (i == 1 || i == 6) {
            z = true;
        }
        bd.isNumbersSpecial = z;
    }

    private void bracketProcessB(BracketData bd, byte level) {
        bd.isoRunLast = 0;
        bd.isoRuns[0].limit = (short) 0;
        bd.isoRuns[0].level = level;
        IsoRun isoRun = bd.isoRuns[0];
        IsoRun isoRun2 = bd.isoRuns[0];
        byte b = (byte) (level & 1);
        bd.isoRuns[0].contextDir = b;
        isoRun2.lastBase = b;
        isoRun.lastStrong = b;
        bd.isoRuns[0].contextPos = 0;
    }

    private void bracketProcessBoundary(BracketData bd, int lastCcPos, byte contextLevel, byte embeddingLevel) {
        IsoRun pLastIsoRun = bd.isoRuns[bd.isoRunLast];
        if ((DirPropFlag(this.dirProps[lastCcPos]) & MASK_ISO) != 0) {
            return;
        }
        if (NoOverride(embeddingLevel) > NoOverride(contextLevel)) {
            contextLevel = embeddingLevel;
        }
        pLastIsoRun.limit = pLastIsoRun.start;
        pLastIsoRun.level = embeddingLevel;
        byte b = (byte) (contextLevel & 1);
        pLastIsoRun.contextDir = b;
        pLastIsoRun.lastBase = b;
        pLastIsoRun.lastStrong = b;
        pLastIsoRun.contextPos = lastCcPos;
    }

    private void bracketProcessLRI_RLI(BracketData bd, byte level) {
        IsoRun pLastIsoRun = bd.isoRuns[bd.isoRunLast];
        pLastIsoRun.lastBase = f135ON;
        short lastLimit = pLastIsoRun.limit;
        bd.isoRunLast++;
        IsoRun pLastIsoRun2 = bd.isoRuns[bd.isoRunLast];
        if (pLastIsoRun2 == null) {
            IsoRun[] isoRunArr = bd.isoRuns;
            int i = bd.isoRunLast;
            IsoRun isoRun = new IsoRun();
            isoRunArr[i] = isoRun;
            pLastIsoRun2 = isoRun;
        }
        pLastIsoRun2.limit = lastLimit;
        pLastIsoRun2.start = lastLimit;
        pLastIsoRun2.level = level;
        byte b = (byte) (level & 1);
        pLastIsoRun2.contextDir = b;
        pLastIsoRun2.lastBase = b;
        pLastIsoRun2.lastStrong = b;
        pLastIsoRun2.contextPos = 0;
    }

    private void bracketProcessPDI(BracketData bd) {
        bd.isoRunLast--;
        IsoRun pLastIsoRun = bd.isoRuns[bd.isoRunLast];
        pLastIsoRun.lastBase = f135ON;
    }

    private void bracketAddOpening(BracketData bd, char match, int position) {
        IsoRun pLastIsoRun = bd.isoRuns[bd.isoRunLast];
        if (pLastIsoRun.limit >= bd.openings.length) {
            Opening[] saveOpenings = bd.openings;
            try {
                int count = bd.openings.length;
                bd.openings = new Opening[count * 2];
                System.arraycopy(saveOpenings, 0, bd.openings, 0, count);
            } catch (Exception e) {
                throw new OutOfMemoryError("Failed to allocate memory for openings");
            }
        }
        Opening[] saveOpenings2 = bd.openings;
        Opening pOpening = saveOpenings2[pLastIsoRun.limit];
        if (pOpening == null) {
            Opening[] openingArr = bd.openings;
            short s = pLastIsoRun.limit;
            Opening opening = new Opening();
            openingArr[s] = opening;
            pOpening = opening;
        }
        pOpening.position = position;
        pOpening.match = match;
        pOpening.contextDir = pLastIsoRun.contextDir;
        pOpening.contextPos = pLastIsoRun.contextPos;
        pOpening.flags = (short) 0;
        pLastIsoRun.limit = (short) (pLastIsoRun.limit + 1);
    }

    private void fixN0c(BracketData bd, int openingIndex, int newPropPosition, byte newProp) {
        IsoRun pLastIsoRun = bd.isoRuns[bd.isoRunLast];
        for (int k = openingIndex + 1; k < pLastIsoRun.limit; k++) {
            Opening qOpening = bd.openings[k];
            if (qOpening.match < 0) {
                if (newPropPosition >= qOpening.contextPos) {
                    if (newPropPosition >= qOpening.position) {
                        continue;
                    } else if (newProp != qOpening.contextDir) {
                        int openingPosition = qOpening.position;
                        this.dirProps[openingPosition] = newProp;
                        int closingPosition = -qOpening.match;
                        this.dirProps[closingPosition] = newProp;
                        qOpening.match = 0;
                        fixN0c(bd, k, openingPosition, newProp);
                        fixN0c(bd, k, closingPosition, newProp);
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private byte bracketProcessClosing(BracketData bd, int openIdx, int position) {
        byte newProp;
        IsoRun pLastIsoRun = bd.isoRuns[bd.isoRunLast];
        Opening pOpening = bd.openings[openIdx];
        byte direction = (byte) (pLastIsoRun.level & 1);
        boolean stable = true;
        if ((direction == 0 && (pOpening.flags & FOUND_L) > 0) || (direction == 1 && (pOpening.flags & FOUND_R) > 0)) {
            newProp = direction;
        } else if ((pOpening.flags & (FOUND_L | FOUND_R)) != 0) {
            stable = openIdx == pLastIsoRun.start;
            if (direction != pOpening.contextDir) {
                newProp = pOpening.contextDir;
            } else {
                newProp = direction;
            }
        } else {
            pLastIsoRun.limit = (short) openIdx;
            return f135ON;
        }
        this.dirProps[pOpening.position] = newProp;
        this.dirProps[position] = newProp;
        fixN0c(bd, openIdx, pOpening.position, newProp);
        if (stable) {
            pLastIsoRun.limit = (short) openIdx;
            while (pLastIsoRun.limit > pLastIsoRun.start && bd.openings[pLastIsoRun.limit - 1].position == pOpening.position) {
                pLastIsoRun.limit = (short) (pLastIsoRun.limit - 1);
            }
        } else {
            pOpening.match = -position;
            for (int k = openIdx - 1; k >= pLastIsoRun.start && bd.openings[k].position == pOpening.position; k--) {
                bd.openings[k].match = 0;
            }
            for (int k2 = openIdx + 1; k2 < pLastIsoRun.limit; k2++) {
                Opening qOpening = bd.openings[k2];
                if (qOpening.position >= position) {
                    break;
                }
                if (qOpening.match > 0) {
                    qOpening.match = 0;
                }
            }
        }
        return newProp;
    }

    private void bracketProcessChar(BracketData bd, int position) {
        byte newProp;
        char match;
        IsoRun pLastIsoRun = bd.isoRuns[bd.isoRunLast];
        byte dirProp = this.dirProps[position];
        if (dirProp == 10) {
            char c = this.text[position];
            int idx = pLastIsoRun.limit - 1;
            while (true) {
                if (idx < pLastIsoRun.start) {
                    break;
                } else if (bd.openings[idx].match != c) {
                    idx--;
                } else {
                    byte newProp2 = bracketProcessClosing(bd, idx, position);
                    if (newProp2 == 10) {
                        c = 0;
                    } else {
                        pLastIsoRun.lastBase = f135ON;
                        pLastIsoRun.contextDir = newProp2;
                        pLastIsoRun.contextPos = position;
                        byte level = this.levels[position];
                        if ((level & Byte.MIN_VALUE) != 0) {
                            byte newProp3 = (byte) (level & 1);
                            pLastIsoRun.lastStrong = newProp3;
                            short flag = (short) DirPropFlag(newProp3);
                            for (int i = pLastIsoRun.start; i < idx; i++) {
                                Opening opening = bd.openings[i];
                                opening.flags = (short) (opening.flags | flag);
                            }
                            byte[] bArr = this.levels;
                            bArr[position] = (byte) (bArr[position] & Byte.MAX_VALUE);
                        }
                        byte[] bArr2 = this.levels;
                        int i2 = bd.openings[idx].position;
                        bArr2[i2] = (byte) (bArr2[i2] & Byte.MAX_VALUE);
                        return;
                    }
                }
            }
            if (c != 0) {
                match = (char) UCharacter.getBidiPairedBracket(c);
            } else {
                match = 0;
            }
            if (match != c && UCharacter.getIntPropertyValue(c, 4117) == 1) {
                if (match == '\u232a') {
                    bracketAddOpening(bd, '\u3009', position);
                } else if (match == '\u3009') {
                    bracketAddOpening(bd, '\u232a', position);
                }
                bracketAddOpening(bd, match, position);
            }
        }
        byte level2 = this.levels[position];
        if ((level2 & Byte.MIN_VALUE) != 0) {
            newProp = (byte) (level2 & 1);
            if (dirProp != 8 && dirProp != 9 && dirProp != 10) {
                this.dirProps[position] = newProp;
            }
            pLastIsoRun.lastBase = newProp;
            pLastIsoRun.lastStrong = newProp;
            pLastIsoRun.contextDir = newProp;
            pLastIsoRun.contextPos = position;
        } else if (dirProp <= 1 || dirProp == 13) {
            newProp = DirFromStrong(dirProp);
            pLastIsoRun.lastBase = dirProp;
            pLastIsoRun.lastStrong = dirProp;
            pLastIsoRun.contextDir = newProp;
            pLastIsoRun.contextPos = position;
        } else if (dirProp == 2) {
            pLastIsoRun.lastBase = (byte) 2;
            if (pLastIsoRun.lastStrong == 0) {
                newProp = 0;
                if (!bd.isNumbersSpecial) {
                    this.dirProps[position] = ENL;
                }
                pLastIsoRun.contextDir = (byte) 0;
                pLastIsoRun.contextPos = position;
            } else {
                newProp = 1;
                if (pLastIsoRun.lastStrong == 13) {
                    this.dirProps[position] = f125AN;
                } else {
                    this.dirProps[position] = ENR;
                }
                pLastIsoRun.contextDir = (byte) 1;
                pLastIsoRun.contextPos = position;
            }
        } else if (dirProp == 5) {
            newProp = 1;
            pLastIsoRun.lastBase = f125AN;
            pLastIsoRun.contextDir = (byte) 1;
            pLastIsoRun.contextPos = position;
        } else if (dirProp == 17) {
            newProp = pLastIsoRun.lastBase;
            if (newProp == 10) {
                this.dirProps[position] = newProp;
            }
        } else {
            newProp = dirProp;
            pLastIsoRun.lastBase = dirProp;
        }
        if (newProp <= 1 || newProp == 13) {
            short flag2 = (short) DirPropFlag(DirFromStrong(newProp));
            for (int i3 = pLastIsoRun.start; i3 < pLastIsoRun.limit; i3++) {
                if (position > bd.openings[i3].position) {
                    Opening opening2 = bd.openings[i3];
                    opening2.flags = (short) (opening2.flags | flag2);
                }
            }
        }
    }

    private byte directionFromFlags() {
        int i = this.flags;
        if ((MASK_RTL & i) == 0 && ((i & DirPropFlag(f125AN)) == 0 || (this.flags & MASK_POSSIBLE_N) == 0)) {
            return (byte) 0;
        }
        if ((this.flags & MASK_LTR) == 0) {
            return (byte) 1;
        }
        return (byte) 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x026e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private byte resolveExplicitLevels() {
        byte b;
        byte newLevel;
        byte newLevel2;
        int start;
        int start2;
        byte level = GetParaLevelAt(0);
        this.isolateCount = 0;
        byte dirct = directionFromFlags();
        if (dirct != 2) {
            return dirct;
        }
        byte newLevel3 = 1;
        if (this.reorderingMode > 1) {
            for (int paraIndex = 0; paraIndex < this.paraCount; paraIndex++) {
                if (paraIndex == 0) {
                    start2 = 0;
                } else {
                    start2 = this.paras_limit[paraIndex - 1];
                }
                int limit = this.paras_limit[paraIndex];
                byte level2 = this.paras_level[paraIndex];
                for (int i = start2; i < limit; i++) {
                    this.levels[i] = level2;
                }
            }
            return dirct;
        }
        int i2 = this.flags & (MASK_EXPLICIT | MASK_ISO);
        byte b2 = f135ON;
        if (i2 == 0) {
            BracketData bracketData = new BracketData();
            bracketInit(bracketData);
            for (int paraIndex2 = 0; paraIndex2 < this.paraCount; paraIndex2++) {
                if (paraIndex2 == 0) {
                    start = 0;
                } else {
                    start = this.paras_limit[paraIndex2 - 1];
                }
                int limit2 = this.paras_limit[paraIndex2];
                byte level3 = this.paras_level[paraIndex2];
                for (int i3 = start; i3 < limit2; i3++) {
                    this.levels[i3] = level3;
                    byte dirProp = this.dirProps[i3];
                    if (dirProp != 18) {
                        if (dirProp == 7) {
                            if (i3 + 1 < this.length) {
                                char[] cArr = this.text;
                                if (cArr[i3] != '\r' || cArr[i3 + 1] != '\n') {
                                    bracketProcessB(bracketData, level3);
                                }
                            }
                        } else {
                            bracketProcessChar(bracketData, i3);
                        }
                    }
                }
            }
            return dirct;
        }
        byte embeddingLevel = level;
        byte previousLevel = level;
        int lastCcPos = 0;
        short[] stack = new short[127];
        int stackLast = 0;
        int overflowIsolateCount = 0;
        int overflowEmbeddingCount = 0;
        int validIsolateCount = 0;
        BracketData bracketData2 = new BracketData();
        bracketInit(bracketData2);
        stack[0] = level;
        this.flags = 0;
        int i4 = 0;
        while (i4 < this.length) {
            byte dirProp2 = this.dirProps[i4];
            switch (dirProp2) {
                case 7:
                    b = newLevel3;
                    this.flags |= DirPropFlag(f126B);
                    this.levels[i4] = GetParaLevelAt(i4);
                    if (i4 + 1 < this.length) {
                        char[] cArr2 = this.text;
                        if (cArr2[i4] != '\r' || cArr2[i4 + 1] != b2) {
                            stackLast = 0;
                            byte previousLevel2 = GetParaLevelAt(i4 + 1);
                            embeddingLevel = previousLevel2;
                            stack[0] = embeddingLevel;
                            bracketProcessB(bracketData2, embeddingLevel);
                            validIsolateCount = 0;
                            previousLevel = previousLevel2;
                            overflowEmbeddingCount = 0;
                            overflowIsolateCount = 0;
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                    break;
                case 8:
                case 9:
                case 10:
                case 13:
                case 17:
                case 19:
                default:
                    b = newLevel3;
                    if (NoOverride(embeddingLevel) != NoOverride(previousLevel)) {
                        bracketProcessBoundary(bracketData2, lastCcPos, previousLevel, embeddingLevel);
                        int i5 = this.flags | DirPropFlagMultiRuns;
                        this.flags = i5;
                        if ((embeddingLevel & Byte.MIN_VALUE) != 0) {
                            this.flags = i5 | DirPropFlagO(embeddingLevel);
                        } else {
                            this.flags = i5 | DirPropFlagE(embeddingLevel);
                        }
                    }
                    byte previousLevel3 = embeddingLevel;
                    this.levels[i4] = embeddingLevel;
                    bracketProcessChar(bracketData2, i4);
                    this.flags |= DirPropFlag(this.dirProps[i4]);
                    previousLevel = previousLevel3;
                    break;
                case 11:
                case 12:
                case 14:
                case 15:
                    this.flags |= DirPropFlag(f127BN);
                    this.levels[i4] = previousLevel;
                    if (dirProp2 == 11) {
                        b = 1;
                    } else if (dirProp2 != 12) {
                        b = 1;
                        newLevel = (byte) ((NoOverride(embeddingLevel) + 1) | 1);
                        if (newLevel > 125 && overflowIsolateCount == 0 && overflowEmbeddingCount == 0) {
                            int lastCcPos2 = i4;
                            embeddingLevel = newLevel;
                            if (dirProp2 == 12 || dirProp2 == 15) {
                                embeddingLevel = (byte) (embeddingLevel | Byte.MIN_VALUE);
                            }
                            stackLast++;
                            stack[stackLast] = embeddingLevel;
                            lastCcPos = lastCcPos2;
                            break;
                        } else if (overflowIsolateCount != 0) {
                            overflowEmbeddingCount++;
                            break;
                        } else {
                            break;
                        }
                    } else {
                        b = 1;
                    }
                    newLevel = (byte) ((embeddingLevel + 2) & 126);
                    if (newLevel > 125) {
                    }
                    if (overflowIsolateCount != 0) {
                    }
                    break;
                case 16:
                    this.flags |= DirPropFlag(f127BN);
                    this.levels[i4] = previousLevel;
                    if (overflowIsolateCount > 0) {
                        b = 1;
                        break;
                    } else if (overflowEmbeddingCount <= 0) {
                        if (stackLast <= 0 || stack[stackLast] >= 256) {
                            b = 1;
                            break;
                        } else {
                            int lastCcPos3 = i4;
                            stackLast--;
                            embeddingLevel = (byte) stack[stackLast];
                            lastCcPos = lastCcPos3;
                            b = 1;
                            break;
                        }
                    } else {
                        overflowEmbeddingCount--;
                        b = 1;
                        break;
                    }
                    break;
                case 18:
                    this.levels[i4] = previousLevel;
                    this.flags |= DirPropFlag(f127BN);
                    b = 1;
                    break;
                case 20:
                case 21:
                    this.flags |= DirPropFlag(b2) | DirPropFlagLR(embeddingLevel);
                    this.levels[i4] = NoOverride(embeddingLevel);
                    if (NoOverride(embeddingLevel) != NoOverride(previousLevel)) {
                        bracketProcessBoundary(bracketData2, lastCcPos, previousLevel, embeddingLevel);
                        this.flags |= DirPropFlagMultiRuns;
                    }
                    byte previousLevel4 = embeddingLevel;
                    if (dirProp2 == 20) {
                        newLevel2 = (byte) ((embeddingLevel + 2) & 126);
                    } else {
                        byte newLevel4 = NoOverride(embeddingLevel);
                        newLevel2 = (byte) ((newLevel4 + 1) | 1);
                    }
                    if (newLevel2 <= 125 && overflowIsolateCount == 0 && overflowEmbeddingCount == 0) {
                        this.flags |= DirPropFlag(dirProp2);
                        int lastCcPos4 = i4;
                        int validIsolateCount2 = validIsolateCount + 1;
                        if (validIsolateCount2 > this.isolateCount) {
                            this.isolateCount = validIsolateCount2;
                        }
                        embeddingLevel = newLevel2;
                        stackLast++;
                        stack[stackLast] = (short) (embeddingLevel + 256);
                        bracketProcessLRI_RLI(bracketData2, embeddingLevel);
                        lastCcPos = lastCcPos4;
                        validIsolateCount = validIsolateCount2;
                        b = 1;
                        previousLevel = previousLevel4;
                        break;
                    } else {
                        this.dirProps[i4] = f138WS;
                        overflowIsolateCount++;
                        previousLevel = previousLevel4;
                        b = 1;
                        break;
                    }
                case 22:
                    if (NoOverride(embeddingLevel) != NoOverride(previousLevel)) {
                        bracketProcessBoundary(bracketData2, lastCcPos, previousLevel, embeddingLevel);
                        this.flags |= DirPropFlagMultiRuns;
                    }
                    if (overflowIsolateCount > 0) {
                        overflowIsolateCount--;
                        this.dirProps[i4] = f138WS;
                    } else if (validIsolateCount > 0) {
                        this.flags |= DirPropFlag(PDI);
                        lastCcPos = i4;
                        overflowEmbeddingCount = 0;
                        while (stack[stackLast] < 256) {
                            stackLast--;
                        }
                        stackLast--;
                        validIsolateCount--;
                        bracketProcessPDI(bracketData2);
                    } else {
                        this.dirProps[i4] = f138WS;
                    }
                    byte embeddingLevel2 = (byte) (stack[stackLast] & (-257));
                    this.flags |= DirPropFlag(b2) | DirPropFlagLR(embeddingLevel2);
                    embeddingLevel = embeddingLevel2;
                    this.levels[i4] = NoOverride(embeddingLevel2);
                    previousLevel = embeddingLevel;
                    b = 1;
                    break;
            }
            i4++;
            newLevel3 = b;
            b2 = f135ON;
        }
        int i6 = this.flags;
        if ((MASK_EMBEDDING & i6) != 0) {
            this.flags = i6 | DirPropFlagLR(this.paraLevel);
        }
        if (this.orderParagraphsLTR && (this.flags & DirPropFlag(f126B)) != 0) {
            this.flags |= DirPropFlag((byte) 0);
        }
        return directionFromFlags();
    }

    private byte checkExplicitLevels() {
        int isolateCount = 0;
        this.flags = 0;
        this.isolateCount = 0;
        int currentParaIndex = 0;
        int currentParaLimit = this.paras_limit[0];
        byte currentParaLevel = this.paraLevel;
        for (int i = 0; i < this.length; i++) {
            byte[] bArr = this.levels;
            byte level = bArr[i];
            byte dirProp = this.dirProps[i];
            if (dirProp == 20 || dirProp == 21) {
                isolateCount++;
                if (isolateCount > this.isolateCount) {
                    this.isolateCount = isolateCount;
                }
            } else if (dirProp == 22) {
                isolateCount--;
            } else if (dirProp == 7) {
                isolateCount = 0;
            }
            if (this.defaultParaLevel != 0 && i == currentParaLimit && currentParaIndex + 1 < this.paraCount) {
                currentParaIndex++;
                currentParaLevel = this.paras_level[currentParaIndex];
                currentParaLimit = this.paras_limit[currentParaIndex];
            }
            int overrideFlag = level & Byte.MIN_VALUE;
            byte level2 = (byte) (level & Byte.MAX_VALUE);
            if (level2 < currentParaLevel || 125 < level2) {
                if (level2 == 0) {
                    if (dirProp != 7) {
                        level2 = currentParaLevel;
                        bArr[i] = (byte) (level2 | overrideFlag);
                    }
                } else {
                    throw new IllegalArgumentException("level " + ((int) level2) + " out of bounds at " + i);
                }
            }
            if (overrideFlag != 0) {
                this.flags |= DirPropFlagO(level2);
            } else {
                this.flags |= DirPropFlagE(level2) | DirPropFlag(dirProp);
            }
        }
        int i2 = this.flags;
        if ((MASK_EMBEDDING & i2) != 0) {
            this.flags = i2 | DirPropFlagLR(this.paraLevel);
        }
        return directionFromFlags();
    }

    private static short GetStateProps(short cell) {
        return (short) (cell & 31);
    }

    private static short GetActionProps(short cell) {
        return (short) (cell >> 5);
    }

    private static short GetState(byte cell) {
        return (short) (cell & RLO);
    }

    private static short GetAction(byte cell) {
        return (short) (cell >> f132ET);
    }

    /* loaded from: classes.dex */
    private static class ImpTabPair {
        short[][] impact;
        byte[][][] imptab;

        ImpTabPair(byte[][] table1, byte[][] table2, short[] act1, short[] act2) {
            this.imptab = new byte[][][]{table1, table2};
            this.impact = new short[][]{act1, act2};
        }
    }

    /* loaded from: classes.dex */
    private static class LevState {
        short[] impAct;
        byte[][] impTab;
        int lastStrongRTL;
        byte runLevel;
        int runStart;
        int startL2EN;
        int startON;
        short state;

        private LevState() {
        }
    }

    private void addPoint(int pos, int flag) {
        Point point = new Point();
        int len = this.insertPoints.points.length;
        if (len == 0) {
            this.insertPoints.points = new Point[10];
            len = 10;
        }
        if (this.insertPoints.size >= len) {
            Point[] savePoints = this.insertPoints.points;
            this.insertPoints.points = new Point[len * 2];
            System.arraycopy(savePoints, 0, this.insertPoints.points, 0, len);
        }
        point.pos = pos;
        point.flag = flag;
        this.insertPoints.points[this.insertPoints.size] = point;
        this.insertPoints.size++;
    }

    private void setLevelsOutsideIsolates(int start, int limit, byte level) {
        int isolateCount = 0;
        for (int k = start; k < limit; k++) {
            byte dirProp = this.dirProps[k];
            if (dirProp == 22) {
                isolateCount--;
            }
            if (isolateCount == 0) {
                this.levels[k] = level;
            }
            if (dirProp == 20 || dirProp == 21) {
                isolateCount++;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void processPropertySeq(LevState levState, short _prop, int start, int limit) {
        int start2;
        int start3;
        byte[] bArr;
        byte[][] impTab = levState.impTab;
        short[] impAct = levState.impAct;
        short oldStateSeq = levState.state;
        byte cell = impTab[oldStateSeq][_prop];
        levState.state = GetState(cell);
        short actionSeq = impAct[GetAction(cell)];
        byte addLevel = impTab[levState.state][7];
        if (actionSeq != 0) {
            switch (actionSeq) {
                case 1:
                    levState.startON = start;
                    break;
                case 2:
                    start2 = levState.startON;
                    break;
                case 3:
                    setLevelsOutsideIsolates(levState.startON, start, (byte) (levState.runLevel + 1));
                    break;
                case 4:
                    setLevelsOutsideIsolates(levState.startON, start, (byte) (levState.runLevel + 2));
                    break;
                case 5:
                    if (levState.startL2EN >= 0) {
                        addPoint(levState.startL2EN, 1);
                    }
                    levState.startL2EN = -1;
                    if (this.insertPoints.points.length == 0 || this.insertPoints.size <= this.insertPoints.confirmed) {
                        levState.lastStrongRTL = -1;
                        if ((impTab[oldStateSeq][7] & 1) != 0 && levState.startON > 0) {
                            start3 = levState.startON;
                        } else {
                            start3 = start;
                        }
                        if (_prop == 5) {
                            addPoint(start, 1);
                            InsertPoints insertPoints = this.insertPoints;
                            insertPoints.confirmed = insertPoints.size;
                        }
                        start2 = start3;
                        break;
                    } else {
                        for (int k = levState.lastStrongRTL + 1; k < start; k++) {
                            this.levels[k] = (byte) ((bArr[k] - 2) & (-2));
                        }
                        InsertPoints insertPoints2 = this.insertPoints;
                        insertPoints2.confirmed = insertPoints2.size;
                        levState.lastStrongRTL = -1;
                        if (_prop == 5) {
                            addPoint(start, 1);
                            InsertPoints insertPoints3 = this.insertPoints;
                            insertPoints3.confirmed = insertPoints3.size;
                            break;
                        }
                    }
                    break;
                case 6:
                    if (this.insertPoints.points.length > 0) {
                        InsertPoints insertPoints4 = this.insertPoints;
                        insertPoints4.size = insertPoints4.confirmed;
                    }
                    levState.startON = -1;
                    levState.startL2EN = -1;
                    levState.lastStrongRTL = limit - 1;
                    break;
                case 7:
                    if (_prop == 3 && this.dirProps[start] == 5 && this.reorderingMode != 6) {
                        if (levState.startL2EN == -1) {
                            levState.lastStrongRTL = limit - 1;
                            break;
                        } else {
                            if (levState.startL2EN >= 0) {
                                addPoint(levState.startL2EN, 1);
                                levState.startL2EN = -2;
                            }
                            addPoint(start, 1);
                            break;
                        }
                    } else if (levState.startL2EN == -1) {
                        levState.startL2EN = start;
                        break;
                    }
                    break;
                case 8:
                    levState.lastStrongRTL = limit - 1;
                    levState.startON = -1;
                    break;
                case 9:
                    int k2 = start - 1;
                    while (k2 >= 0 && (this.levels[k2] & 1) == 0) {
                        k2--;
                    }
                    if (k2 >= 0) {
                        addPoint(k2, 4);
                        InsertPoints insertPoints5 = this.insertPoints;
                        insertPoints5.confirmed = insertPoints5.size;
                    }
                    levState.startON = start;
                    break;
                case 10:
                    addPoint(start, 1);
                    addPoint(start, 2);
                    break;
                case 11:
                    InsertPoints insertPoints6 = this.insertPoints;
                    insertPoints6.size = insertPoints6.confirmed;
                    if (_prop == 5) {
                        addPoint(start, 4);
                        InsertPoints insertPoints7 = this.insertPoints;
                        insertPoints7.confirmed = insertPoints7.size;
                        break;
                    }
                    break;
                case 12:
                    byte level = (byte) (levState.runLevel + addLevel);
                    for (int k3 = levState.startON; k3 < start; k3++) {
                        byte[] bArr2 = this.levels;
                        if (bArr2[k3] < level) {
                            bArr2[k3] = level;
                        }
                    }
                    InsertPoints insertPoints8 = this.insertPoints;
                    insertPoints8.confirmed = insertPoints8.size;
                    levState.startON = start;
                    break;
                case 13:
                    byte level2 = levState.runLevel;
                    int k4 = start - 1;
                    while (k4 >= levState.startON) {
                        if (this.levels[k4] == level2 + 3) {
                            while (true) {
                                byte[] bArr3 = this.levels;
                                if (bArr3[k4] == level2 + 3) {
                                    bArr3[k4] = (byte) (bArr3[k4] - 2);
                                    k4--;
                                } else {
                                    while (this.levels[k4] == level2) {
                                        k4--;
                                    }
                                }
                            }
                        }
                        byte[] bArr4 = this.levels;
                        if (bArr4[k4] == level2 + 2) {
                            bArr4[k4] = level2;
                        } else {
                            bArr4[k4] = (byte) (level2 + 1);
                        }
                        k4--;
                    }
                    break;
                case 14:
                    byte level3 = (byte) (levState.runLevel + 1);
                    for (int k5 = start - 1; k5 >= levState.startON; k5--) {
                        byte[] bArr5 = this.levels;
                        if (bArr5[k5] > level3) {
                            bArr5[k5] = (byte) (bArr5[k5] - 2);
                        }
                    }
                    break;
                default:
                    throw new IllegalStateException("Internal ICU error in processPropertySeq");
            }
            if (addLevel == 0 || start2 < start) {
                byte level4 = (byte) (levState.runLevel + addLevel);
                if (start2 < levState.runStart) {
                    for (int k6 = start2; k6 < limit; k6++) {
                        this.levels[k6] = level4;
                    }
                    return;
                }
                setLevelsOutsideIsolates(start2, limit, level4);
                return;
            }
            return;
        }
        start2 = start;
        if (addLevel == 0) {
        }
        byte level42 = (byte) (levState.runLevel + addLevel);
        if (start2 < levState.runStart) {
        }
    }

    private byte lastL_R_AL() {
        int i = this.prologue.length();
        while (i > 0) {
            int uchar = this.prologue.codePointBefore(i);
            i -= Character.charCount(uchar);
            byte dirProp = (byte) getCustomizedClass(uchar);
            if (dirProp == 0) {
                return (byte) 0;
            }
            if (dirProp == 1 || dirProp == 13) {
                return (byte) 1;
            }
            if (dirProp == 7) {
                return f132ET;
            }
        }
        return f132ET;
    }

    private byte firstL_R_AL_EN_AN() {
        int i = 0;
        while (i < this.epilogue.length()) {
            int uchar = this.epilogue.codePointAt(i);
            i += Character.charCount(uchar);
            byte dirProp = (byte) getCustomizedClass(uchar);
            if (dirProp == 0) {
                return (byte) 0;
            }
            if (dirProp == 1 || dirProp == 13) {
                return (byte) 1;
            }
            if (dirProp == 2) {
                return (byte) 2;
            }
            if (dirProp == 5) {
                return (byte) 3;
            }
        }
        return f132ET;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void resolveImplicitLevels(int start, int limit, short sor, short eor) {
        short stateImp;
        int start1;
        short stateImp2;
        int i;
        byte dirProp;
        int i2;
        Isolate[] isolateArr;
        byte firstStrong;
        int k;
        short nextStrongProp;
        byte lastStrong;
        int i3;
        LevState levState = new LevState();
        short nextStrongProp2 = 1;
        int nextStrongPos = -1;
        boolean inverseRTL = start < this.lastArabicPos && (GetParaLevelAt(start) & 1) > 0 && ((i3 = this.reorderingMode) == 5 || i3 == 6);
        levState.startL2EN = -1;
        levState.lastStrongRTL = -1;
        levState.runStart = start;
        levState.runLevel = this.levels[start];
        levState.impTab = this.impTabPair.imptab[levState.runLevel & 1];
        levState.impAct = this.impTabPair.impact[levState.runLevel & 1];
        short sor2 = (start == 0 && this.prologue != null && (lastStrong = lastL_R_AL()) != 4) ? lastStrong : sor;
        if (this.dirProps[start] == 22) {
            levState.startON = this.isolates[this.isolateCount].startON;
            start1 = this.isolates[this.isolateCount].start1;
            stateImp2 = this.isolates[this.isolateCount].stateImp;
            levState.state = this.isolates[this.isolateCount].state;
            this.isolateCount--;
        } else {
            levState.startON = -1;
            if (this.dirProps[start] == 17) {
                stateImp = (short) (sor2 + 1);
            } else {
                stateImp = 0;
            }
            levState.state = (short) 0;
            processPropertySeq(levState, sor2, start, start);
            start1 = start;
            stateImp2 = stateImp;
        }
        int start2 = start;
        int i4 = start;
        while (i4 <= limit) {
            if (i4 >= limit) {
                int k2 = limit - 1;
                while (k2 > start && (DirPropFlag(this.dirProps[k2]) & MASK_BN_EXPLICIT) != 0) {
                    k2--;
                }
                byte dirProp2 = this.dirProps[k2];
                if (dirProp2 != 20 && dirProp2 != 21) {
                    k = eor;
                }
                short eor2 = (limit != this.length && this.epilogue != null && (firstStrong = firstL_R_AL_EN_AN()) != 4) ? firstStrong : eor;
                i = limit - 1;
                while (i > start && (DirPropFlag(this.dirProps[i]) & MASK_BN_EXPLICIT) != 0) {
                    i--;
                }
                dirProp = this.dirProps[i];
                if ((dirProp != 20 || dirProp == 21) && limit < this.length) {
                    i2 = this.isolateCount + 1;
                    this.isolateCount = i2;
                    isolateArr = this.isolates;
                    if (isolateArr[i2] == null) {
                        isolateArr[i2] = new Isolate();
                    }
                    this.isolates[this.isolateCount].stateImp = stateImp2;
                    this.isolates[this.isolateCount].state = levState.state;
                    this.isolates[this.isolateCount].start1 = start1;
                    this.isolates[this.isolateCount].startON = levState.startON;
                }
                processPropertySeq(levState, eor2, limit, limit);
                return;
            }
            byte prop = this.dirProps[i4];
            if (prop == 7) {
                this.isolateCount = -1;
            }
            if (inverseRTL) {
                if (prop == 13) {
                    prop = 1;
                } else if (prop == 2) {
                    if (nextStrongPos <= i4) {
                        nextStrongProp2 = 1;
                        nextStrongPos = limit;
                        for (int j = i4 + 1; j < limit; j++) {
                            byte prop1 = this.dirProps[j];
                            if (prop1 == 0 || prop1 == 1 || prop1 == 13) {
                                nextStrongProp2 = prop1;
                                nextStrongPos = j;
                            }
                        }
                    }
                    if (nextStrongProp2 == 13) {
                        prop = f125AN;
                    }
                }
            }
            k = groupProp[prop];
            short oldStateImp = stateImp2;
            short[][] sArr = impTabProps;
            short cell = sArr[oldStateImp][k];
            stateImp2 = GetStateProps(cell);
            short actionImp = GetActionProps(cell);
            if (i4 == limit && actionImp == 0) {
                actionImp = 1;
            }
            if (actionImp == 0) {
                nextStrongProp = nextStrongProp2;
            } else {
                short resProp = sArr[oldStateImp][15];
                switch (actionImp) {
                    case 1:
                        nextStrongProp = nextStrongProp2;
                        processPropertySeq(levState, resProp, start1, i4);
                        int start12 = i4;
                        start1 = start12;
                        continue;
                    case 2:
                        nextStrongProp = nextStrongProp2;
                        int start22 = i4;
                        start2 = start22;
                        continue;
                    case 3:
                        processPropertySeq(levState, resProp, start1, start2);
                        nextStrongProp = nextStrongProp2;
                        processPropertySeq(levState, (short) 4, start2, i4);
                        int start13 = i4;
                        start1 = start13;
                        continue;
                    case 4:
                        processPropertySeq(levState, resProp, start1, start2);
                        start1 = start2;
                        start2 = i4;
                        nextStrongProp = nextStrongProp2;
                        continue;
                    default:
                        throw new IllegalStateException("Internal ICU error in resolveImplicitLevels");
                }
            }
            i4++;
            nextStrongProp2 = nextStrongProp;
            nextStrongPos = nextStrongPos;
        }
        if (limit != this.length) {
        }
        i = limit - 1;
        while (i > start) {
            i--;
        }
        dirProp = this.dirProps[i];
        if (dirProp != 20) {
        }
        i2 = this.isolateCount + 1;
        this.isolateCount = i2;
        isolateArr = this.isolates;
        if (isolateArr[i2] == null) {
        }
        this.isolates[this.isolateCount].stateImp = stateImp2;
        this.isolates[this.isolateCount].state = levState.state;
        this.isolates[this.isolateCount].start1 = start1;
        this.isolates[this.isolateCount].startON = levState.startON;
    }

    private void adjustWSLevels() {
        if ((this.flags & MASK_WS) != 0) {
            int i = this.trailingWSStart;
            while (i > 0) {
                while (i > 0) {
                    i--;
                    int flag = DirPropFlag(this.dirProps[i]);
                    if ((flag & MASK_WS) == 0) {
                        break;
                    } else if (this.orderParagraphsLTR && (DirPropFlag(f126B) & flag) != 0) {
                        this.levels[i] = 0;
                    } else {
                        this.levels[i] = GetParaLevelAt(i);
                    }
                }
                while (true) {
                    if (i > 0) {
                        i--;
                        int flag2 = DirPropFlag(this.dirProps[i]);
                        if ((MASK_BN_EXPLICIT & flag2) != 0) {
                            byte[] bArr = this.levels;
                            bArr[i] = bArr[i + 1];
                        } else if (this.orderParagraphsLTR && (DirPropFlag(f126B) & flag2) != 0) {
                            this.levels[i] = 0;
                            break;
                        } else if ((MASK_B_S & flag2) != 0) {
                            this.levels[i] = GetParaLevelAt(i);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void setContext(String prologue, String epilogue) {
        String str = null;
        this.prologue = (prologue == null || prologue.length() <= 0) ? null : prologue;
        if (epilogue != null && epilogue.length() > 0) {
            str = epilogue;
        }
        this.epilogue = str;
    }

    private void setParaSuccess() {
        this.prologue = null;
        this.epilogue = null;
        this.paraBidi = this;
    }

    int Bidi_Min(int x, int y) {
        return x < y ? x : y;
    }

    int Bidi_Abs(int x) {
        return x >= 0 ? x : -x;
    }

    void setParaRunsOnly(char[] parmText, byte parmParaLevel) {
        int addedRuns;
        int start;
        int limit;
        int step;
        int saveTrailingWSStart;
        byte saveDirection;
        int oldRunCount;
        byte saveDirection2;
        byte parmParaLevel2;
        int saveOptions;
        this.reorderingMode = 0;
        int parmLength = parmText.length;
        if (parmLength == 0) {
            setPara(parmText, parmParaLevel, (byte[]) null);
            this.reorderingMode = 3;
            return;
        }
        int saveOptions2 = this.reorderingOptions;
        int i = 2;
        if ((saveOptions2 & 1) > 0) {
            int i2 = this.reorderingOptions & (-2);
            this.reorderingOptions = i2;
            this.reorderingOptions = i2 | 2;
        }
        byte parmParaLevel3 = (byte) (parmParaLevel & 1);
        setPara(parmText, parmParaLevel3, (byte[]) null);
        byte[] saveLevels = new byte[this.length];
        System.arraycopy(getLevels(), 0, saveLevels, 0, this.length);
        int saveTrailingWSStart2 = this.trailingWSStart;
        String visualText = writeReordered(2);
        int[] visualMap = getVisualMap();
        this.reorderingOptions = saveOptions2;
        int saveLength = this.length;
        byte saveDirection3 = this.direction;
        this.reorderingMode = 5;
        byte parmParaLevel4 = (byte) (parmParaLevel3 ^ 1);
        setPara(visualText, parmParaLevel4, (byte[]) null);
        BidiLine.getRuns(this);
        int addedRuns2 = 0;
        int oldRunCount2 = this.runCount;
        int visualStart = 0;
        int i3 = 0;
        while (i3 < oldRunCount2) {
            int runLength = this.runs[i3].limit - visualStart;
            if (runLength < i) {
                parmParaLevel2 = parmParaLevel4;
                saveOptions = saveOptions2;
            } else {
                int logicalStart = this.runs[i3].start;
                parmParaLevel2 = parmParaLevel4;
                int j = logicalStart + 1;
                while (true) {
                    saveOptions = saveOptions2;
                    if (j < logicalStart + runLength) {
                        int index = visualMap[j];
                        int index1 = visualMap[j - 1];
                        int logicalStart2 = logicalStart;
                        String visualText2 = visualText;
                        if (Bidi_Abs(index - index1) != 1 || saveLevels[index] != saveLevels[index1]) {
                            addedRuns2++;
                        }
                        j++;
                        saveOptions2 = saveOptions;
                        logicalStart = logicalStart2;
                        visualText = visualText2;
                    }
                }
            }
            i3++;
            visualStart += runLength;
            parmParaLevel4 = parmParaLevel2;
            saveOptions2 = saveOptions;
            visualText = visualText;
            i = 2;
        }
        if (addedRuns2 > 0) {
            getRunsMemory(oldRunCount2 + addedRuns2);
            int i4 = this.runCount;
            if (i4 == 1) {
                this.runsMemory[0] = this.runs[0];
            } else {
                System.arraycopy(this.runs, 0, this.runsMemory, 0, i4);
            }
            this.runs = this.runsMemory;
            this.runCount += addedRuns2;
            for (int i5 = oldRunCount2; i5 < this.runCount; i5++) {
                BidiRun[] bidiRunArr = this.runs;
                if (bidiRunArr[i5] == null) {
                    bidiRunArr[i5] = new BidiRun(0, 0, (byte) 0);
                }
            }
        }
        int i6 = oldRunCount2 - 1;
        while (i6 >= 0) {
            int newI = i6 + addedRuns2;
            int runLength2 = i6 == 0 ? this.runs[0].limit : this.runs[i6].limit - this.runs[i6 - 1].limit;
            int logicalStart3 = this.runs[i6].start;
            int indexOddBit = this.runs[i6].level & 1;
            if (runLength2 < 2) {
                if (addedRuns2 <= 0) {
                    addedRuns = addedRuns2;
                } else {
                    BidiRun[] bidiRunArr2 = this.runs;
                    addedRuns = addedRuns2;
                    bidiRunArr2[newI].copyFrom(bidiRunArr2[i6]);
                }
                int addedRuns3 = visualMap[logicalStart3];
                this.runs[newI].start = addedRuns3;
                this.runs[newI].level = (byte) (saveLevels[addedRuns3] ^ indexOddBit);
                saveTrailingWSStart = saveTrailingWSStart2;
                saveDirection = saveDirection3;
                oldRunCount = oldRunCount2;
            } else {
                addedRuns = addedRuns2;
                if (indexOddBit > 0) {
                    start = logicalStart3;
                    limit = (logicalStart3 + runLength2) - 1;
                    step = 1;
                } else {
                    start = (logicalStart3 + runLength2) - 1;
                    limit = logicalStart3;
                    step = -1;
                }
                int start2 = start;
                int start3 = newI;
                int runLength3 = start2;
                while (runLength3 != limit) {
                    int logicalStart4 = logicalStart3;
                    int logicalStart5 = visualMap[runLength3];
                    int index12 = visualMap[runLength3 + step];
                    int oldRunCount3 = oldRunCount2;
                    int saveTrailingWSStart3 = saveTrailingWSStart2;
                    if (Bidi_Abs(logicalStart5 - index12) == 1 && saveLevels[logicalStart5] == saveLevels[index12]) {
                        saveDirection2 = saveDirection3;
                    } else {
                        int logicalPos = Bidi_Min(visualMap[start2], logicalStart5);
                        this.runs[start3].start = logicalPos;
                        this.runs[start3].level = (byte) (saveLevels[logicalPos] ^ indexOddBit);
                        BidiRun[] bidiRunArr3 = this.runs;
                        bidiRunArr3[start3].limit = bidiRunArr3[i6].limit;
                        this.runs[i6].limit -= Bidi_Abs(runLength3 - start2) + 1;
                        int insertRemove = this.runs[i6].insertRemove & 10;
                        this.runs[start3].insertRemove = insertRemove;
                        BidiRun bidiRun = this.runs[i6];
                        saveDirection2 = saveDirection3;
                        bidiRun.insertRemove = (~insertRemove) & bidiRun.insertRemove;
                        start2 = runLength3 + step;
                        addedRuns--;
                        start3--;
                    }
                    runLength3 += step;
                    logicalStart3 = logicalStart4;
                    oldRunCount2 = oldRunCount3;
                    saveTrailingWSStart2 = saveTrailingWSStart3;
                    saveDirection3 = saveDirection2;
                }
                saveTrailingWSStart = saveTrailingWSStart2;
                saveDirection = saveDirection3;
                oldRunCount = oldRunCount2;
                if (addedRuns > 0) {
                    BidiRun[] bidiRunArr4 = this.runs;
                    bidiRunArr4[start3].copyFrom(bidiRunArr4[i6]);
                }
                int logicalPos2 = Bidi_Min(visualMap[start2], visualMap[limit]);
                this.runs[start3].start = logicalPos2;
                this.runs[start3].level = (byte) (saveLevels[logicalPos2] ^ indexOddBit);
            }
            i6--;
            addedRuns2 = addedRuns;
            oldRunCount2 = oldRunCount;
            saveTrailingWSStart2 = saveTrailingWSStart;
            saveDirection3 = saveDirection;
        }
        this.paraLevel = (byte) (this.paraLevel ^ 1);
        this.text = parmText;
        this.length = saveLength;
        this.originalLength = parmLength;
        this.direction = saveDirection3;
        this.levels = saveLevels;
        this.trailingWSStart = saveTrailingWSStart2;
        if (this.runCount > 1) {
            this.direction = (byte) 2;
        }
        this.reorderingMode = 3;
    }

    public void setPara(String text, byte paraLevel, byte[] embeddingLevels) {
        if (text == null) {
            setPara(new char[0], paraLevel, embeddingLevels);
        } else {
            setPara(text.toCharArray(), paraLevel, embeddingLevels);
        }
    }

    public void setPara(char[] chars, byte paraLevel, byte[] embeddingLevels) {
        int i;
        short eor;
        short sor;
        int start;
        Isolate[] isolateArr;
        if (paraLevel < 126) {
            verifyRange(paraLevel, 0, 126);
        }
        if (chars == null) {
            chars = new char[0];
        }
        if (this.reorderingMode == 3) {
            setParaRunsOnly(chars, paraLevel);
            return;
        }
        this.paraBidi = null;
        this.text = chars;
        int length = chars.length;
        this.resultLength = length;
        this.originalLength = length;
        this.length = length;
        this.paraLevel = paraLevel;
        this.direction = (byte) (paraLevel & 1);
        this.paraCount = 1;
        this.dirProps = new byte[0];
        this.levels = new byte[0];
        this.runs = new BidiRun[0];
        this.isGoodLogicalToVisualRunsMap = false;
        this.insertPoints.size = 0;
        this.insertPoints.confirmed = 0;
        this.defaultParaLevel = IsDefaultLevel(paraLevel) ? paraLevel : (byte) 0;
        int i2 = this.length;
        if (i2 == 0) {
            if (IsDefaultLevel(paraLevel)) {
                this.paraLevel = (byte) (1 & this.paraLevel);
                this.defaultParaLevel = (byte) 0;
            }
            this.flags = DirPropFlagLR(paraLevel);
            this.runCount = 0;
            this.paraCount = 0;
            setParaSuccess();
            return;
        }
        this.runCount = -1;
        getDirPropsMemory(i2);
        this.dirProps = this.dirPropsMemory;
        getDirProps();
        int i3 = this.length;
        this.trailingWSStart = i3;
        if (embeddingLevels == null) {
            getLevelsMemory(i3);
            this.levels = this.levelsMemory;
            this.direction = resolveExplicitLevels();
        } else {
            this.levels = embeddingLevels;
            this.direction = checkExplicitLevels();
        }
        int i4 = this.isolateCount;
        if (i4 > 0 && ((isolateArr = this.isolates) == null || isolateArr.length < i4)) {
            this.isolates = new Isolate[i4 + 3];
        }
        this.isolateCount = -1;
        switch (this.direction) {
            case 0:
                this.trailingWSStart = 0;
                break;
            case 1:
                this.trailingWSStart = 0;
                break;
            default:
                switch (this.reorderingMode) {
                    case 0:
                        this.impTabPair = impTab_DEFAULT;
                        break;
                    case 1:
                        this.impTabPair = impTab_NUMBERS_SPECIAL;
                        break;
                    case 2:
                        this.impTabPair = impTab_GROUP_NUMBERS_WITH_R;
                        break;
                    case 3:
                        throw new InternalError("Internal ICU error in setPara");
                    case 4:
                        this.impTabPair = impTab_INVERSE_NUMBERS_AS_L;
                        break;
                    case 5:
                        if ((this.reorderingOptions & 1) != 0) {
                            this.impTabPair = impTab_INVERSE_LIKE_DIRECT_WITH_MARKS;
                            break;
                        } else {
                            this.impTabPair = impTab_INVERSE_LIKE_DIRECT;
                            break;
                        }
                    case 6:
                        if ((this.reorderingOptions & 1) != 0) {
                            this.impTabPair = impTab_INVERSE_FOR_NUMBERS_SPECIAL_WITH_MARKS;
                            break;
                        } else {
                            this.impTabPair = impTab_INVERSE_FOR_NUMBERS_SPECIAL;
                            break;
                        }
                }
                if (embeddingLevels == null && this.paraCount <= 1 && (this.flags & DirPropFlagMultiRuns) == 0) {
                    resolveImplicitLevels(0, this.length, GetLRFromLevel(GetParaLevelAt(0)), GetLRFromLevel(GetParaLevelAt(this.length - 1)));
                } else {
                    int limit = 0;
                    byte level = GetParaLevelAt(0);
                    byte nextLevel = this.levels[0];
                    if (level < nextLevel) {
                        eor = GetLRFromLevel(nextLevel);
                    } else {
                        short eor2 = GetLRFromLevel(level);
                        eor = eor2;
                    }
                    do {
                        int start2 = limit;
                        byte level2 = nextLevel;
                        if (start2 > 0 && this.dirProps[start2 - 1] == 7) {
                            sor = GetLRFromLevel(GetParaLevelAt(start2));
                        } else {
                            sor = eor;
                        }
                        while (true) {
                            limit++;
                            if (limit >= this.length || (this.levels[limit] != level2 && (DirPropFlag(this.dirProps[limit]) & MASK_BN_EXPLICIT) == 0)) {
                            }
                        }
                        int i5 = this.length;
                        if (limit < i5) {
                            nextLevel = this.levels[limit];
                        } else {
                            nextLevel = GetParaLevelAt(i5 - 1);
                        }
                        if (NoOverride(level2) < NoOverride(nextLevel)) {
                            eor = GetLRFromLevel(nextLevel);
                        } else {
                            eor = GetLRFromLevel(level2);
                        }
                        if ((level2 & Byte.MIN_VALUE) == 0) {
                            resolveImplicitLevels(start2, limit, sor, eor);
                        } else {
                            do {
                                byte[] bArr = this.levels;
                                start = start2 + 1;
                                bArr[start2] = (byte) (bArr[start2] & Byte.MAX_VALUE);
                                start2 = start;
                            } while (start < limit);
                        }
                    } while (limit < this.length);
                }
                adjustWSLevels();
                break;
        }
        if (this.defaultParaLevel > 0 && (this.reorderingOptions & 1) != 0 && ((i = this.reorderingMode) == 5 || i == 6)) {
            int i6 = 0;
            while (i6 < this.paraCount) {
                int[] iArr = this.paras_limit;
                int last = iArr[i6] - 1;
                if (this.paras_level[i6] != 0) {
                    int start3 = i6 == 0 ? 0 : iArr[i6 - 1];
                    int j = last;
                    while (true) {
                        if (j < start3) {
                            break;
                        }
                        byte dirProp = this.dirProps[j];
                        if (dirProp == 0) {
                            if (j < last) {
                                while (this.dirProps[last] == 7) {
                                    last--;
                                }
                            }
                            addPoint(last, 4);
                        } else if ((DirPropFlag(dirProp) & MASK_R_AL) != 0) {
                            break;
                        } else {
                            j--;
                        }
                    }
                }
                i6++;
            }
        }
        if ((this.reorderingOptions & 2) != 0) {
            this.resultLength -= this.controlCount;
        } else {
            this.resultLength += this.insertPoints.size;
        }
        setParaSuccess();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2 */
    public void setPara(AttributedCharacterIterator paragraph) {
        byte paraLvl;
        byte level;
        Boolean runDirection = (Boolean) paragraph.getAttribute(TextAttribute.RUN_DIRECTION);
        if (runDirection == null) {
            paraLvl = LEVEL_DEFAULT_LTR;
        } else {
            paraLvl = !runDirection.equals(TextAttribute.RUN_DIRECTION_LTR);
        }
        byte[] lvls = null;
        int len = paragraph.getEndIndex() - paragraph.getBeginIndex();
        byte[] embeddingLevels = new byte[len];
        char[] txt = new char[len];
        int i = 0;
        char ch = paragraph.first();
        while (ch != '\uffff') {
            txt[i] = ch;
            Integer embedding = (Integer) paragraph.getAttribute(TextAttribute.BIDI_EMBEDDING);
            if (embedding != null && (level = embedding.byteValue()) != 0) {
                if (level < 0) {
                    lvls = embeddingLevels;
                    embeddingLevels[i] = (byte) ((0 - level) | (-128));
                } else {
                    lvls = embeddingLevels;
                    embeddingLevels[i] = level;
                }
            }
            ch = paragraph.next();
            i++;
        }
        NumericShaper shaper = (NumericShaper) paragraph.getAttribute(TextAttribute.NUMERIC_SHAPING);
        if (shaper != null) {
            shaper.shape(txt, 0, len);
        }
        setPara(txt, paraLvl, lvls);
    }

    public void orderParagraphsLTR(boolean ordarParaLTR) {
        this.orderParagraphsLTR = ordarParaLTR;
    }

    public boolean isOrderParagraphsLTR() {
        return this.orderParagraphsLTR;
    }

    public byte getDirection() {
        verifyValidParaOrLine();
        return this.direction;
    }

    public String getTextAsString() {
        verifyValidParaOrLine();
        return new String(this.text);
    }

    public char[] getText() {
        verifyValidParaOrLine();
        return this.text;
    }

    public int getLength() {
        verifyValidParaOrLine();
        return this.originalLength;
    }

    public int getProcessedLength() {
        verifyValidParaOrLine();
        return this.length;
    }

    public int getResultLength() {
        verifyValidParaOrLine();
        return this.resultLength;
    }

    public byte getParaLevel() {
        verifyValidParaOrLine();
        return this.paraLevel;
    }

    public int countParagraphs() {
        verifyValidParaOrLine();
        return this.paraCount;
    }

    public BidiRun getParagraphByIndex(int paraIndex) {
        int paraStart;
        verifyValidParaOrLine();
        verifyRange(paraIndex, 0, this.paraCount);
        Bidi bidi = this.paraBidi;
        if (paraIndex == 0) {
            paraStart = 0;
        } else {
            paraStart = bidi.paras_limit[paraIndex - 1];
        }
        BidiRun bidiRun = new BidiRun();
        bidiRun.start = paraStart;
        bidiRun.limit = bidi.paras_limit[paraIndex];
        bidiRun.level = GetParaLevelAt(paraStart);
        return bidiRun;
    }

    public BidiRun getParagraph(int charIndex) {
        verifyValidParaOrLine();
        Bidi bidi = this.paraBidi;
        verifyRange(charIndex, 0, bidi.length);
        int paraIndex = 0;
        while (charIndex >= bidi.paras_limit[paraIndex]) {
            paraIndex++;
        }
        return getParagraphByIndex(paraIndex);
    }

    public int getParagraphIndex(int charIndex) {
        verifyValidParaOrLine();
        Bidi bidi = this.paraBidi;
        verifyRange(charIndex, 0, bidi.length);
        int paraIndex = 0;
        while (charIndex >= bidi.paras_limit[paraIndex]) {
            paraIndex++;
        }
        return paraIndex;
    }

    public void setCustomClassifier(BidiClassifier classifier) {
        this.customClassifier = classifier;
    }

    public BidiClassifier getCustomClassifier() {
        return this.customClassifier;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000b, code lost:
        if (r0 == 23) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int getCustomizedClass(int c) {
        int dir;
        BidiClassifier bidiClassifier = this.customClassifier;
        if (bidiClassifier != null) {
            int classify = bidiClassifier.classify(c);
            dir = classify;
        }
        dir = this.bdp.getClass(c);
        if (dir >= 23) {
            return 10;
        }
        return dir;
    }

    public Bidi setLine(int start, int limit) {
        verifyValidPara();
        verifyRange(start, 0, limit);
        verifyRange(limit, 0, this.length + 1);
        if (getParagraphIndex(start) != getParagraphIndex(limit - 1)) {
            throw new IllegalArgumentException();
        }
        return BidiLine.setLine(this, start, limit);
    }

    public byte getLevelAt(int charIndex) {
        verifyValidParaOrLine();
        verifyRange(charIndex, 0, this.length);
        return BidiLine.getLevelAt(this, charIndex);
    }

    public byte[] getLevels() {
        verifyValidParaOrLine();
        if (this.length <= 0) {
            return new byte[0];
        }
        return BidiLine.getLevels(this);
    }

    public BidiRun getLogicalRun(int logicalPosition) {
        verifyValidParaOrLine();
        verifyRange(logicalPosition, 0, this.length);
        return BidiLine.getLogicalRun(this, logicalPosition);
    }

    public int countRuns() {
        verifyValidParaOrLine();
        BidiLine.getRuns(this);
        return this.runCount;
    }

    public BidiRun getVisualRun(int runIndex) {
        verifyValidParaOrLine();
        BidiLine.getRuns(this);
        verifyRange(runIndex, 0, this.runCount);
        return BidiLine.getVisualRun(this, runIndex);
    }

    public int getVisualIndex(int logicalIndex) {
        verifyValidParaOrLine();
        verifyRange(logicalIndex, 0, this.length);
        return BidiLine.getVisualIndex(this, logicalIndex);
    }

    public int getLogicalIndex(int visualIndex) {
        verifyValidParaOrLine();
        verifyRange(visualIndex, 0, this.resultLength);
        if (this.insertPoints.size == 0 && this.controlCount == 0) {
            byte b = this.direction;
            if (b == 0) {
                return visualIndex;
            }
            if (b == 1) {
                return (this.length - visualIndex) - 1;
            }
        }
        BidiLine.getRuns(this);
        return BidiLine.getLogicalIndex(this, visualIndex);
    }

    public int[] getLogicalMap() {
        countRuns();
        if (this.length <= 0) {
            return new int[0];
        }
        return BidiLine.getLogicalMap(this);
    }

    public int[] getVisualMap() {
        countRuns();
        if (this.resultLength <= 0) {
            return new int[0];
        }
        return BidiLine.getVisualMap(this);
    }

    public static int[] reorderLogical(byte[] levels) {
        return BidiLine.reorderLogical(levels);
    }

    public static int[] reorderVisual(byte[] levels) {
        return BidiLine.reorderVisual(levels);
    }

    public static int[] invertMap(int[] srcMap) {
        if (srcMap == null) {
            return null;
        }
        return BidiLine.invertMap(srcMap);
    }

    public Bidi(String paragraph, int flags) {
        this(paragraph.toCharArray(), 0, null, 0, paragraph.length(), flags);
    }

    public Bidi(AttributedCharacterIterator paragraph) {
        this();
        setPara(paragraph);
    }

    public Bidi(char[] text, int textStart, byte[] embeddings, int embStart, int paragraphLength, int flags) {
        this();
        byte paraLvl;
        byte[] paraEmbeddings;
        switch (flags) {
            case 1:
                paraLvl = 1;
                break;
            case 126:
                paraLvl = LEVEL_DEFAULT_LTR;
                break;
            case 127:
                paraLvl = Byte.MAX_VALUE;
                break;
            default:
                paraLvl = 0;
                break;
        }
        if (embeddings == null) {
            paraEmbeddings = null;
        } else {
            paraEmbeddings = new byte[paragraphLength];
            for (int i = 0; i < paragraphLength; i++) {
                byte lev = embeddings[i + embStart];
                if (lev < 0) {
                    lev = (byte) ((-lev) | (-128));
                }
                paraEmbeddings[i] = lev;
            }
        }
        if (textStart == 0 && paragraphLength == text.length) {
            setPara(text, paraLvl, paraEmbeddings);
            return;
        }
        char[] paraText = new char[paragraphLength];
        System.arraycopy(text, textStart, paraText, 0, paragraphLength);
        setPara(paraText, paraLvl, paraEmbeddings);
    }

    public Bidi createLineBidi(int lineStart, int lineLimit) {
        return setLine(lineStart, lineLimit);
    }

    public boolean isMixed() {
        return (isLeftToRight() || isRightToLeft()) ? false : true;
    }

    public boolean isLeftToRight() {
        return getDirection() == 0 && (this.paraLevel & 1) == 0;
    }

    public boolean isRightToLeft() {
        return getDirection() == 1 && (this.paraLevel & 1) == 1;
    }

    public boolean baseIsLeftToRight() {
        return getParaLevel() == 0;
    }

    public int getBaseLevel() {
        return getParaLevel();
    }

    public int getRunCount() {
        return countRuns();
    }

    void getLogicalToVisualRunsMap() {
        if (this.isGoodLogicalToVisualRunsMap) {
            return;
        }
        int count = countRuns();
        int[] iArr = this.logicalToVisualRunsMap;
        if (iArr == null || iArr.length < count) {
            this.logicalToVisualRunsMap = new int[count];
        }
        long[] keys = new long[count];
        for (int i = 0; i < count; i++) {
            keys[i] = (this.runs[i].start << 32) + i;
        }
        Arrays.sort(keys);
        for (int i2 = 0; i2 < count; i2++) {
            this.logicalToVisualRunsMap[i2] = (int) (keys[i2] & (-1));
        }
        this.isGoodLogicalToVisualRunsMap = true;
    }

    public int getRunLevel(int run) {
        verifyValidParaOrLine();
        BidiLine.getRuns(this);
        verifyRange(run, 0, this.runCount);
        getLogicalToVisualRunsMap();
        return this.runs[this.logicalToVisualRunsMap[run]].level;
    }

    public int getRunStart(int run) {
        verifyValidParaOrLine();
        BidiLine.getRuns(this);
        verifyRange(run, 0, this.runCount);
        getLogicalToVisualRunsMap();
        return this.runs[this.logicalToVisualRunsMap[run]].start;
    }

    public int getRunLimit(int run) {
        verifyValidParaOrLine();
        BidiLine.getRuns(this);
        verifyRange(run, 0, this.runCount);
        getLogicalToVisualRunsMap();
        int idx = this.logicalToVisualRunsMap[run];
        BidiRun[] bidiRunArr = this.runs;
        int len = idx == 0 ? bidiRunArr[idx].limit : bidiRunArr[idx].limit - this.runs[idx - 1].limit;
        return this.runs[idx].start + len;
    }

    public static boolean requiresBidi(char[] text, int start, int limit) {
        for (int i = start; i < limit; i++) {
            if (((1 << UCharacter.getDirection(text[i])) & 57378) != 0) {
                return true;
            }
        }
        return false;
    }

    public static void reorderVisually(byte[] levels, int levelStart, Object[] objects, int objectStart, int count) {
        byte[] reorderLevels = new byte[count];
        System.arraycopy(levels, levelStart, reorderLevels, 0, count);
        int[] indexMap = reorderVisual(reorderLevels);
        Object[] temp = new Object[count];
        System.arraycopy(objects, objectStart, temp, 0, count);
        for (int i = 0; i < count; i++) {
            objects[objectStart + i] = temp[indexMap[i]];
        }
    }

    public String writeReordered(int options) {
        verifyValidParaOrLine();
        if (this.length == 0) {
            return "";
        }
        return BidiWriter.writeReordered(this, options);
    }

    public static String writeReverse(String src, int options) {
        if (src == null) {
            throw new IllegalArgumentException();
        }
        if (src.length() > 0) {
            return BidiWriter.writeReverse(src, options);
        }
        return "";
    }
}
