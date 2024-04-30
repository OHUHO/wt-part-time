package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.ExperienceGood;
import com.walker.part.entity.ExperienceLove;
import com.walker.part.form.FansForm;
import com.walker.part.form.LoveForm;
import com.walker.part.mapper.ExperienceLoveMapper;
import com.walker.part.response.ExperienceResp;
import com.walker.part.service.IExperienceLoveService;
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
public class ExperienceLoveServiceImpl extends ServiceImpl<ExperienceLoveMapper, ExperienceLove> implements IExperienceLoveService {


    private final IExperienceService experienceService;

    @Override
    public ExperienceLove getByUserId(String experienceId, String userId) {
        return getBaseMapper().selectOne(new LambdaQueryWrapper<ExperienceLove>()
                .eq(ExperienceLove::getExperienceId,experienceId)
                .eq(ExperienceLove::getUserId,userId)
        );
    }

    @Override
    public Page<ExperienceResp> getPage(LoveForm form) {
        Page<ExperienceLove> goodPage = new Page<>(form.getCurrent(),form.getSize());
        Page<ExperienceLove> experienceLovePage = getBaseMapper().selectPage(goodPage, new LambdaQueryWrapper<ExperienceLove>()
                .eq(ExperienceLove::getUserId, form.getUserId())
        );
        List<ExperienceLove> list = experienceLovePage.getRecords();
        Page<ExperienceResp> pageInfo = new Page<>();
        pageInfo.setTotal(experienceLovePage.getTotal());
        if (CollectionUtils.isEmpty(list)){
            return pageInfo;
        }
        List<String> experienceIds = list.stream()
                .map(ExperienceLove::getExperienceId).collect(Collectors.toList());

        List<ExperienceResp> records =  experienceService.getByIds(experienceIds);
        pageInfo.setRecords(records);
        return pageInfo;
    }

    @Override
    public Long countLove(String experienceId, String userId) {
        return getBaseMapper().selectCount(new LambdaQueryWrapper<ExperienceLove>()
                .eq(StringUtils.isNoneBlank(experienceId), ExperienceLove::getExperienceId, experienceId)
                .eq(StringUtils.isNoneBlank(userId), ExperienceLove::getUserId, userId)
        );
    }
}
