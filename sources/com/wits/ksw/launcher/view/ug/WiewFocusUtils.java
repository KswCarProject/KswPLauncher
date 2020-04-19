package com.wits.ksw.launcher.view.ug;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class WiewFocusUtils {
    public static List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                allchildren.addAll(getAllChildViews(viewchild));
            }
        } else if (view instanceof ViewGroup) {
            allchildren.add(view);
        }
        return allchildren;
    }

    public static void setViewRequestFocus(View view) {
        if (view != null) {
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setFocusableInTouchMode(false);
        }
    }
}
