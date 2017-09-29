package javaweb.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * OndutyModel用于保存一个值班模块ondutyModule的值班列表的索引nextIdx和值班员工的qtalk列表ondutyList;
 *
 * @author jintao.wang Date: 17-7-24 Time: 上午11:13
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OndutyModel implements Serializable {
    private Integer id;

    private String ondutyModule;

    private Integer index;

    private List<List<String>> ondutyQtalkList;

    private List<String> ondutyNameList;

    private Integer stepSize;

    private Byte onduty;

}


