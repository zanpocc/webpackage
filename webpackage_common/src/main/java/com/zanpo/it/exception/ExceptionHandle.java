package com.zanpo.it.exception;

import com.zanpo.it.result.Result;
import com.zanpo.it.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
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
        Result result = new Result();
        if (e instanceof BaseException) {
            BaseException exception = (BaseException) e;
            String code = exception.getCode();
            String message = exception.getMessage();
            result.setCode(code);
            result.setMessage(message);
        } else {
            result.setCode(ResultEnum.PROGRAM_ERROR.getCode());
            result.setMessage(ResultEnum.PROGRAM_ERROR.getMessage());
        }

        return result;
    }
}
