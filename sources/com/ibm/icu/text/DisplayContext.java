package com.ibm.icu.text;

/* loaded from: classes.dex */
public enum DisplayContext {
    STANDARD_NAMES(Type.DIALECT_HANDLING, 0),
    DIALECT_NAMES(Type.DIALECT_HANDLING, 1),
    CAPITALIZATION_NONE(Type.CAPITALIZATION, 0),
    CAPITALIZATION_FOR_MIDDLE_OF_SENTENCE(Type.CAPITALIZATION, 1),
    CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE(Type.CAPITALIZATION, 2),
    CAPITALIZATION_FOR_UI_LIST_OR_MENU(Type.CAPITALIZATION, 3),
    CAPITALIZATION_FOR_STANDALONE(Type.CAPITALIZATION, 4),
    LENGTH_FULL(Type.DISPLAY_LENGTH, 0),
    LENGTH_SHORT(Type.DISPLAY_LENGTH, 1),
    SUBSTITUTE(Type.SUBSTITUTE_HANDLING, 0),
    NO_SUBSTITUTE(Type.SUBSTITUTE_HANDLING, 1);
    
    private final Type type;
    private final int value;

    /* loaded from: classes.dex */
    public enum Type {
        DIALECT_HANDLING,
        CAPITALIZATION,
        DISPLAY_LENGTH,
        SUBSTITUTE_HANDLING
    }

    DisplayContext(Type type, int value) {
        this.type = type;
        this.value = value;
    }

    public Type type() {
        return this.type;
    }

    public int value() {
        return this.value;
    }
}
