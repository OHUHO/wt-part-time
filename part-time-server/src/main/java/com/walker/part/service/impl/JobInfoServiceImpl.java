package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.BusinessInfo;
import com.walker.part.entity.JobInfo;
import com.walker.part.entity.JobType;
import com.walker.part.form.JobForm;
import com.walker.part.mapper.JobInfoMapper;
import com.walker.part.response.JobInfoResp;
import com.walker.part.service.IBusinessInfoService;
import com.walker.part.service.IJobInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walker.part.service.IJobTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
@Service
@RequiredArgsConstructor
public class JobInfoServiceImpl extends ServiceImpl<JobInfoMapper, JobInfo> implements IJobInfoService {

    private final IJobTypeService jobTypeService;

    private final IBusinessInfoService businessInfoService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveForm(JobInfo job) {
        if (StringUtils.isBlank(job.getJobId())){
            job.setCreateTime(LocalDateTime.now());
        }else {
            job.setUpdateTime(LocalDateTime.now());
        }
        job.setStatus(1);
        return this.saveOrUpdate(job);
    }

    @Override
    public Page<JobInfoResp> getPage(JobForm form) {
        Page<JobInfo> page = new Page<>(form.getCurrent(), form.getSize());
        Page<JobInfo> jobInfoPage = getBaseMapper().selectPage(page, new LambdaQueryWrapper<JobInfo>()
                .and(StringUtils.isNoneBlank(form.getKeywords()), w -> w
                        .like(JobInfo::getName, form.getKeywords())
                        .or()
                        .like(JobInfo::getContent, form.getKeywords())
                )
                .eq(StringUtils.isNoneBlank(form.getBusinessId()),JobInfo::getBusinessId,form.getBusinessId())
                .in(CollectionUtils.isNotEmpty(form.getStatus()), JobInfo::getStatus, form.getStatus())
                .orderByDesc(JobInfo::getCreateTime)
        );
        Page<JobInfoResp> pageInfo = new Page<>();
        pageInfo.setTotal(jobInfoPage.getTotal());
        List<JobInfo> list = jobInfoPage.getRecords();
        if (CollectionUtils.isEmpty(list)){
            return pageInfo;
        }
        List<JobInfoResp> records = new ArrayList<>();
        for (JobInfo jobInfo : list) {
            records.add(this.trans(jobInfo));
        }
        pageInfo.setRecords(records);
        return pageInfo;
    }

    @Override
    public JobInfoResp getJobById(String jobId, Integer status) {
        JobInfo jobInfo = getBaseMapper().selectOne(new LambdaQueryWrapper<JobInfo>()
                .eq(JobInfo::getJobId, jobId)
                .eq(status != null, JobInfo::getStatus, status)

        );
        return this.trans(jobInfo);
    }

    /**
     * 数据封装
     * @param jobInfo jobInfo
     * @return JobInfoResp
     */
    private JobInfoResp trans(JobInfo jobInfo){
        JobInfoResp jobInfoResp = new JobInfoResp();
        BeanUtils.copyProperties(jobInfo,jobInfoResp);
        if (ObjectUtils.isNotEmpty(jobInfo)){
            // 兼职类型
            JobType jobType = jobTypeService.getById(jobInfo.getTypeId());
            jobInfoResp.setTypeName(jobType.getTypeName());
            // 店铺类型
            BusinessInfo businessInfo = businessInfoService.getById(jobInfo.getBusinessId());
            jobInfoResp.setStoreName(businessInfo.getStoreName());
        }
        return jobInfoResp;
    }


}
