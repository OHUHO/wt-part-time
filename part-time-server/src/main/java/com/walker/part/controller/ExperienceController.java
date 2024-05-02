package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Experience;
import com.walker.part.entity.Experience;
import com.walker.part.form.ExperienceForm;
import com.walker.part.form.PageForm;
import com.walker.part.response.ExperienceResp;
import com.walker.part.service.IExperienceService;
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
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final IExperienceService experienceService;

    /**
     * 通过ID查询经验信息
     * @param experienceId 经验ID
     * @return 经验
     */
    @GetMapping(value = "/getById",name = "通过ID获取")
    public Result<ExperienceResp> getById(String experienceId){
        return Result.success(experienceService.getByExperienceId(experienceId));
    }

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<Page<ExperienceResp>> list(@RequestBody ExperienceForm form){
        return Result.success(experienceService.getPage(form));
    }

    /**
     * 保存或更新经验
     * @param experience 经验
     * @return 经验
     */
    @PostMapping(value = "/save",name = "保存或更新经验")
    public Result<Experience> save(@RequestBody Experience experience){
        if (experienceService.saveForm(experience)){
            return Result.success("保存成功！",experience);
        }
        return Result.success("保存失败！");
    }

    /**
     * 删除经验
     * @param experienceId 经验ID
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除经验")
    public Result<?> delete(String experienceId){
        if (experienceService.removeById(experienceId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }

}
