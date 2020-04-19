package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ComplexColorCompat {
    private static final String LOG_TAG = "ComplexColorCompat";
    private int mColor;
    private final ColorStateList mColorStateList;
    private final Shader mShader;

    private ComplexColorCompat(Shader shader, ColorStateList colorStateList, @ColorInt int color) {
        this.mShader = shader;
        this.mColorStateList = colorStateList;
        this.mColor = color;
    }

    static ComplexColorCompat from(@NonNull Shader shader) {
        return new ComplexColorCompat(shader, (ColorStateList) null, 0);
    }

    static ComplexColorCompat from(@NonNull ColorStateList colorStateList) {
        return new ComplexColorCompat((Shader) null, colorStateList, colorStateList.getDefaultColor());
    }

    static ComplexColorCompat from(@ColorInt int color) {
        return new ComplexColorCompat((Shader) null, (ColorStateList) null, color);
    }

    @Nullable
    public Shader getShader() {
        return this.mShader;
    }

    @ColorInt
    public int getColor() {
        return this.mColor;
    }

    public void setColor(@ColorInt int color) {
        this.mColor = color;
    }

    public boolean isGradient() {
        return this.mShader != null;
    }

    public boolean isStateful() {
        return this.mShader == null && this.mColorStateList != null && this.mColorStateList.isStateful();
    }

    public boolean onStateChanged(int[] stateSet) {
        int colorForState;
        if (!isStateful() || (colorForState = this.mColorStateList.getColorForState(stateSet, this.mColorStateList.getDefaultColor())) == this.mColor) {
            return false;
        }
        this.mColor = colorForState;
        return true;
    }

    public boolean willDraw() {
        return isGradient() || this.mColor != 0;
    }

    @Nullable
    public static ComplexColorCompat inflate(@NonNull Resources resources, @ColorRes int resId, @Nullable Resources.Theme theme) {
        try {
            return createFromXml(resources, resId, theme);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to inflate ComplexColor.", e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        if (r2.equals("gradient") != false) goto L_0x003e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0068  */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.support.v4.content.res.ComplexColorCompat createFromXml(@android.support.annotation.NonNull android.content.res.Resources r8, @android.support.annotation.ColorRes int r9, @android.support.annotation.Nullable android.content.res.Resources.Theme r10) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
            android.content.res.XmlResourceParser r0 = r8.getXml(r9)
            android.util.AttributeSet r1 = android.util.Xml.asAttributeSet(r0)
        L_0x0008:
            int r2 = r0.next()
            r3 = r2
            r4 = 1
            r5 = 2
            if (r2 == r5) goto L_0x0014
            if (r3 == r4) goto L_0x0014
            goto L_0x0008
        L_0x0014:
            if (r3 != r5) goto L_0x0071
            java.lang.String r2 = r0.getName()
            r5 = -1
            int r6 = r2.hashCode()
            r7 = 89650992(0x557f730, float:1.01546526E-35)
            if (r6 == r7) goto L_0x0034
            r4 = 1191572447(0x4705f3df, float:34291.87)
            if (r6 == r4) goto L_0x002a
            goto L_0x003d
        L_0x002a:
            java.lang.String r4 = "selector"
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L_0x003d
            r4 = 0
            goto L_0x003e
        L_0x0034:
            java.lang.String r6 = "gradient"
            boolean r6 = r2.equals(r6)
            if (r6 == 0) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r4 = r5
        L_0x003e:
            switch(r4) {
                case 0: goto L_0x0068;
                case 1: goto L_0x005f;
                default: goto L_0x0041;
            }
        L_0x0041:
            org.xmlpull.v1.XmlPullParserException r4 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r0.getPositionDescription()
            r5.append(r6)
            java.lang.String r6 = ": unsupported complex color tag "
            r5.append(r6)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x005f:
            android.graphics.Shader r4 = android.support.v4.content.res.GradientColorInflaterCompat.createFromXmlInner(r8, r0, r1, r10)
            android.support.v4.content.res.ComplexColorCompat r4 = from((android.graphics.Shader) r4)
            return r4
        L_0x0068:
            android.content.res.ColorStateList r4 = android.support.v4.content.res.ColorStateListInflaterCompat.createFromXmlInner(r8, r0, r1, r10)
            android.support.v4.content.res.ComplexColorCompat r4 = from((android.content.res.ColorStateList) r4)
            return r4
        L_0x0071:
            org.xmlpull.v1.XmlPullParserException r2 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r4 = "No start tag found"
            r2.<init>(r4)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.ComplexColorCompat.createFromXml(android.content.res.Resources, int, android.content.res.Resources$Theme):android.support.v4.content.res.ComplexColorCompat");
    }
}
