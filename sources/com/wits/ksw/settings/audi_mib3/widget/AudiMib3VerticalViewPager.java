package com.wits.ksw.settings.audi_mib3.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.EdgeEffectCompat;
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

public class AudiMib3VerticalViewPager extends ViewGroup {
    private static final int CLOSE_ENOUGH = 2;
    private static final Comparator<ItemInfo> COMPARATOR = new Comparator<ItemInfo>() {
        public int compare(ItemInfo lhs, ItemInfo rhs) {
            return lhs.position - rhs.position;
        }
    };
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    /* access modifiers changed from: private */
    public static final int[] LAYOUT_ATTRS = {16842931};
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float t) {
            float t2 = t - 1.0f;
            return (t2 * t2 * t2 * t2 * t2) + 1.0f;
        }
    };
    private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();
    private int mActivePointerId = -1;
    /* access modifiers changed from: private */
    public PagerAdapter mAdapter;
    private OnAdapterChangeListener mAdapterChangeListener;
    private EdgeEffectCompat mBottomEdge;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    /* access modifiers changed from: private */
    public int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable = new Runnable() {
        public void run() {
            AudiMib3VerticalViewPager.this.setScrollState(0);
            AudiMib3VerticalViewPager.this.populate();
        }
    };
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout = true;
    private float mFirstOffset = -3.4028235E38f;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mIgnoreGutter;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private ViewPager.OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsUnableToDrag;
    private final ArrayList<ItemInfo> mItems = new ArrayList<>();
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset = Float.MAX_VALUE;
    private int mLeftPageBounds;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets = false;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit = 1;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private int mPageMargin;
    private ViewPager.PageTransformer mPageTransformer;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState = null;
    private ClassLoader mRestoredClassLoader = null;
    private int mRestoredCurItem = -1;
    private int mRightPageBounds;
    private int mScrollState = 0;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private Method mSetChildrenDrawingOrderEnabled;
    private final ItemInfo mTempItem = new ItemInfo();
    private final Rect mTempRect = new Rect();
    private EdgeEffectCompat mTopEdge;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    interface Decor {
    }

    interface OnAdapterChangeListener {
        void onAdapterChanged(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    static class ItemInfo {
        float heightFactor;
        Object object;
        float offset;
        int position;
        boolean scrolling;

        ItemInfo() {
        }
    }

    public AudiMib3VerticalViewPager(Context context) {
        super(context);
        initViewPager();
    }

    public AudiMib3VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewPager();
    }

    /* access modifiers changed from: package-private */
    public void initViewPager() {
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

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: private */
    public void setScrollState(int newState) {
        if (this.mScrollState != newState) {
            this.mScrollState = newState;
            if (this.mPageTransformer != null) {
                enableLayers(newState != 0);
            }
            ViewPager.OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageScrollStateChanged(newState);
            }
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
            if (!((LayoutParams) getChildAt(i).getLayoutParams()).isDecor) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }

    /* access modifiers changed from: package-private */
    public void setOnAdapterChangeListener(OnAdapterChangeListener listener) {
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

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int item, boolean smoothScroll, boolean always) {
        setCurrentItemInternal(item, smoothScroll, always, 0);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int item, boolean smoothScroll, boolean always, int velocity) {
        ViewPager.OnPageChangeListener onPageChangeListener;
        ViewPager.OnPageChangeListener onPageChangeListener2;
        PagerAdapter pagerAdapter = this.mAdapter;
        boolean z = false;
        if (pagerAdapter == null || pagerAdapter.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (always || this.mCurItem != item || this.mItems.size() == 0) {
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
            if (this.mCurItem != item) {
                z = true;
            }
            boolean dispatchSelected = z;
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
        } else {
            setScrollingCacheEnabled(false);
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
            destY = (int) (((float) getClientHeight()) * Math.max(this.mFirstOffset, Math.min(curInfo.offset, this.mLastOffset)));
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
            int i = 1;
            boolean hasTransformer = transformer != null;
            boolean needsPopulate = hasTransformer != (this.mPageTransformer != null);
            this.mPageTransformer = transformer;
            setChildrenDrawingOrderEnabledCompat(hasTransformer);
            if (hasTransformer) {
                if (reverseDrawingOrder) {
                    i = 2;
                }
                this.mDrawingOrder = i;
            } else {
                this.mDrawingOrder = 0;
            }
            if (needsPopulate) {
                populate();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setChildrenDrawingOrderEnabledCompat(boolean enable) {
        if (Build.VERSION.SDK_INT >= 7) {
            if (this.mSetChildrenDrawingOrderEnabled == null) {
                Class<ViewGroup> cls = ViewGroup.class;
                try {
                    this.mSetChildrenDrawingOrderEnabled = cls.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (NoSuchMethodException e) {
                    Log.e(TAG, "Can't find setChildrenDrawingOrderEnabled", e);
                }
            }
            try {
                this.mSetChildrenDrawingOrderEnabled.invoke(this, new Object[]{Boolean.valueOf(enable)});
            } catch (Exception e2) {
                Log.e(TAG, "Error changing children drawing order", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int childCount, int i) {
        return ((LayoutParams) this.mDrawingOrderedChildren.get(this.mDrawingOrder == 2 ? (childCount - 1) - i : i).getLayoutParams()).childIndex;
    }

    /* access modifiers changed from: package-private */
    public ViewPager.OnPageChangeListener setInternalPageChangeListener(ViewPager.OnPageChangeListener listener) {
        ViewPager.OnPageChangeListener oldListener = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = listener;
        return oldListener;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public void setOffscreenPageLimit(int limit) {
        if (limit < 1) {
            Log.w(TAG, "Requested offscreen page limit " + limit + " too small; defaulting to " + 1);
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

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.mMarginDrawable;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable d = this.mMarginDrawable;
        if (d != null && d.isStateful()) {
            d.setState(getDrawableState());
        }
    }

    /* access modifiers changed from: package-private */
    public float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    /* access modifiers changed from: package-private */
    public void smoothScrollTo(int x, int y) {
        smoothScrollTo(x, y, 0);
    }

    /* access modifiers changed from: package-private */
    public void smoothScrollTo(int x, int y, int velocity) {
        int duration;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int sx = getScrollX();
        int sy = getScrollY();
        int dx = x - sx;
        int dy = y - sy;
        if (dx == 0 && dy == 0) {
            completeScroll(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int height = getClientHeight();
        int halfHeight = height / 2;
        float distance = ((float) halfHeight) + (((float) halfHeight) * distanceInfluenceForSnapDuration(Math.min(1.0f, (((float) Math.abs(dx)) * 1.0f) / ((float) height))));
        int velocity2 = Math.abs(velocity);
        if (velocity2 > 0) {
            duration = Math.round(Math.abs(distance / ((float) velocity2)) * 1000.0f) * 4;
        } else {
            duration = (int) ((1.0f + (((float) Math.abs(dx)) / (((float) this.mPageMargin) + (((float) height) * this.mAdapter.getPageWidth(this.mCurItem))))) * 100.0f);
        }
        this.mScroller.startScroll(sx, sy, dx, dy, Math.min(duration, MAX_SETTLE_DURATION));
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* access modifiers changed from: package-private */
    public ItemInfo addNewItem(int position, int index) {
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

    /* access modifiers changed from: package-private */
    public void dataSetChanged() {
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
                LayoutParams lp = (LayoutParams) getChildAt(i2).getLayoutParams();
                if (!lp.isDecor) {
                    lp.heightFactor = 0.0f;
                }
            }
            setCurrentItemInternal(newCurrItem, false, true);
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void populate() {
        populate(this.mCurItem);
    }

    /* access modifiers changed from: package-private */
    public void populate(int newCurrentItem) {
        int focusDirection;
        ItemInfo oldCurInfo;
        String resName;
        ItemInfo ii;
        int curIndex;
        float f;
        float bottomHeightNeeded;
        int startPos;
        int pageLimit;
        float topHeightNeeded;
        int i = newCurrentItem;
        int i2 = this.mCurItem;
        if (i2 != i) {
            int focusDirection2 = i2 < i ? 130 : 33;
            ItemInfo oldCurInfo2 = infoForPosition(i2);
            this.mCurItem = i;
            focusDirection = focusDirection2;
            oldCurInfo = oldCurInfo2;
        } else {
            focusDirection = 2;
            oldCurInfo = null;
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
            if (N == this.mExpectedAdapterCount) {
                ItemInfo curItem = null;
                int curIndex2 = 0;
                while (true) {
                    if (curIndex2 >= this.mItems.size()) {
                        break;
                    }
                    ItemInfo ii2 = this.mItems.get(curIndex2);
                    if (ii2.position < this.mCurItem) {
                        curIndex2++;
                    } else if (ii2.position == this.mCurItem) {
                        curItem = ii2;
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
                        f = 0.0f;
                    } else {
                        curIndex = curIndex2;
                        f = (2.0f - curItem.heightFactor) + (((float) getPaddingLeft()) / ((float) clientHeight));
                    }
                    float topHeightNeeded2 = f;
                    int pos = this.mCurItem - 1;
                    int curIndex3 = curIndex;
                    while (true) {
                        if (pos < 0) {
                            break;
                        }
                        if (extraHeightTop < topHeightNeeded2 || pos >= startPos2) {
                            topHeightNeeded = topHeightNeeded2;
                            if (ii3 == null || pos != ii3.position) {
                                extraHeightTop += addNewItem(pos, itemIndex + 1).heightFactor;
                                curIndex3++;
                                ii3 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                            } else {
                                extraHeightTop += ii3.heightFactor;
                                itemIndex--;
                                ii3 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                            }
                        } else if (ii3 == null) {
                            float f2 = topHeightNeeded2;
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
                        int i3 = newCurrentItem;
                        topHeightNeeded2 = topHeightNeeded;
                    }
                    float extraHeightBottom = curItem.heightFactor;
                    int itemIndex2 = curIndex3 + 1;
                    if (extraHeightBottom < 2.0f) {
                        ItemInfo ii4 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                        if (clientHeight <= 0) {
                            bottomHeightNeeded = 0.0f;
                        } else {
                            bottomHeightNeeded = (((float) getPaddingRight()) / ((float) clientHeight)) + 2.0f;
                        }
                        int pos2 = this.mCurItem + 1;
                        while (true) {
                            if (pos2 >= N) {
                                int i4 = startPos2;
                                break;
                            }
                            if (extraHeightBottom < bottomHeightNeeded || pos2 <= endPos) {
                                pageLimit = pageLimit2;
                                startPos = startPos2;
                                if (ii4 == null || pos2 != ii4.position) {
                                    ItemInfo ii5 = addNewItem(pos2, itemIndex2);
                                    itemIndex2++;
                                    extraHeightBottom += ii5.heightFactor;
                                    ii4 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                                } else {
                                    extraHeightBottom += ii4.heightFactor;
                                    itemIndex2++;
                                    ii4 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                                }
                            } else if (ii4 == null) {
                                int i5 = pageLimit2;
                                int i6 = startPos2;
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
                        ItemInfo itemInfo = ii4;
                    } else {
                        int i7 = startPos2;
                    }
                    calculatePageOffsets(curItem, curIndex3, oldCurInfo);
                    float extraHeightBottom2 = curIndex3;
                } else {
                    int i8 = curIndex2;
                    int i9 = pageLimit2;
                    int i10 = startPos2;
                }
                this.mAdapter.setPrimaryItem((ViewGroup) this, this.mCurItem, curItem != null ? curItem.object : null);
                this.mAdapter.finishUpdate((ViewGroup) this);
                int childCount = getChildCount();
                for (int i11 = 0; i11 < childCount; i11++) {
                    View child = getChildAt(i11);
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    lp.childIndex = i11;
                    if (!lp.isDecor) {
                        if (lp.heightFactor == 0.0f && (ii = infoForChild(child)) != null) {
                            lp.heightFactor = ii.heightFactor;
                            lp.position = ii.position;
                        }
                    }
                }
                sortChildDrawingOrder();
                if (hasFocus()) {
                    View currentFocused = findFocus();
                    ItemInfo ii6 = currentFocused != null ? infoForAnyChild(currentFocused) : null;
                    if (ii6 == null || ii6.position != this.mCurItem) {
                        int i12 = 0;
                        while (i12 < getChildCount()) {
                            View child2 = getChildAt(i12);
                            ItemInfo ii7 = infoForChild(child2);
                            if (ii7 == null || ii7.position != this.mCurItem || !child2.requestFocus(focusDirection)) {
                                i12++;
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            int i13 = startPos2;
            try {
                resName = getResources().getResourceName(getId());
            } catch (Resources.NotFoundException e) {
                resName = Integer.toHexString(getId());
            }
            throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.mExpectedAdapterCount + ", found: " + N + " Pager id: " + resName + " Pager class: " + getClass() + " Problematic adapter: " + this.mAdapter.getClass());
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
                this.mDrawingOrderedChildren.add(getChildAt(i));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    private void calculatePageOffsets(ItemInfo curItem, int curIndex, ItemInfo oldCurInfo) {
        ItemInfo ii;
        ItemInfo ii2;
        int N = this.mAdapter.getCount();
        int height = getClientHeight();
        float marginOffset = height > 0 ? ((float) this.mPageMargin) / ((float) height) : 0.0f;
        if (oldCurInfo != null) {
            int oldCurPosition = oldCurInfo.position;
            if (oldCurPosition < curItem.position) {
                int itemIndex = 0;
                float offset = oldCurInfo.offset + oldCurInfo.heightFactor + marginOffset;
                int pos = oldCurPosition + 1;
                while (pos <= curItem.position && itemIndex < this.mItems.size()) {
                    Object obj = this.mItems.get(itemIndex);
                    while (true) {
                        ii2 = (ItemInfo) obj;
                        if (pos > ii2.position && itemIndex < this.mItems.size() - 1) {
                            itemIndex++;
                            obj = this.mItems.get(itemIndex);
                        }
                    }
                    while (pos < ii2.position) {
                        offset += this.mAdapter.getPageWidth(pos) + marginOffset;
                        pos++;
                    }
                    ii2.offset = offset;
                    offset += ii2.heightFactor + marginOffset;
                    pos++;
                }
            } else if (oldCurPosition > curItem.position) {
                int itemIndex2 = this.mItems.size() - 1;
                float offset2 = oldCurInfo.offset;
                int pos2 = oldCurPosition - 1;
                while (pos2 >= curItem.position && itemIndex2 >= 0) {
                    Object obj2 = this.mItems.get(itemIndex2);
                    while (true) {
                        ii = (ItemInfo) obj2;
                        if (pos2 < ii.position && itemIndex2 > 0) {
                            itemIndex2--;
                            obj2 = this.mItems.get(itemIndex2);
                        }
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
        int itemCount = this.mItems.size();
        float offset3 = curItem.offset;
        int pos3 = curItem.position - 1;
        this.mFirstOffset = curItem.position == 0 ? curItem.offset : -3.4028235E38f;
        this.mLastOffset = curItem.position == N + -1 ? (curItem.offset + curItem.heightFactor) - 1.0f : Float.MAX_VALUE;
        int i = curIndex - 1;
        while (i >= 0) {
            ItemInfo ii3 = this.mItems.get(i);
            while (pos3 > ii3.position) {
                offset3 -= this.mAdapter.getPageWidth(pos3) + marginOffset;
                pos3--;
            }
            offset3 -= ii3.heightFactor + marginOffset;
            ii3.offset = offset3;
            if (ii3.position == 0) {
                this.mFirstOffset = offset3;
            }
            i--;
            pos3--;
        }
        float offset4 = curItem.offset + curItem.heightFactor + marginOffset;
        int pos4 = curItem.position + 1;
        int i2 = curIndex + 1;
        while (i2 < itemCount) {
            ItemInfo ii4 = this.mItems.get(i2);
            while (pos4 < ii4.position) {
                offset4 += this.mAdapter.getPageWidth(pos4) + marginOffset;
                pos4++;
            }
            if (ii4.position == N - 1) {
                this.mLastOffset = (ii4.heightFactor + offset4) - 1.0f;
            }
            ii4.offset = offset4;
            offset4 += ii4.heightFactor + marginOffset;
            i2++;
            pos4++;
        }
        this.mNeedCalculatePageOffsets = false;
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<SavedState>() {
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

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

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.position);
            out.writeParcelable(this.adapterState, flags);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.position + "}";
        }

        SavedState(Parcel in, ClassLoader loader2) {
            super(in);
            loader2 = loader2 == null ? getClass().getClassLoader() : loader2;
            this.position = in.readInt();
            this.adapterState = in.readParcelable(loader2);
            this.loader = loader2;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.position = this.mCurItem;
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            ss.adapterState = pagerAdapter.saveState();
        }
        return ss;
    }

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

    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (!checkLayoutParams(params)) {
            params = generateLayoutParams(params);
        }
        LayoutParams lp = (LayoutParams) params;
        lp.isDecor |= child instanceof Decor;
        if (!this.mInLayout) {
            super.addView(child, index, params);
        } else if (lp == null || !lp.isDecor) {
            lp.needsMeasure = true;
            addViewInLayout(child, index, params);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForChild(View child) {
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo ii = this.mItems.get(i);
            if (this.mAdapter.isViewFromObject(child, ii.object)) {
                return ii;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForAnyChild(View child) {
        while (true) {
            ViewParent parent = child.getParent();
            ViewParent parent2 = parent;
            if (parent == this) {
                return infoForChild(child);
            }
            if (parent2 == null || !(parent2 instanceof View)) {
                return null;
            }
            child = (View) parent2;
        }
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForPosition(int position) {
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo ii = this.mItems.get(i);
            if (ii.position == position) {
                return ii;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LayoutParams lp;
        int measuredHeight;
        int heightMode;
        int widthSize;
        int heightSize;
        int heightMode2;
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
            if (child.getVisibility() != 8) {
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
                        widthMode = 1073741824;
                    } else if (consumeHorizontal) {
                        heightMode3 = 1073741824;
                    }
                    int widthSize2 = childWidthSize;
                    int heightSize2 = childHeightSize;
                    measuredHeight = measuredHeight2;
                    if (lp2.width != -2) {
                        widthMode = 1073741824;
                        if (lp2.width != -1) {
                            widthSize = lp2.width;
                        } else {
                            widthSize = widthSize2;
                        }
                    } else {
                        widthSize = widthSize2;
                    }
                    if (lp2.height == -2) {
                        heightMode2 = heightMode3;
                        heightSize = heightSize2;
                    } else if (lp2.height != -1) {
                        heightSize = lp2.height;
                        heightMode2 = 1073741824;
                    } else {
                        heightMode2 = 1073741824;
                        heightSize = heightSize2;
                    }
                    heightMode = maxGutterSize;
                    int i2 = widthSize;
                    child.measure(View.MeasureSpec.makeMeasureSpec(widthSize, widthMode), View.MeasureSpec.makeMeasureSpec(heightSize, heightMode2));
                    if (consumeVertical) {
                        childHeightSize -= child.getMeasuredHeight();
                    } else if (consumeHorizontal) {
                        childWidthSize -= child.getMeasuredWidth();
                    }
                }
            } else {
                measuredHeight = measuredHeight2;
                heightMode = maxGutterSize;
            }
            i++;
            int i3 = widthMeasureSpec;
            int i4 = heightMeasureSpec;
            maxGutterSize = heightMode;
            measuredHeight2 = measuredHeight;
        }
        int i5 = maxGutterSize;
        this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(childWidthSize, 1073741824);
        this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(childHeightSize, 1073741824);
        this.mInLayout = true;
        populate();
        this.mInLayout = false;
        int size2 = getChildCount();
        for (int i6 = 0; i6 < size2; i6++) {
            View child2 = getChildAt(i6);
            if (child2.getVisibility() != 8 && ((lp = (LayoutParams) child2.getLayoutParams()) == null || !lp.isDecor)) {
                child2.measure(this.mChildWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec((int) (((float) childHeightSize) * lp.heightFactor), 1073741824));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (h != oldh) {
            int i = this.mPageMargin;
            recomputeScrollPosition(h, oldh, i, i);
        }
    }

    private void recomputeScrollPosition(int height, int oldHeight, int margin, int oldMargin) {
        int i = height;
        if (oldHeight <= 0 || this.mItems.isEmpty()) {
            ItemInfo ii = infoForPosition(this.mCurItem);
            int scrollPos = (int) (((float) ((i - getPaddingTop()) - getPaddingBottom())) * (ii != null ? Math.min(ii.offset, this.mLastOffset) : 0.0f));
            if (scrollPos != getScrollY()) {
                completeScroll(false);
                scrollTo(getScrollX(), scrollPos);
                return;
            }
            return;
        }
        int newOffsetPixels = (int) (((float) (((i - getPaddingTop()) - getPaddingBottom()) + margin)) * (((float) getScrollY()) / ((float) (((oldHeight - getPaddingTop()) - getPaddingBottom()) + oldMargin))));
        scrollTo(getScrollX(), newOffsetPixels);
        if (!this.mScroller.isFinished()) {
            this.mScroller.startScroll(0, newOffsetPixels, 0, (int) (infoForPosition(this.mCurItem).offset * ((float) i)), this.mScroller.getDuration() - this.mScroller.timePassed());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        boolean z;
        int paddingBottom;
        int paddingTop;
        int height;
        int count;
        int childLeft;
        int paddingLeft;
        int childTop;
        int count2 = getChildCount();
        int width = r - l;
        int height2 = b - t;
        int paddingLeft2 = getPaddingLeft();
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
                                LayoutParams layoutParams = lp;
                                childLeft = Math.max((width - child.getMeasuredWidth()) / 2, paddingLeft2);
                                break;
                            case 3:
                                int childLeft2 = paddingLeft2;
                                paddingLeft2 += child.getMeasuredWidth();
                                LayoutParams layoutParams2 = lp;
                                childLeft = childLeft2;
                                break;
                            case 5:
                                int childLeft3 = (width - paddingRight) - child.getMeasuredWidth();
                                paddingRight += child.getMeasuredWidth();
                                LayoutParams layoutParams3 = lp;
                                childLeft = childLeft3;
                                break;
                            default:
                                LayoutParams layoutParams4 = lp;
                                childLeft = paddingLeft2;
                                break;
                        }
                        switch (vgrav) {
                            case 16:
                                paddingLeft = paddingLeft2;
                                childTop = Math.max((height2 - child.getMeasuredHeight()) / 2, paddingTop2);
                                break;
                            case 48:
                                childTop = paddingTop2;
                                paddingTop2 += child.getMeasuredHeight();
                                paddingLeft = paddingLeft2;
                                break;
                            case 80:
                                childTop = (height2 - paddingBottom2) - child.getMeasuredHeight();
                                paddingBottom2 += child.getMeasuredHeight();
                                paddingLeft = paddingLeft2;
                                break;
                            default:
                                paddingLeft = paddingLeft2;
                                childTop = paddingTop2;
                                break;
                        }
                        int childTop2 = childTop + scrollY;
                        child.layout(childLeft, childTop2, child.getMeasuredWidth() + childLeft, childTop2 + child.getMeasuredHeight());
                        decorCount++;
                        paddingLeft2 = paddingLeft;
                        paddingTop2 = paddingTop2;
                    }
                }
                i++;
            } else {
                int childHeight = (height2 - paddingTop2) - paddingBottom2;
                int i3 = 0;
                while (i3 < count2) {
                    View child2 = getChildAt(i3);
                    if (child2.getVisibility() != i2) {
                        LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                        if (!lp2.isDecor) {
                            ItemInfo infoForChild = infoForChild(child2);
                            ItemInfo ii = infoForChild;
                            if (infoForChild != null) {
                                ItemInfo ii2 = ii;
                                count = count2;
                                int toff = (int) (((float) childHeight) * ii2.offset);
                                int childLeft4 = paddingLeft2;
                                height = height2;
                                int childTop3 = paddingTop2 + toff;
                                int i4 = toff;
                                if (lp2.needsMeasure != 0) {
                                    lp2.needsMeasure = false;
                                    paddingTop = paddingTop2;
                                    paddingBottom = paddingBottom2;
                                    child2.measure(View.MeasureSpec.makeMeasureSpec((width - paddingLeft2) - paddingRight, 1073741824), View.MeasureSpec.makeMeasureSpec((int) (((float) childHeight) * lp2.heightFactor), 1073741824));
                                } else {
                                    paddingTop = paddingTop2;
                                    paddingBottom = paddingBottom2;
                                }
                                child2.layout(childLeft4, childTop3, child2.getMeasuredWidth() + childLeft4, child2.getMeasuredHeight() + childTop3);
                            } else {
                                height = height2;
                                paddingTop = paddingTop2;
                                paddingBottom = paddingBottom2;
                                ItemInfo itemInfo = ii;
                                count = count2;
                            }
                        } else {
                            count = count2;
                            height = height2;
                            paddingTop = paddingTop2;
                            paddingBottom = paddingBottom2;
                        }
                    } else {
                        count = count2;
                        height = height2;
                        paddingTop = paddingTop2;
                        paddingBottom = paddingBottom2;
                    }
                    i3++;
                    count2 = count;
                    height2 = height;
                    paddingTop2 = paddingTop;
                    paddingBottom2 = paddingBottom;
                    i2 = 8;
                }
                int i5 = height2;
                int i6 = paddingTop2;
                int i7 = paddingBottom2;
                this.mLeftPageBounds = paddingLeft2;
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

    public void computeScroll() {
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            completeScroll(true);
            return;
        }
        int oldX = getScrollX();
        int oldY = getScrollY();
        int x = this.mScroller.getCurrX();
        int y = this.mScroller.getCurrY();
        if (!(oldX == x && oldY == y)) {
            scrollTo(x, y);
            if (!pageScrolled(y)) {
                this.mScroller.abortAnimation();
                scrollTo(x, 0);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
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
        int currentPage = ii.position;
        float pageOffset = ((((float) ypos) / ((float) height)) - ii.offset) / (ii.heightFactor + (((float) i) / ((float) height)));
        this.mCalledSuper = false;
        onPageScrolled(currentPage, pageOffset, (int) (((float) heightWithMargin) * pageOffset));
        if (this.mCalledSuper) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    /* access modifiers changed from: protected */
    public void onPageScrolled(int position, float offset, int offsetPixels) {
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
                    switch (lp.gravity & 112) {
                        case 16:
                            childTop = Math.max((height - child.getMeasuredHeight()) / 2, paddingTop);
                            break;
                        case 48:
                            childTop = paddingTop;
                            paddingTop += child.getHeight();
                            break;
                        case 80:
                            childTop = (height - paddingBottom) - child.getMeasuredHeight();
                            paddingBottom += child.getMeasuredHeight();
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
                    this.mPageTransformer.transformPage(child2, ((float) (child2.getTop() - scrollY2)) / ((float) getClientHeight()));
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
            if (!(oldX == x && oldY == y)) {
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
        if (!needPopulate) {
            return;
        }
        if (postEvents) {
            ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
        } else {
            this.mEndScrollRunnable.run();
        }
    }

    private boolean isGutterDrag(float y, float dy) {
        return (y < ((float) this.mGutterSize) && dy > 0.0f) || (y > ((float) (getHeight() - this.mGutterSize)) && dy < 0.0f);
    }

    private void enableLayers(boolean enable) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewCompat.setLayerType(getChildAt(i), enable ? 2 : 0, (Paint) null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x;
        MotionEvent motionEvent = ev;
        int action = ev.getAction() & 255;
        if (action == 3 || action == 1) {
            this.mIsBeingDragged = false;
            this.mIsUnableToDrag = false;
            this.mActivePointerId = -1;
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker == null) {
                return false;
            }
            velocityTracker.recycle();
            this.mVelocityTracker = null;
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
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mIsUnableToDrag = false;
                this.mScroller.computeScrollOffset();
                if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalY() - this.mScroller.getCurrY()) > this.mCloseEnough) {
                    this.mScroller.abortAnimation();
                    this.mPopulatePending = false;
                    populate();
                    this.mIsBeingDragged = true;
                    requestParentDisallowInterceptTouchEvent(true);
                    setScrollState(1);
                    break;
                } else {
                    completeScroll(false);
                    this.mIsBeingDragged = false;
                    break;
                }
            case 2:
                int activePointerId = this.mActivePointerId;
                if (activePointerId != -1) {
                    int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, activePointerId);
                    float y2 = MotionEventCompat.getY(motionEvent, pointerIndex);
                    float dy = y2 - this.mLastMotionY;
                    float yDiff = Math.abs(dy);
                    float x3 = MotionEventCompat.getX(motionEvent, pointerIndex);
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
                    if (yDiff > ((float) i) && 0.5f * yDiff > xDiff) {
                        this.mIsBeingDragged = true;
                        requestParentDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                        this.mLastMotionY = dy > 0.0f ? this.mInitialMotionY + ((float) this.mTouchSlop) : this.mInitialMotionY - ((float) this.mTouchSlop);
                        this.mLastMotionX = x;
                        setScrollingCacheEnabled(true);
                    } else if (xDiff > ((float) i)) {
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
        this.mVelocityTracker.addMovement(motionEvent);
        return this.mIsBeingDragged;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        PagerAdapter pagerAdapter;
        MotionEvent motionEvent = ev;
        if (this.mFakeDragging) {
            return true;
        }
        if ((ev.getAction() == 0 && ev.getEdgeFlags() != 0) || (pagerAdapter = this.mAdapter) == null || pagerAdapter.getCount() == 0) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
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
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                break;
            case 1:
                if (!this.mIsBeingDragged) {
                    break;
                } else {
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
                    int initialVelocity = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.mActivePointerId);
                    this.mPopulatePending = true;
                    int height = getClientHeight();
                    int scrollY = getScrollY();
                    ItemInfo ii = infoForCurrentScrollPosition();
                    setCurrentItemInternal(determineTargetPage(ii.position, ((((float) scrollY) / ((float) height)) - ii.offset) / ii.heightFactor, initialVelocity, (int) (MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId)) - this.mInitialMotionY)), true, true, initialVelocity);
                    this.mActivePointerId = -1;
                    endDrag();
                    int i = action;
                    needsInvalidate = this.mTopEdge.onRelease() | this.mBottomEdge.onRelease();
                    break;
                }
            case 2:
                if (!this.mIsBeingDragged) {
                    int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                    float y2 = MotionEventCompat.getY(motionEvent, pointerIndex);
                    float yDiff = Math.abs(y2 - this.mLastMotionY);
                    float x2 = MotionEventCompat.getX(motionEvent, pointerIndex);
                    float xDiff = Math.abs(x2 - this.mLastMotionX);
                    if (yDiff > ((float) this.mTouchSlop) && yDiff > xDiff) {
                        this.mIsBeingDragged = true;
                        requestParentDisallowInterceptTouchEvent(true);
                        float f = this.mInitialMotionY;
                        this.mLastMotionY = y2 - f > 0.0f ? f + ((float) this.mTouchSlop) : f - ((float) this.mTouchSlop);
                        this.mLastMotionX = x2;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.mIsBeingDragged == 0) {
                    int i2 = action;
                    break;
                } else {
                    needsInvalidate = false | performDrag(MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId)));
                    int i3 = action;
                    break;
                }
            case 3:
                if (!this.mIsBeingDragged) {
                    int i4 = action;
                    break;
                } else {
                    scrollToItem(this.mCurItem, true, 0, false);
                    this.mActivePointerId = -1;
                    endDrag();
                    needsInvalidate = this.mTopEdge.onRelease() | this.mBottomEdge.onRelease();
                    int i5 = action;
                    break;
                }
            case 5:
                int index = MotionEventCompat.getActionIndex(ev);
                this.mLastMotionY = MotionEventCompat.getY(motionEvent, index);
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, index);
                int i6 = action;
                break;
            case 6:
                onSecondaryPointerUp(ev);
                this.mLastMotionY = MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId));
                int i7 = action;
                break;
            default:
                int i8 = action;
                break;
        }
        if (!needsInvalidate) {
            return true;
        }
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    private boolean performDrag(float y) {
        float f = y;
        boolean needsInvalidate = false;
        this.mLastMotionY = f;
        float scrollY = ((float) getScrollY()) + (this.mLastMotionY - f);
        int height = getClientHeight();
        float topBound = ((float) height) * this.mFirstOffset;
        float bottomBound = ((float) height) * this.mLastOffset;
        boolean topAbsolute = true;
        boolean bottomAbsolute = true;
        ItemInfo firstItem = this.mItems.get(0);
        ArrayList<ItemInfo> arrayList = this.mItems;
        ItemInfo lastItem = arrayList.get(arrayList.size() - 1);
        if (firstItem.position != 0) {
            topAbsolute = false;
            topBound = firstItem.offset * ((float) height);
        }
        if (lastItem.position != this.mAdapter.getCount() - 1) {
            bottomAbsolute = false;
            bottomBound = lastItem.offset * ((float) height);
        }
        if (scrollY < topBound) {
            if (topAbsolute) {
                needsInvalidate = this.mTopEdge.onPull(Math.abs(topBound - scrollY) / ((float) height));
            }
            scrollY = topBound;
        } else if (scrollY > bottomBound) {
            if (bottomAbsolute) {
                needsInvalidate = this.mBottomEdge.onPull(Math.abs(scrollY - bottomBound) / ((float) height));
            }
            scrollY = bottomBound;
        }
        this.mLastMotionX += scrollY - ((float) ((int) scrollY));
        scrollTo(getScrollX(), (int) scrollY);
        pageScrolled((int) scrollY);
        return needsInvalidate;
    }

    private ItemInfo infoForCurrentScrollPosition() {
        int height = getClientHeight();
        float marginOffset = 0.0f;
        float scrollOffset = height > 0 ? ((float) getScrollY()) / ((float) height) : 0.0f;
        if (height > 0) {
            marginOffset = ((float) this.mPageMargin) / ((float) height);
        }
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
            float topBound = offset;
            float bottomBound = ii.heightFactor + offset + marginOffset;
            if (!first && scrollOffset < topBound) {
                return lastItem;
            }
            if (scrollOffset < bottomBound || i == this.mItems.size() - 1) {
                return ii;
            }
            first = false;
            lastPos = ii.position;
            lastOffset = offset;
            lastHeight = ii.heightFactor;
            lastItem = ii;
            i++;
        }
        return lastItem;
    }

    private int determineTargetPage(int currentPage, float pageOffset, int velocity, int deltaY) {
        int targetPage;
        if (Math.abs(deltaY) <= this.mFlingDistance || Math.abs(velocity) <= this.mMinimumVelocity) {
            targetPage = (int) (((float) currentPage) + pageOffset + (currentPage >= this.mCurItem ? 0.4f : 0.6f));
        } else {
            targetPage = velocity > 0 ? currentPage : currentPage + 1;
        }
        if (this.mItems.size() <= 0) {
            return targetPage;
        }
        ArrayList<ItemInfo> arrayList = this.mItems;
        return Math.max(this.mItems.get(0).position, Math.min(targetPage, arrayList.get(arrayList.size() - 1).position));
    }

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
                canvas.translate((float) getPaddingLeft(), this.mFirstOffset * ((float) height));
                this.mTopEdge.setSize(width, height);
                needsInvalidate = false | this.mTopEdge.draw(canvas);
                canvas.restoreToCount(restoreCount);
            }
            if (!this.mBottomEdge.isFinished()) {
                int restoreCount2 = canvas.save();
                int height2 = getHeight();
                int width2 = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.rotate(180.0f);
                canvas.translate((float) ((-width2) - getPaddingLeft()), (-(this.mLastOffset + 1.0f)) * ((float) height2));
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

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float heightFactor;
        float marginOffset;
        super.onDraw(canvas);
        if (this.mPageMargin <= 0 || this.mMarginDrawable == null || this.mItems.size() <= 0 || this.mAdapter == null) {
            Canvas canvas2 = canvas;
            return;
        }
        int scrollY = getScrollY();
        int height = getHeight();
        float marginOffset2 = ((float) this.mPageMargin) / ((float) height);
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
                heightFactor = (ii.offset + ii.heightFactor) * ((float) height);
                offset = ii.offset + ii.heightFactor + marginOffset2;
            } else {
                float heightFactor2 = this.mAdapter.getPageWidth(pos);
                float drawAt = (offset + heightFactor2) * ((float) height);
                offset += heightFactor2 + marginOffset2;
                heightFactor = drawAt;
            }
            int i = this.mPageMargin;
            if (((float) i) + heightFactor > ((float) scrollY)) {
                marginOffset = marginOffset2;
                this.mMarginDrawable.setBounds(this.mLeftPageBounds, (int) heightFactor, this.mRightPageBounds, (int) (((float) i) + heightFactor + 0.5f));
                this.mMarginDrawable.draw(canvas);
            } else {
                Canvas canvas3 = canvas;
                marginOffset = marginOffset2;
            }
            if (heightFactor <= ((float) (scrollY + height))) {
                pos++;
                marginOffset2 = marginOffset;
            } else {
                return;
            }
        }
        Canvas canvas4 = canvas;
        float f = marginOffset2;
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
        if (this.mFakeDragging) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
            int initialVelocity = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.mActivePointerId);
            this.mPopulatePending = true;
            int height = getClientHeight();
            int scrollY = getScrollY();
            ItemInfo ii = infoForCurrentScrollPosition();
            setCurrentItemInternal(determineTargetPage(ii.position, ((((float) scrollY) / ((float) height)) - ii.offset) / ii.heightFactor, initialVelocity, (int) (this.mLastMotionY - this.mInitialMotionY)), true, true, initialVelocity);
            endDrag();
            this.mFakeDragging = false;
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public void fakeDragBy(float yOffset) {
        if (this.mFakeDragging) {
            this.mLastMotionY += yOffset;
            float scrollY = ((float) getScrollY()) - yOffset;
            int height = getClientHeight();
            float topBound = ((float) height) * this.mFirstOffset;
            float bottomBound = ((float) height) * this.mLastOffset;
            ItemInfo firstItem = this.mItems.get(0);
            ArrayList<ItemInfo> arrayList = this.mItems;
            ItemInfo lastItem = arrayList.get(arrayList.size() - 1);
            if (firstItem.position != 0) {
                topBound = firstItem.offset * ((float) height);
            }
            if (lastItem.position != this.mAdapter.getCount() - 1) {
                bottomBound = lastItem.offset * ((float) height);
            }
            if (scrollY < topBound) {
                scrollY = topBound;
            } else if (scrollY > bottomBound) {
                scrollY = bottomBound;
            }
            this.mLastMotionY += scrollY - ((float) ((int) scrollY));
            scrollTo(getScrollX(), (int) scrollY);
            pageScrolled((int) scrollY);
            MotionEvent ev = MotionEvent.obtain(this.mFakeDragBeginTime, SystemClock.uptimeMillis(), 2, 0.0f, this.mLastMotionY, 0);
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
        if (MotionEventCompat.getPointerId(ev, pointerIndex) == this.mActivePointerId) {
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
        if (direction < 0) {
            if (scrollY > ((int) (((float) height) * this.mFirstOffset))) {
                return true;
            }
            return false;
        } else if (direction <= 0 || scrollY >= ((int) (((float) height) * this.mLastOffset))) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(View v, boolean checkV, int dy, int x, int y) {
        View view = v;
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            int scrollX = v.getScrollX();
            int scrollY = v.getScrollY();
            for (int i = group.getChildCount() - 1; i >= 0; i--) {
                View child = group.getChildAt(i);
                if (y + scrollY >= child.getTop() && y + scrollY < child.getBottom() && x + scrollX >= child.getLeft() && x + scrollX < child.getRight()) {
                    if (canScroll(child, true, dy, (x + scrollX) - child.getLeft(), (y + scrollY) - child.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (!checkV) {
            int i2 = dy;
        } else if (ViewCompat.canScrollVertically(v, -dy)) {
            return true;
        }
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event) || executeKeyEvent(event);
    }

    public boolean executeKeyEvent(KeyEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        switch (event.getKeyCode()) {
            case 21:
                return arrowScroll(17);
            case 22:
                return arrowScroll(66);
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
                if (!(parent instanceof ViewGroup)) {
                    break;
                } else if (parent == this) {
                    isChild = true;
                    break;
                } else {
                    parent = parent.getParent();
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
        if (nextFocused == null || nextFocused == currentFocused) {
            if (direction == 33 || direction == 1) {
                handled = pageUp();
            } else if (direction == 130 || direction == 2) {
                handled = pageDown();
            }
        } else if (direction == 33) {
            handled = (currentFocused == null || getChildRectInPagerCoordinates(this.mTempRect, nextFocused).top < getChildRectInPagerCoordinates(this.mTempRect, currentFocused).top) ? nextFocused.requestFocus() : pageUp();
        } else if (direction == 130) {
            handled = (currentFocused == null || getChildRectInPagerCoordinates(this.mTempRect, nextFocused).bottom > getChildRectInPagerCoordinates(this.mTempRect, currentFocused).bottom) ? nextFocused.requestFocus() : pageDown();
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

    /* access modifiers changed from: package-private */
    public boolean pageUp() {
        int i = this.mCurItem;
        if (i <= 0) {
            return false;
        }
        setCurrentItem(i - 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageDown() {
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter == null || this.mCurItem >= pagerAdapter.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.mCurItem + 1, true);
        return true;
    }

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

    public void addTouchables(ArrayList<View> views) {
        ItemInfo ii;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0 && (ii = infoForChild(child)) != null && ii.position == this.mCurItem) {
                child.addTouchables(views);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        int end;
        int increment;
        int index;
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

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return generateDefaultLayoutParams();
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return (p instanceof LayoutParams) && super.checkLayoutParams(p);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        MyAccessibilityDelegate() {
        }

        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(ViewPager.class.getName());
            AccessibilityRecordCompat recordCompat = AccessibilityRecordCompat.obtain();
            recordCompat.setScrollable(canScroll());
            if (event.getEventType() == 4096 && AudiMib3VerticalViewPager.this.mAdapter != null) {
                recordCompat.setItemCount(AudiMib3VerticalViewPager.this.mAdapter.getCount());
                recordCompat.setFromIndex(AudiMib3VerticalViewPager.this.mCurItem);
                recordCompat.setToIndex(AudiMib3VerticalViewPager.this.mCurItem);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setClassName(ViewPager.class.getName());
            info.setScrollable(canScroll());
            if (AudiMib3VerticalViewPager.this.internalCanScrollVertically(1)) {
                info.addAction(4096);
            }
            if (AudiMib3VerticalViewPager.this.internalCanScrollVertically(-1)) {
                info.addAction(8192);
            }
        }

        public boolean performAccessibilityAction(View host, int action, Bundle args) {
            if (super.performAccessibilityAction(host, action, args)) {
                return true;
            }
            switch (action) {
                case 4096:
                    if (!AudiMib3VerticalViewPager.this.internalCanScrollVertically(1)) {
                        return false;
                    }
                    AudiMib3VerticalViewPager audiMib3VerticalViewPager = AudiMib3VerticalViewPager.this;
                    audiMib3VerticalViewPager.setCurrentItem(audiMib3VerticalViewPager.mCurItem + 1);
                    return true;
                case 8192:
                    if (!AudiMib3VerticalViewPager.this.internalCanScrollVertically(-1)) {
                        return false;
                    }
                    AudiMib3VerticalViewPager audiMib3VerticalViewPager2 = AudiMib3VerticalViewPager.this;
                    audiMib3VerticalViewPager2.setCurrentItem(audiMib3VerticalViewPager2.mCurItem - 1);
                    return true;
                default:
                    return false;
            }
        }

        private boolean canScroll() {
            return AudiMib3VerticalViewPager.this.mAdapter != null && AudiMib3VerticalViewPager.this.mAdapter.getCount() > 1;
        }
    }

    private class PagerObserver extends DataSetObserver {
        private PagerObserver() {
        }

        public void onChanged() {
            AudiMib3VerticalViewPager.this.dataSetChanged();
        }

        public void onInvalidated() {
            AudiMib3VerticalViewPager.this.dataSetChanged();
        }
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        int childIndex;
        public int gravity;
        float heightFactor = 0.0f;
        public boolean isDecor;
        boolean needsMeasure;
        int position;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, AudiMib3VerticalViewPager.LAYOUT_ATTRS);
            this.gravity = a.getInteger(0, 48);
            a.recycle();
        }
    }

    static class ViewPositionComparator implements Comparator<View> {
        ViewPositionComparator() {
        }

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
