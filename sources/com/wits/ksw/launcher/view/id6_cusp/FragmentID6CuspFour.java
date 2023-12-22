package com.wits.ksw.launcher.view.id6_cusp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ID6CuspFragmentFour;

/* loaded from: classes7.dex */
public class FragmentID6CuspFour extends ID6CuspBaseFragment implements View.OnKeyListener {
    private ID6CuspFragmentFour binding;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ID6CuspFragmentFour iD6CuspFragmentFour = (ID6CuspFragmentFour) DataBindingUtil.inflate(inflater, C0899R.C0902layout.id6_cusp_fragment_four, null, false);
        this.binding = iD6CuspFragmentFour;
        return iD6CuspFragmentFour.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.id6CuspPhoneIamgeView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspFour.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6CuspFour.this.mainActivity.id6CuspMainViewPager != null && FragmentID6CuspFour.this.mainActivity.id6CuspMainViewPager.getCurrentItem() != 3) {
                    FragmentID6CuspFour.this.mainActivity.id6CuspMainViewPager.setCurrentItem(3);
                    FragmentID6CuspFour.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6CuspPhoneIamgeView.setOnKeyListener(this);
        this.binding.id6CuspAppsImageView.setOnKeyListener(this);
        this.binding.id6CuspSettingImageView.setOnKeyListener(this);
        this.binding.id6CuspPhoneIamgeView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspFour.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6CuspFour.this.viewModel.openShouJiHuLian(v);
                FragmentID6CuspFour.this.setItemSelected(v);
            }
        });
        this.binding.id6CuspAppsImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspFour.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6CuspFour.this.viewModel.openApps(v);
                FragmentID6CuspFour.this.setItemSelected(v);
            }
        });
        this.binding.id6CuspSettingImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspFour.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6CuspFour.this.viewModel.openSettings(v);
                FragmentID6CuspFour.this.setItemSelected(v);
            }
        });
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.id6CuspPhoneIamgeView.setSelected(this.binding.id6CuspPhoneIamgeView == view);
        this.binding.id6CuspAppsImageView.setSelected(this.binding.id6CuspAppsImageView == view);
        this.binding.id6CuspSettingImageView.setSelected(this.binding.id6CuspSettingImageView == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.id6CuspPhoneIamgeView);
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
                this.mainActivity.id6CuspMainViewPager.setCurrentItem(2);
                return true;
            } else if (keyCode == 20) {
                if (v == this.binding.id6CuspPhoneIamgeView) {
                    setItemSelected(this.binding.id6CuspAppsImageView);
                    return false;
                } else if (v == this.binding.id6CuspAppsImageView) {
                    setItemSelected(this.binding.id6CuspSettingImageView);
                    return false;
                } else {
                    return false;
                }
            } else if (keyCode == 19) {
                if (v == this.binding.id6CuspSettingImageView) {
                    setItemSelected(this.binding.id6CuspAppsImageView);
                    return false;
                } else if (v == this.binding.id6CuspAppsImageView) {
                    setItemSelected(this.binding.id6CuspPhoneIamgeView);
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
