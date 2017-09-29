/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jintao.wang  Date: 17-9-7 Time: 下午4:48
 */
@Target(value = { ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UserAnnotation {
    int id() default 0;
    String name() default "";
    int age() default 18;
    String gender() default "M";
}

