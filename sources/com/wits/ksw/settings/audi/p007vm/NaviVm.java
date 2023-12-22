package com.wits.ksw.settings.audi.p007vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.List;

/* renamed from: com.wits.ksw.settings.audi.vm.NaviVm */
/* loaded from: classes7.dex */
public class NaviVm extends AndroidViewModel {
    private Context context;
    public MutableLiveData<List<MapBean>> naviList;

    public NaviVm(Application application) {
        super(application);
        this.naviList = new MutableLiveData<>();
        this.context = getApplication();
        ScanNaviList.getInstance().setMapListScanListener(new ScanNaviList.OnMapListScanListener() { // from class: com.wits.ksw.settings.audi.vm.NaviVm.1
            @Override // com.wits.ksw.settings.utlis_view.ScanNaviList.OnMapListScanListener
            public void onScanFinish(List<MapBean> mapList) {
                NaviVm.this.naviList.postValue(mapList);
            }
        });
        startNaviList();
    }

    public void startNaviList() {
        try {
            List<String> naviList = PowerManagerApp.getDataListFromJsonKey(KeyConfig.NAVI_LIST);
            String navidefual = PowerManagerApp.getSettingsString(KeyConfig.NAVI_DEFUAL);
            if (naviList != null && naviList.size() > 0) {
                ScanNaviList.getInstance().scanList(naviList, navidefual, this.context);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
