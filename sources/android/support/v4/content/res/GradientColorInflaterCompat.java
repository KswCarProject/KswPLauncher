package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.compat.R;
import android.util.AttributeSet;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

final class GradientColorInflaterCompat {
    private static final int TILE_MODE_CLAMP = 0;
    private static final int TILE_MODE_MIRROR = 2;
    private static final int TILE_MODE_REPEAT = 1;

    private GradientColorInflaterCompat() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0012  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.graphics.Shader createFromXml(android.content.res.Resources r4, org.xmlpull.v1.XmlPullParser r5, android.content.res.Resources.Theme r6) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
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
            android.graphics.Shader r1 = createFromXmlInner(r4, r5, r0, r6)
            return r1
        L_0x0017:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r3 = "No start tag found"
            r1.<init>(r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.GradientColorInflaterCompat.createFromXml(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.content.res.Resources$Theme):android.graphics.Shader");
    }

    static Shader createFromXmlInner(Resources resources, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws IOException, XmlPullParserException {
        XmlPullParser xmlPullParser = parser;
        String name = parser.getName();
        if (name.equals("gradient")) {
            Resources.Theme theme2 = theme;
            TypedArray a = TypedArrayUtils.obtainAttributes(resources, theme2, attrs, R.styleable.GradientColor);
            float startX = TypedArrayUtils.getNamedFloat(a, xmlPullParser, "startX", R.styleable.GradientColor_android_startX, 0.0f);
            float startY = TypedArrayUtils.getNamedFloat(a, xmlPullParser, "startY", R.styleable.GradientColor_android_startY, 0.0f);
            float endX = TypedArrayUtils.getNamedFloat(a, xmlPullParser, "endX", R.styleable.GradientColor_android_endX, 0.0f);
            float endY = TypedArrayUtils.getNamedFloat(a, xmlPullParser, "endY", R.styleable.GradientColor_android_endY, 0.0f);
            float centerX = TypedArrayUtils.getNamedFloat(a, xmlPullParser, "centerX", R.styleable.GradientColor_android_centerX, 0.0f);
            float centerY = TypedArrayUtils.getNamedFloat(a, xmlPullParser, "centerY", R.styleable.GradientColor_android_centerY, 0.0f);
            int type = TypedArrayUtils.getNamedInt(a, xmlPullParser, "type", R.styleable.GradientColor_android_type, 0);
            int startColor = TypedArrayUtils.getNamedColor(a, xmlPullParser, "startColor", R.styleable.GradientColor_android_startColor, 0);
            boolean hasCenterColor = TypedArrayUtils.hasAttribute(xmlPullParser, "centerColor");
            int centerColor = TypedArrayUtils.getNamedColor(a, xmlPullParser, "centerColor", R.styleable.GradientColor_android_centerColor, 0);
            int endColor = TypedArrayUtils.getNamedColor(a, xmlPullParser, "endColor", R.styleable.GradientColor_android_endColor, 0);
            int tileMode = TypedArrayUtils.getNamedInt(a, xmlPullParser, "tileMode", R.styleable.GradientColor_android_tileMode, 0);
            float gradientRadius = TypedArrayUtils.getNamedFloat(a, xmlPullParser, "gradientRadius", R.styleable.GradientColor_android_gradientRadius, 0.0f);
            a.recycle();
            ColorStops colorStops = checkColors(inflateChildElements(resources, parser, attrs, theme), startColor, endColor, hasCenterColor, centerColor);
            switch (type) {
                case 1:
                    if (gradientRadius > 0.0f) {
                        int[] iArr = colorStops.mColors;
                        return new RadialGradient(centerX, centerY, gradientRadius, iArr, colorStops.mOffsets, parseTileMode(tileMode));
                    }
                    throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
                case 2:
                    return new SweepGradient(centerX, centerY, colorStops.mColors, colorStops.mOffsets);
                default:
                    int[] iArr2 = colorStops.mColors;
                    int i = centerColor;
                    int[] iArr3 = iArr2;
                    boolean z = hasCenterColor;
                    int i2 = startColor;
                    float f = centerY;
                    int[] iArr4 = iArr3;
                    float f2 = centerX;
                    return new LinearGradient(startX, startY, endX, endY, iArr4, colorStops.mOffsets, parseTileMode(tileMode));
            }
        } else {
            Resources.Theme theme3 = theme;
            throw new XmlPullParserException(parser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x008f, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r13.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' " + "attribute!");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.support.v4.content.res.GradientColorInflaterCompat.ColorStops inflateChildElements(android.content.res.Resources r12, org.xmlpull.v1.XmlPullParser r13, android.util.AttributeSet r14, android.content.res.Resources.Theme r15) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            int r0 = r13.getDepth()
            r1 = 1
            int r0 = r0 + r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 20
            r2.<init>(r3)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
            r3 = r4
        L_0x0013:
            int r4 = r13.next()
            r5 = r4
            if (r4 == r1) goto L_0x0090
            int r4 = r13.getDepth()
            r6 = r4
            if (r4 >= r0) goto L_0x0024
            r4 = 3
            if (r5 == r4) goto L_0x0090
        L_0x0024:
            r4 = 2
            if (r5 == r4) goto L_0x0028
            goto L_0x0013
        L_0x0028:
            if (r6 > r0) goto L_0x0013
            java.lang.String r4 = r13.getName()
            java.lang.String r7 = "item"
            boolean r4 = r4.equals(r7)
            if (r4 != 0) goto L_0x0037
            goto L_0x0013
        L_0x0037:
            int[] r4 = android.support.compat.R.styleable.GradientColorItem
            android.content.res.TypedArray r4 = android.support.v4.content.res.TypedArrayUtils.obtainAttributes(r12, r15, r14, r4)
            int r7 = android.support.compat.R.styleable.GradientColorItem_android_color
            boolean r7 = r4.hasValue(r7)
            int r8 = android.support.compat.R.styleable.GradientColorItem_android_offset
            boolean r8 = r4.hasValue(r8)
            if (r7 == 0) goto L_0x006d
            if (r8 == 0) goto L_0x006d
            int r9 = android.support.compat.R.styleable.GradientColorItem_android_color
            r10 = 0
            int r9 = r4.getColor(r9, r10)
            int r10 = android.support.compat.R.styleable.GradientColorItem_android_offset
            r11 = 0
            float r10 = r4.getFloat(r10, r11)
            r4.recycle()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r9)
            r3.add(r11)
            java.lang.Float r11 = java.lang.Float.valueOf(r10)
            r2.add(r11)
            goto L_0x0013
        L_0x006d:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = r13.getPositionDescription()
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r10 = ": <item> tag requires a 'color' attribute and a 'offset' "
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r10 = "attribute!"
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            r1.<init>(r9)
            throw r1
        L_0x0090:
            int r1 = r3.size()
            if (r1 <= 0) goto L_0x009c
            android.support.v4.content.res.GradientColorInflaterCompat$ColorStops r1 = new android.support.v4.content.res.GradientColorInflaterCompat$ColorStops
            r1.<init>((java.util.List<java.lang.Integer>) r3, (java.util.List<java.lang.Float>) r2)
            return r1
        L_0x009c:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.GradientColorInflaterCompat.inflateChildElements(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.support.v4.content.res.GradientColorInflaterCompat$ColorStops");
    }

    private static ColorStops checkColors(ColorStops colorItems, int startColor, int endColor, boolean hasCenterColor, int centerColor) {
        if (colorItems != null) {
            return colorItems;
        }
        if (hasCenterColor) {
            return new ColorStops(startColor, centerColor, endColor);
        }
        return new ColorStops(startColor, endColor);
    }

    private static Shader.TileMode parseTileMode(int tileMode) {
        switch (tileMode) {
            case 1:
                return Shader.TileMode.REPEAT;
            case 2:
                return Shader.TileMode.MIRROR;
            default:
                return Shader.TileMode.CLAMP;
        }
    }

    static final class ColorStops {
        final int[] mColors;
        final float[] mOffsets;

        ColorStops(List<Integer> colorsList, List<Float> offsetsList) {
            int size = colorsList.size();
            this.mColors = new int[size];
            this.mOffsets = new float[size];
            for (int i = 0; i < size; i++) {
                this.mColors[i] = colorsList.get(i).intValue();
                this.mOffsets[i] = offsetsList.get(i).floatValue();
            }
        }

        ColorStops(int startColor, int endColor) {
            this.mColors = new int[]{startColor, endColor};
            this.mOffsets = new float[]{0.0f, 1.0f};
        }

        ColorStops(int startColor, int centerColor, int endColor) {
            this.mColors = new int[]{startColor, centerColor, endColor};
            this.mOffsets = new float[]{0.0f, 0.5f, 1.0f};
        }
    }
}
