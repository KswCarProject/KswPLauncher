package butterknife.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
final class ListenerBinding implements Binding {
    private final String name;
    private final List<Parameter> parameters;
    private final boolean required;

    ListenerBinding(String name, List<Parameter> parameters, boolean required) {
        this.name = name;
        this.parameters = Collections.unmodifiableList(new ArrayList(parameters));
        this.required = required;
    }

    public String getName() {
        return this.name;
    }

    public List<Parameter> getParameters() {
        return this.parameters;
    }

    @Override // butterknife.internal.Binding
    public String getDescription() {
        return "method '" + this.name + "'";
    }

    public boolean isRequired() {
        return this.required;
    }
}
