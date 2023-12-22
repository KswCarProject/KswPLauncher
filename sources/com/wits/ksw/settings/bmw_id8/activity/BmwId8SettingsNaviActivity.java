package com.wits.ksw.settings.bmw_id8.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.BmwId8SettingsNaviLayoutBinding;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsNaviAdapter;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import java.util.List;

/* loaded from: classes11.dex */
public class BmwId8SettingsNaviActivity extends BaseSkinActivity implements ScanNaviList.OnMapListScanListener {
    private LinearLayoutManager layoutManager;
    private BmwId8SettingsNaviAdapter mAdapter;
    private BmwId8SettingsNaviLayoutBinding mBinding;
    private BmwId8SettingsViewModel mViewModel;
    private List<MapBean> mapBanList;
    private final String TAG = "BmwId8SettingsNaviActivity";
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = new BaseQuickAdapter.OnItemClickListener() { // from class: com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsNaviActivity.2
        @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
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
                BaseSkinActivity.forceStopPackage(BmwId8SettingsNaviActivity.this, mapBean.getPackageName());
            }
            BmwId8SettingsNaviActivity.this.mAdapter.notifyDataSetChanged();
        }
    };
    Handler handler = new Handler() { // from class: com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsNaviActivity.3
        @Override // android.os.Handler
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

    @Override // com.wits.ksw.settings.BaseSkinActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8SettingsNaviActivity", " onCreate ");
        this.mBinding = (BmwId8SettingsNaviLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.bmw_id8_settings_navi_layout);
        BmwId8SettingsViewModel bmwId8SettingsViewModel = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = bmwId8SettingsViewModel;
        this.mBinding.setViewModel(bmwId8SettingsViewModel);
        initData();
        initView();
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8SettingsNaviActivity", " onNewIntent ");
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
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
        this.mBinding.naviRecycle.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.wits.ksw.settings.bmw_id8.activity.BmwId8SettingsNaviActivity.1
            @Override // android.support.p004v7.widget.RecyclerView.ItemDecoration
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

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    @Override // com.wits.ksw.settings.utlis_view.ScanNaviList.OnMapListScanListener
    public void onScanFinish(List<MapBean> mapList) {
        this.mapBanList = mapList;
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.handler.sendEmptyMessage(3);
        }
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.i("BmwId8SettingsNaviActivity", " onStop ");
        ScanNaviList.getInstance().setMapListScanListener(null);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8SettingsNaviActivity", " onDestroy ");
    }
}
