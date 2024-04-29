package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.ExperienceGood;
import com.walker.part.form.FansForm;
import com.walker.part.mapper.ExperienceGoodMapper;
import com.walker.part.service.IExperienceGoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public ExperienceGood getByUserId(String experienceId, String userId) {
        return getBaseMapper().selectOne(new LambdaQueryWrapper<ExperienceGood>()
                .eq(ExperienceGood::getExperienceId,experienceId)
                .eq(ExperienceGood::getUserId,userId)
        );
    }

    @Override
    public Page<ExperienceGood> getPage(FansForm form) {
        Page<ExperienceGood> goodPage = new Page<>(form.getCurrent(),form.getSize());
        return getBaseMapper().selectPage(goodPage,new LambdaQueryWrapper<ExperienceGood>()
                // TODO
        );
    }

}
