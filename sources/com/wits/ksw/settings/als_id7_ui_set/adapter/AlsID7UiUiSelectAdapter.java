package com.wits.ksw.settings.als_id7_ui_set.adapter;

import android.content.Context;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.bean.UiSelectBean;
import java.util.List;

/* loaded from: classes13.dex */
public class AlsID7UiUiSelectAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = AlsID7UiUiSelectAdapter.class.getName();
    private Context context;
    private List<UiSelectBean> data;
    private OnFunctionClickListener functionClickListener;

    /* loaded from: classes13.dex */
    public interface OnFunctionClickListener {
        void functonClick(int pos);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public AlsID7UiUiSelectAdapter(Context context, List<UiSelectBean> appInfoList) {
        this.context = context;
        this.data = appInfoList;
        Log.i(TAG, "FunctionAdapter: ");
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.als_id7_ui_item_ui_select, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(this.context).load(this.data.get(position).getUiPath()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.img_UiSelct);
        if (this.functionClickListener != null) {
            holder.img_UiSelct.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiUiSelectAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    AlsID7UiUiSelectAdapter.this.functionClickListener.functonClick(position);
                }
            });
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<UiSelectBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes13.dex */
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_UiSelct;

        public ViewHolder(View itemView) {
            super(itemView);
            this.img_UiSelct = (ImageView) itemView.findViewById(C0899R.C0901id.img_UiSelct);
        }
    }
}
