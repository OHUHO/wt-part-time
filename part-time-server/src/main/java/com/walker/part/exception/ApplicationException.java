package com.walker.part.exception;

/**
 * @description: ApplicationException
 * @author: Walker
 * @date: 2024-04-21 11:41:41
 * @version: 1.0.0
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }
}
