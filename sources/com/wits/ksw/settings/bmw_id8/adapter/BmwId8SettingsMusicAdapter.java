package com.wits.ksw.settings.bmw_id8.adapter;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import java.util.List;

/* loaded from: classes15.dex */
public class BmwId8SettingsMusicAdapter extends BaseQuickAdapter<LexusLsAppSelBean, BaseViewHolder> {
    private final String TAG;

    public BmwId8SettingsMusicAdapter(List<LexusLsAppSelBean> data) {
        super(C0899R.C0902layout.bmw_id8_settings_system_music_item, data);
        this.TAG = "BmwId8SettingsMusicAdapter";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, final LexusLsAppSelBean item) {
        if (item != null) {
            helper.setChecked(C0899R.C0901id.bmw_id8_settings_system_music_item_btn, item.isChecked());
            helper.setText(C0899R.C0901id.bmw_id8_settings_system_music_item_title, item.getAppName());
            helper.itemView.setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsMusicAdapter.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View v, MotionEvent event) {
                    Log.i("BmwId8SettingsMusicAdapter", " onTouch v " + v.toString() + " Action " + event.getAction() + " v.isFocused() " + v.isFocused());
                    if (event.getAction() == 1 && !v.isFocused()) {
                        v.requestFocus();
                        return false;
                    }
                    return false;
                }
            });
            helper.itemView.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsMusicAdapter.2
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    int position = BmwId8SettingsMusicAdapter.this.getData().indexOf(item);
                    Log.i("BmwId8SettingsMusicAdapter", " position " + position + " keyCode " + keyCode + " event.getAction() " + event.getAction());
                    return keyCode == 19 ? position == 0 : keyCode == 20 && position == BmwId8SettingsMusicAdapter.this.getData().size() - 1;
                }
            });
        }
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter, android.support.p004v7.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return position;
    }
}
