package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    static final boolean DEBUG = false;
    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "StaggeredGridLManager";
    public static final int VERTICAL = 1;
    private final AnchorInfo mAnchorInfo = new AnchorInfo();
    private final Runnable mCheckForGapsRunnable = new Runnable() {
        public void run() {
            StaggeredGridLayoutManager.this.checkForGaps();
        }
    };
    private int mFullSizeSpec;
    private int mGapStrategy = 2;
    private boolean mLaidOutInvalidFullSpan = false;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;
    @NonNull
    private final LayoutState mLayoutState;
    LazySpanLookup mLazySpanLookup = new LazySpanLookup();
    private int mOrientation;
    private SavedState mPendingSavedState;
    int mPendingScrollPosition = -1;
    int mPendingScrollPositionOffset = Integer.MIN_VALUE;
    private int[] mPrefetchDistances;
    @NonNull
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;
    boolean mReverseLayout = false;
    @NonNull
    OrientationHelper mSecondaryOrientation;
    boolean mShouldReverseLayout = false;
    private int mSizePerSpan;
    private boolean mSmoothScrollbarEnabled = true;
    private int mSpanCount = -1;
    Span[] mSpans;
    private final Rect mTmpRect = new Rect();

    public StaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        RecyclerView.LayoutManager.Properties properties = getProperties(context, attrs, defStyleAttr, defStyleRes);
        setOrientation(properties.orientation);
        setSpanCount(properties.spanCount);
        setReverseLayout(properties.reverseLayout);
        this.mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    public StaggeredGridLayoutManager(int spanCount, int orientation) {
        this.mOrientation = orientation;
        setSpanCount(spanCount);
        this.mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    public boolean isAutoMeasureEnabled() {
        return this.mGapStrategy != 0;
    }

    private void createOrientationHelpers() {
        this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }

    /* access modifiers changed from: package-private */
    public boolean checkForGaps() {
        int maxPos;
        int minPos;
        if (getChildCount() == 0 || this.mGapStrategy == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.mShouldReverseLayout) {
            minPos = getLastChildPosition();
            maxPos = getFirstChildPosition();
        } else {
            minPos = getFirstChildPosition();
            maxPos = getLastChildPosition();
        }
        if (minPos == 0 && hasGapsToFix() != null) {
            this.mLazySpanLookup.clear();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        } else if (!this.mLaidOutInvalidFullSpan) {
            return false;
        } else {
            int invalidGapDir = this.mShouldReverseLayout ? -1 : 1;
            LazySpanLookup.FullSpanItem invalidFsi = this.mLazySpanLookup.getFirstFullSpanItemInRange(minPos, maxPos + 1, invalidGapDir, true);
            if (invalidFsi == null) {
                this.mLaidOutInvalidFullSpan = false;
                this.mLazySpanLookup.forceInvalidateAfter(maxPos + 1);
                return false;
            }
            LazySpanLookup.FullSpanItem validFsi = this.mLazySpanLookup.getFirstFullSpanItemInRange(minPos, invalidFsi.mPosition, invalidGapDir * -1, true);
            if (validFsi == null) {
                this.mLazySpanLookup.forceInvalidateAfter(invalidFsi.mPosition);
            } else {
                this.mLazySpanLookup.forceInvalidateAfter(validFsi.mPosition + 1);
            }
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
    }

    public void onScrollStateChanged(int state) {
        if (state == 0) {
            checkForGaps();
        }
    }

    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(view, recycler);
        removeCallbacks(this.mCheckForGapsRunnable);
        for (int i = 0; i < this.mSpanCount; i++) {
            this.mSpans[i].clear();
        }
        view.requestLayout();
    }

    /* access modifiers changed from: package-private */
    public View hasGapsToFix() {
        int childLimit;
        int firstChildIndex;
        int endChildIndex = getChildCount() - 1;
        BitSet mSpansToCheck = new BitSet(this.mSpanCount);
        mSpansToCheck.set(0, this.mSpanCount, true);
        int nextChildDiff = -1;
        int preferredSpanDir = (this.mOrientation != 1 || !isLayoutRTL()) ? -1 : 1;
        if (this.mShouldReverseLayout) {
            firstChildIndex = endChildIndex;
            childLimit = 0 - 1;
        } else {
            firstChildIndex = 0;
            childLimit = endChildIndex + 1;
        }
        if (firstChildIndex < childLimit) {
            nextChildDiff = 1;
        }
        for (int i = firstChildIndex; i != childLimit; i += nextChildDiff) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (mSpansToCheck.get(lp.mSpan.mIndex)) {
                if (checkSpanForGap(lp.mSpan)) {
                    return child;
                }
                mSpansToCheck.clear(lp.mSpan.mIndex);
            }
            if (!lp.mFullSpan && i + nextChildDiff != childLimit) {
                View nextChild = getChildAt(i + nextChildDiff);
                boolean compareSpans = false;
                if (this.mShouldReverseLayout) {
                    int myEnd = this.mPrimaryOrientation.getDecoratedEnd(child);
                    int nextEnd = this.mPrimaryOrientation.getDecoratedEnd(nextChild);
                    if (myEnd < nextEnd) {
                        return child;
                    }
                    if (myEnd == nextEnd) {
                        compareSpans = true;
                    }
                } else {
                    int myStart = this.mPrimaryOrientation.getDecoratedStart(child);
                    int nextStart = this.mPrimaryOrientation.getDecoratedStart(nextChild);
                    if (myStart > nextStart) {
                        return child;
                    }
                    if (myStart == nextStart) {
                        compareSpans = true;
                    }
                }
                if (!compareSpans) {
                    continue;
                } else {
                    if ((lp.mSpan.mIndex - ((LayoutParams) nextChild.getLayoutParams()).mSpan.mIndex < 0) != (preferredSpanDir < 0)) {
                        return child;
                    }
                }
            }
        }
        return null;
    }

    private boolean checkSpanForGap(Span span) {
        if (this.mShouldReverseLayout) {
            if (span.getEndLine() < this.mPrimaryOrientation.getEndAfterPadding()) {
                return !span.getLayoutParams(span.mViews.get(span.mViews.size() - 1)).mFullSpan;
            }
        } else if (span.getStartLine() > this.mPrimaryOrientation.getStartAfterPadding()) {
            return !span.getLayoutParams(span.mViews.get(0)).mFullSpan;
        }
        return false;
    }

    public void setSpanCount(int spanCount) {
        assertNotInLayoutOrScroll((String) null);
        if (spanCount != this.mSpanCount) {
            invalidateSpanAssignments();
            this.mSpanCount = spanCount;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new Span[this.mSpanCount];
            for (int i = 0; i < this.mSpanCount; i++) {
                this.mSpans[i] = new Span(i);
            }
            requestLayout();
        }
    }

    public void setOrientation(int orientation) {
        if (orientation == 0 || orientation == 1) {
            assertNotInLayoutOrScroll((String) null);
            if (orientation != this.mOrientation) {
                this.mOrientation = orientation;
                OrientationHelper tmp = this.mPrimaryOrientation;
                this.mPrimaryOrientation = this.mSecondaryOrientation;
                this.mSecondaryOrientation = tmp;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation.");
    }

    public void setReverseLayout(boolean reverseLayout) {
        assertNotInLayoutOrScroll((String) null);
        if (!(this.mPendingSavedState == null || this.mPendingSavedState.mReverseLayout == reverseLayout)) {
            this.mPendingSavedState.mReverseLayout = reverseLayout;
        }
        this.mReverseLayout = reverseLayout;
        requestLayout();
    }

    public int getGapStrategy() {
        return this.mGapStrategy;
    }

    public void setGapStrategy(int gapStrategy) {
        assertNotInLayoutOrScroll((String) null);
        if (gapStrategy != this.mGapStrategy) {
            if (gapStrategy == 0 || gapStrategy == 2) {
                this.mGapStrategy = gapStrategy;
                requestLayout();
                return;
            }
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
    }

    public void assertNotInLayoutOrScroll(String message) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(message);
        }
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = !this.mReverseLayout;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public void setMeasuredDimension(Rect childrenBounds, int wSpec, int hSpec) {
        int height;
        int width;
        int horizontalPadding = getPaddingLeft() + getPaddingRight();
        int verticalPadding = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            int chooseSize = chooseSize(hSpec, childrenBounds.height() + verticalPadding, getMinimumHeight());
            height = chooseSize(wSpec, (this.mSizePerSpan * this.mSpanCount) + horizontalPadding, getMinimumWidth());
            width = chooseSize;
        } else {
            height = chooseSize(wSpec, childrenBounds.width() + horizontalPadding, getMinimumWidth());
            width = chooseSize(hSpec, (this.mSizePerSpan * this.mSpanCount) + verticalPadding, getMinimumHeight());
        }
        setMeasuredDimension(height, width);
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        onLayoutChildren(recycler, state, true);
    }

    private void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state, boolean shouldCheckForGaps) {
        AnchorInfo anchorInfo = this.mAnchorInfo;
        if (!(this.mPendingSavedState == null && this.mPendingScrollPosition == -1) && state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            anchorInfo.reset();
            return;
        }
        boolean needToCheckForGaps = true;
        boolean recalculateAnchor = (anchorInfo.mValid && this.mPendingScrollPosition == -1 && this.mPendingSavedState == null) ? false : true;
        if (recalculateAnchor) {
            anchorInfo.reset();
            if (this.mPendingSavedState != null) {
                applyPendingSavedState(anchorInfo);
            } else {
                resolveShouldLayoutReverse();
                anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
            }
            updateAnchorInfoForLayout(state, anchorInfo);
            anchorInfo.mValid = true;
        }
        if (this.mPendingSavedState == null && this.mPendingScrollPosition == -1 && !(anchorInfo.mLayoutFromEnd == this.mLastLayoutFromEnd && isLayoutRTL() == this.mLastLayoutRTL)) {
            this.mLazySpanLookup.clear();
            anchorInfo.mInvalidateOffsets = true;
        }
        if (getChildCount() > 0 && (this.mPendingSavedState == null || this.mPendingSavedState.mSpanOffsetsSize < 1)) {
            if (anchorInfo.mInvalidateOffsets) {
                for (int i = 0; i < this.mSpanCount; i++) {
                    this.mSpans[i].clear();
                    if (anchorInfo.mOffset != Integer.MIN_VALUE) {
                        this.mSpans[i].setLine(anchorInfo.mOffset);
                    }
                }
            } else if (recalculateAnchor || this.mAnchorInfo.mSpanReferenceLines == null) {
                for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                    this.mSpans[i2].cacheReferenceLineAndClear(this.mShouldReverseLayout, anchorInfo.mOffset);
                }
                this.mAnchorInfo.saveSpanReferenceLines(this.mSpans);
            } else {
                for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                    Span span = this.mSpans[i3];
                    span.clear();
                    span.setLine(this.mAnchorInfo.mSpanReferenceLines[i3]);
                }
            }
        }
        detachAndScrapAttachedViews(recycler);
        this.mLayoutState.mRecycle = false;
        this.mLaidOutInvalidFullSpan = false;
        updateMeasureSpecs(this.mSecondaryOrientation.getTotalSpace());
        updateLayoutState(anchorInfo.mPosition, state);
        if (anchorInfo.mLayoutFromEnd) {
            setLayoutStateDirection(-1);
            fill(recycler, this.mLayoutState, state);
            setLayoutStateDirection(1);
            this.mLayoutState.mCurrentPosition = anchorInfo.mPosition + this.mLayoutState.mItemDirection;
            fill(recycler, this.mLayoutState, state);
        } else {
            setLayoutStateDirection(1);
            fill(recycler, this.mLayoutState, state);
            setLayoutStateDirection(-1);
            this.mLayoutState.mCurrentPosition = anchorInfo.mPosition + this.mLayoutState.mItemDirection;
            fill(recycler, this.mLayoutState, state);
        }
        repositionToWrapContentIfNecessary();
        if (getChildCount() > 0) {
            if (this.mShouldReverseLayout) {
                fixEndGap(recycler, state, true);
                fixStartGap(recycler, state, false);
            } else {
                fixStartGap(recycler, state, true);
                fixEndGap(recycler, state, false);
            }
        }
        boolean hasGaps = false;
        if (shouldCheckForGaps && !state.isPreLayout()) {
            if (this.mGapStrategy == 0 || getChildCount() <= 0 || (!this.mLaidOutInvalidFullSpan && hasGapsToFix() == null)) {
                needToCheckForGaps = false;
            }
            if (needToCheckForGaps) {
                removeCallbacks(this.mCheckForGapsRunnable);
                if (checkForGaps()) {
                    hasGaps = true;
                }
            }
        }
        if (state.isPreLayout()) {
            this.mAnchorInfo.reset();
        }
        this.mLastLayoutFromEnd = anchorInfo.mLayoutFromEnd;
        this.mLastLayoutRTL = isLayoutRTL();
        if (hasGaps) {
            this.mAnchorInfo.reset();
            onLayoutChildren(recycler, state, false);
        }
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.reset();
    }

    private void repositionToWrapContentIfNecessary() {
        if (this.mSecondaryOrientation.getMode() != 1073741824) {
            int childCount = getChildCount();
            float maxSize = 0.0f;
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                float size = (float) this.mSecondaryOrientation.getDecoratedMeasurement(child);
                if (size >= maxSize) {
                    if (((LayoutParams) child.getLayoutParams()).isFullSpan()) {
                        size = (1.0f * size) / ((float) this.mSpanCount);
                    }
                    maxSize = Math.max(maxSize, size);
                }
            }
            int before = this.mSizePerSpan;
            int desired = Math.round(((float) this.mSpanCount) * maxSize);
            if (this.mSecondaryOrientation.getMode() == Integer.MIN_VALUE) {
                desired = Math.min(desired, this.mSecondaryOrientation.getTotalSpace());
            }
            updateMeasureSpecs(desired);
            if (this.mSizePerSpan != before) {
                for (int i2 = 0; i2 < childCount; i2++) {
                    View child2 = getChildAt(i2);
                    LayoutParams lp = (LayoutParams) child2.getLayoutParams();
                    if (!lp.mFullSpan) {
                        if (!isLayoutRTL() || this.mOrientation != 1) {
                            int newOffset = lp.mSpan.mIndex * this.mSizePerSpan;
                            int prevOffset = lp.mSpan.mIndex * before;
                            if (this.mOrientation == 1) {
                                child2.offsetLeftAndRight(newOffset - prevOffset);
                            } else {
                                child2.offsetTopAndBottom(newOffset - prevOffset);
                            }
                        } else {
                            child2.offsetLeftAndRight(((-((this.mSpanCount - 1) - lp.mSpan.mIndex)) * this.mSizePerSpan) - ((-((this.mSpanCount - 1) - lp.mSpan.mIndex)) * before));
                        }
                    }
                }
            }
        }
    }

    private void applyPendingSavedState(AnchorInfo anchorInfo) {
        if (this.mPendingSavedState.mSpanOffsetsSize > 0) {
            if (this.mPendingSavedState.mSpanOffsetsSize == this.mSpanCount) {
                for (int i = 0; i < this.mSpanCount; i++) {
                    this.mSpans[i].clear();
                    int line = this.mPendingSavedState.mSpanOffsets[i];
                    if (line != Integer.MIN_VALUE) {
                        if (this.mPendingSavedState.mAnchorLayoutFromEnd) {
                            line += this.mPrimaryOrientation.getEndAfterPadding();
                        } else {
                            line += this.mPrimaryOrientation.getStartAfterPadding();
                        }
                    }
                    this.mSpans[i].setLine(line);
                }
            } else {
                this.mPendingSavedState.invalidateSpanInfo();
                this.mPendingSavedState.mAnchorPosition = this.mPendingSavedState.mVisibleAnchorPosition;
            }
        }
        this.mLastLayoutRTL = this.mPendingSavedState.mLastLayoutRTL;
        setReverseLayout(this.mPendingSavedState.mReverseLayout);
        resolveShouldLayoutReverse();
        if (this.mPendingSavedState.mAnchorPosition != -1) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
            anchorInfo.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
        } else {
            anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
        }
        if (this.mPendingSavedState.mSpanLookupSize > 1) {
            this.mLazySpanLookup.mData = this.mPendingSavedState.mSpanLookup;
            this.mLazySpanLookup.mFullSpanItems = this.mPendingSavedState.mFullSpanItems;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateAnchorInfoForLayout(RecyclerView.State state, AnchorInfo anchorInfo) {
        if (!updateAnchorFromPendingData(state, anchorInfo) && !updateAnchorFromChildren(state, anchorInfo)) {
            anchorInfo.assignCoordinateFromPadding();
            anchorInfo.mPosition = 0;
        }
    }

    private boolean updateAnchorFromChildren(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i;
        if (this.mLastLayoutFromEnd) {
            i = findLastReferenceChildPosition(state.getItemCount());
        } else {
            i = findFirstReferenceChildPosition(state.getItemCount());
        }
        anchorInfo.mPosition = i;
        anchorInfo.mOffset = Integer.MIN_VALUE;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean updateAnchorFromPendingData(RecyclerView.State state, AnchorInfo anchorInfo) {
        int i;
        int i2;
        boolean z = false;
        if (state.isPreLayout() || this.mPendingScrollPosition == -1) {
            return false;
        }
        if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= state.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
        }
        if (this.mPendingSavedState == null || this.mPendingSavedState.mAnchorPosition == -1 || this.mPendingSavedState.mSpanOffsetsSize < 1) {
            View child = findViewByPosition(this.mPendingScrollPosition);
            if (child != null) {
                if (this.mShouldReverseLayout) {
                    i = getLastChildPosition();
                } else {
                    i = getFirstChildPosition();
                }
                anchorInfo.mPosition = i;
                if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                    if (anchorInfo.mLayoutFromEnd) {
                        anchorInfo.mOffset = (this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedEnd(child);
                    } else {
                        anchorInfo.mOffset = (this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedStart(child);
                    }
                    return true;
                } else if (this.mPrimaryOrientation.getDecoratedMeasurement(child) > this.mPrimaryOrientation.getTotalSpace()) {
                    if (anchorInfo.mLayoutFromEnd) {
                        i2 = this.mPrimaryOrientation.getEndAfterPadding();
                    } else {
                        i2 = this.mPrimaryOrientation.getStartAfterPadding();
                    }
                    anchorInfo.mOffset = i2;
                    return true;
                } else {
                    int startGap = this.mPrimaryOrientation.getDecoratedStart(child) - this.mPrimaryOrientation.getStartAfterPadding();
                    if (startGap < 0) {
                        anchorInfo.mOffset = -startGap;
                        return true;
                    }
                    int endGap = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(child);
                    if (endGap < 0) {
                        anchorInfo.mOffset = endGap;
                        return true;
                    }
                    anchorInfo.mOffset = Integer.MIN_VALUE;
                }
            } else {
                anchorInfo.mPosition = this.mPendingScrollPosition;
                if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                    if (calculateScrollDirectionForPosition(anchorInfo.mPosition) == 1) {
                        z = true;
                    }
                    anchorInfo.mLayoutFromEnd = z;
                    anchorInfo.assignCoordinateFromPadding();
                } else {
                    anchorInfo.assignCoordinateFromPadding(this.mPendingScrollPositionOffset);
                }
                anchorInfo.mInvalidateOffsets = true;
            }
        } else {
            anchorInfo.mOffset = Integer.MIN_VALUE;
            anchorInfo.mPosition = this.mPendingScrollPosition;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void updateMeasureSpecs(int totalSpace) {
        this.mSizePerSpan = totalSpace / this.mSpanCount;
        this.mFullSizeSpec = View.MeasureSpec.makeMeasureSpec(totalSpace, this.mSecondaryOrientation.getMode());
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }

    public int[] findFirstVisibleItemPositions(int[] into) {
        if (into == null) {
            into = new int[this.mSpanCount];
        } else if (into.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + into.length);
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            into[i] = this.mSpans[i].findFirstVisibleItemPosition();
        }
        return into;
    }

    public int[] findFirstCompletelyVisibleItemPositions(int[] into) {
        if (into == null) {
            into = new int[this.mSpanCount];
        } else if (into.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + into.length);
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            into[i] = this.mSpans[i].findFirstCompletelyVisibleItemPosition();
        }
        return into;
    }

    public int[] findLastVisibleItemPositions(int[] into) {
        if (into == null) {
            into = new int[this.mSpanCount];
        } else if (into.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + into.length);
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            into[i] = this.mSpans[i].findLastVisibleItemPosition();
        }
        return into;
    }

    public int[] findLastCompletelyVisibleItemPositions(int[] into) {
        if (into == null) {
            into = new int[this.mSpanCount];
        } else if (into.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + into.length);
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            into[i] = this.mSpans[i].findLastCompletelyVisibleItemPosition();
        }
        return into;
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollOffset(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollExtent(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollRange(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    private void measureChildWithDecorationsAndMargin(View child, LayoutParams lp, boolean alreadyMeasured) {
        if (lp.mFullSpan) {
            if (this.mOrientation == 1) {
                measureChildWithDecorationsAndMargin(child, this.mFullSizeSpec, getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), lp.height, true), alreadyMeasured);
            } else {
                measureChildWithDecorationsAndMargin(child, getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), lp.width, true), this.mFullSizeSpec, alreadyMeasured);
            }
        } else if (this.mOrientation == 1) {
            measureChildWithDecorationsAndMargin(child, getChildMeasureSpec(this.mSizePerSpan, getWidthMode(), 0, lp.width, false), getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), lp.height, true), alreadyMeasured);
        } else {
            measureChildWithDecorationsAndMargin(child, getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), lp.width, true), getChildMeasureSpec(this.mSizePerSpan, getHeightMode(), 0, lp.height, false), alreadyMeasured);
        }
    }

    private void measureChildWithDecorationsAndMargin(View child, int widthSpec, int heightSpec, boolean alreadyMeasured) {
        boolean measure;
        calculateItemDecorationsForChild(child, this.mTmpRect);
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int widthSpec2 = updateSpecWithExtra(widthSpec, lp.leftMargin + this.mTmpRect.left, lp.rightMargin + this.mTmpRect.right);
        int heightSpec2 = updateSpecWithExtra(heightSpec, lp.topMargin + this.mTmpRect.top, lp.bottomMargin + this.mTmpRect.bottom);
        if (alreadyMeasured) {
            measure = shouldReMeasureChild(child, widthSpec2, heightSpec2, lp);
        } else {
            measure = shouldMeasureChild(child, widthSpec2, heightSpec2, lp);
        }
        if (measure) {
            child.measure(widthSpec2, heightSpec2);
        }
    }

    private int updateSpecWithExtra(int spec, int startInset, int endInset) {
        if (startInset == 0 && endInset == 0) {
            return spec;
        }
        int mode = View.MeasureSpec.getMode(spec);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(spec) - startInset) - endInset), mode);
        }
        return spec;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            this.mPendingSavedState = (SavedState) state;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        int i;
        int line;
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState state = new SavedState();
        state.mReverseLayout = this.mReverseLayout;
        state.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
        state.mLastLayoutRTL = this.mLastLayoutRTL;
        int line2 = 0;
        if (this.mLazySpanLookup == null || this.mLazySpanLookup.mData == null) {
            state.mSpanLookupSize = 0;
        } else {
            state.mSpanLookup = this.mLazySpanLookup.mData;
            state.mSpanLookupSize = state.mSpanLookup.length;
            state.mFullSpanItems = this.mLazySpanLookup.mFullSpanItems;
        }
        if (getChildCount() > 0) {
            if (this.mLastLayoutFromEnd) {
                i = getLastChildPosition();
            } else {
                i = getFirstChildPosition();
            }
            state.mAnchorPosition = i;
            state.mVisibleAnchorPosition = findFirstVisibleItemPositionInt();
            state.mSpanOffsetsSize = this.mSpanCount;
            state.mSpanOffsets = new int[this.mSpanCount];
            while (true) {
                int i2 = line2;
                if (i2 >= this.mSpanCount) {
                    break;
                }
                if (this.mLastLayoutFromEnd) {
                    line = this.mSpans[i2].getEndLine(Integer.MIN_VALUE);
                    if (line != Integer.MIN_VALUE) {
                        line -= this.mPrimaryOrientation.getEndAfterPadding();
                    }
                } else {
                    line = this.mSpans[i2].getStartLine(Integer.MIN_VALUE);
                    if (line != Integer.MIN_VALUE) {
                        line -= this.mPrimaryOrientation.getStartAfterPadding();
                    }
                }
                state.mSpanOffsets[i2] = line;
                line2 = i2 + 1;
            }
        } else {
            state.mAnchorPosition = -1;
            state.mVisibleAnchorPosition = -1;
            state.mSpanOffsetsSize = 0;
        }
        return state;
    }

    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View host, AccessibilityNodeInfoCompat info) {
        ViewGroup.LayoutParams lp = host.getLayoutParams();
        if (!(lp instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(host, info);
            return;
        }
        LayoutParams sglp = (LayoutParams) lp;
        int i = 1;
        if (this.mOrientation == 0) {
            int spanIndex = sglp.getSpanIndex();
            if (sglp.mFullSpan) {
                i = this.mSpanCount;
            }
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanIndex, i, -1, -1, sglp.mFullSpan, false));
            return;
        }
        int spanIndex2 = sglp.getSpanIndex();
        if (sglp.mFullSpan) {
            i = this.mSpanCount;
        }
        info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(-1, -1, spanIndex2, i, sglp.mFullSpan, false));
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        if (getChildCount() > 0) {
            View start = findFirstVisibleItemClosestToStart(false);
            View end = findFirstVisibleItemClosestToEnd(false);
            if (start != null && end != null) {
                int startPos = getPosition(start);
                int endPos = getPosition(end);
                if (startPos < endPos) {
                    event.setFromIndex(startPos);
                    event.setToIndex(endPos);
                    return;
                }
                event.setFromIndex(endPos);
                event.setToIndex(startPos);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int findFirstVisibleItemPositionInt() {
        View first;
        if (this.mShouldReverseLayout) {
            first = findFirstVisibleItemClosestToEnd(true);
        } else {
            first = findFirstVisibleItemClosestToStart(true);
        }
        if (first == null) {
            return -1;
        }
        return getPosition(first);
    }

    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        return super.getRowCountForAccessibility(recycler, state);
    }

    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        return super.getColumnCountForAccessibility(recycler, state);
    }

    /* access modifiers changed from: package-private */
    public View findFirstVisibleItemClosestToStart(boolean fullyVisible) {
        int boundsStart = this.mPrimaryOrientation.getStartAfterPadding();
        int boundsEnd = this.mPrimaryOrientation.getEndAfterPadding();
        int limit = getChildCount();
        View partiallyVisible = null;
        for (int i = 0; i < limit; i++) {
            View child = getChildAt(i);
            int childStart = this.mPrimaryOrientation.getDecoratedStart(child);
            if (this.mPrimaryOrientation.getDecoratedEnd(child) > boundsStart && childStart < boundsEnd) {
                if (childStart >= boundsStart || !fullyVisible) {
                    return child;
                }
                if (partiallyVisible == null) {
                    partiallyVisible = child;
                }
            }
        }
        return partiallyVisible;
    }

    /* access modifiers changed from: package-private */
    public View findFirstVisibleItemClosestToEnd(boolean fullyVisible) {
        int boundsStart = this.mPrimaryOrientation.getStartAfterPadding();
        int boundsEnd = this.mPrimaryOrientation.getEndAfterPadding();
        View partiallyVisible = null;
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            int childStart = this.mPrimaryOrientation.getDecoratedStart(child);
            int childEnd = this.mPrimaryOrientation.getDecoratedEnd(child);
            if (childEnd > boundsStart && childStart < boundsEnd) {
                if (childEnd <= boundsEnd || !fullyVisible) {
                    return child;
                }
                if (partiallyVisible == null) {
                    partiallyVisible = child;
                }
            }
        }
        return partiallyVisible;
    }

    private void fixEndGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean canOffsetChildren) {
        int gap;
        int maxEndLine = getMaxEnd(Integer.MIN_VALUE);
        if (maxEndLine != Integer.MIN_VALUE && (gap = this.mPrimaryOrientation.getEndAfterPadding() - maxEndLine) > 0) {
            int gap2 = gap - (-scrollBy(-gap, recycler, state));
            if (canOffsetChildren && gap2 > 0) {
                this.mPrimaryOrientation.offsetChildren(gap2);
            }
        }
    }

    private void fixStartGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean canOffsetChildren) {
        int gap;
        int minStartLine = getMinStart(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        if (minStartLine != Integer.MAX_VALUE && (gap = minStartLine - this.mPrimaryOrientation.getStartAfterPadding()) > 0) {
            int gap2 = gap - scrollBy(gap, recycler, state);
            if (canOffsetChildren && gap2 > 0) {
                this.mPrimaryOrientation.offsetChildren(-gap2);
            }
        }
    }

    private void updateLayoutState(int anchorPosition, RecyclerView.State state) {
        int targetPos;
        boolean z = false;
        this.mLayoutState.mAvailable = 0;
        this.mLayoutState.mCurrentPosition = anchorPosition;
        int startExtra = 0;
        int endExtra = 0;
        if (isSmoothScrolling() && (targetPos = state.getTargetScrollPosition()) != -1) {
            if (this.mShouldReverseLayout == (targetPos < anchorPosition)) {
                endExtra = this.mPrimaryOrientation.getTotalSpace();
            } else {
                startExtra = this.mPrimaryOrientation.getTotalSpace();
            }
        }
        if (getClipToPadding() != 0) {
            this.mLayoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - startExtra;
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEndAfterPadding() + endExtra;
        } else {
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEnd() + endExtra;
            this.mLayoutState.mStartLine = -startExtra;
        }
        this.mLayoutState.mStopInFocusable = false;
        this.mLayoutState.mRecycle = true;
        LayoutState layoutState = this.mLayoutState;
        if (this.mPrimaryOrientation.getMode() == 0 && this.mPrimaryOrientation.getEnd() == 0) {
            z = true;
        }
        layoutState.mInfinite = z;
    }

    private void setLayoutStateDirection(int direction) {
        this.mLayoutState.mLayoutDirection = direction;
        LayoutState layoutState = this.mLayoutState;
        int i = 1;
        if (this.mShouldReverseLayout != (direction == -1)) {
            i = -1;
        }
        layoutState.mItemDirection = i;
    }

    public void offsetChildrenHorizontal(int dx) {
        super.offsetChildrenHorizontal(dx);
        for (int i = 0; i < this.mSpanCount; i++) {
            this.mSpans[i].onOffset(dx);
        }
    }

    public void offsetChildrenVertical(int dy) {
        super.offsetChildrenVertical(dy);
        for (int i = 0; i < this.mSpanCount; i++) {
            this.mSpans[i].onOffset(dy);
        }
    }

    public void onItemsRemoved(RecyclerView recyclerView, int positionStart, int itemCount) {
        handleUpdate(positionStart, itemCount, 2);
    }

    public void onItemsAdded(RecyclerView recyclerView, int positionStart, int itemCount) {
        handleUpdate(positionStart, itemCount, 1);
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    public void onItemsMoved(RecyclerView recyclerView, int from, int to, int itemCount) {
        handleUpdate(from, to, 8);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int positionStart, int itemCount, Object payload) {
        handleUpdate(positionStart, itemCount, 4);
    }

    private void handleUpdate(int positionStart, int itemCountOrToPosition, int cmd) {
        int affectedRangeStart;
        int affectedRangeEnd;
        int minPosition = this.mShouldReverseLayout ? getLastChildPosition() : getFirstChildPosition();
        if (cmd != 8) {
            affectedRangeStart = positionStart;
            affectedRangeEnd = positionStart + itemCountOrToPosition;
        } else if (positionStart < itemCountOrToPosition) {
            affectedRangeEnd = itemCountOrToPosition + 1;
            affectedRangeStart = positionStart;
        } else {
            affectedRangeEnd = positionStart + 1;
            affectedRangeStart = itemCountOrToPosition;
        }
        this.mLazySpanLookup.invalidateAfter(affectedRangeStart);
        if (cmd != 8) {
            switch (cmd) {
                case 1:
                    this.mLazySpanLookup.offsetForAddition(positionStart, itemCountOrToPosition);
                    break;
                case 2:
                    this.mLazySpanLookup.offsetForRemoval(positionStart, itemCountOrToPosition);
                    break;
            }
        } else {
            this.mLazySpanLookup.offsetForRemoval(positionStart, 1);
            this.mLazySpanLookup.offsetForAddition(itemCountOrToPosition, 1);
        }
        if (affectedRangeEnd > minPosition) {
            if (affectedRangeStart <= (this.mShouldReverseLayout ? getFirstChildPosition() : getLastChildPosition())) {
                requestLayout();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r14v0 */
    /* JADX WARNING: type inference failed for: r14v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r14v5 */
    private int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state) {
        int targetLine;
        int i;
        int added;
        int i2;
        int diff;
        Span currentSpan;
        int end;
        int start;
        int otherStart;
        int otherEnd;
        Span currentSpan2;
        int defaultNewViewLine;
        View view;
        LayoutParams lp;
        int targetLine2;
        boolean z;
        int otherStart2;
        int otherEnd2;
        boolean hasInvalidGap;
        int end2;
        int start2;
        RecyclerView.Recycler recycler2 = recycler;
        LayoutState layoutState2 = layoutState;
        ? r14 = 0;
        boolean z2 = true;
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        if (this.mLayoutState.mInfinite) {
            if (layoutState2.mLayoutDirection == 1) {
                targetLine = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            } else {
                targetLine = Integer.MIN_VALUE;
            }
        } else if (layoutState2.mLayoutDirection == 1) {
            targetLine = layoutState2.mEndLine + layoutState2.mAvailable;
        } else {
            targetLine = layoutState2.mStartLine - layoutState2.mAvailable;
        }
        int targetLine3 = targetLine;
        updateAllRemainingSpans(layoutState2.mLayoutDirection, targetLine3);
        if (this.mShouldReverseLayout) {
            i = this.mPrimaryOrientation.getEndAfterPadding();
        } else {
            i = this.mPrimaryOrientation.getStartAfterPadding();
        }
        int defaultNewViewLine2 = i;
        int targetLine4 = 0;
        while (true) {
            added = targetLine4;
            if (layoutState.hasMore(state)) {
                if (!this.mLayoutState.mInfinite && this.mRemainingSpans.isEmpty()) {
                    int i3 = defaultNewViewLine2;
                    int i4 = targetLine3;
                    i2 = r14;
                    break;
                }
                View view2 = layoutState2.next(recycler2);
                LayoutParams lp2 = (LayoutParams) view2.getLayoutParams();
                int position = lp2.getViewLayoutPosition();
                int spanIndex = this.mLazySpanLookup.getSpan(position);
                boolean assignSpan = spanIndex == -1 ? z2 : r14;
                if (assignSpan) {
                    currentSpan = lp2.mFullSpan ? this.mSpans[r14] : getNextSpan(layoutState2);
                    this.mLazySpanLookup.setSpan(position, currentSpan);
                } else {
                    currentSpan = this.mSpans[spanIndex];
                }
                Span currentSpan3 = currentSpan;
                lp2.mSpan = currentSpan3;
                if (layoutState2.mLayoutDirection == z2) {
                    addView(view2);
                } else {
                    addView(view2, r14);
                }
                measureChildWithDecorationsAndMargin(view2, lp2, r14);
                if (layoutState2.mLayoutDirection == z2) {
                    if (lp2.mFullSpan) {
                        start2 = getMaxEnd(defaultNewViewLine2);
                    } else {
                        start2 = currentSpan3.getEndLine(defaultNewViewLine2);
                    }
                    int end3 = this.mPrimaryOrientation.getDecoratedMeasurement(view2) + start2;
                    if (assignSpan && lp2.mFullSpan) {
                        LazySpanLookup.FullSpanItem fullSpanItem = createFullSpanItemFromEnd(start2);
                        fullSpanItem.mGapDir = -1;
                        fullSpanItem.mPosition = position;
                        this.mLazySpanLookup.addFullSpanItem(fullSpanItem);
                    }
                    start = start2;
                    end = end3;
                } else {
                    if (lp2.mFullSpan != 0) {
                        end2 = getMinStart(defaultNewViewLine2);
                    } else {
                        end2 = currentSpan3.getStartLine(defaultNewViewLine2);
                    }
                    int start3 = end2 - this.mPrimaryOrientation.getDecoratedMeasurement(view2);
                    if (assignSpan && lp2.mFullSpan) {
                        LazySpanLookup.FullSpanItem fullSpanItem2 = createFullSpanItemFromStart(end2);
                        fullSpanItem2.mGapDir = z2;
                        fullSpanItem2.mPosition = position;
                        this.mLazySpanLookup.addFullSpanItem(fullSpanItem2);
                    }
                    end = end2;
                    start = start3;
                }
                if (lp2.mFullSpan != 0 && layoutState2.mItemDirection == -1) {
                    if (assignSpan) {
                        this.mLaidOutInvalidFullSpan = z2;
                    } else {
                        if (layoutState2.mLayoutDirection == z2) {
                            hasInvalidGap = areAllEndsEqual() ^ z2;
                        } else {
                            hasInvalidGap = areAllStartsEqual() ^ z2;
                        }
                        if (hasInvalidGap) {
                            LazySpanLookup.FullSpanItem fullSpanItem3 = this.mLazySpanLookup.getFullSpanItem(position);
                            if (fullSpanItem3 != null) {
                                fullSpanItem3.mHasUnwantedGapAfter = z2;
                            }
                            this.mLaidOutInvalidFullSpan = z2;
                        }
                    }
                }
                attachViewToSpans(view2, lp2, layoutState2);
                if (!isLayoutRTL() || this.mOrientation != z2) {
                    if (lp2.mFullSpan != 0) {
                        otherStart2 = this.mSecondaryOrientation.getStartAfterPadding();
                    } else {
                        otherStart2 = (currentSpan3.mIndex * this.mSizePerSpan) + this.mSecondaryOrientation.getStartAfterPadding();
                    }
                    otherStart = otherStart2;
                    otherEnd = this.mSecondaryOrientation.getDecoratedMeasurement(view2) + otherStart2;
                } else {
                    if (lp2.mFullSpan) {
                        otherEnd2 = this.mSecondaryOrientation.getEndAfterPadding();
                    } else {
                        otherEnd2 = this.mSecondaryOrientation.getEndAfterPadding() - (((this.mSpanCount - (z2 ? 1 : 0)) - currentSpan3.mIndex) * this.mSizePerSpan);
                    }
                    otherEnd = otherEnd2;
                    otherStart = otherEnd2 - this.mSecondaryOrientation.getDecoratedMeasurement(view2);
                }
                if (this.mOrientation == z2) {
                    currentSpan2 = currentSpan3;
                    int i5 = spanIndex;
                    layoutDecoratedWithMargins(view2, otherStart, start, otherEnd, end);
                    int i6 = position;
                    lp = lp2;
                    view = view2;
                    defaultNewViewLine = defaultNewViewLine2;
                    targetLine2 = targetLine3;
                } else {
                    currentSpan2 = currentSpan3;
                    int i7 = spanIndex;
                    lp = lp2;
                    view = view2;
                    defaultNewViewLine = defaultNewViewLine2;
                    int i8 = position;
                    targetLine2 = targetLine3;
                    layoutDecoratedWithMargins(view2, start, otherStart, end, otherEnd);
                }
                if (lp.mFullSpan) {
                    updateAllRemainingSpans(this.mLayoutState.mLayoutDirection, targetLine2);
                } else {
                    updateRemainingSpans(currentSpan2, this.mLayoutState.mLayoutDirection, targetLine2);
                }
                recycle(recycler2, this.mLayoutState);
                if (this.mLayoutState.mStopInFocusable && view.hasFocusable()) {
                    if (lp.mFullSpan) {
                        this.mRemainingSpans.clear();
                    } else {
                        z = false;
                        this.mRemainingSpans.set(currentSpan2.mIndex, false);
                        targetLine3 = targetLine2;
                        targetLine4 = 1;
                        defaultNewViewLine2 = defaultNewViewLine;
                        r14 = z;
                        z2 = true;
                    }
                }
                z = false;
                targetLine3 = targetLine2;
                targetLine4 = 1;
                defaultNewViewLine2 = defaultNewViewLine;
                r14 = z;
                z2 = true;
            } else {
                int i9 = targetLine3;
                i2 = r14;
                break;
            }
        }
        if (added == 0) {
            recycle(recycler2, this.mLayoutState);
        }
        if (this.mLayoutState.mLayoutDirection == -1) {
            diff = this.mPrimaryOrientation.getStartAfterPadding() - getMinStart(this.mPrimaryOrientation.getStartAfterPadding());
        } else {
            diff = getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding()) - this.mPrimaryOrientation.getEndAfterPadding();
        }
        int maxEnd = diff;
        return maxEnd > 0 ? Math.min(layoutState2.mAvailable, maxEnd) : i2;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromEnd(int newItemTop) {
        LazySpanLookup.FullSpanItem fsi = new LazySpanLookup.FullSpanItem();
        fsi.mGapPerSpan = new int[this.mSpanCount];
        for (int i = 0; i < this.mSpanCount; i++) {
            fsi.mGapPerSpan[i] = newItemTop - this.mSpans[i].getEndLine(newItemTop);
        }
        return fsi;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromStart(int newItemBottom) {
        LazySpanLookup.FullSpanItem fsi = new LazySpanLookup.FullSpanItem();
        fsi.mGapPerSpan = new int[this.mSpanCount];
        for (int i = 0; i < this.mSpanCount; i++) {
            fsi.mGapPerSpan[i] = this.mSpans[i].getStartLine(newItemBottom) - newItemBottom;
        }
        return fsi;
    }

    private void attachViewToSpans(View view, LayoutParams lp, LayoutState layoutState) {
        if (layoutState.mLayoutDirection == 1) {
            if (lp.mFullSpan) {
                appendViewToAllSpans(view);
            } else {
                lp.mSpan.appendToSpan(view);
            }
        } else if (lp.mFullSpan) {
            prependViewToAllSpans(view);
        } else {
            lp.mSpan.prependToSpan(view);
        }
    }

    private void recycle(RecyclerView.Recycler recycler, LayoutState layoutState) {
        int line;
        int line2;
        if (layoutState.mRecycle && !layoutState.mInfinite) {
            if (layoutState.mAvailable == 0) {
                if (layoutState.mLayoutDirection == -1) {
                    recycleFromEnd(recycler, layoutState.mEndLine);
                } else {
                    recycleFromStart(recycler, layoutState.mStartLine);
                }
            } else if (layoutState.mLayoutDirection == -1) {
                int scrolled = layoutState.mStartLine - getMaxStart(layoutState.mStartLine);
                if (scrolled < 0) {
                    line2 = layoutState.mEndLine;
                } else {
                    line2 = layoutState.mEndLine - Math.min(scrolled, layoutState.mAvailable);
                }
                recycleFromEnd(recycler, line2);
            } else {
                int scrolled2 = getMinEnd(layoutState.mEndLine) - layoutState.mEndLine;
                if (scrolled2 < 0) {
                    line = layoutState.mStartLine;
                } else {
                    line = layoutState.mStartLine + Math.min(scrolled2, layoutState.mAvailable);
                }
                recycleFromStart(recycler, line);
            }
        }
    }

    private void appendViewToAllSpans(View view) {
        for (int i = this.mSpanCount - 1; i >= 0; i--) {
            this.mSpans[i].appendToSpan(view);
        }
    }

    private void prependViewToAllSpans(View view) {
        for (int i = this.mSpanCount - 1; i >= 0; i--) {
            this.mSpans[i].prependToSpan(view);
        }
    }

    private void updateAllRemainingSpans(int layoutDir, int targetLine) {
        for (int i = 0; i < this.mSpanCount; i++) {
            if (!this.mSpans[i].mViews.isEmpty()) {
                updateRemainingSpans(this.mSpans[i], layoutDir, targetLine);
            }
        }
    }

    private void updateRemainingSpans(Span span, int layoutDir, int targetLine) {
        int deletedSize = span.getDeletedSize();
        if (layoutDir == -1) {
            if (span.getStartLine() + deletedSize <= targetLine) {
                this.mRemainingSpans.set(span.mIndex, false);
            }
        } else if (span.getEndLine() - deletedSize >= targetLine) {
            this.mRemainingSpans.set(span.mIndex, false);
        }
    }

    private int getMaxStart(int def) {
        int maxStart = this.mSpans[0].getStartLine(def);
        for (int i = 1; i < this.mSpanCount; i++) {
            int spanStart = this.mSpans[i].getStartLine(def);
            if (spanStart > maxStart) {
                maxStart = spanStart;
            }
        }
        return maxStart;
    }

    private int getMinStart(int def) {
        int minStart = this.mSpans[0].getStartLine(def);
        for (int i = 1; i < this.mSpanCount; i++) {
            int spanStart = this.mSpans[i].getStartLine(def);
            if (spanStart < minStart) {
                minStart = spanStart;
            }
        }
        return minStart;
    }

    /* access modifiers changed from: package-private */
    public boolean areAllEndsEqual() {
        int end = this.mSpans[0].getEndLine(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; i++) {
            if (this.mSpans[i].getEndLine(Integer.MIN_VALUE) != end) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean areAllStartsEqual() {
        int start = this.mSpans[0].getStartLine(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; i++) {
            if (this.mSpans[i].getStartLine(Integer.MIN_VALUE) != start) {
                return false;
            }
        }
        return true;
    }

    private int getMaxEnd(int def) {
        int maxEnd = this.mSpans[0].getEndLine(def);
        for (int i = 1; i < this.mSpanCount; i++) {
            int spanEnd = this.mSpans[i].getEndLine(def);
            if (spanEnd > maxEnd) {
                maxEnd = spanEnd;
            }
        }
        return maxEnd;
    }

    private int getMinEnd(int def) {
        int minEnd = this.mSpans[0].getEndLine(def);
        for (int i = 1; i < this.mSpanCount; i++) {
            int spanEnd = this.mSpans[i].getEndLine(def);
            if (spanEnd < minEnd) {
                minEnd = spanEnd;
            }
        }
        return minEnd;
    }

    private void recycleFromStart(RecyclerView.Recycler recycler, int line) {
        while (getChildCount() > 0) {
            View child = getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(child) <= line && this.mPrimaryOrientation.getTransformedEndWithDecoration(child) <= line) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.mFullSpan) {
                    int j = 0;
                    while (j < this.mSpanCount) {
                        if (this.mSpans[j].mViews.size() != 1) {
                            j++;
                        } else {
                            return;
                        }
                    }
                    for (int j2 = 0; j2 < this.mSpanCount; j2++) {
                        this.mSpans[j2].popStart();
                    }
                } else if (lp.mSpan.mViews.size() != 1) {
                    lp.mSpan.popStart();
                } else {
                    return;
                }
                removeAndRecycleView(child, recycler);
            } else {
                return;
            }
        }
    }

    private void recycleFromEnd(RecyclerView.Recycler recycler, int line) {
        int i = getChildCount() - 1;
        while (i >= 0) {
            View child = getChildAt(i);
            if (this.mPrimaryOrientation.getDecoratedStart(child) >= line && this.mPrimaryOrientation.getTransformedStartWithDecoration(child) >= line) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.mFullSpan) {
                    int j = 0;
                    while (j < this.mSpanCount) {
                        if (this.mSpans[j].mViews.size() != 1) {
                            j++;
                        } else {
                            return;
                        }
                    }
                    for (int j2 = 0; j2 < this.mSpanCount; j2++) {
                        this.mSpans[j2].popEnd();
                    }
                } else if (lp.mSpan.mViews.size() != 1) {
                    lp.mSpan.popEnd();
                } else {
                    return;
                }
                removeAndRecycleView(child, recycler);
                i--;
            } else {
                return;
            }
        }
    }

    private boolean preferLastSpan(int layoutDir) {
        if (this.mOrientation == 0) {
            if ((layoutDir == -1) != this.mShouldReverseLayout) {
                return true;
            }
            return false;
        }
        if (((layoutDir == -1) == this.mShouldReverseLayout) == isLayoutRTL()) {
            return true;
        }
        return false;
    }

    private Span getNextSpan(LayoutState layoutState) {
        int diff;
        int endIndex;
        int startIndex;
        if (preferLastSpan(layoutState.mLayoutDirection)) {
            startIndex = this.mSpanCount - 1;
            endIndex = -1;
            diff = -1;
        } else {
            startIndex = 0;
            endIndex = this.mSpanCount;
            diff = 1;
        }
        if (layoutState.mLayoutDirection == 1) {
            int minLine = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            int defaultLine = this.mPrimaryOrientation.getStartAfterPadding();
            Span min = null;
            for (int i = startIndex; i != endIndex; i += diff) {
                Span other = this.mSpans[i];
                int otherLine = other.getEndLine(defaultLine);
                if (otherLine < minLine) {
                    min = other;
                    minLine = otherLine;
                }
            }
            return min;
        }
        int maxLine = Integer.MIN_VALUE;
        int defaultLine2 = this.mPrimaryOrientation.getEndAfterPadding();
        Span max = null;
        for (int i2 = startIndex; i2 != endIndex; i2 += diff) {
            Span other2 = this.mSpans[i2];
            int otherLine2 = other2.getStartLine(defaultLine2);
            if (otherLine2 > maxLine) {
                max = other2;
                maxLine = otherLine2;
            }
        }
        return max;
    }

    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(dx, recycler, state);
    }

    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(dy, recycler, state);
    }

    private int calculateScrollDirectionForPosition(int position) {
        if (getChildCount() != 0) {
            if ((position < getFirstChildPosition()) != this.mShouldReverseLayout) {
                return -1;
            }
            return 1;
        } else if (this.mShouldReverseLayout) {
            return 1;
        } else {
            return -1;
        }
    }

    public PointF computeScrollVectorForPosition(int targetPosition) {
        int direction = calculateScrollDirectionForPosition(targetPosition);
        PointF outVector = new PointF();
        if (direction == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            outVector.x = (float) direction;
            outVector.y = 0.0f;
        } else {
            outVector.x = 0.0f;
            outVector.y = (float) direction;
        }
        return outVector;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller scroller = new LinearSmoothScroller(recyclerView.getContext());
        scroller.setTargetPosition(position);
        startSmoothScroll(scroller);
    }

    public void scrollToPosition(int position) {
        if (!(this.mPendingSavedState == null || this.mPendingSavedState.mAnchorPosition == position)) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = position;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        requestLayout();
    }

    public void scrollToPositionWithOffset(int position, int offset) {
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = position;
        this.mPendingScrollPositionOffset = offset;
        requestLayout();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void collectAdjacentPrefetchPositions(int dx, int dy, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int distance;
        int delta = this.mOrientation == 0 ? dx : dy;
        if (getChildCount() != 0 && delta != 0) {
            prepareLayoutStateForDelta(delta, state);
            if (this.mPrefetchDistances == null || this.mPrefetchDistances.length < this.mSpanCount) {
                this.mPrefetchDistances = new int[this.mSpanCount];
            }
            int i = 0;
            int itemPrefetchCount = 0;
            for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                if (this.mLayoutState.mItemDirection == -1) {
                    distance = this.mLayoutState.mStartLine - this.mSpans[i2].getStartLine(this.mLayoutState.mStartLine);
                } else {
                    distance = this.mSpans[i2].getEndLine(this.mLayoutState.mEndLine) - this.mLayoutState.mEndLine;
                }
                if (distance >= 0) {
                    this.mPrefetchDistances[itemPrefetchCount] = distance;
                    itemPrefetchCount++;
                }
            }
            Arrays.sort(this.mPrefetchDistances, 0, itemPrefetchCount);
            while (true) {
                int i3 = i;
                if (i3 < itemPrefetchCount && this.mLayoutState.hasMore(state)) {
                    layoutPrefetchRegistry.addPosition(this.mLayoutState.mCurrentPosition, this.mPrefetchDistances[i3]);
                    this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
                    i = i3 + 1;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void prepareLayoutStateForDelta(int delta, RecyclerView.State state) {
        int referenceChildPosition;
        int layoutDir;
        if (delta > 0) {
            layoutDir = 1;
            referenceChildPosition = getLastChildPosition();
        } else {
            layoutDir = -1;
            referenceChildPosition = getFirstChildPosition();
        }
        this.mLayoutState.mRecycle = true;
        updateLayoutState(referenceChildPosition, state);
        setLayoutStateDirection(layoutDir);
        this.mLayoutState.mCurrentPosition = this.mLayoutState.mItemDirection + referenceChildPosition;
        this.mLayoutState.mAvailable = Math.abs(delta);
    }

    /* access modifiers changed from: package-private */
    public int scrollBy(int dt, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int totalScroll;
        if (getChildCount() == 0 || dt == 0) {
            return 0;
        }
        prepareLayoutStateForDelta(dt, state);
        int consumed = fill(recycler, this.mLayoutState, state);
        if (this.mLayoutState.mAvailable < consumed) {
            totalScroll = dt;
        } else if (dt < 0) {
            totalScroll = -consumed;
        } else {
            totalScroll = consumed;
        }
        this.mPrimaryOrientation.offsetChildren(-totalScroll);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        this.mLayoutState.mAvailable = 0;
        recycle(recycler, this.mLayoutState);
        return totalScroll;
    }

    /* access modifiers changed from: package-private */
    public int getLastChildPosition() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    /* access modifiers changed from: package-private */
    public int getFirstChildPosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    private int findFirstReferenceChildPosition(int itemCount) {
        int limit = getChildCount();
        for (int i = 0; i < limit; i++) {
            int position = getPosition(getChildAt(i));
            if (position >= 0 && position < itemCount) {
                return position;
            }
        }
        return 0;
    }

    private int findLastReferenceChildPosition(int itemCount) {
        for (int i = getChildCount() - 1; i >= 0; i--) {
            int position = getPosition(getChildAt(i));
            if (position >= 0 && position < itemCount) {
                return position;
            }
        }
        return 0;
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

    public int getOrientation() {
        return this.mOrientation;
    }

    @Nullable
    public View onFocusSearchFailed(View focused, int direction, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View directChild;
        int referenceChildPosition;
        int i;
        int i2;
        int i3;
        View view;
        RecyclerView.State state2 = state;
        if (getChildCount() == 0 || (directChild = findContainingItemView(focused)) == null) {
            return null;
        }
        resolveShouldLayoutReverse();
        int layoutDir = convertFocusDirectionToLayoutDirection(direction);
        if (layoutDir == Integer.MIN_VALUE) {
            return null;
        }
        LayoutParams prevFocusLayoutParams = (LayoutParams) directChild.getLayoutParams();
        boolean prevFocusFullSpan = prevFocusLayoutParams.mFullSpan;
        Span prevFocusSpan = prevFocusLayoutParams.mSpan;
        if (layoutDir == 1) {
            referenceChildPosition = getLastChildPosition();
        } else {
            referenceChildPosition = getFirstChildPosition();
        }
        updateLayoutState(referenceChildPosition, state2);
        setLayoutStateDirection(layoutDir);
        this.mLayoutState.mCurrentPosition = this.mLayoutState.mItemDirection + referenceChildPosition;
        this.mLayoutState.mAvailable = (int) (((float) this.mPrimaryOrientation.getTotalSpace()) * MAX_SCROLL_FACTOR);
        this.mLayoutState.mStopInFocusable = true;
        int i4 = 0;
        this.mLayoutState.mRecycle = false;
        fill(recycler, this.mLayoutState, state2);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        if (!prevFocusFullSpan && (view = prevFocusSpan.getFocusableViewAfter(referenceChildPosition, layoutDir)) != null && view != directChild) {
            return view;
        }
        if (preferLastSpan(layoutDir)) {
            for (int i5 = this.mSpanCount - 1; i5 >= 0; i5--) {
                View view2 = this.mSpans[i5].getFocusableViewAfter(referenceChildPosition, layoutDir);
                if (view2 != null && view2 != directChild) {
                    return view2;
                }
            }
        } else {
            for (int i6 = 0; i6 < this.mSpanCount; i6++) {
                View view3 = this.mSpans[i6].getFocusableViewAfter(referenceChildPosition, layoutDir);
                if (view3 != null && view3 != directChild) {
                    return view3;
                }
            }
        }
        boolean shouldSearchFromStart = (this.mReverseLayout ^ 1) == (layoutDir == -1 ? 1 : 0);
        if (!prevFocusFullSpan) {
            if (shouldSearchFromStart) {
                i3 = prevFocusSpan.findFirstPartiallyVisibleItemPosition();
            } else {
                i3 = prevFocusSpan.findLastPartiallyVisibleItemPosition();
            }
            View unfocusableCandidate = findViewByPosition(i3);
            if (!(unfocusableCandidate == null || unfocusableCandidate == directChild)) {
                return unfocusableCandidate;
            }
        }
        if (!preferLastSpan(layoutDir)) {
            while (true) {
                int i7 = i4;
                if (i7 >= this.mSpanCount) {
                    break;
                }
                if (shouldSearchFromStart) {
                    i = this.mSpans[i7].findFirstPartiallyVisibleItemPosition();
                } else {
                    i = this.mSpans[i7].findLastPartiallyVisibleItemPosition();
                }
                View unfocusableCandidate2 = findViewByPosition(i);
                if (unfocusableCandidate2 != null && unfocusableCandidate2 != directChild) {
                    return unfocusableCandidate2;
                }
                i4 = i7 + 1;
            }
        } else {
            int i8 = this.mSpanCount - 1;
            while (true) {
                int i9 = i8;
                if (i9 < 0) {
                    break;
                }
                if (i9 != prevFocusSpan.mIndex) {
                    if (shouldSearchFromStart) {
                        i2 = this.mSpans[i9].findFirstPartiallyVisibleItemPosition();
                    } else {
                        i2 = this.mSpans[i9].findLastPartiallyVisibleItemPosition();
                    }
                    View unfocusableCandidate3 = findViewByPosition(i2);
                    if (unfocusableCandidate3 != null && unfocusableCandidate3 != directChild) {
                        return unfocusableCandidate3;
                    }
                    View view4 = unfocusableCandidate3;
                }
                i8 = i9 - 1;
            }
        }
        return null;
    }

    private int convertFocusDirectionToLayoutDirection(int focusDirection) {
        if (focusDirection != 17) {
            if (focusDirection != 33) {
                if (focusDirection != 66) {
                    if (focusDirection != 130) {
                        switch (focusDirection) {
                            case 1:
                                return (this.mOrientation != 1 && isLayoutRTL()) ? 1 : -1;
                            case 2:
                                return (this.mOrientation != 1 && isLayoutRTL()) ? -1 : 1;
                            default:
                                return Integer.MIN_VALUE;
                        }
                    } else if (this.mOrientation == 1) {
                        return 1;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                } else if (this.mOrientation == 0) {
                    return 1;
                } else {
                    return Integer.MIN_VALUE;
                }
            } else if (this.mOrientation == 1) {
                return -1;
            } else {
                return Integer.MIN_VALUE;
            }
        } else if (this.mOrientation == 0) {
            return -1;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        boolean mFullSpan;
        Span mSpan;

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

        public void setFullSpan(boolean fullSpan) {
            this.mFullSpan = fullSpan;
        }

        public boolean isFullSpan() {
            return this.mFullSpan;
        }

        public final int getSpanIndex() {
            if (this.mSpan == null) {
                return -1;
            }
            return this.mSpan.mIndex;
        }
    }

    class Span {
        static final int INVALID_LINE = Integer.MIN_VALUE;
        int mCachedEnd = Integer.MIN_VALUE;
        int mCachedStart = Integer.MIN_VALUE;
        int mDeletedSize = 0;
        final int mIndex;
        ArrayList<View> mViews = new ArrayList<>();

        Span(int index) {
            this.mIndex = index;
        }

        /* access modifiers changed from: package-private */
        public int getStartLine(int def) {
            if (this.mCachedStart != Integer.MIN_VALUE) {
                return this.mCachedStart;
            }
            if (this.mViews.size() == 0) {
                return def;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }

        /* access modifiers changed from: package-private */
        public void calculateCachedStart() {
            LazySpanLookup.FullSpanItem fsi;
            View startView = this.mViews.get(0);
            LayoutParams lp = getLayoutParams(startView);
            this.mCachedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(startView);
            if (lp.mFullSpan && (fsi = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(lp.getViewLayoutPosition())) != null && fsi.mGapDir == -1) {
                this.mCachedStart -= fsi.getGapForSpan(this.mIndex);
            }
        }

        /* access modifiers changed from: package-private */
        public int getStartLine() {
            if (this.mCachedStart != Integer.MIN_VALUE) {
                return this.mCachedStart;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }

        /* access modifiers changed from: package-private */
        public int getEndLine(int def) {
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                return this.mCachedEnd;
            }
            if (this.mViews.size() == 0) {
                return def;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        /* access modifiers changed from: package-private */
        public void calculateCachedEnd() {
            LazySpanLookup.FullSpanItem fsi;
            View endView = this.mViews.get(this.mViews.size() - 1);
            LayoutParams lp = getLayoutParams(endView);
            this.mCachedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(endView);
            if (lp.mFullSpan && (fsi = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(lp.getViewLayoutPosition())) != null && fsi.mGapDir == 1) {
                this.mCachedEnd += fsi.getGapForSpan(this.mIndex);
            }
        }

        /* access modifiers changed from: package-private */
        public int getEndLine() {
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                return this.mCachedEnd;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        /* access modifiers changed from: package-private */
        public void prependToSpan(View view) {
            LayoutParams lp = getLayoutParams(view);
            lp.mSpan = this;
            this.mViews.add(0, view);
            this.mCachedStart = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (lp.isItemRemoved() || lp.isItemChanged()) {
                this.mDeletedSize += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
        }

        /* access modifiers changed from: package-private */
        public void appendToSpan(View view) {
            LayoutParams lp = getLayoutParams(view);
            lp.mSpan = this;
            this.mViews.add(view);
            this.mCachedEnd = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            if (lp.isItemRemoved() || lp.isItemChanged()) {
                this.mDeletedSize += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
        }

        /* access modifiers changed from: package-private */
        public void cacheReferenceLineAndClear(boolean reverseLayout, int offset) {
            int reference;
            if (reverseLayout) {
                reference = getEndLine(Integer.MIN_VALUE);
            } else {
                reference = getStartLine(Integer.MIN_VALUE);
            }
            clear();
            if (reference != Integer.MIN_VALUE) {
                if (reverseLayout && reference < StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding()) {
                    return;
                }
                if (reverseLayout || reference <= StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding()) {
                    if (offset != Integer.MIN_VALUE) {
                        reference += offset;
                    }
                    this.mCachedEnd = reference;
                    this.mCachedStart = reference;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            this.mViews.clear();
            invalidateCache();
            this.mDeletedSize = 0;
        }

        /* access modifiers changed from: package-private */
        public void invalidateCache() {
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        /* access modifiers changed from: package-private */
        public void setLine(int line) {
            this.mCachedStart = line;
            this.mCachedEnd = line;
        }

        /* access modifiers changed from: package-private */
        public void popEnd() {
            int size = this.mViews.size();
            View end = this.mViews.remove(size - 1);
            LayoutParams lp = getLayoutParams(end);
            lp.mSpan = null;
            if (lp.isItemRemoved() || lp.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(end);
            }
            if (size == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        /* access modifiers changed from: package-private */
        public void popStart() {
            View start = this.mViews.remove(0);
            LayoutParams lp = getLayoutParams(start);
            lp.mSpan = null;
            if (this.mViews.size() == 0) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (lp.isItemRemoved() || lp.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(start);
            }
            this.mCachedStart = Integer.MIN_VALUE;
        }

        public int getDeletedSize() {
            return this.mDeletedSize;
        }

        /* access modifiers changed from: package-private */
        public LayoutParams getLayoutParams(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        /* access modifiers changed from: package-private */
        public void onOffset(int dt) {
            if (this.mCachedStart != Integer.MIN_VALUE) {
                this.mCachedStart += dt;
            }
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                this.mCachedEnd += dt;
            }
        }

        public int findFirstVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOneVisibleChild(this.mViews.size() - 1, -1, false);
            }
            return findOneVisibleChild(0, this.mViews.size(), false);
        }

        public int findFirstPartiallyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
            }
            return findOnePartiallyVisibleChild(0, this.mViews.size(), true);
        }

        public int findFirstCompletelyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOneVisibleChild(this.mViews.size() - 1, -1, true);
            }
            return findOneVisibleChild(0, this.mViews.size(), true);
        }

        public int findLastVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOneVisibleChild(0, this.mViews.size(), false);
            }
            return findOneVisibleChild(this.mViews.size() - 1, -1, false);
        }

        public int findLastPartiallyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOnePartiallyVisibleChild(0, this.mViews.size(), true);
            }
            return findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
        }

        public int findLastCompletelyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOneVisibleChild(0, this.mViews.size(), true);
            }
            return findOneVisibleChild(this.mViews.size() - 1, -1, true);
        }

        /* access modifiers changed from: package-private */
        public int findOnePartiallyOrCompletelyVisibleChild(int fromIndex, int toIndex, boolean completelyVisible, boolean acceptCompletelyVisible, boolean acceptEndPointInclusion) {
            int i = toIndex;
            int start = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            int end = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            int i2 = fromIndex;
            int next = i > i2 ? 1 : -1;
            for (int i3 = i2; i3 != i; i3 += next) {
                View child = this.mViews.get(i3);
                int childStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(child);
                int childEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(child);
                boolean childEndInclusion = false;
                boolean childStartInclusion = !acceptEndPointInclusion ? childStart < end : childStart <= end;
                if (!acceptEndPointInclusion ? childEnd > start : childEnd >= start) {
                    childEndInclusion = true;
                }
                if (childStartInclusion && childEndInclusion) {
                    if (!completelyVisible || !acceptCompletelyVisible) {
                        if (acceptCompletelyVisible) {
                            return StaggeredGridLayoutManager.this.getPosition(child);
                        }
                        if (childStart < start || childEnd > end) {
                            return StaggeredGridLayoutManager.this.getPosition(child);
                        }
                    } else if (childStart >= start && childEnd <= end) {
                        return StaggeredGridLayoutManager.this.getPosition(child);
                    }
                }
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public int findOneVisibleChild(int fromIndex, int toIndex, boolean completelyVisible) {
            return findOnePartiallyOrCompletelyVisibleChild(fromIndex, toIndex, completelyVisible, true, false);
        }

        /* access modifiers changed from: package-private */
        public int findOnePartiallyVisibleChild(int fromIndex, int toIndex, boolean acceptEndPointInclusion) {
            return findOnePartiallyOrCompletelyVisibleChild(fromIndex, toIndex, false, false, acceptEndPointInclusion);
        }

        public View getFocusableViewAfter(int referenceChildPosition, int layoutDir) {
            View candidate = null;
            if (layoutDir != -1) {
                for (int i = this.mViews.size() - 1; i >= 0; i--) {
                    View view = this.mViews.get(i);
                    if ((StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view) >= referenceChildPosition) || ((!StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view) <= referenceChildPosition) || !view.hasFocusable())) {
                        break;
                    }
                    candidate = view;
                }
            } else {
                int limit = this.mViews.size();
                for (int i2 = 0; i2 < limit; i2++) {
                    View view2 = this.mViews.get(i2);
                    if ((StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view2) <= referenceChildPosition) || ((!StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view2) >= referenceChildPosition) || !view2.hasFocusable())) {
                        break;
                    }
                    candidate = view2;
                }
            }
            return candidate;
        }
    }

    static class LazySpanLookup {
        private static final int MIN_SIZE = 10;
        int[] mData;
        List<FullSpanItem> mFullSpanItems;

        LazySpanLookup() {
        }

        /* access modifiers changed from: package-private */
        public int forceInvalidateAfter(int position) {
            if (this.mFullSpanItems != null) {
                for (int i = this.mFullSpanItems.size() - 1; i >= 0; i--) {
                    if (this.mFullSpanItems.get(i).mPosition >= position) {
                        this.mFullSpanItems.remove(i);
                    }
                }
            }
            return invalidateAfter(position);
        }

        /* access modifiers changed from: package-private */
        public int invalidateAfter(int position) {
            if (this.mData == null || position >= this.mData.length) {
                return -1;
            }
            int endPosition = invalidateFullSpansAfter(position);
            if (endPosition == -1) {
                Arrays.fill(this.mData, position, this.mData.length, -1);
                return this.mData.length;
            }
            Arrays.fill(this.mData, position, endPosition + 1, -1);
            return endPosition + 1;
        }

        /* access modifiers changed from: package-private */
        public int getSpan(int position) {
            if (this.mData == null || position >= this.mData.length) {
                return -1;
            }
            return this.mData[position];
        }

        /* access modifiers changed from: package-private */
        public void setSpan(int position, Span span) {
            ensureSize(position);
            this.mData[position] = span.mIndex;
        }

        /* access modifiers changed from: package-private */
        public int sizeForPosition(int position) {
            int len = this.mData.length;
            while (len <= position) {
                len *= 2;
            }
            return len;
        }

        /* access modifiers changed from: package-private */
        public void ensureSize(int position) {
            if (this.mData == null) {
                this.mData = new int[(Math.max(position, 10) + 1)];
                Arrays.fill(this.mData, -1);
            } else if (position >= this.mData.length) {
                int[] old = this.mData;
                this.mData = new int[sizeForPosition(position)];
                System.arraycopy(old, 0, this.mData, 0, old.length);
                Arrays.fill(this.mData, old.length, this.mData.length, -1);
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            if (this.mData != null) {
                Arrays.fill(this.mData, -1);
            }
            this.mFullSpanItems = null;
        }

        /* access modifiers changed from: package-private */
        public void offsetForRemoval(int positionStart, int itemCount) {
            if (this.mData != null && positionStart < this.mData.length) {
                ensureSize(positionStart + itemCount);
                System.arraycopy(this.mData, positionStart + itemCount, this.mData, positionStart, (this.mData.length - positionStart) - itemCount);
                Arrays.fill(this.mData, this.mData.length - itemCount, this.mData.length, -1);
                offsetFullSpansForRemoval(positionStart, itemCount);
            }
        }

        private void offsetFullSpansForRemoval(int positionStart, int itemCount) {
            if (this.mFullSpanItems != null) {
                int end = positionStart + itemCount;
                for (int i = this.mFullSpanItems.size() - 1; i >= 0; i--) {
                    FullSpanItem fsi = this.mFullSpanItems.get(i);
                    if (fsi.mPosition >= positionStart) {
                        if (fsi.mPosition < end) {
                            this.mFullSpanItems.remove(i);
                        } else {
                            fsi.mPosition -= itemCount;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void offsetForAddition(int positionStart, int itemCount) {
            if (this.mData != null && positionStart < this.mData.length) {
                ensureSize(positionStart + itemCount);
                System.arraycopy(this.mData, positionStart, this.mData, positionStart + itemCount, (this.mData.length - positionStart) - itemCount);
                Arrays.fill(this.mData, positionStart, positionStart + itemCount, -1);
                offsetFullSpansForAddition(positionStart, itemCount);
            }
        }

        private void offsetFullSpansForAddition(int positionStart, int itemCount) {
            if (this.mFullSpanItems != null) {
                for (int i = this.mFullSpanItems.size() - 1; i >= 0; i--) {
                    FullSpanItem fsi = this.mFullSpanItems.get(i);
                    if (fsi.mPosition >= positionStart) {
                        fsi.mPosition += itemCount;
                    }
                }
            }
        }

        private int invalidateFullSpansAfter(int position) {
            if (this.mFullSpanItems == null) {
                return -1;
            }
            FullSpanItem item = getFullSpanItem(position);
            if (item != null) {
                this.mFullSpanItems.remove(item);
            }
            int nextFsiIndex = -1;
            int count = this.mFullSpanItems.size();
            int i = 0;
            while (true) {
                if (i >= count) {
                    break;
                } else if (this.mFullSpanItems.get(i).mPosition >= position) {
                    nextFsiIndex = i;
                    break;
                } else {
                    i++;
                }
            }
            if (nextFsiIndex == -1) {
                return -1;
            }
            this.mFullSpanItems.remove(nextFsiIndex);
            return this.mFullSpanItems.get(nextFsiIndex).mPosition;
        }

        public void addFullSpanItem(FullSpanItem fullSpanItem) {
            if (this.mFullSpanItems == null) {
                this.mFullSpanItems = new ArrayList();
            }
            int size = this.mFullSpanItems.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem other = this.mFullSpanItems.get(i);
                if (other.mPosition == fullSpanItem.mPosition) {
                    this.mFullSpanItems.remove(i);
                }
                if (other.mPosition >= fullSpanItem.mPosition) {
                    this.mFullSpanItems.add(i, fullSpanItem);
                    return;
                }
            }
            this.mFullSpanItems.add(fullSpanItem);
        }

        public FullSpanItem getFullSpanItem(int position) {
            if (this.mFullSpanItems == null) {
                return null;
            }
            for (int i = this.mFullSpanItems.size() - 1; i >= 0; i--) {
                FullSpanItem fsi = this.mFullSpanItems.get(i);
                if (fsi.mPosition == position) {
                    return fsi;
                }
            }
            return null;
        }

        public FullSpanItem getFirstFullSpanItemInRange(int minPos, int maxPos, int gapDir, boolean hasUnwantedGapAfter) {
            if (this.mFullSpanItems == null) {
                return null;
            }
            int limit = this.mFullSpanItems.size();
            for (int i = 0; i < limit; i++) {
                FullSpanItem fsi = this.mFullSpanItems.get(i);
                if (fsi.mPosition >= maxPos) {
                    return null;
                }
                if (fsi.mPosition >= minPos && (gapDir == 0 || fsi.mGapDir == gapDir || (hasUnwantedGapAfter && fsi.mHasUnwantedGapAfter))) {
                    return fsi;
                }
            }
            return null;
        }

        static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new Parcelable.Creator<FullSpanItem>() {
                public FullSpanItem createFromParcel(Parcel in) {
                    return new FullSpanItem(in);
                }

                public FullSpanItem[] newArray(int size) {
                    return new FullSpanItem[size];
                }
            };
            int mGapDir;
            int[] mGapPerSpan;
            boolean mHasUnwantedGapAfter;
            int mPosition;

            FullSpanItem(Parcel in) {
                this.mPosition = in.readInt();
                this.mGapDir = in.readInt();
                this.mHasUnwantedGapAfter = in.readInt() != 1 ? false : true;
                int spanCount = in.readInt();
                if (spanCount > 0) {
                    this.mGapPerSpan = new int[spanCount];
                    in.readIntArray(this.mGapPerSpan);
                }
            }

            FullSpanItem() {
            }

            /* access modifiers changed from: package-private */
            public int getGapForSpan(int spanIndex) {
                if (this.mGapPerSpan == null) {
                    return 0;
                }
                return this.mGapPerSpan[spanIndex];
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.mPosition);
                dest.writeInt(this.mGapDir);
                dest.writeInt(this.mHasUnwantedGapAfter ? 1 : 0);
                if (this.mGapPerSpan == null || this.mGapPerSpan.length <= 0) {
                    dest.writeInt(0);
                    return;
                }
                dest.writeInt(this.mGapPerSpan.length);
                dest.writeIntArray(this.mGapPerSpan);
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.mPosition + ", mGapDir=" + this.mGapDir + ", mHasUnwantedGapAfter=" + this.mHasUnwantedGapAfter + ", mGapPerSpan=" + Arrays.toString(this.mGapPerSpan) + '}';
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        boolean mAnchorLayoutFromEnd;
        int mAnchorPosition;
        List<LazySpanLookup.FullSpanItem> mFullSpanItems;
        boolean mLastLayoutRTL;
        boolean mReverseLayout;
        int[] mSpanLookup;
        int mSpanLookupSize;
        int[] mSpanOffsets;
        int mSpanOffsetsSize;
        int mVisibleAnchorPosition;

        public SavedState() {
        }

        SavedState(Parcel in) {
            this.mAnchorPosition = in.readInt();
            this.mVisibleAnchorPosition = in.readInt();
            this.mSpanOffsetsSize = in.readInt();
            if (this.mSpanOffsetsSize > 0) {
                this.mSpanOffsets = new int[this.mSpanOffsetsSize];
                in.readIntArray(this.mSpanOffsets);
            }
            this.mSpanLookupSize = in.readInt();
            if (this.mSpanLookupSize > 0) {
                this.mSpanLookup = new int[this.mSpanLookupSize];
                in.readIntArray(this.mSpanLookup);
            }
            boolean z = false;
            this.mReverseLayout = in.readInt() == 1;
            this.mAnchorLayoutFromEnd = in.readInt() == 1;
            this.mLastLayoutRTL = in.readInt() == 1 ? true : z;
            this.mFullSpanItems = in.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState other) {
            this.mSpanOffsetsSize = other.mSpanOffsetsSize;
            this.mAnchorPosition = other.mAnchorPosition;
            this.mVisibleAnchorPosition = other.mVisibleAnchorPosition;
            this.mSpanOffsets = other.mSpanOffsets;
            this.mSpanLookupSize = other.mSpanLookupSize;
            this.mSpanLookup = other.mSpanLookup;
            this.mReverseLayout = other.mReverseLayout;
            this.mAnchorLayoutFromEnd = other.mAnchorLayoutFromEnd;
            this.mLastLayoutRTL = other.mLastLayoutRTL;
            this.mFullSpanItems = other.mFullSpanItems;
        }

        /* access modifiers changed from: package-private */
        public void invalidateSpanInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mSpanLookupSize = 0;
            this.mSpanLookup = null;
            this.mFullSpanItems = null;
        }

        /* access modifiers changed from: package-private */
        public void invalidateAnchorPositionInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mAnchorPosition = -1;
            this.mVisibleAnchorPosition = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mAnchorPosition);
            dest.writeInt(this.mVisibleAnchorPosition);
            dest.writeInt(this.mSpanOffsetsSize);
            if (this.mSpanOffsetsSize > 0) {
                dest.writeIntArray(this.mSpanOffsets);
            }
            dest.writeInt(this.mSpanLookupSize);
            if (this.mSpanLookupSize > 0) {
                dest.writeIntArray(this.mSpanLookup);
            }
            dest.writeInt(this.mReverseLayout ? 1 : 0);
            dest.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
            dest.writeInt(this.mLastLayoutRTL ? 1 : 0);
            dest.writeList(this.mFullSpanItems);
        }
    }

    class AnchorInfo {
        boolean mInvalidateOffsets;
        boolean mLayoutFromEnd;
        int mOffset;
        int mPosition;
        int[] mSpanReferenceLines;
        boolean mValid;

        AnchorInfo() {
            reset();
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.mPosition = -1;
            this.mOffset = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mInvalidateOffsets = false;
            this.mValid = false;
            if (this.mSpanReferenceLines != null) {
                Arrays.fill(this.mSpanReferenceLines, -1);
            }
        }

        /* access modifiers changed from: package-private */
        public void saveSpanReferenceLines(Span[] spans) {
            int spanCount = spans.length;
            if (this.mSpanReferenceLines == null || this.mSpanReferenceLines.length < spanCount) {
                this.mSpanReferenceLines = new int[StaggeredGridLayoutManager.this.mSpans.length];
            }
            for (int i = 0; i < spanCount; i++) {
                this.mSpanReferenceLines[i] = spans[i].getStartLine(Integer.MIN_VALUE);
            }
        }

        /* access modifiers changed from: package-private */
        public void assignCoordinateFromPadding() {
            int i;
            if (this.mLayoutFromEnd) {
                i = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            } else {
                i = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            }
            this.mOffset = i;
        }

        /* access modifiers changed from: package-private */
        public void assignCoordinateFromPadding(int addedDistance) {
            if (this.mLayoutFromEnd) {
                this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() - addedDistance;
            } else {
                this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding() + addedDistance;
            }
        }
    }
}
