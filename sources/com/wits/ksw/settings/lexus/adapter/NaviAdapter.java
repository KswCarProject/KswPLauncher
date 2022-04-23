package com.wits.ksw.settings.lexus.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.MapBean;
import java.util.List;

public class NaviAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
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

    public NaviAdapter(Context context2, List<MapBean> data) {
        this.context = context2;
        this.mapBanList = data;
    }

    public void registCheckListener(IrbtCheckListener listener) {
        this.rbtCheckListener = listener;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.lexus_navi_adpter_layout, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
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
        if (this.rbtCheckListener != null) {
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
        }
        holder.rbt_navi.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode != 20 || position != NaviAdapter.this.getItemCount() - 1) {
                    return false;
                }
                Log.i("NaviAdapter", "onBindViewHolder: position" + position + ", count:" + NaviAdapter.this.getItemCount());
                return true;
            }
        });
    }

    public int getItemCount() {
        List<MapBean> list = this.mapBanList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RadioButton rbt_navi;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.rbt_navi = (RadioButton) itemView.findViewById(R.id.rbt_navi);
        }
    }
}
