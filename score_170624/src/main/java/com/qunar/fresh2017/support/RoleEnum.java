package com.qunar.fresh2017.support;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lihuiminli
 * @create 2017-06-24 下午9:22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum RoleEnum {
    SYSTEM("系统管理员", 1), GENERAL("普通管理员", 2);
    private String description;
    private int administratorNumber;

    public static RoleEnum getRoleEnum(int administratorNumber){
        for(RoleEnum roleEnum : RoleEnum.values()){
            if(Objects.equal(administratorNumber,roleEnum.getAdministratorNumber())){
                return roleEnum;
            }
        }
        return null;
    }
}
