package com.wits.ksw.settings.id7;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.DividerItemDecoration;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.id7.adapter.FunctionAdapter;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.id7.layout_one.SetFactoryLayout;
import com.wits.ksw.settings.id7.layout_one.SetLanguageLayout;
import com.wits.ksw.settings.id7.layout_one.SetNaviLayout;
import com.wits.ksw.settings.id7.layout_one.SetSystemInfoLayout;
import com.wits.ksw.settings.id7.layout_one.SetSystemLayout;
import com.wits.ksw.settings.id7.layout_one.SetTimeLayout;
import com.wits.ksw.settings.id7.layout_one.SetToAndSysLayout;
import com.wits.ksw.settings.id7.layout_one.SetVocModeLayout;
import com.wits.ksw.settings.id7.layout_one.SetVoiceLayout;
import com.wits.ksw.settings.id7.layout_two.NaviTwo;
import com.wits.ksw.settings.id7.layout_two.SetImageTwo;
import com.wits.ksw.settings.id7.layout_two.SetSystemTwo;
import com.wits.ksw.settings.id7.layout_two.SetVocModelTwo;
import com.wits.ksw.settings.id7.layout_two.TimeSetTwo;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class ID7SettingsActivity extends BaseActivity implements IUpdateTwoLayout, ScanNaviList.OnMapListScanListener {
    private ImageView Img_SetBack;
    private FunctionAdapter adapter;
    private List<FunctionBean> data;
    private FrameLayout frame_OneLayout;
    private FrameLayout frame_TwoLayout;
    private TimeSetTwo fuelUnitTwo;
    private LinearLayoutManager layoutManager;
    private NaviTwo naviTwo;
    private RecyclerView recyclerView;
    private SetFactoryLayout setFactoryLayout;
    private SetImageTwo setImageTwo;
    private SetLanguageLayout setLanguageLayout;
    private SetNaviLayout setNaviLayout;
    private SetSystemInfoLayout setSystemInfoLayout;
    private SetSystemLayout setSystemLayout;
    private SetSystemTwo setSystemTwo;
    private SetTimeLayout setTimeLayout;
    private SetToAndSysLayout setToAndSysLayout;
    private SetVocModeLayout setVocModeLayout;
    private SetVocModelTwo setVocModelTwo;
    private SetVoiceLayout setVoiceLayout;
    private TimeSetTwo timeSetTwo;
    private String voiceData;
    private String defPwd = "1314";
    private boolean first = true;
    Handler handler = new Handler() { // from class: com.wits.ksw.settings.id7.ID7SettingsActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    ID7SettingsActivity.this.initOneLayout();
                    ID7SettingsActivity.this.initTwoLayout();
                    return;
                case 1:
                default:
                    return;
                case 2:
                    String inputPwd = (String) msg.obj;
                    if (!TextUtils.equals(ID7SettingsActivity.this.defPwd, inputPwd)) {
                        ID7SettingsActivity.this.setFactoryLayout.SetTextEEro();
                        return;
                    }
                    ID7SettingsActivity.this.startActivity(new Intent(ID7SettingsActivity.this, FactoryActivity.class));
                    ID7SettingsActivity.this.finish();
                    return;
                case 3:
                    if (ID7SettingsActivity.this.naviTwo != null) {
                        Log.d("Navi", "updateList: " + ID7SettingsActivity.this.mapList.size());
                        ID7SettingsActivity.this.naviTwo.updateMapList(ID7SettingsActivity.this.mapList);
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
        setContentView(C0899R.C0902layout.activity_id7_settings);
        initData();
        initView();
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
            FunctionAdapter functionAdapter = this.adapter;
            if (functionAdapter != null) {
                functionAdapter.notifyDataSetChanged();
            }
        } else if (TextUtils.equals("voicFun", this.voiceData)) {
            initOneLayout();
            initTwoLayout();
            setOneLayout(3);
            for (FunctionBean fb2 : this.data) {
                fb2.setIscheck(false);
            }
            this.data.get(3).setIscheck(true);
            FunctionAdapter functionAdapter2 = this.adapter;
            if (functionAdapter2 != null) {
                functionAdapter2.notifyDataSetChanged();
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
        ScanNaviList.getInstance().setMapListScanListener(this);
        this.handler.sendEmptyMessageDelayed(0, 1000L);
        initSaveData();
        skipItem();
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        ScanNaviList.getInstance().setMapListScanListener(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOneLayout() {
        if (this.setSystemInfoLayout == null) {
            SetSystemLayout setSystemLayout = new SetSystemLayout(this);
            this.setSystemLayout = setSystemLayout;
            setSystemLayout.registIUpdateTwoLayout(this);
        }
        if (this.setNaviLayout == null) {
            SetNaviLayout setNaviLayout = new SetNaviLayout(this);
            this.setNaviLayout = setNaviLayout;
            setNaviLayout.registIUpdateTwoLayout(this);
        }
        if (this.setVoiceLayout == null) {
            this.setVoiceLayout = new SetVoiceLayout(this);
        }
        if (this.setVocModeLayout == null) {
            SetVocModeLayout setVocModeLayout = new SetVocModeLayout(this);
            this.setVocModeLayout = setVocModeLayout;
            setVocModeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setLanguageLayout == null) {
            this.setLanguageLayout = new SetLanguageLayout(this);
        }
        if (this.setToAndSysLayout == null) {
            this.setToAndSysLayout = new SetToAndSysLayout(this);
        }
        if (this.setTimeLayout == null) {
            SetTimeLayout setTimeLayout = new SetTimeLayout(this);
            this.setTimeLayout = setTimeLayout;
            setTimeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setSystemInfoLayout == null) {
            this.setSystemInfoLayout = new SetSystemInfoLayout(this);
        }
        if (this.setFactoryLayout == null) {
            this.setFactoryLayout = new SetFactoryLayout(this, this.handler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initTwoLayout() {
        if (this.setSystemTwo == null) {
            this.setSystemTwo = new SetSystemTwo(this);
        }
        if (this.naviTwo == null) {
            this.naviTwo = new NaviTwo(this);
        }
        if (this.setImageTwo == null) {
            this.setImageTwo = new SetImageTwo(this);
        }
        if (this.setVocModelTwo == null) {
            this.setVocModelTwo = new SetVocModelTwo(this);
        }
        if (this.timeSetTwo == null) {
            this.timeSetTwo = new TimeSetTwo(this);
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
        int[] icons = {C0899R.mipmap.id7_icon_system, C0899R.mipmap.id7_icon_navi, C0899R.mipmap.id7_icon_audio, C0899R.mipmap.id7_icon_eq, C0899R.mipmap.id7_icon_language, C0899R.mipmap.id7_icon_time, C0899R.mipmap.id7_icon_info, C0899R.mipmap.id7_icon_android, C0899R.mipmap.id7_icon_factory};
        this.data = new ArrayList();
        String[] functions = getResources().getStringArray(C0899R.array.set_function);
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
        FunctionAdapter functionAdapter = new FunctionAdapter(this, this.data);
        this.adapter = functionAdapter;
        this.recyclerView.setAdapter(functionAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, 1);
        divider.setDrawable(ContextCompat.getDrawable(this, C0899R.mipmap.id7_lp_line));
        this.recyclerView.addItemDecoration(divider);
        this.adapter.registOnFunctionClickListener(new FunctionAdapter.OnFunctionClickListener() { // from class: com.wits.ksw.settings.id7.ID7SettingsActivity.2
            @Override // com.wits.ksw.settings.id7.adapter.FunctionAdapter.OnFunctionClickListener
            public void functonClick(int pos) {
                ID7SettingsActivity.this.setOneLayout(pos);
                for (FunctionBean fb : ID7SettingsActivity.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) ID7SettingsActivity.this.data.get(pos)).setIscheck(true);
                ID7SettingsActivity.this.adapter.notifyDataSetChanged();
            }
        });
        this.frame_OneLayout = (FrameLayout) findViewById(C0899R.C0901id.frame_OneLayout);
        this.frame_TwoLayout = (FrameLayout) findViewById(C0899R.C0901id.frame_TwoLayout);
        if (this.setSystemLayout == null) {
            SetSystemLayout setSystemLayout = new SetSystemLayout(this);
            this.setSystemLayout = setSystemLayout;
            setSystemLayout.registIUpdateTwoLayout(this);
        }
        this.frame_OneLayout.addView(this.setSystemLayout);
        if (this.setSystemTwo == null) {
            this.setSystemTwo = new SetSystemTwo(this);
        }
        this.frame_TwoLayout.addView(this.setSystemTwo);
        ImageView imageView = (ImageView) findViewById(C0899R.C0901id.Img_SetBack);
        this.Img_SetBack = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.id7.ID7SettingsActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ID7SettingsActivity.this.onBackPressed();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOneLayout(int type) {
        this.frame_OneLayout.removeAllViews();
        this.frame_TwoLayout.removeAllViews();
        switch (type) {
            case 0:
                if (this.setSystemLayout == null) {
                    SetSystemLayout setSystemLayout = new SetSystemLayout(this);
                    this.setSystemLayout = setSystemLayout;
                    setSystemLayout.registIUpdateTwoLayout(this);
                }
                if (this.setSystemTwo == null) {
                    this.setSystemTwo = new SetSystemTwo(this);
                }
                this.frame_OneLayout.addView(this.setSystemLayout);
                this.setSystemLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.setSystemTwo);
                break;
            case 1:
                if (this.setNaviLayout == null) {
                    SetNaviLayout setNaviLayout = new SetNaviLayout(this);
                    this.setNaviLayout = setNaviLayout;
                    setNaviLayout.registIUpdateTwoLayout(this);
                }
                if (this.naviTwo == null) {
                    this.naviTwo = new NaviTwo(this);
                }
                this.frame_OneLayout.addView(this.setNaviLayout);
                this.setNaviLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.naviTwo);
                break;
            case 2:
                if (this.setVoiceLayout == null) {
                    this.setVoiceLayout = new SetVoiceLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new SetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.setVoiceLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 3:
                if (this.setVocModeLayout == null) {
                    SetVocModeLayout setVocModeLayout = new SetVocModeLayout(this);
                    this.setVocModeLayout = setVocModeLayout;
                    setVocModeLayout.registIUpdateTwoLayout(this);
                }
                if (this.setVocModelTwo == null) {
                    this.setVocModelTwo = new SetVocModelTwo(this);
                }
                this.frame_OneLayout.addView(this.setVocModeLayout);
                this.frame_TwoLayout.addView(this.setVocModelTwo);
                break;
            case 4:
                if (this.setLanguageLayout == null) {
                    this.setLanguageLayout = new SetLanguageLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new SetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.setLanguageLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 5:
                if (this.setTimeLayout == null) {
                    SetTimeLayout setTimeLayout = new SetTimeLayout(this);
                    this.setTimeLayout = setTimeLayout;
                    setTimeLayout.registIUpdateTwoLayout(this);
                }
                if (this.timeSetTwo == null) {
                    this.timeSetTwo = new TimeSetTwo(this);
                }
                this.frame_OneLayout.addView(this.setTimeLayout);
                this.setTimeLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.timeSetTwo);
                break;
            case 6:
                if (this.setSystemInfoLayout == null) {
                    this.setSystemInfoLayout = new SetSystemInfoLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new SetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.setSystemInfoLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 7:
                if (this.setToAndSysLayout == null) {
                    this.setToAndSysLayout = new SetToAndSysLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new SetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.setToAndSysLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                sendToApp("com.android.settings", "com.android.settings.Settings");
                break;
            case 8:
                if (this.setFactoryLayout == null) {
                    this.setFactoryLayout = new SetFactoryLayout(this, this.handler);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new SetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.setFactoryLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
        }
        this.frame_OneLayout.requestFocus();
    }

    @Override // com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout
    public void updateTwoLayout(int type, int shwoIndex) {
        switch (type) {
            case 1:
                this.setSystemTwo.showLayout(shwoIndex);
                this.setSystemTwo.invalidate();
                this.setSystemTwo.requestFocus();
                return;
            case 2:
                this.naviTwo.showLayout(shwoIndex);
                this.naviTwo.invalidate();
                this.naviTwo.requestFocus();
                return;
            case 3:
                this.setVocModelTwo.showLayout(shwoIndex);
                this.setVocModelTwo.invalidate();
                this.setVocModelTwo.requestFocus();
                return;
            case 4:
                this.timeSetTwo.showLayout(shwoIndex);
                this.timeSetTwo.invalidate();
                this.timeSetTwo.requestFocus();
                return;
            default:
                return;
        }
    }

    @Override // com.wits.ksw.settings.utlis_view.ScanNaviList.OnMapListScanListener
    public void onScanFinish(List<MapBean> mapList) {
        Log.d("Navi", "onScanFinish " + mapList.size());
        this.mapList = mapList;
        this.handler.sendEmptyMessage(3);
    }
}
