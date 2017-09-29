//<start id="instrumentalist_java" />
package c2.springInAction_c4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Slf4j
public class Instrumentalist implements Performer {
  public Instrumentalist(){

    log.info("wjt;class Instrumentalist implements Performer;Instrumentalist()");
  }
  public void perform() throws PerformanceException, InterruptedException {
    log.info("wjt;Instrumentalist;void perform();start:");
    System.out.println("wjt;Instrumentalist;void perform();start:");;
    Instrument instrument = getInstrument();
    instrument.play();
    Thread.sleep(2000);
    log.info("wjt;Instrumentalist;void perform();finish!!");
    System.out.println("wjt;Instrumentalist;void perform();finish!!");
  }

  public Instrument getInstrument() {
    return instrument;
  }

  //<start id="autowire_property" />
  @Autowired
  @Qualifier("stringed")
  private Instrument instrument;
  //<end id="autowire_property" />

}
//<end id="instrumentalist_java" />
