package android.support.p004v7.recyclerview.extensions;

import android.support.p004v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.p004v7.util.AdapterListUpdateCallback;
import android.support.p004v7.util.DiffUtil;
import android.support.p004v7.widget.RecyclerView;
import android.support.p004v7.widget.RecyclerView.ViewHolder;
import java.util.List;

/* renamed from: android.support.v7.recyclerview.extensions.ListAdapter */
/* loaded from: classes.dex */
public abstract class ListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final AsyncListDiffer<T> mHelper;

    protected ListAdapter(DiffUtil.ItemCallback<T> diffCallback) {
        this.mHelper = new AsyncListDiffer<>(new AdapterListUpdateCallback(this), new AsyncDifferConfig.Builder(diffCallback).build());
    }

    protected ListAdapter(AsyncDifferConfig<T> config) {
        this.mHelper = new AsyncListDiffer<>(new AdapterListUpdateCallback(this), config);
    }

    public void submitList(List<T> list) {
        this.mHelper.submitList(list);
    }

    protected T getItem(int position) {
        return this.mHelper.getCurrentList().get(position);
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mHelper.getCurrentList().size();
    }
}
