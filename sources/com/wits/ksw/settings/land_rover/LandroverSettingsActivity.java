package com.wits.ksw.settings.land_rover;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.id7.FactoryActivity;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.id7.layout_one.SetToAndSysLayout;
import com.wits.ksw.settings.id7.layout_two.SetImageTwo;
import com.wits.ksw.settings.land_rover.adapter.LandroverFunctionAdapter;
import com.wits.ksw.settings.land_rover.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.land_rover.layout_one.LandroverSetFactoryLayout;
import com.wits.ksw.settings.land_rover.layout_one.LandroverSetLanguageLayout;
import com.wits.ksw.settings.land_rover.layout_one.LandroverSetNaviLayout;
import com.wits.ksw.settings.land_rover.layout_one.LandroverSetSystemInfoLayout;
import com.wits.ksw.settings.land_rover.layout_one.LandroverSetSystemLayout;
import com.wits.ksw.settings.land_rover.layout_one.LandroverSetTimeLayout;
import com.wits.ksw.settings.land_rover.layout_one.LandroverSetVocModeLayout;
import com.wits.ksw.settings.land_rover.layout_one.LandroverSetVoiceLayout;
import com.wits.ksw.settings.land_rover.layout_two.LandroverNaviTwo;
import com.wits.ksw.settings.land_rover.layout_two.LandroverSetSystemTwo;
import com.wits.ksw.settings.land_rover.layout_two.LandroverSetVocModelTwo;
import com.wits.ksw.settings.land_rover.layout_two.LandroverTimeSetTwo;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

