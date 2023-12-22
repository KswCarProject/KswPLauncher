package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.content.ClipData;
import android.content.Intent;
import android.support.p001v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import com.wits.ksw.launcher.bmw_id8_ui.ID8PempEditActivity;

/* loaded from: classes10.dex */
public class CardId8PempOnDragListener implements View.OnDragListener {
    private static final String TAG = "CardId8PempOnDragListener";
    private final ID8PempEditActivity id8PempEditActivity;
    private final Fragment mFragment;
    private final String mFragmentTag;

    public CardId8PempOnDragListener(String fragmentTag, Fragment fragment, ID8PempEditActivity id8PempEditActivity) {
        this.id8PempEditActivity = id8PempEditActivity;
        this.mFragmentTag = fragmentTag;
        this.mFragment = fragment;
    }

    @Override // android.view.View.OnDragListener
    public boolean onDrag(View v, DragEvent event) {
        Log.w(TAG, "onDrag:  event getAction : " + event.getAction());
        float tempX = event.getX();
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int currentFragmentX = location[0];
        Log.w(TAG, "Fragment X :" + currentFragmentX);
        switch (event.getAction()) {
            case 2:
                float fingerX = currentFragmentX + tempX;
                Log.w(TAG, "onDrag:  fragmentTag : " + this.mFragmentTag + " , currentScreenX : " + currentFragmentX + ", tempX : " + tempX + ", x :" + fingerX);
                this.id8PempEditActivity.checkAutoScroll(fingerX);
                return true;
            case 3:
                v.getId();
                ClipData data = event.getClipData();
                Intent intent = data.getItemAt(0).getIntent();
                String dragName = intent.getStringExtra("name");
                Log.w(TAG, "drag name : " + dragName);
                String releaseName = v.getTag().toString();
                Log.w(TAG, "release name : " + releaseName);
                this.id8PempEditActivity.sort(dragName, releaseName);
                return true;
            default:
                return true;
        }
    }
}
