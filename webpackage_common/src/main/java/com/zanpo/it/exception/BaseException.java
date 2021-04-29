package com.zanpo.it.exception;

import com.zanpo.it.result.ResultEnum;
import lombok.Data;

/**
 * 通用异常
 *
 * @author cg
 * @date 2020/11/3 21:57
 */
@Data
public class BaseException extends RuntimeException {
    private String code;

    private String message;

    public BaseException() {
        super(ResultEnum.PROGRAM_ERROR.getMessage());
        this.code = ResultEnum.PROGRAM_ERROR.getCode();
        this.message = ResultEnum.PROGRAM_ERROR.getMessage();
    }

    public BaseException(String code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(String message) {
        super(message);
        this.code = ResultEnum.PROGRAM_ERROR.getCode();
        this.message = message;
    }

    public BaseException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }
}
