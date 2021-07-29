package com.wits.ksw;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
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
import com.wits.ksw.databinding.AudiMib3MainLayoutBinding;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding2;
import com.wits.ksw.databinding.BenzNtg6FyBindCls;
import com.wits.ksw.databinding.ID5MaindBind;
import com.wits.ksw.databinding.LandroverMainBinding;
import com.wits.ksw.databinding.LexusLsDragMainLayoutBinding;
import com.wits.ksw.databinding.LexusLsMainLayoutBinding;
import com.wits.ksw.launcher.adpater.BMWViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BenzGsHomePagerAdpater;
import com.wits.ksw.launcher.adpater.BenzMbuxRecyclerViewAdpater;
import com.wits.ksw.launcher.adpater.BenzNTG5RecyclerViewAdapter;
import com.wits.ksw.launcher.adpater.BenzNTG6RecyclerViewAdpater;
import com.wits.ksw.launcher.adpater.BmwId5ViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BmwId6CuspViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BmwId6ViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BmwId6gSHomePagerAdpater;
import com.wits.ksw.launcher.adpater.RomeoMainListAdapter;
import com.wits.ksw.launcher.adpater.UgHomePagerAdpater;
import com.wits.ksw.launcher.als_id7.adapter.BMWId7ViewPagerAdpater;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.base.BaseThemeActivity;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.id7_new.Id7NewActivity;
import com.wits.ksw.launcher.land_rover.adapter.LandRoverViewPagerAdpater;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;
import com.wits.ksw.launcher.land_rover.view.LandroverPopWindow;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.model.BcNTG5ViewModel;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.BwmNbtModel;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.model.RomeoViewModel;
import com.wits.ksw.launcher.pagerlayoutmanager.PagerGridLayoutManager;
import com.wits.ksw.launcher.pagerlayoutmanager.PagerGridSnapHelper;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.launcher.utils.BitmapUtil;
import com.wits.ksw.launcher.utils.FixLinearSnapHelper;
import com.wits.ksw.launcher.utils.KeyUtils;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.launcher.view.audimib3.AudiMib3ViewPagerAdapter;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;
import com.wits.ksw.launcher.view.benzmbux2021.Benz2021DialogThemeActivity;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021Configs;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021RecyclerViewAdpater;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021ViewPagerAdapter;
import com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyConfigs;
import com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyThemeActivity;
import com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyViewPagerAdapter;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;
import com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwo;
import com.wits.ksw.launcher.view.lexusls.LexusLsConfig;
import com.wits.ksw.launcher.view.lexusls.adapter.LexusLsBottomViewPagerAdpater;
import com.wits.ksw.launcher.view.lexusls.adapter.LexusLsDragItemAdapter;
import com.wits.ksw.launcher.view.lexusls.adapter.LexusLsSelAppAdapter;
import com.wits.ksw.launcher.view.lexusls.adapter.LexusLsTopAppAdapter;
import com.wits.ksw.launcher.view.lexusls.drag.DeleteZone;
import com.wits.ksw.launcher.view.lexusls.drag.DragController;
import com.wits.ksw.launcher.view.lexusls.drag.DragLayer;
import com.wits.ksw.launcher.view.lexusls.drag.DragSource;
import com.wits.ksw.launcher.view.lexusls.drag.DraggableLayout;
import com.wits.ksw.launcher.view.lexusls.drag.HorizontalPageLayoutManager;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;
import com.wits.ksw.launcher.view.lexusls.drag.ScrollController;
import com.wits.ksw.launcher.view.ug.UgPager;
import com.wits.ksw.launcher.view.ug.UgViewPager;
import com.wits.ksw.launcher.view.ug.WiewFocusUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends BaseThemeActivity {
    private static final int MSG_HIDE_POP = 1;
    private static final int MSG_SHOW_POP = 0;
    /* access modifiers changed from: private */
    public static final String TAG = ("KSWLauncher." + MainActivity.class.getSimpleName());
    public static AudiMib3MainLayoutBinding audiMib3Binding;
    public static AudiMib3ViewPagerAdapter audiMib3ViewPagerAdapter;
    public static BenzMbux2021ActivityBinding2 benzMbux2021Binding2;
    public static BenzMbux2021ViewPagerAdapter benzMbux2021ViewPagerAdapter;
    public static BenzNtg6FyBindCls benzNtg6FyBinding;
    public static BenzNtg6FyViewPagerAdapter benzNtg6FyViewPagerAdapter;
    private static final int[] bgIconRes = {R.drawable.mbux2_coat_set_icon2_1, R.drawable.mbux2_coat_set_icon2_2, R.drawable.mbux2_coat_set_icon2_3, R.drawable.mbux2_coat_set_icon2_4, R.drawable.mbux2_coat_set_icon2_5, R.drawable.mbux2_coat_set_icon2_6, R.drawable.mbux2_coat_set_icon2_7};
    private static final int[] bgRes = {R.drawable.mbux2_bg_main_2_1, R.drawable.mbux2_bg_main_2_2, R.drawable.mbux2_bg_main_2_3, R.drawable.mbux2_bg_main_2_4, R.drawable.mbux2_bg_main_2_5, R.drawable.mbux2_bg_main_2_6, R.drawable.mbux2_bg_main_2_7};
    /* access modifiers changed from: private */
    public static boolean isFront = false;
    private static final int lineWidth = 0;
    private static ArrayList<LexusLsAppSelBean> mAllAppList = new ArrayList<>();
    public static BcVieModel mAudiMbi3BcViewModel;
    public static BcVieModel mBcVieModel;
    /* access modifiers changed from: private */
    public static ArrayList<LexusLsAppSelBean> mCheckedAppList = new ArrayList<>();
    public static int mColumn = 0;
    public static int mRow = 0;
    public static MainActivity mainActivity;
    /* access modifiers changed from: private */
    public LexusLsDragItemAdapter adapterLexusLsDrag;
    private ActivityMainAlsId6Binding alsBinding;
    private AlsID7ViewModel alsID7ViewModel;
    /* access modifiers changed from: private */
    public ActivityMainAlsId7Binding alsId7Binding;
    private AnimationDrawable animDrawApps;
    private AnimationDrawable animDrawBrowser;
    private AnimationDrawable animDrawBt;
    private AnimationDrawable animDrawCar;
    private AnimationDrawable animDrawDashboard;
    private AnimationDrawable animDrawDvr;
    private AnimationDrawable animDrawFile;
    private AnimationDrawable animDrawMusic;
    private AnimationDrawable animDrawNavi;
    private AnimationDrawable animDrawPhonelink;
    private AnimationDrawable animDrawSet;
    private AnimationDrawable animDrawVideo;
    public ActivityMainBcBinding bcMainActivity;
    /* access modifiers changed from: private */
    public BenzMbux2021ActivityBinding benzMbux2021Binding;
    private String bgFyIndex = "1";
    private String bgIndex = "1";
    public com.wits.ksw.databinding.MainActivity bmwBinding;
    private BmwId6CuspViewPagerAdpater bmwId6CuspViewPagerAdpater;
    private BmwId6ViewPagerAdpater bmwId6ViewPagerAdpater;
    public int dialogHeight;
    public int dialogWidth;
    public ActivityMainGsug2BindingImpl gsug2Binding;
    private HorizontalPageLayoutManager horizhontalPageLayoutManager;
    public ID5MaindBind id5MaindBind;
    public ViewPager id6CuspMainViewPager;
    ImageView id6LeftBtn;
    public ViewPager id6MainViewPager;
    ImageView id6RightBtn;
    private ImageView[] indicatorPoints;
    /* access modifiers changed from: private */
    public boolean isEnableDrag = true;
    private String isFirstLoad = "true";
    /* access modifiers changed from: private */
    public LandroverMainBinding landroverBinding;
    /* access modifiers changed from: private */
    public LandroverHandler landroverHandler = null;
    private LandroverViewModel landroverViewModel;
    private boolean lastLeft = false;
    private boolean lastRight = true;
    private ActivityMainLexusBinding lexusBinding;
    private LexusLsBottomViewPagerAdpater lexusLsBottomViewPagerAdpater;
    LexusLsDragMainLayoutBinding lexusLsDragBinding;
    /* access modifiers changed from: private */
    public LexusLsSelAppAdapter lexusLsSelAppAdapter;
    private LexusLsTopAppAdapter lexusLsTopAppAdapter;
    public ViewPager lexusLsVpagerBottom;
    private RecyclerView lexus_ls_add_app_recycleview;
    private TextView lexus_ls_addapp_iv_cancel;
    private TextView lexus_ls_addapp_iv_ok;
    private RecyclerView lexus_ls_recycleview_desktop;
    private View.OnClickListener mAudiMib3ItemClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_bt_audimib3:
                    MainActivity.this.setItemSelected(MainActivity.audiMib3Binding.include.ivBtAudimib3);
                    MainActivity.mAudiMbi3BcViewModel.openBtApp(v);
                    return;
                case R.id.iv_car_audimib3:
                    MainActivity.this.setItemSelected(MainActivity.audiMib3Binding.include.ivCarAudimib3);
                    MainActivity.mAudiMbi3BcViewModel.openCar(v);
                    return;
                case R.id.iv_music_audimib3:
                    MainActivity.this.setItemSelected(MainActivity.audiMib3Binding.include.ivMusicAudimib3);
                    MainActivity.mAudiMbi3BcViewModel.openMusic(v);
                    return;
                case R.id.iv_navi_audimib3:
                    MainActivity.this.setItemSelected(MainActivity.audiMib3Binding.include.ivNaviAudimib3);
                    MainActivity.mAudiMbi3BcViewModel.openNaviApp(v);
                    return;
                case R.id.iv_set_audimib3:
                    MainActivity.this.setItemSelected(MainActivity.audiMib3Binding.include.ivSetAudimib3);
                    MainActivity.mAudiMbi3BcViewModel.openSettings(v);
                    return;
                default:
                    return;
            }
        }
    };
    public BcNTG5ViewModel mBcNTG5ViewModel;
    /* access modifiers changed from: private */
    public DeleteZone mDeleteZone;
    private View.OnClickListener mDialogBtnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.lexus_ls_addapp_iv_cancel:
                    if (MainActivity.this.mPopupWindow != null) {
                        MainActivity.this.mPopupWindow.dismiss();
                        return;
                    }
                    return;
                case R.id.lexus_ls_addapp_iv_ok:
                    if (MainActivity.this.mPopupWindow != null) {
                        MainActivity.this.mPopupWindow.dismiss();
                        ArrayList unused = MainActivity.mCheckedAppList = (ArrayList) MainActivity.this.lexusLsSelAppAdapter.getCheckBoxIDList();
                        if (MainActivity.mCheckedAppList != null && MainActivity.mCheckedAppList.size() > 0) {
                            Log.e("liuhao", "mCheckedAppList size = " + MainActivity.mCheckedAppList.size());
                            for (int i = 0; i < MainActivity.mCheckedAppList.size(); i++) {
                                Log.e("liuhao", "mCheckedAppList size i = " + i + " appName = " + ((LexusLsAppSelBean) MainActivity.mCheckedAppList.get(i)).getAppName());
                            }
                            ArrayList<LexusLsAppSelBean> tmpList = new ArrayList<>();
                            tmpList.addAll(LexusLsConfig.mAppList);
                            List<LexusLsAppSelBean> shengyuList = LexusLsConfig.getRemoveListResult(MainActivity.mainActivity, tmpList, LexusLsConfig.getMenuAppList(MainActivity.mainActivity));
                            if (shengyuList != null && shengyuList.size() > 0) {
                                LOGE.E("removeList size = " + shengyuList.size());
                                LexusLsConfig.getRemoveListResult(MainActivity.mainActivity, LexusLsConfig.mAppList, shengyuList);
                            }
                            Iterator it = MainActivity.mCheckedAppList.iterator();
                            while (it.hasNext()) {
                                LexusLsAppSelBean bean = (LexusLsAppSelBean) it.next();
                                if (!LexusLsConfig.mAppList.contains(bean) && LexusLsConfig.mAppList.size() <= MainActivity.this.pageSize * 2) {
                                    LexusLsConfig.mAppList.add(bean);
                                }
                            }
                            MainActivity.this.updateHideAndShowAddMenu();
                            MainActivity.this.refreshItemList();
                            MainActivity.this.adapterLexusLsDrag.notifyDataSetChanged();
                            LexusLsConfig.saveDataOrder(MainActivity.mainActivity);
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public DragController mDragController;
    /* access modifiers changed from: private */
    public DragLayer mDragLayer;
    public PopupWindow mPopupThemeWindow;
    public PopupWindow mPopupWindow;
    public PopupWindow mPopupWindow_FY;
    /* access modifiers changed from: private */
    public ScrollController mScrollController = new ScrollController();
    private View.OnClickListener mThemeItemClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            LOGE.E("v.getId() = " + v.getId());
            switch (v.getId()) {
                case R.id.rb_benz_mbux_2021_coat_bg_1:
                    BenzMbux2021Configs.bg_sel = 1;
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 1);
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_2:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 2);
                    BenzMbux2021Configs.bg_sel = 2;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_3:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 3);
                    BenzMbux2021Configs.bg_sel = 3;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_4:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 4);
                    BenzMbux2021Configs.bg_sel = 4;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_5:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 5);
                    BenzMbux2021Configs.bg_sel = 5;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_6:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 6);
                    BenzMbux2021Configs.bg_sel = 6;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_7:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_SAVE, 7);
                    BenzMbux2021Configs.bg_sel = 7;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_icon_sel_classical:
                    MainActivity.this.tv_icon_sel_modern.setTextColor(MainActivity.this.getColor(R.color.color1));
                    MainActivity.this.tv_icon_sel_classical.setTextColor(MainActivity.this.getColor(R.color.rb_text_color));
                    MainActivity.this.rb_icon_sel_modern.setSelected(false);
                    MainActivity.this.rb_icon_sel_classical.setSelected(true);
                    MainActivity.mBcVieModel.itemIconClass.set(true);
                    return;
                case R.id.rb_icon_sel_modern:
                    MainActivity.this.tv_icon_sel_modern.setTextColor(MainActivity.this.getColor(R.color.rb_text_color));
                    MainActivity.this.tv_icon_sel_classical.setTextColor(MainActivity.this.getColor(R.color.color1));
                    MainActivity.this.rb_icon_sel_classical.setSelected(false);
                    MainActivity.this.rb_icon_sel_modern.setSelected(true);
                    MainActivity.mBcVieModel.itemIconClass.set(false);
                    return;
                default:
                    return;
            }
        }
    };
    private PagerGridLayoutManager mbux2021_LayoutManager;
    private ActivityBmwNbtBinding nbtBinging;
    private BwmNbtModel nbtModel;
    /* access modifiers changed from: private */
    public int needUpdateDataPageIndex = -1;
    public ActivityMainBenzNtg5Binding ntg5Binding;
    public int pageCount = 0;
    public int pageSize = 0;
    private IContentObserver.Stub popWidowContentObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                boolean show = PowerManagerApp.getManager().getStatusBoolean("pop_window_show");
                Log.i(MainActivity.TAG, "popWidowContentObserver topApp:" + topApp + "  show:" + show);
                if ((TextUtils.equals(topApp, "com.wits.ksw.media") || TextUtils.equals(topApp, "com.wits.ksw.bt")) && MainActivity.this.landroverHandler != null) {
                    MainActivity.this.landroverHandler.sendEmptyMessage(show ? 0 : 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    /* access modifiers changed from: private */
    public RadioButton rb_icon_sel_classical;
    /* access modifiers changed from: private */
    public RadioButton rb_icon_sel_modern;
    private RecyclerView recyclerview_drag;
    /* access modifiers changed from: private */
    public int replaceAppPageIndex = -1;
    /* access modifiers changed from: private */
    public ActivityRomeoBinding romeoBinding;
    private RomeoViewModel romeoViewModel;
    String screenFile = "";
    /* access modifiers changed from: private */
    public Handler screenHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 666:
                    MainActivity.this.screenFile = MainActivity.screenShotByShell(MainActivity.mainActivity);
                    Log.d(MainActivity.TAG, MainActivity.this.screenFile);
                    return;
                default:
                    return;
            }
        }
    };
    public MutableLiveData<UgPager> select = new MutableLiveData<>();
    IContentObserver shotScreenObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            String topApp = PowerManagerApp.getStatusString("topApp");
            LOGE.E("shotScreenObserver  *********** " + topApp);
            if (!BuildConfig.APPLICATION_ID.equals(topApp)) {
                MainActivity.this.screenHandler.sendEmptyMessageDelayed(666, 2000);
            } else {
                MainActivity.this.screenHandler.removeMessages(666);
            }
        }
    };
    private String styleIndex = "1";
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                boolean isVideoFull = PowerManagerApp.getManager().getStatusBoolean("video_full");
                boolean show = PowerManagerApp.getManager().getStatusBoolean("pop_window_show");
                Log.e(MainActivity.TAG, "111onChange: topApp=" + topApp + "; show =" + show);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID)) {
                    if (MainActivity.this.landroverHandler != null && MainActivity.isFront) {
                        MainActivity.this.landroverHandler.sendEmptyMessage(0);
                    }
                } else if ((!TextUtils.equals(topApp, "com.wits.ksw.media") || isVideoFull || !show) && (!TextUtils.equals(topApp, "com.wits.ksw.bt") || !show)) {
                    if (MainActivity.this.landroverHandler != null) {
                        MainActivity.this.landroverHandler.sendEmptyMessage(1);
                    }
                } else if (MainActivity.this.landroverHandler != null) {
                    MainActivity.this.landroverHandler.sendEmptyMessage(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private int totalPage = 3;
    /* access modifiers changed from: private */
    public TextView tv_icon_sel_classical;
    /* access modifiers changed from: private */
    public TextView tv_icon_sel_modern;
    public ActivityMainGsugBindingImpl ugBinding;
    private IContentObserver.Stub videoFullContentObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                boolean isVideoFull = PowerManagerApp.getManager().getStatusBoolean("video_full");
                Log.e(MainActivity.TAG, "videoFullContentObserver: topApp = " + topApp + "  isVideoFull = " + isVideoFull);
                if (!TextUtils.equals(topApp, "com.wits.ksw.media")) {
                    return;
                }
                if (isVideoFull) {
                    if (MainActivity.this.landroverHandler != null) {
                        MainActivity.this.landroverHandler.sendEmptyMessage(1);
                    }
                } else if (MainActivity.this.landroverHandler != null) {
                    MainActivity.this.landroverHandler.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    /* access modifiers changed from: private */
    public LauncherViewModel viewModel;
    public ViewPager viewPagerBenzMbux2021;
    public ViewPager viewpagerAudiMib3;
    public ViewPager viewpagerBenzNtg6Fy;

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
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        BmwId6ViewPagerAdpater bmwId6ViewPagerAdpater2 = new BmwId6ViewPagerAdpater(getSupportFragmentManager());
        this.bmwId6ViewPagerAdpater = bmwId6ViewPagerAdpater2;
        this.id6MainViewPager.setAdapter(bmwId6ViewPagerAdpater2);
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
                MainActivity.this.lambda$initBmwid6UiView$0$MainActivity(view);
            }
        });
        this.id6RightBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MainActivity.this.lambda$initBmwid6UiView$1$MainActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initBmwid6UiView$0$MainActivity(View v) {
        int index = this.id6MainViewPager.getCurrentItem() - 1;
        if (index >= 0) {
            this.id6MainViewPager.setCurrentItem(index);
            if (index == 0) {
                this.bmwId6ViewPagerAdpater.fragmentID6One.setDefaultSelected();
            } else if (index == 1) {
                this.bmwId6ViewPagerAdpater.fragmentID6Two.setDefaultSelected();
            } else if (index == 2) {
                this.bmwId6ViewPagerAdpater.fragmentID6Three.setDefaultSelected();
            } else if (index == 3) {
                this.bmwId6ViewPagerAdpater.fragmentID6Four.setDefaultSelected();
            }
        }
    }

    public /* synthetic */ void lambda$initBmwid6UiView$1$MainActivity(View v) {
        int index = this.id6MainViewPager.getCurrentItem() + 1;
        if (index <= 3) {
            this.id6MainViewPager.setCurrentItem(index);
            if (index == 0) {
                this.bmwId6ViewPagerAdpater.fragmentID6One.setDefaultSelected();
            } else if (index == 1) {
                this.bmwId6ViewPagerAdpater.fragmentID6Two.setDefaultSelected();
            } else if (index == 2) {
                this.bmwId6ViewPagerAdpater.fragmentID6Three.setDefaultSelected();
            } else if (index == 3) {
                this.bmwId6ViewPagerAdpater.fragmentID6Four.setDefaultSelected();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initBmwid6CuspUiView() {
        Log.i(TAG, "initBmwid6CuspUiView: ");
        setContentView((int) R.layout.activity_main_id6_cusp);
        this.id6CuspMainViewPager = (ViewPager) findViewById(R.id.id6_cusp_main_view_pager);
        final ImageView id6CuspLeftBtn = (ImageView) findViewById(R.id.id6_cusp_left_btn);
        final ImageView id6CuspRightBtn = (ImageView) findViewById(R.id.id6_cusp_right_btn);
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        BmwId6CuspViewPagerAdpater bmwId6CuspViewPagerAdpater2 = new BmwId6CuspViewPagerAdpater(getSupportFragmentManager());
        this.bmwId6CuspViewPagerAdpater = bmwId6CuspViewPagerAdpater2;
        this.id6CuspMainViewPager.setAdapter(bmwId6CuspViewPagerAdpater2);
        this.id6CuspMainViewPager.setCurrentItem(0);
        this.id6CuspMainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int arg, float v, int i1) {
            }

            public void onPageSelected(int i) {
                if (i == 0) {
                    id6CuspLeftBtn.setVisibility(4);
                    id6CuspRightBtn.setVisibility(0);
                } else if (i == 3) {
                    id6CuspLeftBtn.setVisibility(0);
                    id6CuspRightBtn.setVisibility(4);
                } else {
                    id6CuspLeftBtn.setVisibility(0);
                    id6CuspRightBtn.setVisibility(0);
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        id6CuspLeftBtn.setVisibility(4);
        id6CuspRightBtn.setVisibility(0);
        id6CuspLeftBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MainActivity.this.lambda$initBmwid6CuspUiView$2$MainActivity(view);
            }
        });
        id6CuspRightBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MainActivity.this.lambda$initBmwid6CuspUiView$3$MainActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initBmwid6CuspUiView$2$MainActivity(View v) {
        int index = this.id6CuspMainViewPager.getCurrentItem() - 1;
        if (index >= 0) {
            this.id6CuspMainViewPager.setCurrentItem(index);
            if (index == 0) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspOne.setDefaultSelected();
            } else if (index == 1) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspTwo.setDefaultSelected();
            } else if (index == 2) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspThree.setDefaultSelected();
            } else if (index == 3) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspFour.setDefaultSelected();
            }
        }
    }

    public /* synthetic */ void lambda$initBmwid6CuspUiView$3$MainActivity(View v) {
        int index = this.id6CuspMainViewPager.getCurrentItem() + 1;
        if (index <= 3) {
            this.id6CuspMainViewPager.setCurrentItem(index);
            if (index == 0) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspOne.setDefaultSelected();
            } else if (index == 1) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspTwo.setDefaultSelected();
            } else if (index == 2) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspThree.setDefaultSelected();
            } else if (index == 3) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspFour.setDefaultSelected();
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
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        com.wits.ksw.databinding.MainActivity mainActivity2 = (com.wits.ksw.databinding.MainActivity) DataBindingUtil.setContentView(this, R.layout.activity_main_bmw);
        this.bmwBinding = mainActivity2;
        mainActivity2.viewPage.setAdapter(new BMWViewPagerAdpater(getSupportFragmentManager()));
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
    public void initBcUiView() {
        Log.i(TAG, "initBcUiView: ");
        setFull();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        this.bcMainActivity = (ActivityMainBcBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_bc);
        this.bcMainActivity.recyclerView2.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.bcMainActivity.recyclerView2.setAdapter(new BenzNTG6RecyclerViewAdpater(mBcVieModel));
        new LinearSnapHelper() {
            public boolean onFling(int velocityX, int velocityY) {
                return false;
            }
        }.attachToRecyclerView(this.bcMainActivity.recyclerView2);
        this.bcMainActivity.setMBcVieModel(mBcVieModel);
        mBcVieModel.initData();
        this.bcMainActivity.appsBtn.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e(MainActivity.TAG, "onKey: " + keyCode + " action: " + event.getAction());
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
        BcNTG5ViewModel bcNTG5ViewModel = (BcNTG5ViewModel) ViewModelProviders.of((FragmentActivity) this).get(BcNTG5ViewModel.class);
        this.mBcNTG5ViewModel = bcNTG5ViewModel;
        bcNTG5ViewModel.setActivity(this);
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
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        ActivityMainAlsId6Binding activityMainAlsId6Binding = (ActivityMainAlsId6Binding) DataBindingUtil.setContentView(this, R.layout.activity_main_als_id6);
        this.alsBinding = activityMainAlsId6Binding;
        activityMainAlsId6Binding.setViewModel(this.viewModel);
        this.viewModel.initData();
    }

    /* access modifiers changed from: protected */
    public void initBwmNbt() {
        BwmNbtModel bwmNbtModel = (BwmNbtModel) ViewModelProviders.of((FragmentActivity) this).get(BwmNbtModel.class);
        this.nbtModel = bwmNbtModel;
        bwmNbtModel.setActivity(this);
        ActivityBmwNbtBinding activityBmwNbtBinding = (ActivityBmwNbtBinding) DataBindingUtil.setContentView(this, R.layout.activity_bmw_nbt);
        this.nbtBinging = activityBmwNbtBinding;
        this.nbtModel.setMainnbtBinging(activityBmwNbtBinding);
        this.nbtBinging.setNbtModel(this.nbtModel);
        this.nbtModel.initData();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        String str = TAG;
        Log.i(str, "onKeyUp: action=" + event.getAction() + " keyCode =" + keyCode);
        if (UiThemeUtils.isLEXUS_UI(this) && (keyCode == 19 || keyCode == 20 || keyCode == 21 || keyCode == 22)) {
            List<View> viewGroupChildViews = getViewGroupChildViews(this.lexusBinding.lexusMainSv);
            int foucusIndex = getFoucusIndex(viewGroupChildViews);
            Log.i(str, "onKeyUp: viewGroupChildViews=" + viewGroupChildViews.size() + " foucusIndex// =" + foucusIndex);
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
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            if (keyCode == 19 || keyCode == 21) {
                if (this.lexusLsDragBinding.ivLexusLsDragLeftBtn.isFocused()) {
                    this.mScrollController.smoothScrollToPage(0, false);
                    this.lexusLsDragBinding.ivLexusLsDragRightBtn.requestFocus();
                }
            } else if ((keyCode == 20 || keyCode == 22) && this.lexusLsDragBinding.ivLexusLsDragRightBtn.isFocused()) {
                this.mScrollController.smoothScrollToPage(1, false);
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    /* access modifiers changed from: protected */
    public void initLexus() {
        Log.i(TAG, " initLexus: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        ActivityMainLexusBinding activityMainLexusBinding = (ActivityMainLexusBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_lexus);
        this.lexusBinding = activityMainLexusBinding;
        activityMainLexusBinding.setViewModel(this.viewModel);
        this.viewModel.initData();
        if (Settings.System.getInt(getContentResolver(), KeyConfig.AC_CONTROL, 0) == 1) {
            this.viewModel.acControl.set(true);
        } else {
            this.viewModel.acControl.set(false);
        }
    }

    public static String screenShotByShell(Context context) {
        String sdCardPath = Environment.getExternalStorageDirectory().getPath() + "/screenShot";
        File dir = new File(sdCardPath);
        if (!dir.exists()) {
            dir.mkdirs();
        } else {
            delteDirectoryFiles(sdCardPath);
        }
        String filePath = sdCardPath + File.separator + "screenshot-top.png";
        Log.e("liuhao", "filePath = " + filePath);
        try {
            Runtime.getRuntime().exec("screencap -p " + filePath + " \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    private static boolean delteDirectoryFiles(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            file.delete();
            return true;
        }
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            return false;
        }
        for (File f : files) {
            if (!f.isDirectory()) {
                f.delete();
            }
        }
        return true;
    }

    private Bitmap getShotBitmap() {
        Display mDisplay = ((WindowManager) mainActivity.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        mDisplay.getRealMetrics(mDisplayMetrics);
        Rect crop = new Rect(0, 0, mDisplayMetrics.widthPixels, mDisplayMetrics.heightPixels);
        Bitmap mScreenBitmap = getScreenShot(crop, crop.width(), crop.height(), mDisplay.getRotation());
        mScreenBitmap.setHasAlpha(false);
        mScreenBitmap.prepareToDraw();
        return mScreenBitmap;
    }

    public static Bitmap getScreenShot(Rect crop, int w, int h, int rot) {
        try {
            try {
                return (Bitmap) Class.forName("android.view.SurfaceControl").getMethod("screenshot", new Class[]{String.class}).invoke(Class.forName("android.view.SurfaceControl"), new Object[]{crop, Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(rot)});
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e2) {
                return null;
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
                return null;
            }
        } catch (SecurityException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return null;
        } catch (ClassNotFoundException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void initLexusLsDrag() {
        String str;
        LexusLsAppSelBean bean;
        PowerManagerApp.registerIContentObserver("topApp", this.shotScreenObserver);
        Log.i(TAG + " liuhao", "initLexusLsDrag() ");
        setFull();
        setStatusBarTranslucent();
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        LexusLsDragMainLayoutBinding lexusLsDragMainLayoutBinding = (LexusLsDragMainLayoutBinding) DataBindingUtil.setContentView(this, R.layout.lexus_ls_drag_main_layout);
        this.lexusLsDragBinding = lexusLsDragMainLayoutBinding;
        lexusLsDragMainLayoutBinding.setLexusLsViewModel(this.viewModel);
        this.recyclerview_drag = this.lexusLsDragBinding.recyclerviewDrag;
        this.mDragLayer = (DragLayer) findViewById(R.id.demo_draglayer);
        this.mDeleteZone = (DeleteZone) findViewById(R.id.demo_del_zone);
        this.lexusLsDragBinding.ivLexusLsDragLeftBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.mScrollController.smoothScrollToPage(0, true);
            }
        });
        this.lexusLsDragBinding.ivLexusLsDragRightBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.mScrollController.smoothScrollToPage(1, true);
            }
        });
        this.mDragController = new DragController(mainActivity);
        mRow = 1;
        mColumn = 8;
        this.pageSize = 8 * 1;
        this.isFirstLoad = Settings.System.getString(mainActivity.getContentResolver(), LexusLsConfig.IS_FIRST);
        LOGE.E("isFirstLoad = " + this.isFirstLoad);
        if (TextUtils.equals(this.isFirstLoad, "true") || (str = this.isFirstLoad) == null) {
            LOGE.E("isFirstLoad  TextUtils.equals(isFirstLoad,\"true\") || isFirstLoad==null");
            LexusLsConfig.mAppList.clear();
            LexusLsConfig.mAppList.addAll(LexusLsConfig.getMenuAppList(mainActivity));
        } else if (TextUtils.equals(str, "false")) {
            LexusLsConfig.AppPkgs = Settings.System.getString(mainActivity.getContentResolver(), LexusLsConfig.LEXUS_LS_SAVE_PKG);
            LOGE.E("TextUtils.equals(isFirstLoad,\"false\")LexusLsConfig.AppPkgs : " + LexusLsConfig.AppPkgs);
            if (LexusLsConfig.AppPkgs == null || LexusLsConfig.AppPkgs.length() <= 0) {
                LOGE.E("ELSE   isFirstLoad = " + this.isFirstLoad + "\nLexusLsConfig.AppPkgs : " + LexusLsConfig.AppPkgs);
                LexusLsConfig.mAppList.addAll(LexusLsConfig.getMenuAppList(mainActivity));
            } else {
                LexusLsConfig.mAppList.clear();
                String[] pksArray = LexusLsConfig.AppPkgs.split(";");
                for (int i = 0; i < pksArray.length; i++) {
                    new LexusLsAppSelBean();
                    if (LexusLsConfig.isMenu(pksArray[i])) {
                        bean = LexusLsConfig.findBeanByPkg(pksArray[i], LexusLsConfig.getMenuAppList(mainActivity));
                    } else {
                        bean = LexusLsConfig.findBeanByPkg(pksArray[i], (ArrayList) AppInfoUtils.findLexusLsAllApp(mainActivity));
                    }
                    LexusLsConfig.mAppList.add(bean);
                }
            }
        }
        this.pageCount = (LexusLsConfig.mAppList.size() / this.pageSize) + (LexusLsConfig.mAppList.size() % this.pageSize == 0 ? 0 : 1);
        LexusLsDragItemAdapter lexusLsDragItemAdapter = new LexusLsDragItemAdapter(LexusLsConfig.mAppList, this.viewModel, mainActivity);
        this.adapterLexusLsDrag = lexusLsDragItemAdapter;
        this.recyclerview_drag.setAdapter(lexusLsDragItemAdapter);
        this.recyclerview_drag.setItemAnimator(new DefaultItemAnimator());
        this.adapterLexusLsDrag.setOnClickAddApp(new LexusLsDragItemAdapter.IOnAddAppClickListener() {
            public void onClick(View view) {
                MainActivity.this.initPopuWindow(view);
            }
        });
        this.adapterLexusLsDrag.setLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (!view.isInTouchMode() && MainActivity.this.isEnableDrag) {
                    return false;
                }
                LOGE.E("onLongClick ********************* Drag started");
                DragSource dragSource = (DragSource) view;
                MainActivity.this.mDragController.startDragBitmap(view, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
                return true;
            }
        });
        this.adapterLexusLsDrag.setItemDragListener(new LexusLsDragItemAdapter.ItemDragListener() {
            public void onItemDragStarted(View source) {
                LOGE.E("onItemDragStarted soure=" + source);
            }

            public void onItemDropCompleted(View source, View target, boolean success) {
                LOGE.E("========onDropCompleted success : " + success);
                if (success && source != target) {
                    LOGE.E("success && (source != target)");
                    LexusLsAppSelBean sourceItem = ((DraggableLayout) source).getItem();
                    if (target instanceof DeleteZone) {
                        LOGE.E("target instanceof DeleteZone");
                        if (sourceItem == null) {
                            Log.e(MainActivity.TAG, "sourceItem is null in delete action !!!");
                            return;
                        } else if (sourceItem.isDeletable()) {
                            LOGE.E("sourceItem.isDeletable()  POS = " + sourceItem.getItemPos());
                            LexusLsConfig.mAppList.remove(sourceItem);
                            MainActivity.this.updateHideAndShowAddMenu();
                            MainActivity.this.refreshItemList();
                            MainActivity.this.adapterLexusLsDrag.notifyDataSetChanged();
                            LexusLsConfig.saveDataOrder(MainActivity.mainActivity);
                            boolean contains = LexusLsConfig.mAppList.contains(sourceItem);
                        } else {
                            Toast.makeText(MainActivity.this, "no delete action", 0).show();
                        }
                    } else {
                        LexusLsAppSelBean targetItem = ((DraggableLayout) target).getItem();
                        if (sourceItem == null) {
                            LOGE.E("sourceItem is null in replace action !!!");
                            return;
                        } else if (targetItem == null) {
                            MainActivity.this.mDeleteZone.setVisibility(8);
                            LOGE.E("targetItem is null in replace action !!!");
                            return;
                        } else {
                            MainActivity.this.executeItemReplaceAction(sourceItem, targetItem);
                        }
                    }
                }
                if (MainActivity.this.mDragLayer.getDraggingListener() != null) {
                    MainActivity.this.mDragLayer.getDraggingListener().onDragEnd();
                }
            }
        });
        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(mRow, mColumn, 0, this);
        this.horizhontalPageLayoutManager = horizontalPageLayoutManager;
        horizontalPageLayoutManager.setDragLayer(this.mDragLayer);
        this.recyclerview_drag.setLayoutManager(this.horizhontalPageLayoutManager);
        this.mScrollController.setUpRecycleView(this.recyclerview_drag);
        this.mScrollController.setOnPageChangeListener(new ScrollController.OnPageChangeListener() {
            public void onPageChange(int index) {
                LOGE.E("当前页面为 ： " + index);
                if (MainActivity.this.needUpdateDataPageIndex != -1 && index == MainActivity.this.needUpdateDataPageIndex) {
                    MainActivity.this.adapterLexusLsDrag.notifyDataSetChanged();
                    int unused = MainActivity.this.needUpdateDataPageIndex = -1;
                }
                if (MainActivity.this.replaceAppPageIndex != -1 && index == MainActivity.this.replaceAppPageIndex) {
                    MainActivity.this.adapterLexusLsDrag.notifyDataSetChanged();
                    int unused2 = MainActivity.this.replaceAppPageIndex = -1;
                }
                if (index == 0) {
                    MainActivity.this.lexusLsDragBinding.ivLexusLsDragLeftBtn.setVisibility(4);
                    MainActivity.this.lexusLsDragBinding.ivLexusLsDragRightBtn.setVisibility(0);
                    return;
                }
                MainActivity.this.lexusLsDragBinding.ivLexusLsDragLeftBtn.setVisibility(0);
                MainActivity.this.lexusLsDragBinding.ivLexusLsDragRightBtn.setVisibility(4);
            }
        });
        this.lexusLsDragBinding.ivLexusLsDragRightBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
            }
        });
        this.lexusLsDragBinding.ivLexusLsDragLeftBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
            }
        });
        this.mDragLayer.setDragView(this.recyclerview_drag);
        this.mDragLayer.setDragController(this.mDragController);
        this.mDragLayer.setDraggingListener(new DragController.DraggingListener() {
            public void onDragStart(DragSource source, Object info, int dragAction) {
                if (LexusLsConfig.isMenu(((DraggableLayout) source).getItem().getAppPkg())) {
                    LOGE.E("----------GONE-------------->");
                    MainActivity.this.mDeleteZone.setVisibility(8);
                    return;
                }
                LOGE.E("-----------VISIBLE------------->");
                MainActivity.this.mDeleteZone.setVisibility(0);
            }

            public void onDragEnd() {
                LOGE.E("-----------onDragEnd------------->");
                MainActivity.this.mDeleteZone.setVisibility(8);
            }
        });
        DeleteZone deleteZone = this.mDeleteZone;
        if (deleteZone != null) {
            deleteZone.setEnabled(true);
            this.mDragLayer.setDeleteZoneId(this.mDeleteZone.getId());
        }
        this.mDragController.setDraggingListener(this.mDragLayer);
        this.mDragController.setScrollController(this.mScrollController);
    }

    /* access modifiers changed from: private */
    public void executeItemReplaceAction(LexusLsAppSelBean sourceItem, LexusLsAppSelBean targetItem) {
        int sourcePos = sourceItem.getItemPos();
        int targetPos = targetItem.getItemPos();
        LOGE.E("sourcePos: " + sourcePos + " targetPos: " + targetPos);
        int i = this.pageSize;
        if (sourcePos / i != targetPos / i) {
            this.needUpdateDataPageIndex = sourcePos / i;
        }
        Collections.swap(LexusLsConfig.mAppList, sourcePos, targetPos);
        refreshItemList();
        this.adapterLexusLsDrag.notifyDataSetChanged();
        LexusLsConfig.saveDataOrder(mainActivity);
    }

    public void exChangePosition(int dragPostion, int dropPostion) {
        LexusLsAppSelBean item = LexusLsConfig.mAppList.get(dragPostion);
        Log.e("liuhao Adapter", "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
        if (dragPostion < dropPostion) {
            LexusLsConfig.mAppList.add(dropPostion + 1, item);
            LexusLsConfig.mAppList.remove(dragPostion);
            return;
        }
        LexusLsConfig.mAppList.add(dropPostion, item);
        LexusLsConfig.mAppList.remove(dragPostion + 1);
    }

    /* access modifiers changed from: private */
    public void refreshItemList() {
        if (LexusLsConfig.mAppList != null && LexusLsConfig.mAppList.size() > 0) {
            for (int i = 0; i < LexusLsConfig.mAppList.size(); i++) {
                LexusLsConfig.mAppList.get(i).setItemPos(i);
            }
        }
    }

    private void uninstallAppIntent(String pkg, Context context) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + pkg));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void initLexusLs() {
        Log.i(TAG + " liuhao", "initLexusLs: ");
        setFull();
        setStatusBarTranslucent();
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        final LexusLsMainLayoutBinding lexusLsBinding = (LexusLsMainLayoutBinding) DataBindingUtil.setContentView(this, R.layout.lexus_ls_main_layout);
        lexusLsBinding.setLexusLsViewModel(this.viewModel);
        this.lexusLsVpagerBottom = lexusLsBinding.lexusLsViewpagerBottom;
        this.lexusLsBottomViewPagerAdpater = new LexusLsBottomViewPagerAdpater(getSupportFragmentManager());
        lexusLsBinding.lexusLsViewpagerBottom.setAdapter(this.lexusLsBottomViewPagerAdpater);
        lexusLsBinding.lexusLsViewpagerBottom.setCurrentItem(0);
        this.lexusLsTopAppAdapter = new LexusLsTopAppAdapter(mainActivity, mCheckedAppList);
        new PagerGridLayoutManager(2, 7, 1);
        lexusLsBinding.lexusLsRecycleviewDesktop.setAdapter(this.lexusLsTopAppAdapter);
        lexusLsBinding.lexusLsRecycleviewDesktop.setLayoutManager(new GridLayoutManager(mainActivity, 7));
        this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomTwo.setOnClickAddApp(new FragmentLexusLsBottomTwo.IAddAppClickListener() {
            public void onClick(View view) {
                MainActivity.this.initPopuWindow(view);
            }
        });
        lexusLsBinding.lexusLsViewpagerBottom.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int arg, float v, int i1) {
            }

            public void onPageSelected(int i) {
                if (i == 0) {
                    lexusLsBinding.ivLexuslsLeftBtn.setVisibility(4);
                    lexusLsBinding.ivLexuslsRightBtn.setVisibility(0);
                    return;
                }
                lexusLsBinding.ivLexuslsLeftBtn.setVisibility(0);
                lexusLsBinding.ivLexuslsRightBtn.setVisibility(4);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        lexusLsBinding.lexusLsViewpagerBottom.setPageTransformer(true, new ViewPager.PageTransformer() {
            public void transformPage(View page, float position) {
                int pageWidth = page.getWidth();
                if (position <= 0.0f) {
                    page.setTranslationX(0.0f);
                    page.setScaleX(1.0f);
                    page.setScaleY(1.0f);
                } else if (position <= 1.0f) {
                    float SCALE = 0.5f - (position / 2.0f);
                    page.setScaleX(0.5f + SCALE);
                    page.setScaleY(0.5f + SCALE);
                    page.setAlpha(SCALE + SCALE);
                    page.setTranslationX(((float) pageWidth) * (-position));
                } else {
                    page.setTranslationX((float) pageWidth);
                }
            }
        });
        lexusLsBinding.ivLexuslsLeftBtn.setVisibility(4);
        lexusLsBinding.ivLexuslsRightBtn.setVisibility(0);
        lexusLsBinding.ivLexuslsLeftBtn.setOnClickListener(new View.OnClickListener(lexusLsBinding) {
            public final /* synthetic */ LexusLsMainLayoutBinding f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                MainActivity.this.lambda$initLexusLs$4$MainActivity(this.f$1, view);
            }
        });
        lexusLsBinding.ivLexuslsRightBtn.setOnClickListener(new View.OnClickListener(lexusLsBinding) {
            public final /* synthetic */ LexusLsMainLayoutBinding f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                MainActivity.this.lambda$initLexusLs$5$MainActivity(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$initLexusLs$4$MainActivity(LexusLsMainLayoutBinding lexusLsBinding, View v) {
        int index = lexusLsBinding.lexusLsViewpagerBottom.getCurrentItem() - 1;
        if (index >= 0) {
            lexusLsBinding.lexusLsViewpagerBottom.setCurrentItem(index);
            if (index == 0) {
                this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomOne.setDefaultSelected();
            } else if (index == 1) {
                this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomTwo.setDefaultSelected();
            }
        }
    }

    public /* synthetic */ void lambda$initLexusLs$5$MainActivity(LexusLsMainLayoutBinding lexusLsBinding, View v) {
        int index = lexusLsBinding.lexusLsViewpagerBottom.getCurrentItem() + 1;
        if (index <= 2) {
            lexusLsBinding.lexusLsViewpagerBottom.setCurrentItem(index);
            if (index == 1) {
                this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomTwo.setDefaultSelected();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateHideAndShowAddMenu() {
        if (LexusLsConfig.mAppList.size() > this.pageSize * 2) {
            if (LexusLsConfig.mAppList.contains(LexusLsConfig.findBeanByPkg(LexusLsConfig.PKG_MENU_STRS[12], LexusLsConfig.mAppList))) {
                LexusLsConfig.mAppList.remove(LexusLsConfig.findBeanByPkg(LexusLsConfig.PKG_MENU_STRS[12], LexusLsConfig.mAppList));
            }
        } else if (!LexusLsConfig.mAppList.contains(LexusLsConfig.findBeanByPkg(LexusLsConfig.PKG_MENU_STRS[12], LexusLsConfig.mAppList)) && LexusLsConfig.mAppList.size() < this.pageSize * 2) {
            LexusLsAppSelBean beanAddApp = new LexusLsAppSelBean();
            beanAddApp.setAppName(mainActivity.getResources().getString(R.string.add_app).trim());
            beanAddApp.setAppIcon(mainActivity.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_add));
            beanAddApp.setAppPkg("lexus.ls.defined.pkgname.12");
            beanAddApp.setItemPos(LexusLsConfig.mAppList.size());
            beanAddApp.setDeletable(false);
            LexusLsConfig.mAppList.add(beanAddApp);
        }
    }

    /* access modifiers changed from: private */
    public void initPopuWindow(View view) {
        if (this.mPopupWindow != null) {
            showPopuwindow(view);
            return;
        }
        Log.e("liuhao", "add app click~~initPopuWindow()");
        mAllAppList = (ArrayList) AppInfoUtils.findLexusLsAllAppDeleteDesk(mainActivity);
        View popDialog = LayoutInflater.from(mainActivity).inflate(R.layout.lexus_ls_add_pop_layout, (ViewGroup) null);
        this.dialogWidth = ScreenUtil.dip2px(mainActivity.getResources().getDimension(R.dimen.dimen_lexus_ls_pop_width));
        Log.e("liuhao", "diwidth =" + this.dialogWidth);
        this.dialogHeight = ScreenUtil.dip2px(400.0f);
        PopupWindow popupWindow = new PopupWindow(popDialog, ScreenUtil.dip2px(480.0f), ScreenUtil.dip2px(420.0f), true);
        this.mPopupWindow = popupWindow;
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.lexus_ls_addapp_iv_cancel = (TextView) popDialog.findViewById(R.id.lexus_ls_addapp_iv_cancel);
        this.lexus_ls_addapp_iv_ok = (TextView) popDialog.findViewById(R.id.lexus_ls_addapp_iv_ok);
        this.lexus_ls_addapp_iv_cancel.setOnClickListener(this.mDialogBtnClickListener);
        this.lexus_ls_addapp_iv_ok.setOnClickListener(this.mDialogBtnClickListener);
        this.lexus_ls_add_app_recycleview = (RecyclerView) popDialog.findViewById(R.id.lexus_ls_add_app_recycleview);
        this.lexusLsSelAppAdapter = new LexusLsSelAppAdapter(mainActivity, mAllAppList);
        PagerGridLayoutManager lexuslsLayoutManager = new PagerGridLayoutManager(3, 4, 0);
        this.lexus_ls_add_app_recycleview.setAdapter(this.lexusLsSelAppAdapter);
        this.lexus_ls_add_app_recycleview.setLayoutManager(lexuslsLayoutManager);
        this.mPopupWindow.setOutsideTouchable(true);
        showPopuwindow(view);
    }

    private void showPopuwindow(View view) {
        int height = view.getHeight();
        int width = view.getWidth();
        Log.i(TAG, String.format("width = %s ;height= %s;", new Object[]{Integer.valueOf(width), Integer.valueOf(height)}));
        this.mPopupWindow.showAsDropDown(view, width, 0);
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
        Log.d(TAG, "getViewGroupChildViews allchildren size=" + allchildren.size());
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
        RomeoViewModel romeoViewModel2 = (RomeoViewModel) ViewModelProviders.of((FragmentActivity) this).get(RomeoViewModel.class);
        this.romeoViewModel = romeoViewModel2;
        romeoViewModel2.setActivity(this);
        ActivityRomeoBinding activityRomeoBinding = (ActivityRomeoBinding) DataBindingUtil.setContentView(this, R.layout.activity_romeo);
        this.romeoBinding = activityRomeoBinding;
        activityRomeoBinding.setViewModel(this.romeoViewModel);
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
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(MainActivity.TAG, "recyclerView onScrollStateChanged newState=" + newState);
                if (newState == 0) {
                    int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    Log.d(MainActivity.TAG, "recyclerView onScrollStateChanged firstVisibleItemPosition=" + firstVisibleItemPosition);
                    if (firstVisibleItemPosition != 0) {
                        MainActivity.this.romeoBinding.pageIndicator1.getDrawable().setLevel(0);
                        MainActivity.this.romeoBinding.pageIndicator2.getDrawable().setLevel(1);
                        return;
                    }
                    MainActivity.this.romeoBinding.pageIndicator1.getDrawable().setLevel(1);
                    MainActivity.this.romeoBinding.pageIndicator2.getDrawable().setLevel(0);
                }
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(MainActivity.TAG, "recyclerView onScrolled dx=" + dx + " dy=" + dy);
                MainActivity.this.changeDistance(recyclerView);
            }
        });
    }

    /* access modifiers changed from: private */
    public void changeDistance(RecyclerView recyclerView) {
        Log.d(TAG, "calculateTranslate count=" + recyclerView.getChildCount());
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
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        startActivity(new Intent(this, Id7NewActivity.class));
        finish();
    }

    /* access modifiers changed from: protected */
    public void initCommonUIGSUGView() {
        Log.i(TAG, "initCommonUIGSUGView: ");
        ActivityMainGsugBindingImpl activityMainGsugBindingImpl = (ActivityMainGsugBindingImpl) DataBindingUtil.setContentView(this, R.layout.activity_main_gsug);
        this.ugBinding = activityMainGsugBindingImpl;
        activityMainGsugBindingImpl.ugViewPage.setAdapter(new UgHomePagerAdpater(getSupportFragmentManager()));
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        this.ugBinding.setViewModel(this.viewModel);
        this.ugBinding.ugViewPage.setUgPageChangeListener(new UgViewPager.UgPageChangeListener() {
            public void onPageSelected(int i, boolean left, boolean right) {
                Log.i(MainActivity.TAG, "onPageSelected: i:" + i + " left:" + left + " right:" + right);
                MainActivity.this.select.setValue(new UgPager(i, left, right));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initCommonUIGSUG1024View() {
        Log.i(TAG, "initCommonUIGSUG1024View: ");
        ActivityMainGsug2BindingImpl activityMainGsug2BindingImpl = (ActivityMainGsug2BindingImpl) DataBindingUtil.setContentView(this, R.layout.activity_main_gsug2);
        this.gsug2Binding = activityMainGsug2BindingImpl;
        activityMainGsug2BindingImpl.ugViewPage.setAdapter(new UgHomePagerAdpater(getSupportFragmentManager()));
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        this.gsug2Binding.setViewModel(this.viewModel);
        this.gsug2Binding.ugViewPage.setUgPageChangeListener(new UgViewPager.UgPageChangeListener() {
            public void onPageSelected(int i, boolean left, boolean right) {
                Log.i(MainActivity.TAG, "onPageSelected: i:" + i + " left:" + left + " right:" + right);
                MainActivity.this.select.setValue(new UgPager(i, left, right));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initAlsId7UI() {
        Log.i(TAG, "chen-new ui initAlsId7UI: ");
        AlsID7ViewModel alsID7ViewModel2 = (AlsID7ViewModel) ViewModelProviders.of((FragmentActivity) this).get(AlsID7ViewModel.class);
        this.alsID7ViewModel = alsID7ViewModel2;
        alsID7ViewModel2.setActivity(this);
        ActivityMainAlsId7Binding activityMainAlsId7Binding = (ActivityMainAlsId7Binding) DataBindingUtil.setContentView(this, R.layout.activity_main_als_id7);
        this.alsId7Binding = activityMainAlsId7Binding;
        activityMainAlsId7Binding.viewPage.setAdapter(new BMWId7ViewPagerAdpater(getSupportFragmentManager()));
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
        LandroverViewModel landroverViewModel2 = (LandroverViewModel) ViewModelProviders.of((FragmentActivity) this).get(LandroverViewModel.class);
        this.landroverViewModel = landroverViewModel2;
        landroverViewModel2.setActivity(this);
        LandroverMainBinding landroverMainBinding = (LandroverMainBinding) DataBindingUtil.setContentView(this, R.layout.landrover_main);
        this.landroverBinding = landroverMainBinding;
        landroverMainBinding.viewPager.setAdapter(new LandRoverViewPagerAdpater(getSupportFragmentManager()));
        setCurrentItem(0);
        this.landroverBinding.viewPager.setOffscreenPageLimit(2);
        this.landroverBinding.indicato1.setSelected(true);
        this.landroverBinding.indicato2.setSelected(false);
        this.landroverBinding.setLauncherViewModel(this.landroverViewModel);
        this.landroverViewModel.initData();
        LandroverPopWindow.setViewModel(this.landroverViewModel);
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

    private class LandroverHandler extends Handler {
        private LandroverHandler() {
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initAudiMbi3View() {
        Log.i(TAG, "initAudiMbi3View : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mAudiMbi3BcViewModel = bcVieModel;
        bcVieModel.setActivity(this);
        mAudiMbi3BcViewModel.initData();
        AudiMib3MainLayoutBinding audiMib3MainLayoutBinding = (AudiMib3MainLayoutBinding) DataBindingUtil.setContentView(this, R.layout.audi_mib3_main_layout);
        audiMib3Binding = audiMib3MainLayoutBinding;
        audiMib3MainLayoutBinding.setViewModel(mAudiMbi3BcViewModel);
        audiMib3Binding.include.ivMusicAudimib3.setOnClickListener(this.mAudiMib3ItemClickListener);
        audiMib3Binding.include.ivNaviAudimib3.setOnClickListener(this.mAudiMib3ItemClickListener);
        audiMib3Binding.include.ivBtAudimib3.setOnClickListener(this.mAudiMib3ItemClickListener);
        audiMib3Binding.include.ivSetAudimib3.setOnClickListener(this.mAudiMib3ItemClickListener);
        audiMib3Binding.include.ivCarAudimib3.setOnClickListener(this.mAudiMib3ItemClickListener);
        audiMib3ViewPagerAdapter = new AudiMib3ViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = audiMib3Binding.viewPageAudiMib3;
        this.viewpagerAudiMib3 = viewPager;
        viewPager.setAdapter(audiMib3ViewPagerAdapter);
        this.viewpagerAudiMib3.setCurrentItem(0);
        audiMib3Binding.includeLl.setFocusable(false);
        audiMib3Binding.includeLl.setFocusableInTouchMode(false);
        this.viewpagerAudiMib3.setFocusable(true);
        this.viewpagerAudiMib3.setFocusableInTouchMode(true);
        this.viewpagerAudiMib3.requestFocus();
        this.viewpagerAudiMib3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int pageIndex) {
                MainActivity.this.setIndicatorBackground(pageIndex, 4);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(4);
        setItemSelected(audiMib3Binding.include.ivMusicAudimib3);
    }

    public void setItemSelected(View view) {
        boolean z = true;
        audiMib3Binding.include.ivMusicAudimib3.setSelected(audiMib3Binding.include.ivMusicAudimib3 == view);
        audiMib3Binding.include.ivNaviAudimib3.setSelected(audiMib3Binding.include.ivNaviAudimib3 == view);
        audiMib3Binding.include.ivBtAudimib3.setSelected(audiMib3Binding.include.ivBtAudimib3 == view);
        audiMib3Binding.include.ivSetAudimib3.setSelected(audiMib3Binding.include.ivSetAudimib3 == view);
        ImageView imageView = audiMib3Binding.include.ivCarAudimib3;
        if (audiMib3Binding.include.ivCarAudimib3 != view) {
            z = false;
        }
        imageView.setSelected(z);
    }

    /* access modifiers changed from: protected */
    public void initBenzMBUXView() {
        Log.i(TAG, "initBenzMBUXView: ");
        setFull();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        ActivityMainBenzMbuxBindingImpl benzMbuxBinding = (ActivityMainBenzMbuxBindingImpl) DataBindingUtil.setContentView(this, R.layout.activity_main_benz_mbux);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 0, false);
        benzMbuxBinding.benzMbuxRecyclerView.setFocusable(false);
        benzMbuxBinding.benzMbuxRecyclerView.setLayoutManager(linearLayoutManager);
        benzMbuxBinding.benzMbuxRecyclerView.setAdapter(new BenzMbuxRecyclerViewAdpater(mBcVieModel));
        new LinearSnapHelper() {
            public boolean onFling(int velocityX, int velocityY) {
                return false;
            }
        }.attachToRecyclerView(benzMbuxBinding.benzMbuxRecyclerView);
        benzMbuxBinding.setVieModel(mBcVieModel);
    }

    /* access modifiers changed from: protected */
    public void initBenz_MBUX_2021_View() {
        Log.i(TAG, "initBenz_MBUX_2021_View: ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        this.benzMbux2021Binding = (BenzMbux2021ActivityBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_benz_mbux_2021);
        this.mbux2021_LayoutManager = new PagerGridLayoutManager(1, 3, 1);
        initIndicatorPoints(1);
        this.mbux2021_LayoutManager.setPageListener(new PagerGridLayoutManager.PageListener() {
            public void onPageSizeChanged(int pageSize) {
            }

            public void onPageSelect(int pageIndex) {
                MainActivity.this.setIndicatorBackground(pageIndex, 1);
                if (pageIndex == 0) {
                    MainActivity.this.benzMbux2021Binding.layoutMain2021.setBackgroundResource(R.drawable.mbux2_bg_main_1_1);
                } else {
                    MainActivity.this.benzMbux2021Binding.layoutMain2021.setBackgroundResource(R.drawable.mbux2_bg_main_2_1);
                }
            }
        });
        this.benzMbux2021Binding.benzMbux2021RecyclerView.setFocusable(false);
        this.benzMbux2021Binding.benzMbux2021RecyclerView.setLayoutManager(this.mbux2021_LayoutManager);
        this.benzMbux2021Binding.benzMbux2021RecyclerView.setAdapter(new BenzMbux2021RecyclerViewAdpater(mBcVieModel));
        new PagerGridSnapHelper().attachToRecyclerView(this.benzMbux2021Binding.benzMbux2021RecyclerView);
        this.benzMbux2021Binding.setVieModel(mBcVieModel);
        this.benzMbux2021Binding.layoutCoatBenzMbux2021.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.showThemePopup_2021(v);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initBenz_MBUX_2021_View2() {
        Log.i(TAG, "initBenz_MBUX_2021_View2: ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        BenzMbux2021ActivityBinding2 benzMbux2021ActivityBinding2 = (BenzMbux2021ActivityBinding2) DataBindingUtil.setContentView(this, R.layout.activity_main_benz_mbux_2021_2);
        benzMbux2021Binding2 = benzMbux2021ActivityBinding2;
        benzMbux2021ActivityBinding2.setVieModel(mBcVieModel);
        benzMbux2021ViewPagerAdapter = new BenzMbux2021ViewPagerAdapter(getSupportFragmentManager());
        this.viewPagerBenzMbux2021 = benzMbux2021Binding2.benzMbux2021Viewpager;
        benzMbux2021Binding2.benzMbux2021Viewpager.setAdapter(benzMbux2021ViewPagerAdapter);
        benzMbux2021Binding2.benzMbux2021Viewpager.setCurrentItem(0);
        benzMbux2021Binding2.benzMbux2021Viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int pageIndex) {
                ObservableField<BitmapDrawable> observableField;
                LauncherViewModel unused = MainActivity.this.viewModel;
                LauncherViewModel.mediaInfo.setPageIndex(pageIndex);
                LOGE.E("bg_sel = " + BenzMbux2021Configs.bg_sel + "  pageIndex = " + pageIndex);
                MainActivity.this.setIndicatorBackground(pageIndex, 2);
                if (pageIndex == 0) {
                    LOGE.E("bg_sel pageIndex0 ----------- ");
                    ImageView imageView = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel = MainActivity.mBcVieModel;
                    imageView.setBackground(BcVieModel.mediaInfo.picOne.get());
                } else if (pageIndex == 1) {
                    LOGE.E("bg_sel pageIndex1 ----------- ");
                    ImageView imageView2 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel2 = MainActivity.mBcVieModel;
                    if (BcVieModel.mediaInfo.picBg.get() != null) {
                        BcVieModel bcVieModel3 = MainActivity.mBcVieModel;
                        observableField = BcVieModel.mediaInfo.picBg;
                    } else {
                        BcVieModel bcVieModel4 = MainActivity.mBcVieModel;
                        observableField = BcVieModel.mediaInfo.picOther;
                    }
                    imageView2.setBackground(observableField.get());
                } else {
                    LOGE.E("bg_sel pageIndex2 ----------- ");
                    ImageView imageView3 = MainActivity.benzMbux2021Binding2.layoutMain2021;
                    BcVieModel bcVieModel5 = MainActivity.mBcVieModel;
                    imageView3.setBackground(BcVieModel.mediaInfo.picOther.get());
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(2);
        benzMbux2021Binding2.setVieModel(mBcVieModel);
        benzMbux2021Binding2.layoutCoatBenzMbux2021.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LOGE.D("Benz2021DialogThemeActivity");
                MainActivity.this.startActivity(new Intent(MainActivity.mainActivity, Benz2021DialogThemeActivity.class));
            }
        });
        this.styleIndex = Settings.System.getString(mainActivity.getContentResolver(), BenzMbux2021Configs.STYLE_INDEX);
        this.bgIndex = Settings.System.getString(mainActivity.getContentResolver(), BenzMbux2021Configs.BG_INDEX);
        if (TextUtils.isEmpty(this.styleIndex)) {
            this.styleIndex = "1";
        }
        if (TextUtils.isEmpty(this.bgIndex)) {
            this.bgIndex = "1";
        }
        int iStyleIndex = Integer.parseInt(this.styleIndex);
        int iBgIndex = Integer.parseInt(this.bgIndex);
        LOGE.D("styleIndex = " + this.styleIndex + " iStyleIndex = " + iStyleIndex + " iBgIndex" + iBgIndex);
        if (iStyleIndex == 1) {
            mBcVieModel.itemIconClass.set(true);
        } else {
            mBcVieModel.itemIconClass.set(false);
        }
        BcVieModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_ONE[iBgIndex - 1]));
        BcVieModel.mediaInfo.setPicBg((BitmapDrawable) (BcVieModel.mediaInfo.pic == null ? BitmapUtil.getDefaultMBUX2021BG_OTHER() : BcVieModel.mediaInfo.pic.get()));
        BcVieModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[iBgIndex - 1]));
        benzMbux2021Binding2.layoutMain2021.setBackground(BcVieModel.mediaInfo.picOne.get());
    }

    /* access modifiers changed from: private */
    public void showThemePopup_2021(View v) {
        PopupWindow popupWindow = this.mPopupThemeWindow;
        if (popupWindow != null) {
            popupWindow.showAtLocation(getWindow().getDecorView(), 17, 0, 40);
            return;
        }
        View dialogLayout = LayoutInflater.from(mainActivity).inflate(R.layout.dialog_benz_mbux_2021_theme_layout, (ViewGroup) null);
        PopupWindow popupWindow2 = new PopupWindow(dialogLayout, ScreenUtil.dip2px(1100.0f), ScreenUtil.dip2px(400.0f), true);
        this.mPopupThemeWindow = popupWindow2;
        popupWindow2.setBackgroundDrawable(new ColorDrawable(0));
        this.tv_icon_sel_classical = (TextView) dialogLayout.findViewById(R.id.tv_icon_sel_classical);
        this.tv_icon_sel_modern = (TextView) dialogLayout.findViewById(R.id.tv_icon_sel_modern);
        this.rb_icon_sel_classical = (RadioButton) dialogLayout.findViewById(R.id.rb_icon_sel_classical);
        RadioButton radioButton = (RadioButton) dialogLayout.findViewById(R.id.rb_icon_sel_modern);
        this.rb_icon_sel_modern = radioButton;
        radioButton.setOnClickListener(this.mThemeItemClickListener);
        this.rb_icon_sel_classical.setOnClickListener(this.mThemeItemClickListener);
        this.rb_icon_sel_classical.setSelected(!this.rb_icon_sel_modern.isSelected());
        this.tv_icon_sel_classical.setTextColor(getColor(this.rb_icon_sel_classical.isSelected() ? R.color.rb_text_color : R.color.color1));
        dialogLayout.findViewById(R.id.rb_benz_mbux_2021_coat_bg_1).setOnClickListener(this.mThemeItemClickListener);
        dialogLayout.findViewById(R.id.rb_benz_mbux_2021_coat_bg_2).setOnClickListener(this.mThemeItemClickListener);
        dialogLayout.findViewById(R.id.rb_benz_mbux_2021_coat_bg_3).setOnClickListener(this.mThemeItemClickListener);
        dialogLayout.findViewById(R.id.rb_benz_mbux_2021_coat_bg_4).setOnClickListener(this.mThemeItemClickListener);
        dialogLayout.findViewById(R.id.rb_benz_mbux_2021_coat_bg_5).setOnClickListener(this.mThemeItemClickListener);
        dialogLayout.findViewById(R.id.rb_benz_mbux_2021_coat_bg_6).setOnClickListener(this.mThemeItemClickListener);
        dialogLayout.findViewById(R.id.rb_benz_mbux_2021_coat_bg_7).setOnClickListener(this.mThemeItemClickListener);
        dialogLayout.setFocusableInTouchMode(true);
        dialogLayout.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                LOGE.D("dialogLayout onKey keycode = " + keyCode);
                switch (keyCode) {
                    case 4:
                    case 82:
                        if (MainActivity.this.mPopupThemeWindow == null || !MainActivity.this.mPopupThemeWindow.isShowing()) {
                            return true;
                        }
                        MainActivity.this.mPopupThemeWindow.dismiss();
                        return true;
                    default:
                        return true;
                }
            }
        });
        dialogLayout.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                LOGE.D("dialogLayout.setOnTouchListener))))))))))))))))))))");
                MainActivity.this.mPopupWindow.dismiss();
                return false;
            }
        });
        this.mPopupThemeWindow.setFocusable(true);
        this.mPopupThemeWindow.setOutsideTouchable(true);
        this.mPopupThemeWindow.setBackgroundDrawable(new PaintDrawable());
        this.mPopupThemeWindow.showAtLocation(getWindow().getDecorView(), 17, 0, 40);
    }

    private void initIndicatorPoints(int TYPE) {
        if (TYPE == 1) {
            this.benzMbux2021Binding.indicatorBenzMbux2021.removeAllViews();
        } else if (TYPE == 2) {
            benzMbux2021Binding2.indicatorBenzMbux2021.removeAllViews();
        } else if (TYPE == 3) {
            benzNtg6FyBinding.indicatorBenzNtg6Fy.removeAllViews();
        } else if (TYPE == 4) {
            audiMib3Binding.indicatorAudimib3.removeAllViews();
        }
        if (TYPE == 1 || TYPE == 2) {
            this.indicatorPoints = new ImageView[this.totalPage];
        } else if (TYPE == 3) {
            this.indicatorPoints = new ImageView[2];
        } else if (TYPE == 4) {
            this.indicatorPoints = new ImageView[2];
        }
        for (int i = 0; i < this.indicatorPoints.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            if (i == 0) {
                if (TYPE == 1 || TYPE == 2) {
                    imageView.setBackgroundResource(R.drawable.mbux2_indicato_sel);
                } else if (TYPE == 3) {
                    imageView.setBackgroundResource(R.drawable.fy_mbux_indicato_sel);
                } else if (TYPE == 4) {
                    imageView.setBackgroundResource(R.drawable.audi_mib3_main_indicato_sel);
                }
            } else if (TYPE == 1 || TYPE == 2) {
                imageView.setBackgroundResource(R.drawable.mbux2_indicato_n);
            } else if (TYPE == 3) {
                imageView.setBackgroundResource(R.drawable.fy_mbux_indicato_n);
            } else if (TYPE == 4) {
                imageView.setBackgroundResource(R.drawable.audi_mib3_main_indicato_n);
            }
            this.indicatorPoints[i] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(-2, -2));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            if (TYPE == 1) {
                this.benzMbux2021Binding.indicatorBenzMbux2021.addView(imageView, layoutParams);
            } else if (TYPE == 2) {
                benzMbux2021Binding2.indicatorBenzMbux2021.addView(imageView, layoutParams);
            } else if (TYPE == 3) {
                benzNtg6FyBinding.indicatorBenzNtg6Fy.addView(imageView, layoutParams);
            } else if (TYPE == 4) {
                audiMib3Binding.indicatorAudimib3.addView(imageView, layoutParams);
            }
            Log.i(TAG, "indicator: add " + i);
        }
    }

    /* access modifiers changed from: private */
    public void setIndicatorBackground(int selectItems, int styleType) {
        if (this.indicatorPoints.length >= 1) {
            int i = 0;
            while (true) {
                ImageView[] imageViewArr = this.indicatorPoints;
                if (i < imageViewArr.length) {
                    if (i == selectItems) {
                        if (styleType == 1 || styleType == 2) {
                            imageViewArr[i].setBackgroundResource(R.drawable.mbux2_indicato_sel);
                        } else if (styleType == 3) {
                            imageViewArr[i].setBackgroundResource(R.drawable.fy_mbux_indicato_sel);
                        } else if (styleType == 4) {
                            imageViewArr[i].setBackgroundResource(R.drawable.audi_mib3_main_indicato_sel);
                        }
                    } else if (styleType == 1 || styleType == 2) {
                        imageViewArr[i].setBackgroundResource(R.drawable.mbux2_indicato_n);
                    } else if (styleType == 3) {
                        imageViewArr[i].setBackgroundResource(R.drawable.fy_mbux_indicato_n);
                    } else if (styleType == 4) {
                        imageViewArr[i].setBackgroundResource(R.drawable.audi_mib3_main_indicato_n);
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initBenz_NTG6_FY_View() {
        Log.i(TAG, "initBenz_NTG6_FY_View : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        benzNtg6FyBinding = (BenzNtg6FyBindCls) DataBindingUtil.setContentView(mainActivity, R.layout.activity_main_benz_ntg6_fy);
        benzNtg6FyViewPagerAdapter = new BenzNtg6FyViewPagerAdapter(getSupportFragmentManager());
        this.viewpagerBenzNtg6Fy = benzNtg6FyBinding.benzNtg6FyViewpager;
        benzNtg6FyBinding.benzNtg6FyViewpager.setAdapter(benzNtg6FyViewPagerAdapter);
        benzNtg6FyBinding.benzNtg6FyViewpager.setCurrentItem(0);
        benzNtg6FyBinding.benzNtg6FyViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int pageIndex) {
                MainActivity.this.setIndicatorBackground(pageIndex, 3);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(3);
        benzNtg6FyBinding.setViewModel(mBcVieModel);
        benzNtg6FyBinding.layoutCoatBenzFy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LOGE.D("BenzNtg6FyThemeActivity))))))))");
                MainActivity.this.startActivity(new Intent(MainActivity.mainActivity, BenzNtg6FyThemeActivity.class));
            }
        });
        String string = Settings.System.getString(mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_INDEX);
        this.bgFyIndex = string;
        if (TextUtils.isEmpty(string)) {
            this.bgFyIndex = "1";
        }
        int iBgFyIndex = Integer.parseInt(this.bgFyIndex);
        LOGE.D("styleIndex iBgFyIndex" + iBgFyIndex);
        benzNtg6FyBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[iBgFyIndex - 1]);
    }

    private void showThemePopupFy(View v) {
        PopupWindow popupWindow = this.mPopupWindow_FY;
        if (popupWindow != null) {
            popupWindow.showAtLocation(getWindow().getDecorView(), 80, 0, 0);
            return;
        }
        PopupWindow popupWindow2 = new PopupWindow(LayoutInflater.from(mainActivity).inflate(R.layout.benz_ntg6_fy_theme_set_layout, (ViewGroup) null), -1, ScreenUtil.dip2px(400.0f), true);
        this.mPopupWindow_FY = popupWindow2;
        popupWindow2.setBackgroundDrawable(new ColorDrawable(0));
        this.mPopupWindow_FY.setOutsideTouchable(true);
        this.mPopupWindow_FY.showAtLocation(getWindow().getDecorView(), 80, 0, 0);
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
        audiBinding.MLoopRotarySwitchView.setMultiple(6.0f).setR(((float) getWindowManager().getDefaultDisplay().getWidth()) / 3.4f).setLoopRotationX(-22).setLoopRotationZ(1);
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
        com.wits.ksw.databinding.MainActivity mainActivity2 = this.bmwBinding;
        if (mainActivity2 != null) {
            mainActivity2.viewPage.setCurrentItem(index);
        }
        ActivityMainAlsId7Binding activityMainAlsId7Binding = this.alsId7Binding;
        if (activityMainAlsId7Binding != null) {
            activityMainAlsId7Binding.viewPage.setCurrentItem(index);
        }
        LandroverMainBinding landroverMainBinding = this.landroverBinding;
        if (landroverMainBinding != null) {
            landroverMainBinding.viewPager.setCurrentItem(index);
        }
    }

    public void refreshLastViewFocused() {
        LauncherViewModel launcherViewModel = this.viewModel;
        if (launcherViewModel != null) {
            launcherViewModel.addLastViewFocused(this.bmwBinding.menuButton1);
            this.viewModel.refreshLastViewFocused();
        }
        AlsID7ViewModel alsID7ViewModel2 = this.alsID7ViewModel;
        if (alsID7ViewModel2 != null) {
            alsID7ViewModel2.addLastViewFocused(this.alsId7Binding.menuButton1);
            this.alsID7ViewModel.refreshLastViewFocused();
        }
    }

    public void showLastViewFocus() {
        int resId = KswUtils.getLastViewId(this);
        Log.i(TAG, "showLastViewFocus: resId=" + resId);
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
        Log.i(TAG, "onPause: ");
        if (UiThemeUtils.isCommon_UI_GS_UG(this)) {
            showLastViewFocus();
        }
        isFront = false;
        LexusLsConfig.saveDataOrder(mainActivity);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        String str = TAG;
        Log.i(str, "onResume: ");
        initSaveData();
        if (UiThemeUtils.isCommon_UI_GS_UG(this)) {
            showLastViewFocus();
        } else if (UiThemeUtils.isBMW_NBT(this)) {
            this.nbtModel.refrshInit();
        } else {
            LauncherViewModel launcherViewModel = this.viewModel;
            if (launcherViewModel != null) {
                launcherViewModel.resumeViewModel();
            }
        }
        AlsID7ViewModel alsID7ViewModel2 = this.alsID7ViewModel;
        if (alsID7ViewModel2 != null) {
            alsID7ViewModel2.resumeViewModel();
            Log.d(str, "oresume ismusicplay" + this.alsID7ViewModel.isMusicPlay());
            AlsID7ViewModel alsID7ViewModel3 = this.alsID7ViewModel;
            alsID7ViewModel3.setMusicPlayState(alsID7ViewModel3.isMusicPlay());
        }
        isFront = true;
        if (UiThemeUtils.isLAND_ROVER(this)) {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                boolean statusBoolean = PowerManagerApp.getStatusBoolean("video_full");
                Log.d(str, "onResume topapp =" + topApp);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID) && LandroverPopWindow.isForeground(getApplicationContext(), getClass().getName())) {
                    Log.d(str, "onResume showpopwindow");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (UiThemeUtils.isLEXUS_LS_UI(mainActivity)) {
            LOGE.E("shotScreenObserver screenFile = " + this.screenFile);
            ImageView iv_lexus_ls_drag_desktop = (ImageView) findViewById(R.id.iv_lexus_ls_drag_desktop);
            Bitmap bitmap = BitmapFactory.decodeFile(this.screenFile);
            if (!(bitmap == null || iv_lexus_ls_drag_desktop == null)) {
                iv_lexus_ls_drag_desktop.setBackground(new BitmapDrawable((Resources) null, bitmap));
                LOGE.E("set BG");
            }
            refreshItemList();
            LexusLsDragItemAdapter lexusLsDragItemAdapter = this.adapterLexusLsDrag;
            if (lexusLsDragItemAdapter != null) {
                lexusLsDragItemAdapter.notifyDataSetChanged();
            }
        }
        if (UiThemeUtils.isBenz_MBUX_2021(this)) {
            MediaInfo mediaInfo = LauncherViewModel.mediaInfo;
            ViewPager viewPager = this.viewPagerBenzMbux2021;
            mediaInfo.setPageIndex(viewPager != null ? viewPager.getCurrentItem() : 0);
        }
    }

    public static Bitmap blurBitmap(Bitmap bitmap, Context context) {
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
        blurScript.setRadius(5.0f);
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);
        allOut.copyTo(outBitmap);
        bitmap.recycle();
        rs.destroy();
        return outBitmap;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        PopupWindow popupWindow;
        Log.d(TAG, "onKey dispatchKeyEvent " + event.getKeyCode());
        int keyCode = event.getKeyCode();
        if (UiThemeUtils.isLEXUS_UI(this)) {
            if (keyCode == 20) {
                Log.d("ttest", "down");
                return super.dispatchKeyEvent(new KeyEvent(event.getAction(), 22));
            } else if (keyCode == 19) {
                Log.d("ttest", "up");
                return super.dispatchKeyEvent(new KeyEvent(event.getAction(), 21));
            }
        }
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            if (keyCode == 20) {
                Log.d("ttest", "down");
                if (event.getAction() == 0) {
                    KeyUtils.pressKey(22);
                }
                return true;
            } else if (keyCode == 19) {
                Log.d("ttest", "up");
                if (event.getAction() == 0) {
                    KeyUtils.pressKey(21);
                }
                return true;
            }
        }
        if (UiThemeUtils.isBenz_MBUX(this)) {
            if (keyCode == 20) {
                Log.d("ttest isBenz_MBUX", "down");
                if (event.getAction() == 0) {
                    KeyUtils.pressKey(22);
                }
                return true;
            } else if (keyCode == 19) {
                Log.d("ttest isBenz_MBUX", "up");
                if (event.getAction() == 0) {
                    KeyUtils.pressKey(21);
                }
                return true;
            }
        }
        if (UiThemeUtils.isAudi_mib3(this)) {
            if (keyCode == 22) {
                Log.d("ttest onKey isAudi_mib3", "right");
                if (event.getAction() == 0) {
                    KeyUtils.pressKey(20);
                }
                return true;
            } else if (keyCode == 21) {
                Log.d("ttest onKey isAudi_mib3", "left");
                if (event.getAction() == 0) {
                    KeyUtils.pressKey(19);
                }
                return true;
            }
        }
        if (UiThemeUtils.isBenz_MBUX_2021(this) && keyCode == 66 && (popupWindow = this.mPopupThemeWindow) != null) {
            popupWindow.dismiss();
        }
        return super.dispatchKeyEvent(event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (UiThemeUtils.isBenz_NTG6(this) && keyCode == 311) {
            Log.i(TAG, "Benz_NTG6 onKeyDown: setNextFocusDownId appsBtn " + keyCode);
            try {
                this.bcMainActivity.appsBtn.setFocusable(true);
                this.bcMainActivity.appsBtn.setFocusableInTouchMode(true);
                this.bcMainActivity.appsBtn.requestFocus();
                this.bcMainActivity.appsBtn.setFocusableInTouchMode(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, " onKeyDown " + keyCode);
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
