package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class BookmarkDoCoMoResultParser extends AbstractDoCoMoResultParser {
    public URIParsedResult parse(Result result) {
        String text = result.getText();
        String rawText = text;
        if (!text.startsWith("MEBKM:")) {
            return null;
        }
        String title = matchSingleDoCoMoPrefixedField("TITLE:", rawText, true);
        String[] matchDoCoMoPrefixedField = matchDoCoMoPrefixedField("URL:", rawText, true);
        String[] rawUri = matchDoCoMoPrefixedField;
        if (matchDoCoMoPrefixedField == null) {
            return null;
        }
        String str = rawUri[0];
        String uri = str;
        if (URIResultParser.isBasicallyValidURI(str)) {
            return new URIParsedResult(uri, title);
        }
        return null;
    }
}
