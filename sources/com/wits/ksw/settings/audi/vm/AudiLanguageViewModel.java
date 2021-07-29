package com.wits.ksw.settings.audi.vm;

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

public class AudiLanguageViewModel extends AndroidViewModel {
    private static final String TAG = ("KSWLauncher." + AudiLanguageViewModel.class.getSimpleName());
    private ContentObserver contentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            AudiLanguageViewModel.this.languageChange.setValue(Locale.getDefault());
        }
    };
    private Context context;
    public MutableLiveData<Locale> languageChange = new MutableLiveData<>();
    public MutableLiveData<List<FunctionBean>> languageDatas = new MutableLiveData<>();

    public AudiLanguageViewModel(Application application) {
        super(application);
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
