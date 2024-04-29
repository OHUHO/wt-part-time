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
@TableName("experience_good")
public class ExperienceGood implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞ID
     */
    @TableId(value = "good_id", type = IdType.ASSIGN_ID)
    private String goodId;

    /**
     * 经验ID
     */
    private String experienceId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 点赞时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime createTime;
}
