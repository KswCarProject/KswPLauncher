package com.wits.ksw.launcher.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class FixLinearSnapHelper extends LinearSnapHelper {
    private OrientationHelper mHorizontalHelper;
    private RecyclerView mRecyclerView;
    private OrientationHelper mVerticalHelper;

    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToCenter(targetView, getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToCenter(targetView, getVerticalHelper(layoutManager));
        } else {
            out[1] = 0;
        }
        return out;
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        this.mRecyclerView = recyclerView;
        super.attachToRecyclerView(recyclerView);
    }

    private int distanceToCenter(View targetView, OrientationHelper helper) {
        if (helper.getDecoratedStart(targetView) == 0 && this.mRecyclerView.getChildAdapterPosition(targetView) == 0) {
            return 0;
        }
        if (helper.getDecoratedEnd(targetView) == helper.getEndAfterPadding() && this.mRecyclerView.getChildAdapterPosition(targetView) == this.mRecyclerView.getAdapter().getItemCount() - 1) {
            return 0;
        }
        return ((helper.getDecoratedStart(targetView) + ((helper.getDecoratedEnd(targetView) - helper.getDecoratedStart(targetView)) / 2)) - ((helper.getEndAfterPadding() - helper.getStartAfterPadding()) / 2)) - 53;
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        if (this.mVerticalHelper == null) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mVerticalHelper;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (this.mHorizontalHelper == null) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.mHorizontalHelper;
    }
}
