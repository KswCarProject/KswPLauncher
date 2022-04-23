package com.wits.ksw.settings.als_id7_ui_set.factory;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.als_id7_ui_set.adapter.AlsID7UiUiConfigAdapter;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

public class AlsID7UiRawCarShow extends FrameLayout {
    private CheckBox cbox_dcld;
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    /* access modifiers changed from: private */
    public DialogViews dialogViews;
    private int displayIndex = 0;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Log.d("UiConfig", "===send display index====:" + AlsID7UiRawCarShow.this.possint);
                    for (FunctionBean fb : AlsID7UiRawCarShow.this.data) {
                        fb.setIscheck(false);
                    }
                    ((FunctionBean) AlsID7UiRawCarShow.this.data.get(AlsID7UiRawCarShow.this.possint)).setIscheck(true);
                    AlsID7UiRawCarShow.this.uiConfigAdapter.notifyDataSetChanged();
                    AlsID7UiRawCarShow.this.tv_rawCarSele.setText(((FunctionBean) AlsID7UiRawCarShow.this.data.get(AlsID7UiRawCarShow.this.possint)).getTitle());
                    FileUtils.savaIntData("CarVideoDisplayStyle", AlsID7UiRawCarShow.this.possint);
                    AlsID7UiRawCarShow.this.handler.sendEmptyMessageDelayed(1, 300);
                    return;
                case 1:
                    AlsID7UiRawCarShow.this.dialogViews.dismiss();
                    return;
                default:
                    return;
            }
        }
    };
    private ImageView img_refresh;
    private LinearLayoutManager layoutManager;
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    /* access modifiers changed from: private */
    public int possint = 0;
    private RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public TextView tv_rawCarSele;
    /* access modifiers changed from: private */
    public AlsID7UiUiConfigAdapter uiConfigAdapter;
    private View view;

    public AlsID7UiRawCarShow(Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.als_id7_ui_factory_raw_carshow, (ViewGroup) null);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initData() {
        this.data = new ArrayList();
        try {
            List<String> carshow = PowerManagerApp.getDataListFromJsonKey("CarDisplayParam");
            this.displayIndex = PowerManagerApp.getSettingsInt("CarVideoDisplayStyle");
            Log.d("UiConfig", "dataSize:" + this.data.size() + "\tdisplayIndex:" + this.displayIndex);
            if (this.displayIndex < 0) {
                this.displayIndex = 0;
            }
            for (String ss : carshow) {
                FunctionBean fub = new FunctionBean();
                fub.setTitle(ss);
                this.data.add(fub);
            }
            if (this.data.size() > 0 && this.displayIndex < this.data.size()) {
                this.data.get(this.displayIndex).setIscheck(true);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView() {
        this.tv_rawCarSele = (TextView) this.view.findViewById(R.id.tv_rawCarSele);
        if (this.data.size() > 0 && this.displayIndex < this.data.size()) {
            this.tv_rawCarSele.setText(this.data.get(this.displayIndex).getTitle());
        }
        this.dialogViews = new DialogViews(this.m_con);
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.rawCar_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.m_con);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        AlsID7UiUiConfigAdapter alsID7UiUiConfigAdapter = new AlsID7UiUiConfigAdapter(this.m_con, this.data);
        this.uiConfigAdapter = alsID7UiUiConfigAdapter;
        this.recyclerView.setAdapter(alsID7UiUiConfigAdapter);
        this.uiConfigAdapter.registCheckListener(new AlsID7UiUiConfigAdapter.OnItemClickLisen() {
            public void ItemClickLisen(int position) {
                AlsID7UiRawCarShow.this.dialogViews.isSelecUi(AlsID7UiRawCarShow.this.getResources().getString(R.string.dialog_update11), AlsID7UiRawCarShow.this.handler);
                int unused = AlsID7UiRawCarShow.this.possint = position;
            }
        });
    }
}
