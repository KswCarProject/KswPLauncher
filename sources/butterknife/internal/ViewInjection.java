package butterknife.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class ViewInjection {
    private final int id;
    private final Map<ListenerClass, Map<ListenerMethod, ListenerBinding>> listenerBindings = new LinkedHashMap();
    private final Set<ViewBinding> viewBindings = new LinkedHashSet();

    ViewInjection(int id2) {
        this.id = id2;
    }

    public int getId() {
        return this.id;
    }

    public Collection<ViewBinding> getViewBindings() {
        return this.viewBindings;
    }

    public Map<ListenerClass, Map<ListenerMethod, ListenerBinding>> getListenerBindings() {
        return this.listenerBindings;
    }

    public boolean hasListenerBinding(ListenerClass listener, ListenerMethod method) {
        Map<ListenerMethod, ListenerBinding> methods = this.listenerBindings.get(listener);
        return methods != null && methods.containsKey(method);
    }

    public void addListenerBinding(ListenerClass listener, ListenerMethod method, ListenerBinding binding) {
        Map<ListenerMethod, ListenerBinding> methods = this.listenerBindings.get(listener);
        if (methods == null) {
            methods = new LinkedHashMap<>();
            this.listenerBindings.put(listener, methods);
        }
        ListenerBinding existing = methods.get(method);
        if (existing == null) {
            methods.put(method, binding);
            return;
        }
        throw new IllegalStateException("View " + this.id + " already has listener binding for " + listener.type() + "." + method.name() + " on " + existing.getDescription());
    }

    public void addViewBinding(ViewBinding viewBinding) {
        this.viewBindings.add(viewBinding);
    }

    public List<Binding> getRequiredBindings() {
        List<Binding> requiredBindings = new ArrayList<>();
        for (ViewBinding viewBinding : this.viewBindings) {
            if (viewBinding.isRequired()) {
                requiredBindings.add(viewBinding);
            }
        }
        for (Map<ListenerMethod, ListenerBinding> methodBinding : this.listenerBindings.values()) {
            for (ListenerBinding binding : methodBinding.values()) {
                if (binding.isRequired()) {
                    requiredBindings.add(binding);
                }
            }
        }
        return requiredBindings;
    }
}
