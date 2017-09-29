package concert_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author：jintao.wang Date: 17-8-17 Time: 下午9:40
 */
//@Component("audience")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Aspect
public class Audience {
/*    @Before("execution(* concert_aop.Performance.play(..))")
    public void silenceCellhpone(){
        System.out.println("class Audience;void silenceCellhpone()");
    }
    @Before("execution(* concert_aop.Performance.play(..))")
    public void takeSeat(){
        System.out.println("class Audience;void takeSeat()");
    }
    @AfterReturning("execution(* concert_aop.Performance.play(..))")
    public void applause(){
        System.out.println("class Audience;void applause()");
    }
    @AfterThrowing("execution(* concert_aop.Performance.play(..))")
    public void demandRefund(){
        System.out.println("class Audience;void demandRefund()");
    }*/
    @Around("execution(* concert_aop.Performance.play(..))")
    public void watchPerformance(ProceedingJoinPoint jp){
        System.out.println("+++++;void watchPerformance(ProceedingJoinPoint jp),start:");
        try {
            jp.proceed();
            jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("----;void watchPerformance(ProceedingJoinPoint jp),finish!!");
    }
}












