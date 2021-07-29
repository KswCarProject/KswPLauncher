package com.wits.ksw.launcher.id7_new;

import android.arch.lifecycle.Observer;
import android.content.ContentResolver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
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

public class Id7NewActivity extends BaseActivity {
    private TabFragmentPagerAdapter adapter;
    protected ContentResolver contentResolver;
    int endIndex = -1;
    /* access modifiers changed from: private */
    public ID7NewFistFragment id7NewFistFragment;
    /* access modifiers changed from: private */
    public ID7NewTwoFragment id7NewTwoFragment;
    /* access modifiers changed from: private */
    public ImageView img_IndexLeft;
    /* access modifiers changed from: private */
    public ImageView img_IndexRight;
    Handler uiHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (Id7NewActivity.this.contentResolver == null) {
                Id7NewActivity id7NewActivity = Id7NewActivity.this;
                id7NewActivity.contentResolver = id7NewActivity.getContentResolver();
            }
            boolean z = false;
            int bluetooth = Settings.System.getInt(Id7NewActivity.this.contentResolver, "ksw_bluetooth", 0);
            ID7NewFistFragment access$000 = Id7NewActivity.this.id7NewFistFragment;
            if (bluetooth == 0) {
                z = true;
            }
            access$000.setPhoneState(z);
        }
    };
    private List<RelativeLayout> views;
    /* access modifiers changed from: private */
    public MySeepViewPage vpIndex;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_id7_new_launch);
        initView();
        MediaImpl.getInstance().initData();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.uiHandler.sendEmptyMessageDelayed(0, 1000);
    }

    private void updateViewpage() {
        if (Settings.System.getInt(getContentResolver(), SavaUtils.PAGE_INDEX, 0) < 5) {
            this.vpIndex.setCurrentItem(0);
            this.img_IndexLeft.setVisibility(8);
            this.img_IndexRight.setVisibility(0);
            return;
        }
        this.vpIndex.setCurrentItem(1);
        this.img_IndexLeft.setVisibility(0);
        this.img_IndexRight.setVisibility(8);
    }

    /* access modifiers changed from: private */
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

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() != 1) {
            return true;
        }
        switch (event.getKeyCode()) {
            case 19:
                Log.d("ddc", "come on up");
                int page = Settings.System.getInt(getContentResolver(), SavaUtils.PAGE_INDEX, 0) - 1;
                if (page < 0) {
                    page = 0;
                }
                Log.d("ddc", "up page:" + page);
                Settings.System.putInt(getContentResolver(), SavaUtils.PAGE_INDEX, page);
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
                if (event.getKeyCode() == 3) {
                    return true;
                }
                return super.dispatchKeyEvent(event);
        }
    }

    private void initView() {
        this.views = new ArrayList();
        this.id7NewFistFragment = new ID7NewFistFragment(this);
        this.id7NewTwoFragment = new ID7NewTwoFragment(this);
        this.views.add(this.id7NewFistFragment);
        this.views.add(this.id7NewTwoFragment);
        this.vpIndex = (MySeepViewPage) findViewById(R.id.vpIndex);
        TabFragmentPagerAdapter tabFragmentPagerAdapter = new TabFragmentPagerAdapter(this.views);
        this.adapter = tabFragmentPagerAdapter;
        this.vpIndex.setAdapter(tabFragmentPagerAdapter);
        this.img_IndexLeft = (ImageView) findViewById(R.id.img_IndexLeft);
        this.img_IndexRight = (ImageView) findViewById(R.id.img_IndexRight);
        this.img_IndexLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Id7NewActivity.this.vpIndex.setCurrentItem(0);
            }
        });
        this.img_IndexRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Id7NewActivity.this.vpIndex.setCurrentItem(1);
            }
        });
        updateViewpage();
        this.vpIndex.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

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

            public void onPageScrollStateChanged(int i) {
            }
        });
        McuImpl.getInstance().carInfoMutableLiveData.observe(this, new Observer<CarInfo>() {
            public void onChanged(CarInfo carInfo) {
                Id7NewActivity.this.id7NewFistFragment.setOils(carInfo);
            }
        });
        MediaImpl.getInstance().mediaInfoMutableLiveData.observe(this, new Observer<MediaInfo>() {
            public void onChanged(MediaInfo mediaInfo) {
                Log.d("newID7", "ddd:" + mediaInfo.musicName.get());
                Id7NewActivity.this.id7NewTwoFragment.setMediaInfo(mediaInfo);
            }
        });
    }

    public void onBackPressed() {
    }
}
