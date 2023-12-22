package com.wits.ksw.settings.ntg6.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.MarqueeTextView;
import com.wits.ksw.settings.id7.bean.MapBean;
import java.util.List;

/* loaded from: classes6.dex */
public class Ntg6NaviAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<MapBean> mapBanList;
    private IrbtCheckListener rbtCheckListener;

    /* loaded from: classes6.dex */
    public interface IrbtCheckListener {
        void checkListener(int pos);
    }

    /* loaded from: classes6.dex */
    public interface OnItemClickLisen {
        void ItemClickLisen(int position);
    }

    public Ntg6NaviAdapter(Context context, List<MapBean> data) {
        this.context = context;
        this.mapBanList = data;
    }

    public void setDatas(List<MapBean> data) {
        this.mapBanList = data;
        notifyDataSetChanged();
    }

    public void registCheckListener(IrbtCheckListener listener) {
        this.rbtCheckListener = listener;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.navi_ntg6_adpter_layout, viewGroup, false));
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
        holder.mtv_tv.setText(this.mapBanList.get(position).getName());
        Log.d("NaviAdapter", "appName: " + this.mapBanList.get(position).getName());
        holder.rbt_navi.setCompoundDrawablePadding(10);
        holder.rbt_navi.setEnabled(true);
        holder.rbt_navi.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
        holder.rbt_navi.setChecked(this.mapBanList.get(position).isCheck());
        if (this.rbtCheckListener != null) {
            holder.rbt_navi.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.ntg6.adapter.Ntg6NaviAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    for (MapBean mpb : Ntg6NaviAdapter.this.mapBanList) {
                        mpb.setCheck(false);
                    }
                    ((MapBean) Ntg6NaviAdapter.this.mapBanList.get(position)).setCheck(true);
                    Ntg6NaviAdapter.this.notifyDataSetChanged();
                    Ntg6NaviAdapter.this.rbtCheckListener.checkListener(position);
                }
            });
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

    /* loaded from: classes6.dex */
    class MyViewHolder extends RecyclerView.ViewHolder {
        MarqueeTextView mtv_tv;
        RadioButton rbt_navi;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.rbt_navi = (RadioButton) itemView.findViewById(C0899R.C0901id.rbt_navi);
            this.mtv_tv = (MarqueeTextView) itemView.findViewById(C0899R.C0901id.mtv_tv);
        }
    }
}
