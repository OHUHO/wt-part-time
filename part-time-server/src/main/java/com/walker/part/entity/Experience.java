package com.walker.part.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @since 2024-04-21 09:52:39
 */
@Getter
@Setter
@Accessors(chain = true)
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 经验id
     */
    @TableId(value = "experience_id", type = IdType.ASSIGN_ID)
    private String experienceId;

    /**
     * 经验名称
     */
    private String name;

    /**
     * 经验内容
     */
    private String content;

    /**
     * 经验类型
     */
    private String types;

    /**
     * 点赞数量
     */
    private Long good;

    /**
     * 状态：1待审核，2审核通过，3未通过
     */
    private Integer status;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    private Integer logicDelete;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 最后一次更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime updateTime;

    /**
     * 最后一次更新人ID
     */
    private String updateUserId;

    /**
     * 最后一次更新人姓名
     */
    private String updateUserName;
}
