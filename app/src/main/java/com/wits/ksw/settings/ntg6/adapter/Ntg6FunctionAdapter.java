package com.wits.ksw.settings.ntg6.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;

public class Ntg6FunctionAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<FunctionBean> data;
    /* access modifiers changed from: private */
    public OnFunctionClickListener functionClickListener;

    public interface OnFunctionClickListener {
        void funSwitImage(int i);

        void functonClick(int i);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public Ntg6FunctionAdapter(Context context2, List<FunctionBean> appInfoList) {
        this.context = context2;
        this.data = appInfoList;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.list_settings_ntg6_function, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (position == 0) {
            holder.relat_functionItem.setNextFocusUpId(R.id.relat_ntg6functionItem);
        }
        if (position == this.data.size() - 1) {
            holder.relat_functionItem.setNextFocusDownId(R.id.relat_ntg6functionItem);
        }
        if (this.data.get(position).getIcon() != 0) {
            Drawable drawable = ContextCompat.getDrawable(this.context, this.data.get(position).getIcon());
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tv_functionItem.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        }
        holder.tv_functionItem.setText(this.data.get(position).getTitle());
        if (position == 0) {
            holder.relat_functionItem.setBackground(ContextCompat.getDrawable(this.context, R.drawable.ntg6_start_item));
        } else if (position == this.data.size() - 1) {
            holder.relat_functionItem.setBackground(ContextCompat.getDrawable(this.context, R.drawable.ntg6_end_item));
        } else {
            holder.relat_functionItem.setBackground(ContextCompat.getDrawable(this.context, R.drawable.ntg6_zhong_item));
        }
        holder.relat_functionItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Ntg6FunctionAdapter.this.functionClickListener != null) {
                    Ntg6FunctionAdapter.this.functionClickListener.functonClick(position);
                }
            }
        });
        holder.relat_functionItem.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() != 1 || Ntg6FunctionAdapter.this.functionClickListener == null) {
                    return false;
                }
                Ntg6FunctionAdapter.this.functionClickListener.funSwitImage(position);
                return false;
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
        RelativeLayout relat_functionItem;
        TextView tv_functionItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_functionItem = (TextView) itemView.findViewById(R.id.tv_functionItem);
            this.relat_functionItem = (RelativeLayout) itemView.findViewById(R.id.relat_ntg6functionItem);
        }
    }
}
