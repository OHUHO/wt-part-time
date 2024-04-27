package com.walker.part.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.Carousel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.enums.ResultEnum;
import com.walker.part.form.PageForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
public interface ICarouselService extends IService<Carousel> {

    /**
     * 保存或更新轮播图
     * @param carousel 轮播图
     * @return 是否保存成功
     */
    boolean saveForm(Carousel carousel);

    /**
     * 分页查询
     * @param form 查询表单
     * @return 分页结果
     */
    Page<Carousel> getPage(PageForm form);
}
