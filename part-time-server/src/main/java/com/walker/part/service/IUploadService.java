package com.walker.part.service;

import com.walker.part.form.UploadForm;

/**
 * @description: IUploadService
 * @author: Walker
 * @date: 2024-04-21 11:31:31
 * @version: 1.0.0
 */
public interface IUploadService {

    /**
     * 上传文件
     * @param form 表单
     * @return 访问路径
     */
    String upload(UploadForm form);
}
