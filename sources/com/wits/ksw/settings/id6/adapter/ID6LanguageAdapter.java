package com.wits.ksw.settings.id6.adapter;

import android.content.Context;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;

/* loaded from: classes15.dex */
public class ID6LanguageAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<FunctionBean> data;
    private OnFunctionClickListener functionClickListener;
    private int type;

    /* loaded from: classes15.dex */
    public interface OnFunctionClickListener {
        void functonClick(int pos);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public ID6LanguageAdapter(Context context, List<FunctionBean> appInfoList, int type) {
        this.context = context;
        this.data = appInfoList;
        this.type = type;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.list_id6_settings_language, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_functionItem.setText(this.data.get(position).getTitle());
        if (this.type == 0) {
            holder.tv_functionItem.setEnabled(true);
            holder.tv_functionItem.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
        } else {
            holder.tv_functionItem.setEnabled(false);
            holder.tv_functionItem.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_an));
        }
        if (this.data.get(position).isIscheck()) {
            holder.tv_functionItem.setChecked(true);
        } else {
            holder.tv_functionItem.setChecked(false);
        }
        holder.tv_functionItem.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.id6.adapter.ID6LanguageAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (ID6LanguageAdapter.this.functionClickListener != null) {
                    ID6LanguageAdapter.this.functionClickListener.functonClick(position);
                }
            }
        });
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<FunctionBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes15.dex */
    class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton tv_functionItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_functionItem = (RadioButton) itemView.findViewById(C0899R.C0901id.tv_functionItem);
        }
    }
}
