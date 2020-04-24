package com.wits.ksw.launcher.adpater;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BcNtg5ItemBinding;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcNTG5ViewModel;
import java.util.ArrayList;
import java.util.List;

public class BenzNTG5RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static String[] bcItemArrys;
    private static int[] resId;
    private List<BcItem> bcItemList = getBcItemList();
    private BcNTG5ViewModel viewModel;

    public BenzNTG5RecyclerViewAdapter(BcNTG5ViewModel viewModel2) {
        this.viewModel = viewModel2;
        bcItemArrys = KswApplication.appContext.getResources().getStringArray(R.array.bc_ntg5_item_text_array);
        resId = new int[]{R.drawable.bc_ntg5_navi_selector, R.drawable.bc_ntg5_music_selector, R.drawable.bc_ntg5_phone_selector, R.drawable.bc_ntg5_car_selector, R.drawable.bc_ntg5_settings_selector, R.drawable.bc_ntg5_video_selector, R.drawable.bc_ntg5_app_selector, R.drawable.bc_ntg5_link_selector, R.drawable.bc_ntg5_ldash_selector, R.drawable.bc_ntg5_dvr_selector};
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
        BcNtg5ItemBinding bcItemBinding = (BcNtg5ItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.bc_ntg5_item, viewGroup, false);
        bcItemBinding.setMBcVieModel(this.viewModel);
        return new ViewHolder(bcItemBinding);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BcNtg5ItemBinding itemMvvmBinding = viewHolder.getBcItemBinding();
        itemMvvmBinding.setListItem(this.bcItemList.get(position));
        itemMvvmBinding.getRoot().setTag(Integer.valueOf(position));
        itemMvvmBinding.executePendingBindings();
        if (itemMvvmBinding.appIcon.getTextView() == null) {
            itemMvvmBinding.appIcon.bindTextView(itemMvvmBinding.appName);
        }
    }

    public int getItemCount() {
        if (this.bcItemList == null || this.bcItemList.isEmpty()) {
            return 0;
        }
        return this.bcItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        BcNtg5ItemBinding bcItemBinding;

        public ViewHolder(@NonNull BcNtg5ItemBinding binding) {
            super(binding.getRoot());
            this.bcItemBinding = binding;
        }

        public BcNtg5ItemBinding getBcItemBinding() {
            return this.bcItemBinding;
        }
    }
}
