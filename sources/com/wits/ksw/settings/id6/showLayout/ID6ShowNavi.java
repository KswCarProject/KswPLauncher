package com.wits.ksw.settings.id6.showLayout;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.id6.adapter.ID6NaviAdapter;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.List;

public class ID6ShowNavi extends RelativeLayout {
    private Context context;
    private LinearLayoutManager layoutManager;
    private List<MapBean> mapBanList;
    private ID6NaviAdapter naviAdapter;
    private RecyclerView navi_recycle;
    private View view;

    public ID6ShowNavi(Context context2, List<MapBean> data) {
        super(context2);
        this.context = context2;
        this.mapBanList = data;
        initData();
        this.view = LayoutInflater.from(context2).inflate(R.layout.layout_id6_navi_shwo, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initView(this.view);
        this.view.setLayoutParams(layoutParams);
        addView(this.view);
    }

    private void initData() {
        try {
            String defNavi = PowerManagerApp.getSettingsString(KeyConfig.NAVI_DEFUAL);
            Log.d("showDef", "pack:" + defNavi);
            for (MapBean mb : this.mapBanList) {
                if (mb.getPackageName().equals(defNavi)) {
                    mb.setCheck(true);
                } else {
                    mb.setCheck(false);
                }
            }
        } catch (Exception e) {
        }
    }

    public void updateList(List<MapBean> list) {
        this.mapBanList.clear();
        this.mapBanList.addAll(list);
        this.naviAdapter.notifyDataSetChanged();
    }

    private void initView(View view2) {
        this.navi_recycle = (RecyclerView) view2.findViewById(R.id.navi_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.navi_recycle.setLayoutManager(this.layoutManager);
        ID6NaviAdapter iD6NaviAdapter = new ID6NaviAdapter(this.context, this.mapBanList, true);
        this.naviAdapter = iD6NaviAdapter;
        this.navi_recycle.setAdapter(iD6NaviAdapter);
        this.navi_recycle.setEnabled(false);
    }

    public void onUpdateView() {
        initData();
        initView(this.view);
    }
}
