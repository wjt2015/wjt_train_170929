package myspring;

import c2.springInAction_c1.Knight;
import c2.springInAction_c2.Performer;
import c2.springInAction_c3.InstrumentConfiguration;
import concert_aop.Performance;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author：jintao.wang Date: 17-8-13 Time: 下午11:46
 */
@Configuration
@ComponentScan(basePackageClasses = {Knight.class, Performer.class, InstrumentConfiguration.class, Performance.class})
public class KnightConfig {
}




