package c2.springInAction_c4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author：jintao.wang Date: 17-7-27 Time: 下午9:48
 */
@Configuration
public class MyConfigure {
    @Bean
    Instrument guitar(){
        return new Guitar();
    }
    @Bean
    Performer instrumentalist(){
        return new Instrumentalist();
    }
    @Bean
    Performer instrumentalist2(){
        return new Instrumentalist();
    }
    @Bean
    Audience audience(){
        return new Audience();
    }
    @Bean
    Audience audience2(){
        return new Audience();
    }
    @Bean
    Thinker thinker(){
        return new Volunteer();
    }
    @Bean
    MindReader magician(){
        return new Magician();
    }
}
