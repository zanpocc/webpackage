package com.zanpo.it.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/3 21:57
 */
public class BaseException extends NestedRuntimeException {
    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
