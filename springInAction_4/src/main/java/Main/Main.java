package Main;

import annotation.AnnotationCase;
import annotation.AnnotationCaseSubClass;
import annotation.InheritedAnnotation;
import annotation.UserAnnotation;
import annotation.annotationprocessor.AnnotationCheckProcessor;
import annotation.annotationprocessor.FruitInfoProcessor;
import annotation.wjt.IReqApi;
import annotation.wjt2.Apple;
import annotation.wjt2.Banana;
import annotation.wjt2.FruitName;
import c2.springInAction_c1.BraveKnight;
import c2.springInAction_c1.Knight;
import c2.springInAction_c1.RescueDamselQuest;
import c2.springInAction_c2.Juggler;
import c2.springInAction_c2.PerformanceException;
import c2.springInAction_c2.Performer;
import c2.springInAction_c3.Guitar;
import c2.springInAction_c3.Instrumentalist;
import c2.springInAction_c3.Saxophone;
import com.google.common.base.Objects;
import concert_aop.DramaPerformance;
import concert_aop.Performance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author：jintao.wang Date: 17-8-13 Time: 下午11:44
 */
@Slf4j
public class Main {
    public static void main(String[] args){
//        testKnight();
//            testAOP();
//        testYoungAudience();
//        testUserAnnotation();
//        testAnnotation();
//        fruitInfoAnnotation();
//        testNullable();
        testAnnotationCheckProcessor();
    }

    private static void testKnight(){
        String xmlPath = "knight_spring.xml";
//        String xmlPath = "c2/c2_spring.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(xmlPath);
        BraveKnight knight = ctx.getBean("braveKnight", BraveKnight.class);
        knight.embarkOnQuest();
        System.out.println("knight=" + knight);
        knight.embarkOnQuest();
        Juggler performer = ctx.getBean("juggler", Juggler.class);
        System.out.println("performer=" + performer);
        try {
            performer.perform();
        } catch (PerformanceException e) {
            log.error("PerformanceException happens！",e);
        }

        BraveKnight braveKnightC = ctx.getBean("braveKnightC",BraveKnight.class);
        braveKnightC.embarkOnQuest();

        System.out.println("braveKnightC=" + braveKnightC);

        RescueDamselQuest rescueDamselQuestC = ctx.getBean("rescueDamselQuestC",RescueDamselQuest.class);
        rescueDamselQuestC.embark();

        System.out.println("\trescueDamselQuestC=" + rescueDamselQuestC);

        int i = 0,n = 5;
        BraveKnight braveKnight = null;
        System.out.println("+***********+");
        for (i = 0;i < n;i++){
            braveKnight = ctx.getBean("braveKnight",BraveKnight.class);
            System.out.println("i=" + i + ";braveKnight=" + braveKnight);
        }
        System.out.println("++++++++++");

        Guitar guitar = ctx.getBean("guitar",Guitar.class);
        guitar.play();
        System.out.println("guitart=" + guitar);

        Instrumentalist instrumentalist = ctx.getBean("instrumentalist",Instrumentalist.class);
        try {
            instrumentalist.perform();
        } catch (PerformanceException e) {
            log.error("PerformanceException happens！",e);
        }
        System.out.println("instrumentalist=" + instrumentalist);

        Saxophone saxophone = ctx.getBean("saxophone",Saxophone.class);
        System.out.println("saxophone=" + saxophone);
        System.out.println("%%%%%%%%%%%%");

    }

    private static void testAOP(){
        String xmlPath = "knight_spring.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(xmlPath);
//        DramaPerformance dramaPerformance = ctx.getBean("dramaPerformance",DramaPerformance.class);
        System.out.println("------start-----");
//        dramaPerformance.play();
        System.out.println("------finish!!----");
        Performance performance = ctx.getBean("dramaPerformance",Performance.class);
        performance.play();
        System.out.println("------Performance performance;finish!!----");
    }

    private static void testYoungAudience(){
        String xmlPath = "concert_aop/audience_aop.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(xmlPath);
        Performance performance = ctx.getBean("dramaPerformance",Performance.class);
        System.out.println("start+++++++++++");
        performance.play();
        System.out.println("finish+++++++++++");
    }

