package com.wits.ksw.settings.id6.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;

public class ID6LanguageAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<FunctionBean> data;
    /* access modifiers changed from: private */
    public OnFunctionClickListener functionClickListener;
    private int type;

    public interface OnFunctionClickListener {
        void functonClick(int i);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public ID6LanguageAdapter(Context context2, List<FunctionBean> appInfoList, int type2) {
        this.context = context2;
        this.data = appInfoList;
        this.type = type2;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.list_id6_settings_language, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_functionItem.setText(this.data.get(position).getTitle());
        if (this.type == 0) {
            holder.tv_functionItem.setEnabled(true);
            holder.tv_functionItem.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
        } else {
            holder.tv_functionItem.setEnabled(false);
            holder.tv_functionItem.setTextColor(ContextCompat.getColor(this.context, R.color.text_an));
        }
        if (this.data.get(position).isIscheck()) {
            holder.tv_functionItem.setChecked(true);
        } else {
            holder.tv_functionItem.setChecked(false);
        }
        holder.tv_functionItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ID6LanguageAdapter.this.functionClickListener != null) {
                    ID6LanguageAdapter.this.functionClickListener.functonClick(position);
                }
            }
        });
    }

    public int getItemCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton tv_functionItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_functionItem = (RadioButton) itemView.findViewById(R.id.tv_functionItem);
        }
    }
}
