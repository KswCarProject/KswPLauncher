package com.wits.ksw.launcher.utils;

import android.support.p004v7.widget.LinearSnapHelper;
import android.support.p004v7.widget.OrientationHelper;
import android.support.p004v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes11.dex */
public class FixLinearSnapHelper extends LinearSnapHelper {
    private OrientationHelper mHorizontalHelper;
    private RecyclerView mRecyclerView;
    private OrientationHelper mVerticalHelper;

    @Override // android.support.p004v7.widget.LinearSnapHelper, android.support.p004v7.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
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

    @Override // android.support.p004v7.widget.SnapHelper
    public void attachToRecyclerView(RecyclerView recyclerView) throws IllegalStateException {
        this.mRecyclerView = recyclerView;
        super.attachToRecyclerView(recyclerView);
    }

    private int distanceToCenter(View targetView, OrientationHelper helper) {
        if (helper.getDecoratedStart(targetView) != 0 || this.mRecyclerView.getChildAdapterPosition(targetView) != 0) {
            if (helper.getDecoratedEnd(targetView) == helper.getEndAfterPadding() && this.mRecyclerView.getChildAdapterPosition(targetView) == this.mRecyclerView.getAdapter().getItemCount() - 1) {
                return 0;
            }
            int viewCenter = helper.getDecoratedStart(targetView) + ((helper.getDecoratedEnd(targetView) - helper.getDecoratedStart(targetView)) / 2);
            int correctCenter = (helper.getEndAfterPadding() - helper.getStartAfterPadding()) / 2;
            return (viewCenter - correctCenter) - 53;
        }
        return 0;
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
