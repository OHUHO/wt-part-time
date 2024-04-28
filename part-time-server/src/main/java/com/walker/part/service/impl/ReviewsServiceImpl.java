package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Carousel;
import com.walker.part.entity.Reviews;
import com.walker.part.form.PageForm;
import com.walker.part.mapper.ReviewsMapper;
import com.walker.part.service.IReviewsService;
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
public class ReviewsServiceImpl extends ServiceImpl<ReviewsMapper, Reviews> implements IReviewsService {

    @Override
    public Page<Reviews> getPage(PageForm form) {
        Page<Reviews> page = new Page<>(form.getCurrent(), form.getSize());
        return getBaseMapper().selectPage(page,new LambdaQueryWrapper<Reviews>()
                .like(StringUtils.isNoneBlank(form.getKeywords()),Reviews::getContent,form.getKeywords())
        );
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveForm(Reviews form) {
        if (StringUtils.isBlank(form.getReviewsId()) && form.getPoint() == 1){
            // 一分的评价需要审核，离谱
            form.setStatus(1);
            form.setCreateTime(LocalDateTime.now());
        }else {
            form.setUpdateTime(LocalDateTime.now());
        }
        return this.saveOrUpdate(form);
    }

    @Override
    public Reviews getByMultiId(String jobId, String fromId, String toId) {
        return getBaseMapper().selectOne(new LambdaQueryWrapper<Reviews>()
                .eq(Reviews::getJobId,jobId)
                .eq(Reviews::getFromId,fromId)
                .eq(Reviews::getToId,toId)
        );
    }
}
