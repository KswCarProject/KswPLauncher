package com.google.zxing.client.result;

import kotlin.text.Typography;

/* loaded from: classes.dex */
public final class SMSParsedResult extends ParsedResult {
    private final String body;
    private final String[] numbers;
    private final String subject;
    private final String[] vias;

    public SMSParsedResult(String number, String via, String subject, String body) {
        super(ParsedResultType.SMS);
        this.numbers = new String[]{number};
        this.vias = new String[]{via};
        this.subject = subject;
        this.body = body;
    }

    public SMSParsedResult(String[] numbers, String[] vias, String subject, String body) {
        super(ParsedResultType.SMS);
        this.numbers = numbers;
        this.vias = vias;
        this.subject = subject;
        this.body = body;
    }

    public String getSMSURI() {
        StringBuilder result = new StringBuilder();
        result.append("sms:");
        boolean first = true;
        for (int i = 0; i < this.numbers.length; i++) {
            if (!first) {
                result.append(',');
            } else {
                first = false;
            }
            result.append(this.numbers[i]);
            String[] strArr = this.vias;
            if (strArr != null && strArr[i] != null) {
                result.append(";via=");
                result.append(this.vias[i]);
            }
        }
        boolean hasBody = this.body != null;
        boolean hasSubject = this.subject != null;
        if (hasBody || hasSubject) {
            result.append('?');
            if (hasBody) {
                result.append("body=");
                result.append(this.body);
            }
            if (hasSubject) {
                if (hasBody) {
                    result.append(Typography.amp);
                }
                result.append("subject=");
                result.append(this.subject);
            }
        }
        return result.toString();
    }

    public String[] getNumbers() {
        return this.numbers;
    }

    public String[] getVias() {
        return this.vias;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getBody() {
        return this.body;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder result = new StringBuilder(100);
        maybeAppend(this.numbers, result);
        maybeAppend(this.subject, result);
        maybeAppend(this.body, result);
        return result.toString();
    }
}
