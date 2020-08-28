package com.wits.ksw.settings.id6;

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
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.id6.adapter.ID6FunctionAdapter;
import com.wits.ksw.settings.id6.oneLayout.ID6FactoryLayout;
import com.wits.ksw.settings.id6.oneLayout.ID6LanguageLayout;
import com.wits.ksw.settings.id6.oneLayout.ID6NaviLayout;
import com.wits.ksw.settings.id6.oneLayout.ID6SetSystemLayout;
import com.wits.ksw.settings.id6.oneLayout.ID6SetVoiceLayout;
import com.wits.ksw.settings.id6.oneLayout.ID6SystemInfoLayout;
import com.wits.ksw.settings.id6.oneLayout.ID6TimeLayout;
import com.wits.ksw.settings.id6.oneLayout.ID6VoiceModel;
import com.wits.ksw.settings.id6.showLayout.ID6ShowLanguageLayout;
import com.wits.ksw.settings.id6.showLayout.ID6ShowNavi;
import com.wits.ksw.settings.id6.showLayout.ID6ShowSysInfoLayout;
import com.wits.ksw.settings.id6.showLayout.ID6ShowSystemSet;
import com.wits.ksw.settings.id6.showLayout.ID6ShowText;
import com.wits.ksw.settings.id6.showLayout.ID6ShowTimeLayout;
import com.wits.ksw.settings.id6.showLayout.ID6ShowVoiceModel;
import com.wits.ksw.settings.id6.showLayout.ID6ShowVoiceSet;
import com.wits.ksw.settings.id6.twolayout.ID6SetSystemTwo;
import com.wits.ksw.settings.id7.FactoryActivity;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

public class ID6SettingsActivity extends BaseActivity implements IUpdateTwoLayout, ScanNaviList.OnMapListScanListener {
    private ID6FunctionAdapter adapter;
    private List<FunctionBean> data;
    /* access modifiers changed from: private */
    public String defPwd = "1314";
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    if (TextUtils.equals(ID6SettingsActivity.this.defPwd, (String) msg.obj)) {
                        ID6SettingsActivity.this.startActivity(new Intent(ID6SettingsActivity.this, FactoryActivity.class));
                        ID6SettingsActivity.this.finish();
                        return;
                    }
                    ID6SettingsActivity.this.id6FactoryLayout.SetTextEEro();
                    return;
                case 3:
                    if (ID6SettingsActivity.this.id6NaviLayout != null) {
                        ID6SettingsActivity.this.id6NaviLayout.updateList(ID6SettingsActivity.this.mapList);
                    }
                    if (ID6SettingsActivity.this.id6ShowNavi != null) {
                        ID6SettingsActivity.this.id6ShowNavi.updateList(ID6SettingsActivity.this.mapList);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    Handler handlerUI = new Handler() {
        @RequiresApi(api = 24)
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ID6SettingsActivity.this.initOneLayout();
            ID6SettingsActivity.this.initShowLayout();
            ID6SettingsActivity.this.initTwoLayout();
        }
    };
    /* access modifiers changed from: private */
    public ID6FactoryLayout id6FactoryLayout;
    private ID6LanguageLayout id6LanguageLayout;
    /* access modifiers changed from: private */
    public ID6NaviLayout id6NaviLayout;
    private ID6SetSystemLayout id6SetSystemLayout;
    private ID6SetSystemTwo id6SetSystemTwo;
    private ID6SetVoiceLayout id6SetVoiceLayout;
    private ID6ShowLanguageLayout id6ShowLanguageLayout;
    /* access modifiers changed from: private */
    public ID6ShowNavi id6ShowNavi;
    private ID6ShowSysInfoLayout id6ShowSysInfoLayout;
    private ID6ShowSystemSet id6ShowSystemSet;
    private ID6ShowTimeLayout id6ShowTimeLayout;
    private ID6ShowVoiceModel id6ShowVoiceModel;
    private ID6ShowVoiceSet id6ShowVoiceSet;
    private ID6SystemInfoLayout id6SystemInfoLayout;
    private ID6TimeLayout id6TimeLayout;
    private ID6VoiceModel id6VoiceModel;
    private ImageView img_Back;
    private ImageView img_SetIcon;
    private boolean isCheckThree = false;
    private boolean isCheckTwo = false;
    private LinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public List<MapBean> mapList;
    private RecyclerView recyclerView;
    private RelativeLayout relat_One;
    private RelativeLayout relat_Show;
    private RelativeLayout relat_Two;
    private TextView tv_id6SetTitle;
    private String voiceData;

