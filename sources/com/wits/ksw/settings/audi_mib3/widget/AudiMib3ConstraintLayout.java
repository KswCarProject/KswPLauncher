package com.wits.ksw.settings.audi_mib3.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AudiMib3ConstraintLayout extends ConstraintLayout {
    private static final String TAG = ("KSWLauncher." + AudiMib3ConstraintLayout.class.getSimpleName());
    private List<View> childViews;

    public AudiMib3ConstraintLayout(Context context) {
        this(context, (AttributeSet) null);
        Log.i(TAG, "AudiConstraintLayout: ");
        init();
    }

    public AudiMib3ConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "AudiConstraintLayout: ");
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    private void init() {
        Log.i(TAG, "init: " + getChildCount());
        List<View> allChildViews = getAllChildViews(this);
        this.childViews = allChildViews;
        for (View mView : allChildViews) {
            if (mView instanceof CompoundButton) {
                mView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        AudiMib3ConstraintLayout.this.setSeleted(v);
                    }
                });
            }
        }
    }

    private List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }

    public void setSeleted(View view) {
        Iterator<View> it = this.childViews.iterator();
        while (it.hasNext()) {
            View mView = it.next();
            mView.setSelected(view == mView);
        }
    }
}
