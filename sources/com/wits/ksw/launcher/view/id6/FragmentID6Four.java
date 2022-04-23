package com.wits.ksw.launcher.view.id6;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ID6FragmentFour;

public class FragmentID6Four extends ID6BaseFragment implements View.OnKeyListener {
    private ID6FragmentFour binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ID6FragmentFour iD6FragmentFour = (ID6FragmentFour) DataBindingUtil.inflate(inflater, R.layout.id6_fragment_four, (ViewGroup) null, false);
        this.binding = iD6FragmentFour;
        return iD6FragmentFour.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.id6PhoneIamgeView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        this.binding.id6PhoneIamgeView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6Four.this.viewModel.openShouJiHuLian(v);
                FragmentID6Four.this.setItemSelected(v);
            }
        });
        this.binding.id6AppsImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6Four.this.viewModel.openApps(v);
                FragmentID6Four.this.setItemSelected(v);
            }
        });
        this.binding.id6SettingImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6Four.this.viewModel.openSettings(v);
                FragmentID6Four.this.setItemSelected(v);
            }
        });
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        boolean z = true;
        this.binding.id6PhoneIamgeView.setSelected(this.binding.id6PhoneIamgeView == view);
        this.binding.id6AppsImageView.setSelected(this.binding.id6AppsImageView == view);
        ImageView imageView = this.binding.id6SettingImageView;
        if (this.binding.id6SettingImageView != view) {
            z = false;
        }
        imageView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.id6PhoneIamgeView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
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
            } else if (v != this.binding.id6AppsImageView) {
                return false;
            } else {
                setItemSelected(this.binding.id6SettingImageView);
                return false;
            }
        } else if (keyCode != 19) {
            return false;
        } else {
            if (v == this.binding.id6SettingImageView) {
                setItemSelected(this.binding.id6AppsImageView);
                return false;
            } else if (v != this.binding.id6AppsImageView) {
                return false;
            } else {
                setItemSelected(this.binding.id6PhoneIamgeView);
                return false;
            }
        }
    }
}
