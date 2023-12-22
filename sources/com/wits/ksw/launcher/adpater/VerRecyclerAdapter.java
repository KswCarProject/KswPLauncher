package com.wits.ksw.launcher.adpater;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.utlis_view.RtlNaviRadioButton;
import java.util.List;

/* loaded from: classes11.dex */
public class VerRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<LexusLsAppSelBean> data;
    private IAppsCheckListener iAppsCheckListener;
    IFocusListener iFocusListener;
    private boolean isFinish = false;
    private int layoutRes;

    /* loaded from: classes11.dex */
    public interface IAppsCheckListener {
        void checkedListener(String pkg, String cls, int pos);
    }

    /* loaded from: classes11.dex */
    public interface IFocusListener {
        void isFocus(View v, int postion);
    }

    public VerRecyclerAdapter(Context context, List<LexusLsAppSelBean> appInfoList, int res) {
        this.context = context;
        this.data = appInfoList;
        this.layoutRes = res;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(this.context).inflate(this.layoutRes, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Drawable drawable;
        if (this.data.get(position).getAppIcon() == null) {
            drawable = this.context.getDrawable(C0899R.mipmap.ic_launcher);
        } else {
            drawable = this.data.get(position).getAppIcon();
        }
        if (UiThemeUtils.isID7_ALS(this.context) || UiThemeUtils.isID7_ALS_V2(this.context)) {
            Log.d("display", "LauncherViewModel.screenWidth.get() = " + LauncherViewModel.screenWidth.get());
            if (LauncherViewModel.screenWidth.get().intValue() > 1280) {
                Log.d("display", "LauncherViewModel.screenWidth.get() >1280");
                drawable.setBounds(0, 0, 45, 45);
            } else {
                drawable.setBounds(0, 0, 35, 35);
            }
        } else {
            drawable.setBounds(0, 0, 45, 45);
        }
        holder.rbt_apps.setCompoundDrawables(drawable, null, null, null);
        holder.rbt_apps.setText(this.data.get(position).getAppName());
        holder.rbt_apps.setCompoundDrawablePadding(10);
        holder.rbt_apps.setEnabled(true);
        holder.rbt_apps.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
        holder.rbt_apps.setChecked(this.data.get(position).isChecked());
        if (position == 0) {
            holder.rbt_apps.requestFocus();
        }
        holder.rbt_apps.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.adpater.VerRecyclerAdapter.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("liuhaoid", v.getId() + "");
                if (VerRecyclerAdapter.this.iFocusListener != null) {
                    VerRecyclerAdapter.this.iFocusListener.isFocus(v, position);
                }
            }
        });
        holder.rbt_apps.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.adpater.VerRecyclerAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                for (LexusLsAppSelBean bean : VerRecyclerAdapter.this.data) {
                    bean.setChecked(false);
                }
                ((LexusLsAppSelBean) VerRecyclerAdapter.this.data.get(position)).setChecked(true);
                VerRecyclerAdapter.this.notifyDataSetChanged();
                if (VerRecyclerAdapter.this.iAppsCheckListener != null) {
                    VerRecyclerAdapter.this.iAppsCheckListener.checkedListener(((LexusLsAppSelBean) VerRecyclerAdapter.this.data.get(position)).getAppPkg(), ((LexusLsAppSelBean) VerRecyclerAdapter.this.data.get(position)).getAppMainAty(), position);
                }
            }
        });
        holder.rbt_apps.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.launcher.adpater.VerRecyclerAdapter.3
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("liuhao", "onKey: position=" + position + " action=" + event.getAction() + " keyCode =" + keyCode + " isFinish=" + VerRecyclerAdapter.this.isFinish);
                return event.getKeyCode() == 20 ? VerRecyclerAdapter.this.data.size() - 1 == position : keyCode == 19 && position == 0;
            }
        });
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<LexusLsAppSelBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes11.dex */
    class ViewHolder extends RecyclerView.ViewHolder {
        public RtlNaviRadioButton rbt_apps;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rbt_apps = (RtlNaviRadioButton) itemView.findViewById(C0899R.C0901id.rbt_apps);
        }
    }

    public void setAppsCheckListener(IAppsCheckListener listener) {
        this.iAppsCheckListener = listener;
    }

    public void setIFocusListener(IFocusListener i) {
        this.iFocusListener = i;
    }
}
