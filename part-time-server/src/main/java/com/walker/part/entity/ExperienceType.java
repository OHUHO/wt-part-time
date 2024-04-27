package com.walker.part.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @since 2024-04-21 09:52:39
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("experience_type")
public class ExperienceType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 经验种类ID
     */
    @TableId(value = "type_id", type = IdType.ASSIGN_ID)
    private String typeId;

    /**
     * 种类名称
     */
    private String name;

    /**
     * 状态：1使用中，禁用
     */
    private Integer status;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    private Integer logicDelete;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime createTime;

    /**
     * 创建人Id
     */
    private String createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 最后一次时间
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
