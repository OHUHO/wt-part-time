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
@TableName("business_info")
public class BusinessInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家ID
     */
    @TableId(value = "business_id", type = IdType.ASSIGN_ID)
    private String businessId;

    /**
     * 商家真实姓名
     */
    private String username;

    /**
     * 性别：1男，2女，3未知
     */
    private String gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 身份证号码
     */
    private String cardNo;

    /**
     * 注册店铺类型
     */
    private String storeType;

    /**
     * 注册店铺名称
     */
    private String storeName;

    /**
     * 证件类型
     */
    private String papersType;

    /**
     * 证件号码
     */
    private String papersNumber;

    /**
     * 证件链接地址集合
     */
    private String papersImg;

    /**
     * 当前状态：1待审核，2审核通过，3未通过
     */
    private Integer status;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    private Integer logicDelete;

    /**
     * 创建人Id
     */
    private String createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime createTime;

    /**
     * 最后一次更新人id
     */
    private String updateUserId;

    /**
     * 最后一次更新人姓名
     */
    private String updateUserName;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime updateTime;

    /**
     * 审核人ID
     */
    private String passUserId;

    /**
     * 审核人姓名
     */
    private String passUserName;

    /**
     * 审核通过时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime passTime;
}
