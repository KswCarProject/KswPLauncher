package com.chad.library.adapter.base.loadmore;

import com.chad.library.adapter.base.BaseViewHolder;

public abstract class LoadMoreView {
    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_END = 4;
    public static final int STATUS_FAIL = 3;
    public static final int STATUS_LOADING = 2;
    private boolean mLoadMoreEndGone = false;
    private int mLoadMoreStatus = 1;

    public abstract int getLayoutId();

    /* access modifiers changed from: protected */
    public abstract int getLoadEndViewId();

    /* access modifiers changed from: protected */
    public abstract int getLoadFailViewId();

    /* access modifiers changed from: protected */
    public abstract int getLoadingViewId();

    public void setLoadMoreStatus(int loadMoreStatus) {
        this.mLoadMoreStatus = loadMoreStatus;
    }

    public int getLoadMoreStatus() {
        return this.mLoadMoreStatus;
    }

    public void convert(BaseViewHolder holder) {
        switch (this.mLoadMoreStatus) {
            case 1:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                return;
            case 2:
                visibleLoading(holder, true);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                return;
            case 3:
                visibleLoading(holder, false);
                visibleLoadFail(holder, true);
                visibleLoadEnd(holder, false);
                return;
            case 4:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, true);
                return;
            default:
                return;
        }
    }

    private void visibleLoading(BaseViewHolder holder, boolean visible) {
        holder.setGone(getLoadingViewId(), visible);
    }

    private void visibleLoadFail(BaseViewHolder holder, boolean visible) {
        holder.setGone(getLoadFailViewId(), visible);
    }

    private void visibleLoadEnd(BaseViewHolder holder, boolean visible) {
        int loadEndViewId = getLoadEndViewId();
        if (loadEndViewId != 0) {
            holder.setGone(loadEndViewId, visible);
        }
    }

    public final void setLoadMoreEndGone(boolean loadMoreEndGone) {
        this.mLoadMoreEndGone = loadMoreEndGone;
    }

    public final boolean isLoadEndMoreGone() {
        if (getLoadEndViewId() == 0) {
            return true;
        }
        return this.mLoadMoreEndGone;
    }

    @Deprecated
    public boolean isLoadEndGone() {
        return this.mLoadMoreEndGone;
    }
}
