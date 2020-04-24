package butterknife.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class ViewInjector {
    private final String className;
    private final String classPackage;
    private final Map<CollectionBinding, int[]> collectionBindings = new LinkedHashMap();
    private String parentInjector;
    private final String targetClass;
    private final Map<Integer, ViewInjection> viewIdMap = new LinkedHashMap();

    ViewInjector(String classPackage2, String className2, String targetClass2) {
        this.classPackage = classPackage2;
        this.className = className2;
        this.targetClass = targetClass2;
    }

    /* access modifiers changed from: package-private */
    public void addView(int id, ViewBinding binding) {
        getOrCreateViewInjection(id).addViewBinding(binding);
    }

    /* access modifiers changed from: package-private */
    public boolean addListener(int id, ListenerClass listener, ListenerMethod method, ListenerBinding binding) {
        ViewInjection viewInjection = getOrCreateViewInjection(id);
        if (viewInjection.hasListenerBinding(listener, method)) {
            return false;
        }
        viewInjection.addListenerBinding(listener, method, binding);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void addCollection(int[] ids, CollectionBinding binding) {
        this.collectionBindings.put(binding, ids);
    }

    /* access modifiers changed from: package-private */
    public void setParentInjector(String parentInjector2) {
        this.parentInjector = parentInjector2;
    }

    private ViewInjection getOrCreateViewInjection(int id) {
        ViewInjection viewId = this.viewIdMap.get(Integer.valueOf(id));
        if (viewId != null) {
            return viewId;
        }
        ViewInjection viewId2 = new ViewInjection(id);
        this.viewIdMap.put(Integer.valueOf(id), viewId2);
        return viewId2;
    }

    /* access modifiers changed from: package-private */
    public String getFqcn() {
        return this.classPackage + "." + this.className;
    }

    /* access modifiers changed from: package-private */
    public String brewJava() {
        StringBuilder builder = new StringBuilder();
        builder.append("// Generated code from Butter Knife. Do not modify!\n");
        builder.append("package ");
        builder.append(this.classPackage);
        builder.append(";\n\n");
        builder.append("import android.view.View;\n");
        builder.append("import butterknife.ButterKnife.Finder;\n\n");
        builder.append("public class ");
        builder.append(this.className);
        builder.append(" {\n");
        emitInject(builder);
        builder.append(10);
        emitReset(builder);
        builder.append("}\n");
        return builder.toString();
    }

    private void emitInject(StringBuilder builder) {
        builder.append("  public static void inject(Finder finder, final ");
        builder.append(this.targetClass);
        builder.append(" target, Object source) {\n");
        if (this.parentInjector != null) {
            builder.append("    ");
            builder.append(this.parentInjector);
            builder.append(".inject(finder, target, source);\n\n");
        }
        builder.append("    View view;\n");
        for (ViewInjection injection : this.viewIdMap.values()) {
            emitViewInjection(builder, injection);
        }
        for (Map.Entry<CollectionBinding, int[]> entry : this.collectionBindings.entrySet()) {
            emitCollectionBinding(builder, entry.getKey(), entry.getValue());
        }
        builder.append("  }\n");
    }

    private void emitCollectionBinding(StringBuilder builder, CollectionBinding binding, int[] ids) {
        builder.append("    target.");
        builder.append(binding.getName());
        builder.append(" = ");
        switch (binding.getKind()) {
            case ARRAY:
                builder.append("Finder.arrayOf(");
                break;
            case LIST:
                builder.append("Finder.listOf(");
                break;
            default:
                throw new IllegalStateException("Unknown kind: " + binding.getKind());
        }
        for (int i = 0; i < ids.length; i++) {
            if (i > 0) {
                builder.append(',');
            }
            builder.append("\n        ");
            emitCastIfNeeded(builder, binding.getType());
            if (binding.isRequired()) {
                builder.append("finder.findRequiredView(source, ");
                builder.append(ids[i]);
                builder.append(", \"");
                builder.append(binding.getName());
                builder.append("\")");
            } else {
                builder.append("finder.findOptionalView(source, ");
                builder.append(ids[i]);
                builder.append(")");
            }
        }
        builder.append("\n    );");
    }

    private void emitViewInjection(StringBuilder builder, ViewInjection injection) {
        builder.append("    view = ");
        List<Binding> requiredBindings = injection.getRequiredBindings();
        if (requiredBindings.isEmpty()) {
            builder.append("finder.findOptionalView(source, ");
            builder.append(injection.getId());
            builder.append(");\n");
        } else {
            builder.append("finder.findRequiredView(source, ");
            builder.append(injection.getId());
            builder.append(", \"");
            emitHumanDescription(builder, requiredBindings);
            builder.append("\");\n");
        }
        emitViewBindings(builder, injection);
        emitListenerBindings(builder, injection);
    }

    private void emitViewBindings(StringBuilder builder, ViewInjection injection) {
        Collection<ViewBinding> viewBindings = injection.getViewBindings();
        if (!viewBindings.isEmpty()) {
            for (ViewBinding viewBinding : viewBindings) {
                builder.append("    target.");
                builder.append(viewBinding.getName());
                builder.append(" = ");
                emitCastIfNeeded(builder, viewBinding.getType());
                builder.append("view;\n");
            }
        }
    }

    private void emitListenerBindings(StringBuilder builder, ViewInjection injection) {
        ListenerClass listener;
        Map.Entry<ListenerClass, Map<ListenerMethod, ListenerBinding>> e;
        Iterator<Map.Entry<ListenerClass, Map<ListenerMethod, ListenerBinding>>> it;
        Map<ListenerClass, Map<ListenerMethod, ListenerBinding>> bindings;
        StringBuilder sb = builder;
        Map<ListenerClass, Map<ListenerMethod, ListenerBinding>> bindings2 = injection.getListenerBindings();
        if (!bindings2.isEmpty()) {
            String extraIndent = "";
            boolean needsNullChecked = injection.getRequiredBindings().isEmpty();
            if (needsNullChecked) {
                sb.append("    if (view != null) {\n");
                extraIndent = "  ";
            }
            Iterator<Map.Entry<ListenerClass, Map<ListenerMethod, ListenerBinding>>> it2 = bindings2.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<ListenerClass, Map<ListenerMethod, ListenerBinding>> e2 = it2.next();
                ListenerClass listener2 = e2.getKey();
                Map<ListenerMethod, ListenerBinding> methodBindings = e2.getValue();
                boolean needsCast = !"android.view.View".equals(listener2.targetType());
                sb.append(extraIndent);
                sb.append("    ");
                if (needsCast) {
                    sb.append("((");
                    sb.append(listener2.targetType());
                    if (listener2.genericArguments() > 0) {
                        sb.append('<');
                        for (int i = 0; i < listener2.genericArguments(); i++) {
                            if (i > 0) {
                                sb.append(", ");
                            }
                            sb.append('?');
                        }
                        sb.append('>');
                    }
                    sb.append(") ");
                }
                sb.append("view");
                if (needsCast) {
                    sb.append(')');
                }
                sb.append('.');
                sb.append(listener2.setter());
                sb.append("(\n");
                sb.append(extraIndent);
                sb.append("      new ");
                sb.append(listener2.type());
                sb.append("() {\n");
                for (ListenerMethod method : getListenerMethods(listener2)) {
                    sb.append(extraIndent);
                    sb.append("        @Override public ");
                    sb.append(method.returnType());
                    sb.append(' ');
                    sb.append(method.name());
                    sb.append("(\n");
                    String[] parameterTypes = method.parameters();
                    int count = parameterTypes.length;
                    for (int i2 = 0; i2 < count; i2++) {
                        sb.append(extraIndent);
                        sb.append("          ");
                        sb.append(parameterTypes[i2]);
                        sb.append(" p");
                        sb.append(i2);
                        if (i2 < count - 1) {
                            sb.append(',');
                        }
                        sb.append(10);
                    }
                    sb.append(extraIndent);
                    sb.append("        ) {\n");
                    sb.append(extraIndent);
                    sb.append("          ");
                    boolean hasReturnType = !"void".equals(method.returnType());
                    if (hasReturnType) {
                        sb.append("return ");
                    }
                    if (methodBindings.containsKey(method)) {
                        ListenerBinding binding = methodBindings.get(method);
                        sb.append("target.");
                        sb.append(binding.getName());
                        sb.append('(');
                        List<Parameter> parameters = binding.getParameters();
                        String[] listenerParameters = method.parameters();
                        int count2 = parameters.size();
                        int i3 = 0;
                        while (true) {
                            bindings = bindings2;
                            int count3 = count2;
                            if (i3 >= count3) {
                                break;
                            }
                            Parameter parameter = parameters.get(i3);
                            Iterator<Map.Entry<ListenerClass, Map<ListenerMethod, ListenerBinding>>> it3 = it2;
                            int listenerPosition = parameter.getListenerPosition();
                            Map.Entry<ListenerClass, Map<ListenerMethod, ListenerBinding>> e3 = e2;
                            ListenerClass listener3 = listener2;
                            emitCastIfNeeded(sb, listenerParameters[listenerPosition], parameter.getType());
                            sb.append('p');
                            sb.append(listenerPosition);
                            if (i3 < count3 - 1) {
                                sb.append(", ");
                            }
                            i3++;
                            count2 = count3;
                            bindings2 = bindings;
                            it2 = it3;
                            e2 = e3;
                            listener2 = listener3;
                        }
                        it = it2;
                        e = e2;
                        listener = listener2;
                        sb.append(");");
                    } else {
                        bindings = bindings2;
                        it = it2;
                        e = e2;
                        listener = listener2;
                        if (hasReturnType) {
                            sb.append(method.defaultReturn());
                            sb.append(';');
                        }
                    }
                    sb.append(10);
                    sb.append(extraIndent);
                    sb.append("        }\n");
                    bindings2 = bindings;
                    it2 = it;
                    e2 = e;
                    listener2 = listener;
                }
                Iterator<Map.Entry<ListenerClass, Map<ListenerMethod, ListenerBinding>>> it4 = it2;
                Map.Entry<ListenerClass, Map<ListenerMethod, ListenerBinding>> entry = e2;
                ListenerClass listenerClass = listener2;
                sb.append(extraIndent);
                sb.append("      });\n");
                bindings2 = bindings2;
            }
            if (needsNullChecked) {
                sb.append("    }\n");
            }
        }
    }

    static List<ListenerMethod> getListenerMethods(ListenerClass listener) {
        if (listener.method().length == 1) {
            return Arrays.asList(listener.method());
        }
        try {
            List<ListenerMethod> methods = new ArrayList<>();
            Class<? extends Enum<?>> callbacks = listener.callbacks();
            Enum<?>[] enumArr = (Enum[]) callbacks.getEnumConstants();
            int length = enumArr.length;
            int i = 0;
            while (i < length) {
                Enum<?> callbackMethod = enumArr[i];
                ListenerMethod method = (ListenerMethod) callbacks.getField(callbackMethod.name()).getAnnotation(ListenerMethod.class);
                if (method != null) {
                    methods.add(method);
                    i++;
                } else {
                    throw new IllegalStateException(String.format("@%s's %s.%s missing @%s annotation.", new Object[]{callbacks.getEnclosingClass().getSimpleName(), callbacks.getSimpleName(), callbackMethod.name(), ListenerMethod.class.getSimpleName()}));
                }
            }
            return methods;
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    private void emitReset(StringBuilder builder) {
        builder.append("  public static void reset(");
        builder.append(this.targetClass);
        builder.append(" target) {\n");
        if (this.parentInjector != null) {
            builder.append("    ");
            builder.append(this.parentInjector);
            builder.append(".reset(target);\n\n");
        }
        for (ViewInjection injection : this.viewIdMap.values()) {
            for (ViewBinding viewBinding : injection.getViewBindings()) {
                builder.append("    target.");
                builder.append(viewBinding.getName());
                builder.append(" = null;\n");
            }
        }
        for (CollectionBinding collectionBinding : this.collectionBindings.keySet()) {
            builder.append("    target.");
            builder.append(collectionBinding.getName());
            builder.append(" = null;\n");
        }
        builder.append("  }\n");
    }

    static void emitCastIfNeeded(StringBuilder builder, String viewType) {
        emitCastIfNeeded(builder, "android.view.View", viewType);
    }

    static void emitCastIfNeeded(StringBuilder builder, String sourceType, String destinationType) {
        if (!sourceType.equals(destinationType)) {
            builder.append('(');
            builder.append(destinationType);
            builder.append(") ");
        }
    }

    static void emitHumanDescription(StringBuilder builder, List<Binding> bindings) {
        switch (bindings.size()) {
            case 1:
                builder.append(bindings.get(0).getDescription());
                return;
            case 2:
                builder.append(bindings.get(0).getDescription());
                builder.append(" and ");
                builder.append(bindings.get(1).getDescription());
                return;
            default:
                int count = bindings.size();
                for (int i = 0; i < count; i++) {
                    Binding requiredField = bindings.get(i);
                    if (i != 0) {
                        builder.append(", ");
                    }
                    if (i == count - 1) {
                        builder.append("and ");
                    }
                    builder.append(requiredField.getDescription());
                }
                return;
        }
    }
}
