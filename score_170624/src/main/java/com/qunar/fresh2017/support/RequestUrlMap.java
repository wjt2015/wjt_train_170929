package com.qunar.fresh2017.support;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * @author lihuiminli
 * @create 2017-06-24 下午9:12
 */
public class RequestUrlMap {
    Multimap<String, RoleEnum> requestRoleMap = ArrayListMultimap.create();


    public RequestUrlMap() {
        requestRoleMap.put("/admin/pointDetail", RoleEnum.SYSTEM);
        requestRoleMap.put("/login", RoleEnum.GENERAL);
    }


}
