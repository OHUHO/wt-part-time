package com.walker.part.response;

import com.walker.part.entity.UserFans;
import lombok.*;

/**
 * @description: UserFansResp
 * @author: Walker
 * @date: 2024-04-29 09:48:48
 * @version: 1.0.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserFansResp extends UserFans {

    /**
     * 关注者ID，同fromId
     */
    private String fromUserId;

    /**
     * 关注者名称
     */
    private String fromUsername;

    /**
     * 关注者头像
     */
    private String fromPortrait;

    /**
     * 被关注者ID，同toId
     */
    private String toUserId;

    /**
     * 被关注者名称
     */
    private String toUsername;

    /**
     * 被关注者头像
     */
    private String toPortrait;
}
