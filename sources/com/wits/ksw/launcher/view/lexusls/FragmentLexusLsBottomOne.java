package com.wits.ksw.launcher.view.lexusls;

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
import com.wits.ksw.databinding.LexusLsBottomFragmentOne;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes14.dex */
public class FragmentLexusLsBottomOne extends LexusLsBaseBottomFragment implements View.OnKeyListener {
    private static final String TAG = "KswApplication";
    private LexusLsBottomFragmentOne binding;
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne.10
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
        LexusLsBottomFragmentOne lexusLsBottomFragmentOne = (LexusLsBottomFragmentOne) DataBindingUtil.inflate(inflater, C0899R.C0902layout.lexus_ls_bottom_fragment_one, null, false);
        this.binding = lexusLsBottomFragmentOne;
        return lexusLsBottomFragmentOne.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        this.binding.ivLexusLsAir.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentLexusLsBottomOne.this.mainActivity.lexusLsVpagerBottom != null && FragmentLexusLsBottomOne.this.mainActivity.lexusLsVpagerBottom.getCurrentItem() != 0) {
                    Log.e("liuhao", "One lexusLsVpagerBottom");
                    FragmentLexusLsBottomOne.this.mainActivity.lexusLsVpagerBottom.setCurrentItem(0);
                    FragmentLexusLsBottomOne.this.setItemSelected(v);
                }
            }
        });
        this.binding.ivLexusLsNavi.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomOne.this.viewModel.openNaviApp(v);
                FragmentLexusLsBottomOne.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsMusic.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomOne.this.viewModel.openChoseMusic(v);
                FragmentLexusLsBottomOne.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsBt.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomOne.this.viewModel.openBtApp(v);
                FragmentLexusLsBottomOne.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsApp.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomOne.this.viewModel.openApps(v);
                FragmentLexusLsBottomOne.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsVideo.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomOne.this.viewModel.openVideo(v);
                FragmentLexusLsBottomOne.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsCar.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomOne.this.viewModel.openLexusCar(v);
                FragmentLexusLsBottomOne.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsSet.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne.8
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomOne.this.viewModel.openSettings(v);
                FragmentLexusLsBottomOne.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsAir.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne.9
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomOne.this.viewModel.openAirControl(v);
                FragmentLexusLsBottomOne.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsNavi.setOnKeyListener(this);
        this.binding.ivLexusLsMusic.setOnKeyListener(this);
        this.binding.ivLexusLsBt.setOnKeyListener(this);
        this.binding.ivLexusLsApp.setOnKeyListener(this);
        this.binding.ivLexusLsVideo.setOnKeyListener(this);
        this.binding.ivLexusLsCar.setOnKeyListener(this);
        this.binding.ivLexusLsSet.setOnKeyListener(this);
        this.binding.ivLexusLsAir.setOnKeyListener(this);
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.ivLexusLsNavi.setSelected(this.binding.ivLexusLsNavi == view);
        this.binding.ivLexusLsMusic.setSelected(this.binding.ivLexusLsMusic == view);
        this.binding.ivLexusLsBt.setSelected(this.binding.ivLexusLsBt == view);
        this.binding.ivLexusLsApp.setSelected(this.binding.ivLexusLsApp == view);
        this.binding.ivLexusLsVideo.setSelected(this.binding.ivLexusLsVideo == view);
        this.binding.ivLexusLsCar.setSelected(this.binding.ivLexusLsCar == view);
        this.binding.ivLexusLsSet.setSelected(this.binding.ivLexusLsSet == view);
        this.binding.ivLexusLsAir.setSelected(this.binding.ivLexusLsAir == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.ivLexusLsNavi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            Log.i("KswApplication", "FragmentLexusLsBottomOne onKey: " + keyCode);
            if (keyCode == 22) {
                this.mainActivity.lexusLsVpagerBottom.setCurrentItem(1);
                return true;
            } else if (keyCode == 21) {
                this.mainActivity.lexusLsVpagerBottom.setCurrentItem(0);
                return true;
            } else if (keyCode == 20) {
                if (v == this.binding.ivLexusLsNavi) {
                    setItemSelected(this.binding.ivLexusLsMusic);
                } else if (v == this.binding.ivLexusLsMusic) {
                    setItemSelected(this.binding.ivLexusLsBt);
                } else if (v == this.binding.ivLexusLsBt) {
                    setItemSelected(this.binding.ivLexusLsApp);
                } else if (v == this.binding.ivLexusLsApp) {
                    setItemSelected(this.binding.ivLexusLsVideo);
                } else if (v == this.binding.ivLexusLsVideo) {
                    setItemSelected(this.binding.ivLexusLsCar);
                } else if (v == this.binding.ivLexusLsCar) {
                    setItemSelected(this.binding.ivLexusLsSet);
                } else if (v == this.binding.ivLexusLsSet) {
                    setItemSelected(this.binding.ivLexusLsAir);
                }
            } else if (keyCode == 19) {
                if (v == this.binding.ivLexusLsAir) {
                    setItemSelected(this.binding.ivLexusLsSet);
                } else if (v == this.binding.ivLexusLsSet) {
                    setItemSelected(this.binding.ivLexusLsCar);
                } else if (v == this.binding.ivLexusLsCar) {
                    setItemSelected(this.binding.ivLexusLsVideo);
                } else if (v == this.binding.ivLexusLsVideo) {
                    setItemSelected(this.binding.ivLexusLsApp);
                } else if (v == this.binding.ivLexusLsApp) {
                    setItemSelected(this.binding.ivLexusLsBt);
                } else if (v == this.binding.ivLexusLsBt) {
                    setItemSelected(this.binding.ivLexusLsMusic);
                } else if (v == this.binding.ivLexusLsMusic) {
                    setItemSelected(this.binding.ivLexusLsNavi);
                } else if (v == this.binding.ivLexusLsNavi) {
                    setItemSelected(this.binding.ivLexusLsNavi);
                }
            }
        }
        return false;
    }
}
