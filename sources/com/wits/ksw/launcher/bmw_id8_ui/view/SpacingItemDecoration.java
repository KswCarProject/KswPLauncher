package com.wits.ksw.launcher.bmw_id8_ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacingItemDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private int mTextSize;
    private Paint paint;
    private Rect rectBounds;
    private int spacing;
    private int titleHeight = 120;

    public SpacingItemDecoration(Context context2, int spacings) {
        this.spacing = spacings;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1 && position != 0) {
            outRect.left = this.spacing;
        }
    }

    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    private int getMeasureWidth(View header, RecyclerView recyclerView) {
        header.measure(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, 0));
        header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
        return header.getMeasuredWidth();
    }
}
