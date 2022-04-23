package com.wits.ksw.launcher.view.id5;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.KswUtils;

public class FragmentId5Right extends Fragment implements View.OnKeyListener, View.OnClickListener {
    CheckBox id5ItemApps;
    ImageView id5ItemAppsCursor;
    ImageView id5ItemDahsCursor;
    CheckBox id5ItemDash;
    CheckBox id5ItemJly;
    ImageView id5ItemJlyCursor;
    CheckBox id5ItemSet;
    ImageView id5ItemSetCursor;
    CheckBox id5ItemSjhl;
    ImageView id5ItemSjhlCursor;
    CheckBox id5ItemVideo;
    ImageView id5ItemVideoCursor;
    ImageView imageView8;
    /* access modifiers changed from: private */
    public MainActivity mainActivity;
    TextView textView11;
    TextView textView12;
    TextView textView13;
    TextView textView14;
    TextView textView15;
    TextView textView16;
    private LauncherViewModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LauncherViewModel launcherViewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
        this.viewModel = launcherViewModel;
        launcherViewModel.setActivity(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.id5_fragment_two, container, false);
        ButterKnife.inject((Object) this, view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnFocusChangeListener();
        setOnCheckedChangeListener();
        setOnKeyListener();
        setOnClickListener();
    }

    public void onResume() {
        super.onResume();
        addCursorSelected();
    }

    /* access modifiers changed from: private */
    public void setTextAnim(boolean hasFocus, TextView textView) {
        if (hasFocus) {
            KswUtils.translate(textView, R.anim.id5_text_y_scale_in);
        } else {
            KswUtils.translate(textView, R.anim.id5_text_y_scale_out);
        }
    }

    private void setOnClickListener() {
        this.id5ItemVideo.setOnClickListener(this);
        this.id5ItemJly.setOnClickListener(this);
        this.id5ItemSet.setOnClickListener(this);
        this.id5ItemDash.setOnClickListener(this);
        this.id5ItemSjhl.setOnClickListener(this);
        this.id5ItemApps.setOnClickListener(this);
    }

    private void setOnKeyListener() {
        this.id5ItemVideo.setOnKeyListener(this);
        this.id5ItemJly.setOnKeyListener(this);
        this.id5ItemSet.setOnKeyListener(this);
        this.id5ItemDash.setOnKeyListener(this);
        this.id5ItemSjhl.setOnKeyListener(this);
        this.id5ItemApps.setOnKeyListener(this);
    }

    private void setOnCheckedChangeListener() {
        this.id5ItemVideo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Right fragmentId5Right = FragmentId5Right.this;
                fragmentId5Right.setTextAnim(isChecked, fragmentId5Right.textView11);
            }
        });
        this.id5ItemJly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Right fragmentId5Right = FragmentId5Right.this;
                fragmentId5Right.setTextAnim(isChecked, fragmentId5Right.textView12);
            }
        });
        this.id5ItemSet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Right fragmentId5Right = FragmentId5Right.this;
                fragmentId5Right.setTextAnim(isChecked, fragmentId5Right.textView13);
            }
        });
        this.id5ItemDash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Right fragmentId5Right = FragmentId5Right.this;
                fragmentId5Right.setTextAnim(isChecked, fragmentId5Right.textView14);
            }
        });
        this.id5ItemSjhl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Right fragmentId5Right = FragmentId5Right.this;
                fragmentId5Right.setTextAnim(isChecked, fragmentId5Right.textView15);
            }
        });
        this.id5ItemApps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Right fragmentId5Right = FragmentId5Right.this;
                fragmentId5Right.setTextAnim(isChecked, fragmentId5Right.textView16);
            }
        });
    }

    private void setOnFocusChangeListener() {
        this.id5ItemVideo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    try {
                        FragmentId5Right.this.mainActivity.id5MaindBind.id5MainViewPager.setCurrentItem(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                FragmentId5Right.this.setSelected(v);
            }
        });
        this.id5ItemJly.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                FragmentId5Right.this.setSelected(v);
            }
        });
        this.id5ItemSet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                FragmentId5Right.this.setSelected(v);
            }
        });
        this.id5ItemDash.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                FragmentId5Right.this.setSelected(v);
            }
        });
        this.id5ItemSjhl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                FragmentId5Right.this.setSelected(v);
            }
        });
        this.id5ItemApps.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                FragmentId5Right.this.setSelected(v);
            }
        });
    }

    private void addSelected(View view) {
        try {
            CheckBox checkBox = this.id5ItemVideo;
            boolean z = true;
            checkBox.setChecked(view == checkBox);
            CheckBox checkBox2 = this.id5ItemJly;
            checkBox2.setChecked(view == checkBox2);
            CheckBox checkBox3 = this.id5ItemSet;
            checkBox3.setChecked(view == checkBox3);
            CheckBox checkBox4 = this.id5ItemDash;
            checkBox4.setChecked(view == checkBox4);
            CheckBox checkBox5 = this.id5ItemSjhl;
            checkBox5.setChecked(view == checkBox5);
            CheckBox checkBox6 = this.id5ItemApps;
            if (view != checkBox6) {
                z = false;
            }
            checkBox6.setChecked(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCursorSelected() {
        try {
            int i = 0;
            this.id5ItemVideoCursor.setVisibility(this.id5ItemVideo.isChecked() ? 0 : 8);
            this.id5ItemJlyCursor.setVisibility(this.id5ItemJly.isChecked() ? 0 : 8);
            this.id5ItemSetCursor.setVisibility(this.id5ItemSet.isChecked() ? 0 : 8);
            this.id5ItemDahsCursor.setVisibility(this.id5ItemDash.isChecked() ? 0 : 8);
            this.id5ItemSjhlCursor.setVisibility(this.id5ItemSjhl.isChecked() ? 0 : 8);
            ImageView imageView = this.id5ItemAppsCursor;
            if (!this.id5ItemApps.isChecked()) {
                i = 8;
            }
            imageView.setVisibility(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void setSelected(View v) {
        addSelected(v);
        addCursorSelected();
    }

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode != 21) {
            return keyCode == 22;
        }
        try {
            this.mainActivity.id5MaindBind.id5MainViewPager.setCurrentItem(0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id5_item_apps:
                setSelected(v);
                this.viewModel.openApps(v);
                return;
            case R.id.id5_item_dash:
                setSelected(v);
                this.viewModel.openDashboard(v);
                return;
            case R.id.id5_item_jly:
                setSelected(v);
                this.viewModel.openDvr(v);
                return;
            case R.id.id5_item_set:
                setSelected(v);
                this.viewModel.openSettings(v);
                return;
            case R.id.id5_item_sjhl:
                setSelected(v);
                this.viewModel.openShouJiHuLian(v);
                return;
            case R.id.id5_item_video:
                setSelected(v);
                this.viewModel.openVideoMulti(v);
                return;
            default:
                return;
        }
    }
}
