package com.walker.part.response;

import com.walker.part.entity.Experience;
import lombok.*;

/**
 * @description: ExperienceResp
 * @author: Walker
 * @date: 2024-04-28 19:18:18
 * @version: 1.0.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceResp extends Experience {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户ID，值和createUserId相同
     */
    private String userId;

    /**
     * 头像
     */
    private String portrait;

}
