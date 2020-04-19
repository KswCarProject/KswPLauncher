package com.wits.ksw.settings.view_adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.DevBean;
import java.util.List;

public class ListChoseAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    /* access modifiers changed from: private */
    public List<DevBean> mapBanList;
    /* access modifiers changed from: private */
    public IrbtCheckListener rbtCheckListener;

    public interface IrbtCheckListener {
        void checkListener(int i);
    }

    public interface OnItemClickLisen {
        void ItemClickLisen(int i);
    }

    public ListChoseAdapter(Context context2, List<DevBean> data) {
        this.context = context2;
        this.mapBanList = data;
    }

    public void registCheckListener(IrbtCheckListener listener) {
        this.rbtCheckListener = listener;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.navi_ntg6_adpter_layout, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Drawable drawable;
        if (this.mapBanList.get(position).getAppicon() == null) {
            drawable = this.context.getDrawable(R.mipmap.ic_launcher);
        } else {
            drawable = this.mapBanList.get(position).getAppicon();
        }
        drawable.setBounds(0, 0, 40, 40);
        holder.rbt_navi.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        holder.rbt_navi.setText(this.mapBanList.get(position).getName());
        holder.rbt_navi.setCompoundDrawablePadding(10);
        holder.rbt_navi.setEnabled(true);
        holder.rbt_navi.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
        holder.rbt_navi.setChecked(this.mapBanList.get(position).isCheck());
        if (this.rbtCheckListener != null) {
            holder.rbt_navi.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    for (DevBean mpb : ListChoseAdapter.this.mapBanList) {
                        mpb.setCheck(false);
                    }
                    ((DevBean) ListChoseAdapter.this.mapBanList.get(position)).setCheck(true);
                    ListChoseAdapter.this.notifyDataSetChanged();
                    ListChoseAdapter.this.rbtCheckListener.checkListener(position);
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
