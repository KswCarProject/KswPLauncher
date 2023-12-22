package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.content.ClipData;
import android.content.Intent;
import android.support.p001v4.app.Fragment;
import android.view.DragEvent;
import android.view.View;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;

/* loaded from: classes10.dex */
public class CardOnDragListener implements View.OnDragListener {
    private static final String TAG = "CardOnDragListener";
    private final ID8EditActivity id8EditActivity;
    private final Fragment mFragment;
    private final String mFragmentTag;

    public CardOnDragListener(String fragmentTag, Fragment fragment, ID8EditActivity id8EditActivity) {
        this.id8EditActivity = id8EditActivity;
        this.mFragmentTag = fragmentTag;
        this.mFragment = fragment;
    }

    @Override // android.view.View.OnDragListener
    public boolean onDrag(View v, DragEvent event) {
        float tempX = event.getX();
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int currentFragmentX = location[0];
        switch (event.getAction()) {
            case 2:
                float fingerX = currentFragmentX + tempX;
                this.id8EditActivity.checkAutoScroll(fingerX);
                return true;
            case 3:
                v.getId();
                ClipData data = event.getClipData();
                Intent intent = data.getItemAt(0).getIntent();
                String dragName = intent.getStringExtra("name");
                String releaseName = v.getTag().toString();
                this.id8EditActivity.sort(dragName, releaseName);
                return true;
            default:
                return true;
        }
    }
}
