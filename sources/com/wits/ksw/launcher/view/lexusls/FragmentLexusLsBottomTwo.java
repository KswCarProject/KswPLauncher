package com.wits.ksw.launcher.view.lexusls;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.databinding.LexusLsBottomFragmentTwo;

public class FragmentLexusLsBottomTwo extends LexusLsBaseBottomFragment implements View.OnKeyListener {
    private static final String TAG = "KSWLauncher";
    private LexusLsBottomFragmentTwo binding;
    IAddAppClickListener mAddAppListener;

    public interface IAddAppClickListener {
        void onClick(View view);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LexusLsBottomFragmentTwo lexusLsBottomFragmentTwo = (LexusLsBottomFragmentTwo) DataBindingUtil.inflate(inflater, R.layout.lexus_ls_bottom_fragment_two, (ViewGroup) null, false);
        this.binding = lexusLsBottomFragmentTwo;
        return lexusLsBottomFragmentTwo.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.ivLexusLsDvr.setOnKeyListener(this);
        this.binding.ivLexusLsDashboard.setOnKeyListener(this);
        this.binding.ivLexusLsPhonelink.setOnKeyListener(this);
        this.binding.ivLexusLsFile.setOnKeyListener(this);
        this.binding.ivLexusLsAdd.setOnKeyListener(this);
        this.binding.ivLexusLsDvr.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentLexusLsBottomTwo.this.mainActivity.lexusLsVpagerBottom != null && FragmentLexusLsBottomTwo.this.mainActivity.lexusLsVpagerBottom.getCurrentItem() != 1) {
                    Log.e("liuhao", "two lexusLsVpagerBottom");
                    FragmentLexusLsBottomTwo.this.mainActivity.lexusLsVpagerBottom.setCurrentItem(1);
                    FragmentLexusLsBottomTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.ivLexusLsDvr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentLexusLsBottomTwo.this.viewModel.openDvr(v);
                FragmentLexusLsBottomTwo.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsDashboard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentLexusLsBottomTwo.this.viewModel.openDashboard(v);
                FragmentLexusLsBottomTwo.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsPhonelink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentLexusLsBottomTwo.this.viewModel.openShouJiHuLian(v);
                FragmentLexusLsBottomTwo.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentLexusLsBottomTwo.this.viewModel.openFileManager(v);
                FragmentLexusLsBottomTwo.this.setItemSelected(v);
            }
        });
        this.binding.ivLexusLsAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentLexusLsBottomTwo.this.mAddAppListener.onClick(v);
                FragmentLexusLsBottomTwo.this.setItemSelected(v);
            }
        });
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        boolean z = true;
        this.binding.ivLexusLsDvr.setSelected(this.binding.ivLexusLsDvr == view);
        this.binding.ivLexusLsDashboard.setSelected(this.binding.ivLexusLsDashboard == view);
        this.binding.ivLexusLsPhonelink.setSelected(this.binding.ivLexusLsPhonelink == view);
        this.binding.ivLexusLsFile.setSelected(this.binding.ivLexusLsFile == view);
        ImageView imageView = this.binding.ivLexusLsAdd;
        if (this.binding.ivLexusLsAdd != view) {
            z = false;
        }
        imageView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.ivLexusLsDvr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            Log.i("KSWLauncher", "Fragmentid6CuspTwo onKey: " + keyCode);
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
