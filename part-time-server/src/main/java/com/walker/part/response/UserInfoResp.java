package com.walker.part.response;

import com.walker.part.entity.UserInfo;
import lombok.*;

/**
 * @description: UserInfoResp
 * @author: Walker
 * @date: 2024-04-26 08:27:27
 * @version: 1.0.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResp extends UserInfo {

    /**
     * 是否是商家
     */
    private boolean business = false;
}
