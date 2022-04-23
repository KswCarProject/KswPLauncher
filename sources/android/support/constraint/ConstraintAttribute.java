package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.motion.Debug;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

public class ConstraintAttribute {
    private static final String TAG = "TransitionLayout";
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    public enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE
    }

    public AttributeType getType() {
        return this.mType;
    }

    public void setFloatValue(float value) {
        this.mFloatValue = value;
    }

    public void setColorValue(int value) {
        this.mColorValue = value;
    }

    public void setIntValue(int value) {
        this.mIntegerValue = value;
    }

    public void setStringValue(String value) {
        this.mStringValue = value;
    }

    /* renamed from: android.support.constraint.ConstraintAttribute$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType;

        static {
            int[] iArr = new int[AttributeType.values().length];
            $SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType = iArr;
            try {
                iArr[AttributeType.COLOR_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[AttributeType.COLOR_DRAWABLE_TYPE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[AttributeType.INT_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[AttributeType.FLOAT_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[AttributeType.STRING_TYPE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[AttributeType.BOOLEAN_TYPE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[AttributeType.DIMENSION_TYPE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public int noOfInterpValues() {
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[this.mType.ordinal()]) {
            case 1:
            case 2:
                return 4;
            default:
                return 1;
        }
    }

    public float getValueToInterpolate() {
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[this.mType.ordinal()]) {
            case 1:
            case 2:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 3:
                return (float) this.mIntegerValue;
            case 4:
                return this.mFloatValue;
            case 5:
                throw new RuntimeException("Cannot interpolate String");
            case 6:
                return this.mBooleanValue ? 0.0f : 1.0f;
            case 7:
                return this.mFloatValue;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] ret) {
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[this.mType.ordinal()]) {
            case 1:
            case 2:
                int i = this.mColorValue;
                ret[0] = (float) Math.pow((double) (((float) ((i >> 16) & 255)) / 255.0f), 2.2d);
                ret[1] = (float) Math.pow((double) (((float) ((i >> 8) & 255)) / 255.0f), 2.2d);
                ret[2] = (float) Math.pow((double) (((float) (i & 255)) / 255.0f), 2.2d);
                ret[3] = ((float) ((i >> 24) & 255)) / 255.0f;
                return;
            case 3:
                ret[0] = (float) this.mIntegerValue;
                return;
            case 4:
                ret[0] = this.mFloatValue;
                return;
            case 5:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 6:
                ret[0] = this.mBooleanValue ? 0.0f : 1.0f;
                return;
            case 7:
                ret[0] = this.mFloatValue;
                return;
            default:
                return;
        }
    }

    public void setValue(float[] value) {
        boolean z = false;
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[this.mType.ordinal()]) {
            case 1:
            case 2:
                int HSVToColor = Color.HSVToColor(value);
                this.mColorValue = HSVToColor;
                this.mColorValue = (HSVToColor & ViewCompat.MEASURED_SIZE_MASK) | (clamp((int) (value[3] * 255.0f)) << 24);
                return;
            case 3:
                this.mIntegerValue = (int) value[0];
                return;
            case 4:
                this.mFloatValue = value[0];
                return;
            case 5:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 6:
                if (((double) value[0]) > 0.5d) {
                    z = true;
                }
                this.mBooleanValue = z;
                return;
            case 7:
                this.mFloatValue = value[0];
                return;
            default:
                return;
        }
    }

    public boolean diff(ConstraintAttribute constraintAttribute) {
        if (constraintAttribute == null || this.mType != constraintAttribute.mType) {
            return false;
        }
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[this.mType.ordinal()]) {
            case 1:
            case 2:
                if (this.mColorValue == constraintAttribute.mColorValue) {
                    return true;
                }
                return false;
            case 3:
                if (this.mIntegerValue == constraintAttribute.mIntegerValue) {
                    return true;
                }
                return false;
            case 4:
                if (this.mFloatValue == constraintAttribute.mFloatValue) {
                    return true;
                }
                return false;
            case 5:
                if (this.mIntegerValue == constraintAttribute.mIntegerValue) {
                    return true;
                }
                return false;
            case 6:
                if (this.mBooleanValue == constraintAttribute.mBooleanValue) {
                    return true;
                }
                return false;
            case 7:
                if (this.mFloatValue == constraintAttribute.mFloatValue) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public ConstraintAttribute(String name, AttributeType attributeType) {
        this.mName = name;
        this.mType = attributeType;
    }

    public ConstraintAttribute(String name, AttributeType attributeType, Object value) {
        this.mName = name;
        this.mType = attributeType;
        setValue(value);
    }

    public ConstraintAttribute(ConstraintAttribute source, Object value) {
        this.mName = source.mName;
        this.mType = source.mType;
        setValue(value);
    }

    public void setValue(Object value) {
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[this.mType.ordinal()]) {
            case 1:
            case 2:
                this.mColorValue = ((Integer) value).intValue();
                return;
            case 3:
                this.mIntegerValue = ((Integer) value).intValue();
                return;
            case 4:
                this.mFloatValue = ((Float) value).floatValue();
                return;
            case 5:
                this.mStringValue = (String) value;
                return;
            case 6:
                this.mBooleanValue = ((Boolean) value).booleanValue();
                return;
            case 7:
                this.mFloatValue = ((Float) value).floatValue();
                return;
            default:
                return;
        }
    }

    public static HashMap<String, ConstraintAttribute> extractAttributes(HashMap<String, ConstraintAttribute> base, View view) {
        HashMap<String, ConstraintAttribute> ret = new HashMap<>();
        Class<?> cls = view.getClass();
        for (String name : base.keySet()) {
            ConstraintAttribute constraintAttribute = base.get(name);
            try {
                if (name.equals("BackgroundColor")) {
                    ret.put(name, new ConstraintAttribute(constraintAttribute, Integer.valueOf(((ColorDrawable) view.getBackground()).getColor())));
                } else {
                    ret.put(name, new ConstraintAttribute(constraintAttribute, cls.getMethod("getMap" + name, new Class[0]).invoke(view, new Object[0])));
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return ret;
    }

    public static void setAttributes(View view, HashMap<String, ConstraintAttribute> map) {
        Class<?> cls = view.getClass();
        for (String name : map.keySet()) {
            ConstraintAttribute constraintAttribute = map.get(name);
            String methodName = "set" + name;
            try {
                switch (AnonymousClass1.$SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[constraintAttribute.mType.ordinal()]) {
                    case 1:
                        cls.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(view, new Object[]{Integer.valueOf(constraintAttribute.mColorValue)});
                        break;
                    case 2:
                        Method method = cls.getMethod(methodName, new Class[]{Drawable.class});
                        ColorDrawable drawable = new ColorDrawable();
                        drawable.setColor(constraintAttribute.mColorValue);
                        method.invoke(view, new Object[]{drawable});
                        break;
                    case 3:
                        cls.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(view, new Object[]{Integer.valueOf(constraintAttribute.mIntegerValue)});
                        break;
                    case 4:
                        cls.getMethod(methodName, new Class[]{Float.TYPE}).invoke(view, new Object[]{Float.valueOf(constraintAttribute.mFloatValue)});
                        break;
                    case 5:
                        cls.getMethod(methodName, new Class[]{CharSequence.class}).invoke(view, new Object[]{constraintAttribute.mStringValue});
                        break;
                    case 6:
                        cls.getMethod(methodName, new Class[]{Boolean.TYPE}).invoke(view, new Object[]{Boolean.valueOf(constraintAttribute.mBooleanValue)});
                        break;
                    case 7:
                        cls.getMethod(methodName, new Class[]{Float.TYPE}).invoke(view, new Object[]{Float.valueOf(constraintAttribute.mFloatValue)});
                        break;
                }
            } catch (NoSuchMethodException e) {
                Log.e(TAG, e.getMessage());
                Log.e(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
                Log.e(TAG, cls.getName() + " must have a method " + methodName);
            } catch (IllegalAccessException e2) {
                Log.e(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                Log.e(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
                e3.printStackTrace();
            }
        }
    }

    private static int clamp(int c) {
        int c2 = (c & (~(c >> 31))) - 255;
        return (c2 & (c2 >> 31)) + 255;
    }

    public void setInterpolatedValue(View view, float[] value) {
        View view2 = view;
        Class<?> cls = view.getClass();
        String methodName = "set" + this.mName;
        try {
            boolean z = true;
            switch (AnonymousClass1.$SwitchMap$android$support$constraint$ConstraintAttribute$AttributeType[this.mType.ordinal()]) {
                case 1:
                    cls.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(view2, new Object[]{Integer.valueOf((clamp((int) (value[3] * 255.0f)) << 24) | (clamp((int) (((float) Math.pow((double) value[0], 0.45454545454545453d)) * 255.0f)) << 16) | (clamp((int) (((float) Math.pow((double) value[1], 0.45454545454545453d)) * 255.0f)) << 8) | clamp((int) (((float) Math.pow((double) value[2], 0.45454545454545453d)) * 255.0f)))});
                    return;
                case 2:
                    Method method = cls.getMethod(methodName, new Class[]{Drawable.class});
                    int g = clamp((int) (((float) Math.pow((double) value[1], 0.45454545454545453d)) * 255.0f));
                    int b = clamp((int) (((float) Math.pow((double) value[2], 0.45454545454545453d)) * 255.0f));
                    ColorDrawable drawable = new ColorDrawable();
                    drawable.setColor((clamp((int) (value[3] * 255.0f)) << 24) | (clamp((int) (((float) Math.pow((double) value[0], 0.45454545454545453d)) * 255.0f)) << 16) | (g << 8) | b);
                    method.invoke(view2, new Object[]{drawable});
                    return;
                case 3:
                    cls.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(view2, new Object[]{Integer.valueOf((int) value[0])});
                    return;
                case 4:
                    cls.getMethod(methodName, new Class[]{Float.TYPE}).invoke(view2, new Object[]{Float.valueOf(value[0])});
                    return;
                case 5:
                    throw new RuntimeException("unable to interpolate strings " + this.mName);
                case 6:
                    Method method2 = cls.getMethod(methodName, new Class[]{Boolean.TYPE});
                    Object[] objArr = new Object[1];
                    if (value[0] <= 0.5f) {
                        z = false;
                    }
                    objArr[0] = Boolean.valueOf(z);
                    method2.invoke(view2, objArr);
                    return;
                case 7:
                    cls.getMethod(methodName, new Class[]{Float.TYPE}).invoke(view2, new Object[]{Float.valueOf(value[0])});
                    return;
                default:
                    return;
            }
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "no method " + methodName + "on View \"" + Debug.getName(view) + "\"");
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "cannot access method " + methodName + "on View \"" + Debug.getName(view) + "\"");
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    public static void parse(Context context, XmlPullParser parser, HashMap<String, ConstraintAttribute> custom) {
        TypedArray a = context.obtainStyledAttributes(Xml.asAttributeSet(parser), R.styleable.CustomAttribute);
        String name = null;
        Object value = null;
        AttributeType type = null;
        int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.CustomAttribute_attributeName) {
                name = a.getString(attr);
                if (name != null && name.length() > 0) {
                    name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                }
            } else if (attr == R.styleable.CustomAttribute_customBoolean) {
                value = Boolean.valueOf(a.getBoolean(attr, false));
                type = AttributeType.BOOLEAN_TYPE;
            } else if (attr == R.styleable.CustomAttribute_customColorValue) {
                type = AttributeType.COLOR_TYPE;
                value = Integer.valueOf(a.getColor(attr, 0));
            } else if (attr == R.styleable.CustomAttribute_customColorDrawableValue) {
                type = AttributeType.COLOR_DRAWABLE_TYPE;
                value = Integer.valueOf(a.getColor(attr, 0));
            } else if (attr == R.styleable.CustomAttribute_customPixelDimension) {
                type = AttributeType.DIMENSION_TYPE;
                value = Float.valueOf(TypedValue.applyDimension(1, a.getDimension(attr, 0.0f), context.getResources().getDisplayMetrics()));
            } else if (attr == R.styleable.CustomAttribute_customDimension) {
                type = AttributeType.DIMENSION_TYPE;
                value = Float.valueOf(a.getDimension(attr, 0.0f));
            } else if (attr == R.styleable.CustomAttribute_customFloatValue) {
                type = AttributeType.FLOAT_TYPE;
                value = Float.valueOf(a.getFloat(attr, Float.NaN));
            } else if (attr == R.styleable.CustomAttribute_customIntegerValue) {
                type = AttributeType.INT_TYPE;
                value = Integer.valueOf(a.getInteger(attr, -1));
            } else if (attr == R.styleable.CustomAttribute_customStringValue) {
                type = AttributeType.STRING_TYPE;
                value = a.getString(attr);
            }
        }
        if (!(name == null || value == null)) {
            custom.put(name, new ConstraintAttribute(name, type, value));
        }
        a.recycle();
    }
}
