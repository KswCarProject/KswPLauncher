package com.google.zxing.client.result;

import com.google.zxing.Result;

/* loaded from: classes.dex */
public final class WifiResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public WifiParsedResult parse(Result result) {
        String rawText;
        String ssid;
        String rawText2 = getMassagedText(result);
        if (!rawText2.startsWith("WIFI:") || (ssid = matchSinglePrefixedField("S:", (rawText = rawText2.substring(5)), ';', false)) == null || ssid.isEmpty()) {
            return null;
        }
        String pass = matchSinglePrefixedField("P:", rawText, ';', false);
        String matchSinglePrefixedField = matchSinglePrefixedField("T:", rawText, ';', false);
        String type = matchSinglePrefixedField;
        if (matchSinglePrefixedField == null) {
            type = "nopass";
        }
        boolean hidden = Boolean.parseBoolean(matchSinglePrefixedField("H:", rawText, ';', false));
        String identity = matchSinglePrefixedField("I:", rawText, ';', false);
        String anonymousIdentity = matchSinglePrefixedField("A:", rawText, ';', false);
        String eapMethod = matchSinglePrefixedField("E:", rawText, ';', false);
        String phase2Method = matchSinglePrefixedField("H:", rawText, ';', false);
        return new WifiParsedResult(type, ssid, pass, hidden, identity, anonymousIdentity, eapMethod, phase2Method);
    }
}
