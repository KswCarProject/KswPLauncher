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

public final class StringPrep {
    public static final int ALLOW_UNASSIGNED = 1;
    private static final WeakReference<StringPrep>[] CACHE = ((WeakReference[]) new WeakReference[14]);
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
    private static final String[] PROFILE_NAMES = {"rfc3491", "rfc3530cs", "rfc3530csci", "rfc3491", "rfc3530mixp", "rfc3491", "rfc3722", "rfc3920node", "rfc3920res", "rfc4011", "rfc4013", "rfc4505", "rfc4518", "rfc4518ci"};
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

    private char getCodePointValue(int ch) {
        return this.sprepTrie.getCodePointValue(ch);
    }

    private static VersionInfo getVersionInfo(int comp) {
        return VersionInfo.getInstance((comp >> 24) & 255, (comp >> 16) & 255, (comp >> 8) & 255, comp & 255);
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
        boolean z = false;
        this.doNFKC = (iArr[7] & 1) > 0;
        this.checkBiDi = (iArr[7] & 2) > 0 ? true : z;
        this.sprepUniVer = getVersionInfo(reader.getUnicodeVersion());
        this.normCorrVer = getVersionInfo(this.indexes[2]);
        VersionInfo normUniVer = UCharacter.getUnicodeVersion();
        if (normUniVer.compareTo(this.sprepUniVer) < 0 && normUniVer.compareTo(this.normCorrVer) < 0 && (1 & this.indexes[7]) > 0) {
            throw new IOException("Normalization Correction version not supported");
        } else if (this.checkBiDi) {
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
                instance = (StringPrep) ref.get();
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

    /* JADX WARNING: Incorrect type for immutable var: ssa=char, code=int, for r7v7, types: [char] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.StringBuffer map(com.ibm.icu.text.UCharacterIterator r11, int r12) throws com.ibm.icu.text.StringPrepParseException {
        /*
            r10 = this;
            com.ibm.icu.text.StringPrep$Values r0 = new com.ibm.icu.text.StringPrep$Values
            r1 = 0
            r0.<init>()
            r1 = 0
            r2 = -1
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            r4 = r12 & 1
            r5 = 1
            if (r4 <= 0) goto L_0x0014
            r4 = r5
            goto L_0x0015
        L_0x0014:
            r4 = 0
        L_0x0015:
            int r6 = r11.nextCodePoint()
            r2 = r6
            r7 = -1
            if (r6 == r7) goto L_0x0083
            char r1 = r10.getCodePointValue(r2)
            getValues(r1, r0)
            int r6 = r0.type
            r7 = 3
            if (r6 != 0) goto L_0x003c
            if (r4 == 0) goto L_0x002c
            goto L_0x003c
        L_0x002c:
            com.ibm.icu.text.StringPrepParseException r5 = new com.ibm.icu.text.StringPrepParseException
            java.lang.String r6 = r11.getText()
            int r8 = r11.getIndex()
            java.lang.String r9 = "An unassigned code point was found in the input"
            r5.<init>(r9, r7, r6, r8)
            throw r5
        L_0x003c:
            int r6 = r0.type
            if (r6 != r5) goto L_0x007a
            boolean r6 = r0.isIndex
            if (r6 == 0) goto L_0x0076
            int r6 = r0.value
            int[] r8 = r10.indexes
            r7 = r8[r7]
            r9 = 4
            if (r6 < r7) goto L_0x0053
            r7 = r8[r9]
            if (r6 >= r7) goto L_0x0053
            r7 = 1
            goto L_0x0070
        L_0x0053:
            r7 = r8[r9]
            r9 = 5
            if (r6 < r7) goto L_0x005e
            r7 = r8[r9]
            if (r6 >= r7) goto L_0x005e
            r7 = 2
            goto L_0x0070
        L_0x005e:
            r7 = r8[r9]
            if (r6 < r7) goto L_0x0069
            r7 = 6
            r7 = r8[r7]
            if (r6 >= r7) goto L_0x0069
            r7 = 3
            goto L_0x0070
        L_0x0069:
            char[] r7 = r10.mappingData
            int r8 = r6 + 1
            char r7 = r7[r6]
            r6 = r8
        L_0x0070:
            char[] r8 = r10.mappingData
            r3.append(r8, r6, r7)
            goto L_0x0015
        L_0x0076:
            int r6 = r0.value
            int r2 = r2 - r6
            goto L_0x007f
        L_0x007a:
            int r6 = r0.type
            if (r6 != r7) goto L_0x007f
            goto L_0x0015
        L_0x007f:
            com.ibm.icu.text.UTF16.append(r3, r2)
            goto L_0x0015
        L_0x0083:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.StringPrep.map(com.ibm.icu.text.UCharacterIterator, int):java.lang.StringBuffer");
    }

    private StringBuffer normalize(StringBuffer src) {
        return new StringBuffer(Normalizer.normalize(src.toString(), Normalizer.NFKC, 32));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0092, code lost:
        if (r6 == 13) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0099, code lost:
        if (r5 != r11) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009b, code lost:
        r13 = r3.getText();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a1, code lost:
        if (r7 <= r8) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a3, code lost:
        r14 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a5, code lost:
        r14 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a9, code lost:
        throw new com.ibm.icu.text.StringPrepParseException("The input does not conform to the rules for BiDi code points.", 4, r13, r14);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.StringBuffer prepare(com.ibm.icu.text.UCharacterIterator r19, int r20) throws com.ibm.icu.text.StringPrepParseException {
        /*
            r18 = this;
            r0 = r18
            java.lang.StringBuffer r1 = r18.map(r19, r20)
            r2 = r1
            boolean r3 = r0.doNFKC
            if (r3 == 0) goto L_0x000f
            java.lang.StringBuffer r2 = r0.normalize(r1)
        L_0x000f:
            com.ibm.icu.text.UCharacterIterator r3 = com.ibm.icu.text.UCharacterIterator.getInstance((java.lang.StringBuffer) r2)
            com.ibm.icu.text.StringPrep$Values r4 = new com.ibm.icu.text.StringPrep$Values
            r5 = 0
            r4.<init>()
            r5 = 23
            r6 = 23
            r7 = -1
            r8 = -1
            r9 = 0
            r10 = 0
        L_0x0021:
            int r11 = r3.nextCodePoint()
            r12 = r11
            r13 = -1
            r15 = 1
            if (r11 == r13) goto L_0x006e
            char r11 = r0.getCodePointValue(r12)
            getValues(r11, r4)
            int r13 = r4.type
            r14 = 2
            if (r13 == r14) goto L_0x005c
            boolean r13 = r0.checkBiDi
            if (r13 == 0) goto L_0x0021
            com.ibm.icu.impl.UBiDiProps r13 = r0.bdp
            int r5 = r13.getClass(r12)
            r13 = 23
            if (r6 != r13) goto L_0x0045
            r6 = r5
        L_0x0045:
            if (r5 != 0) goto L_0x004e
            r10 = 1
            int r13 = r3.getIndex()
            int r13 = r13 - r15
            r8 = r13
        L_0x004e:
            if (r5 == r15) goto L_0x0054
            r13 = 13
            if (r5 != r13) goto L_0x0021
        L_0x0054:
            r9 = 1
            int r13 = r3.getIndex()
            int r7 = r13 + -1
            goto L_0x0021
        L_0x005c:
            com.ibm.icu.text.StringPrepParseException r13 = new com.ibm.icu.text.StringPrepParseException
            java.lang.String r15 = r3.getText()
            r16 = r1
            int r1 = r4.value
            r17 = r4
            java.lang.String r4 = "A prohibited code point was found in the input"
            r13.<init>(r4, r14, r15, r1)
            throw r13
        L_0x006e:
            r16 = r1
            r17 = r4
            boolean r1 = r0.checkBiDi
            if (r1 != r15) goto L_0x00aa
            r1 = 4
            java.lang.String r4 = "The input does not conform to the rules for BiDi code points."
            if (r10 != r15) goto L_0x008c
            if (r9 != r15) goto L_0x008c
            com.ibm.icu.text.StringPrepParseException r11 = new com.ibm.icu.text.StringPrepParseException
            java.lang.String r13 = r3.getText()
            if (r7 <= r8) goto L_0x0087
            r14 = r7
            goto L_0x0088
        L_0x0087:
            r14 = r8
        L_0x0088:
            r11.<init>(r4, r1, r13, r14)
            throw r11
        L_0x008c:
            if (r9 != r15) goto L_0x00aa
            if (r6 == r15) goto L_0x0095
            r11 = 13
            if (r6 != r11) goto L_0x009b
            goto L_0x0097
        L_0x0095:
            r11 = 13
        L_0x0097:
            if (r5 == r15) goto L_0x00aa
            if (r5 == r11) goto L_0x00aa
        L_0x009b:
            com.ibm.icu.text.StringPrepParseException r11 = new com.ibm.icu.text.StringPrepParseException
            java.lang.String r13 = r3.getText()
            if (r7 <= r8) goto L_0x00a5
            r14 = r7
            goto L_0x00a6
        L_0x00a5:
            r14 = r8
        L_0x00a6:
            r11.<init>(r4, r1, r13, r14)
            throw r11
        L_0x00aa:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.StringPrep.prepare(com.ibm.icu.text.UCharacterIterator, int):java.lang.StringBuffer");
    }

    public String prepare(String src, int options) throws StringPrepParseException {
        return prepare(UCharacterIterator.getInstance(src), options).toString();
    }
}
