package kotlin.time;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b*\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u0005H\u0002ø\u0001\u0000¢\u0006\u0002\u0010&\u001a\u0018\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u0010\u0010/\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u0001H\u0002\u001a\u0010\u00100\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\u0001H\u0002\u001a \u00101\u001a\u00020\u00072\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002ø\u0001\u0000¢\u0006\u0002\u00106\u001a\u0010\u00107\u001a\u00020\u00012\u0006\u00102\u001a\u000203H\u0002\u001a)\u00108\u001a\u00020\u0005*\u0002032\u0006\u00109\u001a\u00020\u00052\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002050;H\b\u001a)\u0010=\u001a\u000203*\u0002032\u0006\u00109\u001a\u00020\u00052\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u0002050;H\b\u001a\u001f\u0010>\u001a\u00020\u0007*\u00020\b2\u0006\u0010?\u001a\u00020\u0007H\nø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a\u001f\u0010>\u001a\u00020\u0007*\u00020\u00052\u0006\u0010?\u001a\u00020\u0007H\nø\u0001\u0000¢\u0006\u0004\bB\u0010C\u001a\u001c\u0010D\u001a\u00020\u0007*\u00020\b2\u0006\u0010E\u001a\u00020FH\u0007ø\u0001\u0000¢\u0006\u0002\u0010G\u001a\u001c\u0010D\u001a\u00020\u0007*\u00020\u00052\u0006\u0010E\u001a\u00020FH\u0007ø\u0001\u0000¢\u0006\u0002\u0010H\u001a\u001c\u0010D\u001a\u00020\u0007*\u00020\u00012\u0006\u0010E\u001a\u00020FH\u0007ø\u0001\u0000¢\u0006\u0002\u0010I\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000\"!\u0010\u0006\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\r\u001a\u0004\b\u000b\u0010\u000e\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\u000f\u001a\u0004\b\u000b\u0010\u0010\"!\u0010\u0011\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\n\u001a\u0004\b\u0013\u0010\f\"!\u0010\u0011\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000e\"!\u0010\u0011\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0010\"!\u0010\u0014\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\f\"!\u0010\u0014\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\r\u001a\u0004\b\u0016\u0010\u000e\"!\u0010\u0014\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0010\"!\u0010\u0017\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\n\u001a\u0004\b\u0019\u0010\f\"!\u0010\u0017\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\r\u001a\u0004\b\u0019\u0010\u000e\"!\u0010\u0017\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0019\u0010\u0010\"!\u0010\u001a\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\n\u001a\u0004\b\u001c\u0010\f\"!\u0010\u001a\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\r\u001a\u0004\b\u001c\u0010\u000e\"!\u0010\u001a\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u001c\u0010\u0010\"!\u0010\u001d\u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\n\u001a\u0004\b\u001f\u0010\f\"!\u0010\u001d\u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\r\u001a\u0004\b\u001f\u0010\u000e\"!\u0010\u001d\u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001f\u0010\u0010\"!\u0010 \u001a\u00020\u0007*\u00020\b8FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\n\u001a\u0004\b\"\u0010\f\"!\u0010 \u001a\u00020\u0007*\u00020\u00058FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\r\u001a\u0004\b\"\u0010\u000e\"!\u0010 \u001a\u00020\u0007*\u00020\u00018FX\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b!\u0010\u000f\u001a\u0004\b\"\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006J"}, d2 = {"MAX_MILLIS", "", "MAX_NANOS", "MAX_NANOS_IN_MILLIS", "NANOS_IN_MILLIS", "", "days", "Lkotlin/time/Duration;", "", "getDays$annotations", "(D)V", "getDays", "(D)J", "(I)V", "(I)J", "(J)V", "(J)J", "hours", "getHours$annotations", "getHours", "microseconds", "getMicroseconds$annotations", "getMicroseconds", "milliseconds", "getMilliseconds$annotations", "getMilliseconds", "minutes", "getMinutes$annotations", "getMinutes", "nanoseconds", "getNanoseconds$annotations", "getNanoseconds", "seconds", "getSeconds$annotations", "getSeconds", "durationOf", "normalValue", "unitDiscriminator", "(JI)J", "durationOfMillis", "normalMillis", "durationOfMillisNormalized", "millis", "durationOfNanos", "normalNanos", "durationOfNanosNormalized", "nanos", "millisToNanos", "nanosToMillis", "parseDuration", "value", "", "strictIso", "", "(Ljava/lang/String;Z)J", "parseOverLongIsoComponent", "skipWhile", "startIndex", "predicate", "Lkotlin/Function1;", "", "substringWhile", "times", "duration", "times-kIfJnKk", "(DJ)J", "times-mvk6XK0", "(IJ)J", "toDuration", "unit", "Lkotlin/time/DurationUnit;", "(DLkotlin/time/DurationUnit;)J", "(ILkotlin/time/DurationUnit;)J", "(JLkotlin/time/DurationUnit;)J", "kotlin-stdlib"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* compiled from: Duration.kt */
public final class DurationKt {
    public static final long MAX_MILLIS = 4611686018427387903L;
    public static final long MAX_NANOS = 4611686018426999999L;
    private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
    public static final int NANOS_IN_MILLIS = 1000000;

    @Deprecated(message = "Use 'Double.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getDays$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getDays$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.days", imports = {"kotlin.time.Duration.Companion.days"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getDays$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getHours$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getHours$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getHours$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMicroseconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMicroseconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMicroseconds$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMilliseconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMilliseconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMilliseconds$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMinutes$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMinutes$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getMinutes$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getNanoseconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getNanoseconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getNanoseconds$annotations(long j) {
    }

    @Deprecated(message = "Use 'Double.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getSeconds$annotations(double d) {
    }

    @Deprecated(message = "Use 'Int.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getSeconds$annotations(int i) {
    }

    @Deprecated(message = "Use 'Long.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "this.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static /* synthetic */ void getSeconds$annotations(long j) {
    }

    public static final long toDuration(int $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (unit.compareTo(DurationUnit.SECONDS) <= 0) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow((long) $this$toDuration, unit, DurationUnit.NANOSECONDS));
        }
        return toDuration((long) $this$toDuration, unit);
    }

    public static final long toDuration(long $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        long maxNsInUnit = DurationUnitKt.convertDurationUnitOverflow(MAX_NANOS, DurationUnit.NANOSECONDS, unit);
        boolean z = false;
        if ((-maxNsInUnit) <= $this$toDuration && $this$toDuration <= maxNsInUnit) {
            z = true;
        }
        if (z) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow($this$toDuration, unit, DurationUnit.NANOSECONDS));
        }
        return durationOfMillis(RangesKt.coerceIn(DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS), -4611686018427387903L, (long) MAX_MILLIS));
    }

    public static final long toDuration(double $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        double valueInNs = DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.NANOSECONDS);
        boolean z = true;
        if (!Double.isNaN(valueInNs)) {
            long nanos = MathKt.roundToLong(valueInNs);
            if (-4611686018426999999L > nanos || nanos >= 4611686018427000000L) {
                z = false;
            }
            if (z) {
                return durationOfNanos(nanos);
            }
            return durationOfMillisNormalized(MathKt.roundToLong(DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS)));
        }
        throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
    }

    public static final long getNanoseconds(int $this$nanoseconds) {
        return toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
    }

    public static final long getNanoseconds(long $this$nanoseconds) {
        return toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
    }

    public static final long getNanoseconds(double $this$nanoseconds) {
        return toDuration($this$nanoseconds, DurationUnit.NANOSECONDS);
    }

    public static final long getMicroseconds(int $this$microseconds) {
        return toDuration($this$microseconds, DurationUnit.MICROSECONDS);
    }

    public static final long getMicroseconds(long $this$microseconds) {
        return toDuration($this$microseconds, DurationUnit.MICROSECONDS);
    }

    public static final long getMicroseconds(double $this$microseconds) {
        return toDuration($this$microseconds, DurationUnit.MICROSECONDS);
    }

    public static final long getMilliseconds(int $this$milliseconds) {
        return toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
    }

    public static final long getMilliseconds(long $this$milliseconds) {
        return toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
    }

    public static final long getMilliseconds(double $this$milliseconds) {
        return toDuration($this$milliseconds, DurationUnit.MILLISECONDS);
    }

    public static final long getSeconds(int $this$seconds) {
        return toDuration($this$seconds, DurationUnit.SECONDS);
    }

    public static final long getSeconds(long $this$seconds) {
        return toDuration($this$seconds, DurationUnit.SECONDS);
    }

    public static final long getSeconds(double $this$seconds) {
        return toDuration($this$seconds, DurationUnit.SECONDS);
    }

    public static final long getMinutes(int $this$minutes) {
        return toDuration($this$minutes, DurationUnit.MINUTES);
    }

    public static final long getMinutes(long $this$minutes) {
        return toDuration($this$minutes, DurationUnit.MINUTES);
    }

    public static final long getMinutes(double $this$minutes) {
        return toDuration($this$minutes, DurationUnit.MINUTES);
    }

    public static final long getHours(int $this$hours) {
        return toDuration($this$hours, DurationUnit.HOURS);
    }

    public static final long getHours(long $this$hours) {
        return toDuration($this$hours, DurationUnit.HOURS);
    }

    public static final long getHours(double $this$hours) {
        return toDuration($this$hours, DurationUnit.HOURS);
    }

    public static final long getDays(int $this$days) {
        return toDuration($this$days, DurationUnit.DAYS);
    }

    public static final long getDays(long $this$days) {
        return toDuration($this$days, DurationUnit.DAYS);
    }

    public static final long getDays(double $this$days) {
        return toDuration($this$days, DurationUnit.DAYS);
    }

    /* renamed from: times-mvk6XK0  reason: not valid java name */
    private static final long m1415timesmvk6XK0(int $this$times_u2dmvk6XK0, long duration) {
        return Duration.m1327timesUwyO8pc(duration, $this$times_u2dmvk6XK0);
    }

    /* renamed from: times-kIfJnKk  reason: not valid java name */
    private static final long m1414timeskIfJnKk(double $this$times_u2dkIfJnKk, long duration) {
        return Duration.m1326timesUwyO8pc(duration, $this$times_u2dkIfJnKk);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0274  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0276  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0279 A[LOOP:4: B:116:0x024e->B:131:0x0279, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x00e4 A[EDGE_INSN: B:187:0x00e4->B:54:0x00e4 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x0282 A[EDGE_INSN: B:200:0x0282->B:133:0x0282 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c9 A[LOOP:1: B:36:0x0084->B:52:0x00c9, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final long parseDuration(java.lang.String r32, boolean r33) {
        /*
            r6 = r32
            int r7 = r32.length()
            if (r7 == 0) goto L_0x036c
            r0 = 0
            kotlin.time.Duration$Companion r1 = kotlin.time.Duration.Companion
            long r8 = r1.m1391getZEROUwyO8pc()
            java.lang.String r10 = "Infinity"
            char r1 = r6.charAt(r0)
            r2 = 43
            r3 = 45
            r11 = 0
            if (r1 != r2) goto L_0x001e
        L_0x001c:
            r1 = 1
            goto L_0x0022
        L_0x001e:
            if (r1 != r3) goto L_0x0021
            goto L_0x001c
        L_0x0021:
            r1 = r11
        L_0x0022:
            if (r1 == 0) goto L_0x0026
            int r0 = r0 + 1
        L_0x0026:
            r13 = r0
            if (r13 <= 0) goto L_0x002b
            r0 = 1
            goto L_0x002c
        L_0x002b:
            r0 = r11
        L_0x002c:
            r14 = r0
            r0 = 0
            r1 = 2
            if (r14 == 0) goto L_0x003c
            r2 = r6
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            boolean r2 = kotlin.text.StringsKt.startsWith$default((java.lang.CharSequence) r2, (char) r3, (boolean) r11, (int) r1, (java.lang.Object) r0)
            if (r2 == 0) goto L_0x003c
            r2 = 1
            goto L_0x003d
        L_0x003c:
            r2 = r11
        L_0x003d:
            r15 = r2
            java.lang.String r5 = "No components"
            if (r7 <= r13) goto L_0x0365
            char r2 = r6.charAt(r13)
            r3 = 80
            java.lang.String r4 = "this as java.lang.String).substring(startIndex)"
            java.lang.String r12 = "Unexpected order of duration components"
            r16 = r5
            java.lang.String r1 = "this as java.lang.String…ing(startIndex, endIndex)"
            if (r2 != r3) goto L_0x01c1
            int r13 = r13 + 1
            if (r13 == r7) goto L_0x01bb
            java.lang.String r2 = "+-."
            r3 = 0
            r16 = 0
            r19 = r16
        L_0x0060:
            if (r13 >= r7) goto L_0x01af
            char r11 = r6.charAt(r13)
            r5 = 84
            if (r11 != r5) goto L_0x0079
            if (r3 != 0) goto L_0x0073
            int r13 = r13 + 1
            if (r13 == r7) goto L_0x0073
            r3 = 1
            r11 = 0
            goto L_0x0060
        L_0x0073:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x0079:
            r5 = r32
            r11 = 0
            r16 = r5
            r21 = 0
            r22 = r13
            r0 = r22
        L_0x0084:
            r22 = r11
            int r11 = r16.length()
            if (r0 >= r11) goto L_0x00d8
            r23 = r15
            r11 = r16
            char r15 = r11.charAt(r0)
            r16 = 0
            r24 = r11
            r11 = 48
            if (r11 > r15) goto L_0x00a3
            r11 = 58
            if (r15 >= r11) goto L_0x00a5
            r20 = 1
            goto L_0x00a7
        L_0x00a3:
            r11 = 58
        L_0x00a5:
            r20 = 0
        L_0x00a7:
            if (r20 != 0) goto L_0x00be
            r11 = r2
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            r25 = r2
            r18 = r10
            r17 = r14
            r2 = 0
            r10 = 0
            r14 = 2
            boolean r11 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r11, (char) r15, (boolean) r10, (int) r14, (java.lang.Object) r2)
            if (r11 == 0) goto L_0x00bc
            goto L_0x00c6
        L_0x00bc:
            r10 = 0
            goto L_0x00c7
        L_0x00be:
            r25 = r2
            r18 = r10
            r17 = r14
            r2 = 0
            r14 = 2
        L_0x00c6:
            r10 = 1
        L_0x00c7:
            if (r10 == 0) goto L_0x00e4
            int r0 = r0 + 1
            r14 = r17
            r10 = r18
            r11 = r22
            r15 = r23
            r16 = r24
            r2 = r25
            goto L_0x0084
        L_0x00d8:
            r25 = r2
            r18 = r10
            r17 = r14
            r23 = r15
            r24 = r16
            r2 = 0
            r14 = 2
        L_0x00e4:
            java.lang.String r0 = r5.substring(r13, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r5 = r0
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            int r5 = r5.length()
            if (r5 != 0) goto L_0x00f8
            r5 = 1
            goto L_0x00f9
        L_0x00f8:
            r5 = 0
        L_0x00f9:
            if (r5 != 0) goto L_0x01a9
            int r5 = r0.length()
            int r13 = r13 + r5
            r5 = r6
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            if (r13 < 0) goto L_0x018c
            int r10 = kotlin.text.StringsKt.getLastIndex(r5)
            if (r13 > r10) goto L_0x018c
            char r5 = r5.charAt(r13)
            int r13 = r13 + 1
            kotlin.time.DurationUnit r10 = kotlin.time.DurationUnitKt.durationUnitByIsoChar(r5, r3)
            r11 = r19
            if (r11 == 0) goto L_0x0129
            r15 = r10
            java.lang.Enum r15 = (java.lang.Enum) r15
            int r15 = r11.compareTo(r15)
            if (r15 <= 0) goto L_0x0123
            goto L_0x0129
        L_0x0123:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r12)
            throw r1
        L_0x0129:
            r19 = r10
            r26 = r0
            java.lang.CharSequence r26 = (java.lang.CharSequence) r26
            r27 = 46
            r28 = 0
            r29 = 0
            r30 = 6
            r31 = 0
            int r11 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r26, (char) r27, (int) r28, (boolean) r29, (int) r30, (java.lang.Object) r31)
            kotlin.time.DurationUnit r15 = kotlin.time.DurationUnit.SECONDS
            if (r10 != r15) goto L_0x0175
            if (r11 <= 0) goto L_0x0175
            r15 = 0
            java.lang.String r2 = r0.substring(r15, r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
            long r14 = parseOverLongIsoComponent(r2)
            long r14 = toDuration((long) r14, (kotlin.time.DurationUnit) r10)
            long r8 = kotlin.time.Duration.m1325plusLRDsOJo(r8, r14)
            java.lang.String r14 = r0.substring(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r4)
            double r14 = java.lang.Double.parseDouble(r14)
            long r14 = toDuration((double) r14, (kotlin.time.DurationUnit) r10)
            long r8 = kotlin.time.Duration.m1325plusLRDsOJo(r8, r14)
            r14 = r17
            r10 = r18
            r15 = r23
            r2 = r25
            r11 = 0
            goto L_0x0060
        L_0x0175:
            long r14 = parseOverLongIsoComponent(r0)
            long r14 = toDuration((long) r14, (kotlin.time.DurationUnit) r10)
            long r8 = kotlin.time.Duration.m1325plusLRDsOJo(r8, r14)
            r14 = r17
            r10 = r18
            r15 = r23
            r2 = r25
            r11 = 0
            goto L_0x0060
        L_0x018c:
            r11 = r19
            r1 = r13
            r2 = 0
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = "Missing unit for value "
            java.lang.StringBuilder r5 = r5.append(r10)
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x01a9:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>()
            throw r1
        L_0x01af:
            r25 = r2
            r18 = r10
            r17 = r14
            r23 = r15
            r11 = r19
            goto L_0x0356
        L_0x01bb:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x01c1:
            r18 = r10
            r17 = r14
            r23 = r15
            if (r33 != 0) goto L_0x035f
            r3 = 0
            int r0 = r7 - r13
            int r2 = r18.length()
            int r5 = java.lang.Math.max(r0, r2)
            r10 = 1
            r11 = 48
            r0 = r32
            r14 = r1
            r1 = r13
            r2 = r18
            r15 = r4
            r4 = r5
            r11 = r16
            r5 = r10
            boolean r0 = kotlin.text.StringsKt.regionMatches((java.lang.String) r0, (int) r1, (java.lang.String) r2, (int) r3, (int) r4, (boolean) r5)
            if (r0 == 0) goto L_0x01f0
            kotlin.time.Duration$Companion r0 = kotlin.time.Duration.Companion
            long r8 = r0.m1389getINFINITEUwyO8pc()
            goto L_0x0356
        L_0x01f0:
            r0 = 0
            r1 = 0
            if (r17 != 0) goto L_0x01f6
            r10 = 1
            goto L_0x01f7
        L_0x01f6:
            r10 = 0
        L_0x01f7:
            r2 = r10
            if (r17 == 0) goto L_0x021b
            char r3 = r6.charAt(r13)
            r4 = 40
            if (r3 != r4) goto L_0x021b
            r3 = r6
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            char r3 = kotlin.text.StringsKt.last(r3)
            r4 = 41
            if (r3 != r4) goto L_0x021b
            r2 = 1
            int r13 = r13 + 1
            int r7 = r7 + -1
            if (r13 == r7) goto L_0x0215
            goto L_0x021b
        L_0x0215:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r11)
            throw r3
        L_0x021b:
            if (r13 >= r7) goto L_0x0352
            if (r1 == 0) goto L_0x0245
            if (r2 == 0) goto L_0x0245
            r3 = r32
            r4 = 0
            r5 = r13
        L_0x0225:
            int r10 = r3.length()
            if (r5 >= r10) goto L_0x0240
            char r10 = r3.charAt(r5)
            r11 = 0
            r16 = r1
            r1 = 32
            if (r10 != r1) goto L_0x0238
            r10 = 1
            goto L_0x0239
        L_0x0238:
            r10 = 0
        L_0x0239:
            if (r10 == 0) goto L_0x0242
            int r5 = r5 + 1
            r1 = r16
            goto L_0x0225
        L_0x0240:
            r16 = r1
        L_0x0242:
            r13 = r5
            goto L_0x0247
        L_0x0245:
            r16 = r1
        L_0x0247:
            r1 = 1
            r3 = r32
            r4 = 0
            r5 = r3
            r10 = 0
            r11 = r13
        L_0x024e:
            r16 = r1
            int r1 = r5.length()
            if (r11 >= r1) goto L_0x0280
            char r1 = r5.charAt(r11)
            r19 = 0
            r20 = r2
            r2 = 48
            if (r2 > r1) goto L_0x0269
            r2 = 58
            if (r1 >= r2) goto L_0x026b
            r21 = 1
            goto L_0x026d
        L_0x0269:
            r2 = 58
        L_0x026b:
            r21 = 0
        L_0x026d:
            if (r21 != 0) goto L_0x0276
            r2 = 46
            if (r1 != r2) goto L_0x0274
            goto L_0x0276
        L_0x0274:
            r1 = 0
            goto L_0x0277
        L_0x0276:
            r1 = 1
        L_0x0277:
            if (r1 == 0) goto L_0x0282
            int r11 = r11 + 1
            r1 = r16
            r2 = r20
            goto L_0x024e
        L_0x0280:
            r20 = r2
        L_0x0282:
            java.lang.String r1 = r3.substring(r13, r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r14)
            r2 = r1
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r2 = r2.length()
            if (r2 != 0) goto L_0x0296
            r10 = 1
            goto L_0x0297
        L_0x0296:
            r10 = 0
        L_0x0297:
            if (r10 != 0) goto L_0x034c
            int r2 = r1.length()
            int r13 = r13 + r2
            r2 = r32
            r3 = 0
            r4 = r2
            r5 = 0
            r10 = r13
        L_0x02a4:
            int r11 = r4.length()
            if (r10 >= r11) goto L_0x02c4
            char r11 = r4.charAt(r10)
            r19 = 0
            r21 = r3
            r3 = 97
            if (r3 > r11) goto L_0x02bc
            r3 = 123(0x7b, float:1.72E-43)
            if (r11 >= r3) goto L_0x02bc
            r3 = 1
            goto L_0x02bd
        L_0x02bc:
            r3 = 0
        L_0x02bd:
            if (r3 == 0) goto L_0x02c6
            int r10 = r10 + 1
            r3 = r21
            goto L_0x02a4
        L_0x02c4:
            r21 = r3
        L_0x02c6:
            java.lang.String r3 = r2.substring(r13, r10)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r14)
            r2 = r3
            int r3 = r2.length()
            int r13 = r13 + r3
            kotlin.time.DurationUnit r3 = kotlin.time.DurationUnitKt.durationUnitByShortName(r2)
            if (r0 == 0) goto L_0x02ea
            r4 = r3
            java.lang.Enum r4 = (java.lang.Enum) r4
            int r4 = r0.compareTo(r4)
            if (r4 <= 0) goto L_0x02e4
            goto L_0x02ea
        L_0x02e4:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r4.<init>(r12)
            throw r4
        L_0x02ea:
            r0 = r3
            r24 = r1
            java.lang.CharSequence r24 = (java.lang.CharSequence) r24
            r25 = 46
            r26 = 0
            r27 = 0
            r28 = 6
            r29 = 0
            int r4 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r24, (char) r25, (int) r26, (boolean) r27, (int) r28, (java.lang.Object) r29)
            if (r4 <= 0) goto L_0x0338
            r5 = 0
            java.lang.String r10 = r1.substring(r5, r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r14)
            long r5 = java.lang.Long.parseLong(r10)
            long r5 = toDuration((long) r5, (kotlin.time.DurationUnit) r3)
            long r5 = kotlin.time.Duration.m1325plusLRDsOJo(r8, r5)
            java.lang.String r8 = r1.substring(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r15)
            double r8 = java.lang.Double.parseDouble(r8)
            long r8 = toDuration((double) r8, (kotlin.time.DurationUnit) r3)
            long r8 = kotlin.time.Duration.m1325plusLRDsOJo(r5, r8)
            if (r13 < r7) goto L_0x0330
            r6 = r32
            r1 = r16
            r2 = r20
            goto L_0x021b
        L_0x0330:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Fractional component must be last"
            r5.<init>(r6)
            throw r5
        L_0x0338:
            long r5 = java.lang.Long.parseLong(r1)
            long r5 = toDuration((long) r5, (kotlin.time.DurationUnit) r3)
            long r8 = kotlin.time.Duration.m1325plusLRDsOJo(r8, r5)
            r6 = r32
            r1 = r16
            r2 = r20
            goto L_0x021b
        L_0x034c:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            r2.<init>()
            throw r2
        L_0x0352:
            r16 = r1
            r20 = r2
        L_0x0356:
            if (r23 == 0) goto L_0x035d
            long r0 = kotlin.time.Duration.m1341unaryMinusUwyO8pc(r8)
            goto L_0x035e
        L_0x035d:
            r0 = r8
        L_0x035e:
            return r0
        L_0x035f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x0365:
            r11 = r5
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r11)
            throw r0
        L_0x036c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The string is empty"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.time.DurationKt.parseDuration(java.lang.String, boolean):long");
    }

    private static final long parseOverLongIsoComponent(String value) {
        Iterable $this$all$iv;
        int it;
        int length = value.length();
        int startIndex = 0;
        if (length > 0 && StringsKt.contains$default((CharSequence) "+-", value.charAt(0), false, 2, (Object) null)) {
            startIndex = 0 + 1;
        }
        if (length - startIndex > 16) {
            Iterable $this$all$iv2 = new IntRange(startIndex, StringsKt.getLastIndex(value));
            if (!($this$all$iv2 instanceof Collection) || !((Collection) $this$all$iv2).isEmpty()) {
                Iterator it2 = $this$all$iv2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        $this$all$iv = 1;
                        break;
                    }
                    char charAt = value.charAt(((IntIterator) it2).nextInt());
                    if ('0' > charAt || charAt >= ':') {
                        it = 0;
                        continue;
                    } else {
                        it = 1;
                        continue;
                    }
                    if (it == 0) {
                        $this$all$iv = null;
                        break;
                    }
                }
            } else {
                $this$all$iv = 1;
            }
            if ($this$all$iv != null) {
                if (value.charAt(0) == '-') {
                    return Long.MIN_VALUE;
                }
                return LongCompanionObject.MAX_VALUE;
            }
        }
        return StringsKt.startsWith$default(value, "+", false, 2, (Object) null) ? Long.parseLong(StringsKt.drop(value, 1)) : Long.parseLong(value);
    }

    private static final String substringWhile(String $this$substringWhile, int startIndex, Function1<? super Character, Boolean> predicate) {
        String $this$skipWhile$iv = $this$substringWhile;
        int i$iv = startIndex;
        while (i$iv < $this$skipWhile$iv.length() && predicate.invoke(Character.valueOf($this$skipWhile$iv.charAt(i$iv))).booleanValue()) {
            i$iv++;
        }
        String $this$skipWhile$iv2 = $this$substringWhile.substring(startIndex, i$iv);
        Intrinsics.checkNotNullExpressionValue($this$skipWhile$iv2, "this as java.lang.String…ing(startIndex, endIndex)");
        return $this$skipWhile$iv2;
    }

    private static final int skipWhile(String $this$skipWhile, int startIndex, Function1<? super Character, Boolean> predicate) {
        int i = startIndex;
        while (i < $this$skipWhile.length() && predicate.invoke(Character.valueOf($this$skipWhile.charAt(i))).booleanValue()) {
            i++;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static final long nanosToMillis(long nanos) {
        return nanos / ((long) NANOS_IN_MILLIS);
    }

    /* access modifiers changed from: private */
    public static final long millisToNanos(long millis) {
        return ((long) NANOS_IN_MILLIS) * millis;
    }

    /* access modifiers changed from: private */
    public static final long durationOfNanos(long normalNanos) {
        return Duration.m1289constructorimpl(normalNanos << 1);
    }

    /* access modifiers changed from: private */
    public static final long durationOfMillis(long normalMillis) {
        return Duration.m1289constructorimpl((normalMillis << 1) + 1);
    }

    /* access modifiers changed from: private */
    public static final long durationOf(long normalValue, int unitDiscriminator) {
        return Duration.m1289constructorimpl((normalValue << 1) + ((long) unitDiscriminator));
    }

    /* access modifiers changed from: private */
    public static final long durationOfNanosNormalized(long nanos) {
        boolean z = false;
        if (-4611686018426999999L <= nanos && nanos < 4611686018427000000L) {
            z = true;
        }
        if (z) {
            return durationOfNanos(nanos);
        }
        return durationOfMillis(nanosToMillis(nanos));
    }

    /* access modifiers changed from: private */
    public static final long durationOfMillisNormalized(long millis) {
        boolean z = false;
        if (-4611686018426L <= millis && millis < 4611686018427L) {
            z = true;
        }
        if (z) {
            return durationOfNanos(millisToNanos(millis));
        }
        return durationOfMillis(RangesKt.coerceIn(millis, -4611686018427387903L, (long) MAX_MILLIS));
    }
}
