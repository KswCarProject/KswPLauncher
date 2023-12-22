package com.wits.ksw.launcher.bmw_id8_ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.p004v7.app.AppCompatActivity;
import android.support.p004v7.app.AppCompatDelegate;
import android.support.p004v7.app.SkinAppCompatDelegateImpl;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8PempFragmentHelper;
import com.wits.ksw.launcher.bmw_id8_ui.listener.PempID8BarOnDragListener;
import com.wits.ksw.launcher.utils.KswUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ID8PempEditActivity extends AppCompatActivity {
    private static final String TAG = "ID8PempEditActivity";
    public static int mScreenWidth;
    private float autoScrollLeftPosition;
    private float autoScrollRightPosition;
    private int currentScrollX;
    private int currentScrollY;
    ImageView ivLeft1;
    ImageView ivLeft2;
    ImageView ivLeft3;
    ImageView ivLeft4;
    ImageView ivLeft5;
    private LinearLayout llContainer;
    LinearLayout llLeft1;
    LinearLayout llLeft2;
    LinearLayout llLeft3;
    LinearLayout llLeft4;
    LinearLayout llLeft5;
    private final int offsetX = 20;
    private HorizontalScrollView scrollView;
    TextView tvLeft1;
    TextView tvLeft2;
    TextView tvLeft3;
    TextView tvLeft4;
    TextView tvLeft5;

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KswUtils.setFull(getWindow());
        setContentView(C0899R.C0902layout.activity_pemp_id8_launcher_edit);
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(C0899R.C0901id.scrollView);
        this.scrollView = horizontalScrollView;
        horizontalScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempEditActivity.1
            @Override // android.view.View.OnScrollChangeListener
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                ID8PempEditActivity.this.currentScrollX = scrollX;
                ID8PempEditActivity.this.currentScrollY = scrollY;
            }
        });
        this.scrollView.setOnDragListener(new View.OnDragListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempEditActivity.2
            @Override // android.view.View.OnDragListener
            public boolean onDrag(View v, DragEvent event) {
                Log.w(ID8PempEditActivity.TAG, "scrollView onDrag: " + event.getX());
                return false;
            }
        });
        LinearLayout linearLayout = (LinearLayout) findViewById(C0899R.C0901id.ll_containe);
        this.llContainer = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.ID8PempEditActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ID8PempEditActivity.this.finish();
            }
        });
        this.llLeft1 = (LinearLayout) findViewById(C0899R.C0901id.ll_left_1);
        this.llLeft2 = (LinearLayout) findViewById(C0899R.C0901id.ll_left_2);
        this.llLeft3 = (LinearLayout) findViewById(C0899R.C0901id.ll_left_3);
        this.llLeft4 = (LinearLayout) findViewById(C0899R.C0901id.ll_left_4);
        this.llLeft5 = (LinearLayout) findViewById(C0899R.C0901id.ll_left_5);
        this.llLeft1.setBackground(null);
        this.llLeft2.setBackground(null);
        this.llLeft3.setBackground(null);
        this.llLeft4.setBackground(null);
        this.llLeft5.setBackground(null);
        LinearLayout llLeftBarContainer = (LinearLayout) findViewById(C0899R.C0901id.ll_left_bar_container);
        llLeftBarContainer.setClickable(false);
        llLeftBarContainer.setFocusable(false);
        this.ivLeft1 = (ImageView) findViewById(C0899R.C0901id.iv_left_1);
        this.ivLeft2 = (ImageView) findViewById(C0899R.C0901id.iv_left_2);
        this.ivLeft3 = (ImageView) findViewById(C0899R.C0901id.iv_left_3);
        this.ivLeft4 = (ImageView) findViewById(C0899R.C0901id.iv_left_4);
        this.ivLeft5 = (ImageView) findViewById(C0899R.C0901id.iv_left_5);
        this.tvLeft1 = (TextView) findViewById(C0899R.C0901id.tv_left_1);
        this.tvLeft2 = (TextView) findViewById(C0899R.C0901id.tv_left_2);
        this.tvLeft3 = (TextView) findViewById(C0899R.C0901id.tv_left_3);
        this.tvLeft4 = (TextView) findViewById(C0899R.C0901id.tv_left_4);
        this.tvLeft5 = (TextView) findViewById(C0899R.C0901id.tv_left_5);
        initLeftIcon(this.ivLeft3, this.tvLeft3, PempID8LauncherConstants.leftIcon3);
        initLeftIcon(this.ivLeft4, this.tvLeft4, PempID8LauncherConstants.leftIcon4);
        initLeftIcon(this.ivLeft5, this.tvLeft5, PempID8LauncherConstants.leftIcon5);
        ID8PempFragmentHelper.getInstance(this).locateFragmentPosition();
        PempID8BarOnDragListener barOnDragListener = new PempID8BarOnDragListener(this);
        this.llLeft3.setOnDragListener(barOnDragListener);
        this.llLeft4.setOnDragListener(barOnDragListener);
        this.llLeft5.setOnDragListener(barOnDragListener);
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int i = dm.widthPixels;
        mScreenWidth = i;
        this.autoScrollLeftPosition = i * 0.2f;
        this.autoScrollRightPosition = i * 0.9f;
        Log.w(TAG, "onCreate: mScreenWidth : " + mScreenWidth);
        Log.w(TAG, "onCreate: autoScrollLeftPosition : " + this.autoScrollLeftPosition);
        Log.w(TAG, "onCreate: autoScrollRightPosition : " + this.autoScrollRightPosition);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void initLeftIcon(ImageView iv, TextView tv, String name) {
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
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_navi;
                nameRes = C0899R.string.ksw_id8_abbr_tel_navigate;
                break;
            case 1:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_music;
                nameRes = C0899R.string.ksw_id8_music;
                break;
            case 2:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_car;
                nameRes = C0899R.string.ksw_id7_car;
                break;
            case 3:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_bt;
                nameRes = C0899R.string.ksw_id8_abbr_tel;
                break;
            case 4:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_weather;
                nameRes = C0899R.string.ksw_id8_weather;
                break;
            case 5:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_modus;
                nameRes = C0899R.string.ksw_id8_modus;
                break;
            case 6:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_dashboard;
                nameRes = C0899R.string.ksw_id8_dashboard;
                break;
            case 7:
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_set;
                nameRes = C0899R.string.ksw_id8_abbr_setting;
                break;
            case '\b':
                iconRes = C0899R.C0900drawable.pemp_id8_main_left_icon_video;
                nameRes = C0899R.string.ksw_id8_abbr_hd_video;
                break;
        }
        if (iconRes == -1) {
            return;
        }
        tv.setText(getString(nameRes));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), iconRes);
        iv.setImageBitmap(bitmap);
    }

    public void sort(String dragName, String releaseName) {
        List<String> nameList = PempID8LauncherConstants.nameList;
        if (dragName.equals(releaseName)) {
            return;
        }
        boolean remove = nameList.remove(dragName);
        if (!remove) {
            Toast.makeText(this, "\u6392\u5e8f\u5f02\u5e38\uff0c\u8bf7\u91cd\u8bd5\u3002occur error,please try again.", 0).show();
            return;
        }
        int releasePosition = -1;
        Iterator<String> it = nameList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String name = it.next();
            if (name.equals(releaseName)) {
                releasePosition = nameList.indexOf(releaseName);
                break;
            }
        }
        if (releasePosition == -1) {
            Toast.makeText(this, "\u6392\u5e8f\u5f02\u5e38\uff0c\u8bf7\u91cd\u8bd5\u3002occur error,please try again.", 0).show();
            return;
        }
        nameList.add(releasePosition, dragName);
        int tempX = this.currentScrollX;
        int tempY = this.currentScrollY;
        ID8PempFragmentHelper.getInstance(this).locateFragmentPosition();
        this.scrollView.scrollTo(tempX, tempY);
    }

    public void emptyClick(View view) {
        finish();
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        PempID8LauncherConstants.saveSystemCardSeq();
    }

    @Override // android.support.p004v7.app.AppCompatActivity
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.w(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        Log.w(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }

    public void checkAutoScroll(float fingerX) {
        if (this.autoScrollLeftPosition >= fingerX) {
            if (fingerX < 0.0f) {
                return;
            }
            int i = this.currentScrollX - 20;
            this.currentScrollX = i;
            this.scrollView.scrollTo(i, this.currentScrollY);
        } else if (fingerX < this.autoScrollRightPosition || fingerX > mScreenWidth) {
        } else {
            int i2 = this.currentScrollX + 20;
            this.currentScrollX = i2;
            this.scrollView.scrollTo(i2, this.currentScrollY);
        }
    }
}
