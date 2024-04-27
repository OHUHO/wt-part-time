package com.walker.part.form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 文件上传表单
 * @author: Walker
 * @date: 2024-04-21 11:09:09
 * @version: 1.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UploadForm {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件
     */
    private MultipartFile file;
}
