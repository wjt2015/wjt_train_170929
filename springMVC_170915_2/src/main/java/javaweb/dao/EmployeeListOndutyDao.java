package javaweb.dao;


import javaweb.model.EmployeeListOnduty;
import javaweb.model.OndutyModule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jintao.wang Date: 17-7-24 Time: 上午11:13
 */
@Repository(value = "employeeListOndutyDao")
public interface EmployeeListOndutyDao {
    /**
     * 向数据库内插入员工列表信息;
     *
     * @param ondutyModule
     * @param qtalkList
     * @param nextIdx
     * @param stepSize
     * @param onduty
     * @return 成功，返回正数；失败，返回负数
     */
    int insertEmployeeListOnduty(@Param("ondutyModule") String ondutyModule, @Param("qtalkList") String qtalkList,
            @Param("nextIdx") Integer nextIdx, @Param("stepSize") Integer stepSize, @Param("onduty") Byte onduty);

    /**
     * 向数据库内插入员工列表信息;
     *
     * @param id，值班工作模块id
     * @return 成功，返回正数；失败，返回负数
     */
    int deleteEmployeeListOndutyById(@Param("id") Integer id);

    /**
     * 获取所有值班工作模块的值班序列列表;
     *
     * @return 所有值班工作模块的值班序列列表
     */
    List<EmployeeListOnduty> selectEmployeeListOnduty(@Param("offset") Integer offset, @Param("rows") Integer rows);

    /**
     * 获取指定值班工作模块的值班序列列表;
     *
     * @param id，值班工作模块的id
     * @return 指定值班工作模块的值班信息
     */
    EmployeeListOnduty selectEmployeeListOndutyById(@Param("id") Integer id);

    /**
     * 更新值班序列列表;
     *
     * @param employeeListOnduty 新的值班序列列表
     * @return 成功，返回正数；失败，返回负数
     */
    int updateEmployeeListOnduty(EmployeeListOnduty employeeListOnduty);

    /**
     * 查询所有的值班模块
     *
     * @param offset
     * @param rows
     * @return
     */
    List<OndutyModule> selectAllOndutyModules(@Param("offset") Integer offset, @Param("rows") Integer rows);

}


