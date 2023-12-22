package com.wits.ksw.launcher.pagerlayoutmanager;

import android.support.p004v7.widget.LinearSmoothScroller;
import android.support.p004v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/* loaded from: classes7.dex */
public class PagerGridSmoothScroller extends LinearSmoothScroller {
    private RecyclerView mRecyclerView;

    public PagerGridSmoothScroller(RecyclerView recyclerView) {
        super(recyclerView.getContext());
        this.mRecyclerView = recyclerView;
    }

    @Override // android.support.p004v7.widget.LinearSmoothScroller, android.support.p004v7.widget.RecyclerView.SmoothScroller
    protected void onTargetFound(View targetView, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        RecyclerView.LayoutManager manager = this.mRecyclerView.getLayoutManager();
        if (manager != null && (manager instanceof PagerGridLayoutManager)) {
            PagerGridLayoutManager layoutManager = (PagerGridLayoutManager) manager;
            int pos = this.mRecyclerView.getChildAdapterPosition(targetView);
            int[] snapDistances = layoutManager.getSnapOffset(pos);
            int dx = snapDistances[0];
            int dy = snapDistances[1];
            PagerConfig.Logi("dx = " + dx);
            PagerConfig.Logi("dy = " + dy);
            int time = calculateTimeForScrolling(Math.max(Math.abs(dx), Math.abs(dy)));
            if (time > 0) {
                action.update(dx, dy, time, this.mDecelerateInterpolator);
            }
        }
    }

    @Override // android.support.p004v7.widget.LinearSmoothScroller
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return PagerConfig.getMillisecondsPreInch() / displayMetrics.densityDpi;
    }
}
