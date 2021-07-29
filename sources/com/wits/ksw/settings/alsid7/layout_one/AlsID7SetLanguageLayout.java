package com.wits.ksw.settings.alsid7.layout_one;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
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
import com.wits.ksw.settings.alsid7.adapter.AlsID7LanguageAdapter;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AlsID7SetLanguageLayout extends RelativeLayout {
    /* access modifiers changed from: private */
    public AlsID7LanguageAdapter adapter;
    private Context context;
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    private int defLanguage = 0;
    private LinearLayoutManager layoutManager;
    private List<Locale> locales;
    private RecyclerView recyclerView;

    public AlsID7SetLanguageLayout(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_set_language, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        LocaleList();
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

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
            boolean ishave = false;
            int checkInex = -1;
            Log.d("SystemLa", "21 la:" + sysLanguage.getLanguage() + "dw:" + sysLanguage.getCountry());
            for (int i = 0; i < languags.size(); i++) {
                Log.d("SystemLa", "language:" + this.locales.get(i).getLanguage() + "country:" + this.locales.get(i).getCountry());
                Log.d("SystemLa", "22 la:" + sysLanguage.getLanguage() + "dw:" + sysLanguage.getCountry());
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

    private void initView(View view) {
        this.recyclerView = (RecyclerView) view.findViewById(R.id.language_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        AlsID7LanguageAdapter alsID7LanguageAdapter = new AlsID7LanguageAdapter(this.context, this.data);
        this.adapter = alsID7LanguageAdapter;
        this.recyclerView.setAdapter(alsID7LanguageAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this.context, 1);
        divider.setDrawable(ContextCompat.getDrawable(this.context, R.mipmap.als_id7_lp_line));
        this.recyclerView.addItemDecoration(divider);
        this.recyclerView.setItemViewCacheSize(30);
        this.adapter.registOnFunctionClickListener(new AlsID7LanguageAdapter.OnFunctionClickListener() {
            public void functonClick(int pos) {
                for (FunctionBean fb : AlsID7SetLanguageLayout.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) AlsID7SetLanguageLayout.this.data.get(pos)).setIscheck(true);
                AlsID7SetLanguageLayout.this.adapter.notifyDataSetChanged();
                try {
                    PowerManagerApp.setSettingsInt(KeyConfig.LANGUAGE_DEFUAL, pos);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });
    }

    private void LocaleList() {
        ArrayList arrayList = new ArrayList();
        this.locales = arrayList;
        arrayList.add(new Locale("zh", "CN"));
        this.locales.add(new Locale("zh", "TW"));
        this.locales.add(new Locale("en"));
        this.locales.add(new Locale("de"));
        this.locales.add(new Locale("es"));
        this.locales.add(new Locale("ko", "KR"));
        this.locales.add(new Locale("it"));
        this.locales.add(new Locale("nl"));
        this.locales.add(new Locale("ru"));
        this.locales.add(new Locale("fr"));
    }
}
