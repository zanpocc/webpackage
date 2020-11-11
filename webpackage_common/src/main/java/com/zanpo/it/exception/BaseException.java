package com.zanpo.it.exception;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/3 21:57
 */
public class BaseException extends RuntimeException {
    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
