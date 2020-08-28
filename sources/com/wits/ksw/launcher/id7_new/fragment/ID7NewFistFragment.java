package com.wits.ksw.launcher.id7_new.fragment;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.databinding.BindingAdapter;
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
        this.tv_btInfo = (TextView) this.view.findViewById(R.id.tv_btInfo);
        this.rel_firtNav = (RelativeLayout) this.view.findViewById(R.id.rel_firtNav);
        this.rel_firtNav.setOnClickListener(this);
        this.tv_xcInfo = (TextView) this.view.findViewById(R.id.tv_xcInfo);
        this.img_xczhiz = (ImageView) this.view.findViewById(R.id.img_xczhiz);
        this.rel_firtbt = (RelativeLayout) this.view.findViewById(R.id.rel_firtbt);
        this.rel_firtbt.setOnClickListener(this);
        this.rel_firtxc = (RelativeLayout) this.view.findViewById(R.id.rel_firtxc);
        this.rel_firtxc.setOnClickListener(this);
        this.rel_firtci = (RelativeLayout) this.view.findViewById(R.id.rel_firtci);
        this.rel_firtci.setOnClickListener(this);
        this.rel_firtaps = (RelativeLayout) this.view.findViewById(R.id.rel_firtaps);
        this.rel_firtaps.setOnClickListener(this);
        setSpeedRotation(this.img_xczhiz, (int) McuImpl.getInstance().carInfo.turnSpeedAnge.get(), McuImpl.getInstance().carInfo.delay.get().intValue());
        String string = getResources().getString(R.string.oil_size);
        this.tv_xcInfo.setText(String.format(string, new Object[]{McuImpl.getInstance().carInfo.oilValue.get() + "L"}));
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
        String string = getResources().getString(R.string.oil_size);
        this.tv_xcInfo.setText(String.format(string, new Object[]{carInfo.oilValue.get() + "L"}));
    }

    @BindingAdapter({"setSpeedRotation"})
    public void setSpeedRotation(ImageView imageView, int rota, int delay) {
        ObjectAnimator objectAnimator = this.animatorMaps.get(Integer.valueOf(imageView.getId()));
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", new float[]{(float) rota});
            this.animatorMaps.put(Integer.valueOf(imageView.getId()), objectAnimator);
        }
        if (objectAnimator.isStarted()) {
            objectAnimator.cancel();
        }
        int duration = WitsCommand.SystemCommand.EXPORT_CONFIG;
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
            if (this.m_con != null) {
                this.tv_btInfo.setText(this.m_con.getString(R.string.gs_phone_unconnected_bt_mess));
            }
        } else if (this.m_con != null) {
            this.tv_btInfo.setText(this.m_con.getString(R.string.gs_phone_connected_bt_mess));
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
        int id = v.getId();
        if (id != R.id.rel_firtxc) {
            switch (id) {
                case R.id.rel_firtNav:
                    oncheckID(2);
                    return;
                case R.id.rel_firtaps:
                    oncheckID(4);
                    return;
                case R.id.rel_firtbt:
                    oncheckID(3);
                    return;
                case R.id.rel_firtci:
                    oncheckID(0);
                    return;
                default:
                    return;
            }
        } else {
            oncheckID(1);
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
