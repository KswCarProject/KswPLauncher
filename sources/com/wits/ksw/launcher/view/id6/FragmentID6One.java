package com.wits.ksw.launcher.view.id6;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ID6FragmentOne;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class FragmentID6One extends ID6BaseFragment implements View.OnKeyListener {
    private static final String TAG = "KswApplication";
    private ID6FragmentOne binding;
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() {
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ID6FragmentOne iD6FragmentOne = (ID6FragmentOne) DataBindingUtil.inflate(inflater, R.layout.id6_fragment_one, (ViewGroup) null, false);
        this.binding = iD6FragmentOne;
        return iD6FragmentOne.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        this.binding.id6BtImageView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6One.this.mainActivity.id6MainViewPager != null && FragmentID6One.this.mainActivity.id6MainViewPager.getCurrentItem() != 0) {
                    FragmentID6One.this.mainActivity.id6MainViewPager.setCurrentItem(0);
                    FragmentID6One.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6MusicIamgeView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6One.this.viewModel.openMusicMulti(v);
                FragmentID6One.this.setItemSelected(v);
            }
        });
        this.binding.id6NavImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6One.this.viewModel.openNaviApp(v);
                FragmentID6One.this.setItemSelected(v);
            }
        });
        this.binding.id6BtImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6One.this.viewModel.openBtApp(v);
                FragmentID6One.this.setItemSelected(v);
            }
        });
        this.binding.id6NavImageView.setOnKeyListener(this);
        this.binding.id6MusicIamgeView.setOnKeyListener(this);
        this.binding.id6BtImageView.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        boolean z = true;
        this.binding.id6MusicIamgeView.setSelected(this.binding.id6MusicIamgeView == view);
        this.binding.id6NavImageView.setSelected(this.binding.id6NavImageView == view);
        ImageView imageView = this.binding.id6BtImageView;
        if (this.binding.id6BtImageView != view) {
            z = false;
        }
        imageView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.id6MusicIamgeView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        Log.i("KswApplication", "FragmentID6One onKey: " + keyCode);
        if (keyCode == 22) {
            this.mainActivity.id6MainViewPager.setCurrentItem(1);
            return true;
        } else if (keyCode == 20) {
            if (v == this.binding.id6MusicIamgeView) {
                setItemSelected(this.binding.id6NavImageView);
                return false;
            } else if (v != this.binding.id6NavImageView) {
                return false;
            } else {
                setItemSelected(this.binding.id6BtImageView);
                return false;
            }
        } else if (keyCode != 19) {
            return false;
        } else {
            if (v == this.binding.id6BtImageView) {
                setItemSelected(this.binding.id6NavImageView);
                return false;
            } else if (v != this.binding.id6NavImageView) {
                return false;
            } else {
                setItemSelected(this.binding.id6MusicIamgeView);
                return false;
            }
        }
    }
}
