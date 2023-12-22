package com.wits.ksw.launcher.bmw_id8_ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import android.support.p004v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes15.dex */
public class SpacingItemDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private int mTextSize;
    private Paint paint;
    private Rect rectBounds;
    private int spacing;
    private int titleHeight = 120;

    public SpacingItemDecoration(Context context, int spacings) {
        this.spacing = spacings;
    }

    @Override // android.support.p004v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1 && position != 0) {
            outRect.left = this.spacing;
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    private int getMeasureWidth(View header, RecyclerView recyclerView) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), BasicMeasure.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        header.measure(widthSpec, heightSpec);
        header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
        return header.getMeasuredWidth();
    }
}
