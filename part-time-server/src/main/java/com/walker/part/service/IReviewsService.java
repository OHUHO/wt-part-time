package com.walker.part.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Reviews;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.form.PageForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
public interface IReviewsService extends IService<Reviews> {

    /**
     * 分页查询
     * @param form 表单
     * @return 分页结果
     */
    Page<Reviews> getPage(PageForm form);

    /**
     * 保存或更新
     * @param form 表单
     * @return 是否保存成功
     */
    boolean saveForm(Reviews form);

    /**
     * 通过兼职ID，评论人ID，被评论人ID查询评论信息
     * @param jobId 兼职ID
     * @param fromId 评论人ID
     * @param toId 被评论人ID
     * @return 评价信息
     */
    Reviews getByMultiId(String jobId, String fromId, String toId);
}
