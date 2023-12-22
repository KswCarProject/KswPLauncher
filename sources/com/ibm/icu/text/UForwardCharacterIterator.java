package com.ibm.icu.text;

/* loaded from: classes.dex */
public interface UForwardCharacterIterator {
    public static final int DONE = -1;

    int next();

    int nextCodePoint();
}
