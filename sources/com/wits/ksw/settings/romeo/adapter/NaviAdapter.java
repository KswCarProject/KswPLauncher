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
import android.widget.RadioButton;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.romeo.interfaces.IUpdateListBg;
import java.util.List;

/* loaded from: classes8.dex */
public class NaviAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<MapBean> mapBanList;
    private IrbtCheckListener rbtCheckListener;
    private IUpdateListBg updateListBg;

    /* loaded from: classes8.dex */
    public interface IrbtCheckListener {
        void checkListener(int pos);
    }

    /* loaded from: classes8.dex */
    public interface OnItemClickLisen {
        void ItemClickLisen(int position);
    }

    public NaviAdapter(Context context, List<MapBean> data) {
        this.context = context;
        this.mapBanList = data;
    }

    public void setIUpdateListBg(IUpdateListBg updateListBg) {
        this.updateListBg = updateListBg;
        Log.d("NaviAdapter", "setIUpdateListBg updateListBg=" + this.updateListBg);
    }

    public void registCheckListener(IrbtCheckListener listener) {
        this.rbtCheckListener = listener;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.romeo_navi_adpter_layout, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Drawable drawable;
        if (this.mapBanList.get(position).getMapicon() == null) {
            drawable = this.context.getDrawable(C0899R.mipmap.ic_launcher);
        } else {
            drawable = this.mapBanList.get(position).getMapicon();
        }
        drawable.setBounds(0, 0, 40, 40);
        holder.rbt_navi.setCompoundDrawables(drawable, null, null, null);
        holder.rbt_navi.setText(this.mapBanList.get(position).getName());
        Log.d("NaviAdapter", "appName: " + this.mapBanList.get(position).getName());
        holder.rbt_navi.setCompoundDrawablePadding(10);
        holder.rbt_navi.setEnabled(true);
        holder.rbt_navi.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
        holder.rbt_navi.setChecked(this.mapBanList.get(position).isCheck());
        holder.rbt_navi.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.romeo.adapter.NaviAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                for (MapBean mpb : NaviAdapter.this.mapBanList) {
                    mpb.setCheck(false);
                }
                ((MapBean) NaviAdapter.this.mapBanList.get(position)).setCheck(true);
                NaviAdapter.this.notifyDataSetChanged();
                NaviAdapter.this.rbtCheckListener.checkListener(position);
            }
        });
        holder.rbt_navi.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.settings.romeo.adapter.NaviAdapter.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 20 && position == NaviAdapter.this.getItemCount() - 1) {
                    Log.i("NaviAdapter", "onBindViewHolder: position" + position + ", count:" + NaviAdapter.this.getItemCount());
                    return true;
                }
                return false;
            }
        });
        holder.rbt_navi.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.settings.romeo.adapter.NaviAdapter.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    NaviAdapter.this.updateListBg.updateListBg(0, 0);
                    return;
                }
                Log.d("NaviAdapter", "onFocusChange");
                NaviAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 1);
            }
        });
        holder.rbt_navi.setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.romeo.adapter.NaviAdapter.4
            @Override // android.view.View.OnTouchListener
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

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MapBean> list = this.mapBanList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes8.dex */
    class MyViewHolder extends RecyclerView.ViewHolder {
        RadioButton rbt_navi;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.rbt_navi = (RadioButton) itemView.findViewById(C0899R.C0901id.rbt_navi);
        }
    }
}
