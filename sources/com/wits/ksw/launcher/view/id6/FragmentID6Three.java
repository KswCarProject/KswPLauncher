package com.wits.ksw.launcher.view.id6;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ID6FragmentThree;
import com.wits.ksw.launcher.utils.KeyUtils;

/* loaded from: classes5.dex */
public class FragmentID6Three extends ID6BaseFragment implements View.OnKeyListener {
    private ID6FragmentThree binding;
    private ImageView[] imageViews = new ImageView[3];

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ID6FragmentThree iD6FragmentThree = (ID6FragmentThree) DataBindingUtil.inflate(inflater, C0899R.C0902layout.id6_fragment_three, null, false);
        this.binding = iD6FragmentThree;
        return iD6FragmentThree.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KeyUtils.pressKey(391);
        this.binding.setViewModel(this.viewModel);
        this.imageViews[0] = this.binding.id6FileIamgeView;
        this.imageViews[1] = this.binding.id6DvrImageView;
        this.imageViews[2] = this.binding.id6DashboardImageView;
        this.binding.id6FileIamgeView.setOnKeyListener(this);
        this.binding.id6DvrImageView.setOnKeyListener(this);
        this.binding.id6DashboardImageView.setOnKeyListener(this);
        this.binding.id6FileIamgeView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Three.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6Three.this.mainActivity.id6MainViewPager != null && FragmentID6Three.this.mainActivity.id6MainViewPager.getCurrentItem() != 2) {
                    FragmentID6Three.this.mainActivity.id6MainViewPager.setCurrentItem(2);
                    FragmentID6Three.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6DashboardImageView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Three.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6Three.this.mainActivity.id6MainViewPager != null && FragmentID6Three.this.mainActivity.id6MainViewPager.getCurrentItem() != 2) {
                    FragmentID6Three.this.mainActivity.id6MainViewPager.setCurrentItem(2);
                    FragmentID6Three.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6FileIamgeView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Three.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6Three.this.viewModel.openFileManager(v);
                FragmentID6Three.this.setItemSelected(v);
            }
        });
        this.binding.id6DvrImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Three.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6Three.this.viewModel.openDvr(v);
                FragmentID6Three.this.setItemSelected(v);
            }
        });
        this.binding.id6DashboardImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.id6.FragmentID6Three.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FragmentID6Three.this.viewModel.openDashboard(v);
                FragmentID6Three.this.setItemSelected(v);
            }
        });
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        this.binding.id6FileIamgeView.setSelected(this.binding.id6FileIamgeView == view);
        this.binding.id6DvrImageView.setSelected(this.binding.id6DvrImageView == view);
        this.binding.id6DashboardImageView.setSelected(this.binding.id6DashboardImageView == view);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.id6FileIamgeView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            if (keyCode == 22) {
                this.mainActivity.id6MainViewPager.setCurrentItem(3);
                return true;
            } else if (keyCode == 21) {
                this.mainActivity.id6MainViewPager.setCurrentItem(1);
                return true;
            } else if (keyCode == 20) {
                if (v == this.binding.id6FileIamgeView) {
                    setItemSelected(this.binding.id6DvrImageView);
                    return false;
                } else if (v == this.binding.id6DvrImageView) {
                    setItemSelected(this.binding.id6DashboardImageView);
                    return false;
                } else {
                    return false;
                }
            } else if (keyCode == 19) {
                if (v == this.binding.id6DashboardImageView) {
                    setItemSelected(this.binding.id6DvrImageView);
                    return false;
                } else if (v == this.binding.id6DvrImageView) {
                    setItemSelected(this.binding.id6FileIamgeView);
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
