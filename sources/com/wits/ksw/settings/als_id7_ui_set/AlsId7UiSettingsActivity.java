package com.wits.ksw.settings.als_id7_ui_set;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.SkinAppCompatDelegateImpl;
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
import com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiFunctionAdapter;
import com.wits.ksw.settings.als_id7_ui_set.interfaces.AlsID7UiIUpdateTwoLayout;
import com.wits.ksw.settings.als_id7_ui_set.layout_one.AlsID7UiSetFactoryLayout;
import com.wits.ksw.settings.als_id7_ui_set.layout_one.AlsID7UiSetLanguageLayout;
import com.wits.ksw.settings.als_id7_ui_set.layout_one.AlsID7UiSetNaviLayout;
import com.wits.ksw.settings.als_id7_ui_set.layout_one.AlsID7UiSetSystemInfoLayout;
import com.wits.ksw.settings.als_id7_ui_set.layout_one.AlsID7UiSetSystemLayout;
import com.wits.ksw.settings.als_id7_ui_set.layout_one.AlsID7UiSetTimeLayout;
import com.wits.ksw.settings.als_id7_ui_set.layout_one.AlsID7UiSetToAndSysLayout;
import com.wits.ksw.settings.als_id7_ui_set.layout_one.AlsID7UiSetVocModeLayout;
import com.wits.ksw.settings.als_id7_ui_set.layout_one.AlsID7UiSetVoiceLayout;
import com.wits.ksw.settings.als_id7_ui_set.layout_two.AlsID7UiNaviTwo;
import com.wits.ksw.settings.als_id7_ui_set.layout_two.AlsID7UiSetImageTwo;
import com.wits.ksw.settings.als_id7_ui_set.layout_two.AlsID7UiSetSystemTwo;
import com.wits.ksw.settings.als_id7_ui_set.layout_two.AlsID7UiSetVocModelTwo;
import com.wits.ksw.settings.als_id7_ui_set.layout_two.AlsID7UiTimeSetTwo;
import com.wits.ksw.settings.id7.FactoryActivity;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

