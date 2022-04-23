package com.wits.ksw.settings.ntg6.one_layout;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class Ntg6VocModeLayout extends RelativeLayout implements SeekBar.OnSeekBarChangeListener {
    private int barMax = 24;
    private Context context;
    private int di = 12;
    private int eqModel = 0;
    private int ga = 12;
    /* access modifiers changed from: private */
    public Handler handler;
    private ImageView img_TwoBack;
    private RadioGroup rdg_vocmd;
    private SeekBar seekbar_mdi;
    private SeekBar seekbar_mgo;
    private SeekBar seekbar_mzh;
    private TextView tv_mdiSize;
    private TextView tv_mgoSize;
    private TextView tv_mzhSize;
    private IUpdateTwoLayout updateTwoLayout;
    private int zo = 12;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public Ntg6VocModeLayout(Context context2, Handler handler2) {
        super(context2);
        this.handler = handler2;
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_ntg6_voc_model, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
        try {
            this.di = PowerManagerApp.getSettingsInt(KeyConfig.EQ_BASS);
            this.zo = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MIDDLE);
            this.ga = PowerManagerApp.getSettingsInt(KeyConfig.EQ_TREBLE);
            this.eqModel = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MODE);
            Log.d("BenchiEQ", "di:" + this.di + "\tzo:" + this.zo + "\tga:" + this.ga + "\tmodel:" + this.eqModel);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void getFocus() {
        this.img_TwoBack.requestFocus();
    }

    private void initView(View view) {
        this.img_TwoBack = (ImageView) view.findViewById(R.id.img_TwoBack);
        this.rdg_vocmd = (RadioGroup) view.findViewById(R.id.rdg_vocmd);
        this.tv_mdiSize = (TextView) view.findViewById(R.id.tv_mdiSize);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekbar_mdi);
        this.seekbar_mdi = seekBar;
        seekBar.setMax(this.barMax);
        this.tv_mzhSize = (TextView) view.findViewById(R.id.tv_mzhSize);
        SeekBar seekBar2 = (SeekBar) view.findViewById(R.id.seekbar_mzh);
        this.seekbar_mzh = seekBar2;
        seekBar2.setMax(this.barMax);
        this.tv_mgoSize = (TextView) view.findViewById(R.id.tv_mgoSize);
        SeekBar seekBar3 = (SeekBar) view.findViewById(R.id.seekbar_mgo);
        this.seekbar_mgo = seekBar3;
        seekBar3.setMax(this.barMax);
        this.seekbar_mdi.setOnSeekBarChangeListener(this);
        this.seekbar_mzh.setOnSeekBarChangeListener(this);
        this.seekbar_mgo.setOnSeekBarChangeListener(this);
        switch (this.eqModel) {
            case 0:
                this.rdg_vocmd.check(R.id.rdb_vocmd1);
                break;
            case 1:
                this.rdg_vocmd.check(R.id.rdb_vocmd2);
                break;
            case 2:
                this.rdg_vocmd.check(R.id.rdb_vocmd3);
                break;
            case 3:
                this.rdg_vocmd.check(R.id.rdb_vocmd4);
                break;
            case 4:
                this.rdg_vocmd.check(R.id.rdb_vocmd5);
                break;
            case 5:
                this.rdg_vocmd.check(R.id.rdb_vocmd6);
                break;
        }
        initBarView(this.eqModel);
        this.rdg_vocmd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdb_vocmd1 /*2131297197*/:
                        Ntg6VocModeLayout.this.setUpdateTwoLayout(0);
                        return;
                    case R.id.rdb_vocmd2 /*2131297198*/:
                        Ntg6VocModeLayout.this.setUpdateTwoLayout(1);
                        return;
                    case R.id.rdb_vocmd3 /*2131297199*/:
                        Ntg6VocModeLayout.this.setUpdateTwoLayout(2);
                        return;
                    case R.id.rdb_vocmd4 /*2131297200*/:
                        Ntg6VocModeLayout.this.setUpdateTwoLayout(3);
                        return;
                    case R.id.rdb_vocmd5 /*2131297201*/:
                        Ntg6VocModeLayout.this.setUpdateTwoLayout(4);
                        return;
                    case R.id.rdb_vocmd6 /*2131297202*/:
                        Ntg6VocModeLayout.this.setUpdateTwoLayout(5);
                        return;
                    default:
                        return;
                }
            }
        });
        this.img_TwoBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Ntg6VocModeLayout.this.handler.sendEmptyMessage(1);
            }
        });
    }

    private void initBarView(int index) {
        switch (index) {
            case 0:
                this.seekbar_mdi.setProgress(this.di);
                this.seekbar_mzh.setProgress(this.zo);
                this.seekbar_mgo.setProgress(this.ga);
                break;
            case 1:
                this.seekbar_mdi.setProgress(16);
                this.seekbar_mzh.setProgress(9);
                this.seekbar_mgo.setProgress(16);
                break;
            case 2:
                this.seekbar_mdi.setProgress(19);
                this.seekbar_mzh.setProgress(13);
                this.seekbar_mgo.setProgress(15);
                break;
            case 3:
                this.seekbar_mdi.setProgress(15);
                this.seekbar_mzh.setProgress(11);
                this.seekbar_mgo.setProgress(17);
                break;
            case 4:
                this.seekbar_mdi.setProgress(13);
                this.seekbar_mzh.setProgress(16);
                this.seekbar_mgo.setProgress(16);
                break;
            case 5:
                this.seekbar_mdi.setProgress(17);
                this.seekbar_mzh.setProgress(11);
                this.seekbar_mgo.setProgress(19);
                break;
        }
        this.tv_mdiSize.setText((this.seekbar_mdi.getProgress() - 12) + "");
        this.tv_mzhSize.setText((this.seekbar_mzh.getProgress() - 12) + "");
        this.tv_mgoSize.setText((this.seekbar_mgo.getProgress() - 12) + "");
    }

    /* access modifiers changed from: private */
    public void setUpdateTwoLayout(int index) {
        FileUtils.savaIntData(KeyConfig.EQ_MODE, index);
        this.eqModel = index;
        if (index == 0) {
            this.seekbar_mdi.setEnabled(true);
            this.seekbar_mzh.setEnabled(true);
            this.seekbar_mgo.setEnabled(true);
        } else {
            this.seekbar_mdi.setEnabled(false);
            this.seekbar_mzh.setEnabled(false);
            this.seekbar_mgo.setEnabled(false);
        }
        switch (index) {
            case 0:
                this.seekbar_mdi.setProgress(this.di);
                this.seekbar_mzh.setProgress(this.zo);
                this.seekbar_mgo.setProgress(this.ga);
                return;
            case 1:
                this.seekbar_mdi.setProgress(16);
                this.seekbar_mzh.setProgress(9);
                this.seekbar_mgo.setProgress(16);
                return;
            case 2:
                this.seekbar_mdi.setProgress(19);
                this.seekbar_mzh.setProgress(13);
                this.seekbar_mgo.setProgress(15);
                return;
            case 3:
                this.seekbar_mdi.setProgress(15);
                this.seekbar_mzh.setProgress(11);
                this.seekbar_mgo.setProgress(17);
                return;
            case 4:
                this.seekbar_mdi.setProgress(13);
                this.seekbar_mzh.setProgress(16);
                this.seekbar_mgo.setProgress(16);
                return;
            case 5:
                this.seekbar_mdi.setProgress(17);
                this.seekbar_mzh.setProgress(11);
                this.seekbar_mgo.setProgress(19);
                return;
            default:
                return;
        }
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekbar_mdi /*2131297411*/:
                this.tv_mdiSize.setText((progress - 12) + "");
                if (this.eqModel == 0) {
                    this.di = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_BASS, progress);
                    Log.d("BenchiEQ", "save di:" + this.di);
                    return;
                }
                return;
            case R.id.seekbar_mgo /*2131297412*/:
                this.tv_mgoSize.setText((progress - 12) + "");
                if (this.eqModel == 0) {
                    this.ga = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_TREBLE, progress);
                    Log.d("BenchiEQ", "save go:" + this.ga);
                    return;
                }
                return;
            case R.id.seekbar_mzh /*2131297413*/:
                this.tv_mzhSize.setText((progress - 12) + "");
                if (this.eqModel == 0) {
                    this.zo = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_MIDDLE, progress);
                    Log.d("BenchiEQ", "save zo:" + this.zo);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
