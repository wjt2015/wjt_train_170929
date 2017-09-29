package c2.springInAction_c1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：jintao.wang Date: 17-8-16 Time: 下午9:14
 */
@Configuration
public class KnightConfiguration {
    @Bean
    BraveKnight braveKnightC(){
        return new BraveKnight();
    }
    @Bean
    RescueDamselQuest rescueDamselQuestC(){
        return new RescueDamselQuest();
    }
}
