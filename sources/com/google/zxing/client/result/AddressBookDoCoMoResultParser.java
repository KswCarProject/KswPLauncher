package com.google.zxing.client.result;

import com.google.zxing.Result;

/* loaded from: classes.dex */
public final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String[] rawName;
        String rawText = getMassagedText(result);
        if (rawText.startsWith("MECARD:") && (rawName = matchDoCoMoPrefixedField("N:", rawText, true)) != null) {
            String name = parseName(rawName[0]);
            String pronunciation = matchSingleDoCoMoPrefixedField("SOUND:", rawText, true);
            String[] phoneNumbers = matchDoCoMoPrefixedField("TEL:", rawText, true);
            String[] emails = matchDoCoMoPrefixedField("EMAIL:", rawText, true);
            String note = matchSingleDoCoMoPrefixedField("NOTE:", rawText, false);
            String[] addresses = matchDoCoMoPrefixedField("ADR:", rawText, true);
            String matchSingleDoCoMoPrefixedField = matchSingleDoCoMoPrefixedField("BDAY:", rawText, true);
            String birthday = matchSingleDoCoMoPrefixedField;
            if (!isStringOfDigits(matchSingleDoCoMoPrefixedField, 8)) {
                birthday = null;
            }
            String[] urls = matchDoCoMoPrefixedField("URL:", rawText, true);
            String org2 = matchSingleDoCoMoPrefixedField("ORG:", rawText, true);
            return new AddressBookParsedResult(maybeWrap(name), null, pronunciation, phoneNumbers, null, emails, null, null, note, addresses, null, org2, birthday, null, urls, null);
        }
        return null;
    }

    private static String parseName(String name) {
        int comma = name.indexOf(44);
        if (comma >= 0) {
            return name.substring(comma + 1) + ' ' + name.substring(0, comma);
        }
        return name;
    }
}
