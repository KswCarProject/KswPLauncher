package com.wits.ksw.settings.audi;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.databinding.AudiNaviBinding;
import com.wits.ksw.settings.audi.p007vm.NaviVm;
import com.wits.ksw.settings.id7.bean.MapBean;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes13.dex */
public class AudiNaviActivity extends AudiSubActivity {
    private static final String TAG = "KswApplication." + AudiNaviActivity.class.getSimpleName();
    private NaviAdpater adpater;
    private AudiNaviBinding binding;
    private NaviVm naviVm;

    @Override // com.wits.ksw.settings.audi.AudiSubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (AudiNaviBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C0899R.C0902layout.audi_navi, null, false);
        this.contentLayout.addView(this.binding.getRoot(), -1, -1);
        this.naviVm = (NaviVm) ViewModelProviders.m59of(this).get(NaviVm.class);
        this.tv_title_set.setText(getResources().getString(C0899R.string.item2));
        this.adpater = new NaviAdpater(this, null);
        this.binding.naviListView.setAdapter((ListAdapter) this.adpater);
        this.binding.naviListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.wits.ksw.settings.audi.AudiNaviActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                MapBean mapBean = (MapBean) parent.getItemAtPosition(position);
                FileUtils.savaStringData(KeyConfig.NAVI_DEFUAL, mapBean.getPackageName());
                Log.i(AudiNaviActivity.TAG, "NaviApp: " + mapBean.getPackageName());
                List<MapBean> mapBeans = AudiNaviActivity.this.naviVm.naviList.getValue();
                if (mapBeans != null && !mapBeans.isEmpty()) {
                    Iterator<MapBean> it = mapBeans.iterator();
                    while (it.hasNext()) {
                        MapBean mMapBean = it.next();
                        mMapBean.setCheck(mapBean == mMapBean);
                    }
                    AudiNaviActivity.this.adpater.setMapBeans(mapBeans);
                    AudiNaviActivity.this.adpater.notifyDataSetChanged();
                }
                Settings.System.putString(KswApplication.appContext.getContentResolver(), "wits_freedom_pkg", mapBean.getPackageName());
            }
        });
        this.naviVm.naviList.observe(this, new Observer<List<MapBean>>() { // from class: com.wits.ksw.settings.audi.AudiNaviActivity.2
            @Override // android.arch.lifecycle.Observer
            public void onChanged(final List<MapBean> mapBeans) {
                Log.i(AudiNaviActivity.TAG, "run: " + mapBeans.size());
                new Handler().post(new Runnable() { // from class: com.wits.ksw.settings.audi.AudiNaviActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AudiNaviActivity.this.adpater.setMapBeans(mapBeans);
                        AudiNaviActivity.this.adpater.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    /* loaded from: classes13.dex */
    class NaviAdpater extends BaseAdapter {
        private LayoutInflater layoutInflater;
        private List<MapBean> mapBeans;

        public NaviAdpater(Context context, List<MapBean> mapBeans) {
            this.mapBeans = mapBeans;
            this.layoutInflater = LayoutInflater.from(context);
        }

        public void setMapBeans(List<MapBean> mapBeans) {
            this.mapBeans = mapBeans;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<MapBean> list = this.mapBeans;
            if (list != null && !list.isEmpty()) {
                return this.mapBeans.size();
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public Object getItem(int position) {
            List<MapBean> list = this.mapBeans;
            if (list != null && !list.isEmpty()) {
                return this.mapBeans.get(position);
            }
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int position) {
            return position;
        }

        @Override // android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = this.layoutInflater.inflate(C0899R.C0902layout.audi_navi_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.radioButton = (RadioButton) convertView.findViewById(C0899R.C0901id.naviItemTitle);
                viewHolder.icon = (ImageView) convertView.findViewById(C0899R.C0901id.naviItemIcon);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            MapBean mapBean = this.mapBeans.get(position);
            viewHolder.icon.setImageDrawable(mapBean.getMapicon());
            viewHolder.radioButton.setText(mapBean.getName());
            viewHolder.radioButton.setChecked(mapBean.isCheck());
            Log.i(AudiNaviActivity.TAG, "getView: " + mapBean.getName());
            return convertView;
        }

        /* loaded from: classes13.dex */
        class ViewHolder {
            ImageView icon;
            RadioButton radioButton;

            ViewHolder() {
            }
        }
    }
}
