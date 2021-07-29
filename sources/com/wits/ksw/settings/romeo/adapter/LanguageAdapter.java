package com.wits.ksw.settings.romeo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.romeo.interfaces.IUpdateListBg;
import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<FunctionBean> data;
    /* access modifiers changed from: private */
    public OnFunctionClickListener functionClickListener;
    /* access modifiers changed from: private */
    public IUpdateListBg updateListBg;

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

    public void setIUpdateListBg(IUpdateListBg updateListBg2) {
        this.updateListBg = updateListBg2;
        Log.d("LanguageAdapter", "setIUpdateListBg// updateListBg=" + this.updateListBg);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.romeo_list_settings_language, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_functionItem.setEnabled(true);
        holder.tv_functionItem.setText(this.data.get(position).getTitle());
        if (this.data.get(position).isIscheck()) {
            holder.tv_functionItem.setChecked(true);
        } else {
            holder.tv_functionItem.setChecked(false);
        }
        holder.tv_functionItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("LanguageAdapter", "onClick");
                if (LanguageAdapter.this.functionClickListener != null) {
                    LanguageAdapter.this.functionClickListener.functonClick(position);
                }
            }
        });
        holder.tv_functionItem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.d("LanguageAdapter", "onFocusChange");
                    LanguageAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 1);
                    return;
                }
                LanguageAdapter.this.updateListBg.updateListBg(0, 0);
            }
        });
        holder.tv_functionItem.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("LanguageAdapter", "onTouch updateListBg=" + LanguageAdapter.this.updateListBg);
                if (event.getAction() == 0) {
                    LanguageAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 2);
                } else if (event.getAction() == 1) {
                    LanguageAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 0);
                } else if (event.getAction() == 3) {
                    LanguageAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 0);
                }
                return false;
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
        List<FunctionBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_functionItem;
        RadioButton tv_functionItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_functionItem = (RadioButton) itemView.findViewById(R.id.tv_functionItem);
            this.img_functionItem = (ImageView) itemView.findViewById(R.id.img_functionItem);
        }
    }
}
