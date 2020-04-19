package com.wits.ksw.settings.audi;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiLanguageViewModel;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.List;

public class AudiLanguageActivity extends AudiSubActivity {
    /* access modifiers changed from: private */
    public static final String TAG = ("KSWLauncher." + AudiLanguageActivity.class.getSimpleName());
    /* access modifiers changed from: private */
    public LanguageAdpater adpater;
    private ListView audiLanguageListView;
    private AudiLanguageViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = LayoutInflater.from(this).inflate(R.layout.audi_language, (ViewGroup) null);
        this.contentLayout.addView(root, -1, -1);
        this.audiLanguageListView = (ListView) root.findViewById(R.id.audiLanguageListView);
        this.adpater = new LanguageAdpater(this, (List<FunctionBean>) null);
        this.audiLanguageListView.setAdapter(this.adpater);
        this.viewModel = (AudiLanguageViewModel) ViewModelProviders.of((FragmentActivity) this).get(AudiLanguageViewModel.class);
        this.viewModel.languageDatas.observe(this, new Observer<List<FunctionBean>>() {
            public void onChanged(@Nullable List<FunctionBean> functionBeans) {
                String access$000 = AudiLanguageActivity.TAG;
                Log.i(access$000, "onChanged: " + functionBeans);
                AudiLanguageActivity.this.adpater.setLanguags(functionBeans);
                AudiLanguageActivity.this.adpater.notifyDataSetChanged();
            }
        });
        this.audiLanguageListView.setOnItemClickListener($$Lambda$AudiLanguageActivity$uw5LvsZq476kVM2_12RMxzUcOY.INSTANCE);
        this.viewModel.updataLanguage();
        Log.i(TAG, "onCreate: ");
    }

    static /* synthetic */ void lambda$onCreate$0(AdapterView parent, View view, int position, long id) {
        try {
            String str = TAG;
            Log.i(str, "setLanguage:" + position);
            PowerManagerApp.setSettingsInt(KeyConfig.LANGUAGE_DEFUAL, position);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    class LanguageAdpater extends BaseAdapter {
        private List<FunctionBean> languags;
        private LayoutInflater layoutInflater;

        public LanguageAdpater(Context context, List<FunctionBean> datas) {
            this.languags = datas;
            this.layoutInflater = LayoutInflater.from(context);
        }

        public void setLanguags(List<FunctionBean> languags2) {
            this.languags = languags2;
        }

        public int getCount() {
            if (this.languags == null || this.languags.isEmpty()) {
                return 0;
            }
            return this.languags.size();
        }

        public Object getItem(int position) {
            if (this.languags == null || this.languags.isEmpty()) {
                return null;
            }
            return this.languags.get(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = this.layoutInflater.inflate(R.layout.audi_language_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.radioButton = (RadioButton) convertView.findViewById(R.id.languageRadioButton);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            FunctionBean functionBean = this.languags.get(position);
            viewHolder.radioButton.setChecked(functionBean.isIscheck());
            viewHolder.radioButton.setText(functionBean.getTitle());
            return convertView;
        }

        class ViewHolder {
            public RadioButton radioButton;

            ViewHolder() {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.viewModel != null) {
            this.viewModel.languageDatas.removeObservers(this);
        }
        super.onDestroy();
    }
}
