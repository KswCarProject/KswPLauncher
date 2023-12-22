package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;

/* loaded from: classes17.dex */
public class FactoryInput extends FrameLayout implements View.OnClickListener {
    public static final String TAG = "FactoryInput";
    private DialogViews dialogViews;
    private String factoryVer;
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    private View restartView;
    private TextView tv_Facinput;
    private TextView tv_facVer;
    private View view;

    public FactoryInput(Context context) {
        super(context);
        this.factoryVer = "";
        this.m_con = context;
        this.dialogViews = new DialogViews(this.m_con);
        this.view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.factory_input, (ViewGroup) null);
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
        TextView textView = (TextView) this.view.findViewById(C0899R.C0901id.tv_facVer);
        this.tv_facVer = textView;
        textView.setText(this.factoryVer);
        TextView textView2 = (TextView) this.view.findViewById(C0899R.C0901id.tv_Facinput);
        this.tv_Facinput = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.id7.layout_factory.FactoryInput.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FactoryInput.this.dialogViews.fileListView(FactoryInput.this.m_con.getString(C0899R.string.update_factory_file));
            }
        });
        this.restartView = this.view.findViewById(C0899R.C0901id.tv_restart);
        Log.d(TAG, " restartView " + this.restartView);
        this.restartView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.id7.layout_factory.-$$Lambda$FactoryInput$SeXI647KvV3TtS_IQDs0tp6SDcE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WitsCommand.sendCommand(1, 125, null);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }
}
