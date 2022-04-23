package com.wits.ksw.launcher.adpater;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BenzMbuxItemBinding;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxBean;
import java.util.ArrayList;
import java.util.List;

public class BenzMbuxRecyclerViewAdpater extends RecyclerView.Adapter<ViewHolder> {
    static String[] bcItemArrys;
    static int[] resId = {R.drawable.benz_mbux_home_navi_selector, R.drawable.benz_mbux_home_music_selector, R.drawable.benz_mbux_home_bt_selector, R.drawable.benz_mbux_home_car_selector, R.drawable.benz_mbux_home_setting_selector, R.drawable.benz_mbux_home_video_selector, R.drawable.benz_mbux_home_app_selector, R.drawable.benz_mbux_home_easy_selector, R.drawable.benz_mbux_home_dashboard_selector, R.drawable.benz_mbux_home_dvr_selector};
    static int[] subIcon1resId = {R.drawable.mbux_navi1_selector, R.drawable.mbux_music1_selector, R.drawable.mbux_bt1_selector, R.drawable.mbux_car1_selector, R.drawable.mbux_settings1_selector, R.drawable.mbux_video1_selector, R.drawable.mbux_app1_selector, R.drawable.mbux_easyconn1_selector, R.drawable.mbux_dashboard1_selector, R.drawable.mbux_dvr1_selector};
    static int[] subIcon2resId = {R.drawable.mbux_navi2_selector, R.drawable.mbux_music2_selector, R.drawable.mbux_bt2_selector, R.drawable.mbux_car2_selector, R.drawable.mbux_settings2_selector, R.drawable.mbux_video2_selector, R.drawable.mbux_app2_selector, R.drawable.mbux_easyconn2_selector, R.drawable.mbux_dashboard2_selector, R.drawable.mbux_dvr2_selector};
    private List<BenzMbuxBean> bcItemList = getBcItemList();
    private BcVieModel viewModel;

    public BenzMbuxRecyclerViewAdpater(BcVieModel viewModel2) {
        this.viewModel = viewModel2;
        bcItemArrys = KswApplication.appContext.getResources().getStringArray(R.array.benz_mbux_home_item_array);
    }

    /* access modifiers changed from: package-private */
    public List<BenzMbuxBean> getBcItemList() {
        List<BenzMbuxBean> bcItems = new ArrayList<>();
        for (int i = 0; i < resId.length; i++) {
            BenzMbuxBean bean = new BenzMbuxBean();
            bean.setId(i);
            Drawable drawable = KswApplication.appContext.getResources().getDrawable(resId[i]);
            Drawable subdrawable1 = KswApplication.appContext.getResources().getDrawable(subIcon1resId[i]);
            Drawable subdrawable2 = KswApplication.appContext.getResources().getDrawable(subIcon2resId[i]);
            bean.setAppIcon(drawable);
            bean.setAppLable(bcItemArrys[i]);
            bean.setSubIcon1(subdrawable1);
            bean.setSubIcon2(subdrawable2);
            bcItems.add(bean);
        }
        return bcItems;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        BenzMbuxItemBinding bcItemBinding = (BenzMbuxItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.benz_mbux_item, viewGroup, false);
        bcItemBinding.setVieModel(this.viewModel);
        return new ViewHolder(bcItemBinding);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        BenzMbuxItemBinding itemMvvmBinding = viewHolder.getBcItemBinding();
        itemMvvmBinding.setListItem(this.bcItemList.get(position));
        itemMvvmBinding.getRoot().setTag(Integer.valueOf(position));
        itemMvvmBinding.executePendingBindings();
    }

    public int getItemCount() {
        List<BenzMbuxBean> list = this.bcItemList;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return this.bcItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        BenzMbuxItemBinding bcItemBinding;

        public ViewHolder(BenzMbuxItemBinding binding) {
            super(binding.getRoot());
            this.bcItemBinding = binding;
        }

        public BenzMbuxItemBinding getBcItemBinding() {
            return this.bcItemBinding;
        }
    }
}
