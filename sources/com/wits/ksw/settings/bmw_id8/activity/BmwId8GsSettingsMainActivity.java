package com.wits.ksw.settings.bmw_id8.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.database.ContentObserver;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BmwId8gsSettingsMainLayoutBinding;
import com.wits.ksw.launcher.bmw_id8_ui.GSID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.settings.BaseSkinActivity;
import com.wits.ksw.settings.bmw_id8.adapter.BmwId8SettingsMainAdapter;
import com.wits.ksw.settings.bmw_id8.bean.BmwId8SettingsMainBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BmwId8GsSettingsMainActivity extends BaseSkinActivity {
    private final String TAG = "BmwId8GsSettingsMainActivity";
    /* access modifiers changed from: private */
    public LauncherViewModel bmwId8ViewModel;
    private ContentObserver contentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange, Uri uri) {
            Log.i("BmwId8GsSettingsMainActivity", "skin nChange");
            if (BmwId8GsSettingsMainActivity.this.mAdapter != null) {
                BmwId8GsSettingsMainActivity.this.mAdapter.notifyDataSetChanged();
            }
        }
    };
    private String id8CacheLeftIcon2;
    private String id8CacheLeftIcon3;
    private String id8CacheLeftIcon4;
    private String id8CacheLeftIcon5;
    /* access modifiers changed from: private */
    public BmwId8SettingsMainAdapter mAdapter;
    /* access modifiers changed from: private */
    public List<BmwId8SettingsMainBean> mBeanList = new ArrayList();
    private BmwId8gsSettingsMainLayoutBinding mBinding;
    private ItemTouchHelper mItemTouchHelper;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            String title = ((BmwId8SettingsMainBean) adapter.getData().get(position)).getTitle();
            Log.i("BmwId8GsSettingsMainActivity", " position " + position + " title " + title);
            BmwId8GsSettingsMainActivity.this.startActivityByTitle(title);
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BmwId8GsSettingsMainActivity", " onCreate ");
        this.mBinding = (BmwId8gsSettingsMainLayoutBinding) DataBindingUtil.setContentView(this, R.layout.bmw_id8gs_settings_main_layout);
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.bmwId8ViewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.mBinding.setLauncherViewModel(this.bmwId8ViewModel);
        initData();
        initView();
        getContentResolver().registerContentObserver(Settings.System.getUriFor(ID8LauncherConstants.ID8_SKIN), true, this.contentObserver);
    }

    private void initData() {
        this.mBeanList.clear();
        this.mBeanList.add(new BmwId8SettingsMainBean(getResources().getString(R.string.ksw_id8_settings_title_system), getResources().getString(R.string.ksw_id8_settings_content), R.drawable.bmw_id8_settings_main_bg, R.drawable.id8_settings_icon_system));
        this.mBeanList.add(new BmwId8SettingsMainBean(getResources().getString(R.string.id8_settings_navigate_title), getResources().getString(R.string.ksw_id8_settings_content), R.drawable.bmw_id8_settings_main_bg, R.drawable.id8_settings_icon_navi));
        this.mBeanList.add(new BmwId8SettingsMainBean(getResources().getString(R.string.ksw_id8_settings_title_audio), getResources().getString(R.string.ksw_id8_settings_content), R.drawable.bmw_id8_settings_main_bg, R.drawable.id8_settings_icon_audio));
        this.mBeanList.add(new BmwId8SettingsMainBean(getResources().getString(R.string.ksw_id8_settings_title_language), getResources().getString(R.string.ksw_id8_settings_content), R.drawable.bmw_id8_settings_main_bg, R.drawable.id8_settings_icon_language));
        this.mBeanList.add(new BmwId8SettingsMainBean(getResources().getString(R.string.ksw_id8_settings_title_time), getResources().getString(R.string.ksw_id8_settings_content), R.drawable.bmw_id8_settings_main_bg, R.drawable.id8_settings_icon_time));
        this.mBeanList.add(new BmwId8SettingsMainBean(getResources().getString(R.string.ksw_id8_settings_title_android), getResources().getString(R.string.ksw_id8_settings_content), R.drawable.bmw_id8_settings_main_bg, R.drawable.id8_settings_icon_android));
        this.mBeanList.add(new BmwId8SettingsMainBean(getResources().getString(R.string.ksw_id8_settings_title_factory), getResources().getString(R.string.ksw_id8_settings_content), R.drawable.bmw_id8_settings_main_bg, R.drawable.id8_settings_icon_factory));
        this.mBeanList.add(new BmwId8SettingsMainBean(getResources().getString(R.string.ksw_id8_settings_title_info), getResources().getString(R.string.item7), R.drawable.bmw_id8_settings_main_bg, R.drawable.id8_settings_icon_info));
    }

    private void initView() {
        this.mAdapter = new BmwId8SettingsMainAdapter(this.mBeanList);
        this.mBinding.bmwId8SettingsMainRecycle.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.mBinding.bmwId8SettingsMainRecycle.setAdapter(this.mAdapter);
        this.mAdapter.setItemClickListener(new BmwId8SettingsMainAdapter.ItemClickListener() {
            public void onItemClick(View v, int position, BmwId8SettingsMainBean item) {
                Log.i("BmwId8GsSettingsMainActivity", "onKeyChanged position " + position);
                BmwId8GsSettingsMainActivity.this.startActivityByTitle(item.getTitle());
            }
        });
        this.mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(15, 0);
            }

            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = viewHolder1.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(BmwId8GsSettingsMainActivity.this.mBeanList, i, i + 1);
                    }
                } else {
                    for (int i2 = fromPosition; i2 > toPosition; i2--) {
                        Collections.swap(BmwId8GsSettingsMainActivity.this.mBeanList, i2, i2 - 1);
                    }
                }
                BmwId8GsSettingsMainActivity.this.mAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
            }

            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
            }

            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
            }

            public boolean isLongPressDragEnabled() {
                return super.isLongPressDragEnabled();
            }
        });
    }

    /* access modifiers changed from: private */
    public void startActivityByTitle(String title) {
        if (TextUtils.equals(title, getResources().getString(R.string.ksw_id8_settings_title_system))) {
            startActivity(new Intent(getApplicationContext(), BmwId8SettingsSystemActivity.class));
        } else if (TextUtils.equals(title, getResources().getString(R.string.fragment_navigate_title))) {
            startActivity(new Intent(getApplicationContext(), BmwId8SettingsNaviActivity.class));
        } else if (TextUtils.equals(title, getResources().getString(R.string.ksw_id8_settings_title_audio))) {
            startActivity(new Intent(getApplicationContext(), BmwId8SettingsAudioActivity.class));
        } else if (TextUtils.equals(title, getResources().getString(R.string.ksw_id8_settings_title_language))) {
            startActivity(new Intent(getApplicationContext(), BmwId8SettingsLanguageActivity.class));
        } else if (TextUtils.equals(title, getResources().getString(R.string.ksw_id8_settings_title_time))) {
            startActivity(new Intent(getApplicationContext(), BmwId8SettingsTimeActivity.class));
        } else if (TextUtils.equals(title, getResources().getString(R.string.ksw_id8_settings_title_android))) {
            startActivity(new Intent("android.settings.SETTINGS"));
            overridePendingTransition(0, 0);
        } else if (TextUtils.equals(title, getResources().getString(R.string.ksw_id8_settings_title_factory))) {
            startActivity(new Intent(getApplicationContext(), BmwId8SettingsFactoryActivity.class));
        } else if (TextUtils.equals(title, getResources().getString(R.string.ksw_id8_settings_title_info))) {
            startActivity(new Intent(getApplicationContext(), BmwId8SettingsInfoActivity.class));
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("BmwId8GsSettingsMainActivity", " onNewIntent ");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.i("BmwId8GsSettingsMainActivity", " onResume ");
        beforeRefreshLeftBarIcon();
        String skinName = ID8LauncherConstants.loadCurrentSkin();
        if (TextUtils.equals(skinName, ID8LauncherConstants.ID8_SKIN_SPORT)) {
            refreshLeftBackground(R.drawable.bmw_id8_main_left_btn_red);
        } else if (TextUtils.equals(skinName, ID8LauncherConstants.ID8_SKIN_EFFICIENT)) {
            refreshLeftBackground(R.drawable.bmw_id8_main_left_btn_blue);
        } else {
            refreshLeftBackground(R.drawable.bmw_id8_main_left_btn_yellow);
        }
        this.mBinding.bmwId8SettingsMainLeftBar.llLeft4.setFocusedByDefault(true);
    }

    private void refreshLeftBackground(int backGroundId) {
        try {
            this.mBinding.bmwId8SettingsMainLeftBar.llLeft1.setBackground(getDrawable(backGroundId));
            this.mBinding.bmwId8SettingsMainLeftBar.llLeft2.setBackground(getDrawable(backGroundId));
            this.mBinding.bmwId8SettingsMainLeftBar.llLeft3.setBackground(getDrawable(backGroundId));
            this.mBinding.bmwId8SettingsMainLeftBar.llLeft4.setBackground(getDrawable(backGroundId));
            this.mBinding.bmwId8SettingsMainLeftBar.llLeft5.setBackground(getDrawable(backGroundId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void beforeRefreshLeftBarIcon() {
        Log.w("BmwId8GsSettingsMainActivity", "beforeRefreshLeftBarIcon: ");
        if (checkLeftIconHasChange()) {
            refreshLeftBarIcon();
        }
    }

    private boolean checkLeftIconHasChange() {
        if (!GSID8LauncherConstants.leftIcon3.equals(this.id8CacheLeftIcon3) || !GSID8LauncherConstants.leftIcon4.equals(this.id8CacheLeftIcon4) || !GSID8LauncherConstants.leftIcon5.equals(this.id8CacheLeftIcon5)) {
            return true;
        }
        return false;
    }

    private void refreshLeftBarIcon() {
        this.id8CacheLeftIcon3 = GSID8LauncherConstants.leftIcon3;
        this.id8CacheLeftIcon4 = GSID8LauncherConstants.leftIcon4;
        this.id8CacheLeftIcon5 = GSID8LauncherConstants.leftIcon5;
        initLeftIcon(this.mBinding.bmwId8SettingsMainLeftBar.llLeft3, this.mBinding.bmwId8SettingsMainLeftBar.ivLeft3, this.mBinding.bmwId8SettingsMainLeftBar.tvLeft3, this.id8CacheLeftIcon3);
        initLeftIcon(this.mBinding.bmwId8SettingsMainLeftBar.llLeft4, this.mBinding.bmwId8SettingsMainLeftBar.ivLeft4, this.mBinding.bmwId8SettingsMainLeftBar.tvLeft4, this.id8CacheLeftIcon4);
        initLeftIcon(this.mBinding.bmwId8SettingsMainLeftBar.llLeft5, this.mBinding.bmwId8SettingsMainLeftBar.ivLeft5, this.mBinding.bmwId8SettingsMainLeftBar.tvLeft5, this.id8CacheLeftIcon5);
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
            com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$13 r2 = new com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$13
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x007c:
            r0 = 2131233432(0x7f080a98, float:1.8083001E38)
            r1 = 2131558780(0x7f0d017c, float:1.8742885E38)
            com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$12 r2 = new com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$12
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x008b:
            r0 = 2131233428(0x7f080a94, float:1.8082993E38)
            r1 = 2131558787(0x7f0d0183, float:1.87429E38)
            com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$11 r2 = new com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$11
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x009a:
            r0 = 2131233429(0x7f080a95, float:1.8082995E38)
            r1 = 2131558795(0x7f0d018b, float:1.8742916E38)
            com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$10 r2 = new com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$10
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00a9:
            r0 = 2131233434(0x7f080a9a, float:1.8083005E38)
            r1 = 2131558821(0x7f0d01a5, float:1.8742969E38)
            com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$9 r2 = new com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$9
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00b8:
            r0 = 2131233077(0x7f080935, float:1.8082281E38)
            r1 = 2131558781(0x7f0d017d, float:1.8742888E38)
            com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$8 r2 = new com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$8
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00c7:
            r0 = 2131233078(0x7f080936, float:1.8082283E38)
            r1 = 2131558758(0x7f0d0166, float:1.874284E38)
            com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$7 r2 = new com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$7
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00d6:
            r0 = 2131233079(0x7f080937, float:1.8082285E38)
            r1 = 2131558796(0x7f0d018c, float:1.8742918E38)
            com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$6 r2 = new com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$6
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00e5:
            r0 = 2131233080(0x7f080938, float:1.8082287E38)
            r1 = 2131558782(0x7f0d017e, float:1.874289E38)
            com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$5 r2 = new com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity$5
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
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.settings.bmw_id8.activity.BmwId8GsSettingsMainActivity.initLeftIcon(android.widget.LinearLayout, android.widget.ImageView, android.widget.TextView, java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Log.i("BmwId8GsSettingsMainActivity", " onPause ");
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i("BmwId8GsSettingsMainActivity", " onStop ");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.i("BmwId8GsSettingsMainActivity", " onDestroy ");
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("BmwId8GsSettingsMainActivity", " dispatchKeyEvent ");
        return super.dispatchKeyEvent(event);
    }
}
