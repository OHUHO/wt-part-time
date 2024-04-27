package com.walker.part.controller;

import com.walker.part.form.UploadForm;
import com.walker.part.service.IUploadService;
import com.walker.part.utils.Result;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: FileController
 * @author: Walker
 * @date: 2024-04-21 11:07:07
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final IUploadService uploadService;

    /**
     * 上传文件
     * @param form 表单
     * @return 访问路径
     */
    @PostMapping(value = "/upload",name = "文件上传")
    public Result<String> upload(UploadForm form){
        if (ObjectUtils.isEmpty(form)){
            return Result.failed("文件不能为空！");
        }
        return Result.success("上传成功！",uploadService.upload(form));
    }

}
