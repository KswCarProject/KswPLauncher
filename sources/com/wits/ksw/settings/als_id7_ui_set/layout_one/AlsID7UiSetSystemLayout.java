package com.wits.ksw.settings.als_id7_ui_set.layout_one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.ExceptionPrint;
import com.wits.ksw.settings.als_id7_ui_set.interfaces.AlsID7UiIUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes6.dex */
public class AlsID7UiSetSystemLayout extends RelativeLayout implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ImageView aux_bottom_iv;
    private CheckBox cbox_sysDcgj;
    private CheckBox cbox_sysDcjy;
    private CheckBox cbox_sysDcld;
    private CheckBox cbox_sysHjs;
    private CheckBox cbox_sysXcjz;
    private int cheVideo;
    private Context context;
    private int dcgj;
    private int dcjy;
    private int dcld;
    private TextView fuelUnitView;
    private int housi;
    private int nbauxsw;
    private TextView tempUnitView;
    private TextView tv_music_app;
    private TextView tv_sysBgld;
    private TextView tv_sysCaux;
    private TextView tv_sysDcsxt;
    private TextView tv_video_app;
    private AlsID7UiIUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(AlsID7UiIUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public AlsID7UiSetSystemLayout(Context context) {
        super(context);
        this.housi = 0;
        this.cheVideo = 0;
        this.dcgj = 0;
        this.dcld = 0;
        this.dcjy = 0;
        this.nbauxsw = 0;
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.als_id7_ui_layout_set_system, (ViewGroup) null);
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

    private void initView(View view) {
        this.tempUnitView = (TextView) view.findViewById(C0899R.C0901id.tv_sysTempUnit);
        this.fuelUnitView = (TextView) view.findViewById(C0899R.C0901id.tv_sysFuelUnit);
        this.tv_sysDcsxt = (TextView) view.findViewById(C0899R.C0901id.tv_sysDcsxt);
        this.tv_sysBgld = (TextView) view.findViewById(C0899R.C0901id.tv_sysBgld);
        this.cbox_sysHjs = (CheckBox) view.findViewById(C0899R.C0901id.cbox_sysHjs);
        this.cbox_sysXcjz = (CheckBox) view.findViewById(C0899R.C0901id.cbox_sysXcjz);
        this.cbox_sysDcgj = (CheckBox) view.findViewById(C0899R.C0901id.cbox_sysDcgj);
        this.cbox_sysDcld = (CheckBox) view.findViewById(C0899R.C0901id.cbox_sysDcld);
        this.tv_sysCaux = (TextView) view.findViewById(C0899R.C0901id.tv_sysCaux);
        this.aux_bottom_iv = (ImageView) view.findViewById(C0899R.C0901id.aux_bottom_iv);
        this.cbox_sysDcjy = (CheckBox) view.findViewById(C0899R.C0901id.cbox_sysDcjy);
        this.cbox_sysHjs.setChecked(this.housi != 0);
        this.cbox_sysXcjz.setChecked(this.cheVideo != 0);
        this.cbox_sysDcgj.setChecked(this.dcgj != 0);
        this.cbox_sysDcld.setChecked(this.dcld != 0);
        this.cbox_sysDcjy.setChecked(this.dcjy != 0);
        this.tv_sysDcsxt.setOnClickListener(this);
        this.tv_sysBgld.setOnClickListener(this);
        this.tv_sysCaux.setOnClickListener(this);
        this.tempUnitView.setOnClickListener(this);
        this.fuelUnitView.setOnClickListener(this);
        this.cbox_sysHjs.setOnCheckedChangeListener(this);
        this.cbox_sysXcjz.setOnCheckedChangeListener(this);
        this.cbox_sysDcgj.setOnCheckedChangeListener(this);
        this.cbox_sysDcld.setOnCheckedChangeListener(this);
        this.cbox_sysDcjy.setOnCheckedChangeListener(this);
        if (this.nbauxsw == 3) {
            this.tv_sysCaux.setVisibility(0);
            this.aux_bottom_iv.setVisibility(0);
        } else {
            this.tv_sysCaux.setVisibility(8);
            this.aux_bottom_iv.setVisibility(8);
        }
        this.tv_music_app = (TextView) view.findViewById(C0899R.C0901id.tv_music_app);
        this.tv_video_app = (TextView) view.findViewById(C0899R.C0901id.tv_video_app);
        this.tv_music_app.setOnClickListener(this);
        this.tv_video_app.setOnClickListener(this);
    }

    public void resetTextColor() {
        this.tv_sysDcsxt.setTextColor(-1);
        this.tv_sysBgld.setTextColor(-1);
        this.tv_sysCaux.setTextColor(-1);
        this.tempUnitView.setTextColor(-1);
        this.fuelUnitView.setTextColor(-1);
        this.tv_music_app.setTextColor(-1);
        this.tv_video_app.setTextColor(-1);
        AlsID7UiIUpdateTwoLayout alsID7UiIUpdateTwoLayout = this.updateTwoLayout;
        if (alsID7UiIUpdateTwoLayout != null) {
            alsID7UiIUpdateTwoLayout.updateTwoLayout(1, 0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (this.updateTwoLayout == null) {
            ExceptionPrint.print("updateTwoLayout is null");
            return;
        }
        resetTextColor();
        switch (v.getId()) {
            case C0899R.C0901id.tv_music_app /* 2131297952 */:
                this.tv_music_app.setTextColor(SkinCompatResources.getColor(this.context, C0899R.color.als_id7_ui_text_color));
                this.updateTwoLayout.updateTwoLayout(1, 6);
                return;
            case C0899R.C0901id.tv_sysBgld /* 2131297981 */:
                this.tv_sysBgld.setTextColor(SkinCompatResources.getColor(this.context, C0899R.color.als_id7_ui_text_color));
                this.updateTwoLayout.updateTwoLayout(1, 2);
                return;
            case C0899R.C0901id.tv_sysCaux /* 2131297982 */:
                this.tv_sysCaux.setTextColor(SkinCompatResources.getColor(this.context, C0899R.color.als_id7_ui_text_color));
                this.updateTwoLayout.updateTwoLayout(1, 3);
                return;
            case C0899R.C0901id.tv_sysDcsxt /* 2131297983 */:
                this.tv_sysDcsxt.setTextColor(SkinCompatResources.getColor(this.context, C0899R.color.als_id7_ui_text_color));
                this.updateTwoLayout.updateTwoLayout(1, 1);
                return;
            case C0899R.C0901id.tv_sysFuelUnit /* 2131297984 */:
                this.fuelUnitView.setTextColor(SkinCompatResources.getColor(this.context, C0899R.color.als_id7_ui_text_color));
                this.updateTwoLayout.updateTwoLayout(1, 5);
                return;
            case C0899R.C0901id.tv_sysTempUnit /* 2131297985 */:
                this.tempUnitView.setTextColor(SkinCompatResources.getColor(this.context, C0899R.color.als_id7_ui_text_color));
                this.updateTwoLayout.updateTwoLayout(1, 4);
                return;
            case C0899R.C0901id.tv_video_app /* 2131298003 */:
                this.tv_video_app.setTextColor(SkinCompatResources.getColor(this.context, C0899R.color.als_id7_ui_text_color));
                this.updateTwoLayout.updateTwoLayout(1, 7);
                return;
            default:
                return;
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case C0899R.C0901id.cbox_sysDcgj /* 2131296756 */:
                FileUtils.savaData(KeyConfig.DAO_CHE_GJ, isChecked);
                return;
            case C0899R.C0901id.cbox_sysDcjy /* 2131296757 */:
                FileUtils.savaData(KeyConfig.DAO_CHE_JY, isChecked);
                return;
            case C0899R.C0901id.cbox_sysDcld /* 2131296758 */:
                FileUtils.savaData(KeyConfig.DAO_CHE_LD, isChecked);
                return;
            case C0899R.C0901id.cbox_sysHjs /* 2131296759 */:
                FileUtils.savaData(KeyConfig.HOU_SHI_SX, isChecked);
                return;
            case C0899R.C0901id.cbox_sysXcjz /* 2131296760 */:
                FileUtils.savaData(KeyConfig.XING_CHE_JZSP, isChecked);
                return;
            default:
                return;
        }
    }
}
