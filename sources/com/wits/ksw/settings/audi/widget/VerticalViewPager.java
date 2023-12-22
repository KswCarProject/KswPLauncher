package com.wits.ksw.settings.audi.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import android.support.p001v4.p003os.ParcelableCompat;
import android.support.p001v4.p003os.ParcelableCompatCreatorCallbacks;
import android.support.p001v4.view.AccessibilityDelegateCompat;
import android.support.p001v4.view.MotionEventCompat;
import android.support.p001v4.view.PagerAdapter;
import android.support.p001v4.view.VelocityTrackerCompat;
import android.support.p001v4.view.ViewCompat;
import android.support.p001v4.view.ViewConfigurationCompat;
import android.support.p001v4.view.ViewPager;
import android.support.p001v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p001v4.view.accessibility.AccessibilityRecordCompat;
import android.support.p001v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes17.dex */
public class VerticalViewPager extends ViewGroup {
    private static final int CLOSE_ENOUGH = 2;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private int mActivePointerId;
    private PagerAdapter mAdapter;
    private OnAdapterChangeListener mAdapterChangeListener;
    private EdgeEffectCompat mBottomEdge;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    private int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mIgnoreGutter;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private ViewPager.OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsUnableToDrag;
    private final ArrayList<ItemInfo> mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private int mLeftPageBounds;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private int mPageMargin;
    private ViewPager.PageTransformer mPageTransformer;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private int mRightPageBounds;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private Method mSetChildrenDrawingOrderEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private EdgeEffectCompat mTopEdge;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private static final int[] LAYOUT_ATTRS = {16842931};
    private static final Comparator<ItemInfo> COMPARATOR = new Comparator<ItemInfo>() { // from class: com.wits.ksw.settings.audi.widget.VerticalViewPager.1
        @Override // java.util.Comparator
        public int compare(ItemInfo lhs, ItemInfo rhs) {
            return lhs.position - rhs.position;
        }
    };
    private static final Interpolator sInterpolator = new Interpolator() { // from class: com.wits.ksw.settings.audi.widget.VerticalViewPager.2
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float t) {
            float t2 = t - 1.0f;
            return (t2 * t2 * t2 * t2 * t2) + 1.0f;
        }
    };
    private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();

    /* loaded from: classes17.dex */
    interface Decor {
    }

    /* loaded from: classes17.dex */
    interface OnAdapterChangeListener {
        void onAdapterChanged(PagerAdapter oldAdapter, PagerAdapter newAdapter);
    }

    /* loaded from: classes17.dex */
    static class ItemInfo {
        float heightFactor;
        Object object;
        float offset;
        int position;
        boolean scrolling;

        ItemInfo() {
        }
    }

    public VerticalViewPager(Context context) {
        super(context);
        this.mItems = new ArrayList<>();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new Runnable() { // from class: com.wits.ksw.settings.audi.widget.VerticalViewPager.3
            @Override // java.lang.Runnable
            public void run() {
                VerticalViewPager.this.setScrollState(0);
                VerticalViewPager.this.populate();
            }
        };
        this.mScrollState = 0;
        initViewPager();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mItems = new ArrayList<>();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new Runnable() { // from class: com.wits.ksw.settings.audi.widget.VerticalViewPager.3
            @Override // java.lang.Runnable
            public void run() {
                VerticalViewPager.this.setScrollState(0);
                VerticalViewPager.this.populate();
            }
        };
        this.mScrollState = 0;
        initViewPager();
    }

    void initViewPager() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        float density = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        this.mMinimumVelocity = (int) (400.0f * density);
        this.mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
        this.mTopEdge = new EdgeEffectCompat(context);
        this.mBottomEdge = new EdgeEffectCompat(context);
        this.mFlingDistance = (int) (25.0f * density);
        this.mCloseEnough = (int) (2.0f * density);
        this.mDefaultGutterSize = (int) (16.0f * density);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScrollState(int newState) {
        if (this.mScrollState == newState) {
            return;
        }
        this.mScrollState = newState;
        if (this.mPageTransformer != null) {
            enableLayers(newState != 0);
        }
        ViewPager.OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(newState);
        }
    }

    public void setAdapter(PagerAdapter adapter) {
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            pagerAdapter.unregisterDataSetObserver(this.mObserver);
            this.mAdapter.startUpdate((ViewGroup) this);
            for (int i = 0; i < this.mItems.size(); i++) {
                ItemInfo ii = this.mItems.get(i);
                this.mAdapter.destroyItem((ViewGroup) this, ii.position, ii.object);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            removeNonDecorViews();
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        PagerAdapter oldAdapter = this.mAdapter;
        this.mAdapter = adapter;
        this.mExpectedAdapterCount = 0;
        if (adapter != null) {
            if (this.mObserver == null) {
                this.mObserver = new PagerObserver();
            }
            this.mAdapter.registerDataSetObserver(this.mObserver);
            this.mPopulatePending = false;
            boolean wasFirstLayout = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (!wasFirstLayout) {
                populate();
            } else {
                requestLayout();
            }
        }
        OnAdapterChangeListener onAdapterChangeListener = this.mAdapterChangeListener;
        if (onAdapterChangeListener != null && oldAdapter != adapter) {
            onAdapterChangeListener.onAdapterChanged(oldAdapter, adapter);
        }
    }

    private void removeNonDecorViews() {
        int i = 0;
        while (i < getChildCount()) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (!lp.isDecor) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }

    void setOnAdapterChangeListener(OnAdapterChangeListener listener) {
        this.mAdapterChangeListener = listener;
    }

    private int getClientHeight() {
        return (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public void setCurrentItem(int item) {
        this.mPopulatePending = false;
        setCurrentItemInternal(item, !this.mFirstLayout, false);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        this.mPopulatePending = false;
        setCurrentItemInternal(item, smoothScroll, false);
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    void setCurrentItemInternal(int item, boolean smoothScroll, boolean always) {
        setCurrentItemInternal(item, smoothScroll, always, 0);
    }

    void setCurrentItemInternal(int item, boolean smoothScroll, boolean always, int velocity) {
        ViewPager.OnPageChangeListener onPageChangeListener;
        ViewPager.OnPageChangeListener onPageChangeListener2;
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter == null || pagerAdapter.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (!always && this.mCurItem == item && this.mItems.size() != 0) {
            setScrollingCacheEnabled(false);
        } else {
            if (item < 0) {
                item = 0;
            } else if (item >= this.mAdapter.getCount()) {
                item = this.mAdapter.getCount() - 1;
            }
            int pageLimit = this.mOffscreenPageLimit;
            int i = this.mCurItem;
            if (item > i + pageLimit || item < i - pageLimit) {
                for (int i2 = 0; i2 < this.mItems.size(); i2++) {
                    this.mItems.get(i2).scrolling = true;
                }
            }
            int i3 = this.mCurItem;
            boolean dispatchSelected = i3 != item;
            if (this.mFirstLayout) {
                this.mCurItem = item;
                if (dispatchSelected && (onPageChangeListener2 = this.mOnPageChangeListener) != null) {
                    onPageChangeListener2.onPageSelected(item);
                }
                if (dispatchSelected && (onPageChangeListener = this.mInternalPageChangeListener) != null) {
                    onPageChangeListener.onPageSelected(item);
                }
                requestLayout();
                return;
            }
            populate(item);
            scrollToItem(item, smoothScroll, velocity, dispatchSelected);
        }
    }

    private void scrollToItem(int item, boolean smoothScroll, int velocity, boolean dispatchSelected) {
        ViewPager.OnPageChangeListener onPageChangeListener;
        ViewPager.OnPageChangeListener onPageChangeListener2;
        ViewPager.OnPageChangeListener onPageChangeListener3;
        ViewPager.OnPageChangeListener onPageChangeListener4;
        ItemInfo curInfo = infoForPosition(item);
        int destY = 0;
        if (curInfo != null) {
            int height = getClientHeight();
            destY = (int) (height * Math.max(this.mFirstOffset, Math.min(curInfo.offset, this.mLastOffset)));
        }
        if (smoothScroll) {
            smoothScrollTo(0, destY, velocity);
            if (dispatchSelected && (onPageChangeListener4 = this.mOnPageChangeListener) != null) {
                onPageChangeListener4.onPageSelected(item);
            }
            if (dispatchSelected && (onPageChangeListener3 = this.mInternalPageChangeListener) != null) {
                onPageChangeListener3.onPageSelected(item);
                return;
            }
            return;
        }
        if (dispatchSelected && (onPageChangeListener2 = this.mOnPageChangeListener) != null) {
            onPageChangeListener2.onPageSelected(item);
        }
        if (dispatchSelected && (onPageChangeListener = this.mInternalPageChangeListener) != null) {
            onPageChangeListener.onPageSelected(item);
        }
        completeScroll(false);
        scrollTo(0, destY);
        pageScrolled(destY);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        this.mOnPageChangeListener = listener;
    }

    public void setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer) {
        if (Build.VERSION.SDK_INT >= 11) {
            boolean hasTransformer = transformer != null;
            boolean needsPopulate = hasTransformer != (this.mPageTransformer != null);
            this.mPageTransformer = transformer;
            setChildrenDrawingOrderEnabledCompat(hasTransformer);
            if (hasTransformer) {
                this.mDrawingOrder = reverseDrawingOrder ? 2 : 1;
            } else {
                this.mDrawingOrder = 0;
            }
            if (needsPopulate) {
                populate();
            }
        }
    }

    void setChildrenDrawingOrderEnabledCompat(boolean enable) {
        if (Build.VERSION.SDK_INT >= 7) {
            if (this.mSetChildrenDrawingOrderEnabled == null) {
                try {
                    this.mSetChildrenDrawingOrderEnabled = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
                } catch (NoSuchMethodException e) {
                    Log.e(TAG, "Can't find setChildrenDrawingOrderEnabled", e);
                }
            }
            try {
                this.mSetChildrenDrawingOrderEnabled.invoke(this, Boolean.valueOf(enable));
            } catch (Exception e2) {
                Log.e(TAG, "Error changing children drawing order", e2);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int childCount, int i) {
        int index = this.mDrawingOrder == 2 ? (childCount - 1) - i : i;
        int result = ((LayoutParams) this.mDrawingOrderedChildren.get(index).getLayoutParams()).childIndex;
        return result;
    }

    ViewPager.OnPageChangeListener setInternalPageChangeListener(ViewPager.OnPageChangeListener listener) {
        ViewPager.OnPageChangeListener oldListener = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = listener;
        return oldListener;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public void setOffscreenPageLimit(int limit) {
        if (limit < 1) {
            Log.w(TAG, "Requested offscreen page limit " + limit + " too small; defaulting to 1");
            limit = 1;
        }
        if (limit != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = limit;
            populate();
        }
    }

    public void setPageMargin(int marginPixels) {
        int oldMargin = this.mPageMargin;
        this.mPageMargin = marginPixels;
        int height = getHeight();
        recomputeScrollPosition(height, height, marginPixels, oldMargin);
        requestLayout();
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    public void setPageMarginDrawable(Drawable d) {
        this.mMarginDrawable = d;
        if (d != null) {
            refreshDrawableState();
        }
        setWillNotDraw(d == null);
        invalidate();
    }

    public void setPageMarginDrawable(int resId) {
        setPageMarginDrawable(getContext().getResources().getDrawable(resId));
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.mMarginDrawable;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable d = this.mMarginDrawable;
        if (d != null && d.isStateful()) {
            d.setState(getDrawableState());
        }
    }

    float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((float) ((f - 0.5f) * 0.4712389167638204d));
    }

    void smoothScrollTo(int x, int y) {
        smoothScrollTo(x, y, 0);
    }

    void smoothScrollTo(int x, int y, int velocity) {
        int duration;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int sx = getScrollX();
        int sy = getScrollY();
        int dx = x - sx;
        int dy = y - sy;
        if (dx != 0 || dy != 0) {
            setScrollingCacheEnabled(true);
            setScrollState(2);
            int height = getClientHeight();
            int halfHeight = height / 2;
            float distanceRatio = Math.min(1.0f, (Math.abs(dx) * 1.0f) / height);
            float distance = halfHeight + (halfHeight * distanceInfluenceForSnapDuration(distanceRatio));
            int velocity2 = Math.abs(velocity);
            if (velocity2 <= 0) {
                float pageHeight = height * this.mAdapter.getPageWidth(this.mCurItem);
                float pageDelta = Math.abs(dx) / (this.mPageMargin + pageHeight);
                duration = (int) ((1.0f + pageDelta) * 100.0f);
            } else {
                duration = Math.round(Math.abs(distance / velocity2) * 1000.0f) * 4;
            }
            this.mScroller.startScroll(sx, sy, dx, dy, Math.min(duration, (int) MAX_SETTLE_DURATION));
            ViewCompat.postInvalidateOnAnimation(this);
            return;
        }
        completeScroll(false);
        populate();
        setScrollState(0);
    }

    ItemInfo addNewItem(int position, int index) {
        ItemInfo ii = new ItemInfo();
        ii.position = position;
        ii.object = this.mAdapter.instantiateItem((ViewGroup) this, position);
        ii.heightFactor = this.mAdapter.getPageWidth(position);
        if (index < 0 || index >= this.mItems.size()) {
            this.mItems.add(ii);
        } else {
            this.mItems.add(index, ii);
        }
        return ii;
    }

    void dataSetChanged() {
        int adapterCount = this.mAdapter.getCount();
        this.mExpectedAdapterCount = adapterCount;
        boolean needPopulate = this.mItems.size() < (this.mOffscreenPageLimit * 2) + 1 && this.mItems.size() < adapterCount;
        int newCurrItem = this.mCurItem;
        boolean isUpdating = false;
        int i = 0;
        while (i < this.mItems.size()) {
            ItemInfo ii = this.mItems.get(i);
            int newPos = this.mAdapter.getItemPosition(ii.object);
            if (newPos != -1) {
                if (newPos == -2) {
                    this.mItems.remove(i);
                    i--;
                    if (!isUpdating) {
                        this.mAdapter.startUpdate((ViewGroup) this);
                        isUpdating = true;
                    }
                    this.mAdapter.destroyItem((ViewGroup) this, ii.position, ii.object);
                    needPopulate = true;
                    if (this.mCurItem == ii.position) {
                        newCurrItem = Math.max(0, Math.min(this.mCurItem, adapterCount - 1));
                        needPopulate = true;
                    }
                } else if (ii.position != newPos) {
                    if (ii.position == this.mCurItem) {
                        newCurrItem = newPos;
                    }
                    ii.position = newPos;
                    needPopulate = true;
                }
            }
            i++;
        }
        if (isUpdating) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (needPopulate) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View child = getChildAt(i2);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (!lp.isDecor) {
                    lp.heightFactor = 0.0f;
                }
            }
            setCurrentItemInternal(newCurrItem, false, true);
            requestLayout();
        }
    }

    void populate() {
        populate(this.mCurItem);
    }

    void populate(int newCurrentItem) {
        int focusDirection;
        ItemInfo oldCurInfo;
        String resName;
        ItemInfo ii;
        int curIndex;
        float paddingLeft;
        int pageLimit;
        int startPos;
        float topHeightNeeded;
        int i = this.mCurItem;
        if (i == newCurrentItem) {
            focusDirection = 2;
            oldCurInfo = null;
        } else {
            int focusDirection2 = i < newCurrentItem ? 130 : 33;
            ItemInfo oldCurInfo2 = infoForPosition(i);
            this.mCurItem = newCurrentItem;
            focusDirection = focusDirection2;
            oldCurInfo = oldCurInfo2;
        }
        if (this.mAdapter == null) {
            sortChildDrawingOrder();
        } else if (this.mPopulatePending) {
            sortChildDrawingOrder();
        } else if (getWindowToken() != null) {
            this.mAdapter.startUpdate((ViewGroup) this);
            int pageLimit2 = this.mOffscreenPageLimit;
            int startPos2 = Math.max(0, this.mCurItem - pageLimit2);
            int N = this.mAdapter.getCount();
            int endPos = Math.min(N - 1, this.mCurItem + pageLimit2);
            if (N != this.mExpectedAdapterCount) {
                try {
                    resName = getResources().getResourceName(getId());
                } catch (Resources.NotFoundException e) {
                    resName = Integer.toHexString(getId());
                }
                throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.mExpectedAdapterCount + ", found: " + N + " Pager id: " + resName + " Pager class: " + getClass() + " Problematic adapter: " + this.mAdapter.getClass());
            }
            ItemInfo curItem = null;
            int curIndex2 = 0;
            while (true) {
                if (curIndex2 >= this.mItems.size()) {
                    break;
                }
                ItemInfo ii2 = this.mItems.get(curIndex2);
                if (ii2.position >= this.mCurItem) {
                    if (ii2.position == this.mCurItem) {
                        curItem = ii2;
                    }
                } else {
                    curIndex2++;
                }
            }
            if (curItem == null && N > 0) {
                curItem = addNewItem(this.mCurItem, curIndex2);
            }
            if (curItem != null) {
                float extraHeightTop = 0.0f;
                int itemIndex = curIndex2 - 1;
                ItemInfo ii3 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                int clientHeight = getClientHeight();
                if (clientHeight <= 0) {
                    curIndex = curIndex2;
                    paddingLeft = 0.0f;
                } else {
                    curIndex = curIndex2;
                    paddingLeft = (2.0f - curItem.heightFactor) + (getPaddingLeft() / clientHeight);
                }
                float topHeightNeeded2 = paddingLeft;
                int pos = this.mCurItem - 1;
                int curIndex3 = curIndex;
                while (pos >= 0) {
                    if (extraHeightTop < topHeightNeeded2 || pos >= startPos2) {
                        topHeightNeeded = topHeightNeeded2;
                        if (ii3 != null && pos == ii3.position) {
                            extraHeightTop += ii3.heightFactor;
                            itemIndex--;
                            ii3 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                        } else {
                            extraHeightTop += addNewItem(pos, itemIndex + 1).heightFactor;
                            curIndex3++;
                            ii3 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                        }
                    } else if (ii3 == null) {
                        break;
                    } else {
                        topHeightNeeded = topHeightNeeded2;
                        if (pos == ii3.position && !ii3.scrolling) {
                            this.mItems.remove(itemIndex);
                            this.mAdapter.destroyItem((ViewGroup) this, pos, ii3.object);
                            itemIndex--;
                            curIndex3--;
                            ii3 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                        }
                    }
                    pos--;
                    topHeightNeeded2 = topHeightNeeded;
                }
                float extraHeightBottom = curItem.heightFactor;
                int itemIndex2 = curIndex3 + 1;
                if (extraHeightBottom < 2.0f) {
                    ItemInfo ii4 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                    float bottomHeightNeeded = clientHeight <= 0 ? 0.0f : (getPaddingRight() / clientHeight) + 2.0f;
                    int pos2 = this.mCurItem + 1;
                    while (pos2 < N) {
                        if (extraHeightBottom < bottomHeightNeeded || pos2 <= endPos) {
                            pageLimit = pageLimit2;
                            startPos = startPos2;
                            if (ii4 != null && pos2 == ii4.position) {
                                extraHeightBottom += ii4.heightFactor;
                                itemIndex2++;
                                ii4 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                            } else {
                                ItemInfo ii5 = addNewItem(pos2, itemIndex2);
                                itemIndex2++;
                                extraHeightBottom += ii5.heightFactor;
                                ii4 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                            }
                        } else if (ii4 == null) {
                            break;
                        } else {
                            pageLimit = pageLimit2;
                            if (pos2 != ii4.position || ii4.scrolling) {
                                startPos = startPos2;
                            } else {
                                this.mItems.remove(itemIndex2);
                                startPos = startPos2;
                                this.mAdapter.destroyItem((ViewGroup) this, pos2, ii4.object);
                                ii4 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                            }
                        }
                        pos2++;
                        pageLimit2 = pageLimit;
                        startPos2 = startPos;
                    }
                }
                calculatePageOffsets(curItem, curIndex3, oldCurInfo);
            }
            this.mAdapter.setPrimaryItem((ViewGroup) this, this.mCurItem, curItem != null ? curItem.object : null);
            this.mAdapter.finishUpdate((ViewGroup) this);
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View child = getChildAt(i2);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                lp.childIndex = i2;
                if (!lp.isDecor && lp.heightFactor == 0.0f && (ii = infoForChild(child)) != null) {
                    lp.heightFactor = ii.heightFactor;
                    lp.position = ii.position;
                }
            }
            sortChildDrawingOrder();
            if (hasFocus()) {
                View currentFocused = findFocus();
                ItemInfo ii6 = currentFocused != null ? infoForAnyChild(currentFocused) : null;
                if (ii6 == null || ii6.position != this.mCurItem) {
                    for (int i3 = 0; i3 < getChildCount(); i3++) {
                        View child2 = getChildAt(i3);
                        ItemInfo ii7 = infoForChild(child2);
                        if (ii7 != null && ii7.position == this.mCurItem && child2.requestFocus(focusDirection)) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private void sortChildDrawingOrder() {
        if (this.mDrawingOrder != 0) {
            ArrayList<View> arrayList = this.mDrawingOrderedChildren;
            if (arrayList == null) {
                this.mDrawingOrderedChildren = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                this.mDrawingOrderedChildren.add(child);
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    private void calculatePageOffsets(ItemInfo curItem, int curIndex, ItemInfo oldCurInfo) {
        ItemInfo ii;
        ItemInfo ii2;
        int N = this.mAdapter.getCount();
        int height = getClientHeight();
        float marginOffset = height > 0 ? this.mPageMargin / height : 0.0f;
        if (oldCurInfo != null) {
            int oldCurPosition = oldCurInfo.position;
            if (oldCurPosition < curItem.position) {
                int itemIndex = 0;
                float offset = oldCurInfo.offset + oldCurInfo.heightFactor + marginOffset;
                int pos = oldCurPosition + 1;
                while (pos <= curItem.position && itemIndex < this.mItems.size()) {
                    ItemInfo ii3 = this.mItems.get(itemIndex);
                    while (true) {
                        ii2 = ii3;
                        if (pos <= ii2.position || itemIndex >= this.mItems.size() - 1) {
                            break;
                        }
                        itemIndex++;
                        ii3 = this.mItems.get(itemIndex);
                    }
                    while (pos < ii2.position) {
                        offset += this.mAdapter.getPageWidth(pos) + marginOffset;
                        pos++;
                    }
                    ii2.offset = offset;
                    offset += ii2.heightFactor + marginOffset;
                    pos++;
                }
            } else {
                int itemIndex2 = curItem.position;
                if (oldCurPosition > itemIndex2) {
                    int itemIndex3 = this.mItems.size() - 1;
                    float offset2 = oldCurInfo.offset;
                    int pos2 = oldCurPosition - 1;
                    while (pos2 >= curItem.position && itemIndex3 >= 0) {
                        ItemInfo ii4 = this.mItems.get(itemIndex3);
                        while (true) {
                            ii = ii4;
                            if (pos2 >= ii.position || itemIndex3 <= 0) {
                                break;
                            }
                            itemIndex3--;
                            ii4 = this.mItems.get(itemIndex3);
                        }
                        while (pos2 > ii.position) {
                            offset2 -= this.mAdapter.getPageWidth(pos2) + marginOffset;
                            pos2--;
                        }
                        offset2 -= ii.heightFactor + marginOffset;
                        ii.offset = offset2;
                        pos2--;
                    }
                }
            }
        }
        int itemCount = this.mItems.size();
        float offset3 = curItem.offset;
        int pos3 = curItem.position - 1;
        this.mFirstOffset = curItem.position == 0 ? curItem.offset : -3.4028235E38f;
        this.mLastOffset = curItem.position == N + (-1) ? (curItem.offset + curItem.heightFactor) - 1.0f : Float.MAX_VALUE;
        int i = curIndex - 1;
        while (i >= 0) {
            ItemInfo ii5 = this.mItems.get(i);
            while (pos3 > ii5.position) {
                offset3 -= this.mAdapter.getPageWidth(pos3) + marginOffset;
                pos3--;
            }
            offset3 -= ii5.heightFactor + marginOffset;
            ii5.offset = offset3;
            if (ii5.position == 0) {
                this.mFirstOffset = offset3;
            }
            i--;
            pos3--;
        }
        float offset4 = curItem.offset + curItem.heightFactor + marginOffset;
        int pos4 = curItem.position + 1;
        int i2 = curIndex + 1;
        while (i2 < itemCount) {
            ItemInfo ii6 = this.mItems.get(i2);
            while (pos4 < ii6.position) {
                offset4 += this.mAdapter.getPageWidth(pos4) + marginOffset;
                pos4++;
            }
            if (ii6.position == N - 1) {
                this.mLastOffset = (ii6.heightFactor + offset4) - 1.0f;
            }
            ii6.offset = offset4;
            offset4 += ii6.heightFactor + marginOffset;
            i2++;
            pos4++;
        }
        this.mNeedCalculatePageOffsets = false;
    }

    /* loaded from: classes17.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<SavedState>() { // from class: com.wits.ksw.settings.audi.widget.VerticalViewPager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.support.p001v4.p003os.ParcelableCompatCreatorCallbacks
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.support.p001v4.p003os.ParcelableCompatCreatorCallbacks
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        });
        Parcelable adapterState;
        ClassLoader loader;
        int position;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.position);
            out.writeParcelable(this.adapterState, flags);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.position + "}";
        }

        SavedState(Parcel in, ClassLoader loader) {
            super(in);
            loader = loader == null ? getClass().getClassLoader() : loader;
            this.position = in.readInt();
            this.adapterState = in.readParcelable(loader);
            this.loader = loader;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.position = this.mCurItem;
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            ss.adapterState = pagerAdapter.saveState();
        }
        return ss;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            pagerAdapter.restoreState(ss.adapterState, ss.loader);
            setCurrentItemInternal(ss.position, false, true);
            return;
        }
        this.mRestoredCurItem = ss.position;
        this.mRestoredAdapterState = ss.adapterState;
        this.mRestoredClassLoader = ss.loader;
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (!checkLayoutParams(params)) {
            params = generateLayoutParams(params);
        }
        LayoutParams lp = (LayoutParams) params;
        lp.isDecor |= child instanceof Decor;
        if (this.mInLayout) {
            if (lp != null && lp.isDecor) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            lp.needsMeasure = true;
            addViewInLayout(child, index, params);
            return;
        }
        super.addView(child, index, params);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    ItemInfo infoForChild(View child) {
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo ii = this.mItems.get(i);
            if (this.mAdapter.isViewFromObject(child, ii.object)) {
                return ii;
            }
        }
        return null;
    }

    ItemInfo infoForAnyChild(View child) {
        while (true) {
            ViewParent parent = child.getParent();
            if (parent != this) {
                if (parent == null || !(parent instanceof View)) {
                    return null;
                }
                child = (View) parent;
            } else {
                return infoForChild(child);
            }
        }
    }

    ItemInfo infoForPosition(int position) {
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo ii = this.mItems.get(i);
            if (ii.position == position) {
                return ii;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LayoutParams lp;
        int measuredHeight;
        int heightMode;
        int widthSize;
        int heightMode2;
        int heightSize;
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int measuredHeight2 = getMeasuredHeight();
        int maxGutterSize = measuredHeight2 / 10;
        this.mGutterSize = Math.min(maxGutterSize, this.mDefaultGutterSize);
        int childWidthSize = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int childHeightSize = (measuredHeight2 - getPaddingTop()) - getPaddingBottom();
        int size = getChildCount();
        int i = 0;
        while (i < size) {
            View child = getChildAt(i);
            if (child.getVisibility() == 8) {
                measuredHeight = measuredHeight2;
                heightMode = maxGutterSize;
            } else {
                LayoutParams lp2 = (LayoutParams) child.getLayoutParams();
                if (lp2 == null || !lp2.isDecor) {
                    measuredHeight = measuredHeight2;
                    heightMode = maxGutterSize;
                } else {
                    int hgrav = lp2.gravity & 7;
                    int vgrav = lp2.gravity & 112;
                    int widthMode = Integer.MIN_VALUE;
                    int heightMode3 = Integer.MIN_VALUE;
                    boolean consumeVertical = vgrav == 48 || vgrav == 80;
                    boolean consumeHorizontal = hgrav == 3 || hgrav == 5;
                    if (consumeVertical) {
                        widthMode = BasicMeasure.EXACTLY;
                    } else if (consumeHorizontal) {
                        heightMode3 = BasicMeasure.EXACTLY;
                    }
                    int widthSize2 = childWidthSize;
                    int heightSize2 = childHeightSize;
                    measuredHeight = measuredHeight2;
                    if (lp2.width == -2) {
                        widthSize = widthSize2;
                    } else {
                        widthMode = BasicMeasure.EXACTLY;
                        if (lp2.width == -1) {
                            widthSize = widthSize2;
                        } else {
                            widthSize = lp2.width;
                        }
                    }
                    if (lp2.height == -2) {
                        heightMode2 = heightMode3;
                        heightSize = heightSize2;
                    } else if (lp2.height == -1) {
                        heightMode2 = 1073741824;
                        heightSize = heightSize2;
                    } else {
                        heightSize = lp2.height;
                        heightMode2 = 1073741824;
                    }
                    heightMode = maxGutterSize;
                    int widthSpec = View.MeasureSpec.makeMeasureSpec(widthSize, widthMode);
                    int heightSpec = View.MeasureSpec.makeMeasureSpec(heightSize, heightMode2);
                    child.measure(widthSpec, heightSpec);
                    if (consumeVertical) {
                        childHeightSize -= child.getMeasuredHeight();
                    } else if (consumeHorizontal) {
                        childWidthSize -= child.getMeasuredWidth();
                    }
                }
            }
            i++;
            maxGutterSize = heightMode;
            measuredHeight2 = measuredHeight;
        }
        this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(childWidthSize, BasicMeasure.EXACTLY);
        this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(childHeightSize, BasicMeasure.EXACTLY);
        this.mInLayout = true;
        populate();
        this.mInLayout = false;
        int size2 = getChildCount();
        for (int i2 = 0; i2 < size2; i2++) {
            View child2 = getChildAt(i2);
            if (child2.getVisibility() != 8 && ((lp = (LayoutParams) child2.getLayoutParams()) == null || !lp.isDecor)) {
                int heightSpec2 = View.MeasureSpec.makeMeasureSpec((int) (childHeightSize * lp.heightFactor), BasicMeasure.EXACTLY);
                child2.measure(this.mChildWidthMeasureSpec, heightSpec2);
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (h != oldh) {
            int i = this.mPageMargin;
            recomputeScrollPosition(h, oldh, i, i);
        }
    }

    private void recomputeScrollPosition(int height, int oldHeight, int margin, int oldMargin) {
        if (oldHeight > 0 && !this.mItems.isEmpty()) {
            int heightWithMargin = ((height - getPaddingTop()) - getPaddingBottom()) + margin;
            int oldHeightWithMargin = ((oldHeight - getPaddingTop()) - getPaddingBottom()) + oldMargin;
            int ypos = getScrollY();
            float pageOffset = ypos / oldHeightWithMargin;
            int newOffsetPixels = (int) (heightWithMargin * pageOffset);
            scrollTo(getScrollX(), newOffsetPixels);
            if (!this.mScroller.isFinished()) {
                int newDuration = this.mScroller.getDuration() - this.mScroller.timePassed();
                ItemInfo targetInfo = infoForPosition(this.mCurItem);
                this.mScroller.startScroll(0, newOffsetPixels, 0, (int) (targetInfo.offset * height), newDuration);
                return;
            }
            return;
        }
        ItemInfo ii = infoForPosition(this.mCurItem);
        float scrollOffset = ii != null ? Math.min(ii.offset, this.mLastOffset) : 0.0f;
        int scrollPos = (int) (((height - getPaddingTop()) - getPaddingBottom()) * scrollOffset);
        if (scrollPos != getScrollY()) {
            completeScroll(false);
            scrollTo(getScrollX(), scrollPos);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        boolean z;
        int count;
        int height;
        int paddingTop;
        int paddingBottom;
        int childLeft;
        int paddingLeft;
        int childTop;
        int count2 = getChildCount();
        int width = r - l;
        int height2 = b - t;
        int childTop2 = getPaddingLeft();
        int paddingTop2 = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom2 = getPaddingBottom();
        int scrollY = getScrollY();
        int decorCount = 0;
        int i = 0;
        while (true) {
            int i2 = 8;
            if (i < count2) {
                View child = getChildAt(i);
                if (child.getVisibility() != 8) {
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    if (lp.isDecor) {
                        int hgrav = lp.gravity & 7;
                        int vgrav = lp.gravity & 112;
                        switch (hgrav) {
                            case 1:
                                childLeft = Math.max((width - child.getMeasuredWidth()) / 2, childTop2);
                                break;
                            case 2:
                            case 4:
                            default:
                                childLeft = childTop2;
                                break;
                            case 3:
                                int childLeft2 = childTop2;
                                childTop2 += child.getMeasuredWidth();
                                childLeft = childLeft2;
                                break;
                            case 5:
                                paddingRight += child.getMeasuredWidth();
                                childLeft = (width - paddingRight) - child.getMeasuredWidth();
                                break;
                        }
                        switch (vgrav) {
                            case 16:
                                int childTop3 = child.getMeasuredHeight();
                                paddingLeft = childTop2;
                                int paddingLeft2 = (height2 - childTop3) / 2;
                                childTop = Math.max(paddingLeft2, paddingTop2);
                                break;
                            case 48:
                                childTop = paddingTop2;
                                int childTop4 = child.getMeasuredHeight();
                                paddingTop2 += childTop4;
                                paddingLeft = childTop2;
                                break;
                            case 80:
                                int childTop5 = height2 - paddingBottom2;
                                childTop = childTop5 - child.getMeasuredHeight();
                                int childTop6 = child.getMeasuredHeight();
                                paddingBottom2 += childTop6;
                                paddingLeft = childTop2;
                                break;
                            default:
                                paddingLeft = childTop2;
                                childTop = paddingTop2;
                                break;
                        }
                        int childTop7 = childTop + scrollY;
                        int paddingTop3 = paddingTop2;
                        int paddingTop4 = childTop7 + child.getMeasuredHeight();
                        child.layout(childLeft, childTop7, child.getMeasuredWidth() + childLeft, paddingTop4);
                        decorCount++;
                        childTop2 = paddingLeft;
                        paddingTop2 = paddingTop3;
                    }
                }
                i++;
            } else {
                int i3 = height2 - paddingTop2;
                int childHeight = i3 - paddingBottom2;
                int i4 = 0;
                while (i4 < count2) {
                    View child2 = getChildAt(i4);
                    if (child2.getVisibility() == i2) {
                        count = count2;
                        height = height2;
                        paddingTop = paddingTop2;
                        paddingBottom = paddingBottom2;
                    } else {
                        LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                        if (lp2.isDecor) {
                            count = count2;
                            height = height2;
                            paddingTop = paddingTop2;
                            paddingBottom = paddingBottom2;
                        } else {
                            ItemInfo ii = infoForChild(child2);
                            if (ii == null) {
                                height = height2;
                                paddingTop = paddingTop2;
                                paddingBottom = paddingBottom2;
                                count = count2;
                            } else {
                                count = count2;
                                int toff = (int) (childHeight * ii.offset);
                                int childLeft3 = childTop2;
                                height = height2;
                                int height3 = paddingTop2 + toff;
                                if (!lp2.needsMeasure) {
                                    paddingTop = paddingTop2;
                                    paddingBottom = paddingBottom2;
                                } else {
                                    lp2.needsMeasure = false;
                                    paddingTop = paddingTop2;
                                    int widthSpec = View.MeasureSpec.makeMeasureSpec((width - childTop2) - paddingRight, BasicMeasure.EXACTLY);
                                    paddingBottom = paddingBottom2;
                                    int heightSpec = View.MeasureSpec.makeMeasureSpec((int) (childHeight * lp2.heightFactor), BasicMeasure.EXACTLY);
                                    child2.measure(widthSpec, heightSpec);
                                }
                                child2.layout(childLeft3, height3, child2.getMeasuredWidth() + childLeft3, child2.getMeasuredHeight() + height3);
                            }
                        }
                    }
                    i4++;
                    count2 = count;
                    height2 = height;
                    paddingTop2 = paddingTop;
                    paddingBottom2 = paddingBottom;
                    i2 = 8;
                }
                this.mLeftPageBounds = childTop2;
                this.mRightPageBounds = width - paddingRight;
                this.mDecorChildCount = decorCount;
                if (this.mFirstLayout) {
                    z = false;
                    scrollToItem(this.mCurItem, false, 0, false);
                } else {
                    z = false;
                }
                this.mFirstLayout = z;
                return;
            }
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
            int oldX = getScrollX();
            int oldY = getScrollY();
            int x = this.mScroller.getCurrX();
            int y = this.mScroller.getCurrY();
            if (oldX != x || oldY != y) {
                scrollTo(x, y);
                if (!pageScrolled(y)) {
                    this.mScroller.abortAnimation();
                    scrollTo(x, 0);
                }
            }
            ViewCompat.postInvalidateOnAnimation(this);
            return;
        }
        completeScroll(true);
    }

    private boolean pageScrolled(int ypos) {
        if (this.mItems.size() == 0) {
            this.mCalledSuper = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        ItemInfo ii = infoForCurrentScrollPosition();
        int height = getClientHeight();
        int i = this.mPageMargin;
        int heightWithMargin = height + i;
        float marginOffset = i / height;
        int currentPage = ii.position;
        float pageOffset = ((ypos / height) - ii.offset) / (ii.heightFactor + marginOffset);
        int offsetPixels = (int) (heightWithMargin * pageOffset);
        this.mCalledSuper = false;
        onPageScrolled(currentPage, pageOffset, offsetPixels);
        if (!this.mCalledSuper) {
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        return true;
    }

    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        int childTop;
        if (this.mDecorChildCount > 0) {
            int scrollY = getScrollY();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int height = getHeight();
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.isDecor) {
                    int vgrav = lp.gravity & 112;
                    switch (vgrav) {
                        case 16:
                            int childTop2 = child.getMeasuredHeight();
                            childTop = Math.max((height - childTop2) / 2, paddingTop);
                            break;
                        case 48:
                            childTop = paddingTop;
                            int childTop3 = child.getHeight();
                            paddingTop += childTop3;
                            break;
                        case 80:
                            int childTop4 = height - paddingBottom;
                            childTop = childTop4 - child.getMeasuredHeight();
                            int childTop5 = child.getMeasuredHeight();
                            paddingBottom += childTop5;
                            break;
                        default:
                            childTop = paddingTop;
                            break;
                    }
                    int childOffset = (childTop + scrollY) - child.getTop();
                    if (childOffset != 0) {
                        child.offsetTopAndBottom(childOffset);
                    }
                }
            }
        }
        ViewPager.OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(position, offset, offsetPixels);
        }
        ViewPager.OnPageChangeListener onPageChangeListener2 = this.mInternalPageChangeListener;
        if (onPageChangeListener2 != null) {
            onPageChangeListener2.onPageScrolled(position, offset, offsetPixels);
        }
        if (this.mPageTransformer != null) {
            int scrollY2 = getScrollY();
            int childCount2 = getChildCount();
            for (int i2 = 0; i2 < childCount2; i2++) {
                View child2 = getChildAt(i2);
                if (!((LayoutParams) child2.getLayoutParams()).isDecor) {
                    float transformPos = (child2.getTop() - scrollY2) / getClientHeight();
                    this.mPageTransformer.transformPage(child2, transformPos);
                }
            }
        }
        this.mCalledSuper = true;
    }

    private void completeScroll(boolean postEvents) {
        boolean needPopulate = this.mScrollState == 2;
        if (needPopulate) {
            setScrollingCacheEnabled(false);
            this.mScroller.abortAnimation();
            int oldX = getScrollX();
            int oldY = getScrollY();
            int x = this.mScroller.getCurrX();
            int y = this.mScroller.getCurrY();
            if (oldX != x || oldY != y) {
                scrollTo(x, y);
            }
        }
        this.mPopulatePending = false;
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo ii = this.mItems.get(i);
            if (ii.scrolling) {
                needPopulate = true;
                ii.scrolling = false;
            }
        }
        if (needPopulate) {
            if (postEvents) {
                ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
            } else {
                this.mEndScrollRunnable.run();
            }
        }
    }

    private boolean isGutterDrag(float y, float dy) {
        return (y < ((float) this.mGutterSize) && dy > 0.0f) || (y > ((float) (getHeight() - this.mGutterSize)) && dy < 0.0f);
    }

    private void enableLayers(boolean enable) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int layerType = enable ? 2 : 0;
            ViewCompat.setLayerType(getChildAt(i), layerType, null);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x;
        int action = ev.getAction() & 255;
        if (action == 3 || action == 1) {
            this.mIsBeingDragged = false;
            this.mIsUnableToDrag = false;
            this.mActivePointerId = -1;
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.mVelocityTracker = null;
                return false;
            }
            return false;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag) {
                return false;
            }
        }
        switch (action) {
            case 0:
                float x2 = ev.getX();
                this.mInitialMotionX = x2;
                this.mLastMotionX = x2;
                float y = ev.getY();
                this.mInitialMotionY = y;
                this.mLastMotionY = y;
                this.mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                this.mIsUnableToDrag = false;
                this.mScroller.computeScrollOffset();
                if (this.mScrollState != 2 || Math.abs(this.mScroller.getFinalY() - this.mScroller.getCurrY()) <= this.mCloseEnough) {
                    completeScroll(false);
                    this.mIsBeingDragged = false;
                    break;
                } else {
                    this.mScroller.abortAnimation();
                    this.mPopulatePending = false;
                    populate();
                    this.mIsBeingDragged = true;
                    requestParentDisallowInterceptTouchEvent(true);
                    setScrollState(1);
                    break;
                }
            case 2:
                int activePointerId = this.mActivePointerId;
                if (activePointerId != -1) {
                    int pointerIndex = MotionEventCompat.findPointerIndex(ev, activePointerId);
                    float y2 = MotionEventCompat.getY(ev, pointerIndex);
                    float dy = y2 - this.mLastMotionY;
                    float yDiff = Math.abs(dy);
                    float x3 = MotionEventCompat.getX(ev, pointerIndex);
                    float xDiff = Math.abs(x3 - this.mInitialMotionX);
                    if (dy == 0.0f || isGutterDrag(this.mLastMotionY, dy)) {
                        x = x3;
                    } else {
                        x = x3;
                        if (canScroll(this, false, (int) dy, (int) x3, (int) y2)) {
                            this.mLastMotionX = x;
                            this.mLastMotionY = y2;
                            this.mIsUnableToDrag = true;
                            return false;
                        }
                    }
                    int i = this.mTouchSlop;
                    if (yDiff > i && 0.5f * yDiff > xDiff) {
                        this.mIsBeingDragged = true;
                        requestParentDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                        this.mLastMotionY = dy > 0.0f ? this.mInitialMotionY + this.mTouchSlop : this.mInitialMotionY - this.mTouchSlop;
                        this.mLastMotionX = x;
                        setScrollingCacheEnabled(true);
                    } else if (xDiff > i) {
                        this.mIsUnableToDrag = true;
                    }
                    if (this.mIsBeingDragged && performDrag(y2)) {
                        ViewCompat.postInvalidateOnAnimation(this);
                        break;
                    }
                }
                break;
            case 6:
                onSecondaryPointerUp(ev);
                break;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(ev);
        return this.mIsBeingDragged;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        PagerAdapter pagerAdapter;
        float f;
        if (this.mFakeDragging) {
            return true;
        }
        if ((ev.getAction() == 0 && ev.getEdgeFlags() != 0) || (pagerAdapter = this.mAdapter) == null || pagerAdapter.getCount() == 0) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(ev);
        int action = ev.getAction();
        boolean needsInvalidate = false;
        switch (action & 255) {
            case 0:
                this.mScroller.abortAnimation();
                this.mPopulatePending = false;
                populate();
                float x = ev.getX();
                this.mInitialMotionX = x;
                this.mLastMotionX = x;
                float y = ev.getY();
                this.mInitialMotionY = y;
                this.mLastMotionY = y;
                this.mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                break;
            case 1:
                if (!this.mIsBeingDragged) {
                    break;
                } else {
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                    int initialVelocity = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.mActivePointerId);
                    this.mPopulatePending = true;
                    int height = getClientHeight();
                    int scrollY = getScrollY();
                    ItemInfo ii = infoForCurrentScrollPosition();
                    int currentPage = ii.position;
                    float pageOffset = ((scrollY / height) - ii.offset) / ii.heightFactor;
                    int activePointerIndex = MotionEventCompat.findPointerIndex(ev, this.mActivePointerId);
                    float y2 = MotionEventCompat.getY(ev, activePointerIndex);
                    int totalDelta = (int) (y2 - this.mInitialMotionY);
                    int nextPage = determineTargetPage(currentPage, pageOffset, initialVelocity, totalDelta);
                    setCurrentItemInternal(nextPage, true, true, initialVelocity);
                    this.mActivePointerId = -1;
                    endDrag();
                    needsInvalidate = this.mTopEdge.onRelease() | this.mBottomEdge.onRelease();
                    break;
                }
            case 2:
                if (!this.mIsBeingDragged) {
                    int pointerIndex = MotionEventCompat.findPointerIndex(ev, this.mActivePointerId);
                    float y3 = MotionEventCompat.getY(ev, pointerIndex);
                    float yDiff = Math.abs(y3 - this.mLastMotionY);
                    float x2 = MotionEventCompat.getX(ev, pointerIndex);
                    float xDiff = Math.abs(x2 - this.mLastMotionX);
                    if (yDiff > this.mTouchSlop && yDiff > xDiff) {
                        this.mIsBeingDragged = true;
                        requestParentDisallowInterceptTouchEvent(true);
                        float f2 = this.mInitialMotionY;
                        if (y3 - f2 > 0.0f) {
                            f = f2 + this.mTouchSlop;
                        } else {
                            f = f2 - this.mTouchSlop;
                        }
                        this.mLastMotionY = f;
                        this.mLastMotionX = x2;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (!this.mIsBeingDragged) {
                    break;
                } else {
                    int activePointerIndex2 = MotionEventCompat.findPointerIndex(ev, this.mActivePointerId);
                    float y4 = MotionEventCompat.getY(ev, activePointerIndex2);
                    needsInvalidate = false | performDrag(y4);
                    break;
                }
                break;
            case 3:
                if (!this.mIsBeingDragged) {
                    break;
                } else {
                    scrollToItem(this.mCurItem, true, 0, false);
                    this.mActivePointerId = -1;
                    endDrag();
                    needsInvalidate = this.mTopEdge.onRelease() | this.mBottomEdge.onRelease();
                    break;
                }
            case 5:
                int index = MotionEventCompat.getActionIndex(ev);
                float y5 = MotionEventCompat.getY(ev, index);
                this.mLastMotionY = y5;
                this.mActivePointerId = MotionEventCompat.getPointerId(ev, index);
                break;
            case 6:
                onSecondaryPointerUp(ev);
                this.mLastMotionY = MotionEventCompat.getY(ev, MotionEventCompat.findPointerIndex(ev, this.mActivePointerId));
                break;
        }
        if (needsInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return true;
    }

    private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    private boolean performDrag(float y) {
        ArrayList<ItemInfo> arrayList;
        boolean needsInvalidate = false;
        float deltaY = this.mLastMotionY - y;
        this.mLastMotionY = y;
        float oldScrollY = getScrollY();
        float scrollY = oldScrollY + deltaY;
        int height = getClientHeight();
        float topBound = height * this.mFirstOffset;
        float bottomBound = height * this.mLastOffset;
        boolean topAbsolute = true;
        boolean bottomAbsolute = true;
        ItemInfo firstItem = this.mItems.get(0);
        ItemInfo lastItem = this.mItems.get(arrayList.size() - 1);
        if (firstItem.position != 0) {
            topAbsolute = false;
            topBound = firstItem.offset * height;
        }
        if (lastItem.position != this.mAdapter.getCount() - 1) {
            bottomAbsolute = false;
            bottomBound = lastItem.offset * height;
        }
        if (scrollY < topBound) {
            if (topAbsolute) {
                float over = topBound - scrollY;
                needsInvalidate = this.mTopEdge.onPull(Math.abs(over) / height);
            }
            scrollY = topBound;
        } else if (scrollY > bottomBound) {
            if (bottomAbsolute) {
                float over2 = scrollY - bottomBound;
                needsInvalidate = this.mBottomEdge.onPull(Math.abs(over2) / height);
            }
            scrollY = bottomBound;
        }
        this.mLastMotionX += scrollY - ((int) scrollY);
        scrollTo(getScrollX(), (int) scrollY);
        pageScrolled((int) scrollY);
        return needsInvalidate;
    }

    private ItemInfo infoForCurrentScrollPosition() {
        int height = getClientHeight();
        float scrollOffset = height > 0 ? getScrollY() / height : 0.0f;
        float marginOffset = height > 0 ? this.mPageMargin / height : 0.0f;
        int lastPos = -1;
        float lastOffset = 0.0f;
        float lastHeight = 0.0f;
        boolean first = true;
        ItemInfo lastItem = null;
        int i = 0;
        while (i < this.mItems.size()) {
            ItemInfo ii = this.mItems.get(i);
            if (!first && ii.position != lastPos + 1) {
                ii = this.mTempItem;
                ii.offset = lastOffset + lastHeight + marginOffset;
                ii.position = lastPos + 1;
                ii.heightFactor = this.mAdapter.getPageWidth(ii.position);
                i--;
            }
            float offset = ii.offset;
            float bottomBound = ii.heightFactor + offset + marginOffset;
            if (first || scrollOffset >= offset) {
                if (scrollOffset < bottomBound || i == this.mItems.size() - 1) {
                    return ii;
                }
                first = false;
                lastPos = ii.position;
                lastOffset = offset;
                lastHeight = ii.heightFactor;
                lastItem = ii;
                i++;
            } else {
                return lastItem;
            }
        }
        return lastItem;
    }

    private int determineTargetPage(int currentPage, float pageOffset, int velocity, int deltaY) {
        int targetPage;
        if (Math.abs(deltaY) > this.mFlingDistance && Math.abs(velocity) > this.mMinimumVelocity) {
            targetPage = velocity > 0 ? currentPage : currentPage + 1;
        } else {
            int targetPage2 = this.mCurItem;
            float truncator = currentPage >= targetPage2 ? 0.4f : 0.6f;
            targetPage = (int) (currentPage + pageOffset + truncator);
        }
        if (this.mItems.size() > 0) {
            ItemInfo firstItem = this.mItems.get(0);
            ArrayList<ItemInfo> arrayList = this.mItems;
            ItemInfo lastItem = arrayList.get(arrayList.size() - 1);
            return Math.max(firstItem.position, Math.min(targetPage, lastItem.position));
        }
        return targetPage;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        PagerAdapter pagerAdapter;
        super.draw(canvas);
        boolean needsInvalidate = false;
        int overScrollMode = ViewCompat.getOverScrollMode(this);
        if (overScrollMode == 0 || (overScrollMode == 1 && (pagerAdapter = this.mAdapter) != null && pagerAdapter.getCount() > 1)) {
            if (!this.mTopEdge.isFinished()) {
                int restoreCount = canvas.save();
                int height = getHeight();
                int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.translate(getPaddingLeft(), this.mFirstOffset * height);
                this.mTopEdge.setSize(width, height);
                needsInvalidate = false | this.mTopEdge.draw(canvas);
                canvas.restoreToCount(restoreCount);
            }
            if (!this.mBottomEdge.isFinished()) {
                int restoreCount2 = canvas.save();
                int height2 = getHeight();
                int width2 = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.rotate(180.0f);
                canvas.translate((-width2) - getPaddingLeft(), (-(this.mLastOffset + 1.0f)) * height2);
                this.mBottomEdge.setSize(width2, height2);
                needsInvalidate |= this.mBottomEdge.draw(canvas);
                canvas.restoreToCount(restoreCount2);
            }
        } else {
            this.mTopEdge.finish();
            this.mBottomEdge.finish();
        }
        if (needsInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float heightFactor;
        float marginOffset;
        super.onDraw(canvas);
        if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
            int scrollY = getScrollY();
            int height = getHeight();
            float marginOffset2 = this.mPageMargin / height;
            int itemIndex = 0;
            ItemInfo ii = this.mItems.get(0);
            float offset = ii.offset;
            int itemCount = this.mItems.size();
            int firstPos = ii.position;
            int lastPos = this.mItems.get(itemCount - 1).position;
            int pos = firstPos;
            while (pos < lastPos) {
                while (pos > ii.position && itemIndex < itemCount) {
                    itemIndex++;
                    ii = this.mItems.get(itemIndex);
                }
                if (pos == ii.position) {
                    heightFactor = (ii.offset + ii.heightFactor) * height;
                    offset = ii.offset + ii.heightFactor + marginOffset2;
                } else {
                    float heightFactor2 = this.mAdapter.getPageWidth(pos);
                    float drawAt = (offset + heightFactor2) * height;
                    offset += heightFactor2 + marginOffset2;
                    heightFactor = drawAt;
                }
                int i = this.mPageMargin;
                if (i + heightFactor > scrollY) {
                    marginOffset = marginOffset2;
                    this.mMarginDrawable.setBounds(this.mLeftPageBounds, (int) heightFactor, this.mRightPageBounds, (int) (i + heightFactor + 0.5f));
                    this.mMarginDrawable.draw(canvas);
                } else {
                    marginOffset = marginOffset2;
                }
                if (heightFactor <= scrollY + height) {
                    pos++;
                    marginOffset2 = marginOffset;
                } else {
                    return;
                }
            }
        }
    }

    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged) {
            return false;
        }
        this.mFakeDragging = true;
        setScrollState(1);
        this.mLastMotionY = 0.0f;
        this.mInitialMotionY = 0.0f;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
        long time = SystemClock.uptimeMillis();
        MotionEvent ev = MotionEvent.obtain(time, time, 0, 0.0f, 0.0f, 0);
        this.mVelocityTracker.addMovement(ev);
        ev.recycle();
        this.mFakeDragBeginTime = time;
        return true;
    }

    public void endFakeDrag() {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        VelocityTracker velocityTracker = this.mVelocityTracker;
        velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
        int initialVelocity = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.mActivePointerId);
        this.mPopulatePending = true;
        int height = getClientHeight();
        int scrollY = getScrollY();
        ItemInfo ii = infoForCurrentScrollPosition();
        int currentPage = ii.position;
        float pageOffset = ((scrollY / height) - ii.offset) / ii.heightFactor;
        int totalDelta = (int) (this.mLastMotionY - this.mInitialMotionY);
        int nextPage = determineTargetPage(currentPage, pageOffset, initialVelocity, totalDelta);
        setCurrentItemInternal(nextPage, true, true, initialVelocity);
        endDrag();
        this.mFakeDragging = false;
    }

    public void fakeDragBy(float yOffset) {
        ArrayList<ItemInfo> arrayList;
        if (this.mFakeDragging) {
            this.mLastMotionY += yOffset;
            float oldScrollY = getScrollY();
            float scrollY = oldScrollY - yOffset;
            int height = getClientHeight();
            float topBound = height * this.mFirstOffset;
            float bottomBound = height * this.mLastOffset;
            ItemInfo firstItem = this.mItems.get(0);
            ItemInfo lastItem = this.mItems.get(arrayList.size() - 1);
            if (firstItem.position != 0) {
                topBound = firstItem.offset * height;
            }
            if (lastItem.position != this.mAdapter.getCount() - 1) {
                bottomBound = lastItem.offset * height;
            }
            if (scrollY < topBound) {
                scrollY = topBound;
            } else if (scrollY > bottomBound) {
                scrollY = bottomBound;
            }
            this.mLastMotionY += scrollY - ((int) scrollY);
            scrollTo(getScrollX(), (int) scrollY);
            pageScrolled((int) scrollY);
            long time = SystemClock.uptimeMillis();
            MotionEvent ev = MotionEvent.obtain(this.mFakeDragBeginTime, time, 2, 0.0f, this.mLastMotionY, 0);
            this.mVelocityTracker.addMovement(ev);
            ev.recycle();
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        int pointerIndex = MotionEventCompat.getActionIndex(ev);
        int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
        if (pointerId == this.mActivePointerId) {
            int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            this.mLastMotionY = MotionEventCompat.getY(ev, newPointerIndex);
            this.mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void setScrollingCacheEnabled(boolean enabled) {
        if (this.mScrollingCacheEnabled != enabled) {
            this.mScrollingCacheEnabled = enabled;
        }
    }

    public boolean internalCanScrollVertically(int direction) {
        if (this.mAdapter == null) {
            return false;
        }
        int height = getClientHeight();
        int scrollY = getScrollY();
        return direction < 0 ? scrollY > ((int) (((float) height) * this.mFirstOffset)) : direction > 0 && scrollY < ((int) (((float) height) * this.mLastOffset));
    }

    protected boolean canScroll(View v, boolean checkV, int dy, int x, int y) {
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            int scrollX = v.getScrollX();
            int scrollY = v.getScrollY();
            int count = group.getChildCount();
            for (int i = count - 1; i >= 0; i--) {
                View child = group.getChildAt(i);
                if (y + scrollY >= child.getTop() && y + scrollY < child.getBottom() && x + scrollX >= child.getLeft() && x + scrollX < child.getRight() && canScroll(child, true, dy, (x + scrollX) - child.getLeft(), (y + scrollY) - child.getTop())) {
                    return true;
                }
            }
        }
        return checkV && ViewCompat.canScrollVertically(v, -dy);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event) || executeKeyEvent(event);
    }

    public boolean executeKeyEvent(KeyEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        switch (event.getKeyCode()) {
            case 21:
                boolean handled = arrowScroll(17);
                return handled;
            case 22:
                boolean handled2 = arrowScroll(66);
                return handled2;
            default:
                return false;
        }
    }

    public boolean arrowScroll(int direction) {
        View currentFocused = findFocus();
        if (currentFocused == this) {
            currentFocused = null;
        } else if (currentFocused != null) {
            boolean isChild = false;
            ViewParent parent = currentFocused.getParent();
            while (true) {
                if (parent instanceof ViewGroup) {
                    if (parent != this) {
                        parent = parent.getParent();
                    } else {
                        isChild = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!isChild) {
                StringBuilder sb = new StringBuilder();
                sb.append(currentFocused.getClass().getSimpleName());
                for (ViewParent parent2 = currentFocused.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                    sb.append(" => ").append(parent2.getClass().getSimpleName());
                }
                Log.e(TAG, "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                currentFocused = null;
            }
        }
        boolean handled = false;
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, direction);
        if (nextFocused != null && nextFocused != currentFocused) {
            if (direction != 33) {
                if (direction == 130) {
                    int nextDown = getChildRectInPagerCoordinates(this.mTempRect, nextFocused).bottom;
                    int currDown = getChildRectInPagerCoordinates(this.mTempRect, currentFocused).bottom;
                    handled = (currentFocused == null || nextDown > currDown) ? nextFocused.requestFocus() : pageDown();
                }
            } else {
                int nextTop = getChildRectInPagerCoordinates(this.mTempRect, nextFocused).top;
                int currTop = getChildRectInPagerCoordinates(this.mTempRect, currentFocused).top;
                handled = (currentFocused == null || nextTop < currTop) ? nextFocused.requestFocus() : pageUp();
            }
        } else if (direction == 33 || direction == 1) {
            handled = pageUp();
        } else if (direction == 130 || direction == 2) {
            handled = pageDown();
        }
        if (handled) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(direction));
        }
        return handled;
    }

    private Rect getChildRectInPagerCoordinates(Rect outRect, View child) {
        if (outRect == null) {
            outRect = new Rect();
        }
        if (child == null) {
            outRect.set(0, 0, 0, 0);
            return outRect;
        }
        outRect.left = child.getLeft();
        outRect.right = child.getRight();
        outRect.top = child.getTop();
        outRect.bottom = child.getBottom();
        ViewParent parent = child.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup group = (ViewGroup) parent;
            outRect.left += group.getLeft();
            outRect.right += group.getRight();
            outRect.top += group.getTop();
            outRect.bottom += group.getBottom();
            parent = group.getParent();
        }
        return outRect;
    }

    boolean pageUp() {
        int i = this.mCurItem;
        if (i > 0) {
            setCurrentItem(i - 1, true);
            return true;
        }
        return false;
    }

    boolean pageDown() {
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null && this.mCurItem < pagerAdapter.getCount() - 1) {
            setCurrentItem(this.mCurItem + 1, true);
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        ItemInfo ii;
        int focusableCount = views.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (child.getVisibility() == 0 && (ii = infoForChild(child)) != null && ii.position == this.mCurItem) {
                    child.addFocusables(views, direction, focusableMode);
                }
            }
        }
        if ((descendantFocusability == 262144 && focusableCount != views.size()) || !isFocusable()) {
            return;
        }
        if (((focusableMode & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && views != null) {
            views.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> views) {
        ItemInfo ii;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0 && (ii = infoForChild(child)) != null && ii.position == this.mCurItem) {
                child.addTouchables(views);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        int index;
        int increment;
        int end;
        ItemInfo ii;
        int count = getChildCount();
        if ((direction & 2) != 0) {
            index = 0;
            increment = 1;
            end = count;
        } else {
            index = count - 1;
            increment = -1;
            end = -1;
        }
        for (int i = index; i != end; i += increment) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0 && (ii = infoForChild(child)) != null && ii.position == this.mCurItem && child.requestFocus(direction, previouslyFocusedRect)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        ItemInfo ii;
        if (event.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(event);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0 && (ii = infoForChild(child)) != null && ii.position == this.mCurItem && child.dispatchPopulateAccessibilityEvent(event)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return generateDefaultLayoutParams();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return (p instanceof LayoutParams) && super.checkLayoutParams(p);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* loaded from: classes17.dex */
    class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        MyAccessibilityDelegate() {
        }

        @Override // android.support.p001v4.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(ViewPager.class.getName());
            AccessibilityRecordCompat recordCompat = AccessibilityRecordCompat.obtain();
            recordCompat.setScrollable(canScroll());
            if (event.getEventType() == 4096 && VerticalViewPager.this.mAdapter != null) {
                recordCompat.setItemCount(VerticalViewPager.this.mAdapter.getCount());
                recordCompat.setFromIndex(VerticalViewPager.this.mCurItem);
                recordCompat.setToIndex(VerticalViewPager.this.mCurItem);
            }
        }

        @Override // android.support.p001v4.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setClassName(ViewPager.class.getName());
            info.setScrollable(canScroll());
            if (VerticalViewPager.this.internalCanScrollVertically(1)) {
                info.addAction(4096);
            }
            if (VerticalViewPager.this.internalCanScrollVertically(-1)) {
                info.addAction(8192);
            }
        }

        @Override // android.support.p001v4.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View host, int action, Bundle args) {
            if (super.performAccessibilityAction(host, action, args)) {
                return true;
            }
            switch (action) {
                case 4096:
                    if (!VerticalViewPager.this.internalCanScrollVertically(1)) {
                        return false;
                    }
                    VerticalViewPager verticalViewPager = VerticalViewPager.this;
                    verticalViewPager.setCurrentItem(verticalViewPager.mCurItem + 1);
                    return true;
                case 8192:
                    if (!VerticalViewPager.this.internalCanScrollVertically(-1)) {
                        return false;
                    }
                    VerticalViewPager verticalViewPager2 = VerticalViewPager.this;
                    verticalViewPager2.setCurrentItem(verticalViewPager2.mCurItem - 1);
                    return true;
                default:
                    return false;
            }
        }

        private boolean canScroll() {
            return VerticalViewPager.this.mAdapter != null && VerticalViewPager.this.mAdapter.getCount() > 1;
        }
    }

    /* loaded from: classes17.dex */
    private class PagerObserver extends DataSetObserver {
        private PagerObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            VerticalViewPager.this.dataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            VerticalViewPager.this.dataSetChanged();
        }
    }

    /* loaded from: classes17.dex */
    public static class LayoutParams extends ViewGroup.LayoutParams {
        int childIndex;
        public int gravity;
        float heightFactor;
        public boolean isDecor;
        boolean needsMeasure;
        int position;

        public LayoutParams() {
            super(-1, -1);
            this.heightFactor = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.heightFactor = 0.0f;
            TypedArray a = context.obtainStyledAttributes(attrs, VerticalViewPager.LAYOUT_ATTRS);
            this.gravity = a.getInteger(0, 48);
            a.recycle();
        }
    }

    /* loaded from: classes17.dex */
    static class ViewPositionComparator implements Comparator<View> {
        ViewPositionComparator() {
        }

        @Override // java.util.Comparator
        public int compare(View lhs, View rhs) {
            LayoutParams llp = (LayoutParams) lhs.getLayoutParams();
            LayoutParams rlp = (LayoutParams) rhs.getLayoutParams();
            if (llp.isDecor != rlp.isDecor) {
                return llp.isDecor ? 1 : -1;
            }
            return llp.position - rlp.position;
        }
    }
}
