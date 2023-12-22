package com.wits.ksw.launcher.view.benzmbux2021;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.databinding.BenzMbux2021ItemBinding;
import com.wits.ksw.launcher.model.BcVieModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class BenzMbux2021RecyclerViewAdpater extends RecyclerView.Adapter<ViewHolder> {
    static String[] bcItemArrys;
    static String[] bcItemTipArrys;
    static int[] resId = {C0899R.C0900drawable.benz_mbux_2021_home_bt_selector1, C0899R.C0900drawable.benz_mbux_2021_home_navi_selector1, C0899R.C0900drawable.benz_mbux_2021_home_car_selector1, C0899R.C0900drawable.benz_mbux_2021_home_video_selector1, C0899R.C0900drawable.benz_mbux_2021_home_music_selector1, C0899R.C0900drawable.benz_mbux_2021_home_setting_selector1, C0899R.C0900drawable.benz_mbux_2021_home_easy_selector1, C0899R.C0900drawable.benz_mbux_2021_home_app_selector1, C0899R.C0900drawable.benz_mbux_2021_home_dashboard_selector1};
    static int[] subIcon1resId = {C0899R.C0900drawable.benz_mbux_2021_bt1_selector, C0899R.C0900drawable.benz_mbux_2021_navi1_selector, C0899R.C0900drawable.benz_mbux_2021_car1_selector, C0899R.C0900drawable.benz_mbux_2021_video1_selector, C0899R.C0900drawable.benz_mbux_2021_music1_selector, C0899R.C0900drawable.benz_mbux_2021_settings1_selector, C0899R.C0900drawable.benz_mbux_2021_easyconn1_selector, C0899R.C0900drawable.benz_mbux_2021_app1_selector, C0899R.C0900drawable.benz_mbux_2021_dashboard1_selector};
    static int[] subIcon2resId = {C0899R.C0900drawable.benz_mbux_2021_bt2_selector, C0899R.C0900drawable.benz_mbux_2021_navi2_selector, C0899R.C0900drawable.benz_mbux_2021_car2_selector, C0899R.C0900drawable.benz_mbux_2021_video2_selector, C0899R.C0900drawable.benz_mbux_2021_music2_selector, C0899R.C0900drawable.benz_mbux_2021_settings2_selector, C0899R.C0900drawable.benz_mbux_2021_easyconn2_selector, C0899R.C0900drawable.benz_mbux_2021_app2_selector, C0899R.C0900drawable.benz_mbux_2021_dashboard2_selector};
    private List<BenzMbux2021Bean> bcItemList;
    private BcVieModel viewModel;

    public BenzMbux2021RecyclerViewAdpater(BcVieModel viewModel) {
        this.viewModel = viewModel;
        bcItemArrys = KswApplication.appContext.getResources().getStringArray(C0899R.array.benz_mbux_2021_home_item_array);
        bcItemTipArrys = KswApplication.appContext.getResources().getStringArray(C0899R.array.benz_mbux_2021_item_tip_array);
        this.bcItemList = getBcItemList();
    }

    List<BenzMbux2021Bean> getBcItemList() {
        List<BenzMbux2021Bean> bcItems = new ArrayList<>();
        for (int i = 0; i < resId.length; i++) {
            BenzMbux2021Bean bean = new BenzMbux2021Bean();
            bean.setId(i);
            Drawable drawable = KswApplication.appContext.getResources().getDrawable(resId[i]);
            Drawable subdrawable1 = KswApplication.appContext.getResources().getDrawable(subIcon1resId[i]);
            Drawable subdrawable2 = KswApplication.appContext.getResources().getDrawable(subIcon2resId[i]);
            bean.setAppIcon(drawable);
            bean.setAppLable(bcItemArrys[i]);
            bean.setAppTip(bcItemTipArrys[i]);
            bean.setSubIcon1(subdrawable1);
            bean.setSubIcon2(subdrawable2);
            bcItems.add(bean);
        }
        return bcItems;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        BenzMbux2021ItemBinding bc2021ItemBinding = (BenzMbux2021ItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), C0899R.C0902layout.benz_mbux_2021_item, viewGroup, false);
        bc2021ItemBinding.setVieModel(this.viewModel);
        return new ViewHolder(bc2021ItemBinding);
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        BenzMbux2021ItemBinding itemMvvmBinding = viewHolder.getBcItemBinding();
        BenzMbux2021Bean userBean = this.bcItemList.get(position);
        itemMvvmBinding.setListItem(userBean);
        itemMvvmBinding.getRoot().setTag(Integer.valueOf(position));
        itemMvvmBinding.executePendingBindings();
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BenzMbux2021Bean> list = this.bcItemList;
        if (list != null && !list.isEmpty()) {
            return this.bcItemList.size();
        }
        return 0;
    }

    /* loaded from: classes4.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        BenzMbux2021ItemBinding bcItemBinding;

        public ViewHolder(BenzMbux2021ItemBinding binding) {
            super(binding.getRoot());
            this.bcItemBinding = binding;
        }

        public BenzMbux2021ItemBinding getBcItemBinding() {
            return this.bcItemBinding;
        }
    }
}
