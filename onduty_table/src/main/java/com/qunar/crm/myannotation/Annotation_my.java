package com.qunar.crm.myannotation;

import java.lang.annotation.*;

/**
 * Created by linux2014 on 17-7-20.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Annotation_my {
    String name() default "张三";//defalt 表示默认值

    String say() default "hello world";

    int age() default 21;
}
