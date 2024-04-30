package com.walker.part.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Experience;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.form.PageForm;
import com.walker.part.response.ExperienceResp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
public interface IExperienceService extends IService<Experience> {

    /**
     * 保存或更新经验
     * @param experience 经验
     * @return 是否保存成功
     */
    boolean saveForm(Experience experience);

    /**
     * 分页查询
     * @param form 分页表单
     * @return 分页集合
     */
    Page<ExperienceResp> getPage(PageForm form);

    /**
     * 通过经验ID查询
     * @param experienceId 经验ID
     * @return 经验
     */
    ExperienceResp getByExperienceId(String experienceId);


    /**
     * 通过经验ID批量获取经验信息
     * @param experienceIds 经验ID列表
     * @return 经验列表
     */
    List<ExperienceResp> getByIds(List<String> experienceIds);
}
