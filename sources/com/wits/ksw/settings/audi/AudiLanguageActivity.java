package com.wits.ksw.settings.audi;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
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
    public static final String TAG = ("KswApplication." + AudiLanguageActivity.class.getSimpleName());
    /* access modifiers changed from: private */
    public LanguageAdpater adpater;
    private ListView audiLanguageListView;
    private AudiLanguageViewModel viewModel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = LayoutInflater.from(this).inflate(R.layout.audi_language, (ViewGroup) null);
        this.contentLayout.addView(root, -1, -1);
        this.audiLanguageListView = (ListView) root.findViewById(R.id.audiLanguageListView);
        this.tv_title_set.setText(getResources().getString(R.string.audi_set_item_language_text));
        LanguageAdpater languageAdpater = new LanguageAdpater(this, (List<FunctionBean>) null);
        this.adpater = languageAdpater;
        this.audiLanguageListView.setAdapter(languageAdpater);
        AudiLanguageViewModel audiLanguageViewModel = (AudiLanguageViewModel) ViewModelProviders.of((FragmentActivity) this).get(AudiLanguageViewModel.class);
        this.viewModel = audiLanguageViewModel;
        audiLanguageViewModel.languageDatas.observe(this, new Observer<List<FunctionBean>>() {
            public void onChanged(List<FunctionBean> functionBeans) {
                Log.i(AudiLanguageActivity.TAG, "onChanged: " + functionBeans);
                AudiLanguageActivity.this.adpater.setLanguags(functionBeans);
                AudiLanguageActivity.this.adpater.notifyDataSetChanged();
            }
        });
        this.audiLanguageListView.setOnItemClickListener($$Lambda$AudiLanguageActivity$O5dSPYI5K7GRA1ZYBRwrDnJbLyM.INSTANCE);
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
            List<FunctionBean> list = this.languags;
            if (list == null || list.isEmpty()) {
                return 0;
            }
            return this.languags.size();
        }

        public Object getItem(int position) {
            List<FunctionBean> list = this.languags;
            if (list == null || list.isEmpty()) {
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
        AudiLanguageViewModel audiLanguageViewModel = this.viewModel;
        if (audiLanguageViewModel != null) {
            audiLanguageViewModel.languageDatas.removeObservers(this);
        }
        super.onDestroy();
    }
}
