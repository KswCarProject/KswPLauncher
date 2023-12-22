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
import com.wits.ksw.databinding.BmwId8SettingsSystemVideoLayoutBinding;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsVideoAdapter;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import java.util.List;

/* loaded from: classes8.dex */
public class BmwId8SettingsSystemVideoLay extends RelativeLayout {
    private final String TAG;
    private Context context;
    private BmwId8SettingsSystemVideoLayoutBinding mBinding;
    private LinearLayoutManager mLinearLayoutManager;
    private BmwId8SettingsViewModel mViewModel;

    public BmwId8SettingsSystemVideoLay(Context context) {
        super(context);
        this.TAG = "BmwId8SettingsSystemVideoLay";
        this.context = context;
        this.mBinding = (BmwId8SettingsSystemVideoLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(context), C0899R.C0902layout.bmw_id8_settings_system_video_layout, null, false);
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
            final List<LexusLsAppSelBean> listVideo = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 2, this.context);
            final BmwId8SettingsVideoAdapter adapterMusic = new BmwId8SettingsVideoAdapter(listVideo);
            adapterMusic.setHasStableIds(true);
            this.mBinding.bmwId8SettingsVideoRecycle.setAdapter(adapterMusic);
            this.mBinding.bmwId8SettingsVideoRecycle.setItemAnimator(null);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
            this.mLinearLayoutManager = linearLayoutManager;
            linearLayoutManager.setOrientation(1);
            this.mBinding.bmwId8SettingsVideoRecycle.setLayoutManager(this.mLinearLayoutManager);
            this.mBinding.bmwId8SettingsVideoRecycle.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemVideoLay.1
                @Override // android.support.p004v7.widget.RecyclerView.ItemDecoration
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
                    Log.i("BmwId8SettingsSystemVideoLay", " getItemOffsets position " + position);
                    if (position != listVideo.size() - 1) {
                        outRect.bottom = -ScreenUtil.dip2px(4.5f);
                    }
                }
            });
            adapterMusic.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemVideoLay.2
                @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Log.i("BmwId8SettingsSystemVideoLay", " position " + position);
                    for (LexusLsAppSelBean bean : listVideo) {
                        bean.setChecked(false);
                    }
                    ((LexusLsAppSelBean) listVideo.get(position)).setChecked(true);
                    adapterMusic.notifyDataSetChanged();
                    String pkg = ((LexusLsAppSelBean) listVideo.get(position)).getAppPkg();
                    String cls = ((LexusLsAppSelBean) listVideo.get(position)).getAppMainAty();
                    Settings.System.putString(BmwId8SettingsSystemVideoLay.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_PKG, pkg);
                    Settings.System.putString(BmwId8SettingsSystemVideoLay.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_CLS, cls);
                    if (cls.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                        LauncherViewModel.setThirdVideo(false);
                    } else {
                        LauncherViewModel.setThirdVideo(true);
                    }
                }
            });
            this.mBinding.bmwId8SettingsVideoRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemVideoLay.3
                @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == 0 && BmwId8SettingsSystemVideoLay.this.mBinding.bmwId8SettingsVideoRecycle.hasFocus()) {
                        int first = BmwId8SettingsSystemVideoLay.this.mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
                        int focusIndex = BmwId8SettingsSystemVideoLay.this.mBinding.bmwId8SettingsVideoRecycle.getChildAdapterPosition(BmwId8SettingsSystemVideoLay.this.mBinding.bmwId8SettingsVideoRecycle.getFocusedChild());
                        Log.e("BmwId8SettingsSystemVideoLay", "onScrollStateChanged: focusIndex " + focusIndex + " first " + first);
                        if (focusIndex < 0 && first > -1) {
                            BmwId8SettingsSystemVideoLay.this.mLinearLayoutManager.findViewByPosition(first).requestFocus();
                        }
                    }
                }

                @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });
            this.mBinding.getRoot().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.wits.ksw.settings.bmw_id8.layout.BmwId8SettingsSystemVideoLay.4
                @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
                public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                    Log.i("BmwId8SettingsSystemVideoLay", "onGlobalFocusChanged: " + BmwId8SettingsSystemVideoLay.this.mBinding.bmwId8SettingsVideoRecycle.hasFocus());
                    if (BmwId8SettingsSystemVideoLay.this.mBinding.bmwId8SettingsVideoRecycle.hasFocus()) {
                        BmwId8SettingsSystemVideoLay.this.mViewModel.systemBgShow.set(false);
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
