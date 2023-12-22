package com.wits.ksw.settings.als_id7_ui_set.factory;

import android.content.Context;
import android.os.Build;
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
import com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiUiSelectAdapter;
import com.wits.ksw.settings.id7.bean.UiSelectBean;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.ToastUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class AlsID7UiLogoSelect extends FrameLayout {
    private static final String TAG = "AlsID7UiLogoSelect";
    private AlsID7UiUiSelectAdapter adapter;
    private List<UiSelectBean> data;
    private DialogViews dialogViews;
    private File filePath;
    private GridLayoutManager gridLayoutManager;
    private String imgFileName;
    private FrameLayout.LayoutParams layoutParams;
    private File logoFile;
    private Context m_con;
    private RecyclerView recyclerView;
    private AlsID7UiUiSelectAdapter siRenAdapter;
    private RecyclerView siRen_recyclerView;
    private List<UiSelectBean> siRendata;
    private GridLayoutManager siRengridLayoutManager;
    private TextView tvOne;
    private TextView tvTwo;
    private TextView tv_Input;
    private View view;

    public AlsID7UiLogoSelect(Context context) {
        super(context);
        this.imgFileName = "";
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(C0899R.C0902layout.als_id7_ui_factory_logo_select, (ViewGroup) null);
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
        int screenHeight = dm.heightPixels;
        this.imgFileName = screenWidth + "_" + screenHeight;
        initTongyong();
        initZhuanYong();
    }

    private void initTongyong() {
        this.logoFile = new File("/system/media/picture/normal/thumbnail");
        this.filePath = new File("/system/media/picture/normal/" + this.imgFileName);
        if (!this.logoFile.exists() || !this.filePath.exists()) {
            return;
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

    private void initZhuanYong() {
        this.logoFile = new File(FileUtils.getLogoFilePath() + "/thumbnail");
        this.filePath = new File(FileUtils.getLogoFilePath() + "/" + this.imgFileName);
        if (!this.logoFile.exists() || !this.filePath.exists()) {
            return;
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

    private void initView() {
        this.dialogViews = new DialogViews(this.m_con);
        this.recyclerView = (RecyclerView) this.view.findViewById(C0899R.C0901id.recyclerView);
        this.siRen_recyclerView = (RecyclerView) this.view.findViewById(C0899R.C0901id.siRen_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.m_con, 3);
        this.gridLayoutManager = gridLayoutManager;
        this.recyclerView.setLayoutManager(gridLayoutManager);
        AlsID7UiUiSelectAdapter alsID7UiUiSelectAdapter = new AlsID7UiUiSelectAdapter(this.m_con, this.data);
        this.adapter = alsID7UiUiSelectAdapter;
        this.recyclerView.setAdapter(alsID7UiUiSelectAdapter);
        this.adapter.registOnFunctionClickListener(new AlsID7UiUiSelectAdapter.OnFunctionClickListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiLogoSelect.1
            @Override // com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiUiSelectAdapter.OnFunctionClickListener
            public void functonClick(int pos) {
                if (Build.VERSION.RELEASE.equals("11") && ((UiSelectBean) AlsID7UiLogoSelect.this.data.get(pos)).getFilePath().contains("splash")) {
                    ToastUtils.showToastShort(AlsID7UiLogoSelect.this.m_con, "This logo file nonsupport Android 11");
                } else if (!Build.VERSION.RELEASE.equals("10") || !((UiSelectBean) AlsID7UiLogoSelect.this.data.get(pos)).getFilePath().contains("elf")) {
                    AlsID7UiLogoSelect.this.dialogViews.isUpdateLogo(AlsID7UiLogoSelect.this.getResources().getString(C0899R.string.string_is_this_logo), ((UiSelectBean) AlsID7UiLogoSelect.this.data.get(pos)).getFilePath());
                    Log.d(AlsID7UiLogoSelect.TAG, "img Path:" + ((UiSelectBean) AlsID7UiLogoSelect.this.data.get(pos)).getFilePath());
                } else {
                    ToastUtils.showToastShort(AlsID7UiLogoSelect.this.m_con, "This logo file nonsupport Android 10");
                }
            }
        });
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this.m_con, 3);
        this.siRengridLayoutManager = gridLayoutManager2;
        this.siRen_recyclerView.setLayoutManager(gridLayoutManager2);
        AlsID7UiUiSelectAdapter alsID7UiUiSelectAdapter2 = new AlsID7UiUiSelectAdapter(this.m_con, this.siRendata);
        this.siRenAdapter = alsID7UiUiSelectAdapter2;
        this.siRen_recyclerView.setAdapter(alsID7UiUiSelectAdapter2);
        this.siRenAdapter.registOnFunctionClickListener(new AlsID7UiUiSelectAdapter.OnFunctionClickListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiLogoSelect.2
            @Override // com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiUiSelectAdapter.OnFunctionClickListener
            public void functonClick(int pos) {
                if (Build.VERSION.RELEASE.equals("11") && ((UiSelectBean) AlsID7UiLogoSelect.this.siRendata.get(pos)).getFilePath().contains("splash")) {
                    ToastUtils.showToastShort(AlsID7UiLogoSelect.this.m_con, "This logo file nonsupport Android 11");
                } else if (!Build.VERSION.RELEASE.equals("10") || !((UiSelectBean) AlsID7UiLogoSelect.this.siRendata.get(pos)).getFilePath().contains("elf")) {
                    AlsID7UiLogoSelect.this.dialogViews.isUpdateLogo(AlsID7UiLogoSelect.this.getResources().getString(C0899R.string.string_is_this_logo), ((UiSelectBean) AlsID7UiLogoSelect.this.siRendata.get(pos)).getFilePath());
                    Log.d(AlsID7UiLogoSelect.TAG, "img Path:" + ((UiSelectBean) AlsID7UiLogoSelect.this.siRendata.get(pos)).getFilePath());
                } else {
                    ToastUtils.showToastShort(AlsID7UiLogoSelect.this.m_con, "This logo file nonsupport Android 10");
                }
            }
        });
        TextView textView = (TextView) this.view.findViewById(C0899R.C0901id.tvOne);
        this.tvOne = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiLogoSelect.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AlsID7UiLogoSelect.this.recyclerView.setVisibility(0);
                AlsID7UiLogoSelect.this.siRen_recyclerView.setVisibility(8);
                AlsID7UiLogoSelect.this.tvOne.setTextColor(SupportMenu.CATEGORY_MASK);
                AlsID7UiLogoSelect.this.tvTwo.setTextColor(-1);
            }
        });
        TextView textView2 = (TextView) this.view.findViewById(C0899R.C0901id.tvTwo);
        this.tvTwo = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiLogoSelect.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AlsID7UiLogoSelect.this.recyclerView.setVisibility(8);
                AlsID7UiLogoSelect.this.siRen_recyclerView.setVisibility(0);
                AlsID7UiLogoSelect.this.tvOne.setTextColor(-1);
                AlsID7UiLogoSelect.this.tvTwo.setTextColor(SupportMenu.CATEGORY_MASK);
            }
        });
        TextView textView3 = (TextView) this.view.findViewById(C0899R.C0901id.tv_Input);
        this.tv_Input = textView3;
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiLogoSelect.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AlsID7UiLogoSelect.this.dialogViews.inputLogoFile(AlsID7UiLogoSelect.this.m_con.getResources().getString(C0899R.string.text_logo_input_msg));
            }
        });
    }
}
