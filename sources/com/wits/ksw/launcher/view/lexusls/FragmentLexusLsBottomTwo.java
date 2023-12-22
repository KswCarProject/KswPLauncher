package com.wits.ksw.launcher.view.lexusls;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.LexusLsBottomFragmentTwo;

/* loaded from: classes14.dex */
public class FragmentLexusLsBottomTwo extends LexusLsBaseBottomFragment implements View.OnKeyListener {
    private static final String TAG = "KswApplication";
    private LexusLsBottomFragmentTwo binding;
    IAddAppClickListener mAddAppListener;

    /* loaded from: classes14.dex */
    public interface IAddAppClickListener {
        void onClick(View view);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LexusLsBottomFragmentTwo lexusLsBottomFragmentTwo = (LexusLsBottomFragmentTwo) DataBindingUtil.inflate(inflater, C0899R.C0902layout.lexus_ls_bottom_fragment_two, null, false);
        this.binding = lexusLsBottomFragmentTwo;
        return lexusLsBottomFragmentTwo.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.ivLexusLsDvr.setOnKeyListener(this);
        this.binding.ivLexusLsDashboard.setOnKeyListener(this);
        this.binding.ivLexusLsPhonelink.setOnKeyListener(this);
        this.binding.ivLexusLsFile.setOnKeyListener(this);
        this.binding.ivLexusLsAdd.setOnKeyListener(this);
        this.binding.ivLexusLsDvr.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwo.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentLexusLsBottomTwo.this.mainActivity.lexusLsVpagerBottom != null && FragmentLexusLsBottomTwo.this.mainActivity.lexusLsVpagerBottom.getCurrentItem() != 1) {
                    Log.e("liuhao", "two lexusLsVpagerBottom");
                    FragmentLexusLsBottomTwo.this.mainActivity.lexusLsVpagerBottom.setCurrentItem(1);
                    FragmentLexusLsBottomTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.ivLexusLsDvr.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwo.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomTwo.this.viewModel.openDvr(v);
                FragmentLexusLsBottomTwo.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsDashboard.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwo.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomTwo.this.viewModel.openDashboard(v);
                FragmentLexusLsBottomTwo.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsPhonelink.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwo.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomTwo.this.viewModel.openShouJiHuLian(v);
                FragmentLexusLsBottomTwo.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsFile.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwo.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomTwo.this.viewModel.openFileManager(v);
                FragmentLexusLsBottomTwo.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsAdd.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwo.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentLexusLsBottomTwo.this.mAddAppListener.onClick(v);
                FragmentLexusLsBottomTwo.this.setItemSelected(v);
            }
        });
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.ivLexusLsDvr.setSelected(this.binding.ivLexusLsDvr == view);
        this.binding.ivLexusLsDashboard.setSelected(this.binding.ivLexusLsDashboard == view);
        this.binding.ivLexusLsPhonelink.setSelected(this.binding.ivLexusLsPhonelink == view);
        this.binding.ivLexusLsFile.setSelected(this.binding.ivLexusLsFile == view);
        this.binding.ivLexusLsAdd.setSelected(this.binding.ivLexusLsAdd == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.ivLexusLsDvr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            Log.i("KswApplication", "Fragmentid6CuspTwo onKey: " + keyCode);
            if (keyCode == 22) {
                this.mainActivity.id6CuspMainViewPager.setCurrentItem(2);
                return true;
            } else if (keyCode == 21) {
                this.mainActivity.id6CuspMainViewPager.setCurrentItem(0);
                return true;
            } else if (keyCode == 20) {
                if (v == this.binding.ivLexusLsDvr) {
                    setItemSelected(this.binding.ivLexusLsDashboard);
                } else if (v == this.binding.ivLexusLsDashboard) {
                    setItemSelected(this.binding.ivLexusLsPhonelink);
                } else if (v == this.binding.ivLexusLsPhonelink) {
                    setItemSelected(this.binding.ivLexusLsFile);
                } else if (v == this.binding.ivLexusLsFile) {
                    setItemSelected(this.binding.ivLexusLsAdd);
                } else if (v == this.binding.ivLexusLsAdd) {
                    setItemSelected(this.binding.ivLexusLsAdd);
                }
            } else if (keyCode == 19) {
                if (v == this.binding.ivLexusLsAdd) {
                    setItemSelected(this.binding.ivLexusLsFile);
                } else if (v == this.binding.ivLexusLsFile) {
                    setItemSelected(this.binding.ivLexusLsFile);
                } else if (v == this.binding.ivLexusLsFile) {
                    setItemSelected(this.binding.ivLexusLsDashboard);
                } else if (v == this.binding.ivLexusLsDashboard) {
                    setItemSelected(this.binding.ivLexusLsFile);
                }
            }
        }
        return false;
    }

    public void setOnClickAddApp(IAddAppClickListener tmpListener) {
        this.mAddAppListener = tmpListener;
    }
}
