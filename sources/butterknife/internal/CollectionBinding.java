package butterknife.internal;

/* loaded from: classes.dex */
final class CollectionBinding implements Binding {
    private final Kind kind;
    private final String name;
    private final boolean required;
    private final String type;

    /* loaded from: classes.dex */
    enum Kind {
        ARRAY,
        LIST
    }

    CollectionBinding(String name, String type, Kind kind, boolean required) {
        this.name = name;
        this.type = type;
        this.kind = kind;
        this.required = required;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public Kind getKind() {
        return this.kind;
    }

    public boolean isRequired() {
        return this.required;
    }

    @Override // butterknife.internal.Binding
    public String getDescription() {
        return "field '" + this.name + "'";
    }
}
