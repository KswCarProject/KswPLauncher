package butterknife.internal;

/* loaded from: classes.dex */
final class ViewBinding implements Binding {
    private final String name;
    private final boolean required;
    private final String type;

    ViewBinding(String name, String type, boolean required) {
        this.name = name;
        this.type = type;
        this.required = required;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    @Override // butterknife.internal.Binding
    public String getDescription() {
        return "field '" + this.name + "'";
    }

    public boolean isRequired() {
        return this.required;
    }
}
