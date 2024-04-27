package com.walker.part.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @description: 分页查询表单
 * @author: Walker
 * @date: 2024-03-8 20:50:16
 * @version: 1.2.4
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageForm {

    /**
     * 当前页索引
     */
    private Integer current;

    /**
     * 每页的条数
     */
    private Integer size;

    /**
     * 关键词
     */
    private String keywords;
}
