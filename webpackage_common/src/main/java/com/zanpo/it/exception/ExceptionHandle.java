package com.zanpo.it.exception;

import com.zanpo.it.result.Result;
import com.zanpo.it.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handBaseException(Exception e){
        log.error(e.getMessage());
        if(e instanceof BaseException){
            BaseException exception = (BaseException) e;

        }

        return Result.failed(ResultEnum.PROGRAM_ERROR.getCode(),e.getMessage());
    }
}
