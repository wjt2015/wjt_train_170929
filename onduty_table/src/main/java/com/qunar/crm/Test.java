package com.qunar.crm;

import com.qunar.crm.myannotation.Annotation_my;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by linux2014 on 17-7-20.
 */
public class Test {
    Annotation[] annotation = null;

    public static void main(String[] args) throws ClassNotFoundException {
        new Test().getAnnotation();
    }

    public void getAnnotation() throws ClassNotFoundException {
        Class<?> stu = Class.forName("com.qunar.crm.myannotation.Student");//静态加载类
        boolean isEmpty = stu.isAnnotationPresent(Annotation_my.class);//判断stu是不是使用了我们刚才定义的注解接口if(isEmpty){
        annotation = stu.getAnnotations();//获取注解接口中的
        for (Annotation a : annotation) {
            Annotation_my my = (Annotation_my) a;//强制转换成Annotation_my类型
            System.out.println(stu + ":\n" + my.name() + " say: " + my.say() + " my age: " + my.age());
        }
//    }
        Method[] method = stu.getMethods();//
        System.out.println("Method:");
        for (Method m : method) {
            boolean ismEmpty = m.isAnnotationPresent(Annotation_my.class);
            if (ismEmpty) {
                Annotation[] aa = m.getAnnotations();
                for (Annotation a : aa) {
                    Annotation_my an = (Annotation_my) a;
                    System.out.println(m + ":\n" + an.name() + " say: " + an.say() + " my age: " + an.age());
                }
            }
        }
        //get Fields by force
        System.out.println("get Fileds by force!");
        Field[] field = stu.getDeclaredFields();
        for (Field f : field) {
            f.setAccessible(true);
            System.out.println(f.getName());
        }
        System.out.println("get methods in interfaces !");
        Class<?> interfaces[] = stu.getInterfaces();
        for (Class<?> c : interfaces) {
            Method[] imethod = c.getMethods();
            for (Method m : imethod) {
                System.out.println(m.getName());
            }
        }
    }

}
