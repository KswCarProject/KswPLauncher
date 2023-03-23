package com.wits.ksw;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
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
import com.txznet.weatherquery.WeatherQueryManager;
import com.wits.ksw.databinding.ActivityBmwNbtBinding;
import com.wits.ksw.databinding.ActivityGsId8LauncherMainBinding;
import com.wits.ksw.databinding.ActivityId8LauncherMainBinding;
import com.wits.ksw.databinding.ActivityMainAlsId6Binding;
import com.wits.ksw.databinding.ActivityMainAlsId7Binding;
import com.wits.ksw.databinding.ActivityMainAlsId7V2Binding;
import com.wits.ksw.databinding.ActivityMainAudiBinding;
import com.wits.ksw.databinding.ActivityMainBcBinding;
import com.wits.ksw.databinding.ActivityMainBenzGsBinding;
import com.wits.ksw.databinding.ActivityMainBenzMbuxBinding;
import com.wits.ksw.databinding.ActivityMainBenzNtg5Binding;
import com.wits.ksw.databinding.ActivityMainGsug2Binding;
import com.wits.ksw.databinding.ActivityMainGsugBinding;
import com.wits.ksw.databinding.ActivityMainId6GsBinding;
import com.wits.ksw.databinding.ActivityMainKswmbuxBinding;
import com.wits.ksw.databinding.ActivityMainLexusBinding;
import com.wits.ksw.databinding.ActivityRomeoBinding;
import com.wits.ksw.databinding.AlsId7UiMainBinding;
import com.wits.ksw.databinding.AudiMib3FyMainLayoutBinding;
import com.wits.ksw.databinding.AudiMib3MainLayoutBinding;
import com.wits.ksw.databinding.AudiMib3TyMainLayoutBinding;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding2;
import com.wits.ksw.databinding.BenzMbux2021KswActivityBinding;
import com.wits.ksw.databinding.BenzNtg6FyBindCls;
import com.wits.ksw.databinding.CarInfoDataBinding;
import com.wits.ksw.databinding.CarInfoDataGsBinding;
import com.wits.ksw.databinding.DashboardDataBinding;
import com.wits.ksw.databinding.DashboardDataGsBinding;
import com.wits.ksw.databinding.ID5MaindBind;
import com.wits.ksw.databinding.LandroverMainBinding;
import com.wits.ksw.databinding.LexusLsDragMainLayoutBinding;
import com.wits.ksw.databinding.LexusLsMainLayoutBinding;
import com.wits.ksw.databinding.MainKswID7Binding;
import com.wits.ksw.databinding.ModusDataBinding;
import com.wits.ksw.databinding.ModusDataGsBinding;
import com.wits.ksw.databinding.MusicDataBinding;
import com.wits.ksw.databinding.MusicDataGsBinding;
import com.wits.ksw.databinding.NavigateDataBinding;
import com.wits.ksw.databinding.PhoneDataBinding;
import com.wits.ksw.databinding.PhoneDataGsBinding;
import com.wits.ksw.databinding.VideoDataBinding;
import com.wits.ksw.databinding.VideoDataGsBinding;
import com.wits.ksw.databinding.WeatherDataBinding;
import com.wits.ksw.databinding.WeatherDataGsBinding;
import com.wits.ksw.launcher.adpater.BMWViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BMWViewPagerAdpaterV2;
import com.wits.ksw.launcher.adpater.BenzGsHomePagerAdpater;
import com.wits.ksw.launcher.adpater.BenzMbuxRecyclerViewAdpater;
import com.wits.ksw.launcher.adpater.BenzNTG5RecyclerViewAdapter;
import com.wits.ksw.launcher.adpater.BenzNTG6RecyclerViewAdpater;
import com.wits.ksw.launcher.adpater.BmwId5ViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BmwId6CuspViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BmwId6ViewPagerAdpater;
import com.wits.ksw.launcher.adpater.BmwId6gSHomePagerAdpater;
import com.wits.ksw.launcher.adpater.KSWID7ViewPagerAdapter;
import com.wits.ksw.launcher.adpater.KSWMBUXHomePagerAdpater;
import com.wits.ksw.launcher.adpater.RomeoMainListAdapter;
import com.wits.ksw.launcher.adpater.UgHomePagerAdpater;
import com.wits.ksw.launcher.als_id7.adapter.BMWId7ViewPagerAdpater;
import com.wits.ksw.launcher.als_id7.adapter.BMWId7ViewPagerAdpaterV2;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.als_id7_ui.adapter.AlsId7UiViewPagerAdapter;
import com.wits.ksw.launcher.base.BaseThemeActivity;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.bmw_id8_ui.GSID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8HorizontalScrollView;
import com.wits.ksw.launcher.id7_new.Id7NewActivity;
import com.wits.ksw.launcher.land_rover.adapter.LandRoverViewPagerAdpater;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;
import com.wits.ksw.launcher.land_rover.view.LandroverPopWindow;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.model.BcNTG5ViewModel;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.BwmNbtModel;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.model.McuImpl;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.launcher.model.RomeoViewModel;
import com.wits.ksw.launcher.pagerlayoutmanager.PagerGridLayoutManager;
import com.wits.ksw.launcher.pagerlayoutmanager.PagerGridSnapHelper;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.launcher.utils.BitmapUtil;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.DisplayUtil;
import com.wits.ksw.launcher.utils.FixLinearSnapHelper;
import com.wits.ksw.launcher.utils.IconUtils;
import com.wits.ksw.launcher.utils.KeyUtils;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.launcher.view.audimib3.AudiMib3FragmentOne;
import com.wits.ksw.launcher.view.audimib3.AudiMib3FragmentTwo;
import com.wits.ksw.launcher.view.audimib3.AudiMib3ViewPagerAdapter;
import com.wits.ksw.launcher.view.audimib3fy.AudiMib3FyFragmentOne;
import com.wits.ksw.launcher.view.audimib3fy.AudiMib3FyFragmentTwo;
import com.wits.ksw.launcher.view.audimib3fy.AudiMib3FyViewPagerAdapter;
import com.wits.ksw.launcher.view.audimib3fyv2.AudiMib3FyV2FragmentOne;
import com.wits.ksw.launcher.view.audimib3fyv2.AudiMib3FyV2FragmentTwo;
import com.wits.ksw.launcher.view.audimib3fyv2.AudiMib3FyV2ViewPagerAdapter;
import com.wits.ksw.launcher.view.audimib3ty.AudiMib3TyFragmentOne;
import com.wits.ksw.launcher.view.audimib3ty.AudiMib3TyFragmentTwo;
import com.wits.ksw.launcher.view.audimib3ty.AudiMib3TyViewPagerAdapter;
import com.wits.ksw.launcher.view.audimib3v2.AudiMib3FragmentOneV2;
import com.wits.ksw.launcher.view.audimib3v2.AudiMib3FragmentTwoV2;
import com.wits.ksw.launcher.view.audimib3v2.AudiMib3ViewPagerAdapterV2;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;
import com.wits.ksw.launcher.view.benzmbux2021.Benz2021DialogThemeActivity;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021Configs;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021RecyclerViewAdpater;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021ViewPagerAdapter;
import com.wits.ksw.launcher.view.benzmbux2021ksw.Benz2021KswDialogThemeActivity;
import com.wits.ksw.launcher.view.benzmbux2021ksw.adapter.BenzMbux2021KswViewPagerAdapter;
import com.wits.ksw.launcher.view.benzmbux2021ksw.bean.BenzMbux2021KswConfigs;
import com.wits.ksw.launcher.view.benzmbux2021kswv2.adapter.BenzMbux2021KswV2ViewPagerAdapter;
import com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyConfigs;
import com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyThemeActivity;
import com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyViewPagerAdapter;
import com.wits.ksw.launcher.view.benzntg6fyv2.BenzNtg6FyViewPagerAdapterV2;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;
import com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwo;
import com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwoV2;
import com.wits.ksw.launcher.view.lexusls.LexusLsConfig;
import com.wits.ksw.launcher.view.lexusls.adapter.LexusLsBottomViewPagerAdpater;
import com.wits.ksw.launcher.view.lexusls.adapter.LexusLsBottomViewPagerAdpaterV2;
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
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.ksw.settings.utlis_view.ToastUtils;
import com.wits.pms.ICmdListener;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.MusicStatus;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsStatus;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseThemeActivity {
    private static final int CLICK_TIME = 1500;
    private static final int MSG_HIDE_POP = 1;
    private static final int MSG_SHOW_POP = 0;
    /* access modifiers changed from: private */
    public static final String TAG = ("KswApplication." + MainActivity.class.getSimpleName());
    public static AlsId7UiMainBinding alsId7UiMainBinding;
    public static AudiMib3MainLayoutBinding audiMib3Binding;
    public static AudiMib3FyV2ViewPagerAdapter audiMib3FyV2ViewPagerAdapter;
    public static AudiMib3FyViewPagerAdapter audiMib3FyViewPagerAdapter;
    public static AudiMib3TyMainLayoutBinding audiMib3TyMainLayoutBinding;
    public static AudiMib3TyViewPagerAdapter audiMib3TyViewPagerAdapter;
    public static AudiMib3ViewPagerAdapter audiMib3ViewPagerAdapter;
    public static AudiMib3ViewPagerAdapterV2 audiMib3ViewPagerAdapterv2;
    public static BenzMbux2021ActivityBinding2 benzMbux2021Binding2;
    public static BenzMbux2021KswActivityBinding benzMbux2021KswActivityBinding;
    public static BenzMbux2021KswV2ViewPagerAdapter benzMbux2021KswV2ViewPagerAdapter;
    public static BenzMbux2021KswViewPagerAdapter benzMbux2021KswViewPagerAdapter;
    public static BenzMbux2021ViewPagerAdapter benzMbux2021ViewPagerAdapter;
    public static BenzNtg6FyBindCls benzNtg6FyBinding;
    public static BenzNtg6FyViewPagerAdapter benzNtg6FyViewPagerAdapter;
    public static BenzNtg6FyViewPagerAdapterV2 benzNtg6FyViewPagerAdapterV2;
    private static final int[] bgIconRes = {R.drawable.mbux2_coat_set_icon2_1, R.drawable.mbux2_coat_set_icon2_2, R.drawable.mbux2_coat_set_icon2_3, R.drawable.mbux2_coat_set_icon2_4, R.drawable.mbux2_coat_set_icon2_5, R.drawable.mbux2_coat_set_icon2_6, R.drawable.mbux2_coat_set_icon2_7};
    private static final int[] bgRes = {R.drawable.mbux2_bg_main_2_1, R.drawable.mbux2_bg_main_2_2, R.drawable.mbux2_bg_main_2_3, R.drawable.mbux2_bg_main_2_4, R.drawable.mbux2_bg_main_2_5, R.drawable.mbux2_bg_main_2_6, R.drawable.mbux2_bg_main_2_7};
    /* access modifiers changed from: private */
    public static boolean isFront = false;
    private static long lastTimeClick;
    private static final int lineWidth = 0;
    private static ArrayList<LexusLsAppSelBean> mAllAppList = new ArrayList<>();
    public static BcVieModel mAudiMbi3BcViewModel;
    public static BcVieModel mAudiMbi3FyBcViewModel;
    public static BcVieModel mAudiMib3TyBcViewModel;
    public static BcVieModel mBcVieModel;
    /* access modifiers changed from: private */
    public static ArrayList<LexusLsAppSelBean> mCheckedAppList = new ArrayList<>();
    /* access modifiers changed from: private */
    public static ArrayList<LexusLsAppSelBean> mCheckedAppList_V2 = new ArrayList<>();
    public static int mColumn = 0;
    public static int mRow = 0;
    public static MainActivity mainActivity;
    public static AudiMib3FyMainLayoutBinding mib3FyMainLayoutBinding;
    public static boolean settingLanguage = false;
    /* access modifiers changed from: private */
    public LexusLsDragItemAdapter adapterLexusLsDrag;
    private ActivityMainAlsId6Binding alsBinding;
    /* access modifiers changed from: private */
    public AlsID7ViewModel alsID7ViewModel;
    /* access modifiers changed from: private */
    public ActivityMainAlsId7Binding alsId7Binding;
    /* access modifiers changed from: private */
    public LauncherViewModel alsId7UiViewModel;
    /* access modifiers changed from: private */
    public ActivityMainAlsId7V2Binding alsId7V2Binding;
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
    public ViewPager audiMib3TyViewPager;
    public ActivityMainBcBinding bcMainActivity;
    /* access modifiers changed from: private */
    public BenzMbux2021ActivityBinding benzMbux2021Binding;
    private String bgFyIndex = "8";
    private String bgIndex = TxzMessage.TXZ_SHOW;
    private String bgIndexKsw = TxzMessage.TXZ_SHOW;
    public com.wits.ksw.databinding.MainActivity bmwBinding;
    private BmwId6CuspViewPagerAdpater bmwId6CuspViewPagerAdpater;
    private BmwId6ViewPagerAdpater bmwId6ViewPagerAdpater;
    /* access modifiers changed from: private */
    public ActivityGsId8LauncherMainBinding bmwId8GsUiMainLayoutBinding;
    /* access modifiers changed from: private */
    public ActivityId8LauncherMainBinding bmwId8UiMainLayoutBinding;
    /* access modifiers changed from: private */
    public LauncherViewModel bmwId8ViewModel;
    private double cardLeftScroll = 0.7d;
    private double cardRightScroll = 0.3d;
    /* access modifiers changed from: private */
    public int cardWith;
    /* access modifiers changed from: private */
    public int currentOldScrollX;
    /* access modifiers changed from: private */
    public int currentScrollX;
    /* access modifiers changed from: private */
    public int currentScrollY;
    public int dialogHeight;
    public int dialogWidth;
    DragController.DraggingListener draggingListener = new DragController.DraggingListener() {
        public void onDragStart(DragSource source, Object info, int dragAction) {
            LexusLsAppSelBean sourceItem = ((DraggableLayout) source).getItem();
            if (UiThemeUtils.isLEXUS_LS_UI(MainActivity.mainActivity)) {
                if (LexusLsConfig.isMenu(sourceItem.getAppPkg())) {
                    LOGE.E("----------GONE-------------->");
                    MainActivity.this.mDeleteZone.setVisibility(8);
                    return;
                }
                LOGE.E("-----------VISIBLE------------->");
                MainActivity.this.mDeleteZone.setVisibility(0);
            } else if (LexusLsConfig.isMenu_V2(sourceItem.getAppPkg())) {
                LOGE.E("----------GONE-------------->");
                MainActivity.this.mDeleteZone.setVisibility(8);
            } else {
                LOGE.E("-----------VISIBLE------------->");
                MainActivity.this.mDeleteZone.setVisibility(0);
            }
        }

        public void onDragEnd() {
            LOGE.E("-----------onDragEnd------------->");
            MainActivity.this.mDeleteZone.setVisibility(8);
        }
    };
    private List<String> gsId8CacheCardSeq = new ArrayList();
    public ActivityMainGsug2Binding gsug2Binding;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int unused = MainActivity.this.lastX = ((View) msg.obj).getScrollX();
            MainActivity mainActivity = MainActivity.this;
            int unused2 = mainActivity.cardWith = mainActivity.bmwId8GsUiMainLayoutBinding.llContaine.getChildAt(0).getWidth();
            if (msg.what != MainActivity.this.touchEventId) {
                return;
            }
            if (MainActivity.this.currentScrollX > MainActivity.this.currentOldScrollX) {
                MainActivity mainActivity2 = MainActivity.this;
                mainActivity2.scrollViewRightStop(mainActivity2.lastX);
                return;
            }
            MainActivity mainActivity3 = MainActivity.this;
            mainActivity3.scrollViewLeftStop(mainActivity3.lastX);
        }
    };
    private HorizontalPageLayoutManager horizhontalPageLayoutManager;
    LexusLsDragItemAdapter.IOnAddAppClickListener iOnAddAppClickListener = new LexusLsDragItemAdapter.IOnAddAppClickListener() {
        public void onClick(View view) {
            MainActivity.this.initPopuWindow(view);
        }
    };
    public ID5MaindBind id5MaindBind;
    public ViewPager id6CuspMainViewPager;
    ImageView id6LeftBtn;
    public ViewPager id6MainViewPager;
    ImageView id6RightBtn;
    private List<String> id8CacheCardSeq = new ArrayList();
    private String id8CacheLeftIcon2;
    private String id8CacheLeftIcon3;
    private String id8CacheLeftIcon4;
    private String id8CacheLeftIcon5;
    boolean id8CardsContainerFirstChildHasFocus;
    View.OnLongClickListener id8OnLongClickListener = new View.OnLongClickListener() {
        public boolean onLongClick(View view) {
            if (UiThemeUtils.isBMW_ID8_UI(view.getContext())) {
                MainActivity.this.bmwId8ViewModel.openID8Edit(view);
                return false;
            }
            MainActivity.this.bmwId8ViewModel.openID8GsEdit(view);
            return false;
        }
    };
    /* access modifiers changed from: private */
    public int index = 0;
    private ImageView[] indicatorPoints;
    /* access modifiers changed from: private */
    public boolean isEnableDrag = true;
    private String isFirstLoad = "true";
    private String isFirstLoad_V2 = "true";
    private boolean isID8FirstStart = true;
    /* access modifiers changed from: private */
    public boolean isStop = false;
    private boolean isVideoStop = false;
    LexusLsDragItemAdapter.ItemDragListener itemDragListener = new LexusLsDragItemAdapter.ItemDragListener() {
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
                        if (UiThemeUtils.isLEXUS_LS_UI(MainActivity.mainActivity)) {
                            LexusLsConfig.mAppList.remove(sourceItem);
                        } else {
                            LexusLsConfig.mAppList_V2.remove(sourceItem);
                        }
                        MainActivity.this.updateHideAndShowAddMenu();
                        MainActivity.this.refreshItemList();
                        MainActivity.this.adapterLexusLsDrag.notifyDataSetChanged();
                        LexusLsConfig.saveDataOrder(MainActivity.mainActivity);
                        if (UiThemeUtils.isLEXUS_LS_UI(MainActivity.mainActivity)) {
                            boolean contains = LexusLsConfig.mAppList.contains(sourceItem);
                        } else {
                            boolean contains2 = LexusLsConfig.mAppList_V2.contains(sourceItem);
                        }
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
    };
    /* access modifiers changed from: private */
    public MainKswID7Binding kswId7Binding;
    /* access modifiers changed from: private */
    public KSWID7ViewPagerAdapter kswid7ViewPagerAdapter;
    public ActivityMainKswmbuxBinding kswmbuxBinding;
    private KSWMBUXHomePagerAdpater kswmbuxHomePagerAdpater;
    /* access modifiers changed from: private */
    public LandroverMainBinding landroverBinding;
    /* access modifiers changed from: private */
    public LandroverHandler landroverHandler = null;
    private LandroverViewModel landroverViewModel;
    private boolean lastLeft = false;
    private boolean lastRight = true;
    /* access modifiers changed from: private */
    public int lastX = 0;
    private ActivityMainLexusBinding lexusBinding;
    private LexusLsBottomViewPagerAdpater lexusLsBottomViewPagerAdpater;
    private LexusLsBottomViewPagerAdpaterV2 lexusLsBottomViewPagerAdpaterV2;
    LexusLsDragMainLayoutBinding lexusLsDragBinding;
    /* access modifiers changed from: private */
    public LexusLsSelAppAdapter lexusLsSelAppAdapter;
    private LexusLsTopAppAdapter lexusLsTopAppAdapter;
    public ViewPager lexusLsVpagerBottom;
    private RecyclerView lexus_ls_add_app_recycleview;
    private TextView lexus_ls_addapp_iv_cancel;
    private TextView lexus_ls_addapp_iv_ok;
    private RecyclerView lexus_ls_recycleview_desktop;
    private View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
        public void onFocusChange(View view, boolean b) {
            Log.d(MainActivity.TAG, "onFocusChange: view.id=" + view.getId());
        }
    };
    private View.OnClickListener mAudiMib3FyItemClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            MainActivity.mAudiMbi3FyBcViewModel.addLastViewFocused(v);
            switch (v.getId()) {
                case R.id.iv_bt_audimib3:
                    MainActivity.mAudiMbi3FyBcViewModel.openBtApp(v);
                    return;
                case R.id.iv_car_audimib3:
                    MainActivity.mAudiMbi3FyBcViewModel.openCar(v);
                    return;
                case R.id.iv_music_audimib3:
                    MainActivity.mAudiMbi3FyBcViewModel.openChoseMusic(v);
                    return;
                case R.id.iv_navi_audimib3:
                    MainActivity.mAudiMbi3FyBcViewModel.openNaviApp(v);
                    return;
                case R.id.iv_set_audimib3:
                    MainActivity.mAudiMbi3FyBcViewModel.openSettings(v);
                    return;
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mAudiMib3ItemClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            MainActivity.mAudiMbi3BcViewModel.addLastViewFocused(v);
            switch (v.getId()) {
                case R.id.iv_bt_audimib3:
                    MainActivity.mAudiMbi3BcViewModel.openBtApp(v);
                    return;
                case R.id.iv_car_audimib3:
                    MainActivity.mAudiMbi3BcViewModel.openCar(v);
                    return;
                case R.id.iv_music_audimib3:
                    MainActivity.mAudiMbi3BcViewModel.openChoseMusic(v);
                    return;
                case R.id.iv_navi_audimib3:
                    MainActivity.mAudiMbi3BcViewModel.openNaviApp(v);
                    return;
                case R.id.iv_set_audimib3:
                    MainActivity.mAudiMbi3BcViewModel.openSettings(v);
                    return;
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mAudiMib3TyItemClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            MainActivity.mAudiMib3TyBcViewModel.addLastViewFocused(v);
            switch (v.getId()) {
                case R.id.iv_bt_audimib3:
                    MainActivity.mAudiMib3TyBcViewModel.openBtApp(v);
                    return;
                case R.id.iv_car_audimib3:
                    MainActivity.mAudiMib3TyBcViewModel.openCar(v);
                    return;
                case R.id.iv_music_audimib3:
                    MainActivity.mAudiMib3TyBcViewModel.openChoseMusic(v);
                    return;
                case R.id.iv_navi_audimib3:
                    MainActivity.mAudiMib3TyBcViewModel.openNaviApp(v);
                    return;
                case R.id.iv_set_audimib3:
                    MainActivity.mAudiMib3TyBcViewModel.openSettings(v);
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
                        if (UiThemeUtils.isLEXUS_LS_UI(MainActivity.mainActivity)) {
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
                            } else {
                                return;
                            }
                        } else {
                            ArrayList unused2 = MainActivity.mCheckedAppList_V2 = (ArrayList) MainActivity.this.lexusLsSelAppAdapter.getCheckBoxIDList();
                            if (MainActivity.mCheckedAppList_V2 != null && MainActivity.mCheckedAppList_V2.size() > 0) {
                                Log.e("liuhao", "mCheckedAppList_V2 size = " + MainActivity.mCheckedAppList_V2.size());
                                for (int i2 = 0; i2 < MainActivity.mCheckedAppList_V2.size(); i2++) {
                                    Log.e("liuhao", "mCheckedAppList_V2 size i = " + i2 + " appName = " + ((LexusLsAppSelBean) MainActivity.mCheckedAppList_V2.get(i2)).getAppName());
                                }
                                ArrayList<LexusLsAppSelBean> tmpList2 = new ArrayList<>();
                                tmpList2.addAll(LexusLsConfig.mAppList_V2);
                                List<LexusLsAppSelBean> shengyuList2 = LexusLsConfig.getRemoveListResult(MainActivity.mainActivity, tmpList2, LexusLsConfig.getMenuAppList_V2(MainActivity.mainActivity));
                                if (shengyuList2 != null && shengyuList2.size() > 0) {
                                    LOGE.E("removeList size = " + shengyuList2.size());
                                    LexusLsConfig.getRemoveListResult(MainActivity.mainActivity, LexusLsConfig.mAppList_V2, shengyuList2);
                                }
                            } else {
                                return;
                            }
                        }
                        if (UiThemeUtils.isLEXUS_LS_UI(MainActivity.mainActivity)) {
                            Iterator it = MainActivity.mCheckedAppList.iterator();
                            while (it.hasNext()) {
                                LexusLsAppSelBean bean = (LexusLsAppSelBean) it.next();
                                if (!LexusLsConfig.mAppList.contains(bean) && LexusLsConfig.mAppList.size() <= MainActivity.this.pageSize * 2) {
                                    LexusLsConfig.mAppList.add(bean);
                                }
                            }
                        } else {
                            Iterator it2 = MainActivity.mCheckedAppList_V2.iterator();
                            while (it2.hasNext()) {
                                LexusLsAppSelBean bean2 = (LexusLsAppSelBean) it2.next();
                                if (!LexusLsConfig.mAppList_V2.contains(bean2) && LexusLsConfig.mAppList_V2.size() <= MainActivity.this.pageSize * 2) {
                                    LexusLsConfig.mAppList_V2.add(bean2);
                                }
                            }
                        }
                        MainActivity.this.updateHideAndShowAddMenu();
                        MainActivity.this.refreshItemList();
                        MainActivity.this.adapterLexusLsDrag.notifyDataSetChanged();
                        if (UiThemeUtils.isLEXUS_LS_UI(MainActivity.mainActivity)) {
                            LexusLsConfig.saveDataOrder(MainActivity.mainActivity);
                            return;
                        } else {
                            LexusLsConfig.saveDataOrder_V2(MainActivity.mainActivity);
                            return;
                        }
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public DragController mDragController;
    /* access modifiers changed from: private */
    public DragLayer mDragLayer;
    private IContentObserver.Stub mId8GsMusicObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            Log.i(MainActivity.TAG, "onChange: mMusicObserver");
            if (MainActivity.this.bmwId8ViewModel != null) {
                try {
                    boolean unused = MainActivity.this.isStop = PowerManagerApp.getManager().getStatusBoolean("music_stop");
                    if (MainActivity.this.isStop) {
                        Log.i(MainActivity.TAG, "updateData: mMusicObserver isMusicStop=" + MainActivity.this.isStop);
                        MainActivity.this.bmwId8ViewModel.setMusicPlayState(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private IContentObserver.Stub mMusicObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            Log.i(MainActivity.TAG, "onChange: mMusicObserver");
            if (MainActivity.this.alsID7ViewModel != null) {
                try {
                    boolean unused = MainActivity.this.isStop = PowerManagerApp.getManager().getStatusBoolean("music_stop");
                    if (MainActivity.this.isStop) {
                        Log.i(MainActivity.TAG, "updateData: mMusicObserver isMusicStop=" + MainActivity.this.isStop);
                        MainActivity.this.alsID7ViewModel.setMusicPlayState(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private View.OnClickListener mOnClickListener_AlsId7 = new View.OnClickListener() {
        public void onClick(View v) {
            MainActivity.this.alsID7ViewModel.addLastViewFocused(v);
            switch (v.getId()) {
                case R.id.btn_apps:
                    Log.e("isSelected1", v.isSelected() + "");
                    MainActivity.this.alsID7ViewModel.openApps(v);
                    return;
                case R.id.btn_settings:
                    Log.e("isSelected2", v.isSelected() + "");
                    MainActivity.this.alsID7ViewModel.openSettings(v);
                    return;
                case R.id.btn_shut_1:
                    MainActivity.this.alsID7ViewModel.openShortCutApp(v, 1);
                    return;
                case R.id.btn_shut_2:
                    Log.e("isSelected", v.isSelected() + "");
                    MainActivity.this.alsID7ViewModel.openShortCutApp(v, 2);
                    return;
                case R.id.btn_shut_3:
                    MainActivity.this.alsID7ViewModel.openShortCutApp(v, 3);
                    return;
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mOnClickListener_AlsId7Ui = new View.OnClickListener() {
        public void onClick(View v) {
            MainActivity.this.alsId7UiViewModel.addLastViewFocused(v);
            switch (v.getId()) {
                case R.id.menu_button1:
                    MainActivity.this.alsId7UiViewModel.openShortCutApp(v, 1);
                    return;
                case R.id.menu_button2:
                    MainActivity.this.alsId7UiViewModel.openShortCutApp(v, 2);
                    return;
                case R.id.menu_button3:
                    MainActivity.this.alsId7UiViewModel.openSettings(v);
                    return;
                case R.id.menu_button4:
                    MainActivity.this.alsId7UiViewModel.openCar(v);
                    return;
                case R.id.menu_button5:
                    MainActivity.this.alsId7UiViewModel.openApps(v);
                    return;
                default:
                    return;
            }
        }
    };
    private View.OnLongClickListener mOnLongListener_AlsId7 = new View.OnLongClickListener() {
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.btn_shut_1:
                    MainActivity.this.alsID7ViewModel.initPopWinowGridApps(v, 1);
                    return true;
                case R.id.btn_shut_2:
                    MainActivity.this.alsID7ViewModel.initPopWinowGridApps(v, 2);
                    return true;
                case R.id.btn_shut_3:
                    MainActivity.this.alsID7ViewModel.initPopWinowGridApps(v, 3);
                    return true;
                default:
                    return false;
            }
        }
    };
    private View.OnLongClickListener mOnLongListener_AlsId7Ui = new View.OnLongClickListener() {
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.menu_button1:
                    MainActivity.this.alsId7UiViewModel.initPopWinowGridApps(v, 1);
                    return true;
                case R.id.menu_button2:
                    MainActivity.this.alsId7UiViewModel.initPopWinowGridApps(v, 2);
                    return true;
                default:
                    return false;
            }
        }
    };
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
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 1);
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_2:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 2);
                    BenzMbux2021Configs.bg_sel = 2;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_3:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 3);
                    BenzMbux2021Configs.bg_sel = 3;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_4:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 4);
                    BenzMbux2021Configs.bg_sel = 4;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_5:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 5);
                    BenzMbux2021Configs.bg_sel = 5;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_6:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 6);
                    BenzMbux2021Configs.bg_sel = 6;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_benz_mbux_2021_coat_bg_7:
                    Settings.System.putInt(MainActivity.mainActivity.getContentResolver(), "BG_SAVE", 7);
                    BenzMbux2021Configs.bg_sel = 7;
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackgroundResource(MainActivity.benzMbux2021Binding2.benzMbux2021Viewpager.getCurrentItem() == 0 ? BenzMbux2021Configs.BG_ONE[BenzMbux2021Configs.bg_sel - 1] : BenzMbux2021Configs.BG_OTHER[BenzMbux2021Configs.bg_sel - 1]);
                    return;
                case R.id.rb_icon_sel_classical:
                    MainActivity.this.tv_icon_sel_modern.setTextColor(MainActivity.this.getColor(R.color.color1));
                    MainActivity.this.tv_icon_sel_classical.setTextColor(MainActivity.this.getColor(R.color.rb_text_color));
                    MainActivity.this.rb_icon_sel_modern.setSelected(false);
                    MainActivity.this.rb_icon_sel_classical.setSelected(true);
                    MainActivity.mBcVieModel.itemIconClassical.set(true);
                    return;
                case R.id.rb_icon_sel_modern:
                    MainActivity.this.tv_icon_sel_modern.setTextColor(MainActivity.this.getColor(R.color.rb_text_color));
                    MainActivity.this.tv_icon_sel_classical.setTextColor(MainActivity.this.getColor(R.color.color1));
                    MainActivity.this.rb_icon_sel_classical.setSelected(false);
                    MainActivity.this.rb_icon_sel_modern.setSelected(true);
                    MainActivity.mBcVieModel.itemIconClassical.set(false);
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
    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
        }
    };
    private OnID8SkinChangeListener onGsID8SkinChangeListener;
    private OnID8SkinChangeListener onID8SkinChangeListener;
    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        public boolean onLongClick(View view) {
            if (!view.isInTouchMode() && MainActivity.this.isEnableDrag) {
                return false;
            }
            LOGE.E("onLongClick ********************* Drag started");
            DragSource dragSource = (DragSource) view;
            MainActivity.this.mDragController.startDragBitmap(view, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
            return true;
        }
    };
    ScrollController.OnPageChangeListener onPageChangeListener = new ScrollController.OnPageChangeListener() {
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
    };
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
    private PopupWindow skinPopupWindow;
    private String styleIndex = TxzMessage.TXZ_SHOW;
    private String styleIndexKsw = TxzMessage.TXZ_SHOW;
    /* access modifiers changed from: private */
    public HashMap<String, View> systemCardViewHashMap;
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
    public int touchEventId = 1001;
    /* access modifiers changed from: private */
    public HashMap<String, View> trdCardViewHashMap;
    /* access modifiers changed from: private */
    public TextView tv_icon_sel_classical;
    /* access modifiers changed from: private */
    public TextView tv_icon_sel_modern;
    public ActivityMainGsugBinding ugBinding;
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
    private LauncherViewModel viewModel;
    public ViewPager viewPagerBenzMbux2021;
    public ViewPager viewPagerBenzMbux2021Ksw;
    public ViewPager viewpagerAudiMib3;
    public ViewPager viewpagerAudiMib3Fy;
    public ViewPager viewpagerBenzNtg6Fy;

    public void setAlsButtonBg(String skinType) {
        if (alsId7UiMainBinding == null) {
            return;
        }
        if (ID8LauncherConstants.ID8_SKIN_PERSONAL.equals(skinType)) {
            alsId7UiMainBinding.menuButton1.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_yellow));
            alsId7UiMainBinding.menuButton2.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_yellow));
            alsId7UiMainBinding.menuButton3.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_yellow));
            alsId7UiMainBinding.menuButton4.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_yellow));
            alsId7UiMainBinding.menuButton5.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_yellow));
            alsId7UiMainBinding.itemViewSkinDefault.setImageResource(R.drawable.als_id7_ui_indicato_skin_yellow_selector);
        } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinType)) {
            alsId7UiMainBinding.menuButton1.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_red));
            alsId7UiMainBinding.menuButton2.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_red));
            alsId7UiMainBinding.menuButton3.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_red));
            alsId7UiMainBinding.menuButton4.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_red));
            alsId7UiMainBinding.menuButton5.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_red));
            alsId7UiMainBinding.itemViewSkinDefault.setImageResource(R.drawable.als_id7_ui_indicato_skin_red_selector);
        } else {
            alsId7UiMainBinding.menuButton1.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_blue));
            alsId7UiMainBinding.menuButton2.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_blue));
            alsId7UiMainBinding.menuButton3.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_blue));
            alsId7UiMainBinding.menuButton4.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_blue));
            alsId7UiMainBinding.menuButton5.setBackground(getResources().getDrawable(R.drawable.als_id7_ui_left_btn_blue));
            alsId7UiMainBinding.itemViewSkinDefault.setImageResource(R.drawable.als_id7_ui_indicato_skin_blue_selector);
        }
    }

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
        int index2 = this.id6MainViewPager.getCurrentItem() - 1;
        if (index2 >= 0) {
            this.id6MainViewPager.setCurrentItem(index2);
            if (index2 == 0) {
                this.bmwId6ViewPagerAdpater.fragmentID6One.setDefaultSelected();
            } else if (index2 == 1) {
                this.bmwId6ViewPagerAdpater.fragmentID6Two.setDefaultSelected();
            } else if (index2 == 2) {
                this.bmwId6ViewPagerAdpater.fragmentID6Three.setDefaultSelected();
            } else if (index2 == 3) {
                this.bmwId6ViewPagerAdpater.fragmentID6Four.setDefaultSelected();
            }
        }
    }

    public /* synthetic */ void lambda$initBmwid6UiView$1$MainActivity(View v) {
        int index2 = this.id6MainViewPager.getCurrentItem() + 1;
        if (index2 <= 3) {
            this.id6MainViewPager.setCurrentItem(index2);
            if (index2 == 0) {
                this.bmwId6ViewPagerAdpater.fragmentID6One.setDefaultSelected();
            } else if (index2 == 1) {
                this.bmwId6ViewPagerAdpater.fragmentID6Two.setDefaultSelected();
            } else if (index2 == 2) {
                this.bmwId6ViewPagerAdpater.fragmentID6Three.setDefaultSelected();
            } else if (index2 == 3) {
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
        int index2 = this.id6CuspMainViewPager.getCurrentItem() - 1;
        if (index2 >= 0) {
            this.id6CuspMainViewPager.setCurrentItem(index2);
            if (index2 == 0) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspOne.setDefaultSelected();
            } else if (index2 == 1) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspTwo.setDefaultSelected();
            } else if (index2 == 2) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspThree.setDefaultSelected();
            } else if (index2 == 3) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspFour.setDefaultSelected();
            }
        }
    }

    public /* synthetic */ void lambda$initBmwid6CuspUiView$3$MainActivity(View v) {
        int index2 = this.id6CuspMainViewPager.getCurrentItem() + 1;
        if (index2 <= 3) {
            this.id6CuspMainViewPager.setCurrentItem(index2);
            if (index2 == 0) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspOne.setDefaultSelected();
            } else if (index2 == 1) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspTwo.setDefaultSelected();
            } else if (index2 == 2) {
                this.bmwId6CuspViewPagerAdpater.fragmentID6CuspThree.setDefaultSelected();
            } else if (index2 == 3) {
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
    public void initUIKSWID7View() {
        Log.i(TAG, "initUIKSWID7View: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        MainKswID7Binding mainKswID7Binding = (MainKswID7Binding) DataBindingUtil.setContentView(this, R.layout.activity_main_ksw_id7);
        this.kswId7Binding = mainKswID7Binding;
        mainKswID7Binding.setLauncherViewModel(this.viewModel);
        this.viewModel.initData();
        this.kswid7ViewPagerAdapter = new KSWID7ViewPagerAdapter(getSupportFragmentManager());
        this.kswId7Binding.viewPage.setAdapter(this.kswid7ViewPagerAdapter);
        setCurrentItem(0);
        this.kswId7Binding.viewPage.setOffscreenPageLimit(2);
        this.kswId7Binding.ivBottomHome.setSelected(true);
        this.kswId7Binding.ivBottomPoint.setSelected(false);
        this.kswId7Binding.viewPage.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == 21 || event.getKeyCode() == 22) {
                    return true;
                }
                return false;
            }
        });
        this.kswId7Binding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int i) {
                boolean z = false;
                MainActivity.this.kswId7Binding.ivBottomHome.setSelected(i == 0);
                ImageView imageView = MainActivity.this.kswId7Binding.ivBottomPoint;
                if (i == 1) {
                    z = true;
                }
                imageView.setSelected(z);
            }

            public void onPageScrollStateChanged(int i) {
                switch (i) {
                    case 0:
                        MainActivity.this.kswid7ViewPagerAdapter.showArrow();
                        return;
                    case 1:
                        MainActivity.this.kswid7ViewPagerAdapter.hideArrow();
                        return;
                    default:
                        return;
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initBmwid7V2UiView() {
        Log.i(TAG, "initBmwid7V2UiView: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        com.wits.ksw.databinding.MainActivity mainActivity2 = (com.wits.ksw.databinding.MainActivity) DataBindingUtil.setContentView(this, R.layout.activity_main_bmw);
        this.bmwBinding = mainActivity2;
        mainActivity2.viewPage.setAdapter(new BMWViewPagerAdpaterV2(getSupportFragmentManager()));
        setCurrentItem(0);
        this.bmwBinding.viewPage.setOffscreenPageLimit(3);
        this.bmwBinding.imageView1.setSelected(false);
        this.bmwBinding.imageView3.setSelected(true);
        this.bmwBinding.imageView4.setSelected(false);
        this.bmwBinding.setLauncherViewModel(this.viewModel);
        this.viewModel.initData();
        this.viewModel.startWeatherLooper();
        addOnPageChangeListener();
    }

    public static boolean isQuickClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if (curClickTime - lastTimeClick >= 1500) {
            flag = true;
        }
        lastTimeClick = curClickTime;
        return flag;
    }

    /* access modifiers changed from: protected */
    public void initAlsId7UiView() {
        Log.i(TAG, "initAlsId7UiView: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.alsId7UiViewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        AlsId7UiMainBinding alsId7UiMainBinding2 = (AlsId7UiMainBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_als_id7_ui);
        alsId7UiMainBinding = alsId7UiMainBinding2;
        alsId7UiMainBinding2.viewPage.setAdapter(new AlsId7UiViewPagerAdapter(getSupportFragmentManager()));
        setCurrentItem(this.index);
        alsId7UiMainBinding.viewPage.setOffscreenPageLimit(3);
        alsId7UiMainBinding.imageView1.setSelected(false);
        alsId7UiMainBinding.imageView3.setSelected(true);
        alsId7UiMainBinding.imageView4.setSelected(false);
        alsId7UiMainBinding.setLauncherViewModel(this.alsId7UiViewModel);
        this.alsId7UiViewModel.initData();
        alsId7UiMainBinding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int i) {
                Log.d(MainActivity.TAG, "onPageSelected: i=" + i);
                int unused = MainActivity.this.index = i;
                switch (i) {
                    case 0:
                        MainActivity.alsId7UiMainBinding.imageView1.setSelected(false);
                        MainActivity.alsId7UiMainBinding.imageView3.setSelected(true);
                        MainActivity.alsId7UiMainBinding.imageView4.setSelected(false);
                        return;
                    case 1:
                        MainActivity.alsId7UiMainBinding.imageView1.setSelected(true);
                        MainActivity.alsId7UiMainBinding.imageView3.setSelected(false);
                        MainActivity.alsId7UiMainBinding.imageView4.setSelected(false);
                        return;
                    case 2:
                        MainActivity.alsId7UiMainBinding.imageView1.setSelected(false);
                        MainActivity.alsId7UiMainBinding.imageView3.setSelected(false);
                        MainActivity.alsId7UiMainBinding.imageView4.setSelected(true);
                        return;
                    default:
                        return;
                }
            }

            public void onPageScrollStateChanged(int i) {
                Log.d(MainActivity.TAG, "onPageScrollStateChanged: i=" + i);
            }
        });
        alsId7UiMainBinding.itemViewSkinDefault.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                Log.d(MainActivity.TAG, "onLongClick: alsId7UiMainBinding.skinLl.getVisibility()" + MainActivity.alsId7UiMainBinding.skinLl.getVisibility());
                if (MainActivity.isQuickClick()) {
                    if (MainActivity.alsId7UiMainBinding.skinLl.getVisibility() == 4) {
                        MainActivity.alsId7UiMainBinding.skinLl.setVisibility(0);
                    }
                    return false;
                }
                MainActivity mainActivity = MainActivity.this;
                ToastUtils.showToastShort(mainActivity, mainActivity.getResources().getString(R.string.als_id7_wranning));
                return true;
            }
        });
        alsId7UiMainBinding.blueIv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Settings.System.putString(KswApplication.appContext.getContentResolver(), KswApplication.SKIN_NAME, ID8LauncherConstants.ID8_SKIN_EFFICIENT);
                MainActivity.this.setAlsButtonBg(ID8LauncherConstants.ID8_SKIN_EFFICIENT);
                MainActivity.alsId7UiMainBinding.skinLl.setVisibility(4);
            }
        });
        alsId7UiMainBinding.yellowIv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Settings.System.putString(KswApplication.appContext.getContentResolver(), KswApplication.SKIN_NAME, ID8LauncherConstants.ID8_SKIN_PERSONAL);
                MainActivity.this.setAlsButtonBg(ID8LauncherConstants.ID8_SKIN_PERSONAL);
                MainActivity.alsId7UiMainBinding.skinLl.setVisibility(4);
            }
        });
        alsId7UiMainBinding.redIv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Settings.System.putString(KswApplication.appContext.getContentResolver(), KswApplication.SKIN_NAME, ID8LauncherConstants.ID8_SKIN_SPORT);
                MainActivity.this.setAlsButtonBg(ID8LauncherConstants.ID8_SKIN_SPORT);
                MainActivity.alsId7UiMainBinding.skinLl.setVisibility(4);
            }
        });
        LauncherViewModel.screenPixels.set(DisplayUtil.getRealScreenRelatedInformation(this));
        if (LauncherViewModel.screenPixels.get().contains(",")) {
            LauncherViewModel.screenWidth.set(Integer.valueOf(LauncherViewModel.screenPixels.get().split(",")[0]));
            LauncherViewModel.screenHeight.set(Integer.valueOf(LauncherViewModel.screenPixels.get().split(",")[1]));
        } else {
            LauncherViewModel.screenWidth.set(-1);
            LauncherViewModel.screenHeight.set(-1);
        }
        alsId7UiMainBinding.menuButton1.setOnLongClickListener(this.mOnLongListener_AlsId7Ui);
        alsId7UiMainBinding.menuButton2.setOnLongClickListener(this.mOnLongListener_AlsId7Ui);
        alsId7UiMainBinding.menuButton1.setOnClickListener(this.mOnClickListener_AlsId7Ui);
        alsId7UiMainBinding.menuButton2.setOnClickListener(this.mOnClickListener_AlsId7Ui);
        alsId7UiMainBinding.menuButton3.setOnClickListener(this.mOnClickListener_AlsId7Ui);
        alsId7UiMainBinding.menuButton4.setOnClickListener(this.mOnClickListener_AlsId7Ui);
        alsId7UiMainBinding.menuButton5.setOnClickListener(this.mOnClickListener_AlsId7Ui);
    }

    private boolean isTouchPointInView(View targetView, int currentX, int currentY) {
        if (targetView == null) {
            return false;
        }
        int[] localtion = new int[2];
        targetView.getLocationOnScreen(localtion);
        int left = localtion[0];
        int top = localtion[1];
        int right = targetView.getMeasuredWidth() + left;
        int bottom = targetView.getMeasuredHeight() + top;
        if (currentY < top || currentY > bottom || currentX < left || currentX > right) {
            return false;
        }
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                if (alsId7UiMainBinding != null && !isTouchPointInView(findViewById(R.id.skin_ll), (int) ev.getX(), (int) ev.getY())) {
                    Log.d(TAG, "dispatchTouchEvent: asdasdasd");
                    if (alsId7UiMainBinding.skinLl.getVisibility() == 0) {
                        alsId7UiMainBinding.skinLl.setVisibility(4);
                        break;
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private int[] calculatePopWindowPos(View anchorView, View contentView) {
        int[] windowPos = new int[2];
        int[] anchorLoc = new int[2];
        anchorView.getLocationOnScreen(anchorLoc);
        int anchorHeight = anchorView.getHeight();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;
        contentView.measure(0, 0);
        int windowHeight = contentView.getMeasuredHeight();
        int windowWidth = contentView.getMeasuredWidth();
        if ((screenHeight - anchorLoc[1]) - anchorHeight < windowHeight) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
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
        if (UiThemeUtils.isLEXUS_LS_UI(this) || UiThemeUtils.isLEXUS_LS_UI_V2(this)) {
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
        this.adapterLexusLsDrag.setOnClickAddApp(this.iOnAddAppClickListener);
        this.adapterLexusLsDrag.setLongClickListener(this.onLongClickListener);
        this.adapterLexusLsDrag.setItemDragListener(this.itemDragListener);
        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(mRow, mColumn, 0, this);
        this.horizhontalPageLayoutManager = horizontalPageLayoutManager;
        horizontalPageLayoutManager.setDragLayer(this.mDragLayer);
        this.recyclerview_drag.setLayoutManager(this.horizhontalPageLayoutManager);
        this.mScrollController.setUpRecycleView(this.recyclerview_drag);
        this.mScrollController.setOnPageChangeListener(this.onPageChangeListener);
        this.lexusLsDragBinding.ivLexusLsDragRightBtn.setOnFocusChangeListener(this.onFocusChangeListener);
        this.lexusLsDragBinding.ivLexusLsDragLeftBtn.setOnFocusChangeListener(this.onFocusChangeListener);
        this.mDragLayer.setDragView(this.recyclerview_drag);
        this.mDragLayer.setDragController(this.mDragController);
        this.mDragLayer.setDraggingListener(this.draggingListener);
        DeleteZone deleteZone = this.mDeleteZone;
        if (deleteZone != null) {
            deleteZone.setEnabled(true);
            this.mDragLayer.setDeleteZoneId(this.mDeleteZone.getId());
        }
        this.mDragController.setDraggingListener(this.mDragLayer);
        this.mDragController.setScrollController(this.mScrollController);
    }

    /* access modifiers changed from: protected */
    public void initLexusLsDragV2() {
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
        this.isFirstLoad_V2 = Settings.System.getString(mainActivity.getContentResolver(), LexusLsConfig.IS_FIRST_V2);
        LOGE.E("isFirstLoad_V2 = " + this.isFirstLoad_V2);
        if (TextUtils.equals(this.isFirstLoad_V2, "true") || (str = this.isFirstLoad_V2) == null) {
            LOGE.E("isFirstLoad  TextUtils.equals(isFirstLoad,\"true\") || isFirstLoad==null");
            LexusLsConfig.mAppList_V2.clear();
            LexusLsConfig.mAppList_V2.addAll(LexusLsConfig.getMenuAppList_V2(mainActivity));
        } else if (TextUtils.equals(str, "false")) {
            LexusLsConfig.AppPkgs_V2 = Settings.System.getString(mainActivity.getContentResolver(), LexusLsConfig.LEXUS_LS_SAVE_PKG_V2);
            LOGE.E("TextUtils.equals(isFirstLoad,\"false\")LexusLsConfig.AppPkgs_V2 : " + LexusLsConfig.AppPkgs_V2);
            if (LexusLsConfig.AppPkgs_V2 == null || LexusLsConfig.AppPkgs_V2.length() <= 0) {
                LOGE.E("ELSE   isFirstLoad_V2 = " + this.isFirstLoad_V2 + "\nLexusLsConfig.AppPkgs_V2 : " + LexusLsConfig.AppPkgs_V2);
                LexusLsConfig.mAppList_V2.addAll(LexusLsConfig.getMenuAppList_V2(mainActivity));
            } else {
                LexusLsConfig.mAppList_V2.clear();
                String[] pksArray = LexusLsConfig.AppPkgs_V2.split(";");
                for (int i = 0; i < pksArray.length; i++) {
                    new LexusLsAppSelBean();
                    if (LexusLsConfig.isMenu_V2(pksArray[i])) {
                        bean = LexusLsConfig.findBeanByPkg(pksArray[i], LexusLsConfig.getMenuAppList_V2(mainActivity));
                    } else {
                        bean = LexusLsConfig.findBeanByPkg(pksArray[i], (ArrayList) AppInfoUtils.findLexusLsAllApp(mainActivity));
                    }
                    LexusLsConfig.mAppList_V2.add(bean);
                }
            }
        }
        this.pageCount = (LexusLsConfig.mAppList_V2.size() / this.pageSize) + (LexusLsConfig.mAppList_V2.size() % this.pageSize == 0 ? 0 : 1);
        LexusLsDragItemAdapter lexusLsDragItemAdapter = new LexusLsDragItemAdapter(LexusLsConfig.mAppList_V2, this.viewModel, mainActivity);
        this.adapterLexusLsDrag = lexusLsDragItemAdapter;
        this.recyclerview_drag.setAdapter(lexusLsDragItemAdapter);
        this.recyclerview_drag.setItemAnimator(new DefaultItemAnimator());
        this.adapterLexusLsDrag.setOnClickAddApp(this.iOnAddAppClickListener);
        this.adapterLexusLsDrag.setLongClickListener(this.onLongClickListener);
        this.adapterLexusLsDrag.setItemDragListener(this.itemDragListener);
        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(mRow, mColumn, 0, this);
        this.horizhontalPageLayoutManager = horizontalPageLayoutManager;
        horizontalPageLayoutManager.setDragLayer(this.mDragLayer);
        this.recyclerview_drag.setLayoutManager(this.horizhontalPageLayoutManager);
        this.mScrollController.setUpRecycleView(this.recyclerview_drag);
        this.mScrollController.setOnPageChangeListener(this.onPageChangeListener);
        this.lexusLsDragBinding.ivLexusLsDragRightBtn.setOnFocusChangeListener(this.onFocusChangeListener);
        this.lexusLsDragBinding.ivLexusLsDragLeftBtn.setOnFocusChangeListener(this.onFocusChangeListener);
        this.mDragLayer.setDragView(this.recyclerview_drag);
        this.mDragLayer.setDragController(this.mDragController);
        this.mDragLayer.setDraggingListener(this.draggingListener);
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
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            Collections.swap(LexusLsConfig.mAppList, sourcePos, targetPos);
        } else {
            Collections.swap(LexusLsConfig.mAppList_V2, sourcePos, targetPos);
        }
        refreshItemList();
        this.adapterLexusLsDrag.notifyDataSetChanged();
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            LexusLsConfig.saveDataOrder(mainActivity);
        } else {
            LexusLsConfig.saveDataOrder_V2(mainActivity);
        }
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

    private void acControlStatus(ArrayList mlist, String pkg1, String pkg2) {
        int AcControl = Settings.System.getInt(getContentResolver(), KeyConfig.AC_CONTROL, 0);
        if (mlist.contains(LexusLsConfig.findBeanByPkg(pkg1, mlist)) && AcControl == 0) {
            mlist.remove(LexusLsConfig.findBeanByPkg(pkg1, mlist));
        } else if (!mlist.contains(LexusLsConfig.findBeanByPkg(pkg1, mlist)) && AcControl == 1) {
            LexusLsAppSelBean beanAC = new LexusLsAppSelBean();
            beanAC.setAppName(getResources().getString(R.string.lexus_ac).trim());
            beanAC.setAppIcon(getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_air));
            beanAC.setAppPkg(pkg2 + "." + 7);
            beanAC.setItemPos(mlist.size());
            beanAC.setDeletable(false);
            mlist.add(7, beanAC);
        }
    }

    /* access modifiers changed from: private */
    public void refreshItemList() {
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            if (LexusLsConfig.mAppList != null && LexusLsConfig.mAppList.size() > 0) {
                acControlStatus(LexusLsConfig.mAppList, LexusLsConfig.PKG_MENU_STRS[7], LexusLsConfig.PKG_DEFINED_MENU_LEXUSLS);
                for (int i = 0; i < LexusLsConfig.mAppList.size(); i++) {
                    LexusLsConfig.mAppList.get(i).setItemPos(i);
                }
            }
        } else if (LexusLsConfig.mAppList_V2 != null && LexusLsConfig.mAppList_V2.size() > 0) {
            acControlStatus(LexusLsConfig.mAppList_V2, LexusLsConfig.PKG_MENU_STRS_V2[7], LexusLsConfig.PKG_DEFINED_MENU_LEXUSLS_V2);
            for (int i2 = 0; i2 < LexusLsConfig.mAppList_V2.size(); i2++) {
                LexusLsConfig.mAppList_V2.get(i2).setItemPos(i2);
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
        this.lexusLsBottomViewPagerAdpaterV2 = new LexusLsBottomViewPagerAdpaterV2(getSupportFragmentManager());
        lexusLsBinding.lexusLsViewpagerBottom.setAdapter(this.lexusLsBottomViewPagerAdpater);
        lexusLsBinding.lexusLsViewpagerBottom.setAdapter(this.lexusLsBottomViewPagerAdpaterV2);
        lexusLsBinding.lexusLsViewpagerBottom.setCurrentItem(0);
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            this.lexusLsTopAppAdapter = new LexusLsTopAppAdapter(mainActivity, mCheckedAppList);
        } else {
            this.lexusLsTopAppAdapter = new LexusLsTopAppAdapter(mainActivity, mCheckedAppList_V2);
        }
        new PagerGridLayoutManager(2, 7, 1);
        lexusLsBinding.lexusLsRecycleviewDesktop.setAdapter(this.lexusLsTopAppAdapter);
        lexusLsBinding.lexusLsRecycleviewDesktop.setLayoutManager(new GridLayoutManager(mainActivity, 7));
        this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomTwo.setOnClickAddApp(new FragmentLexusLsBottomTwo.IAddAppClickListener() {
            public void onClick(View view) {
                MainActivity.this.initPopuWindow(view);
            }
        });
        this.lexusLsBottomViewPagerAdpaterV2.fragmentLexusLsBottomTwoV2.setOnClickAddApp(new FragmentLexusLsBottomTwoV2.IAddAppClickListener() {
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
        int index2 = lexusLsBinding.lexusLsViewpagerBottom.getCurrentItem() - 1;
        if (index2 >= 0) {
            lexusLsBinding.lexusLsViewpagerBottom.setCurrentItem(index2);
            if (index2 == 0) {
                if (UiThemeUtils.isLEXUS_LS_UI(this)) {
                    this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomOne.setDefaultSelected();
                } else {
                    this.lexusLsBottomViewPagerAdpaterV2.fragmentLexusLsBottomOne.setDefaultSelected();
                }
            } else if (index2 != 1) {
            } else {
                if (UiThemeUtils.isLEXUS_LS_UI(this)) {
                    this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomTwo.setDefaultSelected();
                } else {
                    this.lexusLsBottomViewPagerAdpaterV2.fragmentLexusLsBottomTwoV2.setDefaultSelected();
                }
            }
        }
    }

    public /* synthetic */ void lambda$initLexusLs$5$MainActivity(LexusLsMainLayoutBinding lexusLsBinding, View v) {
        int index2 = lexusLsBinding.lexusLsViewpagerBottom.getCurrentItem() + 1;
        if (index2 <= 2) {
            lexusLsBinding.lexusLsViewpagerBottom.setCurrentItem(index2);
            if (index2 != 1) {
                return;
            }
            if (UiThemeUtils.isLEXUS_LS_UI(this)) {
                this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomTwo.setDefaultSelected();
            } else {
                this.lexusLsBottomViewPagerAdpaterV2.fragmentLexusLsBottomTwoV2.setDefaultSelected();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateHideAndShowAddMenu() {
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
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
        } else if (LexusLsConfig.mAppList_V2.size() > this.pageSize * 2) {
            if (LexusLsConfig.mAppList_V2.contains(LexusLsConfig.findBeanByPkg(LexusLsConfig.PKG_MENU_STRS_V2[13], LexusLsConfig.mAppList_V2))) {
                LexusLsConfig.mAppList_V2.remove(LexusLsConfig.findBeanByPkg(LexusLsConfig.PKG_MENU_STRS_V2[13], LexusLsConfig.mAppList_V2));
            }
        } else if (!LexusLsConfig.mAppList_V2.contains(LexusLsConfig.findBeanByPkg(LexusLsConfig.PKG_MENU_STRS_V2[13], LexusLsConfig.mAppList_V2)) && LexusLsConfig.mAppList_V2.size() < this.pageSize * 2) {
            LexusLsAppSelBean beanAddApp2 = new LexusLsAppSelBean();
            beanAddApp2.setAppName(mainActivity.getResources().getString(R.string.add_app).trim());
            beanAddApp2.setAppIcon(mainActivity.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_add));
            beanAddApp2.setAppPkg("lexus.ls.defined.pkgnamev2.13");
            beanAddApp2.setItemPos(LexusLsConfig.mAppList_V2.size());
            beanAddApp2.setDeletable(false);
            LexusLsConfig.mAppList_V2.add(beanAddApp2);
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
        int width = getResources().getDisplayMetrics().widthPixels;
        this.ugBinding = (ActivityMainGsugBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_gsug);
        if (ClientManager.getInstance().isGSClient() && width == 1920) {
            this.ugBinding.musicButton.setImageResource(R.drawable.ug_music_gs_selector);
            this.ugBinding.naviButton.setImageResource(R.drawable.ug_navi_gs_selector);
            this.ugBinding.settingButton.setImageResource(R.drawable.ug_setting_gs_selector);
            this.ugBinding.appsButton.setImageResource(R.drawable.ug_app_gs_selector);
        }
        this.ugBinding.ugViewPage.setAdapter(new UgHomePagerAdpater(getSupportFragmentManager()));
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
        ActivityMainGsug2Binding activityMainGsug2Binding = (ActivityMainGsug2Binding) DataBindingUtil.setContentView(this, R.layout.activity_main_gsug2);
        this.gsug2Binding = activityMainGsug2Binding;
        activityMainGsug2Binding.ugViewPage.setAdapter(new UgHomePagerAdpater(getSupportFragmentManager()));
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
    public void initCommonUIKSWMBUX1024View() {
        Log.i(TAG, "initCommonUIKSWMBUX1024View: ");
        ActivityMainKswmbuxBinding activityMainKswmbuxBinding = (ActivityMainKswmbuxBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_kswmbux);
        this.kswmbuxBinding = activityMainKswmbuxBinding;
        activityMainKswmbuxBinding.ugViewPage.setAdapter(new KSWMBUXHomePagerAdpater(getSupportFragmentManager()));
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        this.kswmbuxBinding.setViewModel(this.viewModel);
        this.kswmbuxBinding.ugViewPage.setUgPageChangeListener(new UgViewPager.UgPageChangeListener() {
            public void onPageSelected(int i, boolean left, boolean right) {
                Log.i(MainActivity.TAG, "onPageSelected: i:" + i + " left:" + left + " right:" + right);
                switch (i) {
                    case 0:
                        MainActivity.this.kswmbuxBinding.imageView1.setSelected(true);
                        MainActivity.this.kswmbuxBinding.imageView2.setSelected(false);
                        MainActivity.this.kswmbuxBinding.imageView3.setSelected(false);
                        break;
                    case 1:
                        MainActivity.this.kswmbuxBinding.imageView1.setSelected(false);
                        MainActivity.this.kswmbuxBinding.imageView2.setSelected(true);
                        MainActivity.this.kswmbuxBinding.imageView3.setSelected(false);
                        break;
                    case 2:
                        MainActivity.this.kswmbuxBinding.imageView1.setSelected(false);
                        MainActivity.this.kswmbuxBinding.imageView2.setSelected(false);
                        MainActivity.this.kswmbuxBinding.imageView3.setSelected(true);
                        break;
                }
                MainActivity.this.select.setValue(new UgPager(i, left, right));
            }
        });
        this.kswmbuxBinding.ugViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int i) {
                boolean z = false;
                MainActivity.this.kswmbuxBinding.imageView1.setSelected(i == 0);
                MainActivity.this.kswmbuxBinding.imageView2.setSelected(i == 1);
                ImageView imageView = MainActivity.this.kswmbuxBinding.imageView3;
                if (i == 2) {
                    z = true;
                }
                imageView.setSelected(z);
            }

            public void onPageScrollStateChanged(int i) {
                switch (i) {
                    case 0:
                        MainActivity.this.kswmbuxBinding.leftButton.setVisibility(0);
                        MainActivity.this.kswmbuxBinding.rightButton.setVisibility(0);
                        return;
                    case 1:
                        MainActivity.this.kswmbuxBinding.leftButton.setVisibility(4);
                        MainActivity.this.kswmbuxBinding.rightButton.setVisibility(4);
                        return;
                    case 2:
                        MainActivity.this.kswmbuxBinding.leftButton.setVisibility(4);
                        MainActivity.this.kswmbuxBinding.rightButton.setVisibility(4);
                        return;
                    default:
                        return;
                }
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
        AlsID7ViewModel.screenPixels.set(DisplayUtil.getRealScreenRelatedInformation(this));
        if (((String) AlsID7ViewModel.screenPixels.get()).contains(",")) {
            AlsID7ViewModel.screenWidth.set(Integer.valueOf(((String) AlsID7ViewModel.screenPixels.get()).split(",")[0]));
            AlsID7ViewModel.screenHeight.set(Integer.valueOf(((String) AlsID7ViewModel.screenPixels.get()).split(",")[1]));
        } else {
            AlsID7ViewModel.screenWidth.set(-1);
            AlsID7ViewModel.screenHeight.set(-1);
        }
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
        this.alsId7Binding.btnShut1.setOnLongClickListener(this.mOnLongListener_AlsId7);
        this.alsId7Binding.btnShut2.setOnLongClickListener(this.mOnLongListener_AlsId7);
        this.alsId7Binding.btnShut3.setOnLongClickListener(this.mOnLongListener_AlsId7);
        this.alsId7Binding.btnApps.setOnClickListener(this.mOnClickListener_AlsId7);
        this.alsId7Binding.btnSettings.setOnClickListener(this.mOnClickListener_AlsId7);
        this.alsId7Binding.btnShut1.setOnClickListener(this.mOnClickListener_AlsId7);
        this.alsId7Binding.btnShut2.setOnClickListener(this.mOnClickListener_AlsId7);
        this.alsId7Binding.btnShut3.setOnClickListener(this.mOnClickListener_AlsId7);
    }

    /* access modifiers changed from: protected */
    public void initAlsId7UI_V2() {
        String str = TAG;
        Log.i(str, "chen-new ui initAlsId7UI_V2: ");
        AlsID7ViewModel alsID7ViewModel2 = (AlsID7ViewModel) ViewModelProviders.of((FragmentActivity) this).get(AlsID7ViewModel.class);
        this.alsID7ViewModel = alsID7ViewModel2;
        alsID7ViewModel2.setActivity(this);
        ActivityMainAlsId7V2Binding activityMainAlsId7V2Binding = (ActivityMainAlsId7V2Binding) DataBindingUtil.setContentView(this, R.layout.activity_main_als_id7_v2);
        this.alsId7V2Binding = activityMainAlsId7V2Binding;
        activityMainAlsId7V2Binding.viewPage.setAdapter(new BMWId7ViewPagerAdpaterV2(getSupportFragmentManager()));
        setCurrentItem(0);
        this.alsId7V2Binding.viewPage.setOffscreenPageLimit(4);
        this.alsId7V2Binding.imageView1.setSelected(false);
        this.alsId7V2Binding.imageView3.setSelected(true);
        this.alsId7V2Binding.imageView4.setSelected(false);
        this.alsId7V2Binding.imageView5.setSelected(false);
        this.alsId7V2Binding.setLauncherViewModel(this.alsID7ViewModel);
        this.alsID7ViewModel.initData();
        this.alsID7ViewModel.startWeatherLooper();
        Log.i(str, "initAlsId7UI_V2:gjr加载 " + LauncherViewModel.weatherInfo.isLoadSuccess);
        Log.i(str, "initAlsId7UI_V2:gjrinit " + LauncherViewModel.weatherInfo.isInitFinished);
        AlsID7ViewModel.screenPixels.set(DisplayUtil.getRealScreenRelatedInformation(this));
        if (((String) AlsID7ViewModel.screenPixels.get()).contains(",")) {
            AlsID7ViewModel.screenWidth.set(Integer.valueOf(((String) AlsID7ViewModel.screenPixels.get()).split(",")[0]));
            AlsID7ViewModel.screenHeight.set(Integer.valueOf(((String) AlsID7ViewModel.screenPixels.get()).split(",")[1]));
        } else {
            AlsID7ViewModel.screenWidth.set(-1);
            AlsID7ViewModel.screenHeight.set(-1);
        }
        this.alsId7V2Binding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        MainActivity.this.alsId7V2Binding.imageView1.setSelected(false);
                        MainActivity.this.alsId7V2Binding.imageView3.setSelected(true);
                        MainActivity.this.alsId7V2Binding.imageView4.setSelected(false);
                        MainActivity.this.alsId7V2Binding.imageView5.setSelected(false);
                        return;
                    case 1:
                        MainActivity.this.alsId7V2Binding.imageView1.setSelected(true);
                        MainActivity.this.alsId7V2Binding.imageView3.setSelected(false);
                        MainActivity.this.alsId7V2Binding.imageView4.setSelected(false);
                        MainActivity.this.alsId7V2Binding.imageView5.setSelected(false);
                        return;
                    case 2:
                        MainActivity.this.alsId7V2Binding.imageView1.setSelected(false);
                        MainActivity.this.alsId7V2Binding.imageView3.setSelected(false);
                        MainActivity.this.alsId7V2Binding.imageView4.setSelected(true);
                        MainActivity.this.alsId7V2Binding.imageView5.setSelected(false);
                        return;
                    case 3:
                        MainActivity.this.alsId7V2Binding.imageView1.setSelected(false);
                        MainActivity.this.alsId7V2Binding.imageView3.setSelected(false);
                        MainActivity.this.alsId7V2Binding.imageView4.setSelected(false);
                        MainActivity.this.alsId7V2Binding.imageView5.setSelected(true);
                        return;
                    default:
                        return;
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        this.alsId7V2Binding.btnShut1.setOnLongClickListener(this.mOnLongListener_AlsId7);
        this.alsId7V2Binding.btnShut2.setOnLongClickListener(this.mOnLongListener_AlsId7);
        this.alsId7V2Binding.btnShut3.setOnLongClickListener(this.mOnLongListener_AlsId7);
        this.alsId7V2Binding.btnApps.setOnClickListener(this.mOnClickListener_AlsId7);
        this.alsId7V2Binding.btnSettings.setOnClickListener(this.mOnClickListener_AlsId7);
        this.alsId7V2Binding.btnShut1.setOnClickListener(this.mOnClickListener_AlsId7);
        this.alsId7V2Binding.btnShut2.setOnClickListener(this.mOnClickListener_AlsId7);
        this.alsId7V2Binding.btnShut3.setOnClickListener(this.mOnClickListener_AlsId7);
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
    public void initAudi_mib3_FyUiView() {
        Log.i(TAG, "initAudiMbi3FyView : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mAudiMbi3FyBcViewModel = bcVieModel;
        bcVieModel.setActivity(this);
        mAudiMbi3FyBcViewModel.initData();
        AudiMib3FyMainLayoutBinding audiMib3FyMainLayoutBinding = (AudiMib3FyMainLayoutBinding) DataBindingUtil.setContentView(this, R.layout.audi_mib3_fy_main_layout);
        mib3FyMainLayoutBinding = audiMib3FyMainLayoutBinding;
        audiMib3FyMainLayoutBinding.setViewModel(mAudiMbi3FyBcViewModel);
        mib3FyMainLayoutBinding.include.ivMusicAudimib3.setOnClickListener(this.mAudiMib3FyItemClickListener);
        mib3FyMainLayoutBinding.include.ivNaviAudimib3.setOnClickListener(this.mAudiMib3FyItemClickListener);
        mib3FyMainLayoutBinding.include.ivBtAudimib3.setOnClickListener(this.mAudiMib3FyItemClickListener);
        mib3FyMainLayoutBinding.include.ivSetAudimib3.setOnClickListener(this.mAudiMib3FyItemClickListener);
        mib3FyMainLayoutBinding.include.ivCarAudimib3.setOnClickListener(this.mAudiMib3FyItemClickListener);
        audiMib3FyViewPagerAdapter = new AudiMib3FyViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = mib3FyMainLayoutBinding.viewPageAudiMib3;
        this.viewpagerAudiMib3Fy = viewPager;
        viewPager.setAdapter(audiMib3FyViewPagerAdapter);
        this.viewpagerAudiMib3Fy.setCurrentItem(0);
        this.viewpagerAudiMib3Fy.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int pageIndex) {
                Log.d("onPageSelected", "0000000000");
                if (pageIndex == 0) {
                    BcVieModel bcVieModel = MainActivity.mAudiMbi3FyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FyFragmentOne audiMib3FyFragmentOne = (AudiMib3FyFragmentOne) MainActivity.audiMib3FyViewPagerAdapter.fragmentPage1;
                        BcVieModel bcVieModel2 = MainActivity.mAudiMbi3FyBcViewModel;
                        AudiMib3FyFragmentOne.setItemSelected(BcVieModel.viewLastSel);
                        MainActivity.this.setIndicatorBackground(pageIndex, 10);
                    }
                }
                if (pageIndex == 1) {
                    BcVieModel bcVieModel3 = MainActivity.mAudiMbi3FyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FyFragmentTwo audiMib3FyFragmentTwo = (AudiMib3FyFragmentTwo) MainActivity.audiMib3FyViewPagerAdapter.fragmentPage2;
                        BcVieModel bcVieModel4 = MainActivity.mAudiMbi3FyBcViewModel;
                        AudiMib3FyFragmentTwo.setItemSelected(BcVieModel.viewLastSel);
                    }
                }
                MainActivity.this.setIndicatorBackground(pageIndex, 10);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(10);
    }

    /* access modifiers changed from: protected */
    public void initAudi_mib3_Fy_V2_UiView() {
        Log.i(TAG, "initAudi_mib3_Fy_V2_UiView : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mAudiMbi3FyBcViewModel = bcVieModel;
        bcVieModel.setActivity(this);
        mAudiMbi3FyBcViewModel.initData();
        AudiMib3FyMainLayoutBinding audiMib3FyMainLayoutBinding = (AudiMib3FyMainLayoutBinding) DataBindingUtil.setContentView(this, R.layout.audi_mib3_fy_main_layout);
        mib3FyMainLayoutBinding = audiMib3FyMainLayoutBinding;
        audiMib3FyMainLayoutBinding.setViewModel(mAudiMbi3FyBcViewModel);
        mib3FyMainLayoutBinding.include.ivMusicAudimib3.setOnClickListener(this.mAudiMib3FyItemClickListener);
        mib3FyMainLayoutBinding.include.ivNaviAudimib3.setOnClickListener(this.mAudiMib3FyItemClickListener);
        mib3FyMainLayoutBinding.include.ivBtAudimib3.setOnClickListener(this.mAudiMib3FyItemClickListener);
        mib3FyMainLayoutBinding.include.ivSetAudimib3.setOnClickListener(this.mAudiMib3FyItemClickListener);
        mib3FyMainLayoutBinding.include.ivCarAudimib3.setOnClickListener(this.mAudiMib3FyItemClickListener);
        audiMib3FyV2ViewPagerAdapter = new AudiMib3FyV2ViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = mib3FyMainLayoutBinding.viewPageAudiMib3;
        this.viewpagerAudiMib3Fy = viewPager;
        viewPager.setAdapter(audiMib3FyV2ViewPagerAdapter);
        this.viewpagerAudiMib3Fy.setCurrentItem(0);
        this.viewpagerAudiMib3Fy.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int pageIndex) {
                Log.d("onPageSelected", "0000000000");
                if (pageIndex == 0) {
                    BcVieModel bcVieModel = MainActivity.mAudiMbi3FyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FyV2FragmentOne audiMib3FyV2FragmentOne = (AudiMib3FyV2FragmentOne) MainActivity.audiMib3FyV2ViewPagerAdapter.fragmentPage1;
                        BcVieModel bcVieModel2 = MainActivity.mAudiMbi3FyBcViewModel;
                        AudiMib3FyV2FragmentOne.setItemSelected(BcVieModel.viewLastSel);
                        MainActivity.this.setIndicatorBackground(pageIndex, 10);
                    }
                }
                if (pageIndex == 1) {
                    BcVieModel bcVieModel3 = MainActivity.mAudiMbi3FyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FyV2FragmentTwo audiMib3FyV2FragmentTwo = (AudiMib3FyV2FragmentTwo) MainActivity.audiMib3FyV2ViewPagerAdapter.fragmentPage2;
                        BcVieModel bcVieModel4 = MainActivity.mAudiMbi3FyBcViewModel;
                        AudiMib3FyV2FragmentTwo.setItemSelected(BcVieModel.viewLastSel);
                    }
                }
                MainActivity.this.setIndicatorBackground(pageIndex, 10);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(10);
    }

    /* access modifiers changed from: protected */
    public void initAudiMib3Ty() {
        Log.i(TAG, "initAudiMib3Ty : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mAudiMib3TyBcViewModel = bcVieModel;
        bcVieModel.setActivity(this);
        mAudiMib3TyBcViewModel.initData();
        AudiMib3TyMainLayoutBinding audiMib3TyMainLayoutBinding2 = (AudiMib3TyMainLayoutBinding) DataBindingUtil.setContentView(this, R.layout.audi_mib3_ty_main_layout);
        audiMib3TyMainLayoutBinding = audiMib3TyMainLayoutBinding2;
        audiMib3TyMainLayoutBinding2.setViewModel(mAudiMib3TyBcViewModel);
        audiMib3TyMainLayoutBinding.include.ivMusicAudimib3.setOnClickListener(this.mAudiMib3TyItemClickListener);
        audiMib3TyMainLayoutBinding.include.ivNaviAudimib3.setOnClickListener(this.mAudiMib3TyItemClickListener);
        audiMib3TyMainLayoutBinding.include.ivBtAudimib3.setOnClickListener(this.mAudiMib3TyItemClickListener);
        audiMib3TyMainLayoutBinding.include.ivSetAudimib3.setOnClickListener(this.mAudiMib3TyItemClickListener);
        audiMib3TyMainLayoutBinding.include.ivCarAudimib3.setOnClickListener(this.mAudiMib3TyItemClickListener);
        audiMib3TyViewPagerAdapter = new AudiMib3TyViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = audiMib3TyMainLayoutBinding.viewPageAudiMib3Ty;
        this.audiMib3TyViewPager = viewPager;
        viewPager.setAdapter(audiMib3TyViewPagerAdapter);
        this.audiMib3TyViewPager.setCurrentItem(0);
        this.audiMib3TyViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int pageIndex) {
                Log.d("onPageSelected", "0000000000");
                if (pageIndex == 0) {
                    BcVieModel bcVieModel = MainActivity.mAudiMib3TyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3TyFragmentOne audiMib3TyFragmentOne = (AudiMib3TyFragmentOne) MainActivity.audiMib3TyViewPagerAdapter.fragmentPage1;
                        BcVieModel bcVieModel2 = MainActivity.mAudiMib3TyBcViewModel;
                        AudiMib3TyFragmentOne.setItemSelected(BcVieModel.viewLastSel);
                        MainActivity.this.setIndicatorBackground(pageIndex, 11);
                    }
                }
                if (pageIndex == 1) {
                    BcVieModel bcVieModel3 = MainActivity.mAudiMib3TyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3TyFragmentTwo audiMib3TyFragmentTwo = (AudiMib3TyFragmentTwo) MainActivity.audiMib3TyViewPagerAdapter.fragmentPage2;
                        BcVieModel bcVieModel4 = MainActivity.mAudiMib3TyBcViewModel;
                        AudiMib3TyFragmentTwo.setItemSelected(BcVieModel.viewLastSel);
                    }
                }
                MainActivity.this.setIndicatorBackground(pageIndex, 11);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(11);
    }

    /* access modifiers changed from: protected */
    public void initBmwId8GsUiView() {
        setFull();
        setStatusBarTranslucent();
        this.systemCardViewHashMap = new HashMap<>();
        this.trdCardViewHashMap = new HashMap<>();
        Log.i(TAG, "initBmwGSId8UiView: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.bmwId8ViewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        ActivityGsId8LauncherMainBinding activityGsId8LauncherMainBinding = (ActivityGsId8LauncherMainBinding) DataBindingUtil.setContentView(this, R.layout.activity_gs_id8_launcher_main);
        this.bmwId8GsUiMainLayoutBinding = activityGsId8LauncherMainBinding;
        activityGsId8LauncherMainBinding.setLauncherViewModel(this.bmwId8ViewModel);
        this.bmwId8GsUiMainLayoutBinding.gsScrollView.setScrollViewListener(new ID8HorizontalScrollView.ScrollViewListener() {
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                int unused = MainActivity.this.currentScrollX = x;
                int unused2 = MainActivity.this.currentScrollY = y;
                int unused3 = MainActivity.this.currentOldScrollX = oldx;
                Log.w(MainActivity.TAG, "scrollX11111: " + MainActivity.this.currentScrollX + "currentScrollY: " + MainActivity.this.currentScrollY);
            }
        });
        this.bmwId8GsUiMainLayoutBinding.gsScrollView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int eventAction = event.getAction();
                int rawY = (int) event.getRawY();
                switch (eventAction) {
                    case 1:
                        MainActivity.this.handler.sendMessageDelayed(MainActivity.this.handler.obtainMessage(MainActivity.this.touchEventId, v), 50);
                        return false;
                    default:
                        return false;
                }
            }
        });
        AnonymousClass53 r0 = new OnID8SkinChangeListener() {
            public void onSkinChangeLeftBar(int drawableId) {
                if (MainActivity.this.bmwId8GsUiMainLayoutBinding != null) {
                    MainActivity.this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft1.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft2.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft3.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft4.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft5.setBackground(MainActivity.this.getDrawable(drawableId));
                }
            }

            public void onSkinChangeCardBGSelector(String skinName) {
                if (MainActivity.this.systemCardViewHashMap != null && MainActivity.this.systemCardViewHashMap.size() != 0) {
                    for (Map.Entry<String, View> next : MainActivity.this.systemCardViewHashMap.entrySet()) {
                        String keyName = next.getKey();
                        View targetView = next.getValue();
                        if (!TextUtils.isEmpty(keyName) && targetView != null) {
                            MainActivity.this.setID8GsCardBGSelector(keyName, targetView, skinName);
                            MainActivity.this.setID8GsMusicBGSelector(skinName);
                        }
                    }
                    if (MainActivity.this.trdCardViewHashMap != null && MainActivity.this.trdCardViewHashMap.size() != 0) {
                        for (Map.Entry<String, View> next2 : MainActivity.this.trdCardViewHashMap.entrySet()) {
                            String keyName2 = next2.getKey();
                            View targetView2 = next2.getValue();
                            if (targetView2 != null) {
                                MainActivity.this.setID8GsCardBGSelector(keyName2, targetView2, skinName);
                            }
                        }
                    }
                }
            }

            public void onSkinChangeMusicAlbum(int drawableId) {
                if (((View) MainActivity.this.systemCardViewHashMap.get("MUSIC")) != null && LauncherViewModel.mediaInfo.pic.get() == null) {
                    Log.w(MainActivity.TAG, "onSkinChangeMusicAlbum: " + drawableId);
                }
            }
        };
        this.onGsID8SkinChangeListener = r0;
        this.bmwId8ViewModel.initSkinData(r0);
        this.bmwId8ViewModel.initData();
        this.bmwId8ViewModel.startWeatherLooper();
        ActivityGsId8LauncherMainBinding activityGsId8LauncherMainBinding2 = this.bmwId8GsUiMainLayoutBinding;
        if (activityGsId8LauncherMainBinding2 != null) {
            activityGsId8LauncherMainBinding2.llLeftContainer.llLeft1.setFocusedByDefault(true);
        }
    }

    public void scrollViewRightStop(int scrollViewX) {
        if (scrollViewX <= 0 || ((double) scrollViewX) > ((double) this.cardWith) * this.cardRightScroll) {
            int i = this.cardWith;
            double d = this.cardRightScroll;
            if (((double) i) * d < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 1.0d)) {
                this.currentScrollX = i;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (1.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 2.0d)) {
                this.currentScrollX = i * 2;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (2.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 3.0d)) {
                this.currentScrollX = i * 3;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (3.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 4.0d)) {
                this.currentScrollX = i * 4;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (4.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 5.0d)) {
                this.currentScrollX = i * 5;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (5.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 6.0d)) {
                this.currentScrollX = i * 6;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (6.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 7.0d)) {
                this.currentScrollX = i * 7;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (7.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 8.0d)) {
                this.currentScrollX = i * 8;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (8.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 9.0d)) {
                this.currentScrollX = i * 9;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (9.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 10.0d)) {
                this.currentScrollX = i * 10;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            }
        } else {
            this.currentScrollX = 0;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        }
    }

    public void scrollViewLeftStop(int scrollViewX) {
        if (scrollViewX <= 0 || ((double) scrollViewX) > ((double) this.cardWith) * this.cardLeftScroll) {
            int i = this.cardWith;
            double d = this.cardLeftScroll;
            if (((double) i) * d < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 1.0d)) {
                this.currentScrollX = i;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (1.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 2.0d)) {
                this.currentScrollX = i * 2;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (2.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 3.0d)) {
                this.currentScrollX = i * 3;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (3.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 4.0d)) {
                this.currentScrollX = i * 4;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (4.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 5.0d)) {
                this.currentScrollX = i * 5;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (5.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 6.0d)) {
                this.currentScrollX = i * 6;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (6.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 7.0d)) {
                this.currentScrollX = i * 7;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (7.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 8.0d)) {
                this.currentScrollX = i * 8;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (8.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 9.0d)) {
                this.currentScrollX = i * 9;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (9.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 10.0d)) {
                this.currentScrollX = i * 10;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            } else if (((double) i) * (10.0d + d) < ((double) scrollViewX) && ((double) scrollViewX) <= ((double) i) * (d + 11.0d)) {
                this.currentScrollX = i * 11;
                this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            }
        } else {
            this.currentScrollX = 0;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        }
    }

    /* access modifiers changed from: protected */
    public void initBmwId8UiView() {
        setFull();
        setStatusBarTranslucent();
        this.systemCardViewHashMap = new HashMap<>();
        this.trdCardViewHashMap = new HashMap<>();
        Log.i(TAG, "initBmwId8UiView: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.bmwId8ViewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        ActivityId8LauncherMainBinding activityId8LauncherMainBinding = (ActivityId8LauncherMainBinding) DataBindingUtil.setContentView(this, R.layout.activity_id8_launcher_main);
        this.bmwId8UiMainLayoutBinding = activityId8LauncherMainBinding;
        activityId8LauncherMainBinding.setLauncherViewModel(this.bmwId8ViewModel);
        AnonymousClass54 r0 = new OnID8SkinChangeListener() {
            public void onSkinChangeLeftBar(int drawableId) {
                if (MainActivity.this.bmwId8UiMainLayoutBinding != null) {
                    MainActivity.this.bmwId8UiMainLayoutBinding.llLeftContainer.llLeft1.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8UiMainLayoutBinding.llLeftContainer.llLeft2.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8UiMainLayoutBinding.llLeftContainer.llLeft3.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8UiMainLayoutBinding.llLeftContainer.llLeft4.setBackground(MainActivity.this.getDrawable(drawableId));
                }
            }

            public void onSkinChangeCardBGSelector(String skinName) {
                if (MainActivity.this.systemCardViewHashMap != null && MainActivity.this.systemCardViewHashMap.size() != 0) {
                    for (Map.Entry<String, View> next : MainActivity.this.systemCardViewHashMap.entrySet()) {
                        String keyName = next.getKey();
                        View targetView = next.getValue();
                        if (!TextUtils.isEmpty(keyName) && targetView != null) {
                            MainActivity.this.setID8CardBGSelector(keyName, targetView, skinName);
                        }
                    }
                    if (MainActivity.this.trdCardViewHashMap != null && MainActivity.this.trdCardViewHashMap.size() != 0) {
                        for (Map.Entry<String, View> next2 : MainActivity.this.trdCardViewHashMap.entrySet()) {
                            String keyName2 = next2.getKey();
                            View targetView2 = next2.getValue();
                            if (targetView2 != null) {
                                MainActivity.this.setID8CardBGSelector(keyName2, targetView2, skinName);
                                MainActivity.this.setID8TrdCardSkinResources(targetView2, skinName);
                            }
                        }
                    }
                }
            }

            public void onSkinChangeMusicAlbum(int drawableId) {
                View view = (View) MainActivity.this.systemCardViewHashMap.get("MUSIC");
                if (view != null && LauncherViewModel.mediaInfo.pic.get() == null) {
                    Log.w(MainActivity.TAG, "onSkinChangeMusicAlbum: " + drawableId);
                    ((ImageView) view.findViewById(R.id.iv_music_album)).setImageDrawable(MainActivity.this.getDrawable(drawableId));
                }
            }
        };
        this.onID8SkinChangeListener = r0;
        this.bmwId8ViewModel.initSkinData(r0);
        this.bmwId8ViewModel.initData();
        this.bmwId8ViewModel.startWeatherLooper();
        ActivityId8LauncherMainBinding activityId8LauncherMainBinding2 = this.bmwId8UiMainLayoutBinding;
        if (activityId8LauncherMainBinding2 != null) {
            activityId8LauncherMainBinding2.llLeftContainer.llLeft1.setFocusedByDefault(true);
        }
    }

    /* access modifiers changed from: private */
    public void setID8CardBGSelector(String cardName, View view, String skinName) {
        int smallCardDrawableId;
        int bigCardDrawableId;
        int drawableId;
        if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
            bigCardDrawableId = R.drawable.bmw_id8_main_icon_navi_mask_blue;
            smallCardDrawableId = R.drawable.bmw_id8_main_icon_weather_mask_blue;
        } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            bigCardDrawableId = R.drawable.bmw_id8_main_icon_navi_mask_red;
            smallCardDrawableId = R.drawable.bmw_id8_main_icon_weather_mask_red;
        } else {
            bigCardDrawableId = R.drawable.bmw_id8_main_icon_navi_mask_yellow;
            smallCardDrawableId = R.drawable.bmw_id8_main_icon_weather_mask_yellow;
        }
        if ("NAVIGATE".equals(cardName) || "DASHBOARD".equals(cardName) || "CAR INFO".equals(cardName)) {
            drawableId = bigCardDrawableId;
        } else {
            drawableId = smallCardDrawableId;
        }
        if (view != null) {
            view.findViewById(R.id.iv_mask).setBackground(getDrawable(drawableId));
        }
    }

    /* access modifiers changed from: private */
    public void setID8GsCardBGSelector(String cardName, View view, String skinName) {
        int drawableId;
        if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
            drawableId = R.drawable.bmw_id8_gs_main_icon_mask_blue;
        } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            drawableId = R.drawable.bmw_id8_gs_main_icon_mask_red;
        } else {
            drawableId = R.drawable.bmw_id8_gs_main_icon_mask_yellow;
        }
        if (view != null) {
            view.findViewById(R.id.iv_mask).setBackground(getDrawable(drawableId));
        }
    }

    /* access modifiers changed from: private */
    public void setID8GsMusicBGSelector(String skinName) {
        int drawableMusicBtnId;
        View viewMusic = this.systemCardViewHashMap.get("MUSIC");
        View viewVideo = this.systemCardViewHashMap.get("VIDEO");
        Log.w(TAG, "drawableMusicBtnId: " + skinName);
        if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
            drawableMusicBtnId = R.drawable.bmw_id8_gs_main_music_icon_blue;
        } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            drawableMusicBtnId = R.drawable.bmw_id8_gs_main_music_icon_red;
        } else {
            drawableMusicBtnId = R.drawable.bmw_id8_gs_main_music_icon_yellow;
        }
        if (viewMusic != null) {
            viewMusic.findViewById(R.id.music_id8_gs_prev).setBackground(getDrawable(drawableMusicBtnId));
            viewMusic.findViewById(R.id.music_id8_gs_playPause).setBackground(getDrawable(drawableMusicBtnId));
            viewMusic.findViewById(R.id.music_id8_gs_next).setBackground(getDrawable(drawableMusicBtnId));
        }
        if (viewVideo != null) {
            viewVideo.findViewById(R.id.video_id8_gs_prev).setBackground(getDrawable(drawableMusicBtnId));
            viewVideo.findViewById(R.id.video_id8_gs_playPause).setBackground(getDrawable(drawableMusicBtnId));
            viewVideo.findViewById(R.id.video_id8_gs_next).setBackground(getDrawable(drawableMusicBtnId));
        }
    }

    private void beforeRefreshFragmentCards() {
        Log.w(TAG, "beforeRefreshFragmentCards: ");
        List<String> nameList = ID8LauncherConstants.nameList;
        if (checkCardSeqHasChange(nameList) || checkDisplayAppsHasUninstall(nameList)) {
            refreshFragmentCards(nameList);
            cacheCardsSeq(nameList);
        }
    }

    private void beforeRefreshFragmentCardsGSID8() {
        Log.w(TAG, "beforeRefreshFragmentCards: ");
        List<String> nameList = GSID8LauncherConstants.nameList;
        if (checkCardSeqHasChangeGSID8(nameList) || checkDisplayAppsHasUninstall(nameList)) {
            refreshFragmentCardsGSID8(nameList);
            cacheCardsSeqGSID8(nameList);
        }
    }

    private void beforeRefreshLeftBarIcon() {
        boolean checkLeftIconHasChange;
        Log.w(TAG, "beforeRefreshLeftBarIcon: ");
        if (UiThemeUtils.isBMW_ID8_UI(this)) {
            checkLeftIconHasChange = checkLeftIconHasChange();
        } else {
            checkLeftIconHasChange = checkLeftIconHasChangeGSID8();
        }
        if (!checkLeftIconHasChange) {
            return;
        }
        if (UiThemeUtils.isBMW_ID8_UI(this)) {
            refreshLeftBarIcon();
        } else if (UiThemeUtils.isUI_GS_ID8(this)) {
            refreshLeftBarIconGSID8();
        }
    }

    private void refreshLeftBarIcon() {
        this.id8CacheLeftIcon2 = ID8LauncherConstants.leftIcon2;
        this.id8CacheLeftIcon3 = ID8LauncherConstants.leftIcon3;
        this.id8CacheLeftIcon4 = ID8LauncherConstants.leftIcon4;
        initLeftIcon(this.bmwId8UiMainLayoutBinding.llLeftContainer.llLeft2, this.bmwId8UiMainLayoutBinding.llLeftContainer.ivLeft2, this.bmwId8UiMainLayoutBinding.llLeftContainer.tvLeft2, this.id8CacheLeftIcon2);
        initLeftIcon(this.bmwId8UiMainLayoutBinding.llLeftContainer.llLeft3, this.bmwId8UiMainLayoutBinding.llLeftContainer.ivLeft3, this.bmwId8UiMainLayoutBinding.llLeftContainer.tvLeft3, this.id8CacheLeftIcon3);
        initLeftIcon(this.bmwId8UiMainLayoutBinding.llLeftContainer.llLeft4, this.bmwId8UiMainLayoutBinding.llLeftContainer.ivLeft4, this.bmwId8UiMainLayoutBinding.llLeftContainer.tvLeft4, this.id8CacheLeftIcon4);
    }

    private void refreshLeftBarIconGSID8() {
        this.id8CacheLeftIcon3 = GSID8LauncherConstants.leftIcon3;
        this.id8CacheLeftIcon4 = GSID8LauncherConstants.leftIcon4;
        this.id8CacheLeftIcon5 = GSID8LauncherConstants.leftIcon5;
        initGsLeftIcon(this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft3, this.bmwId8GsUiMainLayoutBinding.llLeftContainer.ivLeft3, this.bmwId8GsUiMainLayoutBinding.llLeftContainer.tvLeft3, this.id8CacheLeftIcon3);
        initGsLeftIcon(this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft4, this.bmwId8GsUiMainLayoutBinding.llLeftContainer.ivLeft4, this.bmwId8GsUiMainLayoutBinding.llLeftContainer.tvLeft4, this.id8CacheLeftIcon4);
        initGsLeftIcon(this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft5, this.bmwId8GsUiMainLayoutBinding.llLeftContainer.ivLeft5, this.bmwId8GsUiMainLayoutBinding.llLeftContainer.tvLeft5, this.id8CacheLeftIcon5);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initLeftIcon(android.widget.LinearLayout r5, android.widget.ImageView r6, android.widget.TextView r7, java.lang.String r8) {
        /*
            r4 = this;
            r0 = -1
            r1 = -1
            int r2 = r8.hashCode()
            r3 = -1
            switch(r2) {
                case -1591043536: goto L_0x005c;
                case -1409845903: goto L_0x0052;
                case 73532672: goto L_0x0048;
                case 73725445: goto L_0x003e;
                case 76105038: goto L_0x0034;
                case 81665115: goto L_0x0029;
                case 741767578: goto L_0x001f;
                case 1738734196: goto L_0x0015;
                case 1941423060: goto L_0x000b;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x0066
        L_0x000b:
            java.lang.String r2 = "WEATHER"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 1
            goto L_0x0067
        L_0x0015:
            java.lang.String r2 = "DASHBOARD"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 6
            goto L_0x0067
        L_0x001f:
            java.lang.String r2 = "CAR INFO"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 3
            goto L_0x0067
        L_0x0029:
            java.lang.String r2 = "VIDEO"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 8
            goto L_0x0067
        L_0x0034:
            java.lang.String r2 = "PHONE"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 5
            goto L_0x0067
        L_0x003e:
            java.lang.String r2 = "MUSIC"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 2
            goto L_0x0067
        L_0x0048:
            java.lang.String r2 = "MODUS"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 4
            goto L_0x0067
        L_0x0052:
            java.lang.String r2 = "NAVIGATE"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 0
            goto L_0x0067
        L_0x005c:
            java.lang.String r2 = "SETTING"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 7
            goto L_0x0067
        L_0x0066:
            r2 = r3
        L_0x0067:
            switch(r2) {
                case 0: goto L_0x00e5;
                case 1: goto L_0x00d6;
                case 2: goto L_0x00c7;
                case 3: goto L_0x00b8;
                case 4: goto L_0x00a9;
                case 5: goto L_0x009a;
                case 6: goto L_0x008b;
                case 7: goto L_0x007c;
                case 8: goto L_0x006c;
                default: goto L_0x006a;
            }
        L_0x006a:
            goto L_0x00f4
        L_0x006c:
            r0 = 2131233433(0x7f080a99, float:1.8083003E38)
            r1 = 2131558779(0x7f0d017b, float:1.8742883E38)
            com.wits.ksw.MainActivity$64 r2 = new com.wits.ksw.MainActivity$64
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x007c:
            r0 = 2131233432(0x7f080a98, float:1.8083001E38)
            r1 = 2131558780(0x7f0d017c, float:1.8742885E38)
            com.wits.ksw.MainActivity$63 r2 = new com.wits.ksw.MainActivity$63
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x008b:
            r0 = 2131233428(0x7f080a94, float:1.8082993E38)
            r1 = 2131558787(0x7f0d0183, float:1.87429E38)
            com.wits.ksw.MainActivity$62 r2 = new com.wits.ksw.MainActivity$62
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x009a:
            r0 = 2131233426(0x7f080a92, float:1.808299E38)
            r1 = 2131558781(0x7f0d017d, float:1.8742888E38)
            com.wits.ksw.MainActivity$61 r2 = new com.wits.ksw.MainActivity$61
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00a9:
            r0 = 2131233429(0x7f080a95, float:1.8082995E38)
            r1 = 2131558795(0x7f0d018b, float:1.8742916E38)
            com.wits.ksw.MainActivity$60 r2 = new com.wits.ksw.MainActivity$60
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00b8:
            r0 = 2131233427(0x7f080a93, float:1.8082991E38)
            r1 = 2131558778(0x7f0d017a, float:1.8742881E38)
            com.wits.ksw.MainActivity$59 r2 = new com.wits.ksw.MainActivity$59
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00c7:
            r0 = 2131233430(0x7f080a96, float:1.8082997E38)
            r1 = 2131558796(0x7f0d018c, float:1.8742918E38)
            com.wits.ksw.MainActivity$58 r2 = new com.wits.ksw.MainActivity$58
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00d6:
            r0 = 2131233434(0x7f080a9a, float:1.8083005E38)
            r1 = 2131558821(0x7f0d01a5, float:1.8742969E38)
            com.wits.ksw.MainActivity$57 r2 = new com.wits.ksw.MainActivity$57
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00e5:
            r0 = 2131233431(0x7f080a97, float:1.8083E38)
            r1 = 2131558782(0x7f0d017e, float:1.874289E38)
            com.wits.ksw.MainActivity$56 r2 = new com.wits.ksw.MainActivity$56
            r2.<init>()
            r5.setOnClickListener(r2)
        L_0x00f4:
            if (r0 != r3) goto L_0x00f7
            return
        L_0x00f7:
            java.lang.String r2 = r4.getString(r1)
            r7.setText(r2)
            android.content.res.Resources r2 = r4.getResources()
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeResource(r2, r0)
            r6.setImageBitmap(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.MainActivity.initLeftIcon(android.widget.LinearLayout, android.widget.ImageView, android.widget.TextView, java.lang.String):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initGsLeftIcon(android.widget.LinearLayout r5, android.widget.ImageView r6, android.widget.TextView r7, java.lang.String r8) {
        /*
            r4 = this;
            r0 = -1
            r1 = -1
            int r2 = r8.hashCode()
            r3 = -1
            switch(r2) {
                case -1591043536: goto L_0x005c;
                case -1409845903: goto L_0x0052;
                case 73532672: goto L_0x0048;
                case 73725445: goto L_0x003e;
                case 76105038: goto L_0x0034;
                case 81665115: goto L_0x0029;
                case 741767578: goto L_0x001f;
                case 1738734196: goto L_0x0015;
                case 1941423060: goto L_0x000b;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x0066
        L_0x000b:
            java.lang.String r2 = "WEATHER"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 4
            goto L_0x0067
        L_0x0015:
            java.lang.String r2 = "DASHBOARD"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 6
            goto L_0x0067
        L_0x001f:
            java.lang.String r2 = "CAR INFO"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 2
            goto L_0x0067
        L_0x0029:
            java.lang.String r2 = "VIDEO"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 8
            goto L_0x0067
        L_0x0034:
            java.lang.String r2 = "PHONE"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 3
            goto L_0x0067
        L_0x003e:
            java.lang.String r2 = "MUSIC"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 1
            goto L_0x0067
        L_0x0048:
            java.lang.String r2 = "MODUS"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 5
            goto L_0x0067
        L_0x0052:
            java.lang.String r2 = "NAVIGATE"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 0
            goto L_0x0067
        L_0x005c:
            java.lang.String r2 = "SETTING"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 7
            goto L_0x0067
        L_0x0066:
            r2 = r3
        L_0x0067:
            switch(r2) {
                case 0: goto L_0x00e5;
                case 1: goto L_0x00d6;
                case 2: goto L_0x00c7;
                case 3: goto L_0x00b8;
                case 4: goto L_0x00a9;
                case 5: goto L_0x009a;
                case 6: goto L_0x008b;
                case 7: goto L_0x007c;
                case 8: goto L_0x006c;
                default: goto L_0x006a;
            }
        L_0x006a:
            goto L_0x00f4
        L_0x006c:
            r0 = 2131233433(0x7f080a99, float:1.8083003E38)
            r1 = 2131558779(0x7f0d017b, float:1.8742883E38)
            com.wits.ksw.MainActivity$73 r2 = new com.wits.ksw.MainActivity$73
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x007c:
            r0 = 2131233432(0x7f080a98, float:1.8083001E38)
            r1 = 2131558780(0x7f0d017c, float:1.8742885E38)
            com.wits.ksw.MainActivity$72 r2 = new com.wits.ksw.MainActivity$72
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x008b:
            r0 = 2131233428(0x7f080a94, float:1.8082993E38)
            r1 = 2131558787(0x7f0d0183, float:1.87429E38)
            com.wits.ksw.MainActivity$71 r2 = new com.wits.ksw.MainActivity$71
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x009a:
            r0 = 2131233429(0x7f080a95, float:1.8082995E38)
            r1 = 2131558795(0x7f0d018b, float:1.8742916E38)
            com.wits.ksw.MainActivity$70 r2 = new com.wits.ksw.MainActivity$70
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00a9:
            r0 = 2131233434(0x7f080a9a, float:1.8083005E38)
            r1 = 2131558821(0x7f0d01a5, float:1.8742969E38)
            com.wits.ksw.MainActivity$69 r2 = new com.wits.ksw.MainActivity$69
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00b8:
            r0 = 2131233077(0x7f080935, float:1.8082281E38)
            r1 = 2131558781(0x7f0d017d, float:1.8742888E38)
            com.wits.ksw.MainActivity$68 r2 = new com.wits.ksw.MainActivity$68
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00c7:
            r0 = 2131233078(0x7f080936, float:1.8082283E38)
            r1 = 2131558758(0x7f0d0166, float:1.874284E38)
            com.wits.ksw.MainActivity$67 r2 = new com.wits.ksw.MainActivity$67
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00d6:
            r0 = 2131233079(0x7f080937, float:1.8082285E38)
            r1 = 2131558796(0x7f0d018c, float:1.8742918E38)
            com.wits.ksw.MainActivity$66 r2 = new com.wits.ksw.MainActivity$66
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00e5:
            r0 = 2131233080(0x7f080938, float:1.8082287E38)
            r1 = 2131558782(0x7f0d017e, float:1.874289E38)
            com.wits.ksw.MainActivity$65 r2 = new com.wits.ksw.MainActivity$65
            r2.<init>()
            r5.setOnClickListener(r2)
        L_0x00f4:
            if (r0 != r3) goto L_0x00f7
            return
        L_0x00f7:
            java.lang.String r2 = r4.getString(r1)
            r7.setText(r2)
            android.content.res.Resources r2 = r4.getResources()
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeResource(r2, r0)
            r6.setImageBitmap(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.MainActivity.initGsLeftIcon(android.widget.LinearLayout, android.widget.ImageView, android.widget.TextView, java.lang.String):void");
    }

    private boolean checkLeftIconHasChange() {
        if (!ID8LauncherConstants.leftIcon2.equals(this.id8CacheLeftIcon2) || !ID8LauncherConstants.leftIcon3.equals(this.id8CacheLeftIcon3) || !ID8LauncherConstants.leftIcon4.equals(this.id8CacheLeftIcon4)) {
            return true;
        }
        return false;
    }

    private boolean checkLeftIconHasChangeGSID8() {
        if (!GSID8LauncherConstants.leftIcon3.equals(this.id8CacheLeftIcon3) || !GSID8LauncherConstants.leftIcon4.equals(this.id8CacheLeftIcon4) || !GSID8LauncherConstants.leftIcon5.equals(this.id8CacheLeftIcon5)) {
            return true;
        }
        return false;
    }

    private void cacheCardsSeq(List<String> nameList) {
        this.id8CacheCardSeq.clear();
        this.id8CacheCardSeq.addAll(nameList);
    }

    private void cacheCardsSeqGSID8(List<String> nameList) {
        this.gsId8CacheCardSeq.clear();
        this.gsId8CacheCardSeq.addAll(nameList);
    }

    private void refreshFragmentCards(List<String> nameList) {
        View cardView;
        Log.w(TAG, "refreshFragmentCards: ");
        this.bmwId8UiMainLayoutBinding.llContainer.removeAllViews();
        boolean trdAppCountIsMax = displayTrdAppsCount(nameList) >= 5;
        for (String cardName : nameList) {
            if (cardName.equals("ADD WIDGET") && trdAppCountIsMax) {
                break;
            }
            if (cardName.startsWith("3rd")) {
                cardView = get3rdAppView(cardName);
            } else {
                cardView = getSystemCardView(cardName);
            }
            Log.w(TAG, "cardName: " + cardName);
            if (cardView != null) {
                this.bmwId8UiMainLayoutBinding.llContainer.addView(cardView);
            }
        }
        this.bmwId8UiMainLayoutBinding.llContainer.getChildAt(0).findViewById(R.id.iv_mask).setNextFocusLeftId(R.id.ll_left_1);
    }

    private void refreshFragmentCardsGSID8(List<String> nameList) {
        View cardView;
        Log.w(TAG, "refreshFragmentCards: ");
        this.bmwId8GsUiMainLayoutBinding.llContaine.removeAllViews();
        boolean trdAppCountIsMax = displayTrdAppsCount(nameList) >= 5;
        for (String cardName : nameList) {
            if (cardName.equals("ADD WIDGET") && trdAppCountIsMax) {
                break;
            }
            if (cardName.startsWith("3rd")) {
                cardView = get3rdAppView(cardName);
            } else {
                cardView = getSystemGsCardView(cardName);
            }
            Log.w(TAG, "cardName: " + cardName);
            if (cardView != null) {
                this.bmwId8GsUiMainLayoutBinding.llContaine.addView(cardView);
            }
        }
        this.bmwId8GsUiMainLayoutBinding.llContaine.getChildAt(0).findViewById(R.id.iv_mask).setNextFocusLeftId(R.id.ll_left_4);
    }

    private View getSystemCardView(String cardName) {
        View cardView = this.systemCardViewHashMap.get(cardName);
        if (cardView != null) {
            return cardView;
        }
        char c = 65535;
        switch (cardName.hashCode()) {
            case -1591043536:
                if (cardName.equals("SETTING")) {
                    c = 8;
                    break;
                }
                break;
            case -1409845903:
                if (cardName.equals("NAVIGATE")) {
                    c = 0;
                    break;
                }
                break;
            case 73532672:
                if (cardName.equals("MODUS")) {
                    c = 4;
                    break;
                }
                break;
            case 73725445:
                if (cardName.equals("MUSIC")) {
                    c = 2;
                    break;
                }
                break;
            case 76105038:
                if (cardName.equals("PHONE")) {
                    c = 5;
                    break;
                }
                break;
            case 81665115:
                if (cardName.equals("VIDEO")) {
                    c = 7;
                    break;
                }
                break;
            case 741767578:
                if (cardName.equals("CAR INFO")) {
                    c = 3;
                    break;
                }
                break;
            case 906286787:
                if (cardName.equals("ADD WIDGET")) {
                    c = 9;
                    break;
                }
                break;
            case 1738734196:
                if (cardName.equals("DASHBOARD")) {
                    c = 6;
                    break;
                }
                break;
            case 1941423060:
                if (cardName.equals("WEATHER")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                NavigateDataBinding navigateDataBinding = (NavigateDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_navigate, this.bmwId8UiMainLayoutBinding.llContainer, false);
                navigateDataBinding.setNavigateViewModel(this.bmwId8ViewModel);
                navigateDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = navigateDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 1:
                WeatherDataBinding weatherDataBinding = (WeatherDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_weather, this.bmwId8UiMainLayoutBinding.llContainer, false);
                weatherDataBinding.setWeatherViewModel(this.bmwId8ViewModel);
                weatherDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = weatherDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 2:
                MusicDataBinding musicDataBinding = (MusicDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_music, this.bmwId8UiMainLayoutBinding.llContainer, false);
                musicDataBinding.setMediaViewModel(this.bmwId8ViewModel);
                musicDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = musicDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 3:
                CarInfoDataBinding carInfoDataBinding = (CarInfoDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_car_info, this.bmwId8UiMainLayoutBinding.llContainer, false);
                carInfoDataBinding.setCarViewModel(this.bmwId8ViewModel);
                carInfoDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = carInfoDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 4:
                ModusDataBinding modusDataBinding = (ModusDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_modus, this.bmwId8UiMainLayoutBinding.llContainer, false);
                modusDataBinding.setModusViewModel(this.bmwId8ViewModel);
                modusDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = modusDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 5:
                PhoneDataBinding phoneDataBinding = (PhoneDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_phone, this.bmwId8UiMainLayoutBinding.llContainer, false);
                phoneDataBinding.setBtViewModel(this.bmwId8ViewModel);
                phoneDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = phoneDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 6:
                DashboardDataBinding dashboardDataBinding = (DashboardDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_dashboard, this.bmwId8UiMainLayoutBinding.llContainer, false);
                dashboardDataBinding.setDashboardViewModel(this.bmwId8ViewModel);
                dashboardDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = dashboardDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 7:
                VideoDataBinding videoDataBinding = (VideoDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_video, this.bmwId8UiMainLayoutBinding.llContainer, false);
                videoDataBinding.setMediaViewModel(this.bmwId8ViewModel);
                videoDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = videoDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 8:
                View settingFragment = View.inflate(this, R.layout.fragment_setting, (ViewGroup) null);
                settingFragment.findViewById(R.id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
                settingFragment.findViewById(R.id.iv_mask).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        MainActivity.this.bmwId8ViewModel.openSettings(v);
                    }
                });
                cardView = settingFragment;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 9:
                View addWidgetFragment = View.inflate(this, R.layout.fragment_add_widget, (ViewGroup) null);
                addWidgetFragment.findViewById(R.id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
                addWidgetFragment.findViewById(R.id.iv_mask).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        MainActivity.this.bmwId8ViewModel.addWidget(v);
                    }
                });
                cardView = addWidgetFragment;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
        }
        setID8CardBGSelector(cardName, cardView, ID8LauncherConstants.loadCurrentSkin());
        return cardView;
    }

    private View getSystemGsCardView(String cardName) {
        View cardView = this.systemCardViewHashMap.get(cardName);
        if (cardView != null) {
            return cardView;
        }
        char c = 65535;
        switch (cardName.hashCode()) {
            case -1591043536:
                if (cardName.equals("SETTING")) {
                    c = 4;
                    break;
                }
                break;
            case 73532672:
                if (cardName.equals("MODUS")) {
                    c = 7;
                    break;
                }
                break;
            case 73725445:
                if (cardName.equals("MUSIC")) {
                    c = 1;
                    break;
                }
                break;
            case 76105038:
                if (cardName.equals("PHONE")) {
                    c = 2;
                    break;
                }
                break;
            case 81665115:
                if (cardName.equals("VIDEO")) {
                    c = 5;
                    break;
                }
                break;
            case 741767578:
                if (cardName.equals("CAR INFO")) {
                    c = 3;
                    break;
                }
                break;
            case 906286787:
                if (cardName.equals("ADD WIDGET")) {
                    c = 8;
                    break;
                }
                break;
            case 1738734196:
                if (cardName.equals("DASHBOARD")) {
                    c = 0;
                    break;
                }
                break;
            case 1941423060:
                if (cardName.equals("WEATHER")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                DashboardDataGsBinding dashboardDataGsBinding = (DashboardDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_dashboard_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                dashboardDataGsBinding.setDashboardViewModel(this.bmwId8ViewModel);
                dashboardDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = dashboardDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 1:
                MusicDataGsBinding musicDataGsBinding = (MusicDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_music_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                musicDataGsBinding.setMediaViewModel(this.bmwId8ViewModel);
                musicDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = musicDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 2:
                PhoneDataGsBinding phoneDataGsBinding = (PhoneDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_phone_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                phoneDataGsBinding.setBtViewModel(this.bmwId8ViewModel);
                phoneDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = phoneDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 3:
                CarInfoDataGsBinding carInfoDataGsBinding = (CarInfoDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_car_info_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                carInfoDataGsBinding.setCarViewModel(this.bmwId8ViewModel);
                carInfoDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = carInfoDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 4:
                View settingFragment = View.inflate(this, R.layout.fragment_setting_gs, (ViewGroup) null);
                settingFragment.findViewById(R.id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
                settingFragment.findViewById(R.id.iv_mask).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        MainActivity.this.bmwId8ViewModel.openSettings(v);
                    }
                });
                cardView = settingFragment;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 5:
                VideoDataGsBinding videoDataGsBinding = (VideoDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_video_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                videoDataGsBinding.setMediaViewModel(this.bmwId8ViewModel);
                videoDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = videoDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 6:
                WeatherDataGsBinding weatherGsDataBinding = (WeatherDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_weather_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                weatherGsDataBinding.setWeatherViewModel(this.bmwId8ViewModel);
                weatherGsDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = weatherGsDataBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 7:
                ModusDataGsBinding modusDataGsBinding = (ModusDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_modus_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                modusDataGsBinding.setModusViewModel(this.bmwId8ViewModel);
                modusDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = modusDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 8:
                View addWidgetFragment = View.inflate(this, R.layout.fragment_add_widget_gs, (ViewGroup) null);
                addWidgetFragment.findViewById(R.id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
                addWidgetFragment.findViewById(R.id.iv_mask).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        MainActivity.this.bmwId8ViewModel.addWidget(v);
                    }
                });
                cardView = addWidgetFragment;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
        }
        setID8GsCardBGSelector(cardName, cardView, ID8LauncherConstants.loadCurrentSkin());
        return cardView;
    }

    private int displayTrdAppsCount(List<String> nameList) {
        int count = 0;
        for (String cardName : nameList) {
            if (cardName.startsWith("3rd")) {
                count++;
            }
        }
        return count;
    }

    private boolean checkCardSeqHasChange(List<String> nameList) {
        if (this.id8CacheCardSeq.size() != nameList.size()) {
            return true;
        }
        for (int i = 0; i < this.id8CacheCardSeq.size(); i++) {
            if (!this.id8CacheCardSeq.get(i).equals(nameList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCardSeqHasChangeGSID8(List<String> nameList) {
        if (this.gsId8CacheCardSeq.size() != nameList.size()) {
            return true;
        }
        for (int i = 0; i < this.gsId8CacheCardSeq.size(); i++) {
            if (!this.gsId8CacheCardSeq.get(i).equals(nameList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDisplayAppsHasUninstall(List<String> nameList) {
        List<String> uninstallList = new ArrayList<>();
        for (String cardName : nameList) {
            if (cardName.startsWith("3rd") && !AppInfoUtils.isAppPakExist(this, cardName.substring(4).split(",")[0])) {
                uninstallList.add(cardName);
            }
        }
        for (String cardName2 : uninstallList) {
            nameList.remove(cardName2);
        }
        if (uninstallList.size() != 0) {
            return true;
        }
        return false;
    }

    private View get3rdAppView(String cardName) {
        View cardView;
        String str = cardName;
        View cardView2 = this.trdCardViewHashMap.get(str);
        if (cardView2 != null) {
            return cardView2;
        }
        if (UiThemeUtils.isBMW_ID8_UI(this)) {
            cardView = View.inflate(this, R.layout.fragment_3rd, (ViewGroup) null);
            cardView.findViewById(R.id.iv_3rd_bg).setBackground(getDrawable(R.drawable.id8_main_icon_music));
        } else {
            cardView = View.inflate(this, R.layout.fragment_3rd_gs, (ViewGroup) null);
            cardView.findViewById(R.id.iv_3rd_bg).setBackground(getDrawable(R.drawable.gs_id8_main_icon_3app));
        }
        cardView.findViewById(R.id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
        String[] split = str.substring(4).split(",");
        final String pkg = split[0];
        boolean z = true;
        final String cls = split[1];
        TextView tvName = (TextView) cardView.findViewById(R.id.tv_name);
        ImageView iv = (ImageView) cardView.findViewById(R.id.iv);
        ResolveInfo resolveInfo = AppInfoUtils.findAppByPkgAndCls(this, pkg, cls);
        PackageManager pm = KswApplication.appContext.getPackageManager();
        String str2 = TAG;
        StringBuilder append = new StringBuilder().append("get3rdAppView: resolveInfo == null");
        if (resolveInfo != null) {
            z = false;
        }
        Log.w(str2, append.append(z).append(", pkg : ").append(pkg).append(", cls : ").append(cls).toString());
        if (resolveInfo == null) {
            tvName.setText(cls);
            iv.setImageDrawable((Drawable) null);
        } else {
            tvName.setText(resolveInfo.loadLabel(pm).toString());
            iv.setImageDrawable(resolveInfo.loadIcon(pm));
        }
        cardView.findViewById(R.id.iv_mask).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.bmwId8ViewModel.openAppTask(new ComponentName(pkg, cls));
                MainActivity.this.bmwId8ViewModel.addLastViewFocused(view);
                MainActivity.this.bmwId8ViewModel.sendMcuCommand();
            }
        });
        this.trdCardViewHashMap.put(str, cardView);
        String skinName = ID8LauncherConstants.loadCurrentSkin();
        if (UiThemeUtils.isBMW_ID8_UI(this)) {
            setID8CardBGSelector(str, cardView, skinName);
            setID8TrdCardSkinResources(cardView, skinName);
        } else {
            setID8GsCardBGSelector(str, cardView, skinName);
        }
        return cardView;
    }

    /* access modifiers changed from: private */
    public void setID8TrdCardSkinResources(View cardView, String skinName) {
        int titleColorRes;
        int dividerRes;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.iv_3rd_divider);
        TextView textView = (TextView) cardView.findViewById(R.id.tv_name);
        if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
            dividerRes = R.drawable.id8_main_icon_weather_line;
            titleColorRes = R.color.id8_main_style_color_blue;
        } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            dividerRes = R.drawable.id8_main_icon_weather_line_r;
            titleColorRes = R.color.id8_main_style_color_red;
        } else {
            dividerRes = R.drawable.id8_main_icon_weather_line_y;
            titleColorRes = R.color.id8_main_style_color_yellow;
        }
        imageView.setBackground(getResources().getDrawable(dividerRes));
        textView.setTextColor(getResources().getColor(titleColorRes));
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
        this.viewpagerAudiMib3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int pageIndex) {
                Log.d("onPageSelected", "0000000000");
                if (pageIndex == 0) {
                    BcVieModel bcVieModel = MainActivity.mAudiMbi3BcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FragmentOne audiMib3FragmentOne = (AudiMib3FragmentOne) MainActivity.audiMib3ViewPagerAdapter.fragmentPage1;
                        BcVieModel bcVieModel2 = MainActivity.mAudiMbi3BcViewModel;
                        AudiMib3FragmentOne.setItemSelected(BcVieModel.viewLastSel);
                        MainActivity.this.setIndicatorBackground(pageIndex, 4);
                    }
                }
                if (pageIndex == 1) {
                    BcVieModel bcVieModel3 = MainActivity.mAudiMbi3BcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FragmentTwo audiMib3FragmentTwo = (AudiMib3FragmentTwo) MainActivity.audiMib3ViewPagerAdapter.fragmentPage2;
                        BcVieModel bcVieModel4 = MainActivity.mAudiMbi3BcViewModel;
                        AudiMib3FragmentTwo.setItemSelected(BcVieModel.viewLastSel);
                    }
                }
                MainActivity.this.setIndicatorBackground(pageIndex, 4);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(4);
    }

    /* access modifiers changed from: protected */
    public void initAudiMbi3ViewV2() {
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
        audiMib3ViewPagerAdapterv2 = new AudiMib3ViewPagerAdapterV2(getSupportFragmentManager());
        ViewPager viewPager = audiMib3Binding.viewPageAudiMib3;
        this.viewpagerAudiMib3 = viewPager;
        viewPager.setAdapter(audiMib3ViewPagerAdapterv2);
        this.viewpagerAudiMib3.setCurrentItem(0);
        this.viewpagerAudiMib3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int pageIndex) {
                Log.d("onPageSelected", "0000000000");
                if (pageIndex == 0) {
                    BcVieModel bcVieModel = MainActivity.mAudiMbi3BcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FragmentOneV2 audiMib3FragmentOneV2 = (AudiMib3FragmentOneV2) MainActivity.audiMib3ViewPagerAdapterv2.fragmentPage1;
                        BcVieModel bcVieModel2 = MainActivity.mAudiMbi3BcViewModel;
                        AudiMib3FragmentOneV2.setItemSelected(BcVieModel.viewLastSel);
                        MainActivity.this.setIndicatorBackground(pageIndex, 4);
                    }
                }
                if (pageIndex == 1) {
                    BcVieModel bcVieModel3 = MainActivity.mAudiMbi3BcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FragmentTwoV2 audiMib3FragmentTwoV2 = (AudiMib3FragmentTwoV2) MainActivity.audiMib3ViewPagerAdapterv2.fragmentPage2;
                        BcVieModel bcVieModel4 = MainActivity.mAudiMbi3BcViewModel;
                        AudiMib3FragmentTwoV2.setItemSelected(BcVieModel.viewLastSel);
                    }
                }
                MainActivity.this.setIndicatorBackground(pageIndex, 4);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(4);
    }

    public void setItemSelected_AudiMib3(View view) {
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
        ActivityMainBenzMbuxBinding benzMbuxBinding = (ActivityMainBenzMbuxBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_benz_mbux);
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
    public void initBenz_MBUX_2021_KSW_View() {
        Log.i(TAG, "initBenz_MBUX_2021_View2: ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        BenzMbux2021KswActivityBinding benzMbux2021KswActivityBinding2 = (BenzMbux2021KswActivityBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_benz_mbux_2021_ksw);
        benzMbux2021KswActivityBinding = benzMbux2021KswActivityBinding2;
        benzMbux2021KswActivityBinding2.setVieModel(mBcVieModel);
        benzMbux2021KswViewPagerAdapter = new BenzMbux2021KswViewPagerAdapter(getSupportFragmentManager());
        this.viewPagerBenzMbux2021Ksw = benzMbux2021KswActivityBinding.benzMbux2021Viewpager;
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.setAdapter(benzMbux2021KswViewPagerAdapter);
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.setCurrentItem(0);
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int pageIndex) {
                LauncherViewModel.mediaInfo.setPageIndex(pageIndex);
                LOGE.E("bg_sel = " + BenzMbux2021Configs.bg_sel + "  pageIndex = " + pageIndex);
                MainActivity.this.setIndicatorBackground(pageIndex, 12);
                if (pageIndex == 0) {
                    LOGE.E("bg_sel pageIndex0 ----------- ");
                    MainActivity.benzMbux2021KswActivityBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
                    return;
                }
                LOGE.E("bg_sel pageIndex1 ----------- ");
                MainActivity.benzMbux2021KswActivityBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(12);
        benzMbux2021KswActivityBinding.setVieModel(mBcVieModel);
        benzMbux2021KswActivityBinding.layoutCoatBenzMbux2021.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LOGE.D("Benz2021KswDialogThemeActivity");
                MainActivity.this.startActivity(new Intent(MainActivity.mainActivity, Benz2021KswDialogThemeActivity.class));
            }
        });
        this.styleIndexKsw = Settings.System.getString(mainActivity.getContentResolver(), "STYLE_INDEX");
        this.bgIndexKsw = Settings.System.getString(mainActivity.getContentResolver(), "BG_INDEX");
        if (TextUtils.isEmpty(this.styleIndexKsw)) {
            this.styleIndexKsw = TxzMessage.TXZ_SHOW;
        }
        if (TextUtils.isEmpty(this.bgIndexKsw)) {
            this.bgIndexKsw = TxzMessage.TXZ_SHOW;
        }
        int iStyleIndex = Integer.parseInt(this.styleIndexKsw);
        int iBgIndex = Integer.parseInt(this.bgIndexKsw);
        LOGE.D("styleIndex = " + this.styleIndexKsw + " iStyleIndex = " + iStyleIndex + " iBgIndex" + iBgIndex);
        if (iStyleIndex == 1) {
            mBcVieModel.itemIconClassical.set(true);
        } else {
            mBcVieModel.itemIconClassical.set(false);
        }
        LauncherViewModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[iBgIndex - 1]));
        LauncherViewModel.mediaInfo.setPicBg((BitmapDrawable) (LauncherViewModel.mediaInfo.pic == null ? BitmapUtil.getDefaultMBUX2021BG_OTHER() : LauncherViewModel.mediaInfo.pic.get()));
        LauncherViewModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[iBgIndex - 1]));
        benzMbux2021KswActivityBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
    }

    /* access modifiers changed from: protected */
    public void initBenz_MBUX_2021_KSW_View_V2() {
        Log.i(TAG, "initBenz_MBUX_2021_KSW_View_V2: ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        BenzMbux2021KswActivityBinding benzMbux2021KswActivityBinding2 = (BenzMbux2021KswActivityBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_benz_mbux_2021_ksw);
        benzMbux2021KswActivityBinding = benzMbux2021KswActivityBinding2;
        benzMbux2021KswActivityBinding2.setVieModel(mBcVieModel);
        benzMbux2021KswV2ViewPagerAdapter = new BenzMbux2021KswV2ViewPagerAdapter(getSupportFragmentManager());
        this.viewPagerBenzMbux2021Ksw = benzMbux2021KswActivityBinding.benzMbux2021Viewpager;
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.setAdapter(benzMbux2021KswV2ViewPagerAdapter);
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.setCurrentItem(0);
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int pageIndex) {
                LauncherViewModel.mediaInfo.setPageIndex(pageIndex);
                LOGE.E("bg_sel = " + BenzMbux2021Configs.bg_sel + "  pageIndex = " + pageIndex);
                MainActivity.this.setIndicatorBackground(pageIndex, 12);
                if (pageIndex == 0) {
                    LOGE.E("bg_sel pageIndex0 ----------- ");
                    MainActivity.benzMbux2021KswActivityBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
                    return;
                }
                LOGE.E("bg_sel pageIndex1 ----------- ");
                MainActivity.benzMbux2021KswActivityBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(12);
        benzMbux2021KswActivityBinding.setVieModel(mBcVieModel);
        benzMbux2021KswActivityBinding.layoutCoatBenzMbux2021.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LOGE.D("Benz2021KswDialogThemeActivity");
                MainActivity.this.startActivity(new Intent(MainActivity.mainActivity, Benz2021KswDialogThemeActivity.class));
            }
        });
        this.styleIndexKsw = Settings.System.getString(mainActivity.getContentResolver(), "STYLE_INDEX");
        this.bgIndexKsw = Settings.System.getString(mainActivity.getContentResolver(), "BG_INDEX");
        if (TextUtils.isEmpty(this.styleIndexKsw)) {
            this.styleIndexKsw = TxzMessage.TXZ_SHOW;
        }
        if (TextUtils.isEmpty(this.bgIndexKsw)) {
            this.bgIndexKsw = TxzMessage.TXZ_SHOW;
        }
        int iStyleIndex = Integer.parseInt(this.styleIndexKsw);
        int iBgIndex = Integer.parseInt(this.bgIndexKsw);
        LOGE.D("styleIndex = " + this.styleIndexKsw + " iStyleIndex = " + iStyleIndex + " iBgIndex" + iBgIndex);
        if (iStyleIndex == 1) {
            mBcVieModel.itemIconClassical.set(true);
        } else {
            mBcVieModel.itemIconClassical.set(false);
        }
        LauncherViewModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[iBgIndex - 1]));
        LauncherViewModel.mediaInfo.setPicBg((BitmapDrawable) (LauncherViewModel.mediaInfo.pic == null ? BitmapUtil.getDefaultMBUX2021BG_OTHER() : LauncherViewModel.mediaInfo.pic.get()));
        LauncherViewModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[iBgIndex - 1]));
        benzMbux2021KswActivityBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
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
                LauncherViewModel.mediaInfo.setPageIndex(pageIndex);
                LOGE.E("bg_sel = " + BenzMbux2021Configs.bg_sel + "  pageIndex = " + pageIndex);
                MainActivity.this.setIndicatorBackground(pageIndex, 1);
                if (pageIndex == 0) {
                    LOGE.E("bg_sel pageIndex0 ----------- ");
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOne.get());
                } else if (pageIndex == 1) {
                    LOGE.E("bg_sel pageIndex1 ----------- ");
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackground((LauncherViewModel.mediaInfo.picBg.get() != null ? LauncherViewModel.mediaInfo.picBg : LauncherViewModel.mediaInfo.picOther).get());
                } else {
                    LOGE.E("bg_sel pageIndex2 ----------- ");
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
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
        this.styleIndex = Settings.System.getString(mainActivity.getContentResolver(), "STYLE_INDEX");
        this.bgIndex = Settings.System.getString(mainActivity.getContentResolver(), "BG_INDEX");
        if (TextUtils.isEmpty(this.styleIndex)) {
            this.styleIndex = TxzMessage.TXZ_SHOW;
        }
        if (TextUtils.isEmpty(this.bgIndex)) {
            this.bgIndex = TxzMessage.TXZ_SHOW;
        }
        int iStyleIndex = Integer.parseInt(this.styleIndex);
        int iBgIndex = Integer.parseInt(this.bgIndex);
        LOGE.D("styleIndex = " + this.styleIndex + " iStyleIndex = " + iStyleIndex + " iBgIndex" + iBgIndex);
        if (iStyleIndex == 1) {
            mBcVieModel.itemIconClassical.set(true);
        } else {
            mBcVieModel.itemIconClassical.set(false);
        }
        LauncherViewModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_ONE[iBgIndex - 1]));
        LauncherViewModel.mediaInfo.setPicBg((BitmapDrawable) (LauncherViewModel.mediaInfo.pic == null ? BitmapUtil.getDefaultMBUX2021BG_OTHER() : LauncherViewModel.mediaInfo.pic.get()));
        LauncherViewModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021Configs.BG_OTHER[iBgIndex - 1]));
        benzMbux2021Binding2.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOne.get());
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
        } else if (TYPE == 12) {
            benzMbux2021KswActivityBinding.indicatorBenzMbux2021.removeAllViews();
        } else if (TYPE == 3) {
            benzNtg6FyBinding.indicatorBenzNtg6Fy.removeAllViews();
        } else if (TYPE == 4) {
            audiMib3Binding.indicatorAudimib3.removeAllViews();
        } else if (TYPE == 10) {
            mib3FyMainLayoutBinding.indicatorAudimib3.removeAllViews();
        } else if (TYPE == 11) {
            audiMib3TyMainLayoutBinding.indicatorAudiMib3Ty.removeAllViews();
        }
        if (TYPE == 1 || TYPE == 2) {
            this.indicatorPoints = new ImageView[this.totalPage];
        } else if (TYPE == 12) {
            this.indicatorPoints = new ImageView[2];
        } else if (TYPE == 3) {
            this.indicatorPoints = new ImageView[2];
        } else if (TYPE == 4) {
            this.indicatorPoints = new ImageView[2];
        } else if (TYPE == 10) {
            this.indicatorPoints = new ImageView[2];
        } else if (TYPE == 11) {
            this.indicatorPoints = new ImageView[2];
        }
        for (int i = 0; i < this.indicatorPoints.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            if (i == 0) {
                if (TYPE == 1 || TYPE == 2) {
                    imageView.setBackgroundResource(R.drawable.mbux2_indicato_sel);
                }
                if (TYPE == 12) {
                    imageView.setBackgroundResource(R.drawable.mbux2_indicato_sel);
                } else if (TYPE == 3) {
                    imageView.setBackgroundResource(R.drawable.fy_mbux_indicato_sel);
                } else if (TYPE == 4) {
                    imageView.setBackgroundResource(R.drawable.audi_mib3_main_indicato_sel);
                } else if (TYPE == 10) {
                    imageView.setBackgroundResource(R.drawable.fy_audi_mib3_main_indicato_sel);
                } else if (TYPE == 11) {
                    imageView.setBackgroundResource(R.drawable.audi_mib3_ty_main_indicato_sel);
                }
            } else {
                if (TYPE == 1 || TYPE == 2) {
                    imageView.setBackgroundResource(R.drawable.mbux2_indicato_n);
                }
                if (TYPE == 12) {
                    imageView.setBackgroundResource(R.drawable.mbux2_indicato_n);
                } else if (TYPE == 3) {
                    imageView.setBackgroundResource(R.drawable.fy_mbux_indicato_n);
                } else if (TYPE == 4) {
                    imageView.setBackgroundResource(R.drawable.audi_mib3_main_indicato_n);
                } else if (TYPE == 10) {
                    imageView.setBackgroundResource(R.drawable.fy_audi_mib3_main_indicato_n);
                } else if (TYPE == 11) {
                    imageView.setBackgroundResource(R.drawable.audi_mib3_ty_main_indicato_n);
                }
            }
            this.indicatorPoints[i] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(-2, -2));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            if (TYPE == 1) {
                this.benzMbux2021Binding.indicatorBenzMbux2021.addView(imageView, layoutParams);
            } else if (TYPE == 2) {
                benzMbux2021Binding2.indicatorBenzMbux2021.addView(imageView, layoutParams);
            } else if (TYPE == 12) {
                benzMbux2021KswActivityBinding.indicatorBenzMbux2021.addView(imageView, layoutParams);
            } else if (TYPE == 3) {
                benzNtg6FyBinding.indicatorBenzNtg6Fy.addView(imageView, layoutParams);
            } else if (TYPE == 4) {
                audiMib3Binding.indicatorAudimib3.addView(imageView, layoutParams);
            } else if (TYPE == 10) {
                mib3FyMainLayoutBinding.indicatorAudimib3.addView(imageView, layoutParams);
            } else if (TYPE == 11) {
                audiMib3TyMainLayoutBinding.indicatorAudiMib3Ty.addView(imageView, layoutParams);
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
                        if (styleType == 1 || styleType == 2 || styleType == 12) {
                            imageViewArr[i].setBackgroundResource(R.drawable.mbux2_indicato_sel);
                        } else if (styleType == 3) {
                            imageViewArr[i].setBackgroundResource(R.drawable.fy_mbux_indicato_sel);
                        } else if (styleType == 4) {
                            imageViewArr[i].setBackgroundResource(R.drawable.audi_mib3_main_indicato_sel);
                        } else if (styleType == 10) {
                            imageViewArr[i].setBackgroundResource(R.drawable.fy_audi_mib3_main_indicato_sel);
                        } else if (styleType == 11) {
                            imageViewArr[i].setBackgroundResource(R.drawable.audi_mib3_ty_main_indicato_sel);
                        }
                    } else if (styleType == 1 || styleType == 2 || styleType == 12) {
                        imageViewArr[i].setBackgroundResource(R.drawable.mbux2_indicato_n);
                    } else if (styleType == 3) {
                        imageViewArr[i].setBackgroundResource(R.drawable.fy_mbux_indicato_n);
                    } else if (styleType == 4) {
                        imageViewArr[i].setBackgroundResource(R.drawable.audi_mib3_main_indicato_n);
                    } else if (styleType == 10) {
                        imageViewArr[i].setBackgroundResource(R.drawable.fy_audi_mib3_main_indicato_n);
                    } else if (styleType == 11) {
                        imageViewArr[i].setBackgroundResource(R.drawable.audi_mib3_ty_main_indicato_n);
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
            this.bgFyIndex = "8";
        }
        int iBgFyIndex = Integer.parseInt(this.bgFyIndex);
        LOGE.D("styleIndex iBgFyIndex" + iBgFyIndex);
        benzNtg6FyBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[iBgFyIndex - 1]);
    }

    /* access modifiers changed from: protected */
    public void initUI_NTG6_FY_ViewV2() {
        Log.i(TAG, "initUI_NTG6_FY_View : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.of((FragmentActivity) this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        benzNtg6FyBinding = (BenzNtg6FyBindCls) DataBindingUtil.setContentView(mainActivity, R.layout.activity_main_benz_ntg6_fy);
        benzNtg6FyViewPagerAdapterV2 = new BenzNtg6FyViewPagerAdapterV2(getSupportFragmentManager());
        this.viewpagerBenzNtg6Fy = benzNtg6FyBinding.benzNtg6FyViewpager;
        benzNtg6FyBinding.benzNtg6FyViewpager.setAdapter(benzNtg6FyViewPagerAdapterV2);
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
                LOGE.D("UINtg6FyThemeActivity))))))))");
                MainActivity.this.startActivity(new Intent(MainActivity.mainActivity, BenzNtg6FyThemeActivity.class));
            }
        });
        String string = Settings.System.getString(mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_INDEX);
        this.bgFyIndex = string;
        if (TextUtils.isEmpty(string)) {
            this.bgFyIndex = "8";
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
        audiViewModel.startWeatherLooper();
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

    public void setCurrentItem(int index2) {
        com.wits.ksw.databinding.MainActivity mainActivity2 = this.bmwBinding;
        if (mainActivity2 != null) {
            mainActivity2.viewPage.setCurrentItem(index2);
        }
        ActivityMainAlsId7Binding activityMainAlsId7Binding = this.alsId7Binding;
        if (activityMainAlsId7Binding != null) {
            activityMainAlsId7Binding.viewPage.setCurrentItem(index2);
        }
        LandroverMainBinding landroverMainBinding = this.landroverBinding;
        if (landroverMainBinding != null) {
            landroverMainBinding.viewPager.setCurrentItem(index2);
        }
        AlsId7UiMainBinding alsId7UiMainBinding2 = alsId7UiMainBinding;
        if (alsId7UiMainBinding2 != null) {
            alsId7UiMainBinding2.viewPage.setCurrentItem(index2);
        }
        MainKswID7Binding mainKswID7Binding = this.kswId7Binding;
        if (mainKswID7Binding != null) {
            mainKswID7Binding.viewPage.setCurrentItem(index2);
        }
        ActivityMainAlsId7V2Binding activityMainAlsId7V2Binding = this.alsId7V2Binding;
        if (activityMainAlsId7V2Binding != null) {
            activityMainAlsId7V2Binding.viewPage.setCurrentItem(index2);
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
            alsID7ViewModel2.addLastViewFocused(this.alsId7Binding.btnApps);
            this.alsID7ViewModel.refreshLastViewFocused();
        }
        LauncherViewModel launcherViewModel2 = this.alsId7UiViewModel;
        if (launcherViewModel2 != null) {
            launcherViewModel2.addLastViewFocused(alsId7UiMainBinding.menuButton1);
            this.alsId7UiViewModel.refreshLastViewFocused();
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
        if (UiThemeUtils.isUI_GS_ID8(this)) {
            Settings.System.putInt(getContentResolver(), "mPageIndex", 4);
        }
        if (UiThemeUtils.isCommon_UI_GS_UG(this)) {
            showLastViewFocus();
        }
        isFront = false;
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            LexusLsConfig.saveDataOrder(mainActivity);
        } else if (UiThemeUtils.isLEXUS_LS_UI_V2(this)) {
            LexusLsConfig.saveDataOrder_V2(mainActivity);
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
        Settings.System.putInt(getContentResolver(), "launcher_main_activity_freedom", 0);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        AlsID7ViewModel alsID7ViewModel2 = this.alsID7ViewModel;
        if (alsID7ViewModel2 != null) {
            alsID7ViewModel2.refreshShortCutInfo();
        }
        LauncherViewModel launcherViewModel = this.alsId7UiViewModel;
        if (launcherViewModel != null) {
            launcherViewModel.refreshShortCutInfo();
        }
    }

    /* access modifiers changed from: private */
    public void updateData(WitsStatus witsStatus) {
        String str = TAG;
        Log.d(str, "updateData: type=====" + witsStatus.type);
        switch (witsStatus.type) {
            case 1:
                AlsID7ViewModel alsID7ViewModel2 = this.alsID7ViewModel;
                if (alsID7ViewModel2 != null) {
                    alsID7ViewModel2.setMusicPlayState(false);
                    this.alsID7ViewModel.setMusicPlayStop(true);
                }
                LauncherViewModel launcherViewModel = this.bmwId8ViewModel;
                if (launcherViewModel != null) {
                    launcherViewModel.setMusicPlayState(false);
                    this.bmwId8ViewModel.setMusicPlayStop(true);
                    this.bmwId8ViewModel.setVideoPlayState(false);
                    return;
                }
                return;
            case 21:
                MusicStatus musicStatus = MusicStatus.getStatusFromJson(witsStatus.getJsonArg());
                boolean isPlay = musicStatus.isPlay();
                if (MediaImpl.getInstance().getMediaInfo() != null) {
                    String musicName = MediaImpl.getInstance().getMediaInfo().musicName.get();
                    int maxProcess = MediaImpl.getInstance().getMediaInfo().maxProgress.get();
                    if ((musicName == null || "".equals(musicName)) && maxProcess == 0) {
                        MediaImpl.getInstance().handleMediaInfo(musicStatus.path);
                    }
                }
                Log.w(str, "updateData: play = " + isPlay + "isStop=" + this.isStop);
                AlsID7ViewModel alsID7ViewModel3 = this.alsID7ViewModel;
                if (alsID7ViewModel3 != null) {
                    if (this.isStop) {
                        alsID7ViewModel3.setMusicPlayState(false);
                    } else {
                        alsID7ViewModel3.setMusicPlayState(isPlay);
                        this.alsID7ViewModel.setMusicPlayStop(false);
                    }
                }
                LauncherViewModel launcherViewModel2 = this.bmwId8ViewModel;
                if (launcherViewModel2 == null) {
                    return;
                }
                if (this.isStop) {
                    launcherViewModel2.setMusicPlayState(false);
                    return;
                }
                launcherViewModel2.setMusicPlayState(isPlay);
                this.bmwId8ViewModel.setMusicPlayStop(false);
                return;
            default:
                return;
        }
    }

    private void musicObserverStatus() {
        if (UiThemeUtils.isUI_GS_ID8(this)) {
            PowerManagerApp.registerIContentObserver("music_stop", this.mId8GsMusicObserver);
        } else {
            PowerManagerApp.registerIContentObserver("music_stop", this.mMusicObserver);
        }
        try {
            PowerManagerApp.getManager().registerCmdListener(new ICmdListener.Stub() {
                public boolean handleCommand(String s) throws RemoteException {
                    return false;
                }

                public void updateStatusInfo(String s) throws RemoteException {
                    WitsStatus witsStatus = WitsStatus.getWitsStatusFormJson(s);
                    Log.w(MainActivity.TAG, "updateStatusInfo: s =" + witsStatus.getType());
                    MainActivity.this.updateData(witsStatus);
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.d(TAG, "updateStatusInfo: end");
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        ActivityGsId8LauncherMainBinding activityGsId8LauncherMainBinding;
        super.onResume();
        int i = 0;
        Settings.System.putInt(getContentResolver(), "mPageIndex", 0);
        String str = TAG;
        Log.i(str, "onResume: ");
        if (Settings.System.getInt(getContentResolver(), "multi_window", -1) == 1) {
            sendBroadcast(new Intent("com.wits.on_multi_window_mode").putExtra("multi_window", false));
        }
        initSaveData();
        if (UiThemeUtils.isCommon_UI_GS_UG(this)) {
            showLastViewFocus();
        } else if (UiThemeUtils.isBMW_NBT(this)) {
            this.nbtModel.refrshInit();
        } else if (UiThemeUtils.isAudi_mib3(this) || UiThemeUtils.isUI_mib3_V2(this)) {
            BcVieModel bcVieModel = mAudiMbi3BcViewModel;
            if (bcVieModel != null) {
                bcVieModel.resumeViewModel();
            }
        } else {
            LauncherViewModel launcherViewModel = this.viewModel;
            if (launcherViewModel != null) {
                launcherViewModel.resumeViewModel();
            }
        }
        LauncherViewModel launcherViewModel2 = this.alsId7UiViewModel;
        if (launcherViewModel2 != null) {
            launcherViewModel2.resumeViewModel();
        }
        AlsID7ViewModel alsID7ViewModel2 = this.alsID7ViewModel;
        if (alsID7ViewModel2 != null) {
            alsID7ViewModel2.resumeViewModel();
            musicObserverStatus();
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
        if (UiThemeUtils.isLEXUS_LS_UI(mainActivity) || UiThemeUtils.isLEXUS_LS_UI_V2(mainActivity)) {
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
            if (viewPager != null) {
                i = viewPager.getCurrentItem();
            }
            mediaInfo.setPageIndex(i);
        }
        if (UiThemeUtils.isALS_ID7_UI(this)) {
            String skinName = Settings.System.getString(mainActivity.getContentResolver(), KswApplication.SKIN_NAME);
            Log.d(TAG, "onResume: skinName------->" + skinName);
            setAlsButtonBg(skinName);
        }
        UiThemeUtils.isBMW_EVO_ID7_V2(this);
        if (UiThemeUtils.isBMW_ID8_UI(this)) {
            LauncherViewModel launcherViewModel3 = this.bmwId8ViewModel;
            if (!(launcherViewModel3 == null || this.onID8SkinChangeListener == null)) {
                launcherViewModel3.resumeViewModel();
                this.bmwId8ViewModel.initSkinData(this.onID8SkinChangeListener);
            }
            id8ExitModusPage();
            beforeRefreshFragmentCards();
            beforeRefreshLeftBarIcon();
        }
        if (UiThemeUtils.isUI_GS_ID8(this)) {
            gsid8HandlerFreedom();
            Handler handler2 = this.handler;
            if (!(handler2 == null || (activityGsId8LauncherMainBinding = this.bmwId8GsUiMainLayoutBinding) == null)) {
                handler2.sendMessageDelayed(handler2.obtainMessage(this.touchEventId, activityGsId8LauncherMainBinding.gsScrollView), 50);
            }
            if (!(this.bmwId8ViewModel == null || this.onGsID8SkinChangeListener == null)) {
                musicObserverStatus();
                this.bmwId8ViewModel.resumeViewModel();
                this.bmwId8ViewModel.initSkinData(this.onGsID8SkinChangeListener);
            }
            beforeRefreshFragmentCardsGSID8();
            beforeRefreshLeftBarIcon();
            setID8GsMusicBGSelector(ID8LauncherConstants.loadCurrentSkin());
            Settings.System.putInt(getContentResolver(), "launcher_main_activity_freedom", 1);
        }
        if (KswUtils.ismph()) {
            McuImpl.getInstance().getCarInfo().speedUnit.set("mph");
        } else {
            McuImpl.getInstance().getCarInfo().speedUnit.set("km/h");
        }
    }

    private void gsid8HandlerFreedom() {
        if (TextUtils.isEmpty(Settings.System.getString(getContentResolver(), "ro_zlink_land_display"))) {
            try {
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                String display = dm.widthPixels + "x" + dm.heightPixels;
                Runtime.getRuntime().exec("setprop  ro.zlink.land.display " + display);
                Settings.System.putString(getContentResolver(), "ro_zlink_land_display", display);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Settings.System.putInt(getContentResolver(), "mPageIndex", 1);
        String freedomPkg = Settings.System.getString(getContentResolver(), "wits_freedom_pkg");
        try {
            if (TextUtils.isEmpty(freedomPkg) || !KswUtils.isAppInstalled(this, freedomPkg) || (freedomPkg.equals(IconUtils.GOOGLE_MAP) && PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP) == 0)) {
                freedomPkg = getGsId8FreedomPkg();
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        try {
            if (TextUtils.isEmpty(freedomPkg)) {
                return;
            }
            if (!settingLanguage) {
                int memoryMode = Settings.System.getInt(getContentResolver(), "memory_mode_for_freedom");
                if (this.isID8FirstStart) {
                    if (memoryMode != 1) {
                        this.bmwId8ViewModel.launchApp(freedomPkg, 5);
                    }
                    this.isID8FirstStart = false;
                } else {
                    this.bmwId8ViewModel.launchApp(freedomPkg, 5);
                }
                if (memoryMode != 0) {
                    Settings.System.putInt(getContentResolver(), "memory_mode_for_freedom", 0);
                }
                return;
            }
            settingLanguage = false;
        } catch (Settings.SettingNotFoundException e3) {
            this.bmwId8ViewModel.launchApp(freedomPkg, 5);
        }
    }

    private String getGsId8FreedomPkg() {
        String freedomPkg = null;
        String[] strArr = AppInfoUtils.PKG_FREEDOM_MAP;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String pkg = strArr[i];
            if (pkg.equals(IconUtils.GOOGLE_MAP)) {
                try {
                    if (PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP) == 1) {
                        freedomPkg = pkg;
                        break;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else if (KswUtils.isAppInstalled(this, pkg)) {
                freedomPkg = pkg;
                break;
            }
            i++;
        }
        Settings.System.putString(getContentResolver(), "wits_freedom_pkg", freedomPkg);
        FileUtils.savaStringData(KeyConfig.NAVI_DEFUAL, freedomPkg);
        return freedomPkg;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean firstFocus;
        boolean llContainerFocus;
        PopupWindow popupWindow;
        ActivityMainAlsId7Binding activityMainAlsId7Binding;
        ActivityMainAlsId7Binding activityMainAlsId7Binding2;
        Log.d(TAG, "onKey dispatchKeyEvent " + event.getKeyCode() + ", action: " + event.getAction());
        int keyCode = event.getKeyCode();
        if (UiThemeUtils.isID7_ALS(this) && ClientManager.getInstance().isAls6208Client() && keyCode == 20 && event.getAction() == 0 && (activityMainAlsId7Binding2 = this.alsId7Binding) != null && this.alsID7ViewModel != null && activityMainAlsId7Binding2.btnApps.isFocused()) {
            this.alsId7Binding.viewPage.setCurrentItem(0);
        }
        if (UiThemeUtils.isID7_ALS_V2(this) && ClientManager.getInstance().isAls6208Client() && keyCode == 20 && event.getAction() == 0 && (activityMainAlsId7Binding = this.alsId7Binding) != null && this.alsID7ViewModel != null && activityMainAlsId7Binding.btnApps.isFocused()) {
            this.alsId7Binding.viewPage.setCurrentItem(0);
        }
        if (UiThemeUtils.isLEXUS_UI(this)) {
            if (keyCode == 20) {
                Log.d("ttest", "down");
                return super.dispatchKeyEvent(new KeyEvent(event.getAction(), 22));
            } else if (keyCode == 19) {
                Log.d("ttest", "up");
                return super.dispatchKeyEvent(new KeyEvent(event.getAction(), 21));
            }
        }
        if (UiThemeUtils.isLEXUS_LS_UI(this) || UiThemeUtils.isLEXUS_LS_UI_V2(this)) {
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
        if ((UiThemeUtils.isAudi_mib3(this) || UiThemeUtils.isUI_mib3_V2(this)) && keyCode == 22) {
            Log.d("ttest onKey isAudi_mib3", "right");
            if (event.getAction() == 0 && (mAudiMbi3BcViewModel.lastViewFocused == audiMib3Binding.include.ivMusicAudimib3 || mAudiMbi3BcViewModel.lastViewFocused == audiMib3Binding.include.ivNaviAudimib3 || mAudiMbi3BcViewModel.lastViewFocused == audiMib3Binding.include.ivBtAudimib3 || mAudiMbi3BcViewModel.lastViewFocused == audiMib3Binding.include.ivSetAudimib3 || mAudiMbi3BcViewModel.lastViewFocused == audiMib3Binding.include.ivCarAudimib3)) {
                Log.d("ttest onKey isAudi_mib3", "mAudiMbi3BcViewModel.lastViewFocused ==  ");
                if (this.viewpagerAudiMib3.getCurrentItem() != 0) {
                    if (UiThemeUtils.isUI_mib3_V2(this)) {
                        AudiMib3FragmentTwoV2.bindingTwo.dvrItemview.requestFocus();
                        AudiMib3FragmentTwoV2.bindingTwo.dvrItemview.setFocusable(true);
                    } else {
                        AudiMib3FragmentTwo.bindingTwo.dvrItemview.requestFocus();
                        AudiMib3FragmentTwo.bindingTwo.dvrItemview.setFocusable(true);
                    }
                    mAudiMbi3BcViewModel.lastViewFocused = null;
                    return true;
                }
            }
        }
        if (UiThemeUtils.isBenz_MBUX_2021(this) && keyCode == 66 && (popupWindow = this.mPopupThemeWindow) != null) {
            popupWindow.dismiss();
        }
        if ((UiThemeUtils.isBMW_ID8_UI(this) || UiThemeUtils.isUI_GS_ID8(this)) && event.getAction() == 0) {
            if (UiThemeUtils.isBMW_ID8_UI(this)) {
                llContainerFocus = this.bmwId8UiMainLayoutBinding.llContainer.hasFocus();
                firstFocus = this.bmwId8UiMainLayoutBinding.llContainer.getChildAt(0).hasFocus();
            } else {
                llContainerFocus = this.bmwId8GsUiMainLayoutBinding.llContaine.hasFocus();
                firstFocus = this.bmwId8GsUiMainLayoutBinding.llContaine.getChildAt(0).hasFocus();
                Handler handler2 = this.handler;
                handler2.sendMessageDelayed(handler2.obtainMessage(this.touchEventId, this.bmwId8GsUiMainLayoutBinding.gsScrollView), 50);
            }
            if (keyCode == 19 && llContainerFocus && !firstFocus) {
                KeyUtils.pressKey(21);
                return true;
            } else if (keyCode == 20 && llContainerFocus) {
                KeyUtils.pressKey(22);
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String str = TAG;
        Log.d(str, " onKeyDown " + keyCode);
        if (UiThemeUtils.isBenz_NTG6(this) && keyCode == 311) {
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
        if (UiThemeUtils.isBMW_ID8_UI(this) && this.bmwId8ViewModel != null) {
            id8ExitModusPage();
        }
    }

    private void id8ExitModusPage() {
        Boolean aBoolean = this.bmwId8ViewModel.isChangeModusStatusID8.get();
        if (aBoolean != null && aBoolean.booleanValue()) {
            this.bmwId8ViewModel.exitChangeModus();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy:");
        this.viewModel = null;
        this.alsID7ViewModel = null;
        this.alsId7UiViewModel = null;
        this.bmwId8ViewModel = null;
        PowerManagerApp.unRegisterIContentObserver(this.mMusicObserver);
        PowerManagerApp.unRegisterIContentObserver(this.mId8GsMusicObserver);
        try {
            WeatherQueryManager.getInstance().doOnDestroy(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
