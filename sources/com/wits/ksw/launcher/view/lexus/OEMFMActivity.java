package com.wits.ksw.launcher.view.lexus;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityLexusOemFmBinding;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.text.SimpleDateFormat;

public class OEMFMActivity extends AppCompatActivity {
    public static final String TAG = OEMFMActivity.class.getSimpleName();
    private IContentObserver.Stub acObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                McuStatus.ACData acData = McuStatus.ACData.getStatusFromJson(PowerManagerApp.getStatusString("acData"));
                String str = OEMFMActivity.TAG;
                Log.i(str, "onChange: acData=" + acData.getJson());
                OEMFMActivity.this.update(acData);
            } catch (Exception e) {
                e.printStackTrace();
                String str2 = OEMFMActivity.TAG;
                Log.e(str2, "onChange: Exception " + e.getMessage());
            }
        }
    };
    private IContentObserver.Stub mediaObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                McuStatus.MediaData mediaData = McuStatus.MediaData.parseDataFromJson(PowerManagerApp.getStatusString("mcuMediaJson"));
                String str = OEMFMActivity.TAG;
                Log.i(str, "onChange: acData=" + mediaData);
                OEMFMActivity.this.updateMedia(mediaData);
            } catch (Exception e) {
                e.printStackTrace();
                String str2 = OEMFMActivity.TAG;
                Log.e(str2, "onChange: Exception " + e.getMessage());
            }
        }
    };
    private ActivityLexusOemFmBinding oemFmBinding;
    private int tempUnit = 0;
    private LexusUiParams uiParams = new LexusUiParams();
    private LexusOEMFMViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.oemFmBinding = (ActivityLexusOemFmBinding) DataBindingUtil.setContentView(this, R.layout.activity_lexus_oem_fm);
        this.oemFmBinding.setMUiParams(this.uiParams);
        this.viewModel = (LexusOEMFMViewModel) ViewModelProviders.of((FragmentActivity) this).get(LexusOEMFMViewModel.class);
        this.oemFmBinding.setVm(this.viewModel);
        setFull();
        registerIContentObserver();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        PowerManagerApp.unRegisterIContentObserver(this.acObserver);
    }

    public void setFull() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(67108864);
            getWindow().addFlags(134217728);
        }
    }

    public String getFormatMMSS(int min, int sec) {
        return new SimpleDateFormat("mm:ss").format(Long.valueOf((long) ((min * 60 * 1000) + (sec * 1000))));
    }

    private void registerIContentObserver() {
        Log.i(TAG, "onCreate: registerIContentObserver");
        PowerManagerApp.registerIContentObserver("acData", this.acObserver);
        PowerManagerApp.registerIContentObserver("mcuMediaJson", this.mediaObserver);
        try {
            update(McuStatus.ACData.getStatusFromJson(PowerManagerApp.getStatusString("acData")));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void updateMedia(McuStatus.MediaData mediaData) {
        String str = TAG;
        Log.d(str, "update media " + mediaData.type);
        if (mediaData.type == 1) {
            this.viewModel.fm.set(true);
            this.viewModel.cd.set(false);
            this.viewModel.fmBand.set(mediaData.fm.name);
            this.viewModel.fmFrequency.set(mediaData.fm.freq);
            switch (mediaData.fm.preFreq) {
                case 0:
                    this.viewModel.ch.set("");
                    break;
                case 1:
                    this.viewModel.ch.set("CH1");
                    break;
                case 2:
                    this.viewModel.ch.set("CH2");
                    break;
                case 3:
                    this.viewModel.ch.set("CH3");
                    break;
                case 4:
                    this.viewModel.ch.set("CH4");
                    break;
                case 5:
                    this.viewModel.ch.set("CH5");
                    break;
                case 6:
                    this.viewModel.ch.set("CH6");
                    break;
            }
        }
        if (mediaData.type == 16) {
            this.viewModel.fm.set(false);
            this.viewModel.cd.set(true);
            setDisc(mediaData.disc.number);
            ObservableField<String> observableField = this.viewModel.disc;
            observableField.set(mediaData.disc.number + "");
            ObservableField<String> observableField2 = this.viewModel.track;
            observableField2.set(mediaData.disc.track + "");
            this.viewModel.time.set(getFormatMMSS(mediaData.disc.min, mediaData.disc.sec));
        }
        if (mediaData.mode != null) {
            this.viewModel.asl.set(Boolean.valueOf(mediaData.mode.ASL));
            this.viewModel.rand.set(Boolean.valueOf(mediaData.mode.RAND));
            this.viewModel.pause.set(Boolean.valueOf(mediaData.mode.PAUSE));
            this.viewModel.scan.set(Boolean.valueOf(mediaData.mode.SCAN));
            this.viewModel.st.set(Boolean.valueOf(mediaData.mode.ST));
            this.viewModel.rpt.set(Boolean.valueOf(mediaData.mode.RPT));
            String str2 = TAG;
            Log.d(str2, " ASL " + mediaData.mode.ASL + " ST " + mediaData.mode.ST + " Rand " + mediaData.mode.RAND + " Scan " + mediaData.mode.SCAN + " Rpt " + mediaData.mode.RPT + " Pause " + mediaData.mode.PAUSE);
        }
    }

    private void setDisc(int disc) {
        this.oemFmBinding.cd1.setSelected(false);
        this.oemFmBinding.cd2.setSelected(false);
        this.oemFmBinding.cd3.setSelected(false);
        this.oemFmBinding.cd4.setSelected(false);
        this.oemFmBinding.cd5.setSelected(false);
        this.oemFmBinding.cd6.setSelected(false);
        if (disc <= 6) {
            switch (disc) {
                case 1:
                    this.oemFmBinding.cd1.setSelected(true);
                    return;
                case 2:
                    this.oemFmBinding.cd2.setSelected(true);
                    return;
                case 3:
                    this.oemFmBinding.cd3.setSelected(true);
                    return;
                case 4:
                    this.oemFmBinding.cd4.setSelected(true);
                    return;
                case 5:
                    this.oemFmBinding.cd5.setSelected(true);
                    return;
                case 6:
                    this.oemFmBinding.cd6.setSelected(true);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void update(McuStatus.ACData acData) {
        this.uiParams.setOpen(acData.isOpen);
        this.uiParams.setAutoSwitch(acData.autoSwitch);
        this.uiParams.setLoopMode(acData.loop);
        this.uiParams.setACStatus(acData.AC_Switch);
        this.uiParams.setBackMistSwitch(acData.backMistSwitch);
        this.uiParams.setFrontMistSwitch(acData.frontMistSwitch);
        this.uiParams.setWindSpeed((int) acData.speed);
        try {
            this.tempUnit = PowerManagerApp.getSettingsInt(KeyConfig.TempUnit);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (acData.leftTmp == 0.0f) {
            this.uiParams.setLeftTempStr("LO");
        } else if (acData.leftTmp == -1.0f) {
            this.uiParams.setLeftTempStr("HI");
        } else if (this.tempUnit == 1) {
            int tempToF = tempToF(acData.leftTmp);
            LexusUiParams lexusUiParams = this.uiParams;
            lexusUiParams.setLeftTempStr(String.valueOf(tempToF) + "°F");
        } else {
            LexusUiParams lexusUiParams2 = this.uiParams;
            lexusUiParams2.setLeftTempStr(String.valueOf(acData.leftTmp) + "℃");
        }
        if (acData.rightTmp == 0.0f) {
            this.uiParams.setRightTempStr("LO");
        } else if (acData.rightTmp == -1.0f) {
            this.uiParams.setRightTempStr("HI");
        } else if (this.tempUnit == 1) {
            int tempToF2 = tempToF(acData.rightTmp);
            LexusUiParams lexusUiParams3 = this.uiParams;
            lexusUiParams3.setRightTempStr(String.valueOf(tempToF2) + "°F");
        } else {
            LexusUiParams lexusUiParams4 = this.uiParams;
            lexusUiParams4.setRightTempStr(String.valueOf(acData.rightTmp) + "℃");
        }
        this.uiParams.setLoopMode(acData.loop);
        boolean above = acData.isOpen(8);
        boolean front = acData.isOpen(4);
        boolean below = acData.isOpen(2);
        if (!above && !front && !below) {
            this.uiParams.setBlowingMode(0);
        } else if (above && !front && !below) {
            this.uiParams.setBlowingMode(1);
        } else if (!above && !front && below) {
            this.uiParams.setBlowingMode(2);
        } else if (!above && front && below) {
            this.uiParams.setBlowingMode(3);
        } else if (above && !front && below) {
            this.uiParams.setBlowingMode(4);
        }
        String str = TAG;
        Log.d(str, "blowing mode right_above " + above + " right_front " + front + " right_below " + below);
    }

    public static int tempToF(float tmep) {
        return Math.round(((9.0f * tmep) / 5.0f) + 32.0f);
    }
}
