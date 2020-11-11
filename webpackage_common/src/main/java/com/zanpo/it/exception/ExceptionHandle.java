package com.zanpo.it.exception;

import com.zanpo.it.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/11 12:49
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Result handBaseException(BaseException e){
        String message = e.getMessage();
        return Result.failed(message);
    }
}
