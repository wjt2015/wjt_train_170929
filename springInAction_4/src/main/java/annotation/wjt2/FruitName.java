/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package annotation.wjt2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jintao.wang  Date: 17-9-12 Time: 下午6:16
 */
@Documented
@Inherited
@Target(value = { ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FruitName {
    String value() default "";
}

