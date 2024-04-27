package com.walker.part.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.BusinessInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.form.BusinessForm;
import com.walker.part.form.PageForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
public interface IBusinessInfoService extends IService<BusinessInfo> {

    /**
     * 保存或更新商家认证信息
     * @param business 商家认证信息
     * @return 是否保存成功
     */
    boolean saveForm(BusinessInfo business);

    /**
     * 分页查询
     * @param form 表单
     * @return 分页
     */
    Page<BusinessInfo> getPage(BusinessForm form);

    /**
     * 获取审核通过的商家信息
     * @param userId 商家ID
     * @param status 状态
     * @return 商家信息
     */
    BusinessInfo getBusinessById(String userId, Integer status);
}
