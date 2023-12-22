package com.wits.ksw.launcher.view.id6_cusp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ID6CuspFragmentTow;

/* loaded from: classes7.dex */
public class FragmentID6CuspTwo extends ID6CuspBaseFragment implements View.OnKeyListener {
    private static final String TAG = "KswApplication";
    private ID6CuspFragmentTow binding;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ID6CuspFragmentTow iD6CuspFragmentTow = (ID6CuspFragmentTow) DataBindingUtil.inflate(inflater, C0899R.C0902layout.id6_cusp_fragment_tow, null, false);
        this.binding = iD6CuspFragmentTow;
        return iD6CuspFragmentTow.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.id6CuspVideoIamgeView.setOnKeyListener(this);
        this.binding.id6CuspBrowserImageView.setOnKeyListener(this);
        this.binding.id6CuspCarImageView.setOnKeyListener(this);
        this.binding.id6CuspVideoIamgeView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspTwo.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager != null && FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager.getCurrentItem() != 1) {
                    FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager.setCurrentItem(1);
                    FragmentID6CuspTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6CuspBrowserImageView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspTwo.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager != null && FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager.getCurrentItem() != 1) {
                    FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager.setCurrentItem(1);
                    FragmentID6CuspTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6CuspVideoIamgeView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspTwo.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6CuspTwo.this.viewModel.openVideoMulti(v);
                FragmentID6CuspTwo.this.setItemSelected(v);
            }
        });
        this.binding.id6CuspCarImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspTwo.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6CuspTwo.this.viewModel.openCar(v);
                FragmentID6CuspTwo.this.setItemSelected(v);
            }
        });
        this.binding.id6CuspBrowserImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspTwo.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6CuspTwo.this.viewModel.openBrowser(v);
                FragmentID6CuspTwo.this.setItemSelected(v);
            }
        });
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.id6CuspVideoIamgeView.setSelected(this.binding.id6CuspVideoIamgeView == view);
        this.binding.id6CuspCarImageView.setSelected(this.binding.id6CuspCarImageView == view);
        this.binding.id6CuspBrowserImageView.setSelected(this.binding.id6CuspBrowserImageView == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.id6CuspVideoIamgeView);
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
                if (v == this.binding.id6CuspVideoIamgeView) {
                    setItemSelected(this.binding.id6CuspCarImageView);
                } else if (v == this.binding.id6CuspCarImageView) {
                    setItemSelected(this.binding.id6CuspBrowserImageView);
                }
            } else if (keyCode == 19) {
                if (v == this.binding.id6CuspBrowserImageView) {
                    setItemSelected(this.binding.id6CuspCarImageView);
                } else if (v == this.binding.id6CuspCarImageView) {
                    setItemSelected(this.binding.id6CuspVideoIamgeView);
                }
            }
        }
        return false;
    }
}
