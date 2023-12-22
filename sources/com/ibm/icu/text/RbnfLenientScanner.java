package com.ibm.icu.text;

@Deprecated
/* loaded from: classes.dex */
public interface RbnfLenientScanner {
    @Deprecated
    boolean allIgnorable(String str);

    @Deprecated
    int[] findText(String str, String str2, int i);

    @Deprecated
    int prefixLength(String str, String str2);
}
