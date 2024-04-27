package com.walker.part.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.walker.part.exception.ApplicationException;
import com.walker.part.form.UploadForm;
import com.walker.part.service.IUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description: UploadServiceImpl
 * @author: Walker
 * @date: 2024-04-21 11:32:32
 * @version: 1.0.0
 */
@Service
public class UploadServiceImpl implements IUploadService {

    @Value("${storage.url}")
    private String url;

    @Value("${storage.path}")
    private String serverPath;

    @Override
    public String upload(UploadForm form) {
        String originalFilename = form.getFile().getOriginalFilename();
        assert originalFilename != null;
        String fileName = IdWorker.getIdStr() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String absPath = serverPath + fileName;
        try {
            form.getFile().transferTo(new File(absPath));
        } catch (IOException e) {
            throw new ApplicationException("文件上传失败");
        }
        return url + fileName;
    }
}
