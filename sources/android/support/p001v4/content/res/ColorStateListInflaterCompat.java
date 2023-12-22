package android.support.p001v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.compat.C0082R;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: android.support.v4.content.res.ColorStateListInflaterCompat */
/* loaded from: classes.dex */
public final class ColorStateListInflaterCompat {
    private static final int DEFAULT_COLOR = -65536;

    private ColorStateListInflaterCompat() {
    }

    public static ColorStateList createFromXml(Resources r, XmlPullParser parser, Resources.Theme theme) throws XmlPullParserException, IOException {
        int type;
        AttributeSet attrs = Xml.asAttributeSet(parser);
        do {
            type = parser.next();
            if (type == 2) {
                break;
            }
        } while (type != 1);
        if (type != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        return createFromXmlInner(r, parser, attrs, theme);
    }

    public static ColorStateList createFromXmlInner(Resources r, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = parser.getName();
        if (!name.equals("selector")) {
            throw new XmlPullParserException(parser.getPositionDescription() + ": invalid color state list tag " + name);
        }
        return inflate(r, parser, attrs, theme);
    }

    private static ColorStateList inflate(Resources r, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth;
        int i = 1;
        int innerDepth = parser.getDepth() + 1;
        int defaultColor = -65536;
        int[][] stateSpecList = new int[20];
        int[] colorList = new int[stateSpecList.length];
        int listSize = 0;
        while (true) {
            int type = parser.next();
            if (type != i && ((depth = parser.getDepth()) >= innerDepth || type != 3)) {
                if (type == 2 && depth <= innerDepth && parser.getName().equals("item")) {
                    TypedArray a = obtainAttributes(r, theme, attrs, C0082R.styleable.ColorStateListItem);
                    int baseColor = a.getColor(C0082R.styleable.ColorStateListItem_android_color, -65281);
                    float alphaMod = 1.0f;
                    if (a.hasValue(C0082R.styleable.ColorStateListItem_android_alpha)) {
                        alphaMod = a.getFloat(C0082R.styleable.ColorStateListItem_android_alpha, 1.0f);
                    } else if (a.hasValue(C0082R.styleable.ColorStateListItem_alpha)) {
                        alphaMod = a.getFloat(C0082R.styleable.ColorStateListItem_alpha, 1.0f);
                    }
                    a.recycle();
                    int j = 0;
                    int numAttrs = attrs.getAttributeCount();
                    int[] stateSpec = new int[numAttrs];
                    int innerDepth2 = innerDepth;
                    int innerDepth3 = 0;
                    while (innerDepth3 < numAttrs) {
                        int numAttrs2 = numAttrs;
                        int stateResId = attrs.getAttributeNameResource(innerDepth3);
                        int defaultColor2 = defaultColor;
                        if (stateResId != 16843173 && stateResId != 16843551 && stateResId != C0082R.attr.alpha) {
                            int j2 = j + 1;
                            stateSpec[j] = attrs.getAttributeBooleanValue(innerDepth3, false) ? stateResId : -stateResId;
                            j = j2;
                        }
                        innerDepth3++;
                        numAttrs = numAttrs2;
                        defaultColor = defaultColor2;
                    }
                    int defaultColor3 = defaultColor;
                    int[] stateSpec2 = StateSet.trimStateSet(stateSpec, j);
                    int color = modulateColorAlpha(baseColor, alphaMod);
                    defaultColor = (listSize == 0 || stateSpec2.length == 0) ? color : defaultColor3;
                    colorList = GrowingArrayUtils.append(colorList, listSize, color);
                    stateSpecList = (int[][]) GrowingArrayUtils.append(stateSpecList, listSize, stateSpec2);
                    listSize++;
                    innerDepth = innerDepth2;
                    i = 1;
                } else {
                    innerDepth = innerDepth;
                    defaultColor = defaultColor;
                    i = 1;
                }
            }
        }
        int[] colors = new int[listSize];
        int[][] stateSpecs = new int[listSize];
        System.arraycopy(colorList, 0, colors, 0, listSize);
        System.arraycopy(stateSpecList, 0, stateSpecs, 0, listSize);
        return new ColorStateList(stateSpecs, colors);
    }

    private static TypedArray obtainAttributes(Resources res, Resources.Theme theme, AttributeSet set, int[] attrs) {
        return theme == null ? res.obtainAttributes(set, attrs) : theme.obtainStyledAttributes(set, attrs, 0, 0);
    }

    private static int modulateColorAlpha(int color, float alphaMod) {
        int alpha = Math.round(Color.alpha(color) * alphaMod);
        return (16777215 & color) | (alpha << 24);
    }
}
