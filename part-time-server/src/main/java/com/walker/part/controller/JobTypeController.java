package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Carousel;
import com.walker.part.entity.JobType;
import com.walker.part.form.JobTypeForm;
import com.walker.part.form.PageForm;
import com.walker.part.service.IJobTypeService;
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
@RequestMapping("/jobType")
@RequiredArgsConstructor
public class JobTypeController {

    private final IJobTypeService jobTypeService;

    /**
     * 通过ID查询轮播图信息
     * @param typeId 轮播图ID
     * @return 轮播图
     */
    @GetMapping(value = "/getById",name = "通过ID获取")
    public Result<JobType> getById(String typeId){
        return Result.success(jobTypeService.getById(typeId));
    }

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<Page<JobType>> list(@RequestBody JobTypeForm form){
        return Result.success(jobTypeService.getPage(form));
    }

    /**
     * 保存或更新兼职类型
     * @param jobType 兼职类型
     * @return 兼职类型
     */
    @PostMapping(value = "/save",name = "保存或更新")
    public Result<JobType> save(@RequestBody JobType jobType){
        if (jobTypeService.saveForm(jobType)){
            return Result.success("保存成功！",jobType);
        }
        return Result.failed("保存失败!");
    }

    /**
     * 删除兼职类型
     * @param typeId 类型Id
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除兼职类型")
    public Result<?> delete(String typeId){
        if (jobTypeService.removeById(typeId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }

}
