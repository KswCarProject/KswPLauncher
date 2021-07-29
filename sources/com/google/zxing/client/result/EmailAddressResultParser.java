package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Map;
import java.util.regex.Pattern;

public final class EmailAddressResultParser extends ResultParser {
    private static final Pattern COMMA = Pattern.compile(",");

    public EmailAddressParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (massagedText.startsWith("mailto:") || rawText.startsWith("MAILTO:")) {
            String substring = rawText.substring(7);
            String hostEmail = substring;
            int indexOf = substring.indexOf(63);
            int queryStart = indexOf;
            if (indexOf >= 0) {
                hostEmail = hostEmail.substring(0, queryStart);
            }
            try {
                String hostEmail2 = urlDecode(hostEmail);
                String[] tos = null;
                if (!hostEmail2.isEmpty()) {
                    tos = COMMA.split(hostEmail2);
                }
                Map<String, String> nameValues = parseNameValuePairs(rawText);
                String[] ccs = null;
                String[] bccs = null;
                String subject = null;
                String body = null;
                if (nameValues != null) {
                    if (tos == null) {
                        String str = nameValues.get("to");
                        String tosString = str;
                        if (str != null) {
                            tos = COMMA.split(tosString);
                        }
                    }
                    String str2 = nameValues.get("cc");
                    String ccString = str2;
                    if (str2 != null) {
                        ccs = COMMA.split(ccString);
                    }
                    String str3 = nameValues.get("bcc");
                    String bccString = str3;
                    if (str3 != null) {
                        bccs = COMMA.split(bccString);
                    }
                    subject = nameValues.get("subject");
                    body = nameValues.get("body");
                }
                return new EmailAddressParsedResult(tos, ccs, bccs, subject, body);
            } catch (IllegalArgumentException e) {
                return null;
            }
        } else if (!EmailDoCoMoResultParser.isBasicallyValidEmailAddress(rawText)) {
            return null;
        } else {
            return new EmailAddressParsedResult(rawText);
        }
    }
}
