package com.walker.part.handler;

import com.walker.part.enums.ResultEnum;
import com.walker.part.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * @description: ApplicationExceptionHandler
 * @author: Walker
 * @date: 2024-04-21 11:43:43
 * @version: 1.0.0
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * 文件操作异常
     * @param exception 异常信息
     * @return json
     */
    @ExceptionHandler
    public ResponseEntity<Result<String>> exceptionHandler(IOException exception) {
        exception.printStackTrace();
        ResultEnum.FAILED.setCode(5014);
        ResultEnum.FAILED.setMessage(exception.getMessage());
        Result<String> result = Result.failed(ResultEnum.FAILED);
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
