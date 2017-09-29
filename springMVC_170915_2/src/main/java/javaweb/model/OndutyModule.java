package javaweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author：jintao.wang Date: 17-8-21 Time: 下午8:27
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OndutyModule {
    private Integer id;
    private String ondutyModule;
    private Byte onduty;
}
