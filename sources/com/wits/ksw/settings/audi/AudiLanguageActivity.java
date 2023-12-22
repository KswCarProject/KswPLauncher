package com.wits.ksw.settings.audi;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.AudiLanguageViewModel;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.List;

/* loaded from: classes13.dex */
public class AudiLanguageActivity extends AudiSubActivity {
    private static final String TAG = "KswApplication." + AudiLanguageActivity.class.getSimpleName();
    private LanguageAdpater adpater;
    private ListView audiLanguageListView;
    private AudiLanguageViewModel viewModel;

    @Override // com.wits.ksw.settings.audi.AudiSubActivity, com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = LayoutInflater.from(this).inflate(C0899R.C0902layout.audi_language, (ViewGroup) null);
        this.contentLayout.addView(root, -1, -1);
        this.audiLanguageListView = (ListView) root.findViewById(C0899R.C0901id.audiLanguageListView);
        this.tv_title_set.setText(getResources().getString(C0899R.string.audi_set_item_language_text));
        LanguageAdpater languageAdpater = new LanguageAdpater(this, null);
        this.adpater = languageAdpater;
        this.audiLanguageListView.setAdapter((ListAdapter) languageAdpater);
        AudiLanguageViewModel audiLanguageViewModel = (AudiLanguageViewModel) ViewModelProviders.m59of(this).get(AudiLanguageViewModel.class);
        this.viewModel = audiLanguageViewModel;
        audiLanguageViewModel.languageDatas.observe(this, new Observer<List<FunctionBean>>() { // from class: com.wits.ksw.settings.audi.AudiLanguageActivity.1
            @Override // android.arch.lifecycle.Observer
            public void onChanged(List<FunctionBean> functionBeans) {
                Log.i(AudiLanguageActivity.TAG, "onChanged: " + functionBeans);
                AudiLanguageActivity.this.adpater.setLanguags(functionBeans);
                AudiLanguageActivity.this.adpater.notifyDataSetChanged();
            }
        });
        this.audiLanguageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.wits.ksw.settings.audi.-$$Lambda$AudiLanguageActivity$O5dSPYI5K7GRA1ZYBRwrDnJbLyM
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                AudiLanguageActivity.lambda$onCreate$0(adapterView, view, i, j);
            }
        });
        this.viewModel.updataLanguage();
        Log.i(TAG, "onCreate: ");
    }

    static /* synthetic */ void lambda$onCreate$0(AdapterView parent, View view, int position, long id) {
        try {
            Log.i(TAG, "setLanguage:" + position);
            PowerManagerApp.setSettingsInt(KeyConfig.LANGUAGE_DEFUAL, position);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /* loaded from: classes13.dex */
    class LanguageAdpater extends BaseAdapter {
        private List<FunctionBean> languags;
        private LayoutInflater layoutInflater;

        public LanguageAdpater(Context context, List<FunctionBean> datas) {
            this.languags = datas;
            this.layoutInflater = LayoutInflater.from(context);
        }

        public void setLanguags(List<FunctionBean> languags) {
            this.languags = languags;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<FunctionBean> list = this.languags;
            if (list != null && !list.isEmpty()) {
                return this.languags.size();
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public Object getItem(int position) {
            List<FunctionBean> list = this.languags;
            if (list != null && !list.isEmpty()) {
                return this.languags.get(position);
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
                convertView = this.layoutInflater.inflate(C0899R.C0902layout.audi_language_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.radioButton = (RadioButton) convertView.findViewById(C0899R.C0901id.languageRadioButton);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            FunctionBean functionBean = this.languags.get(position);
            viewHolder.radioButton.setChecked(functionBean.isIscheck());
            viewHolder.radioButton.setText(functionBean.getTitle());
            return convertView;
        }

        /* loaded from: classes13.dex */
        class ViewHolder {
            public RadioButton radioButton;

            ViewHolder() {
            }
        }
    }

    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        AudiLanguageViewModel audiLanguageViewModel = this.viewModel;
        if (audiLanguageViewModel != null) {
            audiLanguageViewModel.languageDatas.removeObservers(this);
        }
        super.onDestroy();
    }
}
