package skin.support.constraint.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import skin.support.app.SkinLayoutInflater;
import skin.support.constraint.SkinCompatConstraintLayout;

/* loaded from: classes.dex */
public class SkinConstraintViewInflater implements SkinLayoutInflater {
    @Override // skin.support.app.SkinLayoutInflater
    public View createView(Context context, String name, AttributeSet attrs) {
        char c;
        switch (name.hashCode()) {
            case 1050766810:
                if (name.equals("android.support.constraint.ConstraintLayout")) {
                    c = 0;
                    break;
                }
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                View view = new SkinCompatConstraintLayout(context, attrs);
                return view;
            default:
                return null;
        }
    }
}
