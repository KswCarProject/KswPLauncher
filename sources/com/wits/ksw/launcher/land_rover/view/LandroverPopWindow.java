package com.wits.ksw.launcher.land_rover.view;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.wits.ksw.R;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.WitsCommand;
import java.util.List;

public class LandroverPopWindow {
    private static final String LOG_TAG = "LandroverPopWindow";
    public static View backView;
    public static View btView;
    public static View dvrView;
    public static View gpsView;
    public static Boolean isShown = false;
    private static Context mContext = null;
    private static View mView = null;
    private static WindowManager mWindowManager = null;
    public static View menuView;
    public static View setupView;
    public static View videoView;
    /* access modifiers changed from: private */
    public static LandroverViewModel viewModel;

    public static void setViewModel(LandroverViewModel model) {
        if (viewModel == null) {
            viewModel = model;
        }
    }

    public static boolean isForeground(Context context, String className) {
        List<ActivityManager.RunningTaskInfo> list;
        if (context != null && !TextUtils.isEmpty(className) && (list = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1)) != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            Log.w(LOG_TAG, "ComponentName = " + cpn);
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void showPopupWindow(Context context) {
        if (isShown.booleanValue()) {
            Log.i(LOG_TAG, "return cause already shown");
            return;
        }
        isShown = true;
        Log.i(LOG_TAG, "showPopupWindow ");
        Context applicationContext = context.getApplicationContext();
        mContext = applicationContext;
        mWindowManager = (WindowManager) applicationContext.getSystemService("window");
        mView = setUpView(context);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = 2003;
        params.flags = 262184;
        params.format = -3;
        params.width = -1;
        params.height = -2;
        params.gravity = 81;
        mWindowManager.addView(mView, params);
        Log.i(LOG_TAG, "add view");
    }

    public static void hidePopupWindow() {
        Log.i(LOG_TAG, "hide " + isShown + ", " + mView);
        if (isShown.booleanValue() && mView != null) {
            Log.i(LOG_TAG, "hidePopupWindow");
            mWindowManager.removeView(mView);
            isShown = false;
        }
    }

    private static View setUpView(Context context) {
        Log.i(LOG_TAG, "setUp view");
        View view = LayoutInflater.from(context).inflate(R.layout.landrover_main_bottom_lay, (ViewGroup) null);
        View findViewById = view.findViewById(R.id.landrover_main_bottom_return_btn);
        backView = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.w(LandroverPopWindow.LOG_TAG, "back click viewModel =" + LandroverPopWindow.viewModel);
                if (LandroverPopWindow.viewModel != null) {
                    LandroverPopWindow.viewModel.backKeyClick();
                }
            }
        });
        View findViewById2 = view.findViewById(R.id.landrover_main_bottom_menu_btn);
        menuView = findViewById2;
        findViewById2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.w(LandroverPopWindow.LOG_TAG, "home click viewModel =" + LandroverPopWindow.viewModel);
                if (LandroverPopWindow.viewModel != null) {
                    LandroverPopWindow.viewModel.homeKeyClick();
                }
            }
        });
        view.findViewById(R.id.landrover_main_bottom_setup_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (LandroverPopWindow.viewModel != null) {
                    LandroverPopWindow.viewModel.openSettings((View) null);
                }
            }
        });
        view.findViewById(R.id.landrover_main_bottom_dvr_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (LandroverPopWindow.viewModel != null) {
                    LandroverPopWindow.viewModel.openDvr((View) null);
                }
            }
        });
        view.findViewById(R.id.landrover_main_bottom_gps_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (LandroverPopWindow.viewModel != null) {
                    LandroverPopWindow.viewModel.openNaviApp();
                }
            }
        });
        view.findViewById(R.id.landrover_main_bottom_bt_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (LandroverPopWindow.viewModel != null) {
                    LandroverPopWindow.viewModel.openBtApp();
                }
            }
        });
        view.findViewById(R.id.landrover_main_bottom_video_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (LandroverPopWindow.viewModel != null) {
                    LandroverPopWindow.viewModel.openVideo((View) null);
                }
            }
        });
        view.findViewById(R.id.landrover_main_bottom_air_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WitsCommand.sendMcuCommand(new McuStatus.KswMcuMsg(120, 8, 0));
            }
        });
        view.findViewById(R.id.landrover_main_bottom_radar_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WitsCommand.sendMcuCommand(new McuStatus.KswMcuMsg(120, 9, 0));
            }
        });
        view.findViewById(R.id.landrover_main_bottom_parkassist_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WitsCommand.sendMcuCommand(new McuStatus.KswMcuMsg(120, 16, 0));
            }
        });
        view.findViewById(R.id.landrover_main_bottom_off_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (LandroverPopWindow.viewModel != null) {
                    LandroverPopWindow.viewModel.screenOff();
                }
            }
        });
        return view;
    }
}
