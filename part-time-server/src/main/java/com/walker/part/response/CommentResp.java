package com.walker.part.response;

import com.walker.part.entity.Comment;
import lombok.*;

import java.util.List;

/**
 * @description: CommentResp
 * @author: Walker
 * @date: 2024-04-29 14:10:10
 * @version: 1.0.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentResp extends Comment {

    /**
     * 评论人头像
     */
    private String fromUserPortrait;

    /**
     * 被评论人头像
     */
    private String toUserPortrait;

    /**
     * 子评论信息
     */
    private List<CommentResp> childComment;
}
