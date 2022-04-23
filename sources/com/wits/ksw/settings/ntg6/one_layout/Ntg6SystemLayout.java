package com.wits.ksw.settings.ntg6.one_layout;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.ExceptionPrint;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class Ntg6SystemLayout extends RelativeLayout implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private TextView benzSysTempUnit;
    private CheckBox cbox_sysDcgj;
    private CheckBox cbox_sysDcjy;
    private CheckBox cbox_sysDcld;
    private CheckBox cbox_sysHjs;
    private CheckBox cbox_sysXcjz;
    private int cheVideo = 0;
    private Context context;
    private int dcgj = 0;
    private int dcjy = 0;
    private int dcld = 0;
    /* access modifiers changed from: private */
    public Handler handler;
    private int housi = 0;
    private ImageView img_TwoBack;
    private int nbauxsw = 0;
    private TextView tv_music_app;
    private TextView tv_sysBgld;
    private TextView tv_sysCaux;
    private TextView tv_sysDcsxt;
    private TextView tv_video_app;
    private IUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public Ntg6SystemLayout(Context context2, Handler handler2) {
        super(context2);
        this.context = context2;
        this.handler = handler2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_ntg6_systemset_one, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
        try {
            this.housi = PowerManagerApp.getSettingsInt(KeyConfig.HOU_SHI_SX);
            this.cheVideo = PowerManagerApp.getSettingsInt(KeyConfig.XING_CHE_JZSP);
            this.dcgj = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_GJ);
            this.dcld = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_LD);
            this.dcjy = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_JY);
            this.nbauxsw = PowerManagerApp.getSettingsInt(KeyConfig.NBT_AUX_SW);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void getFocus() {
        this.img_TwoBack.requestFocus();
    }

    private void initView(View view) {
        this.benzSysTempUnit = (TextView) view.findViewById(R.id.benz_sysTempUnit);
        this.img_TwoBack = (ImageView) view.findViewById(R.id.img_TwoBack);
        this.tv_sysDcsxt = (TextView) view.findViewById(R.id.tv_sysDcsxt);
        this.tv_sysBgld = (TextView) view.findViewById(R.id.tv_sysBgld);
        this.tv_sysCaux = (TextView) view.findViewById(R.id.tv_sysCaux);
        this.cbox_sysHjs = (CheckBox) view.findViewById(R.id.cbox_sysHjs);
        this.cbox_sysXcjz = (CheckBox) view.findViewById(R.id.cbox_sysXcjz);
        this.cbox_sysDcgj = (CheckBox) view.findViewById(R.id.cbox_sysDcgj);
        this.cbox_sysDcld = (CheckBox) view.findViewById(R.id.cbox_sysDcld);
        this.cbox_sysDcjy = (CheckBox) view.findViewById(R.id.cbox_sysDcjy);
        boolean z = true;
        this.cbox_sysHjs.setChecked(this.housi != 0);
        this.cbox_sysXcjz.setChecked(this.cheVideo != 0);
        this.cbox_sysDcgj.setChecked(this.dcgj != 0);
        this.cbox_sysDcld.setChecked(this.dcld != 0);
        CheckBox checkBox = this.cbox_sysDcjy;
        if (this.dcjy == 0) {
            z = false;
        }
        checkBox.setChecked(z);
        this.tv_sysDcsxt.setOnClickListener(this);
        this.tv_sysBgld.setOnClickListener(this);
        this.tv_sysCaux.setOnClickListener(this);
        this.benzSysTempUnit.setOnClickListener(this);
        this.cbox_sysHjs.setOnCheckedChangeListener(this);
        this.cbox_sysXcjz.setOnCheckedChangeListener(this);
        this.cbox_sysDcgj.setOnCheckedChangeListener(this);
        this.cbox_sysDcld.setOnCheckedChangeListener(this);
        this.cbox_sysDcjy.setOnCheckedChangeListener(this);
        this.img_TwoBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Ntg6SystemLayout.this.handler.sendEmptyMessage(1);
            }
        });
        if (this.nbauxsw == 3) {
            this.tv_sysCaux.setVisibility(0);
        } else {
            this.tv_sysCaux.setVisibility(8);
        }
        this.tv_music_app = (TextView) view.findViewById(R.id.tv_music_app);
        this.tv_video_app = (TextView) view.findViewById(R.id.tv_video_app);
        this.tv_music_app.setOnClickListener(this);
        this.tv_video_app.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (this.updateTwoLayout == null) {
            ExceptionPrint.print("updateTwoLayout is null");
            return;
        }
        switch (v.getId()) {
            case R.id.benz_sysTempUnit /*2131296463*/:
                this.updateTwoLayout.updateTwoLayout(1, 4);
                return;
            case R.id.tv_music_app /*2131297598*/:
                this.updateTwoLayout.updateTwoLayout(1, 5);
                return;
            case R.id.tv_sysBgld /*2131297618*/:
                this.updateTwoLayout.updateTwoLayout(1, 2);
                return;
            case R.id.tv_sysCaux /*2131297619*/:
                this.updateTwoLayout.updateTwoLayout(1, 3);
                return;
            case R.id.tv_sysDcsxt /*2131297620*/:
                this.updateTwoLayout.updateTwoLayout(1, 1);
                return;
            case R.id.tv_video_app /*2131297637*/:
                this.updateTwoLayout.updateTwoLayout(1, 6);
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cbox_sysDcgj /*2131296571*/:
                FileUtils.savaData(KeyConfig.DAO_CHE_GJ, isChecked);
                return;
            case R.id.cbox_sysDcjy /*2131296572*/:
                FileUtils.savaData(KeyConfig.DAO_CHE_JY, isChecked);
                return;
            case R.id.cbox_sysDcld /*2131296573*/:
                FileUtils.savaData(KeyConfig.DAO_CHE_LD, isChecked);
                return;
            case R.id.cbox_sysHjs /*2131296574*/:
                FileUtils.savaData(KeyConfig.HOU_SHI_SX, isChecked);
                return;
            case R.id.cbox_sysXcjz /*2131296575*/:
                FileUtils.savaData(KeyConfig.XING_CHE_JZSP, isChecked);
                return;
            default:
                return;
        }
    }
}
