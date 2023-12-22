package com.google.zxing.client.result;

import com.google.zxing.Result;

/* loaded from: classes.dex */
public final class TelResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public TelParsedResult parse(Result result) {
        String rawText = getMassagedText(result);
        if (rawText.startsWith("tel:") || rawText.startsWith("TEL:")) {
            String telURI = rawText.startsWith("TEL:") ? "tel:" + rawText.substring(4) : rawText;
            int queryStart = rawText.indexOf(63, 4);
            String number = queryStart < 0 ? rawText.substring(4) : rawText.substring(4, queryStart);
            return new TelParsedResult(number, telURI, null);
        }
        return null;
    }
}
