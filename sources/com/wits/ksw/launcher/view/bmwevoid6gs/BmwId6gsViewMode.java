package com.wits.ksw.launcher.view.bmwevoid6gs;

import android.database.ContentObserver;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class BmwId6gsViewMode extends LauncherViewModel {
    private ContentObserver contentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            BmwId6gsViewMode bmwId6gsViewMode = BmwId6gsViewMode.this;
            bmwId6gsViewMode.setIndex(BmwId6GsConfig.getIndex(bmwId6gsViewMode.context));
        }
    };
    public ObservableInt index = new ObservableInt();
    public ObservableInt pageIndex = new ObservableInt();

    public BmwId6gsViewMode() {
        setPageIndex(0);
        setIndex(0);
        BmwId6GsConfig.saveIndex(this.context, 0);
        this.context.getContentResolver().registerContentObserver(Settings.System.getUriFor(BmwId6GsConfig.INDEX), false, this.contentObserver);
    }

    public void setPageIndex(int value) {
        this.pageIndex.set(value);
    }

    public void setIndex(int value) {
        this.index.set(value);
    }

    public void setCurrentItem(View view, int item) {
        setPageIndex(item);
    }

    public void onClick(View view, int index2) {
        BmwId6GsConfig.saveIndex(this.context, index2);
        switch (index2) {
            case 0:
                openNaviApp(view);
                return;
            case 1:
                openChoseMusic(view);
                return;
            case 2:
                openBtApp(view);
                return;
            case 3:
                openVideo(view);
                return;
            case 4:
                openCar(view);
                return;
            case 5:
                openFileManager(view);
                return;
            case 6:
                openBrowser(view);
                return;
            case 7:
                openDvr(view);
                return;
            case 8:
                openDashboard(view);
                return;
            case 9:
                openShouJiHuLian(view);
                return;
            case 10:
                openApps(view);
                return;
            case 11:
                openSettings(view);
                return;
            default:
                return;
        }
    }
}
