package com.wits.ksw.launcher.adpater;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.databinding.BenzMbuxItemBinding;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BenzMbuxRecyclerViewAdpater extends RecyclerView.Adapter<ViewHolder> {
    static String[] bcItemArrys;
    static int[] resId = {C0899R.C0900drawable.benz_mbux_home_navi_selector, C0899R.C0900drawable.benz_mbux_home_music_selector, C0899R.C0900drawable.benz_mbux_home_bt_selector, C0899R.C0900drawable.benz_mbux_home_car_selector, C0899R.C0900drawable.benz_mbux_home_setting_selector, C0899R.C0900drawable.benz_mbux_home_video_selector, C0899R.C0900drawable.benz_mbux_home_app_selector, C0899R.C0900drawable.benz_mbux_home_easy_selector, C0899R.C0900drawable.benz_mbux_home_dashboard_selector, C0899R.C0900drawable.benz_mbux_home_dvr_selector};
    static int[] subIcon1resId = {C0899R.C0900drawable.mbux_navi1_selector, C0899R.C0900drawable.mbux_music1_selector, C0899R.C0900drawable.mbux_bt1_selector, C0899R.C0900drawable.mbux_car1_selector, C0899R.C0900drawable.mbux_settings1_selector, C0899R.C0900drawable.mbux_video1_selector, C0899R.C0900drawable.mbux_app1_selector, C0899R.C0900drawable.mbux_easyconn1_selector, C0899R.C0900drawable.mbux_dashboard1_selector, C0899R.C0900drawable.mbux_dvr1_selector};
    static int[] subIcon2resId = {C0899R.C0900drawable.mbux_navi2_selector, C0899R.C0900drawable.mbux_music2_selector, C0899R.C0900drawable.mbux_bt2_selector, C0899R.C0900drawable.mbux_car2_selector, C0899R.C0900drawable.mbux_settings2_selector, C0899R.C0900drawable.mbux_video2_selector, C0899R.C0900drawable.mbux_app2_selector, C0899R.C0900drawable.mbux_easyconn2_selector, C0899R.C0900drawable.mbux_dashboard2_selector, C0899R.C0900drawable.mbux_dvr2_selector};
    private List<BenzMbuxBean> bcItemList;
    private BcVieModel viewModel;

    public BenzMbuxRecyclerViewAdpater(BcVieModel viewModel) {
        this.viewModel = viewModel;
        bcItemArrys = KswApplication.appContext.getResources().getStringArray(C0899R.array.benz_mbux_home_item_array);
        this.bcItemList = getBcItemList();
    }

    List<BenzMbuxBean> getBcItemList() {
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

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        BenzMbuxItemBinding bcItemBinding = (BenzMbuxItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), C0899R.C0902layout.benz_mbux_item, viewGroup, false);
        bcItemBinding.setVieModel(this.viewModel);
        return new ViewHolder(bcItemBinding);
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        BenzMbuxItemBinding itemMvvmBinding = viewHolder.getBcItemBinding();
        BenzMbuxBean userBean = this.bcItemList.get(position);
        itemMvvmBinding.setListItem(userBean);
        itemMvvmBinding.getRoot().setTag(Integer.valueOf(position));
        itemMvvmBinding.executePendingBindings();
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BenzMbuxBean> list = this.bcItemList;
        if (list != null && !list.isEmpty()) {
            return this.bcItemList.size();
        }
        return 0;
    }

    /* loaded from: classes11.dex */
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
