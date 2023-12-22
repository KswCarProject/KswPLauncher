package kotlin.time;

import com.ibm.icu.text.DateFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DurationUnit.kt */
@Metadata(m25d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0001\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0001\u00a8\u0006\t"}, m24d2 = {"durationUnitByIsoChar", "Lkotlin/time/DurationUnit;", "isoChar", "", "isTimeComponent", "", "durationUnitByShortName", "shortName", "", "kotlin-stdlib"}, m23k = 5, m22mv = {1, 6, 0}, m20xi = 49, m19xs = "kotlin/time/DurationUnitKt")
/* loaded from: classes.dex */
class DurationUnitKt__DurationUnitKt extends DurationUnitKt__DurationUnitJvmKt {

    /* compiled from: DurationUnit.kt */
    @Metadata(m23k = 3, m22mv = {1, 6, 0}, m20xi = 48)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DurationUnitJvm.values().length];
            iArr[DurationUnitJvm.NANOSECONDS.ordinal()] = 1;
            iArr[DurationUnitJvm.MICROSECONDS.ordinal()] = 2;
            iArr[DurationUnitJvm.MILLISECONDS.ordinal()] = 3;
            iArr[DurationUnitJvm.SECONDS.ordinal()] = 4;
            iArr[DurationUnitJvm.MINUTES.ordinal()] = 5;
            iArr[DurationUnitJvm.HOURS.ordinal()] = 6;
            iArr[DurationUnitJvm.DAYS.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String shortName(DurationUnitJvm $this$shortName) {
        Intrinsics.checkNotNullParameter($this$shortName, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[$this$shortName.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "us";
            case 3:
                return DateFormat.MINUTE_SECOND;
            case 4:
                return DateFormat.SECOND;
            case 5:
                return DateFormat.MINUTE;
            case 6:
                return "h";
            case 7:
                return DateFormat.DAY;
            default:
                throw new IllegalStateException(("Unknown unit: " + $this$shortName).toString());
        }
    }

    public static final DurationUnitJvm durationUnitByShortName(String shortName) {
        Intrinsics.checkNotNullParameter(shortName, "shortName");
        switch (shortName.hashCode()) {
            case 100:
                if (shortName.equals(DateFormat.DAY)) {
                    return DurationUnitJvm.DAYS;
                }
                break;
            case 104:
                if (shortName.equals("h")) {
                    return DurationUnitJvm.HOURS;
                }
                break;
            case 109:
                if (shortName.equals(DateFormat.MINUTE)) {
                    return DurationUnitJvm.MINUTES;
                }
                break;
            case 115:
                if (shortName.equals(DateFormat.SECOND)) {
                    return DurationUnitJvm.SECONDS;
                }
                break;
            case 3494:
                if (shortName.equals(DateFormat.MINUTE_SECOND)) {
                    return DurationUnitJvm.MILLISECONDS;
                }
                break;
            case 3525:
                if (shortName.equals("ns")) {
                    return DurationUnitJvm.NANOSECONDS;
                }
                break;
            case 3742:
                if (shortName.equals("us")) {
                    return DurationUnitJvm.MICROSECONDS;
                }
                break;
        }
        throw new IllegalArgumentException("Unknown duration unit short name: " + shortName);
    }

    public static final DurationUnitJvm durationUnitByIsoChar(char isoChar, boolean isTimeComponent) {
        if (!isTimeComponent) {
            if (isoChar == 'D') {
                return DurationUnitJvm.DAYS;
            }
            throw new IllegalArgumentException("Invalid or unsupported duration ISO non-time unit: " + isoChar);
        } else if (isoChar == 'H') {
            return DurationUnitJvm.HOURS;
        } else {
            if (isoChar == 'M') {
                return DurationUnitJvm.MINUTES;
            }
            if (isoChar == 'S') {
                return DurationUnitJvm.SECONDS;
            }
            throw new IllegalArgumentException("Invalid duration ISO time unit: " + isoChar);
        }
    }
}
