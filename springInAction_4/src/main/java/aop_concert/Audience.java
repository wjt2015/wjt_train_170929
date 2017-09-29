package aop_concert;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author：jintao.wang Date: 17-8-28 Time: 上午10:54
 */
@Aspect
public class Audience {
    @Before("execution(* aop_concert.Performance.perform(..))")
    public void silenceCellphones(){
        System.out.println("void silenceCellphones()");
    }
    @Before("execution(* aop_concert.Performance.perform(..))")
    public void takeSeats(){
        System.out.println("void takeSeats()");
    }
    @AfterReturning("execution(* aop_concert.Performance.perform(..))")
    public void applause(){
        System.out.println("void applause()");
    }
    @AfterThrowing("execution(* aop_concert.Performance.perform(..))")
    public void demandRefund(){
        System.out.println("void demandRefund()");
    }
}
