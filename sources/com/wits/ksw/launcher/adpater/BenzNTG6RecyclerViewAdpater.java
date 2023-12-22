package com.wits.ksw.launcher.adpater;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.databinding.BcItemBinding;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.utils.ClientManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BenzNTG6RecyclerViewAdpater extends RecyclerView.Adapter<ViewHolder> {
    private static String[] bcItemArrys;
    private static int[] resId;
    private List<BcItem> bcItemList;
    private BcVieModel viewModel;

    public BenzNTG6RecyclerViewAdpater(BcVieModel viewModel) {
        this.viewModel = viewModel;
        if (ClientManager.getInstance().isAls6208Client() || ClientManager.getInstance().isYC2306Client()) {
            bcItemArrys = KswApplication.appContext.getResources().getStringArray(C0899R.array.bc_home_netg6_als_6208_item_text_array);
            resId = new int[]{C0899R.C0900drawable.bc_ntg6_navi_normal, C0899R.C0900drawable.bc_ntg6_video_normal, C0899R.C0900drawable.bc_ntg6_music_normal, C0899R.C0900drawable.bc_ntg6_bt_normal, C0899R.C0900drawable.bc_ntg6_car_normal, C0899R.C0900drawable.bc_ntg6_dash_board_normal, C0899R.C0900drawable.bc_ntg6_easyconn_normal, C0899R.C0900drawable.bc_ntg6_file_manager_normal, C0899R.C0900drawable.ntg6_dvr_small, C0899R.C0900drawable.bc_ntg6_settings_normal};
            Log.i(KswApplication.TAG, "BenzNTG6RecyclerViewAdpater: resId=" + resId.length + " bcItemArrys=" + bcItemArrys.length);
            Drawable drawable = KswApplication.appContext.getResources().getDrawable(resId[0]);
            Log.i(KswApplication.TAG, "BenzNTG6RecyclerViewAdpater: drawable=" + drawable);
        } else {
            bcItemArrys = KswApplication.appContext.getResources().getStringArray(C0899R.array.bc_item_text_array);
            resId = new int[]{C0899R.C0900drawable.bc_ntg6_navi_normal, C0899R.C0900drawable.bc_ntg6_music_normal, C0899R.C0900drawable.bc_ntg6_bt_normal, C0899R.C0900drawable.bc_ntg6_car_normal, C0899R.C0900drawable.bc_ntg6_settings_normal, C0899R.C0900drawable.bc_ntg6_video_normal, C0899R.C0900drawable.bc_ntg6_file_manager_normal, C0899R.C0900drawable.bc_ntg6_easyconn_normal, C0899R.C0900drawable.bc_ntg6_dash_board_normal, C0899R.C0900drawable.ntg6_dvr_small};
        }
        this.bcItemList = getBcItemList();
    }

    List<BcItem> getBcItemList() {
        List<BcItem> bcItems = new ArrayList<>();
        for (int i = 0; i < resId.length; i++) {
            BcItem bcItem = new BcItem();
            bcItem.setId(i);
            Drawable drawable = KswApplication.appContext.getResources().getDrawable(resId[i]);
            bcItem.setAppIcon(drawable);
            bcItem.setAppLable(bcItemArrys[i]);
            bcItems.add(bcItem);
        }
        return bcItems;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        BcItemBinding bcItemBinding = (BcItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), C0899R.C0902layout.bc_item, viewGroup, false);
        bcItemBinding.setMBcVieModel(this.viewModel);
        return new ViewHolder(bcItemBinding);
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        BcItemBinding itemMvvmBinding = viewHolder.getBcItemBinding();
        BcItem userBean = this.bcItemList.get(position);
        itemMvvmBinding.setListItem(userBean);
        itemMvvmBinding.getRoot().setTag(Integer.valueOf(position));
        itemMvvmBinding.executePendingBindings();
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BcItem> list = this.bcItemList;
        if (list != null && !list.isEmpty()) {
            return this.bcItemList.size();
        }
        return 0;
    }

    /* loaded from: classes11.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        BcItemBinding bcItemBinding;

        public ViewHolder(BcItemBinding binding) {
            super(binding.getRoot());
            this.bcItemBinding = binding;
        }

        public BcItemBinding getBcItemBinding() {
            return this.bcItemBinding;
        }
    }
}
