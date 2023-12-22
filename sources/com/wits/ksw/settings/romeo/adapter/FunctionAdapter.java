package com.wits.ksw.settings.romeo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;

/* loaded from: classes8.dex */
public class FunctionAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = FunctionAdapter.class.getName();
    private Context context;
    private List<FunctionBean> data;
    private OnFunctionClickListener functionClickListener;
    private OnItemBgChangeListener itemBgChangeListener;
    private boolean isFinish = false;
    private int downPosition = -1;

    /* loaded from: classes8.dex */
    public interface OnFunctionClickListener {
        void functonClick(int pos);
    }

    /* loaded from: classes8.dex */
    public interface OnItemBgChangeListener {
        void onChangeItemSelect(int top, int type, int position);

        void onClearSelect(int top, int type, int position);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public void registOnItemBgChangeListener(OnItemBgChangeListener listener) {
        this.itemBgChangeListener = listener;
    }

    public FunctionAdapter(Context context, List<FunctionBean> appInfoList) {
        this.context = context;
        this.data = appInfoList;
        Log.i(TAG, "FunctionAdapter: ");
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.romeo_list_settings_function, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (this.data.get(position).getIcon() != 0) {
            Drawable drawable = ContextCompat.getDrawable(this.context, this.data.get(position).getIcon());
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tv_functionItem.setImageDrawable(drawable);
        }
        this.data.get(position).isIscheck();
        holder.tv_functionItem.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.romeo.adapter.FunctionAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Log.d(FunctionAdapter.TAG, "tv_functionItem onClick position=" + position);
                OnFunctionClickListener unused = FunctionAdapter.this.functionClickListener;
            }
        });
        holder.tv_functionItem.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.settings.romeo.adapter.FunctionAdapter.2
            @Override // android.view.View.OnFocusChangeListener
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
        holder.tv_functionItem.setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.romeo.adapter.FunctionAdapter.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(FunctionAdapter.TAG, "onTouch " + position + " action " + event.getAction());
                if (event.getAction() == 0) {
                    if (FunctionAdapter.this.itemBgChangeListener != null) {
                        FunctionAdapter.this.downPosition = position;
                        FunctionAdapter.this.itemBgChangeListener.onChangeItemSelect(holder.itemView.getTop(), 2, position);
                        return false;
                    }
                    return false;
                } else if (event.getAction() == 1 && FunctionAdapter.this.functionClickListener != null) {
                    FunctionAdapter.this.functionClickListener.functonClick(position);
                    return false;
                } else {
                    return false;
                }
            }
        });
        holder.tv_functionItem.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.settings.romeo.adapter.FunctionAdapter.4
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i(FunctionAdapter.TAG, "onKey: position=" + position + " action=" + event.getAction() + " keyCode =" + keyCode + " isFinish=" + FunctionAdapter.this.isFinish);
                if (event.getKeyCode() == 20) {
                    return FunctionAdapter.this.data.size() - 1 == position;
                } else if (keyCode == 19) {
                    return position == 0;
                } else {
                    if ((keyCode == 23 || keyCode == 66) && FunctionAdapter.this.functionClickListener != null) {
                        FunctionAdapter.this.functionClickListener.functonClick(position);
                    }
                    if (keyCode != 21) {
                        FunctionAdapter.this.isFinish = false;
                    }
                    FunctionAdapter.this.onBackPressed(keyCode, event);
                    return false;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBackPressed(int keyCode, KeyEvent event) {
        if (event.getAction() == 1 && keyCode == 21) {
            if (this.isFinish) {
                KswUtils.sendKeyDownUpSync(4);
                this.isFinish = false;
            }
            this.isFinish = true;
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

    /* loaded from: classes8.dex */
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView tv_functionItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_functionItem = (ImageView) itemView.findViewById(C0899R.C0901id.tv_functionItem);
        }
    }
}
