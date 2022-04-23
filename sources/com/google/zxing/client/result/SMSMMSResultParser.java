package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class SMSMMSResultParser extends ResultParser {
    public SMSParsedResult parse(Result result) {
        String smsURIWithoutQuery;
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (!massagedText.startsWith("sms:") && !rawText.startsWith("SMS:") && !rawText.startsWith("mms:") && !rawText.startsWith("MMS:")) {
            return null;
        }
        Map<String, String> nameValuePairs = parseNameValuePairs(rawText);
        String subject = null;
        String body = null;
        boolean querySyntax = false;
        if (nameValuePairs != null && !nameValuePairs.isEmpty()) {
            subject = nameValuePairs.get("subject");
            body = nameValuePairs.get("body");
            querySyntax = true;
        }
        int indexOf = rawText.indexOf(63, 4);
        int queryStart = indexOf;
        if (indexOf < 0 || !querySyntax) {
            smsURIWithoutQuery = rawText.substring(4);
        } else {
            smsURIWithoutQuery = rawText.substring(4, queryStart);
        }
        int lastComma = -1;
        List<String> numbers = new ArrayList<>(1);
        List<String> vias = new ArrayList<>(1);
        while (true) {
            int indexOf2 = smsURIWithoutQuery.indexOf(44, lastComma + 1);
            int comma = indexOf2;
            if (indexOf2 > lastComma) {
                addNumberVia(numbers, vias, smsURIWithoutQuery.substring(lastComma + 1, comma));
                lastComma = comma;
            } else {
                addNumberVia(numbers, vias, smsURIWithoutQuery.substring(lastComma + 1));
                return new SMSParsedResult((String[]) numbers.toArray(new String[numbers.size()]), (String[]) vias.toArray(new String[vias.size()]), subject, body);
            }
        }
    }

    private static void addNumberVia(Collection<String> numbers, Collection<String> vias, String numberPart) {
        String via;
        int indexOf = numberPart.indexOf(59);
        int numberEnd = indexOf;
        if (indexOf < 0) {
            numbers.add(numberPart);
            vias.add((Object) null);
            return;
        }
        numbers.add(numberPart.substring(0, numberEnd));
        String substring = numberPart.substring(numberEnd + 1);
        String maybeVia = substring;
        if (substring.startsWith("via=")) {
            via = maybeVia.substring(4);
        } else {
            via = null;
        }
        vias.add(via);
    }
}
