package com.wits.ksw.launcher.view.benzmbux2021new.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.p001v4.app.NotificationManagerCompat;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes5.dex */
public class PagingScrollHelper {
    onPageChangeListener mOnPageChangeListener;
    RecyclerView mRecyclerView = null;
    private MyOnScrollListener mOnScrollListener = new MyOnScrollListener();
    private MyOnFlingListener mOnFlingListener = new MyOnFlingListener();
    private int offsetY = 0;
    private int offsetX = 0;
    int startY = 0;
    int startX = 0;
    private ORIENTATION mOrientation = ORIENTATION.HORIZONTAL;
    ValueAnimator mAnimator = null;
    private MyOnTouchListener mOnTouchListener = new MyOnTouchListener();
    private boolean firstTouch = true;

    /* loaded from: classes5.dex */
    enum ORIENTATION {
        HORIZONTAL,
        VERTICAL,
        NULL
    }

    /* loaded from: classes5.dex */
    public interface onPageChangeListener {
        void onPageChange(int index);
    }

    static /* synthetic */ int access$212(PagingScrollHelper x0, int x1) {
        int i = x0.offsetY + x1;
        x0.offsetY = i;
        return i;
    }

    static /* synthetic */ int access$312(PagingScrollHelper x0, int x1) {
        int i = x0.offsetX + x1;
        x0.offsetX = i;
        return i;
    }

    public void setUpRecycleView(RecyclerView recycleView) {
        if (recycleView == null) {
            throw new IllegalArgumentException("recycleView must be not null");
        }
        this.mRecyclerView = recycleView;
        recycleView.setOnFlingListener(this.mOnFlingListener);
        recycleView.setOnScrollListener(this.mOnScrollListener);
        recycleView.setOnTouchListener(this.mOnTouchListener);
        updateLayoutManger();
    }

