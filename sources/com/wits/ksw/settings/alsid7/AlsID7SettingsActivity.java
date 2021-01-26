package com.wits.ksw.settings.alsid7;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.alsid7.adapter.AlsID7FunctionAdapter;
import com.wits.ksw.settings.alsid7.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.alsid7.layout_one.AlsID7SetFactoryLayout;
import com.wits.ksw.settings.alsid7.layout_one.AlsID7SetLanguageLayout;
import com.wits.ksw.settings.alsid7.layout_one.AlsID7SetNaviLayout;
import com.wits.ksw.settings.alsid7.layout_one.AlsID7SetSystemInfoLayout;
import com.wits.ksw.settings.alsid7.layout_one.AlsID7SetSystemLayout;
import com.wits.ksw.settings.alsid7.layout_one.AlsID7SetTimeLayout;
import com.wits.ksw.settings.alsid7.layout_one.AlsID7SetVocModeLayout;
import com.wits.ksw.settings.alsid7.layout_one.AlsID7SetVoiceLayout;
import com.wits.ksw.settings.alsid7.layout_two.AlsID7NaviTwo;
import com.wits.ksw.settings.alsid7.layout_two.AlsID7SetSystemTwo;
import com.wits.ksw.settings.alsid7.layout_two.AlsID7SetVocModelTwo;
import com.wits.ksw.settings.alsid7.layout_two.AlsID7TimeSetTwo;
import com.wits.ksw.settings.id7.FactoryActivity;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.id7.layout_one.SetToAndSysLayout;
import com.wits.ksw.settings.id7.layout_two.SetImageTwo;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

