package com.wits.ksw.settings.bmw_id8.adapter;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;

public class BmwId8SettingsLanguageAdapter extends BaseQuickAdapter<FunctionBean, BaseViewHolder> {
    private final String TAG = "BmwId8SettingsLanguageAdapter";

    public BmwId8SettingsLanguageAdapter(List<FunctionBean> data) {
        super(R.layout.bmw_id8_settings_language_item, data);
    }

    /* access modifiers changed from: protected */
    public void convert(BaseViewHolder helper, final FunctionBean item) {
        if (item != null) {
            helper.setChecked(R.id.bmw_id8_settings_language_item_btn, item.isIscheck());
            helper.itemView.findViewById(R.id.bmw_id8_settings_language_item_lay).setSelected(item.isIscheck());
            helper.setText((int) R.id.bmw_id8_settings_language_item_btn, (CharSequence) item.getTitle());
            helper.itemView.findViewById(R.id.bmw_id8_settings_language_item_lay).setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() != 1 || v.isFocused()) {
                        return false;
                    }
                    v.requestFocus();
                    return false;
                }
            });
            helper.itemView.findViewById(R.id.bmw_id8_settings_language_item_lay).setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    int position = BmwId8SettingsLanguageAdapter.this.getData().indexOf(item);
                    Log.i("BmwId8SettingsLanguageAdapter", " position " + position + " keyCode " + keyCode + " event.getAction() " + event.getAction());
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
                    } else if (position == BmwId8SettingsLanguageAdapter.this.getData().size() - 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }
    }
}
