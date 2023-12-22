package kotlin.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: Regex.kt */
@Metadata(m25d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, m24d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, m23k = 3, m22mv = {1, 6, 0}, m20xi = 48)
@DebugMetadata(m16c = "kotlin.text.Regex$splitToSequence$1", m15f = "Regex.kt", m14i = {1, 1, 1}, m13l = {276, 284, 288}, m12m = "invokeSuspend", m11n = {"$this$sequence", "matcher", "splitCount"}, m10s = {"L$0", "L$1", "I$0"})
/* loaded from: classes.dex */
final class Regex$splitToSequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ CharSequence $input;
    final /* synthetic */ int $limit;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Regex this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Regex$splitToSequence$1(Regex regex, CharSequence charSequence, int i, Continuation<? super Regex$splitToSequence$1> continuation) {
        super(2, continuation);
        this.this$0 = regex;
        this.$input = charSequence;
        this.$limit = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Regex$splitToSequence$1 regex$splitToSequence$1 = new Regex$splitToSequence$1(this.this$0, this.$input, this.$limit, continuation);
        regex$splitToSequence$1.L$0 = obj;
        return regex$splitToSequence$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super String> sequenceScope, Continuation<? super Unit> continuation) {
        return ((Regex$splitToSequence$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a4  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0070 -> B:18:0x0073). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Regex$splitToSequence$1 regex$splitToSequence$1;
        Pattern pattern;
        Matcher matcher;
        SequenceScope $this$sequence;
        int splitCount;
        int nextStart;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                regex$splitToSequence$1 = this;
                SequenceScope $this$sequence2 = (SequenceScope) regex$splitToSequence$1.L$0;
                pattern = regex$splitToSequence$1.this$0.nativePattern;
                matcher = pattern.matcher(regex$splitToSequence$1.$input);
                if (regex$splitToSequence$1.$limit == 1 || !matcher.find()) {
                    regex$splitToSequence$1.label = 1;
                    return $this$sequence2.yield(regex$splitToSequence$1.$input.toString(), regex$splitToSequence$1) == coroutine_suspended ? coroutine_suspended : Unit.INSTANCE;
                }
                $this$sequence = $this$sequence2;
                splitCount = 0;
                nextStart = 0;
                regex$splitToSequence$1.L$0 = $this$sequence;
                regex$splitToSequence$1.L$1 = matcher;
                regex$splitToSequence$1.I$0 = splitCount;
                regex$splitToSequence$1.label = 2;
                if ($this$sequence.yield(regex$splitToSequence$1.$input.subSequence(nextStart, matcher.start()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                nextStart = matcher.end();
                splitCount++;
                if (splitCount != regex$splitToSequence$1.$limit - 1 || !matcher.find()) {
                    CharSequence charSequence = regex$splitToSequence$1.$input;
                    regex$splitToSequence$1.L$0 = null;
                    regex$splitToSequence$1.L$1 = null;
                    regex$splitToSequence$1.label = 3;
                    return $this$sequence.yield(charSequence.subSequence(nextStart, charSequence.length()).toString(), regex$splitToSequence$1) == coroutine_suspended ? coroutine_suspended : Unit.INSTANCE;
                }
                regex$splitToSequence$1.L$0 = $this$sequence;
                regex$splitToSequence$1.L$1 = matcher;
                regex$splitToSequence$1.I$0 = splitCount;
                regex$splitToSequence$1.label = 2;
                if ($this$sequence.yield(regex$splitToSequence$1.$input.subSequence(nextStart, matcher.start()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                }
                nextStart = matcher.end();
                splitCount++;
                if (splitCount != regex$splitToSequence$1.$limit - 1) {
                }
                CharSequence charSequence2 = regex$splitToSequence$1.$input;
                regex$splitToSequence$1.L$0 = null;
                regex$splitToSequence$1.L$1 = null;
                regex$splitToSequence$1.label = 3;
                if ($this$sequence.yield(charSequence2.subSequence(nextStart, charSequence2.length()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
            case 2:
                regex$splitToSequence$1 = this;
                splitCount = regex$splitToSequence$1.I$0;
                matcher = (Matcher) regex$splitToSequence$1.L$1;
                $this$sequence = (SequenceScope) regex$splitToSequence$1.L$0;
                ResultKt.throwOnFailure($result);
                nextStart = matcher.end();
                splitCount++;
                if (splitCount != regex$splitToSequence$1.$limit - 1) {
                }
                CharSequence charSequence22 = regex$splitToSequence$1.$input;
                regex$splitToSequence$1.L$0 = null;
                regex$splitToSequence$1.L$1 = null;
                regex$splitToSequence$1.label = 3;
                if ($this$sequence.yield(charSequence22.subSequence(nextStart, charSequence22.length()).toString(), regex$splitToSequence$1) == coroutine_suspended) {
                }
                break;
            case 3:
                ResultKt.throwOnFailure($result);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
