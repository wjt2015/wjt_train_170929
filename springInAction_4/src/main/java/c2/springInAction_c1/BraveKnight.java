package c2.springInAction_c1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/** implements Knight
 * @author:jintao.wang Date: 17-7-26 Time: 上午11:26
 */
@Component("braveKnight")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
@ToString
@Setter
@Getter
@NoArgsConstructor
public class BraveKnight{
    @Autowired
    @Qualifier(value = "rescueDamselQuest")
    private Quest quest;
    public BraveKnight(Quest quest){
        this.quest = quest;
    }
    public void embarkOnQuest(){
        log.info("wjt;BraveKnight;void embarkOnQuest()");
        quest.embark();
        log.info("wjt;BraveKnight;void embarkOnQuest()；quest.embark()");
    }
}


