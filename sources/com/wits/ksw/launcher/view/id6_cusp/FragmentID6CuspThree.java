package com.wits.ksw.launcher.view.id6_cusp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ID6CuspFragmentThree;
import com.wits.ksw.launcher.utils.KeyUtils;

public class FragmentID6CuspThree extends ID6CuspBaseFragment implements View.OnKeyListener {
    private ID6CuspFragmentThree binding;
    private ImageView[] imageViews = new ImageView[3];

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ID6CuspFragmentThree iD6CuspFragmentThree = (ID6CuspFragmentThree) DataBindingUtil.inflate(inflater, R.layout.id6_cusp_fragment_three, (ViewGroup) null, false);
        this.binding = iD6CuspFragmentThree;
        return iD6CuspFragmentThree.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KeyUtils.pressKey(391);
        this.binding.setViewModel(this.viewModel);
        this.imageViews[0] = this.binding.id6CuspFileIamgeView;
        this.imageViews[1] = this.binding.id6CuspDvrImageView;
        this.imageViews[2] = this.binding.id6CuspDashboardImageView;
        this.binding.id6CuspFileIamgeView.setOnKeyListener(this);
        this.binding.id6CuspDvrImageView.setOnKeyListener(this);
        this.binding.id6CuspDashboardImageView.setOnKeyListener(this);
        this.binding.id6CuspFileIamgeView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6CuspThree.this.mainActivity.id6CuspMainViewPager != null && FragmentID6CuspThree.this.mainActivity.id6CuspMainViewPager.getCurrentItem() != 2) {
                    FragmentID6CuspThree.this.mainActivity.id6CuspMainViewPager.setCurrentItem(2);
                    FragmentID6CuspThree.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6CuspDashboardImageView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6CuspThree.this.mainActivity.id6CuspMainViewPager != null && FragmentID6CuspThree.this.mainActivity.id6CuspMainViewPager.getCurrentItem() != 2) {
                    FragmentID6CuspThree.this.mainActivity.id6CuspMainViewPager.setCurrentItem(2);
                    FragmentID6CuspThree.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6CuspFileIamgeView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6CuspThree.this.viewModel.openFileManager(v);
                FragmentID6CuspThree.this.setItemSelected(v);
            }
        });
        this.binding.id6CuspDvrImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6CuspThree.this.viewModel.openDvr(v);
                FragmentID6CuspThree.this.setItemSelected(v);
            }
        });
        this.binding.id6CuspDashboardImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6CuspThree.this.viewModel.openDashboard(v);
                FragmentID6CuspThree.this.setItemSelected(v);
            }
        });
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        boolean z = true;
        this.binding.id6CuspFileIamgeView.setSelected(this.binding.id6CuspFileIamgeView == view);
        this.binding.id6CuspDvrImageView.setSelected(this.binding.id6CuspDvrImageView == view);
        ImageView imageView = this.binding.id6CuspDashboardImageView;
        if (this.binding.id6CuspDashboardImageView != view) {
            z = false;
        }
        imageView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.id6CuspFileIamgeView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        if (keyCode == 22) {
            this.mainActivity.id6CuspMainViewPager.setCurrentItem(3);
            return true;
        } else if (keyCode == 21) {
            this.mainActivity.id6CuspMainViewPager.setCurrentItem(1);
            return true;
        } else if (keyCode == 20) {
            if (v == this.binding.id6CuspFileIamgeView) {
                setItemSelected(this.binding.id6CuspDvrImageView);
                return false;
            } else if (v != this.binding.id6CuspDvrImageView) {
                return false;
            } else {
                setItemSelected(this.binding.id6CuspDashboardImageView);
                return false;
            }
        } else if (keyCode != 19) {
            return false;
        } else {
            if (v == this.binding.id6CuspDashboardImageView) {
                setItemSelected(this.binding.id6CuspDvrImageView);
                return false;
            } else if (v != this.binding.id6CuspDvrImageView) {
                return false;
            } else {
                setItemSelected(this.binding.id6CuspFileIamgeView);
                return false;
            }
        }
    }
}
