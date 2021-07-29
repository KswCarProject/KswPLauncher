package com.google.zxing.client.result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CalendarParsedResult extends ParsedResult {
    private static final Pattern DATE_TIME = Pattern.compile("[0-9]{8}(T[0-9]{6}Z?)?");
    private static final Pattern RFC2445_DURATION = Pattern.compile("P(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?");
    private static final long[] RFC2445_DURATION_FIELD_UNITS = {604800000, 86400000, 3600000, 60000, 1000};
    private final String[] attendees;
    private final String description;
    private final long end;
    private final boolean endAllDay;
    private final double latitude;
    private final String location;
    private final double longitude;
    private final String organizer;
    private final long start;
    private final boolean startAllDay;
    private final String summary;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CalendarParsedResult(String summary2, String startString, String endString, String durationString, String location2, String organizer2, String[] attendees2, String description2, double latitude2, double longitude2) {
        super(ParsedResultType.CALENDAR);
        this.summary = summary2;
        try {
            long parseDate = parseDate(startString);
            this.start = parseDate;
            if (endString == null) {
                long durationMS = parseDurationMS(durationString);
                this.end = durationMS < 0 ? -1 : parseDate + durationMS;
            } else {
                try {
                    this.end = parseDate(endString);
                } catch (ParseException pe) {
                    String str = location2;
                    String str2 = organizer2;
                    String[] strArr = attendees2;
                    String str3 = description2;
                    double d = latitude2;
                    double d2 = longitude2;
                    throw new IllegalArgumentException(pe.toString());
                }
            }
            boolean z = true;
            this.startAllDay = startString.length() == 8;
            this.endAllDay = (endString == null || endString.length() != 8) ? false : z;
            this.location = location2;
            this.organizer = organizer2;
            this.attendees = attendees2;
            this.description = description2;
            this.latitude = latitude2;
            this.longitude = longitude2;
        } catch (ParseException pe2) {
            String str4 = location2;
            String str5 = organizer2;
            String[] strArr2 = attendees2;
            String str6 = description2;
            double d3 = latitude2;
            double d4 = longitude2;
            throw new IllegalArgumentException(pe2.toString());
        }
    }

    public String getSummary() {
        return this.summary;
    }

    @Deprecated
    public Date getStart() {
        return new Date(this.start);
    }

    public long getStartTimestamp() {
        return this.start;
    }

    public boolean isStartAllDay() {
        return this.startAllDay;
    }

    @Deprecated
    public Date getEnd() {
        if (this.end < 0) {
            return null;
        }
        return new Date(this.end);
    }

    public long getEndTimestamp() {
        return this.end;
    }

    public boolean isEndAllDay() {
        return this.endAllDay;
    }

    public String getLocation() {
        return this.location;
    }

    public String getOrganizer() {
        return this.organizer;
    }

    public String[] getAttendees() {
        return this.attendees;
    }

    public String getDescription() {
        return this.description;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getDisplayResult() {
        StringBuilder result = new StringBuilder(100);
        maybeAppend(this.summary, result);
        maybeAppend(format(this.startAllDay, this.start), result);
        maybeAppend(format(this.endAllDay, this.end), result);
        maybeAppend(this.location, result);
        maybeAppend(this.organizer, result);
        maybeAppend(this.attendees, result);
        maybeAppend(this.description, result);
        return result.toString();
    }

    private static long parseDate(String when) throws ParseException {
        if (!DATE_TIME.matcher(when).matches()) {
            throw new ParseException(when, 0);
        } else if (when.length() == 8) {
            DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            return format.parse(when).getTime();
        } else if (when.length() != 16 || when.charAt(15) != 'Z') {
            return parseDateTimeString(when);
        } else {
            long milliseconds = parseDateTimeString(when.substring(0, 15));
            Calendar calendar = new GregorianCalendar();
            long milliseconds2 = milliseconds + ((long) calendar.get(15));
            calendar.setTime(new Date(milliseconds2));
            return ((long) calendar.get(16)) + milliseconds2;
        }
    }

    private static String format(boolean allDay, long date) {
        DateFormat dateFormat;
        if (date < 0) {
            return null;
        }
        if (allDay) {
            dateFormat = DateFormat.getDateInstance(2);
        } else {
            dateFormat = DateFormat.getDateTimeInstance(2, 2);
        }
        return dateFormat.format(Long.valueOf(date));
    }

    private static long parseDurationMS(CharSequence durationString) {
        if (durationString == null) {
            return -1;
        }
        Matcher matcher = RFC2445_DURATION.matcher(durationString);
        Matcher m = matcher;
        if (!matcher.matches()) {
            return -1;
        }
        long durationMS = 0;
        int i = 0;
        while (true) {
            long[] jArr = RFC2445_DURATION_FIELD_UNITS;
            if (i >= jArr.length) {
                return durationMS;
            }
            String group = m.group(i + 1);
            String fieldValue = group;
            if (group != null) {
                durationMS += jArr[i] * ((long) Integer.parseInt(fieldValue));
            }
            i++;
        }
    }

    private static long parseDateTimeString(String dateTimeString) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH).parse(dateTimeString).getTime();
    }
}
