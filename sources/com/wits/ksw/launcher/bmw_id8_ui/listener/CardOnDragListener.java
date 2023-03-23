package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.View;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;

public class CardOnDragListener implements View.OnDragListener {
    private static final String TAG = "CardOnDragListener";
    private final ID8EditActivity id8EditActivity;
    private final Fragment mFragment;
    private final String mFragmentTag;

    public CardOnDragListener(String fragmentTag, Fragment fragment, ID8EditActivity id8EditActivity2) {
        this.id8EditActivity = id8EditActivity2;
        this.mFragmentTag = fragmentTag;
        this.mFragment = fragment;
    }

    public boolean onDrag(View v, DragEvent event) {
        float tempX = event.getX();
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int currentFragmentX = location[0];
        switch (event.getAction()) {
            case 2:
                this.id8EditActivity.checkAutoScroll(((float) currentFragmentX) + tempX);
                return true;
            case 3:
                int id = v.getId();
                this.id8EditActivity.sort(event.getClipData().getItemAt(0).getIntent().getStringExtra("name"), v.getTag().toString());
                return true;
            default:
                return true;
        }
    }
}
