package com.wits.ksw.settings.id6.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.MapBean;
import java.util.List;

public class ID6NaviAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private boolean isShow = false;
    /* access modifiers changed from: private */
    public List<MapBean> mapBanList;
    /* access modifiers changed from: private */
    public IrbtCheckListener rbtCheckListener;

    public interface IrbtCheckListener {
        void checkListener(int i);
    }

    public interface OnItemClickLisen {
        void ItemClickLisen(int i);
    }

    public ID6NaviAdapter(Context context2, List<MapBean> data, boolean show) {
        this.context = context2;
        this.mapBanList = data;
        this.isShow = show;
    }

    public void registCheckListener(IrbtCheckListener listener) {
        this.rbtCheckListener = listener;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.navi_id6_adpter_layout, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
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
        holder.rbt_navi.setEnabled(this.isShow ^ true);
        holder.rbt_navi.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
        holder.rbt_navi.setChecked(this.mapBanList.get(position).isCheck());
        if (this.rbtCheckListener != null) {
            holder.rbt_navi.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    for (MapBean mpb : ID6NaviAdapter.this.mapBanList) {
                        mpb.setCheck(false);
                    }
                    ((MapBean) ID6NaviAdapter.this.mapBanList.get(position)).setCheck(true);
                    ID6NaviAdapter.this.notifyDataSetChanged();
                    ID6NaviAdapter.this.rbtCheckListener.checkListener(position);
                }
            });
        }
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
