package com.wits.ksw.settings.audi_mib3;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.DividerItemDecoration;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.AudiMbi3SelThirdCls;
import com.wits.ksw.launcher.adpater.AudiMib3RecyclerAdapter;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import java.util.List;

/* loaded from: classes15.dex */
public class AudiMbi3SelThirdAppActivity extends AudiMib3SubActivity {
    private RecyclerView listview_music;
    private RecyclerView listview_video;

    @Override // com.wits.ksw.settings.audi_mib3.AudiMib3SubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudiMbi3SelThirdCls bindingCls = (AudiMbi3SelThirdCls) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.audimbi3_sel_third_app_layout, null, false);
        this.contentLayout.addView(bindingCls.getRoot(), -1, -1);
        bindingCls.setVm(AudiMib3SettingMainActivity.getInstance.viewModel);
        String type = getIntent().getStringExtra("TYPE_SEL");
        if ("VALUE_MUSIC".equals(type)) {
            bindingCls.listviewMusic.setVisibility(0);
            bindingCls.listviewVideo.setVisibility(8);
            bindingCls.tvTitle.setText(getResources().getString(C0899R.string.music_app_sel));
            bindingCls.ivTitle.setImageResource(C0899R.C0900drawable.id7_main_music_n);
            bindingCls.ivShow.setImageResource(C0899R.C0900drawable.id7_main_music_n);
            this.tv_title_set.setText(getResources().getString(C0899R.string.music_app_sel));
        } else if ("VALUE_VIDEO".equals(type)) {
            bindingCls.listviewMusic.setVisibility(8);
            bindingCls.listviewVideo.setVisibility(0);
            bindingCls.tvTitle.setText(getResources().getString(C0899R.string.video_app_sel));
            bindingCls.ivTitle.setImageResource(C0899R.C0900drawable.id7_main_video_n);
            bindingCls.ivShow.setImageResource(C0899R.C0900drawable.id7_main_video_n);
            this.tv_title_set.setText(getResources().getString(C0899R.string.video_app_sel));
        }
        this.listview_music = bindingCls.listviewMusic;
        this.listview_video = bindingCls.listviewVideo;
        List<LexusLsAppSelBean> listMusic = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 1, this);
        AudiMib3RecyclerAdapter adapterMusic = new AudiMib3RecyclerAdapter(this, listMusic, C0899R.C0902layout.app_third_item_audimbi3_1);
        this.listview_music.setAdapter(adapterMusic);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(1);
        this.listview_music.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this, 1);
        divider.setDrawable(ContextCompat.getDrawable(this, C0899R.C0900drawable.audi_mib3_settings_list_line));
        this.listview_music.addItemDecoration(divider);
        adapterMusic.setAppsCheckListener(new AudiMib3RecyclerAdapter.IAppsCheckListener() { // from class: com.wits.ksw.settings.audi_mib3.AudiMbi3SelThirdAppActivity.1
            @Override // com.wits.ksw.launcher.adpater.AudiMib3RecyclerAdapter.IAppsCheckListener
            public void checkedListener(String pkg, String cls, int pos) {
                Log.i("liuhao", "music app pkg =  " + pkg + "  cls = " + cls);
                Settings.System.putString(AudiMbi3SelThirdAppActivity.this.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_PKG, pkg);
                Settings.System.putString(AudiMbi3SelThirdAppActivity.this.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS, cls);
                if (cls.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                    LauncherViewModel.setThirdMusic(false);
                } else {
                    LauncherViewModel.setThirdMusic(true);
                }
            }
        });
        List<LexusLsAppSelBean> listVideo = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 2, this);
        AudiMib3RecyclerAdapter adapterVideo = new AudiMib3RecyclerAdapter(this, listVideo, C0899R.C0902layout.app_third_item_audimbi3_1);
        this.listview_video.setAdapter(adapterVideo);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(1);
        this.listview_video.setLayoutManager(layoutManager1);
        DividerItemDecoration divider1 = new DividerItemDecoration(this, 1);
        divider1.setDrawable(ContextCompat.getDrawable(this, C0899R.C0900drawable.audi_mib3_settings_list_line));
        this.listview_video.addItemDecoration(divider1);
        adapterVideo.setAppsCheckListener(new AudiMib3RecyclerAdapter.IAppsCheckListener() { // from class: com.wits.ksw.settings.audi_mib3.AudiMbi3SelThirdAppActivity.2
            @Override // com.wits.ksw.launcher.adpater.AudiMib3RecyclerAdapter.IAppsCheckListener
            public void checkedListener(String pkg, String cls, int pos) {
                Log.i("liuhao", "video app pkg =  " + pkg + "  cls = " + cls);
                Settings.System.putString(AudiMbi3SelThirdAppActivity.this.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_PKG, pkg);
                Settings.System.putString(AudiMbi3SelThirdAppActivity.this.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_CLS, cls);
                if (cls.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                    LauncherViewModel.setThirdVideo(false);
                } else {
                    LauncherViewModel.setThirdVideo(true);
                }
            }
        });
    }
}
