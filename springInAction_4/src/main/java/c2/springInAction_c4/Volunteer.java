package c2.springInAction_c4;

/**
 * @author：jintao.wang Date: 17-7-31 Time: 下午3:46
 */
public class Volunteer implements Thinker {
    private String thoughts;
    public void thinkOfSomething(String thoughts) {
        this.thoughts = thoughts;
        System.out.println("wjt;class Volunteer implements Thinker;void thinkOfSomething(String thoughts);thoughts=" + thoughts);
    }
    public String getThoughts() {
        return thoughts;
    }
}