    public void updateLayoutManger() {
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager != null) {
            if (layoutManager.canScrollVertically()) {
                this.mOrientation = ORIENTATION.VERTICAL;
            } else if (layoutManager.canScrollHorizontally()) {
                this.mOrientation = ORIENTATION.HORIZONTAL;
            } else {
                this.mOrientation = ORIENTATION.NULL;
            }
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.startX = 0;
            this.startY = 0;
            this.offsetX = 0;
            this.offsetY = 0;
        }
    }

    public int getPageCount() {
        if (this.mRecyclerView == null || this.mOrientation == ORIENTATION.NULL) {
            return 0;
        }
        if (this.mOrientation == ORIENTATION.VERTICAL && this.mRecyclerView.computeVerticalScrollExtent() != 0) {
            return this.mRecyclerView.computeVerticalScrollRange() / this.mRecyclerView.computeVerticalScrollExtent();
        }
        if (this.mRecyclerView.computeHorizontalScrollExtent() != 0) {
            Log.i("zzz", "rang=" + this.mRecyclerView.computeHorizontalScrollRange() + " extent=" + this.mRecyclerView.computeHorizontalScrollExtent());
            return this.mRecyclerView.computeHorizontalScrollRange() / this.mRecyclerView.computeHorizontalScrollExtent();
        }
        return 0;
    }

    public void scrollToPosition(int position) {
        int endPoint;
        if (this.mAnimator == null) {
            this.mOnFlingListener.onFling(0, 0);
        }
        if (this.mAnimator != null) {
            int startPoint = this.mOrientation == ORIENTATION.VERTICAL ? this.offsetY : this.offsetX;
            if (this.mOrientation == ORIENTATION.VERTICAL) {
                endPoint = this.mRecyclerView.getHeight() * position;
            } else {
                endPoint = this.mRecyclerView.getWidth() * position;
            }
            if (startPoint != endPoint) {
                this.mAnimator.setIntValues(startPoint, endPoint);
                this.mAnimator.start();
            }
        }
    }

    public void scrollToPositionNoAnimator(int position) {
        int endPoint;
        if (this.mOrientation == ORIENTATION.VERTICAL) {
            int i = this.offsetY;
        } else {
            int i2 = this.offsetX;
        }
        if (this.mOrientation == ORIENTATION.VERTICAL) {
            endPoint = this.mRecyclerView.getHeight() * position;
        } else {
            endPoint = this.mRecyclerView.getWidth() * position;
        }
        if (this.mOrientation == ORIENTATION.VERTICAL) {
            int dy = endPoint - this.offsetY;
            this.mRecyclerView.scrollBy(0, dy);
            return;
        }
        int dx = endPoint - this.offsetX;
        this.mRecyclerView.scrollBy(dx, 0);
    }

    /* loaded from: classes5.dex */
    public class MyOnFlingListener extends RecyclerView.OnFlingListener {
        public MyOnFlingListener() {
        }

        @Override // android.support.p004v7.widget.RecyclerView.OnFlingListener
        public boolean onFling(int velocityX, int velocityY) {
            int startPoint;
            int endPoint;
            if (PagingScrollHelper.this.mOrientation == ORIENTATION.NULL) {
                return false;
            }
            int p = PagingScrollHelper.this.getStartPageIndex();
            if (PagingScrollHelper.this.mOrientation == ORIENTATION.VERTICAL) {
                startPoint = PagingScrollHelper.this.offsetY;
                if (velocityY < 0) {
                    p--;
                } else if (velocityY > 0) {
                    p++;
                }
                endPoint = PagingScrollHelper.this.mRecyclerView.getHeight() * p;
            } else {
                startPoint = PagingScrollHelper.this.offsetX;
                if (velocityX < 0) {
                    p--;
                } else if (velocityX > 0) {
                    p++;
                }
                endPoint = PagingScrollHelper.this.mRecyclerView.getWidth() * p;
            }
            if (endPoint < 0) {
                endPoint = 0;
            }
            if (PagingScrollHelper.this.mAnimator == null) {
                PagingScrollHelper pagingScrollHelper = PagingScrollHelper.this;
                new ValueAnimator();
                pagingScrollHelper.mAnimator = ValueAnimator.ofInt(startPoint, endPoint);
                PagingScrollHelper.this.mAnimator.setDuration(200L);
                PagingScrollHelper.this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wits.ksw.launcher.view.benzmbux2021new.util.PagingScrollHelper.MyOnFlingListener.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int nowPoint = ((Integer) animation.getAnimatedValue()).intValue();
                        if (PagingScrollHelper.this.mOrientation == ORIENTATION.VERTICAL) {
                            int dy = nowPoint - PagingScrollHelper.this.offsetY;
                            PagingScrollHelper.this.mRecyclerView.scrollBy(0, dy);
                            return;
                        }
                        int dx = nowPoint - PagingScrollHelper.this.offsetX;
                        PagingScrollHelper.this.mRecyclerView.scrollBy(dx, 0);
                    }
                });
                PagingScrollHelper.this.mAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.wits.ksw.launcher.view.benzmbux2021new.util.PagingScrollHelper.MyOnFlingListener.2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animation) {
                        if (PagingScrollHelper.this.mOnPageChangeListener != null) {
                            PagingScrollHelper.this.mOnPageChangeListener.onPageChange(PagingScrollHelper.this.getPageIndex());
                        }
                        PagingScrollHelper.this.mRecyclerView.stopScroll();
                        PagingScrollHelper.this.startY = PagingScrollHelper.this.offsetY;
                        PagingScrollHelper.this.startX = PagingScrollHelper.this.offsetX;
                    }
                });
            } else {
                PagingScrollHelper.this.mAnimator.cancel();
                PagingScrollHelper.this.mAnimator.setIntValues(startPoint, endPoint);
            }
            PagingScrollHelper.this.mAnimator.start();
            return true;
        }
    }

    /* loaded from: classes5.dex */
    public class MyOnScrollListener extends RecyclerView.OnScrollListener {
        public MyOnScrollListener() {
        }

        @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == 0 && PagingScrollHelper.this.mOrientation != ORIENTATION.NULL) {
                int vX = 0;
                int vY = 0;
                ORIENTATION orientation = PagingScrollHelper.this.mOrientation;
                ORIENTATION orientation2 = ORIENTATION.VERTICAL;
                int i = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
                if (orientation == orientation2) {
                    int absY = Math.abs(PagingScrollHelper.this.offsetY - PagingScrollHelper.this.startY);
                    boolean move = absY > recyclerView.getHeight() / 2;
                    vY = 0;
                    if (move) {
                        if (PagingScrollHelper.this.offsetY - PagingScrollHelper.this.startY >= 0) {
                            i = 1000;
                        }
                        vY = i;
                    }
                } else {
                    int absX = Math.abs(PagingScrollHelper.this.offsetX - PagingScrollHelper.this.startX);
                    boolean move2 = absX > recyclerView.getWidth() / 2;
                    if (move2) {
                        if (PagingScrollHelper.this.offsetX - PagingScrollHelper.this.startX >= 0) {
                            i = 1000;
                        }
                        vX = i;
                    }
                }
                PagingScrollHelper.this.mOnFlingListener.onFling(vX, vY);
            }
        }

        @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            PagingScrollHelper.access$212(PagingScrollHelper.this, dy);
            PagingScrollHelper.access$312(PagingScrollHelper.this, dx);
        }
    }

    /* loaded from: classes5.dex */
    public class MyOnTouchListener implements View.OnTouchListener {
        public MyOnTouchListener() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View v, MotionEvent event) {
            if (PagingScrollHelper.this.firstTouch) {
                PagingScrollHelper.this.firstTouch = false;
                PagingScrollHelper pagingScrollHelper = PagingScrollHelper.this;
                pagingScrollHelper.startY = pagingScrollHelper.offsetY;
                PagingScrollHelper pagingScrollHelper2 = PagingScrollHelper.this;
                pagingScrollHelper2.startX = pagingScrollHelper2.offsetX;
            }
            if (event.getAction() == 1 || event.getAction() == 3) {
                PagingScrollHelper.this.firstTouch = true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPageIndex() {
        if (this.mRecyclerView.getHeight() == 0 || this.mRecyclerView.getWidth() == 0) {
            return 0;
        }
        if (this.mOrientation == ORIENTATION.VERTICAL) {
            int p = this.offsetY / this.mRecyclerView.getHeight();
            return p;
        }
        int p2 = this.offsetX;
        return p2 / this.mRecyclerView.getWidth();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getStartPageIndex() {
        if (this.mRecyclerView.getHeight() == 0 || this.mRecyclerView.getWidth() == 0) {
            return 0;
        }
        if (this.mOrientation == ORIENTATION.VERTICAL) {
            int p = this.startY / this.mRecyclerView.getHeight();
            return p;
        }
        int p2 = this.startX;
        return p2 / this.mRecyclerView.getWidth();
    }

    public void setOnPageChangeListener(onPageChangeListener listener) {
        this.mOnPageChangeListener = listener;
    }
}
