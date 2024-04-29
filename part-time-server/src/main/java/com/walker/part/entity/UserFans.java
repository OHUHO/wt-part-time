package com.walker.part.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Walker
 * @since 2024-04-29 10:05:36
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_fans")
public class UserFans implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户关注ID
     */
    @TableId(value = "fans_id", type = IdType.ASSIGN_ID)
    private String fansId;

    /**
     * 关注的用户ID
     */
    private String fromId;

    /**
     * 被关注的用户ID
     */
    private String toId;

    /**
     * 关注时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime createTime;
}
