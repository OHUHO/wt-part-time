package com.walker.part.form;

import lombok.*;

/**
 * @description: GoodForm
 * @author: Walker
 * @date: 2024-04-30 22:22:22
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodForm extends PageForm {

    /**
     * 经验点赞用户ID
     */
    private String userId;
}
