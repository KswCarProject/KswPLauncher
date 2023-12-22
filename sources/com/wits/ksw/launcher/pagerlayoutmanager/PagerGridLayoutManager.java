package com.wits.ksw.launcher.pagerlayoutmanager;

import android.graphics.PointF;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import android.support.p004v7.widget.LinearSmoothScroller;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

/* loaded from: classes7.dex */
public class PagerGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    public static final int HORIZONTAL = 1;
    private static final String TAG = PagerGridLayoutManager.class.getSimpleName();
    public static final int VERTICAL = 0;
    private int mColumns;
    private int mMaxScrollX;
    private int mMaxScrollY;
    private int mOnePageSize;
    private int mOrientation;
    private RecyclerView mRecyclerView;
    private int mRows;
    private int mOffsetX = 0;
    private int mOffsetY = 0;
    private int mItemWidth = 0;
    private int mItemHeight = 0;
    private int mWidthUsed = 0;
    private int mHeightUsed = 0;
    private int mScrollState = 0;
    private boolean mAllowContinuousScroll = true;
    private boolean mChangeSelectInScrolling = true;
    private int mLastPageCount = -1;
    private int mLastPageIndex = -1;
    private PageListener mPageListener = null;
    private SparseArray<Rect> mItemFrames = new SparseArray<>();

    /* loaded from: classes7.dex */
    public @interface OrientationType {
    }

    /* loaded from: classes7.dex */
    public interface PageListener {
        void onPageSelect(int pageIndex);

        void onPageSizeChanged(int pageSize);
    }

    public PagerGridLayoutManager(int rows, int columns, int orientation) {
        this.mOrientation = orientation;
        this.mRows = rows;
        this.mColumns = columns;
        this.mOnePageSize = rows * columns;
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        this.mRecyclerView = view;
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        PagerConfig.Logi("Item onLayoutChildren");
        PagerConfig.Logi("Item onLayoutChildren isPreLayout = " + state.isPreLayout());
        PagerConfig.Logi("Item onLayoutChildren isMeasuring = " + state.isMeasuring());
        PagerConfig.Loge("Item onLayoutChildren state = " + state);
        if (state.isPreLayout() || !state.didStructureChange()) {
            return;
        }
        if (getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            setPageCount(0);
            setPageIndex(0, false);
            return;
        }
        setPageCount(getTotalPageCount());
        setPageIndex(getPageIndexByOffset(), false);
        int mPageCount = getItemCount() / this.mOnePageSize;
        if (getItemCount() % this.mOnePageSize != 0) {
            mPageCount++;
        }
        if (canScrollHorizontally()) {
            int usableWidth = (mPageCount - 1) * getUsableWidth();
            this.mMaxScrollX = usableWidth;
            this.mMaxScrollY = 0;
            if (this.mOffsetX > usableWidth) {
                this.mOffsetX = usableWidth;
            }
        } else {
            this.mMaxScrollX = 0;
            int usableHeight = (mPageCount - 1) * getUsableHeight();
            this.mMaxScrollY = usableHeight;
            if (this.mOffsetY > usableHeight) {
                this.mOffsetY = usableHeight;
            }
        }
        PagerConfig.Logi("count = " + getItemCount());
        if (this.mItemWidth <= 0) {
            this.mItemWidth = getUsableWidth() / this.mColumns;
        }
        if (this.mItemHeight <= 0) {
            this.mItemHeight = getUsableHeight() / this.mRows;
        }
        this.mWidthUsed = getUsableWidth() - this.mItemWidth;
        this.mHeightUsed = getUsableHeight() - this.mItemHeight;
        for (int i = 0; i < this.mOnePageSize * 2; i++) {
            getItemFrameByPosition(i);
        }
        int i2 = this.mOffsetX;
        if (i2 == 0 && this.mOffsetY == 0) {
            for (int i3 = 0; i3 < this.mOnePageSize && i3 < getItemCount(); i3++) {
                View view = recycler.getViewForPosition(i3);
                addView(view);
                measureChildWithMargins(view, this.mWidthUsed, this.mHeightUsed);
            }
        }
        recycleAndFillItems(recycler, state, true);
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        if (state.isPreLayout()) {
            return;
        }
        setPageCount(getTotalPageCount());
        setPageIndex(getPageIndexByOffset(), false);
    }

    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state, boolean isStart) {
        if (state.isPreLayout()) {
            return;
        }
        PagerConfig.Logi("mOffsetX = " + this.mOffsetX);
        PagerConfig.Logi("mOffsetY = " + this.mOffsetY);
        Rect displayRect = new Rect(this.mOffsetX - this.mItemWidth, this.mOffsetY - this.mItemHeight, getUsableWidth() + this.mOffsetX + this.mItemWidth, getUsableHeight() + this.mOffsetY + this.mItemHeight);
        displayRect.intersect(0, 0, this.mMaxScrollX + getUsableWidth(), this.mMaxScrollY + getUsableHeight());
        PagerConfig.Loge("displayRect = " + displayRect.toString());
        int pageIndex = getPageIndexByOffset();
        int startPos = this.mOnePageSize * pageIndex;
        PagerConfig.Logi("startPos = " + startPos);
        int i = this.mOnePageSize;
        int startPos2 = startPos - (i * 2);
        if (startPos2 < 0) {
            startPos2 = 0;
        }
        int stopPos = (i * 4) + startPos2;
        if (stopPos > getItemCount()) {
            stopPos = getItemCount();
        }
        PagerConfig.Loge("startPos = " + startPos2);
        PagerConfig.Loge("stopPos = " + stopPos);
        detachAndScrapAttachedViews(recycler);
        if (isStart) {
            for (int i2 = startPos2; i2 < stopPos; i2++) {
                addOrRemove(recycler, displayRect, i2);
            }
        } else {
            for (int i3 = stopPos - 1; i3 >= startPos2; i3--) {
                addOrRemove(recycler, displayRect, i3);
            }
        }
        PagerConfig.Loge("child count = " + getChildCount());
    }

    private void addOrRemove(RecyclerView.Recycler recycler, Rect displayRect, int i) {
        View child = recycler.getViewForPosition(i);
        Rect rect = getItemFrameByPosition(i);
        if (!Rect.intersects(displayRect, rect)) {
            removeAndRecycleView(child, recycler);
            return;
        }
        addView(child);
        measureChildWithMargins(child, this.mWidthUsed, this.mHeightUsed);
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
        layoutDecorated(child, (rect.left - this.mOffsetX) + lp.leftMargin + getPaddingLeft(), (rect.top - this.mOffsetY) + lp.topMargin + getPaddingTop(), ((rect.right - this.mOffsetX) - lp.rightMargin) + getPaddingLeft(), ((rect.bottom - this.mOffsetY) - lp.bottomMargin) + getPaddingTop());
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i = this.mOffsetX;
        int newX = i + dx;
        int result = dx;
        int i2 = this.mMaxScrollX;
        if (newX > i2) {
            result = i2 - i;
        } else if (newX < 0) {
            result = 0 - i;
        }
        this.mOffsetX = i + result;
        setPageIndex(getPageIndexByOffset(), true);
        offsetChildrenHorizontal(-result);
        if (result > 0) {
            recycleAndFillItems(recycler, state, true);
        } else {
            recycleAndFillItems(recycler, state, false);
        }
        return result;
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i = this.mOffsetY;
        int newY = i + dy;
        int result = dy;
        int i2 = this.mMaxScrollY;
        if (newY > i2) {
            result = i2 - i;
        } else if (newY < 0) {
            result = 0 - i;
        }
        this.mOffsetY = i + result;
        setPageIndex(getPageIndexByOffset(), true);
        offsetChildrenVertical(-result);
        if (result > 0) {
            recycleAndFillItems(recycler, state, true);
        } else {
            recycleAndFillItems(recycler, state, false);
        }
        return result;
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int state) {
        PagerConfig.Logi("onScrollStateChanged = " + state);
        this.mScrollState = state;
        super.onScrollStateChanged(state);
        if (state == 0) {
            setPageIndex(getPageIndexByOffset(), false);
        }
    }

    private Rect getItemFrameByPosition(int pos) {
        Rect rect = this.mItemFrames.get(pos);
        if (rect == null) {
            rect = new Rect();
            int page = pos / this.mOnePageSize;
            int offsetX = 0;
            int offsetY = 0;
            if (canScrollHorizontally()) {
                offsetX = 0 + (getUsableWidth() * page);
            } else {
                offsetY = 0 + (getUsableHeight() * page);
            }
            int pagePos = pos % this.mOnePageSize;
            int i = this.mColumns;
            int row = pagePos / i;
            int col = pagePos - (i * row);
            int offsetX2 = offsetX + (this.mItemWidth * col);
            int offsetY2 = offsetY + (this.mItemHeight * row);
            PagerConfig.Logi("pagePos = " + pagePos);
            PagerConfig.Logi("\u884c = " + row);
            PagerConfig.Logi("\u5217 = " + col);
            PagerConfig.Logi("offsetX = " + offsetX2);
            PagerConfig.Logi("offsetY = " + offsetY2);
            rect.left = offsetX2;
            rect.top = offsetY2;
            rect.right = this.mItemWidth + offsetX2;
            rect.bottom = this.mItemHeight + offsetY2;
            this.mItemFrames.put(pos, rect);
        }
        return rect;
    }

    private int getUsableWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private int getUsableHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private int getTotalPageCount() {
        if (getItemCount() <= 0) {
            return 0;
        }
        int totalCount = getItemCount() / this.mOnePageSize;
        if (getItemCount() % this.mOnePageSize != 0) {
            return totalCount + 1;
        }
        return totalCount;
    }

    private int getPageIndexByPos(int pos) {
        return pos / this.mOnePageSize;
    }

    private int getPageIndexByOffset() {
        int pageIndex;
        if (canScrollVertically()) {
            int pageHeight = getUsableHeight();
            int i = this.mOffsetY;
            if (i <= 0 || pageHeight <= 0) {
                pageIndex = 0;
            } else {
                pageIndex = i / pageHeight;
                if (i % pageHeight > pageHeight / 2) {
                    pageIndex++;
                }
            }
        } else {
            int pageWidth = getUsableWidth();
            int i2 = this.mOffsetX;
            if (i2 <= 0 || pageWidth <= 0) {
                pageIndex = 0;
            } else {
                pageIndex = i2 / pageWidth;
                if (i2 % pageWidth > pageWidth / 2) {
                    pageIndex++;
                }
            }
        }
        PagerConfig.Logi("getPageIndexByOffset pageIndex = " + pageIndex);
        return pageIndex;
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(recycler, state, widthMeasureSpec, heightMeasureSpec);
        int widthsize = View.MeasureSpec.getSize(widthMeasureSpec);
        int widthmode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightsize = View.MeasureSpec.getSize(heightMeasureSpec);
        int heightmode = View.MeasureSpec.getMode(heightMeasureSpec);
        if (widthmode != 1073741824 && widthsize > 0) {
            widthmode = BasicMeasure.EXACTLY;
        }
        if (heightmode != 1073741824 && heightsize > 0) {
            heightmode = BasicMeasure.EXACTLY;
        }
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(widthsize, widthmode), View.MeasureSpec.makeMeasureSpec(heightsize, heightmode));
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.mOrientation == 1;
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.mOrientation == 0;
    }

    int findNextPageFirstPos() {
        int page = this.mLastPageIndex + 1;
        if (page >= getTotalPageCount()) {
            page = getTotalPageCount() - 1;
        }
        PagerConfig.Loge("computeScrollVectorForPosition next = " + page);
        return this.mOnePageSize * page;
    }

    int findPrePageFirstPos() {
        int page = this.mLastPageIndex - 1;
        PagerConfig.Loge("computeScrollVectorForPosition pre = " + page);
        if (page < 0) {
            page = 0;
        }
        PagerConfig.Loge("computeScrollVectorForPosition pre = " + page);
        return this.mOnePageSize * page;
    }

    public int getOffsetX() {
        return this.mOffsetX;
    }

    public int getOffsetY() {
        return this.mOffsetY;
    }

    @Override // android.support.p004v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int targetPosition) {
        PointF vector = new PointF();
        int[] pos = getSnapOffset(targetPosition);
        vector.x = pos[0];
        vector.y = pos[1];
        return vector;
    }

    int[] getSnapOffset(int targetPosition) {
        int[] pos = getPageLeftTopByPosition(targetPosition);
        int[] offset = {pos[0] - this.mOffsetX, pos[1] - this.mOffsetY};
        return offset;
    }

    private int[] getPageLeftTopByPosition(int pos) {
        int[] leftTop = new int[2];
        int page = getPageIndexByPos(pos);
        if (canScrollHorizontally()) {
            leftTop[0] = getUsableWidth() * page;
            leftTop[1] = 0;
        } else {
            leftTop[0] = 0;
            leftTop[1] = getUsableHeight() * page;
        }
        return leftTop;
    }

    public View findSnapView() {
        if (getFocusedChild() != null) {
            return getFocusedChild();
        }
        if (getChildCount() <= 0) {
            return null;
        }
        int targetPos = getPageIndexByOffset() * this.mOnePageSize;
        for (int i = 0; i < getChildCount(); i++) {
            int childPos = getPosition(getChildAt(i));
            if (childPos == targetPos) {
                return getChildAt(i);
            }
        }
        return getChildAt(0);
    }

    private void setPageCount(int pageCount) {
        if (pageCount >= 0) {
            PageListener pageListener = this.mPageListener;
            if (pageListener != null && pageCount != this.mLastPageCount) {
                pageListener.onPageSizeChanged(pageCount);
            }
            this.mLastPageCount = pageCount;
        }
    }

    private void setPageIndex(int pageIndex, boolean isScrolling) {
        PageListener pageListener;
        PagerConfig.Loge("setPageIndex = " + pageIndex + ":" + isScrolling);
        if (pageIndex == this.mLastPageIndex) {
            return;
        }
        if (isAllowContinuousScroll()) {
            this.mLastPageIndex = pageIndex;
        } else if (!isScrolling) {
            this.mLastPageIndex = pageIndex;
        }
        if ((!isScrolling || this.mChangeSelectInScrolling) && pageIndex >= 0 && (pageListener = this.mPageListener) != null) {
            pageListener.onPageSelect(pageIndex);
        }
    }

    public void setChangeSelectInScrolling(boolean changeSelectInScrolling) {
        this.mChangeSelectInScrolling = changeSelectInScrolling;
    }

    public int setOrientationType(int orientation) {
        int x = this.mOrientation;
        if (x == orientation || this.mScrollState != 0) {
            return x;
        }
        this.mOrientation = orientation;
        this.mItemFrames.clear();
        int x2 = this.mOffsetX;
        int y = this.mOffsetY;
        this.mOffsetX = (y / getUsableHeight()) * getUsableWidth();
        this.mOffsetY = (x2 / getUsableWidth()) * getUsableHeight();
        int mx = this.mMaxScrollX;
        int my = this.mMaxScrollY;
        this.mMaxScrollX = (my / getUsableHeight()) * getUsableWidth();
        this.mMaxScrollY = (mx / getUsableWidth()) * getUsableHeight();
        return this.mOrientation;
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        int targetPageIndex = getPageIndexByPos(position);
        smoothScrollToPage(targetPageIndex);
    }

    public void smoothPrePage() {
        smoothScrollToPage(getPageIndexByOffset() - 1);
    }

    public void smoothNextPage() {
        smoothScrollToPage(getPageIndexByOffset() + 1);
    }

    public void smoothScrollToPage(int pageIndex) {
        if (pageIndex < 0 || pageIndex >= this.mLastPageCount) {
            Log.e(TAG, "pageIndex is outOfIndex, must in [0, " + this.mLastPageCount + ").");
        } else if (this.mRecyclerView == null) {
            Log.e(TAG, "RecyclerView Not Found!");
        } else {
            int currentPageIndex = getPageIndexByOffset();
            if (Math.abs(pageIndex - currentPageIndex) > 3) {
                if (pageIndex > currentPageIndex) {
                    scrollToPage(pageIndex - 3);
                } else if (pageIndex < currentPageIndex) {
                    scrollToPage(pageIndex + 3);
                }
            }
            LinearSmoothScroller smoothScroller = new PagerGridSmoothScroller(this.mRecyclerView);
            int position = this.mOnePageSize * pageIndex;
            smoothScroller.setTargetPosition(position);
            startSmoothScroll(smoothScroller);
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int position) {
        int pageIndex = getPageIndexByPos(position);
        scrollToPage(pageIndex);
    }

    public void prePage() {
        scrollToPage(getPageIndexByOffset() - 1);
    }

    public void nextPage() {
        scrollToPage(getPageIndexByOffset() + 1);
    }

    public void scrollToPage(int pageIndex) {
        int mTargetOffsetXBy;
        int mTargetOffsetYBy;
        if (pageIndex < 0 || pageIndex >= this.mLastPageCount) {
            Log.e(TAG, "pageIndex = " + pageIndex + " is out of bounds, mast in [0, " + this.mLastPageCount + ")");
        } else if (this.mRecyclerView == null) {
            Log.e(TAG, "RecyclerView Not Found!");
        } else {
            if (canScrollVertically()) {
                mTargetOffsetXBy = 0;
                mTargetOffsetYBy = (getUsableHeight() * pageIndex) - this.mOffsetY;
            } else {
                int mTargetOffsetYBy2 = getUsableWidth();
                mTargetOffsetXBy = (mTargetOffsetYBy2 * pageIndex) - this.mOffsetX;
                mTargetOffsetYBy = 0;
            }
            PagerConfig.Loge("mTargetOffsetXBy = " + mTargetOffsetXBy);
            PagerConfig.Loge("mTargetOffsetYBy = " + mTargetOffsetYBy);
            this.mRecyclerView.scrollBy(mTargetOffsetXBy, mTargetOffsetYBy);
            setPageIndex(pageIndex, false);
        }
    }

    public boolean isAllowContinuousScroll() {
        return this.mAllowContinuousScroll;
    }

    public void setAllowContinuousScroll(boolean allowContinuousScroll) {
        this.mAllowContinuousScroll = allowContinuousScroll;
    }

    public void setPageListener(PageListener pageListener) {
        this.mPageListener = pageListener;
    }
}
