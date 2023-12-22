package com.wits.ksw.settings.lexus.adapter;

import android.content.Context;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;

/* loaded from: classes11.dex */
public class LanguageAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<FunctionBean> data;
    private OnFunctionClickListener functionClickListener;

    /* loaded from: classes11.dex */
    public interface OnFunctionClickListener {
        void functonClick(int pos);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public LanguageAdapter(Context context, List<FunctionBean> appInfoList) {
        this.context = context;
        this.data = appInfoList;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.lexus_list_settings_language, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_functionItem.setText(this.data.get(position).getTitle());
        if (this.data.get(position).isIscheck()) {
            holder.tv_functionItem.setChecked(true);
        } else {
            holder.tv_functionItem.setChecked(false);
        }
        holder.img_functionItem.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.lexus.adapter.LanguageAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (LanguageAdapter.this.functionClickListener != null) {
                    LanguageAdapter.this.functionClickListener.functonClick(position);
                }
            }
        });
        if (this.data.size() - 1 == position) {
            holder.img_functionItem.setNextFocusDownId(C0899R.C0901id.img_functionItem);
        }
        if (position == 0) {
            holder.img_functionItem.setNextFocusUpId(C0899R.C0901id.img_functionItem);
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<FunctionBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes11.dex */
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_functionItem;
        RadioButton tv_functionItem;

        public ViewHolder(View itemView) {
            super(itemView);
            RadioButton radioButton = (RadioButton) itemView.findViewById(C0899R.C0901id.tv_functionItem);
            this.tv_functionItem = radioButton;
            radioButton.setEnabled(false);
            this.img_functionItem = (ImageView) itemView.findViewById(C0899R.C0901id.img_functionItem);
        }
    }
}
