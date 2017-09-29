package com.qunar.fresh2017.service.impl;

import com.qunar.fresh2017.dao.AdministratorDao;
import com.qunar.fresh2017.model.AdministratorModel;
import com.qunar.fresh2017.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linux2014 on 17-6-27.
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorDao administratorDao;
    @Override
    public int insertAdministrator(AdministratorModel administratorModel) {
        int iret = 0;
        if(administratorModel != null){
            iret = administratorDao.insertAdministrator(administratorModel);
        }
        return iret;
    }

    @Override
    public List<AdministratorModel> selectSysAndPlainAdministrators() {
        return administratorDao.selectSysAndPlainAdministrators();
    }

    @Override
    public List<AdministratorModel> selectPlainAdministrators() {
        return administratorDao.selectPlainAdministrators();
    }

    @Override
    public int deleteAdministratorById(Integer id) {
        int iret = 0;
        if(id != null){
            iret = administratorDao.deleteAdministratorById(id);
        }
        return iret;
    }

    @Override
    public int deleteOneAdministrator(AdministratorModel administratorModel) {
        int iret = 0;
        Integer id = null;
        if(administratorModel != null && (id = administratorModel.getId()) != null){
            iret = deleteAdministratorById(id);
        }
        return iret;
    }

    @Override
    public AdministratorModel selectAdministratorByRtxId(String rtxId) {
        AdministratorModel administratorModel = null;
        if(rtxId != null){
            administratorModel = administratorDao.selectAdministratorByRtxId(rtxId);
        }
        return administratorModel;
    }

    @Override
    public AdministratorModel selectAdministratorById(Integer id) {
        AdministratorModel administratorModel = null;
        if(id != null){
            administratorModel = administratorDao.selectAdministratorById(id);
        }
        return administratorModel;
    }

    @Override
    public int updateAdministratorById(AdministratorModel administratorModel) {
        int iret = 0;
        if(administratorModel != null && administratorModel.getId() != null){
            iret = administratorDao.updateAdministratorById(administratorModel);
        }
        return iret;
    }
}
