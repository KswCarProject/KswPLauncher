package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser {
    public AddressBookParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (!massagedText.startsWith("MECARD:")) {
            return null;
        }
        String[] matchDoCoMoPrefixedField = matchDoCoMoPrefixedField("N:", rawText, true);
        String[] rawName = matchDoCoMoPrefixedField;
        if (matchDoCoMoPrefixedField == null) {
            return null;
        }
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
        String matchSingleDoCoMoPrefixedField2 = matchSingleDoCoMoPrefixedField("ORG:", rawText, true);
        return new AddressBookParsedResult(maybeWrap(name), (String[]) null, pronunciation, phoneNumbers, (String[]) null, emails, (String[]) null, (String) null, note, addresses, (String[]) null, matchSingleDoCoMoPrefixedField2, birthday, (String) null, matchDoCoMoPrefixedField("URL:", rawText, true), (String[]) null);
    }

    private static String parseName(String name) {
        int indexOf = name.indexOf(44);
        int comma = indexOf;
        if (indexOf >= 0) {
            return name.substring(comma + 1) + ' ' + name.substring(0, comma);
        }
        return name;
    }
}
