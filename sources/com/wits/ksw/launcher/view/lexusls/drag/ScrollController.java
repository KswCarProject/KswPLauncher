package com.wits.ksw.launcher.view.lexusls.drag;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class ScrollController {
    private int lastPageIndex = -1;
    private RecyclerView.LayoutManager layoutManager;
    private MyOnScollListener mMyOnScollListener = new MyOnScollListener();
    private MyOnFlingListener mOnFlingListener = new MyOnFlingListener();
    private OnPageChangeListener mOnPageChangeListener;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView = null;
    private int offsetX = 0;

    public interface OnPageChangeListener {
        void onPageChange(int i);
    }

    static /* synthetic */ int access$212(ScrollController x0, int x1) {
        int i = x0.offsetX + x1;
        x0.offsetX = i;
        return i;
    }

    public void setUpRecycleView(RecyclerView recycleView) {
        if (recycleView != null) {
            this.mRecyclerView = recycleView;
            recycleView.setOnFlingListener(this.mOnFlingListener);
            recycleView.addOnScrollListener(this.mMyOnScollListener);
            RecyclerView.LayoutManager layoutManager2 = this.mRecyclerView.getLayoutManager();
            this.layoutManager = layoutManager2;
            if (layoutManager2 != null) {
                this.offsetX = 0;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("recycleView must be not null");
    }

    public int getCurrentPageIndex() {
        int viewWidth = this.mRecyclerView.getWidth();
        if (viewWidth == 0) {
            return 0;
        }
        return (this.offsetX + (this.mRecyclerView.getWidth() / 2)) / viewWidth;
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.mOnPageChangeListener = listener;
    }

    public void smoothScrollToPage(int page, boolean animator) {
        smoothScrollToOffset(this.mRecyclerView.getWidth() * page, animator);
    }

    public void arrowScroll(boolean isLeft) {
        if (isLeft) {
            smoothScrollToPage(getCurrentPageIndex() - 1, true);
        } else {
            smoothScrollToPage(getCurrentPageIndex() + 1, true);
        }
    }

    public void smoothScrollToOffset(int endPoint, boolean animator) {
        if (endPoint < 0) {
            endPoint = 0;
        }
        Log.d("ffocus", "" + endPoint);
        if (animator) {
            this.mRecyclerView.smoothScrollBy(endPoint - this.offsetX, 0);
        } else {
            this.mRecyclerView.scrollBy(endPoint - this.offsetX, 0);
        }
    }

    /* access modifiers changed from: private */
    public void notifyPageIndexChange() {
        if (getCurrentPageIndex() != this.lastPageIndex) {
            this.lastPageIndex = getCurrentPageIndex();
            OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageChange(getCurrentPageIndex());
            }
        }
    }

    /* access modifiers changed from: private */
    public void animateToCenter() {
        if (this.offsetX != this.mRecyclerView.getWidth() * getCurrentPageIndex()) {
            smoothScrollToOffset(this.mRecyclerView.getWidth() * getCurrentPageIndex(), true);
        }
    }

    class MyOnFlingListener extends RecyclerView.OnFlingListener {
        MyOnFlingListener() {
        }

        public boolean onFling(int velocityX, int velocityY) {
            if (ScrollController.this.mRecyclerView.getScrollState() == 0) {
                return true;
            }
            Log.e("ScrollController ", "MyOnFlingListener");
            int startScollPageIndex = ScrollController.this.getCurrentPageIndex();
            if (velocityX < -1000) {
                startScollPageIndex--;
            } else if (velocityX > 1000) {
                startScollPageIndex++;
            }
            int endPoint = ScrollController.this.mRecyclerView.getWidth() * startScollPageIndex;
            if (endPoint < 0) {
                endPoint = 0;
            }
            ScrollController.this.smoothScrollToOffset(endPoint, true);
            return true;
        }
    }

    class MyOnScollListener extends RecyclerView.OnScrollListener {
        MyOnScollListener() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            Log.e("", "onScrollStateChanged() called with: newState = [" + newState + "]");
            if (newState == 0) {
                ScrollController.this.animateToCenter();
            }
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            ScrollController.access$212(ScrollController.this, dx);
            ScrollController.this.notifyPageIndexChange();
        }
    }
}
