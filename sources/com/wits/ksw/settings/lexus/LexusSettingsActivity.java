package com.wits.ksw.settings.lexus;

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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.id7.FactoryActivity;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.lexus.adapter.FunctionAdapter;
import com.wits.ksw.settings.lexus.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.lexus.layout_one.LexusSetFactoryLayout;
import com.wits.ksw.settings.lexus.layout_one.LexusSetLanguageLayout;
import com.wits.ksw.settings.lexus.layout_one.LexusSetNaviLayout;
import com.wits.ksw.settings.lexus.layout_one.LexusSetSystemInfoLayout;
import com.wits.ksw.settings.lexus.layout_one.LexusSetSystemLayout;
import com.wits.ksw.settings.lexus.layout_one.LexusSetTimeLayout;
import com.wits.ksw.settings.lexus.layout_one.LexusSetToAndSysLayout;
import com.wits.ksw.settings.lexus.layout_one.LexusSetVocModeLayout;
import com.wits.ksw.settings.lexus.layout_one.LexusSetVoiceLayout;
import com.wits.ksw.settings.lexus.layout_two.LexusNaviTwo;
import com.wits.ksw.settings.lexus.layout_two.LexusSetImageTwo;
import com.wits.ksw.settings.lexus.layout_two.LexusSetSystemTwo;
import com.wits.ksw.settings.lexus.layout_two.LexusSetVocModelTwo;
import com.wits.ksw.settings.lexus.layout_two.LexusSetVoiceTwo;
import com.wits.ksw.settings.lexus.layout_two.LexusTimeSetTwo;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class LexusSettingsActivity extends BaseActivity implements IUpdateTwoLayout, ScanNaviList.OnMapListScanListener {
    private ImageView Img_SetBack;
    private FunctionAdapter adapter;
    private List<FunctionBean> data;
    private FrameLayout frame_OneLayout;
    private FrameLayout frame_TwoLayout;
    private LinearLayoutManager layoutManager;
    private LexusSetFactoryLayout lexusSetFactoryLayout;
    private LexusSetNaviLayout lexusSetNaviLayout;
    private TextView lexus_set_title;
    private LexusNaviTwo naviTwo;
    private RecyclerView recyclerView;
    private LexusSetImageTwo setImageTwo;
    private LexusSetLanguageLayout setLanguageLayout;
    private LexusSetSystemInfoLayout setSystemInfoLayout;
    private LexusSetSystemLayout setSystemLayout;
    private LexusSetSystemTwo setSystemTwo;
    private LexusSetTimeLayout setTimeLayout;
    private LexusSetToAndSysLayout setToAndSysLayout;
    private LexusSetVocModeLayout setVocModeLayout;
    private LexusSetVocModelTwo setVocModelTwo;
    private LexusSetVoiceLayout setVoiceLayout;
    private LexusSetVoiceTwo setVoiceTwo;
    private LexusTimeSetTwo timeSetTwo;
    private String voiceData;
    private String defPwd = "1314";
    private String TAG = "LexusSettingsActivity";
    private boolean first = true;
    String screenFile = "";
    private Handler screenHandler = new Handler() { // from class: com.wits.ksw.settings.lexus.LexusSettingsActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 666:
                    LexusSettingsActivity lexusSettingsActivity = LexusSettingsActivity.this;
                    lexusSettingsActivity.screenFile = MainActivity.screenShotByShell(lexusSettingsActivity);
                    Log.d("liuhao SettingsActivity", LexusSettingsActivity.this.screenFile);
                    return;
                default:
                    return;
            }
        }
    };
    Handler handler = new Handler() { // from class: com.wits.ksw.settings.lexus.LexusSettingsActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    LexusSettingsActivity.this.initOneLayout();
                    LexusSettingsActivity.this.initTwoLayout();
                    return;
                case 1:
                default:
                    return;
                case 2:
                    String inputPwd = (String) msg.obj;
                    if (!TextUtils.equals(LexusSettingsActivity.this.defPwd, inputPwd)) {
                        LexusSettingsActivity.this.lexusSetFactoryLayout.SetTextEEro();
                        return;
                    }
                    LexusSettingsActivity.this.startActivity(new Intent(LexusSettingsActivity.this, FactoryActivity.class));
                    LexusSettingsActivity.this.finish();
                    return;
                case 3:
                    if (LexusSettingsActivity.this.naviTwo != null) {
                        Log.d("Navi", "updateList: " + LexusSettingsActivity.this.mapList.size());
                        LexusSettingsActivity.this.naviTwo.updateMapList(LexusSettingsActivity.this.mapList);
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
        setContentView(C0899R.C0902layout.activity_lexus_settings);
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
        if (UiThemeUtils.isLEXUS_LS_UI(this) || UiThemeUtils.isLEXUS_LS_UI_V2(this)) {
            this.screenHandler.sendEmptyMessageDelayed(666, 1800L);
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.screenHandler.removeMessages(666);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        ScanNaviList.getInstance().setMapListScanListener(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOneLayout() {
        if (this.setSystemInfoLayout == null) {
            LexusSetSystemLayout lexusSetSystemLayout = new LexusSetSystemLayout(this);
            this.setSystemLayout = lexusSetSystemLayout;
            lexusSetSystemLayout.registIUpdateTwoLayout(this);
        }
        if (this.lexusSetNaviLayout == null) {
            LexusSetNaviLayout lexusSetNaviLayout = new LexusSetNaviLayout(this);
            this.lexusSetNaviLayout = lexusSetNaviLayout;
            lexusSetNaviLayout.registIUpdateTwoLayout(this);
        }
        if (this.setVoiceLayout == null) {
            LexusSetVoiceLayout lexusSetVoiceLayout = new LexusSetVoiceLayout(this);
            this.setVoiceLayout = lexusSetVoiceLayout;
            lexusSetVoiceLayout.registIUpdateTwoLayout(this);
        }
        if (this.setVocModeLayout == null) {
            LexusSetVocModeLayout lexusSetVocModeLayout = new LexusSetVocModeLayout(this);
            this.setVocModeLayout = lexusSetVocModeLayout;
            lexusSetVocModeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setLanguageLayout == null) {
            this.setLanguageLayout = new LexusSetLanguageLayout(this);
        }
        if (this.setToAndSysLayout == null) {
            this.setToAndSysLayout = new LexusSetToAndSysLayout(this);
        }
        if (this.setTimeLayout == null) {
            LexusSetTimeLayout lexusSetTimeLayout = new LexusSetTimeLayout(this);
            this.setTimeLayout = lexusSetTimeLayout;
            lexusSetTimeLayout.registIUpdateTwoLayout(this);
        }
        if (this.setSystemInfoLayout == null) {
            this.setSystemInfoLayout = new LexusSetSystemInfoLayout(this);
        }
        if (this.lexusSetFactoryLayout == null) {
            this.lexusSetFactoryLayout = new LexusSetFactoryLayout(this, this.handler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initTwoLayout() {
        if (this.setSystemTwo == null) {
            this.setSystemTwo = new LexusSetSystemTwo(this);
        }
        if (this.naviTwo == null) {
            this.naviTwo = new LexusNaviTwo(this);
        }
        if (this.setImageTwo == null) {
            this.setImageTwo = new LexusSetImageTwo(this);
        }
        if (this.setVocModelTwo == null) {
            this.setVocModelTwo = new LexusSetVocModelTwo(this);
        }
        if (this.timeSetTwo == null) {
            this.timeSetTwo = new LexusTimeSetTwo(this);
        }
        if (this.setVoiceTwo == null) {
            this.setVoiceTwo = new LexusSetVoiceTwo(this);
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.voiceData = intent.getStringExtra("voiceData");
        Log.d("lexusstartAction", "===data====:" + this.voiceData);
    }

    private void initData() {
        try {
            this.voiceData = getIntent().getStringExtra("voiceData");
            Log.d("lexusstartAction", "===data====:" + this.voiceData);
            this.defPwd = PowerManagerApp.getSettingsString(KeyConfig.PASSWORD);
            Log.d("lexusFactoryPwd", "===pwd====:" + this.defPwd);
            if (TextUtils.isEmpty(this.defPwd)) {
                this.defPwd = "1314";
            }
        } catch (Exception e) {
            e.getStackTrace();
            this.defPwd = "1314";
        }
        int[] icons = {C0899R.C0900drawable.lexus_settings_btn_set_n, C0899R.C0900drawable.lexus_settings_btn_gps_n, C0899R.C0900drawable.lexus_settings_btn_audio_n, C0899R.C0900drawable.lexus_settings_btn_language_n, C0899R.C0900drawable.lexus_settings_btn_time_n, C0899R.C0900drawable.lexus_settings_btn_info_n, C0899R.C0900drawable.lexus_settings_btn_android_n, C0899R.C0900drawable.lexus_settings_btn_factory_n};
        this.data = new ArrayList();
        getResources().getStringArray(C0899R.array.set_function);
        for (int i : icons) {
            FunctionBean fcb = new FunctionBean();
            fcb.setIcon(i);
            this.data.add(fcb);
        }
        this.data.get(0).setIscheck(true);
    }

    private void initView() {
        this.recyclerView = (RecyclerView) findViewById(C0899R.C0901id.recyclerView);
        this.lexus_set_title = (TextView) findViewById(C0899R.C0901id.lexus_set_title);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        FunctionAdapter functionAdapter = new FunctionAdapter(this, this.data);
        this.adapter = functionAdapter;
        this.recyclerView.setAdapter(functionAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, 1);
        divider.setDrawable(ContextCompat.getDrawable(this, C0899R.C0900drawable.lexus_settings_line_left));
        this.adapter.registOnFunctionClickListener(new FunctionAdapter.OnFunctionClickListener() { // from class: com.wits.ksw.settings.lexus.LexusSettingsActivity.3
            @Override // com.wits.ksw.settings.lexus.adapter.FunctionAdapter.OnFunctionClickListener
            public void functonClick(int pos) {
                String[] stringArray = LexusSettingsActivity.this.getResources().getStringArray(C0899R.array.set_function);
                int arrayPos = pos;
                if (arrayPos >= 3) {
                    arrayPos++;
                }
                LexusSettingsActivity.this.lexus_set_title.setText(stringArray[arrayPos]);
                LexusSettingsActivity.this.setOneLayout(pos);
                for (FunctionBean fb : LexusSettingsActivity.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) LexusSettingsActivity.this.data.get(pos)).setIscheck(true);
                LexusSettingsActivity.this.adapter.notifyDataSetChanged();
            }
        });
        this.frame_OneLayout = (FrameLayout) findViewById(C0899R.C0901id.frame_OneLayout);
        this.frame_TwoLayout = (FrameLayout) findViewById(C0899R.C0901id.frame_TwoLayout);
        if (this.setSystemLayout == null) {
            LexusSetSystemLayout lexusSetSystemLayout = new LexusSetSystemLayout(this);
            this.setSystemLayout = lexusSetSystemLayout;
            lexusSetSystemLayout.registIUpdateTwoLayout(this);
        }
        this.frame_OneLayout.addView(this.setSystemLayout);
        if (this.setSystemTwo == null) {
            this.setSystemTwo = new LexusSetSystemTwo(this);
        }
        this.frame_TwoLayout.addView(this.setSystemTwo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOneLayout(int type) {
        this.frame_OneLayout.removeAllViews();
        this.frame_TwoLayout.removeAllViews();
        this.frame_TwoLayout.setVisibility(type == 7 ? 8 : 0);
        switch (type) {
            case 0:
                if (this.setSystemLayout == null) {
                    LexusSetSystemLayout lexusSetSystemLayout = new LexusSetSystemLayout(this);
                    this.setSystemLayout = lexusSetSystemLayout;
                    lexusSetSystemLayout.registIUpdateTwoLayout(this);
                }
                if (this.setSystemTwo == null) {
                    this.setSystemTwo = new LexusSetSystemTwo(this);
                }
                this.frame_OneLayout.addView(this.setSystemLayout);
                this.setSystemLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.setSystemTwo);
                break;
            case 1:
                if (this.lexusSetNaviLayout == null) {
                    LexusSetNaviLayout lexusSetNaviLayout = new LexusSetNaviLayout(this);
                    this.lexusSetNaviLayout = lexusSetNaviLayout;
                    lexusSetNaviLayout.registIUpdateTwoLayout(this);
                }
                if (this.naviTwo == null) {
                    this.naviTwo = new LexusNaviTwo(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new LexusSetImageTwo(this);
                }
                this.setImageTwo.setResource(C0899R.C0900drawable.lexus_settings_icon2);
                this.frame_OneLayout.addView(this.naviTwo);
                this.lexusSetNaviLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 2:
                if (this.setVoiceLayout == null) {
                    this.setVoiceLayout = new LexusSetVoiceLayout(this);
                }
                if (this.setVoiceTwo == null) {
                    this.setVoiceTwo = new LexusSetVoiceTwo(this);
                }
                this.frame_OneLayout.addView(this.setVoiceLayout);
                this.setVoiceLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.setVoiceTwo);
                break;
            case 3:
                if (this.setLanguageLayout == null) {
                    this.setLanguageLayout = new LexusSetLanguageLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new LexusSetImageTwo(this);
                }
                this.setImageTwo.setResource(C0899R.C0900drawable.lexus_settings_icon4);
                this.frame_OneLayout.addView(this.setLanguageLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 4:
                if (this.setTimeLayout == null) {
                    LexusSetTimeLayout lexusSetTimeLayout = new LexusSetTimeLayout(this);
                    this.setTimeLayout = lexusSetTimeLayout;
                    lexusSetTimeLayout.registIUpdateTwoLayout(this);
                }
                if (this.timeSetTwo == null) {
                    this.timeSetTwo = new LexusTimeSetTwo(this);
                }
                this.frame_OneLayout.addView(this.setTimeLayout);
                this.setTimeLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.timeSetTwo);
                break;
            case 5:
                if (this.setSystemInfoLayout == null) {
                    this.setSystemInfoLayout = new LexusSetSystemInfoLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new LexusSetImageTwo(this);
                }
                this.setImageTwo.setResource(C0899R.C0900drawable.lexus_settings_icon6);
                this.frame_OneLayout.addView(this.setSystemInfoLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 6:
                if (this.setToAndSysLayout == null) {
                    this.setToAndSysLayout = new LexusSetToAndSysLayout(this);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new LexusSetImageTwo(this);
                }
                this.setImageTwo.setResource(C0899R.C0900drawable.lexus_settings_icon7);
                this.frame_OneLayout.addView(this.setToAndSysLayout);
                this.frame_TwoLayout.addView(this.setImageTwo);
                break;
            case 7:
                if (this.lexusSetFactoryLayout == null) {
                    this.lexusSetFactoryLayout = new LexusSetFactoryLayout(this, this.handler);
                }
                if (this.setImageTwo == null) {
                    this.setImageTwo = new LexusSetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.lexusSetFactoryLayout);
                break;
        }
        this.frame_OneLayout.requestFocus();
    }

    @Override // com.wits.ksw.settings.lexus.interfaces.IUpdateTwoLayout
    public void updateTwoLayout(int type, int shwoIndex) {
        Log.d(this.TAG, "updateTwoLayout type=" + type + " shwoIndex=" + shwoIndex);
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
            case 5:
                this.setVoiceTwo.showLayout(shwoIndex);
                this.setVoiceTwo.invalidate();
                this.setVoiceTwo.requestFocus();
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
