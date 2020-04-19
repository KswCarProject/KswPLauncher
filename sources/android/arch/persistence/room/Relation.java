package android.arch.persistence.room;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface Relation {
    Class entity() default Object.class;

    String entityColumn();

    String parentColumn();

    String[] projection() default {};
}
