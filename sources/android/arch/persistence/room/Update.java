package android.arch.persistence.room;

public @interface Update {
    int onConflict() default 3;
}
