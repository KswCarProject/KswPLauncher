package com.google.zxing.client.result;

import com.google.zxing.Result;

/* loaded from: classes.dex */
public final class SMSTOMMSTOResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public SMSParsedResult parse(Result result) {
        String rawText = getMassagedText(result);
        if (rawText.startsWith("smsto:") || rawText.startsWith("SMSTO:") || rawText.startsWith("mmsto:") || rawText.startsWith("MMSTO:")) {
            String number = rawText.substring(6);
            String body = null;
            int bodyStart = number.indexOf(58);
            if (bodyStart >= 0) {
                body = number.substring(bodyStart + 1);
                number = number.substring(0, bodyStart);
            }
            return new SMSParsedResult(number, (String) null, (String) null, body);
        }
        return null;
    }
}
