package com.wits.ksw.settings.audi_mib3.vm;

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

public class AudiMib3LanguageViewModel extends AndroidViewModel {
    private static final String TAG = ("KswApplication." + AudiMib3LanguageViewModel.class.getSimpleName());
    private ContentObserver contentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            AudiMib3LanguageViewModel.this.languageChange.setValue(Locale.getDefault());
        }
    };
    private Context context;
    public MutableLiveData<Locale> languageChange = new MutableLiveData<>();
    public MutableLiveData<List<FunctionBean>> languageDatas = new MutableLiveData<>();

    public AudiMib3LanguageViewModel(Application application) {
        super(application);
        Log.i(TAG, "AudiMib3LanguageViewModel: ");
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
                Log.d(TAG, "factory Language：" + languags.get(i));
                FunctionBean fb = new FunctionBean();
                fb.setTitle(languags.get(i));
                fb.setIscheck(defaultIndex == i);
                data.add(fb);
                i++;
            }
            this.languageDatas.setValue(data);
            Log.i(TAG, "System Language：" + Locale.getDefault().getDisplayName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        this.context.getContentResolver().unregisterContentObserver(this.contentObserver);
        super.onCleared();
    }
}
