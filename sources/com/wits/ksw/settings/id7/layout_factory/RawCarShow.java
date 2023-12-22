package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.adapter.UiConfigAdapter;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes17.dex */
public class RawCarShow extends FrameLayout {
    private CheckBox cbox_dcld;
    private List<FunctionBean> data;
    private DialogViews dialogViews;
    private int displayIndex;
    Handler handler;
    private ImageView img_refresh;
    private LinearLayoutManager layoutManager;
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    private int possint;
    private RecyclerView recyclerView;
    private TextView tv_rawCarSele;
    private UiConfigAdapter uiConfigAdapter;
    private View view;

    public RawCarShow(Context context) {
        super(context);
        this.displayIndex = 0;
        this.possint = 0;
        this.handler = new Handler() { // from class: com.wits.ksw.settings.id7.layout_factory.RawCarShow.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        Log.d("UiConfig", "===send display index====:" + RawCarShow.this.possint);
                        for (FunctionBean fb : RawCarShow.this.data) {
                            fb.setIscheck(false);
                        }
                        ((FunctionBean) RawCarShow.this.data.get(RawCarShow.this.possint)).setIscheck(true);
                        RawCarShow.this.uiConfigAdapter.notifyDataSetChanged();
                        RawCarShow.this.tv_rawCarSele.setText(((FunctionBean) RawCarShow.this.data.get(RawCarShow.this.possint)).getTitle());
                        FileUtils.savaIntData("CarVideoDisplayStyle", RawCarShow.this.possint);
                        RawCarShow.this.handler.sendEmptyMessageDelayed(1, 300L);
                        return;
                    case 1:
                        RawCarShow.this.dialogViews.dismiss();
                        return;
                    default:
                        return;
                }
            }
        };
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(C0899R.C0902layout.factory_raw_carshow, (ViewGroup) null);
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
        this.tv_rawCarSele = (TextView) this.view.findViewById(C0899R.C0901id.tv_rawCarSele);
        if (this.data.size() > 0 && this.displayIndex < this.data.size()) {
            this.tv_rawCarSele.setText(this.data.get(this.displayIndex).getTitle());
        }
        this.dialogViews = new DialogViews(this.m_con);
        this.recyclerView = (RecyclerView) this.view.findViewById(C0899R.C0901id.rawCar_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.m_con);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(this.layoutManager);
        UiConfigAdapter uiConfigAdapter = new UiConfigAdapter(this.m_con, this.data);
        this.uiConfigAdapter = uiConfigAdapter;
        this.recyclerView.setAdapter(uiConfigAdapter);
        this.uiConfigAdapter.registCheckListener(new UiConfigAdapter.OnItemClickLisen() { // from class: com.wits.ksw.settings.id7.layout_factory.RawCarShow.2
            @Override // com.wits.ksw.settings.id7.adapter.UiConfigAdapter.OnItemClickLisen
            public void ItemClickLisen(int position) {
                RawCarShow.this.dialogViews.isSelecUi(RawCarShow.this.getResources().getString(C0899R.string.dialog_update11), RawCarShow.this.handler);
                RawCarShow.this.possint = position;
            }
        });
    }
}
