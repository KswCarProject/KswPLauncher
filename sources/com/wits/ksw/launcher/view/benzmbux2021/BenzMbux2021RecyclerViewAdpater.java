package com.wits.ksw.launcher.view.benzmbux2021;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BenzMbux2021ItemBinding;
import com.wits.ksw.launcher.model.BcVieModel;
import java.util.ArrayList;
import java.util.List;

public class BenzMbux2021RecyclerViewAdpater extends RecyclerView.Adapter<ViewHolder> {
    static String[] bcItemArrys;
    static String[] bcItemTipArrys;
    static int[] resId = {R.drawable.benz_mbux_2021_home_bt_selector1, R.drawable.benz_mbux_2021_home_navi_selector1, R.drawable.benz_mbux_2021_home_car_selector1, R.drawable.benz_mbux_2021_home_video_selector1, R.drawable.benz_mbux_2021_home_music_selector1, R.drawable.benz_mbux_2021_home_setting_selector1, R.drawable.benz_mbux_2021_home_easy_selector1, R.drawable.benz_mbux_2021_home_app_selector1, R.drawable.benz_mbux_2021_home_dashboard_selector1};
    static int[] subIcon1resId = {R.drawable.benz_mbux_2021_bt1_selector, R.drawable.benz_mbux_2021_navi1_selector, R.drawable.benz_mbux_2021_car1_selector, R.drawable.benz_mbux_2021_video1_selector, R.drawable.benz_mbux_2021_music1_selector, R.drawable.benz_mbux_2021_settings1_selector, R.drawable.benz_mbux_2021_easyconn1_selector, R.drawable.benz_mbux_2021_app1_selector, R.drawable.benz_mbux_2021_dashboard1_selector};
    static int[] subIcon2resId = {R.drawable.benz_mbux_2021_bt2_selector, R.drawable.benz_mbux_2021_navi2_selector, R.drawable.benz_mbux_2021_car2_selector, R.drawable.benz_mbux_2021_video2_selector, R.drawable.benz_mbux_2021_music2_selector, R.drawable.benz_mbux_2021_settings2_selector, R.drawable.benz_mbux_2021_easyconn2_selector, R.drawable.benz_mbux_2021_app2_selector, R.drawable.benz_mbux_2021_dashboard2_selector};
    private List<BenzMbux2021Bean> bcItemList = getBcItemList();
    private BcVieModel viewModel;

    public BenzMbux2021RecyclerViewAdpater(BcVieModel viewModel2) {
        this.viewModel = viewModel2;
        bcItemArrys = KswApplication.appContext.getResources().getStringArray(R.array.benz_mbux_2021_home_item_array);
        bcItemTipArrys = KswApplication.appContext.getResources().getStringArray(R.array.benz_mbux_2021_item_tip_array);
    }

    /* access modifiers changed from: package-private */
    public List<BenzMbux2021Bean> getBcItemList() {
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

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        BenzMbux2021ItemBinding bc2021ItemBinding = (BenzMbux2021ItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.benz_mbux_2021_item, viewGroup, false);
        bc2021ItemBinding.setVieModel(this.viewModel);
        return new ViewHolder(bc2021ItemBinding);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        BenzMbux2021ItemBinding itemMvvmBinding = viewHolder.getBcItemBinding();
        itemMvvmBinding.setListItem(this.bcItemList.get(position));
        itemMvvmBinding.getRoot().setTag(Integer.valueOf(position));
        itemMvvmBinding.executePendingBindings();
    }

    public int getItemCount() {
        List<BenzMbux2021Bean> list = this.bcItemList;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return this.bcItemList.size();
    }

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
