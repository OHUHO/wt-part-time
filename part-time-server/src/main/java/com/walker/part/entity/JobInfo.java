package com.walker.part.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("job_info")
public class JobInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 兼职ID
     */
    @TableId(value = "job_id", type = IdType.ASSIGN_ID)
    private String jobId;

    /**
     * 商家ID
     */
    private String businessId;

    /**
     * 兼职名称
     */
    private String name;

    /**
     * 兼职内容
     */
    private String content;

    /**
     * 兼职封面
     */
    private String cover;

    /**
     * 兼职类型ID
     */
    private String typeId;

    /**
     * 薪水
     */
    private BigDecimal salary;

    /**
     * 薪水类型：1小时，2天，3周，4月，5次
     */
    private String salaryType;

    /**
     * 结算方式：1日结，2周结，3月结，4完工结
     */
    private String payType;

    /**
     * 性别需求：1男，2女，3不限
     */
    private String gender;

    /**
     * 招聘人数
     */
    private Integer count;

    /**
     * 已报名人数
     */
    private Integer registered;

    /**
     * 工作开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime beginTime;

    /**
     * 工作结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime endTime;

    /**
     * 地址
     */
    private String address;

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
     * 兼职发布时间
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
