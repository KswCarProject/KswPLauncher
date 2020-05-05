package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.AnimatorRes;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import com.wits.pms.statuscontrol.WitsCommand;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AnimatorInflaterCompat {
    private static final boolean DBG_ANIMATOR_INFLATER = false;
    private static final int MAX_NUM_POINTS = 100;
    private static final String TAG = "AnimatorInflater";
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_COLOR = 3;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_PATH = 2;
    private static final int VALUE_TYPE_UNDEFINED = 4;

    public static Animator loadAnimator(Context context, @AnimatorRes int id) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 24) {
            return AnimatorInflater.loadAnimator(context, id);
        }
        return loadAnimator(context, context.getResources(), context.getTheme(), id);
    }

    public static Animator loadAnimator(Context context, Resources resources, Resources.Theme theme, @AnimatorRes int id) throws Resources.NotFoundException {
        return loadAnimator(context, resources, theme, id, 1.0f);
    }

    public static Animator loadAnimator(Context context, Resources resources, Resources.Theme theme, @AnimatorRes int id, float pathErrorScale) throws Resources.NotFoundException {
        XmlResourceParser parser = null;
        try {
            XmlResourceParser parser2 = resources.getAnimation(id);
            Animator animator = createAnimatorFromXml(context, resources, theme, parser2, pathErrorScale);
            if (parser2 != null) {
                parser2.close();
            }
            return animator;
        } catch (XmlPullParserException ex) {
            Resources.NotFoundException rnf = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
            rnf.initCause(ex);
            throw rnf;
        } catch (IOException ex2) {
            Resources.NotFoundException rnf2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
            rnf2.initCause(ex2);
            throw rnf2;
        } catch (Throwable th) {
            if (parser != null) {
                parser.close();
            }
            throw th;
        }
    }

    private static class PathDataEvaluator implements TypeEvaluator<PathParser.PathDataNode[]> {
        private PathParser.PathDataNode[] mNodeArray;

        PathDataEvaluator() {
        }

        PathDataEvaluator(PathParser.PathDataNode[] nodeArray) {
            this.mNodeArray = nodeArray;
        }

        public PathParser.PathDataNode[] evaluate(float fraction, PathParser.PathDataNode[] startPathData, PathParser.PathDataNode[] endPathData) {
            if (PathParser.canMorph(startPathData, endPathData)) {
                if (this.mNodeArray == null || !PathParser.canMorph(this.mNodeArray, startPathData)) {
                    this.mNodeArray = PathParser.deepCopyNodes(startPathData);
                }
                for (int i = 0; i < startPathData.length; i++) {
                    this.mNodeArray[i].interpolatePathDataNode(startPathData[i], endPathData[i], fraction);
                }
                return this.mNodeArray;
            }
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.animation.PropertyValuesHolder getPVH(android.content.res.TypedArray r24, int r25, int r26, int r27, java.lang.String r28) {
        /*
            r0 = r24
            r1 = r26
            r2 = r27
            r3 = r28
            android.util.TypedValue r4 = r0.peekValue(r1)
            if (r4 == 0) goto L_0x0010
            r7 = 1
            goto L_0x0011
        L_0x0010:
            r7 = 0
        L_0x0011:
            if (r7 == 0) goto L_0x0016
            int r8 = r4.type
            goto L_0x0017
        L_0x0016:
            r8 = 0
        L_0x0017:
            android.util.TypedValue r9 = r0.peekValue(r2)
            if (r9 == 0) goto L_0x001f
            r10 = 1
            goto L_0x0020
        L_0x001f:
            r10 = 0
        L_0x0020:
            if (r10 == 0) goto L_0x0025
            int r11 = r9.type
            goto L_0x0026
        L_0x0025:
            r11 = 0
        L_0x0026:
            r12 = 4
            r13 = r25
            if (r13 != r12) goto L_0x003f
            if (r7 == 0) goto L_0x0033
            boolean r12 = isColorType(r8)
            if (r12 != 0) goto L_0x003b
        L_0x0033:
            if (r10 == 0) goto L_0x003d
            boolean r12 = isColorType(r11)
            if (r12 == 0) goto L_0x003d
        L_0x003b:
            r12 = 3
            goto L_0x0040
        L_0x003d:
            r12 = 0
            goto L_0x0040
        L_0x003f:
            r12 = r13
        L_0x0040:
            if (r12 != 0) goto L_0x0044
            r13 = 1
            goto L_0x0045
        L_0x0044:
            r13 = 0
        L_0x0045:
            r14 = 0
            r15 = 2
            if (r12 != r15) goto L_0x00ef
            java.lang.String r5 = r0.getString(r1)
            java.lang.String r6 = r0.getString(r2)
            android.support.v4.graphics.PathParser$PathDataNode[] r15 = android.support.v4.graphics.PathParser.createNodesFromPathData(r5)
            r18 = r4
            android.support.v4.graphics.PathParser$PathDataNode[] r4 = android.support.v4.graphics.PathParser.createNodesFromPathData(r6)
            if (r15 != 0) goto L_0x006a
            if (r4 == 0) goto L_0x0062
            goto L_0x006a
        L_0x0062:
            r21 = r9
            r23 = r11
            r22 = r14
            goto L_0x00e7
        L_0x006a:
            if (r15 == 0) goto L_0x00cf
            android.support.graphics.drawable.AnimatorInflaterCompat$PathDataEvaluator r19 = new android.support.graphics.drawable.AnimatorInflaterCompat$PathDataEvaluator
            r19.<init>()
            r20 = r19
            if (r4 == 0) goto L_0x00bb
            boolean r19 = android.support.v4.graphics.PathParser.canMorph(r15, r4)
            if (r19 == 0) goto L_0x0094
            r21 = r9
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r17 = 0
            r9[r17] = r15
            r16 = 1
            r9[r16] = r4
            r22 = r14
            r14 = r20
            android.animation.PropertyValuesHolder r9 = android.animation.PropertyValuesHolder.ofObject(r3, r14, r9)
            r14 = r9
            r23 = r11
            goto L_0x00ce
        L_0x0094:
            r21 = r9
            r22 = r14
            r14 = r20
            android.view.InflateException r9 = new android.view.InflateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r23 = r11
            java.lang.String r11 = " Can't morph from "
            r2.append(r11)
            r2.append(r5)
            java.lang.String r11 = " to "
            r2.append(r11)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r9.<init>(r2)
            throw r9
        L_0x00bb:
            r21 = r9
            r23 = r11
            r22 = r14
            r14 = r20
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r9 = 0
            r2[r9] = r15
            android.animation.PropertyValuesHolder r2 = android.animation.PropertyValuesHolder.ofObject(r3, r14, r2)
            r14 = r2
        L_0x00ce:
            goto L_0x00e9
        L_0x00cf:
            r21 = r9
            r23 = r11
            r22 = r14
            if (r4 == 0) goto L_0x00e7
            android.support.graphics.drawable.AnimatorInflaterCompat$PathDataEvaluator r2 = new android.support.graphics.drawable.AnimatorInflaterCompat$PathDataEvaluator
            r2.<init>()
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r11 = 0
            r9[r11] = r4
            android.animation.PropertyValuesHolder r14 = android.animation.PropertyValuesHolder.ofObject(r3, r2, r9)
            goto L_0x00e9
        L_0x00e7:
            r14 = r22
        L_0x00e9:
            r9 = r23
            r11 = r27
            goto L_0x01e3
        L_0x00ef:
            r18 = r4
            r21 = r9
            r23 = r11
            r22 = r14
            r2 = 0
            r4 = 3
            if (r12 != r4) goto L_0x00ff
            android.support.graphics.drawable.ArgbEvaluator r2 = android.support.graphics.drawable.ArgbEvaluator.getInstance()
        L_0x00ff:
            r4 = 5
            r5 = 0
            if (r13 == 0) goto L_0x015d
            if (r7 == 0) goto L_0x0141
            if (r8 != r4) goto L_0x010c
            float r6 = r0.getDimension(r1, r5)
            goto L_0x0110
        L_0x010c:
            float r6 = r0.getFloat(r1, r5)
        L_0x0110:
            if (r10 == 0) goto L_0x0132
            r9 = r23
            if (r9 != r4) goto L_0x011d
            r11 = r27
            float r4 = r0.getDimension(r11, r5)
            goto L_0x0123
        L_0x011d:
            r11 = r27
            float r4 = r0.getFloat(r11, r5)
        L_0x0123:
            r5 = 2
            float[] r5 = new float[r5]
            r14 = 0
            r5[r14] = r6
            r15 = 1
            r5[r15] = r4
            android.animation.PropertyValuesHolder r5 = android.animation.PropertyValuesHolder.ofFloat(r3, r5)
            r14 = r5
            goto L_0x015b
        L_0x0132:
            r9 = r23
            r11 = r27
            r14 = 0
            r15 = 1
            float[] r4 = new float[r15]
            r4[r14] = r6
            android.animation.PropertyValuesHolder r4 = android.animation.PropertyValuesHolder.ofFloat(r3, r4)
            goto L_0x015a
        L_0x0141:
            r9 = r23
            r11 = r27
            if (r9 != r4) goto L_0x014c
            float r4 = r0.getDimension(r11, r5)
            goto L_0x0150
        L_0x014c:
            float r4 = r0.getFloat(r11, r5)
        L_0x0150:
            r5 = 1
            float[] r5 = new float[r5]
            r6 = 0
            r5[r6] = r4
            android.animation.PropertyValuesHolder r4 = android.animation.PropertyValuesHolder.ofFloat(r3, r5)
        L_0x015a:
            r14 = r4
        L_0x015b:
            goto L_0x01dc
        L_0x015d:
            r9 = r23
            r11 = r27
            if (r7 == 0) goto L_0x01b3
            if (r8 != r4) goto L_0x016c
            float r6 = r0.getDimension(r1, r5)
            int r6 = (int) r6
            r14 = r6
            goto L_0x017d
        L_0x016c:
            boolean r6 = isColorType(r8)
            if (r6 == 0) goto L_0x0178
            r6 = 0
            int r14 = r0.getColor(r1, r6)
            goto L_0x017d
        L_0x0178:
            r6 = 0
            int r14 = r0.getInt(r1, r6)
        L_0x017d:
            r6 = r14
            if (r10 == 0) goto L_0x01a8
            if (r9 != r4) goto L_0x018a
            float r4 = r0.getDimension(r11, r5)
            int r4 = (int) r4
            r5 = r4
            r4 = 0
            goto L_0x019b
        L_0x018a:
            boolean r4 = isColorType(r9)
            if (r4 == 0) goto L_0x0196
            r4 = 0
            int r5 = r0.getColor(r11, r4)
            goto L_0x019b
        L_0x0196:
            r4 = 0
            int r5 = r0.getInt(r11, r4)
        L_0x019b:
            r14 = 2
            int[] r14 = new int[r14]
            r14[r4] = r6
            r15 = 1
            r14[r15] = r5
            android.animation.PropertyValuesHolder r14 = android.animation.PropertyValuesHolder.ofInt(r3, r14)
            goto L_0x01dc
        L_0x01a8:
            r4 = 0
            r15 = 1
            int[] r5 = new int[r15]
            r5[r4] = r6
            android.animation.PropertyValuesHolder r14 = android.animation.PropertyValuesHolder.ofInt(r3, r5)
            goto L_0x01dc
        L_0x01b3:
            if (r10 == 0) goto L_0x01da
            if (r9 != r4) goto L_0x01bf
            float r4 = r0.getDimension(r11, r5)
            int r4 = (int) r4
            r5 = r4
            r4 = 0
            goto L_0x01d0
        L_0x01bf:
            boolean r4 = isColorType(r9)
            if (r4 == 0) goto L_0x01cb
            r4 = 0
            int r5 = r0.getColor(r11, r4)
            goto L_0x01d0
        L_0x01cb:
            r4 = 0
            int r5 = r0.getInt(r11, r4)
        L_0x01d0:
            r6 = 1
            int[] r6 = new int[r6]
            r6[r4] = r5
            android.animation.PropertyValuesHolder r14 = android.animation.PropertyValuesHolder.ofInt(r3, r6)
            goto L_0x01dc
        L_0x01da:
            r14 = r22
        L_0x01dc:
            if (r14 == 0) goto L_0x01e3
            if (r2 == 0) goto L_0x01e3
            r14.setEvaluator(r2)
        L_0x01e3:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatorInflaterCompat.getPVH(android.content.res.TypedArray, int, int, int, java.lang.String):android.animation.PropertyValuesHolder");
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator anim, TypedArray arrayAnimator, TypedArray arrayObjectAnimator, float pixelSize, XmlPullParser parser) {
        long duration = (long) TypedArrayUtils.getNamedInt(arrayAnimator, parser, "duration", 1, WitsCommand.SystemCommand.EXPORT_CONFIG);
        long startDelay = (long) TypedArrayUtils.getNamedInt(arrayAnimator, parser, "startOffset", 2, 0);
        int valueType = TypedArrayUtils.getNamedInt(arrayAnimator, parser, "valueType", 7, 4);
        if (TypedArrayUtils.hasAttribute(parser, "valueFrom") && TypedArrayUtils.hasAttribute(parser, "valueTo")) {
            if (valueType == 4) {
                valueType = inferValueTypeFromValues(arrayAnimator, 5, 6);
            }
            PropertyValuesHolder pvh = getPVH(arrayAnimator, valueType, 5, 6, "");
            if (pvh != null) {
                anim.setValues(new PropertyValuesHolder[]{pvh});
            }
        }
        anim.setDuration(duration);
        anim.setStartDelay(startDelay);
        anim.setRepeatCount(TypedArrayUtils.getNamedInt(arrayAnimator, parser, "repeatCount", 3, 0));
        anim.setRepeatMode(TypedArrayUtils.getNamedInt(arrayAnimator, parser, "repeatMode", 4, 1));
        if (arrayObjectAnimator != null) {
            setupObjectAnimator(anim, arrayObjectAnimator, valueType, pixelSize, parser);
        }
    }

    private static void setupObjectAnimator(ValueAnimator anim, TypedArray arrayObjectAnimator, int valueType, float pixelSize, XmlPullParser parser) {
        ObjectAnimator oa = (ObjectAnimator) anim;
        String pathData = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "pathData", 1);
        if (pathData != null) {
            String propertyXName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyXName", 2);
            String propertyYName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyYName", 3);
            if (valueType == 2 || valueType == 4) {
            }
            if (propertyXName == null && propertyYName == null) {
                throw new InflateException(arrayObjectAnimator.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
            }
            setupPathMotion(PathParser.createPathFromPathData(pathData), oa, 0.5f * pixelSize, propertyXName, propertyYName);
            return;
        }
        oa.setPropertyName(TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyName", 0));
    }

    private static void setupPathMotion(Path path, ObjectAnimator oa, float precision, String propertyXName, String propertyYName) {
        Path path2 = path;
        ObjectAnimator objectAnimator = oa;
        String str = propertyXName;
        String str2 = propertyYName;
        PathMeasure measureForTotalLength = new PathMeasure(path2, false);
        float totalLength = 0.0f;
        ArrayList<Float> contourLengths = new ArrayList<>();
        contourLengths.add(Float.valueOf(0.0f));
        while (true) {
            totalLength += measureForTotalLength.getLength();
            contourLengths.add(Float.valueOf(totalLength));
            if (!measureForTotalLength.nextContour()) {
                break;
            }
            path2 = path;
        }
        PathMeasure pathMeasure = new PathMeasure(path2, false);
        int numPoints = Math.min(100, ((int) (totalLength / precision)) + 1);
        float[] mX = new float[numPoints];
        float[] mY = new float[numPoints];
        float[] position = new float[2];
        float step = totalLength / ((float) (numPoints - 1));
        float currentDistance = 0.0f;
        int contourIndex = 0;
        int contourIndex2 = 0;
        while (true) {
            int i = contourIndex2;
            if (i >= numPoints) {
                break;
            }
            PathMeasure measureForTotalLength2 = measureForTotalLength;
            pathMeasure.getPosTan(currentDistance - contourLengths.get(contourIndex).floatValue(), position, (float[]) null);
            mX[i] = position[0];
            mY[i] = position[1];
            currentDistance += step;
            if (contourIndex + 1 < contourLengths.size() && currentDistance > contourLengths.get(contourIndex + 1).floatValue()) {
                contourIndex++;
                pathMeasure.nextContour();
            }
            contourIndex2 = i + 1;
            measureForTotalLength = measureForTotalLength2;
            Path path3 = path;
        }
        PropertyValuesHolder x = null;
        PropertyValuesHolder y = null;
        if (str != null) {
            x = PropertyValuesHolder.ofFloat(str, mX);
        }
        if (str2 != null) {
            y = PropertyValuesHolder.ofFloat(str2, mY);
        }
        if (x == null) {
            objectAnimator.setValues(new PropertyValuesHolder[]{y});
        } else if (y == null) {
            objectAnimator.setValues(new PropertyValuesHolder[]{x});
        } else {
            objectAnimator.setValues(new PropertyValuesHolder[]{x, y});
        }
    }

    private static Animator createAnimatorFromXml(Context context, Resources res, Resources.Theme theme, XmlPullParser parser, float pixelSize) throws XmlPullParserException, IOException {
        return createAnimatorFromXml(context, res, theme, parser, Xml.asAttributeSet(parser), (AnimatorSet) null, 0, pixelSize);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.animation.Animator createAnimatorFromXml(android.content.Context r20, android.content.res.Resources r21, android.content.res.Resources.Theme r22, org.xmlpull.v1.XmlPullParser r23, android.util.AttributeSet r24, android.animation.AnimatorSet r25, int r26, float r27) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r25
            r0 = 0
            r1 = 0
            int r2 = r23.getDepth()
            r7 = r0
            r12 = r1
        L_0x0010:
            r13 = r2
            int r0 = r23.next()
            r14 = r0
            r1 = 3
            if (r0 != r1) goto L_0x0024
            int r0 = r23.getDepth()
            if (r0 <= r13) goto L_0x0020
            goto L_0x0024
        L_0x0020:
            r1 = r20
            goto L_0x00fa
        L_0x0024:
            r0 = 1
            if (r14 == r0) goto L_0x0020
            r0 = 2
            if (r14 == r0) goto L_0x002d
        L_0x002b:
            r2 = r13
            goto L_0x0010
        L_0x002d:
            java.lang.String r15 = r23.getName()
            r16 = 0
            java.lang.String r0 = "objectAnimator"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x0050
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r24
            r4 = r27
            r5 = r23
            android.animation.ObjectAnimator r0 = loadObjectAnimator(r0, r1, r2, r3, r4, r5)
        L_0x004b:
            r1 = r20
            r7 = r0
            goto L_0x00cc
        L_0x0050:
            java.lang.String r0 = "animator"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x006a
            r4 = 0
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r24
            r5 = r27
            r6 = r23
            android.animation.ValueAnimator r0 = loadAnimator(r0, r1, r2, r3, r4, r5, r6)
            goto L_0x004b
        L_0x006a:
            java.lang.String r0 = "set"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00a8
            android.animation.AnimatorSet r0 = new android.animation.AnimatorSet
            r0.<init>()
            r17 = r0
            int[] r0 = android.support.graphics.drawable.AndroidResources.STYLEABLE_ANIMATOR_SET
            r7 = r24
            android.content.res.TypedArray r6 = android.support.v4.content.res.TypedArrayUtils.obtainAttributes(r8, r9, r7, r0)
            java.lang.String r0 = "ordering"
            r1 = 0
            int r18 = android.support.v4.content.res.TypedArrayUtils.getNamedInt(r6, r10, r0, r1, r1)
            r5 = r17
            android.animation.AnimatorSet r5 = (android.animation.AnimatorSet) r5
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r23
            r4 = r24
            r19 = r6
            r6 = r18
            r7 = r27
            createAnimatorFromXml(r0, r1, r2, r3, r4, r5, r6, r7)
            r19.recycle()
            r1 = r20
            r7 = r17
            goto L_0x00cc
        L_0x00a8:
            java.lang.String r0 = "propertyValuesHolder"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00dd
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r23)
            r1 = r20
            android.animation.PropertyValuesHolder[] r0 = loadValues(r1, r8, r9, r10, r0)
            if (r0 == 0) goto L_0x00c9
            if (r7 == 0) goto L_0x00c9
            boolean r2 = r7 instanceof android.animation.ValueAnimator
            if (r2 == 0) goto L_0x00c9
            r2 = r7
            android.animation.ValueAnimator r2 = (android.animation.ValueAnimator) r2
            r2.setValues(r0)
        L_0x00c9:
            r16 = 1
        L_0x00cc:
            if (r11 == 0) goto L_0x00db
            if (r16 != 0) goto L_0x00db
            if (r12 != 0) goto L_0x00d8
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r12 = r0
        L_0x00d8:
            r12.add(r7)
        L_0x00db:
            goto L_0x002b
        L_0x00dd:
            r1 = r20
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unknown animator name: "
            r2.append(r3)
            java.lang.String r3 = r23.getName()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x00fa:
            if (r11 == 0) goto L_0x0125
            if (r12 == 0) goto L_0x0125
            int r0 = r12.size()
            android.animation.Animator[] r0 = new android.animation.Animator[r0]
            r2 = 0
            java.util.Iterator r3 = r12.iterator()
        L_0x0109:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x011c
            java.lang.Object r4 = r3.next()
            android.animation.Animator r4 = (android.animation.Animator) r4
            int r5 = r2 + 1
            r0[r2] = r4
            r2 = r5
            goto L_0x0109
        L_0x011c:
            if (r26 != 0) goto L_0x0122
            r11.playTogether(r0)
            goto L_0x0125
        L_0x0122:
            r11.playSequentially(r0)
        L_0x0125:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatorInflaterCompat.createAnimatorFromXml(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.animation.AnimatorSet, int, float):android.animation.Animator");
    }

    private static PropertyValuesHolder[] loadValues(Context context, Resources res, Resources.Theme theme, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        ArrayList<PropertyValuesHolder> values;
        XmlPullParser xmlPullParser = parser;
        ArrayList<PropertyValuesHolder> values2 = null;
        while (true) {
            values = values2;
            int eventType = parser.getEventType();
            int type = eventType;
            if (eventType == 3 || type == 1) {
                Resources resources = res;
                Resources.Theme theme2 = theme;
                AttributeSet attributeSet = attrs;
                PropertyValuesHolder[] valuesArray = null;
            } else if (type != 2) {
                parser.next();
                values2 = values;
            } else {
                if (parser.getName().equals("propertyValuesHolder")) {
                    TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
                    String propertyName = TypedArrayUtils.getNamedString(a, xmlPullParser, "propertyName", 3);
                    int valueType = TypedArrayUtils.getNamedInt(a, xmlPullParser, "valueType", 2, 4);
                    String propertyName2 = propertyName;
                    PropertyValuesHolder pvh = loadPvh(context, res, theme, parser, propertyName, valueType);
                    if (pvh == null) {
                        pvh = getPVH(a, valueType, 0, 1, propertyName2);
                    }
                    if (pvh != null) {
                        if (values == null) {
                            values = new ArrayList<>();
                        }
                        values.add(pvh);
                    }
                    a.recycle();
                } else {
                    Resources resources2 = res;
                    Resources.Theme theme3 = theme;
                    AttributeSet attributeSet2 = attrs;
                }
                values2 = values;
                parser.next();
            }
        }
        Resources resources3 = res;
        Resources.Theme theme22 = theme;
        AttributeSet attributeSet3 = attrs;
        PropertyValuesHolder[] valuesArray2 = null;
        if (values != null) {
            int count = values.size();
            valuesArray2 = new PropertyValuesHolder[count];
            for (int i = 0; i < count; i++) {
                valuesArray2[i] = values.get(i);
            }
        }
        return valuesArray2;
    }

    private static int inferValueTypeOfKeyframe(Resources res, Resources.Theme theme, AttributeSet attrs, XmlPullParser parser) {
        TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_KEYFRAME);
        int valueType = 0;
        TypedValue keyframeValue = TypedArrayUtils.peekNamedValue(a, parser, "value", 0);
        if ((keyframeValue != null) && isColorType(keyframeValue.type)) {
            valueType = 3;
        }
        a.recycle();
        return valueType;
    }

    private static int inferValueTypeFromValues(TypedArray styledAttributes, int valueFromId, int valueToId) {
        TypedValue tvFrom = styledAttributes.peekValue(valueFromId);
        boolean hasTo = true;
        boolean hasFrom = tvFrom != null;
        int fromType = hasFrom ? tvFrom.type : 0;
        TypedValue tvTo = styledAttributes.peekValue(valueToId);
        if (tvTo == null) {
            hasTo = false;
        }
        int toType = hasTo ? tvTo.type : 0;
        if ((!hasFrom || !isColorType(fromType)) && (!hasTo || !isColorType(toType))) {
            return 0;
        }
        return 3;
    }

    private static void dumpKeyframes(Object[] keyframes, String header) {
        if (keyframes != null && keyframes.length != 0) {
            Log.d(TAG, header);
            int count = keyframes.length;
            for (int i = 0; i < count; i++) {
                Keyframe keyframe = keyframes[i];
                StringBuilder sb = new StringBuilder();
                sb.append("Keyframe ");
                sb.append(i);
                sb.append(": fraction ");
                sb.append(keyframe.getFraction() < 0.0f ? "null" : Float.valueOf(keyframe.getFraction()));
                sb.append(", ");
                sb.append(", value : ");
                sb.append(keyframe.hasValue() ? keyframe.getValue() : "null");
                Log.d(TAG, sb.toString());
            }
        }
    }

    private static PropertyValuesHolder loadPvh(Context context, Resources res, Resources.Theme theme, XmlPullParser parser, String propertyName, int valueType) throws XmlPullParserException, IOException {
        int j;
        int type;
        ArrayList<Keyframe> keyframes;
        PropertyValuesHolder value;
        float f;
        PropertyValuesHolder value2 = null;
        ArrayList<Keyframe> keyframes2 = null;
        int valueType2 = valueType;
        while (true) {
            int next = parser.next();
            j = next;
            if (next == 3 || j == 1) {
                Resources resources = res;
                Resources.Theme theme2 = theme;
                XmlPullParser xmlPullParser = parser;
            } else if (parser.getName().equals("keyframe")) {
                if (valueType2 == 4) {
                    valueType2 = inferValueTypeOfKeyframe(res, theme, Xml.asAttributeSet(parser), parser);
                } else {
                    Resources resources2 = res;
                    Resources.Theme theme3 = theme;
                    XmlPullParser xmlPullParser2 = parser;
                }
                Keyframe keyframe = loadKeyframe(context, res, theme, Xml.asAttributeSet(parser), valueType2, parser);
                if (keyframe != null) {
                    if (keyframes2 == null) {
                        keyframes2 = new ArrayList<>();
                    }
                    keyframes2.add(keyframe);
                }
                parser.next();
            } else {
                Resources resources3 = res;
                Resources.Theme theme4 = theme;
                XmlPullParser xmlPullParser3 = parser;
            }
        }
        Resources resources4 = res;
        Resources.Theme theme22 = theme;
        XmlPullParser xmlPullParser4 = parser;
        if (keyframes2 != null) {
            int size = keyframes2.size();
            int count = size;
            if (size > 0) {
                int i = 0;
                Keyframe firstKeyframe = keyframes2.get(0);
                Keyframe lastKeyframe = keyframes2.get(count - 1);
                float endFraction = lastKeyframe.getFraction();
                float f2 = 0.0f;
                if (endFraction < 1.0f) {
                    if (endFraction < 0.0f) {
                        lastKeyframe.setFraction(1.0f);
                    } else {
                        keyframes2.add(keyframes2.size(), createNewKeyframe(lastKeyframe, 1.0f));
                        count++;
                    }
                }
                float startFraction = firstKeyframe.getFraction();
                if (startFraction != 0.0f) {
                    if (startFraction < 0.0f) {
                        firstKeyframe.setFraction(0.0f);
                    } else {
                        keyframes2.add(0, createNewKeyframe(firstKeyframe, 0.0f));
                        count++;
                    }
                }
                Keyframe[] keyframeArray = new Keyframe[count];
                keyframes2.toArray(keyframeArray);
                while (i < count) {
                    Keyframe keyframe2 = keyframeArray[i];
                    if (keyframe2.getFraction() >= f2) {
                        value = value2;
                        keyframes = keyframes2;
                        type = j;
                        f = f2;
                    } else if (i == 0) {
                        keyframe2.setFraction(f2);
                        value = value2;
                        keyframes = keyframes2;
                        type = j;
                        f = f2;
                    } else if (i == count - 1) {
                        keyframe2.setFraction(1.0f);
                        value = value2;
                        keyframes = keyframes2;
                        type = j;
                        f = 0.0f;
                    } else {
                        int startIndex = i;
                        int j2 = startIndex + 1;
                        value = value2;
                        int endIndex = i;
                        while (true) {
                            keyframes = keyframes2;
                            type = j;
                            int j3 = j2;
                            if (j3 >= count - 1) {
                                f = 0.0f;
                                break;
                            }
                            f = 0.0f;
                            if (keyframeArray[j3].getFraction() >= 0.0f) {
                                break;
                            }
                            endIndex = j3;
                            j2 = j3 + 1;
                            keyframes2 = keyframes;
                            j = type;
                        }
                        distributeKeyframes(keyframeArray, keyframeArray[endIndex + 1].getFraction() - keyframeArray[startIndex - 1].getFraction(), startIndex, endIndex);
                    }
                    i++;
                    f2 = f;
                    value2 = value;
                    keyframes2 = keyframes;
                    j = type;
                }
                ArrayList<Keyframe> arrayList = keyframes2;
                int i2 = j;
                PropertyValuesHolder value3 = PropertyValuesHolder.ofKeyframe(propertyName, keyframeArray);
                if (valueType2 != 3) {
                    return value3;
                }
                value3.setEvaluator(ArgbEvaluator.getInstance());
                return value3;
            }
        }
        ArrayList<Keyframe> arrayList2 = keyframes2;
        int i3 = j;
        String str = propertyName;
        return null;
    }

    private static Keyframe createNewKeyframe(Keyframe sampleKeyframe, float fraction) {
        if (sampleKeyframe.getType() == Float.TYPE) {
            return Keyframe.ofFloat(fraction);
        }
        if (sampleKeyframe.getType() == Integer.TYPE) {
            return Keyframe.ofInt(fraction);
        }
        return Keyframe.ofObject(fraction);
    }

    private static void distributeKeyframes(Keyframe[] keyframes, float gap, int startIndex, int endIndex) {
        float increment = gap / ((float) ((endIndex - startIndex) + 2));
        for (int i = startIndex; i <= endIndex; i++) {
            keyframes[i].setFraction(keyframes[i - 1].getFraction() + increment);
        }
    }

    private static Keyframe loadKeyframe(Context context, Resources res, Resources.Theme theme, AttributeSet attrs, int valueType, XmlPullParser parser) throws XmlPullParserException, IOException {
        Keyframe keyframe;
        TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_KEYFRAME);
        Keyframe keyframe2 = null;
        float fraction = TypedArrayUtils.getNamedFloat(a, parser, "fraction", 3, -1.0f);
        TypedValue keyframeValue = TypedArrayUtils.peekNamedValue(a, parser, "value", 0);
        boolean hasValue = keyframeValue != null;
        if (valueType == 4) {
            if (!hasValue || !isColorType(keyframeValue.type)) {
                valueType = 0;
            } else {
                valueType = 3;
            }
        }
        if (hasValue) {
            if (valueType != 3) {
                switch (valueType) {
                    case 0:
                        keyframe2 = Keyframe.ofFloat(fraction, TypedArrayUtils.getNamedFloat(a, parser, "value", 0, 0.0f));
                        break;
                    case 1:
                        break;
                }
            }
            keyframe2 = Keyframe.ofInt(fraction, TypedArrayUtils.getNamedInt(a, parser, "value", 0, 0));
        } else {
            if (valueType == 0) {
                keyframe = Keyframe.ofFloat(fraction);
            } else {
                keyframe = Keyframe.ofInt(fraction);
            }
            keyframe2 = keyframe;
        }
        int resID = TypedArrayUtils.getNamedResourceId(a, parser, "interpolator", 1, 0);
        if (resID > 0) {
            keyframe2.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, resID));
        }
        a.recycle();
        return keyframe2;
    }

    private static ObjectAnimator loadObjectAnimator(Context context, Resources res, Resources.Theme theme, AttributeSet attrs, float pathErrorScale, XmlPullParser parser) throws Resources.NotFoundException {
        ObjectAnimator anim = new ObjectAnimator();
        loadAnimator(context, res, theme, attrs, anim, pathErrorScale, parser);
        return anim;
    }

    private static ValueAnimator loadAnimator(Context context, Resources res, Resources.Theme theme, AttributeSet attrs, ValueAnimator anim, float pathErrorScale, XmlPullParser parser) throws Resources.NotFoundException {
        TypedArray arrayAnimator = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_ANIMATOR);
        TypedArray arrayObjectAnimator = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        if (anim == null) {
            anim = new ValueAnimator();
        }
        parseAnimatorFromTypeArray(anim, arrayAnimator, arrayObjectAnimator, pathErrorScale, parser);
        int resID = TypedArrayUtils.getNamedResourceId(arrayAnimator, parser, "interpolator", 0, 0);
        if (resID > 0) {
            anim.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, resID));
        }
        arrayAnimator.recycle();
        if (arrayObjectAnimator != null) {
            arrayObjectAnimator.recycle();
        }
        return anim;
    }

    private static boolean isColorType(int type) {
        return type >= 28 && type <= 31;
    }

    private AnimatorInflaterCompat() {
    }
}