public class AlsID7SettingsActivity extends BaseActivity implements IUpdateTwoLayout, ScanNaviList.OnMapListScanListener {
    private ImageView Img_SetBack;
    /* access modifiers changed from: private */
    public AlsID7FunctionAdapter adapter;
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
                        if (TextUtils.equals(AlsID7SettingsActivity.this.defPwd, (String) msg.obj)) {
                            AlsID7SettingsActivity.this.startActivity(new Intent(AlsID7SettingsActivity.this, FactoryActivity.class));
                            AlsID7SettingsActivity.this.finish();
                            return;
                        }
                        AlsID7SettingsActivity.this.setFactoryLayout.SetTextEEro();
                        return;
                    case 3:
                        if (AlsID7SettingsActivity.this.naviTwo != null) {
                            Log.d("Navi", "updateList: " + AlsID7SettingsActivity.this.mapList.size());
                            AlsID7SettingsActivity.this.naviTwo.updateMapList(AlsID7SettingsActivity.this.mapList);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else {
                AlsID7SettingsActivity.this.initOneLayout();
                AlsID7SettingsActivity.this.initTwoLayout();
            }
        }
    };
    private LinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public List<MapBean> mapList = new ArrayList();
    /* access modifiers changed from: private */
    public AlsID7NaviTwo naviTwo;
    private RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public AlsID7SetFactoryLayout setFactoryLayout;
    private SetImageTwo setImageTwo;
    private AlsID7SetLanguageLayout setLanguageLayout;
    private AlsID7SetNaviLayout setNaviLayout;
    private AlsID7SetSystemInfoLayout setSystemInfoLayout;
    private AlsID7SetSystemLayout setSystemLayout;
    private AlsID7SetSystemTwo setSystemTwo;
    private AlsID7SetTimeLayout setTimeLayout;
    private SetToAndSysLayout setToAndSysLayout;
    private AlsID7SetVocModeLayout setVocModeLayout;
    private AlsID7SetVocModelTwo setVocModelTwo;
    private AlsID7SetVoiceLayout setVoiceLayout;
    private AlsID7TimeSetTwo timeSetTwo;
    private String voiceData;

    /* access modifiers changed from: protected */
    @RequiresApi(api = 24)
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_als_id7_settings);
        initData();
        initView();
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
        ScanNaviList.getInstance().setMapListScanListener(this);
        this.handler.sendEmptyMessageDelayed(0, 1000);
        initSaveData();
        skipItem();
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
            this.setSystemLayout = new AlsID7SetSystemLayout(this);
            this.setSystemLayout.registIUpdateTwoLayout(this);
        }
        if (this.setNaviLayout == null) {
            this.setNaviLayout = new AlsID7SetNaviLayout(this);
            this.setNaviLayout.registIUpdateTwoLayout(this);
        }
        if (this.setVoiceLayout == null) {
            this.setVoiceLayout = new AlsID7SetVoiceLayout(this);
        }
        if (this.setVocModeLayout == null) {
            this.setVocModeLayout = new AlsID7SetVocModeLayout(this);
            this.setVocModeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setLanguageLayout == null) {
            this.setLanguageLayout = new AlsID7SetLanguageLayout(this);
        }
        if (this.setToAndSysLayout == null) {
            this.setToAndSysLayout = new SetToAndSysLayout(this);
        }
        if (this.setTimeLayout == null) {
            this.setTimeLayout = new AlsID7SetTimeLayout(this);
            this.setTimeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setSystemInfoLayout == null) {
            this.setSystemInfoLayout = new AlsID7SetSystemInfoLayout(this);
        }
        if (this.setFactoryLayout == null) {
            this.setFactoryLayout = new AlsID7SetFactoryLayout(this, this.handler);
        }
    }

    /* access modifiers changed from: private */
    public void initTwoLayout() {
        if (this.setSystemTwo == null) {
            this.setSystemTwo = new AlsID7SetSystemTwo(this);
        }
        if (this.naviTwo == null) {
            this.naviTwo = new AlsID7NaviTwo(this);
        }
        if (this.setImageTwo == null) {
            this.setImageTwo = new SetImageTwo(this);
        }
        if (this.setVocModelTwo == null) {
            this.setVocModelTwo = new AlsID7SetVocModelTwo(this);
        }
        if (this.timeSetTwo == null) {
            this.timeSetTwo = new AlsID7TimeSetTwo(this);
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
        int[] icons = {R.mipmap.als_id7_icon_system, R.mipmap.als_id7_icon_navi, R.mipmap.als_id7_icon_audio, R.mipmap.als_id7_icon_eq, R.mipmap.als_id7_icon_language, R.mipmap.als_id7_icon_time, R.mipmap.als_id7_icon_info, R.mipmap.als_id7_icon_android, R.mipmap.als_id7_icon_factory};
        this.data = new ArrayList();
        String[] functions = getResources().getStringArray(R.array.set_function);
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
        this.adapter = new AlsID7FunctionAdapter(this, this.data);
        this.recyclerView.setAdapter(this.adapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, 1);
        divider.setDrawable(ContextCompat.getDrawable(this, R.mipmap.als_id7_lp_line));
        this.recyclerView.addItemDecoration(divider);
        this.adapter.registOnFunctionClickListener(new AlsID7FunctionAdapter.OnFunctionClickListener() {
            @RequiresApi(api = 24)
            public void functonClick(int pos) {
                AlsID7SettingsActivity.this.setOneLayout(pos);
                for (FunctionBean fb : AlsID7SettingsActivity.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) AlsID7SettingsActivity.this.data.get(pos)).setIscheck(true);
                AlsID7SettingsActivity.this.adapter.notifyDataSetChanged();
            }
        });
        this.frame_OneLayout = (FrameLayout) findViewById(R.id.frame_OneLayout);
        this.frame_TwoLayout = (FrameLayout) findViewById(R.id.frame_TwoLayout);
        if (this.setSystemLayout == null) {
            this.setSystemLayout = new AlsID7SetSystemLayout(this);
            this.setSystemLayout.registIUpdateTwoLayout(this);
        }
        this.frame_OneLayout.addView(this.setSystemLayout);
        if (this.setSystemTwo == null) {
            this.setSystemTwo = new AlsID7SetSystemTwo(this);
        }
        this.frame_TwoLayout.addView(this.setSystemTwo);
        this.Img_SetBack = (ImageView) findViewById(R.id.Img_SetBack);
        this.Img_SetBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlsID7SettingsActivity.this.onBackPressed();
            }
        });
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 24)
    public void setOneLayout(int type) {
        this.frame_OneLayout.removeAllViews();
        this.frame_TwoLayout.removeAllViews();
        switch (type) {
            case 0:
                if (this.setSystemLayout == null) {
                    this.setSystemLayout = new AlsID7SetSystemLayout(this);
                    this.setSystemLayout.registIUpdateTwoLayout(this);
                }
                if (this.setSystemTwo == null) {
                    this.setSystemTwo = new AlsID7SetSystemTwo(this);
                }
                this.frame_OneLayout.addView(this.setSystemLayout);
                this.setSystemLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.setSystemTwo);
                break;
            case 1:
                if (this.setNaviLayout == null) {
                    this.setNaviLayout = new AlsID7SetNaviLayout(this);
                    this.setNaviLayout.registIUpdateTwoLayout(this);
                }
                if (this.naviTwo == null) {
                    this.naviTwo = new AlsID7NaviTwo(this);
                }
                this.frame_OneLayout.addView(this.setNaviLayout);
                this.setNaviLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.naviTwo);
                break;
            case 2:
                if (this.setVoiceLayout == null) {
                    this.setVoiceLayout = new AlsID7SetVoiceLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new SetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.setVoiceLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 3:
                if (this.setVocModeLayout == null) {
                    this.setVocModeLayout = new AlsID7SetVocModeLayout(this);
                    this.setVocModeLayout.registIUpdateTwoLayout(this);
                }
                if (this.setVocModelTwo == null) {
                    this.setVocModelTwo = new AlsID7SetVocModelTwo(this);
                }
                this.frame_OneLayout.addView(this.setVocModeLayout);
                this.frame_TwoLayout.addView(this.setVocModelTwo);
                break;
            case 4:
                if (this.setLanguageLayout == null) {
                    this.setLanguageLayout = new AlsID7SetLanguageLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new SetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.setLanguageLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 5:
                if (this.setTimeLayout == null) {
                    this.setTimeLayout = new AlsID7SetTimeLayout(this);
                    this.setTimeLayout.registIUpdateTwoLayout(this);
                }
                if (this.timeSetTwo == null) {
                    this.timeSetTwo = new AlsID7TimeSetTwo(this);
                }
                this.frame_OneLayout.addView(this.setTimeLayout);
                this.setTimeLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.timeSetTwo);
                break;
            case 6:
                if (this.setSystemInfoLayout == null) {
                    this.setSystemInfoLayout = new AlsID7SetSystemInfoLayout(this);
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
                    this.setFactoryLayout = new AlsID7SetFactoryLayout(this, this.handler);
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

    public void onScanFinish(List<MapBean> mapList2) {
        Log.d("Navi", "onScanFinish " + mapList2.size());
        this.mapList = mapList2;
        this.handler.sendEmptyMessage(3);
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
