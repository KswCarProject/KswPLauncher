package android.support.constraint.motion;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.support.constraint.R;
import android.support.constraint.motion.MotionLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import org.xmlpull.v1.XmlPullParser;

class TouchResponse {
    private static final boolean DEBUG = false;
    static final int FLAG_DISABLE_POST_SCROLL = 1;
    static final int FLAG_DISABLE_SCROLL = 2;
    private static final int SIDE_BOTTOM = 3;
    private static final int SIDE_END = 6;
    private static final int SIDE_LEFT = 1;
    private static final int SIDE_MIDDLE = 4;
    private static final int SIDE_RIGHT = 2;
    private static final int SIDE_START = 5;
    private static final int SIDE_TOP = 0;
    private static final String TAG = "TouchResponse";
    private static final float[][] TOUCH_DIRECTION = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    private static final int TOUCH_DOWN = 1;
    private static final int TOUCH_END = 5;
    private static final int TOUCH_LEFT = 2;
    private static final int TOUCH_RIGHT = 3;
    private static final float[][] TOUCH_SIDES = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    private static final int TOUCH_START = 4;
    private static final int TOUCH_UP = 0;
    private float[] mAnchorDpDt = new float[2];
    private float mDragScale = 1.0f;
    private boolean mDragStarted = false;
    private float mDragThreshold = 10.0f;
    private int mFlags = 0;
    private float mLastTouchX;
    private float mLastTouchY;
    private int mLimitBoundsTo = -1;
    private float mMaxAcceleration = 1.2f;
    private float mMaxVelocity = 4.0f;
    private final MotionLayout mMotionLayout;
    private boolean mMoveWhenScrollAtTop = true;
    private int mOnTouchUp = 0;
    private int mTouchAnchorId = -1;
    private int mTouchAnchorSide = 0;
    private float mTouchAnchorX = 0.5f;
    private float mTouchAnchorY = 0.5f;
    private float mTouchDirectionX = 0.0f;
    private float mTouchDirectionY = 1.0f;
    private int mTouchRegionId = -1;
    private int mTouchSide = 0;

    TouchResponse(Context context, MotionLayout layout, XmlPullParser parser) {
        this.mMotionLayout = layout;
        fillFromAttributeList(context, Xml.asAttributeSet(parser));
    }

    public void setRTL(boolean rtl) {
        if (rtl) {
            float[][] fArr = TOUCH_DIRECTION;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = TOUCH_SIDES;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = TOUCH_DIRECTION;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = TOUCH_SIDES;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = TOUCH_SIDES;
        int i = this.mTouchAnchorSide;
        this.mTouchAnchorX = fArr5[i][0];
        this.mTouchAnchorY = fArr5[i][1];
        float[][] fArr6 = TOUCH_DIRECTION;
        int i2 = this.mTouchSide;
        this.mTouchDirectionX = fArr6[i2][0];
        this.mTouchDirectionY = fArr6[i2][1];
    }

    private void fillFromAttributeList(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.OnSwipe);
        fill(a);
        a.recycle();
    }

