package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;

public final class AddressBookAUResultParser extends ResultParser {
    public AddressBookParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String[] addresses = null;
        String rawText = massagedText;
        if (!massagedText.contains("MEMORY") || !rawText.contains("\r\n")) {
            return null;
        }
        String name = matchSinglePrefixedField("NAME1:", rawText, 13, true);
        String pronunciation = matchSinglePrefixedField("NAME2:", rawText, 13, true);
        String[] phoneNumbers = matchMultipleValuePrefix("TEL", 3, rawText, true);
        String[] emails = matchMultipleValuePrefix("MAIL", 3, rawText, true);
        String note = matchSinglePrefixedField("MEMORY:", rawText, 13, false);
        String matchSinglePrefixedField = matchSinglePrefixedField("ADD:", rawText, 13, true);
        String address = matchSinglePrefixedField;
        if (matchSinglePrefixedField != null) {
            addresses = new String[]{address};
        }
        return new AddressBookParsedResult(maybeWrap(name), (String[]) null, pronunciation, phoneNumbers, (String[]) null, emails, (String[]) null, (String) null, note, addresses, (String[]) null, (String) null, (String) null, (String) null, (String[]) null, (String[]) null);
    }

    private static String[] matchMultipleValuePrefix(String prefix, int max, String rawText, boolean trim) {
        List<String> values = null;
        for (int i = 1; i <= max; i++) {
            String matchSinglePrefixedField = matchSinglePrefixedField(prefix + i + ':', rawText, 13, trim);
            String value = matchSinglePrefixedField;
            if (matchSinglePrefixedField == null) {
                break;
            }
            if (values == null) {
                values = new ArrayList<>(max);
            }
            values.add(value);
        }
        if (values == null) {
            return null;
        }
        return (String[]) values.toArray(new String[values.size()]);
    }
}
