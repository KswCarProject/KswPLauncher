package com.wits.ksw.settings.audi.widget;

import android.annotation.SuppressLint;
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

public class VerticalViewPager extends ViewGroup {
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
            VerticalViewPager.this.setScrollState(0);
            VerticalViewPager.this.populate();
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

    public VerticalViewPager(Context context) {
        super(context);
        initViewPager();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
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
            if (this.mOnPageChangeListener != null) {
                this.mOnPageChangeListener.onPageScrollStateChanged(newState);
            }
        }
    }

    public void setAdapter(PagerAdapter adapter) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
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
        if (this.mAdapter != null) {
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
        if (this.mAdapterChangeListener != null && oldAdapter != adapter) {
            this.mAdapterChangeListener.onAdapterChanged(oldAdapter, adapter);
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
        if (this.mAdapter == null || this.mAdapter.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (always || this.mCurItem != item || this.mItems.size() == 0) {
            boolean dispatchSelected = true;
            if (item < 0) {
                item = 0;
            } else if (item >= this.mAdapter.getCount()) {
                item = this.mAdapter.getCount() - 1;
            }
            int pageLimit = this.mOffscreenPageLimit;
            if (item > this.mCurItem + pageLimit || item < this.mCurItem - pageLimit) {
                for (int i = 0; i < this.mItems.size(); i++) {
                    this.mItems.get(i).scrolling = true;
                }
            }
            if (this.mCurItem == item) {
                dispatchSelected = false;
            }
            if (this.mFirstLayout) {
                this.mCurItem = item;
                if (dispatchSelected && this.mOnPageChangeListener != null) {
                    this.mOnPageChangeListener.onPageSelected(item);
                }
                if (dispatchSelected && this.mInternalPageChangeListener != null) {
                    this.mInternalPageChangeListener.onPageSelected(item);
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
        ItemInfo curInfo = infoForPosition(item);
        int destY = 0;
        if (curInfo != null) {
            destY = (int) (((float) getClientHeight()) * Math.max(this.mFirstOffset, Math.min(curInfo.offset, this.mLastOffset)));
        }
        if (smoothScroll) {
            smoothScrollTo(0, destY, velocity);
            if (dispatchSelected && this.mOnPageChangeListener != null) {
                this.mOnPageChangeListener.onPageSelected(item);
            }
            if (dispatchSelected && this.mInternalPageChangeListener != null) {
                this.mInternalPageChangeListener.onPageSelected(item);
                return;
            }
            return;
        }
        if (dispatchSelected && this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(item);
        }
        if (dispatchSelected && this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageSelected(item);
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
        int i = velocity2;
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
        boolean isUpdating = false;
        int newCurrItem = this.mCurItem;
        boolean needPopulate2 = needPopulate;
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
                    needPopulate2 = true;
                    if (this.mCurItem == ii.position) {
                        newCurrItem = Math.max(0, Math.min(this.mCurItem, adapterCount - 1));
                        needPopulate2 = true;
                    }
                } else if (ii.position != newPos) {
                    if (ii.position == this.mCurItem) {
                        newCurrItem = newPos;
                    }
                    ii.position = newPos;
                    needPopulate2 = true;
                }
            }
            i++;
        }
        if (isUpdating) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (needPopulate2) {
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
        String resName;
        ItemInfo ii;
        int curIndex;
        float f;
        int clientHeight;
        int pageLimit;
        float topHeightNeeded;
        ItemInfo ii2;
        int i = newCurrentItem;
        ItemInfo oldCurInfo = null;
        int focusDirection = 2;
        if (this.mCurItem != i) {
            focusDirection = this.mCurItem < i ? 130 : 33;
            oldCurInfo = infoForPosition(this.mCurItem);
            this.mCurItem = i;
        }
        int focusDirection2 = focusDirection;
        ItemInfo oldCurInfo2 = oldCurInfo;
        if (this.mAdapter == null) {
            sortChildDrawingOrder();
        } else if (this.mPopulatePending) {
            sortChildDrawingOrder();
        } else if (getWindowToken() != null) {
            this.mAdapter.startUpdate((ViewGroup) this);
            int pageLimit2 = this.mOffscreenPageLimit;
            int startPos = Math.max(0, this.mCurItem - pageLimit2);
            int N = this.mAdapter.getCount();
            int endPos = Math.min(N - 1, this.mCurItem + pageLimit2);
            if (N == this.mExpectedAdapterCount) {
                ItemInfo curItem = null;
                int curIndex2 = 0;
                while (true) {
                    if (curIndex2 >= this.mItems.size()) {
                        break;
                    }
                    ItemInfo ii3 = this.mItems.get(curIndex2);
                    if (ii3.position < this.mCurItem) {
                        curIndex2++;
                    } else if (ii3.position == this.mCurItem) {
                        curItem = ii3;
                    }
                }
                if (curItem == null && N > 0) {
                    curItem = addNewItem(this.mCurItem, curIndex2);
                }
                if (curItem != null) {
                    float extraHeightTop = 0.0f;
                    int itemIndex = curIndex2 - 1;
                    ItemInfo ii4 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                    int clientHeight2 = getClientHeight();
                    if (clientHeight2 <= 0) {
                        curIndex = curIndex2;
                        f = 0.0f;
                    } else {
                        curIndex = curIndex2;
                        f = (((float) getPaddingLeft()) / ((float) clientHeight2)) + (2.0f - curItem.heightFactor);
                    }
                    float topHeightNeeded2 = f;
                    int pos = this.mCurItem - 1;
                    int curIndex3 = curIndex;
                    while (true) {
                        if (pos < 0) {
                            break;
                        }
                        if (extraHeightTop < topHeightNeeded2 || pos >= startPos) {
                            topHeightNeeded = topHeightNeeded2;
                            if (ii4 == null || pos != ii4.position) {
                                extraHeightTop += addNewItem(pos, itemIndex + 1).heightFactor;
                                curIndex3++;
                                ii2 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                            } else {
                                extraHeightTop += ii4.heightFactor;
                                itemIndex--;
                                ii2 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                            }
                        } else if (ii4 == null) {
                            float f2 = topHeightNeeded2;
                            break;
                        } else {
                            topHeightNeeded = topHeightNeeded2;
                            if (pos == ii4.position && !ii4.scrolling) {
                                this.mItems.remove(itemIndex);
                                this.mAdapter.destroyItem((ViewGroup) this, pos, ii4.object);
                                itemIndex--;
                                curIndex3--;
                                ii2 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                            }
                            pos--;
                            topHeightNeeded2 = topHeightNeeded;
                            int i2 = newCurrentItem;
                        }
                        ii4 = ii2;
                        pos--;
                        topHeightNeeded2 = topHeightNeeded;
                        int i22 = newCurrentItem;
                    }
                    float extraHeightBottom = curItem.heightFactor;
                    int itemIndex2 = curIndex3 + 1;
                    if (extraHeightBottom < 2.0f) {
                        ItemInfo ii5 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                        float bottomHeightNeeded = clientHeight2 <= 0 ? 0.0f : (((float) getPaddingRight()) / ((float) clientHeight2)) + 2.0f;
                        int pos2 = this.mCurItem + 1;
                        while (true) {
                            if (pos2 >= N) {
                                int i3 = clientHeight2;
                                break;
                            }
                            if (extraHeightBottom < bottomHeightNeeded || pos2 <= endPos) {
                                pageLimit = pageLimit2;
                                clientHeight = clientHeight2;
                                if (ii5 == null || pos2 != ii5.position) {
                                    ItemInfo ii6 = addNewItem(pos2, itemIndex2);
                                    itemIndex2++;
                                    extraHeightBottom += ii6.heightFactor;
                                    ii5 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                                } else {
                                    extraHeightBottom += ii5.heightFactor;
                                    itemIndex2++;
                                    ii5 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                                }
                            } else if (ii5 == null) {
                                int i4 = pageLimit2;
                                int i5 = clientHeight2;
                                break;
                            } else {
                                pageLimit = pageLimit2;
                                if (pos2 != ii5.position || ii5.scrolling) {
                                    clientHeight = clientHeight2;
                                } else {
                                    this.mItems.remove(itemIndex2);
                                    clientHeight = clientHeight2;
                                    this.mAdapter.destroyItem((ViewGroup) this, pos2, ii5.object);
                                    ii5 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                                }
                            }
                            pos2++;
                            pageLimit2 = pageLimit;
                            clientHeight2 = clientHeight;
                        }
                        ItemInfo itemInfo = ii5;
                    } else {
                        int i6 = clientHeight2;
                    }
                    calculatePageOffsets(curItem, curIndex3, oldCurInfo2);
                    int i7 = curIndex3;
                } else {
                    int i8 = curIndex2;
                    int i9 = pageLimit2;
                }
                this.mAdapter.setPrimaryItem((ViewGroup) this, this.mCurItem, curItem != null ? curItem.object : null);
                this.mAdapter.finishUpdate((ViewGroup) this);
                int childCount = getChildCount();
                for (int i10 = 0; i10 < childCount; i10++) {
                    View child = getChildAt(i10);
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    lp.childIndex = i10;
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
                    ItemInfo ii7 = currentFocused != null ? infoForAnyChild(currentFocused) : null;
                    if (ii7 == null || ii7.position != this.mCurItem) {
                        int i11 = 0;
                        while (true) {
                            int i12 = i11;
                            if (i12 < getChildCount()) {
                                View child2 = getChildAt(i12);
                                ItemInfo ii8 = infoForChild(child2);
                                if (ii8 == null || ii8.position != this.mCurItem || !child2.requestFocus(focusDirection2)) {
                                    i11 = i12 + 1;
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    }
                }
            } else {
                try {
                    resName = getResources().getResourceName(getId());
                } catch (Resources.NotFoundException e) {
                    resName = Integer.toHexString(getId());
                }
                throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.mExpectedAdapterCount + ", found: " + N + " Pager id: " + resName + " Pager class: " + getClass() + " Problematic adapter: " + this.mAdapter.getClass());
            }
        }
    }

    private void sortChildDrawingOrder() {
        if (this.mDrawingOrder != 0) {
            if (this.mDrawingOrderedChildren == null) {
                this.mDrawingOrderedChildren = new ArrayList<>();
            } else {
                this.mDrawingOrderedChildren.clear();
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
        if (this.mAdapter != null) {
            ss.adapterState = this.mAdapter.saveState();
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
        if (this.mAdapter != null) {
            this.mAdapter.restoreState(ss.adapterState, ss.loader);
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
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00cd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r23, int r24) {
        /*
            r22 = this;
            r0 = r22
            r1 = 0
            r2 = r23
            int r3 = getDefaultSize(r1, r2)
            r4 = r24
            int r5 = getDefaultSize(r1, r4)
            r0.setMeasuredDimension(r3, r5)
            int r3 = r22.getMeasuredHeight()
            int r5 = r3 / 10
            int r6 = r0.mDefaultGutterSize
            int r6 = java.lang.Math.min(r5, r6)
            r0.mGutterSize = r6
            int r6 = r22.getMeasuredWidth()
            int r7 = r22.getPaddingLeft()
            int r6 = r6 - r7
            int r7 = r22.getPaddingRight()
            int r6 = r6 - r7
            int r7 = r22.getPaddingTop()
            int r7 = r3 - r7
            int r8 = r22.getPaddingBottom()
            int r7 = r7 - r8
            int r8 = r22.getChildCount()
            r9 = r7
            r7 = r6
            r6 = r1
        L_0x0040:
            r10 = 8
            if (r6 >= r8) goto L_0x00e7
            android.view.View r12 = r0.getChildAt(r6)
            int r13 = r12.getVisibility()
            if (r13 == r10) goto L_0x00d6
            android.view.ViewGroup$LayoutParams r10 = r12.getLayoutParams()
            com.wits.ksw.settings.audi.widget.VerticalViewPager$LayoutParams r10 = (com.wits.ksw.settings.audi.widget.VerticalViewPager.LayoutParams) r10
            if (r10 == 0) goto L_0x00d6
            boolean r13 = r10.isDecor
            if (r13 == 0) goto L_0x00d6
            int r13 = r10.gravity
            r13 = r13 & 7
            int r14 = r10.gravity
            r14 = r14 & 112(0x70, float:1.57E-43)
            r15 = -2147483648(0xffffffff80000000, float:-0.0)
            r16 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = 48
            if (r14 == r1) goto L_0x0071
            r1 = 80
            if (r14 != r1) goto L_0x006f
            goto L_0x0071
        L_0x006f:
            r1 = 0
            goto L_0x0072
        L_0x0071:
            r1 = 1
        L_0x0072:
            r11 = 3
            if (r13 == r11) goto L_0x007c
            r11 = 5
            if (r13 != r11) goto L_0x0079
            goto L_0x007c
        L_0x0079:
            r17 = 0
            goto L_0x007e
        L_0x007c:
            r17 = 1
        L_0x007e:
            r11 = r17
            if (r1 == 0) goto L_0x0085
            r15 = 1073741824(0x40000000, float:2.0)
            goto L_0x0089
        L_0x0085:
            if (r11 == 0) goto L_0x0089
            r16 = 1073741824(0x40000000, float:2.0)
        L_0x0089:
            r17 = r7
            r18 = r9
            int r2 = r10.width
            r19 = r3
            r3 = -2
            if (r2 == r3) goto L_0x009e
            r15 = 1073741824(0x40000000, float:2.0)
            int r2 = r10.width
            r3 = -1
            if (r2 == r3) goto L_0x009e
            int r2 = r10.width
            goto L_0x00a0
        L_0x009e:
            r2 = r17
        L_0x00a0:
            int r3 = r10.height
            r4 = -2
            if (r3 == r4) goto L_0x00b1
            r16 = 1073741824(0x40000000, float:2.0)
            int r3 = r10.height
            r4 = -1
            if (r3 == r4) goto L_0x00b1
            int r3 = r10.height
            r4 = r16
            goto L_0x00b5
        L_0x00b1:
            r4 = r16
            r3 = r18
        L_0x00b5:
            r20 = r5
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r15)
            r21 = r2
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r4)
            r12.measure(r5, r2)
            if (r1 == 0) goto L_0x00cd
            int r16 = r12.getMeasuredHeight()
            int r9 = r9 - r16
            goto L_0x00da
        L_0x00cd:
            if (r11 == 0) goto L_0x00da
            int r16 = r12.getMeasuredWidth()
            int r7 = r7 - r16
            goto L_0x00da
        L_0x00d6:
            r19 = r3
            r20 = r5
        L_0x00da:
            int r6 = r6 + 1
            r3 = r19
            r5 = r20
            r1 = 0
            r2 = r23
            r4 = r24
            goto L_0x0040
        L_0x00e7:
            r19 = r3
            r20 = r5
            r1 = 1073741824(0x40000000, float:2.0)
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r1)
            r0.mChildWidthMeasureSpec = r2
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r1)
            r0.mChildHeightMeasureSpec = r2
            r2 = 1
            r0.mInLayout = r2
            r22.populate()
            r2 = 0
            r0.mInLayout = r2
            int r3 = r22.getChildCount()
        L_0x0107:
            if (r2 >= r3) goto L_0x0130
            android.view.View r4 = r0.getChildAt(r2)
            int r5 = r4.getVisibility()
            if (r5 == r10) goto L_0x012d
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            com.wits.ksw.settings.audi.widget.VerticalViewPager$LayoutParams r5 = (com.wits.ksw.settings.audi.widget.VerticalViewPager.LayoutParams) r5
            if (r5 == 0) goto L_0x011f
            boolean r6 = r5.isDecor
            if (r6 != 0) goto L_0x012d
        L_0x011f:
            float r6 = (float) r9
            float r8 = r5.heightFactor
            float r6 = r6 * r8
            int r6 = (int) r6
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r1)
            int r8 = r0.mChildWidthMeasureSpec
            r4.measure(r8, r6)
        L_0x012d:
            int r2 = r2 + 1
            goto L_0x0107
        L_0x0130:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.settings.audi.widget.VerticalViewPager.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (h != oldh) {
            recomputeScrollPosition(h, oldh, this.mPageMargin, this.mPageMargin);
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
            int newDuration = this.mScroller.getDuration() - this.mScroller.timePassed();
            ItemInfo targetInfo = infoForPosition(this.mCurItem);
            ItemInfo itemInfo = targetInfo;
            this.mScroller.startScroll(0, newOffsetPixels, 0, (int) (targetInfo.offset * ((float) i)), newDuration);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        boolean z;
        int childHeight;
        int height;
        int count;
        int childLeft;
        int childTop;
        int count2 = getChildCount();
        int width = r - l;
        int height2 = b - t;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollY = getScrollY();
        int decorCount = 0;
        int paddingRight2 = paddingRight;
        int paddingLeft2 = paddingLeft;
        for (int i = 0; i < count2; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.isDecor != 0) {
                    int hgrav = lp.gravity & 7;
                    int vgrav = lp.gravity & 112;
                    LayoutParams layoutParams = lp;
                    if (hgrav == 1) {
                        childLeft = Math.max((width - child.getMeasuredWidth()) / 2, paddingLeft2);
                    } else if (hgrav == 3) {
                        childLeft = paddingLeft2;
                        paddingLeft2 += child.getMeasuredWidth();
                    } else if (hgrav != 5) {
                        childLeft = paddingLeft2;
                    } else {
                        childLeft = (width - paddingRight2) - child.getMeasuredWidth();
                        paddingRight2 += child.getMeasuredWidth();
                    }
                    int i2 = hgrav;
                    if (vgrav == 16) {
                        childTop = Math.max((height2 - child.getMeasuredHeight()) / 2, paddingTop);
                    } else if (vgrav == 48) {
                        childTop = paddingTop;
                        paddingTop += child.getMeasuredHeight();
                    } else if (vgrav != 80) {
                        childTop = paddingTop;
                    } else {
                        childTop = (height2 - paddingBottom) - child.getMeasuredHeight();
                        paddingBottom += child.getMeasuredHeight();
                    }
                    int childTop2 = childTop + scrollY;
                    int i3 = vgrav;
                    child.layout(childLeft, childTop2, childLeft + child.getMeasuredWidth(), childTop2 + child.getMeasuredHeight());
                    decorCount++;
                }
            }
        }
        int childHeight2 = (height2 - paddingTop) - paddingBottom;
        int i4 = 0;
        while (i4 < count2) {
            View child2 = getChildAt(i4);
            if (child2.getVisibility() != 8) {
                LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                if (!lp2.isDecor) {
                    ItemInfo infoForChild = infoForChild(child2);
                    ItemInfo ii = infoForChild;
                    if (infoForChild != null) {
                        count = count2;
                        ItemInfo ii2 = ii;
                        int toff = (int) (((float) childHeight2) * ii2.offset);
                        int childLeft2 = paddingLeft2;
                        height = height2;
                        int childTop3 = paddingTop + toff;
                        int i5 = toff;
                        if (lp2.needsMeasure != 0) {
                            lp2.needsMeasure = false;
                            ItemInfo itemInfo = ii2;
                            childHeight = childHeight2;
                            child2.measure(View.MeasureSpec.makeMeasureSpec((width - paddingLeft2) - paddingRight2, 1073741824), View.MeasureSpec.makeMeasureSpec((int) (((float) childHeight2) * lp2.heightFactor), 1073741824));
                        } else {
                            childHeight = childHeight2;
                            ItemInfo itemInfo2 = ii2;
                        }
                        child2.layout(childLeft2, childTop3, child2.getMeasuredWidth() + childLeft2, child2.getMeasuredHeight() + childTop3);
                        i4++;
                        count2 = count;
                        height2 = height;
                        childHeight2 = childHeight;
                    }
                }
            }
            count = count2;
            childHeight = childHeight2;
            height = height2;
            i4++;
            count2 = count;
            height2 = height;
            childHeight2 = childHeight;
        }
        int i6 = childHeight2;
        int i7 = height2;
        this.mLeftPageBounds = paddingLeft2;
        this.mRightPageBounds = width - paddingRight2;
        this.mDecorChildCount = decorCount;
        if (this.mFirstLayout) {
            z = false;
            scrollToItem(this.mCurItem, false, 0, false);
        } else {
            z = false;
        }
        this.mFirstLayout = z;
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
        float marginOffset = ((float) this.mPageMargin) / ((float) height);
        int currentPage = ii.position;
        float pageOffset = ((((float) ypos) / ((float) height)) - ii.offset) / (ii.heightFactor + marginOffset);
        this.mCalledSuper = false;
        onPageScrolled(currentPage, pageOffset, (int) (((float) (this.mPageMargin + height)) * pageOffset));
        if (this.mCalledSuper) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    /* access modifiers changed from: protected */
    public void onPageScrolled(int position, float offset, int offsetPixels) {
        int childTop;
        int i = position;
        float f = offset;
        int i2 = offsetPixels;
        if (this.mDecorChildCount > 0) {
            int scrollY = getScrollY();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int height = getHeight();
            int childCount = getChildCount();
            int paddingBottom2 = paddingBottom;
            int paddingTop2 = paddingTop;
            for (int i3 = 0; i3 < childCount; i3++) {
                View child = getChildAt(i3);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.isDecor) {
                    int vgrav = lp.gravity & 112;
                    if (vgrav == 16) {
                        childTop = Math.max((height - child.getMeasuredHeight()) / 2, paddingTop2);
                    } else if (vgrav == 48) {
                        childTop = paddingTop2;
                        paddingTop2 += child.getHeight();
                    } else if (vgrav != 80) {
                        childTop = paddingTop2;
                    } else {
                        childTop = (height - paddingBottom2) - child.getMeasuredHeight();
                        paddingBottom2 += child.getMeasuredHeight();
                    }
                    int childOffset = (childTop + scrollY) - child.getTop();
                    if (childOffset != 0) {
                        child.offsetTopAndBottom(childOffset);
                    }
                }
            }
        }
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(i, f, i2);
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageScrolled(i, f, i2);
        }
        if (this.mPageTransformer != null) {
            int scrollY2 = getScrollY();
            int childCount2 = getChildCount();
            for (int i4 = 0; i4 < childCount2; i4++) {
                View child2 = getChildAt(i4);
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
        boolean needPopulate2 = needPopulate;
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo ii = this.mItems.get(i);
            if (ii.scrolling) {
                needPopulate2 = true;
                ii.scrolling = false;
            }
        }
        if (!needPopulate2) {
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
            if (this.mVelocityTracker == null) {
                return false;
            }
            this.mVelocityTracker.recycle();
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
        if (action == 0) {
            float x2 = ev.getX();
            this.mInitialMotionX = x2;
            this.mLastMotionX = x2;
            float y = ev.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
            this.mIsUnableToDrag = false;
            this.mScroller.computeScrollOffset();
            if (this.mScrollState != 2 || Math.abs(this.mScroller.getFinalY() - this.mScroller.getCurrY()) <= this.mCloseEnough) {
                completeScroll(false);
                this.mIsBeingDragged = false;
            } else {
                this.mScroller.abortAnimation();
                this.mPopulatePending = false;
                populate();
                this.mIsBeingDragged = true;
                requestParentDisallowInterceptTouchEvent(true);
                setScrollState(1);
            }
        } else if (action == 2) {
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
                if (yDiff > ((float) this.mTouchSlop) && 0.5f * yDiff > xDiff) {
                    this.mIsBeingDragged = true;
                    requestParentDisallowInterceptTouchEvent(true);
                    setScrollState(1);
                    this.mLastMotionY = dy > 0.0f ? this.mInitialMotionY + ((float) this.mTouchSlop) : this.mInitialMotionY - ((float) this.mTouchSlop);
                    this.mLastMotionX = x;
                    setScrollingCacheEnabled(true);
                } else if (xDiff > ((float) this.mTouchSlop)) {
                    this.mIsUnableToDrag = true;
                }
                if (this.mIsBeingDragged && performDrag(y2)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }
        } else if (action == 6) {
            onSecondaryPointerUp(ev);
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        return this.mIsBeingDragged;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            boolean r2 = r0.mFakeDragging
            r3 = 1
            if (r2 == 0) goto L_0x000a
            return r3
        L_0x000a:
            int r2 = r18.getAction()
            r4 = 0
            if (r2 != 0) goto L_0x0018
            int r2 = r18.getEdgeFlags()
            if (r2 == 0) goto L_0x0018
            return r4
        L_0x0018:
            android.support.v4.view.PagerAdapter r2 = r0.mAdapter
            if (r2 == 0) goto L_0x0179
            android.support.v4.view.PagerAdapter r2 = r0.mAdapter
            int r2 = r2.getCount()
            if (r2 != 0) goto L_0x0026
            goto L_0x0179
        L_0x0026:
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            if (r2 != 0) goto L_0x0030
            android.view.VelocityTracker r2 = android.view.VelocityTracker.obtain()
            r0.mVelocityTracker = r2
        L_0x0030:
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            r2.addMovement(r1)
            int r2 = r18.getAction()
            r5 = 0
            r6 = r2 & 255(0xff, float:3.57E-43)
            r7 = -1
            switch(r6) {
                case 0: goto L_0x014f;
                case 1: goto L_0x00f4;
                case 2: goto L_0x0085;
                case 3: goto L_0x0065;
                case 4: goto L_0x0040;
                case 5: goto L_0x0054;
                case 6: goto L_0x0044;
                default: goto L_0x0040;
            }
        L_0x0040:
            r16 = r2
            goto L_0x0172
        L_0x0044:
            r17.onSecondaryPointerUp(r18)
            int r4 = r0.mActivePointerId
            int r4 = android.support.v4.view.MotionEventCompat.findPointerIndex(r1, r4)
            float r4 = android.support.v4.view.MotionEventCompat.getY(r1, r4)
            r0.mLastMotionY = r4
            goto L_0x0081
        L_0x0054:
            int r4 = android.support.v4.view.MotionEventCompat.getActionIndex(r18)
            float r6 = android.support.v4.view.MotionEventCompat.getY(r1, r4)
            r0.mLastMotionY = r6
            int r7 = android.support.v4.view.MotionEventCompat.getPointerId(r1, r4)
            r0.mActivePointerId = r7
            goto L_0x0081
        L_0x0065:
            boolean r6 = r0.mIsBeingDragged
            if (r6 == 0) goto L_0x0081
            int r6 = r0.mCurItem
            r0.scrollToItem(r6, r3, r4, r4)
            r0.mActivePointerId = r7
            r17.endDrag()
            android.support.v4.widget.EdgeEffectCompat r4 = r0.mTopEdge
            boolean r4 = r4.onRelease()
            android.support.v4.widget.EdgeEffectCompat r6 = r0.mBottomEdge
            boolean r6 = r6.onRelease()
            r5 = r4 | r6
        L_0x0081:
            r16 = r2
            goto L_0x0172
        L_0x0085:
            boolean r4 = r0.mIsBeingDragged
            if (r4 != 0) goto L_0x00e0
            int r4 = r0.mActivePointerId
            int r4 = android.support.v4.view.MotionEventCompat.findPointerIndex(r1, r4)
            float r6 = android.support.v4.view.MotionEventCompat.getY(r1, r4)
            float r7 = r0.mLastMotionY
            float r7 = r6 - r7
            float r7 = java.lang.Math.abs(r7)
            float r8 = android.support.v4.view.MotionEventCompat.getX(r1, r4)
            float r9 = r0.mLastMotionX
            float r9 = r8 - r9
            float r9 = java.lang.Math.abs(r9)
            int r10 = r0.mTouchSlop
            float r10 = (float) r10
            int r10 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r10 <= 0) goto L_0x00e0
            int r10 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r10 <= 0) goto L_0x00e0
            r0.mIsBeingDragged = r3
            r0.requestParentDisallowInterceptTouchEvent(r3)
            float r10 = r0.mInitialMotionY
            float r10 = r6 - r10
            r11 = 0
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 <= 0) goto L_0x00c7
            float r10 = r0.mInitialMotionY
            int r11 = r0.mTouchSlop
            float r11 = (float) r11
            float r10 = r10 + r11
            goto L_0x00cd
        L_0x00c7:
            float r10 = r0.mInitialMotionY
            int r11 = r0.mTouchSlop
            float r11 = (float) r11
            float r10 = r10 - r11
        L_0x00cd:
            r0.mLastMotionY = r10
            r0.mLastMotionX = r8
            r0.setScrollState(r3)
            r0.setScrollingCacheEnabled(r3)
            android.view.ViewParent r10 = r17.getParent()
            if (r10 == 0) goto L_0x00e0
            r10.requestDisallowInterceptTouchEvent(r3)
        L_0x00e0:
            boolean r4 = r0.mIsBeingDragged
            if (r4 == 0) goto L_0x0081
            int r4 = r0.mActivePointerId
            int r4 = android.support.v4.view.MotionEventCompat.findPointerIndex(r1, r4)
            float r6 = android.support.v4.view.MotionEventCompat.getY(r1, r4)
            boolean r7 = r0.performDrag(r6)
            r5 = r5 | r7
            goto L_0x0081
        L_0x00f4:
            boolean r4 = r0.mIsBeingDragged
            if (r4 == 0) goto L_0x0040
            android.view.VelocityTracker r4 = r0.mVelocityTracker
            r6 = 1000(0x3e8, float:1.401E-42)
            int r8 = r0.mMaximumVelocity
            float r8 = (float) r8
            r4.computeCurrentVelocity(r6, r8)
            int r6 = r0.mActivePointerId
            float r6 = android.support.v4.view.VelocityTrackerCompat.getYVelocity(r4, r6)
            int r6 = (int) r6
            r0.mPopulatePending = r3
            int r8 = r17.getClientHeight()
            int r9 = r17.getScrollY()
            com.wits.ksw.settings.audi.widget.VerticalViewPager$ItemInfo r10 = r17.infoForCurrentScrollPosition()
            int r11 = r10.position
            float r12 = (float) r9
            float r13 = (float) r8
            float r12 = r12 / r13
            float r13 = r10.offset
            float r12 = r12 - r13
            float r13 = r10.heightFactor
            float r12 = r12 / r13
            int r13 = r0.mActivePointerId
            int r13 = android.support.v4.view.MotionEventCompat.findPointerIndex(r1, r13)
            float r14 = android.support.v4.view.MotionEventCompat.getY(r1, r13)
            float r15 = r0.mInitialMotionY
            float r15 = r14 - r15
            int r15 = (int) r15
            int r7 = r0.determineTargetPage(r11, r12, r6, r15)
            r0.setCurrentItemInternal(r7, r3, r3, r6)
            r3 = -1
            r0.mActivePointerId = r3
            r17.endDrag()
            android.support.v4.widget.EdgeEffectCompat r3 = r0.mTopEdge
            boolean r3 = r3.onRelease()
            r16 = r2
            android.support.v4.widget.EdgeEffectCompat r2 = r0.mBottomEdge
            boolean r2 = r2.onRelease()
            r5 = r3 | r2
            goto L_0x0172
        L_0x014f:
            r16 = r2
            android.widget.Scroller r2 = r0.mScroller
            r2.abortAnimation()
            r0.mPopulatePending = r4
            r17.populate()
            float r2 = r18.getX()
            r0.mInitialMotionX = r2
            r0.mLastMotionX = r2
            float r2 = r18.getY()
            r0.mInitialMotionY = r2
            r0.mLastMotionY = r2
            int r2 = android.support.v4.view.MotionEventCompat.getPointerId(r1, r4)
            r0.mActivePointerId = r2
        L_0x0172:
            if (r5 == 0) goto L_0x0177
            android.support.v4.view.ViewCompat.postInvalidateOnAnimation(r17)
        L_0x0177:
            r2 = 1
            return r2
        L_0x0179:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.settings.audi.widget.VerticalViewPager.onTouchEvent(android.view.MotionEvent):boolean");
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
        ItemInfo lastItem = this.mItems.get(this.mItems.size() - 1);
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
        if (this.mItems.size() > 0) {
            return Math.max(this.mItems.get(0).position, Math.min(targetPage, this.mItems.get(this.mItems.size() - 1).position));
        }
        return targetPage;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        boolean needsInvalidate = false;
        int overScrollMode = ViewCompat.getOverScrollMode(this);
        if (overScrollMode == 0 || (overScrollMode == 1 && this.mAdapter != null && this.mAdapter.getCount() > 1)) {
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
        if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
            int scrollY = getScrollY();
            int height = getHeight();
            float marginOffset2 = ((float) this.mPageMargin) / ((float) height);
            ItemInfo ii = this.mItems.get(0);
            float offset = ii.offset;
            int itemCount = this.mItems.size();
            int firstPos = ii.position;
            int lastPos = this.mItems.get(itemCount - 1).position;
            float offset2 = offset;
            int itemIndex = 0;
            int pos = firstPos;
            while (pos < lastPos) {
                while (pos > ii.position && itemIndex < itemCount) {
                    itemIndex++;
                    ii = this.mItems.get(itemIndex);
                }
                if (pos == ii.position) {
                    heightFactor = (ii.offset + ii.heightFactor) * ((float) height);
                    offset2 = ii.offset + ii.heightFactor + marginOffset2;
                } else {
                    float heightFactor2 = this.mAdapter.getPageWidth(pos);
                    float drawAt = (offset2 + heightFactor2) * ((float) height);
                    offset2 += heightFactor2 + marginOffset2;
                    heightFactor = drawAt;
                }
                if (((float) this.mPageMargin) + heightFactor > ((float) scrollY)) {
                    marginOffset = marginOffset2;
                    this.mMarginDrawable.setBounds(this.mLeftPageBounds, (int) heightFactor, this.mRightPageBounds, (int) (((float) this.mPageMargin) + heightFactor + 0.5f));
                    this.mMarginDrawable.draw(canvas);
                } else {
                    Canvas canvas2 = canvas;
                    marginOffset = marginOffset2;
                }
                if (heightFactor <= ((float) (scrollY + height))) {
                    pos++;
                    marginOffset2 = marginOffset;
                } else {
                    return;
                }
            }
        }
        Canvas canvas3 = canvas;
    }

    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged) {
            return false;
        }
        this.mFakeDragging = true;
        setScrollState(1);
        this.mLastMotionY = 0.0f;
        this.mInitialMotionY = 0.0f;
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            this.mVelocityTracker.clear();
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
            ItemInfo lastItem = this.mItems.get(this.mItems.size() - 1);
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
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.clear();
            }
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
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
                    sb.append(" => ");
                    sb.append(parent2.getClass().getSimpleName());
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
        if (this.mCurItem <= 0) {
            return false;
        }
        setCurrentItem(this.mCurItem - 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageDown() {
        if (this.mAdapter == null || this.mCurItem >= this.mAdapter.getCount() - 1) {
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

    @SuppressLint({"WrongConstant"})
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

        @SuppressLint({"WrongConstant"})
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

        public boolean performAccessibilityAction(View host, int action, Bundle args) {
            if (super.performAccessibilityAction(host, action, args)) {
                return true;
            }
            if (action != 4096) {
                if (action != 8192 || !VerticalViewPager.this.internalCanScrollVertically(-1)) {
                    return false;
                }
                VerticalViewPager.this.setCurrentItem(VerticalViewPager.this.mCurItem - 1);
                return true;
            } else if (!VerticalViewPager.this.internalCanScrollVertically(1)) {
                return false;
            } else {
                VerticalViewPager.this.setCurrentItem(VerticalViewPager.this.mCurItem + 1);
                return true;
            }
        }

        private boolean canScroll() {
            return VerticalViewPager.this.mAdapter != null && VerticalViewPager.this.mAdapter.getCount() > 1;
        }
    }

    private class PagerObserver extends DataSetObserver {
        private PagerObserver() {
        }

        public void onChanged() {
            VerticalViewPager.this.dataSetChanged();
        }

        public void onInvalidated() {
            VerticalViewPager.this.dataSetChanged();
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
            TypedArray a = context.obtainStyledAttributes(attrs, VerticalViewPager.LAYOUT_ATTRS);
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
