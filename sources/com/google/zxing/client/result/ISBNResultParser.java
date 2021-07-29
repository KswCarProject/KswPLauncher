package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

public final class ISBNResultParser extends ResultParser {
    public ISBNParsedResult parse(Result result) {
        if (result.getBarcodeFormat() != BarcodeFormat.EAN_13) {
            return null;
        }
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (massagedText.length() != 13) {
            return null;
        }
        if (rawText.startsWith("978") || rawText.startsWith("979")) {
            return new ISBNParsedResult(rawText);
        }
        return null;
    }
}
