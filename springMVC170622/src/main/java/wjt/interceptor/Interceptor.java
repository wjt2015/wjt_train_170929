package wjt.interceptor;

/**
 * @author：jintao.wang Date: 17-7-27 Time: 下午7:04
 */
public interface Interceptor {
    void performBefore();
    void performAfter();
}
