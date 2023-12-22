package com.wits.ksw.settings.ntg6;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.wits.ksw.C0899R;
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

/* loaded from: classes7.dex */
public class Ntg6SettingsActivity extends BaseActivity implements IUpdateTwoLayout, ScanNaviList.OnMapListScanListener {
    private Ntg6FunctionAdapter adapter;
    private ObjectAnimator animatorIn;
    private ObjectAnimator animatorOut;
    private List<FunctionBean> data;
    private int[] firstImages;
    private ImageView img_funtionImg;
    private LinearLayoutManager layoutManager;
    private List<MapBean> mapList;
    private Ntg6FactoryLayout ntg6FactoryLayout;
    private Ntg6LanguageLayout ntg6LanguageLayout;
    private Ntg6NaviLayout ntg6NaviLayout;
    private Ntg6NaviTwo ntg6NaviTwo;
    private Ntg6SystemLayout ntg6SetSystemLayout;
    private Ntg6SetSystemTwo ntg6SetSystemTwo;
    private Ntg6SystemInfoLayout ntg6SystemInfoLayout;
    private Ntg6TimeLayout ntg6TimeLayout;
    private Ntg6VocModeLayout ntg6VocModeLayout;
    private Ntg6VoiceLayout ntg6VoiceLayout;
    private RecyclerView recyclerView;
    private RelativeLayout relat_Factory;
    private RelativeLayout relat_ntgOneList;
    private RelativeLayout relat_ntgThreeList;
    private RelativeLayout relat_ntgTwoList;
    private TextView tv_systemInfo;
    private TextView tv_systemInfo2;
    private TextView tv_systemTime;
    private String voiceData;
    private String defPwd = "1314";
    private String systemTime = "";
    Handler handler = new Handler() { // from class: com.wits.ksw.settings.ntg6.Ntg6SettingsActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Ntg6SettingsActivity.this.relat_ntgOneList.setVisibility(0);
                    Ntg6SettingsActivity ntg6SettingsActivity = Ntg6SettingsActivity.this;
                    ntg6SettingsActivity.setAnimatorIn(ntg6SettingsActivity.relat_ntgTwoList);
                    Ntg6SettingsActivity.this.animatorIn.setDuration(600L);
                    Ntg6SettingsActivity.this.animatorIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wits.ksw.settings.ntg6.Ntg6SettingsActivity.2.1
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float frac = animation.getAnimatedFraction();
                            if (((int) (10.0f * frac)) == 1) {
                                Ntg6SettingsActivity.this.setAnimatorOut(Ntg6SettingsActivity.this.relat_ntgOneList);
                                Ntg6SettingsActivity.this.animatorOut.setDuration(600L);
                                Ntg6SettingsActivity.this.animatorOut.start();
                            }
                        }
                    });
                    Ntg6SettingsActivity.this.animatorIn.start();
                    Ntg6SettingsActivity.this.recyclerView.requestFocus();
                    return;
                case 2:
                    String inputPwd = (String) msg.obj;
                    if (!TextUtils.equals(Ntg6SettingsActivity.this.defPwd, inputPwd)) {
                        Ntg6SettingsActivity.this.ntg6FactoryLayout.SetTextEEro();
                        Ntg6SettingsActivity ntg6SettingsActivity2 = Ntg6SettingsActivity.this;
                        Toast.makeText(ntg6SettingsActivity2, ntg6SettingsActivity2.getString(C0899R.string.lb_password_error), 0).show();
                        return;
                    }
                    Ntg6SettingsActivity.this.startActivity(new Intent(Ntg6SettingsActivity.this, FactoryActivity.class));
                    Ntg6SettingsActivity.this.handler.sendEmptyMessage(1);
                    Ntg6SettingsActivity.this.finish();
                    return;
                case 3:
                    Ntg6SettingsActivity.this.relat_ntgTwoList.setVisibility(0);
                    Ntg6SettingsActivity ntg6SettingsActivity3 = Ntg6SettingsActivity.this;
                    ntg6SettingsActivity3.setAnimatorIn(ntg6SettingsActivity3.relat_ntgThreeList);
                    Ntg6SettingsActivity.this.animatorIn.setDuration(600L);
                    Ntg6SettingsActivity.this.animatorIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wits.ksw.settings.ntg6.Ntg6SettingsActivity.2.2
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float frac = animation.getAnimatedFraction();
                            if (((int) (10.0f * frac)) == 1) {
                                Ntg6SettingsActivity.this.setAnimatorOut(Ntg6SettingsActivity.this.relat_ntgTwoList);
                                Ntg6SettingsActivity.this.animatorOut.setDuration(600L);
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

    @Override // com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0899R.C0902layout.activity_benchi_settings);
        this.handler.sendEmptyMessageDelayed(4, 300L);
        initData();
        initView();
        Log.d("ntg55startAction", "===data====:" + this.voiceData);
        if (TextUtils.equals("voic", this.voiceData)) {
            updateLayout(2);
            setFistImageView(2);
            this.relat_ntgTwoList.setVisibility(0);
            setAnimatorOut(this.relat_ntgTwoList);
            this.animatorOut.setDuration(300L);
            this.animatorOut.start();
            this.relat_ntgOneList.setVisibility(8);
        } else if (TextUtils.equals("voicFun", this.voiceData)) {
            updateLayout(3);
            setFistImageView(3);
            this.relat_ntgTwoList.setVisibility(0);
            setAnimatorOut(this.relat_ntgTwoList);
            this.animatorOut.setDuration(300L);
            this.animatorOut.start();
            this.relat_ntgOneList.setVisibility(8);
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.voiceData = intent.getStringExtra("voiceData");
        Log.d("ntg55startAction", "onNewIntent:" + this.voiceData);
        if (TextUtils.equals("voic", this.voiceData)) {
            updateLayout(2);
            setFistImageView(2);
            this.relat_ntgTwoList.setVisibility(0);
            setAnimatorOut(this.relat_ntgTwoList);
            this.animatorOut.setDuration(300L);
            this.animatorOut.start();
            this.relat_ntgOneList.setVisibility(8);
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.handler.sendEmptyMessageDelayed(4, 1000L);
        if (this.ntg6TimeLayout == null) {
            this.ntg6TimeLayout = new Ntg6TimeLayout(this, this.handler);
        }
        this.ntg6TimeLayout.onUpdateView();
        ScanNaviList.getInstance().setMapListScanListener(this);
        initSaveData();
        this.recyclerView.requestFocus();
        Ntg6TimeLayout ntg6TimeLayout = this.ntg6TimeLayout;
        if (ntg6TimeLayout != null) {
            ntg6TimeLayout.setOnTimeListener(new Ntg6TimeLayout.onTimeListener() { // from class: com.wits.ksw.settings.ntg6.Ntg6SettingsActivity.1
                @Override // com.wits.ksw.settings.ntg6.one_layout.Ntg6TimeLayout.onTimeListener
                public void getTieType() {
                    new Handler().postDelayed(new Runnable() { // from class: com.wits.ksw.settings.ntg6.Ntg6SettingsActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                int timeZhis = PowerManagerApp.getSettingsInt(KeyConfig.TIME_FORMAT);
                                Ntg6SettingsActivity.this.tv_systemTime.setText(Ntg6SettingsActivity.this.time(timeZhis));
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 500L);
                }
            });
        }
    }

    public String time(int zhiS) {
        String timeFormat = "MM/dd/yyyy HH:mm";
        if (zhiS == 0) {
            timeFormat = "MM/dd/yyyy,HH:mm";
        } else if (1 == zhiS) {
            timeFormat = "MM/dd/yyyy,hh:mm a";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(timeFormat);
        Date date = new Date(System.currentTimeMillis());
        Log.d("TAG_TIME", "time: " + dateFormat.format(date));
        return dateFormat.format(date);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        ScanNaviList.getInstance().setMapListScanListener(null);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void initOneLayout() {
        if (this.ntg6SetSystemLayout == null) {
            Ntg6SystemLayout ntg6SystemLayout = new Ntg6SystemLayout(this, this.handler);
            this.ntg6SetSystemLayout = ntg6SystemLayout;
            ntg6SystemLayout.registIUpdateTwoLayout(this);
        }
        if (this.ntg6NaviLayout == null) {
            Ntg6NaviLayout ntg6NaviLayout = new Ntg6NaviLayout(this, this.handler);
            this.ntg6NaviLayout = ntg6NaviLayout;
            ntg6NaviLayout.registIUpdateTwoLayout(this);
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

    /* JADX INFO: Access modifiers changed from: private */
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
            this.voiceData = getIntent().getStringExtra("voiceData");
            this.defPwd = PowerManagerApp.getSettingsString(KeyConfig.PASSWORD);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.d("Xu-Log", "\u83b7\u53d6\u5de5\u5382\u8bbe\u7f6e\u5bc6\u7801: " + e.toString());
            this.defPwd = "1314";
        }
        try {
            int timeZhis = PowerManagerApp.getSettingsInt(KeyConfig.TIME_FORMAT);
            String time = time(timeZhis);
            this.systemTime = time;
            this.tv_systemTime.setText(time);
        } catch (Exception e2) {
            e2.getStackTrace();
            Log.d("Xu-Log", "\u83b7\u53d6\u65f6\u95f4\u5236\u5f0f: " + e2.toString());
        }
        int[] icons = {C0899R.mipmap.id7_icon_system, C0899R.mipmap.id7_icon_navi, C0899R.mipmap.id7_icon_audio, C0899R.mipmap.id7_icon_eq, C0899R.mipmap.ntg55_list_language, C0899R.mipmap.id7_icon_time, C0899R.mipmap.ntg55_list_info, C0899R.mipmap.id7_icon_android, C0899R.mipmap.id7_icon_factory};
        this.firstImages = new int[]{C0899R.mipmap.ntg55_icon_system, C0899R.mipmap.ntg55_icon_navi, C0899R.mipmap.ntg55_icon_volume, C0899R.mipmap.ntg55_icon_vmodel, C0899R.mipmap.ntg55_icon_language, C0899R.mipmap.ntg55_icon_time, C0899R.mipmap.ntg55_icon_sys_info, C0899R.mipmap.ntg55_icon_android, C0899R.mipmap.ntg55_icon_factory};
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

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnimatorIn(RelativeLayout view) {
        this.animatorIn = ObjectAnimator.ofFloat(view, "translationX", 0.0f, UtilsInfo.dip2px(this, 500.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnimatorOut(RelativeLayout view) {
        this.animatorOut = ObjectAnimator.ofFloat(view, "translationX", 250.0f, 0.0f);
    }

    private void initView() {
        this.relat_Factory = (RelativeLayout) findViewById(C0899R.C0901id.relat_Factory);
        this.tv_systemInfo = (TextView) findViewById(C0899R.C0901id.tv_systemInfo);
        this.tv_systemInfo2 = (TextView) findViewById(C0899R.C0901id.tv_systemInfo2);
        this.tv_systemTime = (TextView) findViewById(C0899R.C0901id.tv_systemTime);
        this.img_funtionImg = (ImageView) findViewById(C0899R.C0901id.img_funtionImg);
        this.relat_ntgOneList = (RelativeLayout) findViewById(C0899R.C0901id.relat_ntgOneList);
        this.relat_ntgTwoList = (RelativeLayout) findViewById(C0899R.C0901id.relat_ntgTwoList);
        this.relat_ntgThreeList = (RelativeLayout) findViewById(C0899R.C0901id.relat_ntgThreeList);
        this.recyclerView = (RecyclerView) findViewById(C0899R.C0901id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        Ntg6FunctionAdapter ntg6FunctionAdapter = new Ntg6FunctionAdapter(this, this.data);
        this.adapter = ntg6FunctionAdapter;
        this.recyclerView.setAdapter(ntg6FunctionAdapter);
        this.adapter.registOnFunctionClickListener(new Ntg6FunctionAdapter.OnFunctionClickListener() { // from class: com.wits.ksw.settings.ntg6.Ntg6SettingsActivity.3
            @Override // com.wits.ksw.settings.ntg6.adapter.Ntg6FunctionAdapter.OnFunctionClickListener
            public void functonClick(int pos) {
                if (pos == 8) {
                    if (Ntg6SettingsActivity.this.ntg6FactoryLayout == null) {
                        Ntg6SettingsActivity ntg6SettingsActivity = Ntg6SettingsActivity.this;
                        Ntg6SettingsActivity ntg6SettingsActivity2 = Ntg6SettingsActivity.this;
                        ntg6SettingsActivity.ntg6FactoryLayout = new Ntg6FactoryLayout(ntg6SettingsActivity2, ntg6SettingsActivity2.handler);
                    }
                    Ntg6SettingsActivity.this.relat_Factory.removeAllViews();
                    Ntg6SettingsActivity.this.relat_Factory.addView(Ntg6SettingsActivity.this.ntg6FactoryLayout);
                    Ntg6SettingsActivity.this.relat_Factory.setVisibility(0);
                } else {
                    Ntg6SettingsActivity.this.relat_Factory.setVisibility(8);
                    Ntg6SettingsActivity.this.relat_ntgTwoList.setVisibility(0);
                    Ntg6SettingsActivity.this.updateLayout(pos);
                    Ntg6SettingsActivity ntg6SettingsActivity3 = Ntg6SettingsActivity.this;
                    ntg6SettingsActivity3.setAnimatorOut(ntg6SettingsActivity3.relat_ntgTwoList);
                    Ntg6SettingsActivity.this.animatorOut.setDuration(300L);
                    Ntg6SettingsActivity.this.animatorOut.start();
                    Ntg6SettingsActivity.this.relat_ntgOneList.setVisibility(8);
                }
                Ntg6SettingsActivity.this.setFistImageView(pos);
            }

            @Override // com.wits.ksw.settings.ntg6.adapter.Ntg6FunctionAdapter.OnFunctionClickListener
            public void funSwitImage(int pos) {
                Log.d("ntg6Image", "index:" + pos);
                if (pos == 8) {
                    if (Ntg6SettingsActivity.this.ntg6FactoryLayout == null) {
                        Ntg6SettingsActivity ntg6SettingsActivity = Ntg6SettingsActivity.this;
                        Ntg6SettingsActivity ntg6SettingsActivity2 = Ntg6SettingsActivity.this;
                        ntg6SettingsActivity.ntg6FactoryLayout = new Ntg6FactoryLayout(ntg6SettingsActivity2, ntg6SettingsActivity2.handler);
                    }
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

    /* JADX INFO: Access modifiers changed from: private */
    public void setFistImageView(int index) {
        if (index != 5) {
            this.tv_systemTime.setVisibility(8);
        } else {
            this.tv_systemTime.setText(this.systemTime);
            this.tv_systemTime.setVisibility(0);
        }
        if (index != 6) {
            this.tv_systemInfo.setVisibility(8);
            this.tv_systemInfo2.setVisibility(8);
        } else {
            String allRAM = String.format(getResources().getString(C0899R.string.text_14), UtilsInfo.getRAMSize(this));
            this.tv_systemInfo2.setVisibility(0);
            this.tv_systemInfo2.setText(allRAM);
            String allROM = String.format(getResources().getString(C0899R.string.text_15), UtilsInfo.getROMSize());
            this.tv_systemInfo.setVisibility(0);
            this.tv_systemInfo.setText(allROM);
        }
        this.img_funtionImg.setBackgroundResource(this.firstImages[index]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getLayoutFouse() {
        if (this.ntg6SetSystemLayout == null) {
            Ntg6SystemLayout ntg6SystemLayout = new Ntg6SystemLayout(this, this.handler);
            this.ntg6SetSystemLayout = ntg6SystemLayout;
            ntg6SystemLayout.registIUpdateTwoLayout(this);
        }
        this.ntg6SetSystemLayout.getFocus();
        if (this.ntg6NaviLayout == null) {
            Ntg6NaviLayout ntg6NaviLayout = new Ntg6NaviLayout(this, this.handler);
            this.ntg6NaviLayout = ntg6NaviLayout;
            ntg6NaviLayout.registIUpdateTwoLayout(this);
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

    /* JADX INFO: Access modifiers changed from: private */
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
                    Ntg6NaviLayout ntg6NaviLayout = new Ntg6NaviLayout(this, this.handler);
                    this.ntg6NaviLayout = ntg6NaviLayout;
                    ntg6NaviLayout.registIUpdateTwoLayout(this);
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

    @Override // com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout
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
        this.animatorOut.setDuration(300L);
        this.animatorOut.start();
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == 1 && event.getKeyCode() == 21) {
            onBackPressed();
        }
        return super.dispatchKeyEvent(event);
    }

    private void addNtgTwoList(View view) {
        if (view == null) {
            NullPointerException exception = new NullPointerException("addNtgTwoList  view is null");
            exception.printStackTrace();
            return;
        }
        this.relat_ntgTwoList.addView(view);
    }

    private void addNtgThreeList(View view) {
        if (view == null) {
            NullPointerException exception = new NullPointerException("addNtgThreeList view is null");
            exception.printStackTrace();
            return;
        }
        this.relat_ntgThreeList.addView(view);
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
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

    @Override // com.wits.ksw.settings.utlis_view.ScanNaviList.OnMapListScanListener
    public void onScanFinish(List<MapBean> mapList) {
        this.mapList = mapList;
        this.handler.sendEmptyMessage(5);
        Log.d("Navi", " finish " + mapList.size());
    }
}
