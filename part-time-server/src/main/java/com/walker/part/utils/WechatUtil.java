package com.walker.part.utils;

import com.walker.part.entity.WechatModel;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 获取微信openID工具
 * @author: Walker
 * @date: 2024-04-22 19:44:44
 * @version: 1.0.0
 */
public class WechatUtil {

    /**
     * 获取openId
     * @param code 微信临时凭证
     * @return openId
     */
    public static String generateOpenId(String code) {
        /*
         * https://api.weixin.qq.com/sns/jscode2session
         * appid=APPID
         * secret=SECRET
         * js_code=JSCODE
         * grant_type=authorization_code
         */
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<>();
        param.put("appid", "wxf96c1bcfb282e83c");
        param.put("secret", "41d9c63ace53a8997f3c075b625386d9");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        // 获取openid
        String result = HttpClientUtil.doGet(url, param);
        //System.out.println("result = " + result);
        WechatModel wechatModel = JsonUtils.jsonToPojo(result, WechatModel.class);
        if (ObjectUtils.isNotEmpty(wechatModel)){
            return wechatModel.getOpenid();
        }
        return null;
    }
}
