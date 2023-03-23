package com.chad.library.adapter.base.callback;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.chad.library.R;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.DraggableController;
import com.chad.library.adapter.base.listener.IDraggableListener;

public class ItemDragAndSwipeCallback extends ItemTouchHelper.Callback {
    private BaseItemDraggableAdapter mBaseItemDraggableAdapter;
    private int mDragMoveFlags = 15;
    private IDraggableListener mDraggableListener;
    private float mMoveThreshold = 0.1f;
    private int mSwipeMoveFlags = 32;
    private float mSwipeThreshold = 0.7f;

    public ItemDragAndSwipeCallback(BaseItemDraggableAdapter adapter) {
        this.mBaseItemDraggableAdapter = adapter;
    }

    public ItemDragAndSwipeCallback(DraggableController draggableController) {
        this.mDraggableListener = draggableController;
    }

    public boolean isLongPressDragEnabled() {
        BaseItemDraggableAdapter baseItemDraggableAdapter = this.mBaseItemDraggableAdapter;
        if (baseItemDraggableAdapter == null) {
            IDraggableListener iDraggableListener = this.mDraggableListener;
            if (iDraggableListener == null) {
                return false;
            }
            if (!iDraggableListener.isItemDraggable() || this.mDraggableListener.hasToggleView()) {
                return false;
            }
            return true;
        } else if (!baseItemDraggableAdapter.isItemDraggable() || this.mBaseItemDraggableAdapter.hasToggleView()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isItemViewSwipeEnabled() {
        BaseItemDraggableAdapter baseItemDraggableAdapter = this.mBaseItemDraggableAdapter;
        if (baseItemDraggableAdapter != null) {
            return baseItemDraggableAdapter.isItemSwipeEnable();
        }
        IDraggableListener iDraggableListener = this.mDraggableListener;
        if (iDraggableListener != null) {
            return iDraggableListener.isItemSwipeEnable();
        }
        return false;
    }

    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState == 2 && !isViewCreateByAdapter(viewHolder)) {
            BaseItemDraggableAdapter baseItemDraggableAdapter = this.mBaseItemDraggableAdapter;
            if (baseItemDraggableAdapter != null) {
                baseItemDraggableAdapter.onItemDragStart(viewHolder);
            } else {
                IDraggableListener iDraggableListener = this.mDraggableListener;
                if (iDraggableListener != null) {
                    iDraggableListener.onItemDragStart(viewHolder);
                }
            }
            viewHolder.itemView.setTag(R.id.BaseQuickAdapter_dragging_support, true);
        } else if (actionState == 1 && !isViewCreateByAdapter(viewHolder)) {
            BaseItemDraggableAdapter baseItemDraggableAdapter2 = this.mBaseItemDraggableAdapter;
            if (baseItemDraggableAdapter2 != null) {
                baseItemDraggableAdapter2.onItemSwipeStart(viewHolder);
            } else {
                IDraggableListener iDraggableListener2 = this.mDraggableListener;
                if (iDraggableListener2 != null) {
                    iDraggableListener2.onItemSwipeStart(viewHolder);
                }
            }
            viewHolder.itemView.setTag(R.id.BaseQuickAdapter_swiping_support, true);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (!isViewCreateByAdapter(viewHolder)) {
            if (viewHolder.itemView.getTag(R.id.BaseQuickAdapter_dragging_support) != null && ((Boolean) viewHolder.itemView.getTag(R.id.BaseQuickAdapter_dragging_support)).booleanValue()) {
                BaseItemDraggableAdapter baseItemDraggableAdapter = this.mBaseItemDraggableAdapter;
                if (baseItemDraggableAdapter != null) {
                    baseItemDraggableAdapter.onItemDragEnd(viewHolder);
                } else {
                    IDraggableListener iDraggableListener = this.mDraggableListener;
                    if (iDraggableListener != null) {
                        iDraggableListener.onItemDragEnd(viewHolder);
                    }
                }
                viewHolder.itemView.setTag(R.id.BaseQuickAdapter_dragging_support, false);
            }
            if (viewHolder.itemView.getTag(R.id.BaseQuickAdapter_swiping_support) != null && ((Boolean) viewHolder.itemView.getTag(R.id.BaseQuickAdapter_swiping_support)).booleanValue()) {
                BaseItemDraggableAdapter baseItemDraggableAdapter2 = this.mBaseItemDraggableAdapter;
                if (baseItemDraggableAdapter2 != null) {
                    baseItemDraggableAdapter2.onItemSwipeClear(viewHolder);
                } else {
                    IDraggableListener iDraggableListener2 = this.mDraggableListener;
                    if (iDraggableListener2 != null) {
                        iDraggableListener2.onItemSwipeClear(viewHolder);
                    }
                }
                viewHolder.itemView.setTag(R.id.BaseQuickAdapter_swiping_support, false);
            }
        }
    }

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (isViewCreateByAdapter(viewHolder)) {
            return makeMovementFlags(0, 0);
        }
        return makeMovementFlags(this.mDragMoveFlags, this.mSwipeMoveFlags);
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        return source.getItemViewType() == target.getItemViewType();
    }

    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder source, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        super.onMoved(recyclerView, source, fromPos, target, toPos, x, y);
        BaseItemDraggableAdapter baseItemDraggableAdapter = this.mBaseItemDraggableAdapter;
        if (baseItemDraggableAdapter != null) {
            baseItemDraggableAdapter.onItemDragMoving(source, target);
            return;
        }
        IDraggableListener iDraggableListener = this.mDraggableListener;
        if (iDraggableListener != null) {
            iDraggableListener.onItemDragMoving(source, target);
        }
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (!isViewCreateByAdapter(viewHolder)) {
            BaseItemDraggableAdapter baseItemDraggableAdapter = this.mBaseItemDraggableAdapter;
            if (baseItemDraggableAdapter != null) {
                baseItemDraggableAdapter.onItemSwiped(viewHolder);
                return;
            }
            IDraggableListener iDraggableListener = this.mDraggableListener;
            if (iDraggableListener != null) {
                iDraggableListener.onItemSwiped(viewHolder);
            }
        }
    }

    public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
        return this.mMoveThreshold;
    }

    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return this.mSwipeThreshold;
    }

    public void setSwipeThreshold(float swipeThreshold) {
        this.mSwipeThreshold = swipeThreshold;
    }

    public void setMoveThreshold(float moveThreshold) {
        this.mMoveThreshold = moveThreshold;
    }

    public void setDragMoveFlags(int dragMoveFlags) {
        this.mDragMoveFlags = dragMoveFlags;
    }

    public void setSwipeMoveFlags(int swipeMoveFlags) {
        this.mSwipeMoveFlags = swipeMoveFlags;
    }

    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Canvas canvas = c;
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (actionState == 1 && !isViewCreateByAdapter(viewHolder)) {
            View itemView = viewHolder2.itemView;
            c.save();
            if (dX > 0.0f) {
                c.clipRect((float) itemView.getLeft(), (float) itemView.getTop(), ((float) itemView.getLeft()) + dX, (float) itemView.getBottom());
                c.translate((float) itemView.getLeft(), (float) itemView.getTop());
            } else {
                c.clipRect(((float) itemView.getRight()) + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                c.translate(((float) itemView.getRight()) + dX, (float) itemView.getTop());
            }
            BaseItemDraggableAdapter baseItemDraggableAdapter = this.mBaseItemDraggableAdapter;
            if (baseItemDraggableAdapter != null) {
                baseItemDraggableAdapter.onItemSwiping(c, viewHolder, dX, dY, isCurrentlyActive);
            } else {
                IDraggableListener iDraggableListener = this.mDraggableListener;
                if (iDraggableListener != null) {
                    iDraggableListener.onItemSwiping(c, viewHolder, dX, dY, isCurrentlyActive);
                }
            }
            c.restore();
        }
    }

    private boolean isViewCreateByAdapter(RecyclerView.ViewHolder viewHolder) {
        int type = viewHolder.getItemViewType();
        return type == 273 || type == 546 || type == 819 || type == 1365;
    }
}
