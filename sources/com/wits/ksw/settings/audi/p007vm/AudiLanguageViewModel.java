package com.wits.ksw.settings.audi.p007vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: com.wits.ksw.settings.audi.vm.AudiLanguageViewModel */
/* loaded from: classes7.dex */
public class AudiLanguageViewModel extends AndroidViewModel {
    private static final String TAG = "KswApplication." + AudiLanguageViewModel.class.getSimpleName();
    private ContentObserver contentObserver;
    private Context context;
    public MutableLiveData<Locale> languageChange;
    public MutableLiveData<List<FunctionBean>> languageDatas;

    public AudiLanguageViewModel(Application application) {
        super(application);
        this.languageDatas = new MutableLiveData<>();
        this.languageChange = new MutableLiveData<>();
        this.contentObserver = new ContentObserver(new Handler()) { // from class: com.wits.ksw.settings.audi.vm.AudiLanguageViewModel.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                AudiLanguageViewModel.this.languageChange.setValue(Locale.getDefault());
            }
        };
        Log.i(TAG, "AudiLanguageViewModel: ");
        Context applicationContext = application.getApplicationContext();
        this.context = applicationContext;
        applicationContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.LANGUAGE_DEFUAL), false, this.contentObserver);
    }

    public void updataLanguage() {
        try {
            int defaultIndex = PowerManagerApp.getSettingsInt(KeyConfig.LANGUAGE_DEFUAL);
            Log.i(TAG, "defaultIndex: " + defaultIndex);
            List<FunctionBean> data = new ArrayList<>();
            List<String> languags = PowerManagerApp.getDataListFromJsonKey(KeyConfig.LANGUAGE_LIST);
            int i = 0;
            while (i < languags.size()) {
                Log.d(TAG, "factory Language\uff1a" + languags.get(i));
                FunctionBean fb = new FunctionBean();
                fb.setTitle(languags.get(i));
                fb.setIscheck(defaultIndex == i);
                data.add(fb);
                i++;
            }
            this.languageDatas.setValue(data);
            Locale sysLanguage = Locale.getDefault();
            Log.i(TAG, "System Language\uff1a" + sysLanguage.getDisplayName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.arch.lifecycle.ViewModel
    protected void onCleared() {
        this.context.getContentResolver().unregisterContentObserver(this.contentObserver);
        super.onCleared();
    }
}
