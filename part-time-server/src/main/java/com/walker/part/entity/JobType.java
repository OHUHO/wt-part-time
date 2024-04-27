package com.walker.part.entity;

import com.baomidou.mybatisplus.annotation.*;

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
@TableName("job_type")
public class JobType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 兼职类型ID
     */
    @TableId(value = "type_id", type = IdType.ASSIGN_ID)
    private String typeId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 兼职类型描述
     */
    private String description;

    /**
     * 兼职封面
     */
    private String cover;

    /**
     * 等级，顺序越小越靠前
     */
    private Integer grade;

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
}
