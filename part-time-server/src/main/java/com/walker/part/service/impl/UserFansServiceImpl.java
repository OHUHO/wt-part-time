package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Experience;
import com.walker.part.entity.ExperienceLove;
import com.walker.part.entity.UserFans;
import com.walker.part.entity.UserInfo;
import com.walker.part.form.FansForm;
import com.walker.part.mapper.UserFansMapper;
import com.walker.part.response.UserFansResp;
import com.walker.part.response.UserInfoResp;
import com.walker.part.service.IUserFansService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walker.part.service.IUserInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Walker
 * @since 2024-04-29 09:33:39
 */
@Service
@RequiredArgsConstructor
public class UserFansServiceImpl extends ServiceImpl<UserFansMapper, UserFans> implements IUserFansService {

    private final IUserInfoService userInfoService;

    @Override
    public UserFans getByFromToId(String fromId, String toId) {
        return getBaseMapper().selectOne(new LambdaQueryWrapper<UserFans>()
                .eq(UserFans::getFromId,fromId)
                .eq(UserFans::getToId,toId)
        );
    }

    @Override
    public Page<UserFansResp> getPage(FansForm form) {
        Page<UserFans> page = new Page<>(form.getCurrent(), form.getSize());
        Page<UserFans> userFansPage = getBaseMapper().selectPage(page, new LambdaQueryWrapper<UserFans>()
                .eq(StringUtils.isNoneBlank(form.getFromId()), UserFans::getFromId, form.getFromId())
                .eq(StringUtils.isNoneBlank(form.getToId()), UserFans::getToId, form.getToId())
        );
        Page<UserFansResp> pageInfo = new Page<>();
        pageInfo.setTotal(userFansPage.getTotal());
        List<UserFans> list = userFansPage.getRecords();
        if (CollectionUtils.isEmpty(list)){
            return pageInfo;
        }
        List<UserFansResp> records = new ArrayList<>();
        for (UserFans userFans : list) {
            records.add(this.transFans(userFans));
        }
        pageInfo.setRecords(records);
        return pageInfo;
    }

    @Override
    public Long countLove(String userId) {
        return getBaseMapper().selectCount(new LambdaQueryWrapper<UserFans>()
                .eq(StringUtils.isNoneBlank(userId), UserFans::getFromId, userId)
        );
    }

    @Override
    public Long countFans(String userId) {
        return getBaseMapper().selectCount(new LambdaQueryWrapper<UserFans>()
                .eq(StringUtils.isNoneBlank(userId), UserFans::getToId, userId)
        );
    }

    /**
     * UserFans数据转换
     * @param userFans UserFans
     * @return 包含用户头像昵称的详细数据
     */
    private UserFansResp transFans(UserFans userFans) {
        UserFansResp userFansResp = new UserFansResp();
        BeanUtils.copyProperties(userFans,userFansResp);

        UserInfo fromUser = userInfoService.getById(userFans.getFromId());
        userFansResp.setFromUserId(fromUser.getId());
        userFansResp.setFromUsername(fromUser.getUsername());
        userFansResp.setFromPortrait(fromUser.getPortrait());

        UserInfo toUser = userInfoService.getById(userFans.getToId());
        userFansResp.setToUserId(toUser.getId());
        userFansResp.setToUsername(toUser.getUsername());
        userFansResp.setToPortrait(toUser.getPortrait());

        return userFansResp;
    }
}
