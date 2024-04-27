package com.walker.part.form;

import lombok.*;

/**
 * @description: UserLoginForm
 * @author: Walker
 * @date: 2024-04-22 19:38:38
 * @version: 1.0.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginForm {

    /**
     * 微信临时凭证
     */
    private String code;

    /**
     * 微信昵称
     */
    private String username;

    /**
     * 头像
     */
    private String portrait;
}
