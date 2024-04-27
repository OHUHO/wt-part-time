package com.walker.part.form;

import lombok.*;

/**
 * @description: JobTypeForm
 * @author: Walker
 * @date: 2024-04-22 08:35:35
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobTypeForm extends PageForm {

    /**
     * 是否需要排序
     */
    private boolean isOrder;
}
