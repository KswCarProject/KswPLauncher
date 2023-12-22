package android.databinding.adapters;

import android.widget.AbsListView;

/* loaded from: classes.dex */
public class AbsListViewBindingAdapter {

    /* loaded from: classes.dex */
    public interface OnScroll {
        void onScroll(AbsListView absListView, int i, int i2, int i3);
    }

    /* loaded from: classes.dex */
    public interface OnScrollStateChanged {
        void onScrollStateChanged(AbsListView absListView, int i);
    }

    public static void setOnScroll(AbsListView view, final OnScroll scrollListener, final OnScrollStateChanged scrollStateListener) {
        view.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: android.databinding.adapters.AbsListViewBindingAdapter.1
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView view2, int scrollState) {
                OnScrollStateChanged onScrollStateChanged = OnScrollStateChanged.this;
                if (onScrollStateChanged != null) {
                    onScrollStateChanged.onScrollStateChanged(view2, scrollState);
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView view2, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                OnScroll onScroll = scrollListener;
                if (onScroll != null) {
                    onScroll.onScroll(view2, firstVisibleItem, visibleItemCount, totalItemCount);
                }
            }
        });
    }
}
