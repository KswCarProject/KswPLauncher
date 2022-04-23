package com.wits.ksw.launcher.view.benzgs;

import android.database.ContentObserver;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public class BenzGsViewMoel extends BcVieModel {
    private ContentObserver contentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            BenzGsViewMoel benzGsViewMoel = BenzGsViewMoel.this;
            benzGsViewMoel.setIndex(BenzConfig.getIndex(benzGsViewMoel.context));
        }
    };
    public ObservableInt index = new ObservableInt(-1);
    public ObservableInt pageIndex = new ObservableInt(0);

    public BenzGsViewMoel() {
        setIndex(BenzConfig.getIndex(this.context));
        this.context.getContentResolver().registerContentObserver(Settings.System.getUriFor(BenzConfig.INDEX), false, this.contentObserver);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        this.context.getContentResolver().unregisterContentObserver(this.contentObserver);
    }

    public void setIndex(int v) {
        this.index.set(v);
        Log.i(TAG, "setIndex: " + v);
    }

    public void onClick(int index2) {
        BenzConfig.saveIndex(this.context, index2);
        setIndex(index2);
        if (index2 == 0) {
            openNaviApp((View) null);
        } else if (index2 == 1) {
            openMusicMulti((View) null);
        } else if (index2 == 2) {
            openBtApp((View) null);
        } else if (index2 == 3) {
            openCar((View) null);
        } else if (index2 == 4) {
            openSettings((View) null);
        } else if (index2 == 5) {
            openVideoMulti((View) null);
        } else if (index2 == 6) {
            openFileManager((View) null);
        } else if (index2 == 7) {
            openShouJiHuLian((View) null);
        } else if (index2 == 8) {
            openDashboard((View) null);
        } else if (index2 == 9) {
            openApps((View) null);
        }
    }

    public void setCurrentItem(View view, int page) {
        this.pageIndex.set(page);
    }

    public static void setOliText(TextView textView, String value) {
        textView.setText(textView.getResources().getString(R.string.oil_size, new Object[]{"" + value}));
    }
}
