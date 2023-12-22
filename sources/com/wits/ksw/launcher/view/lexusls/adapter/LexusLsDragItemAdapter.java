package com.wits.ksw.launcher.view.lexusls.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.launcher.view.lexusls.LexusLsConfig;
import com.wits.ksw.launcher.view.lexusls.drag.DragListener;
import com.wits.ksw.launcher.view.lexusls.drag.DraggableLayout;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;
import com.wits.pms.statuscontrol.WitsCommand;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class LexusLsDragItemAdapter extends RecyclerView.Adapter<mBaseViewHolder> implements DragListener {
    public static final String TAG = "LexusLsDragItemAdapter";
    IOnAddAppClickListener mAddAppListener;
    private View mClickView;
    private Context mContext;
    private ArrayList<LexusLsAppSelBean> mList;
    private LauncherViewModel viewModel;
    private int defaultSelection = -1;
    private ItemDragListener dragListener = null;
    private View.OnLongClickListener longClickListener = null;
    private View.OnClickListener clickListener = null;
    private View.OnClickListener clickMenuListener = null;

    /* loaded from: classes9.dex */
    public interface IOnAddAppClickListener {
        void onClick(View view);
    }

    /* loaded from: classes9.dex */
    public interface ItemDragListener {
        void onItemDragStarted(View source);

        void onItemDropCompleted(View source, View target, boolean success);
    }

    /* loaded from: classes9.dex */
    public interface OnAdapterItemClickListener {
        void onAdapterItemClick(View view, int position);
    }

    public LexusLsDragItemAdapter(ArrayList<LexusLsAppSelBean> list, LauncherViewModel viewModel, Context context) {
        this.mList = new ArrayList<>();
        this.mList = list;
        this.viewModel = viewModel;
        this.mContext = context;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public mBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(C0899R.C0902layout.lexus_ls_drag_item_layout, parent, false);
        return new mBaseViewHolder(itemView);
    }

    public void setItemClickEffect(View mClickView) {
        this.mClickView = mClickView;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(mBaseViewHolder holder, int position) {
        LexusLsAppSelBean appItem = this.mList.get(position);
        holder.tv_title.setText(appItem.getAppName());
        holder.icon.setImageDrawable(appItem.getAppIcon());
        if (this.longClickListener != null) {
            holder.draggable_layout.setOnLongClickListener(this.longClickListener);
        }
        if (this.clickListener != null) {
            holder.draggable_layout.setOnClickListener(this.clickListener);
        }
        holder.draggable_layout.setAnimaView(holder.draggable_layout);
        holder.draggable_layout.setItem(appItem);
        holder.draggable_layout.canDelete(appItem.isDeletable());
        holder.draggable_layout.setDragListener(this);
        holder.draggable_layout.setAnimaView(holder.draggable_layout);
        holder.draggable_layout.setOnClickListener(new mItemOnclick(position, holder.draggable_layout));
        if (this.defaultSelection == -1) {
            this.defaultSelection = KswUtils.getLexusLsLastPosition(this.mContext);
        }
        Log.d(TAG, "onBindViewHolder: defaultSelection=" + this.defaultSelection);
        if (position == this.defaultSelection) {
            Log.e("pos == defaultSelection", "defaultSelection = " + this.defaultSelection);
            holder.layoutLL.setFocusableInTouchMode(true);
            holder.layoutLL.setFocusable(true);
            holder.layoutLL.requestFocus();
            holder.layoutLL.setSelected(true);
            return;
        }
        holder.layoutLL.setSelected(false);
    }

    public void setSelectPosition(int position) {
        if (position >= 0 && position <= this.mList.size()) {
            this.defaultSelection = position;
            notifyDataSetChanged();
        }
    }

    public void sendMcuCommand() {
        try {
            Log.d(TAG, "sendMcuCommand: \u53d1\u9001MCU\u53d1\u6307\u4ee4");
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "sendMcuCommand: " + e.toString());
        }
    }

    /* loaded from: classes9.dex */
    class mItemOnclick implements View.OnClickListener {
        View mView;
        int pos;

        mItemOnclick(int pos, View mView) {
            this.pos = pos;
            this.mView = mView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            Log.e(LexusLsDragItemAdapter.TAG, "pos = " + this.pos);
            KswUtils.saveLexusLsLastPosition(LexusLsDragItemAdapter.this.mContext, this.pos);
            LexusLsDragItemAdapter.this.setSelectPosition(this.pos);
            LexusLsAppSelBean appItem = (LexusLsAppSelBean) LexusLsDragItemAdapter.this.mList.get(this.pos);
            if (UiThemeUtils.isLEXUS_LS_UI(LexusLsDragItemAdapter.this.mContext)) {
                if (!LexusLsDragItemAdapter.isMenu(appItem)) {
                    String pkg = appItem.getAppPkg();
                    String cls = appItem.getAppMainAty();
                    ComponentName componentName = new ComponentName(pkg, cls);
                    Intent intent = new Intent();
                    intent.addFlags(270532608);
                    intent.setComponent(componentName);
                    LexusLsDragItemAdapter.this.mContext.startActivity(intent);
                    LexusLsDragItemAdapter.this.sendMcuCommand();
                    return;
                }
                String pkgName = appItem.getAppPkg();
                String[] menuPkgs = LexusLsConfig.PKG_MENU_STRS;
                LOGE.m43E("LexusLsDragItemAdapter pkgName = " + pkgName);
                if (menuPkgs[0].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openNaviApp(v);
                } else if (menuPkgs[1].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openMusicMulti(v);
                } else if (menuPkgs[2].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openBtApp(v);
                } else if (menuPkgs[3].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openApps(v);
                } else if (menuPkgs[4].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openVideoMulti(v);
                } else if (menuPkgs[5].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openLexusCar(v);
                } else if (menuPkgs[6].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openSettings(v);
                } else if (menuPkgs[7].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openAirControl(v);
                } else if (menuPkgs[8].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openDvr(v);
                } else if (menuPkgs[9].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openDashboard(v);
                } else if (menuPkgs[10].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openShouJiHuLian(v);
                } else if (menuPkgs[11].equals(pkgName)) {
                    LexusLsDragItemAdapter.this.viewModel.openFileManager(v);
                } else if (menuPkgs[12].equals(pkgName) && LexusLsDragItemAdapter.this.mAddAppListener != null) {
                    LexusLsDragItemAdapter.this.mAddAppListener.onClick(v);
                }
            } else if (!LexusLsDragItemAdapter.isMenu_V2(appItem)) {
                String pkg2 = appItem.getAppPkg();
                String cls2 = appItem.getAppMainAty();
                ComponentName componentName2 = new ComponentName(pkg2, cls2);
                Intent intent2 = new Intent();
                intent2.addFlags(270532608);
                intent2.setComponent(componentName2);
                LexusLsDragItemAdapter.this.mContext.startActivity(intent2);
                LexusLsDragItemAdapter.this.sendMcuCommand();
            } else {
                String pkgName2 = appItem.getAppPkg();
                String[] menuPkgs2 = LexusLsConfig.PKG_MENU_STRS_V2;
                LOGE.m43E("LexusLsDragItemAdapter pkgName = " + pkgName2);
                if (menuPkgs2[0].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openNaviApp(v);
                } else if (menuPkgs2[1].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openMusicMulti(v);
                } else if (menuPkgs2[2].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openBtApp(v);
                } else if (menuPkgs2[3].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openApps(v);
                } else if (menuPkgs2[4].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openVideoMulti(v);
                } else if (menuPkgs2[5].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openLexusCar(v);
                } else if (menuPkgs2[6].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openSettings(v);
                } else if (menuPkgs2[7].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openAirControl(v);
                } else if (menuPkgs2[8].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openDvr(v);
                } else if (menuPkgs2[9].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openDashboard(v);
                } else if (menuPkgs2[10].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openShouJiHuLian(v);
                } else if (menuPkgs2[11].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openFileManager(v);
                } else if (menuPkgs2[12].equals(pkgName2)) {
                    LexusLsDragItemAdapter.this.viewModel.openWeatherApp(v);
                } else if (menuPkgs2[13].equals(pkgName2) && LexusLsDragItemAdapter.this.mAddAppListener != null) {
                    LexusLsDragItemAdapter.this.mAddAppListener.onClick(v);
                }
            }
        }
    }

    public void setOnClickAddApp(IOnAddAppClickListener tmpListener) {
        this.mAddAppListener = tmpListener;
    }

    public void setItemDragListener(ItemDragListener listener) {
        this.dragListener = listener;
    }

    @Override // com.wits.ksw.launcher.view.lexusls.drag.DragListener
    public void onDragStarted(View source) {
        ItemDragListener itemDragListener = this.dragListener;
        if (itemDragListener != null) {
            itemDragListener.onItemDragStarted(source);
        }
    }

    @Override // com.wits.ksw.launcher.view.lexusls.drag.DragListener
    public void onDropCompleted(View source, View target, boolean success) {
        ItemDragListener itemDragListener = this.dragListener;
        if (itemDragListener != null) {
            itemDragListener.onItemDropCompleted(source, target, success);
        }
    }

    public void setLongClickListener(View.OnLongClickListener listener) {
        this.longClickListener = listener;
    }

    public void setClickListener(View.OnClickListener listener) {
        this.clickListener = listener;
    }

    /* loaded from: classes9.dex */
    class mMenuOnclick implements View.OnClickListener {
        int pos;

        mMenuOnclick(int pos, View mView) {
            this.pos = pos;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
        }
    }

    public void setClickMenuListener(View.OnClickListener tmplistener) {
        this.clickMenuListener = tmplistener;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<LexusLsAppSelBean> arrayList = this.mList;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public void notifyData(List<LexusLsAppSelBean> poiItemList) {
        if (poiItemList != null) {
            this.mList.clear();
            this.mList.addAll(poiItemList);
            notifyItemRangeChanged(0, poiItemList.size());
        }
    }

    /* loaded from: classes9.dex */
    public class mBaseViewHolder extends RecyclerView.ViewHolder {
        DraggableLayout draggable_layout;
        ImageView icon;
        RelativeLayout layoutLL;
        TextView tv_title;

        public mBaseViewHolder(View itemView) {
            super(itemView);
            this.layoutLL = (RelativeLayout) itemView.findViewById(C0899R.C0901id.layoutLL);
            this.draggable_layout = (DraggableLayout) itemView.findViewById(C0899R.C0901id.draggable_layout);
            this.icon = (ImageView) itemView.findViewById(C0899R.C0901id.icon);
            this.tv_title = (TextView) itemView.findViewById(C0899R.C0901id.tv_title);
        }
    }

    public static boolean isMenu(LexusLsAppSelBean bean) {
        if (bean.getAppPkg().contains(LexusLsConfig.PKG_DEFINED_MENU_LEXUSLS)) {
            return true;
        }
        return false;
    }

    public static boolean isMenu_V2(LexusLsAppSelBean bean) {
        if (bean.getAppPkg().contains(LexusLsConfig.PKG_DEFINED_MENU_LEXUSLS_V2)) {
            return true;
        }
        return false;
    }
}
