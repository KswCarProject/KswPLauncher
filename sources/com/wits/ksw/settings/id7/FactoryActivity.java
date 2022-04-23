package com.wits.ksw.settings.id7;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.id7.adapter.FunctionAdapter;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.id7.layout_factory.CanBusSelect;
import com.wits.ksw.settings.id7.layout_factory.CarConfig;
import com.wits.ksw.settings.id7.layout_factory.CarUiConfig;
import com.wits.ksw.settings.id7.layout_factory.FactoryInput;
import com.wits.ksw.settings.id7.layout_factory.FunctionConfig;
import com.wits.ksw.settings.id7.layout_factory.LogoSelect;
import com.wits.ksw.settings.id7.layout_factory.MicConfig;
import com.wits.ksw.settings.id7.layout_factory.RawCarShow;
import com.wits.ksw.settings.utlis_view.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FactoryActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public FunctionAdapter adapter;
    private CanBusSelect canBusSelect;
    private CarConfig carConfig;
    private CarUiConfig carUiConfig;
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    private FactoryInput factoryInput;
    private FrameLayout factory_Frame;
    private RecyclerView factory_recycle;
    private FunctionConfig functionConfig;
    private LinearLayoutManager layoutManager;
    private LogoSelect logoSelect;
    private MicConfig micConfig;
    private RawCarShow rawCarShow;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("funtionAc", "====onCreate=====");
        setContentView((int) R.layout.activity_sys_factory);
        initData();
        initLayout();
        initView();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (UiThemeUtils.isLAND_ROVER(this)) {
            Log.w("FactoryActivity", "onresume isLAND_ROVER");
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.d("funtionAc", "====onStop=====");
        finish();
    }

    private boolean deleteAllByPath(File rootFilePath) {
        File[] needToDeleteFiles = rootFilePath.listFiles();
        if (needToDeleteFiles == null) {
            return true;
        }
        int i = 0;
        while (i < needToDeleteFiles.length) {
            if (needToDeleteFiles[i].isDirectory()) {
                deleteAllByPath(needToDeleteFiles[i]);
            }
            try {
                Files.delete(needToDeleteFiles[i].toPath());
                i++;
            } catch (IOException e) {
                Log.d("delet", e.getLocalizedMessage());
                return false;
            }
        }
        return true;
    }

    private void initData() {
        deleteAllByPath(new File("/storage/emulated/0/mylogo"));
        try {
            FileUtils.upZipFile(new File("/mnt/vendor/persist/mylogo.zip"), "/storage/emulated/0");
        } catch (IOException e) {
            Log.d("unzip", e.getLocalizedMessage());
        } catch (IllegalArgumentException e2) {
            Log.d("unzip", e2.getLocalizedMessage());
        }
        this.data = new ArrayList();
        String[] functions = getResources().getStringArray(R.array.set_factory);
        for (String title : functions) {
            FunctionBean fcb = new FunctionBean();
            fcb.setTitle(title);
            this.data.add(fcb);
        }
        this.data.get(0).setIscheck(true);
    }

    private void initLayout() {
        this.functionConfig = new FunctionConfig(this);
        this.carConfig = new CarConfig(this);
        this.rawCarShow = new RawCarShow(this);
        this.canBusSelect = new CanBusSelect(this);
        this.micConfig = new MicConfig(this);
        this.factoryInput = new FactoryInput(this);
        this.carUiConfig = new CarUiConfig(this);
        this.logoSelect = new LogoSelect(this);
    }

    private void initView() {
        this.factory_recycle = (RecyclerView) findViewById(R.id.factory_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.factory_recycle.setLayoutManager(this.layoutManager);
        FunctionAdapter functionAdapter = new FunctionAdapter(this, this.data);
        this.adapter = functionAdapter;
        this.factory_recycle.setAdapter(functionAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, 1);
        divider.setDrawable(ContextCompat.getDrawable(this, R.mipmap.id7_lp_line));
        this.factory_recycle.addItemDecoration(divider);
        this.adapter.registOnFunctionClickListener(new FunctionAdapter.OnFunctionClickListener() {
            public void functonClick(int pos) {
                FactoryActivity.this.setOneLayout(pos);
                for (FunctionBean fb : FactoryActivity.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) FactoryActivity.this.data.get(pos)).setIscheck(true);
                FactoryActivity.this.adapter.notifyDataSetChanged();
            }
        });
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.factory_Frame);
        this.factory_Frame = frameLayout;
        frameLayout.addView(this.functionConfig);
    }

    /* access modifiers changed from: private */
    public void setOneLayout(int type) {
        this.factory_Frame.removeAllViews();
        switch (type) {
            case 0:
                this.factory_Frame.addView(this.functionConfig);
                return;
            case 1:
                this.factory_Frame.addView(this.carConfig);
                return;
            case 2:
                this.factory_Frame.addView(this.rawCarShow);
                return;
            case 3:
                this.factory_Frame.addView(this.canBusSelect);
                return;
            case 4:
                this.factory_Frame.addView(this.micConfig);
                return;
            case 5:
                this.factory_Frame.addView(this.carUiConfig);
                return;
            case 6:
                this.factory_Frame.addView(this.factoryInput);
                return;
            case 7:
                this.factory_Frame.addView(this.logoSelect);
                return;
            default:
                return;
        }
    }
}
