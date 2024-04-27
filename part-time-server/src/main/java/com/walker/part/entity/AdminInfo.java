package com.walker.part.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2024-04-21 16:00:08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("admin_info")
@ToString
public class AdminInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 状态：1活跃，2封禁
     */
    private Integer status;

    /**
     * 令牌
     */
    private String token;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer logicDelete;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime createTime;
}
