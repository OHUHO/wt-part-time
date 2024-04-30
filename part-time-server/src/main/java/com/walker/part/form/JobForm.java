package com.walker.part.form;

import lombok.*;

import java.util.List;

/**
 * @description: JobForm
 * @author: Walker
 * @date: 2024-04-26 15:22:22
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobForm extends PageForm {

    /**
     * 状态：1待审核，2审核通过，3未通过
     */
    private List<Integer> status;

    /**
     * 商家ID
     */
    private String businessId;

    /**
     * 兼职种类ID
     */
    private String typeId;
}