    private void fill(TypedArray a) {
        int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.OnSwipe_touchAnchorId) {
                this.mTouchAnchorId = a.getResourceId(attr, this.mTouchAnchorId);
            } else if (attr == R.styleable.OnSwipe_touchAnchorSide) {
                int i2 = a.getInt(attr, this.mTouchAnchorSide);
                this.mTouchAnchorSide = i2;
                float[][] fArr = TOUCH_SIDES;
                this.mTouchAnchorX = fArr[i2][0];
                this.mTouchAnchorY = fArr[i2][1];
            } else if (attr == R.styleable.OnSwipe_dragDirection) {
                int i3 = a.getInt(attr, this.mTouchSide);
                this.mTouchSide = i3;
                float[][] fArr2 = TOUCH_DIRECTION;
                this.mTouchDirectionX = fArr2[i3][0];
                this.mTouchDirectionY = fArr2[i3][1];
            } else if (attr == R.styleable.OnSwipe_maxVelocity) {
                this.mMaxVelocity = a.getFloat(attr, this.mMaxVelocity);
            } else if (attr == R.styleable.OnSwipe_maxAcceleration) {
                this.mMaxAcceleration = a.getFloat(attr, this.mMaxAcceleration);
            } else if (attr == R.styleable.OnSwipe_moveWhenScrollAtTop) {
                this.mMoveWhenScrollAtTop = a.getBoolean(attr, this.mMoveWhenScrollAtTop);
            } else if (attr == R.styleable.OnSwipe_dragScale) {
                this.mDragScale = a.getFloat(attr, this.mDragScale);
            } else if (attr == R.styleable.OnSwipe_dragThreshold) {
                this.mDragThreshold = a.getFloat(attr, this.mDragThreshold);
            } else if (attr == R.styleable.OnSwipe_touchRegionId) {
                this.mTouchRegionId = a.getResourceId(attr, this.mTouchRegionId);
            } else if (attr == R.styleable.OnSwipe_onTouchUp) {
                this.mOnTouchUp = a.getInt(attr, this.mOnTouchUp);
            } else if (attr == R.styleable.OnSwipe_nestedScrollFlags) {
                this.mFlags = a.getInteger(attr, 0);
            } else if (attr == R.styleable.OnSwipe_limitBoundsTo) {
                this.mLimitBoundsTo = a.getResourceId(attr, 0);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setUpTouchEvent(float lastTouchX, float lastTouchY) {
        this.mLastTouchX = lastTouchX;
        this.mLastTouchY = lastTouchY;
        this.mDragStarted = false;
    }

    /* access modifiers changed from: package-private */
    public void processTouchEvent(MotionEvent event, MotionLayout.MotionTracker velocityTracker, int currentState, MotionScene motionScene) {
        float velocity;
        float pos;
        int i;
        float change;
        MotionLayout.MotionTracker motionTracker = velocityTracker;
        motionTracker.addMovement(event);
        switch (event.getAction()) {
            case 0:
                this.mLastTouchX = event.getRawX();
                this.mLastTouchY = event.getRawY();
                this.mDragStarted = false;
                return;
            case 1:
                this.mDragStarted = false;
                motionTracker.computeCurrentVelocity(1000);
                float tvx = velocityTracker.getXVelocity();
                float tvy = velocityTracker.getYVelocity();
                float currentPos = this.mMotionLayout.getProgress();
                float pos2 = currentPos;
                int i2 = this.mTouchAnchorId;
                if (i2 != -1) {
                    this.mMotionLayout.getAnchorDpDt(i2, pos2, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                } else {
                    float minSize = (float) Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                    float[] fArr = this.mAnchorDpDt;
                    fArr[1] = this.mTouchDirectionY * minSize;
                    fArr[0] = this.mTouchDirectionX * minSize;
                }
                float minSize2 = this.mTouchDirectionX;
                float[] fArr2 = this.mAnchorDpDt;
                float f = (fArr2[0] * minSize2) + (this.mTouchDirectionY * fArr2[1]);
                if (minSize2 != 0.0f) {
                    velocity = tvx / fArr2[0];
                } else {
                    velocity = tvy / fArr2[1];
                }
                if (!Float.isNaN(velocity)) {
                    pos = pos2 + (velocity / 3.0f);
                } else {
                    pos = pos2;
                }
                if (pos != 0.0f && pos != 1.0f && (i = this.mOnTouchUp) != 3) {
                    this.mMotionLayout.touchAnimateTo(i, ((double) pos) < 0.5d ? 0.0f : 1.0f, velocity);
                    if (0.0f >= currentPos || 1.0f <= currentPos) {
                        this.mMotionLayout.setState(MotionLayout.TransitionState.FINISHED);
                        return;
                    }
                    return;
                } else if (0.0f >= pos || 1.0f <= pos) {
                    this.mMotionLayout.setState(MotionLayout.TransitionState.FINISHED);
                    return;
                } else {
                    return;
                }
            case 2:
                float dy = event.getRawY() - this.mLastTouchY;
                float dx = event.getRawX() - this.mLastTouchX;
                if (Math.abs((this.mTouchDirectionX * dx) + (this.mTouchDirectionY * dy)) > this.mDragThreshold || this.mDragStarted) {
                    float pos3 = this.mMotionLayout.getProgress();
                    if (!this.mDragStarted) {
                        this.mDragStarted = true;
                        this.mMotionLayout.setProgress(pos3);
                    }
                    int i3 = this.mTouchAnchorId;
                    if (i3 != -1) {
                        this.mMotionLayout.getAnchorDpDt(i3, pos3, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                    } else {
                        float minSize3 = (float) Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                        float[] fArr3 = this.mAnchorDpDt;
                        fArr3[1] = this.mTouchDirectionY * minSize3;
                        fArr3[0] = this.mTouchDirectionX * minSize3;
                    }
                    float minSize4 = this.mTouchDirectionX;
                    float[] fArr4 = this.mAnchorDpDt;
                    if (((double) Math.abs(((minSize4 * fArr4[0]) + (this.mTouchDirectionY * fArr4[1])) * this.mDragScale)) < 0.01d) {
                        float[] fArr5 = this.mAnchorDpDt;
                        fArr5[0] = 0.01f;
                        fArr5[1] = 0.01f;
                    }
                    if (this.mTouchDirectionX != 0.0f) {
                        change = dx / this.mAnchorDpDt[0];
                    } else {
                        change = dy / this.mAnchorDpDt[1];
                    }
                    float pos4 = Math.max(Math.min(pos3 + change, 1.0f), 0.0f);
                    if (pos4 != this.mMotionLayout.getProgress()) {
                        this.mMotionLayout.setProgress(pos4);
                        motionTracker.computeCurrentVelocity(1000);
                        this.mMotionLayout.mLastVelocity = this.mTouchDirectionX != 0.0f ? velocityTracker.getXVelocity() / this.mAnchorDpDt[0] : velocityTracker.getYVelocity() / this.mAnchorDpDt[1];
                    } else {
                        this.mMotionLayout.mLastVelocity = 0.0f;
                    }
                    this.mLastTouchX = event.getRawX();
                    this.mLastTouchY = event.getRawY();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void setDown(float lastTouchX, float lastTouchY) {
        this.mLastTouchX = lastTouchX;
        this.mLastTouchY = lastTouchY;
    }

    /* access modifiers changed from: package-private */
    public float getProgressDirection(float dx, float dy) {
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, this.mMotionLayout.getProgress(), this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f = this.mTouchDirectionX;
        if (f != 0.0f) {
            float[] fArr = this.mAnchorDpDt;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f * dx) / fArr[0];
        }
        float[] fArr2 = this.mAnchorDpDt;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (this.mTouchDirectionY * dy) / fArr2[1];
    }

    /* access modifiers changed from: package-private */
    public void scrollUp(float dx, float dy) {
        float velocity;
        boolean z = false;
        this.mDragStarted = false;
        float pos = this.mMotionLayout.getProgress();
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, pos, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        float f2 = this.mTouchDirectionY;
        float f3 = (fArr[0] * f) + (fArr[1] * f2);
        float f4 = 0.0f;
        if (f != 0.0f) {
            velocity = (f * dx) / fArr[0];
        } else {
            velocity = (f2 * dy) / fArr[1];
        }
        if (!Float.isNaN(velocity)) {
            pos += velocity / 3.0f;
        }
        if (pos != 0.0f) {
            boolean z2 = pos != 1.0f;
            int i = this.mOnTouchUp;
            if (i != 3) {
                z = true;
            }
            if (z && z2) {
                MotionLayout motionLayout = this.mMotionLayout;
                if (((double) pos) >= 0.5d) {
                    f4 = 1.0f;
                }
                motionLayout.touchAnimateTo(i, f4, velocity);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void scrollMove(float dx, float dy) {
        float change;
        float f = (this.mTouchDirectionX * dx) + (this.mTouchDirectionY * dy);
        float pos = this.mMotionLayout.getProgress();
        if (!this.mDragStarted) {
            this.mDragStarted = true;
            this.mMotionLayout.setProgress(pos);
        }
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, pos, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f2 = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        if (((double) Math.abs((f2 * fArr[0]) + (this.mTouchDirectionY * fArr[1]))) < 0.01d) {
            float[] fArr2 = this.mAnchorDpDt;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f3 = this.mTouchDirectionX;
        if (f3 != 0.0f) {
            change = (f3 * dx) / this.mAnchorDpDt[0];
        } else {
            change = (this.mTouchDirectionY * dy) / this.mAnchorDpDt[1];
        }
        float pos2 = Math.max(Math.min(pos + change, 1.0f), 0.0f);
        if (pos2 != this.mMotionLayout.getProgress()) {
            this.mMotionLayout.setProgress(pos2);
        }
    }

    /* access modifiers changed from: package-private */
    public void setupTouch() {
        View view = null;
        int i = this.mTouchAnchorId;
        if (i != -1 && (view = this.mMotionLayout.findViewById(i)) == null) {
            Log.e(TAG, "cannot find TouchAnchorId @id/" + Debug.getName(this.mMotionLayout.getContext(), this.mTouchAnchorId));
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView sv = (NestedScrollView) view;
            sv.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
            sv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                }
            });
        }
    }

    public void setAnchorId(int id) {
        this.mTouchAnchorId = id;
    }

    public int getAnchorId() {
        return this.mTouchAnchorId;
    }

    public void setTouchAnchorLocation(float x, float y) {
        this.mTouchAnchorX = x;
        this.mTouchAnchorY = y;
    }

    public void setMaxVelocity(float velocity) {
        this.mMaxVelocity = velocity;
    }

    public void setMaxAcceleration(float acceleration) {
        this.mMaxAcceleration = acceleration;
    }

    /* access modifiers changed from: package-private */
    public float getMaxAcceleration() {
        return this.mMaxAcceleration;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    /* access modifiers changed from: package-private */
    public boolean getMoveWhenScrollAtTop() {
        return this.mMoveWhenScrollAtTop;
    }

    /* access modifiers changed from: package-private */
    public RectF getTouchRegion(ViewGroup layout, RectF rect) {
        View view;
        int i = this.mTouchRegionId;
        if (i == -1 || (view = layout.findViewById(i)) == null) {
            return null;
        }
        rect.set((float) view.getLeft(), (float) view.getTop(), (float) view.getRight(), (float) view.getBottom());
        return rect;
    }

    /* access modifiers changed from: package-private */
    public int getTouchRegionId() {
        return this.mTouchRegionId;
    }

    /* access modifiers changed from: package-private */
    public RectF getLimitBoundsTo(ViewGroup layout, RectF rect) {
        View view;
        int i = this.mLimitBoundsTo;
        if (i == -1 || (view = layout.findViewById(i)) == null) {
            return null;
        }
        rect.set((float) view.getLeft(), (float) view.getTop(), (float) view.getRight(), (float) view.getBottom());
        return rect;
    }

    /* access modifiers changed from: package-private */
    public int getLimitBoundsToId() {
        return this.mLimitBoundsTo;
    }

    /* access modifiers changed from: package-private */
    public float dot(float dx, float dy) {
        return (this.mTouchDirectionX * dx) + (this.mTouchDirectionY * dy);
    }

    public String toString() {
        return this.mTouchDirectionX + " , " + this.mTouchDirectionY;
    }

    public int getFlags() {
        return this.mFlags;
    }
}
