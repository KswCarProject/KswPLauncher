package com.wits.ksw.settings.romeo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.FixLinearSnapHelper;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.id7.FactoryActivity;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.romeo.adapter.FunctionAdapter;
import com.wits.ksw.settings.romeo.interfaces.IUpdateListBg;
import com.wits.ksw.settings.romeo.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.romeo.layout_one.RomeoSetFactoryLayout;
import com.wits.ksw.settings.romeo.layout_one.RomeoSetLanguageLayout;
import com.wits.ksw.settings.romeo.layout_one.RomeoSetNaviLayout;
import com.wits.ksw.settings.romeo.layout_one.RomeoSetSystemInfoLayout;
import com.wits.ksw.settings.romeo.layout_one.RomeoSetSystemLayout;
import com.wits.ksw.settings.romeo.layout_one.RomeoSetTimeLayout;
import com.wits.ksw.settings.romeo.layout_one.RomeoSetToAndSysLayout;
import com.wits.ksw.settings.romeo.layout_one.RomeoSetVocModeLayout;
import com.wits.ksw.settings.romeo.layout_one.RomeoSetVoiceLayout;
import com.wits.ksw.settings.romeo.layout_two.RomeoNaviTwo;
import com.wits.ksw.settings.romeo.layout_two.RomeoSetImageTwo;
import com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo;
import com.wits.ksw.settings.romeo.layout_two.RomeoSetVocModelTwo;
import com.wits.ksw.settings.romeo.layout_two.RomeoSetVoiceTwo;
import com.wits.ksw.settings.romeo.layout_two.RomeoTimeSetTwo;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

