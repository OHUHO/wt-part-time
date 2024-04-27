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
import lombok.ToString;
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
@TableName("user_info")
@ToString
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 小程序唯一标识
     */
    private String openId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 性别：1男，2女，3未知
     */
    private String gender;

    /**
     * 身份：1用户，2商家
     */
    private String identity;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 积分或余额
     */
    private BigDecimal point;

    /**
     * 粉丝数量
     */
    private Long fans;

    /**
     * 关注数量
     */
    private Long love;

    /**
     * 获赞数量
     */
    private Long good;

    /**
     * 用户状态：1正常，2封禁
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
     * 最后一次更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime updateTime;
}
