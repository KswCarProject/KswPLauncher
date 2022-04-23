package skin.support.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
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
import skin.support.widget.SkinCompatView;

public class SkinCompatViewInflater {
    private static final String LOG_TAG = "SkinCompatViewInflater";
    private static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private static final int[] sOnClickAttrs = {16843375};
    private final Object[] mConstructorArgs = new Object[2];

    public final View createView(View parent, String name, Context context, AttributeSet attrs, boolean inheritContext, boolean readAndroidTheme, boolean readAppTheme, boolean wrapContext) {
        Context context2 = context;
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

    /* JADX WARNING: Removed duplicated region for block: B:1:0x000d A[LOOP:0: B:1:0x000d->B:4:0x001d, LOOP_START, PHI: r0 
      PHI: (r0v1 'view' android.view.View) = (r0v0 'view' android.view.View), (r0v3 'view' android.view.View) binds: [B:0:0x0000, B:4:0x001d] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View createViewFromHackInflater(android.content.Context r4, java.lang.String r5, android.util.AttributeSet r6) {
        /*
            r3 = this;
            r0 = 0
            skin.support.SkinCompatManager r1 = skin.support.SkinCompatManager.getInstance()
            java.util.List r1 = r1.getHookInflaters()
            java.util.Iterator r1 = r1.iterator()
        L_0x000d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0020
            java.lang.Object r2 = r1.next()
            skin.support.app.SkinLayoutInflater r2 = (skin.support.app.SkinLayoutInflater) r2
            android.view.View r0 = r2.createView(r4, r5, r6)
            if (r0 != 0) goto L_0x0020
            goto L_0x000d
        L_0x0020:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: skin.support.app.SkinCompatViewInflater.createViewFromHackInflater(android.content.Context, java.lang.String, android.util.AttributeSet):android.view.View");
    }

    private View createViewFromFV(Context context, String name, AttributeSet attrs) {
        if (name.contains(".")) {
            return null;
        }
        char c = 65535;
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
                    c = 13;
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
                    c = 9;
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
                    c = 8;
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
                    c = 10;
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
                    c = 12;
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
                return new SkinCompatView(context, attrs);
            case 1:
                return new SkinCompatLinearLayout(context, attrs);
            case 2:
                return new SkinCompatRelativeLayout(context, attrs);
            case 3:
                return new SkinCompatFrameLayout(context, attrs);
            case 4:
                return new SkinCompatTextView(context, attrs);
            case 5:
                return new SkinCompatImageView(context, attrs);
            case 6:
                return new SkinCompatButton(context, attrs);
            case 7:
                return new SkinCompatEditText(context, attrs);
            case 8:
                return new SkinCompatSpinner(context, attrs);
            case 9:
                return new SkinCompatImageButton(context, attrs);
            case 10:
                return new SkinCompatCheckBox(context, attrs);
            case 11:
                return new SkinCompatRadioButton(context, attrs);
            case 12:
                return new SkinCompatRadioGroup(context, attrs);
            case 13:
                return new SkinCompatCheckedTextView(context, attrs);
            case 14:
                return new SkinCompatAutoCompleteTextView(context, attrs);
            case 15:
                return new SkinCompatMultiAutoCompleteTextView(context, attrs);
            case 16:
                return new SkinCompatRatingBar(context, attrs);
            case 17:
                return new SkinCompatSeekBar(context, attrs);
            case 18:
                return new SkinCompatProgressBar(context, attrs);
            case 19:
                return new SkinCompatScrollView(context, attrs);
            default:
                return null;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View createViewFromV7(android.content.Context r3, java.lang.String r4, android.util.AttributeSet r5) {
        /*
            r2 = this;
            r0 = 0
            int r1 = r4.hashCode()
            switch(r1) {
                case -254446176: goto L_0x0009;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0013
        L_0x0009:
            java.lang.String r1 = "android.support.v7.widget.Toolbar"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 0
            goto L_0x0014
        L_0x0013:
            r1 = -1
        L_0x0014:
            switch(r1) {
                case 0: goto L_0x0018;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x001e
        L_0x0018:
            skin.support.widget.SkinCompatToolbar r1 = new skin.support.widget.SkinCompatToolbar
            r1.<init>(r3, r5)
            r0 = r1
        L_0x001e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: skin.support.app.SkinCompatViewInflater.createViewFromV7(android.content.Context, java.lang.String, android.util.AttributeSet):android.view.View");
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x000d A[LOOP:0: B:1:0x000d->B:4:0x001d, LOOP_START, PHI: r0 
      PHI: (r0v1 'view' android.view.View) = (r0v0 'view' android.view.View), (r0v3 'view' android.view.View) binds: [B:0:0x0000, B:4:0x001d] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View createViewFromInflater(android.content.Context r4, java.lang.String r5, android.util.AttributeSet r6) {
        /*
            r3 = this;
            r0 = 0
            skin.support.SkinCompatManager r1 = skin.support.SkinCompatManager.getInstance()
            java.util.List r1 = r1.getInflaters()
            java.util.Iterator r1 = r1.iterator()
        L_0x000d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0020
            java.lang.Object r2 = r1.next()
            skin.support.app.SkinLayoutInflater r2 = (skin.support.app.SkinLayoutInflater) r2
            android.view.View r0 = r2.createView(r4, r5, r6)
            if (r0 != 0) goto L_0x0020
            goto L_0x000d
        L_0x0020:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: skin.support.app.SkinCompatViewInflater.createViewFromInflater(android.content.Context, java.lang.String, android.util.AttributeSet):android.view.View");
    }

    public View createViewFromTag(Context context, String name, AttributeSet attrs) {
        if (name.equals("view")) {
            name = attrs.getAttributeValue((String) null, "class");
        }
        try {
            Object[] objArr = this.mConstructorArgs;
            objArr[0] = context;
            objArr[1] = attrs;
            if (-1 == name.indexOf(46)) {
                int i = 0;
                while (true) {
                    String[] strArr = sClassPrefixList;
                    if (i < strArr.length) {
                        View view = createView(context, name, strArr[i]);
                        if (view != null) {
                            return view;
                        }
                        i++;
                    } else {
                        Object[] objArr2 = this.mConstructorArgs;
                        objArr2[0] = null;
                        objArr2[1] = null;
                        return null;
                    }
                }
            } else {
                View createView = createView(context, name, (String) null);
                Object[] objArr3 = this.mConstructorArgs;
                objArr3[0] = null;
                objArr3[1] = null;
                return createView;
            }
        } catch (Exception e) {
            return null;
        } finally {
            Object[] objArr4 = this.mConstructorArgs;
            objArr4[0] = null;
            objArr4[1] = null;
        }
    }

    private void checkOnClickListener(View view, AttributeSet attrs) {
        Context context = view.getContext();
        if (!(context instanceof ContextWrapper)) {
            return;
        }
        if (Build.VERSION.SDK_INT < 15 || ViewCompat.hasOnClickListeners(view)) {
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
        Constructor<? extends U> constructor = map.get(name);
        if (constructor == null) {
            try {
                constructor = context.getClassLoader().loadClass(prefix != null ? prefix + name : name).asSubclass(View.class).getConstructor(sConstructorSignature);
                map.put(name, constructor);
            } catch (Exception e) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.mConstructorArgs);
    }

    private static Context themifyContext(Context context, AttributeSet attrs, boolean useAndroidTheme, boolean useAppTheme) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.View, 0, 0);
        int themeId = 0;
        if (useAndroidTheme) {
            themeId = a.getResourceId(R.styleable.View_android_theme, 0);
        }
        if (useAppTheme && themeId == 0 && (themeId = a.getResourceId(R.styleable.View_theme, 0)) != 0) {
            Log.i(LOG_TAG, "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        a.recycle();
        if (themeId == 0) {
            return context;
        }
        if (!(context instanceof ContextThemeWrapper) || ((ContextThemeWrapper) context).getThemeResId() != themeId) {
            return new ContextThemeWrapper(context, themeId);
        }
        return context;
    }

    private static class DeclaredOnClickListener implements View.OnClickListener {
        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public DeclaredOnClickListener(View hostView, String methodName) {
            this.mHostView = hostView;
            this.mMethodName = methodName;
        }

        public void onClick(View v) {
            if (this.mResolvedMethod == null) {
                resolveMethod(this.mHostView.getContext(), this.mMethodName);
            }
            try {
                this.mResolvedMethod.invoke(this.mResolvedContext, new Object[]{v});
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }

        private void resolveMethod(Context context, String name) {
            String idText;
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.mMethodName, new Class[]{View.class})) != null) {
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
            if (id == -1) {
                idText = "";
            } else {
                idText = " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.mHostView.getClass() + idText);
        }
    }
}
