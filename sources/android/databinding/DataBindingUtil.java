package android.databinding;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/* loaded from: classes.dex */
public class DataBindingUtil {
    private static DataBinderMapper sMapper = new DataBinderMapperImpl();
    private static DataBindingComponent sDefaultComponent = null;

    private DataBindingUtil() {
    }

    public static void setDefaultComponent(DataBindingComponent bindingComponent) {
        sDefaultComponent = bindingComponent;
    }

    public static DataBindingComponent getDefaultComponent() {
        return sDefaultComponent;
    }

    public static <T extends ViewDataBinding> T inflate(LayoutInflater inflater, int layoutId, ViewGroup parent, boolean attachToParent) {
        return (T) inflate(inflater, layoutId, parent, attachToParent, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T inflate(LayoutInflater inflater, int layoutId, ViewGroup parent, boolean attachToParent, DataBindingComponent bindingComponent) {
        boolean useChildren = parent != null && attachToParent;
        int startChildren = useChildren ? parent.getChildCount() : 0;
        View view = inflater.inflate(layoutId, parent, attachToParent);
        if (useChildren) {
            return (T) bindToAddedViews(bindingComponent, parent, startChildren, layoutId);
        }
        return (T) bind(bindingComponent, view, layoutId);
    }

    public static <T extends ViewDataBinding> T bind(View root) {
        return (T) bind(root, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T bind(View root, DataBindingComponent bindingComponent) {
        T binding = (T) getBinding(root);
        if (binding != null) {
            return binding;
        }
        Object tagObj = root.getTag();
        if (!(tagObj instanceof String)) {
            throw new IllegalArgumentException("View is not a binding layout");
        }
        String tag = (String) tagObj;
        int layoutId = sMapper.getLayoutId(tag);
        if (layoutId == 0) {
            throw new IllegalArgumentException("View is not a binding layout. Tag: " + tagObj);
        }
        return (T) sMapper.getDataBinder(bindingComponent, root, layoutId);
    }

    static <T extends ViewDataBinding> T bind(DataBindingComponent bindingComponent, View[] roots, int layoutId) {
        return (T) sMapper.getDataBinder(bindingComponent, roots, layoutId);
    }

    static <T extends ViewDataBinding> T bind(DataBindingComponent bindingComponent, View root, int layoutId) {
        return (T) sMapper.getDataBinder(bindingComponent, root, layoutId);
    }

    public static <T extends ViewDataBinding> T findBinding(View view) {
        while (view != null) {
            T t = (T) ViewDataBinding.getBinding(view);
            if (t != null) {
                return t;
            }
            Object tag = view.getTag();
            if (tag instanceof String) {
                String tagString = (String) tag;
                if (tagString.startsWith("layout") && tagString.endsWith("_0")) {
                    char nextChar = tagString.charAt(6);
                    int slashIndex = tagString.indexOf(47, 7);
                    boolean isUnboundRoot = false;
                    if (nextChar == '/') {
                        isUnboundRoot = slashIndex == -1;
                    } else if (nextChar == '-' && slashIndex != -1) {
                        int nextSlashIndex = tagString.indexOf(47, slashIndex + 1);
                        isUnboundRoot = nextSlashIndex == -1;
                    }
                    if (isUnboundRoot) {
                        return null;
                    }
                }
            }
            ViewParent viewParent = view.getParent();
            if (viewParent instanceof View) {
                view = (View) viewParent;
            } else {
                view = null;
            }
        }
        return null;
    }

    public static <T extends ViewDataBinding> T getBinding(View view) {
        return (T) ViewDataBinding.getBinding(view);
    }

    public static <T extends ViewDataBinding> T setContentView(Activity activity, int layoutId) {
        return (T) setContentView(activity, layoutId, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T setContentView(Activity activity, int layoutId, DataBindingComponent bindingComponent) {
        activity.setContentView(layoutId);
        View decorView = activity.getWindow().getDecorView();
        ViewGroup contentView = (ViewGroup) decorView.findViewById(16908290);
        return (T) bindToAddedViews(bindingComponent, contentView, 0, layoutId);
    }

    public static String convertBrIdToString(int id) {
        return sMapper.convertBrIdToString(id);
    }

    private static <T extends ViewDataBinding> T bindToAddedViews(DataBindingComponent component, ViewGroup parent, int startChildren, int layoutId) {
        int endChildren = parent.getChildCount();
        int childrenAdded = endChildren - startChildren;
        if (childrenAdded == 1) {
            View childView = parent.getChildAt(endChildren - 1);
            return (T) bind(component, childView, layoutId);
        }
        View[] children = new View[childrenAdded];
        for (int i = 0; i < childrenAdded; i++) {
            children[i] = parent.getChildAt(i + startChildren);
        }
        return (T) bind(component, children, layoutId);
    }
}
