package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.Build;
import android.os.RemoteException;
import android.support.p001v4.internal.view.SupportMenu;
import android.support.p004v7.widget.GridLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.adapter.UiSelectAdapter;
import com.wits.ksw.settings.id7.bean.UiSelectBean;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ToastUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes17.dex */
public class LogoSelect extends FrameLayout {
    private static final String TAG = "UiSelect";
    private UiSelectAdapter adapter;
    private List<UiSelectBean> data;
    private DialogViews dialogViews;
    private File filePath;
    private GridLayoutManager gridLayoutManager;
    private String imgFileName;
    private int kswlogo;
    private FrameLayout.LayoutParams layoutParams;
    private File logoFile;
    private Context m_con;
    private RecyclerView recyclerView;
    private UiSelectAdapter siRenAdapter;
    private RecyclerView siRen_recyclerView;
    private List<UiSelectBean> siRendata;
    private GridLayoutManager siRengridLayoutManager;
    private TextView tvOne;
    private TextView tvTwo;
    private TextView tv_Input;
    private View view;

    public LogoSelect(Context context) {
        super(context);
        this.imgFileName = "";
        this.kswlogo = 0;
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(C0899R.C0902layout.factory_logo_select, (ViewGroup) null);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initData() {
        try {
            this.kswlogo = PowerManagerApp.getSettingsInt(KeyConfig.KSW_LOGO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.data = new ArrayList();
        this.siRendata = new ArrayList();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        this.imgFileName = screenWidth + "_" + screenHeight;
        initTongyong();
        initZhuanYong();
    }

    private void initTongyong() {
        int i;
        String name;
        int i2;
        this.logoFile = new File("/system/media/picture/normal/thumbnail");
        this.filePath = new File("/system/media/picture/normal/" + this.imgFileName);
        if (!this.logoFile.exists() || !this.filePath.exists()) {
            return;
        }
        File[] logos = this.logoFile.listFiles();
        File[] files = this.filePath.listFiles();
        while (i < logos.length) {
            UiSelectBean uiSelectBean = new UiSelectBean();
            uiSelectBean.setUiPath(logos[i].getPath());
            int length = logos[i].getName().length();
            try {
                name = logos[i].getName().substring(length - 7, length - 4);
                for (File fle : files) {
                    int leng = fle.getName().length();
                    String fn = fle.getName().substring(leng - 7, leng - 4);
                    Log.d(TAG, "TongYong_logo: name " + fn);
                    if (TextUtils.equals(name, fn)) {
                        uiSelectBean.setFilePath(fle.getPath());
                    }
                }
                Log.d(TAG, "TongYong_logo:" + uiSelectBean.getUiPath() + "\nfiles:" + uiSelectBean.getFilePath());
                i2 = this.kswlogo;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i2 == 0) {
                i = (name.contains("004") || name.contains("005")) ? i + 1 : 0;
            } else if (i2 == 1 && !name.contains("004") && !name.contains("005")) {
            }
            this.data.add(uiSelectBean);
            Log.d(TAG, "TongYong_logo size: " + this.data.size());
        }
    }

    private void initZhuanYong() {
        this.logoFile = new File(FileUtils.getLogoFilePath() + "/thumbnail");
        this.filePath = new File(FileUtils.getLogoFilePath() + "/" + this.imgFileName);
        if (!this.logoFile.exists() || !this.filePath.exists()) {
            return;
        }
        List<UiSelectBean> list = this.siRendata;
        if (list != null) {
            list.clear();
        }
        File[] logos = this.logoFile.listFiles();
        File[] files = this.filePath.listFiles();
        for (int i = 0; i < logos.length; i++) {
            UiSelectBean uiSelectBean = new UiSelectBean();
            uiSelectBean.setUiPath(logos[i].getPath());
            int length = logos[i].getName().length();
            try {
                String name = logos[i].getName().substring(length - 7, length - 4);
                for (File fle : files) {
                    int leng = fle.getName().length();
                    String fn = fle.getName().substring(leng - 7, leng - 4);
                    Log.d(TAG, "zhuanYong_logo: name " + fn);
                    if (TextUtils.equals(name, fn) && (Build.VERSION.RELEASE.equals("12") || Build.VERSION.RELEASE.equals("13") || !fle.getPath().endsWith("bmp"))) {
                        uiSelectBean.setFilePath(fle.getPath());
                    }
                }
                Log.d(TAG, "zhuanYong_logo:" + uiSelectBean.getUiPath() + "\nfiles:" + uiSelectBean.getFilePath());
                this.siRendata.add(uiSelectBean);
                Log.d(TAG, "zhuanYong_logo size: " + this.siRendata.size());
            } catch (StringIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAdapterData() {
        if (this.siRenAdapter != null) {
            initZhuanYong();
            this.siRenAdapter.setListData(this.siRendata);
        }
    }

    private void initView() {
        this.dialogViews = new DialogViews(this.m_con);
        this.recyclerView = (RecyclerView) this.view.findViewById(C0899R.C0901id.recyclerView);
        this.siRen_recyclerView = (RecyclerView) this.view.findViewById(C0899R.C0901id.siRen_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.m_con, 3);
        this.gridLayoutManager = gridLayoutManager;
        this.recyclerView.setLayoutManager(gridLayoutManager);
        UiSelectAdapter uiSelectAdapter = new UiSelectAdapter(this.m_con, this.data);
        this.adapter = uiSelectAdapter;
        this.recyclerView.setAdapter(uiSelectAdapter);
        this.adapter.registOnFunctionClickListener(new UiSelectAdapter.OnFunctionClickListener() { // from class: com.wits.ksw.settings.id7.layout_factory.LogoSelect.1
            @Override // com.wits.ksw.settings.id7.adapter.UiSelectAdapter.OnFunctionClickListener
            public void functonClick(int pos) {
                if (Build.VERSION.RELEASE.equals("11") && ((UiSelectBean) LogoSelect.this.data.get(pos)).getFilePath().contains("splash")) {
                    ToastUtils.showToastShort(LogoSelect.this.m_con, "This logo file nonsupport Android 11");
                } else if (!Build.VERSION.RELEASE.equals("10") || !((UiSelectBean) LogoSelect.this.data.get(pos)).getFilePath().contains("elf")) {
                    LogoSelect.this.dialogViews.isUpdateLogo(LogoSelect.this.getResources().getString(C0899R.string.string_is_this_logo), ((UiSelectBean) LogoSelect.this.data.get(pos)).getFilePath());
                    Log.d(LogoSelect.TAG, "img Path:" + ((UiSelectBean) LogoSelect.this.data.get(pos)).getFilePath());
                } else {
                    ToastUtils.showToastShort(LogoSelect.this.m_con, "This logo file nonsupport Android 10");
                }
            }
        });
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this.m_con, 3);
        this.siRengridLayoutManager = gridLayoutManager2;
        this.siRen_recyclerView.setLayoutManager(gridLayoutManager2);
        UiSelectAdapter uiSelectAdapter2 = new UiSelectAdapter(this.m_con, this.siRendata);
        this.siRenAdapter = uiSelectAdapter2;
        this.siRen_recyclerView.setAdapter(uiSelectAdapter2);
        this.siRenAdapter.registOnFunctionClickListener(new UiSelectAdapter.OnFunctionClickListener() { // from class: com.wits.ksw.settings.id7.layout_factory.LogoSelect.2
            @Override // com.wits.ksw.settings.id7.adapter.UiSelectAdapter.OnFunctionClickListener
            public void functonClick(int pos) {
                if (Build.VERSION.RELEASE.equals("11") && ((UiSelectBean) LogoSelect.this.siRendata.get(pos)).getFilePath().contains("splash")) {
                    ToastUtils.showToastShort(LogoSelect.this.m_con, "This logo file nonsupport Android 11");
                } else if (!Build.VERSION.RELEASE.equals("10") || !((UiSelectBean) LogoSelect.this.siRendata.get(pos)).getFilePath().contains("elf")) {
                    LogoSelect.this.dialogViews.isUpdateLogo(LogoSelect.this.getResources().getString(C0899R.string.string_is_this_logo), ((UiSelectBean) LogoSelect.this.siRendata.get(pos)).getFilePath());
                    Log.d(LogoSelect.TAG, "img Path:" + ((UiSelectBean) LogoSelect.this.siRendata.get(pos)).getFilePath());
                } else {
                    ToastUtils.showToastShort(LogoSelect.this.m_con, "This logo file nonsupport Android 10");
                }
            }
        });
        TextView textView = (TextView) this.view.findViewById(C0899R.C0901id.tvOne);
        this.tvOne = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.id7.layout_factory.LogoSelect.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LogoSelect.this.recyclerView.setVisibility(0);
                LogoSelect.this.siRen_recyclerView.setVisibility(8);
                LogoSelect.this.tvOne.setTextColor(SupportMenu.CATEGORY_MASK);
                LogoSelect.this.tvTwo.setTextColor(-1);
            }
        });
        TextView textView2 = (TextView) this.view.findViewById(C0899R.C0901id.tvTwo);
        this.tvTwo = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.id7.layout_factory.LogoSelect.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LogoSelect.this.recyclerView.setVisibility(8);
                LogoSelect.this.siRen_recyclerView.setVisibility(0);
                LogoSelect.this.tvOne.setTextColor(-1);
                LogoSelect.this.tvTwo.setTextColor(SupportMenu.CATEGORY_MASK);
            }
        });
        TextView textView3 = (TextView) this.view.findViewById(C0899R.C0901id.tv_Input);
        this.tv_Input = textView3;
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.id7.layout_factory.LogoSelect.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LogoSelect.this.dialogViews.inputLogoFile(LogoSelect.this.m_con.getResources().getString(C0899R.string.text_logo_input_msg));
            }
        });
    }
}
