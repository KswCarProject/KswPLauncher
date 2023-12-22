package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: ClassBasedDeclarationContainer.kt */
@Metadata(m25d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0006"}, m24d2 = {"Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "Lkotlin/reflect/KDeclarationContainer;", "jClass", "Ljava/lang/Class;", "getJClass", "()Ljava/lang/Class;", "kotlin-stdlib"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
/* loaded from: classes.dex */
public interface ClassBasedDeclarationContainer extends KDeclarationContainer {
    Class<?> getJClass();
}
