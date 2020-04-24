package butterknife.internal;

final class CollectionBinding implements Binding {
    private final Kind kind;
    private final String name;
    private final boolean required;
    private final String type;

    enum Kind {
        ARRAY,
        LIST
    }

    CollectionBinding(String name2, String type2, Kind kind2, boolean required2) {
        this.name = name2;
        this.type = type2;
        this.kind = kind2;
        this.required = required2;
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

    public String getDescription() {
        return "field '" + this.name + "'";
    }
}
