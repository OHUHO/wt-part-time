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
@TableName("experience_love")
public class ExperienceLove implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏ID
     */
    @TableId(value = "love_id", type = IdType.ASSIGN_ID)
    private String loveId;

    /**
     * 经验ID
     */
    private String experienceId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 收藏时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime createTime;
}
