package com.ibm.icu.text;

import com.ibm.icu.impl.Norm2AllModes;
import com.ibm.icu.impl.Normalizer2Impl;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.lang.UCharacter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class CanonicalIterator {
    private static boolean PROGRESS = false;
    private static final Set<String> SET_WITH_NULL_STRING;
    private static boolean SKIP_ZEROS = true;
    private transient StringBuilder buffer = new StringBuilder();
    private int[] current;
    private boolean done;
    private final Normalizer2Impl nfcImpl;
    private final Normalizer2 nfd;
    private String[][] pieces;
    private String source;

    public CanonicalIterator(String source2) {
        Norm2AllModes allModes = Norm2AllModes.getNFCInstance();
        this.nfd = allModes.decomp;
        this.nfcImpl = allModes.impl.ensureCanonIterData();
        setSource(source2);
    }

    public String getSource() {
        return this.source;
    }

    public void reset() {
        this.done = false;
        int i = 0;
        while (true) {
            int[] iArr = this.current;
            if (i < iArr.length) {
                iArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    public String next() {
        if (this.done) {
            return null;
        }
        this.buffer.setLength(0);
        int i = 0;
        while (true) {
            String[][] strArr = this.pieces;
            if (i >= strArr.length) {
                break;
            }
            this.buffer.append(strArr[i][this.current[i]]);
            i++;
        }
        String result = this.buffer.toString();
        int i2 = this.current.length - 1;
        while (true) {
            if (i2 < 0) {
                this.done = true;
                break;
            }
            int[] iArr = this.current;
            iArr[i2] = iArr[i2] + 1;
            if (iArr[i2] < this.pieces[i2].length) {
                break;
            }
            iArr[i2] = 0;
            i2--;
        }
        return result;
    }

    public void setSource(String newSource) {
        this.source = this.nfd.normalize(newSource);
        this.done = false;
        if (newSource.length() == 0) {
            String[][] strArr = new String[1][];
            this.pieces = strArr;
            this.current = new int[1];
            strArr[0] = new String[]{""};
            return;
        }
        List<String> segmentList = new ArrayList<>();
        int start = 0;
        int i = UTF16.findOffsetFromCodePoint(this.source, 1);
        while (i < this.source.length()) {
            int cp = this.source.codePointAt(i);
            if (this.nfcImpl.isCanonSegmentStarter(cp)) {
                segmentList.add(this.source.substring(start, i));
                start = i;
            }
            i += Character.charCount(cp);
        }
        segmentList.add(this.source.substring(start, i));
        this.pieces = new String[segmentList.size()][];
        this.current = new int[segmentList.size()];
        for (int i2 = 0; i2 < this.pieces.length; i2++) {
            if (PROGRESS) {
                System.out.println("SEGMENT");
            }
            this.pieces[i2] = getEquivalents(segmentList.get(i2));
        }
    }

    @Deprecated
    public static void permute(String source2, boolean skipZeros, Set<String> output) {
        if (source2.length() > 2 || UTF16.countCodePoint(source2) > 1) {
            Set<String> subpermute = new HashSet<>();
            int i = 0;
            while (i < source2.length()) {
                int cp = UTF16.charAt(source2, i);
                if (!skipZeros || i == 0 || UCharacter.getCombiningClass(cp) != 0) {
                    subpermute.clear();
                    permute(source2.substring(0, i) + source2.substring(UTF16.getCharCount(cp) + i), skipZeros, subpermute);
                    String chStr = UTF16.valueOf(source2, i);
                    for (String s : subpermute) {
                        output.add(chStr + s);
                    }
                }
                i += UTF16.getCharCount(cp);
            }
            return;
        }
        output.add(source2);
    }

    static {
        HashSet hashSet = new HashSet();
        SET_WITH_NULL_STRING = hashSet;
        hashSet.add("");
    }

    private String[] getEquivalents(String segment) {
        Set<String> result = new HashSet<>();
        Set<String> basic = getEquivalents2(segment);
        Set<String> permutations = new HashSet<>();
        for (String item : basic) {
            permutations.clear();
            permute(item, SKIP_ZEROS, permutations);
            for (String possible : permutations) {
                if (Normalizer.compare(possible, segment, 0) == 0) {
                    if (PROGRESS) {
                        System.out.println("Adding Permutation: " + Utility.hex(possible));
                    }
                    result.add(possible);
                } else if (PROGRESS) {
                    System.out.println("-Skipping Permutation: " + Utility.hex(possible));
                }
            }
        }
        String[] finalResult = new String[result.size()];
        result.toArray(finalResult);
        return finalResult;
    }

    private Set<String> getEquivalents2(String segment) {
        Set<String> result = new HashSet<>();
        if (PROGRESS) {
            System.out.println("Adding: " + Utility.hex(segment));
        }
        result.add(segment);
        StringBuffer workingBuffer = new StringBuffer();
        UnicodeSet starts = new UnicodeSet();
        int i = 0;
        while (i < segment.length()) {
            int cp = segment.codePointAt(i);
            if (this.nfcImpl.getCanonStartSet(cp, starts)) {
                UnicodeSetIterator iter = new UnicodeSetIterator(starts);
                while (iter.next()) {
                    int cp2 = iter.codepoint;
                    Set<String> remainder = extract(cp2, segment, i, workingBuffer);
                    if (remainder != null) {
                        String prefix = segment.substring(0, i) + UTF16.valueOf(cp2);
                        for (String item : remainder) {
                            result.add(prefix + item);
                        }
                    }
                }
            }
            i += Character.charCount(cp);
        }
        return result;
    }

    private Set<String> extract(int comp, String segment, int segmentPos, StringBuffer buf) {
        if (PROGRESS) {
            System.out.println(" extract: " + Utility.hex(UTF16.valueOf(comp)) + ", " + Utility.hex(segment.substring(segmentPos)));
        }
        String decomp = this.nfcImpl.getDecomposition(comp);
        if (decomp == null) {
            decomp = UTF16.valueOf(comp);
        }
        boolean ok = false;
        int decompCp = UTF16.charAt(decomp, 0);
        int decompPos = 0 + UTF16.getCharCount(decompCp);
        buf.setLength(0);
        int i = segmentPos;
        while (true) {
            if (i >= segment.length()) {
                break;
            }
            int cp = UTF16.charAt(segment, i);
            if (cp == decompCp) {
                if (PROGRESS) {
                    System.out.println("  matches: " + Utility.hex(UTF16.valueOf(cp)));
                }
                if (decompPos == decomp.length()) {
                    buf.append(segment.substring(UTF16.getCharCount(cp) + i));
                    ok = true;
                    break;
                }
                decompCp = UTF16.charAt(decomp, decompPos);
                decompPos += UTF16.getCharCount(decompCp);
            } else {
                if (PROGRESS) {
                    System.out.println("  buffer: " + Utility.hex(UTF16.valueOf(cp)));
                }
                UTF16.append(buf, cp);
            }
            i += UTF16.getCharCount(cp);
        }
        if (!ok) {
            return null;
        }
        if (PROGRESS) {
            System.out.println("Matches");
        }
        if (buf.length() == 0) {
            return SET_WITH_NULL_STRING;
        }
        String remainder = buf.toString();
        if (Normalizer.compare(UTF16.valueOf(comp) + remainder, segment.substring(segmentPos), 0) != 0) {
            return null;
        }
        return getEquivalents2(remainder);
    }
}
