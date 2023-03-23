package com.wits.ksw.settings.bmw_id8.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BmwId8SettingsLanguageLayoutBinding;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsLanguageAdapter;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BmwId8SettingsLanguageActivity extends BaseSkinActivity {
    private final String TAG = "BmwId8SettingsLanguageActivity";
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    private int defLanguage = 0;
    private LinearLayoutManager layoutManager;
    private List<Locale> locales;
    /* access modifiers changed from: private */
    public BmwId8SettingsLanguageAdapter mAdapter;
    private BmwId8SettingsLanguageLayoutBinding mBinding;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Log.i("BmwId8SettingsLanguageActivity", " position " + position);
            for (FunctionBean fb : BmwId8SettingsLanguageActivity.this.data) {
                fb.setIscheck(false);
            }
            ((FunctionBean) BmwId8SettingsLanguageActivity.this.data.get(position)).setIscheck(true);
            BmwId8SettingsLanguageActivity.this.mAdapter.notifyDataSetChanged();
            try {
                PowerManagerApp.setSettingsInt(KeyConfig.LANGUAGE_DEFUAL, position);
            } catch (Exception e) {
                e.getStackTrace();
            }
            MainActivity.settingLanguage = true;
        }
    };
    private BmwId8SettingsViewModel mViewModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8SettingsLanguageActivity", " onCreate ");
        this.mBinding = (BmwId8SettingsLanguageLayoutBinding) DataBindingUtil.setContentView(this, R.layout.bmw_id8_settings_language_layout);
        BmwId8SettingsViewModel instance = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = instance;
        this.mBinding.setViewModel(instance);
        LocaleList();
        initData();
        initView();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8SettingsLanguageActivity", " onNewIntent ");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.i("BmwId8SettingsLanguageActivity", " onResume ");
        if (getCurrentFocus() == null) {
            this.mBinding.languageRecycle.requestFocus();
        }
    }

    private void initData() {
        try {
            this.defLanguage = PowerManagerApp.getSettingsInt(KeyConfig.LANGUAGE_DEFUAL);
            this.data = new ArrayList();
            List<String> languags = PowerManagerApp.getDataListFromJsonKey(KeyConfig.LANGUAGE_LIST);
            for (String lang : languags) {
                Log.d("BmwId8SettingsLanguageActivity", "=====:" + lang);
                FunctionBean fb = new FunctionBean();
                fb.setTitle(lang);
                this.data.add(fb);
            }
            this.data.get(this.defLanguage).setIscheck(true);
            Locale sysLanguage = Locale.getDefault();
            boolean ishave = false;
            int checkInex = -1;
            Log.d("BmwId8SettingsLanguageActivity", "21 la:" + sysLanguage.getLanguage() + "dw:" + sysLanguage.getCountry());
            for (int i = 0; i < languags.size(); i++) {
                Log.d("BmwId8SettingsLanguageActivity", "language:" + this.locales.get(i).getLanguage() + "country:" + this.locales.get(i).getCountry());
                Log.d("BmwId8SettingsLanguageActivity", "22 la:" + sysLanguage.getLanguage() + "dw:" + sysLanguage.getCountry());
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
            e.getStackTrace();
        }
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.mBinding.languageRecycle.setLayoutManager(this.layoutManager);
        this.mAdapter = new BmwId8SettingsLanguageAdapter(this.data);
        this.mBinding.languageRecycle.setAdapter(this.mAdapter);
        this.mBinding.languageRecycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
                Log.i("BmwId8SettingsLanguageActivity", " getItemOffsets position " + position);
                if (position != BmwId8SettingsLanguageActivity.this.data.size() - 1) {
                    outRect.bottom = -ScreenUtil.dip2px(4.5f);
                }
            }
        });
        this.mAdapter.setOnItemClickListener(this.mOnItemClickListener);
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

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i("BmwId8SettingsLanguageActivity", " onStop ");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8SettingsLanguageActivity", " onDestroy ");
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
