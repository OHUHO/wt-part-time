package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walker.part.entity.JobInfo;
import com.walker.part.entity.RegistrationSheet;
import com.walker.part.entity.Reviews;
import com.walker.part.entity.UserInfo;
import com.walker.part.exception.ApplicationException;
import com.walker.part.form.RegistrationForm;
import com.walker.part.mapper.RegistrationSheetMapper;
import com.walker.part.response.JobInfoResp;
import com.walker.part.response.RegistrationResp;
import com.walker.part.service.IJobInfoService;
import com.walker.part.service.IRegistrationSheetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walker.part.service.IReviewsService;
import com.walker.part.service.IUserInfoService;
import com.walker.part.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Walker
 * @since 2024-04-21 09:52:39
 */
@Service
@RequiredArgsConstructor
public class RegistrationSheetServiceImpl extends ServiceImpl<RegistrationSheetMapper, RegistrationSheet> implements IRegistrationSheetService {

    private final IJobInfoService jobInfoService;

    private final IUserInfoService userInfoService;

    private final IReviewsService reviewsService;

    @Override
    public Page<RegistrationResp> getPage(RegistrationForm form) {
        Page<RegistrationSheet> page = new Page<>(form.getCurrent(), form.getSize());
        Page<RegistrationSheet> sheetPage = getBaseMapper().selectPage(page, new LambdaQueryWrapper<RegistrationSheet>()
                .eq(StringUtils.isNoneBlank(form.getJobId()), RegistrationSheet::getJobId, form.getJobId())
                .eq(StringUtils.isNoneBlank(form.getUserId()), RegistrationSheet::getUserId, form.getUserId())
        );
        Page<RegistrationResp> pageInfo = new Page<>();
        pageInfo.setTotal(sheetPage.getTotal());
        List<RegistrationSheet> list = sheetPage.getRecords();
        if (CollectionUtils.isEmpty(list)){
            return pageInfo;
        }
        List<RegistrationResp> records = new ArrayList<>();
        for (RegistrationSheet registrationSheet : list) {
            RegistrationResp registrationResp = new RegistrationResp();
            BeanUtils.copyProperties(registrationSheet,registrationResp);
            //System.out.println("registrationSheet = " + registrationSheet);

            // 设置 兼职名称、地点、兼职种类、开始时间、结束时间
            JobInfoResp jobInfo = jobInfoService.getJobById(registrationSheet.getJobId(), null);
            registrationResp.setName(jobInfo.getName());
            registrationResp.setAddress(jobInfo.getAddress());
            registrationResp.setTypeName(jobInfo.getTypeName());
            registrationResp.setBeginTime(jobInfo.getBeginTime());
            registrationResp.setEndTime(jobInfo.getEndTime());

            // 设置用户信息
            UserInfo userInfo = userInfoService.getById(registrationSheet.getUserId());
            registrationResp.setUsername(userInfo.getUsername());
            registrationResp.setPortrait(userInfo.getPortrait());

            // 用户对商家对评价
            Reviews reviewsToBusiness = reviewsService.getByMultiId(form.getJobId(), userInfo.getId(), jobInfo.getBusinessId());
            registrationResp.setReviewsToBusiness(reviewsToBusiness);

            // 商家对用户对评价
            Reviews reviewsToUser = reviewsService.getByMultiId(form.getJobId(), jobInfo.getBusinessId(), userInfo.getId());
            registrationResp.setReviewsToUser(reviewsToUser);

            records.add(registrationResp);
        }
        pageInfo.setRecords(records);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveForm(RegistrationSheet form) {
        if (StringUtils.isBlank(form.getJobId())){
            throw new ApplicationException("参数不能为空！");
        }
        // 查询当前兼职信息
        JobInfo jobInfo = jobInfoService.getById(form.getJobId());

        if (jobInfo.getRegistered() + 1 > jobInfo.getCount()){
            throw new ApplicationException("兼职人数已满！");
        }
        // 兼职报名时间
        form.setRegistrationTime(LocalDateTime.now());
        form.setCreateTime(LocalDateTime.now());
        this.save(form);
        // 更新兼职表已报名人数
        jobInfo.setRegistered(jobInfo.getRegistered() + 1);
        return jobInfoService.saveOrUpdate(jobInfo);
    }

    @Override
    public boolean registrationEd(String jobId, String userId) {
        RegistrationSheet registration = getBaseMapper().selectOne(new LambdaQueryWrapper<RegistrationSheet>()
                .eq(RegistrationSheet::getJobId, jobId)
                .eq(RegistrationSheet::getUserId, userId)
        );
        return ObjectUtils.isNotEmpty(registration);
    }
}
