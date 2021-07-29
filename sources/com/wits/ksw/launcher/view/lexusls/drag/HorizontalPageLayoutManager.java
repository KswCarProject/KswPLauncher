package com.wits.ksw.launcher.view.lexusls.drag;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

public class HorizontalPageLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = "HorizontalPageLayoutManager";
    private SparseArray<Rect> allItemFrames = new SparseArray<>();
    private int columns = 0;
    private final Context context;
    int itemHeight = 0;
    int itemHeightUsed;
    int itemWidth = 0;
    int itemWidthUsed;
    private int lineWidth = 0;
    private DragLayer mDragLayer = null;
    private int offsetX = 0;
    private int onePageSize = 0;
    int pageSize = 0;
    private int rows = 0;
    private int totalWidth = 0;

    public HorizontalPageLayoutManager(int rows2, int columns2, int lineWidth2, Context context2) {
        this.rows = rows2;
        this.columns = columns2;
        this.onePageSize = rows2 * columns2;
        this.context = context2;
        this.lineWidth = lineWidth2;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-1, -1);
    }

    public boolean canScrollHorizontally() {
        return true;
    }

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

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int itemCount = getItemCount();
        if (itemCount == 0) {
            removeAndRecycleAllViews(recycler);
        } else if (!state.isPreLayout()) {
            int usableWidth = getUsableWidth();
            int i = this.columns;
            this.itemWidth = (usableWidth - ((i + 1) * this.lineWidth)) / i;
            int usableHeight = getUsableHeight();
            int i2 = this.rows;
            int i3 = (usableHeight - ((i2 + 1) * this.lineWidth)) / i2;
            this.itemHeight = i3;
            this.itemWidthUsed = (this.columns - 1) * this.itemWidth;
            this.itemHeightUsed = (i2 - 1) * i3;
            int pageSize2 = getPageSize(itemCount);
            this.pageSize = pageSize2;
            this.totalWidth = (pageSize2 - 1) * getWidth();
            detachAndScrapAttachedViews(recycler);
            if (itemCount > 0) {
                int width = this.itemWidth;
                int height = this.itemHeight;
                int p = 0;
                while (p < this.pageSize) {
                    int r = 0;
                    while (r < this.rows) {
                        int c = 0;
                        while (true) {
                            int i4 = this.columns;
                            if (c >= i4) {
                                break;
                            }
                            int index = (this.onePageSize * p) + (i4 * r) + c;
                            if (index == itemCount) {
                                int c2 = this.columns;
                                r = this.rows;
                                p = this.pageSize;
                                break;
                            }
                            Rect rect = this.allItemFrames.get(index);
                            if (rect == null) {
                                rect = new Rect();
                            }
                            int usableWidth2 = (getUsableWidth() * p) + (this.itemWidth * c);
                            int i5 = this.lineWidth;
                            int x = usableWidth2 + ((c + 1) * i5);
                            int y = (this.itemHeight * r) + (i5 * (r + 1));
                            rect.set(x, y, width + x, height + y);
                            this.allItemFrames.put(index, rect);
                            c++;
                        }
                        r++;
                    }
                    removeAndRecycleAllViews(recycler);
                    p++;
                }
            }
            recycleAndFillItems(recycler, state);
        }
    }

    public int getPageSize(int itemCount) {
        int i = this.onePageSize;
        int i2 = 0;
        if (i == 0) {
            new Exception("行和列不能为空").printStackTrace();
            return 0;
        }
        int i3 = itemCount / i;
        if (itemCount % i != 0) {
            i2 = 1;
        }
        return i3 + i2;
    }

    /* access modifiers changed from: protected */
    public boolean shouldLayoutChildren() {
        return this.context.getResources().getConfiguration().orientation == 2 && getWidth() > getHeight();
    }

    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(view, recycler);
        this.offsetX = 0;
    }

    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!state.isPreLayout()) {
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
            int itemCount = getItemCount();
            for (int i2 = 0; i2 < itemCount; i2++) {
                if (Rect.intersects(displayRect, this.allItemFrames.get(i2))) {
                    View view = recycler.getViewForPosition(i2);
                    addView(view);
                    measureChildWithMargins(view, this.itemWidthUsed, this.itemHeightUsed);
                    Rect rect = this.allItemFrames.get(i2);
                    layoutDecorated(view, rect.left - this.offsetX, rect.top, rect.right - this.offsetX, rect.bottom);
                    DragLayer dragLayer = this.mDragLayer;
                    if (dragLayer != null) {
                        dragLayer.loadChildView(view);
                    }
                }
            }
        }
    }

    private int getUsableWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private int getUsableHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public void setDragLayer(DragLayer layer) {
        this.mDragLayer = layer;
    }
}
