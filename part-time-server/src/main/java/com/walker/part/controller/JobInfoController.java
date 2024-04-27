package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.BusinessInfo;
import com.walker.part.entity.JobInfo;
import com.walker.part.form.BusinessForm;
import com.walker.part.form.JobForm;
import com.walker.part.form.PageForm;
import com.walker.part.response.JobInfoResp;
import com.walker.part.service.IJobInfoService;
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
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobInfoController {

    private final IJobInfoService jobInfoService;

    /**
     * 通过ID查询兼职信息
     * @param jobId 兼职ID
     * @param status 状态
     * @return 兼职信息
     */
    @GetMapping(value = "/getById",name = "通过ID获取")
    public Result<JobInfoResp> getById(String jobId, Integer status){
        return Result.success(jobInfoService.getJobById(jobId,status));
    }

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<Page<JobInfoResp>> list(@RequestBody JobForm form){
        return Result.success(jobInfoService.getPage(form));
    }

    /**
     * 保存或更新兼职信息
     * @param job 兼职信息
     * @return 兼职信息
     */
    @PostMapping(value = "/save",name = "保存或更新兼职信息")
    public Result<JobInfo> save(@RequestBody JobInfo job){
        if (jobInfoService.saveForm(job)){
            return Result.success("保存成功！",job);
        }
        return Result.success("保存失败！");
    }

    /**
     * 删除兼职信息
     * @param jobId 兼职ID
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除兼职信息")
    public Result<?> delete(String jobId){
        if (jobInfoService.removeById(jobId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }

}
