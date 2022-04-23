package com.wits.ksw.settings.audi_mib3.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.List;

public class AudiMib3NaviVm extends AndroidViewModel {
    private Context context = getApplication();
    public MutableLiveData<List<MapBean>> naviList = new MutableLiveData<>();

    public AudiMib3NaviVm(Application application) {
        super(application);
        ScanNaviList.getInstance().setMapListScanListener(new ScanNaviList.OnMapListScanListener() {
            public void onScanFinish(List<MapBean> mapList) {
                AudiMib3NaviVm.this.naviList.postValue(mapList);
            }
        });
        startNaviList();
    }

    public void startNaviList() {
        try {
            List<String> naviList2 = PowerManagerApp.getDataListFromJsonKey(KeyConfig.NAVI_LIST);
            String navidefual = PowerManagerApp.getSettingsString(KeyConfig.NAVI_DEFUAL);
            if (naviList2 != null && naviList2.size() > 0) {
                ScanNaviList.getInstance().scanList(naviList2, navidefual, this.context);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
