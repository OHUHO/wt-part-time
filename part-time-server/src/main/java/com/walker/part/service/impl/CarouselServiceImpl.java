package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Carousel;
import com.walker.part.form.PageForm;
import com.walker.part.mapper.CarouselMapper;
import com.walker.part.service.ICarouselService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

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
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements ICarouselService {

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveForm(Carousel carousel) {
        if (StringUtils.isBlank(carousel.getCarouselId())){
            carousel.setCreateTime(LocalDateTime.now());
        }
        this.saveOrUpdate(carousel);
        // 返回Id
        carousel.setCarouselId(carousel.getCarouselId());
        return true;
    }

    @Override
    public Page<Carousel> getPage(PageForm form) {
        Page<Carousel> page = new Page<>(form.getCurrent(), form.getSize());
        return getBaseMapper().selectPage(page,new LambdaQueryWrapper<Carousel>()
                .and(StringUtils.isNoneBlank(form.getKeywords()),w-> w
                        .like(Carousel::getName,form.getKeywords())
                        .or()
                        .like(Carousel::getDescription,form.getKeywords())
                )
        );
    }
}
