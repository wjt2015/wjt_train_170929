package javaweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * EmployeeListOnduty类表示值班的员工列表;
 * @author jintao.wang Date: 17-7-24 Time: 上午11:13
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
public class EmployeeListOnduty {
    /**
     *id,值班列表记录的数据表主键
     */
    private Integer id;
    /**
     * 值班负责的工作模块
     */
    private String ondutyModule;
    /**
     * 值班列表的qtalk列表，用一个字符串表示，相邻的qtalk之间用分好分割
     */
    private String qtalkList;
    /**
     * 当前值班列表内下一个值班人的索引
     */
    private Integer nextIdx;
    /**
     * stepSize，值班步长，也就是每天每个模块的值班人数；
     */
    private Integer stepSize;
    /**
     * 是否需要安排值班的开关;onduty==1,表示第二天需要安排值班;onduty==0,表示第二天不需要安排值班
     */
    private Byte onduty;

    public EmployeeListOnduty(){
        id = new Integer(0);
        ondutyModule = new String();
        qtalkList = new String();
        nextIdx = new Integer(0);
        stepSize = new Integer(1);
        onduty = new Byte((byte)1);
    }
}
