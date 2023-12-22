package kotlin.ranges;

import com.ibm.icu.text.PluralRules;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m25d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0015B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002J\u0013\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\b\u00a8\u0006\u0016"}, m24d2 = {"Lkotlin/ranges/CharRange;", "Lkotlin/ranges/CharProgression;", "Lkotlin/ranges/ClosedRange;", "", "start", "endInclusive", "(CC)V", "getEndInclusive", "()Ljava/lang/Character;", "getStart", "contains", "", "value", "equals", PluralRules.KEYWORD_OTHER, "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
/* renamed from: kotlin.ranges.CharRange */
/* loaded from: classes.dex */
public final class Ranges extends Progressions implements Range<Character> {
    public static final Companion Companion = new Companion(null);
    private static final Ranges EMPTY = new Ranges(1, 0);

    public Ranges(char start, char endInclusive) {
        super(start, endInclusive, 1);
    }

    @Override // kotlin.ranges.Range
    public /* bridge */ /* synthetic */ boolean contains(Character ch) {
        return contains(ch.charValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.ranges.Range
    public Character getStart() {
        return Character.valueOf(getFirst());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.ranges.Range
    public Character getEndInclusive() {
        return Character.valueOf(getLast());
    }

    public boolean contains(char value) {
        return Intrinsics.compare((int) getFirst(), (int) value) <= 0 && Intrinsics.compare((int) value, (int) getLast()) <= 0;
    }

    @Override // kotlin.ranges.Progressions, kotlin.ranges.Range
    public boolean isEmpty() {
        return Intrinsics.compare((int) getFirst(), (int) getLast()) > 0;
    }

    @Override // kotlin.ranges.Progressions
    public boolean equals(Object other) {
        return (other instanceof Ranges) && ((isEmpty() && ((Ranges) other).isEmpty()) || (getFirst() == ((Ranges) other).getFirst() && getLast() == ((Ranges) other).getLast()));
    }

    @Override // kotlin.ranges.Progressions
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (getFirst() * 31) + getLast();
    }

    @Override // kotlin.ranges.Progressions
    public String toString() {
        return getFirst() + ".." + getLast();
    }

    /* compiled from: Ranges.kt */
    @Metadata(m25d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, m24d2 = {"Lkotlin/ranges/CharRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/CharRange;", "getEMPTY", "()Lkotlin/ranges/CharRange;", "kotlin-stdlib"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
    /* renamed from: kotlin.ranges.CharRange$Companion */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Ranges getEMPTY() {
            return Ranges.EMPTY;
        }
    }
}
