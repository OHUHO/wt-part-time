package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.ExperienceLove;
import com.walker.part.form.FansForm;
import com.walker.part.service.IExperienceLoveService;
import com.walker.part.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Walker
 * @since 2024-04-29 10:05:36
 */
@RestController
@RequestMapping("/love")
@RequiredArgsConstructor
public class ExperienceLoveController {

    private final IExperienceLoveService loveService;

    /**
     * 通过经验ID和用户ID查询是否收藏
     * @param experienceId 经验ID
     * @param userId 用户ID
     * @return 收藏信息
     */
    @GetMapping(value = "/getByUserId",name = "通过ID获取")
    public Result<ExperienceLove> getByUserId(String experienceId, String userId){
        return Result.success(loveService.getByUserId(experienceId,userId));
    }


    /**
     * 通过ID查询收藏信息
     * @param loveId 收藏ID
     * @return 收藏
     */
    @GetMapping(value = "/getById",name = "通过ID获取")
    public Result<ExperienceLove> getById(String loveId){
        return Result.success(loveService.getById(loveId));
    }

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<Page<ExperienceLove>> list(@RequestBody FansForm form){
        return Result.success(loveService.getPage(form));
    }

    /**
     * 保存或更新收藏
     * @param love 收藏
     * @return 收藏
     */
    @PostMapping(value = "/save",name = "保存或更新收藏")
    public Result<ExperienceLove> save(@RequestBody ExperienceLove love){
        if (loveService.saveOrUpdate(love)){
            return Result.success("保存成功！",love);
        }
        return Result.success("保存失败！");
    }

    /**
     * 删除收藏
     * @param loveId 收藏ID
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除收藏")
    public Result<?> delete(String loveId){
        if (loveService.removeById(loveId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }

}
