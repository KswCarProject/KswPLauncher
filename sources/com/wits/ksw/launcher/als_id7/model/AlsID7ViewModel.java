package com.wits.ksw.launcher.als_id7.model;

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
                Log.i(AlsID7ViewModel.this.TAG, "onFocusChange: btPhoneViewFocusChangeListener activity =" + MainActivity.mainActivity);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };
    public View.OnFocusChangeListener carinfoViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(AlsID7ViewModel.this.TAG, "onFocusChange: carinfoViewFocusChangeListener activity =" + MainActivity.mainActivity);
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
                Log.i(AlsID7ViewModel.this.TAG, "onFocusChange: dashViewFocusChangeListener activity =" + MainActivity.mainActivity);
            }
        }
    };
    public View.OnFocusChangeListener musicViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(AlsID7ViewModel.this.TAG, "onFocusChange: musicViewFocusChangeListener activity =" + MainActivity.mainActivity);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };
    public View.OnFocusChangeListener zlinkViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(3);
                Log.i(AlsID7ViewModel.this.TAG, "onFocusChange: zlinkViewFocusChangeListener activity =" + MainActivity.mainActivity);
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
            View musiView = MainActivity.mainActivity.findViewById(R.id.imageFrameLayout);
            if (mediaInfo.musicStop.get().booleanValue()) {
                Log.d(this.TAG, "btnMusicPrev");
                openMusic(musiView);
            }
            KswUtils.sendKeyDownUpSync(88);
            addLastViewFocused(musiView);
            refreshLastViewFocused();
        } catch (Exception e) {
            e.printStackTrace();
            if (0 != 0) {
                openMusic((View) null);
            }
        }
    }

    public void btnMusicPlayPause(View view) {
        try {
            View musiView = MainActivity.mainActivity.findViewById(R.id.imageFrameLayout);
            if (mediaInfo.musicStop.get().booleanValue()) {
                openMusic(musiView);
            }
            KswUtils.sendKeyDownUpSync(85);
            addLastViewFocused(musiView);
            refreshLastViewFocused();
        } catch (Exception e) {
            e.printStackTrace();
            if (0 != 0) {
                openMusic((View) null);
            }
        }
    }

    public void btnMusicNext(View view) {
        try {
            View musiView = MainActivity.mainActivity.findViewById(R.id.imageFrameLayout);
            if (mediaInfo.musicStop.get().booleanValue()) {
                Log.d(this.TAG, "btnMusicNext");
                openMusic(musiView);
            }
            KswUtils.sendKeyDownUpSync(87);
            addLastViewFocused(musiView);
            refreshLastViewFocused();
        } catch (Exception e) {
            e.printStackTrace();
            if (0 != 0) {
                openMusic((View) null);
            }
        }
    }

    public boolean isMusicStop() {
        boolean musicStop = false;
        try {
            musicStop = PowerManagerApp.getManager().getStatusBoolean("music_stop");
            Log.d(this.TAG, "isMusicStop =" + musicStop);
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
            Log.d(this.TAG, "isMusicPlay =" + isPlaying);
            return isPlaying;
        } catch (RemoteException e) {
            e.printStackTrace();
            return isPlaying;
        }
    }

    public void setMusicPlayState(boolean play) {
        mediaInfo.setMusicPlay(play);
    }

    public void setMusicPlayStop(boolean stop) {
        mediaInfo.setMusicStop(stop);
    }

    public static void setPlayBtnicon(ImageView imageView, boolean play) {
        ((RequestBuilder) ((RequestBuilder) Glide.with(imageView.getContext()).load(Integer.valueOf(play ? R.drawable.als_id7_main_music_btn_pause : R.drawable.als_id7_main_music_btn_play)).placeholder((int) R.drawable.als_id7_main_music_btn_play)).error((int) R.drawable.als_id7_main_music_btn_play)).into(imageView);
    }
}
