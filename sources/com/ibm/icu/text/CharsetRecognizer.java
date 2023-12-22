package com.ibm.icu.text;

/* loaded from: classes.dex */
abstract class CharsetRecognizer {
    abstract String getName();

    abstract CharsetMatch match(CharsetDetector charsetDetector);

    CharsetRecognizer() {
    }

    public String getLanguage() {
        return null;
    }
}
