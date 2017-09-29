package com.qunar.fresh2017.service.impl;

import com.qunar.fresh2017.dao.EmployeePointsDao;
import com.qunar.fresh2017.model.AdministratorModel;
import com.qunar.fresh2017.model.EmployeePointsModel;
import com.qunar.fresh2017.model.QueryPageResult;
import com.qunar.fresh2017.model.QueryPointDetailModel;
import com.qunar.fresh2017.model.QueryPointDetailResult;
import com.qunar.fresh2017.service.AdministratorService;
import com.qunar.fresh2017.service.PointsDetailService;
import com.qunar.qtown.model.RtxUser;
import com.qunar.qtown.model.condition.RtxUserCondition;
import com.qunar.qtown.service.RtxUserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lfz on 2017/6/23.
 */
@Service
@Slf4j
public class PointsDetailServiceImpl implements PointsDetailService {
    @Autowired
    private EmployeePointsDao dao;

//    @Autowired
    private RtxUserManager manager=null;

    @Autowired
    private AdministratorService userService;

    @Override
    public void deleteOneEmployeePoints(int id) {
        dao.deleteOneEmployeePoints(id);
    }

    @Override
    public void deleteMultiEmployeePoints(int[] ids) {
        if (ids != null && ids.length > 0) {
            dao.deleteMultiEmployeePoints(ids);
        }
    }

    @Override
    public QueryPageResult queryEmployeePoints(QueryPointDetailModel model) {
        Integer pageIndex = model.getPageIndex();
        Integer pageNum = model.getPageNum();
        if (pageIndex != null && pageIndex > 0 && pageNum != null && pageNum >= 0) {
            model.setPageIndex((pageIndex - 1) * pageNum);
            QueryPageResult result = new QueryPageResult();
            result.setTotalCount(dao.getQueryDetailCount(model));
            result.setContent(dao.queryEmployeePoints(model));
            return result;
        }
        return null;
    }

    @Override
    public int addEmployeePoints(EmployeePointsModel model) {
        dao.addEmployeePoints(model);
        return 0;
    }

    @Override
    public List<String> queryPointItemOrPromotionItem(){
        return dao.queryPointItemOrPromotionItem();
    }

    @Override
    public List<RtxUser> getMessageFromDubbo(RtxUserCondition condition) {
        try {
            return manager.queryRtxUserOne(condition);
        } catch (Exception e) {
            log.error("dubbo访问出错",e);
        }
        return null;
    }

    @Override
    @Transactional
    public void addPointsDetail(EmployeePointsModel model){
        String rtxId=model.getRtxId();
        AdministratorModel bean=userService.selectAdministratorByRtxId(rtxId);
        RtxUserCondition condition = new RtxUserCondition();
        condition.setRtxId(rtxId);
        List<RtxUser> rtxUsers = getMessageFromDubbo(condition);
        RtxUser rtxUser = rtxUsers.get(0);
        if (bean == null) {
            bean = new AdministratorModel();
            doUserInAddPointsDetail(bean,rtxUser);
            userService.insertAdministrator(bean);
        } else {
            doUserInAddPointsDetail(bean,rtxUser);
            userService.updateAdministratorById(bean);
        }
        model.setUserId(bean.getId());
        addEmployeePoints(model);
    }

    @Override
    public void updatePointsDetail(EmployeePointsModel model) {
        dao.updateEmployeePoints(model);
    }

    @Override
    public List<QueryPointDetailResult> downLoadQueryEmployeePoints(QueryPointDetailModel model) {
        return dao.downLoadQueryEmployeePoints(model);
    }

    private void doUserInAddPointsDetail(AdministratorModel model,RtxUser rtxUser){
        model.setRtxId(rtxUser.getRtxId());
        model.setCn(rtxUser.getCn());
        model.setSn(rtxUser.getSn());
        model.setJobCode(rtxUser.getJobCode());
        model.setLeader(rtxUser.getLeader());
        model.setHireType(rtxUser.getHireType());
        model.setDep1(rtxUser.getDep1());
        model.setDep2(rtxUser.getDep3());
        model.setDep3(rtxUser.getDep3());
        model.setDep4(rtxUser.getDep4());
        model.setDep5(rtxUser.getDep5());
        model.setRole(4);
    }
}
