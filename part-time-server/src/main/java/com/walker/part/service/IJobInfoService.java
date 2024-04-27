package com.walker.part.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.BusinessInfo;
import com.walker.part.entity.JobInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.form.JobForm;
import com.walker.part.form.PageForm;
import com.walker.part.response.JobInfoResp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
public interface IJobInfoService extends IService<JobInfo> {

    /**
     * 保存或更新兼职信息
     * @param job 兼职信息
     * @return 是否保存成功
     */
    boolean saveForm(JobInfo job);

    /**
     * 分页查询
     * @param form 表单
     * @return 分页
     */
    Page<JobInfoResp> getPage(JobForm form);

    /**
     * 通过ID查询兼职信息
     * @param jobId 兼职ID
     * @param status 状态
     * @return 兼职信息
     */
    JobInfoResp getJobById(String jobId, Integer status);
}
