package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentActivity;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentTransaction;
import android.util.Log;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8Fragment3rdApp;
import com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8FragmentWeather;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes14.dex */
public class ID8FragmentHelper {
    private static final String TAG = "ID8FragmentHelper";
    private static ID8FragmentHelper id8FragmentHelper;
    private static FragmentManager manager;
    private ArrayList<Integer> flList;
    private ID8FragmentCarInfo fragmentCarInfo;
    private ID8FragmentDashboard fragmentDashboard;
    private ID8FragmentModus fragmentModus;
    private ID8FragmentMusic fragmentMusic;
    private ID8FragmentNavigate fragmentNavigate;
    private ID8FragmentPhone fragmentPhone;
    private ID8FragmentSetting fragmentSetting;
    private ID8FragmentVideo fragmentVideo;
    private ID8FragmentWeather fragmentWeather;
    private HashMap<String, Fragment> systemFragmentHashMap;
    private HashMap<String, Fragment> trdFragmentHashMap;
    private ID8Fragment3rdApp.TrdAppRemoveListener trdAppRemoveListener = new ID8Fragment3rdApp.TrdAppRemoveListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8FragmentHelper.1
        @Override // com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8Fragment3rdApp.TrdAppRemoveListener
        public void onTrdAppRemove() {
            ID8FragmentHelper.this.locateFragmentPosition();
        }
    };
    private ID8FragmentWeather.WeatherRemoveListener weatherRemoveListener = new ID8FragmentWeather.WeatherRemoveListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8FragmentHelper.2
        @Override // com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8FragmentWeather.WeatherRemoveListener
        public void onWeatherAppRemove() {
            ID8FragmentHelper.this.locateFragmentPosition();
        }
    };

    private ID8FragmentHelper() {
        initFragment();
    }

    public static ID8FragmentHelper getInstance(FragmentActivity fragmentActivity) {
        if (id8FragmentHelper == null) {
            id8FragmentHelper = new ID8FragmentHelper();
        }
        manager = fragmentActivity.getSupportFragmentManager();
        return id8FragmentHelper;
    }

    public void locateFragmentPosition() {
        Fragment fragment;
        List<String> nameList = ID8LauncherConstants.nameList;
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
        Fragment fragment2 = new ID8Fragment3rdApp(tag, this.trdAppRemoveListener);
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
        this.fragmentNavigate = new ID8FragmentNavigate();
        this.fragmentWeather = new ID8FragmentWeather(this.weatherRemoveListener);
        this.fragmentMusic = new ID8FragmentMusic();
        this.fragmentCarInfo = new ID8FragmentCarInfo();
        this.fragmentModus = new ID8FragmentModus();
        this.fragmentPhone = new ID8FragmentPhone();
        this.fragmentDashboard = new ID8FragmentDashboard();
        this.fragmentSetting = new ID8FragmentSetting();
        this.fragmentVideo = new ID8FragmentVideo();
        HashMap<String, Fragment> hashMap = new HashMap<>();
        this.systemFragmentHashMap = hashMap;
        hashMap.put("NAVIGATE", this.fragmentNavigate);
        this.systemFragmentHashMap.put("WEATHER", this.fragmentWeather);
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
