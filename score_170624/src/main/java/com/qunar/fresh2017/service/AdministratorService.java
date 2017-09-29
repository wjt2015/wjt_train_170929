package com.qunar.fresh2017.service;

import com.qunar.fresh2017.model.AdministratorModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by linux2014 on 17-6-24.
 */
public interface AdministratorService {
    int insertAdministrator(AdministratorModel administratorModel);
    List<AdministratorModel> selectSysAndPlainAdministrators();
    List<AdministratorModel> selectPlainAdministrators();
    int deleteAdministratorById(Integer id);
    int deleteOneAdministrator(AdministratorModel administratorModel);
    AdministratorModel selectAdministratorByRtxId(String rtxId);
    AdministratorModel selectAdministratorById(Integer id);
    int updateAdministratorById(AdministratorModel administratorModel);
}
