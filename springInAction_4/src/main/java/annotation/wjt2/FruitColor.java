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
 * @author jintao.wang  Date: 17-9-12 Time: 下午6:19
 */
@Documented
@Inherited
@Target(value = { ElementType.FIELD,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FruitColor {
    public enum Color{RED,GREEN,BLUE};

    Color value() default Color.BLUE;
}

