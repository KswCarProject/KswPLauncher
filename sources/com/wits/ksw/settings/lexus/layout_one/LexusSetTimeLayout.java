package com.wits.ksw.settings.lexus.layout_one;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.lexus.interfaces.IUpdateTwoLayout;

/* loaded from: classes10.dex */
public class LexusSetTimeLayout extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private TextView tv_timeSync;
    private TextView tv_timeZhis;
    private IUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public LexusSetTimeLayout(Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.lexus_layout_set_time, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
    }

    private void initView(View view) {
        this.tv_timeSync = (TextView) view.findViewById(C0899R.C0901id.tv_timeSync);
        this.tv_timeZhis = (TextView) view.findViewById(C0899R.C0901id.tv_timeZhis);
        this.tv_timeSync.setOnClickListener(this);
        this.tv_timeZhis.setOnClickListener(this);
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
