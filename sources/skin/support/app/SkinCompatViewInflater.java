package skin.support.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.p001v4.util.ArrayMap;
import android.support.p001v4.view.ViewCompat;
import android.support.p004v7.appcompat.C0365R;
import android.support.p004v7.view.ContextThemeWrapper;
import android.support.p004v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import skin.support.SkinCompatManager;
import skin.support.widget.SkinCompatAutoCompleteTextView;
import skin.support.widget.SkinCompatButton;
import skin.support.widget.SkinCompatCheckBox;
import skin.support.widget.SkinCompatCheckedTextView;
import skin.support.widget.SkinCompatEditText;
import skin.support.widget.SkinCompatFrameLayout;
import skin.support.widget.SkinCompatImageButton;
import skin.support.widget.SkinCompatImageView;
import skin.support.widget.SkinCompatLinearLayout;
import skin.support.widget.SkinCompatMultiAutoCompleteTextView;
import skin.support.widget.SkinCompatProgressBar;
import skin.support.widget.SkinCompatRadioButton;
import skin.support.widget.SkinCompatRadioGroup;
import skin.support.widget.SkinCompatRatingBar;
import skin.support.widget.SkinCompatRelativeLayout;
import skin.support.widget.SkinCompatScrollView;
import skin.support.widget.SkinCompatSeekBar;
import skin.support.widget.SkinCompatSpinner;
import skin.support.widget.SkinCompatTextView;
import skin.support.widget.SkinCompatToolbar;
import skin.support.widget.SkinCompatView;

/* loaded from: classes.dex */
public class SkinCompatViewInflater {
    private static final String LOG_TAG = "SkinCompatViewInflater";
    private final Object[] mConstructorArgs = new Object[2];
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private static final int[] sOnClickAttrs = {16843375};
    private static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();

    public final View createView(View parent, String name, Context context, AttributeSet attrs, boolean inheritContext, boolean readAndroidTheme, boolean readAppTheme, boolean wrapContext) {
        if (inheritContext && parent != null) {
            context = parent.getContext();
        }
        if (readAndroidTheme || readAppTheme) {
            context = themifyContext(context, attrs, readAndroidTheme, readAppTheme);
        }
        if (wrapContext) {
            context = TintContextWrapper.wrap(context);
        }
        View view = createViewFromHackInflater(context, name, attrs);
        if (view == null) {
            view = createViewFromFV(context, name, attrs);
        }
        if (view == null) {
            view = createViewFromV7(context, name, attrs);
        }
        if (view == null) {
            view = createViewFromInflater(context, name, attrs);
        }
        if (view == null) {
            view = createViewFromTag(context, name, attrs);
        }
        if (view != null) {
            checkOnClickListener(view, attrs);
        }
        return view;
    }

    private View createViewFromHackInflater(Context context, String name, AttributeSet attrs) {
        View view = null;
        for (SkinLayoutInflater inflater : SkinCompatManager.getInstance().getHookInflaters()) {
            view = inflater.createView(context, name, attrs);
            if (view != null) {
                break;
            }
        }
        return view;
    }

