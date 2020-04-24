package butterknife.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class ListenerBinding implements Binding {
    private final String name;
    private final List<Parameter> parameters;
    private final boolean required;

    ListenerBinding(String name2, List<Parameter> parameters2, boolean required2) {
        this.name = name2;
        this.parameters = Collections.unmodifiableList(new ArrayList(parameters2));
        this.required = required2;
    }

    public String getName() {
        return this.name;
    }

    public List<Parameter> getParameters() {
        return this.parameters;
    }

    public String getDescription() {
        return "method '" + this.name + "'";
    }

    public boolean isRequired() {
        return this.required;
    }
}
