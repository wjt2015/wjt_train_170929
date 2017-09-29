package c2.springInAction_c2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author：jintao.wang Date: 17-7-27 Time: 下午4:15
 */
@Component(value = "juggler")
@Slf4j
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Juggler implements Performer {
    private static int id = 0;
    private int beanBags;
    public Juggler(){
        id++;
        log.info("wjt;Juggler;public Juggler();id={}",id);
        beanBags = 3;
    }
    public void perform() throws PerformanceException {
        log.info("wjt;Juggler-void perform();");
    }
}
