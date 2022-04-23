package android.arch.persistence.room;

public @interface ForeignKey {
    public static final int CASCADE = 5;
    public static final int NO_ACTION = 1;
    public static final int RESTRICT = 2;
    public static final int SET_DEFAULT = 4;
    public static final int SET_NULL = 3;

    public @interface Action {
    }

    String[] childColumns();

    boolean deferred() default false;

    Class entity();

    int onDelete() default 1;

    int onUpdate() default 1;

    String[] parentColumns();
}
