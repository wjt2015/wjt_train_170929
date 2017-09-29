package concert_aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import javax.annotation.Resource;

/**
 * @author：jintao.wang Date: 17-8-31 Time: 下午8:42
 */
@Aspect
@Slf4j
public class YoungAudience {
    @Resource
    private MyData myData;

    @Pointcut(value = "execution(* concert_aop.Performance.play())")
    public void perform() {
    }

    @Before(value = "perform()")
    public void takeSeats() {
        System.out.println("YoungAudience--void takeSeats();myData=" + myData);
        myData.setID(myData.getID()+1);
    }

    @Before(value = "perform()")
    public void turnOffCellphones() {
        System.out.println("YoungAudience--void turnOffCellphones();myData=" + myData);
        myData.setName("敢问路在何方");
    }

    @AfterReturning(value = "perform()")
    public void applaud() {
        System.out.println("YoungAudience--void applaud();myData=" + myData);
    }

    @Around(value = "perform()")
    public void watchPerformance(ProceedingJoinPoint jp){
        System.out.println(" start;void watchPerformance();jp=" + jp);
        try {
            jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(" finish!!void watchPerformance();jp=" + jp);
    }
}

