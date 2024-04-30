package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.ExperienceGood;
import com.walker.part.form.FansForm;
import com.walker.part.form.GoodForm;
import com.walker.part.mapper.ExperienceGoodMapper;
import com.walker.part.response.ExperienceResp;
import com.walker.part.service.IExperienceGoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walker.part.service.IExperienceService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Walker
 * @since 2024-04-29 10:05:36
 */
@Service
@RequiredArgsConstructor
public class ExperienceGoodServiceImpl extends ServiceImpl<ExperienceGoodMapper, ExperienceGood> implements IExperienceGoodService {

    private final IExperienceService experienceService;


    @Override
    public ExperienceGood getByUserId(String experienceId, String userId) {
        return getBaseMapper().selectOne(new LambdaQueryWrapper<ExperienceGood>()
                .eq(ExperienceGood::getExperienceId,experienceId)
                .eq(ExperienceGood::getUserId,userId)
        );
    }

    @Override
    public Page<ExperienceResp> getPage(GoodForm form) {
        Page<ExperienceGood> goodPage = new Page<>(form.getCurrent(),form.getSize());
        Page<ExperienceGood> experienceGoodPage = getBaseMapper().selectPage(goodPage, new LambdaQueryWrapper<ExperienceGood>()
                .eq(ExperienceGood::getUserId, form.getUserId())
        );
        List<ExperienceGood> list = experienceGoodPage.getRecords();
        Page<ExperienceResp> pageInfo = new Page<>();
        pageInfo.setTotal(experienceGoodPage.getTotal());
        if (CollectionUtils.isEmpty(list)){
            return pageInfo;
        }
        List<String> experienceIds = list.stream()
                .map(ExperienceGood::getExperienceId).collect(Collectors.toList());

        List<ExperienceResp> records =  experienceService.getByIds(experienceIds);
        pageInfo.setRecords(records);
        return pageInfo;
    }

    @Override
    public Long countGood(String experienceId, String userId) {
        return getBaseMapper().selectCount(new LambdaQueryWrapper<ExperienceGood>()
                .eq(StringUtils.isNoneBlank(experienceId), ExperienceGood::getExperienceId, experienceId)
                .eq(StringUtils.isNoneBlank(userId), ExperienceGood::getUserId, userId)
        );
    }

}
