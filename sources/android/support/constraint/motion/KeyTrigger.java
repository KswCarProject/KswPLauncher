package android.support.constraint.motion;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.support.constraint.R;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

public class KeyTrigger extends Key {
    public static final int KEY_TYPE = 5;
    static final String NAME = "KeyTrigger";
    private static final String TAG = "KeyTrigger";
    RectF mCollisionRect = new RectF();
    /* access modifiers changed from: private */
    public String mCross = null;
    private int mCurveFit = -1;
    private Method mFireCross;
    private boolean mFireCrossReset = true;
    private float mFireLastPos;
    private Method mFireNegativeCross;
    private boolean mFireNegativeReset = true;
    private Method mFirePositiveCross;
    private boolean mFirePositiveReset = true;
    /* access modifiers changed from: private */
    public float mFireThreshold = Float.NaN;
    /* access modifiers changed from: private */
    public String mNegativeCross = null;
    /* access modifiers changed from: private */
    public String mPositiveCross = null;
    /* access modifiers changed from: private */
    public boolean mPostLayout = false;
    RectF mTargetRect = new RectF();
    /* access modifiers changed from: private */
    public int mTriggerCollisionId = UNSET;
    private View mTriggerCollisionView = null;
    /* access modifiers changed from: private */
    public int mTriggerID = UNSET;
    /* access modifiers changed from: private */
    public int mTriggerReceiver = UNSET;
    float mTriggerSlack = 0.1f;

    public KeyTrigger() {
        this.mType = 5;
        this.mCustomConstraints = new HashMap();
    }

    public void load(Context context, AttributeSet attrs) {
        Loader.read(this, context.obtainStyledAttributes(attrs, R.styleable.KeyTrigger), context);
    }

    /* access modifiers changed from: package-private */
    public int getCurveFit() {
        return this.mCurveFit;
    }

    public void getAttributeNames(HashSet<String> hashSet) {
    }

    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    public void setValue(String tag, Object value) {
    }

    private void setUpRect(RectF rect, View child, boolean postLayout) {
        rect.top = (float) child.getTop();
        rect.bottom = (float) child.getBottom();
        rect.left = (float) child.getLeft();
        rect.right = (float) child.getRight();
        if (postLayout) {
            child.getMatrix().mapRect(rect);
        }
    }

    public void conditionallyFire(float pos, View child) {
        boolean fireCross = false;
        boolean fireNegative = false;
        boolean firePositive = false;
        if (this.mTriggerCollisionId != UNSET) {
            if (this.mTriggerCollisionView == null) {
                this.mTriggerCollisionView = ((ViewGroup) child.getParent()).findViewById(this.mTriggerCollisionId);
            }
            setUpRect(this.mCollisionRect, this.mTriggerCollisionView, this.mPostLayout);
            setUpRect(this.mTargetRect, child, this.mPostLayout);
            if (this.mCollisionRect.intersect(this.mTargetRect)) {
                if (this.mFireCrossReset) {
                    fireCross = true;
                    this.mFireCrossReset = false;
                }
                if (this.mFirePositiveReset) {
                    firePositive = true;
                    this.mFirePositiveReset = false;
                }
                this.mFireNegativeReset = true;
            } else {
                if (!this.mFireCrossReset) {
                    fireCross = true;
                    this.mFireCrossReset = true;
                }
                if (this.mFireNegativeReset) {
                    fireNegative = true;
                    this.mFireNegativeReset = false;
                }
                this.mFirePositiveReset = true;
            }
        } else {
            if (this.mFireCrossReset) {
                float f = this.mFireThreshold;
                if ((pos - f) * (this.mFireLastPos - f) < 0.0f) {
                    fireCross = true;
                    this.mFireCrossReset = false;
                }
            } else if (Math.abs(pos - this.mFireThreshold) > this.mTriggerSlack) {
                this.mFireCrossReset = true;
            }
            if (this.mFireNegativeReset) {
                float f2 = this.mFireThreshold;
                float offset = pos - f2;
                if (offset * (this.mFireLastPos - f2) < 0.0f && offset < 0.0f) {
                    fireNegative = true;
                    this.mFireNegativeReset = false;
                }
            } else if (Math.abs(pos - this.mFireThreshold) > this.mTriggerSlack) {
                this.mFireNegativeReset = true;
            }
            if (this.mFirePositiveReset) {
                float f3 = this.mFireThreshold;
                float offset2 = pos - f3;
                if (offset2 * (this.mFireLastPos - f3) < 0.0f && offset2 > 0.0f) {
                    firePositive = true;
                    this.mFirePositiveReset = false;
                }
            } else if (Math.abs(pos - this.mFireThreshold) > this.mTriggerSlack) {
                this.mFirePositiveReset = true;
            }
        }
        this.mFireLastPos = pos;
        if (fireNegative || fireCross || firePositive) {
            ((MotionLayout) child.getParent()).fireTrigger(this.mTriggerID, firePositive, pos);
        }
        View call = this.mTriggerReceiver == UNSET ? child : ((MotionLayout) child.getParent()).findViewById(this.mTriggerReceiver);
        if (fireNegative && this.mNegativeCross != null) {
            if (this.mFireNegativeCross == null) {
                try {
                    this.mFireNegativeCross = call.getClass().getMethod(this.mNegativeCross, new Class[0]);
                } catch (NoSuchMethodException e) {
                    Log.e("KeyTrigger", "Could not find method \"" + this.mNegativeCross + "\"on class " + call.getClass().getSimpleName() + " " + Debug.getName(call));
                }
            }
            try {
                this.mFireNegativeCross.invoke(call, new Object[0]);
            } catch (Exception e2) {
                Log.e("KeyTrigger", "Exception in call \"" + this.mNegativeCross + "\"on class " + call.getClass().getSimpleName() + " " + Debug.getName(call));
            }
        }
        if (firePositive && this.mPositiveCross != null) {
            if (this.mFirePositiveCross == null) {
                try {
                    this.mFirePositiveCross = call.getClass().getMethod(this.mPositiveCross, new Class[0]);
                } catch (NoSuchMethodException e3) {
                    Log.e("KeyTrigger", "Could not find method \"" + this.mPositiveCross + "\"on class " + call.getClass().getSimpleName() + " " + Debug.getName(call));
                }
            }
            try {
                this.mFirePositiveCross.invoke(call, new Object[0]);
            } catch (Exception e4) {
                Log.e("KeyTrigger", "Exception in call \"" + this.mPositiveCross + "\"on class " + call.getClass().getSimpleName() + " " + Debug.getName(call));
            }
        }
        if (fireCross && this.mCross != null) {
            if (this.mFireCross == null) {
                try {
                    this.mFireCross = call.getClass().getMethod(this.mCross, new Class[0]);
                } catch (NoSuchMethodException e5) {
                    Log.e("KeyTrigger", "Could not find method \"" + this.mCross + "\"on class " + call.getClass().getSimpleName() + " " + Debug.getName(call));
                }
            }
            try {
                this.mFireCross.invoke(call, new Object[0]);
            } catch (Exception e6) {
                Log.e("KeyTrigger", "Exception in call \"" + this.mCross + "\"on class " + call.getClass().getSimpleName() + " " + Debug.getName(call));
            }
        }
    }

