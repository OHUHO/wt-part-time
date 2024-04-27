package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Carousel;
import com.walker.part.entity.JobType;
import com.walker.part.form.JobTypeForm;
import com.walker.part.form.PageForm;
import com.walker.part.mapper.JobTypeMapper;
import com.walker.part.service.IJobTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
public class JobTypeServiceImpl extends ServiceImpl<JobTypeMapper, JobType> implements IJobTypeService {

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveForm(JobType jobType) {
        if (StringUtils.isBlank(jobType.getTypeId())){
            jobType.setCreateTime(LocalDateTime.now());
        }
        this.saveOrUpdate(jobType);
        jobType.setTypeId(jobType.getTypeId());
        return true;
    }

    @Override
    public Page<JobType> getPage(JobTypeForm form) {
        Page<JobType> page = new Page<>(form.getCurrent(), form.getSize());
        return getBaseMapper().selectPage(page,new LambdaQueryWrapper<JobType>()
                .and(StringUtils.isNoneBlank(form.getKeywords()),w-> w
                        .like(JobType::getTypeName,form.getKeywords())
                        .or()
                        .like(JobType::getDescription,form.getKeywords())
                )
                .orderByAsc(form.isOrder(),JobType::getGrade)
        );
    }
}
