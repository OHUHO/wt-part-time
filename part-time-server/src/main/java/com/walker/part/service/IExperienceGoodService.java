package com.walker.part.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.ExperienceGood;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.form.FansForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-29 10:05:36
 */
public interface IExperienceGoodService extends IService<ExperienceGood> {

    /**
     * 通过经验ID和用户ID查询是否点赞
     * @param experienceId 经验ID
     * @param userId 用户ID
     * @return 点赞信息
     */
    ExperienceGood getByUserId(String experienceId, String userId);

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    Page<ExperienceGood> getPage(FansForm form);
}
