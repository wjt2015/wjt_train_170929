//<start id="instrumentalist_java" />
package c2.springInAction_c3;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import c2.springInAction_c2.PerformanceException;
import c2.springInAction_c2.Performer;

import javax.annotation.Resource;

@Slf4j
@Setter
@Getter
@ToString
public class Instrumentalist implements Performer {
  public Instrumentalist(){
    log.info("wjt;class Instrumentalist implements Performer;Instrumentalist()");
  }
  public void perform() throws PerformanceException {
    getInstrument().play();
  }


  @Resource(name = "saxophone")
  private Instrument instrument;

}
//<end id="instrumentalist_java" />
