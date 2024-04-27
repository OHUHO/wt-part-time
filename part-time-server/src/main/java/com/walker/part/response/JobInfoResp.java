package com.walker.part.response;

import com.walker.part.entity.JobInfo;
import lombok.*;

/**
 * @description: JobInfoResp
 * @author: Walker
 * @date: 2024-04-26 20:09:09
 * @version: 1.0.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobInfoResp extends JobInfo {

    /**
     * 兼职类型
     */
    private String typeName;

    /**
     * 店铺名称
     */
    private String storeName;

}
