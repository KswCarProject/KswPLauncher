package com.wits.ksw.settings.lexus.layout_one;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.ExceptionPrint;
import com.wits.ksw.launcher.view.benzmbux2021new.util.BenzUtils;
import com.wits.ksw.settings.lexus.interfaces.IUpdateTwoLayout;

/* loaded from: classes10.dex */
public class LexusSetVoiceLayout extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private int daohan;
    private int htongh;
    private int meiti;
    private SeekBar seek_daohvoicb;
    private SeekBar seek_mtb;
    private SeekBar seek_tonghb;
    private SeekBar seek_yuancthb;
    private TextView tv_daohvoicsize;
    private TextView tv_eq;
    private TextView tv_houzvoc;
    private TextView tv_mtsize;
    private TextView tv_tonghsize;
    private TextView tv_yuancthsize;
    private TextView tv_yuancvoc;
    private IUpdateTwoLayout updateTwoLayout;
    private int ytongh;

    public LexusSetVoiceLayout(Context context) {
        super(context);
        this.meiti = 26;
        this.htongh = 28;
        this.ytongh = 26;
        this.daohan = 26;
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.lexus_layout_set_voice, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    private void initView(View view) {
        this.tv_houzvoc = (TextView) view.findViewById(C0899R.C0901id.tv_houzvoc);
        this.tv_yuancvoc = (TextView) view.findViewById(C0899R.C0901id.tv_yuancvoc);
        this.tv_eq = (TextView) view.findViewById(C0899R.C0901id.tv_eq);
        this.tv_houzvoc.setOnClickListener(this);
        this.tv_yuancvoc.setOnClickListener(this);
        this.tv_eq.setOnClickListener(this);
    }

    public void resetTextColor() {
        this.tv_houzvoc.setTextColor(-1);
        this.tv_yuancvoc.setTextColor(-1);
        this.tv_eq.setTextColor(-1);
        IUpdateTwoLayout iUpdateTwoLayout = this.updateTwoLayout;
        if (iUpdateTwoLayout != null) {
            iUpdateTwoLayout.updateTwoLayout(5, 0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (this.updateTwoLayout == null) {
            ExceptionPrint.print("updateTwoLayout is null");
            return;
        }
        resetTextColor();
        switch (v.getId()) {
            case C0899R.C0901id.tv_eq /* 2131297900 */:
                this.tv_eq.setTextColor(Color.argb(255, 172, 216, 255));
                this.updateTwoLayout.updateTwoLayout(5, 2);
                Intent intent = new Intent();
                intent.setClassName(BenzUtils.EQ_PKG, "com.wits.csp.eq.lexus.activity.LexusActivity");
                this.context.startActivity(intent);
                return;
            case C0899R.C0901id.tv_houzvoc /* 2131297916 */:
                this.tv_houzvoc.setTextColor(Color.argb(255, 172, 216, 255));
                this.updateTwoLayout.updateTwoLayout(5, 0);
                return;
            case C0899R.C0901id.tv_yuancvoc /* 2131298008 */:
                this.tv_yuancvoc.setTextColor(Color.argb(255, 172, 216, 255));
                this.updateTwoLayout.updateTwoLayout(5, 1);
                return;
            default:
                return;
        }
    }
}
