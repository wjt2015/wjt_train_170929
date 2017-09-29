package concert_aop;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author：jintao.wang Date: 17-8-17 Time: 下午9:39
 */
@Component("dramaPerformance")
public class DramaPerformance implements Performance {
    @Resource
    private MyData myData;

    public void play() {
        System.out.println("class DramaPerformance;void play();");
        System.out.println("class DramaPerformance;myData=" + myData);
        myData.setID(100);
        myData.setName("梁山伯与祝英台");
        myData.setName("梁山伯与祝英台");
    }
}


