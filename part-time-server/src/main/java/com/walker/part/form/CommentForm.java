package com.walker.part.form;

import lombok.*;

/**
 * @description: CommentForm
 * @author: Walker
 * @date: 2024-04-29 14:13:13
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentForm extends PageForm{

    /**
     * 经验ID
     */
    private String experienceId;
}
