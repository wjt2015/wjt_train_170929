/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

/**
 * @author jintao.wang  Date: 17-9-7 Time: 下午7:25
 */
@Inherited
@Target(value = { ElementType.TYPE})
public @interface InheritedAnnotation {
    String value() default "@interface InheritedAnnotation";
}

