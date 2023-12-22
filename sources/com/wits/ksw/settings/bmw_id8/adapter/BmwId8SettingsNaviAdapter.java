package com.wits.ksw.settings.bmw_id8.adapter;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.id7.bean.MapBean;
import java.util.List;

/* loaded from: classes15.dex */
public class BmwId8SettingsNaviAdapter extends BaseQuickAdapter<MapBean, BaseViewHolder> {
    private final String TAG;

    public BmwId8SettingsNaviAdapter(List<MapBean> data) {
        super(C0899R.C0902layout.bmw_id8_settings_navi_item, data);
        this.TAG = "BmwId8SettingsNaviAdapter";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, final MapBean item) {
        if (item != null) {
            helper.setChecked(C0899R.C0901id.bmw_id8_settings_navi_item_btn, item.isCheck());
            helper.itemView.findViewById(C0899R.C0901id.bmw_id8_settings_navi_item_lay).setSelected(item.isCheck());
            helper.setText(C0899R.C0901id.bmw_id8_settings_navi_item_title, item.getName());
            if (item.isCheck()) {
                helper.itemView.requestFocus();
            }
            helper.itemView.findViewById(C0899R.C0901id.bmw_id8_settings_navi_item_lay).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsNaviAdapter.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View v, MotionEvent event) {
                    Log.i("BmwId8SettingsNaviAdapter", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
                    if (event.getAction() == 1 && !v.isFocused()) {
                        v.requestFocus();
                        return false;
                    }
                    return false;
                }
            });
            helper.itemView.findViewById(C0899R.C0901id.bmw_id8_settings_navi_item_lay).setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsNaviAdapter.2
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    int position = BmwId8SettingsNaviAdapter.this.getData().indexOf(item);
                    Log.i("BmwId8SettingsNaviAdapter", " position " + position + " keyCode " + keyCode + " event.getAction() " + event.getAction());
                    if (keyCode == 19) {
                        return position == 0;
                    } else if (keyCode == 20) {
                        return position == BmwId8SettingsNaviAdapter.this.getData().size() - 1;
                    } else {
                        if (keyCode == 21 && event.getAction() == 0) {
                            KswUtils.sendKeyDownUpSync(4);
                        }
                        return false;
                    }
                }
            });
        }
    }
}
