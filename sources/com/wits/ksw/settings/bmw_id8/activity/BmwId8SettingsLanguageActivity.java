package com.wits.ksw.settings.bmw_id8.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.databinding.BmwId8SettingsLanguageLayoutBinding;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsLanguageAdapter;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes11.dex */
public class BmwId8SettingsLanguageActivity extends BaseSkinActivity {
    private List<FunctionBean> data;
    private LinearLayoutManager layoutManager;
    private List<Locale> locales;
    private BmwId8SettingsLanguageAdapter mAdapter;
    private BmwId8SettingsLanguageLayoutBinding mBinding;
    private BmwId8SettingsViewModel mViewModel;
    private final String TAG = "BmwId8SettingsLanguageActivity";
    private int defLanguage = 0;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = new BaseQuickAdapter.OnItemClickListener() { // from class: com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsLanguageActivity.2
        @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
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

    @Override // com.wits.ksw.settings.BaseSkinActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8SettingsLanguageActivity", " onCreate ");
        this.mBinding = (BmwId8SettingsLanguageLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.bmw_id8_settings_language_layout);
        BmwId8SettingsViewModel bmwId8SettingsViewModel = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = bmwId8SettingsViewModel;
        this.mBinding.setViewModel(bmwId8SettingsViewModel);
        LocaleList();
        initData();
        initView();
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8SettingsLanguageActivity", " onNewIntent ");
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
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
        this.mBinding.languageRecycle.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsLanguageActivity.1
            @Override // android.support.p004v7.widget.RecyclerView.ItemDecoration
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

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.i("BmwId8SettingsLanguageActivity", " onStop ");
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8SettingsLanguageActivity", " onDestroy ");
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
