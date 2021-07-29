package com.ibm.icu.text;

import com.ibm.icu.util.ULocale;

@Deprecated
public interface RbnfLenientScannerProvider {
    @Deprecated
    RbnfLenientScanner get(ULocale uLocale, String str);
}
