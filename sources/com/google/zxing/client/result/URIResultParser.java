package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class URIResultParser extends ResultParser {
    private static final Pattern URL_WITHOUT_PROTOCOL_PATTERN = Pattern.compile("([a-zA-Z0-9\\-]+\\.){1,6}[a-zA-Z]{2,}(:\\d{1,5})?(/|\\?|$)");
    private static final Pattern URL_WITH_PROTOCOL_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+-.]+:");

    public URIParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (massagedText.startsWith("URL:") || rawText.startsWith("URI:")) {
            return new URIParsedResult(rawText.substring(4).trim(), (String) null);
        }
        String trim = rawText.trim();
        String rawText2 = trim;
        if (isBasicallyValidURI(trim)) {
            return new URIParsedResult(rawText2, (String) null);
        }
        return null;
    }

    static boolean isBasicallyValidURI(String uri) {
        if (uri.contains(" ")) {
            return false;
        }
        Matcher matcher = URL_WITH_PROTOCOL_PATTERN.matcher(uri);
        Matcher m = matcher;
        if (matcher.find() && m.start() == 0) {
            return true;
        }
        Matcher matcher2 = URL_WITHOUT_PROTOCOL_PATTERN.matcher(uri);
        Matcher m2 = matcher2;
        if (!matcher2.find() || m2.start() != 0) {
            return false;
        }
        return true;
    }
}
