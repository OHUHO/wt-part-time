package com.walker.part.form;

import lombok.*;

import java.util.List;

/**
 * @description: BusinessForm
 * @author: Walker
 * @date: 2024-04-25 17:22:22
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BusinessForm extends PageForm {

    /**
     * 状态：1待审核，2审核通过，3未通过
     */
    private List<Integer> status;
}
