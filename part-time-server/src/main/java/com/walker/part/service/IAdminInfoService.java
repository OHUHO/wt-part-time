package com.walker.part.service;

import com.walker.part.entity.AdminInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.enums.ResultEnum;
import com.walker.part.form.AdminLoginForm;
import com.walker.part.utils.Result;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 15:22:42
 */
public interface IAdminInfoService extends IService<AdminInfo> {

    /**
     * 保存或更新管理员信息
     * @param adminInfo 管理员信息
     * @return 是否保存成功
     */
    boolean saveForm(AdminInfo adminInfo);

    /**
     * 管理员登录
     * @param form 表单
     * @return json
     */
    Result<?> login(AdminLoginForm form);

    /**
     * 查询管理员详情
     * @param token 令牌
     * @return 管理员信息
     */
    AdminInfo getInfo(String token);
}
