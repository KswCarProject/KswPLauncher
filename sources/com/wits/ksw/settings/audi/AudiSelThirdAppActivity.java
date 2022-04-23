package com.wits.ksw.settings.audi;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiSelThirdCls;
import com.wits.ksw.launcher.adpater.VerRecyclerAdapter;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;

public class AudiSelThirdAppActivity extends AudiSubActivity {
    private RecyclerView listview_music;
    private RecyclerView listview_video;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiSelThirdCls bindingCls = (AudiSelThirdCls) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.audi_sel_third_app_layout, (ViewGroup) null, false);
        this.contentLayout.addView(bindingCls.getRoot(), -1, -1);
        bindingCls.setVm(AudiSettingMainActivity.getInstance.viewModel);
        String type = getIntent().getStringExtra("TYPE_SEL");
        if ("VALUE_MUSIC".equals(type)) {
            bindingCls.listviewMusic.setVisibility(0);
            bindingCls.listviewVideo.setVisibility(8);
            this.tv_title_set.setText(getResources().getString(R.string.music_app_sel));
        } else if ("VALUE_VIDEO".equals(type)) {
            bindingCls.listviewMusic.setVisibility(8);
            bindingCls.listviewVideo.setVisibility(0);
            this.tv_title_set.setText(getResources().getString(R.string.video_app_sel));
        }
        this.listview_music = bindingCls.listviewMusic;
        this.listview_video = bindingCls.listviewVideo;
        VerRecyclerAdapter adapterMusic = new VerRecyclerAdapter(this, AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 1, this), R.layout.app_third_item_audi);
        this.listview_music.setAdapter(adapterMusic);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(1);
        this.listview_music.setLayoutManager(layoutManager);
        new DividerItemDecoration(this, 1).setDrawable(ContextCompat.getDrawable(this, R.mipmap.ntg55_right_big_line1));
        adapterMusic.setAppsCheckListener(new VerRecyclerAdapter.IAppsCheckListener() {
            public void checkedListener(String pkg, String cls, int pos) {
                Log.i("liuhao", "music app pkg =  " + pkg + "  cls = " + cls);
                Settings.System.putString(AudiSelThirdAppActivity.this.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_PKG, pkg);
                Settings.System.putString(AudiSelThirdAppActivity.this.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS, cls);
                if (cls.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                    LauncherViewModel.setThirdMusic(false);
                } else {
                    LauncherViewModel.setThirdMusic(true);
                }
            }
        });
        VerRecyclerAdapter adapterVideo = new VerRecyclerAdapter(this, AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 2, this), R.layout.app_third_item_audi);
        this.listview_video.setAdapter(adapterVideo);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(1);
        this.listview_video.setLayoutManager(layoutManager1);
        new DividerItemDecoration(this, 1).setDrawable(ContextCompat.getDrawable(this, R.mipmap.ntg55_right_big_line1));
        adapterVideo.setAppsCheckListener(new VerRecyclerAdapter.IAppsCheckListener() {
            public void checkedListener(String pkg, String cls, int pos) {
                Log.i("liuhao", "video app pkg =  " + pkg + "  cls = " + cls);
                Settings.System.putString(AudiSelThirdAppActivity.this.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_PKG, pkg);
                Settings.System.putString(AudiSelThirdAppActivity.this.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_CLS, cls);
                if (cls.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                    LauncherViewModel.setThirdVideo(false);
                } else {
                    LauncherViewModel.setThirdVideo(true);
                }
            }
        });
    }
}
