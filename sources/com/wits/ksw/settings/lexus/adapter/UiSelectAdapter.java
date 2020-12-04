package com.wits.ksw.settings.lexus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.UiSelectBean;
import java.util.List;

public class UiSelectAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = UiSelectAdapter.class.getName();
    private Context context;
    private List<UiSelectBean> data;
    /* access modifiers changed from: private */
    public OnFunctionClickListener functionClickListener;

    public interface OnFunctionClickListener {
        void functonClick(int i);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public UiSelectAdapter(Context context2, List<UiSelectBean> appInfoList) {
        this.context = context2;
        this.data = appInfoList;
        Log.i(TAG, "FunctionAdapter: ");
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_ui_select, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ((RequestBuilder) ((RequestBuilder) Glide.with(this.context).load(this.data.get(position).getUiPath()).skipMemoryCache(true)).diskCacheStrategy(DiskCacheStrategy.NONE)).into(holder.img_UiSelct);
        if (this.functionClickListener != null) {
            holder.img_UiSelct.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    UiSelectAdapter.this.functionClickListener.functonClick(position);
                }
            });
        }
    }

    public int getItemCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_UiSelct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img_UiSelct = (ImageView) itemView.findViewById(R.id.img_UiSelct);
        }
    }
}
