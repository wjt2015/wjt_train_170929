/**
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package javaweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jintao.wang  Date: 17-9-30 Time: 下午9:33
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class LoginUserModel {
    private Integer id;

    private String userName;

    private String password;

    private Long loginTime;

    private Byte role;

    private Byte isLogin;
}
    