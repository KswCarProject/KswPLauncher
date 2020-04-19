package com.wits.ksw.launcher.adpater;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BcItemBinding;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.utils.ClientManager;
import java.util.ArrayList;
import java.util.List;

public class BenzNTG6RecyclerViewAdpater extends RecyclerView.Adapter<ViewHolder> {
    private static String[] bcItemArrys;
    private static int[] resId;
    private List<BcItem> bcItemList;
    private BcVieModel viewModel;

    public BenzNTG6RecyclerViewAdpater(BcVieModel viewModel2) {
        this.viewModel = viewModel2;
        if (ClientManager.getInstance().isAls6208Client()) {
            bcItemArrys = KswApplication.appContext.getResources().getStringArray(R.array.bc_home_netg6_als_6208_item_text_array);
            resId = new int[]{R.drawable.bc_ntg6_navi_normal, R.drawable.bc_ntg6_video_normal, R.drawable.bc_ntg6_music_normal, R.drawable.bc_ntg6_bt_normal, R.drawable.bc_ntg6_car_normal, R.drawable.bc_ntg6_dash_board_normal, R.drawable.bc_ntg6_easyconn_normal, R.drawable.bc_ntg6_file_manager_normal, R.drawable.ntg6_dvr_small, R.drawable.bc_ntg6_settings_normal};
            Log.i(KswApplication.TAG, "BenzNTG6RecyclerViewAdpater: resId=" + resId.length + " bcItemArrys=" + bcItemArrys.length);
            Drawable drawable = KswApplication.appContext.getResources().getDrawable(resId[0]);
            StringBuilder sb = new StringBuilder();
            sb.append("BenzNTG6RecyclerViewAdpater: drawable=");
            sb.append(drawable);
            Log.i(KswApplication.TAG, sb.toString());
        } else {
            bcItemArrys = KswApplication.appContext.getResources().getStringArray(R.array.bc_item_text_array);
            resId = new int[]{R.drawable.bc_ntg6_navi_normal, R.drawable.bc_ntg6_music_normal, R.drawable.bc_ntg6_bt_normal, R.drawable.bc_ntg6_car_normal, R.drawable.bc_ntg6_settings_normal, R.drawable.bc_ntg6_video_normal, R.drawable.bc_ntg6_file_manager_normal, R.drawable.bc_ntg6_easyconn_normal, R.drawable.bc_ntg6_dash_board_normal, R.drawable.ntg6_dvr_small};
        }
        this.bcItemList = getBcItemList();
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public List<BcItem> getBcItemList() {
        List<BcItem> bcItems = new ArrayList<>();
        for (int i = 0; i < resId.length; i++) {
            BcItem bcItem = new BcItem();
            bcItem.setId(i);
            bcItem.setAppIcon(KswApplication.appContext.getResources().getDrawable(resId[i]));
            bcItem.setAppLable(bcItemArrys[i]);
            bcItems.add(bcItem);
        }
        return bcItems;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        BcItemBinding bcItemBinding = (BcItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.bc_item, viewGroup, false);
        bcItemBinding.setMBcVieModel(this.viewModel);
        return new ViewHolder(bcItemBinding);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BcItemBinding itemMvvmBinding = viewHolder.getBcItemBinding();
        itemMvvmBinding.setListItem(this.bcItemList.get(position));
        itemMvvmBinding.getRoot().setTag(Integer.valueOf(position));
        itemMvvmBinding.executePendingBindings();
    }

    public int getItemCount() {
        if (this.bcItemList == null || this.bcItemList.isEmpty()) {
            return 0;
        }
        return this.bcItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        BcItemBinding bcItemBinding;

        public ViewHolder(@NonNull BcItemBinding binding) {
            super(binding.getRoot());
            this.bcItemBinding = binding;
        }

        public BcItemBinding getBcItemBinding() {
            return this.bcItemBinding;
        }
    }
}
