package com.wits.ksw.settings.als_id7_ui_set;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.SkinAppCompatDelegateImpl;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.BaseActivity;
import com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiFunctionAdapter;
import com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCanBusSelect;
import com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarConfig;
import com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiCarUiConfig;
import com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiFactoryInput;
import com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiFunctionConfig;
import com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiLogoSelect;
import com.wits.ksw.settings.als_id7_ui_set.factory.AlsID7UiRawCarShow;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AlsID7UiFactoryActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public AlsID7UiFunctionAdapter adapter;
    private AlsID7UiCanBusSelect canBusSelect;
    private AlsID7UiCarConfig carConfig;
    private AlsID7UiCarUiConfig carUiConfig;
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    private AlsID7UiFactoryInput factoryInput;
    private FrameLayout factory_Frame;
    private RecyclerView factory_recycle;
    private AlsID7UiFunctionConfig functionConfig;
    private LinearLayoutManager layoutManager;
    private AlsID7UiLogoSelect logoSelect;
    private AlsID7UiRawCarShow rawCarShow;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("AlsID7UiFactoryActivity", "====onCreate=====");
        setContentView((int) R.layout.activity_als_id7_ui_factory);
        initData();
        initLayout();
        initView();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.d("AlsID7UiFactoryActivity", "====onStop=====");
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
        this.functionConfig = new AlsID7UiFunctionConfig(this);
        this.carConfig = new AlsID7UiCarConfig(this);
        this.rawCarShow = new AlsID7UiRawCarShow(this);
        this.canBusSelect = new AlsID7UiCanBusSelect(this);
        this.factoryInput = new AlsID7UiFactoryInput(this);
        this.carUiConfig = new AlsID7UiCarUiConfig(this);
        this.logoSelect = new AlsID7UiLogoSelect(this);
    }

    private void initView() {
        this.factory_recycle = (RecyclerView) findViewById(R.id.factory_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.factory_recycle.setLayoutManager(this.layoutManager);
        AlsID7UiFunctionAdapter alsID7UiFunctionAdapter = new AlsID7UiFunctionAdapter(this, this.data);
        this.adapter = alsID7UiFunctionAdapter;
        this.factory_recycle.setAdapter(alsID7UiFunctionAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, 1);
        divider.setDrawable(ContextCompat.getDrawable(this, R.mipmap.id7_lp_line));
        this.factory_recycle.addItemDecoration(divider);
        this.adapter.registOnFunctionClickListener(new AlsID7UiFunctionAdapter.OnFunctionClickListener() {
            public void functonClick(int pos) {
                AlsID7UiFactoryActivity.this.setOneLayout(pos);
                for (FunctionBean fb : AlsID7UiFactoryActivity.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) AlsID7UiFactoryActivity.this.data.get(pos)).setIscheck(true);
                AlsID7UiFactoryActivity.this.adapter.notifyDataSetChanged();
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
                this.factory_Frame.addView(this.carUiConfig);
                return;
            case 5:
                this.factory_Frame.addView(this.factoryInput);
                return;
            case 6:
                this.factory_Frame.addView(this.logoSelect);
                return;
            default:
                return;
        }
    }

    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }
}
