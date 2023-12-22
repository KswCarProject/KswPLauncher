package com.wits.ksw.settings.id6.oneLayout;

import android.content.Context;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id6.adapter.ID6NaviAdapter;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import java.util.List;

/* loaded from: classes7.dex */
public class ID6NaviLayout extends RelativeLayout {
    private Context context;
    private LinearLayoutManager layoutManager;
    private List<MapBean> mapBanList;
    private ID6NaviAdapter naviAdapter;
    private RecyclerView navi_recycle;

    public ID6NaviLayout(Context context, List<MapBean> data) {
        super(context);
        this.context = context;
        this.mapBanList = data;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_id6_navi, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    public void updateList(List<MapBean> list) {
        this.mapBanList.clear();
        this.mapBanList.addAll(list);
        this.naviAdapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        this.navi_recycle = (RecyclerView) view.findViewById(C0899R.C0901id.navi_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.navi_recycle.setLayoutManager(this.layoutManager);
        ID6NaviAdapter iD6NaviAdapter = new ID6NaviAdapter(this.context, this.mapBanList, false);
        this.naviAdapter = iD6NaviAdapter;
        this.navi_recycle.setAdapter(iD6NaviAdapter);
        this.naviAdapter.registCheckListener(new ID6NaviAdapter.IrbtCheckListener() { // from class: com.wits.ksw.settings.id6.oneLayout.ID6NaviLayout.1
            @Override // com.wits.ksw.settings.id6.adapter.ID6NaviAdapter.IrbtCheckListener
            public void checkListener(int pos) {
                MapBean mapBean = (MapBean) ID6NaviLayout.this.mapBanList.get(pos);
                if (mapBean != null) {
                    FileUtils.savaStringData(KeyConfig.NAVI_DEFUAL, mapBean.getPackageName());
                }
            }
        });
    }
}
