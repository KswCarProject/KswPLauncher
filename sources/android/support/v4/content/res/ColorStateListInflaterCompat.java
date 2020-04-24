package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ColorStateListInflaterCompat {
    private static final int DEFAULT_COLOR = -65536;

    private ColorStateListInflaterCompat() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0012  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0017  */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.res.ColorStateList createFromXml(@android.support.annotation.NonNull android.content.res.Resources r4, @android.support.annotation.NonNull org.xmlpull.v1.XmlPullParser r5, @android.support.annotation.Nullable android.content.res.Resources.Theme r6) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r5)
        L_0x0004:
            int r1 = r5.next()
            r2 = r1
            r3 = 2
            if (r1 == r3) goto L_0x0010
            r1 = 1
            if (r2 == r1) goto L_0x0010
            goto L_0x0004
        L_0x0010:
            if (r2 != r3) goto L_0x0017
            android.content.res.ColorStateList r1 = createFromXmlInner(r4, r5, r0, r6)
            return r1
        L_0x0017:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r3 = "No start tag found"
            r1.<init>(r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.ColorStateListInflaterCompat.createFromXml(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.content.res.Resources$Theme):android.content.res.ColorStateList");
    }

    @NonNull
    public static ColorStateList createFromXmlInner(@NonNull Resources r, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = parser.getName();
        if (name.equals("selector")) {
            return inflate(r, parser, attrs, theme);
        }
        throw new XmlPullParserException(parser.getPositionDescription() + ": invalid color state list tag " + name);
    }

    /* JADX WARNING: type inference failed for: r15v5, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.res.ColorStateList inflate(@android.support.annotation.NonNull android.content.res.Resources r21, @android.support.annotation.NonNull org.xmlpull.v1.XmlPullParser r22, @android.support.annotation.NonNull android.util.AttributeSet r23, @android.support.annotation.Nullable android.content.res.Resources.Theme r24) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r0 = r23
            int r1 = r22.getDepth()
            r2 = 1
            int r1 = r1 + r2
            r3 = -65536(0xffffffffffff0000, float:NaN)
            r4 = 20
            int[][] r4 = new int[r4][]
            int r5 = r4.length
            int[] r5 = new int[r5]
            r7 = r3
            r3 = 0
        L_0x0013:
            int r8 = r22.next()
            r9 = r8
            if (r8 == r2) goto L_0x00f2
            int r8 = r22.getDepth()
            r10 = r8
            if (r8 >= r1) goto L_0x002f
            r8 = 3
            if (r9 == r8) goto L_0x0025
            goto L_0x002f
        L_0x0025:
            r11 = r21
            r12 = r24
            r16 = r1
            r18 = r7
            goto L_0x00fa
        L_0x002f:
            r8 = 2
            if (r9 != r8) goto L_0x00e3
            if (r10 > r1) goto L_0x00e3
            java.lang.String r8 = r22.getName()
            java.lang.String r11 = "item"
            boolean r8 = r8.equals(r11)
            if (r8 != 0) goto L_0x004b
            r11 = r21
            r12 = r24
            r16 = r1
            r18 = r7
            goto L_0x00eb
        L_0x004b:
            int[] r8 = android.support.compat.R.styleable.ColorStateListItem
            r11 = r21
            r12 = r24
            android.content.res.TypedArray r8 = obtainAttributes(r11, r12, r0, r8)
            int r13 = android.support.compat.R.styleable.ColorStateListItem_android_color
            r14 = -65281(0xffffffffffff00ff, float:NaN)
            int r13 = r8.getColor(r13, r14)
            r14 = 1065353216(0x3f800000, float:1.0)
            int r15 = android.support.compat.R.styleable.ColorStateListItem_android_alpha
            boolean r15 = r8.hasValue(r15)
            if (r15 == 0) goto L_0x006f
            int r15 = android.support.compat.R.styleable.ColorStateListItem_android_alpha
            float r14 = r8.getFloat(r15, r14)
            goto L_0x007d
        L_0x006f:
            int r15 = android.support.compat.R.styleable.ColorStateListItem_alpha
            boolean r15 = r8.hasValue(r15)
            if (r15 == 0) goto L_0x007d
            int r15 = android.support.compat.R.styleable.ColorStateListItem_alpha
            float r14 = r8.getFloat(r15, r14)
        L_0x007d:
            r8.recycle()
            r15 = 0
            int r2 = r23.getAttributeCount()
            int[] r6 = new int[r2]
            r16 = r1
            r1 = r15
            r15 = 0
        L_0x008b:
            if (r15 >= r2) goto L_0x00bc
            r17 = r2
            int r2 = r0.getAttributeNameResource(r15)
            r18 = r7
            r7 = 16843173(0x10101a5, float:2.3694738E-38)
            if (r2 == r7) goto L_0x00b5
            r7 = 16843551(0x101031f, float:2.3695797E-38)
            if (r2 == r7) goto L_0x00b5
            int r7 = android.support.compat.R.attr.alpha
            if (r2 == r7) goto L_0x00b5
            int r7 = r1 + 1
            r19 = r7
            r7 = 0
            boolean r20 = r0.getAttributeBooleanValue(r15, r7)
            if (r20 == 0) goto L_0x00b0
            r7 = r2
            goto L_0x00b1
        L_0x00b0:
            int r7 = -r2
        L_0x00b1:
            r6[r1] = r7
            r1 = r19
        L_0x00b5:
            int r15 = r15 + 1
            r2 = r17
            r7 = r18
            goto L_0x008b
        L_0x00bc:
            r17 = r2
            r18 = r7
            int[] r2 = android.util.StateSet.trimStateSet(r6, r1)
            int r6 = modulateColorAlpha(r13, r14)
            if (r3 == 0) goto L_0x00d1
            int r7 = r2.length
            if (r7 != 0) goto L_0x00ce
            goto L_0x00d1
        L_0x00ce:
            r7 = r18
            goto L_0x00d2
        L_0x00d1:
            r7 = r6
        L_0x00d2:
            int[] r5 = android.support.v4.content.res.GrowingArrayUtils.append((int[]) r5, (int) r3, (int) r6)
            java.lang.Object[] r15 = android.support.v4.content.res.GrowingArrayUtils.append((T[]) r4, (int) r3, r2)
            r4 = r15
            int[][] r4 = (int[][]) r4
            int r3 = r3 + 1
            r1 = r16
            goto L_0x00ef
        L_0x00e3:
            r11 = r21
            r12 = r24
            r16 = r1
            r18 = r7
        L_0x00eb:
            r1 = r16
            r7 = r18
        L_0x00ef:
            r2 = 1
            goto L_0x0013
        L_0x00f2:
            r11 = r21
            r12 = r24
            r16 = r1
            r18 = r7
        L_0x00fa:
            int[] r1 = new int[r3]
            int[][] r2 = new int[r3][]
            r6 = 0
            java.lang.System.arraycopy(r5, r6, r1, r6, r3)
            java.lang.System.arraycopy(r4, r6, r2, r6, r3)
            android.content.res.ColorStateList r6 = new android.content.res.ColorStateList
            r6.<init>(r2, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.ColorStateListInflaterCompat.inflate(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.content.res.ColorStateList");
    }

    private static TypedArray obtainAttributes(Resources res, Resources.Theme theme, AttributeSet set, int[] attrs) {
        if (theme == null) {
            return res.obtainAttributes(set, attrs);
        }
        return theme.obtainStyledAttributes(set, attrs, 0, 0);
    }

    @ColorInt
    private static int modulateColorAlpha(@ColorInt int color, @FloatRange(from = 0.0d, to = 1.0d) float alphaMod) {
        return (16777215 & color) | (Math.round(((float) Color.alpha(color)) * alphaMod) << 24);
    }
}
