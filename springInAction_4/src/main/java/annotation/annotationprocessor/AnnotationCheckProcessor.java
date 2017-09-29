/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package annotation.annotationprocessor;

import annotation.wjt2.FruitName;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jintao.wang  Date: 17-9-13 Time: 下午4:20
 */
@Slf4j
public class AnnotationCheckProcessor {
    public static Set<Method> findAllMethodsAnnotatedBySpecificAnnotation(Class<?> classObj,Class<? extends Annotation> annotation){
        Preconditions.checkNotNull(classObj);
        Preconditions.checkNotNull(annotation);

        Set<Method> methodSet = new HashSet<Method>();
        for(Method method:classObj.getDeclaredMethods()){
            if(method.getAnnotation(annotation) != null){
                methodSet.add(method);
            }
        }
        return methodSet;
    }
    public static Set<Method> findAllMethodsAnnotatedBySpecificAnnotationByClass(Set<Class<?>> classObjSet,Class<? extends Annotation> annotation){
        Preconditions.checkNotNull(classObjSet);
        Preconditions.checkNotNull(annotation);
        Set<Method> methodSet = new HashSet<Method>();
        for(Class<?> classObj:classObjSet){
            methodSet.addAll(findAllMethodsAnnotatedBySpecificAnnotation(classObj,annotation));
        }
        return methodSet;
    }

    public static Set<Method> findAllMethodsAnnotatedBySpecificAnnotationByClassNames(Set<String> classNameSet,Class<? extends Annotation> annotation){
        Preconditions.checkNotNull(classNameSet);
        Preconditions.checkNotNull(annotation);
        Set<Method> methodSet = new HashSet<Method>();

        for(String className:classNameSet){
            try {
                methodSet.addAll(findAllMethodsAnnotatedBySpecificAnnotation(Class.forName(className),annotation));
            } catch (ClassNotFoundException e) {
                log.error("ClassNotFoundException happens!!",e);
            }
        }

        return methodSet;
    }

}
    