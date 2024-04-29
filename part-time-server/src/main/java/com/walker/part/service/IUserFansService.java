package com.walker.part.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.UserFans;
import com.baomidou.mybatisplus.extension.service.IService;
import com.walker.part.form.FansForm;
import com.walker.part.form.PageForm;
import com.walker.part.response.UserFansResp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Walker
 * @since 2024-04-29 09:33:39
 */
public interface IUserFansService extends IService<UserFans> {

    /**
     * 通过关注人ID和被关注人ID查询关注信息
     * @param fromId 关注ID
     * @param toId 被关注ID
     * @return 关注
     */
    UserFans getByFromToId(String fromId, String toId);

    /**
     * 分页查询关注列表
     * @param form 表单
     * @return 分页结果
     */
    Page<UserFansResp> getPage(FansForm form);
}
