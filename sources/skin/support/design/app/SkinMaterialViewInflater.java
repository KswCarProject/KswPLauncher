package skin.support.design.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import skin.support.app.SkinLayoutInflater;
import skin.support.design.widget.SkinMaterialAppBarLayout;
import skin.support.design.widget.SkinMaterialBottomNavigationView;
import skin.support.design.widget.SkinMaterialCollapsingToolbarLayout;
import skin.support.design.widget.SkinMaterialCoordinatorLayout;
import skin.support.design.widget.SkinMaterialFloatingActionButton;
import skin.support.design.widget.SkinMaterialNavigationView;
import skin.support.design.widget.SkinMaterialTabLayout;
import skin.support.design.widget.SkinMaterialTextInputEditText;
import skin.support.design.widget.SkinMaterialTextInputLayout;

/* loaded from: classes.dex */
public class SkinMaterialViewInflater implements SkinLayoutInflater {
    @Override // skin.support.app.SkinLayoutInflater
    public View createView(Context context, String name, AttributeSet attrs) {
        if (!name.startsWith("android.support.design.widget.")) {
            return null;
        }
        char c = '\uffff';
        switch (name.hashCode()) {
            case -1830764433:
                if (name.equals("android.support.design.widget.TextInputLayout")) {
                    c = 2;
                    break;
                }
                break;
            case -1138726461:
                if (name.equals("android.support.design.widget.CollapsingToolbarLayout")) {
                    c = '\b';
                    break;
                }
                break;
            case -272444186:
                if (name.equals("android.support.design.widget.FloatingActionButton")) {
                    c = 5;
                    break;
                }
                break;
            case 170302044:
                if (name.equals("android.support.design.widget.TextInputEditText")) {
                    c = 3;
                    break;
                }
                break;
            case 285085340:
                if (name.equals("android.support.design.widget.BottomNavigationView")) {
                    c = 6;
                    break;
                }
                break;
            case 285456578:
                if (name.equals("android.support.design.widget.CoordinatorLayout")) {
                    c = 7;
                    break;
                }
                break;
            case 796212404:
                if (name.equals("android.support.design.widget.AppBarLayout")) {
                    c = 0;
                    break;
                }
                break;
            case 890321297:
                if (name.equals("android.support.design.widget.NavigationView")) {
                    c = 4;
                    break;
                }
                break;
            case 951543143:
                if (name.equals("android.support.design.widget.TabLayout")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new SkinMaterialAppBarLayout(context, attrs);
            case 1:
                return new SkinMaterialTabLayout(context, attrs);
            case 2:
                return new SkinMaterialTextInputLayout(context, attrs);
            case 3:
                return new SkinMaterialTextInputEditText(context, attrs);
            case 4:
                return new SkinMaterialNavigationView(context, attrs);
            case 5:
                return new SkinMaterialFloatingActionButton(context, attrs);
            case 6:
                return new SkinMaterialBottomNavigationView(context, attrs);
            case 7:
                View view = new SkinMaterialCoordinatorLayout(context, attrs);
                return view;
            case '\b':
                return new SkinMaterialCollapsingToolbarLayout(context, attrs);
            default:
                return null;
        }
    }
}
