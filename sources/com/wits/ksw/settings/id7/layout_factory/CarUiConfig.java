package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.id7.adapter.UiConfigAdapter;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

public class CarUiConfig extends FrameLayout {
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    /* access modifiers changed from: private */
    public DialogViews dialogViews;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Log.d("UiConfig", "===send ui index====:" + CarUiConfig.this.possint);
                    for (FunctionBean fb : CarUiConfig.this.data) {
                        fb.setIscheck(false);
                    }
                    ((FunctionBean) CarUiConfig.this.data.get(CarUiConfig.this.possint)).setIscheck(true);
                    CarUiConfig.this.uiConfigAdapter.notifyDataSetChanged();
                    Log.d("UiConfig", "===send ui name====:" + ((FunctionBean) CarUiConfig.this.data.get(CarUiConfig.this.possint)).getTitle());
                    FileUtils.savaStringData(KeyConfig.SUPP_UI_TYPE, ((FunctionBean) CarUiConfig.this.data.get(CarUiConfig.this.possint)).getTitle());
                    CarUiConfig.this.handler.sendEmptyMessageDelayed(1, 300);
                    return;
                case 1:
                    CarUiConfig.this.dialogViews.dismiss();
                    return;
                default:
                    return;
            }
        }
    };
    private LinearLayoutManager layoutManager;
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    /* access modifiers changed from: private */
    public int possint = 0;
    private RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public UiConfigAdapter uiConfigAdapter;
    private String uiIndex = "";
    private List<String> uls;
    private View view;

    public CarUiConfig(@NonNull Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(this.m_con).inflate(R.layout.factory_ui_config, (ViewGroup) null);
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
                fb.setTitle(ss);
                if (TextUtils.equals(ss, this.uiIndex)) {
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
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.ui_recycle);
        this.layoutManager = new LinearLayoutManager(this.m_con);
        this.layoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.uiConfigAdapter = new UiConfigAdapter(this.m_con, this.data);
        this.recyclerView.setAdapter(this.uiConfigAdapter);
        this.uiConfigAdapter.registCheckListener(new UiConfigAdapter.OnItemClickLisen() {
            public void ItemClickLisen(int position) {
                CarUiConfig.this.dialogViews.isSelecUi(CarUiConfig.this.getResources().getString(R.string.dialog_update9), CarUiConfig.this.handler);
                int unused = CarUiConfig.this.possint = position;
            }
        });
    }
}
