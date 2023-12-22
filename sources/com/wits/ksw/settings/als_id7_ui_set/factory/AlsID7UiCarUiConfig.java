package com.wits.ksw.settings.als_id7_ui_set.factory;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiUiConfigAdapter;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class AlsID7UiCarUiConfig extends FrameLayout {
    private List<FunctionBean> data;
    private DialogViews dialogViews;
    Handler handler;
    private LinearLayoutManager layoutManager;
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    private int possint;
    private RecyclerView recyclerView;
    private AlsID7UiUiConfigAdapter uiConfigAdapter;
    private String uiIndex;
    private List<String> uls;
    private View view;

    public AlsID7UiCarUiConfig(Context context) {
        super(context);
        this.possint = 0;
        this.uiIndex = "";
        this.handler = new Handler() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarUiConfig.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        Log.d("UiConfig", "===send ui index====:" + AlsID7UiCarUiConfig.this.possint);
                        for (FunctionBean fb : AlsID7UiCarUiConfig.this.data) {
                            fb.setIscheck(false);
                        }
                        ((FunctionBean) AlsID7UiCarUiConfig.this.data.get(AlsID7UiCarUiConfig.this.possint)).setIscheck(true);
                        AlsID7UiCarUiConfig.this.uiConfigAdapter.notifyDataSetChanged();
                        Log.d("UiConfig", "===send ui name====:" + ((FunctionBean) AlsID7UiCarUiConfig.this.data.get(AlsID7UiCarUiConfig.this.possint)).getTitle());
                        FileUtils.savaStringData(KeyConfig.SUPP_UI_TYPE, ((FunctionBean) AlsID7UiCarUiConfig.this.data.get(AlsID7UiCarUiConfig.this.possint)).getTitle());
                        AlsID7UiCarUiConfig.this.handler.sendEmptyMessageDelayed(1, 300L);
                        return;
                    case 1:
                        AlsID7UiCarUiConfig.this.dialogViews.dismiss();
                        return;
                    default:
                        return;
                }
            }
        };
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(C0899R.C0902layout.als_id7_ui_factory_ui_config, (ViewGroup) null);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initData() {
        try {
            this.uls = PowerManagerApp.getDataListFromJsonKey(KeyConfig.SUPP_UI_LIST);
            this.uiIndex = PowerManagerApp.getSettingsString(KeyConfig.SUPP_UI_TYPE);
            Log.d("UiConfig", "===get dat size====:" + this.uls.size());
            if (TextUtils.isEmpty(this.uiIndex)) {
                this.uiIndex = UiThemeUtils.BMW_EVO_ID7;
            }
            this.data = new ArrayList();
            for (String ss : this.uls) {
                Log.d("UiConfig", "====:" + ss);
                FunctionBean fb = new FunctionBean();
                String[] name = ss.split("&");
                Log.d("UiConfig", "====: title: " + name[name.length - 1] + " display: " + name[0]);
                fb.setDisplay(name[name.length - 1]);
                fb.setTitle(name[0]);
                if (TextUtils.equals(name[0], this.uiIndex)) {
                    fb.setIscheck(true);
                }
                this.data.add(fb);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView() {
        this.dialogViews = new DialogViews(this.m_con);
        this.recyclerView = (RecyclerView) this.view.findViewById(C0899R.C0901id.ui_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.m_con);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        AlsID7UiUiConfigAdapter alsID7UiUiConfigAdapter = new AlsID7UiUiConfigAdapter(this.m_con, this.data);
        this.uiConfigAdapter = alsID7UiUiConfigAdapter;
        this.recyclerView.setAdapter(alsID7UiUiConfigAdapter);
        this.uiConfigAdapter.registCheckListener(new AlsID7UiUiConfigAdapter.OnItemClickLisen() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarUiConfig.2
            @Override // com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiUiConfigAdapter.OnItemClickLisen
            public void ItemClickLisen(int position) {
                AlsID7UiCarUiConfig.this.dialogViews.isSelecUi(AlsID7UiCarUiConfig.this.getResources().getString(C0899R.string.dialog_update9), AlsID7UiCarUiConfig.this.handler);
                AlsID7UiCarUiConfig.this.possint = position;
            }
        });
    }
}
