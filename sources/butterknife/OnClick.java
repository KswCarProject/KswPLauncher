package butterknife;

import butterknife.internal.ListenerClass;
import butterknife.internal.ListenerMethod;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@ListenerClass(method = {@ListenerMethod(name = "onClick", parameters = {"android.view.View"})}, setter = "setOnClickListener", targetType = "android.view.View", type = "android.view.View.OnClickListener")
/* loaded from: classes.dex */
public @interface OnClick {
    int[] value();
}
