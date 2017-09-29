/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jintao.wang  Date: 17-9-26 Time: 下午9:37
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthModel {
    Integer id;
    String userName;
    String password;
    Byte role;

}
    