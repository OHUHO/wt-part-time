package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.BusinessInfo;
import com.walker.part.entity.Experience;
import com.walker.part.entity.UserInfo;
import com.walker.part.form.ExperienceForm;
import com.walker.part.form.PageForm;
import com.walker.part.mapper.ExperienceMapper;
import com.walker.part.response.ExperienceResp;
import com.walker.part.service.IExperienceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walker.part.service.IUserInfoService;
import lombok.RequiredArgsConstructor;
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
public class ExperienceServiceImpl extends ServiceImpl<ExperienceMapper, Experience> implements IExperienceService {

    private final IUserInfoService userInfoService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveForm(Experience experience) {
        if (StringUtils.isBlank(experience.getExperienceId())){
            experience.setCreateTime(LocalDateTime.now());
        }else {
            experience.setUpdateTime(LocalDateTime.now());
        }
        return this.saveOrUpdate(experience);
    }

    @Override
    public Page<ExperienceResp> getPage(ExperienceForm form) {
        Page<Experience> page = new Page<>(form.getCurrent(), form.getSize());
        Page<Experience> experiencePage = getBaseMapper().selectPage(page, new LambdaQueryWrapper<Experience>()
                .and(StringUtils.isNoneBlank(form.getKeywords()), w -> w
                        .like(Experience::getName, form.getKeywords())
                        .or()
                        .like(Experience::getContent, form.getKeywords())
                )
                .eq(StringUtils.isNoneBlank(form.getUserId()),Experience::getCreateUserId,form.getUserId())
                .orderByDesc(Experience::getCreateTime)
        );
        Page<ExperienceResp> pageInfo = new Page<>();
        pageInfo.setTotal(experiencePage.getTotal());
        List<Experience> list = experiencePage.getRecords();
        if (CollectionUtils.isEmpty(list)){
            return pageInfo;
        }
        List<ExperienceResp> records = new ArrayList<>();
        for (Experience experience : list) {
            records.add(this.transExperience(experience));
        }
        pageInfo.setRecords(records);
        return pageInfo;
    }

    @Override
    public ExperienceResp getByExperienceId(String experienceId) {
        Experience experience = getBaseMapper().selectById(experienceId);
        return this.transExperience(experience);
    }

    @Override
    public List<ExperienceResp> getByIds(List<String> experienceIds) {
        List<Experience> experienceList = getBaseMapper().selectBatchIds(experienceIds);
        List<ExperienceResp> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(experienceList)){
            return list;
        }
        for (Experience experience : experienceList) {
            list.add(this.transExperience(experience));
        }
        return list;
    }

    /**
     * Experience转换
     * @param experience 经验
     * @return 经验，包含详细的用户数据
     */
    private ExperienceResp transExperience(Experience experience) {
        ExperienceResp experienceResp = new ExperienceResp();
        BeanUtils.copyProperties(experience,experienceResp);

        // 设置用户信息
        UserInfo userInfo = userInfoService.getById(experience.getCreateUserId());
        experienceResp.setUserId(userInfo.getId());
        experienceResp.setUsername(userInfo.getUsername());
        experienceResp.setPortrait(userInfo.getPortrait());

        return experienceResp;
    }
}
