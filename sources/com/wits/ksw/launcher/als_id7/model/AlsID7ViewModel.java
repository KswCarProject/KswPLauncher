package com.wits.ksw.launcher.als_id7.model;

import android.databinding.BindingAdapter;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class AlsID7ViewModel extends LauncherViewModel {
    /* access modifiers changed from: private */
    public String TAG = "AlsID7ViewModel";
    public View.OnFocusChangeListener btPhoneViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                String access$000 = AlsID7ViewModel.this.TAG;
                Log.i(access$000, "onFocusChange: btPhoneViewFocusChangeListener activity =" + MainActivity.mainActivity);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };
    public View.OnFocusChangeListener carinfoViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                String access$000 = AlsID7ViewModel.this.TAG;
                Log.i(access$000, "onFocusChange: carinfoViewFocusChangeListener activity =" + MainActivity.mainActivity);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(0);
                }
            }
        }
    };
    public View.OnFocusChangeListener dashViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(2);
                String access$000 = AlsID7ViewModel.this.TAG;
                Log.i(access$000, "onFocusChange: dashViewFocusChangeListener activity =" + MainActivity.mainActivity);
            }
        }
    };
    public View.OnFocusChangeListener musicViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                String access$000 = AlsID7ViewModel.this.TAG;
                Log.i(access$000, "onFocusChange: musicViewFocusChangeListener activity =" + MainActivity.mainActivity);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };

    public void addLastViewFocused(View view) {
        int id = view.getId();
        if (id == R.id.btn_music_pause || id == R.id.btn_music_prev || id == R.id.btn_music_next) {
            Log.i(this.TAG, "addLastViewFocused music pre next  play");
            view = MainActivity.mainActivity.findViewById(R.id.imageFrameLayout);
        }
        this.lastViewFocused = view;
        KswUtils.saveLastViewId(this.context, view);
    }

    public void btnMusicPrev(View view) {
        try {
            int lastMode = PowerManagerApp.getStatusInt("lastMode");
            View musiView = MainActivity.mainActivity.findViewById(R.id.imageFrameLayout);
            String str = this.TAG;
            Log.i(str, "btnMusicPrev isMusicStop=" + isMusicStop());
            if (isMusicStop()) {
                Log.d(this.TAG, "btnMusicPrev");
                openMusic(musiView);
            }
            KswUtils.sendKeyDownUpSync(88);
            addLastViewFocused(musiView);
            refreshLastViewFocused();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void btnMusicPlayPause(View view) {
        try {
            int lastMode = PowerManagerApp.getStatusInt("lastMode");
            View musiView = MainActivity.mainActivity.findViewById(R.id.imageFrameLayout);
            String str = this.TAG;
            Log.i(str, "btnMusicPlayPause isMusicStop=" + isMusicStop());
            if (isMusicStop()) {
                Log.d(this.TAG, "btnMusicPlayPause");
                openMusic(musiView);
            }
            KswUtils.sendKeyDownUpSync(85);
            String str2 = this.TAG;
            Log.d(str2, "btnMusicPlayPause isMusicPlay =" + isMusicPlay());
            addLastViewFocused(musiView);
            refreshLastViewFocused();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void btnMusicNext(View view) {
        try {
            int lastMode = PowerManagerApp.getStatusInt("lastMode");
            View musiView = MainActivity.mainActivity.findViewById(R.id.imageFrameLayout);
            String str = this.TAG;
            Log.i(str, " btnMusicNext isMusicStop=" + isMusicStop());
            if (isMusicStop()) {
                Log.d(this.TAG, "btnMusicNext");
                openMusic(musiView);
            }
            KswUtils.sendKeyDownUpSync(87);
            addLastViewFocused(musiView);
            refreshLastViewFocused();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isMusicStop() {
        boolean musicStop = false;
        try {
            musicStop = PowerManagerApp.getManager().getStatusBoolean("music_stop");
            String str = this.TAG;
            Log.d(str, "isMusicStop =" + musicStop);
            return musicStop;
        } catch (RemoteException e) {
            e.printStackTrace();
            return musicStop;
        }
    }

    public boolean isMusicPlay() {
        boolean isPlaying = false;
        try {
            isPlaying = PowerManagerApp.getManager().getStatusBoolean("play");
            String str = this.TAG;
            Log.d(str, "isMusicPlay =" + isPlaying);
            if (isMusicStop()) {
                return false;
            }
            return isPlaying;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setMusicPlayState(boolean play) {
        mediaInfo.setMusicPlay(play);
    }

    @BindingAdapter({"setPlayBtnicon"})
    public static void setPlayBtnicon(ImageView imageView, boolean play) {
        ((RequestBuilder) ((RequestBuilder) Glide.with(imageView.getContext()).load(Integer.valueOf(play ? R.drawable.als_id7_main_music_btn_pause : R.drawable.als_id7_main_music_btn_play)).placeholder((int) R.drawable.als_id7_main_music_btn_play)).error((int) R.drawable.als_id7_main_music_btn_play)).into(imageView);
    }
}
