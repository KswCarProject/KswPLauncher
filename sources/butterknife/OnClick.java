package butterknife;

import butterknife.internal.ListenerClass;
import butterknife.internal.ListenerMethod;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ListenerClass(method = {@ListenerMethod(name = "onClick", parameters = {"android.view.View"})}, setter = "setOnClickListener", targetType = "android.view.View", type = "android.view.View.OnClickListener")
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface OnClick {
    int[] value();
}
