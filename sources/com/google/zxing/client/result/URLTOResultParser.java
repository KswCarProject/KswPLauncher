package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class URLTOResultParser extends ResultParser {
    public URIParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String title = null;
        String rawText = massagedText;
        if (!massagedText.startsWith("urlto:") && !rawText.startsWith("URLTO:")) {
            return null;
        }
        int indexOf = rawText.indexOf(58, 6);
        int titleEnd = indexOf;
        if (indexOf < 0) {
            return null;
        }
        if (titleEnd > 6) {
            title = rawText.substring(6, titleEnd);
        }
        return new URIParsedResult(rawText.substring(titleEnd + 1), title);
    }
}
