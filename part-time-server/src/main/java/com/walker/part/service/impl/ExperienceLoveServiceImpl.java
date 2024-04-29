package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.ExperienceGood;
import com.walker.part.entity.ExperienceLove;
import com.walker.part.form.FansForm;
import com.walker.part.mapper.ExperienceLoveMapper;
import com.walker.part.service.IExperienceLoveService;
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
public class ExperienceLoveServiceImpl extends ServiceImpl<ExperienceLoveMapper, ExperienceLove> implements IExperienceLoveService {

    @Override
    public ExperienceLove getByUserId(String experienceId, String userId) {
        return getBaseMapper().selectOne(new LambdaQueryWrapper<ExperienceLove>()
                .eq(ExperienceLove::getExperienceId,experienceId)
                .eq(ExperienceLove::getUserId,userId)
        );
    }

    @Override
    public Page<ExperienceLove> getPage(FansForm form) {
        Page<ExperienceLove> goodPage = new Page<>(form.getCurrent(),form.getSize());
        return getBaseMapper().selectPage(goodPage,new LambdaQueryWrapper<ExperienceLove>()
                // TODO
        );
    }
}
