package com.google.zxing.client.result;

import com.google.zxing.Result;
import com.wits.ksw.launcher.utils.NaviInfo;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VCardResultParser extends ResultParser {
    private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final Pattern NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final Pattern SEMICOLON_OR_COMMA = Pattern.compile("[;,]");
    private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
    private static final Pattern VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");

    public AddressBookParsedResult parse(Result result) {
        List<String> birthday;
        String rawText = getMassagedText(result);
        Matcher matcher = BEGIN_VCARD.matcher(rawText);
        Matcher m = matcher;
        if (!matcher.find() || m.start() != 0) {
            return null;
        }
        List<List<String>> matchVCardPrefixedField = matchVCardPrefixedField("FN", rawText, true, false);
        List<List<String>> names = matchVCardPrefixedField;
        if (matchVCardPrefixedField == null) {
            List<List<String>> matchVCardPrefixedField2 = matchVCardPrefixedField("N", rawText, true, false);
            names = matchVCardPrefixedField2;
            formatNames(matchVCardPrefixedField2);
        }
        List<String> nicknameString = matchSingleVCardPrefixedField("NICKNAME", rawText, true, false);
        String[] nicknames = nicknameString == null ? null : COMMA.split(nicknameString.get(0));
        List<List<String>> phoneNumbers = matchVCardPrefixedField("TEL", rawText, true, false);
        List<List<String>> emails = matchVCardPrefixedField("EMAIL", rawText, true, false);
        List<String> note = matchSingleVCardPrefixedField("NOTE", rawText, false, false);
        List<List<String>> addresses = matchVCardPrefixedField("ADR", rawText, true, true);
        List<String> org2 = matchSingleVCardPrefixedField("ORG", rawText, true, true);
        List<String> matchSingleVCardPrefixedField = matchSingleVCardPrefixedField("BDAY", rawText, true, false);
        List<String> birthday2 = matchSingleVCardPrefixedField;
        if (matchSingleVCardPrefixedField == null || isLikeVCardDate(birthday2.get(0))) {
            birthday = birthday2;
        } else {
            birthday = null;
        }
        List<String> title = matchSingleVCardPrefixedField("TITLE", rawText, true, false);
        List<List<String>> urls = matchVCardPrefixedField("URL", rawText, true, false);
        List<String> instantMessenger = matchSingleVCardPrefixedField("IMPP", rawText, true, false);
        List<String> matchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("GEO", rawText, true, false);
        List<String> geoString = matchSingleVCardPrefixedField2;
        String[] split = matchSingleVCardPrefixedField2 == null ? null : SEMICOLON_OR_COMMA.split(geoString.get(0));
        String[] geo = split;
        if (!(split == null || geo.length == 2)) {
            geo = null;
        }
        List<String> list = geoString;
        return new AddressBookParsedResult(toPrimaryValues(names), nicknames, (String) null, toPrimaryValues(phoneNumbers), toTypes(phoneNumbers), toPrimaryValues(emails), toTypes(emails), toPrimaryValue(instantMessenger), toPrimaryValue(note), toPrimaryValues(addresses), toTypes(addresses), toPrimaryValue(org2), toPrimaryValue(birthday), toPrimaryValue(title), toPrimaryValues(urls), geo);
    }

    static List<List<String>> matchVCardPrefixedField(CharSequence charSequence, String str, boolean z, boolean z2) {
        String str2;
        String str3;
        int i;
        ArrayList arrayList;
        int indexOf;
        String str4;
        int i2;
        String str5 = str;
        int length = str.length();
        int i3 = 0;
        int i4 = 0;
        ArrayList arrayList2 = null;
        while (i4 < length) {
            int i5 = 2;
            Matcher matcher = Pattern.compile("(?:^|\n)" + charSequence + "(?:;([^:]*))?:", 2).matcher(str5);
            if (i4 > 0) {
                i4--;
            }
            if (!matcher.find(i4)) {
                break;
            }
            int end = matcher.end(i3);
            String group = matcher.group(1);
            if (group != null) {
                String[] split = SEMICOLON.split(group);
                int length2 = split.length;
                int i6 = i3;
                i = i6;
                arrayList = null;
                str3 = null;
                str2 = null;
                while (i6 < length2) {
                    String str6 = split[i6];
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(str6);
                    String[] split2 = EQUALS.split(str6, i5);
                    if (split2.length > 1) {
                        String str7 = split2[0];
                        String str8 = split2[1];
                        if ("ENCODING".equalsIgnoreCase(str7) && "QUOTED-PRINTABLE".equalsIgnoreCase(str8)) {
                            i = 1;
                        } else if ("CHARSET".equalsIgnoreCase(str7)) {
                            str3 = str8;
                        } else if ("VALUE".equalsIgnoreCase(str7)) {
                            str2 = str8;
                        }
                    }
                    i6++;
                    i5 = 2;
                }
            } else {
                arrayList = null;
                i = 0;
                str3 = null;
                str2 = null;
            }
            int i7 = end;
            while (true) {
                indexOf = str5.indexOf(10, i7);
                if (indexOf < 0) {
                    break;
                }
                if (indexOf < str.length() - 1) {
                    int i8 = indexOf + 1;
                    if (str5.charAt(i8) == ' ' || str5.charAt(i8) == 9) {
                        i7 = indexOf + 2;
                    }
                }
                if (i == 0) {
                    break;
                }
                if (indexOf <= 0 || str5.charAt(indexOf - 1) != '=') {
                    if (indexOf >= 2) {
                        if (str5.charAt(indexOf - 2) != '=') {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i7 = indexOf + 1;
            }
            if (indexOf < 0) {
                i4 = length;
                i3 = 0;
            } else if (indexOf > end) {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList(1);
                }
                if (indexOf > 0 && str5.charAt(indexOf - 1) == 13) {
                    indexOf--;
                }
                String substring = str5.substring(end, indexOf);
                if (z) {
                    substring = substring.trim();
                }
                if (i != 0) {
                    String decodeQuotedPrintable = decodeQuotedPrintable(substring, str3);
                    if (z2) {
                        str4 = UNESCAPED_SEMICOLONS.matcher(decodeQuotedPrintable).replaceAll("\n").trim();
                    } else {
                        str4 = decodeQuotedPrintable;
                    }
                } else {
                    if (z2) {
                        substring = UNESCAPED_SEMICOLONS.matcher(substring).replaceAll("\n").trim();
                    }
                    str4 = VCARD_ESCAPES.matcher(NEWLINE_ESCAPE.matcher(CR_LF_SPACE_TAB.matcher(substring).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                }
                if ("uri".equals(str2)) {
                    try {
                        str4 = URI.create(str4).getSchemeSpecificPart();
                    } catch (IllegalArgumentException e) {
                    }
                }
                if (arrayList == null) {
                    ArrayList arrayList3 = new ArrayList(1);
                    arrayList3.add(str4);
                    arrayList2.add(arrayList3);
                    i2 = 0;
                } else {
                    i2 = 0;
                    arrayList.add(0, str4);
                    arrayList2.add(arrayList);
                }
                i4 = indexOf + 1;
                i3 = i2;
            } else {
                i4 = indexOf + 1;
                i3 = 0;
            }
        }
        return arrayList2;
    }

    private static String decodeQuotedPrintable(CharSequence value, String charset) {
        int length = value.length();
        StringBuilder result = new StringBuilder(length);
        ByteArrayOutputStream fragmentBuffer = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char charAt = value.charAt(i);
            char c = charAt;
            switch (charAt) {
                case 10:
                case 13:
                    break;
                case '=':
                    if (i >= length - 2) {
                        break;
                    } else {
                        char charAt2 = value.charAt(i + 1);
                        char nextChar = charAt2;
                        if (!(charAt2 == 13 || nextChar == 10)) {
                            char nextNextChar = value.charAt(i + 2);
                            int firstDigit = parseHexDigit(nextChar);
                            int secondDigit = parseHexDigit(nextNextChar);
                            if (firstDigit >= 0 && secondDigit >= 0) {
                                fragmentBuffer.write((firstDigit << 4) + secondDigit);
                            }
                            i += 2;
                            break;
                        }
                    }
                default:
                    maybeAppendFragment(fragmentBuffer, charset, result);
                    result.append(c);
                    break;
            }
            i++;
        }
        maybeAppendFragment(fragmentBuffer, charset, result);
        return result.toString();
    }

    private static void maybeAppendFragment(ByteArrayOutputStream fragmentBuffer, String charset, StringBuilder result) {
        String fragment;
        if (fragmentBuffer.size() > 0) {
            byte[] fragmentBytes = fragmentBuffer.toByteArray();
            if (charset == null) {
                fragment = new String(fragmentBytes, StandardCharsets.UTF_8);
            } else {
                try {
                    fragment = new String(fragmentBytes, charset);
                } catch (UnsupportedEncodingException e) {
                    fragment = new String(fragmentBytes, StandardCharsets.UTF_8);
                }
            }
            fragmentBuffer.reset();
            result.append(fragment);
        }
    }

    static List<String> matchSingleVCardPrefixedField(CharSequence prefix, String rawText, boolean trim, boolean parseFieldDivider) {
        List<List<String>> matchVCardPrefixedField = matchVCardPrefixedField(prefix, rawText, trim, parseFieldDivider);
        List<List<String>> values = matchVCardPrefixedField;
        if (matchVCardPrefixedField == null || values.isEmpty()) {
            return null;
        }
        return values.get(0);
    }

    private static String toPrimaryValue(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static String[] toPrimaryValues(Collection<List<String>> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        List<String> result = new ArrayList<>(lists.size());
        for (List<String> list : lists) {
            String str = (String) list.get(0);
            String value = str;
            if (str != null && !value.isEmpty()) {
                result.add(value);
            }
        }
        return (String[]) result.toArray(new String[result.size()]);
    }

    private static String[] toTypes(Collection<List<String>> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        List<String> result = new ArrayList<>(lists.size());
        for (List next : lists) {
            List list = next;
            String str = (String) next.get(0);
            String value = str;
            if (str != null && !value.isEmpty()) {
                String type = null;
                int i = 1;
                while (true) {
                    if (i >= list.size()) {
                        break;
                    }
                    String str2 = (String) list.get(i);
                    String metadatum = str2;
                    int indexOf = str2.indexOf(61);
                    int equals = indexOf;
                    if (indexOf < 0) {
                        type = metadatum;
                        break;
                    } else if (NaviInfo.TYPE.equalsIgnoreCase(metadatum.substring(0, equals))) {
                        type = metadatum.substring(equals + 1);
                        break;
                    } else {
                        i++;
                    }
                }
                result.add(type);
            }
        }
        return (String[]) result.toArray(new String[result.size()]);
    }

    private static boolean isLikeVCardDate(CharSequence value) {
        return value == null || VCARD_LIKE_DATE.matcher(value).matches();
    }

    private static void formatNames(Iterable<List<String>> names) {
        if (names != null) {
            for (List next : names) {
                List list = next;
                String name = (String) next.get(0);
                String[] components = new String[5];
                int start = 0;
                int componentIndex = 0;
                while (componentIndex < 4) {
                    int indexOf = name.indexOf(59, start);
                    int end = indexOf;
                    if (indexOf < 0) {
                        break;
                    }
                    components[componentIndex] = name.substring(start, end);
                    componentIndex++;
                    start = end + 1;
                }
                components[componentIndex] = name.substring(start);
                StringBuilder newName = new StringBuilder(100);
                maybeAppendComponent(components, 3, newName);
                maybeAppendComponent(components, 1, newName);
                maybeAppendComponent(components, 2, newName);
                maybeAppendComponent(components, 0, newName);
                maybeAppendComponent(components, 4, newName);
                list.set(0, newName.toString().trim());
            }
        }
    }

    private static void maybeAppendComponent(String[] components, int i, StringBuilder newName) {
        if (components[i] != null && !components[i].isEmpty()) {
            if (newName.length() > 0) {
                newName.append(' ');
            }
            newName.append(components[i]);
        }
    }
}
