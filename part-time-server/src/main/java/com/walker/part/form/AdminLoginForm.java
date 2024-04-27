package com.walker.part.form;

import lombok.*;

/**
 * @description: AdminLoginForm
 * @author: Walker
 * @date: 2024-04-21 15:38:38
 * @version: 1.0.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminLoginForm {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
