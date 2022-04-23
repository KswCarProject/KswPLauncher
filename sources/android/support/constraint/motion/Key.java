package android.support.constraint.motion;

import android.content.Context;
import android.support.constraint.ConstraintAttribute;
import android.util.AttributeSet;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Key {
    static final String ALPHA = "alpha";
    static final String CUSTOM = "CUSTOM";
    static final String ELEVATION = "elevation";
    static final String PIVOT_X = "transformPivotX";
    static final String PIVOT_Y = "transformPivotY";
    static final String PROGRESS = "progress";
    static final String ROTATION = "rotation";
    static final String ROTATION_X = "rotationX";
    static final String ROTATION_Y = "rotationY";
    static final String SCALE_X = "scaleX";
    static final String SCALE_Y = "scaleY";
    static final String TRANSITION_PATH_ROTATE = "transitionPathRotate";
    static final String TRANSLATION_X = "translationX";
    static final String TRANSLATION_Y = "translationY";
    static final String TRANSLATION_Z = "translationZ";
    public static int UNSET = -1;
    static final String WAVE_OFFSET = "waveOffset";
    static final String WAVE_PERIOD = "wavePeriod";
    static final String WAVE_VARIES_BY = "waveVariesBy";
    HashMap<String, ConstraintAttribute> mCustomConstraints;
    int mFramePosition;
    int mTargetId;
    String mTargetString = null;
    protected int mType;

    public abstract void addValues(HashMap<String, SplineSet> hashMap);

    /* access modifiers changed from: package-private */
    public abstract void getAttributeNames(HashSet<String> hashSet);

    /* access modifiers changed from: package-private */
    public abstract void load(Context context, AttributeSet attributeSet);

    public abstract void setValue(String str, Object obj);

    public Key() {
        int i = UNSET;
        this.mFramePosition = i;
        this.mTargetId = i;
    }

    /* access modifiers changed from: package-private */
    public boolean matches(String constraintTag) {
        String str = this.mTargetString;
        if (str == null || constraintTag == null) {
            return false;
        }
        return constraintTag.matches(str);
    }

    /* access modifiers changed from: package-private */
    public float toFloat(Object value) {
        return value instanceof Float ? ((Float) value).floatValue() : Float.parseFloat(value.toString());
    }

    /* access modifiers changed from: package-private */
    public int toInt(Object value) {
        return value instanceof Integer ? ((Integer) value).intValue() : Integer.parseInt(value.toString());
    }

    /* access modifiers changed from: package-private */
    public boolean toBoolean(Object value) {
        return value instanceof Boolean ? ((Boolean) value).booleanValue() : Boolean.parseBoolean(value.toString());
    }

    public void setInterpolation(HashMap<String, Integer> hashMap) {
    }
}
