package com.wits.ksw.settings.romeo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.romeo.interfaces.IUpdateListBg;
import java.util.List;

public class NaviAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    /* access modifiers changed from: private */
    public List<MapBean> mapBanList;
    /* access modifiers changed from: private */
    public IrbtCheckListener rbtCheckListener;
    /* access modifiers changed from: private */
    public IUpdateListBg updateListBg;

    public interface IrbtCheckListener {
        void checkListener(int i);
    }

    public interface OnItemClickLisen {
        void ItemClickLisen(int i);
    }

    public NaviAdapter(Context context2, List<MapBean> data) {
        this.context = context2;
        this.mapBanList = data;
    }

    public void setIUpdateListBg(IUpdateListBg updateListBg2) {
        this.updateListBg = updateListBg2;
        Log.d("NaviAdapter", "setIUpdateListBg updateListBg=" + this.updateListBg);
    }

    public void registCheckListener(IrbtCheckListener listener) {
        this.rbtCheckListener = listener;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.romeo_navi_adpter_layout, viewGroup, false));
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint({"RecyclerView"}) final int position) {
        Drawable drawable;
        if (this.mapBanList.get(position).getMapicon() == null) {
            drawable = this.context.getDrawable(R.mipmap.ic_launcher);
        } else {
            drawable = this.mapBanList.get(position).getMapicon();
        }
        drawable.setBounds(0, 0, 40, 40);
        holder.rbt_navi.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        holder.rbt_navi.setText(this.mapBanList.get(position).getName());
        Log.d("NaviAdapter", "appName: " + this.mapBanList.get(position).getName());
        holder.rbt_navi.setCompoundDrawablePadding(10);
        holder.rbt_navi.setEnabled(true);
        holder.rbt_navi.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
        holder.rbt_navi.setChecked(this.mapBanList.get(position).isCheck());
        holder.rbt_navi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                for (MapBean mpb : NaviAdapter.this.mapBanList) {
                    mpb.setCheck(false);
                }
                ((MapBean) NaviAdapter.this.mapBanList.get(position)).setCheck(true);
                NaviAdapter.this.notifyDataSetChanged();
                NaviAdapter.this.rbtCheckListener.checkListener(position);
            }
        });
        holder.rbt_navi.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode != 20 || position != NaviAdapter.this.getItemCount() - 1) {
                    return false;
                }
                Log.i("NaviAdapter", "onBindViewHolder: position" + position + ", count:" + NaviAdapter.this.getItemCount());
                return true;
            }
        });
        holder.rbt_navi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.d("NaviAdapter", "onFocusChange");
                    NaviAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 1);
                    return;
                }
                NaviAdapter.this.updateListBg.updateListBg(0, 0);
            }
        });
        holder.rbt_navi.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("NaviAdapter", "onTouch updateListBg=" + NaviAdapter.this.updateListBg);
                if (event.getAction() == 0) {
                    NaviAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 2);
                } else if (event.getAction() == 1) {
                    NaviAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 0);
                } else if (event.getAction() == 3) {
                    NaviAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 0);
                }
                return false;
            }
        });
    }

    public int getItemCount() {
        if (this.mapBanList == null) {
            return 0;
        }
        return this.mapBanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RadioButton rbt_navi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rbt_navi = (RadioButton) itemView.findViewById(R.id.rbt_navi);
        }
    }
}
