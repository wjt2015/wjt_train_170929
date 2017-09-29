package c2.springInAction_c4;

import lombok.extern.slf4j.Slf4j;

/**
 * @author：jintao.wang Date: 17-7-28 Time: 下午5:17
 */
@Slf4j
public class Audience {
    public void takeSeats(){
        String str = "wjt;Audience;void takeSeats()";
        log.info(str);
        System.out.println(str);
    }
    public void turnOffCellPhones(){
        String str = "wjt;Audience;void turnOffCellphones()";
        log.info(str);
        System.out.println(str);
    }
    public void keepSilent(){
        String str = "wjt;class Audience;void keepSilent()";
        log.info(str);
        System.out.println(str);
    }
    public void applaud(){
        String str = "wjt;Audience;void applaud()";
        log.info(str);
        System.out.println(str);
    }
    public void demandRefund(){
        String str = "wjt;Audience;void demandRefund()";
        log.info(str);
        System.out.println(str);
    }
}


