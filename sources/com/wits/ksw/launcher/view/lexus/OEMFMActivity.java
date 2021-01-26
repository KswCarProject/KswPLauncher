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
    private IContentObserver.Stub discObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                OEMFMActivity.this.setDisc();
            } catch (Exception e) {
                e.printStackTrace();
                String str = OEMFMActivity.TAG;
                Log.e(str, "onChange: Exception " + e.getMessage());
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
    private IContentObserver.Stub usbInfoObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                OEMFMActivity.this.updateUsbInfo();
            } catch (Exception e) {
                e.printStackTrace();
                String str = OEMFMActivity.TAG;
                Log.e(str, "onChange: Exception " + e.getMessage());
            }
        }
    };
    private IContentObserver.Stub usbStatusObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                OEMFMActivity.this.updateUsbInfo();
            } catch (Exception e) {
                e.printStackTrace();
                String str = OEMFMActivity.TAG;
                Log.e(str, "onChange: Exception " + e.getMessage());
            }
        }
    };
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
        PowerManagerApp.unRegisterIContentObserver(this.mediaObserver);
        PowerManagerApp.unRegisterIContentObserver(this.usbStatusObserver);
        PowerManagerApp.unRegisterIContentObserver(this.usbInfoObserver);
        PowerManagerApp.unRegisterIContentObserver(this.discObserver);
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
        PowerManagerApp.registerIContentObserver("mcuMediaStringInfo", this.usbInfoObserver);
        PowerManagerApp.registerIContentObserver("mcuMediaPlayStatus", this.usbStatusObserver);
        PowerManagerApp.registerIContentObserver("mcuMediaJson", this.mediaObserver);
        PowerManagerApp.registerIContentObserver("mcuDiscStatus", this.discObserver);
        try {
            update(McuStatus.ACData.getStatusFromJson(PowerManagerApp.getStatusString("acData")));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void updateMedia(McuStatus.MediaData mediaData) {
        String str = TAG;
        Log.d(str, "update media " + mediaData.type + " mode " + mediaData.mode);
        if (mediaData.type == 1) {
            this.viewModel.fm.set(true);
            this.viewModel.cd.set(false);
            this.viewModel.usb.set(false);
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
            this.viewModel.usb.set(false);
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
            Log.d(str2, " ASL " + mediaData.mode.ASL + " ST " + mediaData.mode.ST + " Rand " + mediaData.mode.RAND + " Scan " + mediaData.mode.SCAN + " Rpt " + mediaData.mode.RPT + " Pause " + mediaData.mode.PAUSE + " status " + this.viewModel.usbStatus.get());
        }
        try {
            McuStatus.MediaPlayStatus mcuMediaPlayStatus = McuStatus.MediaPlayStatus.parseInfoFromJson(PowerManagerApp.getStatusString("mcuMediaPlayStatus"));
            if (mcuMediaPlayStatus != null) {
                this.viewModel.asl.set(Boolean.valueOf(mcuMediaPlayStatus.ALS));
                this.viewModel.st.set(Boolean.valueOf(mcuMediaPlayStatus.ST));
                this.viewModel.rand.set(Boolean.valueOf(mcuMediaPlayStatus.RAND));
                this.viewModel.scan.set(Boolean.valueOf(mcuMediaPlayStatus.SCAN));
                this.viewModel.rpt.set(Boolean.valueOf(mcuMediaPlayStatus.RPT));
                this.viewModel.usbStatus.set(mcuMediaPlayStatus.status);
                String str3 = TAG;
                Log.d(str3, "mcuMediaPlayStatus ASL " + mcuMediaPlayStatus.ALS + " ST " + mcuMediaPlayStatus.ST + " Rand " + mcuMediaPlayStatus.RAND + " Scan " + mcuMediaPlayStatus.SCAN + " Rpt " + mcuMediaPlayStatus.RPT + " status " + this.viewModel.usbStatus.get());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        updateAudioOff();
    }

    /* access modifiers changed from: private */
    public void updateUsbInfo() {
        try {
            McuStatus.MediaStringInfo mcuMediaStringInfo = McuStatus.MediaStringInfo.parseInfoFromJson(PowerManagerApp.getStatusString("mcuMediaStringInfo"));
            McuStatus.MediaPlayStatus mcuMediaPlayStatus = McuStatus.MediaPlayStatus.parseInfoFromJson(PowerManagerApp.getStatusString("mcuMediaPlayStatus"));
            if (mcuMediaPlayStatus != null) {
                String str = TAG;
                Log.d(str, "PlayStatus type: " + mcuMediaPlayStatus.type);
                if (mcuMediaPlayStatus.type == 17) {
                    this.viewModel.fm.set(false);
                    this.viewModel.cd.set(false);
                    this.viewModel.usb.set(true);
                } else if (mcuMediaPlayStatus.type == 16) {
                    this.viewModel.fm.set(false);
                    this.viewModel.cd.set(true);
                    this.viewModel.usb.set(false);
                } else if (mcuMediaPlayStatus.type == 1) {
                    this.viewModel.fm.set(true);
                    this.viewModel.cd.set(false);
                    this.viewModel.usb.set(false);
                }
                this.viewModel.asl.set(Boolean.valueOf(mcuMediaPlayStatus.ALS));
                this.viewModel.st.set(Boolean.valueOf(mcuMediaPlayStatus.ST));
                this.viewModel.scan.set(Boolean.valueOf(mcuMediaPlayStatus.SCAN));
                this.viewModel.rand.set(Boolean.valueOf(mcuMediaPlayStatus.RAND));
                this.viewModel.rpt.set(Boolean.valueOf(mcuMediaPlayStatus.RPT));
                this.viewModel.usbStatus.set(mcuMediaPlayStatus.status);
                String str2 = TAG;
                Log.d(str2, "PlayStatus ASL " + mcuMediaPlayStatus.ALS + " ST " + mcuMediaPlayStatus.ST + " Rand " + mcuMediaPlayStatus.RAND + " Scan " + mcuMediaPlayStatus.SCAN + " Rpt " + mcuMediaPlayStatus.RPT + " Pause " + mcuMediaPlayStatus.status);
            }
            if (mcuMediaStringInfo != null) {
                String str3 = TAG;
                Log.d(str3, " name " + mcuMediaStringInfo.name);
                this.viewModel.usbMusicTime.set(getFormatMMSS(mcuMediaStringInfo.min, mcuMediaStringInfo.sec));
                this.viewModel.musicName.set(mcuMediaStringInfo.name);
                this.viewModel.artist.set(mcuMediaStringInfo.artist);
                this.viewModel.album.set(mcuMediaStringInfo.album);
                this.viewModel.index.set(mcuMediaStringInfo.folderName);
            }
            updateAudioOff();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void updateAudioOff() {
        try {
            McuStatus.MediaPlayStatus mcuMediaPlayStatus = McuStatus.MediaPlayStatus.parseInfoFromJson(PowerManagerApp.getStatusString("mcuMediaPlayStatus"));
            String str = TAG;
            Log.d(str, "updateAudioOff " + mcuMediaPlayStatus.status);
            if (mcuMediaPlayStatus == null || !mcuMediaPlayStatus.status.equals("AUDIO OFF")) {
                this.viewModel.audioOff.set(false);
                return;
            }
            this.viewModel.fm.set(false);
            this.viewModel.cd.set(false);
            this.viewModel.usb.set(false);
            this.viewModel.audioOff.set(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void setDisc() {
        try {
            McuStatus.DiscStatus disc = McuStatus.DiscStatus.parseInfoFromJson(PowerManagerApp.getStatusString("mcuDiscStatus"));
            String str = TAG;
            Log.d(str, "setDisc: " + disc.discInsert.length + " " + disc);
            if (disc != null) {
                this.viewModel.cd1.set(Boolean.valueOf(disc.discInsert[0]));
                this.viewModel.cd2.set(Boolean.valueOf(disc.discInsert[1]));
                this.viewModel.cd3.set(Boolean.valueOf(disc.discInsert[2]));
                this.viewModel.cd4.set(Boolean.valueOf(disc.discInsert[3]));
                this.viewModel.cd5.set(Boolean.valueOf(disc.discInsert[4]));
                this.viewModel.cd6.set(Boolean.valueOf(disc.discInsert[5]));
                String str2 = TAG;
                Log.d(str2, "setDisc cd1 " + disc.discInsert[0] + " cd2 " + disc.discInsert[1] + " cd3 " + disc.discInsert[2] + " cd4 " + disc.discInsert[3] + " cd5 " + disc.discInsert[4] + " cd6 " + disc.discInsert[5]);
                this.viewModel.cdMode.set(disc.status);
                this.oemFmBinding.cd1.setSelected(false);
                this.oemFmBinding.cd2.setSelected(false);
                this.oemFmBinding.cd3.setSelected(false);
                this.oemFmBinding.cd4.setSelected(false);
                this.oemFmBinding.cd5.setSelected(false);
                this.oemFmBinding.cd6.setSelected(false);
                switch (disc.range) {
                    case 1:
                        this.oemFmBinding.cd1.setSelected(true);
                        break;
                    case 2:
                        this.oemFmBinding.cd2.setSelected(true);
                        break;
                    case 3:
                        this.oemFmBinding.cd3.setSelected(true);
                        break;
                    case 4:
                        this.oemFmBinding.cd4.setSelected(true);
                        break;
                    case 5:
                        this.oemFmBinding.cd5.setSelected(true);
                        break;
                    case 6:
                        this.oemFmBinding.cd6.setSelected(true);
                        break;
                }
            } else {
                this.viewModel.cd1.set(false);
                this.viewModel.cd2.set(false);
                this.viewModel.cd3.set(false);
                this.viewModel.cd4.set(false);
                this.viewModel.cd5.set(false);
                this.viewModel.cd6.set(false);
                this.viewModel.cdMode.set("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.viewModel.cd1.set(false);
            this.viewModel.cd2.set(false);
            this.viewModel.cd3.set(false);
            this.viewModel.cd4.set(false);
            this.viewModel.cd5.set(false);
            this.viewModel.cd6.set(false);
            this.viewModel.cdMode.set("");
        }
        updateAudioOff();
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
