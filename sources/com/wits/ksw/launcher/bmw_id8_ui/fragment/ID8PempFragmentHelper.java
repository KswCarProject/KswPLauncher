package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentActivity;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentTransaction;
import android.util.Log;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.PempID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8PempFragment3rdApp;
import com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8PempFragmentWeather;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes14.dex */
public class ID8PempFragmentHelper {
    private static final String TAG = "ID8PempFragmentHelper";
    private static ID8PempFragmentHelper id8PempFragmentHelper;
    private static FragmentManager manager;
    private ArrayList<Integer> flList;
    private ID8PempFragmentCarInfo fragmentCarInfo;
    private ID8PempFragmentDashboard fragmentDashboard;
    private ID8PempFragmentModus fragmentModus;
    private ID8PempFragmentMusic fragmentMusic;
    private ID8PempFragmentNavigate fragmentNavigate;
    private ID8PempFragmentPhone fragmentPhone;
    private ID8PempFragmentSetting fragmentSetting;
    private ID8PempFragmentVideo fragmentVideo;
    private ID8PempFragmentWeather fragmentWeather;
    private HashMap<String, Fragment> systemFragmentHashMap;
    private HashMap<String, Fragment> trdFragmentHashMap;
    private final ID8PempFragment3rdApp.TrdAppRemoveListener trdAppRemoveListener = new ID8PempFragment3rdApp.TrdAppRemoveListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8PempFragmentHelper.1
        @Override // com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8PempFragment3rdApp.TrdAppRemoveListener
        public void onTrdAppRemove() {
            ID8PempFragmentHelper.this.locateFragmentPosition();
        }
    };
    private final ID8PempFragmentWeather.WeatherRemoveListener weatherRemoveListener = new ID8PempFragmentWeather.WeatherRemoveListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8PempFragmentHelper.2
        @Override // com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8PempFragmentWeather.WeatherRemoveListener
        public void onWeatherAppRemove() {
            ID8PempFragmentHelper.this.locateFragmentPosition();
        }
    };

    private ID8PempFragmentHelper() {
        initFragment();
    }

    public static ID8PempFragmentHelper getInstance(FragmentActivity fragmentActivity) {
        Log.d(TAG, "getInstance: ");
        if (id8PempFragmentHelper == null) {
            id8PempFragmentHelper = new ID8PempFragmentHelper();
        }
        manager = fragmentActivity.getSupportFragmentManager();
        return id8PempFragmentHelper;
    }

    public void locateFragmentPosition() {
        Fragment fragment;
        Log.d(TAG, "locateFragmentPosition: ");
        List<String> nameList = PempID8LauncherConstants.nameList;
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
        Fragment fragment2 = new ID8PempFragment3rdApp(tag, this.trdAppRemoveListener);
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
        this.fragmentNavigate = new ID8PempFragmentNavigate();
        this.fragmentWeather = new ID8PempFragmentWeather(this.weatherRemoveListener);
        this.fragmentMusic = new ID8PempFragmentMusic();
        this.fragmentDashboard = new ID8PempFragmentDashboard();
        this.fragmentModus = new ID8PempFragmentModus();
        this.fragmentPhone = new ID8PempFragmentPhone();
        this.fragmentCarInfo = new ID8PempFragmentCarInfo();
        this.fragmentSetting = new ID8PempFragmentSetting();
        this.fragmentVideo = new ID8PempFragmentVideo();
        HashMap<String, Fragment> hashMap = new HashMap<>();
        this.systemFragmentHashMap = hashMap;
        hashMap.put("NAVIGATE", this.fragmentNavigate);
        this.systemFragmentHashMap.put("WEATHER", this.fragmentWeather);
        this.systemFragmentHashMap.put("MUSIC", this.fragmentMusic);
        this.systemFragmentHashMap.put("DASHBOARD", this.fragmentDashboard);
        this.systemFragmentHashMap.put("MODUS", this.fragmentModus);
        this.systemFragmentHashMap.put("PHONE", this.fragmentPhone);
        this.systemFragmentHashMap.put("CAR INFO", this.fragmentCarInfo);
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
