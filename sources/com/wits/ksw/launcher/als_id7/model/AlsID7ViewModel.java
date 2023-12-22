package com.wits.ksw.launcher.als_id7.model;

import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes14.dex */
public class AlsID7ViewModel extends LauncherViewModel {
    private String TAG = "AlsID7ViewModel";
    public View.OnFocusChangeListener carinfoViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel.1
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(AlsID7ViewModel.this.TAG, "onFocusChange: carinfoViewFocusChangeListener activity =" + MainActivity.mainActivity);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(0);
                }
            }
        }
    };
    public View.OnFocusChangeListener musicViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel.2
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(AlsID7ViewModel.this.TAG, "onFocusChange: musicViewFocusChangeListener activity =" + MainActivity.mainActivity);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };
    public View.OnFocusChangeListener btPhoneViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel.3
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(AlsID7ViewModel.this.TAG, "onFocusChange: btPhoneViewFocusChangeListener activity =" + MainActivity.mainActivity);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };
    public View.OnFocusChangeListener dashViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel.4
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(2);
                Log.i(AlsID7ViewModel.this.TAG, "onFocusChange: dashViewFocusChangeListener activity =" + MainActivity.mainActivity);
            }
        }
    };
    public View.OnFocusChangeListener zlinkViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel.5
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(3);
                Log.i(AlsID7ViewModel.this.TAG, "onFocusChange: zlinkViewFocusChangeListener activity =" + MainActivity.mainActivity);
            }
        }
    };

    @Override // com.wits.ksw.launcher.model.LauncherViewModel
    public void addLastViewFocused(View view) {
        int id = view.getId();
        if (id == C0899R.C0901id.btn_music_pause || id == C0899R.C0901id.btn_music_prev || id == C0899R.C0901id.btn_music_next) {
            Log.i(this.TAG, "addLastViewFocused music pre next  play");
            view = MainActivity.mainActivity.findViewById(C0899R.C0901id.imageFrameLayout);
        }
        this.lastViewFocused = view;
        KswUtils.saveLastViewId(this.context, view);
    }

    public void btnMusicPrev(View view) {
        View musiView = null;
        try {
            musiView = MainActivity.mainActivity.findViewById(C0899R.C0901id.imageFrameLayout);
            if (mediaInfo.musicStop.get().booleanValue()) {
                Log.d(this.TAG, "btnMusicPrev");
                openMusic(musiView);
            }
            KswUtils.sendKeyDownUpSync(88);
            addLastViewFocused(musiView);
            refreshLastViewFocused();
        } catch (Exception e) {
            e.printStackTrace();
            if (musiView != null) {
                openMusic(musiView);
            }
        }
    }

    public void btnMusicPlayPause(View view) {
        View musiView = null;
        try {
            musiView = MainActivity.mainActivity.findViewById(C0899R.C0901id.imageFrameLayout);
            if (mediaInfo.musicStop.get().booleanValue()) {
                openMusic(musiView);
            }
            KswUtils.sendKeyDownUpSync(85);
            addLastViewFocused(musiView);
            refreshLastViewFocused();
        } catch (Exception e) {
            e.printStackTrace();
            if (musiView != null) {
                openMusic(musiView);
            }
        }
    }

    public void btnMusicNext(View view) {
        View musiView = null;
        try {
            musiView = MainActivity.mainActivity.findViewById(C0899R.C0901id.imageFrameLayout);
            if (mediaInfo.musicStop.get().booleanValue()) {
                Log.d(this.TAG, "btnMusicNext");
                openMusic(musiView);
            }
            KswUtils.sendKeyDownUpSync(87);
            addLastViewFocused(musiView);
            refreshLastViewFocused();
        } catch (Exception e) {
            e.printStackTrace();
            if (musiView != null) {
                openMusic(musiView);
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

    @Override // com.wits.ksw.launcher.model.LauncherViewModel
    public void setMusicPlayState(boolean play) {
        mediaInfo.setMusicPlay(play);
    }

    @Override // com.wits.ksw.launcher.model.LauncherViewModel
    public void setMusicPlayStop(boolean stop) {
        mediaInfo.setMusicStop(stop);
    }

    public static void setPlayBtnicon(ImageView imageView, boolean play) {
        Glide.with(imageView.getContext()).load(Integer.valueOf(play ? C0899R.C0900drawable.als_id7_main_music_btn_pause : C0899R.C0900drawable.als_id7_main_music_btn_play)).placeholder((int) C0899R.C0900drawable.als_id7_main_music_btn_play).error(C0899R.C0900drawable.als_id7_main_music_btn_play).into(imageView);
    }
}
