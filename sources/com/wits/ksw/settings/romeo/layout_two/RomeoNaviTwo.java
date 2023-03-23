package com.wits.ksw.settings.romeo.layout_two;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.FixLinearSnapHelper;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.romeo.adapter.NaviAdapter;
import com.wits.ksw.settings.romeo.interfaces.IUpdateListBg;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.ScanNaviList;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.List;

public class RomeoNaviTwo extends RelativeLayout {
    private Context context;
    private LinearLayoutManager layoutManager;
    /* access modifiers changed from: private */
    public List<MapBean> mapBanList;
    private NaviAdapter naviAdapter;
    private int naviMin = 1;
    private RecyclerView navi_recycle;
    private RadioGroup rdg_naviv;
    private RelativeLayout relate_app;
    private RelativeLayout relate_naviv;
    /* access modifiers changed from: private */
    public IUpdateListBg updateListBg;

    public RomeoNaviTwo(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.romeo_layout_set_navi, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    public void registIUpdateListBg(IUpdateListBg updateListBg2) {
        this.updateListBg = updateListBg2;
        this.naviAdapter.setIUpdateListBg(updateListBg2);
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

    private void changeItemBg(final ViewGroup viewGroup, Context context2) {
        Log.d("NaviTwo", "changeItemBg count=" + viewGroup.getChildCount());
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final int finalI = i;
            viewGroup.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int[] locW = new int[2];
                    viewGroup.getChildAt(finalI).getLocationInWindow(locW);
                    if (motionEvent.getAction() == 0) {
                        Log.d("NaviTwo", "changeItemBg onTouch y=" + locW[1]);
                        RomeoNaviTwo.this.updateListBg.updateListBg(locW[1] - 78, 2);
                    } else if (motionEvent.getAction() == 1) {
                        RomeoNaviTwo.this.updateListBg.updateListBg(locW[1] - 78, 0);
                    } else if (motionEvent.getAction() == 3) {
                        RomeoNaviTwo.this.updateListBg.updateListBg(locW[1] - 78, 0);
                    }
                    return false;
                }
            });
            viewGroup.getChildAt(i).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        int[] locW = new int[2];
                        viewGroup.getChildAt(finalI).getLocationInWindow(locW);
                        Log.d("NaviTwo", "changeItemBg onFocusChange y=" + locW[1]);
                        RomeoNaviTwo.this.updateListBg.updateListBg(locW[1] - 78, 1);
                    }
                }
            });
        }
    }

    private void initView(View view) {
        this.relate_naviv = (RelativeLayout) view.findViewById(R.id.relate_naviv);
        this.relate_app = (RelativeLayout) view.findViewById(R.id.relate_app);
        this.navi_recycle = (RecyclerView) view.findViewById(R.id.navi_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.navi_recycle.setLayoutManager(this.layoutManager);
        this.navi_recycle.setItemViewCacheSize(0);
        new FixLinearSnapHelper().attachToRecyclerView(this.navi_recycle);
        this.naviAdapter = new NaviAdapter(this.context, this.mapBanList);
        Log.d("Navi", "init  mapBanList " + this.mapBanList.size());
        this.navi_recycle.setAdapter(this.naviAdapter);
        this.naviAdapter.setIUpdateListBg(this.updateListBg);
        this.navi_recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    int pad = KswUtils.calculateTranslate(recyclerView.getChildAt(i).getTop(), KswUtils.dip2px(RomeoNaviTwo.this.getContext(), 428.0f), i, RomeoNaviTwo.this.getContext());
                    recyclerView.getChildAt(i).setPadding(pad, 0, pad, 0);
                }
            }
        });
        new DividerItemDecoration(this.context, 1);
        this.naviAdapter.registCheckListener(new NaviAdapter.IrbtCheckListener() {
            public void checkListener(int pos) {
                MapBean mapBean = (MapBean) RomeoNaviTwo.this.mapBanList.get(pos);
                if (mapBean != null) {
                    FileUtils.savaStringData(KeyConfig.NAVI_DEFUAL, mapBean.getPackageName());
                }
            }
        });
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rdg_naviv);
        this.rdg_naviv = radioGroup;
        switch (this.naviMin) {
            case 0:
                radioGroup.check(R.id.rdb_naviv1);
                break;
            case 1:
                radioGroup.check(R.id.rdb_naviv2);
                break;
            case 2:
                radioGroup.check(R.id.rdb_naviv3);
                break;
            case 3:
                radioGroup.check(R.id.rdb_naviv4);
                break;
            case 4:
                radioGroup.check(R.id.rdb_naviv5);
                break;
            case 5:
                radioGroup.check(R.id.rdb_naviv6);
                break;
        }
        this.rdg_naviv.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdb_naviv1:
                        FileUtils.savaIntData(KeyConfig.NAVI_VOICE_MIX, 0);
                        return;
                    case R.id.rdb_naviv2:
                        FileUtils.savaIntData(KeyConfig.NAVI_VOICE_MIX, 1);
                        return;
                    case R.id.rdb_naviv3:
                        FileUtils.savaIntData(KeyConfig.NAVI_VOICE_MIX, 2);
                        return;
                    case R.id.rdb_naviv4:
                        FileUtils.savaIntData(KeyConfig.NAVI_VOICE_MIX, 3);
                        return;
                    case R.id.rdb_naviv5:
                        FileUtils.savaIntData(KeyConfig.NAVI_VOICE_MIX, 4);
                        return;
                    case R.id.rdb_naviv6:
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
