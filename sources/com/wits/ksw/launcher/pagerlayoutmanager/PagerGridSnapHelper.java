package com.wits.ksw.launcher.pagerlayoutmanager;

import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

public class PagerGridSnapHelper extends SnapHelper {
    private RecyclerView mRecyclerView;

    public void attachToRecyclerView(RecyclerView recyclerView) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
        int pos = layoutManager.getPosition(targetView);
        PagerConfig.Loge("findTargetSnapPosition, pos = " + pos);
        int[] offset = new int[2];
        if (layoutManager instanceof PagerGridLayoutManager) {
            return ((PagerGridLayoutManager) layoutManager).getSnapOffset(pos);
        }
        return offset;
    }

    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof PagerGridLayoutManager) {
            return ((PagerGridLayoutManager) layoutManager).findSnapView();
        }
        return null;
    }

    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        int target = -1;
        PagerConfig.Loge("findTargetSnapPosition, velocityX = " + velocityX + ", velocityY" + velocityY);
        if (layoutManager != null && (layoutManager instanceof PagerGridLayoutManager)) {
            PagerGridLayoutManager manager = (PagerGridLayoutManager) layoutManager;
            if (manager.canScrollHorizontally()) {
                if (velocityX > PagerConfig.getFlingThreshold()) {
                    target = manager.findNextPageFirstPos();
                } else if (velocityX < (-PagerConfig.getFlingThreshold())) {
                    target = manager.findPrePageFirstPos();
                }
            } else if (manager.canScrollVertically()) {
                if (velocityY > PagerConfig.getFlingThreshold()) {
                    target = manager.findNextPageFirstPos();
                } else if (velocityY < (-PagerConfig.getFlingThreshold())) {
                    target = manager.findPrePageFirstPos();
                }
            }
        }
        PagerConfig.Loge("findTargetSnapPosition, target = " + target);
        return target;
    }

    public boolean onFling(int velocityX, int velocityY) {
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null || this.mRecyclerView.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = PagerConfig.getFlingThreshold();
        PagerConfig.Loge("minFlingVelocity = " + minFlingVelocity);
        if ((Math.abs(velocityY) > minFlingVelocity || Math.abs(velocityX) > minFlingVelocity) && snapFromFling(layoutManager, velocityX, velocityY)) {
            return true;
        }
        return false;
    }

    private boolean snapFromFling(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        RecyclerView.SmoothScroller smoothScroller;
        int targetPosition;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) || (smoothScroller = createSnapScroller(layoutManager)) == null || (targetPosition = findTargetSnapPosition(layoutManager, velocityX, velocityY)) == -1) {
            return false;
        }
        smoothScroller.setTargetPosition(targetPosition);
        layoutManager.startSmoothScroll(smoothScroller);
        return true;
    }

    /* access modifiers changed from: protected */
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return null;
        }
        return new PagerGridSmoothScroller(this.mRecyclerView);
    }

    public void setFlingThreshold(int threshold) {
        PagerConfig.setFlingThreshold(threshold);
    }
}
