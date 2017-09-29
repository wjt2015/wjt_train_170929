package c2.springInAction_c3;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import c2.springInAction_c2.Performer;
import org.springframework.context.annotation.Scope;

/**
* @author：jintao.wang Date: 17-7-27 Time: 下午9:48
 */
@Configuration
public class InstrumentConfiguration {
   @Bean
    Guitar guitar(){
        return new Guitar();
    }
    @Bean
    Instrumentalist instrumentalist(){
        return new Instrumentalist();
    }
}


