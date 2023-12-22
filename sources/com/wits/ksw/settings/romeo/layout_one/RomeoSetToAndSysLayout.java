package com.wits.ksw.settings.romeo.layout_one;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.romeo.interfaces.IUpdateListBg;

/* loaded from: classes11.dex */
public class RomeoSetToAndSysLayout extends RelativeLayout {
    private IUpdateListBg updateListBg;

    public RomeoSetToAndSysLayout(final Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.romeo_layout_set_none, (ViewGroup) null);
        TextView tv = (TextView) view.findViewById(C0899R.C0901id.tv_oneDefaul);
        LinearLayout romeo_settosys_ll = (LinearLayout) view.findViewById(C0899R.C0901id.romeo_settosys_ll);
        tv.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.romeo.layout_one.RomeoSetToAndSysLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Intent intent = new Intent("android.settings.SETTINGS");
                context.startActivity(intent);
            }
        });
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        view.setLayoutParams(layoutParams);
        addView(view);
        changeItemBg(romeo_settosys_ll, getContext());
    }

    private void changeItemBg(final ViewGroup viewGroup, Context context) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final int finalI = i;
            viewGroup.getChildAt(i).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.romeo.layout_one.RomeoSetToAndSysLayout.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int[] locW = new int[2];
                    viewGroup.getChildAt(finalI).getLocationInWindow(locW);
                    if (motionEvent.getAction() == 0) {
                        RomeoSetToAndSysLayout.this.updateListBg.updateListBg(locW[1] - 78, 2);
                    } else if (motionEvent.getAction() == 1) {
                        RomeoSetToAndSysLayout.this.updateListBg.updateListBg(locW[1] - 78, 0);
                    } else if (motionEvent.getAction() == 3) {
                        RomeoSetToAndSysLayout.this.updateListBg.updateListBg(locW[1] - 78, 0);
                    }
                    return false;
                }
            });
            viewGroup.getChildAt(i).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.settings.romeo.layout_one.RomeoSetToAndSysLayout.3
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean b) {
                    if (!b) {
                        RomeoSetToAndSysLayout.this.updateListBg.updateListBg(0, 0);
                        return;
                    }
                    int[] locW = new int[2];
                    viewGroup.getChildAt(finalI).getLocationInWindow(locW);
                    RomeoSetToAndSysLayout.this.updateListBg.updateListBg(locW[1] - 78, 1);
                }
            });
        }
    }

    public void registIUpdateListBg(IUpdateListBg updateListBg) {
        this.updateListBg = updateListBg;
    }
}
