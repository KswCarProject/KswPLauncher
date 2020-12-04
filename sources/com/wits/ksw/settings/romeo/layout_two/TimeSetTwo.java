package com.wits.ksw.settings.romeo.layout_two;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class TimeSetTwo extends RelativeLayout implements RadioGroup.OnCheckedChangeListener {
    private Context context;
    private RadioGroup rdg_timeSy;
    private RadioGroup rdg_timeZhis;
    private RelativeLayout relate_timeSync;
    private RelativeLayout relate_timeZhis;
    /* access modifiers changed from: private */
    public ImageView romeo_list_r1;
    /* access modifiers changed from: private */
    public ImageView romeo_list_r2;
    private int timeSync;
    private int timeZhis;

    public TimeSetTwo(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.romeo_layout_set_time_two, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
        try {
            this.timeSync = PowerManagerApp.getSettingsInt(KeyConfig.TIME_SOURCE);
            this.timeZhis = PowerManagerApp.getSettingsInt(KeyConfig.TIME_FORMAT);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view) {
        this.relate_timeSync = (RelativeLayout) view.findViewById(R.id.relate_timeSync);
        this.relate_timeZhis = (RelativeLayout) view.findViewById(R.id.relate_timeZhis);
        this.rdg_timeSy = (RadioGroup) view.findViewById(R.id.rdg_timeSy);
        this.rdg_timeZhis = (RadioGroup) view.findViewById(R.id.rdg_timeZhis);
        this.romeo_list_r1 = (ImageView) view.findViewById(R.id.romeo_list_r1);
        this.romeo_list_r2 = (ImageView) view.findViewById(R.id.romeo_list_r2);
        switch (this.timeSync) {
            case 0:
                this.rdg_timeSy.check(R.id.rdb_sync2);
                break;
            case 1:
                this.rdg_timeSy.check(R.id.rdb_sync1);
                break;
        }
        switch (this.timeZhis) {
            case 0:
                this.rdg_timeZhis.check(R.id.rdb_zhis2);
                break;
            case 1:
                this.rdg_timeZhis.check(R.id.rdb_zhis1);
                break;
        }
        this.rdg_timeSy.setOnCheckedChangeListener(this);
        this.rdg_timeZhis.setOnCheckedChangeListener(this);
        for (int i = 0; i < this.rdg_timeSy.getChildCount(); i++) {
            final int finalI = i;
            this.rdg_timeSy.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        if (finalI == 0) {
                            TimeSetTwo.this.romeo_list_r1.setVisibility(0);
                        } else if (finalI == 1) {
                            TimeSetTwo.this.romeo_list_r2.setVisibility(0);
                        }
                    } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        if (finalI == 0) {
                            TimeSetTwo.this.romeo_list_r1.setVisibility(4);
                        } else if (finalI == 1) {
                            TimeSetTwo.this.romeo_list_r2.setVisibility(4);
                        }
                    }
                    return false;
                }
            });
            this.rdg_timeSy.getChildAt(i).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View view, boolean b) {
                    int i = 4;
                    if (finalI == 0) {
                        ImageView access$000 = TimeSetTwo.this.romeo_list_r1;
                        if (b) {
                            i = 0;
                        }
                        access$000.setVisibility(i);
                    } else if (finalI == 1) {
                        ImageView access$100 = TimeSetTwo.this.romeo_list_r2;
                        if (b) {
                            i = 0;
                        }
                        access$100.setVisibility(i);
                    }
                }
            });
        }
        for (int i2 = 0; i2 < this.rdg_timeZhis.getChildCount(); i2++) {
            final int finalI2 = i2;
            this.rdg_timeZhis.getChildAt(i2).setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        if (finalI2 == 0) {
                            TimeSetTwo.this.romeo_list_r1.setVisibility(0);
                        } else if (finalI2 == 1) {
                            TimeSetTwo.this.romeo_list_r2.setVisibility(0);
                        }
                    } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        if (finalI2 == 0) {
                            TimeSetTwo.this.romeo_list_r1.setVisibility(4);
                        } else if (finalI2 == 1) {
                            TimeSetTwo.this.romeo_list_r2.setVisibility(4);
                        }
                    }
                    return false;
                }
            });
            this.rdg_timeZhis.getChildAt(i2).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View view, boolean b) {
                    int i = 4;
                    if (finalI2 == 0) {
                        ImageView access$000 = TimeSetTwo.this.romeo_list_r1;
                        if (b) {
                            i = 0;
                        }
                        access$000.setVisibility(i);
                    } else if (finalI2 == 1) {
                        ImageView access$100 = TimeSetTwo.this.romeo_list_r2;
                        if (b) {
                            i = 0;
                        }
                        access$100.setVisibility(i);
                    }
                }
            });
        }
    }

    public void showLayout(int index) {
        switch (index) {
            case 0:
                this.relate_timeSync.setVisibility(0);
                this.relate_timeZhis.setVisibility(8);
                return;
            case 1:
                this.relate_timeSync.setVisibility(8);
                this.relate_timeZhis.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rdb_sync1:
                FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 1);
                return;
            case R.id.rdb_sync2:
                FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 0);
                return;
            case R.id.rdb_zhis1:
                FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 1);
                return;
            case R.id.rdb_zhis2:
                FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 0);
                return;
            default:
                return;
        }
    }
}
