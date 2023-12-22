package com.wits.ksw.settings.audi_mib3.p008vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.database.ContentObserver;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import com.wits.ksw.settings.audi.p007vm.EQViewModel;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* renamed from: com.wits.ksw.settings.audi_mib3.vm.AudiMib3EQViewModel */
/* loaded from: classes5.dex */
public class AudiMib3EQViewModel extends AndroidViewModel {
    private static final String TAG = "KswApplication." + EQViewModel.class.getSimpleName();
    private static Context context;
    public ObservableInt bassProgress;
    public ObservableField<String> bassStr;
    private ContentObserver contentObserver;
    public ObservableInt eqModel;
    public ObservableInt mezzoProgress;
    public ObservableField<String> mezzoStr;
    public ObservableInt trebleProgress;
    public ObservableField<String> trebleStr;

    public AudiMib3EQViewModel(Application application) {
        super(application);
        this.eqModel = new ObservableInt();
        this.bassProgress = new ObservableInt();
        this.mezzoProgress = new ObservableInt();
        this.trebleProgress = new ObservableInt();
        this.bassStr = new ObservableField<>();
        this.mezzoStr = new ObservableField<>();
        this.trebleStr = new ObservableField<>();
        this.contentObserver = new ContentObserver(new Handler()) { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3EQViewModel.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                try {
                    int eq = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MODE);
                    Log.i(AudiMib3EQViewModel.TAG, "onChange: " + eq);
                    if (eq != 0) {
                        AudiMib3EQViewModel.this.setProgress(eq, 12, 12, 12);
                    } else {
                        int bassValue = PowerManagerApp.getSettingsInt(KeyConfig.EQ_BASS);
                        int mezzoValue = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MIDDLE);
                        int trebleValue = PowerManagerApp.getSettingsInt(KeyConfig.EQ_TREBLE);
                        AudiMib3EQViewModel.this.setProgress(eq, bassValue, mezzoValue, trebleValue);
                    }
                    AudiMib3EQViewModel.this.eqModel.set(eq);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        context = application.getApplicationContext();
        try {
            int bassValue = PowerManagerApp.getSettingsInt(KeyConfig.EQ_BASS);
            int mezzoValue = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MIDDLE);
            int trebleValue = PowerManagerApp.getSettingsInt(KeyConfig.EQ_TREBLE);
            int eq = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MODE);
            Log.d(TAG, "\teqModel:" + eq + "\tbassValue:" + bassValue + "\tmezzoValue:" + mezzoValue + "\ttrebleValue:" + trebleValue);
            this.eqModel.set(eq);
            if (eq == 0) {
                setProgress(eq, bassValue, mezzoValue, trebleValue);
            } else {
                setProgress(eq, 12, 12, 12);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.EQ_MODE), true, this.contentObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgress(int model, int bassValue, int mezzoValue, int trebleValue) {
        Log.i(TAG, "setProgress: \tbassValue:" + bassValue + "\tmezzoValue:" + mezzoValue + "\ttrebleValue:" + trebleValue);
        switch (model) {
            case 0:
                this.bassProgress.set(bassValue);
                this.mezzoProgress.set(mezzoValue);
                this.trebleProgress.set(trebleValue);
                break;
            case 1:
                this.bassProgress.set(bassValue + 4);
                this.mezzoProgress.set(mezzoValue - 3);
                this.trebleProgress.set(trebleValue + 4);
                break;
            case 2:
                this.bassProgress.set(bassValue + 7);
                this.mezzoProgress.set(mezzoValue + 1);
                this.trebleProgress.set(trebleValue + 3);
                break;
            case 3:
                this.bassProgress.set(bassValue + 3);
                this.mezzoProgress.set(mezzoValue - 1);
                this.trebleProgress.set(trebleValue + 5);
                break;
            case 4:
                this.bassProgress.set(bassValue + 1);
                this.mezzoProgress.set(mezzoValue + 4);
                this.trebleProgress.set(trebleValue + 4);
                break;
            case 5:
                this.bassProgress.set(bassValue + 5);
                this.mezzoProgress.set(mezzoValue - 1);
                this.trebleProgress.set(trebleValue + 7);
                break;
        }
        this.bassStr.set(String.valueOf(this.bassProgress.get() - 12));
        this.mezzoStr.set(String.valueOf(this.mezzoProgress.get() - 12));
        this.trebleStr.set(String.valueOf(this.trebleProgress.get() - 12));
    }

    public static void setEQModelChangeListener(RadioButton radioButton, final int index) {
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3EQViewModel.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FileUtils.savaIntData(KeyConfig.EQ_MODE, index);
                }
            }
        });
    }

    public void setEQModelChange(RadioButton radioButton, final SeekBar seekBar, final int index) {
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3EQViewModel.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FileUtils.savaIntData(KeyConfig.EQ_MODE, index);
                    seekBar.setEnabled(index == 0);
                }
            }
        });
    }

    public static void setBassSeekBarChangeListener(SeekBar seekbar, AudiMib3EQViewModel viewModel) {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3EQViewModel.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    FileUtils.savaIntData(KeyConfig.EQ_BASS, progress);
                    AudiMib3EQViewModel audiMib3EQViewModel = AudiMib3EQViewModel.this;
                    if (audiMib3EQViewModel != null) {
                        audiMib3EQViewModel.bassStr.set(String.valueOf(progress - 12));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public static void setmezzoSeekBarChangeListener(SeekBar seekbar, AudiMib3EQViewModel viewModel) {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3EQViewModel.5
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    FileUtils.savaIntData(KeyConfig.EQ_MIDDLE, progress);
                    AudiMib3EQViewModel audiMib3EQViewModel = AudiMib3EQViewModel.this;
                    if (audiMib3EQViewModel != null) {
                        audiMib3EQViewModel.mezzoStr.set(String.valueOf(progress - 12));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public static void setTrebleSeekBarChangeListener(SeekBar seekbar, AudiMib3EQViewModel viewModel) {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi_mib3.vm.AudiMib3EQViewModel.6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    FileUtils.savaIntData(KeyConfig.EQ_TREBLE, progress);
                    AudiMib3EQViewModel audiMib3EQViewModel = AudiMib3EQViewModel.this;
                    if (audiMib3EQViewModel != null) {
                        audiMib3EQViewModel.trebleStr.set(String.valueOf(progress - 12));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override // android.arch.lifecycle.ViewModel
    protected void onCleared() {
        context.getContentResolver().unregisterContentObserver(this.contentObserver);
        super.onCleared();
    }
}