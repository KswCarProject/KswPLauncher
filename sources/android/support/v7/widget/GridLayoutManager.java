package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;

public class GridLayoutManager extends LinearLayoutManager {
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final String TAG = "GridLayoutManager";
    int[] mCachedBorders;
    final Rect mDecorInsets = new Rect();
    boolean mPendingSpanCountChange = false;
    final SparseIntArray mPreLayoutSpanIndexCache = new SparseIntArray();
    final SparseIntArray mPreLayoutSpanSizeCache = new SparseIntArray();
    View[] mSet;
    int mSpanCount = -1;
    SpanSizeLookup mSpanSizeLookup = new DefaultSpanSizeLookup();

    public GridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setSpanCount(getProperties(context, attrs, defStyleAttr, defStyleRes).spanCount);
    }

    public GridLayoutManager(Context context, int spanCount) {
        super(context);
        setSpanCount(spanCount);
    }

    public GridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        setSpanCount(spanCount);
    }

    public void setStackFromEnd(boolean stackFromEnd) {
        if (!stackFromEnd) {
            super.setStackFromEnd(false);
            return;
        }
        throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
    }

    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View host, AccessibilityNodeInfoCompat info) {
        ViewGroup.LayoutParams lp = host.getLayoutParams();
        if (!(lp instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(host, info);
            return;
        }
        LayoutParams glp = (LayoutParams) lp;
        int spanGroupIndex = getSpanGroupIndex(recycler, state, glp.getViewLayoutPosition());
        if (this.mOrientation == 0) {
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(glp.getSpanIndex(), glp.getSpanSize(), spanGroupIndex, 1, this.mSpanCount > 1 && glp.getSpanSize() == this.mSpanCount, false));
        } else {
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanGroupIndex, 1, glp.getSpanIndex(), glp.getSpanSize(), this.mSpanCount > 1 && glp.getSpanSize() == this.mSpanCount, false));
        }
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) {
            cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler, state);
        clearPreLayoutSpanMappingCache();
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSpanCountChange = false;
    }

    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }

    private void cachePreLayoutSpanMapping() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LayoutParams lp = (LayoutParams) getChildAt(i).getLayoutParams();
            int viewPosition = lp.getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(viewPosition, lp.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(viewPosition, lp.getSpanIndex());
        }
    }

    public void onItemsAdded(RecyclerView recyclerView, int positionStart, int itemCount) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsRemoved(RecyclerView recyclerView, int positionStart, int itemCount) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsUpdated(RecyclerView recyclerView, int positionStart, int itemCount, Object payload) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsMoved(RecyclerView recyclerView, int from, int to, int itemCount) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -1);
        }
        return new LayoutParams(-1, -2);
    }

    public RecyclerView.LayoutParams generateLayoutParams(Context c, AttributeSet attrs) {
        return new LayoutParams(c, attrs);
    }

    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) lp);
        }
        return new LayoutParams(lp);
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
        return lp instanceof LayoutParams;
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }

    private void updateMeasurements() {
        int totalSpace;
        if (getOrientation() == 1) {
            totalSpace = (getWidth() - getPaddingRight()) - getPaddingLeft();
        } else {
            totalSpace = (getHeight() - getPaddingBottom()) - getPaddingTop();
        }
        calculateItemBorders(totalSpace);
    }

    public void setMeasuredDimension(Rect childrenBounds, int wSpec, int hSpec) {
        int height;
        int width;
        if (this.mCachedBorders == null) {
            super.setMeasuredDimension(childrenBounds, wSpec, hSpec);
        }
        int horizontalPadding = getPaddingLeft() + getPaddingRight();
        int verticalPadding = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            int chooseSize = chooseSize(hSpec, childrenBounds.height() + verticalPadding, getMinimumHeight());
            height = chooseSize(wSpec, this.mCachedBorders[this.mCachedBorders.length - 1] + horizontalPadding, getMinimumWidth());
            width = chooseSize;
        } else {
            height = chooseSize(wSpec, childrenBounds.width() + horizontalPadding, getMinimumWidth());
            width = chooseSize(hSpec, this.mCachedBorders[this.mCachedBorders.length - 1] + verticalPadding, getMinimumHeight());
        }
        setMeasuredDimension(height, width);
    }

    private void calculateItemBorders(int totalSpace) {
        this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, totalSpace);
    }

    static int[] calculateItemBorders(int[] cachedBorders, int spanCount, int totalSpace) {
        if (!(cachedBorders != null && cachedBorders.length == spanCount + 1 && cachedBorders[cachedBorders.length - 1] == totalSpace)) {
            cachedBorders = new int[(spanCount + 1)];
        }
        cachedBorders[0] = 0;
        int sizePerSpan = totalSpace / spanCount;
        int sizePerSpanRemainder = totalSpace % spanCount;
        int consumedPixels = 0;
        int additionalSize = 0;
        for (int i = 1; i <= spanCount; i++) {
            int itemSize = sizePerSpan;
            additionalSize += sizePerSpanRemainder;
            if (additionalSize > 0 && spanCount - additionalSize < sizePerSpanRemainder) {
                itemSize++;
                additionalSize -= spanCount;
            }
            consumedPixels += itemSize;
            cachedBorders[i] = consumedPixels;
        }
        return cachedBorders;
    }

    /* access modifiers changed from: package-private */
    public int getSpaceForSpanRange(int startSpan, int spanSize) {
        if (this.mOrientation != 1 || !isLayoutRTL()) {
            return this.mCachedBorders[startSpan + spanSize] - this.mCachedBorders[startSpan];
        }
        return this.mCachedBorders[this.mSpanCount - startSpan] - this.mCachedBorders[(this.mSpanCount - startSpan) - spanSize];
    }

    /* access modifiers changed from: package-private */
    public void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int itemDirection) {
        super.onAnchorReady(recycler, state, anchorInfo, itemDirection);
        updateMeasurements();
        if (state.getItemCount() > 0 && !state.isPreLayout()) {
            ensureAnchorIsInCorrectSpan(recycler, state, anchorInfo, itemDirection);
        }
        ensureViewSet();
    }

    private void ensureViewSet() {
        if (this.mSet == null || this.mSet.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }

    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int itemDirection) {
        boolean layingOutInPrimaryDirection = itemDirection == 1;
        int span = getSpanIndex(recycler, state, anchorInfo.mPosition);
        if (layingOutInPrimaryDirection) {
            while (span > 0 && anchorInfo.mPosition > 0) {
                anchorInfo.mPosition--;
                span = getSpanIndex(recycler, state, anchorInfo.mPosition);
            }
            return;
        }
        int indexLimit = state.getItemCount() - 1;
        int pos = anchorInfo.mPosition;
        int bestSpan = span;
        while (pos < indexLimit) {
            int next = getSpanIndex(recycler, state, pos + 1);
            if (next <= bestSpan) {
                break;
            }
            pos++;
            bestSpan = next;
        }
        anchorInfo.mPosition = pos;
    }

    /* access modifiers changed from: package-private */
    public View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, int start, int end, int itemCount) {
        ensureLayoutState();
        View outOfBoundsMatch = null;
        int boundsStart = this.mOrientationHelper.getStartAfterPadding();
        int boundsEnd = this.mOrientationHelper.getEndAfterPadding();
        int diff = end > start ? 1 : -1;
        View invalidMatch = null;
        for (int i = start; i != end; i += diff) {
            View view = getChildAt(i);
            int position = getPosition(view);
            if (position >= 0 && position < itemCount && getSpanIndex(recycler, state, position) == 0) {
                if (((RecyclerView.LayoutParams) view.getLayoutParams()).isItemRemoved()) {
                    if (invalidMatch == null) {
                        invalidMatch = view;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(view) < boundsEnd && this.mOrientationHelper.getDecoratedEnd(view) >= boundsStart) {
                    return view;
                } else {
                    if (outOfBoundsMatch == null) {
                        outOfBoundsMatch = view;
                    }
                }
            }
        }
        return outOfBoundsMatch != null ? outOfBoundsMatch : invalidMatch;
    }

    private int getSpanGroupIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int viewPosition) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanGroupIndex(viewPosition, this.mSpanCount);
        }
        int adapterPosition = recycler.convertPreLayoutPositionToPostLayout(viewPosition);
        if (adapterPosition != -1) {
            return this.mSpanSizeLookup.getSpanGroupIndex(adapterPosition, this.mSpanCount);
        }
        Log.w(TAG, "Cannot find span size for pre layout position. " + viewPosition);
        return 0;
    }

    private int getSpanIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int pos) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getCachedSpanIndex(pos, this.mSpanCount);
        }
        int cached = this.mPreLayoutSpanIndexCache.get(pos, -1);
        if (cached != -1) {
            return cached;
        }
        int adapterPosition = recycler.convertPreLayoutPositionToPostLayout(pos);
        if (adapterPosition != -1) {
            return this.mSpanSizeLookup.getCachedSpanIndex(adapterPosition, this.mSpanCount);
        }
        Log.w(TAG, "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + pos);
        return 0;
    }

    private int getSpanSize(RecyclerView.Recycler recycler, RecyclerView.State state, int pos) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanSize(pos);
        }
        int cached = this.mPreLayoutSpanSizeCache.get(pos, -1);
        if (cached != -1) {
            return cached;
        }
        int adapterPosition = recycler.convertPreLayoutPositionToPostLayout(pos);
        if (adapterPosition != -1) {
            return this.mSpanSizeLookup.getSpanSize(adapterPosition);
        }
        Log.w(TAG, "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + pos);
        return 1;
    }

    /* access modifiers changed from: package-private */
    public void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int remainingSpan = this.mSpanCount;
        for (int count = 0; count < this.mSpanCount && layoutState.hasMore(state) && remainingSpan > 0; count++) {
            int pos = layoutState.mCurrentPosition;
            layoutPrefetchRegistry.addPosition(pos, Math.max(0, layoutState.mScrollingOffset));
            remainingSpan -= this.mSpanSizeLookup.getSpanSize(pos);
            layoutState.mCurrentPosition += layoutState.mItemDirection;
        }
    }

    /* access modifiers changed from: package-private */
    public void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, LinearLayoutManager.LayoutChunkResult result) {
        int maxSize;
        int right;
        int left;
        int bottom;
        int top;
        int remainingSpan;
        int otherDirSpecMode;
        float maxSizeInOther;
        int hSpec;
        int wSpec;
        View view;
        RecyclerView.Recycler recycler2 = recycler;
        RecyclerView.State state2 = state;
        LinearLayoutManager.LayoutState layoutState2 = layoutState;
        LinearLayoutManager.LayoutChunkResult layoutChunkResult = result;
        int otherDirSpecMode2 = this.mOrientationHelper.getModeInOther();
        boolean z = false;
        boolean flexibleInOtherDir = otherDirSpecMode2 != 1073741824;
        int currentOtherDirSize = getChildCount() > 0 ? this.mCachedBorders[this.mSpanCount] : 0;
        if (flexibleInOtherDir) {
            updateMeasurements();
        }
        boolean layingOutInPrimaryDirection = layoutState2.mItemDirection == 1;
        int remainingSpan2 = this.mSpanCount;
        if (!layingOutInPrimaryDirection) {
            remainingSpan2 = getSpanIndex(recycler2, state2, layoutState2.mCurrentPosition) + getSpanSize(recycler2, state2, layoutState2.mCurrentPosition);
        }
        int count = 0;
        int consumedSpanCount = 0;
        while (count < this.mSpanCount && layoutState2.hasMore(state2) && remainingSpan2 > 0) {
            int pos = layoutState2.mCurrentPosition;
            int spanSize = getSpanSize(recycler2, state2, pos);
            if (spanSize <= this.mSpanCount) {
                remainingSpan2 -= spanSize;
                if (remainingSpan2 < 0 || (view = layoutState2.next(recycler2)) == null) {
                    break;
                }
                consumedSpanCount += spanSize;
                this.mSet[count] = view;
                count++;
            } else {
                throw new IllegalArgumentException("Item at position " + pos + " requires " + spanSize + " spans but GridLayoutManager has only " + this.mSpanCount + " spans.");
            }
        }
        int remainingSpan3 = remainingSpan2;
        if (count == 0) {
            layoutChunkResult.mFinished = true;
            return;
        }
        int count2 = count;
        int currentOtherDirSize2 = currentOtherDirSize;
        assignSpans(recycler, state, count, consumedSpanCount, layingOutInPrimaryDirection);
        int i = 0;
        int maxSize2 = 0;
        float maxSizeInOther2 = 0.0f;
        while (i < count2) {
            View view2 = this.mSet[i];
            if (layoutState2.mScrapList == null) {
                if (layingOutInPrimaryDirection) {
                    addView(view2);
                } else {
                    addView(view2, z);
                }
            } else if (layingOutInPrimaryDirection) {
                addDisappearingView(view2);
            } else {
                addDisappearingView(view2, z ? 1 : 0);
            }
            calculateItemDecorationsForChild(view2, this.mDecorInsets);
            measureChild(view2, otherDirSpecMode2, z);
            int size = this.mOrientationHelper.getDecoratedMeasurement(view2);
            if (size > maxSize2) {
                maxSize2 = size;
            }
            int maxSize3 = maxSize2;
            float otherSize = (((float) this.mOrientationHelper.getDecoratedMeasurementInOther(view2)) * 1.0f) / ((float) ((LayoutParams) view2.getLayoutParams()).mSpanSize);
            if (otherSize > maxSizeInOther2) {
                maxSizeInOther2 = otherSize;
            }
            i++;
            maxSize2 = maxSize3;
            z = false;
        }
        if (flexibleInOtherDir) {
            guessMeasurement(maxSizeInOther2, currentOtherDirSize2);
            int maxSize4 = 0;
            for (int i2 = 0; i2 < count2; i2++) {
                View view3 = this.mSet[i2];
                measureChild(view3, 1073741824, true);
                int size2 = this.mOrientationHelper.getDecoratedMeasurement(view3);
                if (size2 > maxSize4) {
                    maxSize4 = size2;
                }
            }
            maxSize = maxSize4;
        } else {
            maxSize = maxSize2;
        }
        int i3 = 0;
        while (i3 < count2) {
            View view4 = this.mSet[i3];
            if (this.mOrientationHelper.getDecoratedMeasurement(view4) != maxSize) {
                LayoutParams lp = (LayoutParams) view4.getLayoutParams();
                Rect decorInsets = lp.mDecorInsets;
                maxSizeInOther = maxSizeInOther2;
                int verticalInsets = decorInsets.top + decorInsets.bottom + lp.topMargin + lp.bottomMargin;
                int horizontalInsets = decorInsets.left + decorInsets.right + lp.leftMargin + lp.rightMargin;
                Rect rect = decorInsets;
                int totalSpaceInOther = getSpaceForSpanRange(lp.mSpanIndex, lp.mSpanSize);
                otherDirSpecMode = otherDirSpecMode2;
                if (this.mOrientation == 1) {
                    remainingSpan = remainingSpan3;
                    wSpec = getChildMeasureSpec(totalSpaceInOther, 1073741824, horizontalInsets, lp.width, false);
                    hSpec = View.MeasureSpec.makeMeasureSpec(maxSize - verticalInsets, 1073741824);
                    LayoutParams layoutParams = lp;
                } else {
                    remainingSpan = remainingSpan3;
                    wSpec = View.MeasureSpec.makeMeasureSpec(maxSize - horizontalInsets, 1073741824);
                    LayoutParams layoutParams2 = lp;
                    hSpec = getChildMeasureSpec(totalSpaceInOther, 1073741824, verticalInsets, lp.height, false);
                }
                measureChildWithDecorationsAndMargin(view4, wSpec, hSpec, true);
            } else {
                maxSizeInOther = maxSizeInOther2;
                otherDirSpecMode = otherDirSpecMode2;
                remainingSpan = remainingSpan3;
            }
            i3++;
            maxSizeInOther2 = maxSizeInOther;
            otherDirSpecMode2 = otherDirSpecMode;
            remainingSpan3 = remainingSpan;
            RecyclerView.Recycler recycler3 = recycler;
            RecyclerView.State state3 = state;
        }
        float maxSizeInOther3 = maxSizeInOther2;
        int i4 = otherDirSpecMode2;
        int i5 = remainingSpan3;
        layoutChunkResult.mConsumed = maxSize;
        int left2 = 0;
        int right2 = 0;
        int top2 = 0;
        int bottom2 = 0;
        if (this.mOrientation == 1) {
            if (layoutState2.mLayoutDirection == -1) {
                bottom2 = layoutState2.mOffset;
                top2 = bottom2 - maxSize;
            } else {
                top2 = layoutState2.mOffset;
                bottom2 = top2 + maxSize;
            }
        } else if (layoutState2.mLayoutDirection == -1) {
            right2 = layoutState2.mOffset;
            left2 = right2 - maxSize;
        } else {
            left2 = layoutState2.mOffset;
            right2 = left2 + maxSize;
        }
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i7 < count2) {
                View view5 = this.mSet[i7];
                LayoutParams params = (LayoutParams) view5.getLayoutParams();
                if (this.mOrientation != 1) {
                    left = left2;
                    right = right2;
                    int top3 = getPaddingTop() + this.mCachedBorders[params.mSpanIndex];
                    top = top3;
                    bottom = this.mOrientationHelper.getDecoratedMeasurementInOther(view5) + top3;
                } else if (isLayoutRTL()) {
                    int i8 = left2;
                    int i9 = right2;
                    int right3 = getPaddingLeft() + this.mCachedBorders[this.mSpanCount - params.mSpanIndex];
                    left = right3 - this.mOrientationHelper.getDecoratedMeasurementInOther(view5);
                    top = top2;
                    bottom = bottom2;
                    right = right3;
                } else {
                    int i10 = right2;
                    int left3 = getPaddingLeft() + this.mCachedBorders[params.mSpanIndex];
                    left = left3;
                    right = this.mOrientationHelper.getDecoratedMeasurementInOther(view5) + left3;
                    top = top2;
                    bottom = bottom2;
                }
                int maxSize5 = maxSize;
                float maxSizeInOther4 = maxSizeInOther3;
                layoutDecoratedWithMargins(view5, left, top, right, bottom);
                if (params.isItemRemoved() || params.isItemChanged()) {
                    layoutChunkResult.mIgnoreConsumed = true;
                }
                layoutChunkResult.mFocusable |= view5.hasFocusable();
                i6 = i7 + 1;
                top2 = top;
                bottom2 = bottom;
                maxSize = maxSize5;
                maxSizeInOther3 = maxSizeInOther4;
                left2 = left;
                right2 = right;
            } else {
                int i11 = right2;
                int i12 = maxSize;
                float f = maxSizeInOther3;
                Arrays.fill(this.mSet, (Object) null);
                return;
            }
        }
    }

    private void measureChild(View view, int otherDirParentSpecMode, boolean alreadyMeasured) {
        int hSpec;
        int wSpec;
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        Rect decorInsets = lp.mDecorInsets;
        int verticalInsets = decorInsets.top + decorInsets.bottom + lp.topMargin + lp.bottomMargin;
        int horizontalInsets = decorInsets.left + decorInsets.right + lp.leftMargin + lp.rightMargin;
        int availableSpaceInOther = getSpaceForSpanRange(lp.mSpanIndex, lp.mSpanSize);
        if (this.mOrientation == 1) {
            wSpec = getChildMeasureSpec(availableSpaceInOther, otherDirParentSpecMode, horizontalInsets, lp.width, false);
            hSpec = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), verticalInsets, lp.height, true);
        } else {
            hSpec = getChildMeasureSpec(availableSpaceInOther, otherDirParentSpecMode, verticalInsets, lp.height, false);
            wSpec = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), horizontalInsets, lp.width, true);
        }
        measureChildWithDecorationsAndMargin(view, wSpec, hSpec, alreadyMeasured);
    }

    private void guessMeasurement(float maxSizeInOther, int currentOtherDirSize) {
        calculateItemBorders(Math.max(Math.round(((float) this.mSpanCount) * maxSizeInOther), currentOtherDirSize));
    }

    private void measureChildWithDecorationsAndMargin(View child, int widthSpec, int heightSpec, boolean alreadyMeasured) {
        boolean measure;
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
        if (alreadyMeasured) {
            measure = shouldReMeasureChild(child, widthSpec, heightSpec, lp);
        } else {
            measure = shouldMeasureChild(child, widthSpec, heightSpec, lp);
        }
        if (measure) {
            child.measure(widthSpec, heightSpec);
        }
    }

    private void assignSpans(RecyclerView.Recycler recycler, RecyclerView.State state, int count, int consumedSpanCount, boolean layingOutInPrimaryDirection) {
        int diff;
        int end;
        int start;
        if (layingOutInPrimaryDirection) {
            start = 0;
            end = count;
            diff = 1;
        } else {
            start = count - 1;
            end = -1;
            diff = -1;
        }
        int span = 0;
        for (int i = start; i != end; i += diff) {
            View view = this.mSet[i];
            LayoutParams params = (LayoutParams) view.getLayoutParams();
            params.mSpanSize = getSpanSize(recycler, state, getPosition(view));
            params.mSpanIndex = span;
            span += params.mSpanSize;
        }
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public void setSpanCount(int spanCount) {
        if (spanCount != this.mSpanCount) {
            this.mPendingSpanCountChange = true;
            if (spanCount >= 1) {
                this.mSpanCount = spanCount;
                this.mSpanSizeLookup.invalidateSpanIndexCache();
                requestLayout();
                return;
            }
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + spanCount);
        }
    }

    public static abstract class SpanSizeLookup {
        private boolean mCacheSpanIndices = false;
        final SparseIntArray mSpanIndexCache = new SparseIntArray();

        public abstract int getSpanSize(int i);

        public void setSpanIndexCacheEnabled(boolean cacheSpanIndices) {
            this.mCacheSpanIndices = cacheSpanIndices;
        }

        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }

        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }

        /* access modifiers changed from: package-private */
        public int getCachedSpanIndex(int position, int spanCount) {
            if (!this.mCacheSpanIndices) {
                return getSpanIndex(position, spanCount);
            }
            int existing = this.mSpanIndexCache.get(position, -1);
            if (existing != -1) {
                return existing;
            }
            int value = getSpanIndex(position, spanCount);
            this.mSpanIndexCache.put(position, value);
            return value;
        }

        public int getSpanIndex(int position, int spanCount) {
            int prevKey;
            int positionSpanSize = getSpanSize(position);
            if (positionSpanSize == spanCount) {
                return 0;
            }
            int span = 0;
            int startPos = 0;
            if (this.mCacheSpanIndices && this.mSpanIndexCache.size() > 0 && (prevKey = findReferenceIndexFromCache(position)) >= 0) {
                span = this.mSpanIndexCache.get(prevKey) + getSpanSize(prevKey);
                startPos = prevKey + 1;
            }
            int span2 = span;
            for (int i = startPos; i < position; i++) {
                int size = getSpanSize(i);
                span2 += size;
                if (span2 == spanCount) {
                    span2 = 0;
                } else if (span2 > spanCount) {
                    span2 = size;
                }
            }
            if (span2 + positionSpanSize <= spanCount) {
                return span2;
            }
            return 0;
        }

        /* access modifiers changed from: package-private */
        public int findReferenceIndexFromCache(int position) {
            int lo = 0;
            int hi = this.mSpanIndexCache.size() - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                if (this.mSpanIndexCache.keyAt(mid) < position) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            int index = lo - 1;
            if (index < 0 || index >= this.mSpanIndexCache.size()) {
                return -1;
            }
            return this.mSpanIndexCache.keyAt(index);
        }

        public int getSpanGroupIndex(int adapterPosition, int spanCount) {
            int span = 0;
            int group = 0;
            int positionSpanSize = getSpanSize(adapterPosition);
            for (int i = 0; i < adapterPosition; i++) {
                int size = getSpanSize(i);
                span += size;
                if (span == spanCount) {
                    span = 0;
                    group++;
                } else if (span > spanCount) {
                    span = size;
                    group++;
                }
            }
            if (span + positionSpanSize > spanCount) {
                return group + 1;
            }
            return group;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0089, code lost:
        r29 = r3;
        r33 = r4;
        r34 = r5;
        r30 = r11;
     */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x013e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onFocusSearchFailed(android.view.View r36, int r37, android.support.v7.widget.RecyclerView.Recycler r38, android.support.v7.widget.RecyclerView.State r39) {
        /*
            r35 = this;
            r0 = r35
            r1 = r38
            r2 = r39
            android.view.View r3 = r35.findContainingItemView(r36)
            r4 = 0
            if (r3 != 0) goto L_0x000e
            return r4
        L_0x000e:
            android.view.ViewGroup$LayoutParams r5 = r3.getLayoutParams()
            android.support.v7.widget.GridLayoutManager$LayoutParams r5 = (android.support.v7.widget.GridLayoutManager.LayoutParams) r5
            int r6 = r5.mSpanIndex
            int r7 = r5.mSpanIndex
            int r8 = r5.mSpanSize
            int r7 = r7 + r8
            android.view.View r8 = super.onFocusSearchFailed(r36, r37, r38, r39)
            if (r8 != 0) goto L_0x0022
            return r4
        L_0x0022:
            r4 = r37
            int r9 = r0.convertFocusDirectionToLayoutDirection(r4)
            r11 = 1
            if (r9 != r11) goto L_0x002d
            r12 = r11
            goto L_0x002e
        L_0x002d:
            r12 = 0
        L_0x002e:
            boolean r13 = r0.mShouldReverseLayout
            if (r12 == r13) goto L_0x0034
            r12 = r11
            goto L_0x0035
        L_0x0034:
            r12 = 0
        L_0x0035:
            if (r12 == 0) goto L_0x003f
            int r13 = r35.getChildCount()
            int r13 = r13 - r11
            r14 = -1
            r15 = -1
            goto L_0x0045
        L_0x003f:
            r13 = 0
            r14 = 1
            int r15 = r35.getChildCount()
        L_0x0045:
            int r10 = r0.mOrientation
            if (r10 != r11) goto L_0x0051
            boolean r10 = r35.isLayoutRTL()
            if (r10 == 0) goto L_0x0051
            r10 = r11
            goto L_0x0052
        L_0x0051:
            r10 = 0
        L_0x0052:
            r17 = 0
            r18 = -1
            r19 = 0
            r20 = 0
            r21 = -1
            r22 = 0
            int r11 = r0.getSpanGroupIndex(r1, r2, r13)
            r23 = r5
            r24 = r8
            r25 = r9
            r4 = r18
            r5 = r19
            r8 = r21
            r9 = r22
            r18 = r17
            r17 = r13
        L_0x0074:
            r26 = r17
            r27 = r12
            r12 = r26
            if (r12 == r15) goto L_0x0181
            r28 = r13
            int r13 = r0.getSpanGroupIndex(r1, r2, r12)
            android.view.View r1 = r0.getChildAt(r12)
            if (r1 != r3) goto L_0x0093
        L_0x0089:
            r29 = r3
            r33 = r4
            r34 = r5
            r30 = r11
            goto L_0x018b
        L_0x0093:
            boolean r17 = r1.hasFocusable()
            if (r17 == 0) goto L_0x00a8
            if (r13 == r11) goto L_0x00a8
            if (r18 == 0) goto L_0x009e
            goto L_0x0089
        L_0x009e:
            r29 = r3
            r33 = r4
            r34 = r5
            r30 = r11
            goto L_0x016d
        L_0x00a8:
            android.view.ViewGroup$LayoutParams r17 = r1.getLayoutParams()
            r2 = r17
            android.support.v7.widget.GridLayoutManager$LayoutParams r2 = (android.support.v7.widget.GridLayoutManager.LayoutParams) r2
            r29 = r3
            int r3 = r2.mSpanIndex
            r30 = r11
            int r11 = r2.mSpanIndex
            r31 = r13
            int r13 = r2.mSpanSize
            int r11 = r11 + r13
            boolean r13 = r1.hasFocusable()
            if (r13 == 0) goto L_0x00c8
            if (r3 != r6) goto L_0x00c8
            if (r11 != r7) goto L_0x00c8
            return r1
        L_0x00c8:
            r13 = 0
            boolean r17 = r1.hasFocusable()
            if (r17 == 0) goto L_0x00d1
            if (r18 == 0) goto L_0x00d9
        L_0x00d1:
            boolean r17 = r1.hasFocusable()
            if (r17 != 0) goto L_0x00e1
            if (r20 != 0) goto L_0x00e1
        L_0x00d9:
            r13 = 1
            r33 = r4
        L_0x00dc:
            r34 = r5
        L_0x00de:
            r5 = 0
            goto L_0x013c
        L_0x00e1:
            int r17 = java.lang.Math.max(r3, r6)
            int r19 = java.lang.Math.min(r11, r7)
            r32 = r13
            int r13 = r19 - r17
            boolean r21 = r1.hasFocusable()
            if (r21 == 0) goto L_0x0118
            if (r13 <= r5) goto L_0x00fe
            r21 = 1
            r33 = r4
            r34 = r5
            r13 = r21
            goto L_0x00de
        L_0x00fe:
            if (r13 != r5) goto L_0x0112
            if (r3 <= r4) goto L_0x0106
            r33 = r4
            r4 = 1
            goto L_0x0109
        L_0x0106:
            r33 = r4
            r4 = 0
        L_0x0109:
            if (r10 != r4) goto L_0x010e
            r4 = 1
            r13 = r4
            goto L_0x00dc
        L_0x010e:
            r34 = r5
            r5 = 0
            goto L_0x013a
        L_0x0112:
            r33 = r4
            r34 = r5
            r5 = 0
            goto L_0x013a
        L_0x0118:
            r33 = r4
            if (r18 != 0) goto L_0x0137
            r34 = r5
            r4 = 1
            r5 = 0
            boolean r16 = r0.isViewPartiallyVisible(r1, r5, r4)
            if (r16 == 0) goto L_0x013a
            if (r13 <= r9) goto L_0x012d
            r16 = 1
            r13 = r16
            goto L_0x013c
        L_0x012d:
            if (r13 != r9) goto L_0x013a
            if (r3 <= r8) goto L_0x0132
            goto L_0x0133
        L_0x0132:
            r4 = r5
        L_0x0133:
            if (r10 != r4) goto L_0x013a
            r13 = 1
            goto L_0x013c
        L_0x0137:
            r34 = r5
            r5 = 0
        L_0x013a:
            r13 = r32
        L_0x013c:
            if (r13 == 0) goto L_0x016d
            boolean r4 = r1.hasFocusable()
            if (r4 == 0) goto L_0x0157
            r4 = r1
            int r5 = r2.mSpanIndex
            int r16 = java.lang.Math.min(r11, r7)
            int r17 = java.lang.Math.max(r3, r6)
            int r16 = r16 - r17
            r18 = r4
            r4 = r5
            r5 = r16
            goto L_0x0171
        L_0x0157:
            r4 = r1
            int r5 = r2.mSpanIndex
            int r8 = java.lang.Math.min(r11, r7)
            int r16 = java.lang.Math.max(r3, r6)
            int r8 = r8 - r16
            r20 = r4
            r9 = r8
            r4 = r33
            r8 = r5
            r5 = r34
            goto L_0x0171
        L_0x016d:
            r4 = r33
            r5 = r34
        L_0x0171:
            int r17 = r12 + r14
            r12 = r27
            r13 = r28
            r3 = r29
            r11 = r30
            r1 = r38
            r2 = r39
            goto L_0x0074
        L_0x0181:
            r29 = r3
            r33 = r4
            r34 = r5
            r30 = r11
            r28 = r13
        L_0x018b:
            if (r18 == 0) goto L_0x0190
            r1 = r18
            goto L_0x0192
        L_0x0190:
            r1 = r20
        L_0x0192:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.onFocusSearchFailed(android.view.View, int, android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State):android.view.View");
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }

    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        public int getSpanSize(int position) {
            return 1;
        }

        public int getSpanIndex(int position, int spanCount) {
            return position % spanCount;
        }
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        int mSpanIndex = -1;
        int mSpanSize = 0;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(RecyclerView.LayoutParams source) {
            super(source);
        }

        public int getSpanIndex() {
            return this.mSpanIndex;
        }

        public int getSpanSize() {
            return this.mSpanSize;
        }
    }
}
