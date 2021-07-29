package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.Build;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.adapter.UiSelectAdapter;
import com.wits.ksw.settings.id7.bean.UiSelectBean;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.ToastUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LogoSelect extends FrameLayout {
    private static final String TAG = "UiSelect";
    private UiSelectAdapter adapter;
    /* access modifiers changed from: private */
    public List<UiSelectBean> data;
    /* access modifiers changed from: private */
    public DialogViews dialogViews;
    private File filePath;
    private GridLayoutManager gridLayoutManager;
    private String imgFileName = "";
    private FrameLayout.LayoutParams layoutParams;
    private File logoFile;
    /* access modifiers changed from: private */
    public Context m_con;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    private UiSelectAdapter siRenAdapter;
    /* access modifiers changed from: private */
    public RecyclerView siRen_recyclerView;
    /* access modifiers changed from: private */
    public List<UiSelectBean> siRendata;
    private GridLayoutManager siRengridLayoutManager;
    /* access modifiers changed from: private */
    public TextView tvOne;
    /* access modifiers changed from: private */
    public TextView tvTwo;
    private TextView tv_Input;
    private View view;

    public LogoSelect(Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.factory_logo_select, (ViewGroup) null);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initData() {
        this.data = new ArrayList();
        this.siRendata = new ArrayList();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        this.imgFileName = screenWidth + "_" + dm.heightPixels;
        initTongyong();
        initZhuanYong();
    }

    private void initTongyong() {
        this.logoFile = new File("/system/media/picture/normal/thumbnail");
        this.filePath = new File("/system/media/picture/normal/" + this.imgFileName);
        if (this.logoFile.exists() && this.filePath.exists()) {
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
                        Log.d(TAG, "TongYong_logo: name " + fn);
                        if (TextUtils.equals(name, fn)) {
                            uiSelectBean.setFilePath(fle.getPath());
                        }
                    }
                    Log.d(TAG, "TongYong_logo:" + uiSelectBean.getUiPath() + "\nfiles:" + uiSelectBean.getFilePath());
                    this.data.add(uiSelectBean);
                    Log.d(TAG, "TongYong_logo size: " + this.data.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initZhuanYong() {
        this.logoFile = new File("/storage/emulated/0/mylogo/thumbnail");
        this.filePath = new File("/storage/emulated/0/mylogo/" + this.imgFileName);
        if (this.logoFile.exists() && this.filePath.exists()) {
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
                        if (TextUtils.equals(name, fn) && !fle.getPath().endsWith("bmp")) {
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
    }

    private void initView() {
        this.dialogViews = new DialogViews(this.m_con);
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.recyclerView);
        this.siRen_recyclerView = (RecyclerView) this.view.findViewById(R.id.siRen_recyclerView);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this.m_con, 3);
        this.gridLayoutManager = gridLayoutManager2;
        this.recyclerView.setLayoutManager(gridLayoutManager2);
        UiSelectAdapter uiSelectAdapter = new UiSelectAdapter(this.m_con, this.data);
        this.adapter = uiSelectAdapter;
        this.recyclerView.setAdapter(uiSelectAdapter);
        this.adapter.registOnFunctionClickListener(new UiSelectAdapter.OnFunctionClickListener() {
            public void functonClick(int pos) {
                if (Build.VERSION.RELEASE.equals("11") && ((UiSelectBean) LogoSelect.this.data.get(pos)).getFilePath().contains("splash")) {
                    ToastUtils.showToastShort(LogoSelect.this.m_con, "This logo file nonsupport Android 11");
                } else if (!Build.VERSION.RELEASE.equals("10") || !((UiSelectBean) LogoSelect.this.data.get(pos)).getFilePath().contains("elf")) {
                    LogoSelect.this.dialogViews.isUpdateLogo(LogoSelect.this.getResources().getString(R.string.string_is_this_logo), ((UiSelectBean) LogoSelect.this.data.get(pos)).getFilePath());
                    Log.d(LogoSelect.TAG, "img Path:" + ((UiSelectBean) LogoSelect.this.data.get(pos)).getFilePath());
                } else {
                    ToastUtils.showToastShort(LogoSelect.this.m_con, "This logo file nonsupport Android 10");
                }
            }
        });
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(this.m_con, 3);
        this.siRengridLayoutManager = gridLayoutManager3;
        this.siRen_recyclerView.setLayoutManager(gridLayoutManager3);
        UiSelectAdapter uiSelectAdapter2 = new UiSelectAdapter(this.m_con, this.siRendata);
        this.siRenAdapter = uiSelectAdapter2;
        this.siRen_recyclerView.setAdapter(uiSelectAdapter2);
        this.siRenAdapter.registOnFunctionClickListener(new UiSelectAdapter.OnFunctionClickListener() {
            public void functonClick(int pos) {
                if (Build.VERSION.RELEASE.equals("11") && ((UiSelectBean) LogoSelect.this.siRendata.get(pos)).getFilePath().contains("splash")) {
                    ToastUtils.showToastShort(LogoSelect.this.m_con, "This logo file nonsupport Android 11");
                } else if (!Build.VERSION.RELEASE.equals("10") || !((UiSelectBean) LogoSelect.this.siRendata.get(pos)).getFilePath().contains("elf")) {
                    LogoSelect.this.dialogViews.isUpdateLogo(LogoSelect.this.getResources().getString(R.string.string_is_this_logo), ((UiSelectBean) LogoSelect.this.siRendata.get(pos)).getFilePath());
                    Log.d(LogoSelect.TAG, "img Path:" + ((UiSelectBean) LogoSelect.this.siRendata.get(pos)).getFilePath());
                } else {
                    ToastUtils.showToastShort(LogoSelect.this.m_con, "This logo file nonsupport Android 10");
                }
            }
        });
        TextView textView = (TextView) this.view.findViewById(R.id.tvOne);
        this.tvOne = textView;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LogoSelect.this.recyclerView.setVisibility(0);
                LogoSelect.this.siRen_recyclerView.setVisibility(8);
                LogoSelect.this.tvOne.setTextColor(SupportMenu.CATEGORY_MASK);
                LogoSelect.this.tvTwo.setTextColor(-1);
            }
        });
        TextView textView2 = (TextView) this.view.findViewById(R.id.tvTwo);
        this.tvTwo = textView2;
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LogoSelect.this.recyclerView.setVisibility(8);
                LogoSelect.this.siRen_recyclerView.setVisibility(0);
                LogoSelect.this.tvOne.setTextColor(-1);
                LogoSelect.this.tvTwo.setTextColor(SupportMenu.CATEGORY_MASK);
            }
        });
        TextView textView3 = (TextView) this.view.findViewById(R.id.tv_Input);
        this.tv_Input = textView3;
        textView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LogoSelect.this.dialogViews.inputLogoFile(LogoSelect.this.m_con.getResources().getString(R.string.text_logo_input_msg));
            }
        });
    }
}
