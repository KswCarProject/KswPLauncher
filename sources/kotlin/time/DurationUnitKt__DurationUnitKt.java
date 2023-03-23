package kotlin.time;

import com.ibm.icu.text.DateFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0001\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0001¨\u0006\t"}, d2 = {"durationUnitByIsoChar", "Lkotlin/time/DurationUnit;", "isoChar", "", "isTimeComponent", "", "durationUnitByShortName", "shortName", "", "kotlin-stdlib"}, k = 5, mv = {1, 6, 0}, xi = 49, xs = "kotlin/time/DurationUnitKt")
/* compiled from: DurationUnit.kt */
class DurationUnitKt__DurationUnitKt extends DurationUnitKt__DurationUnitJvmKt {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* compiled from: DurationUnit.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DurationUnit.values().length];
            iArr[DurationUnit.NANOSECONDS.ordinal()] = 1;
            iArr[DurationUnit.MICROSECONDS.ordinal()] = 2;
            iArr[DurationUnit.MILLISECONDS.ordinal()] = 3;
            iArr[DurationUnit.SECONDS.ordinal()] = 4;
            iArr[DurationUnit.MINUTES.ordinal()] = 5;
            iArr[DurationUnit.HOURS.ordinal()] = 6;
            iArr[DurationUnit.DAYS.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String shortName(DurationUnit $this$shortName) {
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

    public static final DurationUnit durationUnitByShortName(String shortName) {
        Intrinsics.checkNotNullParameter(shortName, "shortName");
        switch (shortName.hashCode()) {
            case 100:
                if (shortName.equals(DateFormat.DAY)) {
                    return DurationUnit.DAYS;
                }
                break;
            case 104:
                if (shortName.equals("h")) {
                    return DurationUnit.HOURS;
                }
                break;
            case 109:
                if (shortName.equals(DateFormat.MINUTE)) {
                    return DurationUnit.MINUTES;
                }
                break;
            case 115:
                if (shortName.equals(DateFormat.SECOND)) {
                    return DurationUnit.SECONDS;
                }
                break;
            case 3494:
                if (shortName.equals(DateFormat.MINUTE_SECOND)) {
                    return DurationUnit.MILLISECONDS;
                }
                break;
            case 3525:
                if (shortName.equals("ns")) {
                    return DurationUnit.NANOSECONDS;
                }
                break;
            case 3742:
                if (shortName.equals("us")) {
                    return DurationUnit.MICROSECONDS;
                }
                break;
        }
        throw new IllegalArgumentException("Unknown duration unit short name: " + shortName);
    }

    public static final DurationUnit durationUnitByIsoChar(char isoChar, boolean isTimeComponent) {
        if (!isTimeComponent) {
            if (isoChar == 'D') {
                return DurationUnit.DAYS;
            }
            throw new IllegalArgumentException("Invalid or unsupported duration ISO non-time unit: " + isoChar);
        } else if (isoChar == 'H') {
            return DurationUnit.HOURS;
        } else {
            if (isoChar == 'M') {
                return DurationUnit.MINUTES;
            }
            if (isoChar == 'S') {
                return DurationUnit.SECONDS;
            }
            throw new IllegalArgumentException("Invalid duration ISO time unit: " + isoChar);
        }
    }
}
