package c2.springInAction_c4;

/**
 * @author：jintao.wang Date: 17-7-31 Time: 下午3:44
 */
public class Magician implements MindReader {
    private String thoughts;
    public void interceptThoughts(String thoughts) {
        this.thoughts = thoughts;
        System.out.println("wjt;class Magician implements MindReader;oid interceptThoughts(String thoughts);thoughts=" + thoughts);
    }

    public String getThoughts() {
        return thoughts;
    }
}