public class AlsId7UiSettingsActivity extends BaseActivity implements AlsID7UiIUpdateTwoLayout, ScanNaviList.OnMapListScanListener {
    private ImageView Img_SetBack;
    /* access modifiers changed from: private */
    public AlsID7UiFunctionAdapter adapter;
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    /* access modifiers changed from: private */
    public String defPwd = "1314";
    private boolean first = true;
    private FrameLayout frame_OneLayout;
    private FrameLayout frame_TwoLayout;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    AlsId7UiSettingsActivity.this.initOneLayout();
                    AlsId7UiSettingsActivity.this.initTwoLayout();
                    return;
                case 2:
                    if (TextUtils.equals(AlsId7UiSettingsActivity.this.defPwd, (String) msg.obj)) {
                        AlsId7UiSettingsActivity.this.startActivity(new Intent(AlsId7UiSettingsActivity.this, FactoryActivity.class));
                        AlsId7UiSettingsActivity.this.finish();
                        return;
                    }
                    AlsId7UiSettingsActivity.this.setFactoryLayout.SetTextEEro();
                    return;
                case 3:
                    if (AlsId7UiSettingsActivity.this.naviTwo != null) {
                        Log.d("Navi", "updateList: " + AlsId7UiSettingsActivity.this.mapList.size());
                        AlsId7UiSettingsActivity.this.naviTwo.updateMapList(AlsId7UiSettingsActivity.this.mapList);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private LinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public List<MapBean> mapList = new ArrayList();
    /* access modifiers changed from: private */
    public AlsID7UiNaviTwo naviTwo;
    private RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public AlsID7UiSetFactoryLayout setFactoryLayout;
    private AlsID7UiSetImageTwo setImageTwo;
    private AlsID7UiSetLanguageLayout setLanguageLayout;
    private AlsID7UiSetNaviLayout setNaviLayout;
    private AlsID7UiSetSystemInfoLayout setSystemInfoLayout;
    private AlsID7UiSetSystemLayout setSystemLayout;
    private AlsID7UiSetSystemTwo setSystemTwo;
    private AlsID7UiSetTimeLayout setTimeLayout;
    private AlsID7UiSetToAndSysLayout setToAndSysLayout;
    private AlsID7UiSetVocModeLayout setVocModeLayout;
    private AlsID7UiSetVocModelTwo setVocModelTwo;
    private AlsID7UiSetVoiceLayout setVoiceLayout;
    private AlsID7UiTimeSetTwo timeSetTwo;
    private String voiceData;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_als_id7_ui_settings);
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
            AlsID7UiFunctionAdapter alsID7UiFunctionAdapter = this.adapter;
            if (alsID7UiFunctionAdapter != null) {
                alsID7UiFunctionAdapter.notifyDataSetChanged();
            }
        } else if (TextUtils.equals("voicFun", this.voiceData)) {
            initOneLayout();
            initTwoLayout();
            setOneLayout(3);
            for (FunctionBean fb2 : this.data) {
                fb2.setIscheck(false);
            }
            this.data.get(3).setIscheck(true);
            AlsID7UiFunctionAdapter alsID7UiFunctionAdapter2 = this.adapter;
            if (alsID7UiFunctionAdapter2 != null) {
                alsID7UiFunctionAdapter2.notifyDataSetChanged();
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
    public void initOneLayout() {
        if (this.setSystemInfoLayout == null) {
            AlsID7UiSetSystemLayout alsID7UiSetSystemLayout = new AlsID7UiSetSystemLayout(this);
            this.setSystemLayout = alsID7UiSetSystemLayout;
            alsID7UiSetSystemLayout.registIUpdateTwoLayout(this);
        }
        if (this.setNaviLayout == null) {
            AlsID7UiSetNaviLayout alsID7UiSetNaviLayout = new AlsID7UiSetNaviLayout(this);
            this.setNaviLayout = alsID7UiSetNaviLayout;
            alsID7UiSetNaviLayout.registIUpdateTwoLayout(this);
        }
        if (this.setVoiceLayout == null) {
            this.setVoiceLayout = new AlsID7UiSetVoiceLayout(this);
        }
        if (this.setVocModeLayout == null) {
            AlsID7UiSetVocModeLayout alsID7UiSetVocModeLayout = new AlsID7UiSetVocModeLayout(this);
            this.setVocModeLayout = alsID7UiSetVocModeLayout;
            alsID7UiSetVocModeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setLanguageLayout == null) {
            this.setLanguageLayout = new AlsID7UiSetLanguageLayout(this);
        }
        if (this.setToAndSysLayout == null) {
            this.setToAndSysLayout = new AlsID7UiSetToAndSysLayout(this);
        }
        if (this.setTimeLayout == null) {
            AlsID7UiSetTimeLayout alsID7UiSetTimeLayout = new AlsID7UiSetTimeLayout(this);
            this.setTimeLayout = alsID7UiSetTimeLayout;
            alsID7UiSetTimeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setSystemInfoLayout == null) {
            this.setSystemInfoLayout = new AlsID7UiSetSystemInfoLayout(this);
        }
        if (this.setFactoryLayout == null) {
            this.setFactoryLayout = new AlsID7UiSetFactoryLayout(this, this.handler);
        }
    }

    /* access modifiers changed from: private */
    public void initTwoLayout() {
        if (this.setSystemTwo == null) {
            this.setSystemTwo = new AlsID7UiSetSystemTwo(this);
        }
        if (this.naviTwo == null) {
            this.naviTwo = new AlsID7UiNaviTwo(this);
        }
        if (this.setImageTwo == null) {
            this.setImageTwo = new AlsID7UiSetImageTwo(this);
        }
        if (this.setVocModelTwo == null) {
            this.setVocModelTwo = new AlsID7UiSetVocModelTwo(this);
        }
        if (this.timeSetTwo == null) {
            this.timeSetTwo = new AlsID7UiTimeSetTwo(this);
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
        int[] icons = {R.drawable.als_sp_id7_icon_system, R.drawable.als_sp_id7_icon_navi, R.drawable.als_sp_id7_icon_audio, R.drawable.als_sp_id7_icon_eq, R.drawable.als_sp_id7_icon_language, R.drawable.als_sp_id7_icon_time, R.drawable.als_sp_id7_icon_info, R.drawable.als_sp_id7_icon_android, R.drawable.als_sp_id7_icon_factory};
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        AlsID7UiFunctionAdapter alsID7UiFunctionAdapter = new AlsID7UiFunctionAdapter(this, this.data);
        this.adapter = alsID7UiFunctionAdapter;
        this.recyclerView.setAdapter(alsID7UiFunctionAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, 1);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.als_sp_id7_lp_line));
        this.recyclerView.addItemDecoration(divider);
        this.adapter.registOnFunctionClickListener(new AlsID7UiFunctionAdapter.OnFunctionClickListener() {
            public void functonClick(int pos) {
                AlsId7UiSettingsActivity.this.setOneLayout(pos);
                for (FunctionBean fb : AlsId7UiSettingsActivity.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) AlsId7UiSettingsActivity.this.data.get(pos)).setIscheck(true);
                AlsId7UiSettingsActivity.this.adapter.notifyDataSetChanged();
            }
        });
        this.frame_OneLayout = (FrameLayout) findViewById(R.id.frame_OneLayout);
        this.frame_TwoLayout = (FrameLayout) findViewById(R.id.frame_TwoLayout);
        if (this.setSystemLayout == null) {
            AlsID7UiSetSystemLayout alsID7UiSetSystemLayout = new AlsID7UiSetSystemLayout(this);
            this.setSystemLayout = alsID7UiSetSystemLayout;
            alsID7UiSetSystemLayout.registIUpdateTwoLayout(this);
        }
        this.frame_OneLayout.addView(this.setSystemLayout);
        if (this.setSystemTwo == null) {
            this.setSystemTwo = new AlsID7UiSetSystemTwo(this);
        }
        this.frame_TwoLayout.addView(this.setSystemTwo);
        ImageView imageView = (ImageView) findViewById(R.id.Img_SetBack);
        this.Img_SetBack = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlsId7UiSettingsActivity.this.onBackPressed();
            }
        });
    }

    /* access modifiers changed from: private */
    public void setOneLayout(int type) {
        this.frame_OneLayout.removeAllViews();
        this.frame_TwoLayout.removeAllViews();
        switch (type) {
            case 0:
                if (this.setSystemLayout == null) {
                    AlsID7UiSetSystemLayout alsID7UiSetSystemLayout = new AlsID7UiSetSystemLayout(this);
                    this.setSystemLayout = alsID7UiSetSystemLayout;
                    alsID7UiSetSystemLayout.registIUpdateTwoLayout(this);
                }
                if (this.setSystemTwo == null) {
                    this.setSystemTwo = new AlsID7UiSetSystemTwo(this);
                }
                this.frame_OneLayout.addView(this.setSystemLayout);
                this.setSystemLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.setSystemTwo);
                break;
            case 1:
                if (this.setNaviLayout == null) {
                    AlsID7UiSetNaviLayout alsID7UiSetNaviLayout = new AlsID7UiSetNaviLayout(this);
                    this.setNaviLayout = alsID7UiSetNaviLayout;
                    alsID7UiSetNaviLayout.registIUpdateTwoLayout(this);
                }
                if (this.naviTwo == null) {
                    this.naviTwo = new AlsID7UiNaviTwo(this);
                }
                this.frame_OneLayout.addView(this.setNaviLayout);
                this.setNaviLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.naviTwo);
                break;
            case 2:
                if (this.setVoiceLayout == null) {
                    this.setVoiceLayout = new AlsID7UiSetVoiceLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new AlsID7UiSetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.setVoiceLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 3:
                if (this.setVocModeLayout == null) {
                    AlsID7UiSetVocModeLayout alsID7UiSetVocModeLayout = new AlsID7UiSetVocModeLayout(this);
                    this.setVocModeLayout = alsID7UiSetVocModeLayout;
                    alsID7UiSetVocModeLayout.registIUpdateTwoLayout(this);
                }
                if (this.setVocModelTwo == null) {
                    this.setVocModelTwo = new AlsID7UiSetVocModelTwo(this);
                }
                this.frame_OneLayout.addView(this.setVocModeLayout);
                this.frame_TwoLayout.addView(this.setVocModelTwo);
                break;
            case 4:
                if (this.setLanguageLayout == null) {
                    this.setLanguageLayout = new AlsID7UiSetLanguageLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new AlsID7UiSetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.setLanguageLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 5:
                if (this.setTimeLayout == null) {
                    AlsID7UiSetTimeLayout alsID7UiSetTimeLayout = new AlsID7UiSetTimeLayout(this);
                    this.setTimeLayout = alsID7UiSetTimeLayout;
                    alsID7UiSetTimeLayout.registIUpdateTwoLayout(this);
                }
                if (this.timeSetTwo == null) {
                    this.timeSetTwo = new AlsID7UiTimeSetTwo(this);
                }
                this.frame_OneLayout.addView(this.setTimeLayout);
                this.setTimeLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.timeSetTwo);
                break;
            case 6:
                if (this.setSystemInfoLayout == null) {
                    this.setSystemInfoLayout = new AlsID7UiSetSystemInfoLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new AlsID7UiSetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.setSystemInfoLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 7:
                if (this.setToAndSysLayout == null) {
                    this.setToAndSysLayout = new AlsID7UiSetToAndSysLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new AlsID7UiSetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.setToAndSysLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                sendToApp("com.android.settings", "com.android.settings.Settings");
                break;
            case 8:
                if (this.setFactoryLayout == null) {
                    this.setFactoryLayout = new AlsID7UiSetFactoryLayout(this, this.handler);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new AlsID7UiSetImageTwo(this);
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

    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }
}
