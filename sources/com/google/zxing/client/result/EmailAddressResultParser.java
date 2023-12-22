package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class EmailAddressResultParser extends ResultParser {
    private static final Pattern COMMA = Pattern.compile(",");

    @Override // com.google.zxing.client.result.ResultParser
    public EmailAddressParsedResult parse(Result result) {
        String tosString;
        String rawText = getMassagedText(result);
        if (rawText.startsWith("mailto:") || rawText.startsWith("MAILTO:")) {
            String substring = rawText.substring(7);
            String hostEmail = substring;
            int queryStart = substring.indexOf(63);
            if (queryStart >= 0) {
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
                    if (tos == null && (tosString = nameValues.get("to")) != null) {
                        tos = COMMA.split(tosString);
                    }
                    String ccString = nameValues.get("cc");
                    if (ccString != null) {
                        ccs = COMMA.split(ccString);
                    }
                    String bccString = nameValues.get("bcc");
                    if (bccString != null) {
                        bccs = COMMA.split(bccString);
                    }
                    String subject2 = nameValues.get("subject");
                    subject = subject2;
                    String body2 = nameValues.get("body");
                    body = body2;
                }
                return new EmailAddressParsedResult(tos, ccs, bccs, subject, body);
            } catch (IllegalArgumentException e) {
                return null;
            }
        } else if (EmailDoCoMoResultParser.isBasicallyValidEmailAddress(rawText)) {
            return new EmailAddressParsedResult(rawText);
        } else {
            return null;
        }
    }
}
