package kotlin.text;

import kotlin.Metadata;

@Metadata(m25d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, m24d2 = {"Lkotlin/text/ScreenFloatValueRegEx;", "", "()V", "value", "Lkotlin/text/Regex;", "kotlin-stdlib"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
/* renamed from: kotlin.text.ScreenFloatValueRegEx */
/* loaded from: classes.dex */
final class StringNumberConversionsJVM {
    public static final StringNumberConversionsJVM INSTANCE = new StringNumberConversionsJVM();
    public static final Regex value;

    private StringNumberConversionsJVM() {
    }

    static {
        StringNumberConversionsJVM stringNumberConversionsJVM = INSTANCE;
        String Exp = "[eE][+-]?(\\p{Digit}+)";
        String HexString = "(0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+))";
        String Number = "((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)(" + Exp + ")?)|(\\.((\\p{Digit}+))(" + Exp + ")?)|((" + HexString + ")[pP][+-]?(\\p{Digit}+))";
        String fpRegex = "[\\x00-\\x20]*[+-]?(NaN|Infinity|((" + Number + ")[fFdD]?))[\\x00-\\x20]*";
        value = new Regex(fpRegex);
    }
}
