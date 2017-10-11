/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.model;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jintao.wang  Date: 17-10-10 Time: 下午5:53
 */

/**
 * CookieRecord用于记录用户退出系统时前端需要删除的cookie，这些cookie用name,value和doamin指定；
 */
@AllArgsConstructor
@ToString
public class CookieRecord {
    private List<CookieNameValue> cookieNameValueList;
    private List<String> domainList;

    public CookieRecord() {
        cookieNameValueList = new LinkedList<CookieNameValue>();
        domainList = new LinkedList<String>();
    }

    public void addCookieNameValue(CookieNameValue cookieNameValue){
        Preconditions.checkNotNull(cookieNameValue);

        cookieNameValueList.add(cookieNameValue);
    }

    public void addDomain(String domain){
        Preconditions.checkArgument(Strings.isNullOrEmpty(domain));

        domainList.add(domain);
    }
}
    