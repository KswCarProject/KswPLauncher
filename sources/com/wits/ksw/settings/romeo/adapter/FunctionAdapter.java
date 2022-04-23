package com.wits.ksw.settings.romeo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;

public class FunctionAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public static final String TAG = FunctionAdapter.class.getName();
    private Context context;
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    /* access modifiers changed from: private */
    public int downPosition = -1;
    /* access modifiers changed from: private */
    public OnFunctionClickListener functionClickListener;
    /* access modifiers changed from: private */
    public boolean isFinish = false;
    /* access modifiers changed from: private */
    public OnItemBgChangeListener itemBgChangeListener;

    public interface OnFunctionClickListener {
        void functonClick(int i);
    }

    public interface OnItemBgChangeListener {
        void onChangeItemSelect(int i, int i2, int i3);

        void onClearSelect(int i, int i2, int i3);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public void registOnItemBgChangeListener(OnItemBgChangeListener listener) {
        this.itemBgChangeListener = listener;
    }

    public FunctionAdapter(Context context2, List<FunctionBean> appInfoList) {
        this.context = context2;
        this.data = appInfoList;
        Log.i(TAG, "FunctionAdapter: ");
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.romeo_list_settings_function, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (this.data.get(position).getIcon() != 0) {
            Drawable drawable = ContextCompat.getDrawable(this.context, this.data.get(position).getIcon());
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tv_functionItem.setImageDrawable(drawable);
        }
        this.data.get(position).isIscheck();
        holder.tv_functionItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(FunctionAdapter.TAG, "tv_functionItem onClick position=" + position);
                OnFunctionClickListener unused = FunctionAdapter.this.functionClickListener;
            }
        });
        holder.tv_functionItem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.d(FunctionAdapter.TAG, "onFocusChange " + position);
                    if (FunctionAdapter.this.itemBgChangeListener != null) {
                        FunctionAdapter.this.itemBgChangeListener.onChangeItemSelect(holder.itemView.getTop(), 1, position);
                        if (position == FunctionAdapter.this.downPosition) {
                            FunctionAdapter.this.itemBgChangeListener.onChangeItemSelect(holder.itemView.getTop(), 2, position);
                        }
                    }
                } else if (FunctionAdapter.this.itemBgChangeListener != null) {
                    FunctionAdapter.this.itemBgChangeListener.onClearSelect(holder.itemView.getTop(), 1, position);
                }
            }
        });
        holder.tv_functionItem.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(FunctionAdapter.TAG, "onTouch " + position + " action " + event.getAction());
                if (event.getAction() == 0) {
                    if (FunctionAdapter.this.itemBgChangeListener == null) {
                        return false;
                    }
                    int unused = FunctionAdapter.this.downPosition = position;
                    FunctionAdapter.this.itemBgChangeListener.onChangeItemSelect(holder.itemView.getTop(), 2, position);
                    return false;
                } else if (event.getAction() != 1 || FunctionAdapter.this.functionClickListener == null) {
                    return false;
                } else {
                    FunctionAdapter.this.functionClickListener.functonClick(position);
                    return false;
                }
            }
        });
        holder.tv_functionItem.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i(FunctionAdapter.TAG, "onKey: position=" + position + " action=" + event.getAction() + " keyCode =" + keyCode + " isFinish=" + FunctionAdapter.this.isFinish);
                if (event.getKeyCode() == 20) {
                    if (FunctionAdapter.this.data.size() - 1 == position) {
                        return true;
                    }
                    return false;
                } else if (keyCode != 19) {
                    if ((keyCode == 23 || keyCode == 66) && FunctionAdapter.this.functionClickListener != null) {
                        FunctionAdapter.this.functionClickListener.functonClick(position);
                    }
                    if (keyCode != 21) {
                        boolean unused = FunctionAdapter.this.isFinish = false;
                    }
                    FunctionAdapter.this.onBackPressed(keyCode, event);
                    return false;
                } else if (position == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void onBackPressed(int keyCode, KeyEvent event) {
        if (event.getAction() == 1 && keyCode == 21) {
            if (this.isFinish) {
                KswUtils.sendKeyDownUpSync(4);
                this.isFinish = false;
            }
            this.isFinish = true;
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
        ImageView tv_functionItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_functionItem = (ImageView) itemView.findViewById(R.id.tv_functionItem);
        }
    }
}
