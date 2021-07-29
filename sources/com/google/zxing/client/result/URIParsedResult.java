package com.google.zxing.client.result;

import java.util.regex.Pattern;

public final class URIParsedResult extends ParsedResult {
    private static final Pattern USER_IN_HOST = Pattern.compile(":/*([^/@]+)@[^/]+");
    private final String title;
    private final String uri;

    public URIParsedResult(String uri2, String title2) {
        super(ParsedResultType.URI);
        this.uri = massageURI(uri2);
        this.title = title2;
    }

    public String getURI() {
        return this.uri;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isPossiblyMaliciousURI() {
        return USER_IN_HOST.matcher(this.uri).find();
    }

    public String getDisplayResult() {
        StringBuilder result = new StringBuilder(30);
        maybeAppend(this.title, result);
        maybeAppend(this.uri, result);
        return result.toString();
    }

    private static String massageURI(String uri2) {
        String trim = uri2.trim();
        String uri3 = trim;
        int indexOf = trim.indexOf(58);
        int protocolEnd = indexOf;
        if (indexOf < 0 || isColonFollowedByPortNumber(uri3, protocolEnd)) {
            return "http://".concat(String.valueOf(uri3));
        }
        return uri3;
    }

    private static boolean isColonFollowedByPortNumber(String uri2, int protocolEnd) {
        int start = protocolEnd + 1;
        int indexOf = uri2.indexOf(47, start);
        int nextSlash = indexOf;
        if (indexOf < 0) {
            nextSlash = uri2.length();
        }
        return ResultParser.isSubstringOfDigits(uri2, start, nextSlash - start);
    }
}
