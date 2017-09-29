/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package annotation.annotationprocessor;

import annotation.wjt2.Apple;
import annotation.wjt2.FruitColor;
import annotation.wjt2.FruitName;
import annotation.wjt2.FruitProvider;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jintao.wang  Date: 17-9-12 Time: 下午6:32
 */
@Slf4j
public class FruitInfoProcessor {

    private static String fruitName = "水果名：";
    private static String fruitColor = "水果颜色：";
    private static String fruitProvider = "水果供应商";

    public static void printFruitInfo(Class<?> classObj){
        Preconditions.checkNotNull(classObj);

        FruitName fruitName = null;


        /*读取类的注解*/
        fruitName = classObj.getAnnotation(FruitName.class);

        /*读取service注解*/
        Service service = classObj.getAnnotation(Service.class);
        /*读取所有注解*/
        Annotation[] annotations = classObj.getAnnotations();

        /*读取方法的注解*/
        Method[] methods = classObj.getDeclaredMethods();
        for(Method method:methods){
            annotations = method.getDeclaredAnnotations();
        }

        Method method = null;
        try {
            method = classObj.getDeclaredMethod("getName");
            annotations = method.getDeclaredAnnotations();

            fruitName = method.getAnnotation(FruitName.class);

            String value = fruitName.value();
            System.out.println("value=" + value);

            Apple apple = new Apple();
            String name = (String) method.invoke(apple);
            if(Strings.isNullOrEmpty(name)){
                name = fruitName.value();
            }

        } catch (NoSuchMethodException e) {
            log.error("NoSuchMethodException happens!!",e);
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException happens!!",e);
        } catch (InvocationTargetException e) {
            log.error("InvocationTargetException happens!!",e);
        }

        Field[] fields = classObj.getDeclaredFields();
        for(Field field:fields){
            fruitName = field.getAnnotation(FruitName.class);
            if(fruitName != null){
                String fruitNameStr = fruitName.value();
                System.out.println("\t" + fruitName + fruitNameStr);
            }

            FruitColor fruitColor = field.getAnnotation(FruitColor.class);
            if(fruitColor != null){
                FruitColor.Color color = fruitColor.value();
                System.out.println("\t" + fruitColor + color);
            }

            FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
            if(fruitProvider != null){
                int id = fruitProvider.id();
                String name = fruitProvider.name();
                String address = fruitProvider.address();
                System.out.println("id = " + id + ";name = " + name + ";address = " + address);
            }
        }
    }
}
    