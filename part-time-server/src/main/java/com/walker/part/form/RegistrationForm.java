package com.walker.part.form;

import lombok.*;

/**
 * @description: RegistrationForm
 * @author: Walker
 * @date: 2024-04-26 21:35:35
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm extends PageForm{

    /**
     * 兼职ID
     */
    private String jobId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 商家ID
     */
    private String businessId;
}