    private View createViewFromFV(Context context, String name, AttributeSet attrs) {
        if (name.contains(".")) {
            return null;
        }
        char c = '\uffff';
        switch (name.hashCode()) {
            case -1946472170:
                if (name.equals("RatingBar")) {
                    c = 16;
                    break;
                }
                break;
            case -1495589242:
                if (name.equals("ProgressBar")) {
                    c = 18;
                    break;
                }
                break;
            case -1455429095:
                if (name.equals("CheckedTextView")) {
                    c = '\r';
                    break;
                }
                break;
            case -1346021293:
                if (name.equals("MultiAutoCompleteTextView")) {
                    c = 15;
                    break;
                }
                break;
            case -938935918:
                if (name.equals("TextView")) {
                    c = 4;
                    break;
                }
                break;
            case -937446323:
                if (name.equals("ImageButton")) {
                    c = '\t';
                    break;
                }
                break;
            case -658531749:
                if (name.equals("SeekBar")) {
                    c = 17;
                    break;
                }
                break;
            case -443652810:
                if (name.equals("RelativeLayout")) {
                    c = 2;
                    break;
                }
                break;
            case -339785223:
                if (name.equals("Spinner")) {
                    c = '\b';
                    break;
                }
                break;
            case 2666181:
                if (name.equals("View")) {
                    c = 0;
                    break;
                }
                break;
            case 776382189:
                if (name.equals("RadioButton")) {
                    c = 11;
                    break;
                }
                break;
            case 1125864064:
                if (name.equals("ImageView")) {
                    c = 5;
                    break;
                }
                break;
            case 1127291599:
                if (name.equals("LinearLayout")) {
                    c = 1;
                    break;
                }
                break;
            case 1310765783:
                if (name.equals("FrameLayout")) {
                    c = 3;
                    break;
                }
                break;
            case 1413872058:
                if (name.equals("AutoCompleteTextView")) {
                    c = 14;
                    break;
                }
                break;
            case 1601505219:
                if (name.equals("CheckBox")) {
                    c = '\n';
                    break;
                }
                break;
            case 1666676343:
                if (name.equals("EditText")) {
                    c = 7;
                    break;
                }
                break;
            case 1969230692:
                if (name.equals("RadioGroup")) {
                    c = '\f';
                    break;
                }
                break;
            case 2001146706:
                if (name.equals("Button")) {
                    c = 6;
                    break;
                }
                break;
            case 2059813682:
                if (name.equals("ScrollView")) {
                    c = 19;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                View view = new SkinCompatView(context, attrs);
                return view;
            case 1:
                View view2 = new SkinCompatLinearLayout(context, attrs);
                return view2;
            case 2:
                View view3 = new SkinCompatRelativeLayout(context, attrs);
                return view3;
            case 3:
                View view4 = new SkinCompatFrameLayout(context, attrs);
                return view4;
            case 4:
                View view5 = new SkinCompatTextView(context, attrs);
                return view5;
            case 5:
                View view6 = new SkinCompatImageView(context, attrs);
                return view6;
            case 6:
                View view7 = new SkinCompatButton(context, attrs);
                return view7;
            case 7:
                View view8 = new SkinCompatEditText(context, attrs);
                return view8;
            case '\b':
                View view9 = new SkinCompatSpinner(context, attrs);
                return view9;
            case '\t':
                View view10 = new SkinCompatImageButton(context, attrs);
                return view10;
            case '\n':
                View view11 = new SkinCompatCheckBox(context, attrs);
                return view11;
            case 11:
                View view12 = new SkinCompatRadioButton(context, attrs);
                return view12;
            case '\f':
                View view13 = new SkinCompatRadioGroup(context, attrs);
                return view13;
            case '\r':
                View view14 = new SkinCompatCheckedTextView(context, attrs);
                return view14;
            case 14:
                View view15 = new SkinCompatAutoCompleteTextView(context, attrs);
                return view15;
            case 15:
                View view16 = new SkinCompatMultiAutoCompleteTextView(context, attrs);
                return view16;
            case 16:
                View view17 = new SkinCompatRatingBar(context, attrs);
                return view17;
            case 17:
                View view18 = new SkinCompatSeekBar(context, attrs);
                return view18;
            case 18:
                View view19 = new SkinCompatProgressBar(context, attrs);
                return view19;
            case 19:
                View view20 = new SkinCompatScrollView(context, attrs);
                return view20;
            default:
                return null;
        }
    }

    private View createViewFromV7(Context context, String name, AttributeSet attrs) {
        char c;
        switch (name.hashCode()) {
            case -254446176:
                if (name.equals("android.support.v7.widget.Toolbar")) {
                    c = 0;
                    break;
                }
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                View view = new SkinCompatToolbar(context, attrs);
                return view;
            default:
                return null;
        }
    }

    private View createViewFromInflater(Context context, String name, AttributeSet attrs) {
        View view = null;
        for (SkinLayoutInflater inflater : SkinCompatManager.getInstance().getInflaters()) {
            view = inflater.createView(context, name, attrs);
            if (view != null) {
                break;
            }
        }
        return view;
    }

    public View createViewFromTag(Context context, String name, AttributeSet attrs) {
        if (name.equals("view")) {
            name = attrs.getAttributeValue(null, "class");
        }
        try {
            Object[] objArr = this.mConstructorArgs;
            objArr[0] = context;
            objArr[1] = attrs;
            if (-1 != name.indexOf(46)) {
                return createView(context, name, null);
            }
            int i = 0;
            while (true) {
                String[] strArr = sClassPrefixList;
                if (i >= strArr.length) {
                    return null;
                }
                View view = createView(context, name, strArr[i]);
                if (view != null) {
                    return view;
                }
                i++;
            }
        } catch (Exception e) {
            return null;
        } finally {
            Object[] objArr2 = this.mConstructorArgs;
            objArr2[0] = null;
            objArr2[1] = null;
        }
    }

    private void checkOnClickListener(View view, AttributeSet attrs) {
        Context context = view.getContext();
        if (context instanceof ContextWrapper) {
            if (Build.VERSION.SDK_INT >= 15 && !ViewCompat.hasOnClickListeners(view)) {
                return;
            }
            TypedArray a = context.obtainStyledAttributes(attrs, sOnClickAttrs);
            String handlerName = a.getString(0);
            if (handlerName != null) {
                view.setOnClickListener(new DeclaredOnClickListener(view, handlerName));
            }
            a.recycle();
        }
    }

    private View createView(Context context, String name, String prefix) throws ClassNotFoundException, InflateException {
        Map<String, Constructor<? extends View>> map = sConstructorMap;
        Constructor<? extends View> constructor = map.get(name);
        if (constructor == null) {
            try {
                constructor = context.getClassLoader().loadClass(prefix != null ? prefix + name : name).asSubclass(View.class).getConstructor(sConstructorSignature);
                map.put(name, constructor);
            } catch (Exception e) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return constructor.newInstance(this.mConstructorArgs);
    }

    private static Context themifyContext(Context context, AttributeSet attrs, boolean useAndroidTheme, boolean useAppTheme) {
        TypedArray a = context.obtainStyledAttributes(attrs, C0365R.styleable.View, 0, 0);
        int themeId = 0;
        if (useAndroidTheme) {
            themeId = a.getResourceId(C0365R.styleable.View_android_theme, 0);
        }
        if (useAppTheme && themeId == 0 && (themeId = a.getResourceId(C0365R.styleable.View_theme, 0)) != 0) {
            Log.i(LOG_TAG, "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        a.recycle();
        if (themeId != 0) {
            if (!(context instanceof ContextThemeWrapper) || ((ContextThemeWrapper) context).getThemeResId() != themeId) {
                return new ContextThemeWrapper(context, themeId);
            }
            return context;
        }
        return context;
    }

    /* loaded from: classes.dex */
    private static class DeclaredOnClickListener implements View.OnClickListener {
        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public DeclaredOnClickListener(View hostView, String methodName) {
            this.mHostView = hostView;
            this.mMethodName = methodName;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (this.mResolvedMethod == null) {
                resolveMethod(this.mHostView.getContext(), this.mMethodName);
            }
            try {
                this.mResolvedMethod.invoke(this.mResolvedContext, v);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }

        private void resolveMethod(Context context, String name) {
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.mMethodName, View.class)) != null) {
                        this.mResolvedMethod = method;
                        this.mResolvedContext = context;
                        return;
                    }
                } catch (NoSuchMethodException e) {
                }
                if (context instanceof ContextWrapper) {
                    context = ((ContextWrapper) context).getBaseContext();
                } else {
                    context = null;
                }
            }
            int id = this.mHostView.getId();
            String idText = id == -1 ? "" : " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(id) + "'";
            throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.mHostView.getClass() + idText);
        }
    }
}
