package com.wits.ksw;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.p001v4.view.ViewPager;
import android.support.p004v7.widget.DefaultItemAnimator;
import android.support.p004v7.widget.GridLayoutManager;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.LinearSnapHelper;
import android.support.p004v7.widget.RecyclerView;
import android.support.p004v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.wits.ksw.databinding.ActivityPempId8LauncherMainBinding;
import com.wits.ksw.databinding.ActivityRomeoBinding;
import com.wits.ksw.databinding.ActivityRomeoV2Binding;
import com.wits.ksw.databinding.AlsId7UiMainBinding;
import com.wits.ksw.databinding.AudiMib3FyMainLayoutBinding;
import com.wits.ksw.databinding.AudiMib3MainLayoutBinding;
import com.wits.ksw.databinding.AudiMib3TyMainLayoutBinding;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding2;
import com.wits.ksw.databinding.BenzMbux2021KswActivityBinding;
import com.wits.ksw.databinding.BenzMbuxAppsCardBinding;
import com.wits.ksw.databinding.BenzMbuxBtCardBinding;
import com.wits.ksw.databinding.BenzMbuxCarinfoCardBinding;
import com.wits.ksw.databinding.BenzMbuxDashboardCardBinding;
import com.wits.ksw.databinding.BenzMbuxDvrCardBinding;
import com.wits.ksw.databinding.BenzMbuxKswActivityNewBinding;
import com.wits.ksw.databinding.BenzMbuxMusicCardBinding;
import com.wits.ksw.databinding.BenzMbuxNaviCardBinding;
import com.wits.ksw.databinding.BenzMbuxSetCardBinding;
import com.wits.ksw.databinding.BenzMbuxVideoCardBinding;
import com.wits.ksw.databinding.BenzMbuxWeatherCardBinding;
import com.wits.ksw.databinding.BenzMbuxZlinkCardBinding;
import com.wits.ksw.databinding.BenzNtg6FyBindCls;
import com.wits.ksw.databinding.BenzNtgFyActivityNewBinding;
import com.wits.ksw.databinding.BenzNtgFyAppsCardBinding;
import com.wits.ksw.databinding.BenzNtgFyBtCardBinding;
import com.wits.ksw.databinding.BenzNtgFyCarinfoCardBinding;
import com.wits.ksw.databinding.BenzNtgFyDashboardCardBinding;
import com.wits.ksw.databinding.BenzNtgFyDvrCardBinding;
import com.wits.ksw.databinding.BenzNtgFyMusicCardBinding;
import com.wits.ksw.databinding.BenzNtgFyNaviCardBinding;
import com.wits.ksw.databinding.BenzNtgFySetCardBinding;
import com.wits.ksw.databinding.BenzNtgFyVideoCardBinding;
import com.wits.ksw.databinding.BenzNtgFyWeatherCardBinding;
import com.wits.ksw.databinding.BenzNtgFyZlinkCardBinding;
import com.wits.ksw.databinding.CarInfoDataBinding;
import com.wits.ksw.databinding.CarInfoDataGsBinding;
import com.wits.ksw.databinding.CarInfoPempDataBinding;
import com.wits.ksw.databinding.DashboardDataBinding;
import com.wits.ksw.databinding.DashboardDataGsBinding;
import com.wits.ksw.databinding.DashboardPempDataBinding;
import com.wits.ksw.databinding.ID5MaindBind;
import com.wits.ksw.databinding.LandroverMainBinding;
import com.wits.ksw.databinding.LexusLsDragMainLayoutBinding;
import com.wits.ksw.databinding.LexusLsMainLayoutBinding;
import com.wits.ksw.databinding.MainKswID7Binding;
import com.wits.ksw.databinding.ModusDataBinding;
import com.wits.ksw.databinding.ModusDataGsBinding;
import com.wits.ksw.databinding.ModusPempDataBinding;
import com.wits.ksw.databinding.MusicDataBinding;
import com.wits.ksw.databinding.MusicDataGsBinding;
import com.wits.ksw.databinding.MusicPempDataBinding;
import com.wits.ksw.databinding.NavigateDataBinding;
import com.wits.ksw.databinding.NavigatePempDataBinding;
import com.wits.ksw.databinding.PhoneDataBinding;
import com.wits.ksw.databinding.PhoneDataGsBinding;
import com.wits.ksw.databinding.PhonePempDataBinding;
import com.wits.ksw.databinding.VideoDataBinding;
import com.wits.ksw.databinding.VideoDataGsBinding;
import com.wits.ksw.databinding.VideoPempDataBinding;
import com.wits.ksw.databinding.WeatherDataBinding;
import com.wits.ksw.databinding.WeatherDataGsBinding;
import com.wits.ksw.databinding.WeatherPempDataBinding;
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
import com.wits.ksw.launcher.adpater.RomeoMainListAdapterV2;
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
import com.wits.ksw.launcher.bmw_id8_ui.PempID8LauncherConstants;
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
import com.wits.ksw.launcher.model.RomeoViewModelV2;
import com.wits.ksw.launcher.pagerlayoutmanager.PagerGridLayoutManager;
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
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021ViewPagerAdapter;
import com.wits.ksw.launcher.view.benzmbux2021ksw.Benz2021KswDialogThemeActivity;
import com.wits.ksw.launcher.view.benzmbux2021ksw.adapter.BenzMbux2021KswViewPagerAdapter;
import com.wits.ksw.launcher.view.benzmbux2021ksw.bean.BenzMbux2021KswConfigs;
import com.wits.ksw.launcher.view.benzmbux2021kswv2.adapter.BenzMbux2021KswV2ViewPagerAdapter;
import com.wits.ksw.launcher.view.benzmbux2021new.adapter.BenzCardPagerAdapter;
import com.wits.ksw.launcher.view.benzmbux2021new.adapter.Benzmbux2021newAdapter;
import com.wits.ksw.launcher.view.benzmbux2021new.bean.BenzCardMenu;
import com.wits.ksw.launcher.view.benzmbux2021new.util.BenzUtils;
import com.wits.ksw.launcher.view.benzmbux2021new.util.MyItemTouchHelper;
import com.wits.ksw.launcher.view.benzmbux2021new.util.PagingScrollHelper;
import com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyConfigs;
import com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyThemeActivity;
import com.wits.ksw.launcher.view.benzntg6fy.BenzNtg6FyViewPagerAdapter;
import com.wits.ksw.launcher.view.benzntg6fynew.adapter.BenzNtgfyNewAdapter;
import com.wits.ksw.launcher.view.benzntg6fynew.util.BenzNtgItemTouchHelper;
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
import com.wits.ksw.launcher.view.p006ug.UgPager;
import com.wits.ksw.launcher.view.p006ug.UgViewPager;
import com.wits.ksw.launcher.view.p006ug.WiewFocusUtils;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes17.dex */
public class MainActivity extends BaseThemeActivity {
    private static final int CLICK_TIME = 1500;
    private static final int MSG_HIDE_POP = 1;
    private static final int MSG_SHOW_POP = 0;
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
    private static long lastTimeClick;
    private static final int lineWidth = 0;
    public static BcVieModel mAudiMbi3BcViewModel;
    public static BcVieModel mAudiMbi3FyBcViewModel;
    public static BcVieModel mAudiMib3TyBcViewModel;
    public static BcVieModel mBcVieModel;
    public static BenzMbuxKswActivityNewBinding mBenzMbuxKswActivityNewBinding;
    public static BenzNtgFyActivityNewBinding mBenzNtgFyActivityNewBinding;
    public static MainActivity mainActivity;
    public static AudiMib3FyMainLayoutBinding mib3FyMainLayoutBinding;
    private LexusLsDragItemAdapter adapterLexusLsDrag;
    private ActivityMainAlsId6Binding alsBinding;
    private AlsID7ViewModel alsID7ViewModel;
    private ActivityMainAlsId7Binding alsId7Binding;
    private LauncherViewModel alsId7UiViewModel;
    private ActivityMainAlsId7V2Binding alsId7V2Binding;
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
    private BenzMbux2021ActivityBinding benzMbux2021Binding;
    public com.wits.ksw.databinding.MainActivity bmwBinding;
    private BmwId6CuspViewPagerAdpater bmwId6CuspViewPagerAdpater;
    private BmwId6ViewPagerAdpater bmwId6ViewPagerAdpater;
    private ActivityGsId8LauncherMainBinding bmwId8GsUiMainLayoutBinding;
    private ActivityId8LauncherMainBinding bmwId8UiMainLayoutBinding;
    private LauncherViewModel bmwId8ViewModel;
    private ActivityPempId8LauncherMainBinding bmwPempId8UiMainLayoutBinding;
    private int cardWith;
    private int currentOldScrollX;
    private int currentScrollX;
    private int currentScrollY;
    public int dialogHeight;
    public int dialogWidth;
    public ActivityMainGsug2Binding gsug2Binding;
    private HorizontalPageLayoutManager horizhontalPageLayoutManager;
    public ID5MaindBind id5MaindBind;
    public ViewPager id6CuspMainViewPager;
    ImageView id6LeftBtn;
    public ViewPager id6MainViewPager;
    ImageView id6RightBtn;
    private String id8CacheLeftIcon2;
    private String id8CacheLeftIcon3;
    private String id8CacheLeftIcon4;
    private String id8CacheLeftIcon5;
    boolean id8CardsContainerFirstChildHasFocus;
    private ImageView[] indicatorPoints;
    private MainKswID7Binding kswId7Binding;
    private KSWID7ViewPagerAdapter kswid7ViewPagerAdapter;
    public ActivityMainKswmbuxBinding kswmbuxBinding;
    private KSWMBUXHomePagerAdpater kswmbuxHomePagerAdpater;
    private LandroverMainBinding landroverBinding;
    private LandroverViewModel landroverViewModel;
    private ActivityMainLexusBinding lexusBinding;
    private LexusLsBottomViewPagerAdpater lexusLsBottomViewPagerAdpater;
    private LexusLsBottomViewPagerAdpaterV2 lexusLsBottomViewPagerAdpaterV2;
    LexusLsDragMainLayoutBinding lexusLsDragBinding;
    private LexusLsSelAppAdapter lexusLsSelAppAdapter;
    private LexusLsTopAppAdapter lexusLsTopAppAdapter;
    public ViewPager lexusLsVpagerBottom;
    private RecyclerView lexus_ls_add_app_recycleview;
    private TextView lexus_ls_addapp_iv_cancel;
    private TextView lexus_ls_addapp_iv_ok;
    public BcNTG5ViewModel mBcNTG5ViewModel;
    private List<BenzCardMenu> mBenzCardMenus;
    private BenzCardPagerAdapter mBenzCardPagerAdapter;
    private BenzNtgItemTouchHelper mBenzNtgItemTouchHelper;
    private BenzNtgfyNewAdapter mBenzNtgfyNewAdapter;
    private List<LinearLayout> mBenzPageViews;
    private Benzmbux2021newAdapter mBenzmbux2021newAdapter;
    private DeleteZone mDeleteZone;
    private DragController mDragController;
    private DragLayer mDragLayer;
    private ItemTouchHelper mItemTouchHelper;
    private KeyControlBroadcastReceiver mKeyControlBroadcastReceiver;
    private List<View> mLeftBarView;
    private MyItemTouchHelper mMyItemTouchHelper;
    public PopupWindow mPopupThemeWindow;
    public PopupWindow mPopupWindow;
    public PopupWindow mPopupWindow_FY;
    private PagerGridLayoutManager mbux2021_LayoutManager;
    private ActivityBmwNbtBinding nbtBinging;
    private BwmNbtModel nbtModel;
    public ActivityMainBenzNtg5Binding ntg5Binding;
    private OnID8SkinChangeListener onGsID8SkinChangeListener;
    private OnID8SkinChangeListener onID8SkinChangeListener;
    private OnID8SkinChangeListener onPempID8SkinChangeListener;
    private RecyclerView recyclerview_drag;
    private int resumeCount;
    private ActivityRomeoBinding romeoBinding;
    private ActivityRomeoV2Binding romeoV2Binding;
    private RomeoViewModel romeoViewModel;
    private RomeoViewModelV2 romeoViewModelv2;
    private PopupWindow skinPopupWindow;
    private HashMap<String, View> systemCardViewHashMap;
    private HashMap<String, View> trdCardViewHashMap;
    public ActivityMainGsugBinding ugBinding;
    private LauncherViewModel viewModel;
    public ViewPager viewPagerBenzMbux2021;
    public ViewPager viewPagerBenzMbux2021Ksw;
    public ViewPager viewpagerAudiMib3;
    public ViewPager viewpagerAudiMib3Fy;
    public ViewPager viewpagerBenzNtg6Fy;
    private static final String TAG = "KswApplication." + MainActivity.class.getSimpleName();
    private static ArrayList<LexusLsAppSelBean> mAllAppList = new ArrayList<>();
    private static ArrayList<LexusLsAppSelBean> mCheckedAppList = new ArrayList<>();
    private static ArrayList<LexusLsAppSelBean> mCheckedAppList_V2 = new ArrayList<>();
    public static int mRow = 0;
    public static int mColumn = 0;
    public static final String[] PKG_GOOGLE_APP = {BenzUtils.GOOGLE_MAP, BenzUtils.GOOGLE_PLAY, BenzUtils.GOOGLE_SEARCH_PKG, BenzUtils.GOOGLE_ASSISTANT_PKG};
    private static boolean isFront = false;
    private static final int[] bgIconRes = {C0899R.C0900drawable.mbux2_coat_set_icon2_1, C0899R.C0900drawable.mbux2_coat_set_icon2_2, C0899R.C0900drawable.mbux2_coat_set_icon2_3, C0899R.C0900drawable.mbux2_coat_set_icon2_4, C0899R.C0900drawable.mbux2_coat_set_icon2_5, C0899R.C0900drawable.mbux2_coat_set_icon2_6, C0899R.C0900drawable.mbux2_coat_set_icon2_7};
    private static final int[] bgRes = {C0899R.C0900drawable.mbux2_bg_main_2_1, C0899R.C0900drawable.mbux2_bg_main_2_2, C0899R.C0900drawable.mbux2_bg_main_2_3, C0899R.C0900drawable.mbux2_bg_main_2_4, C0899R.C0900drawable.mbux2_bg_main_2_5, C0899R.C0900drawable.mbux2_bg_main_2_6, C0899R.C0900drawable.mbux2_bg_main_2_7};
    private static boolean IS_HAVE_OPEN_360_APP = false;
    public static boolean settingLanguage = false;
    public MutableLiveData<UgPager> select = new MutableLiveData<>();
    private int mKeyControlCount = 0;
    private int index = 0;
    private View.OnFocusChangeListener listener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.MainActivity.10
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean b) {
            Log.d(MainActivity.TAG, "onFocusChange: view.id=" + view.getId());
        }
    };
    private View.OnClickListener mOnClickListener_AlsId7Ui = new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.11
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            MainActivity.this.alsId7UiViewModel.addLastViewFocused(v);
            switch (v.getId()) {
                case C0899R.C0901id.menu_button1 /* 2131297332 */:
                    MainActivity.this.alsId7UiViewModel.openShortCutApp(v, 1, LauncherViewModel.ALS_KEY_SHORTCUT_PKG_1, LauncherViewModel.ALS_KEY_SHORTCUT_CLS_1);
                    return;
                case C0899R.C0901id.menu_button2 /* 2131297333 */:
                    MainActivity.this.alsId7UiViewModel.openShortCutApp(v, 2, LauncherViewModel.ALS_KEY_SHORTCUT_PKG_2, LauncherViewModel.ALS_KEY_SHORTCUT_CLS_2);
                    return;
                case C0899R.C0901id.menu_button3 /* 2131297334 */:
                    MainActivity.this.alsId7UiViewModel.openSettings(v);
                    return;
                case C0899R.C0901id.menu_button4 /* 2131297335 */:
                    MainActivity.this.alsId7UiViewModel.openCar(v);
                    return;
                case C0899R.C0901id.menu_button5 /* 2131297336 */:
                    MainActivity.this.alsId7UiViewModel.openApps(v);
                    return;
                default:
                    return;
            }
        }
    };
    private View.OnLongClickListener mOnLongListener_AlsId7Ui = new View.OnLongClickListener() { // from class: com.wits.ksw.MainActivity.12
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case C0899R.C0901id.menu_button1 /* 2131297332 */:
                    MainActivity.this.alsId7UiViewModel.initPopWinowGridApps(v, 1, LauncherViewModel.ALS_KEY_SHORTCUT_PKG_1, LauncherViewModel.ALS_KEY_SHORTCUT_CLS_1);
                    return true;
                case C0899R.C0901id.menu_button2 /* 2131297333 */:
                    MainActivity.this.alsId7UiViewModel.initPopWinowGridApps(v, 2, LauncherViewModel.ALS_KEY_SHORTCUT_PKG_2, LauncherViewModel.ALS_KEY_SHORTCUT_CLS_2);
                    return true;
                default:
                    return false;
            }
        }
    };
    private ScrollController mScrollController = new ScrollController();
    public int pageSize = 0;
    public int pageCount = 0;
    private boolean isEnableDrag = true;
    private int needUpdateDataPageIndex = -1;
    private int replaceAppPageIndex = -1;
    private String isFirstLoad = "true";
    private String isFirstLoad_V2 = "true";
    String screenFile = "";
    IContentObserver shotScreenObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.MainActivity.15
        @Override // com.wits.pms.IContentObserver
        public void onChange() throws RemoteException {
            String topApp = PowerManagerApp.getStatusString("topApp");
            LOGE.m43E("shotScreenObserver  *********** " + topApp);
            if (!BuildConfig.APPLICATION_ID.equals(topApp)) {
                MainActivity.this.screenHandler.sendEmptyMessageDelayed(666, 2000L);
            } else {
                MainActivity.this.screenHandler.removeMessages(666);
            }
        }
    };
    private Handler screenHandler = new Handler() { // from class: com.wits.ksw.MainActivity.16
        @Override // android.os.Handler
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
    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() { // from class: com.wits.ksw.MainActivity.17
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (!view.isInTouchMode() && MainActivity.this.isEnableDrag) {
                return false;
            }
            LOGE.m43E("onLongClick ********************* Drag started");
            DragSource dragSource = (DragSource) view;
            MainActivity.this.mDragController.startDragBitmap(view, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);
            return true;
        }
    };
    LexusLsDragItemAdapter.ItemDragListener itemDragListener = new LexusLsDragItemAdapter.ItemDragListener() { // from class: com.wits.ksw.MainActivity.18
        @Override // com.wits.ksw.launcher.view.lexusls.adapter.LexusLsDragItemAdapter.ItemDragListener
        public void onItemDragStarted(View source) {
            LOGE.m43E("onItemDragStarted soure=" + source);
        }

        @Override // com.wits.ksw.launcher.view.lexusls.adapter.LexusLsDragItemAdapter.ItemDragListener
        public void onItemDropCompleted(View source, View target, boolean success) {
            LOGE.m43E("========onDropCompleted success : " + success);
            if (success && source != target) {
                LOGE.m43E("success && (source != target)");
                LexusLsAppSelBean sourceItem = ((DraggableLayout) source).getItem();
                if (target instanceof DeleteZone) {
                    LOGE.m43E("target instanceof DeleteZone");
                    if (sourceItem == null) {
                        Log.e(MainActivity.TAG, "sourceItem is null in delete action !!!");
                        return;
                    } else if (sourceItem.isDeletable()) {
                        LOGE.m43E("sourceItem.isDeletable()  POS = " + sourceItem.getItemPos());
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
                            LexusLsConfig.mAppList.contains(sourceItem);
                        } else {
                            LexusLsConfig.mAppList_V2.contains(sourceItem);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "no delete action", 0).show();
                    }
                } else {
                    LexusLsAppSelBean targetItem = ((DraggableLayout) target).getItem();
                    if (sourceItem == null) {
                        LOGE.m43E("sourceItem is null in replace action !!!");
                        return;
                    } else if (targetItem == null) {
                        MainActivity.this.mDeleteZone.setVisibility(8);
                        LOGE.m43E("targetItem is null in replace action !!!");
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
    ScrollController.OnPageChangeListener onPageChangeListener = new ScrollController.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.19
        @Override // com.wits.ksw.launcher.view.lexusls.drag.ScrollController.OnPageChangeListener
        public void onPageChange(int index) {
            LOGE.m43E("\u5f53\u524d\u9875\u9762\u4e3a \uff1a " + index);
            if (MainActivity.this.needUpdateDataPageIndex != -1 && index == MainActivity.this.needUpdateDataPageIndex) {
                MainActivity.this.adapterLexusLsDrag.notifyDataSetChanged();
                MainActivity.this.needUpdateDataPageIndex = -1;
            }
            if (MainActivity.this.replaceAppPageIndex != -1 && index == MainActivity.this.replaceAppPageIndex) {
                MainActivity.this.adapterLexusLsDrag.notifyDataSetChanged();
                MainActivity.this.replaceAppPageIndex = -1;
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
    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.MainActivity.20
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
        }
    };
    DragController.DraggingListener draggingListener = new DragController.DraggingListener() { // from class: com.wits.ksw.MainActivity.21
        @Override // com.wits.ksw.launcher.view.lexusls.drag.DragController.DraggingListener
        public void onDragStart(DragSource source, Object info, int dragAction) {
            LexusLsAppSelBean sourceItem = ((DraggableLayout) source).getItem();
            if (UiThemeUtils.isLEXUS_LS_UI(MainActivity.mainActivity)) {
                if (LexusLsConfig.isMenu(sourceItem.getAppPkg())) {
                    LOGE.m43E("----------GONE-------------->");
                    MainActivity.this.mDeleteZone.setVisibility(8);
                    return;
                }
                LOGE.m43E("-----------VISIBLE------------->");
                MainActivity.this.mDeleteZone.setVisibility(0);
            } else if (LexusLsConfig.isMenu_V2(sourceItem.getAppPkg())) {
                LOGE.m43E("----------GONE-------------->");
                MainActivity.this.mDeleteZone.setVisibility(8);
            } else {
                LOGE.m43E("-----------VISIBLE------------->");
                MainActivity.this.mDeleteZone.setVisibility(0);
            }
        }

        @Override // com.wits.ksw.launcher.view.lexusls.drag.DragController.DraggingListener
        public void onDragEnd() {
            LOGE.m43E("-----------onDragEnd------------->");
            MainActivity.this.mDeleteZone.setVisibility(8);
        }
    };
    LexusLsDragItemAdapter.IOnAddAppClickListener iOnAddAppClickListener = new LexusLsDragItemAdapter.IOnAddAppClickListener() { // from class: com.wits.ksw.MainActivity.22
        @Override // com.wits.ksw.launcher.view.lexusls.adapter.LexusLsDragItemAdapter.IOnAddAppClickListener
        public void onClick(View view) {
            MainActivity.this.initPopuWindow(view);
        }
    };
    private boolean lastRight = true;
    private boolean lastLeft = false;
    private View.OnClickListener mDialogBtnClickListener = new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.31
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            switch (v.getId()) {
                case C0899R.C0901id.lexus_ls_addapp_iv_cancel /* 2131297261 */:
                    if (MainActivity.this.mPopupWindow != null) {
                        MainActivity.this.mPopupWindow.dismiss();
                        return;
                    }
                    return;
                case C0899R.C0901id.lexus_ls_addapp_iv_ok /* 2131297262 */:
                    if (MainActivity.this.mPopupWindow == null) {
                        return;
                    }
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
                                LOGE.m43E("removeList size = " + shengyuList.size());
                                LexusLsConfig.getRemoveListResult(MainActivity.mainActivity, LexusLsConfig.mAppList, shengyuList);
                            }
                        } else {
                            return;
                        }
                    } else {
                        ArrayList unused2 = MainActivity.mCheckedAppList_V2 = (ArrayList) MainActivity.this.lexusLsSelAppAdapter.getCheckBoxIDList();
                        if (MainActivity.mCheckedAppList_V2 != null && MainActivity.mCheckedAppList_V2.size() > 0) {
                            Log.e(MainActivity.TAG, "mCheckedAppList_V2 size = " + MainActivity.mCheckedAppList_V2.size());
                            for (int i2 = 0; i2 < MainActivity.mCheckedAppList_V2.size(); i2++) {
                                Log.e(MainActivity.TAG, "mCheckedAppList_V2 size i = " + i2 + " appName = " + ((LexusLsAppSelBean) MainActivity.mCheckedAppList_V2.get(i2)).getAppName());
                            }
                            ArrayList<LexusLsAppSelBean> tmpList2 = new ArrayList<>();
                            tmpList2.addAll(LexusLsConfig.mAppList_V2);
                            List<LexusLsAppSelBean> shengyuList2 = LexusLsConfig.getRemoveListResult(MainActivity.mainActivity, tmpList2, LexusLsConfig.getMenuAppList_V2(MainActivity.mainActivity));
                            if (shengyuList2 != null && shengyuList2.size() > 0) {
                                LOGE.m43E("removeList size = " + shengyuList2.size());
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
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mOnClickListener_AlsId7 = new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.40
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            MainActivity.this.alsID7ViewModel.addLastViewFocused(v);
            switch (v.getId()) {
                case C0899R.C0901id.btn_apps /* 2131296701 */:
                    Log.e("isSelected1", v.isSelected() + "");
                    MainActivity.this.alsID7ViewModel.openApps(v);
                    return;
                case C0899R.C0901id.btn_music_next /* 2131296702 */:
                case C0899R.C0901id.btn_music_pause /* 2131296703 */:
                case C0899R.C0901id.btn_music_prev /* 2131296704 */:
                default:
                    return;
                case C0899R.C0901id.btn_settings /* 2131296705 */:
                    Log.e("isSelected2", v.isSelected() + "");
                    MainActivity.this.alsID7ViewModel.openSettings(v);
                    return;
                case C0899R.C0901id.btn_shut_1 /* 2131296706 */:
                    MainActivity.this.alsID7ViewModel.openShortCutApp(v, 1, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1);
                    return;
                case C0899R.C0901id.btn_shut_2 /* 2131296707 */:
                    Log.e("isSelected", v.isSelected() + "");
                    MainActivity.this.alsID7ViewModel.openShortCutApp(v, 2, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2);
                    return;
                case C0899R.C0901id.btn_shut_3 /* 2131296708 */:
                    MainActivity.this.alsID7ViewModel.openShortCutApp(v, 3, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3);
                    return;
            }
        }
    };
    private View.OnClickListener mOnClickListener_AlsId7_V2 = new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.41
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            MainActivity.this.alsID7ViewModel.addLastViewFocused(v);
            switch (v.getId()) {
                case C0899R.C0901id.btn_apps /* 2131296701 */:
                    Log.e("isSelected1", v.isSelected() + "");
                    MainActivity.this.alsID7ViewModel.openApps(v);
                    return;
                case C0899R.C0901id.btn_music_next /* 2131296702 */:
                case C0899R.C0901id.btn_music_pause /* 2131296703 */:
                case C0899R.C0901id.btn_music_prev /* 2131296704 */:
                default:
                    return;
                case C0899R.C0901id.btn_settings /* 2131296705 */:
                    Log.e("isSelected2", v.isSelected() + "");
                    MainActivity.this.alsID7ViewModel.openSettings(v);
                    return;
                case C0899R.C0901id.btn_shut_1 /* 2131296706 */:
                    MainActivity.this.alsID7ViewModel.openShortCutApp(v, 1, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1_V2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1_V2);
                    return;
                case C0899R.C0901id.btn_shut_2 /* 2131296707 */:
                    Log.e("isSelected", v.isSelected() + "");
                    MainActivity.this.alsID7ViewModel.openShortCutApp(v, 2, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2_V2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2_V2);
                    return;
                case C0899R.C0901id.btn_shut_3 /* 2131296708 */:
                    MainActivity.this.alsID7ViewModel.openShortCutApp(v, 3, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3_V2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3_V2);
                    return;
            }
        }
    };
    private View.OnLongClickListener mOnLongListener_AlsId7 = new View.OnLongClickListener() { // from class: com.wits.ksw.MainActivity.42
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case C0899R.C0901id.btn_shut_1 /* 2131296706 */:
                    MainActivity.this.alsID7ViewModel.initPopWinowGridApps(v, 1, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1);
                    return true;
                case C0899R.C0901id.btn_shut_2 /* 2131296707 */:
                    MainActivity.this.alsID7ViewModel.initPopWinowGridApps(v, 2, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2);
                    return true;
                case C0899R.C0901id.btn_shut_3 /* 2131296708 */:
                    MainActivity.this.alsID7ViewModel.initPopWinowGridApps(v, 3, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3);
                    return true;
                default:
                    return false;
            }
        }
    };
    private View.OnLongClickListener mOnLongListener_AlsId7_V2 = new View.OnLongClickListener() { // from class: com.wits.ksw.MainActivity.43
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case C0899R.C0901id.btn_shut_1 /* 2131296706 */:
                    MainActivity.this.alsID7ViewModel.initPopWinowGridApps(v, 1, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1_V2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1_V2);
                    return true;
                case C0899R.C0901id.btn_shut_2 /* 2131296707 */:
                    MainActivity.this.alsID7ViewModel.initPopWinowGridApps(v, 2, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2_V2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2_V2);
                    return true;
                case C0899R.C0901id.btn_shut_3 /* 2131296708 */:
                    MainActivity.this.alsID7ViewModel.initPopWinowGridApps(v, 3, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3_V2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3_V2);
                    return true;
                default:
                    return false;
            }
        }
    };
    private LandroverHandler landroverHandler = null;
    private List<String> id8CacheCardSeq = new ArrayList();
    private List<String> gsId8CacheCardSeq = new ArrayList();
    private List<String> pempId8CacheCardSeq = new ArrayList();
    private int lastX = 0;
    private int touchEventId = 1001;
    private double cardRightScroll = 0.3d;
    private double cardLeftScroll = 0.7d;
    Handler handler = new Handler() { // from class: com.wits.ksw.MainActivity.50
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            View scroller = (View) msg.obj;
            MainActivity.this.lastX = scroller.getScrollX();
            MainActivity mainActivity2 = MainActivity.this;
            mainActivity2.cardWith = mainActivity2.bmwId8GsUiMainLayoutBinding.llContaine.getChildAt(0).getWidth();
            if (msg.what == MainActivity.this.touchEventId) {
                if (MainActivity.this.currentScrollX > MainActivity.this.currentOldScrollX) {
                    MainActivity mainActivity3 = MainActivity.this;
                    mainActivity3.scrollViewRightStop(mainActivity3.lastX);
                    return;
                }
                MainActivity mainActivity4 = MainActivity.this;
                mainActivity4.scrollViewLeftStop(mainActivity4.lastX);
            }
        }
    };
    View.OnLongClickListener id8OnLongClickListener = new View.OnLongClickListener() { // from class: com.wits.ksw.MainActivity.56
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (UiThemeUtils.isBMW_ID8_UI(view.getContext())) {
                MainActivity.this.bmwId8ViewModel.openID8Edit(view);
                return false;
            } else if (UiThemeUtils.isUI_PEMP_ID8(view.getContext())) {
                MainActivity.this.bmwId8ViewModel.openID8PempEdit(view);
                return false;
            } else {
                MainActivity.this.bmwId8ViewModel.openID8GsEdit(view);
                return false;
            }
        }
    };
    private View.OnClickListener mAudiMib3TyItemClickListener = new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.92
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            MainActivity.mAudiMib3TyBcViewModel.addLastViewFocused(v);
            switch (v.getId()) {
                case C0899R.C0901id.iv_bt_audimib3 /* 2131297116 */:
                    MainActivity.mAudiMib3TyBcViewModel.openBtApp(v);
                    return;
                case C0899R.C0901id.iv_car_audimib3 /* 2131297120 */:
                    MainActivity.mAudiMib3TyBcViewModel.openCar(v);
                    return;
                case C0899R.C0901id.iv_music_audimib3 /* 2131297170 */:
                    MainActivity.mAudiMib3TyBcViewModel.openChoseMusic(v);
                    return;
                case C0899R.C0901id.iv_navi_audimib3 /* 2131297174 */:
                    MainActivity.mAudiMib3TyBcViewModel.openNaviApp(v);
                    return;
                case C0899R.C0901id.iv_set_audimib3 /* 2131297179 */:
                    MainActivity.mAudiMib3TyBcViewModel.openSettings(v);
                    return;
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mAudiMib3FyItemClickListener = new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.93
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            MainActivity.mAudiMbi3FyBcViewModel.addLastViewFocused(v);
            switch (v.getId()) {
                case C0899R.C0901id.iv_bt_audimib3 /* 2131297116 */:
                    MainActivity.mAudiMbi3FyBcViewModel.openBtApp(v);
                    return;
                case C0899R.C0901id.iv_car_audimib3 /* 2131297120 */:
                    MainActivity.mAudiMbi3FyBcViewModel.openCar(v);
                    return;
                case C0899R.C0901id.iv_music_audimib3 /* 2131297170 */:
                    MainActivity.mAudiMbi3FyBcViewModel.openChoseMusic(v);
                    return;
                case C0899R.C0901id.iv_navi_audimib3 /* 2131297174 */:
                    MainActivity.mAudiMbi3FyBcViewModel.openNaviApp(v);
                    return;
                case C0899R.C0901id.iv_set_audimib3 /* 2131297179 */:
                    MainActivity.mAudiMbi3FyBcViewModel.openSettings(v);
                    return;
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mAudiMib3ItemClickListener = new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.96
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            MainActivity.mAudiMbi3BcViewModel.addLastViewFocused(v);
            switch (v.getId()) {
                case C0899R.C0901id.iv_bt_audimib3 /* 2131297116 */:
                    MainActivity.mAudiMbi3BcViewModel.openBtApp(v);
                    return;
                case C0899R.C0901id.iv_car_audimib3 /* 2131297120 */:
                    MainActivity.mAudiMbi3BcViewModel.openCar(v);
                    return;
                case C0899R.C0901id.iv_music_audimib3 /* 2131297170 */:
                    MainActivity.mAudiMbi3BcViewModel.openChoseMusic(v);
                    return;
                case C0899R.C0901id.iv_navi_audimib3 /* 2131297174 */:
                    MainActivity.mAudiMbi3BcViewModel.openNaviApp(v);
                    return;
                case C0899R.C0901id.iv_set_audimib3 /* 2131297179 */:
                    MainActivity.mAudiMbi3BcViewModel.openSettings(v);
                    return;
                default:
                    return;
            }
        }
    };
    private int totalPage = 3;
    private String mBenzCardStr = "";
    private String mUiName = "";
    private boolean isCardAdded = false;
    private PagingScrollHelper scrollHelper = new PagingScrollHelper();
    private String styleIndexKsw = TxzMessage.TXZ_SHOW;
    private String bgIndexKsw = TxzMessage.TXZ_SHOW;
    private String styleIndex = TxzMessage.TXZ_SHOW;
    private String bgIndex = TxzMessage.TXZ_SHOW;
    private String bgFyIndex = "8";
    private boolean isStop = false;
    private boolean isVideoStop = false;
    private IContentObserver.Stub mMusicObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.MainActivity.125
        @Override // com.wits.pms.IContentObserver
        public void onChange() throws RemoteException {
            Log.i(MainActivity.TAG, "onChange: mMusicObserver");
            if (MainActivity.this.alsID7ViewModel == null) {
                return;
            }
            try {
                MainActivity.this.isStop = PowerManagerApp.getManager().getStatusBoolean("music_stop");
                if (MainActivity.this.isStop) {
                    Log.i(MainActivity.TAG, "updateData: mMusicObserver isMusicStop=" + MainActivity.this.isStop);
                    MainActivity.this.alsID7ViewModel.setMusicPlayState(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private IContentObserver.Stub mId8GsMusicObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.MainActivity.126
        @Override // com.wits.pms.IContentObserver
        public void onChange() throws RemoteException {
            Log.i(MainActivity.TAG, "onChange: mMusicObserver");
            if (MainActivity.this.bmwId8ViewModel == null) {
                return;
            }
            try {
                MainActivity.this.isStop = PowerManagerApp.getManager().getStatusBoolean("music_stop");
                if (MainActivity.this.isStop) {
                    Log.i(MainActivity.TAG, "updateData: mMusicObserver isMusicStop=" + MainActivity.this.isStop);
                    MainActivity.this.bmwId8ViewModel.setMusicPlayState(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private IContentObserver.Stub mId8PempMusicObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.MainActivity.127
        @Override // com.wits.pms.IContentObserver
        public void onChange() throws RemoteException {
            Log.i(MainActivity.TAG, "onChange: mMusicObserver");
            if (MainActivity.this.bmwId8ViewModel == null) {
                return;
            }
            try {
                MainActivity.this.isStop = PowerManagerApp.getManager().getStatusBoolean("music_stop");
                if (MainActivity.this.isStop) {
                    Log.i(MainActivity.TAG, "updateData: mMusicObserver isMusicStop=" + MainActivity.this.isStop);
                    MainActivity.this.bmwId8ViewModel.setMusicPlayState(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private boolean isID8FirstStart = true;
    private boolean checkZlinkDisplaySetting = true;

    static /* synthetic */ int access$5508(MainActivity x0) {
        int i = x0.mKeyControlCount;
        x0.mKeyControlCount = i + 1;
        return i;
    }

    public void setAlsButtonBg(String skinType) {
        if (alsId7UiMainBinding != null) {
            if (ID8LauncherConstants.ID8_SKIN_PERSONAL.equals(skinType)) {
                alsId7UiMainBinding.menuButton1.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_yellow));
                alsId7UiMainBinding.menuButton2.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_yellow));
                alsId7UiMainBinding.menuButton3.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_yellow));
                alsId7UiMainBinding.menuButton4.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_yellow));
                alsId7UiMainBinding.menuButton5.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_yellow));
                alsId7UiMainBinding.itemViewSkinDefault.setImageResource(C0899R.C0900drawable.als_id7_ui_indicato_skin_yellow_selector);
            } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinType)) {
                alsId7UiMainBinding.menuButton1.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_red));
                alsId7UiMainBinding.menuButton2.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_red));
                alsId7UiMainBinding.menuButton3.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_red));
                alsId7UiMainBinding.menuButton4.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_red));
                alsId7UiMainBinding.menuButton5.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_red));
                alsId7UiMainBinding.itemViewSkinDefault.setImageResource(C0899R.C0900drawable.als_id7_ui_indicato_skin_red_selector);
            } else {
                alsId7UiMainBinding.menuButton1.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_blue));
                alsId7UiMainBinding.menuButton2.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_blue));
                alsId7UiMainBinding.menuButton3.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_blue));
                alsId7UiMainBinding.menuButton4.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_blue));
                alsId7UiMainBinding.menuButton5.setBackground(getResources().getDrawable(C0899R.C0900drawable.als_id7_ui_left_btn_blue));
                alsId7UiMainBinding.itemViewSkinDefault.setImageResource(C0899R.C0900drawable.als_id7_ui_indicato_skin_blue_selector);
            }
        }
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        mainActivity = this;
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Main onCreate: ");
        startService(new Intent(this, KswRunService.class));
    }

    private void setApplicationEnabledSetting(int newState) {
        PackageManager mPm = getPackageManager();
        mPm.setApplicationEnabledSetting(BenzUtils.GOOGLE_ASSISTANT_PKG, newState, 0);
        mPm.setApplicationEnabledSetting("com.google.android.ims", newState, 0);
        mPm.setApplicationEnabledSetting(BenzUtils.GOOGLE_PLAY, newState, 0);
        mPm.setApplicationEnabledSetting(BenzUtils.GOOGLE_MAP, newState, 0);
        mPm.setApplicationEnabledSetting("com.google.android.partnersetup", newState, 0);
        mPm.setApplicationEnabledSetting("com.google.android.gms", newState, 0);
        mPm.setApplicationEnabledSetting("com.google.android.inputmethod.latin", newState, 0);
        mPm.setApplicationEnabledSetting(BenzUtils.GOOGLE_SEARCH_PKG, newState, 0);
        mPm.setApplicationEnabledSetting("com.google.android.onetimeinitializer", newState, 0);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwid5UiView() {
        Log.i(TAG, "initBmwid5UiView: ");
        this.id5MaindBind = (ID5MaindBind) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_id5);
        BmwId5ViewPagerAdpater bmwId5ViewPagerAdpater = new BmwId5ViewPagerAdpater(getSupportFragmentManager());
        this.id5MaindBind.id5MainViewPager.setAdapter(bmwId5ViewPagerAdpater);
        this.id5MaindBind.id5MainViewPager.setCurrentItem(0);
        KswUtils.setFull(getWindow());
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwid6UiView() {
        Log.i(TAG, "initBmwid6UiView: ");
        setContentView(C0899R.C0902layout.activity_main_id6);
        ButterKnife.inject(this);
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        BmwId6ViewPagerAdpater bmwId6ViewPagerAdpater = new BmwId6ViewPagerAdpater(getSupportFragmentManager());
        this.bmwId6ViewPagerAdpater = bmwId6ViewPagerAdpater;
        this.id6MainViewPager.setAdapter(bmwId6ViewPagerAdpater);
        this.id6MainViewPager.setCurrentItem(0);
        this.id6MainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.1
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int arg, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
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

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        this.id6LeftBtn.setVisibility(4);
        this.id6RightBtn.setVisibility(0);
        this.id6LeftBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.-$$Lambda$MainActivity$HRC1bb39AYOM-zW7pZl-6pRUXMU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.lambda$initBmwid6UiView$0$MainActivity(view);
            }
        });
        this.id6RightBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.-$$Lambda$MainActivity$48PlJEU6o9s_lga_7e3ttRjxDKY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.lambda$initBmwid6UiView$1$MainActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initBmwid6UiView$0$MainActivity(View v) {
        int index = this.id6MainViewPager.getCurrentItem() - 1;
        if (index < 0) {
            return;
        }
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

    public /* synthetic */ void lambda$initBmwid6UiView$1$MainActivity(View v) {
        int index = this.id6MainViewPager.getCurrentItem() + 1;
        if (index > 3) {
            return;
        }
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwid6CuspUiView() {
        Log.i(TAG, "initBmwid6CuspUiView: ");
        setContentView(C0899R.C0902layout.activity_main_id6_cusp);
        this.id6CuspMainViewPager = (ViewPager) findViewById(C0899R.C0901id.id6_cusp_main_view_pager);
        final ImageView id6CuspLeftBtn = (ImageView) findViewById(C0899R.C0901id.id6_cusp_left_btn);
        final ImageView id6CuspRightBtn = (ImageView) findViewById(C0899R.C0901id.id6_cusp_right_btn);
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        BmwId6CuspViewPagerAdpater bmwId6CuspViewPagerAdpater = new BmwId6CuspViewPagerAdpater(getSupportFragmentManager());
        this.bmwId6CuspViewPagerAdpater = bmwId6CuspViewPagerAdpater;
        this.id6CuspMainViewPager.setAdapter(bmwId6CuspViewPagerAdpater);
        this.id6CuspMainViewPager.setCurrentItem(0);
        this.id6CuspMainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.2
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int arg, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
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

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        id6CuspLeftBtn.setVisibility(4);
        id6CuspRightBtn.setVisibility(0);
        id6CuspLeftBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.-$$Lambda$MainActivity$ooYtSjZ9M53_kvQ-_04qTanDw4M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.lambda$initBmwid6CuspUiView$2$MainActivity(view);
            }
        });
        id6CuspRightBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.-$$Lambda$MainActivity$IXvBVlLzAXFEaerclmBAoOjvOcE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.lambda$initBmwid6CuspUiView$3$MainActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initBmwid6CuspUiView$2$MainActivity(View v) {
        int index = this.id6CuspMainViewPager.getCurrentItem() - 1;
        if (index < 0) {
            return;
        }
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

    public /* synthetic */ void lambda$initBmwid6CuspUiView$3$MainActivity(View v) {
        int index = this.id6CuspMainViewPager.getCurrentItem() + 1;
        if (index > 3) {
            return;
        }
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwEvoId6GS() {
        ActivityMainId6GsBinding binding = (ActivityMainId6GsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_id6_gs);
        BmwId6gsViewMode viewMode = (BmwId6gsViewMode) ViewModelProviders.m59of(this).get(BmwId6gsViewMode.class);
        viewMode.setActivity(this);
        viewMode.initData();
        binding.setVm(viewMode);
        binding.id6GsMainViewPager.setViewMode(viewMode);
        binding.id6GsMainViewPager.setAdapter(new BmwId6gSHomePagerAdpater(getSupportFragmentManager()));
        binding.id6GsMainViewPager.setOffscreenPageLimit(3);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    public void initBmwid7UiView() {
        Log.i(TAG, "initBmwid7UiView: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        com.wits.ksw.databinding.MainActivity mainActivity2 = (com.wits.ksw.databinding.MainActivity) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_bmw);
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initUIKSWID7View() {
        Log.i(TAG, "initUIKSWID7View: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        MainKswID7Binding mainKswID7Binding = (MainKswID7Binding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_ksw_id7);
        this.kswId7Binding = mainKswID7Binding;
        mainKswID7Binding.setLauncherViewModel(this.viewModel);
        this.viewModel.initData();
        this.kswid7ViewPagerAdapter = new KSWID7ViewPagerAdapter(getSupportFragmentManager());
        this.kswId7Binding.viewPage.setAdapter(this.kswid7ViewPagerAdapter);
        setCurrentItem(0);
        this.kswId7Binding.viewPage.setOffscreenPageLimit(2);
        this.kswId7Binding.ivBottomHome.setSelected(true);
        this.kswId7Binding.ivBottomPoint.setSelected(false);
        this.kswId7Binding.viewPage.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.MainActivity.3
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == 21 || event.getKeyCode() == 22) {
                    return true;
                }
                return false;
            }
        });
        this.kswId7Binding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.4
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                MainActivity.this.kswId7Binding.ivBottomHome.setSelected(i == 0);
                MainActivity.this.kswId7Binding.ivBottomPoint.setSelected(i == 1);
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwid7V2UiView() {
        Log.i(TAG, "initBmwid7V2UiView: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        com.wits.ksw.databinding.MainActivity mainActivity2 = (com.wits.ksw.databinding.MainActivity) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_bmw);
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsId7UiView() {
        Log.i(TAG, "initAlsId7UiView: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.alsId7UiViewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        AlsId7UiMainBinding alsId7UiMainBinding2 = (AlsId7UiMainBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_als_id7_ui);
        alsId7UiMainBinding = alsId7UiMainBinding2;
        alsId7UiMainBinding2.viewPage.setAdapter(new AlsId7UiViewPagerAdapter(getSupportFragmentManager()));
        setCurrentItem(this.index);
        alsId7UiMainBinding.viewPage.setOffscreenPageLimit(3);
        alsId7UiMainBinding.imageView1.setSelected(false);
        alsId7UiMainBinding.imageView3.setSelected(true);
        alsId7UiMainBinding.imageView4.setSelected(false);
        alsId7UiMainBinding.setLauncherViewModel(this.alsId7UiViewModel);
        this.alsId7UiViewModel.initData();
        alsId7UiMainBinding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.5
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                Log.d(MainActivity.TAG, "onPageSelected: i=" + i);
                MainActivity.this.index = i;
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

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
                Log.d(MainActivity.TAG, "onPageScrollStateChanged: i=" + i);
            }
        });
        alsId7UiMainBinding.itemViewSkinDefault.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wits.ksw.MainActivity.6
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Log.d(MainActivity.TAG, "onLongClick: alsId7UiMainBinding.skinLl.getVisibility()" + MainActivity.alsId7UiMainBinding.skinLl.getVisibility());
                if (MainActivity.isQuickClick()) {
                    if (MainActivity.alsId7UiMainBinding.skinLl.getVisibility() == 4) {
                        MainActivity.alsId7UiMainBinding.skinLl.setVisibility(0);
                    }
                    return false;
                }
                MainActivity mainActivity2 = MainActivity.this;
                ToastUtils.showToastShort(mainActivity2, mainActivity2.getResources().getString(C0899R.string.als_id7_wranning));
                return true;
            }
        });
        alsId7UiMainBinding.blueIv.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Settings.System.putString(KswApplication.appContext.getContentResolver(), KswApplication.SKIN_NAME, ID8LauncherConstants.ID8_SKIN_EFFICIENT);
                MainActivity.this.setAlsButtonBg(ID8LauncherConstants.ID8_SKIN_EFFICIENT);
                MainActivity.alsId7UiMainBinding.skinLl.setVisibility(4);
            }
        });
        alsId7UiMainBinding.yellowIv.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Settings.System.putString(KswApplication.appContext.getContentResolver(), KswApplication.SKIN_NAME, ID8LauncherConstants.ID8_SKIN_PERSONAL);
                MainActivity.this.setAlsButtonBg(ID8LauncherConstants.ID8_SKIN_PERSONAL);
                MainActivity.alsId7UiMainBinding.skinLl.setVisibility(4);
            }
        });
        alsId7UiMainBinding.redIv.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.9
            @Override // android.view.View.OnClickListener
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

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                if (alsId7UiMainBinding != null) {
                    View view = findViewById(C0899R.C0901id.skin_ll);
                    if (!isTouchPointInView(view, (int) ev.getX(), (int) ev.getY())) {
                        Log.d(TAG, "dispatchTouchEvent: asdasdasd");
                        if (alsId7UiMainBinding.skinLl.getVisibility() == 0) {
                            alsId7UiMainBinding.skinLl.setVisibility(4);
                            break;
                        }
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBcUiView() {
        Log.i(TAG, "initBcUiView: ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        this.bcMainActivity = (ActivityMainBcBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_bc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 0, false);
        this.bcMainActivity.recyclerView2.setLayoutManager(linearLayoutManager);
        this.bcMainActivity.recyclerView2.setAdapter(new BenzNTG6RecyclerViewAdpater(mBcVieModel));
        this.bcMainActivity.setMBcVieModel(mBcVieModel);
        mBcVieModel.initData();
        this.bcMainActivity.appsBtn.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.MainActivity.13
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e(MainActivity.TAG, "onKey: " + keyCode + " action: " + event.getAction());
                if (keyCode == 20) {
                    KeyUtils.pressKey(19);
                    return false;
                }
                return false;
            }
        });
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenzNTG5View() {
        setFull();
        BcNTG5ViewModel bcNTG5ViewModel = (BcNTG5ViewModel) ViewModelProviders.m59of(this).get(BcNTG5ViewModel.class);
        this.mBcNTG5ViewModel = bcNTG5ViewModel;
        bcNTG5ViewModel.setActivity(this);
        this.ntg5Binding = (ActivityMainBenzNtg5Binding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_benz_ntg5);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 0, false);
        this.ntg5Binding.recyclerView2.setItemViewCacheSize(3);
        this.ntg5Binding.recyclerView2.setLayoutManager(linearLayoutManager);
        this.ntg5Binding.recyclerView2.setAdapter(new BenzNTG5RecyclerViewAdapter(this.mBcNTG5ViewModel));
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper() { // from class: com.wits.ksw.MainActivity.14
            @Override // android.support.p004v7.widget.SnapHelper, android.support.p004v7.widget.RecyclerView.OnFlingListener
            public boolean onFling(int velocityX, int velocityY) {
                return false;
            }
        };
        mLinearSnapHelper.attachToRecyclerView(this.ntg5Binding.recyclerView2);
        this.ntg5Binding.setMBcVieModel(this.mBcNTG5ViewModel);
        this.mBcNTG5ViewModel.initData();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsView() {
        setTheme(C0899R.style.ids6_style);
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        ActivityMainAlsId6Binding activityMainAlsId6Binding = (ActivityMainAlsId6Binding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_als_id6);
        this.alsBinding = activityMainAlsId6Binding;
        activityMainAlsId6Binding.setViewModel(this.viewModel);
        this.viewModel.initData();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBwmNbt() {
        BwmNbtModel bwmNbtModel = (BwmNbtModel) ViewModelProviders.m59of(this).get(BwmNbtModel.class);
        this.nbtModel = bwmNbtModel;
        bwmNbtModel.setActivity(this);
        ActivityBmwNbtBinding activityBmwNbtBinding = (ActivityBmwNbtBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_bmw_nbt);
        this.nbtBinging = activityBmwNbtBinding;
        this.nbtModel.setMainnbtBinging(activityBmwNbtBinding);
        this.nbtBinging.setNbtModel(this.nbtModel);
        this.nbtModel.initData();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexus() {
        Log.i(TAG, " initLexus: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        ActivityMainLexusBinding activityMainLexusBinding = (ActivityMainLexusBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_lexus);
        this.lexusBinding = activityMainLexusBinding;
        activityMainLexusBinding.setViewModel(this.viewModel);
        this.viewModel.initData();
        int openAcControl = Settings.System.getInt(getContentResolver(), KeyConfig.AC_CONTROL, 0);
        if (openAcControl == 1) {
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
        Log.e(TAG, "filePath = " + filePath);
        String shotCmd = "screencap -p " + filePath + " \n";
        try {
            Runtime.getRuntime().exec(shotCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    private static boolean delteDirectoryFiles(String path) {
        File file = new File(path);
        if (file.exists()) {
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
        return false;
    }

    public static Bitmap getScreenShot(Rect crop, int w, int h, int rot) {
        try {
            Method method = Class.forName("android.view.SurfaceControl").getMethod("screenshot", String.class);
            try {
                Bitmap bitmap = (Bitmap) method.invoke(Class.forName("android.view.SurfaceControl"), crop, Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(rot));
                return bitmap;
            } catch (IllegalAccessException e) {
                return null;
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                return null;
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
                return null;
            }
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return null;
        } catch (SecurityException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexusLsDrag() {
        String str;
        LexusLsAppSelBean bean;
        PowerManagerApp.registerIContentObserver("topApp", this.shotScreenObserver);
        Log.i(TAG, "initLexusLsDrag() ");
        setFull();
        setStatusBarTranslucent();
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        LexusLsDragMainLayoutBinding lexusLsDragMainLayoutBinding = (LexusLsDragMainLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.lexus_ls_drag_main_layout);
        this.lexusLsDragBinding = lexusLsDragMainLayoutBinding;
        lexusLsDragMainLayoutBinding.setLexusLsViewModel(this.viewModel);
        this.recyclerview_drag = this.lexusLsDragBinding.recyclerviewDrag;
        this.mDragLayer = (DragLayer) findViewById(C0899R.C0901id.demo_draglayer);
        this.mDeleteZone = (DeleteZone) findViewById(C0899R.C0901id.demo_del_zone);
        this.lexusLsDragBinding.ivLexusLsDragLeftBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.23
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MainActivity.this.mScrollController.smoothScrollToPage(0, true);
            }
        });
        this.lexusLsDragBinding.ivLexusLsDragRightBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.24
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MainActivity.this.mScrollController.smoothScrollToPage(1, true);
            }
        });
        this.mDragController = new DragController(mainActivity);
        mRow = 1;
        mColumn = 8;
        this.pageSize = 8 * 1;
        this.isFirstLoad = Settings.System.getString(mainActivity.getContentResolver(), LexusLsConfig.IS_FIRST);
        LOGE.m43E("isFirstLoad = " + this.isFirstLoad);
        if (TextUtils.equals(this.isFirstLoad, "true") || (str = this.isFirstLoad) == null) {
            LOGE.m43E("isFirstLoad  TextUtils.equals(isFirstLoad,\"true\") || isFirstLoad==null");
            LexusLsConfig.mAppList.clear();
            LexusLsConfig.mAppList.addAll(LexusLsConfig.getMenuAppList(mainActivity));
        } else if (TextUtils.equals(str, "false")) {
            LexusLsConfig.AppPkgs = Settings.System.getString(mainActivity.getContentResolver(), LexusLsConfig.LEXUS_LS_SAVE_PKG);
            LOGE.m43E("TextUtils.equals(isFirstLoad,\"false\")LexusLsConfig.AppPkgs : " + LexusLsConfig.AppPkgs);
            if (LexusLsConfig.AppPkgs != null && LexusLsConfig.AppPkgs.length() > 0) {
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
            } else {
                LOGE.m43E("ELSE   isFirstLoad = " + this.isFirstLoad + "\nLexusLsConfig.AppPkgs : " + LexusLsConfig.AppPkgs);
                LexusLsConfig.mAppList.addAll(LexusLsConfig.getMenuAppList(mainActivity));
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexusLsDragV2() {
        String str;
        LexusLsAppSelBean bean;
        PowerManagerApp.registerIContentObserver("topApp", this.shotScreenObserver);
        Log.i(TAG, "initLexusLsDrag() ");
        setFull();
        setStatusBarTranslucent();
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        LexusLsDragMainLayoutBinding lexusLsDragMainLayoutBinding = (LexusLsDragMainLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.lexus_ls_drag_main_layout);
        this.lexusLsDragBinding = lexusLsDragMainLayoutBinding;
        lexusLsDragMainLayoutBinding.setLexusLsViewModel(this.viewModel);
        this.recyclerview_drag = this.lexusLsDragBinding.recyclerviewDrag;
        this.mDragLayer = (DragLayer) findViewById(C0899R.C0901id.demo_draglayer);
        this.mDeleteZone = (DeleteZone) findViewById(C0899R.C0901id.demo_del_zone);
        this.lexusLsDragBinding.ivLexusLsDragLeftBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.25
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MainActivity.this.mScrollController.smoothScrollToPage(0, true);
            }
        });
        this.lexusLsDragBinding.ivLexusLsDragRightBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.26
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MainActivity.this.mScrollController.smoothScrollToPage(1, true);
            }
        });
        this.mDragController = new DragController(mainActivity);
        mRow = 1;
        mColumn = 8;
        this.pageSize = 8 * 1;
        this.isFirstLoad_V2 = Settings.System.getString(mainActivity.getContentResolver(), LexusLsConfig.IS_FIRST_V2);
        LOGE.m43E("isFirstLoad_V2 = " + this.isFirstLoad_V2);
        if (TextUtils.equals(this.isFirstLoad_V2, "true") || (str = this.isFirstLoad_V2) == null) {
            LOGE.m43E("isFirstLoad  TextUtils.equals(isFirstLoad,\"true\") || isFirstLoad==null");
            LexusLsConfig.mAppList_V2.clear();
            LexusLsConfig.mAppList_V2.addAll(LexusLsConfig.getMenuAppList_V2(mainActivity));
        } else if (TextUtils.equals(str, "false")) {
            LexusLsConfig.AppPkgs_V2 = Settings.System.getString(mainActivity.getContentResolver(), LexusLsConfig.LEXUS_LS_SAVE_PKG_V2);
            LOGE.m43E("TextUtils.equals(isFirstLoad,\"false\")LexusLsConfig.AppPkgs_V2 : " + LexusLsConfig.AppPkgs_V2);
            if (LexusLsConfig.AppPkgs_V2 != null && LexusLsConfig.AppPkgs_V2.length() > 0) {
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
            } else {
                LOGE.m43E("ELSE   isFirstLoad_V2 = " + this.isFirstLoad_V2 + "\nLexusLsConfig.AppPkgs_V2 : " + LexusLsConfig.AppPkgs_V2);
                LexusLsConfig.mAppList_V2.addAll(LexusLsConfig.getMenuAppList_V2(mainActivity));
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

    /* JADX INFO: Access modifiers changed from: private */
    public void executeItemReplaceAction(LexusLsAppSelBean sourceItem, LexusLsAppSelBean targetItem) {
        int sourcePos = sourceItem.getItemPos();
        int targetPos = targetItem.getItemPos();
        LOGE.m43E("sourcePos: " + sourcePos + " targetPos: " + targetPos);
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

    private void acControlStatus(ArrayList mlist, String pkg1, String pkg2) {
        int AcControl = Settings.System.getInt(getContentResolver(), KeyConfig.AC_CONTROL, 0);
        if (mlist.contains(LexusLsConfig.findBeanByPkg(pkg1, mlist)) && AcControl == 0) {
            mlist.remove(LexusLsConfig.findBeanByPkg(pkg1, mlist));
        } else if (!mlist.contains(LexusLsConfig.findBeanByPkg(pkg1, mlist)) && AcControl == 1) {
            LexusLsAppSelBean beanAC = new LexusLsAppSelBean();
            beanAC.setAppName(getResources().getString(C0899R.string.lexus_ac).trim());
            beanAC.setAppIcon(getResources().getDrawable(C0899R.C0900drawable.lexus_ls_main_icon_cut_air));
            beanAC.setAppPkg(pkg2 + ".7");
            beanAC.setItemPos(mlist.size());
            beanAC.setDeletable(false);
            mlist.add(7, beanAC);
        }
    }

    private void eqControlStatus(ArrayList mlist) {
        try {
            int isSee = PowerManagerApp.getSettingsInt(KeyConfig.EQ_APP);
            if (mlist.contains(LexusLsConfig.findBeanByPkg(BenzUtils.EQ_PKG, mlist)) && isSee == 0) {
                mlist.remove(LexusLsConfig.findBeanByPkg(BenzUtils.EQ_PKG, mlist));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void googleAPPSControl(ArrayList mlist) {
        int isSee = Settings.System.getInt(getContentResolver(), KeyConfig.GOOGLE_APP, 0);
        if (isSee == 0) {
            for (int i = 0; i < 4; i++) {
                String[] strArr = PKG_GOOGLE_APP;
                if (mlist.contains(LexusLsConfig.findBeanByPkg(strArr[i], mlist))) {
                    mlist.remove(LexusLsConfig.findBeanByPkg(strArr[i], mlist));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshItemList() {
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            if (LexusLsConfig.mAppList == null || LexusLsConfig.mAppList.size() <= 0) {
                return;
            }
            acControlStatus(LexusLsConfig.mAppList, LexusLsConfig.PKG_MENU_STRS[7], LexusLsConfig.PKG_DEFINED_MENU_LEXUSLS);
            eqControlStatus(LexusLsConfig.mAppList);
            googleAPPSControl(LexusLsConfig.mAppList);
            for (int i = 0; i < LexusLsConfig.mAppList.size(); i++) {
                LexusLsConfig.mAppList.get(i).setItemPos(i);
            }
        } else if (LexusLsConfig.mAppList_V2 != null && LexusLsConfig.mAppList_V2.size() > 0) {
            acControlStatus(LexusLsConfig.mAppList_V2, LexusLsConfig.PKG_MENU_STRS_V2[7], LexusLsConfig.PKG_DEFINED_MENU_LEXUSLS_V2);
            eqControlStatus(LexusLsConfig.mAppList_V2);
            googleAPPSControl(LexusLsConfig.mAppList_V2);
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLexusLs() {
        Log.i(TAG, "initLexusLs: ");
        setFull();
        setStatusBarTranslucent();
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        final LexusLsMainLayoutBinding lexusLsBinding = (LexusLsMainLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.lexus_ls_main_layout);
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
        this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomTwo.setOnClickAddApp(new FragmentLexusLsBottomTwo.IAddAppClickListener() { // from class: com.wits.ksw.MainActivity.27
            @Override // com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwo.IAddAppClickListener
            public void onClick(View view) {
                MainActivity.this.initPopuWindow(view);
            }
        });
        this.lexusLsBottomViewPagerAdpaterV2.fragmentLexusLsBottomTwoV2.setOnClickAddApp(new FragmentLexusLsBottomTwoV2.IAddAppClickListener() { // from class: com.wits.ksw.MainActivity.28
            @Override // com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwoV2.IAddAppClickListener
            public void onClick(View view) {
                MainActivity.this.initPopuWindow(view);
            }
        });
        lexusLsBinding.lexusLsViewpagerBottom.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.29
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int arg, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (i == 0) {
                    lexusLsBinding.ivLexuslsLeftBtn.setVisibility(4);
                    lexusLsBinding.ivLexuslsRightBtn.setVisibility(0);
                    return;
                }
                lexusLsBinding.ivLexuslsLeftBtn.setVisibility(0);
                lexusLsBinding.ivLexuslsRightBtn.setVisibility(4);
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        lexusLsBinding.lexusLsViewpagerBottom.setPageTransformer(true, new ViewPager.PageTransformer() { // from class: com.wits.ksw.MainActivity.30
            @Override // android.support.p001v4.view.ViewPager.PageTransformer
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
                    page.setTranslationX(pageWidth * (-position));
                } else {
                    page.setTranslationX(pageWidth);
                }
            }
        });
        lexusLsBinding.ivLexuslsLeftBtn.setVisibility(4);
        lexusLsBinding.ivLexuslsRightBtn.setVisibility(0);
        lexusLsBinding.ivLexuslsLeftBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.-$$Lambda$MainActivity$2W77CJzFilVAcoYIuTmDSFqX7WA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.lambda$initLexusLs$4$MainActivity(lexusLsBinding, view);
            }
        });
        lexusLsBinding.ivLexuslsRightBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.-$$Lambda$MainActivity$QRhS7yD_IXgj5WxibtTYHCzLtkA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.lambda$initLexusLs$5$MainActivity(lexusLsBinding, view);
            }
        });
    }

    public /* synthetic */ void lambda$initLexusLs$4$MainActivity(LexusLsMainLayoutBinding lexusLsBinding, View v) {
        int index = lexusLsBinding.lexusLsViewpagerBottom.getCurrentItem() - 1;
        if (index < 0) {
            return;
        }
        lexusLsBinding.lexusLsViewpagerBottom.setCurrentItem(index);
        if (index == 0) {
            if (UiThemeUtils.isLEXUS_LS_UI(this)) {
                this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomOne.setDefaultSelected();
            } else {
                this.lexusLsBottomViewPagerAdpaterV2.fragmentLexusLsBottomOne.setDefaultSelected();
            }
        } else if (index == 1) {
            if (UiThemeUtils.isLEXUS_LS_UI(this)) {
                this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomTwo.setDefaultSelected();
            } else {
                this.lexusLsBottomViewPagerAdpaterV2.fragmentLexusLsBottomTwoV2.setDefaultSelected();
            }
        }
    }

    public /* synthetic */ void lambda$initLexusLs$5$MainActivity(LexusLsMainLayoutBinding lexusLsBinding, View v) {
        int index = lexusLsBinding.lexusLsViewpagerBottom.getCurrentItem() + 1;
        if (index > 2) {
            return;
        }
        lexusLsBinding.lexusLsViewpagerBottom.setCurrentItem(index);
        if (index == 1) {
            if (UiThemeUtils.isLEXUS_LS_UI(this)) {
                this.lexusLsBottomViewPagerAdpater.fragmentLexusLsBottomTwo.setDefaultSelected();
            } else {
                this.lexusLsBottomViewPagerAdpaterV2.fragmentLexusLsBottomTwoV2.setDefaultSelected();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHideAndShowAddMenu() {
        if (UiThemeUtils.isLEXUS_LS_UI(this)) {
            if (LexusLsConfig.mAppList.size() > this.pageSize * 2) {
                if (LexusLsConfig.mAppList.contains(LexusLsConfig.findBeanByPkg(LexusLsConfig.PKG_MENU_STRS[12], LexusLsConfig.mAppList))) {
                    LexusLsConfig.mAppList.remove(LexusLsConfig.findBeanByPkg(LexusLsConfig.PKG_MENU_STRS[12], LexusLsConfig.mAppList));
                }
            } else if (!LexusLsConfig.mAppList.contains(LexusLsConfig.findBeanByPkg(LexusLsConfig.PKG_MENU_STRS[12], LexusLsConfig.mAppList)) && LexusLsConfig.mAppList.size() < this.pageSize * 2) {
                LexusLsAppSelBean beanAddApp = new LexusLsAppSelBean();
                beanAddApp.setAppName(mainActivity.getResources().getString(C0899R.string.add_app).trim());
                beanAddApp.setAppIcon(mainActivity.getResources().getDrawable(C0899R.C0900drawable.lexus_ls_main_icon_cut_add));
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
            beanAddApp2.setAppName(mainActivity.getResources().getString(C0899R.string.add_app).trim());
            beanAddApp2.setAppIcon(mainActivity.getResources().getDrawable(C0899R.C0900drawable.lexus_ls_main_icon_cut_add));
            beanAddApp2.setAppPkg("lexus.ls.defined.pkgnamev2.13");
            beanAddApp2.setItemPos(LexusLsConfig.mAppList_V2.size());
            beanAddApp2.setDeletable(false);
            LexusLsConfig.mAppList_V2.add(beanAddApp2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPopuWindow(View view) {
        if (this.mPopupWindow != null) {
            showPopuwindow(view);
            return;
        }
        String str = TAG;
        Log.e(str, "add app click~~initPopuWindow()");
        mAllAppList = (ArrayList) AppInfoUtils.findLexusLsAllAppDeleteDesk(mainActivity);
        View popDialog = LayoutInflater.from(mainActivity).inflate(C0899R.C0902layout.lexus_ls_add_pop_layout, (ViewGroup) null);
        this.dialogWidth = ScreenUtil.dip2px(mainActivity.getResources().getDimension(C0899R.dimen.dimen_lexus_ls_pop_width));
        Log.e(str, "diwidth =" + this.dialogWidth);
        this.dialogHeight = ScreenUtil.dip2px(400.0f);
        PopupWindow popupWindow = new PopupWindow(popDialog, ScreenUtil.dip2px(480.0f), ScreenUtil.dip2px(420.0f), true);
        this.mPopupWindow = popupWindow;
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.lexus_ls_addapp_iv_cancel = (TextView) popDialog.findViewById(C0899R.C0901id.lexus_ls_addapp_iv_cancel);
        this.lexus_ls_addapp_iv_ok = (TextView) popDialog.findViewById(C0899R.C0901id.lexus_ls_addapp_iv_ok);
        this.lexus_ls_addapp_iv_cancel.setOnClickListener(this.mDialogBtnClickListener);
        this.lexus_ls_addapp_iv_ok.setOnClickListener(this.mDialogBtnClickListener);
        this.lexus_ls_add_app_recycleview = (RecyclerView) popDialog.findViewById(C0899R.C0901id.lexus_ls_add_app_recycleview);
        this.lexusLsSelAppAdapter = new LexusLsSelAppAdapter(mainActivity, mAllAppList);
        PagerGridLayoutManager lexuslsLayoutManager = new PagerGridLayoutManager(3, 4, 0);
        this.lexus_ls_add_app_recycleview.setAdapter(this.lexusLsSelAppAdapter);
        this.lexus_ls_add_app_recycleview.setLayoutManager(lexuslsLayoutManager);
        this.mPopupWindow.setOutsideTouchable(true);
        showPopuwindow(view);
        this.mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.wits.ksw.-$$Lambda$MainActivity$zl7045iidAcYRJagODcIUvNiOlg
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                MainActivity.this.lambda$initPopuWindow$6$MainActivity();
            }
        });
    }

    public /* synthetic */ void lambda$initPopuWindow$6$MainActivity() {
        this.mPopupWindow = null;
    }

    private void showPopuwindow(View view) {
        int height = view.getHeight();
        int width = view.getWidth();
        Log.i(TAG, String.format("width = %s ;height= %s;", Integer.valueOf(width), Integer.valueOf(height)));
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
                List<View> viewGroupChildViews = getViewGroupChildViews((ViewGroup) viewchild);
                allchildren.addAll(viewGroupChildViews);
            } else {
                allchildren.add(viewchild);
            }
        }
        Log.d(TAG, "getViewGroupChildViews allchildren size=" + allchildren.size());
        return allchildren;
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBwmID7Hicar() {
        Log.i(TAG, "initBwmID7Hicar: ");
        initBmwid7UiView();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initRomeo() {
        setFull();
        RomeoViewModel romeoViewModel = (RomeoViewModel) ViewModelProviders.m59of(this).get(RomeoViewModel.class);
        this.romeoViewModel = romeoViewModel;
        romeoViewModel.setActivity(this);
        ActivityRomeoBinding activityRomeoBinding = (ActivityRomeoBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_romeo);
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
        FixLinearSnapHelper snapHelper = new FixLinearSnapHelper();
        snapHelper.attachToRecyclerView(this.romeoBinding.romeoMainRv);
        this.romeoBinding.romeoMainRv.setAdapter(rvAdapter);
        this.romeoBinding.pageIndicator1.getDrawable().setLevel(1);
        this.romeoBinding.pageIndicator2.getDrawable().setLevel(0);
        this.romeoBinding.romeoMainRv.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.wits.ksw.MainActivity.32
            @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(MainActivity.TAG, "recyclerView onScrollStateChanged newState=" + newState);
                if (newState == 0) {
                    LinearLayoutManager layoutManager2 = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisibleItemPosition = layoutManager2.findFirstVisibleItemPosition();
                    Log.d(MainActivity.TAG, "recyclerView onScrollStateChanged firstVisibleItemPosition=" + firstVisibleItemPosition);
                    if (firstVisibleItemPosition == 0) {
                        MainActivity.this.romeoBinding.pageIndicator1.getDrawable().setLevel(1);
                        MainActivity.this.romeoBinding.pageIndicator2.getDrawable().setLevel(0);
                        return;
                    }
                    MainActivity.this.romeoBinding.pageIndicator1.getDrawable().setLevel(0);
                    MainActivity.this.romeoBinding.pageIndicator2.getDrawable().setLevel(1);
                }
            }

            @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(MainActivity.TAG, "recyclerView onScrolled dx=" + dx + " dy=" + dy);
                MainActivity.this.changeDistance(recyclerView);
            }
        });
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initRomeo_V2() {
        setFull();
        RomeoViewModelV2 romeoViewModelV2 = (RomeoViewModelV2) ViewModelProviders.m59of(this).get(RomeoViewModelV2.class);
        this.romeoViewModelv2 = romeoViewModelV2;
        romeoViewModelV2.setActivity(this);
        ActivityRomeoV2Binding activityRomeoV2Binding = (ActivityRomeoV2Binding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_romeo_v2);
        this.romeoV2Binding = activityRomeoV2Binding;
        activityRomeoV2Binding.setViewModel(this.romeoViewModelv2);
        this.romeoViewModelv2.initData();
        this.romeoViewModelv2.startWeatherLooper();
        this.romeoViewModelv2.setRomeoBinding(this.romeoV2Binding);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(1);
        this.romeoV2Binding.romeoMainRv.setLayoutManager(layoutManager);
        this.romeoV2Binding.romeoMainRv.setItemViewCacheSize(0);
        RomeoMainListAdapterV2 rvAdapter = new RomeoMainListAdapterV2(this, this.romeoViewModelv2);
        rvAdapter.setLayoutManager(layoutManager);
        rvAdapter.setBinding(this.romeoV2Binding);
        FixLinearSnapHelper snapHelper = new FixLinearSnapHelper();
        snapHelper.attachToRecyclerView(this.romeoV2Binding.romeoMainRv);
        this.romeoV2Binding.romeoMainRv.setAdapter(rvAdapter);
        this.romeoV2Binding.pageIndicator1.getDrawable().setLevel(1);
        this.romeoV2Binding.pageIndicator2.getDrawable().setLevel(0);
        this.romeoV2Binding.romeoMainRv.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.wits.ksw.MainActivity.33
            @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(MainActivity.TAG, "recyclerView onScrollStateChanged newState=" + newState);
                if (newState == 0) {
                    LinearLayoutManager layoutManager2 = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisibleItemPosition = layoutManager2.findFirstVisibleItemPosition();
                    Log.d(MainActivity.TAG, "recyclerView onScrollStateChanged firstVisibleItemPosition=" + firstVisibleItemPosition);
                    if (firstVisibleItemPosition == 0) {
                        MainActivity.this.romeoV2Binding.pageIndicator1.getDrawable().setLevel(1);
                        MainActivity.this.romeoV2Binding.pageIndicator2.getDrawable().setLevel(0);
                        return;
                    }
                    MainActivity.this.romeoV2Binding.pageIndicator1.getDrawable().setLevel(0);
                    MainActivity.this.romeoV2Binding.pageIndicator2.getDrawable().setLevel(1);
                }
            }

            @Override // android.support.p004v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(MainActivity.TAG, "recyclerView onScrolled dx=" + dx + " dy=" + dy);
                MainActivity.this.changeDistance(recyclerView);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeDistance(RecyclerView recyclerView) {
        Log.d(TAG, "calculateTranslate count=" + recyclerView.getChildCount());
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            int pad = KswUtils.calculateTranslate(recyclerView.getChildAt(i).getTop(), KswUtils.dip2px(this, 428.0f), i, this);
            recyclerView.getChildAt(i).setPadding(pad, 0, 0, 0);
        }
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenzGSView() {
        setFull();
        BenzGsViewMoel mBenzGsViewMoel = (BenzGsViewMoel) ViewModelProviders.m59of(this).get(BenzGsViewMoel.class);
        mBenzGsViewMoel.setActivity(this);
        mBenzGsViewMoel.initData();
        ActivityMainBenzGsBinding benzGsbinding = (ActivityMainBenzGsBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_benz_gs);
        benzGsbinding.benzgsViewpage.setAdapter(new BenzGsHomePagerAdpater(getSupportFragmentManager()));
        benzGsbinding.benzgsViewpage.setCurrentItem(0);
        benzGsbinding.benzgsViewpage.setOffscreenPageLimit(2);
        benzGsbinding.benzgsViewpage.setViewMoel(mBenzGsViewMoel);
        benzGsbinding.setVm(mBenzGsViewMoel);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initGSUiView() {
        Log.i(TAG, "initGSUiView: ");
        initSaveData();
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        startActivity(new Intent(this, Id7NewActivity.class));
        finish();
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initCommonUIGSUGView() {
        Log.i(TAG, "initCommonUIGSUGView: ");
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        this.ugBinding = (ActivityMainGsugBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_gsug);
        if (ClientManager.getInstance().isGSClient() && width == 1920) {
            this.ugBinding.musicButton.setImageResource(C0899R.C0900drawable.ug_music_gs_selector);
            this.ugBinding.naviButton.setImageResource(C0899R.C0900drawable.ug_navi_gs_selector);
            this.ugBinding.settingButton.setImageResource(C0899R.C0900drawable.ug_setting_gs_selector);
            this.ugBinding.appsButton.setImageResource(C0899R.C0900drawable.ug_app_gs_selector);
        }
        this.ugBinding.ugViewPage.setAdapter(new UgHomePagerAdpater(getSupportFragmentManager()));
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        this.ugBinding.setViewModel(this.viewModel);
        this.ugBinding.ugViewPage.setUgPageChangeListener(new UgViewPager.UgPageChangeListener() { // from class: com.wits.ksw.MainActivity.34
            @Override // com.wits.ksw.launcher.view.p006ug.UgViewPager.UgPageChangeListener
            public void onPageSelected(int i, boolean left, boolean right) {
                Log.i(MainActivity.TAG, "onPageSelected: i:" + i + " left:" + left + " right:" + right);
                MainActivity.this.select.setValue(new UgPager(i, left, right));
            }
        });
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initCommonUIGSUG1024View() {
        Log.i(TAG, "initCommonUIGSUG1024View: ");
        ActivityMainGsug2Binding activityMainGsug2Binding = (ActivityMainGsug2Binding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_gsug2);
        this.gsug2Binding = activityMainGsug2Binding;
        activityMainGsug2Binding.ugViewPage.setAdapter(new UgHomePagerAdpater(getSupportFragmentManager()));
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        this.gsug2Binding.setViewModel(this.viewModel);
        this.gsug2Binding.ugViewPage.setUgPageChangeListener(new UgViewPager.UgPageChangeListener() { // from class: com.wits.ksw.MainActivity.35
            @Override // com.wits.ksw.launcher.view.p006ug.UgViewPager.UgPageChangeListener
            public void onPageSelected(int i, boolean left, boolean right) {
                Log.i(MainActivity.TAG, "onPageSelected: i:" + i + " left:" + left + " right:" + right);
                MainActivity.this.select.setValue(new UgPager(i, left, right));
            }
        });
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initCommonUIKSWMBUX1024View() {
        Log.i(TAG, "initCommonUIKSWMBUX1024View: ");
        ActivityMainKswmbuxBinding activityMainKswmbuxBinding = (ActivityMainKswmbuxBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_kswmbux);
        this.kswmbuxBinding = activityMainKswmbuxBinding;
        activityMainKswmbuxBinding.ugViewPage.setAdapter(new KSWMBUXHomePagerAdpater(getSupportFragmentManager()));
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.viewModel.initData();
        this.kswmbuxBinding.setViewModel(this.viewModel);
        this.kswmbuxBinding.ugViewPage.setUgPageChangeListener(new UgViewPager.UgPageChangeListener() { // from class: com.wits.ksw.MainActivity.36
            @Override // com.wits.ksw.launcher.view.p006ug.UgViewPager.UgPageChangeListener
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
        this.kswmbuxBinding.ugViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.37
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                MainActivity.this.kswmbuxBinding.imageView1.setSelected(i == 0);
                MainActivity.this.kswmbuxBinding.imageView2.setSelected(i == 1);
                MainActivity.this.kswmbuxBinding.imageView3.setSelected(i == 2);
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsId7UI() {
        Log.i(TAG, "chen-new ui initAlsId7UI: ");
        AlsID7ViewModel alsID7ViewModel = (AlsID7ViewModel) ViewModelProviders.m59of(this).get(AlsID7ViewModel.class);
        this.alsID7ViewModel = alsID7ViewModel;
        alsID7ViewModel.setActivity(this);
        ActivityMainAlsId7Binding activityMainAlsId7Binding = (ActivityMainAlsId7Binding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_als_id7);
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
        if (AlsID7ViewModel.screenPixels.get().contains(",")) {
            AlsID7ViewModel.screenWidth.set(Integer.valueOf(AlsID7ViewModel.screenPixels.get().split(",")[0]));
            AlsID7ViewModel.screenHeight.set(Integer.valueOf(AlsID7ViewModel.screenPixels.get().split(",")[1]));
        } else {
            AlsID7ViewModel.screenWidth.set(-1);
            AlsID7ViewModel.screenHeight.set(-1);
        }
        this.alsId7Binding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.38
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
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

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAlsId7UI_V2() {
        Log.i(TAG, "chen-new ui initAlsId7UI_V2: ");
        AlsID7ViewModel alsID7ViewModel = (AlsID7ViewModel) ViewModelProviders.m59of(this).get(AlsID7ViewModel.class);
        this.alsID7ViewModel = alsID7ViewModel;
        alsID7ViewModel.setActivity(this);
        ActivityMainAlsId7V2Binding activityMainAlsId7V2Binding = (ActivityMainAlsId7V2Binding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_als_id7_v2);
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
        AlsID7ViewModel.screenPixels.set(DisplayUtil.getRealScreenRelatedInformation(this));
        if (AlsID7ViewModel.screenPixels.get().contains(",")) {
            AlsID7ViewModel.screenWidth.set(Integer.valueOf(AlsID7ViewModel.screenPixels.get().split(",")[0]));
            AlsID7ViewModel.screenHeight.set(Integer.valueOf(AlsID7ViewModel.screenPixels.get().split(",")[1]));
        } else {
            AlsID7ViewModel.screenWidth.set(-1);
            AlsID7ViewModel.screenHeight.set(-1);
        }
        this.alsId7V2Binding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.39
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
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

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        this.alsId7V2Binding.btnShut1.setOnLongClickListener(this.mOnLongListener_AlsId7_V2);
        this.alsId7V2Binding.btnShut2.setOnLongClickListener(this.mOnLongListener_AlsId7_V2);
        this.alsId7V2Binding.btnShut3.setOnLongClickListener(this.mOnLongListener_AlsId7_V2);
        this.alsId7V2Binding.btnApps.setOnClickListener(this.mOnClickListener_AlsId7_V2);
        this.alsId7V2Binding.btnSettings.setOnClickListener(this.mOnClickListener_AlsId7_V2);
        this.alsId7V2Binding.btnShut1.setOnClickListener(this.mOnClickListener_AlsId7_V2);
        this.alsId7V2Binding.btnShut2.setOnClickListener(this.mOnClickListener_AlsId7_V2);
        this.alsId7V2Binding.btnShut3.setOnClickListener(this.mOnClickListener_AlsId7_V2);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initLandRover() {
        LandroverViewModel landroverViewModel = (LandroverViewModel) ViewModelProviders.m59of(this).get(LandroverViewModel.class);
        this.landroverViewModel = landroverViewModel;
        landroverViewModel.setActivity(this);
        LandroverMainBinding landroverMainBinding = (LandroverMainBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.landrover_main);
        this.landroverBinding = landroverMainBinding;
        landroverMainBinding.viewPager.setAdapter(new LandRoverViewPagerAdpater(getSupportFragmentManager()));
        setCurrentItem(0);
        this.landroverBinding.viewPager.setOffscreenPageLimit(2);
        this.landroverBinding.indicato1.setSelected(true);
        this.landroverBinding.indicato2.setSelected(false);
        this.landroverBinding.setLauncherViewModel(this.landroverViewModel);
        this.landroverViewModel.initData();
        LandroverPopWindow.setViewModel(this.landroverViewModel);
        this.landroverBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.44
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
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

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        this.landroverBinding.iconLeft.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.45
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MainActivity.this.landroverBinding.viewPager.setCurrentItem(0);
            }
        });
        this.landroverBinding.iconRight.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.46
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MainActivity.this.landroverBinding.viewPager.setCurrentItem(1);
            }
        });
    }

    /* loaded from: classes17.dex */
    private class LandroverHandler extends Handler {
        private LandroverHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                default:
                    return;
            }
        }
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudi_mib3_FyUiView() {
        Log.i(TAG, "initAudiMbi3FyView : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mAudiMbi3FyBcViewModel = bcVieModel;
        bcVieModel.setActivity(this);
        mAudiMbi3FyBcViewModel.initData();
        AudiMib3FyMainLayoutBinding audiMib3FyMainLayoutBinding = (AudiMib3FyMainLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.audi_mib3_fy_main_layout);
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
        this.viewpagerAudiMib3Fy.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.47
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int pageIndex) {
                Log.d("onPageSelected", "0000000000");
                if (pageIndex == 0) {
                    BcVieModel bcVieModel2 = MainActivity.mAudiMbi3FyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FyFragmentOne audiMib3FyFragmentOne = (AudiMib3FyFragmentOne) MainActivity.audiMib3FyViewPagerAdapter.fragmentPage1;
                        BcVieModel bcVieModel3 = MainActivity.mAudiMbi3FyBcViewModel;
                        AudiMib3FyFragmentOne.setItemSelected(BcVieModel.viewLastSel);
                        MainActivity.this.setIndicatorBackground(pageIndex, 10);
                    }
                }
                if (pageIndex == 1) {
                    BcVieModel bcVieModel4 = MainActivity.mAudiMbi3FyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FyFragmentTwo audiMib3FyFragmentTwo = (AudiMib3FyFragmentTwo) MainActivity.audiMib3FyViewPagerAdapter.fragmentPage2;
                        BcVieModel bcVieModel5 = MainActivity.mAudiMbi3FyBcViewModel;
                        AudiMib3FyFragmentTwo.setItemSelected(BcVieModel.viewLastSel);
                    }
                }
                MainActivity.this.setIndicatorBackground(pageIndex, 10);
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(10);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudi_mib3_Fy_V2_UiView() {
        Log.i(TAG, "initAudi_mib3_Fy_V2_UiView : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mAudiMbi3FyBcViewModel = bcVieModel;
        bcVieModel.setActivity(this);
        mAudiMbi3FyBcViewModel.initData();
        AudiMib3FyMainLayoutBinding audiMib3FyMainLayoutBinding = (AudiMib3FyMainLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.audi_mib3_fy_main_layout);
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
        this.viewpagerAudiMib3Fy.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.48
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int pageIndex) {
                Log.d("onPageSelected", "0000000000");
                if (pageIndex == 0) {
                    BcVieModel bcVieModel2 = MainActivity.mAudiMbi3FyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FyV2FragmentOne audiMib3FyV2FragmentOne = (AudiMib3FyV2FragmentOne) MainActivity.audiMib3FyV2ViewPagerAdapter.fragmentPage1;
                        BcVieModel bcVieModel3 = MainActivity.mAudiMbi3FyBcViewModel;
                        AudiMib3FyV2FragmentOne.setItemSelected(BcVieModel.viewLastSel);
                        MainActivity.this.setIndicatorBackground(pageIndex, 10);
                    }
                }
                if (pageIndex == 1) {
                    BcVieModel bcVieModel4 = MainActivity.mAudiMbi3FyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FyV2FragmentTwo audiMib3FyV2FragmentTwo = (AudiMib3FyV2FragmentTwo) MainActivity.audiMib3FyV2ViewPagerAdapter.fragmentPage2;
                        BcVieModel bcVieModel5 = MainActivity.mAudiMbi3FyBcViewModel;
                        AudiMib3FyV2FragmentTwo.setItemSelected(BcVieModel.viewLastSel);
                    }
                }
                MainActivity.this.setIndicatorBackground(pageIndex, 10);
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(10);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiMib3Ty() {
        Log.i(TAG, "initAudiMib3Ty : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mAudiMib3TyBcViewModel = bcVieModel;
        bcVieModel.setActivity(this);
        mAudiMib3TyBcViewModel.initData();
        AudiMib3TyMainLayoutBinding audiMib3TyMainLayoutBinding2 = (AudiMib3TyMainLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.audi_mib3_ty_main_layout);
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
        this.audiMib3TyViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.49
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int pageIndex) {
                Log.d("onPageSelected", "0000000000");
                if (pageIndex == 0) {
                    BcVieModel bcVieModel2 = MainActivity.mAudiMib3TyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3TyFragmentOne audiMib3TyFragmentOne = (AudiMib3TyFragmentOne) MainActivity.audiMib3TyViewPagerAdapter.fragmentPage1;
                        BcVieModel bcVieModel3 = MainActivity.mAudiMib3TyBcViewModel;
                        AudiMib3TyFragmentOne.setItemSelected(BcVieModel.viewLastSel);
                        MainActivity.this.setIndicatorBackground(pageIndex, 11);
                    }
                }
                if (pageIndex == 1) {
                    BcVieModel bcVieModel4 = MainActivity.mAudiMib3TyBcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3TyFragmentTwo audiMib3TyFragmentTwo = (AudiMib3TyFragmentTwo) MainActivity.audiMib3TyViewPagerAdapter.fragmentPage2;
                        BcVieModel bcVieModel5 = MainActivity.mAudiMib3TyBcViewModel;
                        AudiMib3TyFragmentTwo.setItemSelected(BcVieModel.viewLastSel);
                    }
                }
                MainActivity.this.setIndicatorBackground(pageIndex, 11);
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(11);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwId8GsUiView() {
        setFull();
        setStatusBarTranslucent();
        this.systemCardViewHashMap = new HashMap<>();
        this.trdCardViewHashMap = new HashMap<>();
        Log.i(TAG, "initBmwGSId8UiView: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.bmwId8ViewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        ActivityGsId8LauncherMainBinding activityGsId8LauncherMainBinding = (ActivityGsId8LauncherMainBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_gs_id8_launcher_main);
        this.bmwId8GsUiMainLayoutBinding = activityGsId8LauncherMainBinding;
        activityGsId8LauncherMainBinding.setLauncherViewModel(this.bmwId8ViewModel);
        ArrayList arrayList = new ArrayList();
        this.mLeftBarView = arrayList;
        arrayList.add(this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft1);
        this.mLeftBarView.add(this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft2);
        this.mLeftBarView.add(this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft3);
        this.mLeftBarView.add(this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft4);
        this.mLeftBarView.add(this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft5);
        registerKeyControlBroadcastReceiver();
        this.bmwId8GsUiMainLayoutBinding.gsScrollView.setScrollViewListener(new ID8HorizontalScrollView.ScrollViewListener() { // from class: com.wits.ksw.MainActivity.51
            @Override // com.wits.ksw.launcher.bmw_id8_ui.view.ID8HorizontalScrollView.ScrollViewListener
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                MainActivity.this.currentScrollX = x;
                MainActivity.this.currentScrollY = y;
                MainActivity.this.currentOldScrollX = oldx;
                Log.w(MainActivity.TAG, "scrollX11111: " + MainActivity.this.currentScrollX + "currentScrollY: " + MainActivity.this.currentScrollY);
            }
        });
        this.bmwId8GsUiMainLayoutBinding.gsScrollView.setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.MainActivity.52
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int eventAction = event.getAction();
                int rawY = (int) event.getRawY();
                switch (eventAction) {
                    case 1:
                        MainActivity.this.handler.sendMessageDelayed(MainActivity.this.handler.obtainMessage(MainActivity.this.touchEventId, v), 50L);
                        return false;
                    case 2:
                    default:
                        return false;
                }
            }
        });
        OnID8SkinChangeListener onID8SkinChangeListener = new OnID8SkinChangeListener() { // from class: com.wits.ksw.MainActivity.53
            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeLeftBar(int drawableId) {
                if (MainActivity.this.bmwId8GsUiMainLayoutBinding != null) {
                    MainActivity.this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft1.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft2.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft3.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft4.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8GsUiMainLayoutBinding.llLeftContainer.llLeft5.setBackground(MainActivity.this.getDrawable(drawableId));
                }
            }

            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
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

            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeMusicAlbum(int drawableId) {
                View view = (View) MainActivity.this.systemCardViewHashMap.get("MUSIC");
                if (view != null && LauncherViewModel.mediaInfo.pic.get() == null) {
                    Log.w(MainActivity.TAG, "onSkinChangeMusicAlbum: " + drawableId);
                }
            }
        };
        this.onGsID8SkinChangeListener = onID8SkinChangeListener;
        this.bmwId8ViewModel.initSkinData(onID8SkinChangeListener);
        this.bmwId8ViewModel.initData();
        this.bmwId8ViewModel.startWeatherLooper();
        ActivityGsId8LauncherMainBinding activityGsId8LauncherMainBinding2 = this.bmwId8GsUiMainLayoutBinding;
        if (activityGsId8LauncherMainBinding2 != null) {
            activityGsId8LauncherMainBinding2.llLeftContainer.llLeft1.setFocusedByDefault(true);
        }
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwId8PempUiView() {
        setFull();
        setStatusBarTranslucent();
        this.systemCardViewHashMap = new HashMap<>();
        this.trdCardViewHashMap = new HashMap<>();
        Log.i(TAG, "initBmwId8PempUiView: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.bmwId8ViewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        ActivityPempId8LauncherMainBinding activityPempId8LauncherMainBinding = (ActivityPempId8LauncherMainBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_pemp_id8_launcher_main);
        this.bmwPempId8UiMainLayoutBinding = activityPempId8LauncherMainBinding;
        activityPempId8LauncherMainBinding.setLauncherViewModel(this.bmwId8ViewModel);
        OnID8SkinChangeListener onID8SkinChangeListener = new OnID8SkinChangeListener() { // from class: com.wits.ksw.MainActivity.54
            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeLeftBar(int drawableId) {
                if (MainActivity.this.bmwPempId8UiMainLayoutBinding != null) {
                    MainActivity.this.bmwPempId8UiMainLayoutBinding.llLeftContainer.llLeft1.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwPempId8UiMainLayoutBinding.llLeftContainer.llLeft2.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwPempId8UiMainLayoutBinding.llLeftContainer.llLeft3.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwPempId8UiMainLayoutBinding.llLeftContainer.llLeft4.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwPempId8UiMainLayoutBinding.llLeftContainer.llLeft5.setBackground(MainActivity.this.getDrawable(drawableId));
                }
            }

            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeCardBGSelector(String skinName) {
                if (MainActivity.this.systemCardViewHashMap != null && MainActivity.this.systemCardViewHashMap.size() != 0) {
                    for (Map.Entry<String, View> next : MainActivity.this.systemCardViewHashMap.entrySet()) {
                        String keyName = next.getKey();
                        View targetView = next.getValue();
                        if (!TextUtils.isEmpty(keyName) && targetView != null) {
                            MainActivity.this.setPempID8CardBGSelector(keyName, targetView, skinName);
                        }
                    }
                    if (MainActivity.this.trdCardViewHashMap != null && MainActivity.this.trdCardViewHashMap.size() != 0) {
                        for (Map.Entry<String, View> next2 : MainActivity.this.trdCardViewHashMap.entrySet()) {
                            String keyName2 = next2.getKey();
                            View targetView2 = next2.getValue();
                            if (targetView2 != null) {
                                MainActivity.this.setPempID8CardBGSelector(keyName2, targetView2, skinName);
                                MainActivity.this.setID8TrdCardSkinResources(targetView2, skinName);
                            }
                        }
                    }
                }
            }

            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeMusicAlbum(int drawableId) {
                View view = (View) MainActivity.this.systemCardViewHashMap.get("MUSIC");
                if (view != null && LauncherViewModel.mediaInfo.pic.get() == null) {
                    Log.w(MainActivity.TAG, "onSkinChangeMusicAlbum: " + drawableId);
                    ((ImageView) view.findViewById(C0899R.C0901id.iv_music_album)).setImageDrawable(MainActivity.this.getDrawable(drawableId));
                }
            }
        };
        this.onPempID8SkinChangeListener = onID8SkinChangeListener;
        this.bmwId8ViewModel.initSkinData(onID8SkinChangeListener);
        this.bmwId8ViewModel.initData();
        this.bmwId8ViewModel.startWeatherLooper();
        ActivityPempId8LauncherMainBinding activityPempId8LauncherMainBinding2 = this.bmwPempId8UiMainLayoutBinding;
        if (activityPempId8LauncherMainBinding2 != null) {
            activityPempId8LauncherMainBinding2.llLeftContainer.llLeft1.setFocusedByDefault(true);
        }
    }

    public void scrollViewRightStop(int scrollViewX) {
        if (scrollViewX > 0 && scrollViewX <= this.cardWith * this.cardRightScroll) {
            this.currentScrollX = 0;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            return;
        }
        int i = this.cardWith;
        double d = this.cardRightScroll;
        if (i * d < scrollViewX && scrollViewX <= i * (d + 1.0d)) {
            this.currentScrollX = i;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (1.0d + d) < scrollViewX && scrollViewX <= i * (d + 2.0d)) {
            this.currentScrollX = i * 2;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (2.0d + d) < scrollViewX && scrollViewX <= i * (d + 3.0d)) {
            this.currentScrollX = i * 3;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (3.0d + d) < scrollViewX && scrollViewX <= i * (d + 4.0d)) {
            this.currentScrollX = i * 4;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (4.0d + d) < scrollViewX && scrollViewX <= i * (d + 5.0d)) {
            this.currentScrollX = i * 5;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (5.0d + d) < scrollViewX && scrollViewX <= i * (d + 6.0d)) {
            this.currentScrollX = i * 6;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (6.0d + d) < scrollViewX && scrollViewX <= i * (d + 7.0d)) {
            this.currentScrollX = i * 7;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (7.0d + d) < scrollViewX && scrollViewX <= i * (d + 8.0d)) {
            this.currentScrollX = i * 8;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (8.0d + d) < scrollViewX && scrollViewX <= i * (d + 9.0d)) {
            this.currentScrollX = i * 9;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (9.0d + d) < scrollViewX && scrollViewX <= i * (d + 10.0d)) {
            this.currentScrollX = i * 10;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        }
    }

    public void scrollViewLeftStop(int scrollViewX) {
        if (scrollViewX > 0 && scrollViewX <= this.cardWith * this.cardLeftScroll) {
            this.currentScrollX = 0;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
            return;
        }
        int i = this.cardWith;
        double d = this.cardLeftScroll;
        if (i * d < scrollViewX && scrollViewX <= i * (d + 1.0d)) {
            this.currentScrollX = i;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (1.0d + d) < scrollViewX && scrollViewX <= i * (d + 2.0d)) {
            this.currentScrollX = i * 2;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (2.0d + d) < scrollViewX && scrollViewX <= i * (d + 3.0d)) {
            this.currentScrollX = i * 3;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (3.0d + d) < scrollViewX && scrollViewX <= i * (d + 4.0d)) {
            this.currentScrollX = i * 4;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (4.0d + d) < scrollViewX && scrollViewX <= i * (d + 5.0d)) {
            this.currentScrollX = i * 5;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (5.0d + d) < scrollViewX && scrollViewX <= i * (d + 6.0d)) {
            this.currentScrollX = i * 6;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (6.0d + d) < scrollViewX && scrollViewX <= i * (d + 7.0d)) {
            this.currentScrollX = i * 7;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (7.0d + d) < scrollViewX && scrollViewX <= i * (d + 8.0d)) {
            this.currentScrollX = i * 8;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (8.0d + d) < scrollViewX && scrollViewX <= i * (d + 9.0d)) {
            this.currentScrollX = i * 9;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (9.0d + d) < scrollViewX && scrollViewX <= i * (d + 10.0d)) {
            this.currentScrollX = i * 10;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        } else if (i * (10.0d + d) < scrollViewX && scrollViewX <= i * (d + 11.0d)) {
            this.currentScrollX = i * 11;
            this.bmwId8GsUiMainLayoutBinding.gsScrollView.smoothScrollTo(this.currentScrollX, this.currentScrollY);
        }
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBmwId8UiView() {
        setFull();
        setStatusBarTranslucent();
        this.systemCardViewHashMap = new HashMap<>();
        this.trdCardViewHashMap = new HashMap<>();
        Log.i(TAG, "initBmwId8UiView: ");
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.m59of(this).get(LauncherViewModel.class);
        this.bmwId8ViewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        ActivityId8LauncherMainBinding activityId8LauncherMainBinding = (ActivityId8LauncherMainBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_id8_launcher_main);
        this.bmwId8UiMainLayoutBinding = activityId8LauncherMainBinding;
        activityId8LauncherMainBinding.setLauncherViewModel(this.bmwId8ViewModel);
        OnID8SkinChangeListener onID8SkinChangeListener = new OnID8SkinChangeListener() { // from class: com.wits.ksw.MainActivity.55
            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeLeftBar(int drawableId) {
                if (MainActivity.this.bmwId8UiMainLayoutBinding != null) {
                    MainActivity.this.bmwId8UiMainLayoutBinding.llLeftContainer.llLeft1.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8UiMainLayoutBinding.llLeftContainer.llLeft2.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8UiMainLayoutBinding.llLeftContainer.llLeft3.setBackground(MainActivity.this.getDrawable(drawableId));
                    MainActivity.this.bmwId8UiMainLayoutBinding.llLeftContainer.llLeft4.setBackground(MainActivity.this.getDrawable(drawableId));
                }
            }

            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
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

            @Override // com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener
            public void onSkinChangeMusicAlbum(int drawableId) {
                View view = (View) MainActivity.this.systemCardViewHashMap.get("MUSIC");
                if (view != null && LauncherViewModel.mediaInfo.pic.get() == null) {
                    Log.w(MainActivity.TAG, "onSkinChangeMusicAlbum: " + drawableId);
                    ((ImageView) view.findViewById(C0899R.C0901id.iv_music_album)).setImageDrawable(MainActivity.this.getDrawable(drawableId));
                }
            }
        };
        this.onID8SkinChangeListener = onID8SkinChangeListener;
        this.bmwId8ViewModel.initSkinData(onID8SkinChangeListener);
        this.bmwId8ViewModel.initData();
        this.bmwId8ViewModel.startWeatherLooper();
        ActivityId8LauncherMainBinding activityId8LauncherMainBinding2 = this.bmwId8UiMainLayoutBinding;
        if (activityId8LauncherMainBinding2 != null) {
            activityId8LauncherMainBinding2.llLeftContainer.llLeft1.setFocusedByDefault(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setID8CardBGSelector(String cardName, View view, String skinName) {
        int bigCardDrawableId;
        int smallCardDrawableId;
        int drawableId;
        if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
            bigCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_navi_mask_blue;
            smallCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_weather_mask_blue;
        } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            bigCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_navi_mask_red;
            smallCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_weather_mask_red;
        } else {
            bigCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_navi_mask_yellow;
            smallCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_weather_mask_yellow;
        }
        if ("NAVIGATE".equals(cardName) || "DASHBOARD".equals(cardName) || "CAR INFO".equals(cardName)) {
            drawableId = bigCardDrawableId;
        } else {
            drawableId = smallCardDrawableId;
        }
        if (view != null) {
            view.findViewById(C0899R.C0901id.iv_mask).setBackground(getDrawable(drawableId));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setID8GsCardBGSelector(String cardName, View view, String skinName) {
        int drawableId;
        if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
            drawableId = C0899R.C0900drawable.bmw_id8_gs_main_icon_mask_blue;
        } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            drawableId = C0899R.C0900drawable.bmw_id8_gs_main_icon_mask_red;
        } else {
            drawableId = C0899R.C0900drawable.bmw_id8_gs_main_icon_mask_yellow;
        }
        if (view != null) {
            view.findViewById(C0899R.C0901id.iv_mask).setBackground(getDrawable(drawableId));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPempID8CardBGSelector(String cardName, View view, String skinName) {
        int bigCardDrawableId;
        int smallCardDrawableId;
        int drawableId;
        if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
            bigCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_navi_mask_blue;
            smallCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_weather_mask_blue;
        } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            bigCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_navi_mask_red;
            smallCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_weather_mask_red;
        } else {
            bigCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_navi_mask_yellow;
            smallCardDrawableId = C0899R.C0900drawable.bmw_id8_main_icon_weather_mask_yellow;
        }
        if ("NAVIGATE".equals(cardName) || "DASHBOARD".equals(cardName) || "CAR INFO".equals(cardName)) {
            drawableId = bigCardDrawableId;
        } else {
            drawableId = smallCardDrawableId;
        }
        if (view != null) {
            view.findViewById(C0899R.C0901id.iv_mask).setBackground(getDrawable(drawableId));
        }
    }

    private void setPempID8MusicBGSelector(String skinName) {
        int drawableMusicBtnId;
        View viewMusic = this.systemCardViewHashMap.get("MUSIC");
        if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
            drawableMusicBtnId = C0899R.C0900drawable.bmw_id8_pemp_main_music_icon_bg_blue;
        } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            drawableMusicBtnId = C0899R.C0900drawable.bmw_id8_pemp_main_music_icon_bg_red;
        } else {
            drawableMusicBtnId = C0899R.C0900drawable.bmw_id8_pemp_main_music_icon_bg_yellow;
        }
        if (viewMusic != null) {
            viewMusic.findViewById(C0899R.C0901id.pemp_id8_music_prev).setBackground(getDrawable(drawableMusicBtnId));
            viewMusic.findViewById(C0899R.C0901id.pemp_id8_music_play).setBackground(getDrawable(drawableMusicBtnId));
            viewMusic.findViewById(C0899R.C0901id.pemp_id8_music_next).setBackground(getDrawable(drawableMusicBtnId));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setID8GsMusicBGSelector(String skinName) {
        int drawableMusicBtnId;
        View viewMusic = this.systemCardViewHashMap.get("MUSIC");
        View viewVideo = this.systemCardViewHashMap.get("VIDEO");
        Log.w(TAG, "drawableMusicBtnId: " + skinName);
        if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
            drawableMusicBtnId = C0899R.C0900drawable.bmw_id8_gs_main_music_icon_blue;
        } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            drawableMusicBtnId = C0899R.C0900drawable.bmw_id8_gs_main_music_icon_red;
        } else {
            drawableMusicBtnId = C0899R.C0900drawable.bmw_id8_gs_main_music_icon_yellow;
        }
        if (viewMusic != null) {
            viewMusic.findViewById(C0899R.C0901id.music_id8_gs_prev).setBackground(getDrawable(drawableMusicBtnId));
            viewMusic.findViewById(C0899R.C0901id.music_id8_gs_playPause).setBackground(getDrawable(drawableMusicBtnId));
            viewMusic.findViewById(C0899R.C0901id.music_id8_gs_next).setBackground(getDrawable(drawableMusicBtnId));
        }
        if (viewVideo != null) {
            viewVideo.findViewById(C0899R.C0901id.video_id8_gs_prev).setBackground(getDrawable(drawableMusicBtnId));
            viewVideo.findViewById(C0899R.C0901id.video_id8_gs_playPause).setBackground(getDrawable(drawableMusicBtnId));
            viewVideo.findViewById(C0899R.C0901id.video_id8_gs_next).setBackground(getDrawable(drawableMusicBtnId));
        }
    }

    private void beforeRefreshFragmentCards() {
        String str = TAG;
        Log.w(str, "beforeRefreshFragmentCards: ");
        List<String> nameList = ID8LauncherConstants.nameList;
        ID8LauncherConstants.IteratorNameList(nameList);
        ID8LauncherConstants.IteratorNameList(ID8LauncherConstants.nameList);
        if (checkCardSeqHasChange(nameList) || checkDisplayAppsHasUninstall(nameList)) {
            Log.e(str, "cacheCardsSeq: id8" + nameList.toString());
            refreshFragmentCards(nameList);
            cacheCardsSeq(nameList);
        }
    }

    private void beforeRefreshFragmentCardsGSID8() {
        Log.w(TAG, "beforeRefreshFragmentCards: ");
        List<String> nameList = GSID8LauncherConstants.nameList;
        ID8LauncherConstants.IteratorNameList(nameList);
        ID8LauncherConstants.IteratorNameList(GSID8LauncherConstants.nameList);
        if (checkCardSeqHasChangeGSID8(nameList) || checkDisplayAppsHasUninstall(nameList)) {
            refreshFragmentCardsGSID8(nameList);
            cacheCardsSeqGSID8(nameList);
        }
    }

    private void beforeRefreshFragmentCardsPempId8() {
        Log.w(TAG, "beforeRefreshFragmentCardsPempId8: ");
        List<String> nameList = PempID8LauncherConstants.nameList;
        ID8LauncherConstants.IteratorNameList(nameList);
        ID8LauncherConstants.IteratorNameList(PempID8LauncherConstants.nameList);
        if (checkCardSeqHasChangePempID8(nameList) || checkDisplayAppsHasUninstall(nameList)) {
            refreshFragmentCardsPempId8(nameList);
            cacheCardsSeqPempID8(nameList);
        }
    }

    private void beforeRefreshLeftBarIcon() {
        boolean checkLeftIconHasChange;
        Log.w(TAG, "beforeRefreshLeftBarIcon: ");
        if (UiThemeUtils.isBMW_ID8_UI(this)) {
            checkLeftIconHasChange = checkLeftIconHasChange();
        } else {
            boolean checkLeftIconHasChange2 = UiThemeUtils.isUI_PEMP_ID8(this);
            if (checkLeftIconHasChange2) {
                checkLeftIconHasChange = checkLeftIconHasChangePempID8();
            } else {
                checkLeftIconHasChange = checkLeftIconHasChangeGSID8();
            }
        }
        if (checkLeftIconHasChange) {
            if (UiThemeUtils.isBMW_ID8_UI(this)) {
                refreshLeftBarIcon();
            } else if (UiThemeUtils.isUI_GS_ID8(this)) {
                refreshLeftBarIconGSID8();
            } else if (UiThemeUtils.isUI_PEMP_ID8(this)) {
                refreshLeftBarIconPempID8();
            }
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

    private void refreshLeftBarIconPempID8() {
        this.id8CacheLeftIcon3 = PempID8LauncherConstants.leftIcon3;
        this.id8CacheLeftIcon4 = PempID8LauncherConstants.leftIcon4;
        this.id8CacheLeftIcon5 = PempID8LauncherConstants.leftIcon5;
        initPempId8LeftIcon(this.bmwPempId8UiMainLayoutBinding.llLeftContainer.llLeft3, this.bmwPempId8UiMainLayoutBinding.llLeftContainer.ivLeft3, this.bmwPempId8UiMainLayoutBinding.llLeftContainer.tvLeft3, this.id8CacheLeftIcon3);
        initPempId8LeftIcon(this.bmwPempId8UiMainLayoutBinding.llLeftContainer.llLeft4, this.bmwPempId8UiMainLayoutBinding.llLeftContainer.ivLeft4, this.bmwPempId8UiMainLayoutBinding.llLeftContainer.tvLeft4, this.id8CacheLeftIcon4);
        initPempId8LeftIcon(this.bmwPempId8UiMainLayoutBinding.llLeftContainer.llLeft5, this.bmwPempId8UiMainLayoutBinding.llLeftContainer.ivLeft5, this.bmwPempId8UiMainLayoutBinding.llLeftContainer.tvLeft5, this.id8CacheLeftIcon5);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void initLeftIcon(LinearLayout linearLayout, ImageView iv, TextView tv, String name) {
        char c;
        int iconRes = -1;
        int nameRes = -1;
        switch (name.hashCode()) {
            case -1591043536:
                if (name.equals("SETTING")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case -1409845903:
                if (name.equals("NAVIGATE")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 73532672:
                if (name.equals("MODUS")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case 73725445:
                if (name.equals("MUSIC")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 76105038:
                if (name.equals("PHONE")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 81665115:
                if (name.equals("VIDEO")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 741767578:
                if (name.equals("CAR INFO")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 1738734196:
                if (name.equals("DASHBOARD")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 1941423060:
                if (name.equals("WEATHER")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_navi;
                nameRes = C0899R.string.ksw_id8_abbr_tel_navigate;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.57
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openNaviApp(view);
                    }
                });
                break;
            case 1:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_weather;
                nameRes = C0899R.string.ksw_id8_weather;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.58
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openWeatherApp(view);
                    }
                });
                break;
            case 2:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_music;
                nameRes = C0899R.string.ksw_id8_music;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.59
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openMusicMulti(view);
                    }
                });
                break;
            case 3:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_car;
                nameRes = C0899R.string.ksw_id8_abbr_car_info;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.60
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openCar(view);
                        MainActivity.this.showLastViewFocus();
                    }
                });
                break;
            case 4:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_modus;
                nameRes = C0899R.string.ksw_id8_modus;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.61
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.enterChangeModus(view);
                    }
                });
                break;
            case 5:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_bt;
                nameRes = C0899R.string.ksw_id8_abbr_tel;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.62
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openBtApp(view);
                    }
                });
                break;
            case 6:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_dashboard;
                nameRes = C0899R.string.ksw_id8_dashboard;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.63
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openDashboard(view);
                    }
                });
                break;
            case 7:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_set;
                nameRes = C0899R.string.ksw_id8_abbr_setting;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.64
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openSettings(view);
                    }
                });
                break;
            case '\b':
                iconRes = C0899R.C0900drawable.id8_main_left_icon_video;
                nameRes = C0899R.string.ksw_id8_abbr_hd_video;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.65
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openVideoMulti(view);
                    }
                });
                break;
        }
        if (iconRes == -1) {
            return;
        }
        tv.setText(getString(nameRes));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), iconRes);
        iv.setImageBitmap(bitmap);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void initGsLeftIcon(LinearLayout linearLayout, ImageView iv, TextView tv, String name) {
        char c;
        int iconRes = -1;
        int nameRes = -1;
        switch (name.hashCode()) {
            case -1591043536:
                if (name.equals("SETTING")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case -1409845903:
                if (name.equals("NAVIGATE")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 73532672:
                if (name.equals("MODUS")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 73725445:
                if (name.equals("MUSIC")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case 76105038:
                if (name.equals("PHONE")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 81665115:
                if (name.equals("VIDEO")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 741767578:
                if (name.equals("CAR INFO")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 1738734196:
                if (name.equals("DASHBOARD")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 1941423060:
                if (name.equals("WEATHER")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                iconRes = C0899R.C0900drawable.id8_gs_main_left_icon_navi;
                nameRes = C0899R.string.ksw_id8_abbr_tel_navigate;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.66
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openNaviApp();
                    }
                });
                break;
            case 1:
                iconRes = C0899R.C0900drawable.id8_gs_main_left_icon_music;
                nameRes = C0899R.string.ksw_id8_music;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.67
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openMusicMulti(view);
                    }
                });
                break;
            case 2:
                iconRes = C0899R.C0900drawable.id8_gs_main_left_icon_car;
                nameRes = C0899R.string.ksw_id7_car;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.68
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openCar(view);
                        MainActivity.this.showLastViewFocus();
                    }
                });
                break;
            case 3:
                iconRes = C0899R.C0900drawable.id8_gs_main_left_icon_bt;
                nameRes = C0899R.string.ksw_id8_abbr_tel;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.69
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openBtApp(view);
                    }
                });
                break;
            case 4:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_weather;
                nameRes = C0899R.string.ksw_id8_weather;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.70
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openWeatherApp(view);
                    }
                });
                break;
            case 5:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_modus;
                nameRes = C0899R.string.ksw_id8_modus;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.71
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.enterGsChangeModus(view);
                    }
                });
                break;
            case 6:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_dashboard;
                nameRes = C0899R.string.ksw_id8_dashboard;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.72
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openDashboard(view);
                    }
                });
                break;
            case 7:
                iconRes = C0899R.C0900drawable.id8_main_left_icon_set;
                nameRes = C0899R.string.ksw_id8_abbr_setting;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.73
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openSettings(view);
                    }
                });
                break;
            case '\b':
                iconRes = C0899R.C0900drawable.id8_main_left_icon_video;
                nameRes = C0899R.string.ksw_id8_abbr_hd_video;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.74
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openVideoMulti(view);
                    }
                });
                break;
        }
        if (iconRes == -1) {
            return;
        }
        tv.setText(getString(nameRes));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), iconRes);
        iv.setImageBitmap(bitmap);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void initPempId8LeftIcon(LinearLayout linearLayout, ImageView iv, TextView tv, String name) {
        char c;
        int iconRes = -1;
        int nameRes = -1;
        Log.e(TAG, "initPempId8LeftIcon: name=" + name);
        switch (name.hashCode()) {
            case -1591043536:
                if (name.equals("SETTING")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case -1409845903:
                if (name.equals("NAVIGATE")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 73532672:
                if (name.equals("MODUS")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 73725445:
                if (name.equals("MUSIC")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case 76105038:
                if (name.equals("PHONE")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 81665115:
                if (name.equals("VIDEO")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 741767578:
                if (name.equals("CAR INFO")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 1738734196:
                if (name.equals("DASHBOARD")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 1941423060:
                if (name.equals("WEATHER")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_navi;
                nameRes = C0899R.string.ksw_id8_abbr_tel_navigate;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.75
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openNaviApp();
                    }
                });
                break;
            case 1:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_music;
                nameRes = C0899R.string.ksw_id8_music;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.76
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openMusicMulti(view);
                    }
                });
                break;
            case 2:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_car;
                nameRes = C0899R.string.ksw_id7_car;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.77
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openCar(view);
                        MainActivity.this.showLastViewFocus();
                    }
                });
                break;
            case 3:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_bt;
                nameRes = C0899R.string.ksw_id8_abbr_tel;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.78
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openBtApp(view);
                    }
                });
                break;
            case 4:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_weather;
                nameRes = C0899R.string.ksw_id8_weather;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.79
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openWeatherApp(view);
                    }
                });
                break;
            case 5:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_modus;
                nameRes = C0899R.string.ksw_id8_modus;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.80
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.enterPempChangeModus(view);
                    }
                });
                break;
            case 6:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_dashboard;
                nameRes = C0899R.string.ksw_id8_dashboard;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.81
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openDashboard(view);
                    }
                });
                break;
            case 7:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_set;
                nameRes = C0899R.string.ksw_id8_abbr_setting;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.82
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openSettings(view);
                    }
                });
                break;
            case '\b':
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_video;
                nameRes = C0899R.string.ksw_id8_abbr_hd_video;
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.83
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MainActivity.this.bmwId8ViewModel.openVideoMulti(view);
                    }
                });
                break;
        }
        if (iconRes == -1) {
            return;
        }
        tv.setText(getString(nameRes));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), iconRes);
        iv.setImageBitmap(bitmap);
    }

    private boolean checkLeftIconHasChange() {
        if (ID8LauncherConstants.leftIcon2.equals(this.id8CacheLeftIcon2) && ID8LauncherConstants.leftIcon3.equals(this.id8CacheLeftIcon3) && ID8LauncherConstants.leftIcon4.equals(this.id8CacheLeftIcon4)) {
            return false;
        }
        return true;
    }

    private boolean checkLeftIconHasChangeGSID8() {
        if (GSID8LauncherConstants.leftIcon3.equals(this.id8CacheLeftIcon3) && GSID8LauncherConstants.leftIcon4.equals(this.id8CacheLeftIcon4) && GSID8LauncherConstants.leftIcon5.equals(this.id8CacheLeftIcon5)) {
            return false;
        }
        return true;
    }

    private boolean checkLeftIconHasChangePempID8() {
        if (PempID8LauncherConstants.leftIcon3.equals(this.id8CacheLeftIcon3) && PempID8LauncherConstants.leftIcon4.equals(this.id8CacheLeftIcon4) && PempID8LauncherConstants.leftIcon5.equals(this.id8CacheLeftIcon5)) {
            return false;
        }
        return true;
    }

    private void cacheCardsSeq(List<String> nameList) {
        this.id8CacheCardSeq.clear();
        this.id8CacheCardSeq.addAll(nameList);
    }

    private void cacheCardsSeqGSID8(List<String> nameList) {
        this.gsId8CacheCardSeq.clear();
        this.gsId8CacheCardSeq.addAll(nameList);
    }

    private void cacheCardsSeqPempID8(List<String> nameList) {
        this.pempId8CacheCardSeq.clear();
        this.pempId8CacheCardSeq.addAll(nameList);
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
        View childAt0 = this.bmwId8UiMainLayoutBinding.llContainer.getChildAt(0);
        childAt0.findViewById(C0899R.C0901id.iv_mask).setNextFocusLeftId(C0899R.C0901id.ll_left_1);
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
        View childAt0 = this.bmwId8GsUiMainLayoutBinding.llContaine.getChildAt(0);
        childAt0.findViewById(C0899R.C0901id.iv_mask).setNextFocusLeftId(C0899R.C0901id.ll_left_1);
    }

    private void refreshFragmentCardsPempId8(List<String> nameList) {
        View cardView;
        Log.w(TAG, "refreshFragmentCardsPempId8: ");
        this.bmwPempId8UiMainLayoutBinding.llContainer.removeAllViews();
        boolean trdAppCountIsMax = displayTrdAppsCount(nameList) >= 5;
        for (String cardName : nameList) {
            if (cardName.equals("ADD WIDGET") && trdAppCountIsMax) {
                break;
            }
            if (cardName.startsWith("3rd")) {
                cardView = get3rdAppView(cardName);
            } else {
                cardView = getPempSystemCardView(cardName);
            }
            Log.w(TAG, "cardName: " + cardName);
            if (cardView != null) {
                this.bmwPempId8UiMainLayoutBinding.llContainer.addView(cardView);
            }
        }
        View childAt0 = this.bmwPempId8UiMainLayoutBinding.llContainer.getChildAt(0);
        childAt0.findViewById(C0899R.C0901id.iv_mask).setNextFocusLeftId(C0899R.C0901id.ll_left_1);
    }

    private View getSystemCardView(String cardName) {
        View cardView = this.systemCardViewHashMap.get(cardName);
        if (cardView != null) {
            return cardView;
        }
        char c = '\uffff';
        switch (cardName.hashCode()) {
            case -1591043536:
                if (cardName.equals("SETTING")) {
                    c = '\b';
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
                    c = '\t';
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
                NavigateDataBinding navigateDataBinding = (NavigateDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_navigate, this.bmwId8UiMainLayoutBinding.llContainer, false);
                navigateDataBinding.setNavigateViewModel(this.bmwId8ViewModel);
                navigateDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = navigateDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 1:
                WeatherDataBinding weatherDataBinding = (WeatherDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_weather, this.bmwId8UiMainLayoutBinding.llContainer, false);
                weatherDataBinding.setWeatherViewModel(this.bmwId8ViewModel);
                weatherDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = weatherDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 2:
                MusicDataBinding musicDataBinding = (MusicDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_music, this.bmwId8UiMainLayoutBinding.llContainer, false);
                musicDataBinding.setMediaViewModel(this.bmwId8ViewModel);
                musicDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = musicDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 3:
                CarInfoDataBinding carInfoDataBinding = (CarInfoDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_car_info, this.bmwId8UiMainLayoutBinding.llContainer, false);
                carInfoDataBinding.setCarViewModel(this.bmwId8ViewModel);
                carInfoDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = carInfoDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 4:
                ModusDataBinding modusDataBinding = (ModusDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_modus, this.bmwId8UiMainLayoutBinding.llContainer, false);
                modusDataBinding.setModusViewModel(this.bmwId8ViewModel);
                modusDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = modusDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 5:
                PhoneDataBinding phoneDataBinding = (PhoneDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_phone, this.bmwId8UiMainLayoutBinding.llContainer, false);
                phoneDataBinding.setBtViewModel(this.bmwId8ViewModel);
                phoneDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = phoneDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 6:
                DashboardDataBinding dashboardDataBinding = (DashboardDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_dashboard, this.bmwId8UiMainLayoutBinding.llContainer, false);
                dashboardDataBinding.setDashboardViewModel(this.bmwId8ViewModel);
                dashboardDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = dashboardDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 7:
                VideoDataBinding videoDataBinding = (VideoDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_video, this.bmwId8UiMainLayoutBinding.llContainer, false);
                videoDataBinding.setMediaViewModel(this.bmwId8ViewModel);
                videoDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = videoDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case '\b':
                View settingFragment = View.inflate(this, C0899R.C0902layout.fragment_setting, null);
                settingFragment.findViewById(C0899R.C0901id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
                settingFragment.findViewById(C0899R.C0901id.iv_mask).setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.84
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        MainActivity.this.bmwId8ViewModel.openSettings(v);
                    }
                });
                cardView = settingFragment;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case '\t':
                View addWidgetFragment = View.inflate(this, C0899R.C0902layout.fragment_add_widget, null);
                addWidgetFragment.findViewById(C0899R.C0901id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
                addWidgetFragment.findViewById(C0899R.C0901id.iv_mask).setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.85
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        MainActivity.this.bmwId8ViewModel.addWidget(v);
                    }
                });
                cardView = addWidgetFragment;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
        }
        String skinName = ID8LauncherConstants.loadCurrentSkin();
        setID8CardBGSelector(cardName, cardView, skinName);
        return cardView;
    }

    private View getPempSystemCardView(String cardName) {
        View cardView = this.systemCardViewHashMap.get(cardName);
        if (cardView != null) {
            return cardView;
        }
        char c = '\uffff';
        switch (cardName.hashCode()) {
            case -1591043536:
                if (cardName.equals("SETTING")) {
                    c = '\b';
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
                    c = '\t';
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
                NavigatePempDataBinding naviPempDataBinding = (NavigatePempDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_pemp_navigate, this.bmwPempId8UiMainLayoutBinding.llContainer, false);
                naviPempDataBinding.setNavigateViewModel(this.bmwId8ViewModel);
                naviPempDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = naviPempDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 1:
                WeatherPempDataBinding weatherPempDataBinding = (WeatherPempDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_pemp_weather, this.bmwPempId8UiMainLayoutBinding.llContainer, false);
                weatherPempDataBinding.setWeatherViewModel(this.bmwId8ViewModel);
                weatherPempDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = weatherPempDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 2:
                final MusicPempDataBinding musicPempDataBinding = (MusicPempDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_pemp_music, this.bmwPempId8UiMainLayoutBinding.llContainer, false);
                musicPempDataBinding.setMediaViewModel(this.bmwId8ViewModel);
                musicPempDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                musicPempDataBinding.ivMask.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.MainActivity.86
                    @Override // android.view.View.OnKeyListener
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == 66) {
                            v.clearFocus();
                            musicPempDataBinding.pempId8MusicPlay.requestFocus();
                            musicPempDataBinding.pempId8MusicPlay.callOnClick();
                            return true;
                        }
                        return false;
                    }
                });
                cardView = musicPempDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 3:
                CarInfoPempDataBinding carInfoPempDataBinding = (CarInfoPempDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_pemp_car_info, this.bmwPempId8UiMainLayoutBinding.llContainer, false);
                carInfoPempDataBinding.setCarViewModel(this.bmwId8ViewModel);
                carInfoPempDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = carInfoPempDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 4:
                ModusPempDataBinding modusPempDataBinding = (ModusPempDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_pemp_modus, this.bmwPempId8UiMainLayoutBinding.llContainer, false);
                modusPempDataBinding.setModusViewModel(this.bmwId8ViewModel);
                modusPempDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = modusPempDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 5:
                PhonePempDataBinding phonePempDataBinding = (PhonePempDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_pemp_phone, this.bmwPempId8UiMainLayoutBinding.llContainer, false);
                phonePempDataBinding.setBtViewModel(this.bmwId8ViewModel);
                phonePempDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = phonePempDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 6:
                DashboardPempDataBinding dashboardPempDataBinding = (DashboardPempDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_pemp_dashboard, this.bmwPempId8UiMainLayoutBinding.llContainer, false);
                dashboardPempDataBinding.setDashboardViewModel(this.bmwId8ViewModel);
                dashboardPempDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = dashboardPempDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 7:
                VideoPempDataBinding videoPempDataBinding = (VideoPempDataBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_pemp_video, this.bmwPempId8UiMainLayoutBinding.llContainer, false);
                videoPempDataBinding.setMediaViewModel(this.bmwId8ViewModel);
                videoPempDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = videoPempDataBinding.llContainer;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case '\b':
                View settingFragment = View.inflate(this, C0899R.C0902layout.fragment_pemp_setting, null);
                settingFragment.findViewById(C0899R.C0901id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
                settingFragment.findViewById(C0899R.C0901id.iv_mask).setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.87
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        MainActivity.this.bmwId8ViewModel.openSettings(v);
                    }
                });
                cardView = settingFragment;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case '\t':
                View addWidgetFragment = View.inflate(this, C0899R.C0902layout.fragment_pemp_add_widget, null);
                addWidgetFragment.findViewById(C0899R.C0901id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
                addWidgetFragment.findViewById(C0899R.C0901id.iv_mask).setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.88
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        MainActivity.this.bmwId8ViewModel.addWidget(v);
                    }
                });
                cardView = addWidgetFragment;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
        }
        String skinName = ID8LauncherConstants.loadCurrentSkin();
        setPempID8CardBGSelector(cardName, cardView, skinName);
        return cardView;
    }

    private View getSystemGsCardView(String cardName) {
        View cardView = this.systemCardViewHashMap.get(cardName);
        if (cardView != null) {
            return cardView;
        }
        char c = '\uffff';
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
                    c = '\b';
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
                DashboardDataGsBinding dashboardDataGsBinding = (DashboardDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_dashboard_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                dashboardDataGsBinding.setDashboardViewModel(this.bmwId8ViewModel);
                dashboardDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = dashboardDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 1:
                MusicDataGsBinding musicDataGsBinding = (MusicDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_music_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                musicDataGsBinding.setMediaViewModel(this.bmwId8ViewModel);
                musicDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = musicDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 2:
                PhoneDataGsBinding phoneDataGsBinding = (PhoneDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_phone_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                phoneDataGsBinding.setBtViewModel(this.bmwId8ViewModel);
                phoneDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = phoneDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 3:
                CarInfoDataGsBinding carInfoDataGsBinding = (CarInfoDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_car_info_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                carInfoDataGsBinding.setCarViewModel(this.bmwId8ViewModel);
                carInfoDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = carInfoDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 4:
                View settingFragment = View.inflate(this, C0899R.C0902layout.fragment_setting_gs, null);
                settingFragment.findViewById(C0899R.C0901id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
                settingFragment.findViewById(C0899R.C0901id.iv_mask).setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.89
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        MainActivity.this.bmwId8ViewModel.openSettings(v);
                    }
                });
                cardView = settingFragment;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 5:
                VideoDataGsBinding videoDataGsBinding = (VideoDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_video_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                videoDataGsBinding.setMediaViewModel(this.bmwId8ViewModel);
                videoDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = videoDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 6:
                WeatherDataGsBinding weatherGsDataBinding = (WeatherDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_weather_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                weatherGsDataBinding.setWeatherViewModel(this.bmwId8ViewModel);
                weatherGsDataBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = weatherGsDataBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case 7:
                ModusDataGsBinding modusDataGsBinding = (ModusDataGsBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.fragment_modus_gs, this.bmwId8GsUiMainLayoutBinding.llContaine, false);
                modusDataGsBinding.setModusViewModel(this.bmwId8ViewModel);
                modusDataGsBinding.ivMask.setOnLongClickListener(this.id8OnLongClickListener);
                cardView = modusDataGsBinding.llContainerGs;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
            case '\b':
                View addWidgetFragment = View.inflate(this, C0899R.C0902layout.fragment_add_widget_gs, null);
                addWidgetFragment.findViewById(C0899R.C0901id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
                addWidgetFragment.findViewById(C0899R.C0901id.iv_mask).setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.90
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        MainActivity.this.bmwId8ViewModel.addWidget(v);
                    }
                });
                cardView = addWidgetFragment;
                this.systemCardViewHashMap.put(cardName, cardView);
                break;
        }
        String skinName = ID8LauncherConstants.loadCurrentSkin();
        setID8GsCardBGSelector(cardName, cardView, skinName);
        return cardView;
    }

    private int displayTrdAppsCount(List<String> nameList) {
        int count = 0;
        for (String cardName : nameList) {
            if (cardName != null && cardName.startsWith("3rd")) {
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

    private boolean checkCardSeqHasChangePempID8(List<String> nameList) {
        if (this.pempId8CacheCardSeq.size() != nameList.size()) {
            return true;
        }
        for (int i = 0; i < this.pempId8CacheCardSeq.size(); i++) {
            if (!this.pempId8CacheCardSeq.get(i).equals(nameList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDisplayAppsHasUninstall(List<String> nameList) {
        List<String> uninstallList = new ArrayList<>();
        for (String cardName : nameList) {
            if (cardName.startsWith("3rd")) {
                String[] split = cardName.substring(4).split(",");
                String pkg = split[0];
                if (!AppInfoUtils.isAppPakExist(this, pkg)) {
                    uninstallList.add(cardName);
                }
            }
        }
        for (String cardName2 : uninstallList) {
            nameList.remove(cardName2);
        }
        return uninstallList.size() != 0;
    }

    private View get3rdAppView(String cardName) {
        View cardView;
        View cardView2 = this.trdCardViewHashMap.get(cardName);
        if (cardView2 != null) {
            return cardView2;
        }
        if (UiThemeUtils.isBMW_ID8_UI(this)) {
            cardView = View.inflate(this, C0899R.C0902layout.fragment_3rd, null);
            cardView.findViewById(C0899R.C0901id.iv_3rd_bg).setBackground(getDrawable(C0899R.C0900drawable.id8_main_icon_music));
        } else if (UiThemeUtils.isUI_PEMP_ID8(this)) {
            cardView = View.inflate(this, C0899R.C0902layout.fragment_pemp_3rd, null);
            cardView.findViewById(C0899R.C0901id.iv_3rd_bg).setBackground(getDrawable(C0899R.C0900drawable.pemp_id8_main_icon_3app));
        } else {
            cardView = View.inflate(this, C0899R.C0902layout.fragment_3rd_gs, null);
            cardView.findViewById(C0899R.C0901id.iv_3rd_bg).setBackground(getDrawable(C0899R.C0900drawable.gs_id8_main_icon_3app));
        }
        cardView.findViewById(C0899R.C0901id.iv_mask).setOnLongClickListener(this.id8OnLongClickListener);
        String[] split = cardName.substring(4).split(",");
        final String pkg = split[0];
        boolean z = true;
        final String cls = split[1];
        TextView tvName = (TextView) cardView.findViewById(C0899R.C0901id.tv_name);
        ImageView iv = (ImageView) cardView.findViewById(C0899R.C0901id.iv);
        ResolveInfo resolveInfo = AppInfoUtils.findAppByPkgAndCls(this, pkg, cls);
        PackageManager pm = KswApplication.appContext.getPackageManager();
        String str = TAG;
        StringBuilder append = new StringBuilder().append("get3rdAppView: resolveInfo == null");
        if (resolveInfo != null) {
            z = false;
        }
        Log.w(str, append.append(z).append(", pkg : ").append(pkg).append(", cls : ").append(cls).toString());
        if (resolveInfo == null) {
            try {
                String appName = pm.getApplicationLabel(pm.getApplicationInfo(pkg, 128)).toString();
                Drawable appIcon = pm.getApplicationIcon(pkg);
                tvName.setText(appName);
                iv.setImageDrawable(appIcon);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                try {
                    PackageManager packageManager = getPackageManager();
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(pkg, 0);
                    String appName2 = applicationInfo.loadLabel(packageManager).toString();
                    Drawable appIcon2 = applicationInfo.loadIcon(packageManager);
                    tvName.setText(appName2);
                    iv.setImageDrawable(appIcon2);
                } catch (PackageManager.NameNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            tvName.setText(resolveInfo.loadLabel(pm).toString());
            iv.setImageDrawable(resolveInfo.loadIcon(pm));
        }
        cardView.findViewById(C0899R.C0901id.iv_mask).setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.91
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.bmwId8ViewModel.openAppTask(new ComponentName(pkg, cls));
                MainActivity.this.bmwId8ViewModel.addLastViewFocused(view);
                MainActivity.this.bmwId8ViewModel.sendMcuCommand();
            }
        });
        this.trdCardViewHashMap.put(cardName, cardView);
        String skinName = ID8LauncherConstants.loadCurrentSkin();
        if (UiThemeUtils.isBMW_ID8_UI(this)) {
            setID8CardBGSelector(cardName, cardView, skinName);
            setID8TrdCardSkinResources(cardView, skinName);
        } else if (UiThemeUtils.isUI_PEMP_ID8(this)) {
            setPempID8CardBGSelector(cardName, cardView, skinName);
            setID8TrdCardSkinResources(cardView, skinName);
        } else {
            setID8GsCardBGSelector(cardName, cardView, skinName);
        }
        return cardView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setID8TrdCardSkinResources(View cardView, String skinName) {
        int dividerRes;
        int titleColorRes;
        ImageView imageView = (ImageView) cardView.findViewById(C0899R.C0901id.iv_3rd_divider);
        TextView textView = (TextView) cardView.findViewById(C0899R.C0901id.tv_name);
        if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
            dividerRes = C0899R.C0900drawable.id8_main_icon_weather_line;
            titleColorRes = C0899R.color.id8_main_style_color_blue;
        } else if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            dividerRes = C0899R.C0900drawable.id8_main_icon_weather_line_r;
            titleColorRes = C0899R.color.id8_main_style_color_red;
        } else {
            dividerRes = C0899R.C0900drawable.id8_main_icon_weather_line_y;
            titleColorRes = C0899R.color.id8_main_style_color_yellow;
        }
        imageView.setBackground(getResources().getDrawable(dividerRes));
        textView.setTextColor(getResources().getColor(titleColorRes));
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiMbi3View() {
        Log.i(TAG, "initAudiMbi3View : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mAudiMbi3BcViewModel = bcVieModel;
        bcVieModel.setActivity(this);
        mAudiMbi3BcViewModel.initData();
        AudiMib3MainLayoutBinding audiMib3MainLayoutBinding = (AudiMib3MainLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.audi_mib3_main_layout);
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
        this.viewpagerAudiMib3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.94
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int pageIndex) {
                Log.d("onPageSelected", "0000000000");
                if (pageIndex == 0) {
                    BcVieModel bcVieModel2 = MainActivity.mAudiMbi3BcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FragmentOne audiMib3FragmentOne = (AudiMib3FragmentOne) MainActivity.audiMib3ViewPagerAdapter.fragmentPage1;
                        BcVieModel bcVieModel3 = MainActivity.mAudiMbi3BcViewModel;
                        AudiMib3FragmentOne.setItemSelected(BcVieModel.viewLastSel);
                        MainActivity.this.setIndicatorBackground(pageIndex, 4);
                    }
                }
                if (pageIndex == 1) {
                    BcVieModel bcVieModel4 = MainActivity.mAudiMbi3BcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FragmentTwo audiMib3FragmentTwo = (AudiMib3FragmentTwo) MainActivity.audiMib3ViewPagerAdapter.fragmentPage2;
                        BcVieModel bcVieModel5 = MainActivity.mAudiMbi3BcViewModel;
                        AudiMib3FragmentTwo.setItemSelected(BcVieModel.viewLastSel);
                    }
                }
                MainActivity.this.setIndicatorBackground(pageIndex, 4);
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(4);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiMbi3ViewV2() {
        Log.i(TAG, "initAudiMbi3View : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mAudiMbi3BcViewModel = bcVieModel;
        bcVieModel.setActivity(this);
        mAudiMbi3BcViewModel.initData();
        AudiMib3MainLayoutBinding audiMib3MainLayoutBinding = (AudiMib3MainLayoutBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.audi_mib3_main_layout);
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
        this.viewpagerAudiMib3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.95
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int pageIndex) {
                Log.d("onPageSelected", "0000000000");
                if (pageIndex == 0) {
                    BcVieModel bcVieModel2 = MainActivity.mAudiMbi3BcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FragmentOneV2 audiMib3FragmentOneV2 = (AudiMib3FragmentOneV2) MainActivity.audiMib3ViewPagerAdapterv2.fragmentPage1;
                        BcVieModel bcVieModel3 = MainActivity.mAudiMbi3BcViewModel;
                        AudiMib3FragmentOneV2.setItemSelected(BcVieModel.viewLastSel);
                        MainActivity.this.setIndicatorBackground(pageIndex, 4);
                    }
                }
                if (pageIndex == 1) {
                    BcVieModel bcVieModel4 = MainActivity.mAudiMbi3BcViewModel;
                    if (BcVieModel.viewLastSel != null) {
                        AudiMib3FragmentTwoV2 audiMib3FragmentTwoV2 = (AudiMib3FragmentTwoV2) MainActivity.audiMib3ViewPagerAdapterv2.fragmentPage2;
                        BcVieModel bcVieModel5 = MainActivity.mAudiMbi3BcViewModel;
                        AudiMib3FragmentTwoV2.setItemSelected(BcVieModel.viewLastSel);
                    }
                }
                MainActivity.this.setIndicatorBackground(pageIndex, 4);
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(4);
    }

    public void setItemSelected_AudiMib3(View view) {
        audiMib3Binding.include.ivMusicAudimib3.setSelected(audiMib3Binding.include.ivMusicAudimib3 == view);
        audiMib3Binding.include.ivNaviAudimib3.setSelected(audiMib3Binding.include.ivNaviAudimib3 == view);
        audiMib3Binding.include.ivBtAudimib3.setSelected(audiMib3Binding.include.ivBtAudimib3 == view);
        audiMib3Binding.include.ivSetAudimib3.setSelected(audiMib3Binding.include.ivSetAudimib3 == view);
        audiMib3Binding.include.ivCarAudimib3.setSelected(audiMib3Binding.include.ivCarAudimib3 == view);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenzMBUXView() {
        Log.i(TAG, "initBenzMBUXView: ");
        setFull();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        ActivityMainBenzMbuxBinding benzMbuxBinding = (ActivityMainBenzMbuxBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_benz_mbux);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 0, false);
        benzMbuxBinding.benzMbuxRecyclerView.setFocusable(false);
        benzMbuxBinding.benzMbuxRecyclerView.setLayoutManager(linearLayoutManager);
        benzMbuxBinding.benzMbuxRecyclerView.setAdapter(new BenzMbuxRecyclerViewAdpater(mBcVieModel));
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper() { // from class: com.wits.ksw.MainActivity.97
            @Override // android.support.p004v7.widget.SnapHelper, android.support.p004v7.widget.RecyclerView.OnFlingListener
            public boolean onFling(int velocityX, int velocityY) {
                return false;
            }
        };
        mLinearSnapHelper.attachToRecyclerView(benzMbuxBinding.benzMbuxRecyclerView);
        benzMbuxBinding.setVieModel(mBcVieModel);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_View() {
        Log.i(TAG, "initBenz_MBUX_2021_View: ");
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_KSW_View_new() {
        this.mUiName = UiThemeUtils.getUiName(this);
        String str = TAG;
        Log.i(str, "initBenz_MBUX_2021_KSW_View_new: mUiName " + this.mUiName);
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        BenzUtils.init(getApplicationContext(), mBcVieModel);
        this.mBenzCardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_MBUX_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
        if (UiThemeUtils.isBenz_MBUX_2021_KSW_V2(this)) {
            this.mBenzCardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_MBUX_V2_ORDER, BenzUtils.BENZ_MBUX_V2_ORDER_DEFAULT);
        }
        Log.i(str, "initBenz_MBUX_2021_KSW_View_new: mBenzCardStr " + this.mBenzCardStr);
        BenzMbuxKswActivityNewBinding benzMbuxKswActivityNewBinding = (BenzMbuxKswActivityNewBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.benz_mbux_ksw_activity_new);
        mBenzMbuxKswActivityNewBinding = benzMbuxKswActivityNewBinding;
        benzMbuxKswActivityNewBinding.setVieModel(mBcVieModel);
        loadBenzCardView();
        this.mBenzCardMenus = BenzUtils.loadCardMenuList();
        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(1, 5, 0, this);
        Benzmbux2021newAdapter benzmbux2021newAdapter = new Benzmbux2021newAdapter(this.mBenzCardMenus);
        this.mBenzmbux2021newAdapter = benzmbux2021newAdapter;
        benzmbux2021newAdapter.setmBcVieModel(mBcVieModel);
        mBcVieModel.itemIconClassical.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() { // from class: com.wits.ksw.MainActivity.98
            @Override // android.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable sender, int propertyId) {
                Log.i(MainActivity.TAG, "onPropertyChanged: \u4e3b\u9898\u5207\u6362\uff0c\u7f16\u8f91\u754c\u9762\u540c\u6b65\u5237\u65b0\u5361\u7247 ");
                MainActivity.this.mBenzmbux2021newAdapter.setCardList(BenzUtils.loadCardMenuList());
            }
        });
        this.mBenzmbux2021newAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.wits.ksw.MainActivity.99
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case C0899R.C0901id.benz_mbux_2021_ksw_new_item_bg /* 2131296482 */:
                        MainActivity.mBcVieModel.selectCardName.set((String) view.getTag());
                        MainActivity.this.mBenzmbux2021newAdapter.notifyDataSetChanged();
                        return;
                    case C0899R.C0901id.benz_mbux_edit_delete_btn /* 2131296489 */:
                        BenzUtils.deleteItemByPosition(position, MainActivity.mBcVieModel, MainActivity.this.mBenzmbux2021newAdapter, MainActivity.this.scrollHelper);
                        return;
                    case C0899R.C0901id.benz_mbux_edit_left_btn /* 2131296491 */:
                        BenzUtils.changeCardItemPosition(position, true, MainActivity.this.scrollHelper, MainActivity.this.mBenzmbux2021newAdapter);
                        return;
                    case C0899R.C0901id.benz_mbux_edit_ok_btn /* 2131296492 */:
                        String cardStr = BenzUtils.getBenzCardStr(MainActivity.this.getApplicationContext(), BenzUtils.BENZ_MBUX_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
                        if (UiThemeUtils.isBenz_MBUX_2021_KSW_V2(MainActivity.this.getApplicationContext())) {
                            cardStr = BenzUtils.getBenzCardStr(MainActivity.this.getApplicationContext(), BenzUtils.BENZ_MBUX_V2_ORDER, BenzUtils.BENZ_MBUX_V2_ORDER_DEFAULT);
                        }
                        if (!TextUtils.equals(MainActivity.this.mBenzCardStr, cardStr)) {
                            MainActivity.this.mBenzCardStr = cardStr;
                            Log.i(MainActivity.TAG, "onItemChildClick: benz_mbux_edit_ok_btn \u5361\u7247\u72b6\u6001\u53d8\u5316\u53ca\u65f6\u66f4\u65b0");
                            MainActivity.this.loadBenzCardView();
                            MainActivity.this.initIndicatorPoints(12);
                        }
                        MainActivity.mBcVieModel.isEditState.set(false);
                        return;
                    case C0899R.C0901id.benz_mbux_edit_right_btn /* 2131296493 */:
                        BenzUtils.changeCardItemPosition(position, false, MainActivity.this.scrollHelper, MainActivity.this.mBenzmbux2021newAdapter);
                        return;
                    default:
                        return;
                }
            }
        });
        mBenzMbuxKswActivityNewBinding.benzMbux2021KswNewRecycle.setAdapter(this.mBenzmbux2021newAdapter);
        this.scrollHelper.setUpRecycleView(mBenzMbuxKswActivityNewBinding.benzMbux2021KswNewRecycle);
        this.scrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() { // from class: com.wits.ksw.MainActivity.100
            @Override // com.wits.ksw.launcher.view.benzmbux2021new.util.PagingScrollHelper.onPageChangeListener
            public void onPageChange(int index) {
                Log.i(MainActivity.TAG, "scrollHelper onPageChange: index " + index);
                MainActivity.this.mBenzmbux2021newAdapter.notifyDataSetChanged();
            }
        });
        mBenzMbuxKswActivityNewBinding.benzMbux2021KswNewRecycle.setLayoutManager(horizontalPageLayoutManager);
        this.scrollHelper.updateLayoutManger();
        this.scrollHelper.scrollToPosition(0);
        mBenzMbuxKswActivityNewBinding.benzMbux2021KswNewRecycle.setHorizontalScrollBarEnabled(true);
        MyItemTouchHelper myItemTouchHelper = new MyItemTouchHelper(this.mBenzCardMenus, this.mBenzmbux2021newAdapter);
        this.mMyItemTouchHelper = myItemTouchHelper;
        myItemTouchHelper.setScrollHelper(this.scrollHelper);
        this.mMyItemTouchHelper.setViewModel(mBcVieModel);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(this.mMyItemTouchHelper);
        this.mItemTouchHelper = itemTouchHelper;
        itemTouchHelper.attachToRecyclerView(mBenzMbuxKswActivityNewBinding.benzMbux2021KswNewRecycle);
        initIndicatorPoints(12);
        mBenzMbuxKswActivityNewBinding.layoutCoatBenzMbux2021.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.101
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
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
        LOGE.m44D("styleIndex = " + this.styleIndexKsw + " iStyleIndex = " + iStyleIndex + " iBgIndex" + iBgIndex);
        if (iStyleIndex == 1) {
            mBcVieModel.itemIconClassical.set(true);
        } else {
            mBcVieModel.itemIconClassical.set(false);
        }
        LauncherViewModel.mediaInfo.setPicOne((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[iBgIndex - 1]));
        LauncherViewModel.mediaInfo.setPicBg((BitmapDrawable) (LauncherViewModel.mediaInfo.pic == null ? BitmapUtil.getDefaultMBUX2021BG_OTHER() : LauncherViewModel.mediaInfo.pic.get()));
        LauncherViewModel.mediaInfo.setPicOther((BitmapDrawable) getResources().getDrawable(BenzMbux2021KswConfigs.BG_OTHER[iBgIndex - 1]));
        mBenzMbuxKswActivityNewBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadBenzCardView() {
        try {
            this.mBenzPageViews = new ArrayList();
            DisplayMetrics dm = getResources().getDisplayMetrics();
            int width = dm.widthPixels;
            int height = dm.heightPixels;
            for (int i = 0; i < 2; i++) {
                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(0);
                layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                for (int j = i * 5; j < (i + 1) * 5; j++) {
                    layout.addView(getBenzSystemCardView(BenzUtils.nameList.get(j)), width / 5, height);
                }
                this.mBenzPageViews.add(layout);
            }
            int cardLength = BenzUtils.nameList.size();
            if (cardLength > 10) {
                int i2 = 0;
                while (true) {
                    if (i2 >= (cardLength + (-15) > 0 ? 2 : 1)) {
                        break;
                    }
                    LinearLayout layout2 = new LinearLayout(this);
                    layout2.setOrientation(0);
                    layout2.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                    int max = (cardLength - 10) - (i2 * 5);
                    int max2 = max > 5 ? 5 : max;
                    for (int j2 = (i2 * 5) + 10; j2 < (i2 * 5) + max2 + 10; j2++) {
                        layout2.addView(getBenzSThirdCardView(BenzUtils.nameList.get(j2)), width / 5, height);
                    }
                    this.mBenzPageViews.add(layout2);
                    i2++;
                }
            }
            this.mBenzCardPagerAdapter = new BenzCardPagerAdapter(this.mBenzPageViews);
            mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.setAdapter(this.mBenzCardPagerAdapter);
            mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.setCurrentItem(0);
            mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.102
                @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i3, float v, int i1) {
                }

                @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
                public void onPageSelected(int pageIndex) {
                    MainActivity.this.scrollHelper.scrollToPosition(pageIndex);
                    LauncherViewModel.mediaInfo.setPageIndex(pageIndex);
                    LOGE.m43E("bg_sel = " + BenzMbux2021Configs.bg_sel + "  pageIndex = " + pageIndex);
                    MainActivity.this.setIndicatorBackground(pageIndex, 12);
                    if (pageIndex == 0) {
                        LOGE.m43E("bg_sel pageIndex0 ----------- ");
                        MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
                        return;
                    }
                    LOGE.m43E("bg_sel pageIndex1 ----------- ");
                    MainActivity.mBenzMbuxKswActivityNewBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
                }

                @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i3) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private View getBenzSystemCardView(String cardName) {
        char c;
        if (TextUtils.isEmpty(cardName)) {
            Log.i(TAG, "getBenzSystemCardView: cardName is null");
        }
        View cardView = null;
        switch (cardName.hashCode()) {
            case 2130:
                if (cardName.equals(BenzUtils.f201BT)) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 66484:
                if (cardName.equals(BenzUtils.CAR)) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 68096:
                if (cardName.equals(BenzUtils.DVR)) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case 81986:
                if (cardName.equals(BenzUtils.SET)) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case 2015858:
                if (cardName.equals(BenzUtils.APPS)) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 2336762:
                if (cardName.equals(BenzUtils.LINK)) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 2388902:
                if (cardName.equals(BenzUtils.NAVI)) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 73725445:
                if (cardName.equals("MUSIC")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case 81665115:
                if (cardName.equals("VIDEO")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 1738734196:
                if (cardName.equals("DASHBOARD")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 1941423060:
                if (cardName.equals("WEATHER")) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                BenzMbuxNaviCardBinding naviCardBinding = (BenzMbuxNaviCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_mbux_navi_card, null, false);
                naviCardBinding.setViewModel(mBcVieModel);
                naviCardBinding.getRoot().setTag(BenzUtils.NAVI);
                naviCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.NAVI);
                cardView = naviCardBinding.getRoot();
                break;
            case 1:
                BenzMbuxMusicCardBinding musicCardBinding = (BenzMbuxMusicCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_mbux_music_card, null, false);
                musicCardBinding.setViewModel(mBcVieModel);
                musicCardBinding.getRoot().setTag("MUSIC");
                musicCardBinding.benzMbuxLocalCardBg.setTag("MUSIC");
                musicCardBinding.benzMbuxLocalMusicCardBg.setTag("MUSIC");
                musicCardBinding.benzMbuxLocalMusicCardBg.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wits.ksw.MainActivity.103
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View v) {
                        try {
                            MainActivity.mBcVieModel.selectCardName.set((String) v.getTag());
                            MainActivity.this.mBenzmbux2021newAdapter.notifyDataSetChanged();
                            int position = BenzUtils.nameList.indexOf((String) v.getTag());
                            if (MainActivity.this.scrollHelper != null) {
                                MainActivity.this.scrollHelper.scrollToPosition(position / 5);
                            }
                            MainActivity.mBcVieModel.isEditState.set(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                });
                cardView = musicCardBinding.getRoot();
                break;
            case 2:
                BenzMbuxBtCardBinding btCardBinding = (BenzMbuxBtCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_mbux_bt_card, null, false);
                btCardBinding.setViewModel(mBcVieModel);
                btCardBinding.getRoot().setTag(BenzUtils.f201BT);
                btCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.f201BT);
                cardView = btCardBinding.getRoot();
                break;
            case 3:
                BenzMbuxCarinfoCardBinding carinfoCardBinding = (BenzMbuxCarinfoCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_mbux_carinfo_card, null, false);
                carinfoCardBinding.setViewModel(mBcVieModel);
                carinfoCardBinding.getRoot().setTag(BenzUtils.CAR);
                carinfoCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.CAR);
                cardView = carinfoCardBinding.getRoot();
                break;
            case 4:
                BenzMbuxSetCardBinding setCardBinding = (BenzMbuxSetCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_mbux_set_card, null, false);
                setCardBinding.setViewModel(mBcVieModel);
                setCardBinding.getRoot().setTag(BenzUtils.SET);
                setCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.SET);
                cardView = setCardBinding.getRoot();
                break;
            case 5:
                BenzMbuxVideoCardBinding videoCardBinding = (BenzMbuxVideoCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_mbux_video_card, null, false);
                videoCardBinding.setViewModel(mBcVieModel);
                videoCardBinding.getRoot().setTag("VIDEO");
                videoCardBinding.benzMbuxLocalCardBg.setTag("VIDEO");
                cardView = videoCardBinding.getRoot();
                break;
            case 6:
                BenzMbuxAppsCardBinding appsCardBinding = (BenzMbuxAppsCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_mbux_apps_card, null, false);
                appsCardBinding.setViewModel(mBcVieModel);
                appsCardBinding.getRoot().setTag(BenzUtils.APPS);
                appsCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.APPS);
                cardView = appsCardBinding.getRoot();
                break;
            case 7:
                BenzMbuxZlinkCardBinding zlinkCardBinding = (BenzMbuxZlinkCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_mbux_zlink_card, null, false);
                zlinkCardBinding.setViewModel(mBcVieModel);
                zlinkCardBinding.getRoot().setTag(BenzUtils.LINK);
                zlinkCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.LINK);
                cardView = zlinkCardBinding.getRoot();
                break;
            case '\b':
                BenzMbuxDashboardCardBinding dashboardCardBinding = (BenzMbuxDashboardCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_mbux_dashboard_card, null, false);
                dashboardCardBinding.setViewModel(mBcVieModel);
                dashboardCardBinding.getRoot().setTag("DASHBOARD");
                dashboardCardBinding.benzMbuxLocalCardBg.setTag("DASHBOARD");
                cardView = dashboardCardBinding.getRoot();
                break;
            case '\t':
                BenzMbuxDvrCardBinding dvrCardBinding = (BenzMbuxDvrCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_mbux_dvr_card, null, false);
                dvrCardBinding.setViewModel(mBcVieModel);
                dvrCardBinding.getRoot().setTag(BenzUtils.DVR);
                dvrCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.DVR);
                cardView = dvrCardBinding.getRoot();
                break;
            case '\n':
                BenzMbuxWeatherCardBinding weatherCardBinding = (BenzMbuxWeatherCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_mbux_weather_card, null, false);
                weatherCardBinding.setViewModel(mBcVieModel);
                weatherCardBinding.getRoot().setTag("WEATHER");
                weatherCardBinding.benzMbuxLocalCardBg.setTag("WEATHER");
                cardView = weatherCardBinding.getRoot();
                break;
        }
        cardView.findViewById(C0899R.C0901id.benz_mbux_local_card_bg).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wits.ksw.MainActivity.104
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                try {
                    MainActivity.mBcVieModel.selectCardName.set((String) v.getTag());
                    MainActivity.this.mBenzmbux2021newAdapter.notifyDataSetChanged();
                    int position = BenzUtils.nameList.indexOf((String) v.getTag());
                    if (MainActivity.this.scrollHelper != null) {
                        MainActivity.this.scrollHelper.scrollToPosition(position / 5);
                    }
                    MainActivity.mBcVieModel.isEditState.set(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        return cardView;
    }

    private View getBenzSThirdCardView(String cardName) {
        if (TextUtils.isEmpty(cardName)) {
            Log.i(TAG, "getBenzSThirdCardView: cardName is null");
        }
        View cardView = View.inflate(this, C0899R.C0902layout.benz_mbux_third_card, null);
        cardView.setTag(cardName);
        String[] split = cardName.substring(4).split(",");
        boolean z = false;
        final String pkg = split[0];
        final String cls = split[1];
        TextView titleName = (TextView) cardView.findViewById(C0899R.C0901id.benz_mbux_third_card_title);
        ImageView bgImg = (ImageView) cardView.findViewById(C0899R.C0901id.benz_mbux_third_card_bg);
        bgImg.setTag(cardName);
        bgImg.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.105
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MainActivity.mBcVieModel.openAppTask(new ComponentName(pkg, cls));
            }
        });
        bgImg.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wits.ksw.MainActivity.106
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                Log.i(MainActivity.TAG, "getBenzSThirdCardView onLongClick:  " + ((String) v.getTag()));
                MainActivity.mBcVieModel.selectCardName.set((String) v.getTag());
                MainActivity.this.mBenzmbux2021newAdapter.notifyDataSetChanged();
                int position = BenzUtils.nameList.indexOf((String) v.getTag());
                if (MainActivity.this.scrollHelper != null) {
                    MainActivity.this.scrollHelper.scrollToPosition(position / 5);
                }
                MainActivity.mBcVieModel.isEditState.set(true);
                return true;
            }
        });
        ImageView iconImg = (ImageView) cardView.findViewById(C0899R.C0901id.benz_mbux_third_card_icon);
        ResolveInfo resolveInfo = AppInfoUtils.findAppByPkgAndCls(this, pkg, cls);
        PackageManager pm = KswApplication.appContext.getPackageManager();
        String str = TAG;
        StringBuilder append = new StringBuilder().append("getBenzSThirdCardView: resolveInfo == null");
        if (resolveInfo == null) {
            z = true;
        }
        Log.w(str, append.append(z).append(", pkg : ").append(pkg).append(", cls : ").append(cls).toString());
        if (resolveInfo == null) {
            try {
                String appName = pm.getApplicationLabel(pm.getApplicationInfo(pkg, 128)).toString();
                Drawable appIcon = pm.getApplicationIcon(pkg);
                titleName.setText(appName);
                iconImg.setImageDrawable(appIcon);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            titleName.setText(resolveInfo.loadLabel(pm).toString());
            iconImg.setImageDrawable(resolveInfo.loadIcon(pm));
        }
        return cardView;
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_KSW_View() {
        Log.i(TAG, "initBenz_MBUX_2021_KSW_View: ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        BenzMbux2021KswActivityBinding benzMbux2021KswActivityBinding2 = (BenzMbux2021KswActivityBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_benz_mbux_2021_ksw);
        benzMbux2021KswActivityBinding = benzMbux2021KswActivityBinding2;
        benzMbux2021KswActivityBinding2.setVieModel(mBcVieModel);
        benzMbux2021KswViewPagerAdapter = new BenzMbux2021KswViewPagerAdapter(getSupportFragmentManager(), this);
        this.viewPagerBenzMbux2021Ksw = benzMbux2021KswActivityBinding.benzMbux2021Viewpager;
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.setAdapter(benzMbux2021KswViewPagerAdapter);
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.setCurrentItem(0);
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.107
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int pageIndex) {
                LauncherViewModel.mediaInfo.setPageIndex(pageIndex);
                LOGE.m43E("bg_sel = " + BenzMbux2021Configs.bg_sel + "  pageIndex = " + pageIndex);
                MainActivity.this.setIndicatorBackground(pageIndex, 12);
                if (pageIndex == 0) {
                    LOGE.m43E("bg_sel pageIndex0 ----------- ");
                    MainActivity.benzMbux2021KswActivityBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
                    return;
                }
                LOGE.m43E("bg_sel pageIndex1 ----------- ");
                MainActivity.benzMbux2021KswActivityBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(12);
        benzMbux2021KswActivityBinding.setVieModel(mBcVieModel);
        benzMbux2021KswActivityBinding.layoutCoatBenzMbux2021.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.108
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LOGE.m44D("Benz2021KswDialogThemeActivity");
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
        LOGE.m44D("styleIndex = " + this.styleIndexKsw + " iStyleIndex = " + iStyleIndex + " iBgIndex" + iBgIndex);
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_KSW_View_V2() {
        Log.i(TAG, "initBenz_MBUX_2021_KSW_View_V2: ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        BenzMbux2021KswActivityBinding benzMbux2021KswActivityBinding2 = (BenzMbux2021KswActivityBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_benz_mbux_2021_ksw);
        benzMbux2021KswActivityBinding = benzMbux2021KswActivityBinding2;
        benzMbux2021KswActivityBinding2.setVieModel(mBcVieModel);
        benzMbux2021KswV2ViewPagerAdapter = new BenzMbux2021KswV2ViewPagerAdapter(getSupportFragmentManager(), this);
        this.viewPagerBenzMbux2021Ksw = benzMbux2021KswActivityBinding.benzMbux2021Viewpager;
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.setAdapter(benzMbux2021KswV2ViewPagerAdapter);
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.setCurrentItem(0);
        benzMbux2021KswActivityBinding.benzMbux2021Viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.109
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int pageIndex) {
                LauncherViewModel.mediaInfo.setPageIndex(pageIndex);
                LOGE.m43E("bg_sel = " + BenzMbux2021Configs.bg_sel + "  pageIndex = " + pageIndex);
                MainActivity.this.setIndicatorBackground(pageIndex, 12);
                if (pageIndex == 0) {
                    LOGE.m43E("bg_sel pageIndex0 ----------- ");
                    MainActivity.benzMbux2021KswActivityBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
                    return;
                }
                LOGE.m43E("bg_sel pageIndex1 ----------- ");
                MainActivity.benzMbux2021KswActivityBinding.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(12);
        benzMbux2021KswActivityBinding.setVieModel(mBcVieModel);
        benzMbux2021KswActivityBinding.layoutCoatBenzMbux2021.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.110
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LOGE.m44D("Benz2021KswDialogThemeActivity");
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
        LOGE.m44D("styleIndex = " + this.styleIndexKsw + " iStyleIndex = " + iStyleIndex + " iBgIndex" + iBgIndex);
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

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_MBUX_2021_View2() {
        String str = TAG;
        Log.i(str, "initBenz_MBUX_2021_View2: ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        if (UiThemeUtils.isUI_MBUX_YO(this)) {
            mBcVieModel.isYO.set(true);
            Log.e(str, "initBenz_MBUX_2021_View2:  true true!!");
        } else {
            mBcVieModel.isYO.set(false);
            Log.e(str, "initBenz_MBUX_2021_View2:  FFFFFFFFFF");
        }
        BenzMbux2021ActivityBinding2 benzMbux2021ActivityBinding2 = (BenzMbux2021ActivityBinding2) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_benz_mbux_2021_2);
        benzMbux2021Binding2 = benzMbux2021ActivityBinding2;
        benzMbux2021ActivityBinding2.setVieModel(mBcVieModel);
        benzMbux2021ViewPagerAdapter = new BenzMbux2021ViewPagerAdapter(getSupportFragmentManager());
        this.viewPagerBenzMbux2021 = benzMbux2021Binding2.benzMbux2021Viewpager;
        benzMbux2021Binding2.benzMbux2021Viewpager.setAdapter(benzMbux2021ViewPagerAdapter);
        benzMbux2021Binding2.benzMbux2021Viewpager.setCurrentItem(0);
        benzMbux2021Binding2.benzMbux2021Viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.111
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int pageIndex) {
                LauncherViewModel.mediaInfo.setPageIndex(pageIndex);
                LOGE.m43E("bg_sel = " + BenzMbux2021Configs.bg_sel + "  pageIndex = " + pageIndex);
                MainActivity.this.setIndicatorBackground(pageIndex, 1);
                if (pageIndex == 0) {
                    LOGE.m43E("bg_sel pageIndex0 ----------- ");
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOne.get());
                } else if (pageIndex == 1) {
                    LOGE.m43E("bg_sel pageIndex1 ----------- ");
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackground((LauncherViewModel.mediaInfo.picBg.get() != null ? LauncherViewModel.mediaInfo.picBg : LauncherViewModel.mediaInfo.picOther).get());
                } else {
                    LOGE.m43E("bg_sel pageIndex2 ----------- ");
                    MainActivity.benzMbux2021Binding2.layoutMain2021.setBackground(LauncherViewModel.mediaInfo.picOther.get());
                }
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(2);
        benzMbux2021Binding2.setVieModel(mBcVieModel);
        benzMbux2021Binding2.layoutCoatBenzMbux2021.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.112
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LOGE.m44D("Benz2021DialogThemeActivity");
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
        LOGE.m44D("styleIndex = " + this.styleIndex + " iStyleIndex = " + iStyleIndex + " iBgIndex" + iBgIndex);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void initIndicatorPoints(int TYPE) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int i = 11;
        if (TYPE == 1) {
            this.benzMbux2021Binding.indicatorBenzMbux2021.removeAllViews();
        } else if (TYPE == 2) {
            benzMbux2021Binding2.indicatorBenzMbux2021.removeAllViews();
        } else if (TYPE == 12) {
            if ((width == 1280 && height == 720) || (width == 1024 && height == 600)) {
                benzMbux2021KswActivityBinding.indicatorBenzMbux2021.removeAllViews();
            } else {
                mBenzMbuxKswActivityNewBinding.indicatorBenzMbux2021.removeAllViews();
            }
        } else if (TYPE == 3) {
            if ((width == 1280 && height == 720) || (width == 1024 && height == 600)) {
                benzNtg6FyBinding.indicatorBenzNtg6Fy.removeAllViews();
            } else {
                mBenzNtgFyActivityNewBinding.indicatorBenzNtg6Fy.removeAllViews();
            }
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
            if (BenzUtils.nameList.size() > 15) {
                this.indicatorPoints = new ImageView[4];
            } else if (BenzUtils.nameList.size() > 10) {
                this.indicatorPoints = new ImageView[3];
            } else if (width != 1280 || height != 720) {
                this.indicatorPoints = new ImageView[2];
            } else {
                this.indicatorPoints = new ImageView[3];
            }
        } else if (TYPE == 3) {
            if (BenzUtils.nameList.size() > 15) {
                this.indicatorPoints = new ImageView[4];
            } else if (BenzUtils.nameList.size() > 10) {
                this.indicatorPoints = new ImageView[3];
            } else {
                this.indicatorPoints = new ImageView[2];
            }
        } else if (TYPE == 4) {
            this.indicatorPoints = new ImageView[2];
        } else if (TYPE == 10) {
            this.indicatorPoints = new ImageView[2];
        } else if (TYPE == 11) {
            this.indicatorPoints = new ImageView[2];
        }
        int i2 = 0;
        while (i2 < this.indicatorPoints.length) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            if (i2 == 0) {
                if (TYPE == 1 || TYPE == 2) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.mbux2_indicato_sel);
                }
                if (TYPE == 12) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.mbux2_indicato_sel);
                } else if (TYPE == 3) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.fy_mbux_indicato_sel);
                } else if (TYPE == 4) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.audi_mib3_main_indicato_sel);
                } else if (TYPE == 10) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.fy_audi_mib3_main_indicato_sel);
                } else if (TYPE == i) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.audi_mib3_ty_main_indicato_sel);
                }
            } else {
                if (TYPE == 1 || TYPE == 2) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.mbux2_indicato_n);
                }
                if (TYPE == 12) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.mbux2_indicato_n);
                } else if (TYPE == 3) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.fy_mbux_indicato_n);
                } else if (TYPE == 4) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.audi_mib3_main_indicato_n);
                } else if (TYPE == 10) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.fy_audi_mib3_main_indicato_n);
                } else if (TYPE == i) {
                    imageView.setBackgroundResource(C0899R.C0900drawable.audi_mib3_ty_main_indicato_n);
                }
            }
            this.indicatorPoints[i2] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(-2, -2));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            if (TYPE == 1) {
                this.benzMbux2021Binding.indicatorBenzMbux2021.addView(imageView, layoutParams);
            } else if (TYPE == 2) {
                benzMbux2021Binding2.indicatorBenzMbux2021.addView(imageView, layoutParams);
            } else if (TYPE == 12) {
                if ((width == 1280 && height == 720) || (width == 1024 && height == 600)) {
                    benzMbux2021KswActivityBinding.indicatorBenzMbux2021.addView(imageView, layoutParams);
                } else {
                    mBenzMbuxKswActivityNewBinding.indicatorBenzMbux2021.addView(imageView, layoutParams);
                }
            } else if (TYPE == 3) {
                if (width == 1280 && height == 720) {
                    benzNtg6FyBinding.indicatorBenzNtg6Fy.addView(imageView, layoutParams);
                }
                if (width != 1024 || height != 600) {
                    mBenzNtgFyActivityNewBinding.indicatorBenzNtg6Fy.addView(imageView, layoutParams);
                }
                benzNtg6FyBinding.indicatorBenzNtg6Fy.addView(imageView, layoutParams);
            } else if (TYPE == 4) {
                audiMib3Binding.indicatorAudimib3.addView(imageView, layoutParams);
            } else if (TYPE == 10) {
                mib3FyMainLayoutBinding.indicatorAudimib3.addView(imageView, layoutParams);
            } else if (TYPE == i) {
                audiMib3TyMainLayoutBinding.indicatorAudiMib3Ty.addView(imageView, layoutParams);
            }
            Log.i(TAG, "indicator: add " + i2);
            i2++;
            i = 11;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIndicatorBackground(int selectItems, int styleType) {
        if (this.indicatorPoints.length < 1) {
            return;
        }
        int i = 0;
        while (true) {
            ImageView[] imageViewArr = this.indicatorPoints;
            if (i < imageViewArr.length) {
                if (i == selectItems) {
                    if (styleType == 1 || styleType == 2 || styleType == 12) {
                        imageViewArr[i].setBackgroundResource(C0899R.C0900drawable.mbux2_indicato_sel);
                    } else if (styleType == 3) {
                        imageViewArr[i].setBackgroundResource(C0899R.C0900drawable.fy_mbux_indicato_sel);
                    } else if (styleType == 4) {
                        imageViewArr[i].setBackgroundResource(C0899R.C0900drawable.audi_mib3_main_indicato_sel);
                    } else if (styleType == 10) {
                        imageViewArr[i].setBackgroundResource(C0899R.C0900drawable.fy_audi_mib3_main_indicato_sel);
                    } else if (styleType == 11) {
                        imageViewArr[i].setBackgroundResource(C0899R.C0900drawable.audi_mib3_ty_main_indicato_sel);
                    }
                } else if (styleType == 1 || styleType == 2 || styleType == 12) {
                    imageViewArr[i].setBackgroundResource(C0899R.C0900drawable.mbux2_indicato_n);
                } else if (styleType == 3) {
                    imageViewArr[i].setBackgroundResource(C0899R.C0900drawable.fy_mbux_indicato_n);
                } else if (styleType == 4) {
                    imageViewArr[i].setBackgroundResource(C0899R.C0900drawable.audi_mib3_main_indicato_n);
                } else if (styleType == 10) {
                    imageViewArr[i].setBackgroundResource(C0899R.C0900drawable.fy_audi_mib3_main_indicato_n);
                } else if (styleType == 11) {
                    imageViewArr[i].setBackgroundResource(C0899R.C0900drawable.audi_mib3_ty_main_indicato_n);
                }
                i++;
            } else {
                return;
            }
        }
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_NTG6_FY_View() {
        Log.i(TAG, "initBenz_NTG6_FY_View : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        benzNtg6FyBinding = (BenzNtg6FyBindCls) DataBindingUtil.setContentView(mainActivity, C0899R.C0902layout.activity_main_benz_ntg6_fy);
        benzNtg6FyViewPagerAdapter = new BenzNtg6FyViewPagerAdapter(getSupportFragmentManager());
        this.viewpagerBenzNtg6Fy = benzNtg6FyBinding.benzNtg6FyViewpager;
        benzNtg6FyBinding.benzNtg6FyViewpager.setAdapter(benzNtg6FyViewPagerAdapter);
        benzNtg6FyBinding.benzNtg6FyViewpager.setCurrentItem(0);
        benzNtg6FyBinding.benzNtg6FyViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.113
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int pageIndex) {
                MainActivity.this.setIndicatorBackground(pageIndex, 3);
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(3);
        benzNtg6FyBinding.setViewModel(mBcVieModel);
        benzNtg6FyBinding.layoutCoatBenzFy.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.114
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LOGE.m44D("BenzNtg6FyThemeActivity))))))))");
                MainActivity.this.startActivity(new Intent(MainActivity.mainActivity, BenzNtg6FyThemeActivity.class));
            }
        });
        String string = Settings.System.getString(mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_INDEX);
        this.bgFyIndex = string;
        if (TextUtils.isEmpty(string)) {
            this.bgFyIndex = "8";
        }
        int iBgFyIndex = Integer.parseInt(this.bgFyIndex);
        LOGE.m44D("styleIndex iBgFyIndex" + iBgFyIndex);
        benzNtg6FyBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[iBgFyIndex - 1]);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initUI_NTG6_FY_ViewV2() {
        Log.i(TAG, "initUI_NTG6_FY_View : ");
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        benzNtg6FyBinding = (BenzNtg6FyBindCls) DataBindingUtil.setContentView(mainActivity, C0899R.C0902layout.activity_main_benz_ntg6_fy);
        benzNtg6FyViewPagerAdapterV2 = new BenzNtg6FyViewPagerAdapterV2(getSupportFragmentManager());
        this.viewpagerBenzNtg6Fy = benzNtg6FyBinding.benzNtg6FyViewpager;
        benzNtg6FyBinding.benzNtg6FyViewpager.setAdapter(benzNtg6FyViewPagerAdapterV2);
        benzNtg6FyBinding.benzNtg6FyViewpager.setCurrentItem(0);
        benzNtg6FyBinding.benzNtg6FyViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.115
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int pageIndex) {
                MainActivity.this.setIndicatorBackground(pageIndex, 3);
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        initIndicatorPoints(3);
        benzNtg6FyBinding.setViewModel(mBcVieModel);
        benzNtg6FyBinding.layoutCoatBenzFy.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.116
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LOGE.m44D("UINtg6FyThemeActivity))))))))");
                MainActivity.this.startActivity(new Intent(MainActivity.mainActivity, BenzNtg6FyThemeActivity.class));
            }
        });
        String string = Settings.System.getString(mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_INDEX);
        this.bgFyIndex = string;
        if (TextUtils.isEmpty(string)) {
            this.bgFyIndex = "8";
        }
        int iBgFyIndex = Integer.parseInt(this.bgFyIndex);
        LOGE.m44D("styleIndex iBgFyIndex" + iBgFyIndex);
        benzNtg6FyBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[iBgFyIndex - 1]);
    }

    private void showThemePopupFy(View v) {
        PopupWindow popupWindow = this.mPopupWindow_FY;
        if (popupWindow != null) {
            popupWindow.showAtLocation(getWindow().getDecorView(), 80, 0, 0);
            return;
        }
        View popDialog = LayoutInflater.from(mainActivity).inflate(C0899R.C0902layout.benz_ntg6_fy_theme_set_layout, (ViewGroup) null);
        PopupWindow popupWindow2 = new PopupWindow(popDialog, -1, ScreenUtil.dip2px(400.0f), true);
        this.mPopupWindow_FY = popupWindow2;
        popupWindow2.setBackgroundDrawable(new ColorDrawable(0));
        this.mPopupWindow_FY.setOutsideTouchable(true);
        this.mPopupWindow_FY.showAtLocation(getWindow().getDecorView(), 80, 0, 0);
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initBenz_NTG6_FY_View_new() {
        this.mUiName = UiThemeUtils.getUiName(this);
        Log.i(TAG, "initBenz_NTG6_FY_View_new: mUiName " + this.mUiName);
        setFull();
        setStatusBarTranslucent();
        BcVieModel bcVieModel = (BcVieModel) ViewModelProviders.m59of(this).get(BcVieModel.class);
        mBcVieModel = bcVieModel;
        bcVieModel.setActivity(this);
        mBcVieModel.initData();
        BenzUtils.init(getApplicationContext(), mBcVieModel);
        this.mBenzCardStr = BenzUtils.getBenzCardStr(getApplicationContext(), BenzUtils.BENZ_NTG6_FY_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
        if (UiThemeUtils.isUI_NTG6_FY_V2(this) && ClientManager.getInstance().isAls6208Client()) {
            this.mBenzCardStr = BenzUtils.getBenzCardStr(getApplicationContext(), BenzUtils.BENZ_NTG6_FY_V2_ORDER, BenzUtils.BENZ_NTG6_FY_V2_ORDER_DEFAULT);
        }
        BenzNtgFyActivityNewBinding benzNtgFyActivityNewBinding = (BenzNtgFyActivityNewBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.benz_ntg_fy_activity_new);
        mBenzNtgFyActivityNewBinding = benzNtgFyActivityNewBinding;
        benzNtgFyActivityNewBinding.setViewModel(mBcVieModel);
        loadBenzNtgCardView();
        this.mBenzCardMenus = BenzUtils.loadCardMenuList();
        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(1, 5, 0, this);
        BenzNtgfyNewAdapter benzNtgfyNewAdapter = new BenzNtgfyNewAdapter(this.mBenzCardMenus);
        this.mBenzNtgfyNewAdapter = benzNtgfyNewAdapter;
        benzNtgfyNewAdapter.setmBcVieModel(mBcVieModel);
        this.mBenzNtgfyNewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.wits.ksw.MainActivity.117
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case C0899R.C0901id.benz_mbux_2021_ksw_new_item_bg /* 2131296482 */:
                        MainActivity.mBcVieModel.selectCardName.set((String) view.getTag());
                        MainActivity.this.mBenzNtgfyNewAdapter.notifyDataSetChanged();
                        return;
                    case C0899R.C0901id.benz_mbux_edit_delete_btn /* 2131296489 */:
                        BenzUtils.deleteItemByPosition(position, MainActivity.mBcVieModel, MainActivity.this.mBenzNtgfyNewAdapter, MainActivity.this.scrollHelper);
                        return;
                    case C0899R.C0901id.benz_mbux_edit_left_btn /* 2131296491 */:
                        BenzUtils.changeCardItemPosition(position, true, MainActivity.this.scrollHelper, MainActivity.this.mBenzNtgfyNewAdapter);
                        return;
                    case C0899R.C0901id.benz_mbux_edit_ok_btn /* 2131296492 */:
                        String cardStr = BenzUtils.getBenzCardStr(MainActivity.this.getApplicationContext(), BenzUtils.BENZ_NTG6_FY_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
                        if (UiThemeUtils.isUI_NTG6_FY_V2(MainActivity.this.getApplicationContext()) && ClientManager.getInstance().isAls6208Client()) {
                            cardStr = BenzUtils.getBenzCardStr(MainActivity.this.getApplicationContext(), BenzUtils.BENZ_NTG6_FY_V2_ORDER, BenzUtils.BENZ_NTG6_FY_V2_ORDER_DEFAULT);
                        }
                        if (!TextUtils.equals(MainActivity.this.mBenzCardStr, cardStr)) {
                            MainActivity.this.mBenzCardStr = cardStr;
                            Log.i(MainActivity.TAG, "onItemChildClick: benz_mbux_edit_ok_btn \u5361\u7247\u72b6\u6001\u53d8\u5316\u53ca\u65f6\u66f4\u65b0");
                            MainActivity.this.loadBenzNtgCardView();
                            MainActivity.this.initIndicatorPoints(3);
                        }
                        MainActivity.mBcVieModel.isEditState.set(false);
                        return;
                    case C0899R.C0901id.benz_mbux_edit_right_btn /* 2131296493 */:
                        BenzUtils.changeCardItemPosition(position, false, MainActivity.this.scrollHelper, MainActivity.this.mBenzNtgfyNewAdapter);
                        return;
                    default:
                        return;
                }
            }
        });
        mBenzNtgFyActivityNewBinding.benzMbux2021KswNewRecycle.setAdapter(this.mBenzNtgfyNewAdapter);
        this.scrollHelper.setUpRecycleView(mBenzNtgFyActivityNewBinding.benzMbux2021KswNewRecycle);
        this.scrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() { // from class: com.wits.ksw.MainActivity.118
            @Override // com.wits.ksw.launcher.view.benzmbux2021new.util.PagingScrollHelper.onPageChangeListener
            public void onPageChange(int index) {
                Log.i(MainActivity.TAG, "scrollHelper onPageChange: index " + index);
                MainActivity.this.mBenzNtgfyNewAdapter.notifyDataSetChanged();
            }
        });
        mBenzNtgFyActivityNewBinding.benzMbux2021KswNewRecycle.setLayoutManager(horizontalPageLayoutManager);
        this.scrollHelper.updateLayoutManger();
        this.scrollHelper.scrollToPosition(0);
        mBenzNtgFyActivityNewBinding.benzMbux2021KswNewRecycle.setHorizontalScrollBarEnabled(true);
        BenzNtgItemTouchHelper benzNtgItemTouchHelper = new BenzNtgItemTouchHelper(this.mBenzCardMenus, this.mBenzNtgfyNewAdapter);
        this.mBenzNtgItemTouchHelper = benzNtgItemTouchHelper;
        benzNtgItemTouchHelper.setScrollHelper(this.scrollHelper);
        this.mBenzNtgItemTouchHelper.setViewModel(mBcVieModel);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(this.mBenzNtgItemTouchHelper);
        this.mItemTouchHelper = itemTouchHelper;
        itemTouchHelper.attachToRecyclerView(mBenzNtgFyActivityNewBinding.benzMbux2021KswNewRecycle);
        initIndicatorPoints(3);
        mBenzNtgFyActivityNewBinding.layoutCoatBenzFy.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.119
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LOGE.m44D("UINtg6FyThemeActivity))))))))");
                MainActivity.this.startActivity(new Intent(MainActivity.mainActivity, BenzNtg6FyThemeActivity.class));
            }
        });
        String string = Settings.System.getString(mainActivity.getContentResolver(), BenzNtg6FyConfigs.BG_INDEX);
        this.bgFyIndex = string;
        if (TextUtils.isEmpty(string)) {
            this.bgFyIndex = "8";
        }
        int iBgFyIndex = Integer.parseInt(this.bgFyIndex);
        LOGE.m44D("styleIndex iBgFyIndex" + iBgFyIndex);
        mBenzNtgFyActivityNewBinding.layoutMainNtgFy.setBackgroundResource(BenzNtg6FyConfigs.BG_ONE[iBgFyIndex - 1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadBenzNtgCardView() {
        try {
            this.mBenzPageViews = new ArrayList();
            DisplayMetrics dm = getResources().getDisplayMetrics();
            int width = dm.widthPixels;
            int height = dm.heightPixels;
            for (int i = 0; i < 2; i++) {
                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(0);
                layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                for (int j = i * 5; j < (i + 1) * 5; j++) {
                    layout.addView(getBenzNtgSystemCardView(BenzUtils.nameList.get(j)), width / 5, height);
                }
                this.mBenzPageViews.add(layout);
            }
            int cardLength = BenzUtils.nameList.size();
            if (cardLength > 10) {
                int i2 = 0;
                while (true) {
                    if (i2 >= (cardLength + (-15) > 0 ? 2 : 1)) {
                        break;
                    }
                    LinearLayout layout2 = new LinearLayout(this);
                    layout2.setOrientation(0);
                    layout2.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                    int max = (cardLength - 10) - (i2 * 5);
                    int max2 = max > 5 ? 5 : max;
                    for (int j2 = (i2 * 5) + 10; j2 < (i2 * 5) + max2 + 10; j2++) {
                        layout2.addView(getBenzNtgThirdCardView(BenzUtils.nameList.get(j2)), width / 5, height);
                    }
                    this.mBenzPageViews.add(layout2);
                    i2++;
                }
            }
            Log.i(TAG, "loadBenzNtgCardView: mBenzPageViews.size() " + this.mBenzPageViews.size());
            this.mBenzCardPagerAdapter = new BenzCardPagerAdapter(this.mBenzPageViews);
            mBenzNtgFyActivityNewBinding.benzMbux2021Viewpager.setAdapter(this.mBenzCardPagerAdapter);
            mBenzNtgFyActivityNewBinding.benzMbux2021Viewpager.setCurrentItem(0);
            mBenzNtgFyActivityNewBinding.benzMbux2021Viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.120
                @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i3, float v, int i1) {
                }

                @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
                public void onPageSelected(int pageIndex) {
                    MainActivity.this.scrollHelper.scrollToPosition(pageIndex);
                    MainActivity.this.setIndicatorBackground(pageIndex, 3);
                }

                @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i3) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View getBenzNtgSystemCardView(String cardName) {
        String str = TAG;
        Log.i(str, "getBenzNtgSystemCardView: cardName " + cardName);
        if (TextUtils.isEmpty(cardName)) {
            Log.i(str, "getBenzNtgSystemCardView: cardName is null");
        }
        View cardView = null;
        char c = '\uffff';
        switch (cardName.hashCode()) {
            case 2130:
                if (cardName.equals(BenzUtils.f201BT)) {
                    c = 2;
                    break;
                }
                break;
            case 66484:
                if (cardName.equals(BenzUtils.CAR)) {
                    c = 3;
                    break;
                }
                break;
            case 68096:
                if (cardName.equals(BenzUtils.DVR)) {
                    c = '\t';
                    break;
                }
                break;
            case 81986:
                if (cardName.equals(BenzUtils.SET)) {
                    c = 4;
                    break;
                }
                break;
            case 2015858:
                if (cardName.equals(BenzUtils.APPS)) {
                    c = 6;
                    break;
                }
                break;
            case 2336762:
                if (cardName.equals(BenzUtils.LINK)) {
                    c = 7;
                    break;
                }
                break;
            case 2388902:
                if (cardName.equals(BenzUtils.NAVI)) {
                    c = 0;
                    break;
                }
                break;
            case 73725445:
                if (cardName.equals("MUSIC")) {
                    c = 1;
                    break;
                }
                break;
            case 81665115:
                if (cardName.equals("VIDEO")) {
                    c = 5;
                    break;
                }
                break;
            case 1738734196:
                if (cardName.equals("DASHBOARD")) {
                    c = '\b';
                    break;
                }
                break;
            case 1941423060:
                if (cardName.equals("WEATHER")) {
                    c = '\n';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                BenzNtgFyNaviCardBinding naviCardBinding = (BenzNtgFyNaviCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_ntg_fy_navi_card, null, false);
                naviCardBinding.setViewModel(mBcVieModel);
                naviCardBinding.getRoot().setTag(BenzUtils.NAVI);
                naviCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.NAVI);
                cardView = naviCardBinding.getRoot();
                break;
            case 1:
                BenzNtgFyMusicCardBinding musicCardBinding = (BenzNtgFyMusicCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_ntg_fy_music_card, null, false);
                musicCardBinding.setViewModel(mBcVieModel);
                musicCardBinding.getRoot().setTag("MUSIC");
                musicCardBinding.benzMbuxLocalCardBg.setTag("MUSIC");
                cardView = musicCardBinding.getRoot();
                break;
            case 2:
                BenzNtgFyBtCardBinding btCardBinding = (BenzNtgFyBtCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_ntg_fy_bt_card, null, false);
                btCardBinding.setViewModel(mBcVieModel);
                btCardBinding.getRoot().setTag(BenzUtils.f201BT);
                btCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.f201BT);
                cardView = btCardBinding.getRoot();
                break;
            case 3:
                BenzNtgFyCarinfoCardBinding carinfoCardBinding = (BenzNtgFyCarinfoCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_ntg_fy_carinfo_card, null, false);
                carinfoCardBinding.setViewModel(mBcVieModel);
                carinfoCardBinding.getRoot().setTag(BenzUtils.CAR);
                carinfoCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.CAR);
                cardView = carinfoCardBinding.getRoot();
                break;
            case 4:
                BenzNtgFySetCardBinding setCardBinding = (BenzNtgFySetCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_ntg_fy_set_card, null, false);
                setCardBinding.setViewModel(mBcVieModel);
                setCardBinding.getRoot().setTag(BenzUtils.SET);
                setCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.SET);
                cardView = setCardBinding.getRoot();
                break;
            case 5:
                BenzNtgFyVideoCardBinding videoCardBinding = (BenzNtgFyVideoCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_ntg_fy_video_card, null, false);
                videoCardBinding.setViewModel(mBcVieModel);
                videoCardBinding.getRoot().setTag("VIDEO");
                videoCardBinding.benzMbuxLocalCardBg.setTag("VIDEO");
                cardView = videoCardBinding.getRoot();
                break;
            case 6:
                BenzNtgFyAppsCardBinding appsCardBinding = (BenzNtgFyAppsCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_ntg_fy_apps_card, null, false);
                appsCardBinding.setViewModel(mBcVieModel);
                appsCardBinding.getRoot().setTag(BenzUtils.APPS);
                appsCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.APPS);
                cardView = appsCardBinding.getRoot();
                break;
            case 7:
                BenzNtgFyZlinkCardBinding zlinkCardBinding = (BenzNtgFyZlinkCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_ntg_fy_zlink_card, null, false);
                zlinkCardBinding.setViewModel(mBcVieModel);
                zlinkCardBinding.getRoot().setTag(BenzUtils.LINK);
                zlinkCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.LINK);
                cardView = zlinkCardBinding.getRoot();
                break;
            case '\b':
                BenzNtgFyDashboardCardBinding dashboardCardBinding = (BenzNtgFyDashboardCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_ntg_fy_dashboard_card, null, false);
                dashboardCardBinding.setViewModel(mBcVieModel);
                dashboardCardBinding.getRoot().setTag("DASHBOARD");
                dashboardCardBinding.benzMbuxLocalCardBg.setTag("DASHBOARD");
                cardView = dashboardCardBinding.getRoot();
                break;
            case '\t':
                BenzNtgFyDvrCardBinding dvrCardBinding = (BenzNtgFyDvrCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_ntg_fy_dvr_card, null, false);
                dvrCardBinding.setViewModel(mBcVieModel);
                dvrCardBinding.getRoot().setTag(BenzUtils.DVR);
                dvrCardBinding.benzMbuxLocalCardBg.setTag(BenzUtils.DVR);
                cardView = dvrCardBinding.getRoot();
                break;
            case '\n':
                BenzNtgFyWeatherCardBinding weatherCardBinding = (BenzNtgFyWeatherCardBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.benz_ntg_fy_weather_card, null, false);
                weatherCardBinding.setViewModel(mBcVieModel);
                weatherCardBinding.getRoot().setTag("WEATHER");
                weatherCardBinding.benzMbuxLocalCardBg.setTag("WEATHER");
                cardView = weatherCardBinding.getRoot();
                break;
        }
        cardView.findViewById(C0899R.C0901id.benz_mbux_local_card_bg).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wits.ksw.MainActivity.121
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                try {
                    Log.i(MainActivity.TAG, "getBenzNtgSystemCardView onLongClick: ");
                    MainActivity.mBcVieModel.selectCardName.set((String) v.getTag());
                    MainActivity.this.mBenzNtgfyNewAdapter.notifyDataSetChanged();
                    int position = BenzUtils.nameList.indexOf((String) v.getTag());
                    if (MainActivity.this.scrollHelper != null) {
                        MainActivity.this.scrollHelper.scrollToPosition(position / 5);
                    }
                    MainActivity.mBcVieModel.isEditState.set(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        return cardView;
    }

    private View getBenzNtgThirdCardView(String cardName) {
        String str = TAG;
        Log.i(str, "getBenzNtgThirdCardView: cardName " + cardName);
        if (TextUtils.isEmpty(cardName)) {
            Log.i(str, "getBenzNtgThirdCardView: cardName is null");
        }
        View cardView = View.inflate(this, C0899R.C0902layout.benz_ntg_fy_third_card, null);
        cardView.setTag(cardName);
        String[] split = cardName.substring(4).split(",");
        boolean z = false;
        final String pkg = split[0];
        final String cls = split[1];
        TextView titleName = (TextView) cardView.findViewById(C0899R.C0901id.benz_mbux_third_card_title);
        ImageView bgImg = (ImageView) cardView.findViewById(C0899R.C0901id.benz_mbux_third_card_bg);
        bgImg.setTag(cardName);
        bgImg.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.MainActivity.122
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MainActivity.mBcVieModel.openAppTask(new ComponentName(pkg, cls));
            }
        });
        bgImg.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wits.ksw.MainActivity.123
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                Log.i(MainActivity.TAG, "getBenzNtgThirdCardView onLongClick:  " + ((String) v.getTag()));
                MainActivity.mBcVieModel.selectCardName.set((String) v.getTag());
                MainActivity.this.mBenzNtgfyNewAdapter.notifyDataSetChanged();
                int position = BenzUtils.nameList.indexOf((String) v.getTag());
                if (MainActivity.this.scrollHelper != null) {
                    MainActivity.this.scrollHelper.scrollToPosition(position / 5);
                }
                MainActivity.mBcVieModel.isEditState.set(true);
                return true;
            }
        });
        ImageView iconImg = (ImageView) cardView.findViewById(C0899R.C0901id.benz_mbux_third_card_icon);
        ResolveInfo resolveInfo = AppInfoUtils.findAppByPkgAndCls(this, pkg, cls);
        PackageManager pm = KswApplication.appContext.getPackageManager();
        StringBuilder append = new StringBuilder().append("getBenzNtgThirdCardView: resolveInfo == null");
        if (resolveInfo == null) {
            z = true;
        }
        Log.w(str, append.append(z).append(", pkg : ").append(pkg).append(", cls : ").append(cls).toString());
        if (resolveInfo == null) {
            try {
                String appName = pm.getApplicationLabel(pm.getApplicationInfo(pkg, 128)).toString();
                Drawable appIcon = pm.getApplicationIcon(pkg);
                titleName.setText(appName);
                iconImg.setImageDrawable(appIcon);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            titleName.setText(resolveInfo.loadLabel(pm).toString());
            iconImg.setImageDrawable(resolveInfo.loadIcon(pm));
        }
        return cardView;
    }

    @Override // com.wits.ksw.launcher.base.BaseThemeActivity
    protected void initAudiView() {
        Log.i(TAG, "initAudiView: ");
        setFull();
        setStatusBarTranslucent();
        AudiViewModel audiViewModel = (AudiViewModel) ViewModelProviders.m59of(this).get(AudiViewModel.class);
        audiViewModel.setActivity(this);
        audiViewModel.initData();
        audiViewModel.startWeatherLooper();
        ActivityMainAudiBinding audiBinding = (ActivityMainAudiBinding) DataBindingUtil.setContentView(this, C0899R.C0902layout.activity_main_audi);
        audiBinding.MLoopRotarySwitchView.setMultiple(6.0f).setR(getWindowManager().getDefaultDisplay().getWidth() / 3.4f).setLoopRotationX(-22).setLoopRotationZ(1);
        audiBinding.setVm(audiViewModel);
    }

    private void addOnPageChangeListener() {
        this.bmwBinding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.MainActivity.124
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
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

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
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
        AlsId7UiMainBinding alsId7UiMainBinding2 = alsId7UiMainBinding;
        if (alsId7UiMainBinding2 != null) {
            alsId7UiMainBinding2.viewPage.setCurrentItem(index);
        }
        MainKswID7Binding mainKswID7Binding = this.kswId7Binding;
        if (mainKswID7Binding != null) {
            mainKswID7Binding.viewPage.setCurrentItem(index);
        }
        ActivityMainAlsId7V2Binding activityMainAlsId7V2Binding = this.alsId7V2Binding;
        if (activityMainAlsId7V2Binding != null) {
            activityMainAlsId7V2Binding.viewPage.setCurrentItem(index);
        }
    }

    public void refreshLastViewFocused() {
        LauncherViewModel launcherViewModel = this.viewModel;
        if (launcherViewModel != null) {
            launcherViewModel.addLastViewFocused(this.bmwBinding.menuButton1);
            this.viewModel.refreshLastViewFocused();
        }
        AlsID7ViewModel alsID7ViewModel = this.alsID7ViewModel;
        if (alsID7ViewModel != null) {
            alsID7ViewModel.addLastViewFocused(this.alsId7Binding.btnApps);
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
        View view = getWindow().getDecorView().findViewById(resId);
        WiewFocusUtils.setViewRequestFocus(view);
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        BcVieModel bcVieModel;
        super.onPause();
        Log.i(TAG, "onPause: ");
        if (UiThemeUtils.isUI_GS_ID8(this) || UiThemeUtils.isUI_PEMP_ID8(this)) {
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
        if ((UiThemeUtils.isBenz_MBUX_2021_KSW(this) || UiThemeUtils.isBenz_MBUX_2021_KSW_V2(this) || ((UiThemeUtils.isBenz_NTG6_FY(this) && ClientManager.getInstance().isAls6208Client()) || (UiThemeUtils.isUI_NTG6_FY_V2(this) && ClientManager.getInstance().isAls6208Client()))) && (bcVieModel = mBcVieModel) != null) {
            bcVieModel.isEditState.set(false);
        }
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
        Settings.System.putInt(getContentResolver(), "launcher_main_activity_freedom", 0);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.alsID7ViewModel != null && UiThemeUtils.isID7_ALS(this)) {
            this.alsID7ViewModel.refreshShortCutInfo(LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3);
        }
        if (this.alsID7ViewModel != null && UiThemeUtils.isID7_ALS_V2(this)) {
            this.alsID7ViewModel.refreshShortCutInfo(LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1_V2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1_V2, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2_V2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2_V2, LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3_V2, LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3_V2);
        }
        if (this.alsId7UiViewModel != null && UiThemeUtils.isALS_ID7_UI(this)) {
            this.alsId7UiViewModel.refreshShortCutInfo(LauncherViewModel.ALS_KEY_SHORTCUT_PKG_1, LauncherViewModel.ALS_KEY_SHORTCUT_CLS_1, LauncherViewModel.ALS_KEY_SHORTCUT_PKG_2, LauncherViewModel.ALS_KEY_SHORTCUT_CLS_2, null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateData(WitsStatus witsStatus) {
        String str = TAG;
        Log.d(str, "updateData: type=====" + witsStatus.type);
        switch (witsStatus.type) {
            case 1:
                AlsID7ViewModel alsID7ViewModel = this.alsID7ViewModel;
                if (alsID7ViewModel != null) {
                    alsID7ViewModel.setMusicPlayState(false);
                    this.alsID7ViewModel.setMusicPlayStop(true);
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
                AlsID7ViewModel alsID7ViewModel2 = this.alsID7ViewModel;
                if (alsID7ViewModel2 != null) {
                    if (this.isStop) {
                        alsID7ViewModel2.setMusicPlayState(false);
                    } else {
                        alsID7ViewModel2.setMusicPlayState(isPlay);
                        this.alsID7ViewModel.setMusicPlayStop(false);
                    }
                }
                LauncherViewModel launcherViewModel = this.bmwId8ViewModel;
                if (launcherViewModel != null) {
                    if (this.isStop) {
                        launcherViewModel.setMusicPlayState(false);
                        return;
                    }
                    launcherViewModel.setMusicPlayState(isPlay);
                    this.bmwId8ViewModel.setMusicPlayStop(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void musicObserverStatus() {
        if (UiThemeUtils.isUI_GS_ID8(this)) {
            PowerManagerApp.registerIContentObserver("music_stop", this.mId8GsMusicObserver);
        } else if (UiThemeUtils.isUI_PEMP_ID8(this)) {
            PowerManagerApp.registerIContentObserver("music_stop", this.mId8PempMusicObserver);
        } else {
            PowerManagerApp.registerIContentObserver("music_stop", this.mMusicObserver);
        }
        try {
            PowerManagerApp.getManager().registerCmdListener(new ICmdListener.Stub() { // from class: com.wits.ksw.MainActivity.128
                @Override // com.wits.pms.ICmdListener
                public boolean handleCommand(String s) throws RemoteException {
                    return false;
                }

                @Override // com.wits.pms.ICmdListener
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

    /* JADX WARN: Can't wrap try/catch for region: R(62:1|(1:3)|4|(1:6)(2:159|(1:161)(2:162|(1:164)))|7|(1:9)|10|(1:12)|13|(3:150|151|(1:155))|15|(4:143|(1:146)|147|(1:149))|19|(3:21|(1:23)(1:25)|24)|26|(1:28)|29|(3:31|(1:35)|36)|37|(3:39|(1:43)|44)|45|(5:47|(1:51)|52|(1:56)|57)|58|59|60|(7:65|66|(14:70|(1:72)|73|74|(1:76)|(1:82)|(1:84)|85|86|(1:90)|91|(1:93)|94|(1:96))|98|(1:100)(1:104)|101|102)|112|(1:114)|115|116|(1:118)|(1:122)|(1:124)|125|126|(1:128)(1:137)|129|(1:131)(1:136)|132|(1:134)(1:135)|66|(19:68|70|(0)|73|74|(0)|(3:78|80|82)|(0)|85|86|(2:88|90)|91|(0)|94|(0)|98|(0)(0)|101|102)|108|110|70|(0)|73|74|(0)|(0)|(0)|85|86|(0)|91|(0)|94|(0)|98|(0)(0)|101|102) */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0251, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0252, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0323, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0324, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02e4 A[Catch: Exception -> 0x0383, TRY_LEAVE, TryCatch #3 {Exception -> 0x0383, blocks: (B:80:0x01df, B:83:0x0201, B:115:0x02b7, B:117:0x02bd, B:123:0x02d7, B:125:0x02e4, B:126:0x02eb, B:128:0x02f9, B:130:0x0307, B:132:0x030d, B:134:0x0317, B:136:0x031c, B:137:0x031f, B:141:0x0327, B:143:0x0333, B:145:0x033d, B:146:0x0346, B:148:0x034e, B:149:0x0367, B:151:0x036b, B:140:0x0324, B:119:0x02c7, B:121:0x02cd, B:87:0x020c, B:89:0x021c, B:90:0x0223, B:92:0x0231, B:94:0x023f, B:96:0x0245, B:98:0x024a, B:99:0x024d, B:103:0x0255, B:105:0x0261, B:107:0x0272, B:109:0x027a, B:111:0x0297, B:113:0x029b, B:102:0x0252), top: B:167:0x01df, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02f9 A[Catch: RemoteException -> 0x0323, Exception -> 0x0383, TryCatch #1 {RemoteException -> 0x0323, blocks: (B:126:0x02eb, B:128:0x02f9, B:130:0x0307, B:132:0x030d, B:134:0x0317, B:136:0x031c, B:137:0x031f), top: B:163:0x02eb, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0307 A[Catch: RemoteException -> 0x0323, Exception -> 0x0383, TryCatch #1 {RemoteException -> 0x0323, blocks: (B:126:0x02eb, B:128:0x02f9, B:130:0x0307, B:132:0x030d, B:134:0x0317, B:136:0x031c, B:137:0x031f), top: B:163:0x02eb, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x031c A[Catch: RemoteException -> 0x0323, Exception -> 0x0383, TryCatch #1 {RemoteException -> 0x0323, blocks: (B:126:0x02eb, B:128:0x02f9, B:130:0x0307, B:132:0x030d, B:134:0x0317, B:136:0x031c, B:137:0x031f), top: B:163:0x02eb, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0333 A[Catch: Exception -> 0x0383, TryCatch #3 {Exception -> 0x0383, blocks: (B:80:0x01df, B:83:0x0201, B:115:0x02b7, B:117:0x02bd, B:123:0x02d7, B:125:0x02e4, B:126:0x02eb, B:128:0x02f9, B:130:0x0307, B:132:0x030d, B:134:0x0317, B:136:0x031c, B:137:0x031f, B:141:0x0327, B:143:0x0333, B:145:0x033d, B:146:0x0346, B:148:0x034e, B:149:0x0367, B:151:0x036b, B:140:0x0324, B:119:0x02c7, B:121:0x02cd, B:87:0x020c, B:89:0x021c, B:90:0x0223, B:92:0x0231, B:94:0x023f, B:96:0x0245, B:98:0x024a, B:99:0x024d, B:103:0x0255, B:105:0x0261, B:107:0x0272, B:109:0x027a, B:111:0x0297, B:113:0x029b, B:102:0x0252), top: B:167:0x01df, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x034e A[Catch: Exception -> 0x0383, TryCatch #3 {Exception -> 0x0383, blocks: (B:80:0x01df, B:83:0x0201, B:115:0x02b7, B:117:0x02bd, B:123:0x02d7, B:125:0x02e4, B:126:0x02eb, B:128:0x02f9, B:130:0x0307, B:132:0x030d, B:134:0x0317, B:136:0x031c, B:137:0x031f, B:141:0x0327, B:143:0x0333, B:145:0x033d, B:146:0x0346, B:148:0x034e, B:149:0x0367, B:151:0x036b, B:140:0x0324, B:119:0x02c7, B:121:0x02cd, B:87:0x020c, B:89:0x021c, B:90:0x0223, B:92:0x0231, B:94:0x023f, B:96:0x0245, B:98:0x024a, B:99:0x024d, B:103:0x0255, B:105:0x0261, B:107:0x0272, B:109:0x027a, B:111:0x0297, B:113:0x029b, B:102:0x0252), top: B:167:0x01df, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x036b A[Catch: Exception -> 0x0383, TRY_LEAVE, TryCatch #3 {Exception -> 0x0383, blocks: (B:80:0x01df, B:83:0x0201, B:115:0x02b7, B:117:0x02bd, B:123:0x02d7, B:125:0x02e4, B:126:0x02eb, B:128:0x02f9, B:130:0x0307, B:132:0x030d, B:134:0x0317, B:136:0x031c, B:137:0x031f, B:141:0x0327, B:143:0x0333, B:145:0x033d, B:146:0x0346, B:148:0x034e, B:149:0x0367, B:151:0x036b, B:140:0x0324, B:119:0x02c7, B:121:0x02cd, B:87:0x020c, B:89:0x021c, B:90:0x0223, B:92:0x0231, B:94:0x023f, B:96:0x0245, B:98:0x024a, B:99:0x024d, B:103:0x0255, B:105:0x0261, B:107:0x0272, B:109:0x027a, B:111:0x0297, B:113:0x029b, B:102:0x0252), top: B:167:0x01df, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x039d  */
    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onResume() {
        String str;
        String uiName;
        String cardStr;
        int googleApp;
        int weatherApp;
        int eqApp;
        ActivityGsId8LauncherMainBinding activityGsId8LauncherMainBinding;
        super.onResume();
        Settings.System.putInt(getContentResolver(), "mPageIndex", 0);
        String str2 = TAG;
        Log.i(str2, "onResume: ");
        if (Settings.System.getInt(getContentResolver(), "multi_window", -1) == 1) {
            Intent intent = new Intent("com.wits.on_multi_window_mode").putExtra("multi_window", false);
            sendBroadcast(intent);
        }
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
        LauncherViewModel launcherViewModel2 = this.alsId7UiViewModel;
        if (launcherViewModel2 != null) {
            launcherViewModel2.resumeViewModel();
        }
        AlsID7ViewModel alsID7ViewModel = this.alsID7ViewModel;
        if (alsID7ViewModel != null) {
            alsID7ViewModel.resumeViewModel();
            musicObserverStatus();
        }
        isFront = true;
        if (UiThemeUtils.isLAND_ROVER(this)) {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                PowerManagerApp.getStatusBoolean("video_full");
                Log.d(str2, "onResume topapp =" + topApp);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID) && LandroverPopWindow.isForeground(getApplicationContext(), getClass().getName())) {
                    Log.d(str2, "onResume showpopwindow");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (UiThemeUtils.isLEXUS_LS_UI(mainActivity) || UiThemeUtils.isLEXUS_LS_UI_V2(mainActivity)) {
            LOGE.m43E("shotScreenObserver screenFile = " + this.screenFile);
            ImageView iv_lexus_ls_drag_desktop = (ImageView) findViewById(C0899R.C0901id.iv_lexus_ls_drag_desktop);
            Bitmap bitmap = BitmapFactory.decodeFile(this.screenFile);
            if (bitmap != null && iv_lexus_ls_drag_desktop != null) {
                iv_lexus_ls_drag_desktop.setBackground(new BitmapDrawable((Resources) null, bitmap));
                LOGE.m43E("set BG");
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
        if (UiThemeUtils.isALS_ID7_UI(this)) {
            String skinName = Settings.System.getString(mainActivity.getContentResolver(), KswApplication.SKIN_NAME);
            Log.d(TAG, "onResume: skinName------->" + skinName);
            setAlsButtonBg(skinName);
        }
        UiThemeUtils.isBMW_EVO_ID7_V2(this);
        if (UiThemeUtils.isBMW_ID8_UI(this)) {
            LauncherViewModel launcherViewModel3 = this.bmwId8ViewModel;
            if (launcherViewModel3 != null && this.onID8SkinChangeListener != null) {
                launcherViewModel3.resumeViewModel();
                this.bmwId8ViewModel.initSkinData(this.onID8SkinChangeListener);
            }
            id8ExitModusPage();
            beforeRefreshFragmentCards();
            beforeRefreshLeftBarIcon();
        }
        if (UiThemeUtils.isUI_PEMP_ID8(this)) {
            if (this.bmwId8ViewModel != null && this.onPempID8SkinChangeListener != null) {
                musicObserverStatus();
                this.bmwId8ViewModel.resumeViewModel();
                this.bmwId8ViewModel.initSkinData(this.onPempID8SkinChangeListener);
            }
            beforeRefreshFragmentCardsPempId8();
            beforeRefreshLeftBarIcon();
            setPempID8MusicBGSelector(ID8LauncherConstants.loadCurrentSkin());
        }
        if (UiThemeUtils.isUI_GS_ID8(this)) {
            gsid8HandlerFreedom();
            Handler handler = this.handler;
            if (handler != null && (activityGsId8LauncherMainBinding = this.bmwId8GsUiMainLayoutBinding) != null) {
                handler.sendMessageDelayed(handler.obtainMessage(this.touchEventId, activityGsId8LauncherMainBinding.gsScrollView), 50L);
            }
            if (this.bmwId8ViewModel != null && this.onGsID8SkinChangeListener != null) {
                musicObserverStatus();
                this.bmwId8ViewModel.resumeViewModel();
                this.bmwId8ViewModel.initSkinData(this.onGsID8SkinChangeListener);
            }
            beforeRefreshFragmentCardsGSID8();
            beforeRefreshLeftBarIcon();
            setID8GsMusicBGSelector(ID8LauncherConstants.loadCurrentSkin());
            Settings.System.putInt(getContentResolver(), "launcher_main_activity_freedom", 1);
        }
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!UiThemeUtils.isBenz_MBUX_2021_KSW(this) && !UiThemeUtils.isBenz_MBUX_2021_KSW_V2(this)) {
            str = "onResume: \u5237\u65b0\u5361\u7247\u53ca\u6307\u793a\u5668 ";
            if ((UiThemeUtils.isBenz_NTG6_FY(this) && ClientManager.getInstance().isAls6208Client()) || (UiThemeUtils.isUI_NTG6_FY_V2(this) && ClientManager.getInstance().isAls6208Client())) {
                uiName = UiThemeUtils.getUiName(this);
                if (!TextUtils.equals(uiName, this.mUiName)) {
                    this.mUiName = uiName;
                    this.mBenzCardStr = "";
                    BenzUtils.loadSeq();
                }
                googleApp = PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP);
                weatherApp = PowerManagerApp.getSettingsInt(KeyConfig.GLOBAL_WEATHER_APP);
                eqApp = PowerManagerApp.getSettingsInt(KeyConfig.EQ_APP);
                if (googleApp == 0) {
                    BenzUtils.removeByPkg(BenzUtils.GOOGLE_SEARCH_PKG);
                    BenzUtils.removeByPkg(BenzUtils.GOOGLE_ASSISTANT_PKG);
                    BenzUtils.removeByPkg(BenzUtils.GOOGLE_MAP);
                    BenzUtils.removeByPkg(BenzUtils.GOOGLE_PLAY);
                }
                if (weatherApp == 0 && UiThemeUtils.isBenz_NTG6_FY(this) && ClientManager.getInstance().isAls6208Client()) {
                    BenzUtils.removeByPkg(BenzUtils.WEATHER_PKG);
                }
                if (eqApp == 0) {
                    BenzUtils.removeByPkg(BenzUtils.EQ_PKG);
                }
                BenzUtils.saveSystemCardSeq();
                cardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_NTG6_FY_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
                if (UiThemeUtils.isUI_NTG6_FY_V2(this) && ClientManager.getInstance().isAls6208Client()) {
                    cardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_NTG6_FY_V2_ORDER, BenzUtils.BENZ_NTG6_FY_V2_ORDER_DEFAULT);
                }
                if (!TextUtils.equals(this.mBenzCardStr, cardStr)) {
                    Log.i(TAG, str);
                    loadBenzNtgCardView();
                    this.mBenzNtgfyNewAdapter.setCardList(BenzUtils.loadCardMenuList());
                    initIndicatorPoints(3);
                    this.mBenzCardStr = cardStr;
                }
                if (this.isCardAdded) {
                    this.isCardAdded = false;
                    Log.i(TAG, "onResume: \u5361\u7247\u5df2\u6dfb\u52a0\u5b8c\u6210\uff0c\u5c06viewpager\u6ed1\u52a8\u5230\u5e94\u7528\u56fe\u6807\u6240\u5728\u9875 ");
                    int position = BenzUtils.nameList.indexOf(BenzUtils.APPS);
                    mBenzNtgFyActivityNewBinding.benzMbux2021Viewpager.setCurrentItem(position / 5);
                }
            }
            if (KswUtils.ismph()) {
                McuImpl.getInstance().getCarInfo().speedUnit.set("mph");
            } else {
                McuImpl.getInstance().getCarInfo().speedUnit.set("km/h");
            }
            handle360();
        }
        String uiName2 = UiThemeUtils.getUiName(this);
        if (!TextUtils.equals(uiName2, this.mUiName)) {
            this.mUiName = uiName2;
            this.mBenzCardStr = "";
            BenzUtils.loadSeq();
        }
        int googleApp2 = PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP);
        int weatherApp2 = PowerManagerApp.getSettingsInt(KeyConfig.GLOBAL_WEATHER_APP);
        int eqApp2 = PowerManagerApp.getSettingsInt(KeyConfig.EQ_APP);
        if (googleApp2 == 0) {
            BenzUtils.removeByPkg(BenzUtils.GOOGLE_SEARCH_PKG);
            BenzUtils.removeByPkg(BenzUtils.GOOGLE_ASSISTANT_PKG);
            BenzUtils.removeByPkg(BenzUtils.GOOGLE_MAP);
            BenzUtils.removeByPkg(BenzUtils.GOOGLE_PLAY);
        }
        if (weatherApp2 == 0 && UiThemeUtils.isBenz_MBUX_2021_KSW(this)) {
            BenzUtils.removeByPkg(BenzUtils.WEATHER_PKG);
        }
        if (eqApp2 == 0) {
            BenzUtils.removeByPkg(BenzUtils.EQ_PKG);
        }
        BenzUtils.saveSystemCardSeq();
        String cardStr2 = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_MBUX_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
        if (UiThemeUtils.isBenz_MBUX_2021_KSW_V2(this)) {
            cardStr2 = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_MBUX_V2_ORDER, BenzUtils.BENZ_MBUX_V2_ORDER_DEFAULT);
        }
        if (TextUtils.equals(this.mBenzCardStr, cardStr2)) {
            str = "onResume: \u5237\u65b0\u5361\u7247\u53ca\u6307\u793a\u5668 ";
        } else {
            Log.i(TAG, "onResume: \u5237\u65b0\u5361\u7247\u53ca\u6307\u793a\u5668 ");
            loadBenzCardView();
            str = "onResume: \u5237\u65b0\u5361\u7247\u53ca\u6307\u793a\u5668 ";
            this.mBenzmbux2021newAdapter.setCardList(BenzUtils.loadCardMenuList());
            initIndicatorPoints(12);
            this.mBenzCardStr = cardStr2;
        }
        if (this.isCardAdded) {
            this.isCardAdded = false;
            Log.i(TAG, "onResume: \u5361\u7247\u5df2\u6dfb\u52a0\u5b8c\u6210\uff0c\u5c06viewpager\u6ed1\u52a8\u5230\u5e94\u7528\u56fe\u6807\u6240\u5728\u9875 ");
            int position2 = BenzUtils.nameList.indexOf(BenzUtils.APPS);
            mBenzMbuxKswActivityNewBinding.benzMbux2021Viewpager.setCurrentItem(position2 / 5);
        }
        if (UiThemeUtils.isBenz_NTG6_FY(this)) {
            uiName = UiThemeUtils.getUiName(this);
            if (!TextUtils.equals(uiName, this.mUiName)) {
            }
            googleApp = PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP);
            weatherApp = PowerManagerApp.getSettingsInt(KeyConfig.GLOBAL_WEATHER_APP);
            eqApp = PowerManagerApp.getSettingsInt(KeyConfig.EQ_APP);
            if (googleApp == 0) {
            }
            if (weatherApp == 0) {
                BenzUtils.removeByPkg(BenzUtils.WEATHER_PKG);
            }
            if (eqApp == 0) {
            }
            BenzUtils.saveSystemCardSeq();
            cardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_NTG6_FY_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
            if (UiThemeUtils.isUI_NTG6_FY_V2(this)) {
                cardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_NTG6_FY_V2_ORDER, BenzUtils.BENZ_NTG6_FY_V2_ORDER_DEFAULT);
            }
            if (!TextUtils.equals(this.mBenzCardStr, cardStr)) {
            }
            if (this.isCardAdded) {
            }
            if (KswUtils.ismph()) {
            }
            handle360();
        }
        uiName = UiThemeUtils.getUiName(this);
        if (!TextUtils.equals(uiName, this.mUiName)) {
        }
        googleApp = PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP);
        weatherApp = PowerManagerApp.getSettingsInt(KeyConfig.GLOBAL_WEATHER_APP);
        eqApp = PowerManagerApp.getSettingsInt(KeyConfig.EQ_APP);
        if (googleApp == 0) {
        }
        if (weatherApp == 0) {
        }
        if (eqApp == 0) {
        }
        BenzUtils.saveSystemCardSeq();
        cardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_NTG6_FY_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
        if (UiThemeUtils.isUI_NTG6_FY_V2(this)) {
        }
        if (!TextUtils.equals(this.mBenzCardStr, cardStr)) {
        }
        if (this.isCardAdded) {
        }
        if (KswUtils.ismph()) {
        }
        handle360();
    }

    private void handle360() {
        int googleApp = this.resumeCount;
        if (googleApp == 1) {
            int googleApp2 = Settings.System.getInt(getContentResolver(), KeyConfig.GOOGLE_APP, 0);
            this.resumeCount++;
            if (googleApp2 == 1) {
                try {
                    if (PowerManagerApp.getStatusBoolean("googleDisableBoot")) {
                        Log.e(TAG, "\u5f00\u542fgoogle");
                        setApplicationEnabledSetting(1);
                        PowerManagerApp.setBooleanStatus("googleDisableBoot", false);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else if (googleApp == 0) {
            this.resumeCount = googleApp + 1;
        }
    }

    private void gsid8HandlerFreedom() {
        if (this.checkZlinkDisplaySetting) {
            try {
                Process process = Runtime.getRuntime().exec("getprop ro.zlink.land.display");
                InputStreamReader ir = new InputStreamReader(process.getInputStream());
                BufferedReader input = new BufferedReader(ir);
                String displayStr = null;
                while (true) {
                    String str = input.readLine();
                    if (str == null) {
                        break;
                    }
                    displayStr = str;
                    Log.i(TAG, "prop = " + str);
                }
                if (TextUtils.isEmpty(displayStr)) {
                    DisplayMetrics dm = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(dm);
                    String display = dm.widthPixels + "x" + dm.heightPixels;
                    Runtime.getRuntime().exec("setprop  ro.zlink.land.display " + display);
                } else {
                    this.checkZlinkDisplaySetting = false;
                }
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
            if (!TextUtils.isEmpty(freedomPkg)) {
                if (!settingLanguage) {
                    int memoryMode = Settings.System.getInt(getContentResolver(), "memory_mode_for_freedom");
                    if (!this.isID8FirstStart) {
                        this.bmwId8ViewModel.launchApp(freedomPkg, 5);
                    } else {
                        if (memoryMode != 1) {
                            this.bmwId8ViewModel.launchApp(freedomPkg, 5);
                        }
                        this.isID8FirstStart = false;
                    }
                    if (memoryMode != 0) {
                        Settings.System.putInt(getContentResolver(), "memory_mode_for_freedom", 0);
                    }
                    return;
                }
                settingLanguage = false;
            }
        } catch (Settings.SettingNotFoundException e3) {
            this.bmwId8ViewModel.launchApp(freedomPkg, 5);
        }
    }

    private String getGsId8FreedomPkg() {
        int isSelect;
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
                    isSelect = PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (isSelect != 1) {
                    i++;
                } else {
                    freedomPkg = pkg;
                    break;
                }
            } else if (!KswUtils.isAppInstalled(this, pkg)) {
                i++;
            } else {
                freedomPkg = pkg;
                break;
            }
        }
        Settings.System.putString(getContentResolver(), "wits_freedom_pkg", freedomPkg);
        FileUtils.savaStringData(KeyConfig.NAVI_DEFUAL, freedomPkg);
        return freedomPkg;
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean llContainerFocus;
        boolean firstFocus;
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
        if (UiThemeUtils.isBenz_MBUX(this) || UiThemeUtils.isBenz_MBUX_2021_KSW(this) || UiThemeUtils.isBenz_MBUX_2021_KSW_V2(this) || ((UiThemeUtils.isBenz_NTG6_FY(this) && ClientManager.getInstance().isAls6208Client()) || (UiThemeUtils.isUI_NTG6_FY_V2(this) && ClientManager.getInstance().isAls6208Client()))) {
            if (keyCode == 20) {
                if (event.getAction() == 0) {
                    KeyUtils.pressKey(22);
                }
                return true;
            } else if (keyCode == 19) {
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
        if ((UiThemeUtils.isBMW_ID8_UI(this) || UiThemeUtils.isUI_GS_ID8(this) || UiThemeUtils.isUI_PEMP_ID8(this)) && event.getAction() == 0) {
            if (UiThemeUtils.isBMW_ID8_UI(this)) {
                llContainerFocus = this.bmwId8UiMainLayoutBinding.llContainer.hasFocus();
                firstFocus = this.bmwId8UiMainLayoutBinding.llContainer.getChildAt(0).hasFocus();
            } else {
                boolean llContainerFocus2 = UiThemeUtils.isUI_PEMP_ID8(this);
                if (llContainerFocus2) {
                    llContainerFocus = this.bmwPempId8UiMainLayoutBinding.llContainer.hasFocus();
                    firstFocus = this.bmwPempId8UiMainLayoutBinding.llContainer.getChildAt(0).hasFocus();
                } else {
                    llContainerFocus = this.bmwId8GsUiMainLayoutBinding.llContaine.hasFocus();
                    firstFocus = this.bmwId8GsUiMainLayoutBinding.llContaine.getChildAt(0).hasFocus();
                    Handler handler = this.handler;
                    handler.sendMessageDelayed(handler.obtainMessage(this.touchEventId, this.bmwId8GsUiMainLayoutBinding.gsScrollView), 50L);
                }
            }
            if (keyCode == 19 && llContainerFocus && !firstFocus) {
                KeyUtils.pressKey(21);
                return true;
            } else if (keyCode == 20 && llContainerFocus) {
                KeyUtils.pressKey(22);
                return true;
            }
        }
        boolean llContainerFocus3 = super.dispatchKeyEvent(event);
        return llContainerFocus3;
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
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

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed: ");
        if ((UiThemeUtils.isBMW_ID8_UI(this) || UiThemeUtils.isUI_PEMP_ID8(this)) && this.bmwId8ViewModel != null) {
            id8ExitModusPage();
        }
        try {
            if (UiThemeUtils.isBenz_MBUX_2021_KSW(this) || UiThemeUtils.isBenz_MBUX_2021_KSW_V2(this)) {
                String uiName = UiThemeUtils.getUiName(this);
                if (!TextUtils.equals(uiName, this.mUiName)) {
                    this.mUiName = uiName;
                    this.mBenzCardStr = "";
                    BenzUtils.loadSeq();
                }
                try {
                    int googleApp = PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP);
                    int weatherApp = PowerManagerApp.getSettingsInt(KeyConfig.GLOBAL_WEATHER_APP);
                    int eqApp = PowerManagerApp.getSettingsInt(KeyConfig.EQ_APP);
                    if (googleApp == 0) {
                        BenzUtils.removeByPkg(BenzUtils.GOOGLE_SEARCH_PKG);
                        BenzUtils.removeByPkg(BenzUtils.GOOGLE_ASSISTANT_PKG);
                        BenzUtils.removeByPkg(BenzUtils.GOOGLE_MAP);
                        BenzUtils.removeByPkg(BenzUtils.GOOGLE_PLAY);
                    }
                    if (weatherApp == 0 && UiThemeUtils.isBenz_MBUX_2021_KSW(this)) {
                        BenzUtils.removeByPkg(BenzUtils.WEATHER_PKG);
                    }
                    if (eqApp == 0) {
                        BenzUtils.removeByPkg(BenzUtils.EQ_PKG);
                    }
                    BenzUtils.saveSystemCardSeq();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                String cardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_MBUX_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
                if (UiThemeUtils.isBenz_MBUX_2021_KSW_V2(this)) {
                    cardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_MBUX_V2_ORDER, BenzUtils.BENZ_MBUX_V2_ORDER_DEFAULT);
                }
                if (!TextUtils.equals(this.mBenzCardStr, cardStr)) {
                    Log.i(TAG, "onBackPressed: \u5237\u65b0\u5361\u7247\u53ca\u6307\u793a\u5668 ");
                    loadBenzCardView();
                    this.mBenzmbux2021newAdapter.setCardList(BenzUtils.loadCardMenuList());
                    initIndicatorPoints(12);
                    this.mBenzCardStr = cardStr;
                }
                BcVieModel bcVieModel = mBcVieModel;
                if (bcVieModel != null) {
                    bcVieModel.isEditState.set(false);
                }
            }
            if ((UiThemeUtils.isBenz_NTG6_FY(this) && ClientManager.getInstance().isAls6208Client()) || (UiThemeUtils.isUI_NTG6_FY_V2(this) && ClientManager.getInstance().isAls6208Client())) {
                String uiName2 = UiThemeUtils.getUiName(this);
                if (!TextUtils.equals(uiName2, this.mUiName)) {
                    this.mUiName = uiName2;
                    this.mBenzCardStr = "";
                    BenzUtils.loadSeq();
                }
                try {
                    int googleApp2 = PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP);
                    int weatherApp2 = PowerManagerApp.getSettingsInt(KeyConfig.GLOBAL_WEATHER_APP);
                    int eqApp2 = PowerManagerApp.getSettingsInt(KeyConfig.EQ_APP);
                    if (googleApp2 == 0) {
                        BenzUtils.removeByPkg(BenzUtils.GOOGLE_SEARCH_PKG);
                        BenzUtils.removeByPkg(BenzUtils.GOOGLE_ASSISTANT_PKG);
                        BenzUtils.removeByPkg(BenzUtils.GOOGLE_MAP);
                        BenzUtils.removeByPkg(BenzUtils.GOOGLE_PLAY);
                    }
                    if (weatherApp2 == 0 && UiThemeUtils.isBenz_NTG6_FY(this) && ClientManager.getInstance().isAls6208Client()) {
                        BenzUtils.removeByPkg(BenzUtils.WEATHER_PKG);
                    }
                    if (eqApp2 == 0) {
                        BenzUtils.removeByPkg(BenzUtils.EQ_PKG);
                    }
                    BenzUtils.saveSystemCardSeq();
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
                String cardStr2 = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_NTG6_FY_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
                if (UiThemeUtils.isUI_NTG6_FY_V2(this) && ClientManager.getInstance().isAls6208Client()) {
                    cardStr2 = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_NTG6_FY_V2_ORDER, BenzUtils.BENZ_NTG6_FY_V2_ORDER_DEFAULT);
                }
                if (!TextUtils.equals(this.mBenzCardStr, cardStr2)) {
                    Log.i(TAG, "onBackPressed: \u5237\u65b0\u5361\u7247\u53ca\u6307\u793a\u5668 ");
                    loadBenzNtgCardView();
                    this.mBenzNtgfyNewAdapter.setCardList(BenzUtils.loadCardMenuList());
                    initIndicatorPoints(3);
                    this.mBenzCardStr = cardStr2;
                }
                BcVieModel bcVieModel2 = mBcVieModel;
                if (bcVieModel2 != null) {
                    bcVieModel2.isEditState.set(false);
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private void id8ExitModusPage() {
        Boolean aBoolean = this.bmwId8ViewModel.isChangeModusStatusID8.get();
        if (aBoolean != null && aBoolean.booleanValue()) {
            this.bmwId8ViewModel.exitChangeModus();
        }
    }

    /* loaded from: classes17.dex */
    public class KeyControlBroadcastReceiver extends BroadcastReceiver {
        public KeyControlBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MainActivity.access$5508(MainActivity.this);
            if (MainActivity.this.mKeyControlCount == 2) {
                String message = intent.getStringExtra("message");
                char c = '\uffff';
                switch (message.hashCode()) {
                    case -418852507:
                        if (message.equals("KEYCODE_ENTER")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -207426337:
                        if (message.equals("KEYCODE_DPAD_RIGHT")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -145649313:
                        if (message.equals("KEYCODE_DPAD_DOWN")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -145421116:
                        if (message.equals("KEYCODE_DPAD_LEFT")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 361859736:
                        if (message.equals("KEYCODE_DPAD_UP")) {
                            c = 0;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        MainActivity.this.keyControlView(1);
                        break;
                    case 1:
                        MainActivity.this.keyControlView(2);
                        break;
                    case 2:
                        MainActivity.this.keyControlView(3);
                        break;
                    case 3:
                        MainActivity.this.keyControlView(4);
                        break;
                    case 4:
                        MainActivity.this.setViewClickEvent();
                        break;
                }
                MainActivity.this.mKeyControlCount = 0;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViewClickEvent() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            long downTime = SystemClock.uptimeMillis();
            int[] ints = new int[2];
            MotionEvent downEvent = MotionEvent.obtain(downTime, downTime, 0, ints[0] + 5, ints[1] + 5, 0);
            long downTime2 = downTime + 1000;
            MotionEvent upEvent = MotionEvent.obtain(downTime2, downTime2, 1, ints[0] + 5, ints[1] + 5, 0);
            currentFocus.onTouchEvent(downEvent);
            currentFocus.onTouchEvent(upEvent);
            downEvent.recycle();
            upEvent.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void keyControlView(int type) {
        View currentFocus = getCurrentFocus();
        if (currentFocus == null) {
            View view = getWindow().getDecorView().findViewById(C0899R.C0901id.ll_left_1);
            WiewFocusUtils.setViewRequestFocus(view);
            LauncherViewModel launcherViewModel = this.viewModel;
            if (launcherViewModel != null) {
                launcherViewModel.addLastViewFocused(view);
                return;
            }
            return;
        }
        int resId = KswUtils.getLastViewId(this);
        int index = setViewReturnIndex(getWindow().getDecorView().findViewById(resId));
        int typeIndex = setTypeReturnIndex(type, index);
        if (index != typeIndex) {
            focusIndexView(typeIndex);
        }
    }

    private int setTypeReturnIndex(int type, int index) {
        switch (type) {
            case 1:
                if (index > 1 && index != 6) {
                    return index - 1;
                }
                return index;
            case 2:
                if (index != 5 && index < GSID8LauncherConstants.nameList.size() + 5) {
                    return index + 1;
                }
                return index;
            case 3:
                if (index == 6) {
                    return 1;
                }
                if (index > 6) {
                    return index - 1;
                }
                return index;
            case 4:
                if (index <= 5) {
                    return 6;
                }
                if (index < GSID8LauncherConstants.nameList.size() + 5) {
                    return index + 1;
                }
                return index;
            default:
                return index;
        }
    }

    private int setViewReturnIndex(View view) {
        View cardView;
        int index = 0;
        if (this.mLeftBarView != null) {
            for (int i = 0; i < this.mLeftBarView.size(); i++) {
                View indexView = this.mLeftBarView.get(i);
                if (view == indexView) {
                    index = i + 1;
                }
            }
        }
        if (index == 0) {
            List<String> nameList = GSID8LauncherConstants.nameList;
            int i2 = 0;
            while (true) {
                if (i2 >= nameList.size()) {
                    break;
                }
                if (nameList.get(i2).startsWith("3rd")) {
                    View cardView2 = this.trdCardViewHashMap.get(nameList.get(i2));
                    cardView = cardView2;
                } else {
                    View cardView3 = this.systemCardViewHashMap.get(nameList.get(i2));
                    cardView = cardView3;
                }
                if (cardView == null || cardView.findViewById(C0899R.C0901id.iv_mask) != this.bmwId8ViewModel.getLastViewFocused()) {
                    i2++;
                } else {
                    index = i2 + 6;
                    break;
                }
            }
        }
        Log.w(TAG, "lastFocusView index: " + index);
        return index;
    }

    private void focusIndexView(int index) {
        View cardView;
        View targetView;
        if (index <= 5) {
            View targetView2 = this.mLeftBarView.get(index - 1);
            targetView = targetView2;
        } else {
            List<String> nameList = GSID8LauncherConstants.nameList;
            if (nameList.get(index - 6).startsWith("3rd")) {
                View cardView2 = this.trdCardViewHashMap.get(nameList.get(index - 6));
                cardView = cardView2;
            } else {
                View cardView3 = this.systemCardViewHashMap.get(nameList.get(index - 6));
                cardView = cardView3;
            }
            targetView = cardView.findViewById(C0899R.C0901id.iv_mask);
        }
        WiewFocusUtils.setViewRequestFocus(targetView);
        LauncherViewModel launcherViewModel = this.bmwId8ViewModel;
        if (launcherViewModel != null) {
            launcherViewModel.addLastViewFocused(targetView);
        }
    }

    private void registerKeyControlBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.KeyEvent.control");
        KeyControlBroadcastReceiver keyControlBroadcastReceiver = new KeyControlBroadcastReceiver();
        this.mKeyControlBroadcastReceiver = keyControlBroadcastReceiver;
        registerReceiver(keyControlBroadcastReceiver, intentFilter);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy:");
        this.viewModel = null;
        this.alsID7ViewModel = null;
        this.alsId7UiViewModel = null;
        this.bmwId8ViewModel = null;
        PowerManagerApp.unRegisterIContentObserver(this.mMusicObserver);
        PowerManagerApp.unRegisterIContentObserver(this.mId8GsMusicObserver);
        PowerManagerApp.unRegisterIContentObserver(this.mId8PempMusicObserver);
        KeyControlBroadcastReceiver keyControlBroadcastReceiver = this.mKeyControlBroadcastReceiver;
        if (keyControlBroadcastReceiver != null) {
            unregisterReceiver(keyControlBroadcastReceiver);
        }
        try {
            WeatherQueryManager.getInstance().doOnDestroy(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: requestCode " + requestCode + " resultCode " + resultCode);
        String cardStr = "";
        if (resultCode == 120) {
            if (UiThemeUtils.isBenz_MBUX_2021_KSW(this)) {
                cardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_MBUX_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
            } else if (UiThemeUtils.isBenz_MBUX_2021_KSW_V2(this)) {
                cardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_MBUX_V2_ORDER, BenzUtils.BENZ_MBUX_V2_ORDER_DEFAULT);
            } else if (UiThemeUtils.isBenz_NTG6_FY(this) && ClientManager.getInstance().isAls6208Client()) {
                cardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_NTG6_FY_ORDER, "NAVI;MUSIC;BT;CAR;SET;VIDEO;APPS;LINK;DASHBOARD;DVR");
            } else if (UiThemeUtils.isUI_NTG6_FY_V2(this) && ClientManager.getInstance().isAls6208Client()) {
                cardStr = BenzUtils.getBenzCardStr(this, BenzUtils.BENZ_NTG6_FY_V2_ORDER, BenzUtils.BENZ_NTG6_FY_V2_ORDER_DEFAULT);
            }
            if (!TextUtils.equals(this.mBenzCardStr, cardStr)) {
                this.isCardAdded = true;
                Toast.makeText(this, getText(C0899R.string.benz_mbux_2021_card_add_success), 0).show();
                return;
            }
            this.isCardAdded = false;
            Toast.makeText(this, getText(C0899R.string.benz_mbux_2021_card_add_fail), 0).show();
        }
    }
}
