package com.walker.part.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.RegistrationSheet;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.form.PageForm;
import com.walker.part.form.RegistrationForm;
import com.walker.part.response.RegistrationResp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
public interface IRegistrationSheetService extends IService<RegistrationSheet> {

    /**
     * 分页查询
     * @param form 表单
     * @return 分页结果
     */
    Page<RegistrationResp> getPage(RegistrationForm form);

    /**
     * 保存或更新表单
     * @param form 表单
     * @return 是否保存成功
     */
    boolean saveForm(RegistrationSheet form);

    /**
     * 通过用户是否已报名该兼职
     * @param jobId 兼职ID
     * @param userId 用户ID
     * @return 是否报名
     */
    boolean registrationEd(String jobId, String userId);
}
