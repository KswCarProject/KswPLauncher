package com.wits.ksw.settings.ntg6;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.wits.ksw.R;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.id7.FactoryActivity;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.ntg6.adapter.Ntg6FunctionAdapter;
import com.wits.ksw.settings.ntg6.one_layout.Ntg6FactoryLayout;
import com.wits.ksw.settings.ntg6.one_layout.Ntg6LanguageLayout;
import com.wits.ksw.settings.ntg6.one_layout.Ntg6NaviLayout;
import com.wits.ksw.settings.ntg6.one_layout.Ntg6SystemInfoLayout;
import com.wits.ksw.settings.ntg6.one_layout.Ntg6SystemLayout;
import com.wits.ksw.settings.ntg6.one_layout.Ntg6TimeLayout;
import com.wits.ksw.settings.ntg6.one_layout.Ntg6VocModeLayout;
import com.wits.ksw.settings.ntg6.one_layout.Ntg6VoiceLayout;
import com.wits.ksw.settings.ntg6.two_layout.Ntg6NaviTwo;
import com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ntg6SettingsActivity extends BaseActivity implements IUpdateTwoLayout, ScanNaviList.OnMapListScanListener {
    private Ntg6FunctionAdapter adapter;
    /* access modifiers changed from: private */
    public ObjectAnimator animatorIn;
    /* access modifiers changed from: private */
    public ObjectAnimator animatorOut;
    private List<FunctionBean> data;
    /* access modifiers changed from: private */
    public String defPwd = "1314";
    private int[] firstImages;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Ntg6SettingsActivity.this.relat_ntgOneList.setVisibility(0);
                    Ntg6SettingsActivity ntg6SettingsActivity = Ntg6SettingsActivity.this;
                    ntg6SettingsActivity.setAnimatorIn(ntg6SettingsActivity.relat_ntgTwoList);
                    Ntg6SettingsActivity.this.animatorIn.setDuration(600);
                    Ntg6SettingsActivity.this.animatorIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (((int) (10.0f * animation.getAnimatedFraction())) == 1) {
                                Ntg6SettingsActivity.this.setAnimatorOut(Ntg6SettingsActivity.this.relat_ntgOneList);
                                Ntg6SettingsActivity.this.animatorOut.setDuration(600);
                                Ntg6SettingsActivity.this.animatorOut.start();
                            }
                        }
                    });
                    Ntg6SettingsActivity.this.animatorIn.start();
                    Ntg6SettingsActivity.this.recyclerView.requestFocus();
                    return;
                case 2:
                    if (TextUtils.equals(Ntg6SettingsActivity.this.defPwd, (String) msg.obj)) {
                        Ntg6SettingsActivity.this.startActivity(new Intent(Ntg6SettingsActivity.this, FactoryActivity.class));
                        Ntg6SettingsActivity.this.handler.sendEmptyMessage(1);
                        Ntg6SettingsActivity.this.finish();
                        return;
                    }
                    Ntg6SettingsActivity.this.ntg6FactoryLayout.SetTextEEro();
                    Ntg6SettingsActivity ntg6SettingsActivity2 = Ntg6SettingsActivity.this;
                    Toast.makeText(ntg6SettingsActivity2, ntg6SettingsActivity2.getString(R.string.lb_password_error), 0).show();
                    return;
                case 3:
                    Ntg6SettingsActivity.this.relat_ntgTwoList.setVisibility(0);
                    Ntg6SettingsActivity ntg6SettingsActivity3 = Ntg6SettingsActivity.this;
                    ntg6SettingsActivity3.setAnimatorIn(ntg6SettingsActivity3.relat_ntgThreeList);
                    Ntg6SettingsActivity.this.animatorIn.setDuration(600);
                    Ntg6SettingsActivity.this.animatorIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (((int) (10.0f * animation.getAnimatedFraction())) == 1) {
                                Ntg6SettingsActivity.this.setAnimatorOut(Ntg6SettingsActivity.this.relat_ntgTwoList);
                                Ntg6SettingsActivity.this.animatorOut.setDuration(600);
                                Ntg6SettingsActivity.this.animatorOut.start();
                            }
                        }
                    });
                    Ntg6SettingsActivity.this.animatorIn.start();
                    Ntg6SettingsActivity.this.getLayoutFouse();
                    return;
                case 4:
                    Ntg6SettingsActivity.this.initOneLayout();
                    Ntg6SettingsActivity.this.initTwoLayout();
                    return;
                case 5:
                    if (Ntg6SettingsActivity.this.ntg6NaviTwo != null) {
                        Ntg6SettingsActivity.this.ntg6NaviTwo.updateList(Ntg6SettingsActivity.this.mapList);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private ImageView img_funtionImg;
    private LinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public List<MapBean> mapList;
    /* access modifiers changed from: private */
    public Ntg6FactoryLayout ntg6FactoryLayout;
    private Ntg6LanguageLayout ntg6LanguageLayout;
    private Ntg6NaviLayout ntg6NaviLayout;
    /* access modifiers changed from: private */
    public Ntg6NaviTwo ntg6NaviTwo;
    private Ntg6SystemLayout ntg6SetSystemLayout;
    private Ntg6SetSystemTwo ntg6SetSystemTwo;
    private Ntg6SystemInfoLayout ntg6SystemInfoLayout;
    private Ntg6TimeLayout ntg6TimeLayout;
    private Ntg6VocModeLayout ntg6VocModeLayout;
    private Ntg6VoiceLayout ntg6VoiceLayout;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public RelativeLayout relat_Factory;
    /* access modifiers changed from: private */
    public RelativeLayout relat_ntgOneList;
    /* access modifiers changed from: private */
    public RelativeLayout relat_ntgThreeList;
    /* access modifiers changed from: private */
    public RelativeLayout relat_ntgTwoList;
    private String systemTime = "";
    private TextView tv_systemInfo;
    private TextView tv_systemInfo2;
    private TextView tv_systemTime;
    private String voiceData;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_benchi_settings);
        this.handler.sendEmptyMessageDelayed(4, 1000);
        initData();
        initView();
        Log.d("ntg55startAction", "===data====:" + this.voiceData);
        if (TextUtils.equals("voic", this.voiceData)) {
            updateLayout(2);
            setFistImageView(2);
            this.relat_ntgTwoList.setVisibility(0);
            setAnimatorOut(this.relat_ntgTwoList);
            this.animatorOut.setDuration(300);
            this.animatorOut.start();
            this.relat_ntgOneList.setVisibility(8);
        } else if (TextUtils.equals("voicFun", this.voiceData)) {
            updateLayout(3);
            setFistImageView(3);
            this.relat_ntgTwoList.setVisibility(0);
            setAnimatorOut(this.relat_ntgTwoList);
            this.animatorOut.setDuration(300);
            this.animatorOut.start();
            this.relat_ntgOneList.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.voiceData = intent.getStringExtra("voiceData");
        Log.d("ntg55startAction", "onNewIntent:" + this.voiceData);
        if (TextUtils.equals("voic", this.voiceData)) {
            updateLayout(2);
            setFistImageView(2);
            this.relat_ntgTwoList.setVisibility(0);
            setAnimatorOut(this.relat_ntgTwoList);
            this.animatorOut.setDuration(300);
            this.animatorOut.start();
            this.relat_ntgOneList.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.handler.sendEmptyMessageDelayed(4, 1000);
        if (this.ntg6TimeLayout == null) {
            this.ntg6TimeLayout = new Ntg6TimeLayout(this, this.handler);
        }
        this.ntg6TimeLayout.onUpdateView();
        ScanNaviList.getInstance().setMapListScanListener(this);
        initSaveData();
        this.recyclerView.requestFocus();
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

    /* access modifiers changed from: private */
    public void initOneLayout() {
        if (this.ntg6SetSystemLayout == null) {
            Ntg6SystemLayout ntg6SystemLayout = new Ntg6SystemLayout(this, this.handler);
            this.ntg6SetSystemLayout = ntg6SystemLayout;
            ntg6SystemLayout.registIUpdateTwoLayout(this);
        }
        if (this.ntg6NaviLayout == null) {
            Ntg6NaviLayout ntg6NaviLayout2 = new Ntg6NaviLayout(this, this.handler);
            this.ntg6NaviLayout = ntg6NaviLayout2;
            ntg6NaviLayout2.registIUpdateTwoLayout(this);
        }
        if (this.ntg6VoiceLayout == null) {
            this.ntg6VoiceLayout = new Ntg6VoiceLayout(this, this.handler);
        }
        if (this.ntg6VocModeLayout == null) {
            this.ntg6VocModeLayout = new Ntg6VocModeLayout(this, this.handler);
        }
        if (this.ntg6LanguageLayout == null) {
            this.ntg6LanguageLayout = new Ntg6LanguageLayout(this, this.handler);
        }
        if (this.ntg6SystemInfoLayout == null) {
            this.ntg6SystemInfoLayout = new Ntg6SystemInfoLayout(this, this.handler);
        }
        if (this.ntg6FactoryLayout == null) {
            this.ntg6FactoryLayout = new Ntg6FactoryLayout(this, this.handler);
        }
    }

    /* access modifiers changed from: private */
    public void initTwoLayout() {
        if (this.ntg6SetSystemTwo == null) {
            this.ntg6SetSystemTwo = new Ntg6SetSystemTwo(this, this.handler);
        }
        if (this.ntg6NaviTwo == null) {
            this.ntg6NaviTwo = new Ntg6NaviTwo(this, this.handler);
        }
    }

    private void initData() {
        try {
            this.systemTime = new SimpleDateFormat("MM/dd/yyyy,HH:mm").format(new Date(System.currentTimeMillis()));
            this.voiceData = getIntent().getStringExtra("voiceData");
            this.defPwd = PowerManagerApp.getSettingsString(KeyConfig.PASSWORD);
        } catch (Exception e) {
            e.getStackTrace();
            this.defPwd = "1314";
        }
        int[] icons = {R.mipmap.id7_icon_system, R.mipmap.id7_icon_navi, R.mipmap.id7_icon_audio, R.mipmap.id7_icon_eq, R.mipmap.ntg55_list_language, R.mipmap.id7_icon_time, R.mipmap.ntg55_list_info, R.mipmap.id7_icon_android, R.mipmap.id7_icon_factory};
        this.firstImages = new int[]{R.mipmap.ntg55_icon_system, R.mipmap.ntg55_icon_navi, R.mipmap.ntg55_icon_volume, R.mipmap.ntg55_icon_vmodel, R.mipmap.ntg55_icon_language, R.mipmap.ntg55_icon_time, R.mipmap.ntg55_icon_sys_info, R.mipmap.ntg55_icon_android, R.mipmap.ntg55_icon_factory};
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

    /* access modifiers changed from: private */
    public void setAnimatorIn(RelativeLayout view) {
        this.animatorIn = ObjectAnimator.ofFloat(view, "translationX", new float[]{0.0f, (float) UtilsInfo.dip2px(this, 500.0f)});
    }

    /* access modifiers changed from: private */
    public void setAnimatorOut(RelativeLayout view) {
        this.animatorOut = ObjectAnimator.ofFloat(view, "translationX", new float[]{250.0f, 0.0f});
    }

    private void initView() {
        this.relat_Factory = (RelativeLayout) findViewById(R.id.relat_Factory);
        this.tv_systemInfo = (TextView) findViewById(R.id.tv_systemInfo);
        this.tv_systemInfo2 = (TextView) findViewById(R.id.tv_systemInfo2);
        this.tv_systemTime = (TextView) findViewById(R.id.tv_systemTime);
        this.img_funtionImg = (ImageView) findViewById(R.id.img_funtionImg);
        this.relat_ntgOneList = (RelativeLayout) findViewById(R.id.relat_ntgOneList);
        this.relat_ntgTwoList = (RelativeLayout) findViewById(R.id.relat_ntgTwoList);
        this.relat_ntgThreeList = (RelativeLayout) findViewById(R.id.relat_ntgThreeList);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        Ntg6FunctionAdapter ntg6FunctionAdapter = new Ntg6FunctionAdapter(this, this.data);
        this.adapter = ntg6FunctionAdapter;
        this.recyclerView.setAdapter(ntg6FunctionAdapter);
        this.adapter.registOnFunctionClickListener(new Ntg6FunctionAdapter.OnFunctionClickListener() {
            public void functonClick(int pos) {
                if (pos == 8) {
                    Ntg6SettingsActivity.this.relat_Factory.removeAllViews();
                    Ntg6SettingsActivity.this.relat_Factory.addView(Ntg6SettingsActivity.this.ntg6FactoryLayout);
                    Ntg6SettingsActivity.this.relat_Factory.setVisibility(0);
                } else {
                    Ntg6SettingsActivity.this.relat_Factory.setVisibility(8);
                    Ntg6SettingsActivity.this.relat_ntgTwoList.setVisibility(0);
                    Ntg6SettingsActivity.this.updateLayout(pos);
                    Ntg6SettingsActivity ntg6SettingsActivity = Ntg6SettingsActivity.this;
                    ntg6SettingsActivity.setAnimatorOut(ntg6SettingsActivity.relat_ntgTwoList);
                    Ntg6SettingsActivity.this.animatorOut.setDuration(300);
                    Ntg6SettingsActivity.this.animatorOut.start();
                    Ntg6SettingsActivity.this.relat_ntgOneList.setVisibility(8);
                }
                Ntg6SettingsActivity.this.setFistImageView(pos);
            }

            public void funSwitImage(int pos) {
                Log.d("ntg6Image", "index:" + pos);
                if (pos == 8) {
                    Ntg6SettingsActivity.this.relat_Factory.removeAllViews();
                    Ntg6SettingsActivity.this.relat_Factory.addView(Ntg6SettingsActivity.this.ntg6FactoryLayout);
                    Ntg6SettingsActivity.this.relat_Factory.setVisibility(0);
                } else {
                    Ntg6SettingsActivity.this.relat_Factory.setVisibility(8);
                    Ntg6SettingsActivity.this.relat_ntgTwoList.setVisibility(0);
                }
                Ntg6SettingsActivity.this.setFistImageView(pos);
            }
        });
    }

    /* access modifiers changed from: private */
    public void setFistImageView(int index) {
        if (index == 5) {
            this.tv_systemTime.setText(this.systemTime);
            this.tv_systemTime.setVisibility(0);
        } else {
            this.tv_systemTime.setVisibility(8);
        }
        if (index == 6) {
            String allRAM = String.format(getResources().getString(R.string.text_14), new Object[]{UtilsInfo.getRAMSize(this)});
            this.tv_systemInfo2.setVisibility(0);
            this.tv_systemInfo2.setText(allRAM);
            String allROM = String.format(getResources().getString(R.string.text_15), new Object[]{UtilsInfo.getROMSize()});
            this.tv_systemInfo.setVisibility(0);
            this.tv_systemInfo.setText(allROM);
        } else {
            this.tv_systemInfo.setVisibility(8);
            this.tv_systemInfo2.setVisibility(8);
        }
        this.img_funtionImg.setBackgroundResource(this.firstImages[index]);
    }

    /* access modifiers changed from: private */
    public void getLayoutFouse() {
        if (this.ntg6SetSystemLayout == null) {
            Ntg6SystemLayout ntg6SystemLayout = new Ntg6SystemLayout(this, this.handler);
            this.ntg6SetSystemLayout = ntg6SystemLayout;
            ntg6SystemLayout.registIUpdateTwoLayout(this);
        }
        this.ntg6SetSystemLayout.getFocus();
        if (this.ntg6NaviLayout == null) {
            Ntg6NaviLayout ntg6NaviLayout2 = new Ntg6NaviLayout(this, this.handler);
            this.ntg6NaviLayout = ntg6NaviLayout2;
            ntg6NaviLayout2.registIUpdateTwoLayout(this);
        }
        this.ntg6NaviLayout.getFocus();
        if (this.ntg6VoiceLayout == null) {
            this.ntg6VoiceLayout = new Ntg6VoiceLayout(this, this.handler);
        }
        this.ntg6VoiceLayout.getFocus();
        if (this.ntg6VocModeLayout == null) {
            this.ntg6VocModeLayout = new Ntg6VocModeLayout(this, this.handler);
        }
        this.ntg6VocModeLayout.getFocus();
        if (this.ntg6LanguageLayout == null) {
            this.ntg6LanguageLayout = new Ntg6LanguageLayout(this, this.handler);
        }
        this.ntg6LanguageLayout.getFocus();
        this.ntg6TimeLayout.getFocus();
        if (this.ntg6SystemInfoLayout == null) {
            this.ntg6SystemInfoLayout = new Ntg6SystemInfoLayout(this, this.handler);
        }
        this.ntg6SystemInfoLayout.getFocus();
    }

    /* access modifiers changed from: private */
    public void updateLayout(int type) {
        this.relat_ntgTwoList.removeAllViews();
        this.relat_ntgThreeList.removeAllViews();
        this.relat_Factory.removeAllViews();
        switch (type) {
            case 0:
                if (this.ntg6SetSystemLayout == null) {
                    Ntg6SystemLayout ntg6SystemLayout = new Ntg6SystemLayout(this, this.handler);
                    this.ntg6SetSystemLayout = ntg6SystemLayout;
                    ntg6SystemLayout.registIUpdateTwoLayout(this);
                }
                addNtgTwoList(this.ntg6SetSystemLayout);
                if (this.ntg6SetSystemTwo == null) {
                    this.ntg6SetSystemTwo = new Ntg6SetSystemTwo(this, this.handler);
                }
                addNtgThreeList(this.ntg6SetSystemTwo);
                return;
            case 1:
                if (this.ntg6NaviLayout == null) {
                    Ntg6NaviLayout ntg6NaviLayout2 = new Ntg6NaviLayout(this, this.handler);
                    this.ntg6NaviLayout = ntg6NaviLayout2;
                    ntg6NaviLayout2.registIUpdateTwoLayout(this);
                }
                addNtgTwoList(this.ntg6NaviLayout);
                if (this.ntg6NaviTwo == null) {
                    this.ntg6NaviTwo = new Ntg6NaviTwo(this, this.handler);
                }
                addNtgThreeList(this.ntg6NaviTwo);
                return;
            case 2:
                if (this.ntg6VoiceLayout == null) {
                    this.ntg6VoiceLayout = new Ntg6VoiceLayout(this, this.handler);
                }
                addNtgTwoList(this.ntg6VoiceLayout);
                return;
            case 3:
                if (this.ntg6VocModeLayout == null) {
                    this.ntg6VocModeLayout = new Ntg6VocModeLayout(this, this.handler);
                }
                addNtgTwoList(this.ntg6VocModeLayout);
                return;
            case 4:
                if (this.ntg6LanguageLayout == null) {
                    this.ntg6LanguageLayout = new Ntg6LanguageLayout(this, this.handler);
                }
                addNtgTwoList(this.ntg6LanguageLayout);
                return;
            case 5:
                addNtgTwoList(this.ntg6TimeLayout);
                return;
            case 6:
                if (this.ntg6SystemInfoLayout == null) {
                    this.ntg6SystemInfoLayout = new Ntg6SystemInfoLayout(this, this.handler);
                }
                addNtgTwoList(this.ntg6SystemInfoLayout);
                return;
            case 7:
                sendToApp("com.android.settings", "com.android.settings.Settings");
                this.handler.sendEmptyMessage(1);
                return;
            default:
                return;
        }
    }

    public void updateTwoLayout(int type, int shwoIndex) {
        this.relat_ntgThreeList.setVisibility(0);
        this.relat_ntgTwoList.setVisibility(8);
        switch (type) {
            case 1:
                this.ntg6SetSystemTwo.showLayout(shwoIndex);
                break;
            case 2:
                this.ntg6NaviTwo.showLayout(shwoIndex);
                break;
        }
        setAnimatorOut(this.relat_ntgThreeList);
        this.animatorOut.setDuration(300);
        this.animatorOut.start();
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == 1 && event.getKeyCode() == 21) {
            onBackPressed();
        }
        return super.dispatchKeyEvent(event);
    }

    private void addNtgTwoList(View view) {
        if (view == null) {
            new NullPointerException("addNtgTwoList  view is null").printStackTrace();
        } else {
            this.relat_ntgTwoList.addView(view);
        }
    }

    private void addNtgThreeList(View view) {
        if (view == null) {
            new NullPointerException("addNtgThreeList view is null").printStackTrace();
        } else {
            this.relat_ntgThreeList.addView(view);
        }
    }

    public void onBackPressed() {
        Log.d("NTG6ShowLayout", "one:" + this.relat_ntgOneList.isShown());
        Log.d("NTG6ShowLayout", "two:" + this.relat_ntgTwoList.isShown());
        Log.d("NTG6ShowLayout", "three:" + this.relat_ntgThreeList.isShown());
        if (this.relat_ntgOneList.isShown()) {
            super.onBackPressed();
        } else if (this.relat_ntgTwoList.isShown()) {
            this.handler.sendEmptyMessage(1);
        } else if (this.relat_ntgThreeList.isShown()) {
            this.handler.sendEmptyMessage(3);
        } else {
            super.onBackPressed();
        }
    }

    public void onScanFinish(List<MapBean> mapList2) {
        this.mapList = mapList2;
        this.handler.sendEmptyMessage(5);
        Log.d("Navi", " finish " + mapList2.size());
    }
}
