package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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
    public String clickUiItem = "";
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    /* access modifiers changed from: private */
    public DialogViews dialogViews;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Log.d("UiConfig", "    clickUiItem=" + CarUiConfig.this.clickUiItem);
                    for (FunctionBean fb : CarUiConfig.this.data) {
                        if (CarUiConfig.this.clickUiItem.equals(fb.getTitle())) {
                            fb.setIscheck(true);
                        } else {
                            fb.setIscheck(false);
                        }
                    }
                    CarUiConfig.this.uiConfigAdapter.notifyDataSetChanged();
                    FileUtils.savaStringData(KeyConfig.SUPP_UI_TYPE, CarUiConfig.this.clickUiItem);
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
    private RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public UiConfigAdapter uiConfigAdapter;
    private String uiIndex = "";
    private List<String> uls;
    private View view;

    public CarUiConfig(Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.factory_ui_config, (ViewGroup) null);
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
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.ui_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.m_con);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        UiConfigAdapter uiConfigAdapter2 = new UiConfigAdapter(this.m_con, this.data);
        this.uiConfigAdapter = uiConfigAdapter2;
        this.recyclerView.setAdapter(uiConfigAdapter2);
        this.uiConfigAdapter.registCheckListener(new UiConfigAdapter.OnItemClickLisen() {
            public void ItemClickLisen(int position) {
                CarUiConfig carUiConfig = CarUiConfig.this;
                String unused = carUiConfig.clickUiItem = ((FunctionBean) carUiConfig.data.get(position)).getTitle();
                Log.d("clickUiItem", "clickUiItem=" + CarUiConfig.this.clickUiItem);
                CarUiConfig.this.dialogViews.isSelecUi(CarUiConfig.this.getResources().getString(R.string.dialog_update9), CarUiConfig.this.handler);
            }
        });
    }
}
