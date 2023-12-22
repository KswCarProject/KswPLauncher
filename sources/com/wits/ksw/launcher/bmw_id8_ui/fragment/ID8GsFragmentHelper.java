package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentActivity;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentTransaction;
import android.util.Log;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.GSID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8GsFragment3rdApp;
import com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8GsFragmentWeather;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes14.dex */
public class ID8GsFragmentHelper {
    private static final String TAG = "ID8GsFragmentHelper";
    private static ID8GsFragmentHelper id8GsFragmentHelper;
    private static FragmentManager manager;
    private ArrayList<Integer> flList;
    private ID8GsFragmentCarInfo fragmentCarInfo;
    private ID8GsFragmentDashboard fragmentDashboard;
    private ID8GsFragmentModus fragmentModus;
    private ID8GsFragmentMusic fragmentMusic;
    private ID8GsFragmentPhone fragmentPhone;
    private ID8GsFragmentSetting fragmentSetting;
    private ID8GsFragmentVideo fragmentVideo;
    private ID8GsFragmentWeather fragmentWeather;
    private HashMap<String, Fragment> systemFragmentHashMap;
    private HashMap<String, Fragment> trdFragmentHashMap;
    private ID8GsFragment3rdApp.TrdAppRemoveListener trdAppRemoveListener = new ID8GsFragment3rdApp.TrdAppRemoveListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8GsFragmentHelper.1
        @Override // com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8GsFragment3rdApp.TrdAppRemoveListener
        public void onTrdAppRemove() {
            ID8GsFragmentHelper.this.locateFragmentPosition();
        }
    };
    private ID8GsFragmentWeather.WeatherRemoveListener weatherRemoveListener = new ID8GsFragmentWeather.WeatherRemoveListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8GsFragmentHelper.2
        @Override // com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8GsFragmentWeather.WeatherRemoveListener
        public void onWeatherAppRemove() {
            ID8GsFragmentHelper.this.locateFragmentPosition();
        }
    };

    private ID8GsFragmentHelper() {
        initFragment();
    }

    public static ID8GsFragmentHelper getInstance(FragmentActivity fragmentActivity) {
        if (id8GsFragmentHelper == null) {
            id8GsFragmentHelper = new ID8GsFragmentHelper();
        }
        manager = fragmentActivity.getSupportFragmentManager();
        return id8GsFragmentHelper;
    }

    public void locateFragmentPosition() {
        Fragment fragment;
        List<String> nameList = GSID8LauncherConstants.nameList;
        Log.w(TAG, "locateFragmentPosition: " + nameList.toString());
        clear(manager, this.systemFragmentHashMap, this.trdFragmentHashMap);
        FragmentTransaction transaction = manager.beginTransaction();
        for (int i = 0; i < nameList.size(); i++) {
            String tag = String.valueOf(nameList.get(i));
            if (!tag.equals("ADD WIDGET")) {
                if (tag.startsWith("3rd")) {
                    fragment = getTrdFragment(tag);
                } else {
                    fragment = this.systemFragmentHashMap.get(tag);
                }
                if (fragment != null) {
                    transaction.add(this.flList.get(i).intValue(), fragment, tag);
                }
            }
        }
        transaction.commitNow();
    }

    private Fragment getTrdFragment(String tag) {
        Fragment fragment = this.trdFragmentHashMap.get(tag);
        if (fragment != null) {
            return fragment;
        }
        Fragment fragment2 = new ID8GsFragment3rdApp(tag, this.trdAppRemoveListener);
        this.trdFragmentHashMap.put(tag, fragment2);
        return fragment2;
    }

    private void clear(FragmentManager manager2, HashMap<String, Fragment> systemFragmentHashMap, HashMap<String, Fragment> trdFragmentHashMap) {
        FragmentTransaction transaction = manager2.beginTransaction();
        for (Fragment fragment : systemFragmentHashMap.values()) {
            transaction.remove(fragment);
        }
        for (Fragment fragment2 : trdFragmentHashMap.values()) {
            transaction.remove(fragment2);
        }
        transaction.commitNow();
    }

    private void initFragment() {
        this.fragmentWeather = new ID8GsFragmentWeather(this.weatherRemoveListener);
        this.fragmentMusic = new ID8GsFragmentMusic();
        this.fragmentCarInfo = new ID8GsFragmentCarInfo();
        this.fragmentModus = new ID8GsFragmentModus();
        this.fragmentPhone = new ID8GsFragmentPhone();
        this.fragmentDashboard = new ID8GsFragmentDashboard();
        this.fragmentSetting = new ID8GsFragmentSetting();
        this.fragmentVideo = new ID8GsFragmentVideo();
        HashMap<String, Fragment> hashMap = new HashMap<>();
        this.systemFragmentHashMap = hashMap;
        hashMap.put("WEATHER", this.fragmentWeather);
        this.systemFragmentHashMap.put("MUSIC", this.fragmentMusic);
        this.systemFragmentHashMap.put("CAR INFO", this.fragmentCarInfo);
        this.systemFragmentHashMap.put("MODUS", this.fragmentModus);
        this.systemFragmentHashMap.put("PHONE", this.fragmentPhone);
        this.systemFragmentHashMap.put("DASHBOARD", this.fragmentDashboard);
        this.systemFragmentHashMap.put("SETTING", this.fragmentSetting);
        this.systemFragmentHashMap.put("VIDEO", this.fragmentVideo);
        this.trdFragmentHashMap = new HashMap<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.flList = arrayList;
        arrayList.add(Integer.valueOf((int) C0899R.C0901id.fl_content1));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content2));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content3));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content4));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content5));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content6));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content7));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content8));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content9));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content10));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content11));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content12));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content13));
        this.flList.add(Integer.valueOf((int) C0899R.C0901id.fl_content14));
    }
}
