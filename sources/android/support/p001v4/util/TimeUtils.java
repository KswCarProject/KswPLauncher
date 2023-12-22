package android.support.p001v4.util;

import java.io.PrintWriter;

/* renamed from: android.support.v4.util.TimeUtils */
/* loaded from: classes.dex */
public final class TimeUtils {
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final Object sFormatSync = new Object();
    private static char[] sFormatStr = new char[24];

    private static int accumField(int amt, int suffix, boolean always, int zeropad) {
        if (amt > 99 || (always && zeropad >= 3)) {
            return suffix + 3;
        }
        if (amt > 9 || (always && zeropad >= 2)) {
            return suffix + 2;
        }
        if (always || amt > 0) {
            return suffix + 1;
        }
        return 0;
    }

    private static int printField(char[] formatStr, int amt, char suffix, int pos, boolean always, int zeropad) {
        if (always || amt > 0) {
            if ((always && zeropad >= 3) || amt > 99) {
                int dig = amt / 100;
                formatStr[pos] = (char) (dig + 48);
                pos++;
                amt -= dig * 100;
            }
            if ((always && zeropad >= 2) || amt > 9 || pos != pos) {
                int dig2 = amt / 10;
                formatStr[pos] = (char) (dig2 + 48);
                pos++;
                amt -= dig2 * 10;
            }
            formatStr[pos] = (char) (amt + 48);
            int pos2 = pos + 1;
            formatStr[pos2] = suffix;
            return pos2 + 1;
        }
        return pos;
    }

    /* JADX WARN: Code restructure failed: missing block: B:76:0x0133, code lost:
        if (r9 != r7) goto L69;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int formatDurationLocked(long duration, int fieldLen) {
        char prefix;
        int days;
        int hours;
        int seconds;
        int minutes;
        int start;
        int myLen;
        long duration2 = duration;
        if (sFormatStr.length < fieldLen) {
            sFormatStr = new char[fieldLen];
        }
        char[] formatStr = sFormatStr;
        if (duration2 == 0) {
            int fieldLen2 = fieldLen - 1;
            while (0 < fieldLen2) {
                formatStr[0] = ' ';
            }
            formatStr[0] = '0';
            return 0 + 1;
        }
        if (duration2 > 0) {
            prefix = '+';
        } else {
            duration2 = -duration2;
            prefix = '-';
        }
        int millis = (int) (duration2 % 1000);
        int seconds2 = (int) Math.floor(duration2 / 1000);
        if (seconds2 <= SECONDS_PER_DAY) {
            days = 0;
        } else {
            int days2 = seconds2 / SECONDS_PER_DAY;
            seconds2 -= SECONDS_PER_DAY * days2;
            days = days2;
        }
        if (seconds2 <= SECONDS_PER_HOUR) {
            hours = 0;
        } else {
            int hours2 = seconds2 / SECONDS_PER_HOUR;
            seconds2 -= hours2 * SECONDS_PER_HOUR;
            hours = hours2;
        }
        if (seconds2 <= 60) {
            seconds = seconds2;
            minutes = 0;
        } else {
            int minutes2 = seconds2 / 60;
            seconds = seconds2 - (minutes2 * 60);
            minutes = minutes2;
        }
        int pos = 0;
        int pos2 = 3;
        if (fieldLen != 0) {
            int myLen2 = accumField(days, 1, false, 0);
            int myLen3 = myLen2 + accumField(hours, 1, myLen2 > 0, 2);
            int myLen4 = myLen3 + accumField(minutes, 1, myLen3 > 0, 2);
            for (int myLen5 = myLen + accumField(millis, 2, true, myLen4 + accumField(seconds, 1, myLen4 > 0, 2) > 0 ? 3 : 0) + 1; myLen5 < fieldLen; myLen5++) {
                formatStr[pos] = ' ';
                pos++;
            }
        }
        formatStr[pos] = prefix;
        int pos3 = pos + 1;
        boolean zeropad = fieldLen != 0;
        int pos4 = printField(formatStr, days, 'd', pos3, false, 0);
        int pos5 = printField(formatStr, hours, 'h', pos4, pos4 != pos3, zeropad ? 2 : 0);
        int pos6 = printField(formatStr, minutes, 'm', pos5, pos5 != pos3, zeropad ? 2 : 0);
        int pos7 = printField(formatStr, seconds, 's', pos6, pos6 != pos3, zeropad ? 2 : 0);
        if (zeropad) {
            start = pos3;
        } else {
            start = pos3;
        }
        pos2 = 0;
        int pos8 = printField(formatStr, millis, 'm', pos7, true, pos2);
        formatStr[pos8] = 's';
        return pos8 + 1;
    }

    public static void formatDuration(long duration, StringBuilder builder) {
        synchronized (sFormatSync) {
            int len = formatDurationLocked(duration, 0);
            builder.append(sFormatStr, 0, len);
        }
    }

    public static void formatDuration(long duration, PrintWriter pw, int fieldLen) {
        synchronized (sFormatSync) {
            int len = formatDurationLocked(duration, fieldLen);
            pw.print(new String(sFormatStr, 0, len));
        }
    }

    public static void formatDuration(long duration, PrintWriter pw) {
        formatDuration(duration, pw, 0);
    }

    public static void formatDuration(long time, long now, PrintWriter pw) {
        if (time == 0) {
            pw.print("--");
        } else {
            formatDuration(time - now, pw, 0);
        }
    }

    private TimeUtils() {
    }
}