public class LandroverSettingsActivity extends BaseActivity implements IUpdateTwoLayout, ScanNaviList.OnMapListScanListener {
    private ImageView Img_SetBack;
    /* access modifiers changed from: private */
    public LandroverFunctionAdapter adapter;
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    /* access modifiers changed from: private */
    public String defPwd = "1314";
    private boolean first = true;
    private FrameLayout frame_OneLayout;
    private FrameLayout frame_TwoLayout;
    Handler handler = new Handler() {
        @RequiresApi(api = 24)
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i != 0) {
                switch (i) {
                    case 2:
                        if (TextUtils.equals(LandroverSettingsActivity.this.defPwd, (String) msg.obj)) {
                            LandroverSettingsActivity.this.startActivity(new Intent(LandroverSettingsActivity.this, FactoryActivity.class));
                            LandroverSettingsActivity.this.finish();
                            return;
                        }
                        LandroverSettingsActivity.this.setFactoryLayout.SetTextEEro();
                        return;
                    case 3:
                        if (LandroverSettingsActivity.this.naviTwo != null) {
                            Log.d("Navi", "updateList: " + LandroverSettingsActivity.this.mapList.size());
                            LandroverSettingsActivity.this.naviTwo.updateMapList(LandroverSettingsActivity.this.mapList);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else {
                LandroverSettingsActivity.this.initOneLayout();
                LandroverSettingsActivity.this.initTwoLayout();
            }
        }
    };
    private LinearLayoutManager layoutManager;
    private WindowManager.LayoutParams lp;
    /* access modifiers changed from: private */
    public List<MapBean> mapList = new ArrayList();
    /* access modifiers changed from: private */
    public LandroverNaviTwo naviTwo;
    private RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public LandroverSetFactoryLayout setFactoryLayout;
    private SetImageTwo setImageTwo;
    private LandroverSetLanguageLayout setLanguageLayout;
    private LandroverSetNaviLayout setNaviLayout;
    private LandroverSetSystemInfoLayout setSystemInfoLayout;
    private LandroverSetSystemLayout setSystemLayout;
    private LandroverSetSystemTwo setSystemTwo;
    private LandroverSetTimeLayout setTimeLayout;
    private SetToAndSysLayout setToAndSysLayout;
    private LandroverSetVocModeLayout setVocModeLayout;
    private LandroverSetVocModelTwo setVocModelTwo;
    private LandroverSetVoiceLayout setVoiceLayout;
    private LandroverTimeSetTwo timeSetTwo;
    private String voiceData;

    /* access modifiers changed from: protected */
    @RequiresApi(api = 24)
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_land_rover_settings);
        initData();
        initView();
    }

    private void setFullWidown() {
        this.lp = getWindow().getAttributes();
        if (!((this.lp.flags & 1024) == 1024)) {
            WindowManager.LayoutParams layoutParams = this.lp;
            layoutParams.flags = 1024 | layoutParams.flags;
            getWindow().setAttributes(this.lp);
            getWindow().addFlags(512);
        }
    }

    @RequiresApi(api = 24)
    public void skipItem() {
        if (TextUtils.equals("voic", this.voiceData)) {
            initOneLayout();
            initTwoLayout();
            setOneLayout(2);
            for (FunctionBean fb : this.data) {
                fb.setIscheck(false);
            }
            this.data.get(2).setIscheck(true);
            if (this.adapter != null) {
                this.adapter.notifyDataSetChanged();
            }
        } else if (TextUtils.equals("voicFun", this.voiceData)) {
            initOneLayout();
            initTwoLayout();
            setOneLayout(3);
            for (FunctionBean fb2 : this.data) {
                fb2.setIscheck(false);
            }
            this.data.get(3).setIscheck(true);
            if (this.adapter != null) {
                this.adapter.notifyDataSetChanged();
            }
        }
    }

    private void initSaveData() {
        try {
            List<String> naviList = PowerManagerApp.getDataListFromJsonKey(KeyConfig.NAVI_LIST);
            String navidefual = PowerManagerApp.getSettingsString(KeyConfig.NAVI_DEFUAL);
            if (naviList != null && naviList.size() > 0) {
                ScanNaviList.getInstance().scanList(naviList, navidefual, this);
                Log.d("Navi", "==size==:" + ScanNaviList.getInstance().getMapList().size());
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    @RequiresApi(api = 24)
    public void onResume() {
        super.onResume();
        setFullWidown();
        ScanNaviList.getInstance().setMapListScanListener(this);
        this.handler.sendEmptyMessageDelayed(0, 1000);
        initSaveData();
        skipItem();
        if (this.frame_TwoLayout != null) {
            this.frame_TwoLayout.removeAllViews();
        }
        if (this.frame_OneLayout != null) {
            this.frame_OneLayout.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        ScanNaviList.getInstance().setMapListScanListener((ScanNaviList.OnMapListScanListener) null);
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 24)
    public void initOneLayout() {
        if (this.setSystemInfoLayout == null) {
            this.setSystemLayout = new LandroverSetSystemLayout(this);
            this.setSystemLayout.registIUpdateTwoLayout(this);
        }
        if (this.setNaviLayout == null) {
            this.setNaviLayout = new LandroverSetNaviLayout(this);
            this.setNaviLayout.registIUpdateTwoLayout(this);
        }
        if (this.setVoiceLayout == null) {
            this.setVoiceLayout = new LandroverSetVoiceLayout(this);
        }
        if (this.setVocModeLayout == null) {
            this.setVocModeLayout = new LandroverSetVocModeLayout(this);
            this.setVocModeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setLanguageLayout == null) {
            this.setLanguageLayout = new LandroverSetLanguageLayout(this);
        }
        if (this.setToAndSysLayout == null) {
            this.setToAndSysLayout = new SetToAndSysLayout(this);
        }
        if (this.setTimeLayout == null) {
            this.setTimeLayout = new LandroverSetTimeLayout(this);
            this.setTimeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setSystemInfoLayout == null) {
            this.setSystemInfoLayout = new LandroverSetSystemInfoLayout(this);
        }
        if (this.setFactoryLayout == null) {
            this.setFactoryLayout = new LandroverSetFactoryLayout(this, this.handler);
        }
    }

    /* access modifiers changed from: private */
    public void initTwoLayout() {
        if (this.setSystemTwo == null) {
            this.setSystemTwo = new LandroverSetSystemTwo(this);
        }
        if (this.naviTwo == null) {
            this.naviTwo = new LandroverNaviTwo(this);
        }
        if (this.setVocModelTwo == null) {
            this.setVocModelTwo = new LandroverSetVocModelTwo(this);
        }
        if (this.timeSetTwo == null) {
            this.timeSetTwo = new LandroverTimeSetTwo(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.voiceData = intent.getStringExtra("voiceData");
        Log.d("id7startAction", "===data====:" + this.voiceData);
    }

    private void initData() {
        try {
            this.voiceData = getIntent().getStringExtra("voiceData");
            Log.d("id7startAction", "===data====:" + this.voiceData);
            this.defPwd = PowerManagerApp.getSettingsString(KeyConfig.PASSWORD);
            Log.d("id7FactoryPwd", "===pwd====:" + this.defPwd);
            if (TextUtils.isEmpty(this.defPwd)) {
                this.defPwd = "1314";
            }
        } catch (Exception e) {
            e.getStackTrace();
            this.defPwd = "1314";
        }
        int[] icons = {R.drawable.land_rover_settings_system_icon, R.drawable.land_rover_settings_gps_icon, R.drawable.land_rover_settings_audio_icon, R.drawable.land_rover_settings_language_icon, R.drawable.land_rover_settings_time_icon, R.drawable.land_rover_settings_info_icon, R.drawable.land_rover_settings_android_icon, R.drawable.land_rover_settings_factory_icon};
        this.data = new ArrayList();
        String[] functions = getResources().getStringArray(R.array.set_land_rover_function);
        for (int i = 0; i < icons.length; i++) {
            FunctionBean fcb = new FunctionBean();
            fcb.setIcon(icons[i]);
            fcb.setTitle(functions[i]);
            this.data.add(fcb);
        }
        this.data.get(0).setIscheck(true);
    }

    private void initView() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.layoutManager = new LinearLayoutManager(this);
        this.layoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.adapter = new LandroverFunctionAdapter(this, this.data);
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.registOnFunctionClickListener(new LandroverFunctionAdapter.OnFunctionClickListener() {
            @RequiresApi(api = 24)
            public void functonClick(int pos) {
                LandroverSettingsActivity.this.setOneLayout(pos);
                for (FunctionBean fb : LandroverSettingsActivity.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) LandroverSettingsActivity.this.data.get(pos)).setIscheck(true);
                LandroverSettingsActivity.this.adapter.notifyDataSetChanged();
            }
        });
        this.frame_OneLayout = (FrameLayout) findViewById(R.id.frame_OneLayout);
        this.frame_TwoLayout = (FrameLayout) findViewById(R.id.frame_TwoLayout);
        if (this.setSystemLayout == null) {
            this.setSystemLayout = new LandroverSetSystemLayout(this);
            this.setSystemLayout.registIUpdateTwoLayout(this);
        }
        this.frame_OneLayout.addView(this.setSystemLayout);
        if (this.setSystemTwo == null) {
            this.setSystemTwo = new LandroverSetSystemTwo(this);
        }
        this.Img_SetBack = (ImageView) findViewById(R.id.Img_SetBack);
        this.Img_SetBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LandroverSettingsActivity.this.onBackPressed();
            }
        });
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 24)
    public void setOneLayout(int type) {
        this.frame_OneLayout.removeAllViews();
        this.frame_TwoLayout.removeAllViews();
        this.frame_OneLayout.setVisibility(0);
        switch (type) {
            case 0:
                if (this.setSystemLayout == null) {
                    this.setSystemLayout = new LandroverSetSystemLayout(this);
                    this.setSystemLayout.registIUpdateTwoLayout(this);
                }
                if (this.setSystemTwo == null) {
                    this.setSystemTwo = new LandroverSetSystemTwo(this);
                }
                this.frame_OneLayout.addView(this.setSystemLayout);
                this.setSystemLayout.resetTextColor();
                break;
            case 1:
                if (this.setNaviLayout == null) {
                    this.setNaviLayout = new LandroverSetNaviLayout(this);
                    this.setNaviLayout.registIUpdateTwoLayout(this);
                }
                if (this.naviTwo == null) {
                    this.naviTwo = new LandroverNaviTwo(this);
                }
                this.frame_OneLayout.addView(this.setNaviLayout);
                this.setNaviLayout.resetTextColor();
                break;
            case 2:
                if (this.setVoiceLayout == null) {
                    this.setVoiceLayout = new LandroverSetVoiceLayout(this);
                }
                this.frame_OneLayout.addView(this.setVoiceLayout);
                break;
            case 3:
                if (this.setLanguageLayout == null) {
                    this.setLanguageLayout = new LandroverSetLanguageLayout(this);
                }
                this.frame_OneLayout.addView(this.setLanguageLayout);
                break;
            case 4:
                if (this.setTimeLayout == null) {
                    this.setTimeLayout = new LandroverSetTimeLayout(this);
                    this.setTimeLayout.registIUpdateTwoLayout(this);
                }
                if (this.timeSetTwo == null) {
                    this.timeSetTwo = new LandroverTimeSetTwo(this);
                }
                this.frame_OneLayout.addView(this.setTimeLayout);
                this.setTimeLayout.resetTextColor();
                break;
            case 5:
                if (this.setSystemInfoLayout == null) {
                    this.setSystemInfoLayout = new LandroverSetSystemInfoLayout(this);
                }
                this.frame_OneLayout.addView(this.setSystemInfoLayout);
                break;
            case 6:
                if (this.setToAndSysLayout == null) {
                    this.setToAndSysLayout = new SetToAndSysLayout(this);
                }
                this.frame_OneLayout.addView(this.setToAndSysLayout);
                sendToApp("com.android.settings", "com.android.settings.Settings");
                break;
            case 7:
                if (this.setFactoryLayout == null) {
                    this.setFactoryLayout = new LandroverSetFactoryLayout(this, this.handler);
                }
                this.frame_OneLayout.addView(this.setFactoryLayout);
                break;
        }
        this.frame_OneLayout.requestFocus();
    }

    public void updateTwoLayout(int type, int shwoIndex) {
        this.frame_TwoLayout.removeAllViews();
        this.frame_OneLayout.setVisibility(4);
        switch (type) {
            case 1:
                this.frame_TwoLayout.addView(this.setSystemTwo);
                this.setSystemTwo.showLayout(shwoIndex);
                this.setSystemTwo.invalidate();
                this.setSystemTwo.requestFocus();
                return;
            case 2:
                this.frame_TwoLayout.addView(this.naviTwo);
                this.naviTwo.showLayout(shwoIndex);
                this.naviTwo.invalidate();
                this.naviTwo.requestFocus();
                return;
            case 3:
                this.frame_TwoLayout.addView(this.setVocModelTwo);
                this.setVocModelTwo.showLayout(shwoIndex);
                this.setVocModelTwo.invalidate();
                this.setVocModelTwo.requestFocus();
                return;
            case 4:
                this.frame_TwoLayout.addView(this.timeSetTwo);
                this.timeSetTwo.showLayout(shwoIndex);
                this.timeSetTwo.invalidate();
                this.timeSetTwo.requestFocus();
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        Log.d("onBackPressed", "onBackPressed frame_TwoLayout child =" + this.frame_TwoLayout.getChildCount());
        if (this.frame_TwoLayout == null || this.frame_TwoLayout.getChildCount() <= 0) {
            super.onBackPressed();
            return;
        }
        this.frame_TwoLayout.removeAllViews();
        this.frame_OneLayout.setVisibility(0);
    }

    public void onScanFinish(List<MapBean> mapList2) {
        Log.d("Navi", "onScanFinish " + mapList2.size());
        this.mapList = mapList2;
        this.handler.sendEmptyMessage(3);
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
