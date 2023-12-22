package com.wits.ksw.settings.bmw_id8.adapter;

import android.view.KeyEvent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.KeyUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.bmw_id8.bean.BmwId8SettingsMainBean;
import java.util.List;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes15.dex */
public class BmwId8SettingsMainAdapter extends BaseQuickAdapter<BmwId8SettingsMainBean, BaseViewHolder> {
    private final String TAG;
    private ItemClickListener mItemClickListener;

    /* loaded from: classes15.dex */
    public interface ItemClickListener {
        void onItemClick(View v, int position, BmwId8SettingsMainBean item);
    }

    public BmwId8SettingsMainAdapter(List<BmwId8SettingsMainBean> data) {
        super(C0899R.C0902layout.bmw_id8_settings_main_item, data);
        this.TAG = "BmwId8SettingsMainAdapter";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, final BmwId8SettingsMainBean item) {
        if (item != null) {
            helper.setBackgroundRes(C0899R.C0901id.bmw_id8_settings_main_item_bg, item.getBgId());
            helper.setImageResource(C0899R.C0901id.bmw_id8_settings_main_item_icon, item.getIconId());
            helper.setText(C0899R.C0901id.bmw_id8_settings_main_item_title, item.getTitle());
            helper.setText(C0899R.C0901id.bmw_id8_settings_main_item_content, item.getContent());
            helper.setTextColor(C0899R.C0901id.bmw_id8_settings_main_item_content, SkinCompatResources.getInstance().getColor(C0899R.color.id8_main_style_color));
            helper.itemView.findViewById(C0899R.C0901id.bmw_id8_settings_main_item_bg).setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsMainAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (BmwId8SettingsMainAdapter.this.mItemClickListener != null) {
                        BmwId8SettingsMainAdapter.this.mItemClickListener.onItemClick(v, BmwId8SettingsMainAdapter.this.getData().indexOf(item), item);
                    }
                }
            });
            helper.itemView.findViewById(C0899R.C0901id.bmw_id8_settings_main_item_bg).setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsMainAdapter.2
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    int position = BmwId8SettingsMainAdapter.this.getData().indexOf(item);
                    if (position == 0 && keyCode == 19) {
                        return true;
                    }
                    if (keyCode == 20 && event.getAction() == 0) {
                        KeyUtils.pressKey(22);
                        return true;
                    } else if (keyCode == 19 && event.getAction() == 0) {
                        KeyUtils.pressKey(21);
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            if (UiThemeUtils.isBMW_ID8_UI(this.mContext) || UiThemeUtils.isUI_GS_ID8(this.mContext) || UiThemeUtils.isUI_PEMP_ID8(this.mContext)) {
                helper.itemView.findViewById(C0899R.C0901id.bmw_id8_settings_main_item_bg).setNextFocusLeftId(C0899R.C0901id.ll_left_1);
            } else {
                helper.itemView.findViewById(C0899R.C0901id.bmw_id8_settings_main_item_bg).setNextFocusLeftId(C0899R.C0901id.ll_left_4);
            }
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}
