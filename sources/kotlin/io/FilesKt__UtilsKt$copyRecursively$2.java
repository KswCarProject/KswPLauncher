package kotlin.io;

import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "f", "Ljava/io/File;", "e", "Ljava/io/IOException;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* compiled from: Utils.kt */
final class FilesKt__UtilsKt$copyRecursively$2 extends Lambda implements Function2<File, IOException, Unit> {
    final /* synthetic */ Function2<File, IOException, OnErrorAction> $onError;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilesKt__UtilsKt$copyRecursively$2(Function2<? super File, ? super IOException, ? extends OnErrorAction> function2) {
        super(2);
        this.$onError = function2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) {
        invoke((File) p1, (IOException) p2);
        return Unit.INSTANCE;
    }

    public final void invoke(File f, IOException e) {
        Intrinsics.checkNotNullParameter(f, "f");
        Intrinsics.checkNotNullParameter(e, "e");
        if (this.$onError.invoke(f, e) == OnErrorAction.TERMINATE) {
            throw new TerminateException(f);
        }
    }
}
