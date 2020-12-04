package com.wits.ksw.settings.id6.showLayout;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.id6.adapter.ID6LanguageAdapter;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ID6ShowLanguageLayout extends RelativeLayout {
    private ID6LanguageAdapter adapter;
    private Context context;
    private List<FunctionBean> data;
    private int defLanguage = 0;
    private LinearLayoutManager layoutManager;
    private List<Locale> locales;
    private RecyclerView recyclerView;
    private View view;

    @RequiresApi(api = 24)
    public ID6ShowLanguageLayout(Context context2) {
        super(context2);
        this.context = context2;
        this.view = LayoutInflater.from(context2).inflate(R.layout.layout_id6_show_language, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        LocaleList();
        initData();
        initView(this.view);
        this.view.setLayoutParams(layoutParams);
        addView(this.view);
    }

    @RequiresApi(api = 24)
    private void initData() {
        try {
            this.defLanguage = PowerManagerApp.getSettingsInt(KeyConfig.LANGUAGE_DEFUAL);
            this.data = new ArrayList();
            List<String> languags = PowerManagerApp.getDataListFromJsonKey(KeyConfig.LANGUAGE_LIST);
            for (String lang : languags) {
                Log.d("SettingSetLaung", "=====:" + lang);
                FunctionBean fb = new FunctionBean();
                fb.setTitle(lang);
                this.data.add(fb);
            }
            this.data.get(this.defLanguage).setIscheck(true);
            Locale sysLanguage = Locale.getDefault();
            Log.d("SystemLa", "la:" + sysLanguage.getLanguage() + "dw:" + sysLanguage.getCountry());
            int checkInex = -1;
            boolean ishave = false;
            for (int i = 0; i < languags.size(); i++) {
                if (TextUtils.equals(this.locales.get(i).getLanguage(), sysLanguage.getLanguage()) && this.locales.get(i).getCountry().equals(sysLanguage.getCountry())) {
                    ishave = true;
                    checkInex = i;
                }
            }
            if (ishave) {
                for (FunctionBean lsg : this.data) {
                    lsg.setIscheck(false);
                }
                this.data.get(checkInex).setIscheck(true);
                return;
            }
            for (FunctionBean lsg2 : this.data) {
                lsg2.setIscheck(false);
            }
            FunctionBean fb2 = new FunctionBean();
            fb2.setTitle("Other Launguae");
            fb2.setIscheck(true);
            this.data.add(fb2);
        } catch (Exception e) {
        }
    }

    private void initView(View view2) {
        this.recyclerView = (RecyclerView) view2.findViewById(R.id.language_recycle);
        this.layoutManager = new LinearLayoutManager(this.context);
        this.layoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        if (Build.DISPLAY.contains("8937")) {
            this.adapter = new ID6LanguageAdapter(this.context, this.data, 1);
        } else {
            this.adapter = new ID6LanguageAdapter(this.context, this.data.subList(0, 3), 1);
        }
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setEnabled(false);
    }

    @RequiresApi(api = 24)
    public void updateView() {
        initData();
        initView(this.view);
    }

    private void LocaleList() {
        this.locales = new ArrayList();
        this.locales.add(new Locale("zh", "CN"));
        this.locales.add(new Locale("zh", "TW"));
        this.locales.add(new Locale("en"));
        this.locales.add(new Locale("de"));
        this.locales.add(new Locale("es"));
        this.locales.add(new Locale("ko", "KR"));
        this.locales.add(new Locale("it"));
        this.locales.add(new Locale("nl"));
        this.locales.add(new Locale("pt"));
        this.locales.add(new Locale("ru"));
        this.locales.add(new Locale("ja"));
    }
}
