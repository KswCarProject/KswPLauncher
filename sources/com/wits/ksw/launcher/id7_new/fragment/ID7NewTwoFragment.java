package com.wits.ksw.launcher.id7_new.fragment;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.constraint.Constraints;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.id7_new.SavaUtils;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.WitsCommand;

public class ID7NewTwoFragment extends RelativeLayout implements View.OnClickListener {
    protected ContentResolver contentResolver = this.m_con.getContentResolver();
    private Context m_con;
    private RelativeLayout rel_firtMs;
    private RelativeLayout rel_firtfm;
    private RelativeLayout rel_firtie;
    private RelativeLayout rel_firtset;
    private RelativeLayout rel_firtvd;
    private TextView tv_msInfo;
    private View view;

    public ID7NewTwoFragment(Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.fragment_id7_new_two, (ViewGroup) null);
        init();
        this.view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.view);
    }

    private void init() {
        RelativeLayout relativeLayout = (RelativeLayout) this.view.findViewById(R.id.rel_firtfm);
        this.rel_firtfm = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.tv_msInfo = (TextView) this.view.findViewById(R.id.tv_msInfo);
        RelativeLayout relativeLayout2 = (RelativeLayout) this.view.findViewById(R.id.rel_firtMs);
        this.rel_firtMs = relativeLayout2;
        relativeLayout2.setOnClickListener(this);
        RelativeLayout relativeLayout3 = (RelativeLayout) this.view.findViewById(R.id.rel_firtvd);
        this.rel_firtvd = relativeLayout3;
        relativeLayout3.setOnClickListener(this);
        RelativeLayout relativeLayout4 = (RelativeLayout) this.view.findViewById(R.id.rel_firtie);
        this.rel_firtie = relativeLayout4;
        relativeLayout4.setOnClickListener(this);
        RelativeLayout relativeLayout5 = (RelativeLayout) this.view.findViewById(R.id.rel_firtset);
        this.rel_firtset = relativeLayout5;
        relativeLayout5.setOnClickListener(this);
        try {
            if (TextUtils.isEmpty(MediaImpl.getInstance().getMediaInfo().musicName.get())) {
                Context context = this.m_con;
                if (context != null) {
                    this.tv_msInfo.setText(context.getString(R.string.unkonw));
                }
            } else {
                this.tv_msInfo.setText(MediaImpl.getInstance().getMediaInfo().musicName.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int page = Settings.System.getInt(getContext().getContentResolver(), SavaUtils.PAGE_INDEX, 0);
        if (page > 4) {
            initFouse(page);
        } else {
            initFouse(-1);
        }
    }

    public void setMediaInfo(MediaInfo mediaInfo) {
        if (TextUtils.isEmpty(mediaInfo.musicName.get())) {
            Context context = this.m_con;
            if (context != null) {
                this.tv_msInfo.setText(context.getString(R.string.unkonw));
                return;
            }
            return;
        }
        this.tv_msInfo.setText(mediaInfo.musicName.get());
    }

    public void initFouse(int inext) {
        if (this.m_con != null) {
            updateFouce(inext);
        }
    }

    public void updateFouce(int index) {
        switch (index) {
            case -1:
                this.rel_firtMs.setSelected(false);
                this.rel_firtvd.setSelected(false);
                this.rel_firtie.setSelected(false);
                this.rel_firtfm.setSelected(false);
                this.rel_firtset.setSelected(false);
                return;
            case 5:
                this.rel_firtMs.setSelected(true);
                this.rel_firtvd.setSelected(false);
                this.rel_firtie.setSelected(false);
                this.rel_firtfm.setSelected(false);
                this.rel_firtset.setSelected(false);
                return;
            case 6:
                this.rel_firtMs.setSelected(false);
                this.rel_firtvd.setSelected(true);
                this.rel_firtie.setSelected(false);
                this.rel_firtfm.setSelected(false);
                this.rel_firtset.setSelected(false);
                return;
            case 7:
                this.rel_firtMs.setSelected(false);
                this.rel_firtvd.setSelected(false);
                this.rel_firtie.setSelected(true);
                this.rel_firtfm.setSelected(false);
                this.rel_firtset.setSelected(false);
                return;
            case 8:
                this.rel_firtMs.setSelected(false);
                this.rel_firtvd.setSelected(false);
                this.rel_firtie.setSelected(false);
                this.rel_firtfm.setSelected(true);
                this.rel_firtset.setSelected(false);
                return;
            case 9:
                this.rel_firtMs.setSelected(false);
                this.rel_firtvd.setSelected(false);
                this.rel_firtie.setSelected(false);
                this.rel_firtfm.setSelected(false);
                this.rel_firtset.setSelected(true);
                return;
            default:
                return;
        }
    }

    public void oncheckID(int id) {
        switch (id) {
            case 5:
                openMusicMulti();
                Settings.System.putInt(this.m_con.getContentResolver(), SavaUtils.PAGE_INDEX, 5);
                updateFouce(5);
                return;
            case 6:
                Log.d("liuhaoNew", "111111111111111111111");
                openVideoMulti();
                Settings.System.putInt(this.m_con.getContentResolver(), SavaUtils.PAGE_INDEX, 6);
                updateFouce(6);
                return;
            case 7:
                openApp(new ComponentName("com.android.chrome", LauncherViewModel.CLS_CHROME));
                Settings.System.putInt(this.m_con.getContentResolver(), SavaUtils.PAGE_INDEX, 7);
                updateFouce(7);
                try {
                    WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 8:
                openApp(getContext().getPackageManager().getLaunchIntentForPackage("com.estrongs.android.pop"));
                Settings.System.putInt(this.m_con.getContentResolver(), SavaUtils.PAGE_INDEX, 8);
                updateFouce(8);
                return;
            case 9:
                openApp(new ComponentName(BuildConfig.APPLICATION_ID, "com.wits.ksw.settings.SettingsActivity"));
                Settings.System.putInt(this.m_con.getContentResolver(), SavaUtils.PAGE_INDEX, 9);
                updateFouce(9);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_firtMs:
                oncheckID(5);
                return;
            case R.id.rel_firtfm:
                oncheckID(8);
                return;
            case R.id.rel_firtie:
                oncheckID(7);
                return;
            case R.id.rel_firtset:
                oncheckID(9);
                return;
            case R.id.rel_firtvd:
                oncheckID(6);
                return;
            default:
                return;
        }
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

    public void openMusicMulti() {
        String pkg = Settings.System.getString(this.m_con.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_PKG);
        String cls = Settings.System.getString(this.m_con.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS);
        if (TextUtils.isEmpty(pkg) || TextUtils.isEmpty(cls) || KeyConfig.CLS_LOCAL_MUSIC.equals(cls)) {
            openMusic();
        } else if (KeyConfig.CLS_LOCAL_VIDEO.equals(cls)) {
            openApp(new Intent("com.wits.media.VIDEO"));
        } else {
            openAppByCls(new ComponentName(pkg, cls));
        }
    }

    public void openVideoMulti() {
        Log.d("openVideoMulti", "000000000000000000");
        String pkg = Settings.System.getString(this.m_con.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_PKG);
        String cls = Settings.System.getString(this.m_con.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_CLS);
        if (TextUtils.isEmpty(pkg) || TextUtils.isEmpty(cls) || KeyConfig.CLS_LOCAL_VIDEO.equals(cls)) {
            openApp(new Intent("com.wits.media.VIDEO"));
        } else if (KeyConfig.CLS_LOCAL_MUSIC.equals(cls)) {
            openMusic();
        } else {
            openAppByCls(new ComponentName(pkg, cls));
        }
    }

    public void openAppByCls(ComponentName component) {
        try {
            Log.i("Constraints openAppByCls ", "11111111111111111 openApp: " + component.toString());
            Intent intent = new Intent();
            intent.setComponent(component);
            intent.setFlags(268435456);
            this.m_con.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Context context = this.m_con;
            Toast.makeText(context, context.getString(R.string.uninstall), 0).show();
        }
    }

    public void openMusic() {
        String pkg = Settings.System.getString(this.contentResolver, "defPlayApp");
        Log.i(Constraints.TAG, "oncheckID openMusic: pkg=" + pkg);
        if (TextUtils.isEmpty(pkg)) {
            pkg = "com.wits.media.MUSIC";
        }
        if (pkg.equals("com.wits.ksw.media")) {
            openApp(new Intent("com.wits.media.MUSIC"));
            return;
        }
        Intent intent = this.m_con.getPackageManager().getLaunchIntentForPackage(pkg);
        Log.d(Constraints.TAG, "oncheckID   intent = " + intent);
        if (intent == null) {
            openApp(new Intent("com.wits.media.MUSIC"));
            return;
        }
        openApp(intent);
        try {
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
