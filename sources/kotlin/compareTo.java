package kotlin;

import com.ibm.icu.text.PluralRules;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m25d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000f\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0087\f\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0006"}, m24d2 = {"compareTo", "", "T", "", PluralRules.KEYWORD_OTHER, "(Ljava/lang/Comparable;Ljava/lang/Object;)I", "kotlin-stdlib"}, m23k = 2, m22mv = {1, 6, 0}, m20xi = 48)
/* renamed from: kotlin.CompareToKt */
/* loaded from: classes.dex */
public final class compareTo {
    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> int compareTo(Comparable<? super T> comparable, T t) {
        Intrinsics.checkNotNullParameter(comparable, "<this>");
        return comparable.compareTo(t);
    }
}
