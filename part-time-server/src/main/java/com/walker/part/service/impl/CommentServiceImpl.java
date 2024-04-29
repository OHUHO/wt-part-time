package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Comment;
import com.walker.part.entity.UserInfo;
import com.walker.part.form.CommentForm;
import com.walker.part.mapper.CommentMapper;
import com.walker.part.response.CommentResp;
import com.walker.part.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walker.part.service.IUserInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    private final IUserInfoService userInfoService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveForm(Comment comment) {
        if (StringUtils.isBlank(comment.getCommentId())){
            comment.setCommentTime(LocalDateTime.now());
            comment.setCreateTime(LocalDateTime.now());
        } else{
            comment.setUpdateTime(LocalDateTime.now());
        }
        return this.saveOrUpdate(comment);
    }

    @Override
    public Page<CommentResp> getPage(CommentForm form) {
        Page<Comment> page = new Page<>(form.getCurrent(), form.getSize());
        // 分页查询条数只包括父评论
        Page<Comment> commentPage = getBaseMapper().selectPage(page, new LambdaQueryWrapper<Comment>()
                .like(StringUtils.isNotBlank(form.getKeywords()), Comment::getContent, form.getKeywords())
                .eq(StringUtils.isNoneBlank(form.getExperienceId()),Comment::getExperienceId,form.getExperienceId())
                .eq(Comment::getToUserId,"")
        );
        Page<CommentResp> pageInfo = new Page<>();
        pageInfo.setTotal(commentPage.getTotal());

        List<Comment> list = commentPage.getRecords();
        if (CollectionUtils.isEmpty(list)){
            return pageInfo;
        }
        //new ArrayList<>()
        //for (Comment comment : list) {
        //
        //}
        return pageInfo;
    }

    /**
     * 不使用分页
     * @param form 查询表单
     * @return 集合
     */
    @Override
    public List<CommentResp> list(CommentForm form){
        // 所有评论
        List<Comment> comments = getBaseMapper().selectList(new LambdaQueryWrapper<Comment>()
                .like(StringUtils.isNotBlank(form.getKeywords()), Comment::getContent, form.getKeywords())
                .eq(StringUtils.isNoneBlank(form.getExperienceId()), Comment::getExperienceId, form.getExperienceId())
        );
        if (CollectionUtils.isEmpty(comments)){
            return null;
        }
        // 父评论
        List<Comment> parentComment = comments.stream()
                .filter(comment -> StringUtils.isBlank(comment.getParentId()))
                .collect(Collectors.toList());
        // 父评论封装
        List<CommentResp> parentCommentResp =  this.transComments(parentComment);
        // 递归查询子评论
        for (CommentResp commentResp : parentCommentResp) {
            // 子评论
            List<CommentResp> childComments = this.getChildComments(comments, commentResp.getCommentId());
            commentResp.setChildComment(childComments);
        }
        return parentCommentResp;
    }

    /**
     *
     * @return 评论列表
     */
    private List<CommentResp> getChildComments(List<Comment> comments,String parentId) {
        // 子评论
        List<Comment> childComment = comments.stream()
                .filter(child -> child.getParentId().equals(parentId))
                .collect(Collectors.toList());
        List<CommentResp> childCommentResp = this.transComments(childComment);
        for (CommentResp commentResp : childCommentResp) {
            List<CommentResp> childComments = this.getChildComments(comments, commentResp.getCommentId());
            commentResp.setChildComment(childComments);
        }
        return childCommentResp;
    }


    /**
     * 评论转换
     * @param comments 评论列表
     * @return 评论信息，包含用户头像和昵称
     */
    private List<CommentResp> transComments(List<Comment> comments) {
        List<CommentResp> parentCommentResp = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResp commentResp = new CommentResp();
            BeanUtils.copyProperties(comment,commentResp);
            // 用户信息封装
            UserInfo fromUser = userInfoService.getById(comment.getFromUserId());
            commentResp.setFromUserName(fromUser.getUsername());
            commentResp.setFromUserPortrait(fromUser.getPortrait());
            if (StringUtils.isNoneBlank(comment.getToUserId())){
                UserInfo toUser = userInfoService.getById(comment.getToUserId());
                commentResp.setToUserName(toUser.getUsername());
                commentResp.setToUserPortrait(toUser.getPortrait());
            }
            parentCommentResp.add(commentResp);
        }
        return parentCommentResp;
    }

}
