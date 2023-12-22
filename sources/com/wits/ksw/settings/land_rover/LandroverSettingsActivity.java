package com.wits.ksw.settings.land_rover;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ActivityLandRoverSettingsBinding;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;
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

/* loaded from: classes6.dex */
public class LandroverSettingsActivity extends BaseActivity implements IUpdateTwoLayout, ScanNaviList.OnMapListScanListener {
    private ImageView Img_SetBack;
    private LandroverFunctionAdapter adapter;
    private List<FunctionBean> data;
    private FrameLayout frame_OneLayout;
    private FrameLayout frame_TwoLayout;
    private ActivityLandRoverSettingsBinding landroverBinding;
    private LandroverViewModel landroverViewModel;
    private LinearLayoutManager layoutManager;

    /* renamed from: lp */
    private WindowManager.LayoutParams f230lp;
    private LandroverNaviTwo naviTwo;
    private RecyclerView recyclerView;
    private LandroverSetFactoryLayout setFactoryLayout;
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
    private String defPwd = "1314";
    private boolean first = true;
    Handler handler = new Handler() { // from class: com.wits.ksw.settings.land_rover.LandroverSettingsActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    LandroverSettingsActivity.this.initOneLayout();
                    LandroverSettingsActivity.this.initTwoLayout();
                    return;
                case 1:
                default:
                    return;
                case 2:
                    String inputPwd = (String) msg.obj;
                    if (!TextUtils.equals(LandroverSettingsActivity.this.defPwd, inputPwd)) {
                        LandroverSettingsActivity.this.setFactoryLayout.SetTextEEro();
                        return;
                    }
                    LandroverSettingsActivity.this.startActivity(new Intent(LandroverSettingsActivity.this, FactoryActivity.class));
                    LandroverSettingsActivity.this.finish();
                    return;
                case 3:
                    if (LandroverSettingsActivity.this.naviTwo != null) {
                        Log.d("Navi", "updateList: " + LandroverSettingsActivity.this.mapList.size());
                        LandroverSettingsActivity.this.naviTwo.updateMapList(LandroverSettingsActivity.this.mapList);
                        return;
                    }
                    return;
            }
        }
    };
    private List<MapBean> mapList = new ArrayList();

    @Override // com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LandroverViewModel landroverViewModel = (LandroverViewModel) ViewModelProviders.m59of(this).get(LandroverViewModel.class);
        this.landroverViewModel = landroverViewModel;
        landroverViewModel.setActivity(this);
        ActivityLandRoverSettingsBinding activityLandRoverSettingsBinding = (ActivityLandRoverSettingsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_land_rover_settings);
        this.landroverBinding = activityLandRoverSettingsBinding;
        activityLandRoverSettingsBinding.setLauncherViewModel(this.landroverViewModel);
        initData();
        initView();
    }

    private void setFullWidown() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        this.f230lp = attributes;
        boolean isFull = (attributes.flags & 1024) == 1024;
        if (!isFull) {
            WindowManager.LayoutParams layoutParams = this.f230lp;
            layoutParams.flags = 1024 | layoutParams.flags;
            getWindow().setAttributes(this.f230lp);
            getWindow().addFlags(512);
        }
    }

    public void skipItem() {
        if (TextUtils.equals("voic", this.voiceData)) {
            initOneLayout();
            initTwoLayout();
            setOneLayout(2);
            for (FunctionBean fb : this.data) {
                fb.setIscheck(false);
            }
            this.data.get(2).setIscheck(true);
            LandroverFunctionAdapter landroverFunctionAdapter = this.adapter;
            if (landroverFunctionAdapter != null) {
                landroverFunctionAdapter.notifyDataSetChanged();
            }
        } else if (TextUtils.equals("voicFun", this.voiceData)) {
            initOneLayout();
            initTwoLayout();
            setOneLayout(3);
            for (FunctionBean fb2 : this.data) {
                fb2.setIscheck(false);
            }
            this.data.get(3).setIscheck(true);
            LandroverFunctionAdapter landroverFunctionAdapter2 = this.adapter;
            if (landroverFunctionAdapter2 != null) {
                landroverFunctionAdapter2.notifyDataSetChanged();
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

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        setFullWidown();
        ScanNaviList.getInstance().setMapListScanListener(this);
        this.handler.sendEmptyMessageDelayed(0, 1000L);
        initSaveData();
        skipItem();
        FrameLayout frameLayout = this.frame_TwoLayout;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        FrameLayout frameLayout2 = this.frame_OneLayout;
        if (frameLayout2 != null) {
            frameLayout2.setVisibility(0);
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        ScanNaviList.getInstance().setMapListScanListener(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOneLayout() {
        if (this.setSystemInfoLayout == null) {
            LandroverSetSystemLayout landroverSetSystemLayout = new LandroverSetSystemLayout(this);
            this.setSystemLayout = landroverSetSystemLayout;
            landroverSetSystemLayout.registIUpdateTwoLayout(this);
        }
        if (this.setNaviLayout == null) {
            LandroverSetNaviLayout landroverSetNaviLayout = new LandroverSetNaviLayout(this);
            this.setNaviLayout = landroverSetNaviLayout;
            landroverSetNaviLayout.registIUpdateTwoLayout(this);
        }
        if (this.setVoiceLayout == null) {
            this.setVoiceLayout = new LandroverSetVoiceLayout(this);
        }
        if (this.setVocModeLayout == null) {
            LandroverSetVocModeLayout landroverSetVocModeLayout = new LandroverSetVocModeLayout(this);
            this.setVocModeLayout = landroverSetVocModeLayout;
            landroverSetVocModeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setLanguageLayout == null) {
            this.setLanguageLayout = new LandroverSetLanguageLayout(this);
        }
        if (this.setToAndSysLayout == null) {
            this.setToAndSysLayout = new SetToAndSysLayout(this);
        }
        if (this.setTimeLayout == null) {
            LandroverSetTimeLayout landroverSetTimeLayout = new LandroverSetTimeLayout(this);
            this.setTimeLayout = landroverSetTimeLayout;
            landroverSetTimeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setSystemInfoLayout == null) {
            this.setSystemInfoLayout = new LandroverSetSystemInfoLayout(this);
        }
        if (this.setFactoryLayout == null) {
            this.setFactoryLayout = new LandroverSetFactoryLayout(this, this.handler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
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

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
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
        int[] icons = {C0899R.C0900drawable.land_rover_settings_system_icon, C0899R.C0900drawable.land_rover_settings_gps_icon, C0899R.C0900drawable.land_rover_settings_audio_icon, C0899R.C0900drawable.land_rover_settings_language_icon, C0899R.C0900drawable.land_rover_settings_time_icon, C0899R.C0900drawable.land_rover_settings_info_icon, C0899R.C0900drawable.land_rover_settings_android_icon, C0899R.C0900drawable.land_rover_settings_factory_icon};
        this.data = new ArrayList();
        String[] functions = getResources().getStringArray(C0899R.array.set_land_rover_function);
        for (int i = 0; i < icons.length; i++) {
            FunctionBean fcb = new FunctionBean();
            fcb.setIcon(icons[i]);
            fcb.setTitle(functions[i]);
            this.data.add(fcb);
        }
        this.data.get(0).setIscheck(true);
    }

    private void initView() {
        this.recyclerView = (RecyclerView) findViewById(C0899R.C0901id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        LandroverFunctionAdapter landroverFunctionAdapter = new LandroverFunctionAdapter(this, this.data);
        this.adapter = landroverFunctionAdapter;
        this.recyclerView.setAdapter(landroverFunctionAdapter);
        this.adapter.registOnFunctionClickListener(new LandroverFunctionAdapter.OnFunctionClickListener() { // from class: com.wits.ksw.settings.land_rover.LandroverSettingsActivity.2
            @Override // com.wits.ksw.settings.land_rover.adapter.LandroverFunctionAdapter.OnFunctionClickListener
            public void functonClick(int pos) {
                LandroverSettingsActivity.this.setOneLayout(pos);
                for (FunctionBean fb : LandroverSettingsActivity.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) LandroverSettingsActivity.this.data.get(pos)).setIscheck(true);
                LandroverSettingsActivity.this.adapter.notifyDataSetChanged();
            }
        });
        this.frame_OneLayout = (FrameLayout) findViewById(C0899R.C0901id.frame_OneLayout);
        this.frame_TwoLayout = (FrameLayout) findViewById(C0899R.C0901id.frame_TwoLayout);
        if (this.setSystemLayout == null) {
            LandroverSetSystemLayout landroverSetSystemLayout = new LandroverSetSystemLayout(this);
            this.setSystemLayout = landroverSetSystemLayout;
            landroverSetSystemLayout.registIUpdateTwoLayout(this);
        }
        this.frame_OneLayout.addView(this.setSystemLayout);
        if (this.setSystemTwo == null) {
            this.setSystemTwo = new LandroverSetSystemTwo(this);
        }
        ImageView imageView = (ImageView) findViewById(C0899R.C0901id.Img_SetBack);
        this.Img_SetBack = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.land_rover.LandroverSettingsActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LandroverSettingsActivity.this.onBackPressed();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOneLayout(int type) {
        this.frame_OneLayout.removeAllViews();
        this.frame_TwoLayout.removeAllViews();
        this.frame_OneLayout.setVisibility(0);
        switch (type) {
            case 0:
                if (this.setSystemLayout == null) {
                    LandroverSetSystemLayout landroverSetSystemLayout = new LandroverSetSystemLayout(this);
                    this.setSystemLayout = landroverSetSystemLayout;
                    landroverSetSystemLayout.registIUpdateTwoLayout(this);
                }
                if (this.setSystemTwo == null) {
                    this.setSystemTwo = new LandroverSetSystemTwo(this);
                }
                this.frame_OneLayout.addView(this.setSystemLayout);
                this.setSystemLayout.resetTextColor();
                break;
            case 1:
                if (this.setNaviLayout == null) {
                    LandroverSetNaviLayout landroverSetNaviLayout = new LandroverSetNaviLayout(this);
                    this.setNaviLayout = landroverSetNaviLayout;
                    landroverSetNaviLayout.registIUpdateTwoLayout(this);
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
                    LandroverSetTimeLayout landroverSetTimeLayout = new LandroverSetTimeLayout(this);
                    this.setTimeLayout = landroverSetTimeLayout;
                    landroverSetTimeLayout.registIUpdateTwoLayout(this);
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

    @Override // com.wits.ksw.settings.land_rover.interfaces.IUpdateTwoLayout
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

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        Log.d("onBackPressed", "onBackPressed frame_TwoLayout child =" + this.frame_TwoLayout.getChildCount());
        FrameLayout frameLayout = this.frame_TwoLayout;
        if (frameLayout != null && frameLayout.getChildCount() > 0) {
            this.frame_TwoLayout.removeAllViews();
            this.frame_OneLayout.setVisibility(0);
            return;
        }
        super.onBackPressed();
    }

    @Override // com.wits.ksw.settings.utlis_view.ScanNaviList.OnMapListScanListener
    public void onScanFinish(List<MapBean> mapList) {
        Log.d("Navi", "onScanFinish " + mapList.size());
        this.mapList = mapList;
        this.handler.sendEmptyMessage(3);
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
