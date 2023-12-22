package com.ibm.icu.text;

import com.ibm.icu.impl.CharTrie;
import com.ibm.icu.impl.ICUBinary;
import com.ibm.icu.impl.StringPrepDataReader;
import com.ibm.icu.impl.Trie;
import com.ibm.icu.impl.UBiDiProps;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.util.ICUUncheckedIOException;
import com.ibm.icu.util.VersionInfo;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class StringPrep {
    public static final int ALLOW_UNASSIGNED = 1;
    private static final int CHECK_BIDI_ON = 2;
    public static final int DEFAULT = 0;
    private static final int DELETE = 3;
    private static final int FOUR_UCHARS_MAPPING_INDEX_START = 6;
    private static final int INDEX_MAPPING_DATA_SIZE = 1;
    private static final int INDEX_TOP = 16;
    private static final int MAP = 1;
    private static final int MAX_INDEX_VALUE = 16319;
    private static final int MAX_PROFILE = 13;
    private static final int NORMALIZATION_ON = 1;
    private static final int NORM_CORRECTNS_LAST_UNI_VERSION = 2;
    private static final int ONE_UCHAR_MAPPING_INDEX_START = 3;
    private static final int OPTIONS = 7;
    private static final int PROHIBITED = 2;
    public static final int RFC3491_NAMEPREP = 0;
    public static final int RFC3530_NFS4_CIS_PREP = 3;
    public static final int RFC3530_NFS4_CS_PREP = 1;
    public static final int RFC3530_NFS4_CS_PREP_CI = 2;
    public static final int RFC3530_NFS4_MIXED_PREP_PREFIX = 4;
    public static final int RFC3530_NFS4_MIXED_PREP_SUFFIX = 5;
    public static final int RFC3722_ISCSI = 6;
    public static final int RFC3920_NODEPREP = 7;
    public static final int RFC3920_RESOURCEPREP = 8;
    public static final int RFC4011_MIB = 9;
    public static final int RFC4013_SASLPREP = 10;
    public static final int RFC4505_TRACE = 11;
    public static final int RFC4518_LDAP = 12;
    public static final int RFC4518_LDAP_CI = 13;
    private static final int THREE_UCHARS_MAPPING_INDEX_START = 5;
    private static final int TWO_UCHARS_MAPPING_INDEX_START = 4;
    private static final int TYPE_LIMIT = 4;
    private static final int TYPE_THRESHOLD = 65520;
    private static final int UNASSIGNED = 0;
    private UBiDiProps bdp;
    private boolean checkBiDi;
    private boolean doNFKC;
    private int[] indexes;
    private char[] mappingData;
    private VersionInfo normCorrVer;
    private CharTrie sprepTrie;
    private VersionInfo sprepUniVer;
    private static final String[] PROFILE_NAMES = {"rfc3491", "rfc3530cs", "rfc3530csci", "rfc3491", "rfc3530mixp", "rfc3491", "rfc3722", "rfc3920node", "rfc3920res", "rfc4011", "rfc4013", "rfc4505", "rfc4518", "rfc4518ci"};
    private static final WeakReference<StringPrep>[] CACHE = new WeakReference[14];

    private char getCodePointValue(int ch) {
        return this.sprepTrie.getCodePointValue(ch);
    }

    private static VersionInfo getVersionInfo(int comp) {
        int micro = comp & 255;
        int milli = (comp >> 8) & 255;
        int minor = (comp >> 16) & 255;
        int major = (comp >> 24) & 255;
        return VersionInfo.getInstance(major, minor, milli, micro);
    }

    private static VersionInfo getVersionInfo(byte[] version) {
        if (version.length != 4) {
            return null;
        }
        return VersionInfo.getInstance(version[0], version[1], version[2], version[3]);
    }

    public StringPrep(InputStream inputStream) throws IOException {
        this(ICUBinary.getByteBufferFromInputStreamAndCloseStream(inputStream));
    }

    private StringPrep(ByteBuffer bytes) throws IOException {
        StringPrepDataReader reader = new StringPrepDataReader(bytes);
        this.indexes = reader.readIndexes(16);
        this.sprepTrie = new CharTrie(bytes, (Trie.DataManipulate) null);
        this.mappingData = reader.read(this.indexes[1] / 2);
        int[] iArr = this.indexes;
        this.doNFKC = (iArr[7] & 1) > 0;
        this.checkBiDi = (iArr[7] & 2) > 0;
        this.sprepUniVer = getVersionInfo(reader.getUnicodeVersion());
        this.normCorrVer = getVersionInfo(this.indexes[2]);
        VersionInfo normUniVer = UCharacter.getUnicodeVersion();
        if (normUniVer.compareTo(this.sprepUniVer) < 0 && normUniVer.compareTo(this.normCorrVer) < 0 && (1 & this.indexes[7]) > 0) {
            throw new IOException("Normalization Correction version not supported");
        }
        if (this.checkBiDi) {
            this.bdp = UBiDiProps.INSTANCE;
        }
    }

    public static StringPrep getInstance(int profile) {
        if (profile < 0 || profile > 13) {
            throw new IllegalArgumentException("Bad profile type");
        }
        StringPrep instance = null;
        WeakReference<StringPrep>[] weakReferenceArr = CACHE;
        synchronized (weakReferenceArr) {
            WeakReference<StringPrep> ref = weakReferenceArr[profile];
            if (ref != null) {
                instance = ref.get();
            }
            if (instance == null) {
                ByteBuffer bytes = ICUBinary.getRequiredData(PROFILE_NAMES[profile] + ".spp");
                if (bytes != null) {
                    try {
                        instance = new StringPrep(bytes);
                    } catch (IOException e) {
                        throw new ICUUncheckedIOException(e);
                    }
                }
                if (instance != null) {
                    weakReferenceArr[profile] = new WeakReference<>(instance);
                }
            }
        }
        return instance;
    }

    /* loaded from: classes.dex */
    private static final class Values {
        boolean isIndex;
        int type;
        int value;

        private Values() {
        }

        public void reset() {
            this.isIndex = false;
            this.value = 0;
            this.type = -1;
        }
    }

    private static final void getValues(char trieWord, Values values) {
        values.reset();
        if (trieWord == 0) {
            values.type = 4;
        } else if (trieWord >= TYPE_THRESHOLD) {
            values.type = trieWord - TYPE_THRESHOLD;
        } else {
            values.type = 1;
            if ((trieWord & 2) > 0) {
                values.isIndex = true;
                values.value = trieWord >> 2;
            } else {
                values.isIndex = false;
                values.value = (trieWord << 16) >> 16;
                values.value >>= 2;
            }
            if ((trieWord >> 2) == MAX_INDEX_VALUE) {
                values.type = 3;
                values.isIndex = false;
                values.value = 0;
            }
        }
    }

    private StringBuffer map(UCharacterIterator iter, int options) throws StringPrepParseException {
        char c;
        Values val = new Values();
        StringBuffer dest = new StringBuffer();
        boolean allowUnassigned = (options & 1) > 0;
        while (true) {
            int nextCodePoint = iter.nextCodePoint();
            int ch = nextCodePoint;
            if (nextCodePoint != -1) {
                char result = getCodePointValue(ch);
                getValues(result, val);
                if (val.type == 0 && !allowUnassigned) {
                    throw new StringPrepParseException("An unassigned code point was found in the input", 3, iter.getText(), iter.getIndex());
                }
                if (val.type == 1) {
                    if (val.isIndex) {
                        int index = val.value;
                        int[] iArr = this.indexes;
                        if (index >= iArr[3] && index < iArr[4]) {
                            c = 1;
                        } else {
                            int length = iArr[4];
                            if (index >= length && index < iArr[5]) {
                                c = 2;
                            } else {
                                int length2 = iArr[5];
                                if (index >= length2 && index < iArr[6]) {
                                    c = 3;
                                } else {
                                    c = this.mappingData[index];
                                    index++;
                                }
                            }
                        }
                        dest.append(this.mappingData, index, c);
                    } else {
                        ch -= val.value;
                        UTF16.append(dest, ch);
                    }
                } else if (val.type != 3) {
                    UTF16.append(dest, ch);
                }
            } else {
                return dest;
            }
        }
    }

    private StringBuffer normalize(StringBuffer src) {
        return new StringBuffer(Normalizer.normalize(src.toString(), Normalizer.NFKC, 32));
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0092, code lost:
        if (r6 == 13) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0099, code lost:
        if (r5 != r11) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x009b, code lost:
        r13 = r3.getText();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a1, code lost:
        if (r7 <= r8) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a3, code lost:
        r14 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a5, code lost:
        r14 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a9, code lost:
        throw new com.ibm.icu.text.StringPrepParseException("The input does not conform to the rules for BiDi code points.", 4, r13, r14);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public StringBuffer prepare(UCharacterIterator src, int options) throws StringPrepParseException {
        StringBuffer mapOut = map(src, options);
        StringBuffer normOut = mapOut;
        if (this.doNFKC) {
            normOut = normalize(mapOut);
        }
        UCharacterIterator iter = UCharacterIterator.getInstance(normOut);
        Values val = new Values();
        int direction = 23;
        int firstCharDir = 23;
        int rtlPos = -1;
        int ltrPos = -1;
        boolean rightToLeft = false;
        boolean leftToRight = false;
        while (true) {
            int ch = iter.nextCodePoint();
            if (ch == -1) {
                if (this.checkBiDi) {
                    if (leftToRight && rightToLeft) {
                        throw new StringPrepParseException("The input does not conform to the rules for BiDi code points.", 4, iter.getText(), rtlPos > ltrPos ? rtlPos : ltrPos);
                    } else if (rightToLeft) {
                        int i = firstCharDir != 1 ? 13 : 13;
                        if (direction != 1) {
                        }
                    }
                }
                return normOut;
            }
            char result = getCodePointValue(ch);
            getValues(result, val);
            if (val.type == 2) {
                throw new StringPrepParseException("A prohibited code point was found in the input", 2, iter.getText(), val.value);
            }
            if (this.checkBiDi) {
                direction = this.bdp.getClass(ch);
                if (firstCharDir == 23) {
                    firstCharDir = direction;
                }
                if (direction == 0) {
                    leftToRight = true;
                    ltrPos = iter.getIndex() - 1;
                }
                if (direction == 1 || direction == 13) {
                    rightToLeft = true;
                    rtlPos = iter.getIndex() - 1;
                }
            }
        }
    }

    public String prepare(String src, int options) throws StringPrepParseException {
        StringBuffer result = prepare(UCharacterIterator.getInstance(src), options);
        return result.toString();
    }
}
