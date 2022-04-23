package com.wits.ksw.settings.als_id7_ui_set.factory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class AlsID7UiFactoryInput extends FrameLayout implements View.OnClickListener {
    public static final String TAG = "AlsID7UiFactoryInput";
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

    public AlsID7UiFactoryInput(Context context) {
        super(context);
        this.m_con = context;
        this.dialogViews = new DialogViews(this.m_con);
        this.view = LayoutInflater.from(this.m_con).inflate(R.layout.als_id7_ui_factory_input, (ViewGroup) null);
        Log.d(TAG, " view " + this.view);
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
        TextView textView = (TextView) this.view.findViewById(R.id.tv_facVer);
        this.tv_facVer = textView;
        textView.setText(this.factoryVer);
        TextView textView2 = (TextView) this.view.findViewById(R.id.tv_Facinput);
        this.tv_Facinput = textView2;
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlsID7UiFactoryInput.this.dialogViews.fileListView(AlsID7UiFactoryInput.this.m_con.getString(R.string.update_factory_file));
            }
        });
        this.restartView = this.view.findViewById(R.id.tv_restart);
        Log.d(TAG, " restartView " + this.restartView);
        this.restartView.setOnClickListener($$Lambda$AlsID7UiFactoryInput$RD3B31deT64JfE03sMdBD6w9ItY.INSTANCE);
    }

    public void onClick(View view2) {
    }
}
