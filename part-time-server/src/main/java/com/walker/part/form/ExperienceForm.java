package com.walker.part.form;

import lombok.*;

/**
 * @description: ExperienceForm
 * @author: Walker
 * @date: 2024-05-02 10:04:04
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceForm extends PageForm {

    /**
     * 用户名
     */
    private String userId;
}
