package com.wits.ksw.launcher.view.id6;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ID6FragmentTow;

/* loaded from: classes5.dex */
public class FragmentID6Two extends ID6BaseFragment implements View.OnKeyListener {
    private static final String TAG = "KswApplication";
    private ID6FragmentTow binding;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ID6FragmentTow iD6FragmentTow = (ID6FragmentTow) DataBindingUtil.inflate(inflater, C0899R.C0902layout.id6_fragment_tow, null, false);
        this.binding = iD6FragmentTow;
        return iD6FragmentTow.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.id6VideoIamgeView.setOnKeyListener(this);
        this.binding.id6BrowserImageView.setOnKeyListener(this);
        this.binding.id6CarImageView.setOnKeyListener(this);
        this.binding.id6VideoIamgeView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Two.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6Two.this.mainActivity.id6MainViewPager != null && FragmentID6Two.this.mainActivity.id6MainViewPager.getCurrentItem() != 1) {
                    FragmentID6Two.this.mainActivity.id6MainViewPager.setCurrentItem(1);
                    FragmentID6Two.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6BrowserImageView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Two.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6Two.this.mainActivity.id6MainViewPager != null && FragmentID6Two.this.mainActivity.id6MainViewPager.getCurrentItem() != 1) {
                    FragmentID6Two.this.mainActivity.id6MainViewPager.setCurrentItem(1);
                    FragmentID6Two.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6VideoIamgeView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Two.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6Two.this.viewModel.openVideoMulti(v);
                FragmentID6Two.this.setItemSelected(v);
            }
        });
        this.binding.id6CarImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Two.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6Two.this.viewModel.openCar(v);
                FragmentID6Two.this.setItemSelected(v);
            }
        });
        this.binding.id6BrowserImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Two.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6Two.this.viewModel.openBrowser(v);
                FragmentID6Two.this.setItemSelected(v);
            }
        });
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.id6VideoIamgeView.setSelected(this.binding.id6VideoIamgeView == view);
        this.binding.id6CarImageView.setSelected(this.binding.id6CarImageView == view);
        this.binding.id6BrowserImageView.setSelected(this.binding.id6BrowserImageView == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.id6VideoIamgeView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            Log.i("KswApplication", "FragmentID6Two onKey: " + keyCode);
            if (keyCode == 22) {
                this.mainActivity.id6MainViewPager.setCurrentItem(2);
                return true;
            } else if (keyCode == 21) {
                this.mainActivity.id6MainViewPager.setCurrentItem(0);
                return true;
            } else if (keyCode == 20) {
                if (v == this.binding.id6VideoIamgeView) {
                    setItemSelected(this.binding.id6CarImageView);
                } else if (v == this.binding.id6CarImageView) {
                    setItemSelected(this.binding.id6BrowserImageView);
                }
            } else if (keyCode == 19) {
                if (v == this.binding.id6BrowserImageView) {
                    setItemSelected(this.binding.id6CarImageView);
                } else if (v == this.binding.id6CarImageView) {
                    setItemSelected(this.binding.id6VideoIamgeView);
                }
            }
        }
        return false;
    }
}
