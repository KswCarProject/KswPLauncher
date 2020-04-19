package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
    private static final int DEFAULT_FADE_COLOR = -858993460;
    private static final int DEFAULT_OVERHANG_SIZE = 32;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private boolean mDisplayListReflectionLoaded;
    final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private Method mGetDisplayList;
    private float mInitialMotionX;
    private float mInitialMotionY;
    boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    final ArrayList<DisableLayerRunnable> mPostedRunnables;
    boolean mPreservedOpenState;
    private Field mRecreateDisplayList;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    float mSlideOffset;
    int mSlideRange;
    View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;

    public interface PanelSlideListener {
        void onPanelClosed(@NonNull View view);

        void onPanelOpened(@NonNull View view);

        void onPanelSlide(@NonNull View view, float f);
    }

    public static class SimplePanelSlideListener implements PanelSlideListener {
        public void onPanelSlide(View panel, float slideOffset) {
        }

        public void onPanelOpened(View panel) {
        }

        public void onPanelClosed(View panel) {
        }
    }

    public SlidingPaneLayout(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public SlidingPaneLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingPaneLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mSliderFadeColor = DEFAULT_FADE_COLOR;
        this.mFirstLayout = true;
        this.mTmpRect = new Rect();
        this.mPostedRunnables = new ArrayList<>();
        float density = context.getResources().getDisplayMetrics().density;
        this.mOverhangSize = (int) ((32.0f * density) + 0.5f);
        setWillNotDraw(false);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        ViewCompat.setImportantForAccessibility(this, 1);
        this.mDragHelper = ViewDragHelper.create(this, 0.5f, new DragHelperCallback());
        this.mDragHelper.setMinVelocity(400.0f * density);
    }

    public void setParallaxDistance(@Px int parallaxBy) {
        this.mParallaxBy = parallaxBy;
        requestLayout();
    }

    @Px
    public int getParallaxDistance() {
        return this.mParallaxBy;
    }

    public void setSliderFadeColor(@ColorInt int color) {
        this.mSliderFadeColor = color;
    }

    @ColorInt
    public int getSliderFadeColor() {
        return this.mSliderFadeColor;
    }

    public void setCoveredFadeColor(@ColorInt int color) {
        this.mCoveredFadeColor = color;
    }

    @ColorInt
    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }

    public void setPanelSlideListener(@Nullable PanelSlideListener listener) {
        this.mPanelSlideListener = listener;
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnPanelSlide(View panel) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelSlide(panel, this.mSlideOffset);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnPanelOpened(View panel) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelOpened(panel);
        }
        sendAccessibilityEvent(32);
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnPanelClosed(View panel) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelClosed(panel);
        }
        sendAccessibilityEvent(32);
    }

    /* access modifiers changed from: package-private */
    public void updateObscuredViewsVisibility(View panel) {
        int bottom;
        int top;
        int right;
        int left;
        boolean isLayoutRtl;
        int vis;
        View view = panel;
        boolean isLayoutRtl2 = isLayoutRtlSupport();
        int startBound = isLayoutRtl2 ? getWidth() - getPaddingRight() : getPaddingLeft();
        int endBound = isLayoutRtl2 ? getPaddingLeft() : getWidth() - getPaddingRight();
        int topBound = getPaddingTop();
        int bottomBound = getHeight() - getPaddingBottom();
        if (view == null || !viewIsOpaque(panel)) {
            bottom = 0;
            top = 0;
            right = 0;
            left = 0;
        } else {
            left = panel.getLeft();
            right = panel.getRight();
            top = panel.getTop();
            bottom = panel.getBottom();
        }
        int i = 0;
        int childCount = getChildCount();
        while (i < childCount) {
            View child = getChildAt(i);
            if (child == view) {
                boolean z = isLayoutRtl2;
                return;
            }
            if (child.getVisibility() == 8) {
                isLayoutRtl = isLayoutRtl2;
            } else {
                int clampedChildLeft = Math.max(isLayoutRtl2 ? endBound : startBound, child.getLeft());
                int clampedChildTop = Math.max(topBound, child.getTop());
                isLayoutRtl = isLayoutRtl2;
                int clampedChildRight = Math.min(isLayoutRtl2 ? startBound : endBound, child.getRight());
                int clampedChildBottom = Math.min(bottomBound, child.getBottom());
                if (clampedChildLeft < left || clampedChildTop < top || clampedChildRight > right || clampedChildBottom > bottom) {
                    vis = 0;
                } else {
                    vis = 4;
                }
                int i2 = clampedChildRight;
                child.setVisibility(vis);
            }
            i++;
            isLayoutRtl2 = isLayoutRtl;
            view = panel;
        }
        boolean z2 = isLayoutRtl2;
    }

    /* access modifiers changed from: package-private */
    public void setAllChildrenVisible() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 4) {
                child.setVisibility(0);
            }
        }
    }

    private static boolean viewIsOpaque(View v) {
        Drawable bg;
        if (v.isOpaque()) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 18 || (bg = v.getBackground()) == null) {
            return false;
        }
        if (bg.getOpacity() == -1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
        int count = this.mPostedRunnables.size();
        for (int i = 0; i < count; i++) {
            this.mPostedRunnables.get(i).run();
        }
        this.mPostedRunnables.clear();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00bb, code lost:
        if (r14.width == 0) goto L_0x00ac;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r32, int r33) {
        /*
            r31 = this;
            r0 = r31
            int r1 = android.view.View.MeasureSpec.getMode(r32)
            int r2 = android.view.View.MeasureSpec.getSize(r32)
            int r3 = android.view.View.MeasureSpec.getMode(r33)
            int r4 = android.view.View.MeasureSpec.getSize(r33)
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = 1073741824(0x40000000, float:2.0)
            if (r1 == r6) goto L_0x0032
            boolean r7 = r31.isInEditMode()
            if (r7 == 0) goto L_0x002a
            if (r1 != r5) goto L_0x0023
            r1 = 1073741824(0x40000000, float:2.0)
            goto L_0x0049
        L_0x0023:
            if (r1 != 0) goto L_0x0049
            r1 = 1073741824(0x40000000, float:2.0)
            r2 = 300(0x12c, float:4.2E-43)
            goto L_0x0049
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Width must have an exact value or MATCH_PARENT"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            if (r3 != 0) goto L_0x0049
            boolean r7 = r31.isInEditMode()
            if (r7 == 0) goto L_0x0041
            if (r3 != 0) goto L_0x0049
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = 300(0x12c, float:4.2E-43)
            goto L_0x0049
        L_0x0041:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Height must not be UNSPECIFIED"
            r5.<init>(r6)
            throw r5
        L_0x0049:
            r7 = 0
            r8 = 0
            if (r3 == r5) goto L_0x005e
            if (r3 == r6) goto L_0x0050
            goto L_0x006a
        L_0x0050:
            int r9 = r31.getPaddingTop()
            int r9 = r4 - r9
            int r10 = r31.getPaddingBottom()
            int r9 = r9 - r10
            r8 = r9
            r7 = r9
            goto L_0x006a
        L_0x005e:
            int r9 = r31.getPaddingTop()
            int r9 = r4 - r9
            int r10 = r31.getPaddingBottom()
            int r8 = r9 - r10
        L_0x006a:
            r9 = 0
            r10 = 0
            int r11 = r31.getPaddingLeft()
            int r11 = r2 - r11
            int r12 = r31.getPaddingRight()
            int r11 = r11 - r12
            r12 = r11
            int r13 = r31.getChildCount()
            r14 = 2
            if (r13 <= r14) goto L_0x0086
            java.lang.String r14 = "SlidingPaneLayout"
            java.lang.String r15 = "onMeasure: More than two child views are not supported."
            android.util.Log.e(r14, r15)
        L_0x0086:
            r14 = 0
            r0.mSlideableView = r14
            r15 = r12
            r12 = r7
            r7 = 0
        L_0x008c:
            r6 = 8
            r17 = 1
            r18 = 0
            if (r7 >= r13) goto L_0x0142
            android.view.View r5 = r0.getChildAt(r7)
            android.view.ViewGroup$LayoutParams r19 = r5.getLayoutParams()
            r14 = r19
            android.support.v4.widget.SlidingPaneLayout$LayoutParams r14 = (android.support.v4.widget.SlidingPaneLayout.LayoutParams) r14
            r20 = r1
            int r1 = r5.getVisibility()
            if (r1 != r6) goto L_0x00b0
            r1 = 0
            r14.dimWhenOffset = r1
        L_0x00ac:
            r21 = r4
            goto L_0x0136
        L_0x00b0:
            float r1 = r14.weight
            int r1 = (r1 > r18 ? 1 : (r1 == r18 ? 0 : -1))
            if (r1 <= 0) goto L_0x00be
            float r1 = r14.weight
            float r9 = r9 + r1
            int r1 = r14.width
            if (r1 != 0) goto L_0x00be
            goto L_0x00ac
        L_0x00be:
            int r1 = r14.leftMargin
            int r6 = r14.rightMargin
            int r1 = r1 + r6
            int r6 = r14.width
            r21 = r4
            r4 = -2
            if (r6 != r4) goto L_0x00d3
            int r4 = r11 - r1
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r6)
            goto L_0x00e9
        L_0x00d3:
            int r4 = r14.width
            r6 = -1
            if (r4 != r6) goto L_0x00e1
            int r4 = r11 - r1
            r6 = 1073741824(0x40000000, float:2.0)
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r6)
            goto L_0x00e9
        L_0x00e1:
            r6 = 1073741824(0x40000000, float:2.0)
            int r4 = r14.width
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r6)
        L_0x00e9:
            int r6 = r14.height
            r22 = r1
            r1 = -2
            if (r6 != r1) goto L_0x00f7
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r1)
            goto L_0x010b
        L_0x00f7:
            int r1 = r14.height
            r6 = -1
            if (r1 != r6) goto L_0x0103
            r1 = 1073741824(0x40000000, float:2.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r1)
            goto L_0x010b
        L_0x0103:
            r1 = 1073741824(0x40000000, float:2.0)
            int r6 = r14.height
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r1)
        L_0x010b:
            r1 = r6
            r5.measure(r4, r1)
            int r6 = r5.getMeasuredWidth()
            r23 = r1
            int r1 = r5.getMeasuredHeight()
            r24 = r4
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 != r4) goto L_0x0125
            if (r1 <= r12) goto L_0x0125
            int r12 = java.lang.Math.min(r1, r8)
        L_0x0125:
            int r15 = r15 - r6
            if (r15 >= 0) goto L_0x012b
            r4 = r17
            goto L_0x012c
        L_0x012b:
            r4 = 0
        L_0x012c:
            r14.slideable = r4
            r4 = r4 | r10
            boolean r10 = r14.slideable
            if (r10 == 0) goto L_0x0135
            r0.mSlideableView = r5
        L_0x0135:
            r10 = r4
        L_0x0136:
            int r7 = r7 + 1
            r1 = r20
            r4 = r21
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = 1073741824(0x40000000, float:2.0)
            goto L_0x008c
        L_0x0142:
            r20 = r1
            r21 = r4
            if (r10 != 0) goto L_0x0155
            int r1 = (r9 > r18 ? 1 : (r9 == r18 ? 0 : -1))
            if (r1 <= 0) goto L_0x014d
            goto L_0x0155
        L_0x014d:
            r26 = r3
            r30 = r8
            r27 = r13
            goto L_0x0296
        L_0x0155:
            int r1 = r0.mOverhangSize
            int r1 = r11 - r1
            r4 = 0
        L_0x015a:
            if (r4 >= r13) goto L_0x0290
            android.view.View r5 = r0.getChildAt(r4)
            int r7 = r5.getVisibility()
            if (r7 != r6) goto L_0x0173
        L_0x0167:
            r29 = r1
            r26 = r3
        L_0x016b:
            r30 = r8
            r27 = r13
        L_0x016f:
            r1 = 1073741824(0x40000000, float:2.0)
            goto L_0x0282
        L_0x0173:
            android.view.ViewGroup$LayoutParams r7 = r5.getLayoutParams()
            android.support.v4.widget.SlidingPaneLayout$LayoutParams r7 = (android.support.v4.widget.SlidingPaneLayout.LayoutParams) r7
            int r14 = r5.getVisibility()
            if (r14 != r6) goto L_0x0180
            goto L_0x0167
        L_0x0180:
            int r14 = r7.width
            if (r14 != 0) goto L_0x018d
            float r14 = r7.weight
            int r14 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r14 <= 0) goto L_0x018d
            r14 = r17
            goto L_0x018e
        L_0x018d:
            r14 = 0
        L_0x018e:
            if (r14 == 0) goto L_0x0193
            r19 = 0
            goto L_0x0197
        L_0x0193:
            int r19 = r5.getMeasuredWidth()
        L_0x0197:
            r25 = r19
            if (r10 == 0) goto L_0x0204
            android.view.View r6 = r0.mSlideableView
            if (r5 == r6) goto L_0x0204
            int r6 = r7.width
            if (r6 >= 0) goto L_0x01f8
            r6 = r25
            if (r6 > r1) goto L_0x01b3
            r26 = r3
            float r3 = r7.weight
            int r3 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r3 <= 0) goto L_0x01b0
            goto L_0x01b5
        L_0x01b0:
            r29 = r1
            goto L_0x016b
        L_0x01b3:
            r26 = r3
        L_0x01b5:
            if (r14 == 0) goto L_0x01dc
            int r3 = r7.height
            r27 = r13
            r13 = -2
            if (r3 != r13) goto L_0x01c7
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r3)
            r3 = 1073741824(0x40000000, float:2.0)
            goto L_0x01e8
        L_0x01c7:
            int r3 = r7.height
            r13 = -1
            if (r3 != r13) goto L_0x01d3
            r3 = 1073741824(0x40000000, float:2.0)
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r3)
            goto L_0x01e8
        L_0x01d3:
            r3 = 1073741824(0x40000000, float:2.0)
            int r13 = r7.height
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r3)
            goto L_0x01e8
        L_0x01dc:
            r27 = r13
            r3 = 1073741824(0x40000000, float:2.0)
            int r13 = r5.getMeasuredHeight()
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r3)
        L_0x01e8:
            r28 = r14
            int r14 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r3)
            r5.measure(r14, r13)
            r29 = r1
            r30 = r8
            goto L_0x016f
        L_0x01f8:
            r26 = r3
            r27 = r13
            r29 = r1
            r30 = r8
            r1 = 1073741824(0x40000000, float:2.0)
            goto L_0x0282
        L_0x0204:
            r26 = r3
            r27 = r13
            r28 = r14
            r6 = r25
            float r3 = r7.weight
            int r3 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r3 <= 0) goto L_0x027c
            int r3 = r7.width
            if (r3 != 0) goto L_0x023c
            int r3 = r7.height
            r13 = -2
            if (r3 != r13) goto L_0x0223
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            int r14 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r3)
            r3 = r14
            goto L_0x0247
        L_0x0223:
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            int r14 = r7.height
            r3 = -1
            if (r14 != r3) goto L_0x0233
            r14 = 1073741824(0x40000000, float:2.0)
            int r16 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r14)
            r3 = r16
            goto L_0x0247
        L_0x0233:
            r14 = 1073741824(0x40000000, float:2.0)
            int r3 = r7.height
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r14)
            goto L_0x0247
        L_0x023c:
            r13 = -2
            r14 = 1073741824(0x40000000, float:2.0)
            int r3 = r5.getMeasuredHeight()
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r14)
        L_0x0247:
            if (r10 == 0) goto L_0x0261
            int r14 = r7.leftMargin
            int r13 = r7.rightMargin
            int r14 = r14 + r13
            int r13 = r11 - r14
            r29 = r1
            r30 = r8
            r1 = 1073741824(0x40000000, float:2.0)
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r1)
            if (r6 == r13) goto L_0x025f
            r5.measure(r8, r3)
        L_0x025f:
            goto L_0x016f
        L_0x0261:
            r29 = r1
            r30 = r8
            r1 = 0
            int r8 = java.lang.Math.max(r1, r15)
            float r13 = r7.weight
            float r14 = (float) r8
            float r13 = r13 * r14
            float r13 = r13 / r9
            int r13 = (int) r13
            int r14 = r6 + r13
            r1 = 1073741824(0x40000000, float:2.0)
            int r14 = android.view.View.MeasureSpec.makeMeasureSpec(r14, r1)
            r5.measure(r14, r3)
            goto L_0x0282
        L_0x027c:
            r29 = r1
            r30 = r8
            r1 = 1073741824(0x40000000, float:2.0)
        L_0x0282:
            int r4 = r4 + 1
            r3 = r26
            r13 = r27
            r1 = r29
            r8 = r30
            r6 = 8
            goto L_0x015a
        L_0x0290:
            r26 = r3
            r30 = r8
            r27 = r13
        L_0x0296:
            r1 = r2
            int r3 = r31.getPaddingTop()
            int r3 = r3 + r12
            int r4 = r31.getPaddingBottom()
            int r3 = r3 + r4
            r0.setMeasuredDimension(r1, r3)
            r0.mCanSlide = r10
            android.support.v4.widget.ViewDragHelper r4 = r0.mDragHelper
            int r4 = r4.getViewDragState()
            if (r4 == 0) goto L_0x02b5
            if (r10 != 0) goto L_0x02b5
            android.support.v4.widget.ViewDragHelper r4 = r0.mDragHelper
            r4.abort()
        L_0x02b5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.SlidingPaneLayout.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingStart;
        int childLeft;
        int childRight;
        boolean isLayoutRtl = isLayoutRtlSupport();
        if (isLayoutRtl) {
            this.mDragHelper.setEdgeTrackingEnabled(2);
        } else {
            this.mDragHelper.setEdgeTrackingEnabled(1);
        }
        int width = r - l;
        int paddingStart2 = isLayoutRtl ? getPaddingRight() : getPaddingLeft();
        int paddingEnd = isLayoutRtl ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        int xStart = paddingStart2;
        int nextXStart = xStart;
        if (this.mFirstLayout) {
            this.mSlideOffset = (!this.mCanSlide || !this.mPreservedOpenState) ? 0.0f : 1.0f;
        }
        int xStart2 = xStart;
        int i = 0;
        while (i < childCount) {
            View child = getChildAt(i);
            if (child.getVisibility() == 8) {
                paddingStart = paddingStart2;
            } else {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth();
                int offset = 0;
                if (lp.slideable) {
                    int margin = lp.leftMargin + lp.rightMargin;
                    int range = (Math.min(nextXStart, (width - paddingEnd) - this.mOverhangSize) - xStart2) - margin;
                    this.mSlideRange = range;
                    int lpMargin = isLayoutRtl ? lp.rightMargin : lp.leftMargin;
                    paddingStart = paddingStart2;
                    int i2 = margin;
                    lp.dimWhenOffset = ((xStart2 + lpMargin) + range) + (childWidth / 2) > width - paddingEnd;
                    int pos = (int) (((float) range) * this.mSlideOffset);
                    xStart2 += pos + lpMargin;
                    int i3 = range;
                    this.mSlideOffset = ((float) pos) / ((float) this.mSlideRange);
                } else {
                    paddingStart = paddingStart2;
                    if (!this.mCanSlide || this.mParallaxBy == 0) {
                        xStart2 = nextXStart;
                    } else {
                        xStart2 = nextXStart;
                        offset = (int) ((1.0f - this.mSlideOffset) * ((float) this.mParallaxBy));
                    }
                }
                if (isLayoutRtl) {
                    childRight = (width - xStart2) + offset;
                    childLeft = childRight - childWidth;
                } else {
                    childLeft = xStart2 - offset;
                    childRight = childLeft + childWidth;
                }
                child.layout(childLeft, paddingTop, childRight, paddingTop + child.getMeasuredHeight());
                nextXStart += child.getWidth();
            }
            i++;
            paddingStart2 = paddingStart;
        }
        if (this.mFirstLayout) {
            if (this.mCanSlide) {
                if (this.mParallaxBy != 0) {
                    parallaxOtherViews(this.mSlideOffset);
                }
                if (((LayoutParams) this.mSlideableView.getLayoutParams()).dimWhenOffset) {
                    dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
                }
            } else {
                for (int i4 = 0; i4 < childCount; i4++) {
                    dimChildView(getChildAt(i4), 0.0f, this.mSliderFadeColor);
                }
            }
            updateObscuredViewsVisibility(this.mSlideableView);
        }
        this.mFirstLayout = false;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            this.mFirstLayout = true;
        }
    }

    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
        if (!isInTouchMode() && !this.mCanSlide) {
            this.mPreservedOpenState = child == this.mSlideableView;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        View secondChild;
        int action = ev.getActionMasked();
        if (!this.mCanSlide && action == 0 && getChildCount() > 1 && (secondChild = getChildAt(1)) != null) {
            this.mPreservedOpenState = !this.mDragHelper.isViewUnder(secondChild, (int) ev.getX(), (int) ev.getY());
        }
        if (!this.mCanSlide || (this.mIsUnableToDrag && action != 0)) {
            this.mDragHelper.cancel();
            return super.onInterceptTouchEvent(ev);
        } else if (action == 3 || action == 1) {
            this.mDragHelper.cancel();
            return false;
        } else {
            boolean interceptTap = false;
            if (action == 0) {
                this.mIsUnableToDrag = false;
                float x = ev.getX();
                float y = ev.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                if (this.mDragHelper.isViewUnder(this.mSlideableView, (int) x, (int) y) && isDimmed(this.mSlideableView)) {
                    interceptTap = true;
                }
            } else if (action == 2) {
                float x2 = ev.getX();
                float y2 = ev.getY();
                float adx = Math.abs(x2 - this.mInitialMotionX);
                float ady = Math.abs(y2 - this.mInitialMotionY);
                if (adx > ((float) this.mDragHelper.getTouchSlop()) && ady > adx) {
                    this.mDragHelper.cancel();
                    this.mIsUnableToDrag = true;
                    return false;
                }
            }
            if (this.mDragHelper.shouldInterceptTouchEvent(ev) || interceptTap) {
                return true;
            }
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.mCanSlide) {
            return super.onTouchEvent(ev);
        }
        this.mDragHelper.processTouchEvent(ev);
        switch (ev.getActionMasked()) {
            case 0:
                float x = ev.getX();
                float y = ev.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                break;
            case 1:
                if (isDimmed(this.mSlideableView)) {
                    float x2 = ev.getX();
                    float y2 = ev.getY();
                    float dx = x2 - this.mInitialMotionX;
                    float dy = y2 - this.mInitialMotionY;
                    int slop = this.mDragHelper.getTouchSlop();
                    if ((dx * dx) + (dy * dy) < ((float) (slop * slop)) && this.mDragHelper.isViewUnder(this.mSlideableView, (int) x2, (int) y2)) {
                        closePane(this.mSlideableView, 0);
                        break;
                    }
                }
                break;
        }
        return true;
    }

    private boolean closePane(View pane, int initialVelocity) {
        if (!this.mFirstLayout && !smoothSlideTo(0.0f, initialVelocity)) {
            return false;
        }
        this.mPreservedOpenState = false;
        return true;
    }

    private boolean openPane(View pane, int initialVelocity) {
        if (!this.mFirstLayout && !smoothSlideTo(1.0f, initialVelocity)) {
            return false;
        }
        this.mPreservedOpenState = true;
        return true;
    }

    @Deprecated
    public void smoothSlideOpen() {
        openPane();
    }

    public boolean openPane() {
        return openPane(this.mSlideableView, 0);
    }

    @Deprecated
    public void smoothSlideClosed() {
        closePane();
    }

    public boolean closePane() {
        return closePane(this.mSlideableView, 0);
    }

    public boolean isOpen() {
        return !this.mCanSlide || this.mSlideOffset == 1.0f;
    }

    @Deprecated
    public boolean canSlide() {
        return this.mCanSlide;
    }

    public boolean isSlideable() {
        return this.mCanSlide;
    }

    /* access modifiers changed from: package-private */
    public void onPanelDragged(int newLeft) {
        if (this.mSlideableView == null) {
            this.mSlideOffset = 0.0f;
            return;
        }
        boolean isLayoutRtl = isLayoutRtlSupport();
        LayoutParams lp = (LayoutParams) this.mSlideableView.getLayoutParams();
        this.mSlideOffset = ((float) ((isLayoutRtl ? (getWidth() - newLeft) - this.mSlideableView.getWidth() : newLeft) - ((isLayoutRtl ? getPaddingRight() : getPaddingLeft()) + (isLayoutRtl ? lp.rightMargin : lp.leftMargin)))) / ((float) this.mSlideRange);
        if (this.mParallaxBy != 0) {
            parallaxOtherViews(this.mSlideOffset);
        }
        if (lp.dimWhenOffset) {
            dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
        }
        dispatchOnPanelSlide(this.mSlideableView);
    }

    private void dimChildView(View v, float mag, int fadeColor) {
        LayoutParams lp = (LayoutParams) v.getLayoutParams();
        if (mag > 0.0f && fadeColor != 0) {
            int color = (((int) (((float) ((-16777216 & fadeColor) >>> 24)) * mag)) << 24) | (16777215 & fadeColor);
            if (lp.dimPaint == null) {
                lp.dimPaint = new Paint();
            }
            lp.dimPaint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_OVER));
            if (v.getLayerType() != 2) {
                v.setLayerType(2, lp.dimPaint);
            }
            invalidateChildRegion(v);
        } else if (v.getLayerType() != 0) {
            if (lp.dimPaint != null) {
                lp.dimPaint.setColorFilter((ColorFilter) null);
            }
            DisableLayerRunnable dlr = new DisableLayerRunnable(v);
            this.mPostedRunnables.add(dlr);
            ViewCompat.postOnAnimation(this, dlr);
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int save = canvas.save();
        if (this.mCanSlide && !lp.slideable && this.mSlideableView != null) {
            canvas.getClipBounds(this.mTmpRect);
            if (isLayoutRtlSupport()) {
                this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
            } else {
                this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
            }
            canvas.clipRect(this.mTmpRect);
        }
        boolean result = super.drawChild(canvas, child, drawingTime);
        canvas.restoreToCount(save);
        return result;
    }

    /* access modifiers changed from: package-private */
    public void invalidateChildRegion(View v) {
        if (Build.VERSION.SDK_INT >= 17) {
            ViewCompat.setLayerPaint(v, ((LayoutParams) v.getLayoutParams()).dimPaint);
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            if (!this.mDisplayListReflectionLoaded) {
                try {
                    this.mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", (Class[]) null);
                } catch (NoSuchMethodException e) {
                    Log.e(TAG, "Couldn't fetch getDisplayList method; dimming won't work right.", e);
                }
                try {
                    this.mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
                    this.mRecreateDisplayList.setAccessible(true);
                } catch (NoSuchFieldException e2) {
                    Log.e(TAG, "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e2);
                }
                this.mDisplayListReflectionLoaded = true;
            }
            if (this.mGetDisplayList == null || this.mRecreateDisplayList == null) {
                v.invalidate();
                return;
            }
            try {
                this.mRecreateDisplayList.setBoolean(v, true);
                this.mGetDisplayList.invoke(v, (Object[]) null);
            } catch (Exception e3) {
                Log.e(TAG, "Error refreshing display list state", e3);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this, v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
    }

    /* access modifiers changed from: package-private */
    public boolean smoothSlideTo(float slideOffset, int velocity) {
        int startBound;
        if (!this.mCanSlide) {
            return false;
        }
        boolean isLayoutRtl = isLayoutRtlSupport();
        LayoutParams lp = (LayoutParams) this.mSlideableView.getLayoutParams();
        if (isLayoutRtl) {
            startBound = (int) (((float) getWidth()) - ((((float) (getPaddingRight() + lp.rightMargin)) + (((float) this.mSlideRange) * slideOffset)) + ((float) this.mSlideableView.getWidth())));
        } else {
            startBound = (int) (((float) (getPaddingLeft() + lp.leftMargin)) + (((float) this.mSlideRange) * slideOffset));
        }
        if (!this.mDragHelper.smoothSlideViewTo(this.mSlideableView, startBound, this.mSlideableView.getTop())) {
            return false;
        }
        setAllChildrenVisible();
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    public void computeScroll() {
        if (!this.mDragHelper.continueSettling(true)) {
            return;
        }
        if (!this.mCanSlide) {
            this.mDragHelper.abort();
        } else {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Deprecated
    public void setShadowDrawable(Drawable d) {
        setShadowDrawableLeft(d);
    }

    public void setShadowDrawableLeft(@Nullable Drawable d) {
        this.mShadowDrawableLeft = d;
    }

    public void setShadowDrawableRight(@Nullable Drawable d) {
        this.mShadowDrawableRight = d;
    }

    @Deprecated
    public void setShadowResource(@DrawableRes int resId) {
        setShadowDrawable(getResources().getDrawable(resId));
    }

    public void setShadowResourceLeft(int resId) {
        setShadowDrawableLeft(ContextCompat.getDrawable(getContext(), resId));
    }

    public void setShadowResourceRight(int resId) {
        setShadowDrawableRight(ContextCompat.getDrawable(getContext(), resId));
    }

    public void draw(Canvas c) {
        Drawable shadowDrawable;
        int right;
        int left;
        super.draw(c);
        if (isLayoutRtlSupport()) {
            shadowDrawable = this.mShadowDrawableRight;
        } else {
            shadowDrawable = this.mShadowDrawableLeft;
        }
        View shadowView = getChildCount() > 1 ? getChildAt(1) : null;
        if (shadowView != null && shadowDrawable != null) {
            int top = shadowView.getTop();
            int bottom = shadowView.getBottom();
            int shadowWidth = shadowDrawable.getIntrinsicWidth();
            if (isLayoutRtlSupport()) {
                left = shadowView.getRight();
                right = left + shadowWidth;
            } else {
                right = shadowView.getLeft();
                left = right - shadowWidth;
            }
            shadowDrawable.setBounds(left, top, right, bottom);
            shadowDrawable.draw(c);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parallaxOtherViews(float r12) {
        /*
            r11 = this;
            boolean r0 = r11.isLayoutRtlSupport()
            android.view.View r1 = r11.mSlideableView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v4.widget.SlidingPaneLayout$LayoutParams r1 = (android.support.v4.widget.SlidingPaneLayout.LayoutParams) r1
            boolean r2 = r1.dimWhenOffset
            r3 = 0
            if (r2 == 0) goto L_0x001c
            if (r0 == 0) goto L_0x0016
            int r2 = r1.rightMargin
            goto L_0x0018
        L_0x0016:
            int r2 = r1.leftMargin
        L_0x0018:
            if (r2 > 0) goto L_0x001c
            r2 = 1
            goto L_0x001d
        L_0x001c:
            r2 = r3
        L_0x001d:
            int r4 = r11.getChildCount()
        L_0x0022:
            if (r3 >= r4) goto L_0x005f
            android.view.View r5 = r11.getChildAt(r3)
            android.view.View r6 = r11.mSlideableView
            if (r5 != r6) goto L_0x002d
            goto L_0x005c
        L_0x002d:
            float r6 = r11.mParallaxOffset
            r7 = 1065353216(0x3f800000, float:1.0)
            float r6 = r7 - r6
            int r8 = r11.mParallaxBy
            float r8 = (float) r8
            float r6 = r6 * r8
            int r6 = (int) r6
            r11.mParallaxOffset = r12
            float r8 = r7 - r12
            int r9 = r11.mParallaxBy
            float r9 = (float) r9
            float r8 = r8 * r9
            int r8 = (int) r8
            int r9 = r6 - r8
            if (r0 == 0) goto L_0x0047
            int r10 = -r9
            goto L_0x0048
        L_0x0047:
            r10 = r9
        L_0x0048:
            r5.offsetLeftAndRight(r10)
            if (r2 == 0) goto L_0x005c
            if (r0 == 0) goto L_0x0053
            float r10 = r11.mParallaxOffset
            float r10 = r10 - r7
            goto L_0x0057
        L_0x0053:
            float r10 = r11.mParallaxOffset
            float r10 = r7 - r10
        L_0x0057:
            int r7 = r11.mCoveredFadeColor
            r11.dimChildView(r5, r10, r7)
        L_0x005c:
            int r3 = r3 + 1
            goto L_0x0022
        L_0x005f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.SlidingPaneLayout.parallaxOtherViews(float):void");
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        View view = v;
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            int scrollX = v.getScrollX();
            int scrollY = v.getScrollY();
            for (int i = group.getChildCount() - 1; i >= 0; i--) {
                View child = group.getChildAt(i);
                if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight() && y + scrollY >= child.getTop() && y + scrollY < child.getBottom()) {
                    if (canScroll(child, true, dx, (x + scrollX) - child.getLeft(), (y + scrollY) - child.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (checkV) {
            if (v.canScrollHorizontally(isLayoutRtlSupport() ? dx : -dx)) {
                return true;
            }
        } else {
            int i2 = dx;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isDimmed(View child) {
        if (child == null) {
            return false;
        }
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (!this.mCanSlide || !lp.dimWhenOffset || this.mSlideOffset <= 0.0f) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) p) : new LayoutParams(p);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return (p instanceof LayoutParams) && super.checkLayoutParams(p);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.isOpen = isSlideable() ? isOpen() : this.mPreservedOpenState;
        return ss;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        if (ss.isOpen) {
            openPane();
        } else {
            closePane();
        }
        this.mPreservedOpenState = ss.isOpen;
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        DragHelperCallback() {
        }

        public boolean tryCaptureView(View child, int pointerId) {
            if (SlidingPaneLayout.this.mIsUnableToDrag) {
                return false;
            }
            return ((LayoutParams) child.getLayoutParams()).slideable;
        }

        public void onViewDragStateChanged(int state) {
            if (SlidingPaneLayout.this.mDragHelper.getViewDragState() != 0) {
                return;
            }
            if (SlidingPaneLayout.this.mSlideOffset == 0.0f) {
                SlidingPaneLayout.this.updateObscuredViewsVisibility(SlidingPaneLayout.this.mSlideableView);
                SlidingPaneLayout.this.dispatchOnPanelClosed(SlidingPaneLayout.this.mSlideableView);
                SlidingPaneLayout.this.mPreservedOpenState = false;
                return;
            }
            SlidingPaneLayout.this.dispatchOnPanelOpened(SlidingPaneLayout.this.mSlideableView);
            SlidingPaneLayout.this.mPreservedOpenState = true;
        }

        public void onViewCaptured(View capturedChild, int activePointerId) {
            SlidingPaneLayout.this.setAllChildrenVisible();
        }

        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            SlidingPaneLayout.this.onPanelDragged(left);
            SlidingPaneLayout.this.invalidate();
        }

        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int left;
            LayoutParams lp = (LayoutParams) releasedChild.getLayoutParams();
            if (SlidingPaneLayout.this.isLayoutRtlSupport()) {
                int startToRight = SlidingPaneLayout.this.getPaddingRight() + lp.rightMargin;
                if (xvel < 0.0f || (xvel == 0.0f && SlidingPaneLayout.this.mSlideOffset > 0.5f)) {
                    startToRight += SlidingPaneLayout.this.mSlideRange;
                }
                left = (SlidingPaneLayout.this.getWidth() - startToRight) - SlidingPaneLayout.this.mSlideableView.getWidth();
            } else {
                int left2 = SlidingPaneLayout.this.getPaddingLeft() + lp.leftMargin;
                if (xvel > 0.0f || (xvel == 0.0f && SlidingPaneLayout.this.mSlideOffset > 0.5f)) {
                    left = left2 + SlidingPaneLayout.this.mSlideRange;
                } else {
                    left = left2;
                }
            }
            SlidingPaneLayout.this.mDragHelper.settleCapturedViewAt(left, releasedChild.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        public int getViewHorizontalDragRange(View child) {
            return SlidingPaneLayout.this.mSlideRange;
        }

        public int clampViewPositionHorizontal(View child, int left, int dx) {
            LayoutParams lp = (LayoutParams) SlidingPaneLayout.this.mSlideableView.getLayoutParams();
            if (SlidingPaneLayout.this.isLayoutRtlSupport()) {
                int startBound = SlidingPaneLayout.this.getWidth() - ((SlidingPaneLayout.this.getPaddingRight() + lp.rightMargin) + SlidingPaneLayout.this.mSlideableView.getWidth());
                return Math.max(Math.min(left, startBound), startBound - SlidingPaneLayout.this.mSlideRange);
            }
            int startBound2 = SlidingPaneLayout.this.getPaddingLeft() + lp.leftMargin;
            return Math.min(Math.max(left, startBound2), SlidingPaneLayout.this.mSlideRange + startBound2);
        }

        public int clampViewPositionVertical(View child, int top, int dy) {
            return child.getTop();
        }

        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            SlidingPaneLayout.this.mDragHelper.captureChildView(SlidingPaneLayout.this.mSlideableView, pointerId);
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int[] ATTRS = {16843137};
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;
        public float weight = 0.0f;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(@NonNull ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(@NonNull ViewGroup.MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(@NonNull LayoutParams source) {
            super(source);
            this.weight = source.weight;
        }

        public LayoutParams(@NonNull Context c, @Nullable AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, ATTRS);
            this.weight = a.getFloat(0, 0.0f);
            a.recycle();
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, (ClassLoader) null);
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in, (ClassLoader) null);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        boolean isOpen;

        SavedState(Parcelable superState) {
            super(superState);
        }

        SavedState(Parcel in, ClassLoader loader) {
            super(in, loader);
            this.isOpen = in.readInt() != 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.isOpen ? 1 : 0);
        }
    }

    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect = new Rect();

        AccessibilityDelegate() {
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            AccessibilityNodeInfoCompat superNode = AccessibilityNodeInfoCompat.obtain(info);
            super.onInitializeAccessibilityNodeInfo(host, superNode);
            copyNodeInfoNoChildren(info, superNode);
            superNode.recycle();
            info.setClassName(SlidingPaneLayout.class.getName());
            info.setSource(host);
            ViewParent parent = ViewCompat.getParentForAccessibility(host);
            if (parent instanceof View) {
                info.setParent((View) parent);
            }
            int childCount = SlidingPaneLayout.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = SlidingPaneLayout.this.getChildAt(i);
                if (!filter(child) && child.getVisibility() == 0) {
                    ViewCompat.setImportantForAccessibility(child, 1);
                    info.addChild(child);
                }
            }
        }

        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(SlidingPaneLayout.class.getName());
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
            if (!filter(child)) {
                return super.onRequestSendAccessibilityEvent(host, child, event);
            }
            return false;
        }

        public boolean filter(View child) {
            return SlidingPaneLayout.this.isDimmed(child);
        }

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat dest, AccessibilityNodeInfoCompat src) {
            Rect rect = this.mTmpRect;
            src.getBoundsInParent(rect);
            dest.setBoundsInParent(rect);
            src.getBoundsInScreen(rect);
            dest.setBoundsInScreen(rect);
            dest.setVisibleToUser(src.isVisibleToUser());
            dest.setPackageName(src.getPackageName());
            dest.setClassName(src.getClassName());
            dest.setContentDescription(src.getContentDescription());
            dest.setEnabled(src.isEnabled());
            dest.setClickable(src.isClickable());
            dest.setFocusable(src.isFocusable());
            dest.setFocused(src.isFocused());
            dest.setAccessibilityFocused(src.isAccessibilityFocused());
            dest.setSelected(src.isSelected());
            dest.setLongClickable(src.isLongClickable());
            dest.addAction(src.getActions());
            dest.setMovementGranularities(src.getMovementGranularities());
        }
    }

    private class DisableLayerRunnable implements Runnable {
        final View mChildView;

        DisableLayerRunnable(View childView) {
            this.mChildView = childView;
        }

        public void run() {
            if (this.mChildView.getParent() == SlidingPaneLayout.this) {
                this.mChildView.setLayerType(0, (Paint) null);
                SlidingPaneLayout.this.invalidateChildRegion(this.mChildView);
            }
            SlidingPaneLayout.this.mPostedRunnables.remove(this);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isLayoutRtlSupport() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }
}
