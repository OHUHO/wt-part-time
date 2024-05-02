package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Carousel;
import com.walker.part.entity.UserInfo;
import com.walker.part.form.PageForm;
import com.walker.part.form.UserLoginForm;
import com.walker.part.response.UserInfoResp;
import com.walker.part.service.IUserInfoService;
import com.walker.part.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final IUserInfoService userInfoService;

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<Page<UserInfo>> list(@RequestBody PageForm form){
        return Result.success(userInfoService.getPage(form));
    }

    /**
     * 微信用户登录
     * @param form 表单
     * @return 用户信息
     */
    @PostMapping(value = "/login",name = "微信登录")
    public Result<UserInfo> login(@RequestBody UserLoginForm form){
        return Result.success(userInfoService.login(form));
    }

    /**
     * 查询用户信息
     * @param userId 用户Id
     * @return 用户信息
     */
    @GetMapping(value = "/getById",name = "通过用户Id查询")
    public Result<UserInfoResp> getById(String userId){
        return Result.success(userInfoService.geUserInfo(userId));
    }

}
