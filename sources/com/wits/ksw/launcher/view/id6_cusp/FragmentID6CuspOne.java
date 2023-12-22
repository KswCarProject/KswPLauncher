package com.wits.ksw.launcher.view.id6_cusp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ID6CuspFragmentOne;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes7.dex */
public class FragmentID6CuspOne extends ID6CuspBaseFragment implements View.OnKeyListener {
    private static final String TAG = "KswApplication";
    private ID6CuspFragmentOne binding;
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspOne.5
        @Override // com.wits.pms.IContentObserver
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                Log.i("KswApplication", "onChange: topApp=" + topApp);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID)) {
                    MediaImpl.getInstance().initData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ID6CuspFragmentOne iD6CuspFragmentOne = (ID6CuspFragmentOne) DataBindingUtil.inflate(inflater, C0899R.C0902layout.id6_cusp_fragment_one, null, false);
        this.binding = iD6CuspFragmentOne;
        return iD6CuspFragmentOne.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        this.binding.id6CuspBtImageView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspOne.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6CuspOne.this.mainActivity.id6CuspMainViewPager != null && FragmentID6CuspOne.this.mainActivity.id6CuspMainViewPager.getCurrentItem() != 0) {
                    FragmentID6CuspOne.this.mainActivity.id6CuspMainViewPager.setCurrentItem(0);
                    FragmentID6CuspOne.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6CuspMusicIamgeView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspOne.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6CuspOne.this.viewModel.openMusicMulti(v);
                FragmentID6CuspOne.this.setItemSelected(v);
            }
        });
        this.binding.id6CuspNavImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspOne.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6CuspOne.this.viewModel.openNaviApp(v);
                FragmentID6CuspOne.this.setItemSelected(v);
            }
        });
        this.binding.id6CuspBtImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspOne.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6CuspOne.this.viewModel.openBtApp(v);
                FragmentID6CuspOne.this.setItemSelected(v);
            }
        });
        this.binding.id6CuspNavImageView.setOnKeyListener(this);
        this.binding.id6CuspMusicIamgeView.setOnKeyListener(this);
        this.binding.id6CuspBtImageView.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.id6CuspMusicIamgeView.setSelected(this.binding.id6CuspMusicIamgeView == view);
        this.binding.id6CuspNavImageView.setSelected(this.binding.id6CuspNavImageView == view);
        this.binding.id6CuspBtImageView.setSelected(this.binding.id6CuspBtImageView == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.id6CuspMusicIamgeView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            Log.i("KswApplication", "FragmentID6CuspOne onKey: " + keyCode);
            if (keyCode == 22) {
                this.mainActivity.id6CuspMainViewPager.setCurrentItem(1);
                return true;
            } else if (keyCode == 20) {
                if (v == this.binding.id6CuspMusicIamgeView) {
                    setItemSelected(this.binding.id6CuspNavImageView);
                    return false;
                } else if (v == this.binding.id6CuspNavImageView) {
                    setItemSelected(this.binding.id6CuspBtImageView);
                    return false;
                } else {
                    return false;
                }
            } else if (keyCode == 19) {
                if (v == this.binding.id6CuspBtImageView) {
                    setItemSelected(this.binding.id6CuspNavImageView);
                    return false;
                } else if (v == this.binding.id6CuspNavImageView) {
                    setItemSelected(this.binding.id6CuspMusicIamgeView);
                    return false;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }
}
