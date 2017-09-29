package c2.springInAction_c1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author jintao.wang Date: 17-7-26 Time: 下午8:15
 */
@Component("minstrel")
@Slf4j
public class Minstrel {
    public void singBeforeQuest(){
        log.info("Minstrel;la-la-la;The knight is so brave!");
    }
    public void singAfterQuest(){
        log.info("Minstrel;Tee hee he;The brave knight did embark on a quest!");
    }
}
