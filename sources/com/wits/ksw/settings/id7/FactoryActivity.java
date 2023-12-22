package com.wits.ksw.settings.id7;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.DividerItemDecoration;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import com.wits.ksw.C0899R;
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
import com.wits.ksw.settings.id7.layout_factory.ReverseExitTimeSelect;
import com.wits.ksw.settings.utlis_view.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class FactoryActivity extends BaseActivity {
    private FunctionAdapter adapter;
    private CanBusSelect canBusSelect;
    private CarConfig carConfig;
    private CarUiConfig carUiConfig;
    private List<FunctionBean> data;
    private FactoryInput factoryInput;
    private FrameLayout factory_Frame;
    private RecyclerView factory_recycle;
    private FunctionConfig functionConfig;
    private LinearLayoutManager layoutManager;
    private LogoSelect logoSelect;
    private MicConfig micConfig;
    private RawCarShow rawCarShow;
    private ReverseExitTimeSelect reverseExitTimeSelect;

    @Override // com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("funtionAc", "====onCreate=====");
        setContentView(C0899R.C0902layout.activity_sys_factory);
        initData();
        initLayout();
        initView();
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (UiThemeUtils.isLAND_ROVER(this)) {
            Log.w("FactoryActivity", "onresume isLAND_ROVER");
        }
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.d("funtionAc", "====onStop=====");
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
        new TestAsyncTask().execute(new Integer[0]);
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
        this.functionConfig = new FunctionConfig(this);
        this.carConfig = new CarConfig(this);
        this.rawCarShow = new RawCarShow(this);
        this.canBusSelect = new CanBusSelect(this);
        this.micConfig = new MicConfig(this);
        this.reverseExitTimeSelect = new ReverseExitTimeSelect(this);
        this.factoryInput = new FactoryInput(this);
        this.carUiConfig = new CarUiConfig(this);
        this.logoSelect = new LogoSelect(this);
    }

    private void initView() {
        this.factory_recycle = (RecyclerView) findViewById(C0899R.C0901id.factory_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.factory_recycle.setLayoutManager(this.layoutManager);
        FunctionAdapter functionAdapter = new FunctionAdapter(this, this.data);
        this.adapter = functionAdapter;
        this.factory_recycle.setAdapter(functionAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, 1);
        divider.setDrawable(ContextCompat.getDrawable(this, C0899R.mipmap.id7_lp_line));
        this.factory_recycle.addItemDecoration(divider);
        this.adapter.registOnFunctionClickListener(new FunctionAdapter.OnFunctionClickListener() { // from class: com.wits.ksw.settings.id7.FactoryActivity.1
            @Override // com.wits.ksw.settings.id7.adapter.FunctionAdapter.OnFunctionClickListener
            public void functonClick(int pos) {
                FactoryActivity.this.setOneLayout(pos);
                for (FunctionBean fb : FactoryActivity.this.data) {
                    fb.setIscheck(false);
                }
                ((FunctionBean) FactoryActivity.this.data.get(pos)).setIscheck(true);
                FactoryActivity.this.adapter.notifyDataSetChanged();
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
                this.factory_Frame.addView(this.micConfig);
                return;
            case 5:
                this.factory_Frame.addView(this.reverseExitTimeSelect);
                return;
            case 6:
                this.factory_Frame.addView(this.carUiConfig);
                return;
            case 7:
                this.factory_Frame.addView(this.factoryInput);
                return;
            case 8:
                this.factory_Frame.addView(this.logoSelect);
                return;
            default:
                return;
        }
    }

    /* loaded from: classes16.dex */
    public class TestAsyncTask extends AsyncTask<Integer, Integer, Bitmap> {
        public TestAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(Integer... integers) {
            try {
                FileUtils.upZipFile(new File(FileUtils.getUpZipPath()), FileUtils.folderPath);
                return null;
            } catch (IOException e) {
                Log.d("unzip", e.getLocalizedMessage());
                return null;
            } catch (IllegalArgumentException e2) {
                Log.d("unzip", e2.getLocalizedMessage());
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap result) {
            super.onPostExecute((TestAsyncTask) result);
            FactoryActivity.this.logoSelect.setAdapterData();
        }
    }
}
