package com.qunar.fresh2017.support;

import com.qunar.qtown.model.RtxUser;
import com.qunar.qtown.model.condition.RtxUserCondition;
import com.qunar.qtown.service.RtxUserManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by lfz on 2017/6/24.
 */
public class QtownUtil {
    @Autowired
    private RtxUserManager manager;

    public List<RtxUser> getRtxUser(RtxUserCondition condition) throws Exception {
        return manager.queryRtxUserOne(condition);
    }
}
