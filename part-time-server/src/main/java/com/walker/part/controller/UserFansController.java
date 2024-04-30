package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.UserFans;
import com.walker.part.form.FansForm;
import com.walker.part.response.UserFansResp;
import com.walker.part.service.IUserFansService;
import com.walker.part.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Walker
 * @since 2024-04-29 09:33:39
 */
@RestController
@RequestMapping("/fans")
@RequiredArgsConstructor
public class UserFansController {

    private final IUserFansService fansService;

    /**
     * 统计用户的粉丝数量
     * @param userId 用户ID
     * @return 粉丝数量
     */
    @GetMapping(value = "/countFans",name = "统计用户的粉丝数量")
    public Result<Long> countFans(String userId){
        return Result.success("查询成功！",fansService.countFans(userId));
    }

    /**
     * 统计用户的关注数量
     * @param userId 用户ID
     * @return 关注数量
     */
    @GetMapping(value = "/countLove",name = "统计用户的关注数量")
    public Result<Long> countLove(String userId){
        return Result.success("查询成功！",fansService.countLove(userId));
    }


    /**
     * 通过关注人ID和被关注人ID查询关注信息
     * @param fromId 关注ID
     * @param toId 被关注ID
     * @return 关注
     */
    @GetMapping(value = "/getByFromToId",name = "通过ID获取")
    public Result<UserFans> getByFromToId(String fromId,String toId){
        return Result.success(fansService.getByFromToId(fromId,toId));
    }


    /**
     * 通过ID查询关注信息
     * @param fansId 关注ID
     * @return 关注
     */
    @GetMapping(value = "/getById",name = "通过ID获取")
    public Result<UserFans> getById(String fansId){
        return Result.success(fansService.getById(fansId));
    }

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<Page<UserFansResp>> list(@RequestBody FansForm form){
        return Result.success(fansService.getPage(form));
    }

    /**
     * 保存或更新关注
     * @param fans 关注
     * @return 关注
     */
    @PostMapping(value = "/save",name = "保存或更新关注")
    public Result<UserFans> save(@RequestBody UserFans fans){
        if (fansService.saveOrUpdate(fans)){
            return Result.success("保存成功！",fans);
        }
        return Result.success("保存失败！");
    }

    /**
     * 删除关注
     * @param fansId 关注ID
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除关注")
    public Result<?> delete(String fansId){
        if (fansService.removeById(fansId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }

}
