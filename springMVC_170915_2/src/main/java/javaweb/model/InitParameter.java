/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jintao.wang Date: 17-10-13 Time: 上午11:31
 */

/**
 * 用于存储web.xml文件内的初始化参数
 */
@Component
@AllArgsConstructor
@Getter
@Setter
public class InitParameter {
    private List<String> domainList;

    public InitParameter(){
        domainList = new LinkedList<String>();
    }

    public void copyDomainList(List<String> domainListParam){
        if(domainListParam != null){
            domainList.addAll(domainListParam);
        }
    }

    public void setDomainList(List<String> domainList) {
        this.domainList = domainList;
    }
}


