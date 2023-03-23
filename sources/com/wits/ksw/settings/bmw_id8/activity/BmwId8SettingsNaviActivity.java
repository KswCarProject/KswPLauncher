package com.wits.ksw.settings.bmw_id8.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BmwId8SettingsNaviLayoutBinding;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsNaviAdapter;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import java.util.List;

public class BmwId8SettingsNaviActivity extends BaseSkinActivity implements ScanNaviList.OnMapListScanListener {
    private final String TAG = "BmwId8SettingsNaviActivity";
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 3:
                    if (BmwId8SettingsNaviActivity.this.mAdapter != null) {
                        BmwId8SettingsNaviActivity.this.mAdapter.notifyDataSetChanged();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private LinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public BmwId8SettingsNaviAdapter mAdapter;
    private BmwId8SettingsNaviLayoutBinding mBinding;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Log.i("BmwId8SettingsNaviActivity", " position " + position);
            for (MapBean mpb : BmwId8SettingsNaviActivity.this.mapBanList) {
                mpb.setCheck(false);
            }
            MapBean mapBean = (MapBean) BmwId8SettingsNaviActivity.this.mapBanList.get(position);
            if (mapBean != null) {
                FileUtils.savaStringData(KeyConfig.NAVI_DEFUAL, mapBean.getPackageName());
                mapBean.setCheck(true);
                Settings.System.putString(BmwId8SettingsNaviActivity.this.getContentResolver(), "wits_freedom_pkg", mapBean.getPackageName());
            }
            BmwId8SettingsNaviActivity.this.mAdapter.notifyDataSetChanged();
        }
    };
    private BmwId8SettingsViewModel mViewModel;
    /* access modifiers changed from: private */
    public List<MapBean> mapBanList;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8SettingsNaviActivity", " onCreate ");
        this.mBinding = (BmwId8SettingsNaviLayoutBinding) DataBindingUtil.setContentView(this, R.layout.bmw_id8_settings_navi_layout);
        BmwId8SettingsViewModel instance = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = instance;
        this.mBinding.setViewModel(instance);
        initData();
        initView();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8SettingsNaviActivity", " onNewIntent ");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.i("BmwId8SettingsNaviActivity", " onResume ");
    }

    private void initData() {
        try {
            ScanNaviList.getInstance().setMapListScanListener(this);
            if (getCurrentFocus() == null) {
                this.mBinding.naviRecycle.requestFocus();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView() {
        this.mapBanList = ScanNaviList.getInstance().getMapList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.mBinding.naviRecycle.setLayoutManager(this.layoutManager);
        this.mAdapter = new BmwId8SettingsNaviAdapter(this.mapBanList);
        this.mBinding.naviRecycle.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this.mOnItemClickListener);
        this.mBinding.naviRecycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
                Log.i("BmwId8SettingsNaviActivity", " getItemOffsets position " + position);
                if (position != BmwId8SettingsNaviActivity.this.mapBanList.size() - 1) {
                    outRect.bottom = -ScreenUtil.dip2px(4.5f);
                }
            }
        });
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    public void onScanFinish(List<MapBean> mapList) {
        this.mapBanList = mapList;
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
            this.handler.sendEmptyMessage(3);
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i("BmwId8SettingsNaviActivity", " onStop ");
        ScanNaviList.getInstance().setMapListScanListener((ScanNaviList.OnMapListScanListener) null);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8SettingsNaviActivity", " onDestroy ");
    }
}
