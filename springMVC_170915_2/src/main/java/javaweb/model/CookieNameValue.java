/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.model;

/**
 * @author jintao.wang  Date: 17-10-10 Time: 下午5:59
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CookieNameValue用于记录cookie的name和value;
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CookieNameValue {
    private String name;
    private String value;
}
    