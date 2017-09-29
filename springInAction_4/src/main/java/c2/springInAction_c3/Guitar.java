package c2.springInAction_c3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

@Slf4j
@Qualifier("stringed")
public class Guitar implements Instrument {
  public Guitar(){
    System.out.println("wjt;class Guitar implements Instrument;public Guitar()");
    log.info("wjt;class Guitar implements Instrument;public Guitar()");
  }
  public void play() {
    System.out.println("wjt;class Guitar implements Instrument;void play();Strum strum strum");
  }
}


