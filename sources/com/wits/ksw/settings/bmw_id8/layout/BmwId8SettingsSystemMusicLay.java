package com.wits.ksw.settings.bmw_id8.layout;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.provider.Settings;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.BmwId8SettingsSystemMusicLayoutBinding;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsMusicAdapter;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import java.util.List;

/* loaded from: classes8.dex */
public class BmwId8SettingsSystemMusicLay extends RelativeLayout {
    private final String TAG;
    private Context context;
    private BmwId8SettingsSystemMusicLayoutBinding mBinding;
    private LinearLayoutManager mLinearLayoutManager;
    private BmwId8SettingsViewModel mViewModel;

    public BmwId8SettingsSystemMusicLay(Context context) {
        super(context);
        this.TAG = "BmwId8SettingsSystemMusicLay";
        this.context = context;
        this.mBinding = (BmwId8SettingsSystemMusicLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context), C0899R.C0902layout.bmw_id8_settings_system_music_layout, null, false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mBinding.getRoot().setLayoutParams(layoutParams);
        BmwId8SettingsViewModel bmwId8SettingsViewModel = BmwId8SettingsViewModel.getInstance();
        this.mViewModel = bmwId8SettingsViewModel;
        this.mBinding.setViewModel(bmwId8SettingsViewModel);
        addView(this.mBinding.getRoot());
        initView();
        initData();
    }

    private void initView() {
        try {
            final List<LexusLsAppSelBean> listMusic = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 1, this.context);
            final BmwId8SettingsMusicAdapter adapterMusic = new BmwId8SettingsMusicAdapter(listMusic);
            adapterMusic.setHasStableIds(true);
            this.mBinding.bmwId8SettingsMusicRecycle.setAdapter(adapterMusic);
            this.mBinding.bmwId8SettingsMusicRecycle.setItemAnimator(null);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
            this.mLinearLayoutManager = linearLayoutManager;
            linearLayoutManager.setOrientation(1);
            this.mBinding.bmwId8SettingsMusicRecycle.setLayoutManager(this.mLinearLayoutManager);
            this.mBinding.bmwId8SettingsMusicRecycle.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemMusicLay.1
                @Override // android.support.p004v7.widget.RecyclerView.ItemDecoration
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
                    Log.i("BmwId8SettingsSystemMusicLay", " getItemOffsets position " + position);
                    if (position != listMusic.size() - 1) {
                        outRect.bottom = -ScreenUtil.dip2px(4.5f);
                    }
                }
            });
            adapterMusic.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemMusicLay.2
                @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Log.i("BmwId8SettingsSystemMusicLay", "onItemClick position " + position);
                    for (LexusLsAppSelBean bean : listMusic) {
                        bean.setChecked(false);
                    }
                    ((LexusLsAppSelBean) listMusic.get(position)).setChecked(true);
                    adapterMusic.notifyDataSetChanged();
                    String pkg = ((LexusLsAppSelBean) listMusic.get(position)).getAppPkg();
                    String cls = ((LexusLsAppSelBean) listMusic.get(position)).getAppMainAty();
                    Settings.System.putString(BmwId8SettingsSystemMusicLay.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_PKG, pkg);
                    Settings.System.putString(BmwId8SettingsSystemMusicLay.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS, cls);
                    Settings.System.putString(BmwId8SettingsSystemMusicLay.this.context.getContentResolver(), "KEY_SHORTCUT_PKG_1", pkg);
                    Settings.System.putString(BmwId8SettingsSystemMusicLay.this.context.getContentResolver(), "KEY_SHORTCUT_CLS_1", cls);
                    if (cls.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                        LauncherViewModel.setThirdMusic(false);
                    } else {
                        LauncherViewModel.setThirdMusic(true);
                    }
                }
            });
            this.mBinding.bmwId8SettingsMusicRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemMusicLay.3
                @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == 0 && BmwId8SettingsSystemMusicLay.this.mBinding.bmwId8SettingsMusicRecycle.hasFocus()) {
                        int first = BmwId8SettingsSystemMusicLay.this.mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
                        int focusIndex = BmwId8SettingsSystemMusicLay.this.mBinding.bmwId8SettingsMusicRecycle.getChildAdapterPosition(BmwId8SettingsSystemMusicLay.this.mBinding.bmwId8SettingsMusicRecycle.getFocusedChild());
                        Log.e("BmwId8SettingsSystemMusicLay", "onScrollStateChanged: focusIndex " + focusIndex + " first " + first);
                        if (focusIndex < 0 && first > -1) {
                            BmwId8SettingsSystemMusicLay.this.mLinearLayoutManager.findViewByPosition(first).requestFocus();
                        }
                    }
                }

                @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });
            this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemMusicLay.4
                @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
                public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                    Log.i("BmwId8SettingsSystemMusicLay", "onGlobalFocusChanged: " + BmwId8SettingsSystemMusicLay.this.mBinding.bmwId8SettingsMusicRecycle.hasFocus());
                    if (BmwId8SettingsSystemMusicLay.this.mBinding.bmwId8SettingsMusicRecycle.hasFocus()) {
                        BmwId8SettingsSystemMusicLay.this.mViewModel.systemBgShow.set(false);
                    }
                }
            });
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initData() {
    }
}
