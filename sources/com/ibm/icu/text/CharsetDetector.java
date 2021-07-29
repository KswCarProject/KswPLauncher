package com.ibm.icu.text;

import com.ibm.icu.text.CharsetRecog_2022;
import com.ibm.icu.text.CharsetRecog_Unicode;
import com.ibm.icu.text.CharsetRecog_mbcs;
import com.ibm.icu.text.CharsetRecog_sbcs;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CharsetDetector {
    private static final List<CSRecognizerInfo> ALL_CS_RECOGNIZERS;
    private static final int kBufSize = 8000;
    short[] fByteStats = new short[256];
    boolean fC1Bytes = false;
    String fDeclaredEncoding;
    private boolean[] fEnabledRecognizers;
    byte[] fInputBytes = new byte[kBufSize];
    int fInputLen;
    InputStream fInputStream;
    byte[] fRawInput;
    int fRawLength;
    private boolean fStripTags = false;

    public CharsetDetector setDeclaredEncoding(String encoding) {
        this.fDeclaredEncoding = encoding;
        return this;
    }

    public CharsetDetector setText(byte[] in) {
        this.fRawInput = in;
        this.fRawLength = in.length;
        return this;
    }

    public CharsetDetector setText(InputStream in) throws IOException {
        this.fInputStream = in;
        in.mark(kBufSize);
        this.fRawInput = new byte[kBufSize];
        this.fRawLength = 0;
        int remainingLength = kBufSize;
        while (remainingLength > 0) {
            int bytesRead = this.fInputStream.read(this.fRawInput, this.fRawLength, remainingLength);
            if (bytesRead <= 0) {
                break;
            }
            this.fRawLength += bytesRead;
            remainingLength -= bytesRead;
        }
        this.fInputStream.reset();
        return this;
    }

    public CharsetMatch detect() {
        CharsetMatch[] matches = detectAll();
        if (matches == null || matches.length == 0) {
            return null;
        }
        return matches[0];
    }

    public CharsetMatch[] detectAll() {
        CharsetMatch m;
        ArrayList<CharsetMatch> matches = new ArrayList<>();
        MungeInput();
        int i = 0;
        while (true) {
            List<CSRecognizerInfo> list = ALL_CS_RECOGNIZERS;
            if (i < list.size()) {
                CSRecognizerInfo rcinfo = list.get(i);
                boolean[] zArr = this.fEnabledRecognizers;
                if ((zArr != null ? zArr[i] : rcinfo.isDefaultEnabled) && (m = rcinfo.recognizer.match(this)) != null) {
                    matches.add(m);
                }
                i++;
            } else {
                Collections.sort(matches);
                Collections.reverse(matches);
                return (CharsetMatch[]) matches.toArray(new CharsetMatch[matches.size()]);
            }
        }
    }

    public Reader getReader(InputStream in, String declaredEncoding) {
        this.fDeclaredEncoding = declaredEncoding;
        try {
            setText(in);
            CharsetMatch match = detect();
            if (match == null) {
                return null;
            }
            return match.getReader();
        } catch (IOException e) {
            return null;
        }
    }

    public String getString(byte[] in, String declaredEncoding) {
        this.fDeclaredEncoding = declaredEncoding;
        try {
            setText(in);
            CharsetMatch match = detect();
            if (match == null) {
                return null;
            }
            return match.getString(-1);
        } catch (IOException e) {
            return null;
        }
    }

    public static String[] getAllDetectableCharsets() {
        String[] allCharsetNames = new String[ALL_CS_RECOGNIZERS.size()];
        for (int i = 0; i < allCharsetNames.length; i++) {
            allCharsetNames[i] = ALL_CS_RECOGNIZERS.get(i).recognizer.getName();
        }
        return allCharsetNames;
    }

    public boolean inputFilterEnabled() {
        return this.fStripTags;
    }

    public boolean enableInputFilter(boolean filter) {
        boolean previous = this.fStripTags;
        this.fStripTags = filter;
        return previous;
    }

    private void MungeInput() {
        int dsti = 0;
        boolean inMarkup = false;
        int openTags = 0;
        int badTags = 0;
        if (this.fStripTags) {
            for (int srci = 0; srci < this.fRawLength; srci++) {
                byte[] bArr = this.fInputBytes;
                if (dsti >= bArr.length) {
                    break;
                }
                byte b = this.fRawInput[srci];
                if (b == 60) {
                    if (inMarkup) {
                        badTags++;
                    }
                    inMarkup = true;
                    openTags++;
                }
                if (!inMarkup) {
                    bArr[dsti] = b;
                    dsti++;
                }
                if (b == 62) {
                    inMarkup = false;
                }
            }
            this.fInputLen = dsti;
        }
        if (openTags < 5 || openTags / 5 < badTags || (this.fInputLen < 100 && this.fRawLength > 600)) {
            int limit = this.fRawLength;
            if (limit > kBufSize) {
                limit = kBufSize;
            }
            int srci2 = 0;
            while (srci2 < limit) {
                this.fInputBytes[srci2] = this.fRawInput[srci2];
                srci2++;
            }
            this.fInputLen = srci2;
        }
        Arrays.fill(this.fByteStats, 0);
        for (int srci3 = 0; srci3 < this.fInputLen; srci3++) {
            int val = this.fInputBytes[srci3] & 255;
            short[] sArr = this.fByteStats;
            sArr[val] = (short) (sArr[val] + 1);
        }
        this.fC1Bytes = false;
        for (int i = 128; i <= 159; i++) {
            if (this.fByteStats[i] != 0) {
                this.fC1Bytes = true;
                return;
            }
        }
    }

    private static class CSRecognizerInfo {
        boolean isDefaultEnabled;
        CharsetRecognizer recognizer;

        CSRecognizerInfo(CharsetRecognizer recognizer2, boolean isDefaultEnabled2) {
            this.recognizer = recognizer2;
            this.isDefaultEnabled = isDefaultEnabled2;
        }
    }

    static {
        List<CSRecognizerInfo> list = new ArrayList<>();
        list.add(new CSRecognizerInfo(new CharsetRecog_UTF8(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_Unicode.CharsetRecog_UTF_16_BE(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_Unicode.CharsetRecog_UTF_16_LE(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_Unicode.CharsetRecog_UTF_32_BE(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_Unicode.CharsetRecog_UTF_32_LE(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_mbcs.CharsetRecog_sjis(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_2022.CharsetRecog_2022JP(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_2022.CharsetRecog_2022CN(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_2022.CharsetRecog_2022KR(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_mbcs.CharsetRecog_gb_18030(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_mbcs.CharsetRecog_euc.CharsetRecog_euc_jp(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_mbcs.CharsetRecog_euc.CharsetRecog_euc_kr(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_mbcs.CharsetRecog_big5(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_1(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_2(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_5_ru(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_6_ar(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_7_el(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_8_I_he(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_8_he(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_windows_1251(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_windows_1256(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_KOI8_R(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_8859_9_tr(), true));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_IBM424_he_rtl(), false));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_IBM424_he_ltr(), false));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_IBM420_ar_rtl(), false));
        list.add(new CSRecognizerInfo(new CharsetRecog_sbcs.CharsetRecog_IBM420_ar_ltr(), false));
        ALL_CS_RECOGNIZERS = Collections.unmodifiableList(list);
    }

    @Deprecated
    public String[] getDetectableCharsets() {
        List<String> csnames = new ArrayList<>(ALL_CS_RECOGNIZERS.size());
        int i = 0;
        while (true) {
            List<CSRecognizerInfo> list = ALL_CS_RECOGNIZERS;
            if (i >= list.size()) {
                return (String[]) csnames.toArray(new String[csnames.size()]);
            }
            CSRecognizerInfo rcinfo = list.get(i);
            boolean[] zArr = this.fEnabledRecognizers;
            if (zArr == null ? rcinfo.isDefaultEnabled : zArr[i]) {
                csnames.add(rcinfo.recognizer.getName());
            }
            i++;
        }
    }

    @Deprecated
    public CharsetDetector setDetectableCharset(String encoding, boolean enabled) {
        List<CSRecognizerInfo> list;
        int modIdx = -1;
        boolean isDefaultVal = false;
        int i = 0;
        while (true) {
            list = ALL_CS_RECOGNIZERS;
            if (i >= list.size()) {
                break;
            }
            CSRecognizerInfo csrinfo = list.get(i);
            if (csrinfo.recognizer.getName().equals(encoding)) {
                modIdx = i;
                isDefaultVal = csrinfo.isDefaultEnabled == enabled;
            } else {
                i++;
            }
        }
        if (modIdx >= 0) {
            if (this.fEnabledRecognizers == null && !isDefaultVal) {
                this.fEnabledRecognizers = new boolean[list.size()];
                int i2 = 0;
                while (true) {
                    List<CSRecognizerInfo> list2 = ALL_CS_RECOGNIZERS;
                    if (i2 >= list2.size()) {
                        break;
                    }
                    this.fEnabledRecognizers[i2] = list2.get(i2).isDefaultEnabled;
                    i2++;
                }
            }
            boolean[] zArr = this.fEnabledRecognizers;
            if (zArr != null) {
                zArr[modIdx] = enabled;
            }
            return this;
        }
        throw new IllegalArgumentException("Invalid encoding: \"" + encoding + "\"");
    }
}
