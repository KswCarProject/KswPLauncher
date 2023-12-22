package com.wits.ksw.settings.als_id7_ui_set;

import android.os.Bundle;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.DividerItemDecoration;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import com.wits.ksw.C0899R;
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

/* loaded from: classes6.dex */
public class AlsID7UiFactoryActivity extends BaseActivity {
    private AlsID7UiFunctionAdapter adapter;
    private AlsID7UiCanBusSelect canBusSelect;
    private AlsID7UiCarConfig carConfig;
    private AlsID7UiCarUiConfig carUiConfig;
    private List<FunctionBean> data;
    private AlsID7UiFactoryInput factoryInput;
    private FrameLayout factory_Frame;
    private RecyclerView factory_recycle;
    private AlsID7UiFunctionConfig functionConfig;
    private LinearLayoutManager layoutManager;
    private AlsID7UiLogoSelect logoSelect;
    private AlsID7UiRawCarShow rawCarShow;

    @Override // com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("AlsID7UiFactoryActivity", "====onCreate=====");
        setContentView(C0899R.C0902layout.activity_als_id7_ui_factory);
        initData();
        initLayout();
        initView();
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.d("AlsID7UiFactoryActivity", "====onStop=====");
        finish();
    }

    private boolean deleteAllByPath(File rootFilePath) {
        File[] needToDeleteFiles = rootFilePath.listFiles();
        if (needToDeleteFiles == null) {
            return true;
        }
        for (int i = 0; i < needToDeleteFiles.length; i++) {
            if (needToDeleteFiles[i].isDirectory()) {
                deleteAllByPath(needToDeleteFiles[i]);
            }
            try {
                Files.delete(needToDeleteFiles[i].toPath());
            } catch (IOException e) {
                Log.d("delet", e.getLocalizedMessage());
                return false;
            }
        }
        return true;
    }

    private void initData() {
        File logoFile = new File(FileUtils.getLogoFilePath());
        deleteAllByPath(logoFile);
        try {
            FileUtils.upZipFile(new File(FileUtils.getUpZipPath()), FileUtils.folderPath);
        } catch (IOException e) {
            Log.d("unzip", e.getLocalizedMessage());
        } catch (IllegalArgumentException e2) {
            Log.d("unzip", e2.getLocalizedMessage());
        }
        this.data = new ArrayList();
        String[] functions = getResources().getStringArray(C0899R.array.set_factory);
        for (String str : functions) {
            FunctionBean fcb = new FunctionBean();
            fcb.setTitle(str);
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
        this.factory_recycle = (RecyclerView) findViewById(C0899R.C0901id.factory_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.factory_recycle.setLayoutManager(this.layoutManager);
        AlsID7UiFunctionAdapter alsID7UiFunctionAdapter = new AlsID7UiFunctionAdapter(this, this.data);
        this.adapter = alsID7UiFunctionAdapter;
        this.factory_recycle.setAdapter(alsID7UiFunctionAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, 1);
        divider.setDrawable(ContextCompat.getDrawable(this, C0899R.mipmap.id7_lp_line));
        this.factory_recycle.addItemDecoration(divider);
        this.adapter.registOnFunctionClickListener(new AlsID7UiFunctionAdapter.OnFunctionClickListener() { // from class: com.wits.ksw.settings.als_id7_ui_set.AlsID7UiFactoryActivity.1
            @Override // com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiFunctionAdapter.OnFunctionClickListener
            public void functonClick(int pos) {
                AlsID7UiFactoryActivity.this.setOneLayout(pos);
                for (FunctionBean fb : AlsID7UiFactoryActivity.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) AlsID7UiFactoryActivity.this.data.get(pos)).setIscheck(true);
                AlsID7UiFactoryActivity.this.adapter.notifyDataSetChanged();
            }
        });
        FrameLayout frameLayout = (FrameLayout) findViewById(C0899R.C0901id.factory_Frame);
        this.factory_Frame = frameLayout;
        frameLayout.addView(this.functionConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
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
}
