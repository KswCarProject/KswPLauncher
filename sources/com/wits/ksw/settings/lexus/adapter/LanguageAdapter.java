package com.wits.ksw.settings.lexus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<FunctionBean> data;
    /* access modifiers changed from: private */
    public OnFunctionClickListener functionClickListener;

    public interface OnFunctionClickListener {
        void functonClick(int i);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public LanguageAdapter(Context context2, List<FunctionBean> appInfoList) {
        this.context = context2;
        this.data = appInfoList;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.lexus_list_settings_language, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_functionItem.setText(this.data.get(position).getTitle());
        if (this.data.get(position).isIscheck()) {
            holder.tv_functionItem.setChecked(true);
        } else {
            holder.tv_functionItem.setChecked(false);
        }
        holder.img_functionItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (LanguageAdapter.this.functionClickListener != null) {
                    LanguageAdapter.this.functionClickListener.functonClick(position);
                }
            }
        });
        if (this.data.size() - 1 == position) {
            holder.img_functionItem.setNextFocusDownId(R.id.img_functionItem);
        }
        if (position == 0) {
            holder.img_functionItem.setNextFocusUpId(R.id.img_functionItem);
        }
    }

    public int getItemCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_functionItem;
        RadioButton tv_functionItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_functionItem = (RadioButton) itemView.findViewById(R.id.tv_functionItem);
            this.tv_functionItem.setEnabled(false);
            this.img_functionItem = (ImageView) itemView.findViewById(R.id.img_functionItem);
        }
    }
}
