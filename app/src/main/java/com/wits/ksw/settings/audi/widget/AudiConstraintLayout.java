package com.wits.ksw.settings.audi.widget;

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

public class AudiConstraintLayout extends ConstraintLayout {
    private static final String TAG = ("KSWLauncher." + AudiConstraintLayout.class.getSimpleName());
    private List<View> childViews;

    public AudiConstraintLayout(Context context) {
        this(context, (AttributeSet) null);
        Log.i(TAG, "AudiConstraintLayout: ");
        init();
    }

    public AudiConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "AudiConstraintLayout: ");
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    private void init() {
        String str = TAG;
        Log.i(str, "init: " + getChildCount());
        this.childViews = getAllChildViews(this);
        for (View mView : this.childViews) {
            if (mView instanceof CompoundButton) {
                mView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        AudiConstraintLayout.this.setSeleted(v);
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
