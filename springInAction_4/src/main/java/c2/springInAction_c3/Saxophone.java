// <start id="piano_java" />
package c2.springInAction_c3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "saxophone")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Saxophone implements Instrument {
  public Saxophone() {
    System.out.println("wjt;class Saxophone implements Instrument;public Saxophone()");;
    log.info("wjt;class Saxophone implements Instrument;public Saxophone()");
  }

  public void play() {
    System.out.println("wjt;class Saxophone implements Instrument;void play();PLINK PLINK PLINK");
  }
}
// <end id="piano_java" />
