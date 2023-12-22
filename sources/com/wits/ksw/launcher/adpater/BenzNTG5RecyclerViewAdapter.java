package com.wits.ksw.launcher.adpater;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.databinding.BcNtg5ItemBinding;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.model.BcNTG5ViewModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BenzNTG5RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static String[] bcItemArrys;
    private static int[] resId;
    private List<BcItem> bcItemList;
    private View focusPosition;
    private boolean init = true;
    private BcNTG5ViewModel viewModel;

    public View getFocusPosition() {
        return this.focusPosition;
    }

    public void setFocusPosition(View focusPosition) {
        this.focusPosition = focusPosition;
    }

    public BenzNTG5RecyclerViewAdapter(BcNTG5ViewModel viewModel) {
        this.viewModel = viewModel;
        bcItemArrys = KswApplication.appContext.getResources().getStringArray(C0899R.array.bc_ntg5_item_text_array);
        resId = new int[]{C0899R.C0900drawable.bc_ntg5_navi_selector, C0899R.C0900drawable.bc_ntg5_music_selector, C0899R.C0900drawable.bc_ntg5_phone_selector, C0899R.C0900drawable.bc_ntg5_car_selector, C0899R.C0900drawable.bc_ntg5_settings_selector, C0899R.C0900drawable.bc_ntg5_video_selector, C0899R.C0900drawable.bc_ntg5_app_selector, C0899R.C0900drawable.bc_ntg5_link_selector, C0899R.C0900drawable.bc_ntg5_ldash_selector, C0899R.C0900drawable.bc_ntg5_dvr_selector};
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
        BcNtg5ItemBinding bcItemBinding = (BcNtg5ItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), C0899R.C0902layout.bc_ntg5_item, viewGroup, false);
        bcItemBinding.setMBcVieModel(this.viewModel);
        return new ViewHolder(bcItemBinding);
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        BcNtg5ItemBinding itemMvvmBinding = viewHolder.getBcItemBinding();
        Log.d("BenzNTG5RecyclerViewAdapter ", "focusPosition: " + this.focusPosition);
        BcItem userBean = this.bcItemList.get(position);
        itemMvvmBinding.setListItem(userBean);
        itemMvvmBinding.getRoot().setTag(Integer.valueOf(position));
        itemMvvmBinding.executePendingBindings();
        if (itemMvvmBinding.appIcon.getTextView() == null) {
            itemMvvmBinding.appIcon.bindTextView(itemMvvmBinding.appName);
        }
        if (itemMvvmBinding.appIcon.getAdapter() == null) {
            itemMvvmBinding.appIcon.setAdapter(this);
        }
        if (position == 0 && this.init) {
            this.focusPosition = itemMvvmBinding.appIcon;
            this.init = false;
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BcItem> list = this.bcItemList;
        if (list != null && !list.isEmpty()) {
            return this.bcItemList.size();
        }
        return 0;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow((BenzNTG5RecyclerViewAdapter) holder);
        Log.d("sss", "onViewAttachedToWindow");
        View view = this.focusPosition;
        if (view != null) {
            this.viewModel.refreshViewFocused(view);
        }
    }

    /* loaded from: classes11.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        BcNtg5ItemBinding bcItemBinding;

        public ViewHolder(BcNtg5ItemBinding binding) {
            super(binding.getRoot());
            this.bcItemBinding = binding;
        }

        public BcNtg5ItemBinding getBcItemBinding() {
            return this.bcItemBinding;
        }
    }
}
