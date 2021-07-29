package android.databinding.adapters;

import android.widget.AbsListView;

public class AbsListViewBindingAdapter {

    public interface OnScroll {
        void onScroll(AbsListView absListView, int i, int i2, int i3);
    }

    public interface OnScrollStateChanged {
        void onScrollStateChanged(AbsListView absListView, int i);
    }

    public static void setOnScroll(AbsListView view, final OnScroll scrollListener, final OnScrollStateChanged scrollStateListener) {
        view.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                OnScrollStateChanged onScrollStateChanged = scrollStateListener;
                if (onScrollStateChanged != null) {
                    onScrollStateChanged.onScrollStateChanged(view, scrollState);
                }
            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                OnScroll onScroll = scrollListener;
                if (onScroll != null) {
                    onScroll.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }
            }
        });
    }
}