    /* access modifiers changed from: protected */
    @RequiresApi(api = 24)
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_id6_settings);
        initData();
        this.handlerUI.sendEmptyMessageDelayed(0, 1000);
        initView();
        if (TextUtils.equals("voic", this.voiceData)) {
            funCheckLayout(2);
            for (FunctionBean fb : this.data) {
                fb.setIscheck(false);
            }
            this.data.get(2).setIscheck(true);
        } else if (TextUtils.equals("voicFun", this.voiceData)) {
            funCheckLayout(3);
            for (FunctionBean fb2 : this.data) {
                fb2.setIscheck(false);
            }
            this.data.get(3).setIscheck(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.voiceData = intent.getStringExtra("voiceData");
        Log.d("id6startAction", "onNewIntent:" + this.voiceData);
        if (TextUtils.equals("voic", this.voiceData)) {
            funCheckLayout(2);
            for (FunctionBean fb : this.data) {
                fb.setIscheck(false);
            }
            this.data.get(2).setIscheck(true);
        }
    }

    private void initData() {
        this.voiceData = getIntent().getStringExtra("voiceData");
        Log.d("id6startAction", "===data====:" + this.voiceData);
        this.data = new ArrayList();
        String[] functions = getResources().getStringArray(R.array.set_id6_function);
        for (int i = 0; i < functions.length; i++) {
            FunctionBean fcb = new FunctionBean();
            fcb.setTitle(functions[i]);
            this.data.add(fcb);
        }
        this.data.get(0).setIscheck(true);
        try {
            this.defPwd = PowerManagerApp.getSettingsString(KeyConfig.PASSWORD);
            Log.d("id6FactoryPwd", "===pwd====:" + this.defPwd);
            if (TextUtils.isEmpty(this.defPwd)) {
                this.defPwd = "1314";
            }
        } catch (Exception e) {
            this.defPwd = "1314";
        }
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 24)
    public void initShowLayout() {
        if (this.id6ShowSystemSet == null) {
            this.id6ShowSystemSet = new ID6ShowSystemSet(this);
        }
        if (this.id6ShowVoiceSet == null) {
            this.id6ShowVoiceSet = new ID6ShowVoiceSet(this);
        }
        if (this.id6ShowVoiceModel == null) {
            this.id6ShowVoiceModel = new ID6ShowVoiceModel(this);
        }
        if (this.id6ShowLanguageLayout == null) {
            this.id6ShowLanguageLayout = new ID6ShowLanguageLayout(this);
        }
        if (this.id6ShowSysInfoLayout == null) {
            this.id6ShowSysInfoLayout = new ID6ShowSysInfoLayout(this);
        }
    }

    /* access modifiers changed from: private */
    public void initTwoLayout() {
        if (this.id6SetSystemTwo == null) {
            this.id6SetSystemTwo = new ID6SetSystemTwo(this);
        }
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 24)
    public void initOneLayout() {
        if (this.id6NaviLayout == null) {
            this.id6NaviLayout = new ID6NaviLayout(this, ScanNaviList.getInstance().getMapList());
        }
        if (this.id6SetSystemLayout == null) {
            this.id6SetSystemLayout = new ID6SetSystemLayout(this);
        }
        if (this.id6SetSystemLayout != null) {
            this.id6SetSystemLayout.registIUpdateTwoLayout(this);
        }
        if (this.id6SetVoiceLayout == null) {
            this.id6SetVoiceLayout = new ID6SetVoiceLayout(this);
        }
        if (this.id6VoiceModel == null) {
            this.id6VoiceModel = new ID6VoiceModel(this);
        }
        if (this.id6LanguageLayout == null) {
            this.id6LanguageLayout = new ID6LanguageLayout(this);
        }
        if (this.id6SystemInfoLayout == null) {
            this.id6SystemInfoLayout = new ID6SystemInfoLayout(this);
        }
        if (this.id6FactoryLayout == null) {
            this.id6FactoryLayout = new ID6FactoryLayout(this, this.handler);
        }
    }

    private void initView() {
        this.relat_Show = (RelativeLayout) findViewById(R.id.relat_Show);
        this.relat_Two = (RelativeLayout) findViewById(R.id.relat_Two);
        this.tv_id6SetTitle = (TextView) findViewById(R.id.tv_id6SetTitle);
        this.relat_Show.removeAllViews();
        if (this.id6ShowNavi == null) {
            this.id6ShowNavi = new ID6ShowNavi(this, ScanNaviList.getInstance().getMapList());
        }
        this.relat_Show.addView(this.id6ShowNavi);
        this.img_SetIcon = (ImageView) findViewById(R.id.img_SetIcon);
        this.relat_One = (RelativeLayout) findViewById(R.id.relat_One);
        this.img_Back = (ImageView) findViewById(R.id.img_Back);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.layoutManager = new LinearLayoutManager(this);
        this.layoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.adapter = new ID6FunctionAdapter(this, this.data);
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.registOnFunctionClickListener(new ID6FunctionAdapter.OnFunctionClickListener() {
            @RequiresApi(api = 24)
            public void functonClick(int pos) {
                ID6SettingsActivity.this.funCheckLayout(pos);
            }

            @RequiresApi(api = 24)
            public void funSwitImage(int pos) {
                ID6SettingsActivity.this.fousceLayout(pos);
            }
        });
        this.img_Back.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = 24)
            public void onClick(View v) {
                ID6SettingsActivity.this.onBackPressed();
            }
        });
    }

    @RequiresApi(api = 24)
    private void updateView() {
        if (this.id6ShowSystemSet != null) {
            this.id6ShowSystemSet.updateView();
        }
        if (this.id6ShowVoiceSet != null) {
            this.id6ShowVoiceSet.updateView();
        }
        if (this.id6ShowVoiceModel != null) {
            this.id6ShowVoiceModel.updateView();
        }
        if (this.id6ShowLanguageLayout != null) {
            this.id6ShowLanguageLayout.updateView();
        }
        if (this.id6ShowNavi != null) {
            this.id6ShowNavi.onUpdateView();
        }
    }

    @RequiresApi(api = 24)
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == 1 && event.getKeyCode() == 21) {
            Log.d("id6DisPatch", "====send code 21 is back======");
            onBackPressed();
        }
        return super.dispatchKeyEvent(event);
    }

    @RequiresApi(api = 24)
    public void onBackPressed() {
        if (!this.isCheckTwo && !this.isCheckThree) {
            super.onBackPressed();
        } else if (this.isCheckThree) {
            checkViewTwo();
        } else if (this.isCheckTwo) {
            checkView();
        }
        updateView();
    }

    private void checkViewTwo() {
        if (!this.isCheckThree) {
            this.isCheckThree = true;
            this.relat_One.setVisibility(8);
            this.relat_Two.setVisibility(0);
            return;
        }
        this.isCheckThree = false;
        this.relat_One.setVisibility(0);
        this.relat_Two.setVisibility(8);
    }

    private void checkView() {
        if (!this.isCheckTwo) {
            this.isCheckTwo = true;
            this.relat_One.setVisibility(0);
            this.img_SetIcon.setVisibility(0);
            this.recyclerView.setVisibility(8);
            this.relat_Show.setVisibility(8);
            return;
        }
        this.isCheckTwo = false;
        this.relat_One.setVisibility(8);
        this.img_SetIcon.setVisibility(8);
        this.recyclerView.setVisibility(0);
        this.relat_Show.setVisibility(0);
        this.tv_id6SetTitle.setText(getString(R.string.ksw_id7_setting));
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 24)
    public void fousceLayout(int pos) {
        this.relat_Show.removeAllViews();
        switch (pos) {
            case 0:
                if (this.id6ShowNavi == null) {
                    this.id6ShowNavi = new ID6ShowNavi(this, ScanNaviList.getInstance().getMapList());
                }
                this.relat_Show.addView(this.id6ShowNavi);
                return;
            case 1:
                if (this.id6ShowSystemSet == null) {
                    this.id6ShowSystemSet = new ID6ShowSystemSet(this);
                }
                this.relat_Show.addView(this.id6ShowSystemSet);
                return;
            case 2:
                if (this.id6ShowVoiceSet == null) {
                    this.id6ShowVoiceSet = new ID6ShowVoiceSet(this);
                }
                this.relat_Show.addView(this.id6ShowVoiceSet);
                return;
            case 3:
                if (this.id6ShowVoiceModel == null) {
                    this.id6ShowVoiceModel = new ID6ShowVoiceModel(this);
                }
                this.relat_Show.addView(this.id6ShowVoiceModel);
                return;
            case 4:
                if (this.id6ShowLanguageLayout == null) {
                    this.id6ShowLanguageLayout = new ID6ShowLanguageLayout(this);
                }
                this.relat_Show.addView(this.id6ShowLanguageLayout);
                return;
            case 5:
                if (this.id6ShowTimeLayout == null) {
                    this.id6ShowTimeLayout = new ID6ShowTimeLayout(this);
                }
                this.relat_Show.addView(this.id6ShowTimeLayout);
                return;
            case 6:
                if (this.id6ShowSysInfoLayout == null) {
                    this.id6ShowSysInfoLayout = new ID6ShowSysInfoLayout(this);
                }
                this.relat_Show.addView(this.id6ShowSysInfoLayout);
                return;
            case 7:
                this.relat_Show.addView(new ID6ShowText(this, 0));
                return;
            case 8:
                this.relat_Show.addView(new ID6ShowText(this, 1));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 24)
    public void funCheckLayout(int pos) {
        checkView();
        this.relat_One.removeAllViews();
        switch (pos) {
            case 0:
                if (this.id6NaviLayout == null) {
                    this.id6NaviLayout = new ID6NaviLayout(this, ScanNaviList.getInstance().getMapList());
                }
                this.relat_One.addView(this.id6NaviLayout);
                this.tv_id6SetTitle.setText(getString(R.string.item2));
                break;
            case 1:
                if (this.id6SetSystemLayout == null) {
                    this.id6SetSystemLayout = new ID6SetSystemLayout(this);
                }
                this.relat_One.addView(this.id6SetSystemLayout);
                this.tv_id6SetTitle.setText(getString(R.string.item1));
                break;
            case 2:
                if (this.id6SetVoiceLayout == null) {
                    this.id6SetVoiceLayout = new ID6SetVoiceLayout(this);
                }
                this.relat_One.addView(this.id6SetVoiceLayout);
                this.tv_id6SetTitle.setText(getString(R.string.item3));
                break;
            case 3:
                if (this.id6VoiceModel == null) {
                    this.id6VoiceModel = new ID6VoiceModel(this);
                }
                this.relat_One.addView(this.id6VoiceModel);
                this.tv_id6SetTitle.setText(getString(R.string.item4));
                break;
            case 4:
                if (this.id6LanguageLayout == null) {
                    this.id6LanguageLayout = new ID6LanguageLayout(this);
                }
                this.relat_One.addView(this.id6LanguageLayout);
                this.tv_id6SetTitle.setText(getString(R.string.item5));
                break;
            case 5:
                if (this.id6TimeLayout == null) {
                    this.id6TimeLayout = new ID6TimeLayout(this);
                }
                this.relat_One.addView(this.id6TimeLayout);
                this.tv_id6SetTitle.setText(getString(R.string.item6));
                break;
            case 6:
                if (this.id6SystemInfoLayout == null) {
                    this.id6SystemInfoLayout = new ID6SystemInfoLayout(this);
                }
                this.relat_One.addView(this.id6SystemInfoLayout);
                this.tv_id6SetTitle.setText(getString(R.string.item7));
                break;
            case 7:
                sendToApp("com.android.settings", "com.android.settings.Settings");
                finish();
                break;
            case 8:
                if (this.id6FactoryLayout == null) {
                    this.id6FactoryLayout = new ID6FactoryLayout(this, this.handler);
                }
                this.relat_One.addView(this.id6FactoryLayout);
                this.tv_id6SetTitle.setText(getString(R.string.item9));
                break;
        }
        fousceLayout(pos);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.id6TimeLayout == null) {
            this.id6TimeLayout = new ID6TimeLayout(this);
        }
        this.id6TimeLayout.onUpdateView();
        if (this.id6ShowTimeLayout == null) {
            this.id6ShowTimeLayout = new ID6ShowTimeLayout(this);
        }
        this.id6ShowTimeLayout.onUpdateView();
        ScanNaviList.getInstance().setMapListScanListener(this);
        initSaveData();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        ScanNaviList.getInstance().setMapListScanListener((ScanNaviList.OnMapListScanListener) null);
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

    public void updateTwoLayout(int type, int shwoIndex) {
        checkViewTwo();
        this.relat_Two.removeAllViews();
        if (type == 1) {
            if (this.id6SetSystemTwo == null) {
                this.id6SetSystemTwo = new ID6SetSystemTwo(this);
            }
            this.relat_Two.addView(this.id6SetSystemTwo);
            this.id6SetSystemTwo.showLayout(shwoIndex);
        }
    }

    public void onScanFinish(List<MapBean> mapList2) {
        this.mapList = mapList2;
        this.handler.sendEmptyMessage(3);
        Log.d("Navi", " finish " + mapList2.size());
    }
}
