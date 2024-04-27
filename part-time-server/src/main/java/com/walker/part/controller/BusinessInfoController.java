package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.BusinessInfo;
import com.walker.part.entity.Carousel;
import com.walker.part.form.BusinessForm;
import com.walker.part.form.PageForm;
import com.walker.part.service.IBusinessInfoService;
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
@RequestMapping("/business")
@RequiredArgsConstructor
public class BusinessInfoController {

    private final IBusinessInfoService businessInfoService;

    /**
     * 通过ID查询商家信息
     * @param businessId 商家ID
     * @param status 状态
     * @return 商家信息
     */
    @GetMapping(value = "/getById",name = "通过ID获取")
    public Result<BusinessInfo> getById(String businessId,Integer status){
        return Result.success(businessInfoService.getBusinessById(businessId,status));
    }

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<Page<BusinessInfo>> list(@RequestBody BusinessForm form){
        return Result.success(businessInfoService.getPage(form));
    }

    /**
     * 保存或更新商家信息
     * @param business 商家认证信息
     * @return 商家认证信息
     */
    @PostMapping(value = "/save",name = "保存或更新商家信息")
    public Result<BusinessInfo> save(@RequestBody BusinessInfo business){
        if (businessInfoService.saveForm(business)){
            return Result.success("保存成功！",business);
        }
        return Result.success("保存失败！");
    }

    /**
     * 删除商家信息
     * @param businessId 商家ID
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除商家信息")
    public Result<?> delete(String businessId){
        if (businessInfoService.removeById(businessId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }

}
