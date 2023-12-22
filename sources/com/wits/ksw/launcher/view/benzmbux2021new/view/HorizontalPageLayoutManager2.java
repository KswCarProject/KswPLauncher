package com.wits.ksw.launcher.view.benzmbux2021new.view;

import android.graphics.Rect;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import com.wits.ksw.launcher.view.benzmbux2021new.interfaces.PageDecorationLastJudge;

/* loaded from: classes17.dex */
public class HorizontalPageLayoutManager2 extends RecyclerView.LayoutManager implements PageDecorationLastJudge {
    int columns;
    int itemHeightUsed;
    int itemWidthUsed;
    int onePageSize;
    int rows;
    int totalHeight = 0;
    int totalWidth = 0;
    int offsetY = 0;
    int offsetX = 0;
    private SparseArray<Rect> allItemFrames = new SparseArray<>();
    int pageSize = 0;
    int itemWidth = 0;
    int itemHeight = 0;

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    public HorizontalPageLayoutManager2(int rows, int columns) {
        this.rows = 0;
        this.columns = 0;
        this.onePageSize = 0;
        this.rows = rows;
        this.columns = columns;
        this.onePageSize = rows * columns;
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int i = this.offsetX;
        int newX = i + dx;
        int result = dx;
        int i2 = this.totalWidth;
        if (newX > i2) {
            result = i2 - i;
        } else if (newX < 0) {
            result = 0 - i;
        }
        this.offsetX = i + result;
        offsetChildrenHorizontal(-result);
        recycleAndFillItems(recycler, state);
        return result;
    }

    private int getUsableWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private int getUsableHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
        } else if (state.isPreLayout()) {
        } else {
            this.itemWidth = getUsableWidth() / this.columns;
            int usableHeight = getUsableHeight();
            int i = this.rows;
            int i2 = usableHeight / i;
            this.itemHeight = i2;
            this.itemWidthUsed = (this.columns - 1) * this.itemWidth;
            this.itemHeightUsed = (i - 1) * i2;
            computePageSize(state);
            Log.i("zzz", "itemCount=" + getItemCount() + " state itemCount=" + state.getItemCount() + " pageSize=" + this.pageSize);
            this.totalWidth = (this.pageSize - 1) * getWidth();
            detachAndScrapAttachedViews(recycler);
            int count = getItemCount();
            int p = 0;
            while (p < this.pageSize) {
                int r = 0;
                while (r < this.rows) {
                    int c = 0;
                    while (true) {
                        int i3 = this.columns;
                        if (c >= i3) {
                            break;
                        }
                        int index = (this.onePageSize * p) + (i3 * r) + c;
                        if (index == count) {
                            int c2 = this.columns;
                            r = this.rows;
                            p = this.pageSize;
                            break;
                        }
                        View view = recycler.getViewForPosition(index);
                        addView(view);
                        measureChildWithMargins(view, this.itemWidthUsed, this.itemHeightUsed);
                        int width = getDecoratedMeasuredWidth(view);
                        int height = getDecoratedMeasuredHeight(view);
                        Rect rect = this.allItemFrames.get(index);
                        if (rect == null) {
                            rect = new Rect();
                        }
                        int x = (getUsableWidth() * p) + (this.itemWidth * c);
                        int y = this.itemHeight * r;
                        rect.set(x, y, width + x, height + y);
                        this.allItemFrames.put(index, rect);
                        c++;
                    }
                    r++;
                }
                removeAndRecycleAllViews(recycler);
                p++;
            }
            recycleAndFillItems(recycler, state);
        }
    }

    private void computePageSize(RecyclerView.State state) {
        this.pageSize = (state.getItemCount() / this.onePageSize) + (state.getItemCount() % this.onePageSize == 0 ? 0 : 1);
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(view, recycler);
        this.offsetX = 0;
        this.offsetY = 0;
    }

    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) {
            return;
        }
        Rect displayRect = new Rect(getPaddingLeft() + this.offsetX, getPaddingTop(), ((getWidth() - getPaddingLeft()) - getPaddingRight()) + this.offsetX, (getHeight() - getPaddingTop()) - getPaddingBottom());
        Rect childRect = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childRect.left = getDecoratedLeft(child);
            childRect.top = getDecoratedTop(child);
            childRect.right = getDecoratedRight(child);
            childRect.bottom = getDecoratedBottom(child);
            if (!Rect.intersects(displayRect, childRect)) {
                removeAndRecycleView(child, recycler);
            }
        }
        for (int i2 = 0; i2 < getItemCount(); i2++) {
            if (Rect.intersects(displayRect, this.allItemFrames.get(i2))) {
                View view = recycler.getViewForPosition(i2);
                addView(view);
                measureChildWithMargins(view, this.itemWidthUsed, this.itemHeightUsed);
                Rect rect = this.allItemFrames.get(i2);
                layoutDecorated(view, rect.left - this.offsetX, rect.top, rect.right - this.offsetX, rect.bottom);
            }
        }
    }

    @Override // com.wits.ksw.launcher.view.benzmbux2021new.interfaces.PageDecorationLastJudge
    public boolean isLastRow(int index) {
        if (index >= 0 && index < getItemCount()) {
            int i = this.onePageSize;
            int indexOfPage = (index % i) + 1;
            if (indexOfPage > (this.rows - 1) * this.columns && indexOfPage <= i) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // com.wits.ksw.launcher.view.benzmbux2021new.interfaces.PageDecorationLastJudge
    public boolean isLastColumn(int position) {
        if (position >= 0 && position < getItemCount() && (position + 1) % this.columns == 0) {
            return true;
        }
        return false;
    }

    @Override // com.wits.ksw.launcher.view.benzmbux2021new.interfaces.PageDecorationLastJudge
    public boolean isPageLast(int position) {
        return (position + 1) % this.onePageSize == 0;
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        computePageSize(state);
        return this.pageSize * getWidth();
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return this.offsetX;
    }

    @Override // android.support.p004v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return getWidth();
    }
}
