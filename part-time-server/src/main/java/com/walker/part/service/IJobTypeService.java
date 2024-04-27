package com.walker.part.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.JobType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.enums.ResultEnum;
import com.walker.part.form.JobTypeForm;
import com.walker.part.form.PageForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
public interface IJobTypeService extends IService<JobType> {

    /**
     * 保存或更新兼职类型
     * @param jobType 兼职类型
     * @return 是否保存成功
     */
    boolean saveForm(JobType jobType);

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    Page<JobType> getPage(JobTypeForm form);
}
