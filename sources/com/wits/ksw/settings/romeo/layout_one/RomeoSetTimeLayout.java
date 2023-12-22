package com.wits.ksw.settings.romeo.layout_one;

import android.content.Context;
import android.graphics.Color;
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
import com.wits.ksw.settings.romeo.interfaces.IUpdateTwoLayout;

/* loaded from: classes11.dex */
public class RomeoSetTimeLayout extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private LinearLayout romeo_time_ll;
    private TextView tv_timeSync;
    private TextView tv_timeZhis;
    private IUpdateListBg updateListBg;
    private IUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public RomeoSetTimeLayout(Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.romeo_layout_set_time, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    public void registIUpdateListBg(IUpdateListBg updateListBg) {
        this.updateListBg = updateListBg;
    }

    private void changeItemBg(final ViewGroup viewGroup, Context context) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final int finalI = i;
            viewGroup.getChildAt(i).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.romeo.layout_one.RomeoSetTimeLayout.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int[] locW = new int[2];
                    viewGroup.getChildAt(finalI).getLocationInWindow(locW);
                    if (motionEvent.getAction() == 0) {
                        RomeoSetTimeLayout.this.updateListBg.updateListBg(locW[1] - 78, 2);
                    } else if (motionEvent.getAction() == 1) {
                        RomeoSetTimeLayout.this.updateListBg.updateListBg(locW[1] - 78, 0);
                    } else if (motionEvent.getAction() == 3) {
                        RomeoSetTimeLayout.this.updateListBg.updateListBg(locW[1] - 78, 0);
                    }
                    return false;
                }
            });
            viewGroup.getChildAt(i).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.settings.romeo.layout_one.RomeoSetTimeLayout.2
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean b) {
                    if (!b) {
                        RomeoSetTimeLayout.this.updateListBg.updateListBg(0, 0);
                        return;
                    }
                    int[] locW = new int[2];
                    viewGroup.getChildAt(finalI).getLocationInWindow(locW);
                    RomeoSetTimeLayout.this.updateListBg.updateListBg(locW[1] - 78, 1);
                }
            });
        }
    }

    private void initData() {
    }

    private void initView(View view) {
        this.tv_timeSync = (TextView) view.findViewById(C0899R.C0901id.tv_timeSync);
        this.tv_timeZhis = (TextView) view.findViewById(C0899R.C0901id.tv_timeZhis);
        this.romeo_time_ll = (LinearLayout) view.findViewById(C0899R.C0901id.romeo_time_ll);
        this.tv_timeSync.setOnClickListener(this);
        this.tv_timeZhis.setOnClickListener(this);
        changeItemBg(this.romeo_time_ll, getContext());
    }

    public void resetTextColor() {
        this.tv_timeSync.setTextColor(Color.argb(255, 172, 216, 255));
        this.tv_timeZhis.setTextColor(-1);
        IUpdateTwoLayout iUpdateTwoLayout = this.updateTwoLayout;
        if (iUpdateTwoLayout != null) {
            iUpdateTwoLayout.updateTwoLayout(4, 0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.tv_timeSync /* 2131297993 */:
                this.tv_timeSync.setTextColor(Color.argb(255, 172, 216, 255));
                this.tv_timeZhis.setTextColor(-1);
                IUpdateTwoLayout iUpdateTwoLayout = this.updateTwoLayout;
                if (iUpdateTwoLayout != null) {
                    iUpdateTwoLayout.updateTwoLayout(4, 0);
                    return;
                }
                return;
            case C0899R.C0901id.tv_timeZhis /* 2131297994 */:
                this.tv_timeZhis.setTextColor(Color.argb(255, 172, 216, 255));
                this.tv_timeSync.setTextColor(-1);
                IUpdateTwoLayout iUpdateTwoLayout2 = this.updateTwoLayout;
                if (iUpdateTwoLayout2 != null) {
                    iUpdateTwoLayout2.updateTwoLayout(4, 1);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
