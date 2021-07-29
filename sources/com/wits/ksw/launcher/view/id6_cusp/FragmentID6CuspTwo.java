package com.wits.ksw.launcher.view.id6_cusp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ID6CuspFragmentTow;

public class FragmentID6CuspTwo extends ID6CuspBaseFragment implements View.OnKeyListener {
    private static final String TAG = "KSWLauncher";
    private ID6CuspFragmentTow binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ID6CuspFragmentTow iD6CuspFragmentTow = (ID6CuspFragmentTow) DataBindingUtil.inflate(inflater, R.layout.id6_cusp_fragment_tow, (ViewGroup) null, false);
        this.binding = iD6CuspFragmentTow;
        return iD6CuspFragmentTow.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setViewModel(this.viewModel);
        this.binding.id6CuspVideoIamgeView.setOnKeyListener(this);
        this.binding.id6CuspBrowserImageView.setOnKeyListener(this);
        this.binding.id6CuspCarImageView.setOnKeyListener(this);
        this.binding.id6CuspVideoIamgeView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager != null && FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager.getCurrentItem() != 1) {
                    FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager.setCurrentItem(1);
                    FragmentID6CuspTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6CuspBrowserImageView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager != null && FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager.getCurrentItem() != 1) {
                    FragmentID6CuspTwo.this.mainActivity.id6CuspMainViewPager.setCurrentItem(1);
                    FragmentID6CuspTwo.this.setItemSelected(v);
                }
            }
        });
        this.binding.id6CuspVideoIamgeView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6CuspTwo.this.viewModel.openVideo(v);
                FragmentID6CuspTwo.this.setItemSelected(v);
            }
        });
        this.binding.id6CuspCarImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6CuspTwo.this.viewModel.openCar(v);
                FragmentID6CuspTwo.this.setItemSelected(v);
            }
        });
        this.binding.id6CuspBrowserImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentID6CuspTwo.this.viewModel.openBrowser(v);
                FragmentID6CuspTwo.this.setItemSelected(v);
            }
        });
        setDefaultSelected();
    }

    public void setItemSelected(View view) {
        boolean z = true;
        this.binding.id6CuspVideoIamgeView.setSelected(this.binding.id6CuspVideoIamgeView == view);
        this.binding.id6CuspCarImageView.setSelected(this.binding.id6CuspCarImageView == view);
        ImageView imageView = this.binding.id6CuspBrowserImageView;
        if (this.binding.id6CuspBrowserImageView != view) {
            z = false;
        }
        imageView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(this.binding.id6CuspVideoIamgeView);
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
