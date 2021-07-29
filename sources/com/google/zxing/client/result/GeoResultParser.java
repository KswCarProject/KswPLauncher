package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GeoResultParser extends ResultParser {
    private static final Pattern GEO_URL_PATTERN = Pattern.compile("geo:([\\-0-9.]+),([\\-0-9.]+)(?:,([\\-0-9.]+))?(?:\\?(.*))?", 2);

    public GeoParsedResult parse(Result result) {
        double altitude;
        Matcher matcher = GEO_URL_PATTERN.matcher(getMassagedText(result));
        Matcher matcher2 = matcher;
        if (!matcher.matches()) {
            return null;
        }
        String query = matcher2.group(4);
        try {
            double parseDouble = Double.parseDouble(matcher2.group(1));
            double latitude = parseDouble;
            if (parseDouble <= 90.0d) {
                if (latitude >= -90.0d) {
                    double parseDouble2 = Double.parseDouble(matcher2.group(2));
                    double longitude = parseDouble2;
                    if (parseDouble2 <= 180.0d) {
                        if (longitude >= -180.0d) {
                            if (matcher2.group(3) == null) {
                                altitude = 0.0d;
                            } else {
                                double parseDouble3 = Double.parseDouble(matcher2.group(3));
                                double altitude2 = parseDouble3;
                                if (parseDouble3 < 0.0d) {
                                    return null;
                                }
                                altitude = altitude2;
                            }
                            return new GeoParsedResult(latitude, longitude, altitude, query);
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
