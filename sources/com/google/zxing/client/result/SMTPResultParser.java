package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class SMTPResultParser extends ResultParser {
    public EmailAddressParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (!massagedText.startsWith("smtp:") && !rawText.startsWith("SMTP:")) {
            return null;
        }
        String emailAddress = rawText.substring(5);
        String subject = null;
        String body = null;
        int indexOf = emailAddress.indexOf(58);
        int colon = indexOf;
        if (indexOf >= 0) {
            subject = emailAddress.substring(colon + 1);
            emailAddress = emailAddress.substring(0, colon);
            int indexOf2 = subject.indexOf(58);
            int colon2 = indexOf2;
            if (indexOf2 >= 0) {
                body = subject.substring(colon2 + 1);
                subject = subject.substring(0, colon2);
                int i = colon2;
            } else {
                int i2 = colon2;
            }
        } else {
            int i3 = colon;
        }
        return new EmailAddressParsedResult(new String[]{emailAddress}, (String[]) null, (String[]) null, subject, body);
    }
}
