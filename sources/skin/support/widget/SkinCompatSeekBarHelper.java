package skin.support.widget;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;
import skin.support.C1899R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes.dex */
public class SkinCompatSeekBarHelper extends SkinCompatProgressBarHelper {
    private int mThumbResId;
    private final SeekBar mView;

    public SkinCompatSeekBarHelper(SeekBar view) {
        super(view);
        this.mThumbResId = 0;
        this.mView = view;
    }

    @Override // skin.support.widget.SkinCompatProgressBarHelper
    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        super.loadFromAttributes(attrs, defStyleAttr);
        TypedArray a = this.mView.getContext().obtainStyledAttributes(attrs, C1899R.styleable.AppCompatSeekBar, defStyleAttr, 0);
        this.mThumbResId = a.getResourceId(C1899R.styleable.AppCompatSeekBar_android_thumb, 0);
        a.recycle();
        applySkin();
    }

    @Override // skin.support.widget.SkinCompatProgressBarHelper, skin.support.widget.SkinCompatHelper
    public void applySkin() {
        super.applySkin();
        int checkResourceId = checkResourceId(this.mThumbResId);
        this.mThumbResId = checkResourceId;
        if (checkResourceId != 0) {
            SeekBar seekBar = this.mView;
            seekBar.setThumb(SkinCompatResources.getDrawableCompat(seekBar.getContext(), this.mThumbResId));
        }
    }
}
