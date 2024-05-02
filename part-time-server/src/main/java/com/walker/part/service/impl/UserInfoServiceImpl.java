package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.walker.part.entity.BusinessInfo;
import com.walker.part.entity.UserInfo;
import com.walker.part.exception.ApplicationException;
import com.walker.part.form.UserLoginForm;
import com.walker.part.mapper.UserInfoMapper;
import com.walker.part.response.UserInfoResp;
import com.walker.part.service.IBusinessInfoService;
import com.walker.part.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walker.part.utils.WechatUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    private final IBusinessInfoService businessInfoService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public UserInfo login(UserLoginForm form) {
        if (StringUtils.isBlank(form.getCode())){
            throw new ApplicationException("参数不能为空！");
        }
        String openId = WechatUtil.generateOpenId(form.getCode());
        if (StringUtils.isBlank(openId)){
            throw new ApplicationException("获取OpenId失败！");
        }
        System.out.println("current openId = " + openId);
        UserInfo user = getBaseMapper().selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getOpenId, openId)
        );
        if (ObjectUtils.isEmpty(user)){
            // 注册
            user = new UserInfo();
            user.setOpenId(openId)
                    .setUsername(form.getUsername())
                    .setPortrait(form.getPortrait())
                    .setCreateTime(LocalDateTime.now());
        }else {
            // 登录
            user.setUsername(form.getUsername())
                    .setPortrait(form.getPortrait())
                    .setUpdateTime(LocalDateTime.now());
        }
        this.saveOrUpdate(user);
        return user;
    }

    @Override
    public UserInfoResp geUserInfo(String userId) {
        UserInfo user = getBaseMapper().selectById(userId);
        BusinessInfo business = businessInfoService.getBusinessById(userId,null);
        UserInfoResp userResp = new UserInfoResp();
        BeanUtils.copyProperties(user,userResp);
        if (ObjectUtils.isNotEmpty(business)){
            userResp.setBusiness(true);
        }
        return userResp;
    }
}