public class RomeoSettingsActivity extends BaseActivity implements IUpdateTwoLayout, IUpdateListBg, ScanNaviList.OnMapListScanListener {
    private ImageView Img_SetBack;
    /* access modifiers changed from: private */
    public String TAG = "RomeoSettingsActivity";
    private FunctionAdapter adapter;
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
                    RomeoSettingsActivity.this.initOneLayout();
                    RomeoSettingsActivity.this.initTwoLayout();
                    return;
                case 2:
                    if (TextUtils.equals(RomeoSettingsActivity.this.defPwd, (String) msg.obj)) {
                        RomeoSettingsActivity.this.startActivity(new Intent(RomeoSettingsActivity.this, FactoryActivity.class));
                        RomeoSettingsActivity.this.finish();
                        return;
                    }
                    RomeoSettingsActivity.this.romeoSetFactoryLayout.SetTextEEro();
                    return;
                case 3:
                    if (RomeoSettingsActivity.this.romeoNaviTwo != null) {
                        Log.d("Navi", "updateList: " + RomeoSettingsActivity.this.mapList.size());
                        RomeoSettingsActivity.this.romeoNaviTwo.updateMapList(RomeoSettingsActivity.this.mapList);
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
    public TextView lexus_set_title;
    /* access modifiers changed from: private */
    public List<MapBean> mapList = new ArrayList();
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public RomeoNaviTwo romeoNaviTwo;
    /* access modifiers changed from: private */
    public RomeoSetFactoryLayout romeoSetFactoryLayout;
    private RomeoSetImageTwo romeoSetImageTwo;
    private RomeoSetLanguageLayout romeoSetLanguageLayout;
    private RomeoSetNaviLayout romeoSetNaviLayout;
    private RomeoSetSystemInfoLayout romeoSetSystemInfoLayout;
    private RomeoSetSystemLayout romeoSetSystemLayout;
    private RomeoSetSystemTwo romeoSetSystemTwo;
    private RomeoSetTimeLayout romeoSetTimeLayout;
    private RomeoSetToAndSysLayout romeoSetToAndSysLayout;
    private RomeoSetVocModeLayout romeoSetVocModeLayout;
    private RomeoSetVocModelTwo romeoSetVocModelTwo;
    private RomeoSetVoiceLayout romeoSetVoiceLayout;
    private RomeoSetVoiceTwo romeoSetVoiceTwo;
    private RomeoTimeSetTwo romeoTimeSetTwo;
    private ImageView romeo_indicator_1;
    private ImageView romeo_indicator_2;
    private ImageView romeo_indicator_3;
    private ImageView romeo_indicator_4;
    private ImageView romeo_indicator_5;
    private ImageView romeo_indicator_6;
    private ImageView romeo_left1;
    private ImageView romeo_left2;
    private ImageView romeo_left3;
    private ImageView romeo_left4;
    private ImageView romeo_left5;
    private ImageView romeo_left6;
    private ImageView romeo_settings_indicator1;
    private ImageView romeo_settings_indicator2;
    private ImageView romeo_settings_indicator3;
    private ImageView romeo_settings_indicator4;
    private ImageView romeo_settings_indicator5;
    private ImageView romeo_settings_indicator6;
    private ImageView romeo_settings_list1;
    private ImageView romeo_settings_list2;
    private ImageView romeo_settings_list3;
    private ImageView romeo_settings_list4;
    private ImageView romeo_settings_list5;
    private ImageView romeo_settings_list6;
    private String voiceData;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_romeo_settings);
        initData();
        initView();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(this.TAG, "onWindowFocusChanged hasFocus=" + hasFocus + " first=" + this.first);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d(this.TAG, "dispatchKeyEvent keyCode=" + event.getKeyCode() + " first=" + this.first + " hasFocus=" + this.recyclerView.hasFocus() + " action=" + event.getAction());
        if (event.getKeyCode() == 22 && event.getAction() == 1 && this.recyclerView.hasFocus()) {
            this.frame_OneLayout.requestFocus();
        }
        if ((event.getKeyCode() == 19 || event.getKeyCode() == 20 || event.getKeyCode() == 21 || event.getKeyCode() == 22) && this.first) {
            this.recyclerView.requestFocus();
            this.first = false;
        }
        return super.dispatchKeyEvent(event);
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
        } else if (TextUtils.equals("voicFun", this.voiceData)) {
            initOneLayout();
            initTwoLayout();
            setOneLayout(3);
            for (FunctionBean fb2 : this.data) {
                fb2.setIscheck(false);
            }
            this.data.get(3).setIscheck(true);
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
        if (this.romeoSetSystemLayout == null) {
            RomeoSetSystemLayout romeoSetSystemLayout2 = new RomeoSetSystemLayout(this);
            this.romeoSetSystemLayout = romeoSetSystemLayout2;
            romeoSetSystemLayout2.registIUpdateTwoLayout(this);
            this.romeoSetSystemLayout.registIUpdateListBg(this);
        }
        if (this.romeoSetNaviLayout == null) {
            RomeoSetNaviLayout romeoSetNaviLayout2 = new RomeoSetNaviLayout(this);
            this.romeoSetNaviLayout = romeoSetNaviLayout2;
            romeoSetNaviLayout2.registIUpdateTwoLayout(this);
        }
        if (this.romeoSetVoiceLayout == null) {
            RomeoSetVoiceLayout romeoSetVoiceLayout2 = new RomeoSetVoiceLayout(this);
            this.romeoSetVoiceLayout = romeoSetVoiceLayout2;
            romeoSetVoiceLayout2.registIUpdateTwoLayout(this);
            this.romeoSetVoiceLayout.registIUpdateListBg(this);
        }
        if (this.romeoSetVocModeLayout == null) {
            RomeoSetVocModeLayout romeoSetVocModeLayout2 = new RomeoSetVocModeLayout(this);
            this.romeoSetVocModeLayout = romeoSetVocModeLayout2;
            romeoSetVocModeLayout2.registIUpdateTwoLayout(this);
        }
        if (this.romeoSetLanguageLayout == null) {
            RomeoSetLanguageLayout romeoSetLanguageLayout2 = new RomeoSetLanguageLayout(this);
            this.romeoSetLanguageLayout = romeoSetLanguageLayout2;
            romeoSetLanguageLayout2.registIUpdateListBg(this);
        }
        if (this.romeoSetToAndSysLayout == null) {
            RomeoSetToAndSysLayout romeoSetToAndSysLayout2 = new RomeoSetToAndSysLayout(this);
            this.romeoSetToAndSysLayout = romeoSetToAndSysLayout2;
            romeoSetToAndSysLayout2.registIUpdateListBg(this);
        }
        if (this.romeoSetTimeLayout == null) {
            RomeoSetTimeLayout romeoSetTimeLayout2 = new RomeoSetTimeLayout(this);
            this.romeoSetTimeLayout = romeoSetTimeLayout2;
            romeoSetTimeLayout2.registIUpdateTwoLayout(this);
            this.romeoSetTimeLayout.registIUpdateListBg(this);
        }
        if (this.romeoSetSystemInfoLayout == null) {
            RomeoSetSystemInfoLayout romeoSetSystemInfoLayout2 = new RomeoSetSystemInfoLayout(this);
            this.romeoSetSystemInfoLayout = romeoSetSystemInfoLayout2;
            romeoSetSystemInfoLayout2.registIUpdateListBg(this);
        }
        if (this.romeoSetFactoryLayout == null) {
            this.romeoSetFactoryLayout = new RomeoSetFactoryLayout(this, this.handler);
        }
    }

    /* access modifiers changed from: private */
    public void initTwoLayout() {
        if (this.romeoSetSystemTwo == null) {
            this.romeoSetSystemTwo = new RomeoSetSystemTwo(this);
        }
        if (this.romeoNaviTwo == null) {
            RomeoNaviTwo romeoNaviTwo2 = new RomeoNaviTwo(this);
            this.romeoNaviTwo = romeoNaviTwo2;
            romeoNaviTwo2.registIUpdateListBg(this);
        }
        if (this.romeoSetImageTwo == null) {
            this.romeoSetImageTwo = new RomeoSetImageTwo(this);
        }
        if (this.romeoSetVocModelTwo == null) {
            this.romeoSetVocModelTwo = new RomeoSetVocModelTwo(this);
        }
        if (this.romeoTimeSetTwo == null) {
            this.romeoTimeSetTwo = new RomeoTimeSetTwo(this);
        }
        if (this.romeoSetVoiceTwo == null) {
            this.romeoSetVoiceTwo = new RomeoSetVoiceTwo(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
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
        int[] icons = {R.drawable.lexus_settings_btn_set_n, R.drawable.lexus_settings_btn_gps_n, R.drawable.lexus_settings_btn_audio_n, R.drawable.lexus_settings_btn_language_n, R.drawable.lexus_settings_btn_time_n, R.drawable.lexus_settings_btn_info_n, R.drawable.lexus_settings_btn_android_n, R.drawable.lexus_settings_btn_factory_n};
        this.data = new ArrayList();
        String[] stringArray = getResources().getStringArray(R.array.set_function);
        for (int icon : icons) {
            FunctionBean fcb = new FunctionBean();
            fcb.setIcon(icon);
            this.data.add(fcb);
        }
        this.data.get(0).setIscheck(true);
    }

    /* access modifiers changed from: private */
    public void changeDistance(RecyclerView recyclerView2) {
        Log.d(this.TAG, "calculateTranslate count=" + recyclerView2.getChildCount());
        for (int i = 0; i < recyclerView2.getChildCount(); i++) {
            recyclerView2.getChildAt(i).setPadding(KswUtils.calculateTranslate(recyclerView2.getChildAt(i).getTop(), KswUtils.dip2px(this, 428.0f), i, this), 0, 0, 0);
        }
    }

    /* access modifiers changed from: private */
    public void resetListItemBg(int type) {
        if (type == -1) {
            this.romeo_settings_indicator1.setVisibility(8);
            this.romeo_settings_indicator2.setVisibility(8);
            this.romeo_settings_indicator3.setVisibility(8);
            this.romeo_settings_indicator4.setVisibility(8);
            this.romeo_settings_indicator5.setVisibility(8);
            this.romeo_settings_indicator6.setVisibility(8);
            this.romeo_settings_list1.getDrawable().setLevel(0);
            this.romeo_settings_list2.getDrawable().setLevel(0);
            this.romeo_settings_list3.getDrawable().setLevel(0);
            this.romeo_settings_list4.getDrawable().setLevel(0);
            this.romeo_settings_list5.getDrawable().setLevel(0);
            this.romeo_settings_list6.getDrawable().setLevel(0);
            return;
        }
        if (this.romeo_settings_list1.getDrawable().getLevel() == type) {
            this.romeo_settings_indicator1.setVisibility(8);
            this.romeo_settings_list1.getDrawable().setLevel(0);
        }
        if (this.romeo_settings_list2.getDrawable().getLevel() == type) {
            this.romeo_settings_indicator2.setVisibility(8);
            this.romeo_settings_list2.getDrawable().setLevel(0);
        }
        if (this.romeo_settings_list3.getDrawable().getLevel() == type) {
            this.romeo_settings_indicator3.setVisibility(8);
            this.romeo_settings_list3.getDrawable().setLevel(0);
        }
        if (this.romeo_settings_list4.getDrawable().getLevel() == type) {
            this.romeo_settings_indicator4.setVisibility(8);
            this.romeo_settings_list4.getDrawable().setLevel(0);
        }
        if (this.romeo_settings_list5.getDrawable().getLevel() == type) {
            this.romeo_settings_indicator5.setVisibility(8);
            this.romeo_settings_list5.getDrawable().setLevel(0);
        }
        if (this.romeo_settings_list6.getDrawable().getLevel() == type) {
            this.romeo_settings_indicator6.setVisibility(8);
            this.romeo_settings_list6.getDrawable().setLevel(0);
        }
    }

    private void resetRecycleItemBg(int type) {
        if (type == -1) {
            this.romeo_indicator_1.setVisibility(8);
            this.romeo_indicator_2.setVisibility(8);
            this.romeo_indicator_3.setVisibility(8);
            this.romeo_indicator_4.setVisibility(8);
            this.romeo_indicator_5.setVisibility(8);
            this.romeo_indicator_6.setVisibility(8);
            this.romeo_left1.getDrawable().setLevel(0);
            this.romeo_left2.getDrawable().setLevel(0);
            this.romeo_left3.getDrawable().setLevel(0);
            this.romeo_left4.getDrawable().setLevel(0);
            this.romeo_left5.getDrawable().setLevel(0);
            this.romeo_left6.getDrawable().setLevel(0);
            return;
        }
        if (this.romeo_left1.getDrawable().getLevel() == type) {
            this.romeo_indicator_1.setVisibility(8);
            this.romeo_left1.getDrawable().setLevel(0);
        }
        if (this.romeo_left2.getDrawable().getLevel() == type) {
            this.romeo_indicator_2.setVisibility(8);
            this.romeo_left2.getDrawable().setLevel(0);
        }
        if (this.romeo_left3.getDrawable().getLevel() == type) {
            this.romeo_indicator_3.setVisibility(8);
            this.romeo_left3.getDrawable().setLevel(0);
        }
        if (this.romeo_left4.getDrawable().getLevel() == type) {
            this.romeo_indicator_4.setVisibility(8);
            this.romeo_left4.getDrawable().setLevel(0);
        }
        if (this.romeo_left5.getDrawable().getLevel() == type) {
            this.romeo_indicator_5.setVisibility(8);
            this.romeo_left5.getDrawable().setLevel(0);
        }
        if (this.romeo_left6.getDrawable().getLevel() == type) {
            this.romeo_indicator_6.setVisibility(8);
            this.romeo_left6.getDrawable().setLevel(0);
        }
    }

    public void changeListItemSelect(int top, int type) {
        if (type == 0) {
            resetListItemBg(-1);
            return;
        }
        if (type == 1) {
            resetRecycleItemBg(1);
        }
        this.romeo_settings_indicator1.setVisibility(8);
        this.romeo_settings_indicator2.setVisibility(8);
        this.romeo_settings_indicator3.setVisibility(8);
        this.romeo_settings_indicator4.setVisibility(8);
        this.romeo_settings_indicator5.setVisibility(8);
        this.romeo_settings_indicator6.setVisibility(8);
        if (this.romeo_settings_list1.getDrawable().getLevel() == type) {
            this.romeo_settings_list1.getDrawable().setLevel(0);
        }
        if (this.romeo_settings_list2.getDrawable().getLevel() == type) {
            this.romeo_settings_list2.getDrawable().setLevel(0);
        }
        if (this.romeo_settings_list3.getDrawable().getLevel() == type) {
            this.romeo_settings_list3.getDrawable().setLevel(0);
        }
        if (this.romeo_settings_list4.getDrawable().getLevel() == type) {
            this.romeo_settings_list4.getDrawable().setLevel(0);
        }
        if (this.romeo_settings_list5.getDrawable().getLevel() == type) {
            this.romeo_settings_list5.getDrawable().setLevel(0);
        }
        if (this.romeo_settings_list6.getDrawable().getLevel() == type) {
            this.romeo_settings_list6.getDrawable().setLevel(0);
        }
        if (top >= 0) {
            if (top < 107) {
                this.romeo_settings_list1.getDrawable().setLevel(type);
                this.romeo_settings_indicator1.setVisibility(0);
            } else if (top < 214) {
                this.romeo_settings_list2.getDrawable().setLevel(type);
                this.romeo_settings_indicator2.setVisibility(0);
            } else if (top < 321) {
                this.romeo_settings_list3.getDrawable().setLevel(type);
                this.romeo_settings_indicator3.setVisibility(0);
            } else if (top < 428) {
                this.romeo_settings_list4.getDrawable().setLevel(type);
                this.romeo_settings_indicator4.setVisibility(0);
            } else if (top < 535) {
                this.romeo_settings_list5.getDrawable().setLevel(type);
                this.romeo_settings_indicator5.setVisibility(0);
            } else if (top < 642) {
                this.romeo_settings_list6.getDrawable().setLevel(type);
                this.romeo_settings_indicator6.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void changeItemSelect(int top, int type) {
        if (type == 1) {
            resetListItemBg(1);
        }
        this.romeo_indicator_1.setVisibility(8);
        this.romeo_indicator_2.setVisibility(8);
        this.romeo_indicator_3.setVisibility(8);
        this.romeo_indicator_4.setVisibility(8);
        this.romeo_indicator_5.setVisibility(8);
        this.romeo_indicator_6.setVisibility(8);
        if (this.romeo_left1.getDrawable().getLevel() == type) {
            this.romeo_left1.getDrawable().setLevel(0);
        }
        if (this.romeo_left2.getDrawable().getLevel() == type) {
            this.romeo_left2.getDrawable().setLevel(0);
        }
        if (this.romeo_left3.getDrawable().getLevel() == type) {
            this.romeo_left3.getDrawable().setLevel(0);
        }
        if (this.romeo_left4.getDrawable().getLevel() == type) {
            this.romeo_left4.getDrawable().setLevel(0);
        }
        if (this.romeo_left5.getDrawable().getLevel() == type) {
            this.romeo_left5.getDrawable().setLevel(0);
        }
        if (this.romeo_left6.getDrawable().getLevel() == type) {
            this.romeo_left6.getDrawable().setLevel(0);
        }
        if (top >= 0) {
            if (top < 107) {
                this.romeo_left1.getDrawable().setLevel(type);
                this.romeo_indicator_1.setVisibility(0);
            } else if (top < 214) {
                this.romeo_left2.getDrawable().setLevel(type);
                this.romeo_indicator_2.setVisibility(0);
            } else if (top < 321) {
                this.romeo_left3.getDrawable().setLevel(type);
                this.romeo_indicator_3.setVisibility(0);
            } else if (top < 428) {
                this.romeo_left4.getDrawable().setLevel(type);
                this.romeo_indicator_4.setVisibility(0);
            } else if (top < 535) {
                this.romeo_left5.getDrawable().setLevel(type);
                this.romeo_indicator_5.setVisibility(0);
            } else if (top < 642) {
                this.romeo_left6.getDrawable().setLevel(type);
                this.romeo_indicator_6.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void clearItemSelect(int top, int type) {
        if (top >= 0) {
            if (top < 107) {
                if (this.romeo_left1.getDrawable().getLevel() == type) {
                    this.romeo_left1.getDrawable().setLevel(0);
                    this.romeo_indicator_1.setVisibility(8);
                }
            } else if (top < 214) {
                if (this.romeo_left2.getDrawable().getLevel() == type) {
                    this.romeo_left2.getDrawable().setLevel(0);
                    this.romeo_indicator_2.setVisibility(8);
                }
            } else if (top < 321) {
                if (this.romeo_left3.getDrawable().getLevel() == type) {
                    this.romeo_left3.getDrawable().setLevel(0);
                    this.romeo_indicator_3.setVisibility(8);
                }
            } else if (top < 428) {
                if (this.romeo_left4.getDrawable().getLevel() == type) {
                    this.romeo_left4.getDrawable().setLevel(0);
                    this.romeo_indicator_4.setVisibility(8);
                }
            } else if (top < 535) {
                if (this.romeo_left5.getDrawable().getLevel() == type) {
                    this.romeo_left5.getDrawable().setLevel(0);
                    this.romeo_indicator_5.setVisibility(8);
                }
            } else if (top < 642 && this.romeo_left6.getDrawable().getLevel() == type) {
                this.romeo_left6.getDrawable().setLevel(0);
                this.romeo_indicator_6.setVisibility(8);
            }
        }
    }

    private void initView() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.lexus_set_title = (TextView) findViewById(R.id.lexus_set_title);
        this.romeo_settings_list1 = (ImageView) findViewById(R.id.romeo_settings_list1);
        this.romeo_settings_list2 = (ImageView) findViewById(R.id.romeo_settings_list2);
        this.romeo_settings_list3 = (ImageView) findViewById(R.id.romeo_settings_list3);
        this.romeo_settings_list4 = (ImageView) findViewById(R.id.romeo_settings_list4);
        this.romeo_settings_list5 = (ImageView) findViewById(R.id.romeo_settings_list5);
        this.romeo_settings_list6 = (ImageView) findViewById(R.id.romeo_settings_list6);
        this.romeo_settings_indicator1 = (ImageView) findViewById(R.id.romeo_settings_indicator1);
        this.romeo_settings_indicator2 = (ImageView) findViewById(R.id.romeo_settings_indicator2);
        this.romeo_settings_indicator3 = (ImageView) findViewById(R.id.romeo_settings_indicator3);
        this.romeo_settings_indicator4 = (ImageView) findViewById(R.id.romeo_settings_indicator4);
        this.romeo_settings_indicator5 = (ImageView) findViewById(R.id.romeo_settings_indicator5);
        this.romeo_settings_indicator6 = (ImageView) findViewById(R.id.romeo_settings_indicator6);
        this.romeo_left1 = (ImageView) findViewById(R.id.romeo_left1);
        this.romeo_left2 = (ImageView) findViewById(R.id.romeo_left2);
        this.romeo_left3 = (ImageView) findViewById(R.id.romeo_left3);
        this.romeo_left4 = (ImageView) findViewById(R.id.romeo_left4);
        this.romeo_left5 = (ImageView) findViewById(R.id.romeo_left5);
        this.romeo_left6 = (ImageView) findViewById(R.id.romeo_left6);
        this.romeo_indicator_1 = (ImageView) findViewById(R.id.romeo_indicator_1);
        this.romeo_indicator_2 = (ImageView) findViewById(R.id.romeo_indicator_2);
        this.romeo_indicator_3 = (ImageView) findViewById(R.id.romeo_indicator_3);
        this.romeo_indicator_4 = (ImageView) findViewById(R.id.romeo_indicator_4);
        this.romeo_indicator_5 = (ImageView) findViewById(R.id.romeo_indicator_5);
        this.romeo_indicator_6 = (ImageView) findViewById(R.id.romeo_indicator_6);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setItemViewCacheSize(0);
        new FixLinearSnapHelper().attachToRecyclerView(this.recyclerView);
        FunctionAdapter functionAdapter = new FunctionAdapter(this, this.data);
        this.adapter = functionAdapter;
        this.recyclerView.setAdapter(functionAdapter);
        new DividerItemDecoration(this, 1).setDrawable(ContextCompat.getDrawable(this, R.drawable.lexus_settings_line_left));
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RomeoSettingsActivity.this.changeDistance(recyclerView);
            }
        });
        this.adapter.registOnItemBgChangeListener(new FunctionAdapter.OnItemBgChangeListener() {
            public void onChangeItemSelect(int top, int type, int position) {
                Log.d(RomeoSettingsActivity.this.TAG, "onChangeItemSelect top=" + top + " type=" + type + " position=" + position);
                if (position == 6) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) RomeoSettingsActivity.this.recyclerView.getLayoutManager();
                    layoutManager.scrollToPositionWithOffset(2, 0);
                    layoutManager.setStackFromEnd(true);
                    top = 428;
                    Log.d(RomeoSettingsActivity.this.TAG, "onChangeItemSelect top/=" + 428 + " type=" + type + " position=" + position);
                } else if (position == 1) {
                    LinearLayoutManager layoutManager2 = (LinearLayoutManager) RomeoSettingsActivity.this.recyclerView.getLayoutManager();
                    layoutManager2.scrollToPositionWithOffset(0, 0);
                    layoutManager2.setStackFromEnd(true);
                    top = 107;
                    Log.d(RomeoSettingsActivity.this.TAG, "onChangeItemSelect top/=" + 107 + " type=" + type + " position=" + position);
                }
                RomeoSettingsActivity.this.changeItemSelect(top, type);
            }

            public void onClearSelect(int top, int type, int position) {
                RomeoSettingsActivity.this.clearItemSelect(top, type);
            }
        });
        this.adapter.registOnFunctionClickListener(new FunctionAdapter.OnFunctionClickListener() {
            public void functonClick(int pos) {
                String[] stringArray = RomeoSettingsActivity.this.getResources().getStringArray(R.array.set_function);
                int arrayPos = pos;
                if (arrayPos >= 3) {
                    arrayPos++;
                }
                RomeoSettingsActivity.this.lexus_set_title.setText(stringArray[arrayPos]);
                RomeoSettingsActivity.this.setOneLayout(pos);
                RomeoSettingsActivity.this.resetListItemBg(-1);
                for (FunctionBean fb : RomeoSettingsActivity.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) RomeoSettingsActivity.this.data.get(pos)).setIscheck(true);
            }
        });
        this.frame_OneLayout = (FrameLayout) findViewById(R.id.frame_OneLayout);
        this.frame_TwoLayout = (FrameLayout) findViewById(R.id.frame_TwoLayout);
        if (this.romeoSetSystemLayout == null) {
            RomeoSetSystemLayout romeoSetSystemLayout2 = new RomeoSetSystemLayout(this);
            this.romeoSetSystemLayout = romeoSetSystemLayout2;
            romeoSetSystemLayout2.registIUpdateTwoLayout(this);
            this.romeoSetSystemLayout.registIUpdateListBg(this);
        }
        this.frame_OneLayout.addView(this.romeoSetSystemLayout);
        if (this.romeoSetSystemTwo == null) {
            this.romeoSetSystemTwo = new RomeoSetSystemTwo(this);
        }
        this.frame_TwoLayout.addView(this.romeoSetSystemTwo);
    }

    /* access modifiers changed from: private */
    public void setOneLayout(int type) {
        this.frame_OneLayout.removeAllViews();
        this.frame_TwoLayout.removeAllViews();
        this.frame_TwoLayout.setVisibility(type == 7 ? 8 : 0);
        switch (type) {
            case 0:
                if (this.romeoSetSystemLayout == null) {
                    RomeoSetSystemLayout romeoSetSystemLayout2 = new RomeoSetSystemLayout(this);
                    this.romeoSetSystemLayout = romeoSetSystemLayout2;
                    romeoSetSystemLayout2.registIUpdateTwoLayout(this);
                    this.romeoSetSystemLayout.registIUpdateListBg(this);
                }
                if (this.romeoSetSystemTwo == null) {
                    this.romeoSetSystemTwo = new RomeoSetSystemTwo(this);
                }
                this.frame_OneLayout.addView(this.romeoSetSystemLayout);
                this.romeoSetSystemLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.romeoSetSystemTwo);
                break;
            case 1:
                if (this.romeoSetNaviLayout == null) {
                    RomeoSetNaviLayout romeoSetNaviLayout2 = new RomeoSetNaviLayout(this);
                    this.romeoSetNaviLayout = romeoSetNaviLayout2;
                    romeoSetNaviLayout2.registIUpdateTwoLayout(this);
                }
                if (this.romeoNaviTwo == null) {
                    RomeoNaviTwo romeoNaviTwo2 = new RomeoNaviTwo(this);
                    this.romeoNaviTwo = romeoNaviTwo2;
                    romeoNaviTwo2.registIUpdateListBg(this);
                }
                if (this.romeoSetImageTwo == null) {
                    this.romeoSetImageTwo = new RomeoSetImageTwo(this);
                }
                this.romeoSetImageTwo.setResource(R.drawable.romeo_settings_icon2);
                this.frame_OneLayout.addView(this.romeoNaviTwo);
                this.romeoSetNaviLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.romeoSetImageTwo);
                break;
            case 2:
                if (this.romeoSetVoiceLayout == null) {
                    RomeoSetVoiceLayout romeoSetVoiceLayout2 = new RomeoSetVoiceLayout(this);
                    this.romeoSetVoiceLayout = romeoSetVoiceLayout2;
                    romeoSetVoiceLayout2.registIUpdateListBg(this);
                }
                if (this.romeoSetVoiceTwo == null) {
                    this.romeoSetVoiceTwo = new RomeoSetVoiceTwo(this);
                }
                this.frame_OneLayout.addView(this.romeoSetVoiceLayout);
                this.romeoSetVoiceLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.romeoSetVoiceTwo);
                break;
            case 3:
                if (this.romeoSetLanguageLayout == null) {
                    RomeoSetLanguageLayout romeoSetLanguageLayout2 = new RomeoSetLanguageLayout(this);
                    this.romeoSetLanguageLayout = romeoSetLanguageLayout2;
                    romeoSetLanguageLayout2.registIUpdateListBg(this);
                }
                if (this.romeoSetImageTwo == null) {
                    this.romeoSetImageTwo = new RomeoSetImageTwo(this);
                }
                this.romeoSetImageTwo.setResource(R.drawable.romeo_settings_icon4);
                this.frame_OneLayout.addView(this.romeoSetLanguageLayout);
                this.frame_TwoLayout.addView(this.romeoSetImageTwo);
                break;
            case 4:
                if (this.romeoSetTimeLayout == null) {
                    RomeoSetTimeLayout romeoSetTimeLayout2 = new RomeoSetTimeLayout(this);
                    this.romeoSetTimeLayout = romeoSetTimeLayout2;
                    romeoSetTimeLayout2.registIUpdateTwoLayout(this);
                    this.romeoSetTimeLayout.registIUpdateListBg(this);
                }
                if (this.romeoTimeSetTwo == null) {
                    this.romeoTimeSetTwo = new RomeoTimeSetTwo(this);
                }
                this.frame_OneLayout.addView(this.romeoSetTimeLayout);
                this.romeoSetTimeLayout.resetTextColor();
                this.frame_TwoLayout.addView(this.romeoTimeSetTwo);
                break;
            case 5:
                if (this.romeoSetSystemInfoLayout == null) {
                    RomeoSetSystemInfoLayout romeoSetSystemInfoLayout2 = new RomeoSetSystemInfoLayout(this);
                    this.romeoSetSystemInfoLayout = romeoSetSystemInfoLayout2;
                    romeoSetSystemInfoLayout2.registIUpdateListBg(this);
                }
                if (this.romeoSetImageTwo == null) {
                    this.romeoSetImageTwo = new RomeoSetImageTwo(this);
                }
                this.romeoSetImageTwo.setResource(R.drawable.romeo_settings_icon6);
                this.frame_OneLayout.addView(this.romeoSetSystemInfoLayout);
                this.frame_TwoLayout.addView(this.romeoSetImageTwo);
                break;
            case 6:
                if (this.romeoSetToAndSysLayout == null) {
                    RomeoSetToAndSysLayout romeoSetToAndSysLayout2 = new RomeoSetToAndSysLayout(this);
                    this.romeoSetToAndSysLayout = romeoSetToAndSysLayout2;
                    romeoSetToAndSysLayout2.registIUpdateListBg(this);
                }
                if (this.romeoSetImageTwo == null) {
                    this.romeoSetImageTwo = new RomeoSetImageTwo(this);
                }
                this.romeoSetImageTwo.setResource(R.drawable.romeo_settings_icon7);
                this.frame_OneLayout.addView(this.romeoSetToAndSysLayout);
                this.frame_TwoLayout.addView(this.romeoSetImageTwo);
                break;
            case 7:
                if (this.romeoSetFactoryLayout == null) {
                    this.romeoSetFactoryLayout = new RomeoSetFactoryLayout(this, this.handler);
                }
                if (this.romeoSetImageTwo == null) {
                    this.romeoSetImageTwo = new RomeoSetImageTwo(this);
                }
                this.frame_OneLayout.addView(this.romeoSetFactoryLayout);
                this.frame_TwoLayout.addView(this.romeoSetImageTwo);
                this.frame_TwoLayout.setVisibility(0);
                break;
        }
        this.frame_OneLayout.requestFocus();
    }

    public void updateTwoLayout(int type, int shwoIndex) {
        Log.d(this.TAG, "updateTwoLayout type=" + type + " shwoIndex=" + shwoIndex);
        switch (type) {
            case 1:
                this.romeoSetSystemTwo.showLayout(shwoIndex);
                this.romeoSetSystemTwo.invalidate();
                this.romeoSetSystemTwo.requestFocus();
                return;
            case 2:
                this.romeoNaviTwo.showLayout(shwoIndex);
                this.romeoNaviTwo.invalidate();
                this.romeoNaviTwo.requestFocus();
                return;
            case 3:
                this.romeoSetVocModelTwo.showLayout(shwoIndex);
                this.romeoSetVocModelTwo.invalidate();
                this.romeoSetVocModelTwo.requestFocus();
                return;
            case 4:
                this.romeoTimeSetTwo.showLayout(shwoIndex);
                this.romeoTimeSetTwo.invalidate();
                this.romeoTimeSetTwo.requestFocus();
                return;
            case 5:
                this.romeoSetVoiceTwo.showLayout(shwoIndex);
                this.romeoSetVoiceTwo.invalidate();
                this.romeoSetVoiceTwo.requestFocus();
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

    public void updateListBg(int top, int type) {
        Log.d(this.TAG, "updateListBg top=" + top + " type=" + type);
        changeListItemSelect(top, type);
    }
}
