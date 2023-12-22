package com.wits.ksw.settings.utlis_view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.id7.bean.DevBean;
import com.wits.ksw.settings.view_adapter.ListChoseAdapter;
import com.wits.pms.ksw.OnMcuUpdateProgressListener;
import com.wits.pms.statuscontrol.McuUpdater;
import com.wits.pms.statuscontrol.WitsCommand;
import java.io.File;
import java.util.List;

/* loaded from: classes10.dex */
public class DialogViews extends Dialog {
    private Handler dialogHandler;
    private int errorFlag;
    private String fileName;
    private String logoPath;
    private String logoZipName;

    /* renamed from: lp */
    private WindowManager.LayoutParams f243lp;
    private Context m_con;
    private ProgressBar mcuProgBar;
    private TextView mcuTvPro;
    private Boolean mcu_start;
    public int size;
    private TextView tv_prossToast;
    private String updateDirPath;
    private String updateFilePath;
    private Window window;

    public DialogViews(Context context) {
        super(context);
        this.updateDirPath = "";
        this.updateFilePath = "";
        this.logoPath = "";
        this.logoZipName = "mylogo.zip";
        this.errorFlag = -1;
        this.size = 0;
        this.dialogHandler = new Handler() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.12
            Intent intent_recv = new Intent("com.ksw.mcu.recv");

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        DialogViews.this.mcuProgBar.setProgress(DialogViews.this.size);
                        DialogViews.this.mcuTvPro.setText(DialogViews.this.size + "%");
                        return;
                    case 1:
                        if (DialogViews.this.size < 0.5d) {
                            Log.d("FactoryMcuUpdate", "dialogHandler:handleMessage size=" + DialogViews.this.size);
                            ToastUtils.showToastShort(DialogViews.this.m_con, DialogViews.this.m_con.getString(C0899R.string.dialog_update7));
                            DialogViews.this.dismiss();
                            return;
                        }
                        return;
                    case 2:
                        DialogViews.this.dismiss();
                        return;
                    case 3:
                        DialogViews.this.dialogHandler.removeMessages(3);
                        if (DialogViews.this.errorFlag == -1) {
                            this.intent_recv.putExtra("action", "ready2update");
                            this.intent_recv.putExtra("value", "NO");
                            DialogViews.this.m_con.sendBroadcast(this.intent_recv);
                            DialogViews.this.tv_prossToast.setText(DialogViews.this.m_con.getString(C0899R.string.app_not_update));
                        } else {
                            this.intent_recv.putExtra("action", "update.end");
                            this.intent_recv.putExtra("value", "FAIL");
                            DialogViews.this.m_con.sendBroadcast(this.intent_recv);
                            DialogViews.this.tv_prossToast.setText(DialogViews.this.m_con.getString(C0899R.string.mcu_file_corrupted));
                        }
                        DialogViews.this.dialogHandler.sendEmptyMessageDelayed(2, 2000L);
                        return;
                    case 4:
                        if (UiThemeUtils.isUI_GS_ID8(DialogViews.this.m_con)) {
                            Settings.System.putInt(DialogViews.this.m_con.getContentResolver(), "memory_mode_for_freedom", 1);
                        }
                        System.exit(0);
                        return;
                    default:
                        return;
                }
            }
        };
        this.m_con = context;
        Window window = getWindow();
        this.window = window;
        this.f243lp = window.getAttributes();
        this.mcu_start = true;
    }

    public void qrCode(Bitmap bitmap) {
        Log.d("qrCode", "show qrCode");
        View view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.dialog_qrcode, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        view.findViewById(C0899R.C0901id.qr_root).setBackground(new BitmapDrawable((Resources) null, bitmap));
        this.f243lp.x = 10;
        this.f243lp.y = 10;
        this.f243lp.width = UtilsInfo.dip2px(this.m_con, 300.0f);
        this.f243lp.height = UtilsInfo.dip2px(this.m_con, 300.0f);
        this.window.setAttributes(this.f243lp);
        show();
    }

    public void isQuestView(String msgToast, final Handler handler) {
        View view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.dialog_file_list, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        TextView dlg_msg = (TextView) findViewById(C0899R.C0901id.dlg_msg);
        TextView dlg_ok = (TextView) findViewById(C0899R.C0901id.dlg_ok);
        TextView dlg_cancel = (TextView) findViewById(C0899R.C0901id.dlg_cancel);
        dlg_msg.setText(msgToast);
        dlg_ok.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                handler.sendEmptyMessage(1);
                DialogViews.this.dismiss();
            }
        });
        dlg_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DialogViews.this.dismiss();
            }
        });
        this.f243lp.x = 10;
        this.f243lp.y = 10;
        this.f243lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.f243lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.f243lp);
        show();
    }

    public void isUpdateLogo(String msgToast, final String path) {
        Log.d("UiSelect", "updateLogo path : " + path);
        View view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.dialog_file_list, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        TextView dlg_msg = (TextView) findViewById(C0899R.C0901id.dlg_msg);
        TextView dlg_ok = (TextView) findViewById(C0899R.C0901id.dlg_ok);
        TextView dlg_cancel = (TextView) findViewById(C0899R.C0901id.dlg_cancel);
        dlg_msg.setText(msgToast);
        dlg_ok.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                WitsCommand.sendCommand(1, 124, path);
                DialogViews.this.dismiss();
                ToastUtils.showToastShort(DialogViews.this.m_con, DialogViews.this.m_con.getResources().getString(C0899R.string.string_set_logo_over));
            }
        });
        dlg_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DialogViews.this.dismiss();
            }
        });
        this.f243lp.x = 10;
        this.f243lp.y = 10;
        this.f243lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.f243lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.f243lp);
        show();
    }

    public void isSelecUi(String msg, final Handler handler) {
        View view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.dialog_select_ui, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        TextView dlg_ok = (TextView) findViewById(C0899R.C0901id.dlg_ok);
        TextView upText_msg = (TextView) findViewById(C0899R.C0901id.upText_msg);
        TextView dlg_cancel = (TextView) findViewById(C0899R.C0901id.dlg_cancel);
        upText_msg.setText(msg);
        dlg_ok.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                handler.sendEmptyMessage(0);
            }
        });
        dlg_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DialogViews.this.dismiss();
            }
        });
        this.f243lp.x = 10;
        this.f243lp.y = 10;
        this.f243lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.f243lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.f243lp);
        show();
    }

    public void showUpdateIng() {
        View view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.dialog_update_pro, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        this.f243lp.x = 10;
        this.f243lp.y = 10;
        this.f243lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.f243lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.f243lp);
        show();
    }

    public void fileListView(String msgToast) {
        this.fileName = "factory_config.xml";
        View view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.dialog_file_list, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        TextView dlg_msg = (TextView) findViewById(C0899R.C0901id.dlg_msg);
        TextView dlg_ok = (TextView) findViewById(C0899R.C0901id.dlg_ok);
        TextView dlg_cancel = (TextView) findViewById(C0899R.C0901id.dlg_cancel);
        dlg_msg.setText(msgToast);
        dlg_ok.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DialogViews.this.updateDefXml();
                DialogViews.this.dismiss();
            }
        });
        dlg_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DialogViews.this.dismiss();
            }
        });
        this.f243lp.x = 10;
        this.f243lp.y = 10;
        this.f243lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.f243lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.f243lp);
        show();
    }

    public void updateDefXml() {
        File[] itemFile;
        List<String> rootList = FileUtils.getSDPath(getContext());
        if (new File("/sdcard/ksw_files/factory_config.xml").exists()) {
            this.updateFilePath = "/sdcard/ksw_files/factory_config.xml";
        } else {
            try {
                for (String sp : rootList) {
                    File file = new File(sp);
                    File[] files = file.listFiles();
                    for (File fs : files) {
                        if (fs.isDirectory() && fs.getName().toLowerCase().equals("oem") && (itemFile = fs.listFiles()) != null && itemFile.length > 0) {
                            int length = itemFile.length;
                            int i = 0;
                            while (true) {
                                if (i >= length) {
                                    break;
                                }
                                File is = itemFile[i];
                                if (!this.fileName.equals(is.getName())) {
                                    i++;
                                } else {
                                    this.updateFilePath = is.getAbsolutePath();
                                    break;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(KswApplication.TAG, "updateDefXml: error" + e.toString());
            }
        }
        Intent intent_recv = new Intent("com.ksw.mcu.recv");
        if (TextUtils.isEmpty(this.updateFilePath)) {
            intent_recv.putExtra("action", "update.factory_config.end");
            intent_recv.putExtra("value", "FAIL");
            this.m_con.sendBroadcast(intent_recv);
            Context context = this.m_con;
            ToastUtils.showToastShort(context, context.getString(C0899R.string.dialog_update2));
            return;
        }
        Log.d("FactoryUpdate", "====factory path======:" + this.updateFilePath);
        WitsCommand.sendCommand(1, 200, this.updateFilePath);
        intent_recv.putExtra("action", "update.factory_config.end");
        intent_recv.putExtra("value", "SUCCESS");
        this.m_con.sendBroadcast(intent_recv);
        MainActivity.mainActivity.finish();
        Intent intent = new Intent();
        intent.setClass(KswApplication.appContext, MainActivity.class);
        intent.addFlags(268435456);
        KswApplication.appContext.startActivity(intent);
        if (UiThemeUtils.isUI_GS_ID8(this.m_con)) {
            Settings.System.putInt(this.m_con.getContentResolver(), "memory_mode_for_freedom", 1);
        }
        System.exit(0);
    }

    public void inputLogoFile(String msgToast) {
        View view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.dialog_file_list, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        TextView dlg_msg = (TextView) findViewById(C0899R.C0901id.dlg_msg);
        TextView dlg_ok = (TextView) findViewById(C0899R.C0901id.dlg_ok);
        TextView dlg_cancel = (TextView) findViewById(C0899R.C0901id.dlg_cancel);
        dlg_msg.setText(msgToast);
        dlg_ok.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DialogViews.this.updateLogoFile();
                DialogViews.this.dismiss();
            }
        });
        dlg_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DialogViews.this.dismiss();
            }
        });
        this.f243lp.x = 10;
        this.f243lp.y = 10;
        this.f243lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.f243lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.f243lp);
        show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLogoFile() {
        List<String> rootList = FileUtils.getSDPath(getContext());
        if (Build.VERSION.RELEASE.equals("12")) {
            this.logoZipName = "mylogo12.zip";
        } else if (Build.VERSION.RELEASE.equals("13")) {
            this.logoZipName = "mylogo13.zip";
        }
        for (String sp : rootList) {
            File file = new File(sp);
            File[] files = file.listFiles();
            if (files != null) {
                for (File fs : files) {
                    if (fs.getName().toLowerCase().equals(this.logoZipName)) {
                        this.logoPath = fs.getAbsolutePath();
                    }
                }
            }
        }
        if (TextUtils.isEmpty(this.logoPath)) {
            Context context = this.m_con;
            ToastUtils.showToastShort(context, context.getString(C0899R.string.text_logo_input_non));
            return;
        }
        Log.d("logoUpdate", "====logo path======:" + this.logoPath);
        try {
            boolean success = FileUtils.copyFile(this.logoPath, this.m_con);
            if (success) {
                Context context2 = this.m_con;
                ToastUtils.showToastShort(context2, context2.getResources().getString(C0899R.string.text_logo_input_ok));
            }
            this.dialogHandler.sendEmptyMessageDelayed(4, 1000L);
        } catch (Exception e) {
            Log.d("logoUpdate", e.getLocalizedMessage());
        }
    }

    public void setMcuView() {
        View view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.dialog_mcu_upinfo, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        this.tv_prossToast = (TextView) view.findViewById(C0899R.C0901id.tv_prossToast);
        this.window.setSoftInputMode(2);
        this.f243lp.x = 10;
        this.f243lp.y = 10;
        this.f243lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.f243lp.height = UtilsInfo.dip2px(this.m_con, 230.0f);
        this.window.setAttributes(this.f243lp);
        this.mcuProgBar = (ProgressBar) findViewById(C0899R.C0901id.mcuProgBar);
        this.mcuTvPro = (TextView) findViewById(C0899R.C0901id.mcuTvPro);
        this.mcuProgBar.setMax(100);
        show();
        this.dialogHandler.sendEmptyMessageDelayed(1, 5000L);
        final Intent intent_recv = new Intent("com.ksw.mcu.recv");
        try {
            McuUpdater.registerMcuUpdateListener(new OnMcuUpdateProgressListener.Stub() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.11
                @Override // com.wits.pms.ksw.OnMcuUpdateProgressListener
                public void success() throws RemoteException {
                    intent_recv.putExtra("action", "update.end");
                    intent_recv.putExtra("value", "SUCCESS");
                    DialogViews.this.m_con.sendBroadcast(intent_recv);
                    Log.d("FactoryMcuUpdate", "McuUpdater:success");
                    DialogViews.this.dismiss();
                    ToastUtils.showToastShort(DialogViews.this.m_con, DialogViews.this.m_con.getString(C0899R.string.dialog_update3));
                }

                @Override // com.wits.pms.ksw.OnMcuUpdateProgressListener
                public void failed(int i) throws RemoteException {
                    DialogViews.this.errorFlag = i;
                    Log.d("FactoryMcuUpdate", "McuUpdater:failed  errorFlag=" + DialogViews.this.errorFlag);
                    DialogViews.this.dialogHandler.removeMessages(1);
                    DialogViews.this.dialogHandler.sendEmptyMessage(3);
                }

                @Override // com.wits.pms.ksw.OnMcuUpdateProgressListener
                public void progress(int i) throws RemoteException {
                    Log.d("FactoryMcuUpdate", "McuUpdater:progress i=" + i);
                    DialogViews.this.size = i;
                    DialogViews.this.dialogHandler.sendEmptyMessage(0);
                    if (DialogViews.this.mcu_start.booleanValue()) {
                        intent_recv.putExtra("action", "ready2update");
                        intent_recv.putExtra("value", "YES");
                        intent_recv.putExtra("action", "update.start");
                        DialogViews.this.m_con.sendBroadcast(intent_recv);
                        DialogViews.this.mcu_start = false;
                    }
                }
            });
            McuUpdater.mcuUpdate(this.updateFilePath);
        } catch (Exception e) {
            dismiss();
        }
    }

    public void updateMcu(String msgToast) {
        File[] itemFile;
        this.fileName = "ksw_mcu.bin";
        List<String> rootList = FileUtils.getSDPath(getContext());
        for (String sp : rootList) {
            File file = new File(sp);
            Log.i("FileUtil", " file.read = " + file.canRead());
            File[] files = file.listFiles();
            int length = files.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    File fs = files[i];
                    if (this.fileName.equals(fs.getName().toLowerCase())) {
                        this.updateFilePath = fs.getAbsolutePath();
                        break;
                    }
                    if (fs.isDirectory() && (itemFile = fs.listFiles()) != null && itemFile.length > 0) {
                        int length2 = itemFile.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 < length2) {
                                File is = itemFile[i2];
                                if (this.fileName.equals(is.getName().toLowerCase())) {
                                    this.updateFilePath = is.getAbsolutePath();
                                    break;
                                }
                                i2++;
                            }
                        }
                    }
                    i++;
                }
            }
        }
        if (new File("/sdcard/ksw_mcu.bin").exists()) {
            this.updateFilePath = "/sdcard/ksw_mcu.bin";
        }
        if (TextUtils.isEmpty(this.updateFilePath)) {
            Context context = this.m_con;
            ToastUtils.showToastShort(context, context.getString(C0899R.string.dialog_update2));
            return;
        }
        View view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.dialog_mcu_up, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        setCanceledOnTouchOutside(false);
        this.window.setSoftInputMode(2);
        this.f243lp.x = 10;
        this.f243lp.y = 10;
        this.f243lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.f243lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.f243lp);
        show();
        TextView dlg_msg = (TextView) findViewById(C0899R.C0901id.dlg_msg);
        TextView dlg_ok = (TextView) findViewById(C0899R.C0901id.dlg_ok);
        TextView dlg_cancel = (TextView) findViewById(C0899R.C0901id.dlg_cancel);
        dlg_msg.setText(msgToast);
        dlg_ok.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DialogViews.this.dismiss();
                DialogViews.this.setMcuView();
            }
        });
        dlg_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DialogViews.this.dismiss();
            }
        });
    }

    public void KswAPPupdateMcu(String msgToast) {
        File[] itemFile;
        this.fileName = "ksw_mcu.bin";
        List<String> rootList = FileUtils.getSDPath(getContext());
        if (new File("/sdcard/ksw_files/ksw_mcu.bin").exists()) {
            this.updateFilePath = "/sdcard/ksw_files/ksw_mcu.bin";
        } else {
            for (String sp : rootList) {
                File file = new File(sp);
                Log.i("FileUtil", " file.read = " + file.canRead());
                File[] files = file.listFiles();
                int length = files.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        File fs = files[i];
                        if (this.fileName.equals(fs.getName().toLowerCase())) {
                            this.updateFilePath = fs.getAbsolutePath();
                            break;
                        }
                        if (fs.isDirectory() && (itemFile = fs.listFiles()) != null && itemFile.length > 0) {
                            int length2 = itemFile.length;
                            int i2 = 0;
                            while (true) {
                                if (i2 < length2) {
                                    File is = itemFile[i2];
                                    if (this.fileName.equals(is.getName().toLowerCase())) {
                                        this.updateFilePath = is.getAbsolutePath();
                                        break;
                                    }
                                    i2++;
                                }
                            }
                        }
                        i++;
                    }
                }
            }
        }
        if (new File("/sdcard/ksw_mcu.bin").exists()) {
            this.updateFilePath = "/sdcard/ksw_mcu.bin";
        }
        Intent intent_recv = new Intent("com.ksw.mcu.recv");
        if (TextUtils.isEmpty(this.updateFilePath)) {
            intent_recv.putExtra("action", "ready2update");
            intent_recv.putExtra("value", "NO");
            this.m_con.sendBroadcast(intent_recv);
            Context context = this.m_con;
            ToastUtils.showToastShort(context, context.getString(C0899R.string.dialog_update2));
            return;
        }
        setMcuView();
    }

    public void listChoseApk(final List<DevBean> devBanList) {
        View view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.dialog_list_chose, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        RecyclerView rv = (RecyclerView) view.findViewById(C0899R.C0901id.navi_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.m_con);
        layoutManager.setOrientation(1);
        rv.setLayoutManager(layoutManager);
        ListChoseAdapter adapter = new ListChoseAdapter(this.m_con, devBanList);
        rv.setAdapter(adapter);
        adapter.registCheckListener(new ListChoseAdapter.IrbtCheckListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.15
            @Override // com.wits.ksw.settings.view_adapter.ListChoseAdapter.IrbtCheckListener
            public void checkListener(int pos) {
                FileUtils.savaStringData(KeyConfig.DEF_DVRAPK, ((DevBean) devBanList.get(pos)).getPackageName());
                Log.d("DialogSava", "savPackage:" + ((DevBean) devBanList.get(pos)).getPackageName());
                DialogViews.this.dismiss();
            }
        });
        this.f243lp.x = 10;
        this.f243lp.y = 10;
        this.f243lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.f243lp.height = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.window.setAttributes(this.f243lp);
        show();
    }

    public void listPlayApk(final List<DevBean> playAppList) {
        View view = LayoutInflater.from(this.m_con).inflate(C0899R.C0902layout.dialog_list_chose, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        RecyclerView rv = (RecyclerView) view.findViewById(C0899R.C0901id.navi_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.m_con);
        layoutManager.setOrientation(1);
        rv.setLayoutManager(layoutManager);
        ListChoseAdapter adapter = new ListChoseAdapter(this.m_con, playAppList);
        rv.setAdapter(adapter);
        adapter.registCheckListener(new ListChoseAdapter.IrbtCheckListener() { // from class: com.wits.ksw.settings.utlis_view.DialogViews.16
            @Override // com.wits.ksw.settings.view_adapter.ListChoseAdapter.IrbtCheckListener
            public void checkListener(int pos) {
                Settings.System.putString(DialogViews.this.m_con.getContentResolver(), "defPlayApp", ((DevBean) playAppList.get(pos)).getPackageName());
                Log.d("DialogSava", "palyAPP:" + ((DevBean) playAppList.get(pos)).getPackageName());
                DialogViews.this.dismiss();
            }
        });
        this.f243lp.x = 10;
        this.f243lp.y = 10;
        this.f243lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.f243lp.height = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.window.setAttributes(this.f243lp);
        show();
    }
}
