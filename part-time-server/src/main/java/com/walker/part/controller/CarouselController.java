package com.walker.part.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Carousel;
import com.walker.part.form.PageForm;
import com.walker.part.service.ICarouselService;
import com.walker.part.utils.Result;
import lombok.Getter;
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
@RequestMapping("/carousel")
@RequiredArgsConstructor
public class CarouselController {

    private final ICarouselService carouselService;

    /**
     * 通过ID查询轮播图信息
     * @param carouselId 轮播图ID
     * @return 轮播图
     */
    @GetMapping(value = "/getById",name = "通过ID获取")
    public Result<Carousel> getById(String carouselId){
        return Result.success(carouselService.getById(carouselId));
    }

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    @PostMapping("/list")
    public Result<Page<Carousel>> list(@RequestBody PageForm form){
        return Result.success(carouselService.getPage(form));
    }

    /**
     * 保存或更新轮播图
     * @param carousel 轮播图
     * @return 轮播图
     */
    @PostMapping(value = "/save",name = "保存或更新轮播图")
    public Result<Carousel> save(@RequestBody Carousel carousel){
        if (carouselService.saveForm(carousel)){
            return Result.success("保存成功！",carousel);
        }
        return Result.success("保存失败！");
    }

    /**
     * 删除轮播图
     * @param carouselId 轮播图ID
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/delete",name = "删除轮播图")
    public Result<?> delete(String carouselId){
        if (carouselService.removeById(carouselId)){
            return Result.success("删除成功！");
        }
        return Result.failed("删除失败！");
    }
}
