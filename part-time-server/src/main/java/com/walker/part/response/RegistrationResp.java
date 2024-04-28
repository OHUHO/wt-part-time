package com.walker.part.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.walker.part.entity.RegistrationSheet;
import com.walker.part.entity.Reviews;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: RegistrationResp
 * @author: Walker
 * @date: 2024-04-27 08:52:52
 * @version: 1.0.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResp extends RegistrationSheet {

    /**
     * 兼职名称
     */
    private String name;

    /**
     * 兼职种类
     */
    private String typeName;

    /**
     * 兼职开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime beginTime;

    /**
     * 兼职结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime endTime;

    /**
     * 兼职地点
     */
    private String address;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户头像
     */
    private String portrait;

    /**
     * 用户对商家对的评价信息，默认为null
     */
    private Reviews reviewsToBusiness = null;

    /**
     * 商家对用户的评价信息，默认为null
     */
    private Reviews reviewsToUser = null;
}
