package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Carousel;
import com.walker.part.entity.RegistrationSheet;
import com.walker.part.form.PageForm;
import com.walker.part.form.RegistrationForm;
import com.walker.part.response.RegistrationResp;
import com.walker.part.service.IRegistrationSheetService;
import com.walker.part.utils.Result;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationSheetController {

    private final IRegistrationSheetService registrationSheetService;

    /**
     * 用户是否已报名该兼职
     * @param jobId 兼职ID
     * @param userId 用户ID
     * @return 是否报名
     */
    @GetMapping(value = "/registrationEd",name = "查询是否报名过该兼职")
    public Result<?> registrationEd(String jobId, String userId){
        return Result.success(registrationSheetService.registrationEd(jobId,userId));
    }

    /**
     * 通过ID查询兼职注册信息
     * @param registrationId 兼职注册ID
     * @return 兼职注册
     */
    @GetMapping(value = "/getById",name = "通过ID获取")
    public Result<RegistrationSheet> getById(String registrationId){
        return Result.success(registrationSheetService.getById(registrationId));
    }

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<Page<RegistrationResp>> list(@RequestBody RegistrationForm form){
        return Result.success(registrationSheetService.getPage(form));
    }

    /**
     * 保存或更新兼职注册
     * @param form 兼职注册
     * @return 兼职注册
     */
    @PostMapping(value = "/save",name = "保存或更新兼职注册")
    public Result<RegistrationSheet> save(@RequestBody RegistrationSheet form){
        if (registrationSheetService.saveForm(form)){
            return Result.success("保存成功！",form);
        }
        return Result.success("保存失败！");
    }

    /**
     * 删除兼职注册
     * @param registrationId 兼职注册ID
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除兼职注册")
    public Result<?> delete(String registrationId){
        if (registrationSheetService.removeById(registrationId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }

}
