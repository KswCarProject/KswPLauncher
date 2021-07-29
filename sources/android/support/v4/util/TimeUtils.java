package android.support.v4.util;

import java.io.PrintWriter;

public final class TimeUtils {
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static char[] sFormatStr = new char[24];
    private static final Object sFormatSync = new Object();

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
        if (!always && amt <= 0) {
            return pos;
        }
        int startPos = pos;
        if ((always && zeropad >= 3) || amt > 99) {
            int dig = amt / 100;
            formatStr[pos] = (char) (dig + 48);
            pos++;
            amt -= dig * 100;
        }
        if ((always && zeropad >= 2) || amt > 9 || startPos != pos) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0133, code lost:
        if (r9 != r7) goto L_0x013a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int formatDurationLocked(long r27, int r29) {
        /*
            r0 = r27
            r2 = r29
            char[] r3 = sFormatStr
            int r3 = r3.length
            if (r3 >= r2) goto L_0x000d
            char[] r3 = new char[r2]
            sFormatStr = r3
        L_0x000d:
            char[] r3 = sFormatStr
            r4 = 0
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            r7 = 32
            if (r6 != 0) goto L_0x0026
            r4 = 0
            int r2 = r2 + -1
        L_0x001a:
            if (r4 >= r2) goto L_0x001f
            r3[r4] = r7
            goto L_0x001a
        L_0x001f:
            r5 = 48
            r3[r4] = r5
            int r5 = r4 + 1
            return r5
        L_0x0026:
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x002e
            r4 = 43
            r10 = r4
            goto L_0x0032
        L_0x002e:
            r4 = 45
            long r0 = -r0
            r10 = r4
        L_0x0032:
            r4 = 1000(0x3e8, double:4.94E-321)
            long r8 = r0 % r4
            int r11 = (int) r8
            long r4 = r0 / r4
            double r4 = (double) r4
            double r4 = java.lang.Math.floor(r4)
            int r4 = (int) r4
            r5 = 0
            r6 = 0
            r8 = 0
            r9 = 86400(0x15180, float:1.21072E-40)
            if (r4 <= r9) goto L_0x004d
            int r5 = r4 / r9
            int r9 = r9 * r5
            int r4 = r4 - r9
            r12 = r5
            goto L_0x004e
        L_0x004d:
            r12 = r5
        L_0x004e:
            r5 = 3600(0xe10, float:5.045E-42)
            if (r4 <= r5) goto L_0x0059
            int r6 = r4 / 3600
            int r5 = r6 * 3600
            int r4 = r4 - r5
            r13 = r6
            goto L_0x005a
        L_0x0059:
            r13 = r6
        L_0x005a:
            r5 = 60
            if (r4 <= r5) goto L_0x0066
            int r8 = r4 / 60
            int r5 = r8 * 60
            int r4 = r4 - r5
            r14 = r4
            r15 = r8
            goto L_0x0068
        L_0x0066:
            r14 = r4
            r15 = r8
        L_0x0068:
            r4 = 0
            r16 = 3
            r9 = 2
            r8 = 0
            r6 = 1
            if (r2 == 0) goto L_0x00a5
            int r5 = accumField(r12, r6, r8, r8)
            if (r5 <= 0) goto L_0x0077
            r8 = r6
        L_0x0077:
            int r8 = accumField(r13, r6, r8, r9)
            int r5 = r5 + r8
            if (r5 <= 0) goto L_0x0080
            r8 = r6
            goto L_0x0081
        L_0x0080:
            r8 = 0
        L_0x0081:
            int r8 = accumField(r15, r6, r8, r9)
            int r5 = r5 + r8
            if (r5 <= 0) goto L_0x008a
            r8 = r6
            goto L_0x008b
        L_0x008a:
            r8 = 0
        L_0x008b:
            int r8 = accumField(r14, r6, r8, r9)
            int r5 = r5 + r8
            if (r5 <= 0) goto L_0x0095
            r8 = r16
            goto L_0x0096
        L_0x0095:
            r8 = 0
        L_0x0096:
            int r8 = accumField(r11, r9, r6, r8)
            int r8 = r8 + r6
            int r5 = r5 + r8
        L_0x009c:
            if (r5 >= r2) goto L_0x00a5
            r3[r4] = r7
            int r4 = r4 + 1
            int r5 = r5 + 1
            goto L_0x009c
        L_0x00a5:
            r3[r4] = r10
            int r17 = r4 + 1
            r8 = r17
            if (r2 == 0) goto L_0x00af
            r4 = r6
            goto L_0x00b0
        L_0x00af:
            r4 = 0
        L_0x00b0:
            r18 = r4
            r7 = 100
            r19 = 0
            r20 = 0
            r4 = r3
            r5 = r12
            r21 = r6
            r6 = r7
            r7 = r17
            r22 = r8
            r23 = 0
            r8 = r19
            r19 = r9
            r9 = r20
            int r9 = printField(r4, r5, r6, r7, r8, r9)
            r6 = 104(0x68, float:1.46E-43)
            r8 = r22
            if (r9 == r8) goto L_0x00d6
            r17 = r21
            goto L_0x00d8
        L_0x00d6:
            r17 = r23
        L_0x00d8:
            if (r18 == 0) goto L_0x00dd
            r20 = r19
            goto L_0x00df
        L_0x00dd:
            r20 = r23
        L_0x00df:
            r4 = r3
            r5 = r13
            r7 = r9
            r24 = r8
            r8 = r17
            r17 = r9
            r9 = r20
            int r9 = printField(r4, r5, r6, r7, r8, r9)
            r6 = 109(0x6d, float:1.53E-43)
            r8 = r24
            if (r9 == r8) goto L_0x00f7
            r17 = r21
            goto L_0x00f9
        L_0x00f7:
            r17 = r23
        L_0x00f9:
            if (r18 == 0) goto L_0x00fe
            r20 = r19
            goto L_0x0100
        L_0x00fe:
            r20 = r23
        L_0x0100:
            r4 = r3
            r5 = r15
            r7 = r9
            r25 = r8
            r8 = r17
            r17 = r9
            r9 = r20
            int r9 = printField(r4, r5, r6, r7, r8, r9)
            r6 = 115(0x73, float:1.61E-43)
            r8 = r25
            if (r9 == r8) goto L_0x0116
            goto L_0x0118
        L_0x0116:
            r21 = r23
        L_0x0118:
            if (r18 == 0) goto L_0x011b
            goto L_0x011d
        L_0x011b:
            r19 = r23
        L_0x011d:
            r4 = r3
            r5 = r14
            r7 = r9
            r26 = r8
            r8 = r21
            r17 = r9
            r9 = r19
            int r9 = printField(r4, r5, r6, r7, r8, r9)
            r6 = 109(0x6d, float:1.53E-43)
            r8 = 1
            if (r18 == 0) goto L_0x0136
            r7 = r26
            if (r9 == r7) goto L_0x0138
            goto L_0x013a
        L_0x0136:
            r7 = r26
        L_0x0138:
            r16 = r23
        L_0x013a:
            r4 = r3
            r5 = r11
            r17 = r7
            r7 = r9
            r19 = r9
            r9 = r16
            int r4 = printField(r4, r5, r6, r7, r8, r9)
            r5 = 115(0x73, float:1.61E-43)
            r3[r4] = r5
            int r5 = r4 + 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.TimeUtils.formatDurationLocked(long, int):int");
    }

    public static void formatDuration(long duration, StringBuilder builder) {
        synchronized (sFormatSync) {
            builder.append(sFormatStr, 0, formatDurationLocked(duration, 0));
        }
    }

    public static void formatDuration(long duration, PrintWriter pw, int fieldLen) {
        synchronized (sFormatSync) {
            pw.print(new String(sFormatStr, 0, formatDurationLocked(duration, fieldLen)));
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
