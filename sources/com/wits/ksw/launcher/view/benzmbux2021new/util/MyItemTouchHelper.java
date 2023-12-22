package com.wits.ksw.launcher.view.benzmbux2021new.util;

import android.support.p004v7.widget.RecyclerView;
import android.support.p004v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux2021new.adapter.Benzmbux2021newAdapter;
import com.wits.ksw.launcher.view.benzmbux2021new.bean.BenzCardMenu;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class MyItemTouchHelper extends ItemTouchHelper.Callback {
    private static final String TAG = "MyItemTouchHelper";
    private BcVieModel mBcVieModel;
    private int mCurrentPosition = 0;
    private PagingScrollHelper mScrollHelper;
    private List<BenzCardMenu> pictureBeans;
    private Benzmbux2021newAdapter recycleViewAdapter;

    public MyItemTouchHelper(List<BenzCardMenu> pictureBeans, Benzmbux2021newAdapter recycleViewAdapter) {
        Log.d(TAG, "into MyItemTouchHelper");
        this.pictureBeans = pictureBeans;
        this.recycleViewAdapter = recycleViewAdapter;
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(15, 0);
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        if (fromPosition < toPosition) {
            if (fromPosition < 10 && toPosition >= 10) {
                return true;
            }
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(this.pictureBeans, i, i + 1);
            }
        } else if (fromPosition >= 10 && toPosition < 10) {
            return true;
        } else {
            for (int i2 = fromPosition; i2 > toPosition; i2--) {
                Collections.swap(this.pictureBeans, i2, i2 - 1);
            }
        }
        this.mCurrentPosition = toPosition;
        this.recycleViewAdapter.notifyItemMoved(fromPosition, toPosition);
        BenzUtils.changeCardItemPosition(fromPosition, toPosition);
        Log.d(TAG, "onMove fromPosition = " + fromPosition + " toPosition" + toPosition);
        return true;
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != 0) {
            String name = (String) viewHolder.itemView.findViewById(C0899R.C0901id.benz_mbux_2021_ksw_new_item_bg).getTag();
            this.mCurrentPosition = BenzUtils.nameList.indexOf(name);
            Log.i(TAG, "onSelectedChanged: name " + name + " mCurrentPosition " + this.mCurrentPosition);
            this.mBcVieModel.selectCardName.set(name);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        try {
            Log.i(TAG, "clearView: mCurrentPosition " + this.mCurrentPosition);
            PagingScrollHelper pagingScrollHelper = this.mScrollHelper;
            if (pagingScrollHelper != null) {
                pagingScrollHelper.scrollToPosition(this.mCurrentPosition / 5);
                this.recycleViewAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setScrollHelper(PagingScrollHelper scrollHelper) {
        this.mScrollHelper = scrollHelper;
    }

    public void setViewModel(BcVieModel bcVieModel) {
        this.mBcVieModel = bcVieModel;
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchHelper.Callback
    public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int viewSize, int viewSizeOutOfBounds, int totalSize, long msSinceStartScroll) {
        int direction = (int) Math.signum(viewSizeOutOfBounds);
        return direction * 10;
    }
}
