package com.wits.ksw.settings.id6.oneLayout;

import android.content.Context;
import android.support.p001v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes7.dex */
public class ID6VoiceModel extends RelativeLayout implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private int barMax;
    private Context context;

    /* renamed from: di */
    private int f220di;
    private int eqModel;

    /* renamed from: ga */
    private int f221ga;
    private SeekBar seekbar_mdi;
    private SeekBar seekbar_mgo;
    private SeekBar seekbar_mzh;
    private TextView tv_eqgud;
    private TextView tv_eqjues;
    private TextView tv_eqliux;
    private TextView tv_eqwuq;
    private TextView tv_eqyaog;
    private TextView tv_eqyongh;
    private TextView tv_mdiSize;
    private TextView tv_mgoSize;
    private TextView tv_mzhSize;

    /* renamed from: zo */
    private int f222zo;

    public ID6VoiceModel(Context context) {
        super(context);
        this.eqModel = 0;
        this.barMax = 24;
        this.f220di = 12;
        this.f222zo = 12;
        this.f221ga = 12;
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_id6_voice_model, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
        try {
            this.f220di = PowerManagerApp.getSettingsInt(KeyConfig.EQ_BASS);
            this.f222zo = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MIDDLE);
            this.f221ga = PowerManagerApp.getSettingsInt(KeyConfig.EQ_TREBLE);
            this.eqModel = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MODE);
            Log.d("BenchiEQ", "di:" + this.f220di + "\tzo:" + this.f222zo + "\tga:" + this.f221ga + "\tmodel:" + this.eqModel);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view) {
        this.tv_mdiSize = (TextView) view.findViewById(C0899R.C0901id.tv_mdiSize);
        SeekBar seekBar = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_mdi);
        this.seekbar_mdi = seekBar;
        seekBar.setMax(this.barMax);
        this.tv_mzhSize = (TextView) view.findViewById(C0899R.C0901id.tv_mzhSize);
        SeekBar seekBar2 = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_mzh);
        this.seekbar_mzh = seekBar2;
        seekBar2.setMax(this.barMax);
        this.tv_mgoSize = (TextView) view.findViewById(C0899R.C0901id.tv_mgoSize);
        SeekBar seekBar3 = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_mgo);
        this.seekbar_mgo = seekBar3;
        seekBar3.setMax(this.barMax);
        this.seekbar_mdi.setOnSeekBarChangeListener(this);
        this.seekbar_mzh.setOnSeekBarChangeListener(this);
        this.seekbar_mgo.setOnSeekBarChangeListener(this);
        this.tv_eqyongh = (TextView) view.findViewById(C0899R.C0901id.tv_eqyongh);
        this.tv_eqliux = (TextView) view.findViewById(C0899R.C0901id.tv_eqliux);
        this.tv_eqgud = (TextView) view.findViewById(C0899R.C0901id.tv_eqgud);
        this.tv_eqyaog = (TextView) view.findViewById(C0899R.C0901id.tv_eqyaog);
        this.tv_eqjues = (TextView) view.findViewById(C0899R.C0901id.tv_eqjues);
        this.tv_eqwuq = (TextView) view.findViewById(C0899R.C0901id.tv_eqwuq);
        switch (this.eqModel) {
            case 0:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                break;
            case 1:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                break;
            case 2:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                break;
            case 3:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                break;
            case 4:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                break;
            case 5:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                break;
        }
        initBarView(this.eqModel);
        this.tv_eqyongh.setOnClickListener(this);
        this.tv_eqliux.setOnClickListener(this);
        this.tv_eqgud.setOnClickListener(this);
        this.tv_eqyaog.setOnClickListener(this);
        this.tv_eqjues.setOnClickListener(this);
        this.tv_eqwuq.setOnClickListener(this);
    }

    private void initBarView(int index) {
        switch (index) {
            case 0:
                this.seekbar_mdi.setProgress(this.f220di);
                this.seekbar_mzh.setProgress(this.f222zo);
                this.seekbar_mgo.setProgress(this.f221ga);
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
        int sdi = this.seekbar_mdi.getProgress() - 12;
        this.tv_mdiSize.setText(sdi + "");
        int zh = this.seekbar_mzh.getProgress() - 12;
        this.tv_mzhSize.setText(zh + "");
        int go = this.seekbar_mgo.getProgress() - 12;
        this.tv_mgoSize.setText(go + "");
    }

    private void setUpdateTwoLayout(int index) {
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
                this.seekbar_mdi.setProgress(this.f220di);
                this.seekbar_mzh.setProgress(this.f222zo);
                this.seekbar_mgo.setProgress(this.f221ga);
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

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case C0899R.C0901id.seekbar_mdi /* 2131297743 */:
                int go = progress - 12;
                this.tv_mdiSize.setText(go + "");
                if (this.eqModel == 0) {
                    this.f220di = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_BASS, progress);
                    Log.d("BenchiEQ", "save di:" + this.f220di);
                    return;
                }
                return;
            case C0899R.C0901id.seekbar_mgo /* 2131297744 */:
                int zh = progress - 12;
                this.tv_mgoSize.setText(zh + "");
                if (this.eqModel == 0) {
                    this.f221ga = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_TREBLE, progress);
                    Log.d("BenchiEQ", "save go:" + this.f221ga);
                    return;
                }
                return;
            case C0899R.C0901id.seekbar_mzh /* 2131297745 */:
                int zh2 = progress - 12;
                this.tv_mzhSize.setText(zh2 + "");
                if (this.eqModel == 0) {
                    this.f222zo = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_MIDDLE, progress);
                    Log.d("BenchiEQ", "save zo:" + this.f222zo);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.tv_eqgud /* 2131297903 */:
                setUpdateTwoLayout(2);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                return;
            case C0899R.C0901id.tv_eqjues /* 2131297904 */:
                setUpdateTwoLayout(4);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                return;
            case C0899R.C0901id.tv_eqliux /* 2131297905 */:
                setUpdateTwoLayout(1);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                return;
            case C0899R.C0901id.tv_eqwuq /* 2131297906 */:
                setUpdateTwoLayout(5);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                return;
            case C0899R.C0901id.tv_eqyaog /* 2131297907 */:
                setUpdateTwoLayout(3);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                return;
            case C0899R.C0901id.tv_eqyongh /* 2131297908 */:
                setUpdateTwoLayout(0);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, C0899R.color.text_red));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
                return;
            default:
                return;
        }
    }
}
