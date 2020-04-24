package butterknife.internal;

final class ViewBinding implements Binding {
    private final String name;
    private final boolean required;
    private final String type;

    ViewBinding(String name2, String type2, boolean required2) {
        this.name = name2;
        this.type = type2;
        this.required = required2;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return "field '" + this.name + "'";
    }

    public boolean isRequired() {
        return this.required;
    }
}
