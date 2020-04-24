package butterknife.internal;

final class Parameter {
    static final Parameter[] NONE = new Parameter[0];
    private final int listenerPosition;
    private final String type;

    Parameter(int listenerPosition2, String type2) {
        this.listenerPosition = listenerPosition2;
        this.type = type2;
    }

    /* access modifiers changed from: package-private */
    public int getListenerPosition() {
        return this.listenerPosition;
    }

    /* access modifiers changed from: package-private */
    public String getType() {
        return this.type;
    }
}