    private static class Loader {
        private static final int COLLISION = 9;
        private static final int CROSS = 4;
        private static final int FRAME_POS = 8;
        private static final int NEGATIVE_CROSS = 1;
        private static final int POSITIVE_CROSS = 2;
        private static final int POST_LAYOUT = 10;
        private static final int TARGET_ID = 7;
        private static final int TRIGGER_ID = 6;
        private static final int TRIGGER_RECEIVER = 11;
        private static final int TRIGGER_SLACK = 5;
        private static SparseIntArray mAttrMap;

        private Loader() {
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTrigger_framePosition, 8);
            mAttrMap.append(R.styleable.KeyTrigger_onCross, 4);
            mAttrMap.append(R.styleable.KeyTrigger_onNegativeCross, 1);
            mAttrMap.append(R.styleable.KeyTrigger_onPositiveCross, 2);
            mAttrMap.append(R.styleable.KeyTrigger_motionTarget, 7);
            mAttrMap.append(R.styleable.KeyTrigger_triggerId, 6);
            mAttrMap.append(R.styleable.KeyTrigger_triggerSlack, 5);
            mAttrMap.append(R.styleable.KeyTrigger_motion_triggerOnCollision, 9);
            mAttrMap.append(R.styleable.KeyTrigger_motion_postLayoutCollision, 10);
            mAttrMap.append(R.styleable.KeyTrigger_triggerReceiver, 11);
        }

        public static void read(KeyTrigger c, TypedArray a, Context context) {
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                switch (mAttrMap.get(attr)) {
                    case 1:
                        String unused = c.mNegativeCross = a.getString(attr);
                        continue;
                    case 2:
                        String unused2 = c.mPositiveCross = a.getString(attr);
                        continue;
                    case 4:
                        String unused3 = c.mCross = a.getString(attr);
                        continue;
                    case 5:
                        c.mTriggerSlack = a.getFloat(attr, c.mTriggerSlack);
                        continue;
                    case 6:
                        int unused4 = c.mTriggerID = a.getResourceId(attr, c.mTriggerID);
                        continue;
                    case 7:
                        if (!MotionLayout.IS_IN_EDIT_MODE) {
                            if (a.peekValue(attr).type != 3) {
                                c.mTargetId = a.getResourceId(attr, c.mTargetId);
                                break;
                            } else {
                                c.mTargetString = a.getString(attr);
                                break;
                            }
                        } else {
                            c.mTargetId = a.getResourceId(attr, c.mTargetId);
                            if (c.mTargetId == -1) {
                                c.mTargetString = a.getString(attr);
                                break;
                            } else {
                                continue;
                            }
                        }
                    case 8:
                        c.mFramePosition = a.getInteger(attr, c.mFramePosition);
                        float unused5 = c.mFireThreshold = (((float) c.mFramePosition) + 0.5f) / 100.0f;
                        continue;
                    case 9:
                        int unused6 = c.mTriggerCollisionId = a.getResourceId(attr, c.mTriggerCollisionId);
                        continue;
                    case 10:
                        boolean unused7 = c.mPostLayout = a.getBoolean(attr, c.mPostLayout);
                        continue;
                    case 11:
                        int unused8 = c.mTriggerReceiver = a.getResourceId(attr, c.mTriggerReceiver);
                        break;
                }
                Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(attr) + "   " + mAttrMap.get(attr));
            }
        }
    }
}
