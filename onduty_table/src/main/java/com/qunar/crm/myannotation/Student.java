package com.qunar.crm.myannotation;

/**
 * Created by linux2014 on 17-7-20.
 */
@Annotation_my
@SuppressWarnings("unused")
public class Student implements Person {
    private String name;

//    @Override
    @Annotation_my(name="流氓公子") //赋值给name  默认的为张三
//在定义注解时没有给定默认值时，在此处必须name赋初值
    public void name() {

    }


//    @Override
    @Annotation_my(say=" hello world  ！")
    public void say() {

    }

//    @Override
    @Annotation_my(age=20)
    public void age() {

    }
}
