package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.ExperienceGood;
import com.walker.part.form.FansForm;
import com.walker.part.form.GoodForm;
import com.walker.part.response.ExperienceResp;
import com.walker.part.service.IExperienceGoodService;
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
@RequestMapping("/good")
@RequiredArgsConstructor
public class ExperienceGoodController {

    private final IExperienceGoodService goodService;

    /**
     * 统计用户或经验的点赞数量
     * @param experienceId 经验ID
     * @param userId 用户ID
     * @return 点赞数量
     */
    @GetMapping(value = "/count",name = "统计用户或经验的点赞数量")
    public Result<Long> count(String experienceId,String userId){
        return Result.success("查询成功！",goodService.countGood(experienceId,userId));
    }

    /**
     * 通过经验ID和用户ID查询是否点赞
     * @param experienceId 经验ID
     * @param userId 用户ID
     * @return 点赞信息
     */
    @GetMapping(value = "/getByUserId",name = "通过ID获取")
    public Result<ExperienceGood> getByUserId(String experienceId, String userId){
        return Result.success(goodService.getByUserId(experienceId,userId));
    }

    /**
     * 通过ID查询点赞信息
     * @param goodId 点赞ID
     * @return 点赞
     */
    @GetMapping(value = "/getById",name = "通过ID获取")
    public Result<ExperienceGood> getById(String goodId){
        return Result.success(goodService.getById(goodId));
    }

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<Page<ExperienceResp>> list(@RequestBody GoodForm form){
        return Result.success(goodService.getPage(form));
    }

    /**
     * 保存或更新点赞
     * @param good 点赞
     * @return 点赞
     */
    @PostMapping(value = "/save",name = "保存或更新点赞")
    public Result<ExperienceGood> save(@RequestBody ExperienceGood good){
        if (goodService.saveOrUpdate(good)){
            return Result.success("保存成功！",good);
        }
        return Result.success("保存失败！");
    }

    /**
     * 删除点赞
     * @param goodId 点赞ID
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除点赞")
    public Result<?> delete(String goodId){
        if (goodService.removeById(goodId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }
}
