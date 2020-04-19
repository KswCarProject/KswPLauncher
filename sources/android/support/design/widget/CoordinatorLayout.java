package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.coordinatorlayout.R;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.util.Pools;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.DirectedAcyclicGraph;
import android.support.v4.widget.ViewGroupUtils;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent2 {
    static final Class<?>[] CONSTRUCTOR_PARAMS = {Context.class, AttributeSet.class};
    static final int EVENT_NESTED_SCROLL = 1;
    static final int EVENT_PRE_DRAW = 0;
    static final int EVENT_VIEW_REMOVED = 2;
    static final String TAG = "CoordinatorLayout";
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal<Map<String, Constructor<Behavior>>> sConstructors = new ThreadLocal<>();
    private static final Pools.Pool<Rect> sRectPool = new Pools.SynchronizedPool(12);
    private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
    private View mBehaviorTouchView;
    private final DirectedAcyclicGraph<View> mChildDag;
    private final List<View> mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private WindowInsetsCompat mLastInsets;
    private boolean mNeedsPreDrawListener;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    private OnPreDrawListener mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempDependenciesList;
    private final int[] mTempIntPair;
    private final List<View> mTempList1;

    public interface AttachedBehavior {
        @NonNull
        Behavior getBehavior();
    }

    @Deprecated
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DefaultBehavior {
        Class<? extends Behavior> value();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DispatchChangeEvent {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Class<?>[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            java.lang.Class<android.support.design.widget.CoordinatorLayout> r0 = android.support.design.widget.CoordinatorLayout.class
            java.lang.Package r0 = r0.getPackage()
            r1 = 0
            if (r0 == 0) goto L_0x000e
            java.lang.String r2 = r0.getName()
            goto L_0x000f
        L_0x000e:
            r2 = r1
        L_0x000f:
            WIDGET_PACKAGE_NAME = r2
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 21
            if (r0 < r2) goto L_0x001f
            android.support.design.widget.CoordinatorLayout$ViewElevationComparator r0 = new android.support.design.widget.CoordinatorLayout$ViewElevationComparator
            r0.<init>()
            TOP_SORTED_CHILDREN_COMPARATOR = r0
            goto L_0x0021
        L_0x001f:
            TOP_SORTED_CHILDREN_COMPARATOR = r1
        L_0x0021:
            r0 = 2
            java.lang.Class[] r0 = new java.lang.Class[r0]
            r1 = 0
            java.lang.Class<android.content.Context> r2 = android.content.Context.class
            r0[r1] = r2
            r1 = 1
            java.lang.Class<android.util.AttributeSet> r2 = android.util.AttributeSet.class
            r0[r1] = r2
            CONSTRUCTOR_PARAMS = r0
            java.lang.ThreadLocal r0 = new java.lang.ThreadLocal
            r0.<init>()
            sConstructors = r0
            android.support.v4.util.Pools$SynchronizedPool r0 = new android.support.v4.util.Pools$SynchronizedPool
            r1 = 12
            r0.<init>(r1)
            sRectPool = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.<clinit>():void");
    }

    @NonNull
    private static Rect acquireTempRect() {
        Rect rect = sRectPool.acquire();
        if (rect == null) {
            return new Rect();
        }
        return rect;
    }

    private static void releaseTempRect(@NonNull Rect rect) {
        rect.setEmpty();
        sRectPool.release(rect);
    }

    public CoordinatorLayout(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public CoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.coordinatorLayoutStyle);
    }

    public CoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a;
        this.mDependencySortedChildren = new ArrayList();
        this.mChildDag = new DirectedAcyclicGraph<>();
        this.mTempList1 = new ArrayList();
        this.mTempDependenciesList = new ArrayList();
        this.mTempIntPair = new int[2];
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        if (defStyleAttr == 0) {
            a = context.obtainStyledAttributes(attrs, R.styleable.CoordinatorLayout, 0, R.style.Widget_Support_CoordinatorLayout);
        } else {
            a = context.obtainStyledAttributes(attrs, R.styleable.CoordinatorLayout, defStyleAttr, 0);
        }
        int keylineArrayRes = a.getResourceId(R.styleable.CoordinatorLayout_keylines, 0);
        if (keylineArrayRes != 0) {
            Resources res = context.getResources();
            this.mKeylines = res.getIntArray(keylineArrayRes);
            float density = res.getDisplayMetrics().density;
            int count = this.mKeylines.length;
            for (int i = 0; i < count; i++) {
                this.mKeylines[i] = (int) (((float) this.mKeylines[i]) * density);
            }
        }
        this.mStatusBarBackground = a.getDrawable(R.styleable.CoordinatorLayout_statusBarBackground);
        a.recycle();
        setupForInsets();
        super.setOnHierarchyChangeListener(new HierarchyChangeListener());
    }

    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.mOnHierarchyChangeListener = onHierarchyChangeListener;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        resetTouchBehaviors(false);
        if (this.mNeedsPreDrawListener) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener();
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        if (this.mLastInsets == null && ViewCompat.getFitsSystemWindows(this)) {
            ViewCompat.requestApplyInsets(this);
        }
        this.mIsAttachedToWindow = true;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        resetTouchBehaviors(false);
        if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        if (this.mNestedScrollingTarget != null) {
            onStopNestedScroll(this.mNestedScrollingTarget);
        }
        this.mIsAttachedToWindow = false;
    }

    public void setStatusBarBackground(@Nullable Drawable bg) {
        if (this.mStatusBarBackground != bg) {
            Drawable drawable = null;
            if (this.mStatusBarBackground != null) {
                this.mStatusBarBackground.setCallback((Drawable.Callback) null);
            }
            if (bg != null) {
                drawable = bg.mutate();
            }
            this.mStatusBarBackground = drawable;
            if (this.mStatusBarBackground != null) {
                if (this.mStatusBarBackground.isStateful()) {
                    this.mStatusBarBackground.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.mStatusBarBackground, ViewCompat.getLayoutDirection(this));
                this.mStatusBarBackground.setVisible(getVisibility() == 0, false);
                this.mStatusBarBackground.setCallback(this);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Nullable
    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] state = getDrawableState();
        boolean changed = false;
        Drawable d = this.mStatusBarBackground;
        if (d != null && d.isStateful()) {
            changed = false | d.setState(state);
        }
        if (changed) {
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.mStatusBarBackground;
    }

    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        boolean visible = visibility == 0;
        if (this.mStatusBarBackground != null && this.mStatusBarBackground.isVisible() != visible) {
            this.mStatusBarBackground.setVisible(visible, false);
        }
    }

    public void setStatusBarBackgroundResource(@DrawableRes int resId) {
        setStatusBarBackground(resId != 0 ? ContextCompat.getDrawable(getContext(), resId) : null);
    }

    public void setStatusBarBackgroundColor(@ColorInt int color) {
        setStatusBarBackground(new ColorDrawable(color));
    }

    /* access modifiers changed from: package-private */
    public final WindowInsetsCompat setWindowInsets(WindowInsetsCompat insets) {
        if (ObjectsCompat.equals(this.mLastInsets, insets)) {
            return insets;
        }
        this.mLastInsets = insets;
        boolean z = true;
        this.mDrawStatusBarBackground = insets != null && insets.getSystemWindowInsetTop() > 0;
        if (this.mDrawStatusBarBackground || getBackground() != null) {
            z = false;
        }
        setWillNotDraw(z);
        WindowInsetsCompat insets2 = dispatchApplyWindowInsetsToBehaviors(insets);
        requestLayout();
        return insets2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final WindowInsetsCompat getLastWindowInsets() {
        return this.mLastInsets;
    }

    private void resetTouchBehaviors(boolean notifyOnInterceptTouchEvent) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            Behavior b = ((LayoutParams) child.getLayoutParams()).getBehavior();
            if (b != null) {
                long now = SystemClock.uptimeMillis();
                MotionEvent cancelEvent = MotionEvent.obtain(now, now, 3, 0.0f, 0.0f, 0);
                if (notifyOnInterceptTouchEvent) {
                    b.onInterceptTouchEvent(this, child, cancelEvent);
                } else {
                    b.onTouchEvent(this, child, cancelEvent);
                }
                cancelEvent.recycle();
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            ((LayoutParams) getChildAt(i2).getLayoutParams()).resetTouchBehaviorTracking();
        }
        this.mBehaviorTouchView = null;
        this.mDisallowInterceptReset = false;
    }

    private void getTopSortedChildren(List<View> out) {
        out.clear();
        boolean useCustomOrder = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            out.add(getChildAt(useCustomOrder ? getChildDrawingOrder(childCount, i) : i));
        }
        if (TOP_SORTED_CHILDREN_COMPARATOR != null) {
            Collections.sort(out, TOP_SORTED_CHILDREN_COMPARATOR);
        }
    }

    private boolean performIntercept(MotionEvent ev, int type) {
        MotionEvent motionEvent = ev;
        int action = ev.getActionMasked();
        List<View> topmostChildList = this.mTempList1;
        getTopSortedChildren(topmostChildList);
        int childCount = topmostChildList.size();
        MotionEvent cancelEvent = null;
        boolean newBlock = false;
        boolean intercepted = false;
        for (int i = 0; i < childCount; i++) {
            View child = topmostChildList.get(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            Behavior b = lp.getBehavior();
            if ((intercepted || newBlock) && action != 0) {
                if (b != null) {
                    if (cancelEvent == null) {
                        long now = SystemClock.uptimeMillis();
                        cancelEvent = MotionEvent.obtain(now, now, 3, 0.0f, 0.0f, 0);
                    }
                    switch (type) {
                        case 0:
                            b.onInterceptTouchEvent(this, child, cancelEvent);
                            break;
                        case 1:
                            b.onTouchEvent(this, child, cancelEvent);
                            break;
                    }
                }
            } else {
                if (!intercepted && b != null) {
                    switch (type) {
                        case 0:
                            intercepted = b.onInterceptTouchEvent(this, child, motionEvent);
                            break;
                        case 1:
                            intercepted = b.onTouchEvent(this, child, motionEvent);
                            break;
                    }
                    if (intercepted) {
                        this.mBehaviorTouchView = child;
                    }
                }
                boolean wasBlocking = lp.didBlockInteraction();
                boolean isBlocking = lp.isBlockingInteractionBelow(this, child);
                newBlock = isBlocking && !wasBlocking;
                if (isBlocking && !newBlock) {
                    topmostChildList.clear();
                    return intercepted;
                }
            }
        }
        topmostChildList.clear();
        return intercepted;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        if (action == 0) {
            resetTouchBehaviors(true);
        }
        boolean intercepted = performIntercept(ev, 0);
        if (action == 1 || action == 3) {
            resetTouchBehaviors(true);
        }
        return intercepted;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0015, code lost:
        if (r6 != false) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = 0
            r3 = 0
            r4 = 0
            int r5 = r19.getActionMasked()
            android.view.View r6 = r0.mBehaviorTouchView
            r7 = 1
            if (r6 != 0) goto L_0x0017
            boolean r6 = r0.performIntercept(r1, r7)
            r3 = r6
            if (r6 == 0) goto L_0x002b
        L_0x0017:
            android.view.View r6 = r0.mBehaviorTouchView
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            android.support.design.widget.CoordinatorLayout$LayoutParams r6 = (android.support.design.widget.CoordinatorLayout.LayoutParams) r6
            android.support.design.widget.CoordinatorLayout$Behavior r8 = r6.getBehavior()
            if (r8 == 0) goto L_0x002b
            android.view.View r9 = r0.mBehaviorTouchView
            boolean r2 = r8.onTouchEvent(r0, r9, r1)
        L_0x002b:
            android.view.View r6 = r0.mBehaviorTouchView
            if (r6 != 0) goto L_0x0035
            boolean r6 = super.onTouchEvent(r19)
            r2 = r2 | r6
            goto L_0x004c
        L_0x0035:
            if (r3 == 0) goto L_0x004c
            if (r4 != 0) goto L_0x0049
            long r16 = android.os.SystemClock.uptimeMillis()
            r12 = 3
            r13 = 0
            r14 = 0
            r15 = 0
            r8 = r16
            r10 = r16
            android.view.MotionEvent r4 = android.view.MotionEvent.obtain(r8, r10, r12, r13, r14, r15)
        L_0x0049:
            super.onTouchEvent(r4)
        L_0x004c:
            if (r4 == 0) goto L_0x0052
            r4.recycle()
        L_0x0052:
            if (r5 == r7) goto L_0x0057
            r6 = 3
            if (r5 != r6) goto L_0x005b
        L_0x0057:
            r6 = 0
            r0.resetTouchBehaviors(r6)
        L_0x005b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
        if (disallowIntercept && !this.mDisallowInterceptReset) {
            resetTouchBehaviors(false);
            this.mDisallowInterceptReset = true;
        }
    }

    private int getKeyline(int index) {
        if (this.mKeylines == null) {
            Log.e(TAG, "No keylines defined for " + this + " - attempted index lookup " + index);
            return 0;
        } else if (index >= 0 && index < this.mKeylines.length) {
            return this.mKeylines[index];
        } else {
            Log.e(TAG, "Keyline index " + index + " out of range for " + this);
            return 0;
        }
    }

    static Behavior parseBehavior(Context context, AttributeSet attrs, String name) {
        String fullName;
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        if (name.startsWith(".")) {
            fullName = context.getPackageName() + name;
        } else if (name.indexOf(46) >= 0) {
            fullName = name;
        } else if (!TextUtils.isEmpty(WIDGET_PACKAGE_NAME)) {
            fullName = WIDGET_PACKAGE_NAME + '.' + name;
        } else {
            fullName = name;
        }
        try {
            Map<String, Constructor<Behavior>> constructors = sConstructors.get();
            if (constructors == null) {
                constructors = new HashMap<>();
                sConstructors.set(constructors);
            }
            Constructor<?> constructor = constructors.get(fullName);
            if (constructor == null) {
                constructor = context.getClassLoader().loadClass(fullName).getConstructor(CONSTRUCTOR_PARAMS);
                constructor.setAccessible(true);
                constructors.put(fullName, constructor);
            }
            return (Behavior) constructor.newInstance(new Object[]{context, attrs});
        } catch (Exception e) {
            throw new RuntimeException("Could not inflate Behavior subclass " + fullName, e);
        }
    }

    /* access modifiers changed from: package-private */
    public LayoutParams getResolvedLayoutParams(View child) {
        LayoutParams result = (LayoutParams) child.getLayoutParams();
        if (!result.mBehaviorResolved) {
            if (child instanceof AttachedBehavior) {
                Behavior attachedBehavior = ((AttachedBehavior) child).getBehavior();
                if (attachedBehavior == null) {
                    Log.e(TAG, "Attached behavior class is null");
                }
                result.setBehavior(attachedBehavior);
                result.mBehaviorResolved = true;
            } else {
                DefaultBehavior defaultBehavior = null;
                for (Class cls = child.getClass(); cls != null; cls = cls.getSuperclass()) {
                    DefaultBehavior defaultBehavior2 = (DefaultBehavior) cls.getAnnotation(DefaultBehavior.class);
                    defaultBehavior = defaultBehavior2;
                    if (defaultBehavior2 != null) {
                        break;
                    }
                }
                if (defaultBehavior != null) {
                    try {
                        result.setBehavior((Behavior) defaultBehavior.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception e) {
                        Log.e(TAG, "Default behavior class " + defaultBehavior.value().getName() + " could not be instantiated. Did you forget" + " a default constructor?", e);
                    }
                }
                result.mBehaviorResolved = true;
            }
        }
        return result;
    }

    private void prepareChildren() {
        this.mDependencySortedChildren.clear();
        this.mChildDag.clear();
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            LayoutParams lp = getResolvedLayoutParams(view);
            lp.findAnchorView(this, view);
            this.mChildDag.addNode(view);
            for (int j = 0; j < count; j++) {
                if (j != i) {
                    View other = getChildAt(j);
                    if (lp.dependsOn(this, view, other)) {
                        if (!this.mChildDag.contains(other)) {
                            this.mChildDag.addNode(other);
                        }
                        this.mChildDag.addEdge(other, view);
                    }
                }
            }
        }
        this.mDependencySortedChildren.addAll(this.mChildDag.getSortedList());
        Collections.reverse(this.mDependencySortedChildren);
    }

    /* access modifiers changed from: package-private */
    public void getDescendantRect(View descendant, Rect out) {
        ViewGroupUtils.getDescendantRect(this, descendant, out);
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    public void onMeasureChild(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0138, code lost:
        if (r26.onMeasureChild(r34, r21, r29, r23, r22, 0) == false) goto L_0x0147;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r35, int r36) {
        /*
            r34 = this;
            r7 = r34
            r34.prepareChildren()
            r34.ensurePreDrawListener()
            int r8 = r34.getPaddingLeft()
            int r9 = r34.getPaddingTop()
            int r10 = r34.getPaddingRight()
            int r11 = r34.getPaddingBottom()
            int r12 = android.support.v4.view.ViewCompat.getLayoutDirection(r34)
            r0 = 1
            if (r12 != r0) goto L_0x0021
            r1 = r0
            goto L_0x0022
        L_0x0021:
            r1 = 0
        L_0x0022:
            r14 = r1
            int r15 = android.view.View.MeasureSpec.getMode(r35)
            int r16 = android.view.View.MeasureSpec.getSize(r35)
            int r6 = android.view.View.MeasureSpec.getMode(r36)
            int r17 = android.view.View.MeasureSpec.getSize(r36)
            int r18 = r8 + r10
            int r19 = r9 + r11
            int r1 = r34.getSuggestedMinimumWidth()
            int r2 = r34.getSuggestedMinimumHeight()
            r3 = 0
            android.support.v4.view.WindowInsetsCompat r4 = r7.mLastInsets
            if (r4 == 0) goto L_0x004b
            boolean r4 = android.support.v4.view.ViewCompat.getFitsSystemWindows(r34)
            if (r4 == 0) goto L_0x004b
            goto L_0x004c
        L_0x004b:
            r0 = 0
        L_0x004c:
            r20 = r0
            java.util.List<android.view.View> r0 = r7.mDependencySortedChildren
            int r5 = r0.size()
            r4 = r1
            r0 = 0
        L_0x0056:
            r1 = r0
            if (r1 >= r5) goto L_0x018e
            java.util.List<android.view.View> r0 = r7.mDependencySortedChildren
            java.lang.Object r0 = r0.get(r1)
            r21 = r0
            android.view.View r21 = (android.view.View) r21
            int r0 = r21.getVisibility()
            r13 = 8
            if (r0 != r13) goto L_0x0076
            r24 = r1
            r27 = r5
            r28 = r6
            r25 = 0
            goto L_0x0186
        L_0x0076:
            android.view.ViewGroup$LayoutParams r0 = r21.getLayoutParams()
            r13 = r0
            android.support.design.widget.CoordinatorLayout$LayoutParams r13 = (android.support.design.widget.CoordinatorLayout.LayoutParams) r13
            r0 = 0
            r23 = r0
            int r0 = r13.keyline
            if (r0 < 0) goto L_0x00ca
            if (r15 == 0) goto L_0x00ca
            int r0 = r13.keyline
            int r0 = r7.getKeyline(r0)
            r24 = r1
            int r1 = r13.gravity
            int r1 = resolveKeylineGravity(r1)
            int r1 = android.support.v4.view.GravityCompat.getAbsoluteGravity(r1, r12)
            r1 = r1 & 7
            r25 = r2
            r2 = 3
            if (r1 != r2) goto L_0x00a1
            if (r14 == 0) goto L_0x00a6
        L_0x00a1:
            r2 = 5
            if (r1 != r2) goto L_0x00b3
            if (r14 == 0) goto L_0x00b3
        L_0x00a6:
            int r2 = r16 - r10
            int r2 = r2 - r0
            r27 = r3
            r3 = 0
            int r2 = java.lang.Math.max(r3, r2)
            r23 = r2
            goto L_0x00d1
        L_0x00b3:
            r27 = r3
            if (r1 != r2) goto L_0x00b9
            if (r14 == 0) goto L_0x00be
        L_0x00b9:
            r2 = 3
            if (r1 != r2) goto L_0x00c8
            if (r14 == 0) goto L_0x00c8
        L_0x00be:
            int r2 = r0 - r8
            r3 = 0
            int r0 = java.lang.Math.max(r3, r2)
            r23 = r0
            goto L_0x00d1
        L_0x00c8:
            r3 = 0
            goto L_0x00d1
        L_0x00ca:
            r24 = r1
            r25 = r2
            r27 = r3
            r3 = 0
        L_0x00d1:
            r0 = r35
            r1 = r36
            if (r20 == 0) goto L_0x010c
            boolean r2 = android.support.v4.view.ViewCompat.getFitsSystemWindows(r21)
            if (r2 != 0) goto L_0x010c
            android.support.v4.view.WindowInsetsCompat r2 = r7.mLastInsets
            int r2 = r2.getSystemWindowInsetLeft()
            android.support.v4.view.WindowInsetsCompat r3 = r7.mLastInsets
            int r3 = r3.getSystemWindowInsetRight()
            int r2 = r2 + r3
            android.support.v4.view.WindowInsetsCompat r3 = r7.mLastInsets
            int r3 = r3.getSystemWindowInsetTop()
            r29 = r0
            android.support.v4.view.WindowInsetsCompat r0 = r7.mLastInsets
            int r0 = r0.getSystemWindowInsetBottom()
            int r3 = r3 + r0
            int r0 = r16 - r2
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r15)
            r30 = r0
            int r0 = r17 - r3
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r6)
            r22 = r0
            r29 = r30
            goto L_0x0110
        L_0x010c:
            r29 = r0
            r22 = r1
        L_0x0110:
            android.support.design.widget.CoordinatorLayout$Behavior r26 = r13.getBehavior()
            if (r26 == 0) goto L_0x013b
            r30 = 0
            r0 = r26
            r1 = r34
            r3 = r25
            r2 = r21
            r32 = r3
            r31 = r27
            r25 = 0
            r3 = r29
            r33 = r4
            r4 = r23
            r27 = r5
            r5 = r22
            r28 = r6
            r6 = r30
            boolean r0 = r0.onMeasureChild(r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x0155
            goto L_0x0147
        L_0x013b:
            r33 = r4
            r28 = r6
            r32 = r25
            r31 = r27
            r25 = 0
            r27 = r5
        L_0x0147:
            r5 = 0
            r0 = r34
            r1 = r21
            r2 = r29
            r3 = r23
            r4 = r22
            r0.onMeasureChild(r1, r2, r3, r4, r5)
        L_0x0155:
            int r0 = r21.getMeasuredWidth()
            int r0 = r18 + r0
            int r1 = r13.leftMargin
            int r0 = r0 + r1
            int r1 = r13.rightMargin
            int r0 = r0 + r1
            r1 = r33
            int r0 = java.lang.Math.max(r1, r0)
            int r1 = r21.getMeasuredHeight()
            int r1 = r19 + r1
            int r2 = r13.topMargin
            int r1 = r1 + r2
            int r2 = r13.bottomMargin
            int r1 = r1 + r2
            r2 = r32
            int r1 = java.lang.Math.max(r2, r1)
            int r2 = r21.getMeasuredState()
            r3 = r31
            int r2 = android.view.View.combineMeasuredStates(r3, r2)
            r4 = r0
            r3 = r2
            r2 = r1
        L_0x0186:
            int r0 = r24 + 1
            r5 = r27
            r6 = r28
            goto L_0x0056
        L_0x018e:
            r1 = r4
            r27 = r5
            r28 = r6
            r0 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r0 = r0 & r3
            r4 = r35
            int r0 = android.view.View.resolveSizeAndState(r1, r4, r0)
            int r5 = r3 << 16
            r6 = r36
            int r5 = android.view.View.resolveSizeAndState(r2, r6, r5)
            r7.setMeasuredDimension(r0, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat insets) {
        Behavior b;
        if (insets.isConsumed()) {
            return insets;
        }
        int z = getChildCount();
        for (int i = 0; i < z; i++) {
            View child = getChildAt(i);
            if (ViewCompat.getFitsSystemWindows(child) && (b = ((LayoutParams) child.getLayoutParams()).getBehavior()) != null) {
                insets = b.onApplyWindowInsets(this, child, insets);
                if (insets.isConsumed()) {
                    break;
                }
            }
        }
        return insets;
    }

    public void onLayoutChild(@NonNull View child, int layoutDirection) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.checkAnchorChanged()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        } else if (lp.mAnchorView != null) {
            layoutChildWithAnchor(child, lp.mAnchorView, layoutDirection);
        } else if (lp.keyline >= 0) {
            layoutChildWithKeyline(child, lp.keyline, layoutDirection);
        } else {
            layoutChild(child, layoutDirection);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        Behavior behavior;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int childCount = this.mDependencySortedChildren.size();
        for (int i = 0; i < childCount; i++) {
            View child = this.mDependencySortedChildren.get(i);
            if (child.getVisibility() != 8 && ((behavior = ((LayoutParams) child.getLayoutParams()).getBehavior()) == null || !behavior.onLayoutChild(this, child, layoutDirection))) {
                onLayoutChild(child, layoutDirection);
            }
        }
    }

    public void onDraw(Canvas c) {
        super.onDraw(c);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            int inset = this.mLastInsets != null ? this.mLastInsets.getSystemWindowInsetTop() : 0;
            if (inset > 0) {
                this.mStatusBarBackground.setBounds(0, 0, getWidth(), inset);
                this.mStatusBarBackground.draw(c);
            }
        }
    }

    public void setFitsSystemWindows(boolean fitSystemWindows) {
        super.setFitsSystemWindows(fitSystemWindows);
        setupForInsets();
    }

    /* access modifiers changed from: package-private */
    public void recordLastChildRect(View child, Rect r) {
        ((LayoutParams) child.getLayoutParams()).setLastChildRect(r);
    }

    /* access modifiers changed from: package-private */
    public void getLastChildRect(View child, Rect out) {
        out.set(((LayoutParams) child.getLayoutParams()).getLastChildRect());
    }

    /* access modifiers changed from: package-private */
    public void getChildRect(View child, boolean transform, Rect out) {
        if (child.isLayoutRequested() || child.getVisibility() == 8) {
            out.setEmpty();
        } else if (transform) {
            getDescendantRect(child, out);
        } else {
            out.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
        }
    }

    private void getDesiredAnchoredChildRectWithoutConstraints(View child, int layoutDirection, Rect anchorRect, Rect out, LayoutParams lp, int childWidth, int childHeight) {
        int left;
        int top;
        int i = layoutDirection;
        Rect rect = anchorRect;
        LayoutParams layoutParams = lp;
        int absGravity = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(layoutParams.gravity), i);
        int absAnchorGravity = GravityCompat.getAbsoluteGravity(resolveGravity(layoutParams.anchorGravity), i);
        int hgrav = absGravity & 7;
        int vgrav = absGravity & 112;
        int anchorHgrav = absAnchorGravity & 7;
        int anchorVgrav = absAnchorGravity & 112;
        if (anchorHgrav == 1) {
            left = rect.left + (anchorRect.width() / 2);
        } else if (anchorHgrav != 5) {
            left = rect.left;
        } else {
            left = rect.right;
        }
        if (anchorVgrav == 16) {
            top = rect.top + (anchorRect.height() / 2);
        } else if (anchorVgrav != 80) {
            top = rect.top;
        } else {
            top = rect.bottom;
        }
        if (hgrav == 1) {
            left -= childWidth / 2;
        } else if (hgrav != 5) {
            left -= childWidth;
        }
        if (vgrav == 16) {
            top -= childHeight / 2;
        } else if (vgrav != 80) {
            top -= childHeight;
        }
        out.set(left, top, left + childWidth, top + childHeight);
    }

    private void constrainChildRect(LayoutParams lp, Rect out, int childWidth, int childHeight) {
        int width = getWidth();
        int height = getHeight();
        int left = Math.max(getPaddingLeft() + lp.leftMargin, Math.min(out.left, ((width - getPaddingRight()) - childWidth) - lp.rightMargin));
        int top = Math.max(getPaddingTop() + lp.topMargin, Math.min(out.top, ((height - getPaddingBottom()) - childHeight) - lp.bottomMargin));
        out.set(left, top, left + childWidth, top + childHeight);
    }

    /* access modifiers changed from: package-private */
    public void getDesiredAnchoredChildRect(View child, int layoutDirection, Rect anchorRect, Rect out) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int childWidth = child.getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();
        getDesiredAnchoredChildRectWithoutConstraints(child, layoutDirection, anchorRect, out, lp, childWidth, childHeight);
        constrainChildRect(lp, out, childWidth, childHeight);
    }

    private void layoutChildWithAnchor(View child, View anchor, int layoutDirection) {
        Rect anchorRect = acquireTempRect();
        Rect childRect = acquireTempRect();
        try {
            getDescendantRect(anchor, anchorRect);
            getDesiredAnchoredChildRect(child, layoutDirection, anchorRect, childRect);
            child.layout(childRect.left, childRect.top, childRect.right, childRect.bottom);
        } finally {
            releaseTempRect(anchorRect);
            releaseTempRect(childRect);
        }
    }

    private void layoutChildWithKeyline(View child, int keyline, int layoutDirection) {
        int keyline2;
        int i = layoutDirection;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int absGravity = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(lp.gravity), i);
        int hgrav = absGravity & 7;
        int vgrav = absGravity & 112;
        int width = getWidth();
        int height = getHeight();
        int childWidth = child.getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();
        if (i == 1) {
            keyline2 = width - keyline;
        } else {
            keyline2 = keyline;
        }
        int left = getKeyline(keyline2) - childWidth;
        int top = 0;
        if (hgrav == 1) {
            left += childWidth / 2;
        } else if (hgrav == 5) {
            left += childWidth;
        }
        if (vgrav == 16) {
            top = 0 + (childHeight / 2);
        } else if (vgrav == 80) {
            top = 0 + childHeight;
        }
        int left2 = Math.max(getPaddingLeft() + lp.leftMargin, Math.min(left, ((width - getPaddingRight()) - childWidth) - lp.rightMargin));
        int top2 = Math.max(getPaddingTop() + lp.topMargin, Math.min(top, ((height - getPaddingBottom()) - childHeight) - lp.bottomMargin));
        child.layout(left2, top2, left2 + childWidth, top2 + childHeight);
    }

    private void layoutChild(View child, int layoutDirection) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        Rect parent = acquireTempRect();
        parent.set(getPaddingLeft() + lp.leftMargin, getPaddingTop() + lp.topMargin, (getWidth() - getPaddingRight()) - lp.rightMargin, (getHeight() - getPaddingBottom()) - lp.bottomMargin);
        if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this) && !ViewCompat.getFitsSystemWindows(child)) {
            parent.left += this.mLastInsets.getSystemWindowInsetLeft();
            parent.top += this.mLastInsets.getSystemWindowInsetTop();
            parent.right -= this.mLastInsets.getSystemWindowInsetRight();
            parent.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
        }
        Rect out = acquireTempRect();
        GravityCompat.apply(resolveGravity(lp.gravity), child.getMeasuredWidth(), child.getMeasuredHeight(), parent, out, layoutDirection);
        child.layout(out.left, out.top, out.right, out.bottom);
        releaseTempRect(parent);
        releaseTempRect(out);
    }

    private static int resolveGravity(int gravity) {
        if ((gravity & 7) == 0) {
            gravity |= GravityCompat.START;
        }
        if ((gravity & 112) == 0) {
            return gravity | 48;
        }
        return gravity;
    }

    private static int resolveKeylineGravity(int gravity) {
        if (gravity == 0) {
            return 8388661;
        }
        return gravity;
    }

    private static int resolveAnchoredChildGravity(int gravity) {
        if (gravity == 0) {
            return 17;
        }
        return gravity;
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.mBehavior != null) {
            float scrimAlpha = lp.mBehavior.getScrimOpacity(this, child);
            if (scrimAlpha > 0.0f) {
                if (this.mScrimPaint == null) {
                    this.mScrimPaint = new Paint();
                }
                this.mScrimPaint.setColor(lp.mBehavior.getScrimColor(this, child));
                this.mScrimPaint.setAlpha(clamp(Math.round(255.0f * scrimAlpha), 0, 255));
                int saved = canvas.save();
                if (child.isOpaque()) {
                    canvas.clipRect((float) child.getLeft(), (float) child.getTop(), (float) child.getRight(), (float) child.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()), this.mScrimPaint);
                canvas.restoreToCount(saved);
            }
        }
        return super.drawChild(canvas, child, drawingTime);
    }

    private static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    /* access modifiers changed from: package-private */
    public final void onChildViewsChanged(int type) {
        boolean handled;
        int i = type;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int childCount = this.mDependencySortedChildren.size();
        Rect inset = acquireTempRect();
        Rect drawRect = acquireTempRect();
        Rect lastDrawRect = acquireTempRect();
        for (int i2 = 0; i2 < childCount; i2++) {
            View child = this.mDependencySortedChildren.get(i2);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (i != 0 || child.getVisibility() != 8) {
                for (int j = 0; j < i2; j++) {
                    if (lp.mAnchorDirectChild == this.mDependencySortedChildren.get(j)) {
                        offsetChildToAnchor(child, layoutDirection);
                    }
                }
                int j2 = 1;
                getChildRect(child, true, drawRect);
                if (lp.insetEdge != 0 && !drawRect.isEmpty()) {
                    int absInsetEdge = GravityCompat.getAbsoluteGravity(lp.insetEdge, layoutDirection);
                    int i3 = absInsetEdge & 112;
                    if (i3 == 48) {
                        inset.top = Math.max(inset.top, drawRect.bottom);
                    } else if (i3 == 80) {
                        inset.bottom = Math.max(inset.bottom, getHeight() - drawRect.top);
                    }
                    int i4 = absInsetEdge & 7;
                    if (i4 == 3) {
                        inset.left = Math.max(inset.left, drawRect.right);
                    } else if (i4 == 5) {
                        inset.right = Math.max(inset.right, getWidth() - drawRect.left);
                    }
                }
                if (lp.dodgeInsetEdges != 0 && child.getVisibility() == 0) {
                    offsetChildByInset(child, inset, layoutDirection);
                }
                if (i != 2) {
                    getLastChildRect(child, lastDrawRect);
                    if (!lastDrawRect.equals(drawRect)) {
                        recordLastChildRect(child, drawRect);
                    }
                }
                int j3 = i2 + 1;
                while (j3 < childCount) {
                    View checkChild = this.mDependencySortedChildren.get(j3);
                    LayoutParams checkLp = (LayoutParams) checkChild.getLayoutParams();
                    Behavior b = checkLp.getBehavior();
                    if (b != null && b.layoutDependsOn(this, checkChild, child)) {
                        if (i != 0 || !checkLp.getChangedAfterNestedScroll()) {
                            if (i != 2) {
                                handled = b.onDependentViewChanged(this, checkChild, child);
                            } else {
                                b.onDependentViewRemoved(this, checkChild, child);
                                handled = true;
                            }
                            boolean handled2 = handled;
                            if (i == j2) {
                                checkLp.setChangedAfterNestedScroll(handled2);
                            }
                        } else {
                            checkLp.resetChangedAfterNestedScroll();
                        }
                    }
                    j3++;
                    j2 = 1;
                }
            }
        }
        releaseTempRect(inset);
        releaseTempRect(drawRect);
        releaseTempRect(lastDrawRect);
    }

    private void offsetChildByInset(View child, Rect inset, int layoutDirection) {
        int distance;
        int distance2;
        int distance3;
        int distance4;
        if (ViewCompat.isLaidOut(child) && child.getWidth() > 0 && child.getHeight() > 0) {
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            Behavior behavior = lp.getBehavior();
            Rect dodgeRect = acquireTempRect();
            Rect bounds = acquireTempRect();
            bounds.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
            if (behavior == null || !behavior.getInsetDodgeRect(this, child, dodgeRect)) {
                dodgeRect.set(bounds);
            } else if (!bounds.contains(dodgeRect)) {
                throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + dodgeRect.toShortString() + " | Bounds:" + bounds.toShortString());
            }
            releaseTempRect(bounds);
            if (dodgeRect.isEmpty()) {
                releaseTempRect(dodgeRect);
                return;
            }
            int absDodgeInsetEdges = GravityCompat.getAbsoluteGravity(lp.dodgeInsetEdges, layoutDirection);
            boolean offsetY = false;
            if ((absDodgeInsetEdges & 48) == 48 && (distance4 = (dodgeRect.top - lp.topMargin) - lp.mInsetOffsetY) < inset.top) {
                setInsetOffsetY(child, inset.top - distance4);
                offsetY = true;
            }
            if ((absDodgeInsetEdges & 80) == 80 && (distance3 = ((getHeight() - dodgeRect.bottom) - lp.bottomMargin) + lp.mInsetOffsetY) < inset.bottom) {
                setInsetOffsetY(child, distance3 - inset.bottom);
                offsetY = true;
            }
            if (!offsetY) {
                setInsetOffsetY(child, 0);
            }
            boolean offsetX = false;
            if ((absDodgeInsetEdges & 3) == 3 && (distance2 = (dodgeRect.left - lp.leftMargin) - lp.mInsetOffsetX) < inset.left) {
                setInsetOffsetX(child, inset.left - distance2);
                offsetX = true;
            }
            if ((absDodgeInsetEdges & 5) == 5 && (distance = ((getWidth() - dodgeRect.right) - lp.rightMargin) + lp.mInsetOffsetX) < inset.right) {
                setInsetOffsetX(child, distance - inset.right);
                offsetX = true;
            }
            if (!offsetX) {
                setInsetOffsetX(child, 0);
            }
            releaseTempRect(dodgeRect);
        }
    }

    private void setInsetOffsetX(View child, int offsetX) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.mInsetOffsetX != offsetX) {
            ViewCompat.offsetLeftAndRight(child, offsetX - lp.mInsetOffsetX);
            lp.mInsetOffsetX = offsetX;
        }
    }

    private void setInsetOffsetY(View child, int offsetY) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.mInsetOffsetY != offsetY) {
            ViewCompat.offsetTopAndBottom(child, offsetY - lp.mInsetOffsetY);
            lp.mInsetOffsetY = offsetY;
        }
    }

    public void dispatchDependentViewsChanged(@NonNull View view) {
        List<View> dependents = this.mChildDag.getIncomingEdges(view);
        if (dependents != null && !dependents.isEmpty()) {
            for (int i = 0; i < dependents.size(); i++) {
                View child = dependents.get(i);
                Behavior b = ((LayoutParams) child.getLayoutParams()).getBehavior();
                if (b != null) {
                    b.onDependentViewChanged(this, child, view);
                }
            }
        }
    }

    @NonNull
    public List<View> getDependencies(@NonNull View child) {
        List<View> dependencies = this.mChildDag.getOutgoingEdges(child);
        this.mTempDependenciesList.clear();
        if (dependencies != null) {
            this.mTempDependenciesList.addAll(dependencies);
        }
        return this.mTempDependenciesList;
    }

    @NonNull
    public List<View> getDependents(@NonNull View child) {
        List<View> edges = this.mChildDag.getIncomingEdges(child);
        this.mTempDependenciesList.clear();
        if (edges != null) {
            this.mTempDependenciesList.addAll(edges);
        }
        return this.mTempDependenciesList;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final List<View> getDependencySortedChildren() {
        prepareChildren();
        return Collections.unmodifiableList(this.mDependencySortedChildren);
    }

    /* access modifiers changed from: package-private */
    public void ensurePreDrawListener() {
        boolean hasDependencies = false;
        int childCount = getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            } else if (hasDependencies(getChildAt(i))) {
                hasDependencies = true;
                break;
            } else {
                i++;
            }
        }
        if (hasDependencies == this.mNeedsPreDrawListener) {
            return;
        }
        if (hasDependencies) {
            addPreDrawListener();
        } else {
            removePreDrawListener();
        }
    }

    private boolean hasDependencies(View child) {
        return this.mChildDag.hasOutgoingEdges(child);
    }

    /* access modifiers changed from: package-private */
    public void addPreDrawListener() {
        if (this.mIsAttachedToWindow) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener();
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = true;
    }

    /* access modifiers changed from: package-private */
    public void removePreDrawListener() {
        if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = false;
    }

    /* access modifiers changed from: package-private */
    public void offsetChildToAnchor(View child, int layoutDirection) {
        Behavior b;
        View view = child;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.mAnchorView != null) {
            Rect anchorRect = acquireTempRect();
            Rect childRect = acquireTempRect();
            Rect desiredChildRect = acquireTempRect();
            getDescendantRect(lp.mAnchorView, anchorRect);
            boolean z = false;
            getChildRect(view, false, childRect);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            int childHeight2 = childHeight;
            getDesiredAnchoredChildRectWithoutConstraints(child, layoutDirection, anchorRect, desiredChildRect, lp, childWidth, childHeight);
            if (!(desiredChildRect.left == childRect.left && desiredChildRect.top == childRect.top)) {
                z = true;
            }
            boolean changed = z;
            constrainChildRect(lp, desiredChildRect, childWidth, childHeight2);
            int dx = desiredChildRect.left - childRect.left;
            int dy = desiredChildRect.top - childRect.top;
            if (dx != 0) {
                ViewCompat.offsetLeftAndRight(view, dx);
            }
            if (dy != 0) {
                ViewCompat.offsetTopAndBottom(view, dy);
            }
            if (changed && (b = lp.getBehavior()) != null) {
                b.onDependentViewChanged(this, view, lp.mAnchorView);
            }
            releaseTempRect(anchorRect);
            releaseTempRect(childRect);
            releaseTempRect(desiredChildRect);
        }
    }

    public boolean isPointInChildBounds(@NonNull View child, int x, int y) {
        Rect r = acquireTempRect();
        getDescendantRect(child, r);
        try {
            return r.contains(x, y);
        } finally {
            releaseTempRect(r);
        }
    }

    public boolean doViewsOverlap(@NonNull View first, @NonNull View second) {
        boolean z = false;
        if (first.getVisibility() != 0 || second.getVisibility() != 0) {
            return false;
        }
        Rect firstRect = acquireTempRect();
        getChildRect(first, first.getParent() != this, firstRect);
        Rect secondRect = acquireTempRect();
        getChildRect(second, second.getParent() != this, secondRect);
        try {
            if (firstRect.left <= secondRect.right && firstRect.top <= secondRect.bottom && firstRect.right >= secondRect.left && firstRect.bottom >= secondRect.top) {
                z = true;
            }
            return z;
        } finally {
            releaseTempRect(firstRect);
            releaseTempRect(secondRect);
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (p instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) p);
        }
        if (p instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) p);
        }
        return new LayoutParams(p);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return (p instanceof LayoutParams) && super.checkLayoutParams(p);
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return onStartNestedScroll(child, target, nestedScrollAxes, 0);
    }

    public boolean onStartNestedScroll(View child, View target, int axes, int type) {
        int i = type;
        int childCount = getChildCount();
        boolean handled = false;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < childCount) {
                View view = getChildAt(i3);
                if (view.getVisibility() != 8) {
                    LayoutParams lp = (LayoutParams) view.getLayoutParams();
                    Behavior viewBehavior = lp.getBehavior();
                    if (viewBehavior != null) {
                        boolean accepted = viewBehavior.onStartNestedScroll(this, view, child, target, axes, type);
                        lp.setNestedScrollAccepted(i, accepted);
                        handled |= accepted;
                    } else {
                        lp.setNestedScrollAccepted(i, false);
                    }
                }
                i2 = i3 + 1;
            } else {
                return handled;
            }
        }
    }

    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        onNestedScrollAccepted(child, target, nestedScrollAxes, 0);
    }

    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes, int type) {
        Behavior viewBehavior;
        View view = target;
        int i = type;
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(child, view, nestedScrollAxes, i);
        this.mNestedScrollingTarget = view;
        int childCount = getChildCount();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < childCount) {
                View view2 = getChildAt(i3);
                LayoutParams lp = (LayoutParams) view2.getLayoutParams();
                if (lp.isNestedScrollAccepted(i) && (viewBehavior = lp.getBehavior()) != null) {
                    viewBehavior.onNestedScrollAccepted(this, view2, child, target, nestedScrollAxes, type);
                }
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }

    public void onStopNestedScroll(View target) {
        onStopNestedScroll(target, 0);
    }

    public void onStopNestedScroll(View target, int type) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(target, type);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            LayoutParams lp = (LayoutParams) view.getLayoutParams();
            if (lp.isNestedScrollAccepted(type)) {
                Behavior viewBehavior = lp.getBehavior();
                if (viewBehavior != null) {
                    viewBehavior.onStopNestedScroll(this, view, target, type);
                }
                lp.resetNestedScroll(type);
                lp.resetChangedAfterNestedScroll();
            }
        }
        this.mNestedScrollingTarget = null;
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, 0);
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Behavior viewBehavior;
        int childCount = getChildCount();
        int i = 0;
        boolean accepted = false;
        while (true) {
            int i2 = i;
            if (i2 >= childCount) {
                break;
            }
            View view = getChildAt(i2);
            if (view.getVisibility() == 8) {
                int i3 = type;
            } else {
                LayoutParams lp = (LayoutParams) view.getLayoutParams();
                if (lp.isNestedScrollAccepted(type) && (viewBehavior = lp.getBehavior()) != null) {
                    viewBehavior.onNestedScroll(this, view, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
                    accepted = true;
                }
            }
            i = i2 + 1;
        }
        int i4 = type;
        if (accepted) {
            onChildViewsChanged(1);
        }
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        onNestedPreScroll(target, dx, dy, consumed, 0);
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed, int type) {
        Behavior viewBehavior;
        int xConsumed;
        int yConsumed;
        int childCount = getChildCount();
        int xConsumed2 = 0;
        int yConsumed2 = 0;
        boolean accepted = false;
        int xConsumed3 = 0;
        while (true) {
            int i = xConsumed3;
            if (i >= childCount) {
                break;
            }
            View view = getChildAt(i);
            if (view.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) view.getLayoutParams();
                if (lp.isNestedScrollAccepted(type) && (viewBehavior = lp.getBehavior()) != null) {
                    int[] iArr = this.mTempIntPair;
                    this.mTempIntPair[1] = 0;
                    iArr[0] = 0;
                    LayoutParams layoutParams = lp;
                    viewBehavior.onNestedPreScroll(this, view, target, dx, dy, this.mTempIntPair, type);
                    if (dx > 0) {
                        xConsumed = Math.max(xConsumed2, this.mTempIntPair[0]);
                    } else {
                        xConsumed = Math.min(xConsumed2, this.mTempIntPair[0]);
                    }
                    if (dy > 0) {
                        yConsumed = Math.max(yConsumed2, this.mTempIntPair[1]);
                    } else {
                        yConsumed = Math.min(yConsumed2, this.mTempIntPair[1]);
                    }
                    xConsumed2 = xConsumed;
                    yConsumed2 = yConsumed;
                    accepted = true;
                }
            }
            xConsumed3 = i + 1;
        }
        consumed[0] = xConsumed2;
        consumed[1] = yConsumed2;
        if (accepted) {
            onChildViewsChanged(1);
        }
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Behavior viewBehavior;
        int childCount = getChildCount();
        boolean handled = false;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= childCount) {
                break;
            }
            View view = getChildAt(i2);
            if (view.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) view.getLayoutParams();
                if (lp.isNestedScrollAccepted(0) && (viewBehavior = lp.getBehavior()) != null) {
                    handled = viewBehavior.onNestedFling(this, view, target, velocityX, velocityY, consumed) | handled;
                }
            }
            i = i2 + 1;
        }
        if (handled) {
            onChildViewsChanged(1);
        }
        return handled;
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Behavior viewBehavior;
        int childCount = getChildCount();
        boolean handled = false;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) view.getLayoutParams();
                if (lp.isNestedScrollAccepted(0) && (viewBehavior = lp.getBehavior()) != null) {
                    handled |= viewBehavior.onNestedPreFling(this, view, target, velocityX, velocityY);
                }
            }
        }
        return handled;
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    class OnPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        OnPreDrawListener() {
        }

        public boolean onPreDraw() {
            CoordinatorLayout.this.onChildViewsChanged(0);
            return true;
        }
    }

    static class ViewElevationComparator implements Comparator<View> {
        ViewElevationComparator() {
        }

        public int compare(View lhs, View rhs) {
            float lz = ViewCompat.getZ(lhs);
            float rz = ViewCompat.getZ(rhs);
            if (lz > rz) {
                return -1;
            }
            if (lz < rz) {
                return 1;
            }
            return 0;
        }
    }

    public static abstract class Behavior<V extends View> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attrs) {
        }

        public void onAttachedToLayoutParams(@NonNull LayoutParams params) {
        }

        public void onDetachedFromLayoutParams() {
        }

        public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout parent, @NonNull V v, @NonNull MotionEvent ev) {
            return false;
        }

        public boolean onTouchEvent(@NonNull CoordinatorLayout parent, @NonNull V v, @NonNull MotionEvent ev) {
            return false;
        }

        @ColorInt
        public int getScrimColor(@NonNull CoordinatorLayout parent, @NonNull V v) {
            return ViewCompat.MEASURED_STATE_MASK;
        }

        @FloatRange(from = 0.0d, to = 1.0d)
        public float getScrimOpacity(@NonNull CoordinatorLayout parent, @NonNull V v) {
            return 0.0f;
        }

        public boolean blocksInteractionBelow(@NonNull CoordinatorLayout parent, @NonNull V child) {
            return getScrimOpacity(parent, child) > 0.0f;
        }

        public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull V v, @NonNull View dependency) {
            return false;
        }

        public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull V v, @NonNull View dependency) {
            return false;
        }

        public void onDependentViewRemoved(@NonNull CoordinatorLayout parent, @NonNull V v, @NonNull View dependency) {
        }

        public boolean onMeasureChild(@NonNull CoordinatorLayout parent, @NonNull V v, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
            return false;
        }

        public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull V v, int layoutDirection) {
            return false;
        }

        public static void setTag(@NonNull View child, @Nullable Object tag) {
            ((LayoutParams) child.getLayoutParams()).mBehaviorTag = tag;
        }

        @Nullable
        public static Object getTag(@NonNull View child) {
            return ((LayoutParams) child.getLayoutParams()).mBehaviorTag;
        }

        @Deprecated
        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View directTargetChild, @NonNull View target, int axes) {
            return false;
        }

        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
            if (type == 0) {
                return onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes);
            }
            return false;
        }

        @Deprecated
        public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View directTargetChild, @NonNull View target, int axes) {
        }

        public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
            if (type == 0) {
                onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes);
            }
        }

        @Deprecated
        public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View target) {
        }

        public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int type) {
            if (type == 0) {
                onStopNestedScroll(coordinatorLayout, child, target);
            }
        }

        @Deprecated
        public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        }

        public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
            if (type == 0) {
                onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
            }
        }

        @Deprecated
        public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        }

        public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
            if (type == 0) {
                onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
            }
        }

        public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
            return false;
        }

        public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View target, float velocityX, float velocityY) {
            return false;
        }

        @NonNull
        public WindowInsetsCompat onApplyWindowInsets(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull WindowInsetsCompat insets) {
            return insets;
        }

        public boolean onRequestChildRectangleOnScreen(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull Rect rectangle, boolean immediate) {
            return false;
        }

        public void onRestoreInstanceState(@NonNull CoordinatorLayout parent, @NonNull V v, @NonNull Parcelable state) {
        }

        @Nullable
        public Parcelable onSaveInstanceState(@NonNull CoordinatorLayout parent, @NonNull V v) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        public boolean getInsetDodgeRect(@NonNull CoordinatorLayout parent, @NonNull V v, @NonNull Rect rect) {
            return false;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int anchorGravity = 0;
        public int dodgeInsetEdges = 0;
        public int gravity = 0;
        public int insetEdge = 0;
        public int keyline = -1;
        View mAnchorDirectChild;
        int mAnchorId = -1;
        View mAnchorView;
        Behavior mBehavior;
        boolean mBehaviorResolved = false;
        Object mBehaviorTag;
        private boolean mDidAcceptNestedScrollNonTouch;
        private boolean mDidAcceptNestedScrollTouch;
        private boolean mDidBlockInteraction;
        private boolean mDidChangeAfterNestedScroll;
        int mInsetOffsetX;
        int mInsetOffsetY;
        final Rect mLastChildRect = new Rect();

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        LayoutParams(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CoordinatorLayout_Layout);
            this.gravity = a.getInteger(R.styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.mAnchorId = a.getResourceId(R.styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            this.anchorGravity = a.getInteger(R.styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.keyline = a.getInteger(R.styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            this.insetEdge = a.getInt(R.styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.dodgeInsetEdges = a.getInt(R.styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            this.mBehaviorResolved = a.hasValue(R.styleable.CoordinatorLayout_Layout_layout_behavior);
            if (this.mBehaviorResolved) {
                this.mBehavior = CoordinatorLayout.parseBehavior(context, attrs, a.getString(R.styleable.CoordinatorLayout_Layout_layout_behavior));
            }
            a.recycle();
            if (this.mBehavior != null) {
                this.mBehavior.onAttachedToLayoutParams(this);
            }
        }

        public LayoutParams(LayoutParams p) {
            super(p);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams p) {
            super(p);
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        @IdRes
        public int getAnchorId() {
            return this.mAnchorId;
        }

        public void setAnchorId(@IdRes int id) {
            invalidateAnchor();
            this.mAnchorId = id;
        }

        @Nullable
        public Behavior getBehavior() {
            return this.mBehavior;
        }

        public void setBehavior(@Nullable Behavior behavior) {
            if (this.mBehavior != behavior) {
                if (this.mBehavior != null) {
                    this.mBehavior.onDetachedFromLayoutParams();
                }
                this.mBehavior = behavior;
                this.mBehaviorTag = null;
                this.mBehaviorResolved = true;
                if (behavior != null) {
                    behavior.onAttachedToLayoutParams(this);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setLastChildRect(Rect r) {
            this.mLastChildRect.set(r);
        }

        /* access modifiers changed from: package-private */
        public Rect getLastChildRect() {
            return this.mLastChildRect;
        }

        /* access modifiers changed from: package-private */
        public boolean checkAnchorChanged() {
            return this.mAnchorView == null && this.mAnchorId != -1;
        }

        /* access modifiers changed from: package-private */
        public boolean didBlockInteraction() {
            if (this.mBehavior == null) {
                this.mDidBlockInteraction = false;
            }
            return this.mDidBlockInteraction;
        }

        /* access modifiers changed from: package-private */
        public boolean isBlockingInteractionBelow(CoordinatorLayout parent, View child) {
            if (this.mDidBlockInteraction) {
                return true;
            }
            boolean blocksInteractionBelow = this.mDidBlockInteraction | (this.mBehavior != null ? this.mBehavior.blocksInteractionBelow(parent, child) : false);
            this.mDidBlockInteraction = blocksInteractionBelow;
            return blocksInteractionBelow;
        }

        /* access modifiers changed from: package-private */
        public void resetTouchBehaviorTracking() {
            this.mDidBlockInteraction = false;
        }

        /* access modifiers changed from: package-private */
        public void resetNestedScroll(int type) {
            setNestedScrollAccepted(type, false);
        }

        /* access modifiers changed from: package-private */
        public void setNestedScrollAccepted(int type, boolean accept) {
            switch (type) {
                case 0:
                    this.mDidAcceptNestedScrollTouch = accept;
                    return;
                case 1:
                    this.mDidAcceptNestedScrollNonTouch = accept;
                    return;
                default:
                    return;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isNestedScrollAccepted(int type) {
            switch (type) {
                case 0:
                    return this.mDidAcceptNestedScrollTouch;
                case 1:
                    return this.mDidAcceptNestedScrollNonTouch;
                default:
                    return false;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean getChangedAfterNestedScroll() {
            return this.mDidChangeAfterNestedScroll;
        }

        /* access modifiers changed from: package-private */
        public void setChangedAfterNestedScroll(boolean changed) {
            this.mDidChangeAfterNestedScroll = changed;
        }

        /* access modifiers changed from: package-private */
        public void resetChangedAfterNestedScroll() {
            this.mDidChangeAfterNestedScroll = false;
        }

        /* access modifiers changed from: package-private */
        public boolean dependsOn(CoordinatorLayout parent, View child, View dependency) {
            return dependency == this.mAnchorDirectChild || shouldDodge(dependency, ViewCompat.getLayoutDirection(parent)) || (this.mBehavior != null && this.mBehavior.layoutDependsOn(parent, child, dependency));
        }

        /* access modifiers changed from: package-private */
        public void invalidateAnchor() {
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
        }

        /* access modifiers changed from: package-private */
        public View findAnchorView(CoordinatorLayout parent, View forChild) {
            if (this.mAnchorId == -1) {
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
                return null;
            }
            if (this.mAnchorView == null || !verifyAnchorView(forChild, parent)) {
                resolveAnchorView(forChild, parent);
            }
            return this.mAnchorView;
        }

        private void resolveAnchorView(View forChild, CoordinatorLayout parent) {
            this.mAnchorView = parent.findViewById(this.mAnchorId);
            if (this.mAnchorView != null) {
                if (this.mAnchorView != parent) {
                    View directChild = this.mAnchorView;
                    ViewParent p = this.mAnchorView.getParent();
                    while (p != parent && p != null) {
                        if (p != forChild) {
                            if (p instanceof View) {
                                directChild = (View) p;
                            }
                            p = p.getParent();
                        } else if (parent.isInEditMode()) {
                            this.mAnchorDirectChild = null;
                            this.mAnchorView = null;
                            return;
                        } else {
                            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                        }
                    }
                    this.mAnchorDirectChild = directChild;
                } else if (parent.isInEditMode()) {
                    this.mAnchorDirectChild = null;
                    this.mAnchorView = null;
                } else {
                    throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                }
            } else if (parent.isInEditMode()) {
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
            } else {
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + parent.getResources().getResourceName(this.mAnchorId) + " to anchor view " + forChild);
            }
        }

        private boolean verifyAnchorView(View forChild, CoordinatorLayout parent) {
            if (this.mAnchorView.getId() != this.mAnchorId) {
                return false;
            }
            View directChild = this.mAnchorView;
            for (ViewParent p = this.mAnchorView.getParent(); p != parent; p = p.getParent()) {
                if (p == null || p == forChild) {
                    this.mAnchorDirectChild = null;
                    this.mAnchorView = null;
                    return false;
                }
                if (p instanceof View) {
                    directChild = (View) p;
                }
            }
            this.mAnchorDirectChild = directChild;
            return true;
        }

        private boolean shouldDodge(View other, int layoutDirection) {
            int absInset = GravityCompat.getAbsoluteGravity(((LayoutParams) other.getLayoutParams()).insetEdge, layoutDirection);
            return absInset != 0 && (GravityCompat.getAbsoluteGravity(this.dodgeInsetEdges, layoutDirection) & absInset) == absInset;
        }
    }

    private class HierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        HierarchyChangeListener() {
        }

        public void onChildViewAdded(View parent, View child) {
            if (CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
                CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        public void onChildViewRemoved(View parent, View child) {
            CoordinatorLayout.this.onChildViewsChanged(2);
            if (CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
                CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        Parcelable savedState;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        SparseArray<Parcelable> behaviorStates = ss.behaviorStates;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childId = child.getId();
            Behavior b = getResolvedLayoutParams(child).getBehavior();
            if (!(childId == -1 || b == null || (savedState = behaviorStates.get(childId)) == null)) {
                b.onRestoreInstanceState(this, child, savedState);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable state;
        SavedState ss = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> behaviorStates = new SparseArray<>();
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childId = child.getId();
            Behavior b = ((LayoutParams) child.getLayoutParams()).getBehavior();
            if (!(childId == -1 || b == null || (state = b.onSaveInstanceState(this, child)) == null)) {
                behaviorStates.append(childId, state);
            }
        }
        ss.behaviorStates = behaviorStates;
        return ss;
    }

    public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
        Behavior behavior = ((LayoutParams) child.getLayoutParams()).getBehavior();
        if (behavior == null || !behavior.onRequestChildRectangleOnScreen(this, child, rectangle, immediate)) {
            return super.requestChildRectangleOnScreen(child, rectangle, immediate);
        }
        return true;
    }

    private void setupForInsets() {
        if (Build.VERSION.SDK_INT >= 21) {
            if (ViewCompat.getFitsSystemWindows(this)) {
                if (this.mApplyWindowInsetsListener == null) {
                    this.mApplyWindowInsetsListener = new OnApplyWindowInsetsListener() {
                        public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                            return CoordinatorLayout.this.setWindowInsets(insets);
                        }
                    };
                }
                ViewCompat.setOnApplyWindowInsetsListener(this, this.mApplyWindowInsetsListener);
                setSystemUiVisibility(1280);
                return;
            }
            ViewCompat.setOnApplyWindowInsetsListener(this, (OnApplyWindowInsetsListener) null);
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in, (ClassLoader) null);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        SparseArray<Parcelable> behaviorStates;

        public SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            int size = source.readInt();
            int[] ids = new int[size];
            source.readIntArray(ids);
            Parcelable[] states = source.readParcelableArray(loader);
            this.behaviorStates = new SparseArray<>(size);
            for (int i = 0; i < size; i++) {
                this.behaviorStates.append(ids[i], states[i]);
            }
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            int size = this.behaviorStates != null ? this.behaviorStates.size() : 0;
            dest.writeInt(size);
            int[] ids = new int[size];
            Parcelable[] states = new Parcelable[size];
            for (int i = 0; i < size; i++) {
                ids[i] = this.behaviorStates.keyAt(i);
                states[i] = this.behaviorStates.valueAt(i);
            }
            dest.writeIntArray(ids);
            dest.writeParcelableArray(states, flags);
        }
    }
}
