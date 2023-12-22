package com.wits.ksw.launcher.id7_new;

import android.arch.lifecycle.Observer;
import android.content.ContentResolver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.p001v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.id7_new.adapter.TabFragmentPagerAdapter;
import com.wits.ksw.launcher.id7_new.fragment.ID7NewFistFragment;
import com.wits.ksw.launcher.id7_new.fragment.ID7NewTwoFragment;
import com.wits.ksw.launcher.id7_new.view.MySeepViewPage;
import com.wits.ksw.launcher.model.McuImpl;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.settings.BaseActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class Id7NewActivity extends BaseActivity {
    private TabFragmentPagerAdapter adapter;
    protected ContentResolver contentResolver;
    private ID7NewFistFragment id7NewFistFragment;
    private ID7NewTwoFragment id7NewTwoFragment;
    private ImageView img_IndexLeft;
    private ImageView img_IndexRight;
    private List<RelativeLayout> views;
    private MySeepViewPage vpIndex;
    Handler uiHandler = new Handler() { // from class: com.wits.ksw.launcher.id7_new.Id7NewActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (Id7NewActivity.this.contentResolver == null) {
                Id7NewActivity id7NewActivity = Id7NewActivity.this;
                id7NewActivity.contentResolver = id7NewActivity.getContentResolver();
            }
            int bluetooth = Settings.System.getInt(Id7NewActivity.this.contentResolver, "ksw_bluetooth", 0);
            Id7NewActivity.this.id7NewFistFragment.setPhoneState(bluetooth == 0);
        }
    };
    int endIndex = -1;

    @Override // com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0899R.C0902layout.activity_id7_new_launch);
        initView();
        MediaImpl.getInstance().initData();
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.uiHandler.sendEmptyMessageDelayed(0, 1000L);
    }

    private void updateViewpage() {
        int page = Settings.System.getInt(getContentResolver(), SavaUtils.PAGE_INDEX, 0);
        if (page < 5) {
            this.vpIndex.setCurrentItem(0);
            this.img_IndexLeft.setVisibility(8);
            this.img_IndexRight.setVisibility(0);
            return;
        }
        this.vpIndex.setCurrentItem(1);
        this.img_IndexLeft.setVisibility(0);
        this.img_IndexRight.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFragment() {
        int page = Settings.System.getInt(getContentResolver(), SavaUtils.PAGE_INDEX, 0);
        if (page < 5) {
            this.vpIndex.setCurrentItem(0);
            this.id7NewFistFragment.initFouse(page);
            this.id7NewTwoFragment.initFouse(-1);
            this.img_IndexLeft.setVisibility(8);
            this.img_IndexRight.setVisibility(0);
            return;
        }
        this.vpIndex.setCurrentItem(1);
        this.id7NewTwoFragment.initFouse(page);
        this.id7NewFistFragment.initFouse(-1);
        this.img_IndexLeft.setVisibility(0);
        this.img_IndexRight.setVisibility(8);
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == 1) {
            switch (event.getKeyCode()) {
                case 19:
                    Log.d("ddc", "come on up");
                    int page = Settings.System.getInt(getContentResolver(), SavaUtils.PAGE_INDEX, 0);
                    int page2 = page - 1;
                    if (page2 < 0) {
                        page2 = 0;
                    }
                    Log.d("ddc", "up page:" + page2);
                    Settings.System.putInt(getContentResolver(), SavaUtils.PAGE_INDEX, page2);
                    updateFragment();
                    return true;
                case 20:
                    Log.d("ddc", "come on down");
                    int pagep = Settings.System.getInt(getContentResolver(), SavaUtils.PAGE_INDEX, 0) + 1;
                    if (pagep > 9) {
                        pagep = 9;
                    }
                    Settings.System.putInt(getContentResolver(), SavaUtils.PAGE_INDEX, pagep);
                    updateFragment();
                    Log.d("ddc", "up page:" + pagep);
                    return true;
                case 66:
                    int pageOk = Settings.System.getInt(getContentResolver(), SavaUtils.PAGE_INDEX, 0);
                    Log.d("ddc", "check ok index:" + pageOk);
                    if (pageOk < 5) {
                        this.id7NewFistFragment.oncheckID(pageOk);
                    } else {
                        this.id7NewTwoFragment.oncheckID(pageOk);
                    }
                    return true;
                default:
                    int page3 = event.getKeyCode();
                    if (page3 == 3) {
                        return true;
                    }
                    return super.dispatchKeyEvent(event);
            }
        }
        return true;
    }

    private void initView() {
        this.views = new ArrayList();
        this.id7NewFistFragment = new ID7NewFistFragment(this);
        this.id7NewTwoFragment = new ID7NewTwoFragment(this);
        this.views.add(this.id7NewFistFragment);
        this.views.add(this.id7NewTwoFragment);
        this.vpIndex = (MySeepViewPage) findViewById(C0899R.C0901id.vpIndex);
        TabFragmentPagerAdapter tabFragmentPagerAdapter = new TabFragmentPagerAdapter(this.views);
        this.adapter = tabFragmentPagerAdapter;
        this.vpIndex.setAdapter(tabFragmentPagerAdapter);
        this.img_IndexLeft = (ImageView) findViewById(C0899R.C0901id.img_IndexLeft);
        this.img_IndexRight = (ImageView) findViewById(C0899R.C0901id.img_IndexRight);
        this.img_IndexLeft.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.id7_new.Id7NewActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Id7NewActivity.this.vpIndex.setCurrentItem(0);
            }
        });
        this.img_IndexRight.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.id7_new.Id7NewActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Id7NewActivity.this.vpIndex.setCurrentItem(1);
            }
        });
        updateViewpage();
        this.vpIndex.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.launcher.id7_new.Id7NewActivity.4
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        Id7NewActivity.this.img_IndexLeft.setVisibility(8);
                        Id7NewActivity.this.img_IndexRight.setVisibility(0);
                        if (Id7NewActivity.this.endIndex > i) {
                            Settings.System.putInt(Id7NewActivity.this.getContentResolver(), SavaUtils.PAGE_INDEX, 4);
                            Id7NewActivity.this.updateFragment();
                            break;
                        }
                        break;
                    case 1:
                        Id7NewActivity.this.img_IndexLeft.setVisibility(0);
                        Id7NewActivity.this.img_IndexRight.setVisibility(8);
                        Settings.System.putInt(Id7NewActivity.this.getContentResolver(), SavaUtils.PAGE_INDEX, 5);
                        Id7NewActivity.this.updateFragment();
                        break;
                }
                Id7NewActivity.this.endIndex = i;
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
        McuImpl.getInstance().carInfoMutableLiveData.observe(this, new Observer<CarInfo>() { // from class: com.wits.ksw.launcher.id7_new.Id7NewActivity.5
            @Override // android.arch.lifecycle.Observer
            public void onChanged(CarInfo carInfo) {
                Id7NewActivity.this.id7NewFistFragment.setOils(carInfo);
            }
        });
        MediaImpl.getInstance().mediaInfoMutableLiveData.observe(this, new Observer<MediaInfo>() { // from class: com.wits.ksw.launcher.id7_new.Id7NewActivity.6
            @Override // android.arch.lifecycle.Observer
            public void onChanged(MediaInfo mediaInfo) {
                Log.d("newID7", "ddd:" + mediaInfo.musicName.get());
                Id7NewActivity.this.id7NewTwoFragment.setMediaInfo(mediaInfo);
            }
        });
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
    }
}
