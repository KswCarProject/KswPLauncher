package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StringBuilderJVM.kt */
@Metadata(m25d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, m24d2 = {"Lkotlin/text/SystemProperties;", "", "()V", "LINE_SEPARATOR", "", "kotlin-stdlib"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
/* loaded from: classes.dex */
final class SystemProperties {
    public static final SystemProperties INSTANCE = new SystemProperties();
    public static final String LINE_SEPARATOR;

    private SystemProperties() {
    }

    static {
        String property = System.getProperty("line.separator");
        Intrinsics.checkNotNull(property);
        LINE_SEPARATOR = property;
    }
}
