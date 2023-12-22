package com.wits.ksw.launcher.view.id6;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ID6FragmentFour;

/* loaded from: classes5.dex */
public class FragmentID6Four extends ID6BaseFragment implements View.OnKeyListener {
    private ID6FragmentFour binding;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ID6FragmentFour iD6FragmentFour = (ID6FragmentFour) DataBindingUtil.inflate(inflater, C0899R.C0902layout.id6_fragment_four, null, false);
        this.binding = iD6FragmentFour;
        return iD6FragmentFour.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.id6PhoneIamgeView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Four.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6Four.this.mainActivity.id6MainViewPager != null && FragmentID6Four.this.mainActivity.id6MainViewPager.getCurrentItem() != 3) {
                    FragmentID6Four.this.mainActivity.id6MainViewPager.setCurrentItem(3);
                    FragmentID6Four.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6PhoneIamgeView.setOnKeyListener(this);
        this.binding.id6AppsImageView.setOnKeyListener(this);
        this.binding.id6SettingImageView.setOnKeyListener(this);
        this.binding.id6PhoneIamgeView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Four.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6Four.this.viewModel.openShouJiHuLian(v);
                FragmentID6Four.this.setItemSelected(v);
            }
        });
        this.binding.id6AppsImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Four.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6Four.this.viewModel.openApps(v);
                FragmentID6Four.this.setItemSelected(v);
            }
        });
        this.binding.id6SettingImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Four.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6Four.this.viewModel.openSettings(v);
                FragmentID6Four.this.setItemSelected(v);
            }
        });
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.id6PhoneIamgeView.setSelected(this.binding.id6PhoneIamgeView == view);
        this.binding.id6AppsImageView.setSelected(this.binding.id6AppsImageView == view);
        this.binding.id6SettingImageView.setSelected(this.binding.id6SettingImageView == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.id6PhoneIamgeView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            if (keyCode == 22) {
                return true;
            }
            if (keyCode == 21) {
                this.mainActivity.id6MainViewPager.setCurrentItem(2);
                return true;
            } else if (keyCode == 20) {
                if (v == this.binding.id6PhoneIamgeView) {
                    setItemSelected(this.binding.id6AppsImageView);
                    return false;
                } else if (v == this.binding.id6AppsImageView) {
                    setItemSelected(this.binding.id6SettingImageView);
                    return false;
                } else {
                    return false;
                }
            } else if (keyCode == 19) {
                if (v == this.binding.id6SettingImageView) {
                    setItemSelected(this.binding.id6AppsImageView);
                    return false;
                } else if (v == this.binding.id6AppsImageView) {
                    setItemSelected(this.binding.id6PhoneIamgeView);
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
