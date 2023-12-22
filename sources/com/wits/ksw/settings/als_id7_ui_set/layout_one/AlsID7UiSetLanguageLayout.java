package com.wits.ksw.settings.als_id7_ui_set.layout_one;

import android.content.Context;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.DividerItemDecoration;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiLanguageAdapter;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class AlsID7UiSetLanguageLayout extends RelativeLayout {
    private AlsID7UiLanguageAdapter adapter;
    private Context context;
    private List<FunctionBean> data;
    private int defLanguage;
    private LinearLayoutManager layoutManager;
    private List<Locale> locales;
    private RecyclerView recyclerView;

    public AlsID7UiSetLanguageLayout(Context context) {
        super(context);
        this.defLanguage = 0;
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.als_id7_ui_layout_set_language, (ViewGroup) null);
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
                if (TextUtils.equals(this.locales.get(i).getLanguage(), "zh")) {
                    if (this.locales.get(i).getCountry().equals(sysLanguage.getCountry())) {
                        ishave = true;
                        checkInex = i;
                    } else if (TextUtils.equals(sysLanguage.getCountry(), "HK") || TextUtils.equals(sysLanguage.getCountry(), "MO")) {
                        ishave = true;
                        checkInex = 0;
                    }
                } else if (TextUtils.equals(this.locales.get(i).getLanguage(), sysLanguage.getLanguage())) {
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
        } catch (Exception e) {
        }
    }

    private void initView(View view) {
        this.recyclerView = (RecyclerView) view.findViewById(C0899R.C0901id.language_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        AlsID7UiLanguageAdapter alsID7UiLanguageAdapter = new AlsID7UiLanguageAdapter(this.context, this.data);
        this.adapter = alsID7UiLanguageAdapter;
        this.recyclerView.setAdapter(alsID7UiLanguageAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this.context, 1);
        divider.setDrawable(ContextCompat.getDrawable(this.context, C0899R.mipmap.als_id7_lp_line));
        this.recyclerView.addItemDecoration(divider);
        this.recyclerView.setItemViewCacheSize(30);
        this.adapter.registOnFunctionClickListener(new AlsID7UiLanguageAdapter.OnFunctionClickListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.layout_one.AlsID7UiSetLanguageLayout.1
            @Override // com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiLanguageAdapter.OnFunctionClickListener
            public void functonClick(int pos) {
                for (FunctionBean fb : AlsID7UiSetLanguageLayout.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) AlsID7UiSetLanguageLayout.this.data.get(pos)).setIscheck(true);
                AlsID7UiSetLanguageLayout.this.adapter.notifyDataSetChanged();
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
        this.locales.add(Locale.KOREA);
        this.locales.add(new Locale("it"));
        this.locales.add(new Locale("nl"));
        this.locales.add(new Locale("ru"));
        this.locales.add(new Locale("fr"));
        this.locales.add(new Locale("pt"));
        this.locales.add(new Locale("tr"));
        this.locales.add(new Locale("vi"));
        this.locales.add(new Locale("pl"));
        this.locales.add(new Locale("ar"));
        this.locales.add(new Locale("ja"));
        this.locales.add(new Locale("iw", "IL"));
        this.locales.add(new Locale("el"));
        this.locales.add(new Locale("th"));
        this.locales.add(new Locale("hr"));
        this.locales.add(new Locale("cs"));
    }
}
