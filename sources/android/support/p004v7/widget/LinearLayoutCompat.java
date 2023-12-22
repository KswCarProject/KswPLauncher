package android.support.p004v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import android.support.p001v4.view.GravityCompat;
import android.support.p001v4.view.InputDeviceCompat;
import android.support.p001v4.view.ViewCompat;
import android.support.p004v7.appcompat.C0365R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v7.widget.LinearLayoutCompat */
/* loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v7.widget.LinearLayoutCompat$DividerMode */
    /* loaded from: classes.dex */
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v7.widget.LinearLayoutCompat$OrientationMode */
    /* loaded from: classes.dex */
    public @interface OrientationMode {
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, C0365R.styleable.LinearLayoutCompat, defStyleAttr, 0);
        int index = a.getInt(C0365R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (index >= 0) {
            setOrientation(index);
        }
        int index2 = a.getInt(C0365R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (index2 >= 0) {
            setGravity(index2);
        }
        boolean baselineAligned = a.getBoolean(C0365R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!baselineAligned) {
            setBaselineAligned(baselineAligned);
        }
        this.mWeightSum = a.getFloat(C0365R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = a.getInt(C0365R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = a.getBoolean(C0365R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a.getDrawable(C0365R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = a.getInt(C0365R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = a.getDimensionPixelSize(C0365R.styleable.LinearLayoutCompat_dividerPadding, 0);
        a.recycle();
    }

    public void setShowDividers(int showDividers) {
        if (showDividers != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = showDividers;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable divider) {
        if (divider == this.mDivider) {
            return;
        }
        this.mDivider = divider;
        if (divider != null) {
            this.mDividerWidth = divider.getIntrinsicWidth();
            this.mDividerHeight = divider.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(divider == null);
        requestLayout();
    }

    public void setDividerPadding(int padding) {
        this.mDividerPadding = padding;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int bottom;
        int count = getVirtualChildCount();
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (child != null && child.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int top = (child.getTop() - lp.topMargin) - this.mDividerHeight;
                drawHorizontalDivider(canvas, top);
            }
        }
        if (hasDividerBeforeChildAt(count)) {
            View child2 = getVirtualChildAt(count - 1);
            if (child2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                int bottom2 = child2.getBottom() + lp2.bottomMargin;
                bottom = bottom2;
            }
            drawHorizontalDivider(canvas, bottom);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int position;
        int position2;
        int count = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (child != null && child.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (isLayoutRtl) {
                    position2 = child.getRight() + lp.rightMargin;
                } else {
                    int position3 = child.getLeft();
                    position2 = (position3 - lp.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, position2);
            }
        }
        if (hasDividerBeforeChildAt(count)) {
            View child2 = getVirtualChildAt(count - 1);
            if (child2 == null) {
                if (isLayoutRtl) {
                    position = getPaddingLeft();
                } else {
                    int position4 = getWidth();
                    position = (position4 - getPaddingRight()) - this.mDividerWidth;
                }
            } else {
                LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                if (isLayoutRtl) {
                    position = (child2.getLeft() - lp2.leftMargin) - this.mDividerWidth;
                } else {
                    int position5 = child2.getRight();
                    position = position5 + lp2.rightMargin;
                }
            }
            drawVerticalDivider(canvas, position);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int top) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, top, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + top);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int left) {
        this.mDivider.setBounds(left, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + left, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean baselineAligned) {
        this.mBaselineAligned = baselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean enabled) {
        this.mUseLargestChild = enabled;
    }

    @Override // android.view.View
    public int getBaseline() {
        int majorGravity;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i = this.mBaselineAlignedChildIndex;
        if (childCount <= i) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View child = getChildAt(i);
        int childBaseline = child.getBaseline();
        if (childBaseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int childTop = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (majorGravity = this.mGravity & 112) != 48) {
            switch (majorGravity) {
                case 16:
                    childTop += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                    break;
                case 80:
                    childTop = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                    break;
            }
        }
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        return lp.topMargin + childTop + childBaseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    View getVirtualChildAt(int index) {
        return getChildAt(index);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float weightSum) {
        this.mWeightSum = Math.max(0.0f, weightSum);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mOrientation == 1) {
            measureVertical(widthMeasureSpec, heightMeasureSpec);
        } else {
            measureHorizontal(widthMeasureSpec, heightMeasureSpec);
        }
    }

    protected boolean hasDividerBeforeChildAt(int childIndex) {
        if (childIndex == 0) {
            return (this.mShowDividers & 1) != 0;
        } else if (childIndex == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            for (int i = childIndex - 1; i >= 0; i--) {
                if (getChildAt(i).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:159:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:196:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void measureVertical(int widthMeasureSpec, int heightMeasureSpec) {
        int count;
        int childState;
        int heightMode;
        float totalWeight;
        int delta;
        int delta2;
        int heightMode2;
        int baselineChildIndex;
        int delta3;
        float weightSum;
        boolean matchWidthLocally;
        int allFillParent;
        int alternativeMaxWidth;
        int alternativeMaxWidth2;
        int heightSize;
        int weightedMaxWidth;
        int childState2;
        int oldHeight;
        int i;
        int heightMode3;
        LayoutParams lp;
        int count2;
        int count3;
        int weightedMaxWidth2;
        int alternativeMaxWidth3;
        int childState3;
        View child;
        int largestChildHeight;
        int i2;
        int weightedMaxWidth3;
        this.mTotalLength = 0;
        int count4 = getVirtualChildCount();
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode4 = View.MeasureSpec.getMode(heightMeasureSpec);
        int baselineChildIndex2 = this.mBaselineAlignedChildIndex;
        boolean useLargestChild = this.mUseLargestChild;
        boolean skippedMeasure = false;
        int weightedMaxWidth4 = 0;
        float totalWeight2 = 0.0f;
        int measuredWidth = 0;
        int alternativeMaxWidth4 = 0;
        boolean matchWidth = false;
        int maxWidth = 0;
        int i3 = 0;
        int weightedMaxWidth5 = 0;
        int largestChildHeight2 = 1;
        while (true) {
            int weightedMaxWidth6 = i3;
            if (alternativeMaxWidth4 < count4) {
                View child2 = getVirtualChildAt(alternativeMaxWidth4);
                if (child2 == null) {
                    this.mTotalLength += measureNullChild(alternativeMaxWidth4);
                    count2 = count4;
                    heightMode3 = heightMode4;
                    i3 = weightedMaxWidth6;
                } else {
                    int largestChildHeight3 = weightedMaxWidth5;
                    int largestChildHeight4 = child2.getVisibility();
                    if (largestChildHeight4 == 8) {
                        alternativeMaxWidth4 += getChildrenSkipCount(child2, alternativeMaxWidth4);
                        count2 = count4;
                        i3 = weightedMaxWidth6;
                        weightedMaxWidth5 = largestChildHeight3;
                        heightMode3 = heightMode4;
                    } else {
                        if (hasDividerBeforeChildAt(alternativeMaxWidth4)) {
                            this.mTotalLength += this.mDividerHeight;
                        }
                        LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                        float totalWeight3 = totalWeight2 + lp2.weight;
                        if (heightMode4 != 1073741824 || lp2.height != 0 || lp2.weight <= 0.0f) {
                            int i4 = alternativeMaxWidth4;
                            if (lp2.height == 0 && lp2.weight > 0.0f) {
                                lp2.height = -2;
                                oldHeight = 0;
                            } else {
                                oldHeight = Integer.MIN_VALUE;
                            }
                            int oldHeight2 = oldHeight;
                            i = i4;
                            heightMode3 = heightMode4;
                            lp = lp2;
                            count2 = count4;
                            count3 = 1073741824;
                            weightedMaxWidth2 = weightedMaxWidth6;
                            alternativeMaxWidth3 = measuredWidth;
                            childState3 = maxWidth;
                            int childState4 = totalWeight3 == 0.0f ? this.mTotalLength : 0;
                            measureChildBeforeLayout(child2, i, widthMeasureSpec, 0, heightMeasureSpec, childState4);
                            if (oldHeight2 != Integer.MIN_VALUE) {
                                lp.height = oldHeight2;
                            }
                            int childHeight = child2.getMeasuredHeight();
                            int totalLength = this.mTotalLength;
                            child = child2;
                            this.mTotalLength = Math.max(totalLength, totalLength + childHeight + lp.topMargin + lp.bottomMargin + getNextLocationOffset(child));
                            if (!useLargestChild) {
                                largestChildHeight = largestChildHeight3;
                            } else {
                                largestChildHeight = Math.max(childHeight, largestChildHeight3);
                            }
                        } else {
                            int totalLength2 = this.mTotalLength;
                            int i5 = alternativeMaxWidth4;
                            int i6 = lp2.bottomMargin;
                            this.mTotalLength = Math.max(totalLength2, lp2.topMargin + totalLength2 + i6);
                            skippedMeasure = true;
                            alternativeMaxWidth3 = measuredWidth;
                            childState3 = maxWidth;
                            count2 = count4;
                            weightedMaxWidth2 = weightedMaxWidth6;
                            largestChildHeight = largestChildHeight3;
                            i = i5;
                            count3 = BasicMeasure.EXACTLY;
                            heightMode3 = heightMode4;
                            lp = lp2;
                            child = child2;
                        }
                        if (baselineChildIndex2 >= 0) {
                            i2 = i;
                            if (baselineChildIndex2 == i2 + 1) {
                                this.mBaselineChildTop = this.mTotalLength;
                            }
                        } else {
                            i2 = i;
                        }
                        if (i2 < baselineChildIndex2 && lp.weight > 0.0f) {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                        boolean matchWidthLocally2 = false;
                        if (widthMode != count3 && lp.width == -1) {
                            matchWidth = true;
                            matchWidthLocally2 = true;
                        }
                        int margin = lp.leftMargin + lp.rightMargin;
                        int measuredWidth2 = child.getMeasuredWidth() + margin;
                        int maxWidth2 = Math.max(weightedMaxWidth4, measuredWidth2);
                        int childState5 = View.combineMeasuredStates(childState3, child.getMeasuredState());
                        int allFillParent2 = (largestChildHeight2 == 0 || lp.width != -1) ? 0 : 1;
                        if (lp.weight > 0.0f) {
                            weightedMaxWidth3 = Math.max(weightedMaxWidth2, matchWidthLocally2 ? margin : measuredWidth2);
                        } else {
                            weightedMaxWidth3 = weightedMaxWidth2;
                            alternativeMaxWidth3 = Math.max(alternativeMaxWidth3, matchWidthLocally2 ? margin : measuredWidth2);
                        }
                        int alternativeMaxWidth5 = getChildrenSkipCount(child, i2);
                        int i7 = alternativeMaxWidth5 + i2;
                        weightedMaxWidth5 = largestChildHeight;
                        largestChildHeight2 = allFillParent2;
                        i3 = weightedMaxWidth3;
                        totalWeight2 = totalWeight3;
                        measuredWidth = alternativeMaxWidth3;
                        alternativeMaxWidth4 = i7;
                        weightedMaxWidth4 = maxWidth2;
                        maxWidth = childState5;
                    }
                }
                alternativeMaxWidth4++;
                heightMode4 = heightMode3;
                count4 = count2;
            } else {
                int largestChildHeight5 = weightedMaxWidth5;
                int count5 = count4;
                int heightMode5 = heightMode4;
                int weightedMaxWidth7 = weightedMaxWidth6;
                int i8 = 8;
                int alternativeMaxWidth6 = measuredWidth;
                if (this.mTotalLength > 0) {
                    count = count5;
                    if (hasDividerBeforeChildAt(count)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                } else {
                    count = count5;
                }
                if (useLargestChild) {
                    heightMode = heightMode5;
                    if (heightMode == Integer.MIN_VALUE || heightMode == 0) {
                        this.mTotalLength = 0;
                        int i9 = 0;
                        while (i9 < count) {
                            View child3 = getVirtualChildAt(i9);
                            if (child3 == null) {
                                this.mTotalLength += measureNullChild(i9);
                                childState2 = maxWidth;
                            } else if (child3.getVisibility() == i8) {
                                i9 += getChildrenSkipCount(child3, i9);
                                childState2 = maxWidth;
                            } else {
                                LayoutParams lp3 = (LayoutParams) child3.getLayoutParams();
                                int totalLength3 = this.mTotalLength;
                                childState2 = maxWidth;
                                int childState6 = lp3.topMargin;
                                this.mTotalLength = Math.max(totalLength3, totalLength3 + largestChildHeight5 + childState6 + lp3.bottomMargin + getNextLocationOffset(child3));
                            }
                            i9++;
                            maxWidth = childState2;
                            i8 = 8;
                        }
                        childState = maxWidth;
                    } else {
                        childState = maxWidth;
                    }
                } else {
                    childState = maxWidth;
                    heightMode = heightMode5;
                }
                this.mTotalLength += getPaddingTop() + getPaddingBottom();
                int largestChildHeight6 = largestChildHeight5;
                int heightSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumHeight()), heightMeasureSpec, 0);
                int heightSize2 = heightSizeAndState & ViewCompat.MEASURED_SIZE_MASK;
                int delta4 = heightSize2 - this.mTotalLength;
                if (skippedMeasure) {
                    totalWeight = totalWeight2;
                } else if (delta4 == 0 || totalWeight2 <= 0.0f) {
                    int alternativeMaxWidth7 = Math.max(alternativeMaxWidth6, weightedMaxWidth7);
                    if (!useLargestChild || heightMode == 1073741824) {
                        alternativeMaxWidth = alternativeMaxWidth7;
                    } else {
                        int i10 = 0;
                        while (i10 < count) {
                            float totalWeight4 = totalWeight2;
                            View child4 = getVirtualChildAt(i10);
                            if (child4 != null) {
                                alternativeMaxWidth2 = alternativeMaxWidth7;
                                int alternativeMaxWidth8 = child4.getVisibility();
                                heightSize = heightSize2;
                                if (alternativeMaxWidth8 == 8) {
                                    weightedMaxWidth = weightedMaxWidth7;
                                } else if (((LayoutParams) child4.getLayoutParams()).weight > 0.0f) {
                                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(child4.getMeasuredWidth(), BasicMeasure.EXACTLY);
                                    weightedMaxWidth = weightedMaxWidth7;
                                    int weightedMaxWidth8 = View.MeasureSpec.makeMeasureSpec(largestChildHeight6, BasicMeasure.EXACTLY);
                                    child4.measure(makeMeasureSpec, weightedMaxWidth8);
                                } else {
                                    weightedMaxWidth = weightedMaxWidth7;
                                }
                            } else {
                                alternativeMaxWidth2 = alternativeMaxWidth7;
                                heightSize = heightSize2;
                                weightedMaxWidth = weightedMaxWidth7;
                            }
                            i10++;
                            alternativeMaxWidth7 = alternativeMaxWidth2;
                            totalWeight2 = totalWeight4;
                            heightSize2 = heightSize;
                            weightedMaxWidth7 = weightedMaxWidth;
                        }
                        alternativeMaxWidth = alternativeMaxWidth7;
                    }
                    delta = widthMeasureSpec;
                    alternativeMaxWidth6 = alternativeMaxWidth;
                    delta2 = childState;
                    if (largestChildHeight2 == 0 && widthMode != 1073741824) {
                        weightedMaxWidth4 = alternativeMaxWidth6;
                    }
                    int maxWidth3 = weightedMaxWidth4 + getPaddingLeft() + getPaddingRight();
                    setMeasuredDimension(View.resolveSizeAndState(Math.max(maxWidth3, getSuggestedMinimumWidth()), delta, delta2), heightSizeAndState);
                    if (!matchWidth) {
                        forceUniformWidth(count, heightMeasureSpec);
                        return;
                    }
                    return;
                } else {
                    totalWeight = totalWeight2;
                }
                float weightSum2 = this.mWeightSum;
                if (weightSum2 <= 0.0f) {
                    weightSum2 = totalWeight;
                }
                this.mTotalLength = 0;
                int i11 = 0;
                int delta5 = delta4;
                delta2 = childState;
                while (i11 < count) {
                    View child5 = getVirtualChildAt(i11);
                    int largestChildHeight7 = largestChildHeight6;
                    int largestChildHeight8 = child5.getVisibility();
                    boolean useLargestChild2 = useLargestChild;
                    if (largestChildHeight8 == 8) {
                        heightMode2 = heightMode;
                        delta3 = delta5;
                        baselineChildIndex = baselineChildIndex2;
                    } else {
                        LayoutParams lp4 = (LayoutParams) child5.getLayoutParams();
                        float childExtra = lp4.weight;
                        if (childExtra <= 0.0f) {
                            heightMode2 = heightMode;
                            int heightMode6 = delta5;
                            baselineChildIndex = baselineChildIndex2;
                            delta3 = heightMode6;
                        } else {
                            baselineChildIndex = baselineChildIndex2;
                            int share = (int) ((delta5 * childExtra) / weightSum2);
                            float weightSum3 = weightSum2 - childExtra;
                            delta3 = delta5 - share;
                            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp4.leftMargin + lp4.rightMargin, lp4.width);
                            if (lp4.height != 0) {
                                heightMode2 = heightMode;
                            } else if (heightMode != 1073741824) {
                                heightMode2 = heightMode;
                            } else {
                                heightMode2 = heightMode;
                                child5.measure(childWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec(share > 0 ? share : 0, BasicMeasure.EXACTLY));
                                delta2 = View.combineMeasuredStates(delta2, child5.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                                weightSum2 = weightSum3;
                            }
                            int heightMode7 = child5.getMeasuredHeight();
                            int childHeight2 = heightMode7 + share;
                            if (childHeight2 < 0) {
                                childHeight2 = 0;
                            }
                            child5.measure(childWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec(childHeight2, BasicMeasure.EXACTLY));
                            delta2 = View.combineMeasuredStates(delta2, child5.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                            weightSum2 = weightSum3;
                        }
                        int margin2 = lp4.leftMargin + lp4.rightMargin;
                        int measuredWidth3 = child5.getMeasuredWidth() + margin2;
                        weightedMaxWidth4 = Math.max(weightedMaxWidth4, measuredWidth3);
                        if (widthMode != 1073741824) {
                            weightSum = weightSum2;
                            if (lp4.width == -1) {
                                matchWidthLocally = true;
                                int alternativeMaxWidth9 = Math.max(alternativeMaxWidth6, !matchWidthLocally ? margin2 : measuredWidth3);
                                if (largestChildHeight2 != 0 && lp4.width == -1) {
                                    allFillParent = 1;
                                    int totalLength4 = this.mTotalLength;
                                    int alternativeMaxWidth10 = lp4.topMargin;
                                    this.mTotalLength = Math.max(totalLength4, totalLength4 + child5.getMeasuredHeight() + alternativeMaxWidth10 + lp4.bottomMargin + getNextLocationOffset(child5));
                                    largestChildHeight2 = allFillParent;
                                    weightSum2 = weightSum;
                                    alternativeMaxWidth6 = alternativeMaxWidth9;
                                }
                                allFillParent = 0;
                                int totalLength42 = this.mTotalLength;
                                int alternativeMaxWidth102 = lp4.topMargin;
                                this.mTotalLength = Math.max(totalLength42, totalLength42 + child5.getMeasuredHeight() + alternativeMaxWidth102 + lp4.bottomMargin + getNextLocationOffset(child5));
                                largestChildHeight2 = allFillParent;
                                weightSum2 = weightSum;
                                alternativeMaxWidth6 = alternativeMaxWidth9;
                            }
                        } else {
                            weightSum = weightSum2;
                        }
                        matchWidthLocally = false;
                        int alternativeMaxWidth92 = Math.max(alternativeMaxWidth6, !matchWidthLocally ? margin2 : measuredWidth3);
                        if (largestChildHeight2 != 0) {
                            allFillParent = 1;
                            int totalLength422 = this.mTotalLength;
                            int alternativeMaxWidth1022 = lp4.topMargin;
                            this.mTotalLength = Math.max(totalLength422, totalLength422 + child5.getMeasuredHeight() + alternativeMaxWidth1022 + lp4.bottomMargin + getNextLocationOffset(child5));
                            largestChildHeight2 = allFillParent;
                            weightSum2 = weightSum;
                            alternativeMaxWidth6 = alternativeMaxWidth92;
                        }
                        allFillParent = 0;
                        int totalLength4222 = this.mTotalLength;
                        int alternativeMaxWidth10222 = lp4.topMargin;
                        this.mTotalLength = Math.max(totalLength4222, totalLength4222 + child5.getMeasuredHeight() + alternativeMaxWidth10222 + lp4.bottomMargin + getNextLocationOffset(child5));
                        largestChildHeight2 = allFillParent;
                        weightSum2 = weightSum;
                        alternativeMaxWidth6 = alternativeMaxWidth92;
                    }
                    i11++;
                    largestChildHeight6 = largestChildHeight7;
                    useLargestChild = useLargestChild2;
                    baselineChildIndex2 = baselineChildIndex;
                    delta5 = delta3;
                    heightMode = heightMode2;
                }
                delta = widthMeasureSpec;
                this.mTotalLength += getPaddingTop() + getPaddingBottom();
                if (largestChildHeight2 == 0) {
                    weightedMaxWidth4 = alternativeMaxWidth6;
                }
                int maxWidth32 = weightedMaxWidth4 + getPaddingLeft() + getPaddingRight();
                setMeasuredDimension(View.resolveSizeAndState(Math.max(maxWidth32, getSuggestedMinimumWidth()), delta, delta2), heightSizeAndState);
                if (!matchWidth) {
                }
            }
        }
    }

    private void forceUniformWidth(int count, int heightMeasureSpec) {
        int uniformMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), BasicMeasure.EXACTLY);
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.width == -1) {
                    int oldHeight = lp.height;
                    lp.height = child.getMeasuredHeight();
                    measureChildWithMargins(child, uniformMeasureSpec, 0, heightMeasureSpec, 0);
                    lp.height = oldHeight;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:206:0x0553  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x058b  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0642  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x064a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) {
        int count;
        int childState;
        int descent;
        int maxHeight;
        int widthMode;
        float totalWeight;
        int largestChildWidth;
        int count2;
        int widthSizeAndState;
        int delta;
        int childState2;
        int widthMode2;
        int alternativeMaxHeight;
        int maxHeight2;
        int count3;
        int widthMode3;
        int widthSizeAndState2;
        int count4;
        boolean useLargestChild;
        int delta2;
        int alternativeMaxHeight2;
        boolean allFillParent;
        int delta3;
        int alternativeMaxHeight3;
        int alternativeMaxHeight4;
        int largestChildWidth2;
        int widthSize;
        int maxHeight3;
        int i;
        int i2;
        int oldWidth;
        int weightedMaxHeight;
        int alternativeMaxHeight5;
        int childState3;
        int largestChildWidth3;
        int widthMode4;
        boolean baselineAligned;
        int count5;
        int count6;
        LayoutParams lp;
        int largestChildWidth4;
        int margin;
        int largestChildWidth5;
        int weightedMaxHeight2;
        int alternativeMaxHeight6;
        this.mTotalLength = 0;
        int count7 = getVirtualChildCount();
        int widthMode5 = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] maxAscent = this.mMaxAscent;
        int[] maxDescent = this.mMaxDescent;
        maxAscent[3] = -1;
        maxAscent[2] = -1;
        maxAscent[1] = -1;
        maxAscent[0] = -1;
        maxDescent[3] = -1;
        maxDescent[2] = -1;
        maxDescent[1] = -1;
        maxDescent[0] = -1;
        boolean baselineAligned2 = this.mBaselineAligned;
        boolean useLargestChild2 = this.mUseLargestChild;
        boolean isExactly = widthMode5 == 1073741824;
        int i3 = 0;
        int childState4 = 0;
        float totalWeight2 = 0.0f;
        int childHeight = 0;
        int childState5 = 0;
        int largestChildWidth6 = 0;
        boolean skippedMeasure = false;
        boolean matchHeight = true;
        int weightedMaxHeight3 = 0;
        int alternativeMaxHeight7 = 0;
        while (i3 < count7) {
            View child = getVirtualChildAt(i3);
            if (child == null) {
                int largestChildWidth7 = childState5;
                int largestChildWidth8 = this.mTotalLength;
                this.mTotalLength = largestChildWidth8 + measureNullChild(i3);
                baselineAligned = baselineAligned2;
                count5 = count7;
                childState5 = largestChildWidth7;
                largestChildWidth3 = widthMode5;
            } else {
                int largestChildWidth9 = childState5;
                int largestChildWidth10 = child.getVisibility();
                int weightedMaxHeight4 = alternativeMaxHeight7;
                if (largestChildWidth10 == 8) {
                    i3 += getChildrenSkipCount(child, i3);
                    baselineAligned = baselineAligned2;
                    childState5 = largestChildWidth9;
                    alternativeMaxHeight7 = weightedMaxHeight4;
                    count5 = count7;
                    largestChildWidth3 = widthMode5;
                } else {
                    if (hasDividerBeforeChildAt(i3)) {
                        this.mTotalLength += this.mDividerWidth;
                    }
                    LayoutParams lp2 = (LayoutParams) child.getLayoutParams();
                    float totalWeight3 = totalWeight2 + lp2.weight;
                    if (widthMode5 != 1073741824 || lp2.width != 0 || lp2.weight <= 0.0f) {
                        int alternativeMaxHeight8 = weightedMaxHeight3;
                        if (lp2.width == 0 && lp2.weight > 0.0f) {
                            lp2.width = -2;
                            oldWidth = 0;
                        } else {
                            oldWidth = Integer.MIN_VALUE;
                        }
                        int oldWidth2 = (totalWeight3 > 0.0f ? 1 : (totalWeight3 == 0.0f ? 0 : -1));
                        weightedMaxHeight = weightedMaxHeight4;
                        int oldWidth3 = oldWidth;
                        alternativeMaxHeight5 = alternativeMaxHeight8;
                        childState3 = childHeight;
                        int childState6 = oldWidth2 == 0 ? this.mTotalLength : 0;
                        largestChildWidth3 = widthMode5;
                        widthMode4 = childState4;
                        baselineAligned = baselineAligned2;
                        count5 = count7;
                        count6 = -1;
                        measureChildBeforeLayout(child, i3, widthMeasureSpec, childState6, heightMeasureSpec, 0);
                        if (oldWidth3 == Integer.MIN_VALUE) {
                            lp = lp2;
                        } else {
                            lp = lp2;
                            lp.width = oldWidth3;
                        }
                        int childWidth = child.getMeasuredWidth();
                        if (isExactly) {
                            this.mTotalLength += lp.leftMargin + childWidth + lp.rightMargin + getNextLocationOffset(child);
                        } else {
                            int totalLength = this.mTotalLength;
                            this.mTotalLength = Math.max(totalLength, totalLength + childWidth + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child));
                        }
                        if (!useLargestChild2) {
                            largestChildWidth4 = largestChildWidth9;
                        } else {
                            largestChildWidth4 = Math.max(childWidth, largestChildWidth9);
                        }
                    } else {
                        if (isExactly) {
                            int i4 = this.mTotalLength;
                            int i5 = lp2.leftMargin;
                            alternativeMaxHeight6 = weightedMaxHeight3;
                            int alternativeMaxHeight9 = lp2.rightMargin;
                            this.mTotalLength = i4 + i5 + alternativeMaxHeight9;
                        } else {
                            alternativeMaxHeight6 = weightedMaxHeight3;
                            int totalLength2 = this.mTotalLength;
                            this.mTotalLength = Math.max(totalLength2, lp2.leftMargin + totalLength2 + lp2.rightMargin);
                        }
                        if (baselineAligned2) {
                            int freeSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                            child.measure(freeSpec, freeSpec);
                            lp = lp2;
                            childState3 = childHeight;
                            baselineAligned = baselineAligned2;
                            largestChildWidth4 = largestChildWidth9;
                            weightedMaxHeight = weightedMaxHeight4;
                            alternativeMaxHeight5 = alternativeMaxHeight6;
                            count5 = count7;
                            largestChildWidth3 = widthMode5;
                            count6 = -1;
                            widthMode4 = childState4;
                        } else {
                            largestChildWidth6 = 1;
                            lp = lp2;
                            childState3 = childHeight;
                            baselineAligned = baselineAligned2;
                            largestChildWidth4 = largestChildWidth9;
                            weightedMaxHeight = weightedMaxHeight4;
                            alternativeMaxHeight5 = alternativeMaxHeight6;
                            count5 = count7;
                            largestChildWidth3 = widthMode5;
                            count6 = -1;
                            widthMode4 = childState4;
                        }
                    }
                    boolean matchHeightLocally = false;
                    if (heightMode != 1073741824 && lp.height == count6) {
                        skippedMeasure = true;
                        matchHeightLocally = true;
                    }
                    int margin2 = lp.topMargin + lp.bottomMargin;
                    int childHeight2 = child.getMeasuredHeight() + margin2;
                    int childState7 = View.combineMeasuredStates(childState3, child.getMeasuredState());
                    if (!baselineAligned) {
                        margin = margin2;
                        largestChildWidth5 = largestChildWidth4;
                    } else {
                        int childBaseline = child.getBaseline();
                        if (childBaseline == count6) {
                            margin = margin2;
                            largestChildWidth5 = largestChildWidth4;
                        } else {
                            int gravity = (lp.gravity < 0 ? this.mGravity : lp.gravity) & 112;
                            int index = ((gravity >> 4) & (-2)) >> 1;
                            margin = margin2;
                            maxAscent[index] = Math.max(maxAscent[index], childBaseline);
                            largestChildWidth5 = largestChildWidth4;
                            int largestChildWidth11 = childHeight2 - childBaseline;
                            maxDescent[index] = Math.max(maxDescent[index], largestChildWidth11);
                        }
                    }
                    int maxHeight4 = Math.max(widthMode4, childHeight2);
                    boolean allFillParent2 = matchHeight && lp.height == -1;
                    if (lp.weight > 0.0f) {
                        weightedMaxHeight2 = Math.max(weightedMaxHeight, matchHeightLocally ? margin : childHeight2);
                    } else {
                        int weightedMaxHeight5 = weightedMaxHeight;
                        alternativeMaxHeight5 = Math.max(alternativeMaxHeight5, matchHeightLocally ? margin : childHeight2);
                        weightedMaxHeight2 = weightedMaxHeight5;
                    }
                    int weightedMaxHeight6 = getChildrenSkipCount(child, i3);
                    i3 += weightedMaxHeight6;
                    matchHeight = allFillParent2;
                    childHeight = childState7;
                    totalWeight2 = totalWeight3;
                    childState5 = largestChildWidth5;
                    weightedMaxHeight3 = alternativeMaxHeight5;
                    childState4 = maxHeight4;
                    alternativeMaxHeight7 = weightedMaxHeight2;
                }
            }
            i3++;
            baselineAligned2 = baselineAligned;
            widthMode5 = largestChildWidth3;
            count7 = count5;
        }
        boolean baselineAligned3 = baselineAligned2;
        int count8 = count7;
        int widthMode6 = widthMode5;
        int weightedMaxHeight7 = alternativeMaxHeight7;
        int weightedMaxHeight8 = weightedMaxHeight3;
        int childState8 = childHeight;
        int widthMode7 = childState4;
        int largestChildWidth12 = childState5;
        int largestChildWidth13 = this.mTotalLength;
        if (largestChildWidth13 > 0) {
            count = count8;
            if (hasDividerBeforeChildAt(count)) {
                this.mTotalLength += this.mDividerWidth;
            }
        } else {
            count = count8;
        }
        if (maxAscent[1] == -1 && maxAscent[0] == -1 && maxAscent[2] == -1 && maxAscent[3] == -1) {
            childState = childState8;
            descent = widthMode7;
        } else {
            int ascent = Math.max(maxAscent[3], Math.max(maxAscent[0], Math.max(maxAscent[1], maxAscent[2])));
            int i6 = maxDescent[3];
            int i7 = maxDescent[0];
            int i8 = maxDescent[1];
            childState = childState8;
            int childState9 = maxDescent[2];
            int descent2 = Math.max(i6, Math.max(i7, Math.max(i8, childState9)));
            descent = Math.max(widthMode7, ascent + descent2);
        }
        if (useLargestChild2) {
            widthMode = widthMode6;
            if (widthMode == Integer.MIN_VALUE || widthMode == 0) {
                this.mTotalLength = 0;
                int i9 = 0;
                while (i9 < count) {
                    View child2 = getVirtualChildAt(i9);
                    if (child2 == null) {
                        this.mTotalLength += measureNullChild(i9);
                        maxHeight3 = descent;
                        i = i9;
                    } else if (child2.getVisibility() == 8) {
                        i2 = i9 + getChildrenSkipCount(child2, i9);
                        maxHeight3 = descent;
                        i9 = i2 + 1;
                        descent = maxHeight3;
                    } else {
                        LayoutParams lp3 = (LayoutParams) child2.getLayoutParams();
                        if (isExactly) {
                            int i10 = this.mTotalLength;
                            maxHeight3 = descent;
                            int maxHeight5 = lp3.leftMargin;
                            i = i9;
                            int i11 = lp3.rightMargin;
                            this.mTotalLength = i10 + maxHeight5 + largestChildWidth12 + i11 + getNextLocationOffset(child2);
                        } else {
                            maxHeight3 = descent;
                            i = i9;
                            int maxHeight6 = this.mTotalLength;
                            this.mTotalLength = Math.max(maxHeight6, maxHeight6 + largestChildWidth12 + lp3.leftMargin + lp3.rightMargin + getNextLocationOffset(child2));
                        }
                    }
                    i2 = i;
                    i9 = i2 + 1;
                    descent = maxHeight3;
                }
                maxHeight = descent;
            } else {
                maxHeight = descent;
            }
        } else {
            maxHeight = descent;
            widthMode = widthMode6;
        }
        int maxHeight7 = this.mTotalLength;
        this.mTotalLength = maxHeight7 + getPaddingLeft() + getPaddingRight();
        int widthSizeAndState3 = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumWidth()), widthMeasureSpec, 0);
        int widthSize2 = widthSizeAndState3 & ViewCompat.MEASURED_SIZE_MASK;
        int delta4 = widthSize2 - this.mTotalLength;
        if (largestChildWidth6 != 0) {
            totalWeight = totalWeight2;
            largestChildWidth = weightedMaxHeight8;
        } else if (delta4 == 0 || totalWeight2 <= 0.0f) {
            int alternativeMaxHeight10 = Math.max(weightedMaxHeight8, weightedMaxHeight7);
            if (!useLargestChild2) {
                alternativeMaxHeight3 = alternativeMaxHeight10;
            } else if (widthMode != 1073741824) {
                int i12 = 0;
                while (i12 < count) {
                    float totalWeight4 = totalWeight2;
                    View child3 = getVirtualChildAt(i12);
                    if (child3 != null) {
                        alternativeMaxHeight4 = alternativeMaxHeight10;
                        int alternativeMaxHeight11 = child3.getVisibility();
                        widthSize = widthSize2;
                        if (alternativeMaxHeight11 == 8) {
                            largestChildWidth2 = largestChildWidth12;
                        } else if (((LayoutParams) child3.getLayoutParams()).weight > 0.0f) {
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(largestChildWidth12, BasicMeasure.EXACTLY);
                            largestChildWidth2 = largestChildWidth12;
                            int largestChildWidth14 = child3.getMeasuredHeight();
                            child3.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(largestChildWidth14, BasicMeasure.EXACTLY));
                        } else {
                            largestChildWidth2 = largestChildWidth12;
                        }
                    } else {
                        alternativeMaxHeight4 = alternativeMaxHeight10;
                        largestChildWidth2 = largestChildWidth12;
                        widthSize = widthSize2;
                    }
                    i12++;
                    alternativeMaxHeight10 = alternativeMaxHeight4;
                    totalWeight2 = totalWeight4;
                    widthSize2 = widthSize;
                    largestChildWidth12 = largestChildWidth2;
                }
                alternativeMaxHeight3 = alternativeMaxHeight10;
            } else {
                alternativeMaxHeight3 = alternativeMaxHeight10;
            }
            delta = heightMeasureSpec;
            count2 = count;
            widthSizeAndState = widthSizeAndState3;
            alternativeMaxHeight = alternativeMaxHeight3;
            childState2 = maxHeight;
            widthMode2 = childState;
            if (!matchHeight && heightMode != 1073741824) {
                childState2 = alternativeMaxHeight;
            }
            int maxHeight8 = childState2 + getPaddingTop() + getPaddingBottom();
            setMeasuredDimension(widthSizeAndState | ((-16777216) & widthMode2), View.resolveSizeAndState(Math.max(maxHeight8, getSuggestedMinimumHeight()), delta, widthMode2 << 16));
            if (!skippedMeasure) {
                forceUniformHeight(count2, widthMeasureSpec);
                return;
            }
            return;
        } else {
            totalWeight = totalWeight2;
            largestChildWidth = weightedMaxHeight8;
        }
        float weightSum = this.mWeightSum;
        if (weightSum <= 0.0f) {
            weightSum = totalWeight;
        }
        maxAscent[3] = -1;
        maxAscent[2] = -1;
        maxAscent[1] = -1;
        maxAscent[0] = -1;
        maxDescent[3] = -1;
        maxDescent[2] = -1;
        maxDescent[1] = -1;
        maxDescent[0] = -1;
        this.mTotalLength = 0;
        int i13 = 0;
        int delta5 = delta4;
        int maxHeight9 = -1;
        int childState10 = childState;
        while (i13 < count) {
            int weightedMaxHeight9 = weightedMaxHeight7;
            View child4 = getVirtualChildAt(i13);
            if (child4 != null) {
                useLargestChild = useLargestChild2;
                count3 = count;
                if (child4.getVisibility() == 8) {
                    widthMode3 = widthMode;
                    widthSizeAndState2 = widthSizeAndState3;
                    count4 = delta5;
                } else {
                    LayoutParams lp4 = (LayoutParams) child4.getLayoutParams();
                    float childExtra = lp4.weight;
                    if (childExtra > 0.0f) {
                        int share = (int) ((delta5 * childExtra) / weightSum);
                        float weightSum2 = weightSum - childExtra;
                        int delta6 = delta5 - share;
                        widthSizeAndState2 = widthSizeAndState3;
                        int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom() + lp4.topMargin + lp4.bottomMargin, lp4.height);
                        if (lp4.width == 0 && widthMode == 1073741824) {
                            child4.measure(View.MeasureSpec.makeMeasureSpec(share > 0 ? share : 0, BasicMeasure.EXACTLY), childHeightMeasureSpec);
                            widthMode3 = widthMode;
                        } else {
                            int childWidth2 = child4.getMeasuredWidth() + share;
                            if (childWidth2 < 0) {
                                childWidth2 = 0;
                            }
                            widthMode3 = widthMode;
                            child4.measure(View.MeasureSpec.makeMeasureSpec(childWidth2, BasicMeasure.EXACTLY), childHeightMeasureSpec);
                        }
                        childState10 = View.combineMeasuredStates(childState10, child4.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                        weightSum = weightSum2;
                        delta2 = delta6;
                    } else {
                        widthMode3 = widthMode;
                        widthSizeAndState2 = widthSizeAndState3;
                        delta2 = delta5;
                    }
                    if (isExactly) {
                        this.mTotalLength += child4.getMeasuredWidth() + lp4.leftMargin + lp4.rightMargin + getNextLocationOffset(child4);
                    } else {
                        int totalLength3 = this.mTotalLength;
                        this.mTotalLength = Math.max(totalLength3, child4.getMeasuredWidth() + totalLength3 + lp4.leftMargin + lp4.rightMargin + getNextLocationOffset(child4));
                    }
                    boolean matchHeightLocally2 = heightMode != 1073741824 && lp4.height == -1;
                    int margin3 = lp4.topMargin + lp4.bottomMargin;
                    int childHeight3 = child4.getMeasuredHeight() + margin3;
                    maxHeight9 = Math.max(maxHeight9, childHeight3);
                    float weightSum3 = weightSum;
                    int alternativeMaxHeight12 = Math.max(largestChildWidth, matchHeightLocally2 ? margin3 : childHeight3);
                    if (matchHeight) {
                        alternativeMaxHeight2 = alternativeMaxHeight12;
                        if (lp4.height == -1) {
                            allFillParent = true;
                            if (baselineAligned3) {
                                matchHeight = allFillParent;
                                delta3 = delta2;
                            } else {
                                int childBaseline2 = child4.getBaseline();
                                matchHeight = allFillParent;
                                if (childBaseline2 == -1) {
                                    delta3 = delta2;
                                } else {
                                    int gravity2 = (lp4.gravity < 0 ? this.mGravity : lp4.gravity) & 112;
                                    int index2 = ((gravity2 >> 4) & (-2)) >> 1;
                                    int gravity3 = maxAscent[index2];
                                    maxAscent[index2] = Math.max(gravity3, childBaseline2);
                                    delta3 = delta2;
                                    maxDescent[index2] = Math.max(maxDescent[index2], childHeight3 - childBaseline2);
                                }
                            }
                            weightSum = weightSum3;
                            largestChildWidth = alternativeMaxHeight2;
                            count4 = delta3;
                        }
                    } else {
                        alternativeMaxHeight2 = alternativeMaxHeight12;
                    }
                    allFillParent = false;
                    if (baselineAligned3) {
                    }
                    weightSum = weightSum3;
                    largestChildWidth = alternativeMaxHeight2;
                    count4 = delta3;
                }
            } else {
                count3 = count;
                widthMode3 = widthMode;
                widthSizeAndState2 = widthSizeAndState3;
                count4 = delta5;
                useLargestChild = useLargestChild2;
            }
            i13++;
            delta5 = count4;
            widthSizeAndState3 = widthSizeAndState2;
            useLargestChild2 = useLargestChild;
            count = count3;
            weightedMaxHeight7 = weightedMaxHeight9;
            widthMode = widthMode3;
        }
        count2 = count;
        widthSizeAndState = widthSizeAndState3;
        delta = heightMeasureSpec;
        int i14 = this.mTotalLength;
        this.mTotalLength = i14 + getPaddingLeft() + getPaddingRight();
        if (maxAscent[1] == -1 && maxAscent[0] == -1 && maxAscent[2] == -1 && maxAscent[3] == -1) {
            maxHeight2 = maxHeight9;
        } else {
            int ascent2 = Math.max(maxAscent[3], Math.max(maxAscent[0], Math.max(maxAscent[1], maxAscent[2])));
            int descent3 = Math.max(maxDescent[3], Math.max(maxDescent[0], Math.max(maxDescent[1], maxDescent[2])));
            maxHeight2 = Math.max(maxHeight9, ascent2 + descent3);
        }
        alternativeMaxHeight = largestChildWidth;
        widthMode2 = childState10;
        childState2 = maxHeight2;
        if (!matchHeight) {
            childState2 = alternativeMaxHeight;
        }
        int maxHeight82 = childState2 + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(widthSizeAndState | ((-16777216) & widthMode2), View.resolveSizeAndState(Math.max(maxHeight82, getSuggestedMinimumHeight()), delta, widthMode2 << 16));
        if (!skippedMeasure) {
        }
    }

    private void forceUniformHeight(int count, int widthMeasureSpec) {
        int uniformMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), BasicMeasure.EXACTLY);
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.height == -1) {
                    int oldWidth = lp.width;
                    lp.width = child.getMeasuredWidth();
                    measureChildWithMargins(child, widthMeasureSpec, 0, uniformMeasureSpec, 0);
                    lp.width = oldWidth;
                }
            }
        }
    }

    int getChildrenSkipCount(View child, int index) {
        return 0;
    }

    int measureNullChild(int childIndex) {
        return 0;
    }

    void measureChildBeforeLayout(View child, int childIndex, int widthMeasureSpec, int totalWidth, int heightMeasureSpec, int totalHeight) {
        measureChildWithMargins(child, widthMeasureSpec, totalWidth, heightMeasureSpec, totalHeight);
    }

    int getLocationOffset(View child) {
        return 0;
    }

    int getNextLocationOffset(View child) {
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (this.mOrientation == 1) {
            layoutVertical(l, t, r, b);
        } else {
            layoutHorizontal(l, t, r, b);
        }
    }

    void layoutVertical(int left, int top, int right, int bottom) {
        int childTop;
        int paddingLeft;
        int gravity;
        int childLeft;
        int paddingLeft2 = getPaddingLeft();
        int width = right - left;
        int childRight = width - getPaddingRight();
        int childSpace = (width - paddingLeft2) - getPaddingRight();
        int count = getVirtualChildCount();
        int i = this.mGravity;
        int majorGravity = i & 112;
        int minorGravity = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        switch (majorGravity) {
            case 16:
                int childTop2 = getPaddingTop();
                childTop = childTop2 + (((bottom - top) - this.mTotalLength) / 2);
                break;
            case 80:
                int childTop3 = getPaddingTop();
                childTop = ((childTop3 + bottom) - top) - this.mTotalLength;
                break;
            default:
                childTop = getPaddingTop();
                break;
        }
        int i2 = 0;
        while (i2 < count) {
            View child = getVirtualChildAt(i2);
            if (child == null) {
                childTop += measureNullChild(i2);
                paddingLeft = paddingLeft2;
            } else if (child.getVisibility() == 8) {
                paddingLeft = paddingLeft2;
            } else {
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int gravity2 = lp.gravity;
                if (gravity2 >= 0) {
                    gravity = gravity2;
                } else {
                    gravity = minorGravity;
                }
                int layoutDirection = ViewCompat.getLayoutDirection(this);
                int absoluteGravity = GravityCompat.getAbsoluteGravity(gravity, layoutDirection);
                switch (absoluteGravity & 7) {
                    case 1:
                        int childLeft2 = childSpace - childWidth;
                        childLeft = (((childLeft2 / 2) + paddingLeft2) + lp.leftMargin) - lp.rightMargin;
                        break;
                    case 5:
                        int childLeft3 = childRight - childWidth;
                        childLeft = childLeft3 - lp.rightMargin;
                        break;
                    default:
                        childLeft = lp.leftMargin + paddingLeft2;
                        break;
                }
                if (hasDividerBeforeChildAt(i2)) {
                    childTop += this.mDividerHeight;
                }
                int childTop4 = childTop + lp.topMargin;
                int childTop5 = getLocationOffset(child);
                paddingLeft = paddingLeft2;
                setChildFrame(child, childLeft, childTop4 + childTop5, childWidth, childHeight);
                int childTop6 = childTop4 + childHeight + lp.bottomMargin + getNextLocationOffset(child);
                i2 += getChildrenSkipCount(child, i2);
                childTop = childTop6;
            }
            i2++;
            paddingLeft2 = paddingLeft;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void layoutHorizontal(int left, int top, int right, int bottom) {
        int childLeft;
        int start;
        int dir;
        int layoutDirection;
        int[] maxDescent;
        int[] maxAscent;
        int paddingTop;
        int height;
        int childBottom;
        int childBaseline;
        int gravity;
        int gravity2;
        int childTop;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop2 = getPaddingTop();
        int height2 = bottom - top;
        int childBottom2 = height2 - getPaddingBottom();
        int childSpace = (height2 - paddingTop2) - getPaddingBottom();
        int count = getVirtualChildCount();
        int i = this.mGravity;
        int majorGravity = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int minorGravity = i & 112;
        boolean baselineAligned = this.mBaselineAligned;
        int[] maxAscent2 = this.mMaxAscent;
        int[] maxDescent2 = this.mMaxDescent;
        int layoutDirection2 = ViewCompat.getLayoutDirection(this);
        switch (GravityCompat.getAbsoluteGravity(majorGravity, layoutDirection2)) {
            case 1:
                int childLeft2 = getPaddingLeft();
                childLeft = childLeft2 + (((right - left) - this.mTotalLength) / 2);
                break;
            case 5:
                int childLeft3 = getPaddingLeft();
                childLeft = ((childLeft3 + right) - left) - this.mTotalLength;
                break;
            default:
                childLeft = getPaddingLeft();
                break;
        }
        if (!isLayoutRtl) {
            start = 0;
            dir = 1;
        } else {
            int start2 = count - 1;
            start = start2;
            dir = -1;
        }
        int i2 = 0;
        while (i2 < count) {
            int childIndex = start + (dir * i2);
            boolean isLayoutRtl2 = isLayoutRtl;
            View child = getVirtualChildAt(childIndex);
            if (child == null) {
                childLeft += measureNullChild(childIndex);
                layoutDirection = layoutDirection2;
                maxDescent = maxDescent2;
                maxAscent = maxAscent2;
                paddingTop = paddingTop2;
                height = height2;
                childBottom = childBottom2;
            } else {
                int i3 = i2;
                int i4 = child.getVisibility();
                layoutDirection = layoutDirection2;
                if (i4 == 8) {
                    maxDescent = maxDescent2;
                    maxAscent = maxAscent2;
                    paddingTop = paddingTop2;
                    height = height2;
                    childBottom = childBottom2;
                    i2 = i3;
                } else {
                    int childWidth = child.getMeasuredWidth();
                    int childHeight = child.getMeasuredHeight();
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    if (baselineAligned) {
                        height = height2;
                        if (lp.height != -1) {
                            childBaseline = child.getBaseline();
                            gravity = lp.gravity;
                            if (gravity < 0) {
                                gravity2 = gravity;
                            } else {
                                gravity2 = minorGravity;
                            }
                            switch (gravity2 & 112) {
                                case 16:
                                    childBottom = childBottom2;
                                    int childTop2 = ((((childSpace - childHeight) / 2) + paddingTop2) + lp.topMargin) - lp.bottomMargin;
                                    childTop = childTop2;
                                    break;
                                case 48:
                                    childBottom = childBottom2;
                                    int childTop3 = lp.topMargin + paddingTop2;
                                    if (childBaseline == -1) {
                                        childTop = childTop3;
                                        break;
                                    } else {
                                        childTop = childTop3 + (maxAscent2[1] - childBaseline);
                                        break;
                                    }
                                case 80:
                                    int childTop4 = childBottom2 - childHeight;
                                    childBottom = childBottom2;
                                    int childBottom3 = lp.bottomMargin;
                                    int childTop5 = childTop4 - childBottom3;
                                    if (childBaseline == -1) {
                                        childTop = childTop5;
                                        break;
                                    } else {
                                        int descent = child.getMeasuredHeight() - childBaseline;
                                        childTop = childTop5 - (maxDescent2[2] - descent);
                                        break;
                                    }
                                default:
                                    childBottom = childBottom2;
                                    childTop = paddingTop2;
                                    break;
                            }
                            if (hasDividerBeforeChildAt(childIndex)) {
                                childLeft += this.mDividerWidth;
                            }
                            int childLeft4 = childLeft + lp.leftMargin;
                            int childLeft5 = getLocationOffset(child);
                            paddingTop = paddingTop2;
                            maxDescent = maxDescent2;
                            maxAscent = maxAscent2;
                            setChildFrame(child, childLeft4 + childLeft5, childTop, childWidth, childHeight);
                            int childLeft6 = childLeft4 + childWidth + lp.rightMargin + getNextLocationOffset(child);
                            i2 = i3 + getChildrenSkipCount(child, childIndex);
                            childLeft = childLeft6;
                        }
                    } else {
                        height = height2;
                    }
                    childBaseline = -1;
                    gravity = lp.gravity;
                    if (gravity < 0) {
                    }
                    switch (gravity2 & 112) {
                        case 16:
                            break;
                        case 48:
                            break;
                        case 80:
                            break;
                    }
                    if (hasDividerBeforeChildAt(childIndex)) {
                    }
                    int childLeft42 = childLeft + lp.leftMargin;
                    int childLeft52 = getLocationOffset(child);
                    paddingTop = paddingTop2;
                    maxDescent = maxDescent2;
                    maxAscent = maxAscent2;
                    setChildFrame(child, childLeft42 + childLeft52, childTop, childWidth, childHeight);
                    int childLeft62 = childLeft42 + childWidth + lp.rightMargin + getNextLocationOffset(child);
                    i2 = i3 + getChildrenSkipCount(child, childIndex);
                    childLeft = childLeft62;
                }
            }
            i2++;
            isLayoutRtl = isLayoutRtl2;
            layoutDirection2 = layoutDirection;
            height2 = height;
            childBottom2 = childBottom;
            paddingTop2 = paddingTop;
            maxDescent2 = maxDescent;
            maxAscent2 = maxAscent;
        }
    }

    private void setChildFrame(View child, int left, int top, int width, int height) {
        child.layout(left, top, left + width, top + height);
    }

    public void setOrientation(int orientation) {
        if (this.mOrientation != orientation) {
            this.mOrientation = orientation;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int gravity) {
        if (this.mGravity != gravity) {
            if ((8388615 & gravity) == 0) {
                gravity |= GravityCompat.START;
            }
            if ((gravity & 112) == 0) {
                gravity |= 48;
            }
            this.mGravity = gravity;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int horizontalGravity) {
        int gravity = horizontalGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i = this.mGravity;
        if ((8388615 & i) != gravity) {
            this.mGravity = ((-8388616) & i) | gravity;
            requestLayout();
        }
    }

    public void setVerticalGravity(int verticalGravity) {
        int gravity = verticalGravity & 112;
        int i = this.mGravity;
        if ((i & 112) != gravity) {
            this.mGravity = (i & (-113)) | gravity;
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(LinearLayoutCompat.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(LinearLayoutCompat.class.getName());
    }

    /* renamed from: android.support.v7.widget.LinearLayoutCompat$LayoutParams */
    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.gravity = -1;
            TypedArray a = c.obtainStyledAttributes(attrs, C0365R.styleable.LinearLayoutCompat_Layout);
            this.weight = a.getFloat(C0365R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = a.getInt(C0365R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(int width, int height, float weight) {
            super(width, height);
            this.gravity = -1;
            this.weight = weight;
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
            this.gravity = -1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams source) {
            super((ViewGroup.MarginLayoutParams) source);
            this.gravity = -1;
            this.weight = source.weight;
            this.gravity = source.gravity;
        }
    }
}
