package com.wits.ksw.settings.bmw_id8.adapter;

import android.view.KeyEvent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.KeyUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.bmw_id8.bean.BmwId8SettingsMainBean;
import java.util.List;
import skin.support.content.res.SkinCompatResources;

public class BmwId8SettingsMainAdapter extends BaseQuickAdapter<BmwId8SettingsMainBean, BaseViewHolder> {
    private final String TAG = "BmwId8SettingsMainAdapter";
    /* access modifiers changed from: private */
    public ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        void onItemClick(View view, int i, BmwId8SettingsMainBean bmwId8SettingsMainBean);
    }

    public BmwId8SettingsMainAdapter(List<BmwId8SettingsMainBean> data) {
        super(R.layout.bmw_id8_settings_main_item, data);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder helper, final BmwId8SettingsMainBean item) {
        if (item != null) {
            helper.setBackgroundRes(R.id.bmw_id8_settings_main_item_bg, item.getBgId());
            helper.setImageResource(R.id.bmw_id8_settings_main_item_icon, item.getIconId());
            helper.setText((int) R.id.bmw_id8_settings_main_item_title, (CharSequence) item.getTitle());
            helper.setText((int) R.id.bmw_id8_settings_main_item_content, (CharSequence) item.getContent());
            helper.setTextColor(R.id.bmw_id8_settings_main_item_content, SkinCompatResources.getInstance().getColor(R.color.id8_main_style_color));
            helper.itemView.findViewById(R.id.bmw_id8_settings_main_item_bg).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (BmwId8SettingsMainAdapter.this.mItemClickListener != null) {
                        BmwId8SettingsMainAdapter.this.mItemClickListener.onItemClick(v, BmwId8SettingsMainAdapter.this.getData().indexOf(item), item);
                    }
                }
            });
            helper.itemView.findViewById(R.id.bmw_id8_settings_main_item_bg).setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (BmwId8SettingsMainAdapter.this.getData().indexOf(item) == 0 && keyCode == 19) {
                        return true;
                    }
                    if (keyCode == 20 && event.getAction() == 0) {
                        KeyUtils.pressKey(22);
                        return true;
                    } else if (keyCode != 19 || event.getAction() != 0) {
                        return false;
                    } else {
                        KeyUtils.pressKey(21);
                        return true;
                    }
                }
            });
            if (UiThemeUtils.isBMW_ID8_UI(this.mContext)) {
                helper.itemView.findViewById(R.id.bmw_id8_settings_main_item_bg).setNextFocusLeftId(R.id.ll_left_1);
            } else {
                helper.itemView.findViewById(R.id.bmw_id8_settings_main_item_bg).setNextFocusLeftId(R.id.ll_left_4);
            }
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}
