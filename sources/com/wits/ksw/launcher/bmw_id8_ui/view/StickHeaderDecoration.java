package com.wits.ksw.launcher.bmw_id8_ui.view;

import android.graphics.Canvas;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class StickHeaderDecoration extends RecyclerView.ItemDecoration {
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private RecyclerView recyclerView;
    private StickHeaderInterface stickHeaderInterface;

    public interface StickHeaderInterface {
        boolean isStick(int i);
    }

    public StickHeaderDecoration(RecyclerView recyclerView2) {
        this.recyclerView = recyclerView2;
        this.manager = recyclerView2.getLayoutManager();
        RecyclerView.Adapter adapter2 = recyclerView2.getAdapter();
        this.adapter = adapter2;
        if (adapter2 == null) {
            throw new RuntimeException("please set Decoration after set adapter");
        } else if (adapter2 instanceof StickHeaderInterface) {
            this.stickHeaderInterface = (StickHeaderInterface) adapter2;
        } else {
            throw new RuntimeException("please make your adapter implements StickHeaderInterface");
        }
    }

    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int i = 0;
        View childAt = parent.getChildAt(0);
        if (childAt != null) {
            int position = parent.getChildViewHolder(childAt).getPosition();
            for (int i2 = position; i2 >= 0; i2--) {
                if (this.stickHeaderInterface.isStick(i2)) {
                    int top = 0;
                    if (position + 1 < this.adapter.getItemCount() && this.stickHeaderInterface.isStick(position + 1)) {
                        View childNext = parent.getChildAt(1);
                        if (this.manager.getDecoratedTop(childNext) >= 0) {
                            i = this.manager.getDecoratedTop(childNext);
                        }
                        top = i;
                    }
                    RecyclerView.ViewHolder inflate = this.recyclerView.getAdapter().createViewHolder(parent, this.recyclerView.getAdapter().getItemViewType(i2));
                    this.recyclerView.getAdapter().bindViewHolder(inflate, i2);
                    int measureHeight = getMeasureHeight(inflate.itemView);
                    c.save();
                    if (top < inflate.itemView.getMeasuredHeight() && top > 0) {
                        c.translate(0.0f, (float) (top - measureHeight));
                    }
                    inflate.itemView.draw(c);
                    c.restore();
                    return;
                }
            }
        }
    }

    private int getMeasureHeight(View header) {
        header.measure(View.MeasureSpec.makeMeasureSpec(this.recyclerView.getWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, 0));
        header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
        return header.getMeasuredHeight();
    }
}
