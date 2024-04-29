package com.walker.part.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.form.CommentForm;
import com.walker.part.response.CommentResp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 保存或更新评论
     * @param comment 评论
     * @return 是否保存成功
     */
    boolean saveForm(Comment comment);

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    Page<CommentResp> getPage(CommentForm form);

    /**
     * 不分页查询
     * @param form 查询表单
     * @return 集合
     */
    List<CommentResp> list(CommentForm form);
}
