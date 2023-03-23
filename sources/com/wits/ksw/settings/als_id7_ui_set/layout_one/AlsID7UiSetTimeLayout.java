package com.wits.ksw.settings.als_id7_ui_set.layout_one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.als_id7_ui_set.interfaces.AlsID7UiIUpdateTwoLayout;
import skin.support.content.res.SkinCompatResources;

public class AlsID7UiSetTimeLayout extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private TextView tv_timeSync;
    private TextView tv_timeZhis;
    private AlsID7UiIUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(AlsID7UiIUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public AlsID7UiSetTimeLayout(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.als_id7_ui_layout_set_time, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
    }

    private void initView(View view) {
        this.tv_timeSync = (TextView) view.findViewById(R.id.tv_timeSync);
        this.tv_timeZhis = (TextView) view.findViewById(R.id.tv_timeZhis);
        this.tv_timeSync.setOnClickListener(this);
        this.tv_timeZhis.setOnClickListener(this);
    }

    public void resetTextColor() {
        this.tv_timeSync.setTextColor(-1);
        this.tv_timeZhis.setTextColor(-1);
        AlsID7UiIUpdateTwoLayout alsID7UiIUpdateTwoLayout = this.updateTwoLayout;
        if (alsID7UiIUpdateTwoLayout != null) {
            alsID7UiIUpdateTwoLayout.updateTwoLayout(4, 0);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_timeSync /*2131297941*/:
                this.tv_timeSync.setTextColor(SkinCompatResources.getColor(this.context, R.color.als_id7_ui_text_color));
                this.tv_timeZhis.setTextColor(-1);
                AlsID7UiIUpdateTwoLayout alsID7UiIUpdateTwoLayout = this.updateTwoLayout;
                if (alsID7UiIUpdateTwoLayout != null) {
                    alsID7UiIUpdateTwoLayout.updateTwoLayout(4, 0);
                    return;
                }
                return;
            case R.id.tv_timeZhis /*2131297942*/:
                this.tv_timeZhis.setTextColor(SkinCompatResources.getColor(this.context, R.color.als_id7_ui_text_color));
                this.tv_timeSync.setTextColor(-1);
                AlsID7UiIUpdateTwoLayout alsID7UiIUpdateTwoLayout2 = this.updateTwoLayout;
                if (alsID7UiIUpdateTwoLayout2 != null) {
                    alsID7UiIUpdateTwoLayout2.updateTwoLayout(4, 1);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
