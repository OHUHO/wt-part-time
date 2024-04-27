package com.walker.part.entity;

import lombok.*;

/**
 * @description: WechatModel
 * @author: Walker
 * @date: 2024-04-22 20:04:04
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WechatModel {

    /**
     * session key
     */
    private String session_key;

    /**
     * open id
     */
    private String openid;

}
