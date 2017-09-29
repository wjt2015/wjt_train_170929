package com.qunar.fresh2017.dao;

import com.qunar.fresh2017.model.AdministratorModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by linux2014 on 17-6-24.
 */
@Repository
public interface AdministratorDao {
    int insertAdministrator(AdministratorModel administratorModel);
    List<AdministratorModel> selectSysAndPlainAdministrators();
    List<AdministratorModel> selectPlainAdministrators();
    int deleteAdministratorById(@Param("id")Integer id);
    AdministratorModel selectAdministratorByRtxId(@Param("rtxId")String rtxId);
    AdministratorModel selectAdministratorById(@Param("id")Integer id);
    int updateAdministratorById(AdministratorModel administratorModel);
}
