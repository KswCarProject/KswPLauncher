package com.wits.ksw.settings.lexus.layout_two;

import android.content.Context;
import android.provider.Settings;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.DividerItemDecoration;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.lexus.adapter.NaviAdapter;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.List;

/* loaded from: classes16.dex */
public class LexusNaviTwo extends RelativeLayout {
    private Context context;
    private LinearLayoutManager layoutManager;
    private List<MapBean> mapBanList;
    private NaviAdapter naviAdapter;
    private int naviMin;
    private RecyclerView navi_recycle;
    private RadioGroup rdg_naviv;
    private RelativeLayout relate_app;
    private RelativeLayout relate_naviv;

    public LexusNaviTwo(Context context) {
        super(context);
        this.naviMin = 1;
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.lexus_layout_set_navi, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    public void updateMapList(List<MapBean> mapList) {
        this.mapBanList = mapList;
        Log.d("Navi", "mapBanList size: " + this.mapBanList.size());
        this.naviAdapter.notifyDataSetChanged();
    }

    private void initData() {
        try {
            this.naviMin = PowerManagerApp.getSettingsInt(KeyConfig.NAVI_VOICE_MIX);
            Log.d("Navi", "==min==:" + this.naviMin);
            this.mapBanList = ScanNaviList.getInstance().getMapList();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view) {
        this.relate_naviv = (RelativeLayout) view.findViewById(C0899R.C0901id.relate_naviv);
        this.relate_app = (RelativeLayout) view.findViewById(C0899R.C0901id.relate_app);
        this.navi_recycle = (RecyclerView) view.findViewById(C0899R.C0901id.navi_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.navi_recycle.setLayoutManager(this.layoutManager);
        this.naviAdapter = new NaviAdapter(this.context, this.mapBanList);
        Log.d("Navi", "init  mapBanList " + this.mapBanList.size());
        this.navi_recycle.setAdapter(this.naviAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this.context, 1);
        divider.setDrawable(ContextCompat.getDrawable(this.context, C0899R.C0900drawable.lexus_settings_line_left));
        this.navi_recycle.addItemDecoration(divider);
        this.naviAdapter.registCheckListener(new NaviAdapter.IrbtCheckListener() { // from class: com.wits.ksw.settings.lexus.layout_two.LexusNaviTwo.1
            @Override // com.wits.ksw.settings.lexus.adapter.NaviAdapter.IrbtCheckListener
            public void checkListener(int pos) {
                MapBean mapBean = (MapBean) LexusNaviTwo.this.mapBanList.get(pos);
                if (mapBean != null) {
                    FileUtils.savaStringData(KeyConfig.NAVI_DEFUAL, mapBean.getPackageName());
                    mapBean.setCheck(true);
                    Settings.System.putString(LexusNaviTwo.this.context.getContentResolver(), "wits_freedom_pkg", mapBean.getPackageName());
                }
            }
        });
        RadioGroup radioGroup = (RadioGroup) view.findViewById(C0899R.C0901id.rdg_naviv);
        this.rdg_naviv = radioGroup;
        switch (this.naviMin) {
            case 0:
                radioGroup.check(C0899R.C0901id.rdb_naviv1);
                break;
            case 1:
                radioGroup.check(C0899R.C0901id.rdb_naviv2);
                break;
            case 2:
                radioGroup.check(C0899R.C0901id.rdb_naviv3);
                break;
            case 3:
                radioGroup.check(C0899R.C0901id.rdb_naviv4);
                break;
            case 4:
                radioGroup.check(C0899R.C0901id.rdb_naviv5);
                break;
            case 5:
                radioGroup.check(C0899R.C0901id.rdb_naviv6);
                break;
        }
        this.rdg_naviv.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.lexus.layout_two.LexusNaviTwo.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case C0899R.C0901id.rdb_naviv1 /* 2131297485 */:
                        FileUtils.savaIntData(KeyConfig.NAVI_VOICE_MIX, 0);
                        return;
                    case C0899R.C0901id.rdb_naviv2 /* 2131297486 */:
                        FileUtils.savaIntData(KeyConfig.NAVI_VOICE_MIX, 1);
                        return;
                    case C0899R.C0901id.rdb_naviv3 /* 2131297487 */:
                        FileUtils.savaIntData(KeyConfig.NAVI_VOICE_MIX, 2);
                        return;
                    case C0899R.C0901id.rdb_naviv4 /* 2131297488 */:
                        FileUtils.savaIntData(KeyConfig.NAVI_VOICE_MIX, 3);
                        return;
                    case C0899R.C0901id.rdb_naviv5 /* 2131297489 */:
                        FileUtils.savaIntData(KeyConfig.NAVI_VOICE_MIX, 4);
                        return;
                    case C0899R.C0901id.rdb_naviv6 /* 2131297490 */:
                        FileUtils.savaIntData(KeyConfig.NAVI_VOICE_MIX, 5);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void showLayout(int index) {
        switch (index) {
            case 0:
                this.relate_naviv.setVisibility(0);
                this.relate_app.setVisibility(8);
                return;
            case 1:
                this.relate_naviv.setVisibility(8);
                this.relate_app.setVisibility(0);
                return;
            default:
                return;
        }
    }
}
