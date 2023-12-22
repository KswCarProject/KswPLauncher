package kotlin.text;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m25d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\f\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u00a8\u0006\u0003"}, m24d2 = {"titlecaseImpl", "", "", "kotlin-stdlib"}, m23k = 2, m22mv = {1, 6, 0}, m20xi = 48)
/* renamed from: kotlin.text._OneToManyTitlecaseMappingsKt */
/* loaded from: classes.dex */
public final class _OneToManyTitlecaseMappings {
    public static final String titlecaseImpl(char $this$titlecaseImpl) {
        String uppercase = String.valueOf($this$titlecaseImpl).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(uppercase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        if (uppercase.length() > 1) {
            if ($this$titlecaseImpl == '\u0149') {
                return uppercase;
            }
            char charAt = uppercase.charAt(0);
            String substring = uppercase.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            String lowerCase = substring.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            return charAt + lowerCase;
        }
        return String.valueOf(Character.toTitleCase($this$titlecaseImpl));
    }
}
