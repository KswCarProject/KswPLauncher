package com.wits.ksw.settings.utlis_view;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.DevBean;
import com.wits.ksw.settings.view_adapter.ListChoseAdapter;
import com.wits.pms.ksw.OnMcuUpdateProgressListener;
import com.wits.pms.statuscontrol.McuUpdater;
import com.wits.pms.statuscontrol.WitsCommand;
import java.io.File;
import java.util.List;

public class DialogViews extends Dialog {
    /* access modifiers changed from: private */
    public Handler dialogHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    DialogViews.this.mcuProgBar.setProgress(DialogViews.this.size);
                    DialogViews.this.mcuTvPro.setText(DialogViews.this.size + "%");
                    return;
                case 1:
                    if (((double) DialogViews.this.size) < 0.5d) {
                        Log.d("FactoryMcuUpdate", "dialogHandler:handleMessage size=" + DialogViews.this.size);
                        ToastUtils.showToastShort(DialogViews.this.m_con, DialogViews.this.m_con.getString(R.string.dialog_update7));
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
                        DialogViews.this.tv_prossToast.setText(DialogViews.this.m_con.getString(R.string.app_not_update));
                    } else {
                        DialogViews.this.tv_prossToast.setText(DialogViews.this.m_con.getString(R.string.mcu_file_corrupted));
                    }
                    DialogViews.this.dialogHandler.sendEmptyMessageDelayed(2, 2000);
                    return;
                case 4:
                    System.exit(0);
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public int errorFlag = -1;
    private String fileName;
    private String logoPath = "";
    private WindowManager.LayoutParams lp;
    /* access modifiers changed from: private */
    public Context m_con;
    /* access modifiers changed from: private */
    public ProgressBar mcuProgBar;
    /* access modifiers changed from: private */
    public TextView mcuTvPro;
    /* access modifiers changed from: private */
    public int size;
    /* access modifiers changed from: private */
    public TextView tv_prossToast;
    private String updateDirPath = "";
    private String updateFilePath = "";
    private Window window;

    public DialogViews(Context context) {
        super(context);
        this.m_con = context;
        Window window2 = getWindow();
        this.window = window2;
        this.lp = window2.getAttributes();
    }

