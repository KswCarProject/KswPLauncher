package com.wits.ksw.launcher.view.benzgs;

import android.database.ContentObserver;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes5.dex */
public class BenzGsViewMoel extends BcVieModel {
    public ObservableInt pageIndex = new ObservableInt(0);
    public ObservableInt index = new ObservableInt(-1);
    private ContentObserver contentObserver = new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel.1
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            BenzGsViewMoel benzGsViewMoel = BenzGsViewMoel.this;
            benzGsViewMoel.setIndex(BenzConfig.getIndex(benzGsViewMoel.context));
        }
    };

    public BenzGsViewMoel() {
        setIndex(BenzConfig.getIndex(this.context));
        this.context.getContentResolver().registerContentObserver(Settings.System.getUriFor(BenzConfig.INDEX), false, this.contentObserver);
    }

    @Override // com.wits.ksw.launcher.model.LauncherViewModel, com.wits.ksw.launcher.base.BaseViewModel, android.arch.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.context.getContentResolver().unregisterContentObserver(this.contentObserver);
    }

    public void setIndex(int v) {
        this.index.set(v);
        Log.i(TAG, "setIndex: " + v);
    }

    public void onClick(int index) {
        BenzConfig.saveIndex(this.context, index);
        setIndex(index);
        if (index == 0) {
            openNaviApp(null);
        } else if (index == 1) {
            openMusicMulti(null);
        } else if (index == 2) {
            openBtApp(null);
        } else if (index == 3) {
            openCar(null);
        } else if (index == 4) {
            openSettings(null);
        } else if (index == 5) {
            openVideoMulti(null);
        } else if (index == 6) {
            openFileManager(null);
        } else if (index == 7) {
            openShouJiHuLian(null);
        } else if (index == 8) {
            openDashboard(null);
        } else if (index == 9) {
            openApps(null);
        }
    }

    public void setCurrentItem(View view, int page) {
        this.pageIndex.set(page);
    }

    public static void setOliText(TextView textView, String value) {
        textView.setText(textView.getResources().getString(C0899R.string.oil_size, "" + value));
    }
}
