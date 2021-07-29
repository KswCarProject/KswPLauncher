package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.adapter.UiConfigAdapter;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

public class CanBusSelect extends FrameLayout {
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    /* access modifiers changed from: private */
    public DialogViews dialogViews;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Log.d("CanConfig", "===send ok index====:" + CanBusSelect.this.possint);
                    for (FunctionBean fb : CanBusSelect.this.data) {
                        fb.setIscheck(false);
                    }
                    ((FunctionBean) CanBusSelect.this.data.get(CanBusSelect.this.possint)).setIscheck(true);
                    CanBusSelect.this.tv_rawCarSele.setText(((FunctionBean) CanBusSelect.this.data.get(CanBusSelect.this.possint)).getTitle());
                    CanBusSelect.this.uiConfigAdapter.notifyDataSetChanged();
                    FileUtils.savaIntData("Protocol", CanBusSelect.this.possint);
                    CanBusSelect.this.handler.sendEmptyMessageDelayed(1, 300);
                    return;
                case 1:
                    CanBusSelect.this.dialogViews.dismiss();
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
    public UiConfigAdapter uiConfigAdapter;
    private View view;

    public CanBusSelect(Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.factory_can_bus, (ViewGroup) null);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initData() {
        this.data = new ArrayList();
        try {
            List<String> carshow = PowerManagerApp.getDataListFromJsonKey("CANBusProtocol");
            this.possint = PowerManagerApp.getSettingsInt("Protocol");
            Log.d("CanConfig", "===init can select index=====" + this.possint);
            for (String ss : carshow) {
                FunctionBean functionBean = new FunctionBean();
                functionBean.setTitle(ss);
                this.data.add(functionBean);
            }
            this.data.get(this.possint).setIscheck(true);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView() {
        if (this.possint == -1) {
            this.possint = 0;
        }
        TextView textView = (TextView) this.view.findViewById(R.id.tv_rawCarSele);
        this.tv_rawCarSele = textView;
        textView.setText(this.data.get(this.possint).getTitle());
        this.dialogViews = new DialogViews(this.m_con);
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.rawCar_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.m_con);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        UiConfigAdapter uiConfigAdapter2 = new UiConfigAdapter(this.m_con, this.data);
        this.uiConfigAdapter = uiConfigAdapter2;
        this.recyclerView.setAdapter(uiConfigAdapter2);
        this.uiConfigAdapter.registCheckListener(new UiConfigAdapter.OnItemClickLisen() {
            public void ItemClickLisen(int position) {
                CanBusSelect.this.dialogViews.isSelecUi(CanBusSelect.this.getResources().getString(R.string.dialog_update10), CanBusSelect.this.handler);
                int unused = CanBusSelect.this.possint = position;
            }
        });
    }
}
