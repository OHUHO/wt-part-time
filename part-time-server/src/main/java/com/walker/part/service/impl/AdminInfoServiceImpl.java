package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.walker.part.entity.AdminInfo;
import com.walker.part.form.AdminLoginForm;
import com.walker.part.mapper.AdminInfoMapper;
import com.walker.part.service.IAdminInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walker.part.utils.Result;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 15:22:42
 */
@Service
@RequiredArgsConstructor
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements IAdminInfoService {


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveForm(AdminInfo adminInfo) {
        if (StringUtils.isBlank(adminInfo.getId())){
            adminInfo.setCreateTime(LocalDateTime.now());
        }
        this.saveOrUpdate(adminInfo);
        adminInfo.setId(adminInfo.getId());
        return true;
    }

    @Override
    public Result<?> login(AdminLoginForm form) {
        //System.out.println("form.getUsername() = " + form.getUsername());
        //System.out.println("form.getUsername() = " + form.getPassword());
        AdminInfo adminInfo = getBaseMapper().selectOne(new LambdaQueryWrapper<AdminInfo>()
                .eq(AdminInfo::getName, form.getUsername())
                .eq(AdminInfo::getPassword, form.getPassword())
        );
        //System.out.println("adminInfo = " + adminInfo);
        if (ObjectUtils.isEmpty(adminInfo)){
            return Result.failed("用户名或密码不正确！");
        }
        String token = IdWorker.getIdStr();
        adminInfo.setToken(token);
        // 更新token到数据库
        getBaseMapper().updateById(adminInfo);

        Map<String, String> map = new HashMap<>();
        map.put("token",token);
        return Result.success("登录成功！",map);
    }

    @Override
    public AdminInfo getInfo(String token) {
        AdminInfo adminInfo = getBaseMapper().selectOne(new LambdaQueryWrapper<AdminInfo>()
                .eq(AdminInfo::getToken, token));
        adminInfo.setPassword(null);
        return adminInfo;
    }
}
