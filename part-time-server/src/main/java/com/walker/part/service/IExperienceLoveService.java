package com.walker.part.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.ExperienceLove;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.form.FansForm;
import com.walker.part.form.LoveForm;
import com.walker.part.response.ExperienceResp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-29 10:05:36
 */
public interface IExperienceLoveService extends IService<ExperienceLove> {

    /**
     * 通过经验ID和用户ID查询是否收藏
     * @param experienceId 经验ID
     * @param userId 用户ID
     * @return 收藏信息
     */
    ExperienceLove getByUserId(String experienceId, String userId);

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    Page<ExperienceResp> getPage(LoveForm form);

    /**
     * 统计用户或经验的收藏数量
     * @param experienceId 经验ID
     * @param userId 用户ID
     * @return 收藏数量
     */
    Long countLove(String experienceId, String userId);
}
