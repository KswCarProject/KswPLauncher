package com.google.zxing.client.result;

import com.bumptech.glide.load.Key;
import com.google.zxing.Result;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class ResultParser {
    private static final Pattern AMPERSAND = Pattern.compile("&");
    private static final String BYTE_ORDER_MARK = "﻿";
    private static final Pattern DIGITS = Pattern.compile("\\d+");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final ResultParser[] PARSERS = {new BookmarkDoCoMoResultParser(), new AddressBookDoCoMoResultParser(), new EmailDoCoMoResultParser(), new AddressBookAUResultParser(), new VCardResultParser(), new BizcardResultParser(), new VEventResultParser(), new EmailAddressResultParser(), new SMTPResultParser(), new TelResultParser(), new SMSMMSResultParser(), new SMSTOMMSTOResultParser(), new GeoResultParser(), new WifiResultParser(), new URLTOResultParser(), new URIResultParser(), new ISBNResultParser(), new ProductResultParser(), new ExpandedProductResultParser(), new VINResultParser()};

    public abstract ParsedResult parse(Result result);

    protected static String getMassagedText(Result result) {
        String text = result.getText();
        String text2 = text;
        if (text.startsWith(BYTE_ORDER_MARK)) {
            return text2.substring(1);
        }
        return text2;
    }

    public static ParsedResult parseResult(Result theResult) {
        for (ResultParser parse : PARSERS) {
            ParsedResult parse2 = parse.parse(theResult);
            ParsedResult result = parse2;
            if (parse2 != null) {
                return result;
            }
        }
        return new TextParsedResult(theResult.getText(), (String) null);
    }

    protected static void maybeAppend(String value, StringBuilder result) {
        if (value != null) {
            result.append(10);
            result.append(value);
        }
    }

    protected static void maybeAppend(String[] value, StringBuilder result) {
        if (value != null) {
            for (String s : value) {
                result.append(10);
                result.append(s);
            }
        }
    }

    protected static String[] maybeWrap(String value) {
        if (value == null) {
            return null;
        }
        return new String[]{value};
    }

    protected static String unescapeBackslash(String escaped) {
        int indexOf = escaped.indexOf(92);
        int backslash = indexOf;
        if (indexOf < 0) {
            return escaped;
        }
        int max = escaped.length();
        StringBuilder sb = new StringBuilder(max - 1);
        StringBuilder unescaped = sb;
        sb.append(escaped.toCharArray(), 0, backslash);
        boolean nextIsEscaped = false;
        for (int i = backslash; i < max; i++) {
            char c = escaped.charAt(i);
            if (nextIsEscaped || c != '\\') {
                unescaped.append(c);
                nextIsEscaped = false;
            } else {
                nextIsEscaped = true;
            }
        }
        return unescaped.toString();
    }

    protected static int parseHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 'A') + 10;
    }

    protected static boolean isStringOfDigits(CharSequence value, int length) {
        return value != null && length > 0 && length == value.length() && DIGITS.matcher(value).matches();
    }

    protected static boolean isSubstringOfDigits(CharSequence value, int offset, int length) {
        int max;
        if (value == null || length <= 0 || value.length() < (max = offset + length) || !DIGITS.matcher(value.subSequence(offset, max)).matches()) {
            return false;
        }
        return true;
    }

    static Map<String, String> parseNameValuePairs(String uri) {
        int indexOf = uri.indexOf(63);
        int paramStart = indexOf;
        if (indexOf < 0) {
            return null;
        }
        Map<String, String> result = new HashMap<>(3);
        for (String appendKeyValue : AMPERSAND.split(uri.substring(paramStart + 1))) {
            appendKeyValue(appendKeyValue, result);
        }
        return result;
    }

    private static void appendKeyValue(CharSequence keyValue, Map<String, String> result) {
        String[] split = EQUALS.split(keyValue, 2);
        String[] keyValueTokens = split;
        if (split.length == 2) {
            try {
                result.put(keyValueTokens[0], urlDecode(keyValueTokens[1]));
            } catch (IllegalArgumentException e) {
            }
        }
    }

    static String urlDecode(String encoded) {
        try {
            return URLDecoder.decode(encoded, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalStateException(uee);
        }
    }

    static String[] matchPrefixedField(String prefix, String rawText, char endChar, boolean trim) {
        List<String> matches = null;
        int i = 0;
        int max = rawText.length();
        while (i < max) {
            int indexOf = rawText.indexOf(prefix, i);
            int i2 = indexOf;
            if (indexOf < 0) {
                break;
            }
            int start = prefix.length() + i2;
            i = start;
            boolean more = true;
            while (more) {
                int indexOf2 = rawText.indexOf(endChar, i);
                int i3 = indexOf2;
                if (indexOf2 < 0) {
                    i = rawText.length();
                    more = false;
                } else if (countPrecedingBackslashes(rawText, i3) % 2 != 0) {
                    i = i3 + 1;
                } else {
                    if (matches == null) {
                        matches = new ArrayList<>(3);
                    }
                    String element = unescapeBackslash(rawText.substring(start, i3));
                    if (trim) {
                        element = element.trim();
                    }
                    if (!element.isEmpty()) {
                        matches.add(element);
                    }
                    i = i3 + 1;
                    more = false;
                }
            }
        }
        if (matches == null || matches.isEmpty()) {
            return null;
        }
        return (String[]) matches.toArray(new String[matches.size()]);
    }

    private static int countPrecedingBackslashes(CharSequence s, int pos) {
        int count = 0;
        int i = pos - 1;
        while (i >= 0 && s.charAt(i) == '\\') {
            count++;
            i--;
        }
        return count;
    }

    static String matchSinglePrefixedField(String prefix, String rawText, char endChar, boolean trim) {
        String[] matchPrefixedField = matchPrefixedField(prefix, rawText, endChar, trim);
        String[] matches = matchPrefixedField;
        if (matchPrefixedField == null) {
            return null;
        }
        return matches[0];
    }
}
