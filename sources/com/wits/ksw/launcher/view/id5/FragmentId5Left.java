package com.wits.ksw.launcher.view.id5;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.KswUtils;

public class FragmentId5Left extends Fragment implements View.OnKeyListener, View.OnClickListener {
    private static final String TAG = "KSWLauncher";
    @InjectView(2131231050)
    CheckBox id5ItemBrowser;
    @InjectView(2131231051)
    ImageView id5ItemBrowserCursor;
    @InjectView(2131231052)
    CheckBox id5ItemBt;
    @InjectView(2131231053)
    ImageView id5ItemBtCursor;
    @InjectView(2131231054)
    CheckBox id5ItemCar;
    @InjectView(2131231055)
    ImageView id5ItemCarCursor;
    @InjectView(2131231058)
    CheckBox id5ItemFile;
    @InjectView(2131231057)
    ImageView id5ItemFileCursor;
    @InjectView(2131231061)
    CheckBox id5ItemMusic;
    @InjectView(2131231062)
    ImageView id5ItemMusicCursor;
    @InjectView(2131231063)
    CheckBox id5ItemNavi;
    @InjectView(2131231064)
    ImageView id5ItemNaviCursor;
    @InjectView(2131231128)
    ImageView imageView7;
    /* access modifiers changed from: private */
    public MainActivity mainActivity;
    @InjectView(2131231538)
    TextView textView10;
    @InjectView(2131231550)
    TextView textView5;
    @InjectView(2131231551)
    TextView textView6;
    @InjectView(2131231552)
    TextView textView7;
    @InjectView(2131231553)
    TextView textView8;
    @InjectView(2131231554)
    TextView textView9;
    private LauncherViewModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
        this.viewModel.setActivity(getActivity());
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.id5_fragment_one, container, false);
        ButterKnife.inject((Object) this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnFocusChangeListener();
        setOnCheckedChangeListener();
        setOnKeyListener();
        setOnClickListener();
        setSelected(this.id5ItemMusic);
    }

    public void onResume() {
        super.onResume();
        addCursorSelected();
    }

    private void setOnClickListener() {
        this.id5ItemMusic.setOnClickListener(this);
        this.id5ItemBt.setOnClickListener(this);
        this.id5ItemNavi.setOnClickListener(this);
        this.id5ItemFile.setOnClickListener(this);
        this.id5ItemCar.setOnClickListener(this);
        this.id5ItemBrowser.setOnClickListener(this);
    }

    private void setOnKeyListener() {
        this.id5ItemMusic.setOnKeyListener(this);
        this.id5ItemBt.setOnKeyListener(this);
        this.id5ItemNavi.setOnKeyListener(this);
        this.id5ItemFile.setOnKeyListener(this);
        this.id5ItemCar.setOnKeyListener(this);
        this.id5ItemBrowser.setOnKeyListener(this);
    }

    public void setOnCheckedChangeListener() {
        this.id5ItemMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Left.this.setTextAnim(isChecked, FragmentId5Left.this.textView5);
            }
        });
        this.id5ItemBt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Left.this.setTextAnim(isChecked, FragmentId5Left.this.textView6);
            }
        });
        this.id5ItemNavi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Left.this.setTextAnim(isChecked, FragmentId5Left.this.textView7);
            }
        });
        this.id5ItemFile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Left.this.setTextAnim(isChecked, FragmentId5Left.this.textView8);
            }
        });
        this.id5ItemCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Left.this.setTextAnim(isChecked, FragmentId5Left.this.textView9);
            }
        });
        this.id5ItemBrowser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentId5Left.this.setTextAnim(isChecked, FragmentId5Left.this.textView10);
            }
        });
    }

    private void setOnFocusChangeListener() {
        this.id5ItemMusic.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i("KSWLauncher", "onFocusChange: id5ItemMusic " + hasFocus);
                FragmentId5Left.this.setSelected(v);
            }
        });
        this.id5ItemBt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i("KSWLauncher", "onFocusChange: id5ItemBt " + hasFocus);
                FragmentId5Left.this.setSelected(v);
            }
        });
        this.id5ItemNavi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i("KSWLauncher", "onFocusChange: id5ItemNavi " + hasFocus);
                FragmentId5Left.this.setSelected(v);
            }
        });
        this.id5ItemFile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i("KSWLauncher", "onFocusChange: id5ItemFile " + hasFocus);
                FragmentId5Left.this.setSelected(v);
            }
        });
        this.id5ItemCar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i("KSWLauncher", "onFocusChange: id5ItemCar " + hasFocus);
                FragmentId5Left.this.setSelected(v);
            }
        });
        this.id5ItemBrowser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i("KSWLauncher", "onFocusChange: id5ItemBrowser " + hasFocus);
                FragmentId5Left.this.setSelected(v);
                if (hasFocus) {
                    try {
                        FragmentId5Left.this.mainActivity.id5MaindBind.id5MainViewPager.setCurrentItem(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void setTextAnim(boolean hasFocus, TextView textView52) {
        if (hasFocus) {
            KswUtils.translate(textView52, R.anim.id5_text_y_scale_in);
        } else {
            KswUtils.translate(textView52, R.anim.id5_text_y_scale_out);
        }
    }

    private void addSelected(View view) {
        try {
            boolean z = false;
            this.id5ItemMusic.setChecked(view == this.id5ItemMusic);
            this.id5ItemBt.setChecked(view == this.id5ItemBt);
            this.id5ItemNavi.setChecked(view == this.id5ItemNavi);
            this.id5ItemFile.setChecked(view == this.id5ItemFile);
            this.id5ItemCar.setChecked(view == this.id5ItemCar);
            CheckBox checkBox = this.id5ItemBrowser;
            if (view == this.id5ItemBrowser) {
                z = true;
            }
            checkBox.setChecked(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCursorSelected() {
        try {
            int i = 8;
            this.id5ItemMusicCursor.setVisibility(this.id5ItemMusic.isChecked() ? 0 : 8);
            this.id5ItemBtCursor.setVisibility(this.id5ItemBt.isChecked() ? 0 : 8);
            this.id5ItemNaviCursor.setVisibility(this.id5ItemNavi.isChecked() ? 0 : 8);
            this.id5ItemFileCursor.setVisibility(this.id5ItemFile.isChecked() ? 0 : 8);
            this.id5ItemCarCursor.setVisibility(this.id5ItemCar.isChecked() ? 0 : 8);
            ImageView imageView = this.id5ItemBrowserCursor;
            if (this.id5ItemBrowser.isChecked()) {
                i = 0;
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
        if (keyCode == 22) {
            try {
                this.mainActivity.id5MaindBind.id5MainViewPager.setCurrentItem(1);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else if (keyCode == 21) {
            return true;
        } else {
            return false;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id5_item_browser:
                setSelected(v);
                this.viewModel.openBrowser(v);
                return;
            case R.id.id5_item_bt:
                setSelected(v);
                this.viewModel.openBtApp(v);
                return;
            case R.id.id5_item_car:
                setSelected(v);
                this.viewModel.openCar(v);
                return;
            case R.id.id5_item_file:
                setSelected(v);
                this.viewModel.openFileManager(v);
                return;
            case R.id.id5_item_music:
                setSelected(v);
                this.viewModel.openChoseMusic(v);
                return;
            case R.id.id5_item_navi:
                setSelected(v);
                this.viewModel.openNaviApp(v);
                return;
            default:
                return;
        }
    }
}
