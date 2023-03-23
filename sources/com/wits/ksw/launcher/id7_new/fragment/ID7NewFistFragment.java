package com.wits.ksw.launcher.id7_new.fragment;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.support.constraint.Constraints;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.id7_new.SavaUtils;
import com.wits.ksw.launcher.model.McuImpl;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.WitsCommand;
import java.util.HashMap;

public class ID7NewFistFragment extends RelativeLayout implements View.OnClickListener {
    private HashMap<Integer, ObjectAnimator> animatorMaps = new HashMap<>();
    protected ContentResolver contentResolver;
    private ImageView img_xczhiz;
    private Context m_con;
    private RelativeLayout rel_firtNav;
    private RelativeLayout rel_firtaps;
    private RelativeLayout rel_firtbt;
    private RelativeLayout rel_firtci;
    private RelativeLayout rel_firtxc;
    private TextView tv_btInfo;
    private TextView tv_xcInfo;
    private View view;

    public ID7NewFistFragment(Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.fragment_id7_new_fist, (ViewGroup) null);
        this.contentResolver = this.m_con.getContentResolver();
        init();
        this.view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.view);
    }

    private void init() {
        String oils;
        this.tv_btInfo = (TextView) this.view.findViewById(R.id.tv_btInfo);
        RelativeLayout relativeLayout = (RelativeLayout) this.view.findViewById(R.id.rel_firtNav);
        this.rel_firtNav = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.tv_xcInfo = (TextView) this.view.findViewById(R.id.tv_xcInfo);
        this.img_xczhiz = (ImageView) this.view.findViewById(R.id.img_xczhiz);
        RelativeLayout relativeLayout2 = (RelativeLayout) this.view.findViewById(R.id.rel_firtbt);
        this.rel_firtbt = relativeLayout2;
        relativeLayout2.setOnClickListener(this);
        RelativeLayout relativeLayout3 = (RelativeLayout) this.view.findViewById(R.id.rel_firtxc);
        this.rel_firtxc = relativeLayout3;
        relativeLayout3.setOnClickListener(this);
        RelativeLayout relativeLayout4 = (RelativeLayout) this.view.findViewById(R.id.rel_firtci);
        this.rel_firtci = relativeLayout4;
        relativeLayout4.setOnClickListener(this);
        RelativeLayout relativeLayout5 = (RelativeLayout) this.view.findViewById(R.id.rel_firtaps);
        this.rel_firtaps = relativeLayout5;
        relativeLayout5.setOnClickListener(this);
        try {
            setSpeedRotation(this.img_xczhiz, (int) McuImpl.getInstance().carInfo.turnSpeedAnge.get(), McuImpl.getInstance().carInfo.delay.get().intValue());
            oils = String.format(getResources().getString(R.string.oil_size), new Object[]{McuImpl.getInstance().carInfo.oilValue.get()});
        } catch (RuntimeException e) {
            setSpeedRotation(this.img_xczhiz, 0, 0);
            oils = String.format("0.0", new Object[0]);
        }
        this.tv_xcInfo.setText(oils);
        int page = Settings.System.getInt(getContext().getContentResolver(), SavaUtils.PAGE_INDEX, 0);
        if (page < 5) {
            initFouse(page);
        } else {
            initFouse(-1);
        }
        registerBtContentObserver();
    }

    public void setOils(CarInfo carInfo) {
        setSpeedRotation(this.img_xczhiz, (int) carInfo.turnSpeedAnge.get(), carInfo.delay.get().intValue());
        this.tv_xcInfo.setText(carInfo.oilValue.get());
    }

    public void setSpeedRotation(ImageView imageView, int rota, int delay) {
        ObjectAnimator objectAnimator = this.animatorMaps.get(Integer.valueOf(imageView.getId()));
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", new float[]{(float) rota});
            this.animatorMaps.put(Integer.valueOf(imageView.getId()), objectAnimator);
        }
        if (objectAnimator.isStarted()) {
            objectAnimator.cancel();
        }
        int duration = 300;
        if (delay != 0 && delay <= 300) {
            duration = delay - 10;
        }
        Log.i("lkt", "lkt time delay - " + duration);
        objectAnimator.setDuration((long) duration);
        objectAnimator.setFloatValues(new float[]{(float) rota});
        objectAnimator.start();
    }

    private void registerBtContentObserver() {
        this.contentResolver.registerContentObserver(Settings.System.getUriFor("ksw_bluetooth"), false, new ContentObserver(new Handler()) {
            public void onChange(boolean selfChange, Uri uri) {
                boolean z = false;
                int bluetooth = Settings.System.getInt(ID7NewFistFragment.this.contentResolver, "ksw_bluetooth", 0);
                ID7NewFistFragment iD7NewFistFragment = ID7NewFistFragment.this;
                if (bluetooth == 0) {
                    z = true;
                }
                iD7NewFistFragment.setPhoneState(z);
            }
        });
    }

    public void setPhoneState(boolean isOpen) {
        if (isOpen) {
            Context context = this.m_con;
            if (context != null) {
                this.tv_btInfo.setText(context.getString(R.string.gs_phone_unconnected_bt_mess));
                return;
            }
            return;
        }
        Context context2 = this.m_con;
        if (context2 != null) {
            this.tv_btInfo.setText(context2.getString(R.string.gs_phone_connected_bt_mess));
        }
    }

    public void initFouse(int index) {
        if (this.m_con != null) {
            updateFouse(index);
        }
    }

    public void updateFouse(int index) {
        Log.d("id7Fies", "come on updateFouse" + index);
        switch (index) {
            case -1:
                this.rel_firtci.setSelected(false);
                this.rel_firtxc.setSelected(false);
                this.rel_firtNav.setSelected(false);
                this.rel_firtbt.setSelected(false);
                this.rel_firtaps.setSelected(false);
                return;
            case 0:
                this.rel_firtci.setSelected(true);
                this.rel_firtxc.setSelected(false);
                this.rel_firtNav.setSelected(false);
                this.rel_firtbt.setSelected(false);
                this.rel_firtaps.setSelected(false);
                return;
            case 1:
                this.rel_firtci.setSelected(false);
                this.rel_firtxc.setSelected(true);
                this.rel_firtNav.setSelected(false);
                this.rel_firtbt.setSelected(false);
                this.rel_firtaps.setSelected(false);
                return;
            case 2:
                this.rel_firtci.setSelected(false);
                this.rel_firtxc.setSelected(false);
                this.rel_firtNav.setSelected(true);
                this.rel_firtbt.setSelected(false);
                this.rel_firtaps.setSelected(false);
                return;
            case 3:
                this.rel_firtci.setSelected(false);
                this.rel_firtxc.setSelected(false);
                this.rel_firtNav.setSelected(false);
                this.rel_firtbt.setSelected(true);
                this.rel_firtaps.setSelected(false);
                return;
            case 4:
                this.rel_firtci.setSelected(false);
                this.rel_firtxc.setSelected(false);
                this.rel_firtNav.setSelected(false);
                this.rel_firtbt.setSelected(false);
                this.rel_firtaps.setSelected(true);
                return;
            default:
                return;
        }
    }

    public void oncheckID(int id) {
        switch (id) {
            case 0:
                onSendCommand(1, WitsCommand.SystemCommand.CAR_MODE);
                Settings.System.putInt(this.m_con.getContentResolver(), SavaUtils.PAGE_INDEX, 0);
                updateFouse(0);
                return;
            case 1:
                openApp(new ComponentName(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.DashboardActivity"));
                Settings.System.putInt(this.m_con.getContentResolver(), SavaUtils.PAGE_INDEX, 1);
                updateFouse(1);
                return;
            case 2:
                openApp(getContext().getPackageManager().getLaunchIntentForPackage(Settings.System.getString(this.m_con.getContentResolver(), KeyConfig.NAVI_DEFUAL)));
                Settings.System.putInt(this.m_con.getContentResolver(), SavaUtils.PAGE_INDEX, 2);
                updateFouse(2);
                return;
            case 3:
                onSendCommand(1, WitsCommand.SystemCommand.OPEN_BT);
                Settings.System.putInt(this.m_con.getContentResolver(), SavaUtils.PAGE_INDEX, 3);
                updateFouse(3);
                return;
            case 4:
                openApp(new Intent("com.wits.ksw.ACTION_APPS"));
                Settings.System.putInt(this.m_con.getContentResolver(), SavaUtils.PAGE_INDEX, 4);
                updateFouse(4);
                try {
                    WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_firtNav /*2131297569*/:
                oncheckID(2);
                return;
            case R.id.rel_firtaps /*2131297570*/:
                oncheckID(4);
                return;
            case R.id.rel_firtbt /*2131297571*/:
                oncheckID(3);
                return;
            case R.id.rel_firtci /*2131297572*/:
                oncheckID(0);
                return;
            case R.id.rel_firtxc /*2131297577*/:
                oncheckID(1);
                return;
            default:
                return;
        }
    }

    public void setFouces() {
        this.rel_firtaps.requestFocus();
    }

    private void onSendCommand(int command, int subCommand) {
        Log.i(Constraints.TAG, "onSendCommand: command:" + command + " subCommand:" + subCommand);
        try {
            WitsCommand.sendCommand(command, subCommand, (String) null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(Constraints.TAG, "onSendCommand: " + e.getMessage());
        }
    }

    private void openApp(ComponentName component) {
        try {
            Intent intent = new Intent();
            intent.setComponent(component);
            this.m_con.startActivity(intent);
            Log.i(Constraints.TAG, "openApp: " + component.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getContext().getString(R.string.uninstall), 0).show();
        }
    }

    private void openApp(Intent intent) {
        try {
            this.m_con.startActivity(intent);
            Log.i(Constraints.TAG, "openApp: " + intent.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getContext().getString(R.string.uninstall), 0).show();
        }
    }
}
