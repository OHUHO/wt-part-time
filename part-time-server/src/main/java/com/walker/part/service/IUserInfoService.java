package com.walker.part.service;

import com.walker.part.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.enums.ResultEnum;
import com.walker.part.form.UserLoginForm;
import com.walker.part.response.UserInfoResp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 微信登录
     * @param form 表单
     * @return 用户信息
     */
    UserInfo login(UserLoginForm form);

    /**
     * 获取当前用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    UserInfoResp geUserInfo(String userId);
}
