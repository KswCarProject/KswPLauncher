package com.wits.ksw.settings.romeo.layout_one;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.ExceptionPrint;
import com.wits.ksw.settings.romeo.interfaces.IUpdateListBg;
import com.wits.ksw.settings.romeo.interfaces.IUpdateTwoLayout;

public class SetVoiceLayout extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private int daohan = 26;
    private int htongh = 28;
    private int meiti = 26;
    private LinearLayout romeo_voice_ll;
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
    /* access modifiers changed from: private */
    public IUpdateListBg updateListBg;
    private IUpdateTwoLayout updateTwoLayout;
    private int ytongh = 26;

    public SetVoiceLayout(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.romeo_layout_set_voice, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public void registIUpdateListBg(IUpdateListBg updateListBg2) {
        this.updateListBg = updateListBg2;
    }

    private void changeItemBg(final ViewGroup viewGroup, Context context2) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final int finalI = i;
            viewGroup.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int[] locW = new int[2];
                    viewGroup.getChildAt(finalI).getLocationInWindow(locW);
                    if (motionEvent.getAction() == 0) {
                        SetVoiceLayout.this.updateListBg.updateListBg(locW[1] - 78, 2);
                    } else if (motionEvent.getAction() == 1) {
                        SetVoiceLayout.this.updateListBg.updateListBg(locW[1] - 78, 0);
                    } else if (motionEvent.getAction() == 3) {
                        SetVoiceLayout.this.updateListBg.updateListBg(locW[1] - 78, 0);
                    }
                    return false;
                }
            });
            viewGroup.getChildAt(i).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        int[] locW = new int[2];
                        viewGroup.getChildAt(finalI).getLocationInWindow(locW);
                        SetVoiceLayout.this.updateListBg.updateListBg(locW[1] - 78, 1);
                        return;
                    }
                    SetVoiceLayout.this.updateListBg.updateListBg(0, 0);
                }
            });
        }
    }

    private void initView(View view) {
        this.tv_houzvoc = (TextView) view.findViewById(R.id.tv_houzvoc);
        this.tv_yuancvoc = (TextView) view.findViewById(R.id.tv_yuancvoc);
        this.tv_eq = (TextView) view.findViewById(R.id.tv_eq);
        this.romeo_voice_ll = (LinearLayout) view.findViewById(R.id.romeo_voice_ll);
        this.tv_houzvoc.setOnClickListener(this);
        this.tv_yuancvoc.setOnClickListener(this);
        this.tv_eq.setOnClickListener(this);
        changeItemBg(this.romeo_voice_ll, getContext());
    }

    public void resetTextColor() {
        this.tv_houzvoc.setTextColor(Color.argb(255, 172, 216, 255));
        this.tv_yuancvoc.setTextColor(-1);
        this.tv_eq.setTextColor(-1);
        IUpdateTwoLayout iUpdateTwoLayout = this.updateTwoLayout;
        if (iUpdateTwoLayout != null) {
            iUpdateTwoLayout.updateTwoLayout(5, 0);
        }
    }

    public void onClick(View v) {
        if (this.updateTwoLayout == null) {
            ExceptionPrint.print("updateTwoLayout is null");
            return;
        }
        resetTextColor();
        switch (v.getId()) {
            case R.id.tv_eq:
                this.tv_houzvoc.setTextColor(-1);
                this.tv_yuancvoc.setTextColor(-1);
                this.tv_eq.setTextColor(Color.argb(255, 172, 216, 255));
                this.updateTwoLayout.updateTwoLayout(5, 2);
                Intent intent = new Intent();
                intent.setClassName("com.wits.csp.eq", "com.wits.csp.eq.view.MainActivity");
                this.context.startActivity(intent);
                return;
            case R.id.tv_houzvoc:
                this.tv_yuancvoc.setTextColor(-1);
                this.tv_eq.setTextColor(-1);
                this.tv_houzvoc.setTextColor(Color.argb(255, 172, 216, 255));
                this.updateTwoLayout.updateTwoLayout(5, 0);
                return;
            case R.id.tv_yuancvoc:
                this.tv_houzvoc.setTextColor(-1);
                this.tv_eq.setTextColor(-1);
                this.tv_yuancvoc.setTextColor(Color.argb(255, 172, 216, 255));
                this.updateTwoLayout.updateTwoLayout(5, 1);
                return;
            default:
                return;
        }
    }
}
