package com.walker.part.controller;

import com.walker.part.entity.AdminInfo;
import com.walker.part.form.AdminLoginForm;
import com.walker.part.service.IAdminInfoService;
import com.walker.part.utils.Result;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 15:22:42
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminInfoController {

    private final IAdminInfoService adminInfoService;

    /**
     * 退出登录
     * @return json
     */
    @PostMapping(value = "/logout",name = "退出登录")
    public Result<?> logout(){
        return Result.success("退出登录成功！");
    }

    /**
     * 获管理员详情
     * @param token 令牌
     * @return object
     */
    @GetMapping(value = "/info",name = "获取详细信息")
    public Result<AdminInfo> info(String token){
        return Result.success(adminInfoService.getInfo(token));
    }

    @PostMapping(value = "/login",name = "登录")
    public Result<?> login(@RequestBody AdminLoginForm form){
        return adminInfoService.login(form);
    }

    /**
     * 保存或更新管理员信息
     * @param adminInfo 管理员信息
     * @return 管理员信息
     */
    @PostMapping(value = "/save",name = "保存或更新")
    public Result<AdminInfo> save(@RequestBody AdminInfo adminInfo){
        if (adminInfoService.saveForm(adminInfo)){
            return Result.success("保存成功！",adminInfo);
        }
        return Result.failed("保存失败！");
    }

    /**
     * 删除管理员信息
     * @param adminId 管理员ID
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除")
    public Result<?> delete(String adminId){
        if (adminInfoService.removeById(adminId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }
}
