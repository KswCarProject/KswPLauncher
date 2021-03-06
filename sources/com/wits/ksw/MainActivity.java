package com.wits.ksw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.wits.ksw.databinding.ActivityBmwNbtBinding;
import com.wits.ksw.databinding.ActivityMainAlsId6Binding;
import com.wits.ksw.databinding.ActivityMainAlsId7Binding;
import com.wits.ksw.databinding.ActivityMainAudiBinding;
import com.wits.ksw.databinding.ActivityMainBcBinding;
import com.wits.ksw.databinding.ActivityMainBenzGsBinding;
import com.wits.ksw.databinding.ActivityMainBenzMbuxBindingImpl;
import com.wits.ksw.databinding.ActivityMainBenzNtg5Binding;
import com.wits.ksw.databinding.ActivityMainGsug2BindingImpl;
import com.wits.ksw.databinding.ActivityMainGsugBindingImpl;
import com.wits.ksw.databinding.ActivityMainId6GsBinding;
import com.wits.ksw.databinding.ActivityMainLexusBinding;
import com.wits.ksw.databinding.ActivityRomeoBinding;
import com.wits.ksw.databinding.ID5MaindBind;
import com.wits.ksw.databinding.LandroverMainBinding;
import com.wits.ksw.launcher.adpater.BMWViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BenzGsHomePagerAdpater;
import com.wits.ksw.launcher.adpater.BenzMbuxRecyclerViewAdpater;
import com.wits.ksw.launcher.adpater.BenzNTG5RecyclerViewAdapter;
import com.wits.ksw.launcher.adpater.BenzNTG6RecyclerViewAdpater;
import com.wits.ksw.launcher.adpater.BmwId5ViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BmwId6ViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BmwId6gSHomePagerAdpater;
import com.wits.ksw.launcher.adpater.RomeoMainListAdapter;
import com.wits.ksw.launcher.adpater.UgHomePagerAdpater;
import com.wits.ksw.launcher.als_id7.adapter.BMWId7ViewPagerAdpater;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.base.BaseThemeActivity;
import com.wits.ksw.launcher.id7_new.Id7NewActivity;
import com.wits.ksw.launcher.land_rover.adapter.LandRoverViewPagerAdpater;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.model.BcNTG5ViewModel;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.BwmNbtModel;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.model.RomeoViewModel;
import com.wits.ksw.launcher.utils.FixLinearSnapHelper;
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
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseThemeActivity {
    /* access modifiers changed from: private */
    public static final String TAG = ("KSWLauncher." + MainActivity.class.getSimpleName());
    public static MainActivity mainActivity;
    private ActivityMainAlsId6Binding alsBinding;
    private AlsID7ViewModel alsID7ViewModel;
    /* access modifiers changed from: private */
    public ActivityMainAlsId7Binding alsId7Binding;
    public ActivityMainBcBinding bcMainActivity;
    public com.wits.ksw.databinding.MainActivity bmwBinding;
    private BmwId6ViewPagerAdpater bmwId6ViewPagerAdpater;
    public ActivityMainGsug2BindingImpl gsug2Binding;
    public ID5MaindBind id5MaindBind;
    @InjectView(2131231100)
    ImageView id6LeftBtn;
    @InjectView(2131231101)
    public ViewPager id6MainViewPager;
    @InjectView(2131231113)
    ImageView id6RightBtn;
    /* access modifiers changed from: private */
    public LandroverMainBinding landroverBinding;
    private ActivityMainLexusBinding lexusBinding;
    public BcNTG5ViewModel mBcNTG5ViewModel;
    public BcVieModel mBcVieModel;
    private ActivityBmwNbtBinding nbtBinging;
    private BwmNbtModel nbtModel;
    public ActivityMainBenzNtg5Binding ntg5Binding;
    /* access modifiers changed from: private */
    public ActivityRomeoBinding romeoBinding;
    private RomeoViewModel romeoViewModel;
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
    public void initBenzNTG5View() {
        setFull();
        this.mBcNTG5ViewModel = (BcNTG5ViewModel) ViewModelProviders.of((FragmentActivity) this).get(BcNTG5ViewModel.class);
        this.mBcNTG5ViewModel.setActivity(this);
        this.ntg5Binding = (ActivityMainBenzNtg5Binding) DataBindingUtil.setContentView(this, R.layout.activity_main_benz_ntg5);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 0, false);
        this.ntg5Binding.recyclerView2.setItemViewCacheSize(3);
        this.ntg5Binding.recyclerView2.setLayoutManager(linearLayoutManager);
        this.ntg5Binding.recyclerView2.setAdapter(new BenzNTG5RecyclerViewAdapter(this.mBcNTG5ViewModel));
        new LinearSnapHelper() {
            public boolean onFling(int velocityX, int velocityY) {
                return false;
            }
        }.attachToRecyclerView(this.ntg5Binding.recyclerView2);
        this.ntg5Binding.setMBcVieModel(this.mBcNTG5ViewModel);
        this.mBcNTG5ViewModel.initData();
    }

    /* access modifiers changed from: protected */
    public void initAlsView() {
        setTheme(R.style.ids6_style);
        this.viewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel.setActivity(this);
        this.alsBinding = (ActivityMainAlsId6Binding) DataBindingUtil.setContentView(this, R.layout.activity_main_als_id6);
        this.alsBinding.setViewModel(this.viewModel);
        this.viewModel.initData();
    }

    /* access modifiers changed from: protected */
    public void initBwmNbt() {
        this.nbtModel = (BwmNbtModel) ViewModelProviders.of((FragmentActivity) this).get(BwmNbtModel.class);
        this.nbtModel.setActivity(this);
        this.nbtBinging = (ActivityBmwNbtBinding) DataBindingUtil.setContentView(this, R.layout.activity_bmw_nbt);
        this.nbtModel.setMainnbtBinging(this.nbtBinging);
        this.nbtBinging.setNbtModel(this.nbtModel);
        this.nbtModel.initData();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        String str = TAG;
        Log.i(str, "onKeyUp: action=" + event.getAction() + " keyCode =" + keyCode);
        if (UiThemeUtils.isLEXUS_UI(this) && (keyCode == 19 || keyCode == 20 || keyCode == 21 || keyCode == 22)) {
            List<View> viewGroupChildViews = getViewGroupChildViews(this.lexusBinding.lexusMainSv);
            int foucusIndex = getFoucusIndex(viewGroupChildViews);
            String str2 = TAG;
            Log.i(str2, "onKeyUp: viewGroupChildViews=" + viewGroupChildViews.size() + " foucusIndex// =" + foucusIndex);
            if (foucusIndex == -1) {
                this.lexusBinding.lexusMainSv.scrollTo(0, 0);
                View lastViewFocused = viewGroupChildViews.get(0);
                lastViewFocused.setFocusableInTouchMode(true);
                lastViewFocused.requestFocus();
                lastViewFocused.setFocusableInTouchMode(false);
            }
            if (foucusIndex == 5 || foucusIndex == 10) {
                this.lexusBinding.lexusMainSv.scrollTo(1920, 0);
            }
            if (foucusIndex == 0 || foucusIndex == 6) {
                this.lexusBinding.lexusMainSv.scrollTo(0, 0);
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    /* access modifiers changed from: protected */
    public void initLexus() {
        Log.i(TAG, " initLexus: ");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel.setActivity(this);
        this.lexusBinding = (ActivityMainLexusBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_lexus);
        this.lexusBinding.setViewModel(this.viewModel);
        this.viewModel.initData();
        if (Settings.System.getInt(getContentResolver(), KeyConfig.AC_CONTROL, 0) == 1) {
            this.viewModel.acControl.set(true);
        } else {
            this.viewModel.acControl.set(false);
        }
    }

    private int getFoucusIndex(List<View> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isFocused()) {
                return i;
            }
        }
        return -1;
    }

    private List<View> getViewGroupChildViews(ViewGroup vp) {
        List<View> allchildren = new ArrayList<>();
        for (int i = 0; i < vp.getChildCount(); i++) {
            View viewchild = vp.getChildAt(i);
            if (viewchild instanceof ViewGroup) {
                allchildren.addAll(getViewGroupChildViews((ViewGroup) viewchild));
            } else {
                allchildren.add(viewchild);
            }
        }
        String str = TAG;
        Log.d(str, "getViewGroupChildViews allchildren size=" + allchildren.size());
        return allchildren;
    }

    /* access modifiers changed from: protected */
    public void initBwmID7Hicar() {
        Log.i(TAG, "initBwmID7Hicar: ");
        initBmwid7UiView();
    }

    /* access modifiers changed from: protected */
    public void initRomeo() {
        setFull();
        this.romeoViewModel = (RomeoViewModel) ViewModelProviders.of((FragmentActivity) this).get(RomeoViewModel.class);
        this.romeoViewModel.setActivity(this);
        this.romeoBinding = (ActivityRomeoBinding) DataBindingUtil.setContentView(this, R.layout.activity_romeo);
        this.romeoBinding.setViewModel(this.romeoViewModel);
        this.romeoViewModel.initData();
        this.romeoViewModel.setRomeoBinding(this.romeoBinding);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(1);
        this.romeoBinding.romeoMainRv.setLayoutManager(layoutManager);
        this.romeoBinding.romeoMainRv.setItemViewCacheSize(0);
        RomeoMainListAdapter rvAdapter = new RomeoMainListAdapter(this, this.romeoViewModel);
        rvAdapter.setLayoutManager(layoutManager);
        rvAdapter.setBinding(this.romeoBinding);
        new FixLinearSnapHelper().attachToRecyclerView(this.romeoBinding.romeoMainRv);
        this.romeoBinding.romeoMainRv.setAdapter(rvAdapter);
        this.romeoBinding.pageIndicator1.getDrawable().setLevel(1);
        this.romeoBinding.pageIndicator2.getDrawable().setLevel(0);
        this.romeoBinding.romeoMainRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                String access$000 = MainActivity.TAG;
                Log.d(access$000, "recyclerView onScrollStateChanged newState=" + newState);
                if (newState == 0) {
                    int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    String access$0002 = MainActivity.TAG;
                    Log.d(access$0002, "recyclerView onScrollStateChanged firstVisibleItemPosition=" + firstVisibleItemPosition);
                    if (firstVisibleItemPosition != 0) {
                        MainActivity.this.romeoBinding.pageIndicator1.getDrawable().setLevel(0);
                        MainActivity.this.romeoBinding.pageIndicator2.getDrawable().setLevel(1);
                        return;
                    }
                    MainActivity.this.romeoBinding.pageIndicator1.getDrawable().setLevel(1);
                    MainActivity.this.romeoBinding.pageIndicator2.getDrawable().setLevel(0);
                }
            }

            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                String access$000 = MainActivity.TAG;
                Log.d(access$000, "recyclerView onScrolled dx=" + dx + " dy=" + dy);
                MainActivity.this.changeDistance(recyclerView);
            }
        });
    }

    /* access modifiers changed from: private */
    public void changeDistance(@NonNull RecyclerView recyclerView) {
        String str = TAG;
        Log.d(str, "calculateTranslate count=" + recyclerView.getChildCount());
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            recyclerView.getChildAt(i).setPadding(KswUtils.calculateTranslate(recyclerView.getChildAt(i).getTop(), KswUtils.dip2px(this, 428.0f), i, this), 0, 0, 0);
        }
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
    public void initCommonUIGSUG1024View() {
        Log.i(TAG, "initCommonUIGSUG1024View: ");
        this.gsug2Binding = (ActivityMainGsug2BindingImpl) DataBindingUtil.setContentView(this, R.layout.activity_main_gsug2);
        this.gsug2Binding.ugViewPage.setAdapter(new UgHomePagerAdpater(getSupportFragmentManager()));
        this.viewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel.setActivity(this);
        this.viewModel.initData();
        this.gsug2Binding.setViewModel(this.viewModel);
        this.gsug2Binding.ugViewPage.setUgPageChangeListener(new UgViewPager.UgPageChangeListener() {
            public void onPageSelected(int i, boolean left, boolean right) {
                String access$000 = MainActivity.TAG;
                Log.i(access$000, "onPageSelected: i:" + i + " left:" + left + " right:" + right);
                MainActivity.this.select.setValue(new UgPager(i, left, right));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initAlsId7UI() {
        Log.i(TAG, "chen-new ui initAlsId7UI: ");
        this.alsID7ViewModel = (AlsID7ViewModel) ViewModelProviders.of((FragmentActivity) this).get(AlsID7ViewModel.class);
        this.alsID7ViewModel.setActivity(this);
        this.alsId7Binding = (ActivityMainAlsId7Binding) DataBindingUtil.setContentView(this, R.layout.activity_main_als_id7);
        this.alsId7Binding.viewPage.setAdapter(new BMWId7ViewPagerAdpater(getSupportFragmentManager()));
        setCurrentItem(0);
        this.alsId7Binding.viewPage.setOffscreenPageLimit(3);
        this.alsId7Binding.imageView1.setSelected(false);
        this.alsId7Binding.imageView3.setSelected(true);
        this.alsId7Binding.imageView4.setSelected(false);
        this.alsId7Binding.setLauncherViewModel(this.alsID7ViewModel);
        this.alsID7ViewModel.initData();
        this.alsId7Binding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        MainActivity.this.alsId7Binding.imageView1.setSelected(false);
                        MainActivity.this.alsId7Binding.imageView3.setSelected(true);
                        MainActivity.this.alsId7Binding.imageView4.setSelected(false);
                        return;
                    case 1:
                        MainActivity.this.alsId7Binding.imageView1.setSelected(true);
                        MainActivity.this.alsId7Binding.imageView3.setSelected(false);
                        MainActivity.this.alsId7Binding.imageView4.setSelected(false);
                        return;
                    case 2:
                        MainActivity.this.alsId7Binding.imageView1.setSelected(false);
                        MainActivity.this.alsId7Binding.imageView3.setSelected(false);
                        MainActivity.this.alsId7Binding.imageView4.setSelected(true);
                        return;
                    default:
                        return;
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initLandRover() {
        this.viewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel.setActivity(this);
        this.landroverBinding = (LandroverMainBinding) DataBindingUtil.setContentView(this, R.layout.landrover_main);
        this.landroverBinding.viewPager.setAdapter(new LandRoverViewPagerAdpater(getSupportFragmentManager()));
        setCurrentItem(0);
        this.landroverBinding.viewPager.setOffscreenPageLimit(2);
        this.landroverBinding.indicato1.setSelected(true);
        this.landroverBinding.indicato2.setSelected(false);
        this.landroverBinding.setLauncherViewModel(this.viewModel);
        this.viewModel.initData();
        this.landroverBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        MainActivity.this.landroverBinding.indicato2.setSelected(false);
                        MainActivity.this.landroverBinding.indicato1.setSelected(true);
                        return;
                    case 1:
                        MainActivity.this.landroverBinding.indicato2.setSelected(true);
                        MainActivity.this.landroverBinding.indicato1.setSelected(false);
                        return;
                    default:
                        return;
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        this.landroverBinding.iconLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.landroverBinding.viewPager.setCurrentItem(0);
            }
        });
        this.landroverBinding.iconRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.landroverBinding.viewPager.setCurrentItem(1);
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
        audiBinding.MLoopRotarySwitchView.setMultiple(6.0f).setR(((float) getWindowManager().getDefaultDisplay().getWidth()) / 3.2f).setLoopRotationX(-22).setLoopRotationZ(1);
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
        if (this.alsId7Binding != null) {
            this.alsId7Binding.viewPage.setCurrentItem(index);
        }
        if (this.landroverBinding != null) {
            this.landroverBinding.viewPager.setCurrentItem(index);
        }
    }

    public void refreshLastViewFocused() {
        if (this.viewModel != null) {
            this.viewModel.addLastViewFocused(this.bmwBinding.menuButton1);
            this.viewModel.refreshLastViewFocused();
        }
        if (this.alsID7ViewModel != null) {
            this.alsID7ViewModel.addLastViewFocused(this.alsId7Binding.menuButton1);
            this.alsID7ViewModel.refreshLastViewFocused();
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
        } else if (UiThemeUtils.isBMW_NBT(this)) {
            this.nbtModel.refrshInit();
        } else if (this.viewModel != null) {
            this.viewModel.resumeViewModel();
        }
        if (this.alsID7ViewModel != null) {
            this.alsID7ViewModel.resumeViewModel();
            String str = TAG;
            Log.d(str, "oresume ismusicplay" + this.alsID7ViewModel.isMusicPlay());
            this.alsID7ViewModel.setMusicPlayState(this.alsID7ViewModel.isMusicPlay());
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (UiThemeUtils.isLEXUS_UI(this)) {
            if (keyCode == 20) {
                return super.dispatchKeyEvent(new KeyEvent(event.getAction(), 22));
            }
            if (keyCode == 19) {
                return super.dispatchKeyEvent(new KeyEvent(event.getAction(), 21));
            }
        }
        return super.dispatchKeyEvent(event);
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
        this.alsID7ViewModel = null;
    }
}
