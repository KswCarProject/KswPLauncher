package com.wits.ksw.launcher.bmw_id8_ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BmwId8ModusLayoutBinding;
import com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.settings.BaseSkinActivity;

public class ID8ModusActivity extends BaseSkinActivity {
    private final String TAG = "ID8ModusActivity";
    /* access modifiers changed from: private */
    public LauncherViewModel bmwId8ViewModel;
    private String id8CacheLeftIcon2;
    private String id8CacheLeftIcon3;
    private String id8CacheLeftIcon4;
    /* access modifiers changed from: private */
    public BmwId8ModusLayoutBinding mBinding;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ID8ModusActivity", " onCreate ");
        setFull();
        setStatusBarTranslucent();
        this.mBinding = (BmwId8ModusLayoutBinding) DataBindingUtil.setContentView(this, R.layout.activity_id8_modus_layout);
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of((FragmentActivity) this).get(LauncherViewModel.class);
        this.bmwId8ViewModel = launcherViewModel;
        launcherViewModel.setActivity(this);
        this.mBinding.setLauncherViewModel(this.bmwId8ViewModel);
        this.bmwId8ViewModel.initSkinData(new OnID8SkinChangeListener() {
            public void onSkinChangeLeftBar(int drawableId) {
                if (ID8ModusActivity.this.mBinding != null) {
                    ID8ModusActivity.this.mBinding.llLeftContainer.llLeft1.setBackground(ID8ModusActivity.this.getDrawable(drawableId));
                    ID8ModusActivity.this.mBinding.llLeftContainer.llLeft2.setBackground(ID8ModusActivity.this.getDrawable(drawableId));
                    ID8ModusActivity.this.mBinding.llLeftContainer.llLeft3.setBackground(ID8ModusActivity.this.getDrawable(drawableId));
                    ID8ModusActivity.this.mBinding.llLeftContainer.llLeft4.setBackground(ID8ModusActivity.this.getDrawable(drawableId));
                }
            }

            public void onSkinChangeCardBGSelector(String skinName) {
            }

            public void onSkinChangeMusicAlbum(int drawableId) {
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("ID8ModusActivity", " onNewIntent ");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.i("ID8ModusActivity", " onResume ");
        beforeRefreshLeftBarIcon();
        BmwId8ModusLayoutBinding bmwId8ModusLayoutBinding = this.mBinding;
        if (bmwId8ModusLayoutBinding != null) {
            bmwId8ModusLayoutBinding.llLeftContainer.llLeft1.setFocusedByDefault(true);
        }
    }

    private void beforeRefreshLeftBarIcon() {
        Log.w("ID8ModusActivity", "beforeRefreshLeftBarIcon: ");
        if (checkLeftIconHasChange()) {
            refreshLeftBarIcon();
        }
    }

    private boolean checkLeftIconHasChange() {
        return !ID8LauncherConstants.leftIcon2.equals(this.id8CacheLeftIcon2) || !ID8LauncherConstants.leftIcon3.equals(this.id8CacheLeftIcon3) || !ID8LauncherConstants.leftIcon4.equals(this.id8CacheLeftIcon4);
    }

    private void refreshLeftBarIcon() {
        this.id8CacheLeftIcon2 = ID8LauncherConstants.leftIcon2;
        this.id8CacheLeftIcon3 = ID8LauncherConstants.leftIcon3;
        this.id8CacheLeftIcon4 = ID8LauncherConstants.leftIcon4;
        initLeftIcon(this.mBinding.llLeftContainer.llLeft2, this.mBinding.llLeftContainer.ivLeft2, this.mBinding.llLeftContainer.tvLeft2, this.id8CacheLeftIcon2);
        initLeftIcon(this.mBinding.llLeftContainer.llLeft3, this.mBinding.llLeftContainer.ivLeft3, this.mBinding.llLeftContainer.tvLeft3, this.id8CacheLeftIcon3);
        initLeftIcon(this.mBinding.llLeftContainer.llLeft4, this.mBinding.llLeftContainer.ivLeft4, this.mBinding.llLeftContainer.tvLeft4, this.id8CacheLeftIcon4);
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
            com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$10 r2 = new com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$10
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x007c:
            r0 = 2131233432(0x7f080a98, float:1.8083001E38)
            r1 = 2131558780(0x7f0d017c, float:1.8742885E38)
            com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$9 r2 = new com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$9
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x008b:
            r0 = 2131233428(0x7f080a94, float:1.8082993E38)
            r1 = 2131558787(0x7f0d0183, float:1.87429E38)
            com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$8 r2 = new com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$8
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x009a:
            r0 = 2131233429(0x7f080a95, float:1.8082995E38)
            r1 = 2131558795(0x7f0d018b, float:1.8742916E38)
            com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$7 r2 = new com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$7
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00a9:
            r0 = 2131233434(0x7f080a9a, float:1.8083005E38)
            r1 = 2131558821(0x7f0d01a5, float:1.8742969E38)
            com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$6 r2 = new com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$6
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00b8:
            r0 = 2131233426(0x7f080a92, float:1.808299E38)
            r1 = 2131558781(0x7f0d017d, float:1.8742888E38)
            com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$5 r2 = new com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$5
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00c7:
            r0 = 2131233427(0x7f080a93, float:1.8082991E38)
            r1 = 2131558778(0x7f0d017a, float:1.8742881E38)
            com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$4 r2 = new com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$4
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00d6:
            r0 = 2131233430(0x7f080a96, float:1.8082997E38)
            r1 = 2131558796(0x7f0d018c, float:1.8742918E38)
            com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$3 r2 = new com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$3
            r2.<init>()
            r5.setOnClickListener(r2)
            goto L_0x00f4
        L_0x00e5:
            r0 = 2131233431(0x7f080a97, float:1.8083E38)
            r1 = 2131558782(0x7f0d017e, float:1.874289E38)
            com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$2 r2 = new com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity$2
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
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity.initLeftIcon(android.widget.LinearLayout, android.widget.ImageView, android.widget.TextView, java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Log.i("ID8ModusActivity", " onPause ");
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i("ID8ModusActivity", " onStop ");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.i("ID8ModusActivity", " onDestroy ");
    }

    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("ID8ModusActivity", " dispatchKeyEvent ");
        return super.dispatchKeyEvent(event);
    }
}
