package com.wits.ksw.launcher.view.benzntg6fynew.adapter;

import android.text.TextUtils;
import android.util.Log;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux2021new.bean.BenzCardItem;
import com.wits.ksw.launcher.view.benzmbux2021new.bean.BenzCardMenu;
import java.util.List;

/* loaded from: classes5.dex */
public class BenzNtgfyNewAdapter extends BaseMultiItemQuickAdapter<BenzCardMenu, BaseViewHolder> {
    private static final String TAG = "BenzNtgfyNewAdapter";
    private BcVieModel mBcVieModel;
    private List<BenzCardMenu> mBenzCardMenuList;

    public BenzNtgfyNewAdapter(List<BenzCardMenu> data) {
        super(data);
        this.mBenzCardMenuList = data;
        addItemType(1, C0899R.C0902layout.benz_ntg_fy_new_recycle_item);
        addItemType(2, C0899R.C0902layout.benz_ntg_fy_new_recycle_item_third);
    }

    public void setCardList(List<BenzCardMenu> data) {
        this.mBenzCardMenuList = data;
        notifyDataSetChanged();
    }

    public void setmBcVieModel(BcVieModel vieModel) {
        this.mBcVieModel = vieModel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, BenzCardMenu item) {
        int type = helper.getItemViewType();
        BenzCardItem benzCardItem = (BenzCardItem) item.getBenzCardItem();
        Log.i(TAG, "convert: benzCardItem.getName() " + benzCardItem.getName());
        if (benzCardItem.getImgSrc() != null) {
            helper.setImageDrawable(C0899R.C0901id.benz_mbux_2021_ksw_new_item_img, benzCardItem.getImgSrc());
        }
        helper.setImageResource(C0899R.C0901id.benz_mbux_2021_ksw_new_item_bg, benzCardItem.getImgBg());
        helper.setTag(C0899R.C0901id.benz_mbux_2021_ksw_new_item_bg, benzCardItem.getName());
        helper.addOnClickListener(C0899R.C0901id.benz_mbux_2021_ksw_new_item_bg);
        helper.setText(C0899R.C0901id.benz_mbux_2021_ksw_new_item_title, benzCardItem.getTitle());
        helper.setText(C0899R.C0901id.benz_mbux_2021_ksw_new_item_content, benzCardItem.getContent());
        if (type == 1) {
            helper.setImageResource(C0899R.C0901id.benz_mbux_2021_ksw_new_item_left_icon, benzCardItem.getIconLeft());
            helper.addOnClickListener(C0899R.C0901id.benz_mbux_2021_ksw_new_item_left_icon);
            helper.setImageResource(C0899R.C0901id.benz_mbux_2021_ksw_new_item_right_icon, benzCardItem.getIconRight());
            helper.addOnClickListener(C0899R.C0901id.benz_mbux_2021_ksw_new_item_right_icon);
        }
        helper.addOnClickListener(C0899R.C0901id.benz_mbux_edit_ok_btn);
        helper.addOnClickListener(C0899R.C0901id.benz_mbux_edit_left_btn);
        helper.addOnClickListener(C0899R.C0901id.benz_mbux_edit_right_btn);
        if (TextUtils.equals(this.mBcVieModel.selectCardName.get(), benzCardItem.getName())) {
            helper.setAlpha(C0899R.C0901id.benz_mbux_2021_ksw_new_item_bg, 0.5f);
            helper.setAlpha(C0899R.C0901id.benz_mbux_2021_ksw_new_item_img, 0.5f);
            helper.setVisible(C0899R.C0901id.benz_mbux_edit_lay, true);
        } else {
            helper.setAlpha(C0899R.C0901id.benz_mbux_2021_ksw_new_item_bg, 1.0f);
            helper.setAlpha(C0899R.C0901id.benz_mbux_2021_ksw_new_item_img, 1.0f);
            helper.setVisible(C0899R.C0901id.benz_mbux_edit_lay, false);
        }
        if (type == 2) {
            helper.addOnClickListener(C0899R.C0901id.benz_mbux_edit_delete_btn);
        }
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter, android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
