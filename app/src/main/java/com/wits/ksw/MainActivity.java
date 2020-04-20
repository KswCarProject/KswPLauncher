package com.wits.ksw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.MutableLiveData;
import androidx.viewpager.widget.ViewPager;

import butterknife.ButterKnife;
import com.wits.ksw.launcher.adpater.BMWViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BenzGsHomePagerAdpater;
import com.wits.ksw.launcher.adpater.BenzMbuxRecyclerViewAdpater;
import com.wits.ksw.launcher.adpater.BenzNTG6RecyclerViewAdpater;
import com.wits.ksw.launcher.adpater.BmwId5ViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BmwId6ViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BmwId6gSHomePagerAdpater;
import com.wits.ksw.launcher.adpater.UgHomePagerAdpater;
import com.wits.ksw.launcher.base.BaseThemeActivity;
import com.wits.ksw.launcher.id7_new.Id7NewActivity;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.KeyUtils;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;
import com.wits.ksw.launcher.view.ug.UgPager;
import com.wits.ksw.launcher.view.ug.UgViewPager;
import com.wits.ksw.launcher.view.ug.WiewFocusUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.List;

public class MainActivity extends BaseThemeActivity {
    /* access modifiers changed from: private */
    public static final String TAG = ("KSWLauncher." + MainActivity.class.getSimpleName());
    public static MainActivity mainActivity;
    public ActivityMainBcBinding bcMainActivity;
    public com.wits.ksw.databinding.MainActivity bmwBinding;
    private BmwId6ViewPagerAdpater bmwId6ViewPagerAdpater;
    public ID5MaindBind id5MaindBind;
    @InjectView(2131231054)
    ImageView id6LeftBtn;
    @InjectView(2131231055)
    public ViewPager id6MainViewPager;
    @InjectView(2131231067)
    ImageView id6RightBtn;
    public BcVieModel mBcVieModel;
    public MutableLiveData<UgPager> select = new MutableLiveData<>();
    public ActivityMainGsugBindingImpl ugBinding;
    private LauncherViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        mainActivity = this;
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Main onCreate: ");
        startService(new Intent(this, KswRunService.class));
    }

    /* access modifiers changed from: protected */
    public void initBmwid5UiView() {
        Log.i(TAG, "initBmwid5UiView: ");
        this.id5MaindBind = (ID5MaindBind) DataBindingUtil.setContentView(this, R.layout.activity_main_id5);
        this.id5MaindBind.id5MainViewPager.setAdapter(new BmwId5ViewPagerAdpater(getSupportFragmentManager()));
        this.id5MaindBind.id5MainViewPager.setCurrentItem(0);
        KswUtils.setFull(getWindow());
    }

    /* access modifiers changed from: protected */
    public void initBmwid6UiView() {
        Log.i(TAG, "initBmwid6UiView: ");
        setContentView((int) R.layout.activity_main_id6);
        ButterKnife.inject((Activity) this);
        this.viewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel.setActivity(this);
        this.viewModel.initData();
        this.bmwId6ViewPagerAdpater = new BmwId6ViewPagerAdpater(getSupportFragmentManager());
        this.id6MainViewPager.setAdapter(this.bmwId6ViewPagerAdpater);
        this.id6MainViewPager.setCurrentItem(0);
        this.id6MainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int arg, float v, int i1) {
            }

            public void onPageSelected(int i) {
                if (i == 0) {
                    MainActivity.this.id6LeftBtn.setVisibility(4);
                    MainActivity.this.id6RightBtn.setVisibility(0);
                } else if (i == 3) {
                    MainActivity.this.id6LeftBtn.setVisibility(0);
                    MainActivity.this.id6RightBtn.setVisibility(4);
                } else {
                    MainActivity.this.id6LeftBtn.setVisibility(0);
                    MainActivity.this.id6RightBtn.setVisibility(0);
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        this.id6LeftBtn.setVisibility(4);
        this.id6RightBtn.setVisibility(0);
        this.id6LeftBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MainActivity.lambda$initBmwid6UiView$0(MainActivity.this, view);
            }
        });
        this.id6RightBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MainActivity.lambda$initBmwid6UiView$1(MainActivity.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initBmwid6UiView$0(MainActivity mainActivity2, View v) {
        int index = mainActivity2.id6MainViewPager.getCurrentItem() - 1;
        if (index >= 0) {
            mainActivity2.id6MainViewPager.setCurrentItem(index);
            if (index == 0) {
                mainActivity2.bmwId6ViewPagerAdpater.fragmentID6One.setDefaultSelected();
            } else if (index == 1) {
                mainActivity2.bmwId6ViewPagerAdpater.fragmentID6Two.setDefaultSelected();
            } else if (index == 2) {
                mainActivity2.bmwId6ViewPagerAdpater.fragmentID6Three.setDefaultSelected();
            } else if (index == 3) {
                mainActivity2.bmwId6ViewPagerAdpater.fragmentID6Four.setDefaultSelected();
            }
        }
    }

    public static /* synthetic */ void lambda$initBmwid6UiView$1(MainActivity mainActivity2, View v) {
        int index = mainActivity2.id6MainViewPager.getCurrentItem() + 1;
        if (index <= 3) {
            mainActivity2.id6MainViewPager.setCurrentItem(index);
            if (index == 0) {
                mainActivity2.bmwId6ViewPagerAdpater.fragmentID6One.setDefaultSelected();
            } else if (index == 1) {
                mainActivity2.bmwId6ViewPagerAdpater.fragmentID6Two.setDefaultSelected();
            } else if (index == 2) {
                mainActivity2.bmwId6ViewPagerAdpater.fragmentID6Three.setDefaultSelected();
            } else if (index == 3) {
                mainActivity2.bmwId6ViewPagerAdpater.fragmentID6Four.setDefaultSelected();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initBmwEvoId6GS() {
        ActivityMainId6GsBinding binding = (ActivityMainId6GsBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_id6_gs);
        BmwId6gsViewMode viewMode = (BmwId6gsViewMode) ViewModelProviders.of((FragmentActivity) this).get(BmwId6gsViewMode.class);
        viewMode.setActivity(this);
        viewMode.initData();
        binding.setVm(viewMode);
        binding.id6GsMainViewPager.setViewMode(viewMode);
        binding.id6GsMainViewPager.setAdapter(new BmwId6gSHomePagerAdpater(getSupportFragmentManager()));
        binding.id6GsMainViewPager.setOffscreenPageLimit(3);
    }

    public void initBmwid7UiView() {
        Log.i(TAG, "initBmwid7UiView: ");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel.setActivity(this);
        this.bmwBinding = (com.wits.ksw.databinding.MainActivity) DataBindingUtil.setContentView(this, R.layout.activity_main_bmw);
        this.bmwBinding.viewPage.setAdapter(new BMWViewPagerAdpater(getSupportFragmentManager()));
        setCurrentItem(0);
        this.bmwBinding.viewPage.setOffscreenPageLimit(3);
        this.bmwBinding.imageView1.setSelected(false);
        this.bmwBinding.imageView3.setSelected(true);
        this.bmwBinding.imageView4.setSelected(false);
        this.bmwBinding.setLauncherViewModel(this.viewModel);
        this.viewModel.initData();
        addOnPageChangeListener();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi", "WrongConstant"})
    @RequiresApi(api = 23)
    public void initBcUiView() {
        Log.i(TAG, "initBcUiView: ");
        setFull();
        this.mBcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        this.mBcVieModel.setActivity(this);
        this.bcMainActivity = (ActivityMainBcBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_bc);
        this.bcMainActivity.recyclerView2.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.bcMainActivity.recyclerView2.setAdapter(new BenzNTG6RecyclerViewAdpater(this.mBcVieModel));
        new LinearSnapHelper() {
            public boolean onFling(int velocityX, int velocityY) {
                return false;
            }
        }.attachToRecyclerView(this.bcMainActivity.recyclerView2);
        this.bcMainActivity.setMBcVieModel(this.mBcVieModel);
        this.mBcVieModel.initData();
        this.bcMainActivity.appsBtn.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String access$000 = MainActivity.TAG;
                Log.e(access$000, "onKey: " + keyCode + " action: " + event.getAction());
                if (keyCode != 20) {
                    return false;
                }
                KeyUtils.pressKey(19);
                return false;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initBenzGSView() {
        setFull();
        BenzGsViewMoel mBenzGsViewMoel = (BenzGsViewMoel) ViewModelProviders.of((FragmentActivity) this).get(BenzGsViewMoel.class);
        mBenzGsViewMoel.setActivity(this);
        mBenzGsViewMoel.initData();
        ActivityMainBenzGsBinding benzGsbinding = (ActivityMainBenzGsBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_benz_gs);
        benzGsbinding.benzgsViewpage.setAdapter(new BenzGsHomePagerAdpater(getSupportFragmentManager()));
        benzGsbinding.benzgsViewpage.setCurrentItem(0);
        benzGsbinding.benzgsViewpage.setOffscreenPageLimit(2);
        benzGsbinding.benzgsViewpage.setViewMoel(mBenzGsViewMoel);
        benzGsbinding.setVm(mBenzGsViewMoel);
    }

    /* access modifiers changed from: protected */
    public void initGSUiView() {
        Log.i(TAG, "initGSUiView: ");
        initSaveData();
        this.viewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel.setActivity(this);
        this.viewModel.initData();
        startActivity(new Intent(this, Id7NewActivity.class));
        finish();
    }

    /* access modifiers changed from: protected */
    public void initCommonUIGSUGView() {
        Log.i(TAG, "initCommonUIGSUGView: ");
        this.ugBinding = (ActivityMainGsugBindingImpl) DataBindingUtil.setContentView(this, R.layout.activity_main_gsug);
        this.ugBinding.ugViewPage.setAdapter(new UgHomePagerAdpater(getSupportFragmentManager()));
        this.viewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel.setActivity(this);
        this.viewModel.initData();
        this.ugBinding.setViewModel(this.viewModel);
        this.ugBinding.ugViewPage.setUgPageChangeListener(new UgViewPager.UgPageChangeListener() {
            public void onPageSelected(int i, boolean left, boolean right) {
                String access$000 = MainActivity.TAG;
                Log.i(access$000, "onPageSelected: i:" + i + " left:" + left + " right:" + right);
                MainActivity.this.select.setValue(new UgPager(i, left, right));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initBenzMBUXView() {
        Log.i(TAG, "initBenzMBUXView: ");
        setFull();
        this.mBcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        this.mBcVieModel.setActivity(this);
        this.mBcVieModel.initData();
        ActivityMainBenzMbuxBindingImpl benzMbuxBinding = (ActivityMainBenzMbuxBindingImpl) DataBindingUtil.setContentView(this, R.layout.activity_main_benz_mbux);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 0, false);
        benzMbuxBinding.benzMbuxRecyclerView.setFocusable(false);
        benzMbuxBinding.benzMbuxRecyclerView.setLayoutManager(linearLayoutManager);
        benzMbuxBinding.benzMbuxRecyclerView.setAdapter(new BenzMbuxRecyclerViewAdpater(this.mBcVieModel));
        new LinearSnapHelper() {
            public boolean onFling(int velocityX, int velocityY) {
                return false;
            }
        }.attachToRecyclerView(benzMbuxBinding.benzMbuxRecyclerView);
        benzMbuxBinding.setVieModel(this.mBcVieModel);
    }

    /* access modifiers changed from: protected */
    public void initAudiView() {
        Log.i(TAG, "initAudiView: ");
        setFull();
        setStatusBarTranslucent();
        AudiViewModel audiViewModel = (AudiViewModel) ViewModelProviders.of((FragmentActivity) this).get(AudiViewModel.class);
        audiViewModel.setActivity(this);
        audiViewModel.initData();
        ActivityMainAudiBinding audiBinding = (ActivityMainAudiBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_audi);
        audiBinding.MLoopRotarySwitchView.setMultiple(6.0f).setR(((float) getWindowManager().getDefaultDisplay().getWidth()) / 3.2f).setLoopRotationX(-22).setLoopRotationZ(-1);
        audiBinding.setVm(audiViewModel);
    }

    private void addOnPageChangeListener() {
        this.bmwBinding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        MainActivity.this.bmwBinding.imageView1.setSelected(false);
                        MainActivity.this.bmwBinding.imageView3.setSelected(true);
                        MainActivity.this.bmwBinding.imageView4.setSelected(false);
                        return;
                    case 1:
                        MainActivity.this.bmwBinding.imageView1.setSelected(true);
                        MainActivity.this.bmwBinding.imageView3.setSelected(false);
                        MainActivity.this.bmwBinding.imageView4.setSelected(false);
                        return;
                    case 2:
                        MainActivity.this.bmwBinding.imageView1.setSelected(false);
                        MainActivity.this.bmwBinding.imageView3.setSelected(false);
                        MainActivity.this.bmwBinding.imageView4.setSelected(true);
                        return;
                    default:
                        return;
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void initSaveData() {
        try {
            List<String> naviList = PowerManagerApp.getDataListFromJsonKey(KeyConfig.NAVI_LIST);
            String navidefual = PowerManagerApp.getSettingsString(KeyConfig.NAVI_DEFUAL);
            if (naviList != null && naviList.size() > 0) {
                ScanNaviList.getInstance().scanList(naviList, navidefual, this);
                Log.d("Navi", "==size==:" + ScanNaviList.getInstance().getMapList().size());
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void setCurrentItem(int index) {
        if (this.bmwBinding != null) {
            this.bmwBinding.viewPage.setCurrentItem(index);
        }
    }

    public void refreshLastViewFocused() {
        if (this.viewModel != null) {
            this.viewModel.addLastViewFocused(this.bmwBinding.menuButton1);
            this.viewModel.refreshLastViewFocused();
        }
    }

    public void showLastViewFocus() {
        int resId = KswUtils.getLastViewId(this);
        String str = TAG;
        Log.i(str, "showLastViewFocus: resId=" + resId);
        WiewFocusUtils.setViewRequestFocus(getWindow().getDecorView().findViewById(resId));
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (UiThemeUtils.isCommon_UI_GS_UG(this)) {
            showLastViewFocus();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        initSaveData();
        if (UiThemeUtils.isCommon_UI_GS_UG(this)) {
            showLastViewFocus();
        } else if (this.viewModel != null) {
            this.viewModel.resumeViewModel();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (UiThemeUtils.isBenz_NTG6(this) && keyCode == 311) {
            String str = TAG;
            Log.i(str, "Benz_NTG6 onKeyDown: setNextFocusDownId appsBtn " + keyCode);
            try {
                this.bcMainActivity.appsBtn.setFocusable(true);
                this.bcMainActivity.appsBtn.setFocusableInTouchMode(true);
                this.bcMainActivity.appsBtn.requestFocus();
                this.bcMainActivity.appsBtn.setFocusableInTouchMode(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onBackPressed() {
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy:");
        this.viewModel = null;
    }
}
