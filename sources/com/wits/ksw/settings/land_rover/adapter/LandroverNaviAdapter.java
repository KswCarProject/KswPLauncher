package com.wits.ksw.settings.land_rover.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.bean.MapBean;
import java.util.List;

/* loaded from: classes16.dex */
public class LandroverNaviAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<MapBean> mapBanList;
    private IrbtCheckListener rbtCheckListener;

    /* loaded from: classes16.dex */
    public interface IrbtCheckListener {
        void checkListener(int pos);
    }

    /* loaded from: classes16.dex */
    public interface OnItemClickLisen {
        void ItemClickLisen(int position);
    }

    public LandroverNaviAdapter(Context context, List<MapBean> data) {
        this.context = context;
        this.mapBanList = data;
    }

    public void registCheckListener(IrbtCheckListener listener) {
        this.rbtCheckListener = listener;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.land_rover_navi_adpter_layout, viewGroup, false));
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
        if (this.rbtCheckListener != null) {
            holder.img_functionItem.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.land_rover.adapter.LandroverNaviAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    for (MapBean mpb : LandroverNaviAdapter.this.mapBanList) {
                        mpb.setCheck(false);
                    }
                    ((MapBean) LandroverNaviAdapter.this.mapBanList.get(position)).setCheck(true);
                    LandroverNaviAdapter.this.notifyDataSetChanged();
                    LandroverNaviAdapter.this.rbtCheckListener.checkListener(position);
                }
            });
        }
        holder.img_functionItem.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.settings.land_rover.adapter.LandroverNaviAdapter.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 20 && position == LandroverNaviAdapter.this.getItemCount() - 1) {
                    Log.i("NaviAdapter", "onBindViewHolder: position" + position + ", count:" + LandroverNaviAdapter.this.getItemCount());
                    return true;
                }
                return false;
            }
        });
        if (this.mapBanList.size() - 1 == position) {
            holder.img_functionItem.setNextFocusDownId(C0899R.C0901id.img_functionItem);
        }
        if (position == 0) {
            holder.img_functionItem.setNextFocusUpId(C0899R.C0901id.img_functionItem);
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MapBean> list = this.mapBanList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes16.dex */
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_functionItem;
        RadioButton rbt_navi;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.rbt_navi = (RadioButton) itemView.findViewById(C0899R.C0901id.rbt_navi);
            this.img_functionItem = (ImageView) itemView.findViewById(C0899R.C0901id.img_functionItem);
        }
    }
}
