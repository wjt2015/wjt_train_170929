package c2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author：jintao.wang Date: 17-8-9 Time: 下午9:26
 */
@Component("sgtPeppers")
@Setter
@Getter
@AllArgsConstructor
@ToString
public class SgtPeppers implements CompactDisc {
    private String title;
    private String artist;

    public SgtPeppers() {
        this.title = "class SgtPeppers;Lonely Hearts Club Band!!";
        this.artist = "class SgtPeppers;The beatles!!";
    }

    public void play() {
        System.out.println("class SgtPeppers;Playing " + title + " by " + artist);
    }
}
