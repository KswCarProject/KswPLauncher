package com.wits.ksw.launcher.bmw_id8_ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.SkinAppCompatDelegateImpl;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8FragmentHelper;
import com.wits.ksw.launcher.bmw_id8_ui.listener.BarOnDragListener;
import com.wits.ksw.launcher.utils.KswUtils;
import java.util.Iterator;
import java.util.List;

public class ID8EditActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static int mScreenWidth;
    private float autoScrollLeftPosition;
    private float autoScrollRightPosition;
    /* access modifiers changed from: private */
    public int currentScrollX;
    /* access modifiers changed from: private */
    public int currentScrollY;
    ImageView ivLeft1;
    ImageView ivLeft2;
    ImageView ivLeft3;
    ImageView ivLeft4;
    private LinearLayout llContainer;
    LinearLayout llLeft1;
    LinearLayout llLeft2;
    LinearLayout llLeft3;
    LinearLayout llLeft4;
    private int offsetX = 20;
    private HorizontalScrollView scrollView;
    TextView tvLeft1;
    TextView tvLeft2;
    TextView tvLeft3;
    TextView tvLeft4;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KswUtils.setFull(getWindow());
        setContentView((int) R.layout.activity_id8_launcher_edit);
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.scrollView);
        this.scrollView = horizontalScrollView;
        horizontalScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int unused = ID8EditActivity.this.currentScrollX = scrollX;
                int unused2 = ID8EditActivity.this.currentScrollY = scrollY;
            }
        });
        this.scrollView.setOnDragListener(new View.OnDragListener() {
            public boolean onDrag(View v, DragEvent event) {
                Log.w(ID8EditActivity.TAG, "scrollView onDrag: " + event.getX());
                return false;
            }
        });
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_container);
        this.llContainer = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ID8EditActivity.this.finish();
            }
        });
        this.llLeft1 = (LinearLayout) findViewById(R.id.ll_left_1);
        this.llLeft2 = (LinearLayout) findViewById(R.id.ll_left_2);
        this.llLeft3 = (LinearLayout) findViewById(R.id.ll_left_3);
        this.llLeft4 = (LinearLayout) findViewById(R.id.ll_left_4);
        this.llLeft1.setBackground((Drawable) null);
        this.llLeft2.setBackground((Drawable) null);
        this.llLeft3.setBackground((Drawable) null);
        this.llLeft4.setBackground((Drawable) null);
        LinearLayout llLeftBarContainer = (LinearLayout) findViewById(R.id.ll_left_bar_container);
        llLeftBarContainer.setClickable(false);
        llLeftBarContainer.setFocusable(false);
        this.ivLeft1 = (ImageView) findViewById(R.id.iv_left_1);
        this.ivLeft2 = (ImageView) findViewById(R.id.iv_left_2);
        this.ivLeft3 = (ImageView) findViewById(R.id.iv_left_3);
        this.ivLeft4 = (ImageView) findViewById(R.id.iv_left_4);
        this.tvLeft1 = (TextView) findViewById(R.id.tv_left_1);
        this.tvLeft2 = (TextView) findViewById(R.id.tv_left_2);
        this.tvLeft3 = (TextView) findViewById(R.id.tv_left_3);
        this.tvLeft4 = (TextView) findViewById(R.id.tv_left_4);
        initLeftIcon(this.ivLeft2, this.tvLeft2, ID8LauncherConstants.leftIcon2);
        initLeftIcon(this.ivLeft3, this.tvLeft3, ID8LauncherConstants.leftIcon3);
        initLeftIcon(this.ivLeft4, this.tvLeft4, ID8LauncherConstants.leftIcon4);
        ID8FragmentHelper.getInstance(this).locateFragmentPosition();
        BarOnDragListener barOnDragListener = new BarOnDragListener(this);
        this.llLeft2.setOnDragListener(barOnDragListener);
        this.llLeft3.setOnDragListener(barOnDragListener);
        this.llLeft4.setOnDragListener(barOnDragListener);
        int i = getResources().getDisplayMetrics().widthPixels;
        mScreenWidth = i;
        this.autoScrollLeftPosition = ((float) i) * 0.2f;
        this.autoScrollRightPosition = ((float) i) * 0.9f;
        Log.w(TAG, "onCreate: mScreenWidth : " + mScreenWidth);
        Log.w(TAG, "onCreate: autoScrollLeftPosition : " + this.autoScrollLeftPosition);
        Log.w(TAG, "onCreate: autoScrollRightPosition : " + this.autoScrollRightPosition);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initLeftIcon(android.widget.ImageView r5, android.widget.TextView r6, java.lang.String r7) {
        /*
            r4 = this;
            r0 = -1
            r1 = -1
            int r2 = r7.hashCode()
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
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 1
            goto L_0x0067
        L_0x0015:
            java.lang.String r2 = "DASHBOARD"
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 6
            goto L_0x0067
        L_0x001f:
            java.lang.String r2 = "CAR INFO"
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 3
            goto L_0x0067
        L_0x0029:
            java.lang.String r2 = "VIDEO"
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 8
            goto L_0x0067
        L_0x0034:
            java.lang.String r2 = "PHONE"
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 5
            goto L_0x0067
        L_0x003e:
            java.lang.String r2 = "MUSIC"
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 2
            goto L_0x0067
        L_0x0048:
            java.lang.String r2 = "MODUS"
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 4
            goto L_0x0067
        L_0x0052:
            java.lang.String r2 = "NAVIGATE"
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 0
            goto L_0x0067
        L_0x005c:
            java.lang.String r2 = "SETTING"
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L_0x000a
            r2 = 7
            goto L_0x0067
        L_0x0066:
            r2 = r3
        L_0x0067:
            switch(r2) {
                case 0: goto L_0x00a3;
                case 1: goto L_0x009c;
                case 2: goto L_0x0095;
                case 3: goto L_0x008e;
                case 4: goto L_0x0087;
                case 5: goto L_0x0080;
                case 6: goto L_0x0079;
                case 7: goto L_0x0072;
                case 8: goto L_0x006b;
                default: goto L_0x006a;
            }
        L_0x006a:
            goto L_0x00aa
        L_0x006b:
            r0 = 2131233433(0x7f080a99, float:1.8083003E38)
            r1 = 2131558779(0x7f0d017b, float:1.8742883E38)
            goto L_0x00aa
        L_0x0072:
            r0 = 2131233432(0x7f080a98, float:1.8083001E38)
            r1 = 2131558780(0x7f0d017c, float:1.8742885E38)
            goto L_0x00aa
        L_0x0079:
            r0 = 2131233428(0x7f080a94, float:1.8082993E38)
            r1 = 2131558787(0x7f0d0183, float:1.87429E38)
            goto L_0x00aa
        L_0x0080:
            r0 = 2131233426(0x7f080a92, float:1.808299E38)
            r1 = 2131558781(0x7f0d017d, float:1.8742888E38)
            goto L_0x00aa
        L_0x0087:
            r0 = 2131233429(0x7f080a95, float:1.8082995E38)
            r1 = 2131558795(0x7f0d018b, float:1.8742916E38)
            goto L_0x00aa
        L_0x008e:
            r0 = 2131233427(0x7f080a93, float:1.8082991E38)
            r1 = 2131558778(0x7f0d017a, float:1.8742881E38)
            goto L_0x00aa
        L_0x0095:
            r0 = 2131233430(0x7f080a96, float:1.8082997E38)
            r1 = 2131558796(0x7f0d018c, float:1.8742918E38)
            goto L_0x00aa
        L_0x009c:
            r0 = 2131233434(0x7f080a9a, float:1.8083005E38)
            r1 = 2131558821(0x7f0d01a5, float:1.8742969E38)
            goto L_0x00aa
        L_0x00a3:
            r0 = 2131233431(0x7f080a97, float:1.8083E38)
            r1 = 2131558782(0x7f0d017e, float:1.874289E38)
        L_0x00aa:
            if (r0 != r3) goto L_0x00ad
            return
        L_0x00ad:
            java.lang.String r2 = r4.getString(r1)
            r6.setText(r2)
            android.content.res.Resources r2 = r4.getResources()
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeResource(r2, r0)
            r5.setImageBitmap(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity.initLeftIcon(android.widget.ImageView, android.widget.TextView, java.lang.String):void");
    }

    public void sort(String dragName, String releaseName) {
        List<String> nameList = ID8LauncherConstants.nameList;
        if (!dragName.equals(releaseName)) {
            if (!nameList.remove(dragName)) {
                Toast.makeText(this, "排序异常，请重试。occur error,please try again.", 0).show();
                return;
            }
            int releasePosition = -1;
            Iterator<String> it = nameList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().equals(releaseName)) {
                        releasePosition = nameList.indexOf(releaseName);
                        break;
                    }
                } else {
                    break;
                }
            }
            if (releasePosition == -1) {
                Toast.makeText(this, "排序异常，请重试。occur error,please try again.", 0).show();
                return;
            }
            nameList.add(releasePosition, dragName);
            int tempX = this.currentScrollX;
            int tempY = this.currentScrollY;
            ID8FragmentHelper.getInstance(this).locateFragmentPosition();
            this.scrollView.scrollTo(tempX, tempY);
        }
    }

    public void emptyClick(View view) {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        ID8LauncherConstants.saveSystemCardSeq();
    }

    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.w(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.w(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }

    public void checkAutoScroll(float fingerX) {
        if (this.autoScrollLeftPosition >= fingerX) {
            if (fingerX >= 0.0f) {
                int i = this.currentScrollX - this.offsetX;
                this.currentScrollX = i;
                this.scrollView.scrollTo(i, this.currentScrollY);
            }
        } else if (fingerX >= this.autoScrollRightPosition && fingerX <= ((float) mScreenWidth)) {
            int i2 = this.currentScrollX + this.offsetX;
            this.currentScrollX = i2;
            this.scrollView.scrollTo(i2, this.currentScrollY);
        }
    }
}
