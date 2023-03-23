package com.wits.ksw.settings.bmw_id8.adapter;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.id7.bean.MapBean;
import java.util.List;

public class BmwId8SettingsNaviAdapter extends BaseQuickAdapter<MapBean, BaseViewHolder> {
    private final String TAG = "BmwId8SettingsNaviAdapter";

    public BmwId8SettingsNaviAdapter(List<MapBean> data) {
        super(R.layout.bmw_id8_settings_navi_item, data);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder helper, final MapBean item) {
        if (item != null) {
            helper.setChecked(R.id.bmw_id8_settings_navi_item_btn, item.isCheck());
            helper.itemView.findViewById(R.id.bmw_id8_settings_navi_item_lay).setSelected(item.isCheck());
            helper.setText((int) R.id.bmw_id8_settings_navi_item_title, (CharSequence) item.getName());
            if (item.isCheck()) {
                helper.itemView.requestFocus();
            }
            helper.itemView.findViewById(R.id.bmw_id8_settings_navi_item_lay).setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    Log.i("BmwId8SettingsNaviAdapter", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
                    if (event.getAction() != 1 || v.isFocused()) {
                        return false;
                    }
                    v.requestFocus();
                    return false;
                }
            });
            helper.itemView.findViewById(R.id.bmw_id8_settings_navi_item_lay).setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    int position = BmwId8SettingsNaviAdapter.this.getData().indexOf(item);
                    Log.i("BmwId8SettingsNaviAdapter", " position " + position + " keyCode " + keyCode + " event.getAction() " + event.getAction());
                    if (keyCode == 19) {
                        if (position == 0) {
                            return true;
                        }
                        return false;
                    } else if (keyCode != 20) {
                        if (keyCode == 21 && event.getAction() == 0) {
                            KswUtils.sendKeyDownUpSync(4);
                        }
                        return false;
                    } else if (position == BmwId8SettingsNaviAdapter.this.getData().size() - 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }
    }
}
