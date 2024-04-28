package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Carousel;
import com.walker.part.entity.Reviews;
import com.walker.part.form.PageForm;
import com.walker.part.service.IReviewsService;
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
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewsController {

    private final IReviewsService reviewsService;


    /**
     * 通过兼职ID，评论人ID，被评论人ID查询评论信息
     * @param jobId 兼职ID
     * @param fromId 评论人ID
     * @param toId 被评论人ID
     * @return 评价信息
     */
    @GetMapping(value = "/getByMultiId",name = "通过ID获取")
    public Result<Reviews> getByMultiId(String jobId,String fromId,String toId){
        return Result.success(reviewsService.getByMultiId(jobId,fromId,toId));
    }

    /**
     * 通过ID查询评价信息
     * @param reviewsId 评价ID
     * @return 评价信息
     */
    @GetMapping(value = "/getById",name = "通过ID获取")
    public Result<Reviews> getById(String reviewsId){
        return Result.success(reviewsService.getById(reviewsId));
    }

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<Page<Reviews>> list(@RequestBody PageForm form){
        return Result.success(reviewsService.getPage(form));
    }

    /**
     * 保存或更新评价信息
     * @param form 表单
     * @return 评价信息
     */
    @PostMapping(value = "/save",name = "保存或更新评价")
    public Result<Reviews> save(@RequestBody Reviews form){
        if (reviewsService.saveForm(form)){
            return Result.success("保存成功！",form);
        }
        return Result.success("保存失败！");
    }

    /**
     * 删除评价信息
     * @param reviewsId 评价ID
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除评价")
    public Result<?> delete(String reviewsId){
        if (reviewsService.removeById(reviewsId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }


}
