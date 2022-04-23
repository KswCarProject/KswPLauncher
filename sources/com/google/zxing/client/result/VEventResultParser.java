package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.List;

public final class VEventResultParser extends ResultParser {
    public CalendarParsedResult parse(Result result) {
        double d;
        String massagedText = getMassagedText(result);
        if (massagedText.indexOf("BEGIN:VEVENT") < 0) {
            return null;
        }
        String matchSingleVCardPrefixedField = matchSingleVCardPrefixedField("SUMMARY", massagedText, true);
        String matchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("DTSTART", massagedText, true);
        if (matchSingleVCardPrefixedField2 == null) {
            return null;
        }
        String matchSingleVCardPrefixedField3 = matchSingleVCardPrefixedField("DTEND", massagedText, true);
        String matchSingleVCardPrefixedField4 = matchSingleVCardPrefixedField("DURATION", massagedText, true);
        String matchSingleVCardPrefixedField5 = matchSingleVCardPrefixedField("LOCATION", massagedText, true);
        String stripMailto = stripMailto(matchSingleVCardPrefixedField("ORGANIZER", massagedText, true));
        String[] matchVCardPrefixedField = matchVCardPrefixedField("ATTENDEE", massagedText, true);
        if (matchVCardPrefixedField != null) {
            for (int i = 0; i < matchVCardPrefixedField.length; i++) {
                matchVCardPrefixedField[i] = stripMailto(matchVCardPrefixedField[i]);
            }
        }
        String matchSingleVCardPrefixedField6 = matchSingleVCardPrefixedField("DESCRIPTION", massagedText, true);
        String matchSingleVCardPrefixedField7 = matchSingleVCardPrefixedField("GEO", massagedText, true);
        double d2 = Double.NaN;
        if (matchSingleVCardPrefixedField7 == null) {
            d = Double.NaN;
        } else {
            int indexOf = matchSingleVCardPrefixedField7.indexOf(59);
            if (indexOf < 0) {
                return null;
            }
            try {
                d2 = Double.parseDouble(matchSingleVCardPrefixedField7.substring(0, indexOf));
                d = Double.parseDouble(matchSingleVCardPrefixedField7.substring(indexOf + 1));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        try {
            return new CalendarParsedResult(matchSingleVCardPrefixedField, matchSingleVCardPrefixedField2, matchSingleVCardPrefixedField3, matchSingleVCardPrefixedField4, matchSingleVCardPrefixedField5, stripMailto, matchVCardPrefixedField, matchSingleVCardPrefixedField6, d2, d);
        } catch (IllegalArgumentException e2) {
            return null;
        }
    }

    private static String matchSingleVCardPrefixedField(CharSequence prefix, String rawText, boolean trim) {
        List<String> matchSingleVCardPrefixedField = VCardResultParser.matchSingleVCardPrefixedField(prefix, rawText, trim, false);
        List<String> values = matchSingleVCardPrefixedField;
        if (matchSingleVCardPrefixedField == null || values.isEmpty()) {
            return null;
        }
        return values.get(0);
    }

    private static String[] matchVCardPrefixedField(CharSequence prefix, String rawText, boolean trim) {
        List<List<String>> matchVCardPrefixedField = VCardResultParser.matchVCardPrefixedField(prefix, rawText, trim, false);
        List<List<String>> values = matchVCardPrefixedField;
        if (matchVCardPrefixedField == null || values.isEmpty()) {
            return null;
        }
        int size = values.size();
        int size2 = size;
        String[] result = new String[size];
        for (int i = 0; i < size2; i++) {
            result[i] = (String) values.get(i).get(0);
        }
        return result;
    }

    private static String stripMailto(String s) {
        if (s == null) {
            return s;
        }
        if (s.startsWith("mailto:") || s.startsWith("MAILTO:")) {
            return s.substring(7);
        }
        return s;
    }
}
