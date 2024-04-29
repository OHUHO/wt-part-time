package com.walker.part.form;

import lombok.*;

/**
 * @description: FansForm
 * @author: Walker
 * @date: 2024-04-29 09:52:52
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FansForm extends PageForm{

    /**
     * 关注者ID
     */
    private String fromId;

    /**
     * 被关注者ID
     */
    private String toId;
}
