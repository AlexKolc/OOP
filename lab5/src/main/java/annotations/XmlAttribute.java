package annotations;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlAttribute {
    String name() default "";

    String tag() default "";
}
