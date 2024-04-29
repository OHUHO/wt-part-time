package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Carousel;
import com.walker.part.entity.Comment;
import com.walker.part.form.CommentForm;
import com.walker.part.form.PageForm;
import com.walker.part.response.CommentResp;
import com.walker.part.service.ICommentService;
import com.walker.part.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final ICommentService commentService;

    /**
     * 通过经验ID查询评论信息
     * @param experienceId 经验ID
     * @return 经验
     */
    //@GetMapping(value = "/getById",name = "通过ID获取")
    //public Result<> getById(String experienceId){
    //    return Result.success(commentService.getByExperienceId(experienceId));
    //}


    /**
     * 通过ID查询评论信息
     * @param carouselId 评论ID
     * @return 评论
     */
    //@GetMapping(value = "/getById",name = "通过ID获取")
    //public Result<Carousel> getById(String carouselId){
    //    return Result.success(carouselService.getById(carouselId));
    //}

    /**
     * 不分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<List<CommentResp>> list(@RequestBody CommentForm form){
        return Result.success(commentService.list(form));
    }

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/page")
    public Result<Page<CommentResp>> page(@RequestBody CommentForm form){
        return Result.success(commentService.getPage(form));
    }

    /**
     * 保存或更新评论
     * @param comment 评论
     * @return 评论
     */
    @PostMapping(value = "/save",name = "保存或更新评论")
    public Result<Comment> save(@RequestBody Comment comment){
        if (commentService.saveForm(comment)){
            return Result.success("保存成功！",comment);
        }
        return Result.success("保存失败！");
    }

    /**
     * 删除评论
     * @param commentId 评论ID
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除评论")
    public Result<?> delete(String commentId){
        if (commentService.removeById(commentId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }

}