    private static void testUserAnnotation(){
        Class annotationCaseClass = AnnotationCase.class;
        Field field = null;
        try {
            field = annotationCaseClass.getDeclaredField("vi");
        } catch (NoSuchFieldException e) {
            log.error("void testUserAnnotation();NoSuchFieldException occurs!!",e);
        }
        System.out.println("annotationCaseClass = " + annotationCaseClass);
        System.out.println("field = " + field);
        UserAnnotation userAnnotation = field.getAnnotation(UserAnnotation.class);
        System.out.println("userAnnotation = " + userAnnotation);
        System.out.println("id = " + userAnnotation.id() + ";name = " + userAnnotation.name() + ";");

        Class annotationCaseSubClass = AnnotationCaseSubClass.class;
        System.out.println("annotationCaseSubClass = " + annotationCaseSubClass);
        try {
            field = annotationCaseSubClass.getDeclaredField("name");
        } catch (NoSuchFieldException e) {
            log.error("void testUserAnnotation();NoSuchFieldException occurs!!",e);
        }
        System.out.println("field = " + field);
        InheritedAnnotation inheritedAnnotation = field.getAnnotation(InheritedAnnotation.class);
        System.out.println("inheritedAnnotation = " + inheritedAnnotation);
        userAnnotation = field.getAnnotation(UserAnnotation.class);
        System.out.println("userAnnotation = " + userAnnotation);
        System.out.println("-++++++++==!!");
        Annotation[] annotations = field.getDeclaredAnnotations();
        System.out.println("annotations = " + annotations);
        for (Annotation annotation:annotations){
            System.out.println(annotation);
            System.out.println("---annotation");
        }
        System.out.println("-------------finish!!");
    }
    /**
     * 获取方法、参数的注解
     */
    private static void testAnnotation(){
        Class ireqClassObj = IReqApi.class;
        Method method = null;
        try {
            method = ireqClassObj.getDeclaredMethod("login",String.class,String.class);
        } catch (NoSuchMethodException e) {
            log.error("void testAnnotation();NoSuchMethodException occurs!!",e);
        }
        /**
         * 获取方法的所有注解
         */
        System.out.println("获取方法的所有注解,start:");
        for (Annotation annotation:method.getDeclaredAnnotations()){
            System.out.println("annotation = " + annotation);
        }
        System.out.println("获取方法的所有注解,finish!!");

        System.out.println("获取方法参数的所有注解,start:");
        for (Annotation[] annotations:method.getParameterAnnotations()){
            for (Annotation annotation:annotations){
                System.out.println("annotation = " + annotation);
            }
        }
        System.out.println("获取方法参数的所有注解,finish!!");

        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        Integer a = null;
        JTextArea jTextArea = new JTextArea();
        jTextArea.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }
        });

    }
    private static void fruitInfoAnnotation(){
        FruitInfoProcessor.printFruitInfo(Apple.class);
    }

    private static void testNullable(){
        boolean b = Objects.equal(null,null);
        System.out.println("b=" + b);
    }

    private static void testAnnotationCheckProcessor(){

        Set<Class<?>> classObjSet = new HashSet<Class<?>>();
        classObjSet.add(Apple.class);
        classObjSet.add(Banana.class);
        Set<Method> methodSet = AnnotationCheckProcessor.findAllMethodsAnnotatedBySpecificAnnotationByClass(classObjSet,
                FruitName.class);
        System.out.println("methodSet=" + methodSet);

        methodSet.clear();
        String appleClassName = "annotation.wjt2.Apple";
        String bananaClassName = "annotation.wjt2.Banana";
        Set<String> classNameSet = new HashSet<String>();
        classNameSet.add(appleClassName);
        classNameSet.add(bananaClassName);
        methodSet = AnnotationCheckProcessor.findAllMethodsAnnotatedBySpecificAnnotationByClassNames(classNameSet,FruitName.class);

        System.out.println("methodSet=" + methodSet);
    }

    private static void testAnnotation2(){
        Annotation annotation = null;
        AnnotatedElement annotatedElement = null;
    }
}













