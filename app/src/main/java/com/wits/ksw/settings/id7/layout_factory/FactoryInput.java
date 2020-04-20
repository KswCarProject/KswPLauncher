package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class FactoryInput extends FrameLayout implements View.OnClickListener {
    /* access modifiers changed from: private */
    public DialogViews dialogViews;
    private String factoryVer = "";
    private FrameLayout.LayoutParams layoutParams;
    /* access modifiers changed from: private */
    public Context m_con;
    private View restartView;
    private TextView tv_Facinput;
    private TextView tv_facVer;
    private View view;

    public FactoryInput(@NonNull Context context) {
        super(context);
        this.m_con = context;
        this.dialogViews = new DialogViews(this.m_con);
        this.view = LayoutInflater.from(this.m_con).inflate(R.layout.factory_input, (ViewGroup) null);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initData() {
        try {
            this.factoryVer = PowerManagerApp.getSettingsString("ver");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView() {
        this.dialogViews = new DialogViews(this.m_con);
        this.tv_facVer = (TextView) this.view.findViewById(R.id.tv_facVer);
        this.tv_facVer.setText(this.factoryVer);
        this.tv_Facinput = (TextView) this.view.findViewById(R.id.tv_Facinput);
        this.tv_Facinput.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FactoryInput.this.dialogViews.fileListView(FactoryInput.this.m_con.getString(R.string.update_factory_file));
            }
        });
        this.restartView = this.view.findViewById(R.id.tv_restart);
        this.restartView.setOnClickListener($$Lambda$FactoryInput$HW8JpZHdzbdm4OHzfpZZLWmuThA.INSTANCE);
    }

    public void onClick(View view2) {
    }
}
