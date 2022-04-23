package skin.support.widget;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;
import skin.support.R;
import skin.support.content.res.SkinCompatResources;

public class SkinCompatSeekBarHelper extends SkinCompatProgressBarHelper {
    private int mThumbResId = 0;
    private final SeekBar mView;

    public SkinCompatSeekBarHelper(SeekBar view) {
        super(view);
        this.mView = view;
    }

    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        super.loadFromAttributes(attrs, defStyleAttr);
        TypedArray a = this.mView.getContext().obtainStyledAttributes(attrs, R.styleable.AppCompatSeekBar, defStyleAttr, 0);
        this.mThumbResId = a.getResourceId(R.styleable.AppCompatSeekBar_android_thumb, 0);
        a.recycle();
        applySkin();
    }

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
