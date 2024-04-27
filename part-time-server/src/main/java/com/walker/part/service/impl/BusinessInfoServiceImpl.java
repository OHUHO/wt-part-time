package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.BusinessInfo;
import com.walker.part.entity.Carousel;
import com.walker.part.form.BusinessForm;
import com.walker.part.form.PageForm;
import com.walker.part.mapper.BusinessInfoMapper;
import com.walker.part.service.IBusinessInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

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
public class BusinessInfoServiceImpl extends ServiceImpl<BusinessInfoMapper, BusinessInfo> implements IBusinessInfoService {

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveForm(BusinessInfo business) {
        if (StringUtils.isBlank(business.getBusinessId())){
            business.setCreateTime(LocalDateTime.now());
        }else {
            business.setUpdateTime(LocalDateTime.now());
        }
        return this.saveOrUpdate(business);
    }

    @Override
    public Page<BusinessInfo> getPage(BusinessForm form) {
        Page<BusinessInfo> page = new Page<>(form.getCurrent(), form.getSize());
        return getBaseMapper().selectPage(page,new LambdaQueryWrapper<BusinessInfo>()
                .and(StringUtils.isNoneBlank(form.getKeywords()), w-> w
                        .like(BusinessInfo::getUsername,form.getKeywords())
                        .or()
                        .like(BusinessInfo::getStoreName,form.getKeywords())
                )
                .in(CollectionUtils.isNotEmpty(form.getStatus()),BusinessInfo::getStatus,form.getStatus())
        );
    }

    @Override
    public BusinessInfo getBusinessById(String userId, Integer status) {
        return getBaseMapper().selectOne(new LambdaQueryWrapper<BusinessInfo>()
                .eq(BusinessInfo::getBusinessId,userId)
                .eq(status != null,BusinessInfo::getStatus,status)
                //.select(Arrays.asList(BusinessInfo::getBusinessId,
                //        BusinessInfo::getStoreName,
                //        BusinessInfo::getStoreType)
                //)
        );
    }
}