    public void qrCode(Bitmap bitmap) {
        Log.d("qrCode", "show qrCode");
        View view = LayoutInflater.from(this.m_con).inflate(R.layout.dialog_qrcode, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        view.findViewById(R.id.qr_root).setBackground(new BitmapDrawable((Resources) null, bitmap));
        this.lp.x = 10;
        this.lp.y = 10;
        this.lp.width = UtilsInfo.dip2px(this.m_con, 300.0f);
        this.lp.height = UtilsInfo.dip2px(this.m_con, 300.0f);
        this.window.setAttributes(this.lp);
        show();
    }

    public void isQuestView(String msgToast, final Handler handler) {
        setContentView(LayoutInflater.from(this.m_con).inflate(R.layout.dialog_file_list, (ViewGroup) null));
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        ((TextView) findViewById(R.id.dlg_msg)).setText(msgToast);
        ((TextView) findViewById(R.id.dlg_ok)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                handler.sendEmptyMessage(1);
                DialogViews.this.dismiss();
            }
        });
        ((TextView) findViewById(R.id.dlg_cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogViews.this.dismiss();
            }
        });
        this.lp.x = 10;
        this.lp.y = 10;
        this.lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.lp);
        show();
    }

    public void isUpdateLogo(String msgToast, final String path) {
        Log.d("UiSelect", "updateLogo path : " + path);
        setContentView(LayoutInflater.from(this.m_con).inflate(R.layout.dialog_file_list, (ViewGroup) null));
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        ((TextView) findViewById(R.id.dlg_msg)).setText(msgToast);
        ((TextView) findViewById(R.id.dlg_ok)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WitsCommand.sendCommand(1, 124, path);
                DialogViews.this.dismiss();
                ToastUtils.showToastShort(DialogViews.this.m_con, DialogViews.this.m_con.getResources().getString(R.string.string_set_logo_over));
            }
        });
        ((TextView) findViewById(R.id.dlg_cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogViews.this.dismiss();
            }
        });
        this.lp.x = 10;
        this.lp.y = 10;
        this.lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.lp);
        show();
    }

    public void isSelecUi(String msg, final Handler handler) {
        setContentView(LayoutInflater.from(this.m_con).inflate(R.layout.dialog_select_ui, (ViewGroup) null));
        this.window.setGravity(17);
        ((TextView) findViewById(R.id.upText_msg)).setText(msg);
        ((TextView) findViewById(R.id.dlg_ok)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                handler.sendEmptyMessage(0);
            }
        });
        ((TextView) findViewById(R.id.dlg_cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogViews.this.dismiss();
            }
        });
        this.lp.x = 10;
        this.lp.y = 10;
        this.lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.lp);
        show();
    }

    public void showUpdateIng() {
        setContentView(LayoutInflater.from(this.m_con).inflate(R.layout.dialog_update_pro, (ViewGroup) null));
        this.window.setGravity(17);
        this.lp.x = 10;
        this.lp.y = 10;
        this.lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.lp);
        show();
    }

    public void fileListView(String msgToast) {
        this.fileName = "factory_config.xml";
        setContentView(LayoutInflater.from(this.m_con).inflate(R.layout.dialog_file_list, (ViewGroup) null));
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        ((TextView) findViewById(R.id.dlg_msg)).setText(msgToast);
        ((TextView) findViewById(R.id.dlg_ok)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogViews.this.updateDefXml();
                DialogViews.this.dismiss();
            }
        });
        ((TextView) findViewById(R.id.dlg_cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogViews.this.dismiss();
            }
        });
        this.lp.x = 10;
        this.lp.y = 10;
        this.lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.lp);
        show();
    }

    /* access modifiers changed from: private */
    public void updateDefXml() {
        File[] itemFile;
        for (String sp : FileUtils.getSDPath(getContext())) {
            for (File fs : new File(sp).listFiles()) {
                if (fs.isDirectory() && fs.getName().toLowerCase().equals("oem") && (itemFile = fs.listFiles()) != null && itemFile.length > 0) {
                    int length = itemFile.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        File is = itemFile[i];
                        if (this.fileName.equals(is.getName())) {
                            this.updateFilePath = is.getAbsolutePath();
                            break;
                        }
                        i++;
                    }
                }
            }
        }
        if (TextUtils.isEmpty(this.updateFilePath)) {
            Context context = this.m_con;
            ToastUtils.showToastShort(context, context.getString(R.string.dialog_update2));
            return;
        }
        Log.d("FactoryUpdate", "====factory path======:" + this.updateFilePath);
        WitsCommand.sendCommand(1, 200, this.updateFilePath);
        System.exit(0);
    }

    public void inputLogoFile(String msgToast) {
        setContentView(LayoutInflater.from(this.m_con).inflate(R.layout.dialog_file_list, (ViewGroup) null));
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        ((TextView) findViewById(R.id.dlg_msg)).setText(msgToast);
        ((TextView) findViewById(R.id.dlg_ok)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogViews.this.updateLogoFile();
                DialogViews.this.dismiss();
            }
        });
        ((TextView) findViewById(R.id.dlg_cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogViews.this.dismiss();
            }
        });
        this.lp.x = 10;
        this.lp.y = 10;
        this.lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.lp);
        show();
    }

    /* access modifiers changed from: private */
    public void updateLogoFile() {
        for (String sp : FileUtils.getSDPath(getContext())) {
            File[] files = new File(sp).listFiles();
            if (files != null) {
                for (File fs : files) {
                    if (fs.getName().toLowerCase().equals("mylogo.zip")) {
                        this.logoPath = fs.getAbsolutePath();
                    }
                }
            }
        }
        if (TextUtils.isEmpty(this.logoPath)) {
            Context context = this.m_con;
            ToastUtils.showToastShort(context, context.getString(R.string.text_logo_input_non));
            return;
        }
        Log.d("logoUpdate", "====logo path======:" + this.logoPath);
        try {
            if (FileUtils.copyFile(this.logoPath, this.m_con)) {
                Context context2 = this.m_con;
                ToastUtils.showToastShort(context2, context2.getResources().getString(R.string.text_logo_input_ok));
            }
            this.dialogHandler.sendEmptyMessageDelayed(4, 1000);
        } catch (Exception e) {
            Log.d("logoUpdate", e.getLocalizedMessage());
        }
    }

    public void setMcuView() {
        View view = LayoutInflater.from(this.m_con).inflate(R.layout.dialog_mcu_upinfo, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        this.tv_prossToast = (TextView) view.findViewById(R.id.tv_prossToast);
        this.window.setSoftInputMode(2);
        this.lp.x = 10;
        this.lp.y = 10;
        this.lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.lp.height = UtilsInfo.dip2px(this.m_con, 230.0f);
        this.window.setAttributes(this.lp);
        this.mcuProgBar = (ProgressBar) findViewById(R.id.mcuProgBar);
        this.mcuTvPro = (TextView) findViewById(R.id.mcuTvPro);
        this.mcuProgBar.setMax(100);
        show();
        this.dialogHandler.sendEmptyMessageDelayed(1, 5000);
        try {
            McuUpdater.registerMcuUpdateListener(new OnMcuUpdateProgressListener.Stub() {
                public void success() throws RemoteException {
                    Log.d("FactoryMcuUpdate", "McuUpdater:success");
                    DialogViews.this.dismiss();
                    ToastUtils.showToastShort(DialogViews.this.m_con, DialogViews.this.m_con.getString(R.string.dialog_update3));
                }

                public void failed(int i) throws RemoteException {
                    int unused = DialogViews.this.errorFlag = i;
                    Log.d("FactoryMcuUpdate", "McuUpdater:failed  errorFlag=" + DialogViews.this.errorFlag);
                    DialogViews.this.dialogHandler.removeMessages(1);
                    DialogViews.this.dialogHandler.sendEmptyMessage(3);
                }

                public void progress(int i) throws RemoteException {
                    Log.d("FactoryMcuUpdate", "McuUpdater:progress i=" + i);
                    int unused = DialogViews.this.size = i;
                    DialogViews.this.dialogHandler.sendEmptyMessage(0);
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
        for (String sp : FileUtils.getSDPath(getContext())) {
            File file = new File(sp);
            Log.i("FileUtil", " file.read = " + file.canRead());
            File[] files = file.listFiles();
            int length = files.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                File fs = files[i];
                if (this.fileName.equals(fs.getName().toLowerCase())) {
                    this.updateFilePath = fs.getAbsolutePath();
                    break;
                }
                if (fs.isDirectory() && (itemFile = fs.listFiles()) != null && itemFile.length > 0) {
                    int length2 = itemFile.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        }
                        File is = itemFile[i2];
                        if (this.fileName.equals(is.getName().toLowerCase())) {
                            this.updateFilePath = is.getAbsolutePath();
                            break;
                        }
                        i2++;
                    }
                }
                i++;
            }
        }
        if (new File("/sdcard/ksw_mcu.bin").exists()) {
            this.updateFilePath = "/sdcard/ksw_mcu.bin";
        }
        if (TextUtils.isEmpty(this.updateFilePath)) {
            Context context = this.m_con;
            ToastUtils.showToastShort(context, context.getString(R.string.dialog_update2));
            return;
        }
        setContentView(LayoutInflater.from(this.m_con).inflate(R.layout.dialog_mcu_up, (ViewGroup) null));
        this.window.setGravity(17);
        setCanceledOnTouchOutside(false);
        this.window.setSoftInputMode(2);
        this.lp.x = 10;
        this.lp.y = 10;
        this.lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.lp.height = UtilsInfo.dip2px(this.m_con, 200.0f);
        this.window.setAttributes(this.lp);
        show();
        ((TextView) findViewById(R.id.dlg_msg)).setText(msgToast);
        ((TextView) findViewById(R.id.dlg_ok)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogViews.this.dismiss();
                DialogViews.this.setMcuView();
            }
        });
        ((TextView) findViewById(R.id.dlg_cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogViews.this.dismiss();
            }
        });
    }

    public void listChoseApk(final List<DevBean> devBanList) {
        View view = LayoutInflater.from(this.m_con).inflate(R.layout.dialog_list_chose, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.navi_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.m_con);
        layoutManager.setOrientation(1);
        rv.setLayoutManager(layoutManager);
        ListChoseAdapter adapter = new ListChoseAdapter(this.m_con, devBanList);
        rv.setAdapter(adapter);
        adapter.registCheckListener(new ListChoseAdapter.IrbtCheckListener() {
            public void checkListener(int pos) {
                FileUtils.savaStringData(KeyConfig.DEF_DVRAPK, ((DevBean) devBanList.get(pos)).getPackageName());
                Log.d("DialogSava", "savPackage:" + ((DevBean) devBanList.get(pos)).getPackageName());
                DialogViews.this.dismiss();
            }
        });
        this.lp.x = 10;
        this.lp.y = 10;
        this.lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.lp.height = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.window.setAttributes(this.lp);
        show();
    }

    public void listPlayApk(final List<DevBean> playAppList) {
        View view = LayoutInflater.from(this.m_con).inflate(R.layout.dialog_list_chose, (ViewGroup) null);
        setContentView(view);
        this.window.setGravity(17);
        this.window.setSoftInputMode(2);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.navi_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.m_con);
        layoutManager.setOrientation(1);
        rv.setLayoutManager(layoutManager);
        ListChoseAdapter adapter = new ListChoseAdapter(this.m_con, playAppList);
        rv.setAdapter(adapter);
        adapter.registCheckListener(new ListChoseAdapter.IrbtCheckListener() {
            public void checkListener(int pos) {
                Settings.System.putString(DialogViews.this.m_con.getContentResolver(), "defPlayApp", ((DevBean) playAppList.get(pos)).getPackageName());
                Log.d("DialogSava", "palyAPP:" + ((DevBean) playAppList.get(pos)).getPackageName());
                DialogViews.this.dismiss();
            }
        });
        this.lp.x = 10;
        this.lp.y = 10;
        this.lp.width = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.lp.height = UtilsInfo.dip2px(this.m_con, 400.0f);
        this.window.setAttributes(this.lp);
        show();
    }
}
