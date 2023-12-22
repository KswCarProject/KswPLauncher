package android.arch.persistence.room;

/* loaded from: classes.dex */
public @interface Update {
    int onConflict() default 3;
}
