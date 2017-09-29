package c2.springInAction_c1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author jintao.wang Date: 17-7-26 Time: 上午11:36
 */
@Component("rescueDamselQuest")
@Slf4j
@ToString
@Setter
@Getter
@NoArgsConstructor
public class RescueDamselQuest implements Quest {
    public void embark() {
        log.info("wjt;RescueDamselQuest;void embark()；");
    }
}


